package com.intsmaze.rpc.tcp.two.service;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
public class Provider {

	public static void main(String[] args) throws Exception {

		ServerSocket server=new ServerSocket(1234);
		while(true)
		{
			Socket socket=server.accept();
			ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
			
			String classname=input.readUTF();
			String methodName=input.readUTF();		
			Class<?>[] parameterTypes=(Class<?>[]) input.readObject();
			Object[] arguments=(Object[]) input.readObject();		
			
			Class serviceclass=Class.forName(classname);
			Object object = serviceclass.newInstance();
			Method method=serviceclass.getMethod(methodName, parameterTypes);
			
			Object result=method.invoke(object, arguments);
			
			ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(result);
			socket.close();
		}
	}
}
