package com.pilgrimm.socket.pingpong;

import java.util.concurrent.*;
import java.net.*;
import java.io.*;

public class ServerTask implements Callable<Void> {

	Socket s;

	ServerTask(Socket s) {
		this.s = s;
	}

	public Void call() {
		BufferedWriter writer = null;
		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			int i = 0;
			StringBuilder sb = new StringBuilder();
			while ((i = reader.read()) != -1) {
				char c = (char) i;
				if (c == '\n')
					break;
				sb.append(c);
			}
			System.out.println("The client sends: " + sb);

			writer.write("pong");
			writer.write('\n');
			writer.flush();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {

			try {
				writer.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			try {
				s.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		}
		return null;
	}
}
