package com.intsmaze.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
public class SocketUseWaitOne {
	/**设置等待建立连接的超时时间
	 * 当客户端Socket构造方法请求与服务器连接时，可能要等待一段时间。
	 * 默认下，socket构造方法会一直等待下去，直到连接成功，或者出现异常。
	 * 这时候可以设置等待连接的时间，这需要用到无参构造函数。
	 * */
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket();
			
			SocketAddress remoteAddr=new InetSocketAddress("localhost",8000);
			//这个要留意SocketAddress方式。
			
			socket.connect(remoteAddr, 1);//等待建立连接的时间是一毫秒
		 /**如果在1毫秒内连接成功，则connect()方法顺利返回，如果在1毫秒内出现某种异常，则抛出该异常，如果超出
		 * 1毫秒，既没有连接成功，也没用出现其他异常，那么会抛出socketTimeExpception
		 * 当时间设置为0时表示永远不会超时，就是相当于没有设置等待时间。
		 * */
		}catch (SocketTimeoutException e) {
			System.out.println("..... " );
		}
		catch (IOException e) {
			System.out.println("Can't connect to port " );
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
