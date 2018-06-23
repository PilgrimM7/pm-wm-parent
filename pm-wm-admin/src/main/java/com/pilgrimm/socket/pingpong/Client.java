package com.pilgrimm.socket.pingpong;

import java.util.concurrent.*;
import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args) {

		ExecutorService es = Executors.newSingleThreadExecutor();

		try {

			Socket s = new Socket(InetAddress.getLocalHost(), 1027);
			try {
				s.setSoTimeout(20000);
			} catch (SocketException se) {
				se.printStackTrace();
			}

			Callable<Void> task = new ClientTask(s);
			es.submit(task);

		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
}
