package com.pilgrimm.socket.server;

import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		try {
			// 服务端在8888端口监听客户端请求的TCP连接
			ServerSocket server = new ServerSocket(8888);
			System.out.println("****服务端启动，等待客户端上线****");
			Socket socket = null;
			boolean f = true;
			while (f) {
				// 等待客户端的连接，如果没有获取连接
				socket = server.accept();
				// 为每个客户端连接开启一个线程
				new Thread(new ServerTread(socket)).start();
			}
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
	
}
