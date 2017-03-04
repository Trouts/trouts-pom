package cn.trouts.trouts.framework.web.conf;



import cn.trouts.components.component.TroutsEnvironment;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import cn.trouts.framework.context.conf.TroutsframeworkConfig;

public class TroutsWebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		 	return new Class[]{TroutsframeworkConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{TroutsMvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub

		System.out.println("**********"+TroutsEnvironment.getValue("web.servlet.mappings"));
		return null;
	}

}
