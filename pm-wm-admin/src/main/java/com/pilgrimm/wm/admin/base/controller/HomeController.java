package com.pilgrimm.wm.admin.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * 首页
	 */
	@RequestMapping("/home")
	public String home() {
		return "/home/home";
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
	
	/**
	 * 登录页
	 */
	@RequestMapping("/login")
	public String login() {
		return "/home/login";
	}
	
	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", "0");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			map.put("errcode", "1");
			map.put("errmsg", "用户名错误！");
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			map.put("errcode", "1");
			map.put("errmsg", "密码错误！");
		}
		return map;
	}
	
	/**
	 * 登出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return "redirect:/home/login";
	}

}
