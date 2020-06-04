package com.intsmaze.socket;
import java.net.ServerSocket;
public class ClientExceptionTwoServer {
	  public static void main(String args[])throws Exception {
	    ServerSocket serverSocket = new ServerSocket(8000,2);  //连接请求队列的长度为2
	    Thread.sleep(360000);   //睡眠6分钟
	  }
	}