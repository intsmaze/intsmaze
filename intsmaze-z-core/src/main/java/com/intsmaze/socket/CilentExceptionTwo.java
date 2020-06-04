package com.intsmaze.socket;

import java.net.Socket;
public class CilentExceptionTwo {
	 /**服务器进程拒绝客户进程的连接请求。
	 *  ServerSocket serverSocket = new ServerSocket(port,backlog);
	 *  参数backlog设定服务器的连接请求队列长度，如果队列中的连接请求已近满了，服务器会拒绝其余的连接请求。 
	 * */
	  public static void main(String args[])throws Exception {
		//先运行ClientExceptionTwoServer程序，再运行
	    Socket s1 = new Socket("localhost",8000);
	    System.out.println("第一次连接成功");
	    Socket s2 = new Socket("localhost",8000);
	    System.out.println("第二次连接成功");
	    Socket s3 = new Socket("localhost",8000);//因为服务器已经有两个请求了，且这两个请求都没有断开连接，所以第三个请求无法被接受。
	    System.out.println("第三次连接成功");
	  }
	}

