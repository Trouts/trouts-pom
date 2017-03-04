package cn.trouts.components.component.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import cn.trouts.framework.utils.TroutsLogUtils;

@Component
public class URLFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource, InitializingBean {

	private final static Logger logger = TroutsLogUtils
			.getLogger(URLFilterInvocationSecurityMetadataSource.class);
	private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections
			.emptyList();
	// 权限集合
	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

//	@Autowired
//	private ResourcesService resourcesService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.access.SecurityMetadataSource#getAttributes
	 * (java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		final HttpServletRequest request = ((FilterInvocation) object)
				.getRequest();

		Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;
//		if (ContextHelper.hasAuthorities(AuthoritiesMark.ADMINISTRATOR_MARK)) {
//			// logger.info(AuthoritiesMark.ADMINISTRATOR_MARK+" 拥有所有访问权限");
////			logger.info("超级用户直接访问 ->{} ",
////					 AuthoritiesMark.ADMINISTRATOR_MARK);
//			return attrs;
//		}
//		String contextPath = request.getServletContext().getContextPath();
//
//		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
//				.entrySet()) {
//			AntPathRequestMatcher antpath = (AntPathRequestMatcher) entry
//					.getKey();
//			if (StringHelper.equals(contextPath + antpath.getPattern(),
//					request.getRequestURI())) {
//				attrs = entry.getValue();
//				logger.info("URL资源权限：" + request.getRequestURI() + " -> "
//						+ attrs);
//				break;
//			}
//		}
//		if (attrs.size() == 0) {
//			attrs = SecurityConfig
//					.createListFromCommaDelimitedString(AuthoritiesMark.USER_MARK);
//		}

		// logger.info("URL资源：" + request.getRequestURI() + " -> " + attrs);
		return attrs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.access.SecurityMetadataSource#
	 * getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
				.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.access.SecurityMetadataSource#supports(java
	 * .lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	private Map<String, String> loadResuorce() {
		Map<String, String> map = new LinkedHashMap<String, String>();

//		List<Map<String, String>> list = resourcesService
//				.getResourceMapping(ResourcesType.URL.name());
//		Iterator<Map<String, String>> it = list.iterator();
//		while (it.hasNext()) {
//			Map<String, String> rs = it.next();
//			String resourcePath = rs.get("resourcePath");
//			String authorityMark = rs.get("authorityMark");
//
//			if (map.containsKey(resourcePath)) {
//				String mark = map.get("resourcePath");
//				map.put(resourcePath, mark + "," + authorityMark);
//			} else {
//				map.put(resourcePath, authorityMark);
//			}
//		}
		return map;
	}

	protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap() {
		Map<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();

		Map<String, String> resMap = this.loadResuorce();
		for (Map.Entry<String, String> entry : resMap.entrySet()) {
			String key = entry.getKey();
			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
			atts = SecurityConfig.createListFromCommaDelimitedString(entry
					.getValue());
			// atts.add(new SecurityConfig(AuthoritiesMark.ADMINISTRATOR_MARK));
			map.put(new AntPathRequestMatcher(key), atts);
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		this.requestMap = this.bindRequestMap();
		logger.info("资源权限列表" + this.requestMap);
	}

	public void refreshResuorceMap() {
		this.requestMap = this.bindRequestMap();
	}

}
