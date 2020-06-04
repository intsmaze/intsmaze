package com.intsmaze.rpc.tcp.two.client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class consumer {
	
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] arg) throws Exception
	{
		//我们要想调用远程提供的服务，必须告诉远程我们要调用你的哪一个类，这里我们可以在本地创建一个interface来获取类的名称，但是这样我们必须
		//保证该interface和远程的interface的所在包名一致。这种方式不好。所以我们还是通过硬编码的方式吧。
//		String interfacename=SayHelloService.class.getName();
		
		String classname="cn.intsmaze.tcp.two.service.SayHelloServiceImpl";
		String method="sayHello";
		Class[] argumentsType={String.class};
		Object[] arguments={"intsmaze"};
		
		Socket socket=new Socket("127.0.0.1",1234);
		
		ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
	
		output.writeUTF(classname);
		output.writeUTF(method);
		output.writeObject(argumentsType);
		output.writeObject(arguments);
		
		ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
		Object result=input.readObject();
		System.out.println(result);
		socket.close();
	}
}
