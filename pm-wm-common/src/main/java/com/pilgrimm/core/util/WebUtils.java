package com.pilgrimm.core.util;

public class WebUtils {
	
	// 获取项目部署路径
    public static String getRealPath() {
    	String realPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
		int len = realPath.indexOf("WEB-INF");
		realPath = realPath.substring(0, len);
		return realPath;
    }

}
