package com.pilgrimm.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		MultipartFile mFile = multipartRequest.getFile("filename");
		try {
			InputStream in = mFile.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.doPost(req, resp);
	}
	
	

}
