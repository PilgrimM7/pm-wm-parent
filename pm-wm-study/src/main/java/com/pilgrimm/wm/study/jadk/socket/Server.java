package com.pilgrimm.wm.study.jadk.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static final String ENCODE = "UTF-8";
	
	public static void main(String[] args) {
        try {
            // 1.创建一个服务器端的 Socket，即 ServerSocket，指定绑定的端，并监听
            ServerSocket server = new ServerSocket(8887);
            
            // 2.调用 accept 方法开始监听，等待客户端连接
            System.out.println("****服务器开始启动，等待客户端上线****");
            Socket socket = server.accept();
            
            // 3.获取输入流
            InputStream is = socket.getInputStream();
            String content = "";
            byte buff[] = new byte[1024];
            int len;
            while ((len = is.read(buff)) != -1) {
            	content += new String(buff, 0, len, ENCODE);
            }
            System.out.println("我是服务器，客户端说：" + content);
            
            // 4.获取输出流
            OutputStream os = socket.getOutputStream();
            os.write("好的，我收到消息了，你可以出去玩了".getBytes(ENCODE));
            os.flush();
            socket.shutdownOutput();

            // 5.关闭资源
            is.close();
            os.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
