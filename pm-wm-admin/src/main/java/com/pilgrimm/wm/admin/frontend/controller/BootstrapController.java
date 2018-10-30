package com.pilgrimm.wm.admin.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend/bootstrap")
public class BootstrapController {
	
	@RequestMapping("/index")
	public String index() {
		return "/frontend/bootstrap/index";
	}
	
}
