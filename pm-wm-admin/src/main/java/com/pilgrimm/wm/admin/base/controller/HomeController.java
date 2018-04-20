package com.pilgrimm.wm.admin.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pilgrimm.core.common.AbstractController;

@Controller
@RequestMapping("/home")
public class HomeController extends AbstractController {
	
	/**
	 * 主页
	 */
	@RequestMapping("/index")
	public String index() {
		return "/home/index";
	}

	/**
	 * 列表页 
	 */
	@RequestMapping("/adminlte/workplate")
	public String workplate() {
		return "/home/adminlte/workplate";
	}
	
	/**
	 * 列表页 
	 */
	@RequestMapping("/metronic/index")
	public String index2() {
		return "/home/metronic/index";
	}
	
	/**
	 * 列表页 
	 */
	@RequestMapping("/hplus/index")
	public String index3() {
		return "/home/hplus/index";
	}

}
