package com.pilgrimm.wm.admin.func.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pilgrimm.core.util.mt.ExcelUtil;

@Controller
@RequestMapping("/func/excel")
public class ExcelController {
	
	@RequestMapping("/index")
	public String index() {
		return "/func/excel/index";
	}
	
	/**
	 * @description 根据批注导入
	 * @param request
	 * @param response
	 */
	@RequestMapping("/importByComment")
	public void importByComment(HttpServletRequest request, HttpServletResponse response) {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile mFile = multipartRequest.getFile("filename");
		try {
			InputStream in = mFile.getInputStream();
			List<Map<String, Object>> rows = new ExcelUtil().readExcelToMap(in);
			System.out.println(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
