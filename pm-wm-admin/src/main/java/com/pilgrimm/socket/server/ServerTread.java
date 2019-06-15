package com.pilgrimm.socket.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerTread implements Runnable {
	
	private static final String ENCODE = "UTF-8";

	private Socket socket = null;

	public ServerTread(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		try {
			// 获取一个输入流，用来读取客户端所发送的登录信息
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODE));

			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("我是服务端，客户端说" + info);
			}
			socket.shutdownInput();// 关闭输入流

			// 获取输出流
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), ENCODE));
			
			StringBuffer sb = new StringBuffer();
			bw.write(sb.toString());
			bw.flush();
			socket.shutdownOutput();

			// 关闭资源
			br.close();
			bw.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
