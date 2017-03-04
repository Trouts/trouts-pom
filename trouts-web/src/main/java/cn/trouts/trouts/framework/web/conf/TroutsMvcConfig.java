package cn.trouts.trouts.framework.web.conf;

import java.util.concurrent.TimeUnit;

import cn.trouts.components.component.TroutsEnvironment;
import cn.trouts.framework.utils.StringUtils;
import cn.trouts.framework.utils.TroutsLogUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"cn.trouts.controllers"})
public class TroutsMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String handlers = TroutsEnvironment.getValue("web.resource.handlers");
        String locations = TroutsEnvironment.getValue("web.resource.locations");

        if(StringUtils.isNoneBlank(handlers,locations)){
            TroutsLogUtils.printLog("web.resource.handler,web.resource.handler config info: {} == {}",
                    handlers,locations);
            registry.addResourceHandler(StringUtils.split(handlers,","))
                    .addResourceLocations(StringUtils.split(locations, ","))
                    .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
        } else {
            TroutsLogUtils.printLog("web.resource.handler,web.resource.handler no config at the same time: {}=={}",
                    handlers,locations);
            registry.addResourceHandler("/resources/**")
                    .addResourceLocations("/resources/")
                    .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
        }

    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        String viewPrefix = TroutsEnvironment.getValue("web.view.prefix");
        String viewSuffix = TroutsEnvironment.getValue("web.view.suffix");
        if(StringUtils.isNotBlank(viewPrefix)){
            resolver.setPrefix(viewPrefix);
        } else {
            resolver.setPrefix("/WEB-INF/");
        }
        if(StringUtils.isNotBlank(viewSuffix)){
            resolver.setPrefix(viewSuffix);
        } else {
            resolver.setPrefix(".jsp");
        }
        return resolver;
    }


}
