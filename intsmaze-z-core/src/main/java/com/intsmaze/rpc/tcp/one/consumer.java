package com.intsmaze.rpc.tcp.one;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class consumer {

	
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] arg) throws Exception
	{
		String interfacename=SayHelloService.class.getName();
		
		Method method=SayHelloService.class.getMethod("sayHello", String.class);
		
		Object[] arguments={"hello"};
		
		Socket socket=new Socket("127.0.0.1",1234);
		
		ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
	
		output.writeUTF(interfacename);
		output.writeUTF(method.getName());
		output.writeObject(method.getParameterTypes());
		output.writeObject(arguments);
		
		ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
		Object result=input.readObject();
		System.out.println(result);
		socket.close();
	}
}
