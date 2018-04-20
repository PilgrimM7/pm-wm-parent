package com.pilgrimm.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Component;

@Component("serverServlet")
public class ServerServlet extends HttpServlet {

	private static final long serialVersionUID = -2740392393164941362L;

	@Override
	public void init() throws ServletException {

		FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
				start(); // 使用另一个线程来执行该方法，会避免占用Tomcat的启动时间
				return "Collection Completed";
			}
		});

		new Thread(task).start();
	}

	private void start() {
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

	public class ServerTread implements Runnable {

		private Socket client = null;

		public ServerTread(Socket client) {
			this.client = client;
		}

		public void run() {

			try {
				// 获取一个输入流，用来读取客户端所发送的登录信息
				InputStream is = client.getInputStream();// 字节输入流
				InputStreamReader isr = new InputStreamReader(is, "GBK");// 将字节流转为
				BufferedReader br = new BufferedReader(isr);// 为输入流添加缓冲

				String info = null;
				while ((info = br.readLine()) != null) {
					System.out.println("我是服务器，客户端说" + info);
				}
				client.shutdownInput();// 关闭输入流

				// 获取输出流
				OutputStream os = client.getOutputStream();
				PrintWriter pw = new PrintWriter(os);// 包装打印流

				StringBuffer sb = new StringBuffer();
				pw.write(sb.toString());
				pw.flush();
				client.shutdownOutput();

				// 关闭资源
				pw.close();
				br.close();
				isr.close();
				is.close();
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
