package com.commerce.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletHttpUtils {

	public static String getBasePath(HttpServletRequest request) {

		String path = request.getContextPath();
		String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		String basePath = serverPath + path + "/";

		return basePath;
	}

	public static HttpServletRequest getHttpServletRequest() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

}
