package cn.trouts.trouts.framework.web.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class WebUtils {
	
	/**
	 * 获取当前登入用户
	 * 
	 * @return 用户名
	 */
	public static String getCurrentUsername() {
		UserDetails userDetails;

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication == null) {
			return "system";
		}
		
		if (!authentication.isAuthenticated()) {
			return "anonymous";
		} else {
			userDetails = (UserDetails) authentication.getPrincipal();
		}
		return userDetails.getUsername();
	}

}
