package cn.intsmaze.io.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	private int port=1000;
	private ServerSocket serversocket;
	private BufferedReader bufIn=null;
	private BufferedWriter bufOut=null;	
	
	public Server() throws Exception
	{	
		serversocket=new ServerSocket();
		serversocket.bind(new InetSocketAddress("127.0.0.1",port));	
	}
	public void service()
	{
		while(true)
		{
			Socket socket=null;	
			try
			{
				socket=serversocket.accept();
				bufIn=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bufOut=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				String line=bufIn.readLine();
				System.out.println(line);
				bufOut.write("fanhui"+"\n");
				bufOut.flush();		
				
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}	
	public static void main(String[] args) throws Exception {
		new Server().service();					
	}
}
