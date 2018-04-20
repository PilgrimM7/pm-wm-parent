package com.pilgrimm.core.util;

import java.util.Map;

public class ParamUtil {

	public static String getString(Map<String, Object> paramMap, String key) {
		return getString(paramMap, key, "");
	}

	public static String getString(Map<String, Object> paramMap, String key, String def) {
		if (paramMap == null) {
			return def;
		}
		def = paramMap.get(key) != null ? paramMap.get(key).toString() : def;
		return def;
	}

	public static int getInt(Map<String, Object> paramMap, String key) {
		return getInt(paramMap, key, 0);
	}

	public static int getInt(Map<String, Object> paramMap, String key, int def) {
		try {
			def = Integer.valueOf(paramMap.get(key).toString()).intValue();
		} catch (Exception ex) {
			return def;
		}
		return def;
	}

}
