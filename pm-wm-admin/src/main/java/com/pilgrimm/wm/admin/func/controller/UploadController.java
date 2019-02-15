package com.pilgrimm.wm.admin.func.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/func/upload")
public class UploadController {
	
	@ResponseBody
	@RequestMapping(value = "/upload")
	public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String param_1 = request.getParameter("param_1");
		System.out.println("param_1 -> " + param_1);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile mFile = multipartRequest.getFile("file_1");
		try {
			File file = new File("F:\\upload\\" + mFile.getOriginalFilename());
			InputStream in = mFile.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			in.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("errcode", "0");
		return map;
	}
}
