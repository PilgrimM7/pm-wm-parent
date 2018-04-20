package com.pilgrimm.wm.admin.frontend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/frontend/jqGrid")
public class JqGridController {
	
	@RequestMapping("/index")
	public String index() {
		return "/frontend/jqGrid/index";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "1");
		map.put("invdate", "2");
		map.put("name", "3");
		map.put("amount", "4");
		map.put("tax", "5");
		map.put("total", "6");
		map.put("note", "7");
		list.add(map);
		result.put("page", 1);
		result.put("total", 1);
		result.put("records", 1);
		result.put("rows", list);
		return result;
	}
	
}
