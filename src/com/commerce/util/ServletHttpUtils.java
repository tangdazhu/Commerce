package com.commerce.util;


import javax.servlet.http.HttpServletRequest;

public class ServletHttpUtils {

	public static String getBasePath(HttpServletRequest request) {

		String path = request.getContextPath();
		String serverPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort();
		String basePath = serverPath + path + "/";

		return basePath;
	}

}
