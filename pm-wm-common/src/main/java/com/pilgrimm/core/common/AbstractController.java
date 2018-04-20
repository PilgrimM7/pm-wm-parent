package com.pilgrimm.core.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pilgrimm.core.validate.Validate;

public abstract class AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);
	
	protected Map<String, Object> read(HttpServletRequest request,
			Validate validate) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Iterator<Entry<String, String[]>> entries = request.getParameterMap()
				.entrySet().iterator();
		Entry<String, String[]> entry = null;
		for (; entries.hasNext();) {
			entry = entries.next();
			if (entry.getValue().length == 1) {
				String value = validate.validate(entry.getKey(), entry.getValue()[0]);

				resultMap.put(entry.getKey(), value);
			} else {
				String[] values = new String[entry.getValue().length];
				for (int i = 0; i < entry.getValue().length; i++) {
					values[i] = validate.validate(entry.getKey(), entry.getValue()[i]);
				}

				resultMap.put(entry.getKey(), values);
			}
		}
		return resultMap;
	}

}
