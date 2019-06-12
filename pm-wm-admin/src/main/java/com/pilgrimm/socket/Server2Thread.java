package com.pilgrimm.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Server2Thread implements Runnable {

	private Socket client = null;

	public Server2Thread(Socket client) {
		this.client = client;
	}

	@Override
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
			
			// 2、入账明细
			StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><bzjpkg>"
    				+ "<TransCode>1</TransCode>"
    				+ "<TransDate>2</TransDate>"
    				+ "<TransTime>3</TransTime>"
    				+ "<SeqNo>4</SeqNo>"
    				+ "<Result>5</Result>"
    				+ "<AddWord>6</AddWord>"
    				+ "<itemcnt>7</itemcnt>"
    				
    				+ "<item>"
    					+ "<InDate>20180114</InDate>"
    					+ "<InTime>120000</InTime>"
    					+ "<InAmount>13.00</InAmount>"
    					+ "<AccName>邢台天丰工程技术有限公司</AccName>"
    					+ "<AccAcct>15</AccAcct>"
    					+ "<AccBank>16</AccBank>"
    					+ "<OpeningBank>17</OpeningBank>"
    					+ "<InMemo>18</InMemo>"
    					+ "<HstSeqNum>0000000001</HstSeqNum>"
    				+ "</item>"
    					
    				+ "<item>"
    					+ "<InDate>20180114</InDate>"
    					+ "<InTime>220000</InTime>"
    					+ "<InAmount>23.00</InAmount>"
    					+ "<AccName>24</AccName>"
    					+ "<AccAcct>25</AccAcct>"
    					+ "<AccBank>26</AccBank>"
    					+ "<OpeningBank>27</OpeningBank>"
    					+ "<InMemo>28</InMemo>"
    					+ "<HstSeqNum>0000000002</HstSeqNum>"
    				+ "</item>"
    				
    				+ "</bzjpkg>");
			
			// 3、单笔入账
//			StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><bzjpkg>"
//					+ "<TransCode>1</TransCode>"
//					+ "<TransDate>2</TransDate>"
//					+ "<TransTime>3</TransTime>"
//					+ "<SeqNo>4</SeqNo>"
//					+ "<Result>1</Result>"
//					+ "<AddWord>6</AddWord>"
//					
//    				+ "<InDate>20180114</InDate>"
//    				+ "<InTime>120000</InTime>"
//    				+ "<InAmount>13.00</InAmount>"
//    				+ "<AccName>邢台天丰工程技术有限公司</AccName>"
//    				+ "<AccAcct>15</AccAcct>"
//    				+ "<AccBank>16</AccBank>"
//    				+ "<OpeningBank>17</OpeningBank>"
//    				+ "<InMemo>18</InMemo>"
//    				+ "<HstSeqNum>0000000001</HstSeqNum>"
//    				+ "</bzjpkg>");
			
			// 4、保证金退款
//			StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><bzjpkg>"
//					+ "<TransCode>1</TransCode>"
//					+ "<TransDate>20180114</TransDate>"
//					+ "<TransTime>120000</TransTime>"
//					+ "<SeqNo>4</SeqNo>"
//					+ "<Result>5</Result>"
//					+ "<AddWord>6</AddWord>"
//					
//    				+ "<InAcctNo>20180114</InAcctNo>"
//    				+ "<InName>120000</InName>"
//    				+ "<HstSeqNum>0000000001</HstSeqNum>"
//    				+ "</bzjpkg>");
			
			// 5、退款明细
//			StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><bzjpkg>"
//					+ "<TransCode>1</TransCode>"
//					+ "<TransDate>20180114</TransDate>"
//					+ "<TransTime>120000</TransTime>"
//					+ "<SeqNo>4</SeqNo>"
//					+ "<Result>5</Result>"
//					+ "<AddWord>6</AddWord>"
//					
//					+ "<itemcnt>7</itemcnt>"
//					+ "<item>"
//						+ "<InDate>20180114</InDate>"
//						+ "<InTime>120000</InTime>"
//						+ "<InAmount>13.00</InAmount>"
//						+ "<AccName>邢台天丰工程技术有限公司</AccName>"
//						+ "<AccNo>0000001</AccNo>"
//						+ "<AccBank>中国农业银行</AccBank>"
//						+ "<HstSeqNum>0000000001</HstSeqNum>"
//					+ "</item>"
//						
//					+ "<item>"
//						+ "<InDate>20180114</InDate>"
//						+ "<InTime>120000</InTime>"
//						+ "<InAmount>13.00</InAmount>"
//						+ "<AccName>邢台天丰工程技术有限公司</AccName>"
//						+ "<AccNo>0000001</AccNo>"
//						+ "<AccBank>中国农业银行</AccBank>"
//						+ "<HstSeqNum>0000000002</HstSeqNum>"
//					+ "</item>"
//						
//    				+ "</bzjpkg>");
			
			// 6、退款单笔明细
