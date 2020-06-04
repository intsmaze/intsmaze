package com.intsmaze.socket;

import java.net.*;
import java.io.*;

/**
 * 通过socket的HTTP GET，不通过"URL"类下载
 * @author luogang
 */
public class SocketGet {
	public static void main(String[] args) throws IOException {
		Socket s = null;

		try {
			String host = "www.lietu.com"; // 主机名
			String file = "/index.jsp"; // 网页路径
			int port = 80; // 端口号

			s = new Socket(host, port);

			OutputStream out = s.getOutputStream();
			PrintWriter outw = new PrintWriter(out, false);
			outw.print("GET " + file + " HTTP/1.0\r\n");
			outw.print("Accept: text/plain, text/html, text/*\r\n");
			outw.print("\r\n");
			outw.flush();

			InputStream in = s.getInputStream();
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			// br.close(); // Q. Do I need this?
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}

		if (s != null) {
			try {
				s.close();
			} catch (IOException ioEx) {
			}
		}
	}
}
