package com.pilgrimm.wm.portal.animation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animation")
public class AnimationController {
	
	@RequestMapping("/snowflake ")
	public String index() {
		return "/animation/snowflake ";
	}
}
