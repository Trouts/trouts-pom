package cn.trouts.framework.context.conf;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cn.trouts.framework.context.TroutsContext;
import cn.trouts.framework.utils.StringUtils;
import cn.trouts.repositories.TroutsRepositoryImpl;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration
@ComponentScan(basePackages = "cn.trouts.components")
@PropertySources({
	@PropertySource("classpath:tfk.properties"),
	@PropertySource(value="classpath:jdbc.properties",ignoreResourceNotFound=true)
})
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeServiceImpl")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "cn.trouts.repositories",repositoryBaseClass=TroutsRepositoryImpl.class)
@EnableAspectJAutoProxy //启用AOP
public class TroutsframeworkConfig implements EnvironmentAware{
	private Environment env;
	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		this.env = environment;
	}
	
	
	@Bean
	public PlatformTransactionManager transactionManager()

	{
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}
	
	
	@Bean
	public Map<String, Object> jpaProperties() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("hibernate.dialect", MySQL5Dialect.class.getName());

		if (StringUtils.equals("true", env.getProperty("hibernate.show_sql"))) {
			props.put("hibernate.show_sql", true);
		} else {
			props.put("hibernate.show_sql", false);
		}

		return props;
	}

	

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		
		Map<String, Object> props = jpaProperties();
	

		if (StringUtils.equals("false", env.getProperty("hibernate.show_sql"))) {
			props.put("hibernate.show_sql", false);
		} else {
			props.put("hibernate.show_sql", true);
		}

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		if (StringUtils.equals("false", env.getProperty("hibernate.show_sql"))) {
			vendorAdapter.setShowSql(true);
		} else {
			vendorAdapter.setShowSql(false);
		}

		if ("false".equals(env.getProperty("hibernate.generate_ddl"))) {
			vendorAdapter.setGenerateDdl(false);
		} else {
			vendorAdapter.setGenerateDdl(true);
		}
		
		
		String database = StringUtils.isBlank(env.getProperty("jdbc.database")) ? "MYSQL" : env
				.getProperty("jdbc.database").toUpperCase();
		switch (database) {
		case "MYSQL": {
			vendorAdapter.setDatabase(Database.MYSQL);
			props.put("hibernate.dialect", MySQL5Dialect.class.getName());
			break;
		}
		case "DB2": {
			vendorAdapter.setDatabase(Database.DB2);
			break;
		}
		case "ORACLE": {
			vendorAdapter.setDatabase(Database.ORACLE);
			break;
		}
		case "SQL_SERVER": {
			vendorAdapter.setDatabase(Database.SQL_SERVER);
			break;
		}
		default: {
			throw new RuntimeException("database 不支持：" + database);
		}
		}
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("cn.trouts.entitys");
		factory.setDataSource(dataSource());
		factory.setJpaPropertyMap(props);
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	@Bean
	public DataSource dataSource() {
		DruidDataSource dds = new DruidDataSource();
		dds.setUrl(env.getProperty("jdbc.url"));
		dds.setUsername(env.getProperty("jdbc.username"));
		dds.setPassword(env.getProperty("jdbc.password"));
		dds.setInitialSize(1);
		dds.setMaxActive(20);
		// P6DataSource p6spy = new P6DataSource(c3p0);
		return dds;

	}

	@Bean(name = { "messageSource" })
	public MessageSource configureMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/messages",
				"classpath:messages/springMessages");
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * 审计 ：操作人
	 * @return
	 */
	@Bean
	public AuditorAware<String> auditorProvider() {

		return () -> {
			return TroutsContext.getCurrentUser();
		};
	}
	
	

}
