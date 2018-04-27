package com.pilgrimm.socket;

import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
	public static void main(String[] args) {
		
		try {
			// 服务端在8888端口监听客户端请求的TCP连接
			ServerSocket server = new ServerSocket(8888);
			System.out.println("****服务器开始启动，等待客户端上线****");
			Socket client = null;
			boolean f = true;
			while (f) {
				// 等待客户端的连接，如果没有获取连接
				client = server.accept();
				// 为每个客户端连接开启一个线程
				new Thread(new Server2Thread(client)).start();
			}
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
