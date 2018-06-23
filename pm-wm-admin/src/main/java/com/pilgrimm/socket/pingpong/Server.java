package com.pilgrimm.socket.pingpong;

import java.util.concurrent.*;
import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) {

		ExecutorService esp = Executors.newFixedThreadPool(50);
		try (ServerSocket ss = new ServerSocket(1027)) {
			while (true) {
				try {

					Socket s = ss.accept();
					Callable<Void> task = new ServerTask(s);
					esp.submit(task);

				} catch (BindException be) {
				} catch (ConnectException ce) {
				} catch (NoRouteToHostException nrthe) {
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
