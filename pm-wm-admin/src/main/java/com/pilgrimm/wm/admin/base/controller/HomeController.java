package com.pilgrimm.wm.admin.base.controller;

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
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String userName, String password, Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			model.addAttribute("userName", "用户名错误！");
			return "login";
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			model.addAttribute("password", "密码错误！");
			return "login";
		}
		return "/home/index";
	}

}
