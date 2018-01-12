package com.pilgrimm.wm.portal.test.controller;

import java.util.Date;

import com.pilgrimm.core.util.wy.DateUtil;

public class TestController {
	
	public String test() {
		DateUtil.dateToStr(new Date(), "yyyy-MM-dd");
		return "";
	}
}
