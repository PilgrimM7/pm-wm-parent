package com.pilgrimm.wm.admin.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend/select2")
public class Select2Controller {
	
	@RequestMapping("/index")
	public String index() {
		return "/frontend/select2/index";
	}
	
}
