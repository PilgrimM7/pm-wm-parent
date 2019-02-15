package com.pilgrimm.wm.study.jadk.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class ServletForUpload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write("error, please use post method to upload file");
		pw.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		
		try {
			String param_1 = request.getParameter("param_1");
			System.out.println("param_1 -> " + param_1);
			
			Iterator<Part> iterator = request.getParts().iterator();
			while (iterator.hasNext()) { // 遍历客户端上传的所有文件
				Part part = (Part) iterator.next();
				if (part.getName().startsWith("file")) {
					String fileName = getFileName(part);
					System.out.println(fileName);
					File file = new File("F:\\upload\\" + fileName);
					InputStream inputStream = part.getInputStream();
					FileOutputStream fos = new FileOutputStream(file);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = inputStream.read(buffer)) != -1) {
						fos.write(buffer, 0, len);
					}
					inputStream.close();
					fos.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter pw = response.getWriter();
		pw.write("ok");
		pw.close();
	}
	
	private String getFileName(Part part) {
        String head = part.getHeader("Content-Disposition");
        String fileName = head.substring(head.indexOf("filename=\"") + 10, head.lastIndexOf("\""));
        return fileName;
    }

}
