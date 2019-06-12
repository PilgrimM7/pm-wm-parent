package com.pilgrimm.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) {
		
		try {
			// 1.创建 socket 指定服务器地址和端
			Socket client = new Socket("127.0.0.1", 11167);
			// 2.客户端向服务器发送登录信息
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
            
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
            		+ "<filename>对账.txt</filename>"
            		+ "</bzjpkg>");
            bw.write(sb.toString());
            bw.flush();
            client.shutdownOutput();

            // 3. 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("服务器发来消息说：" + info);
            }
            client.shutdownInput();

            // 4.关闭其他资源
            bw.close();
            br.close();
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}