//			StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><bzjpkg>"
//					+ "<TransCode>1</TransCode>"
//					+ "<TransDate>2</TransDate>"
//					+ "<TransTime>3</TransTime>"
//					+ "<SeqNo>4</SeqNo>"
//					+ "<Result>5</Result>"
//					+ "<AddWord>6</AddWord>"
//					
//    				+ "<InDate>20180114</InDate>"
//    				+ "<InTime>120000</InTime>"
//    				+ "<InAmount>13.00</InAmount>"
//    				+ "<AccName>邢台天丰工程技术有限公司</AccName>"
//    				+ "<AccNo>15</AccNo>"
//    				+ "<AccBank>16</AccBank>"
//    				+ "<HstSeqNum>0000000001</HstSeqNum>"
//    				+ "</bzjpkg>");
			
			// 7、保证金转账
//			StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><bzjpkg>"
//					+ "<TransCode>1</TransCode>"
//					+ "<TransDate>2</TransDate>"
//					+ "<TransTime>3</TransTime>"
//					+ "<SeqNo>4</SeqNo>"
//					+ "<Result>5</Result>"
//					+ "<AddWord>6</AddWord>"
//					
//    				+ "<InAmount>13.00</InAmount>"
//    				+ "<InName>邢台天丰工程技术有限公司</InName>"
//    				+ "<InAcctNo>15</InAcctNo>"
//    				+ "<AccBank>16</AccBank>"
//    				+ "<HstSeqNum>0000000001</HstSeqNum>"
//    				+ "</bzjpkg>");
			
			// 8、全部入账明细
//			StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><bzjpkg>"
//    				+ "<TransCode>1</TransCode>"
//    				+ "<TransDate>2</TransDate>"
//    				+ "<TransTime>3</TransTime>"
//    				+ "<SeqNo>4</SeqNo>"
//    				+ "<Result>5</Result>"
//    				+ "<AddWord>6</AddWord>"
//    				+ "<itemcnt>7</itemcnt>"
//    				
//    				+ "<item>"
//    					+ "<InDate>20180114</InDate>"
//    					+ "<InTime>120000</InTime>"
//    					+ "<InAmount>13.00</InAmount>"
//    					+ "<AccName>邢台天丰工程技术有限公司</AccName>"
//    					+ "<AccAcct>15</AccAcct>"
//    					+ "<AccBank>16</AccBank>"
//    					+ "<OpeningBank>17</OpeningBank>"
//    					+ "<InMemo>18</InMemo>"
//    					+ "<HstSeqNum>0000000001</HstSeqNum>"
//    				+ "</item>"
//    					
//    				+ "<item>"
//    					+ "<InDate>20180114</InDate>"
//    					+ "<InTime>220000</InTime>"
//    					+ "<InAmount>23.00</InAmount>"
//    					+ "<AccName>24</AccName>"
//    					+ "<AccAcct>25</AccAcct>"
//    					+ "<AccBank>26</AccBank>"
//    					+ "<OpeningBank>27</OpeningBank>"
//    					+ "<InMemo>28</InMemo>"
//    					+ "<HstSeqNum>0000000002</HstSeqNum>"
//    				+ "</item>"
//    				
//    				+ "</bzjpkg>");
			
			// 9、全部退费明细
//			StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><bzjpkg>"
//					+ "<TransCode>1</TransCode>"
//					+ "<TransDate>20180114</TransDate>"
//					+ "<TransTime>120000</TransTime>"
//					+ "<SeqNo>4</SeqNo>"
//					+ "<Result>5</Result>"
//					+ "<AddWord>6</AddWord>"
//					
//					+ "<itemcnt>7</itemcnt>"
//					+ "<item>"
//						+ "<InDate>20180114</InDate>"
//						+ "<InTime>120000</InTime>"
//						+ "<InAmount>13.00</InAmount>"
//						+ "<AccName>邢台天丰工程技术有限公司</AccName>"
//						+ "<AccNo>0000001</AccNo>"
//						+ "<AccBank>中国农业银行</AccBank>"
//						+ "<HstSeqNum>0000000001</HstSeqNum>"
//					+ "</item>"
//						
//					+ "<item>"
//						+ "<InDate>20180114</InDate>"
//						+ "<InTime>120000</InTime>"
//						+ "<InAmount>13.00</InAmount>"
//						+ "<AccName>邢台天丰工程技术有限公司</AccName>"
//						+ "<AccNo>0000001</AccNo>"
//						+ "<AccBank>中国农业银行</AccBank>"
//						+ "<HstSeqNum>0000000002</HstSeqNum>"
//					+ "</item>"
//						
//    				+ "</bzjpkg>");
			
            
			bw.write(sb.toString());
//			bw.write("好的，我收到消息了，你可以出去玩了");
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
