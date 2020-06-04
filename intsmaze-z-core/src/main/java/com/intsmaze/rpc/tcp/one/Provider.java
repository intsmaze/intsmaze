package com.intsmaze.rpc.tcp.one;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Provider {

	public static void main(String[] args) throws Exception {

		ServerSocket server=new ServerSocket(1234);
		
		while(true)
		{
			Socket socket=server.accept();
			
			ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
			
			String interfacename=input.readUTF();
			String methodName=input.readUTF();		
			Class<?>[] parameterTypes=(Class<?>[]) input.readObject();
			Object[] arguments=(Object[]) input.readObject();		
			
			Class serviceinterfaceclass=Class.forName(interfacename);

			List<Class> list=ClassUtils.getAllImplClassesByInterface(serviceinterfaceclass);
			
			Object object = list.get(0).newInstance();
			
			Method method=serviceinterfaceclass.getMethod(methodName, parameterTypes);
			
			Object result=method.invoke(object, arguments);
			
			ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(result);
			socket.close();
		}
		
	}

}
