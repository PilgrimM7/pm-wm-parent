package com.pilgrimm.wm.admin.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pilgrimm.core.common.AbstractController;
import com.pilgrimm.core.util.SpringUtil;
import com.pilgrimm.core.validate.DefaultValidate;
import com.pilgrimm.wm.common.user.model.User;
import com.pilgrimm.wm.common.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * 事务测试
	 */
	@RequestMapping("/txTest")
	public void txTest(HttpServletRequest request) {
		
		try {
			userService.txTest(new User(1, "cc", 27), new User(1, "ff", 27));
		} catch (Exception e) {
			logger.error("异常" + "_" + e.getMessage(), e);
		}
	}
	
	/**
	 * 获取所有用户列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request) {

		List<User> findAll = userService.findAll();
		request.setAttribute("userList", findAll);
		
		UserService us = (UserService) SpringUtil.getBeanByName("userService");
		System.out.println(us);
		
		return "/user/allUser2";
	}

	/**
	 * 跳转到添加用户界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request) {

		return "/user/addUser";
	}

	/**
	 * 添加用户并重定向
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser")
	public String addUser(User user, HttpServletRequest request) {
		user.setName("小张");
		user.setAge(15);
		userService.save(user);
		return "redirect:/user/getAllUser";
	}

	/**
	 * 编辑用户
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateUser")
	public String updateUser(User user, HttpServletRequest request) {

		if (userService.update(user)) {
			user = userService.findById(user.getId());
			request.setAttribute("user", user);
			return "redirect:/user/getAllUser";
		} else {
			return "/error";
		}
	}

	/**
	 * 根据id查询单个用户
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	public String getUser(int id, HttpServletRequest request) {

		request.setAttribute("user", userService.findById(id));
		return "/user/editUser";
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delUser")
	public void delUser(int id, HttpServletRequest request,
			HttpServletResponse response) {
		
		String result = "{\"result\":\"error\"}";
		if (userService.delete(id)) {
			result = "{\"result\":\"success\"}";
		}
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			logger.error("异常" + "_" + e.getMessage(), e);
		}

	}
	
	/**
	 * 获取所有用户列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<User> list = userService.findAll();
		result.put("rows", list);
		return result;
	}
	
	/**
	 * 获取所有用户列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryForList")
	public Map<String, Object> queryForList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<User> list = userService.findAll();
		result.put("rows", list);
		return result;
	}
	
	/**
	 * 列表页 
	 */
	@RequestMapping("/index")
	public String index() {
		return "/user/index";
	}
	
	/**
	 * 获取分页数据
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryForPage")
	public Map<String, Object> queryForPage(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> paramMap = read(request, new DefaultValidate());
			result = userService.queryForPage(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
