package cn.trouts.trouts.framework.web.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import cn.trouts.components.component.web.URLFilterInvocationSecurityMetadataSource;
@Configuration
@EnableWebSecurity
public class TroutsSecurityConfig extends WebSecurityConfigurerAdapter
		implements EnvironmentAware {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;

	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置不拦截规则
		web.ignoring().antMatchers("/anon/**", "/resources/**","/**/*.js","/**/*.css","/javax.faces.resource/**",
				"/css/**,/js/**", "/images/**");

	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);
		http.authorizeRequests()
				.anyRequest()
				.authenticated()
				.withObjectPostProcessor(
						new ObjectPostProcessor<FilterSecurityInterceptor>() {
							public <O extends FilterSecurityInterceptor> O postProcess(
									O fsi) {
								fsi.setAccessDecisionManager(accessDecisionManager());
								fsi.setSecurityMetadataSource(uRLFilterInvocationSecurityMetadataSource());
								return fsi;
							}
						}).and().formLogin().loginPage("/anon/login.face")
				.loginProcessingUrl("/login").permitAll().and().headers()
				.disable().logout().and().csrf().disable()
				.authenticationProvider(authenticationProvider());

	}

	@Bean(name = "accessDecisionManager")
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
		decisionVoters.add(roleVoter());
		decisionVoters.add(authenticatedVoter());
		decisionVoters.add(webExpressionVoter());// 启用表达式投票器

		AffirmativeBased accessDecisionManager = new AffirmativeBased(
				decisionVoters);

		return accessDecisionManager;
	}

	@Bean
	public RoleVoter roleVoter() {
		RoleVoter rv = new RoleVoter();
		rv.setRolePrefix("");
		return rv;
	}

	@Bean
	public AuthenticatedVoter authenticatedVoter() {
		AuthenticatedVoter av = new AuthenticatedVoter();
		return av;
	}

	/*
	 * 表达式投票器
	 */
	@Bean(name = "expressionVoter")
	public WebExpressionVoter webExpressionVoter() {
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());
		return webExpressionVoter;
	}



	@Bean
	public URLFilterInvocationSecurityMetadataSource uRLFilterInvocationSecurityMetadataSource() {
		URLFilterInvocationSecurityMetadataSource ufism = new URLFilterInvocationSecurityMetadataSource();
		return ufism;
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
		dap.setHideUserNotFoundExceptions(false);
//		dap.setUserDetailsService(userService);
		dap.setPasswordEncoder(passwordEncode());
		dap.setSaltSource(saltSource());
		dap.setMessageSource(messageSource);
		return dap;
	}

	@Bean
	public Md5PasswordEncoder passwordEncode() {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();

		return md5;
	}
	@Bean
	public ReflectionSaltSource saltSource() {
		ReflectionSaltSource rss = new ReflectionSaltSource();
		rss.setUserPropertyToUse("username");
		return rss;
	}

	/*
	 * 表达式控制器
	 */
	@Bean(name = "expressionHandler")
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		return webSecurityExpressionHandler;
	}

}
