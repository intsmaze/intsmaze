package com.intsmaze.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
public class SocketUseTwo {
	/**
	 * Socket有参数构造方法必须设定一个服务器地址，它包括IP地址或主机名，外加端口。
	 * 注意一个Socket对象中不仅包括目的IP地址和端口，也包含本机的IP地址和端口。默认下系统用的就是本机IP地址，
	 * 端口系统随机分配的。
	 * 但是当一个主机同时属于两个网络时，它同时拥有两个IP地址，这时就要显示的设置本机地址和端口了,来确定他与那个网络的机器连接了。
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		InetAddress id=InetAddress.getByName("www.hao123.com");//InetAddress是IP地址
		//也可以InetAddress id=InetAddress.getByName("132.12.12.12");直接传入ip地址
		Socket s1=new Socket(id,80);
		Socket s2=new Socket("www.hao123.com",80);//主机名
		
		InetAddress id1=InetAddress.getByName("localhost");
		Socket s3=new Socket("www.hao123.com",80,id1,80);
	}

}
