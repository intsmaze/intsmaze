package com.intsmaze.socket;

import java.io.IOException;
import java.net.Socket;
//soceket的构造方法，除了不带参数的外，其他构造方法都会试图建立与服务器的连接，如果建立连接，就返回socket对象，否则抛出异常。
public class SocketUseOne {
	public static void main(String args[]) {
		String host = "localhost";
		if (args.length > 0)
		{
			host = args[0];
			System.out.println(host);//what can it do?
		}
		new SocketUseOne().scan(host);
	}

	public void scan(String host) {
		Socket socket = null;
		for (int port = 1; port < 1024; port++) {
			try {
				//这个应该是判断本机上是否有服务器监听本机的端口，如果本机上没有服务器，那么不会连接上，因为参数Host是连向本机。
				socket = new Socket(host, port);
				System.out.println("There is a server on port " + port);
			} catch (IOException e) {
				System.out.println("Can't connect to port " + port);
			} finally {
				try {
					if (socket != null)
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

