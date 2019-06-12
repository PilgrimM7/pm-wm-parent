package com.pilgrimm.socket.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
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
				new Thread(new ServerTread(client)).start();
			}
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
	public class ServerTread implements Runnable {

		private Socket client = null;

		public ServerTread(Socket client) {
			this.client = client;
		}

		public void run() {

			try {
				// 获取一个输入流，用来读取客户端所发送的登录信息
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));

				String info = null;
				while ((info = br.readLine()) != null) {
					System.out.println("我是服务器，客户端说" + info);
				}
				client.shutdownInput();// 关闭输入流

				// 获取输出流
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
				
				StringBuffer sb = new StringBuffer();
				bw.write(sb.toString());
				bw.flush();
				client.shutdownOutput();

				// 关闭资源
				br.close();
				bw.close();
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
