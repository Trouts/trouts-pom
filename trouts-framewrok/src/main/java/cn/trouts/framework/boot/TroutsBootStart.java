package cn.trouts.framework.boot;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import cn.trouts.framework.context.conf.TroutsframeworkConfig;

public class TroutsBootStart  extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{TroutsframeworkConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
//		return new Class[]{WebConfig.class};
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}
}
