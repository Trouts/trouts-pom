package cn.trouts.framework.context;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class TroutsContext {
	/**
	 * 获取当前登入用户
	 * 
	 * @return 用户名
	 */
	public static String getCurrentUser() {
		UserDetails userDetails;

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return "system";
		} else {
			userDetails = (UserDetails) authentication.getPrincipal();
		}
		return userDetails.getUsername();
	}

}
