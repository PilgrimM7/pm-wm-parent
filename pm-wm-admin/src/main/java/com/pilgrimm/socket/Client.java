package com.pilgrimm.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) {
		
		try {
			// 1.创建 socket 指定服务器地址和端
			Socket client = new Socket("127.0.0.1", 8889);
			// 2.客户端向服务器发送登录信息
            OutputStream os = client.getOutputStream();// 字节输出流
            PrintWriter pw = new PrintWriter(os);
            
            // 1、保证金到账
//			StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><bzjpkg>"
//					+ "<TransCode>3001</TransCode>"
//					+ "<TransDate>2</TransDate>"
//					+ "<TransTime>3</TransTime>"
//					+ "<SeqNo>4</SeqNo>"
//					+ "<AccNo>5</AccNo>"
//					+ "<AccName>6</AccName>"
//					+ "<AccBank>16</AccBank>"
//	 				+ "<InAmount>13.00</InAmount>"
//	 				+ "<InDate>20180114</InDate>"
//    				+ "<InTime>120000</InTime>"
//    				+ "<HstSeqNum>0000000001</HstSeqNum>"
//	 				+ "<Abstract>邢台天丰工程技术有限公司</Abstract>"
//	 				+ "<Remark>Z1305001600061011-001</Remark>"
//	 				+ "</bzjpkg>");
//            pw.write(sb.toString());
            
            // 10、保证金对账
            StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><bzjpkg>"
            		+ "<TransCode>3012</TransCode>"
            		+ "<TransDate>2</TransDate>"
            		+ "<TransTime>3</TransTime>"
            		+ "<SeqNo></SeqNo>"
            		+ "<filename>duizhang.txt</filename>"
            		+ "</bzjpkg>");
            pw.write(sb.toString());
            
            pw.flush();
            client.shutdownOutput();// 关闭输出流

            // 3. 获取输入流
            InputStream is = client.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("服务器发来消息说：" + info);
            }

            // 3.关闭其他资源
            pw.close();
            os.close();
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}