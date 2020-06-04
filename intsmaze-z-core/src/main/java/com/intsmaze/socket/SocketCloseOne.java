package com.intsmaze.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketCloseOne {
	/**当客户端与服务器的通信结束后，应该及时关闭socket，以释放socket占用的包括端口在内的各种资源。
	 * close（）方法就是干这的。为了保证close方法被执行，要放到finally中。
	 * 
	 *问题：假定进程A输出数据，进程B读入数据，进程A如何告诉进程B所有数据已经输入完毕。
	 *1，当进程A和B交换的是字节流，并且都是一行一行地读/写数据时，可以事先约定一个特殊的标识作为结束标识，如以字符串"bye"
	 *作为结束标识，当进程A向进程B发送一行字符串"Bye",进程B读到这行数据后就停止读数据。
	 *
	 *2,进程A先发送一个消息，告诉进程B所发的正文的长度，然后再发送正文。进程B先获知进程A将发送的正文的长度，
	 *接下来只要读取该长度的字符或者字节就结束读取。
	 *
	 *3，进程A发完所有的数据后，关闭Socket。当进程B读取了进程A所发送的所有数据后，再次执行输入流的read方法时，该方法返回-1.
	 *如果执行bufferreader方法，返回null。
	 *
	 *4，当调用socket的close方法关闭socket时，它的输入流和输出流也都被关闭，有时候仅仅希望关闭输入流或输出流之一。
	 *这时可以采用socket提供的半关闭方法。
	 *进程A在读入数据时，进程B的输出流已经关闭了，进程B读入所有数据后，就会读到输入流的末尾。
	 *注意先后调用socket的 shutdownInput(),shutdownOutput()仅仅关闭了一端的输入流和输出流，**但是之间的通信还存在**
	 *通信结束后仍然要调用close方法，才会释放socket占用的资源。
	 * 	Socket s1=new Socket("localhost",8000);
		s1.shutdownInput();
		s1.shutdownOutput();
		System.out.println(s1.isClosed());
		System.out.println(s1.isConnected()&&!s1.isClosed());
		//注意isConnected()只是表示是否曾经连接上服务器，不表示当前的连接状态，表示当前的连接状态用上面方法。
		s1.close();
		System.out.println(s1.isConnected()&&!s1.isClosed());
		System.out.println(s1.isClosed());
	 */
	public static void main(String[] args)  {

		for(int i=1;i<10000;i++)
		{
			try {
				ServerSocket ss=new ServerSocket(i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		
	}

}
