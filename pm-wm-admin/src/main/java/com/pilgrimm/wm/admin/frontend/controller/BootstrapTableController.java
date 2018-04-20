package com.pilgrimm.wm.admin.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend/bootstrapTable")
public class BootstrapTableController {
	
	@RequestMapping("/index")
	public String index() {
		return "/frontend/bootstrapTable/index";
	}
	
}
