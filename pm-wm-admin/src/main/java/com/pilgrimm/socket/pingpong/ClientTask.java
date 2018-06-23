package com.pilgrimm.socket.pingpong;

import java.util.concurrent.*;
import java.net.*;
import java.io.*;

public class ClientTask implements Callable<Void> {

	Socket s;

	ClientTask(Socket s) {
		this.s = s;
	}

	public Void call() {

		BufferedWriter writer = null;
		BufferedReader reader = null;

		try {

			writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

			writer.write("ping");
			writer.write('\n');
			writer.flush();

			int i = 0;
			StringBuilder sb = new StringBuilder();
			while ((i = reader.read()) != -1) {
				System.out.println("I'm reading.");
				char c = (char)i;
			    if(c == '\n')
			        break;
			    sb.append(c);
			}
			System.out.println("The server sends: " + sb);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		finally {

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
