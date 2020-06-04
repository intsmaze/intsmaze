package com.intsmaze.rpc.tcp.three.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import net.sf.json.JSONObject;
import cn.intsmaze.tcp.three.ClassWays;
import cn.intsmaze.tcp.three.ServiceRoute;

public class Provider {

	//服务启动的时候，组装相关信息，然后写入第三方存储机制，供服务的调用者去获取
	public void reallyUse() {
		
		ClassWays classWays = new ClassWays();
		Class[] argumentsType = { String.class };
		classWays.setArgumentsType(argumentsType);
		classWays.setClassname("cn.intsmaze.tcp.three.service.SayHelloServiceImpl");
		classWays.setMethod("sayHello");
		classWays.setIp("127.0.0.1");
		classWays.setPort(1234);
		
		JSONObject js=JSONObject.fromObject(classWays);
		
		//模拟第三方存储介质,实际中应该是redis,mysql，zookeeper等。
		ServiceRoute.NAME.put("SayHello", js.toString());
	}

	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(1234);
		//实际中，这个地方应该调用如下方法，但是因为简单的模拟服务的注册，将注册的信息硬编码在ServiceRoute类中，这个类的构造方法里面会自动注册服务的相关信息。
		//server.reallyUse();
		while (true) {
			Socket socket = server.accept();

			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			String classname = input.readUTF();
			String methodName = input.readUTF();
			Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
			Object[] arguments = (Object[]) input.readObject();

			Class serviceclass = Class.forName(classname);

			Object object = serviceclass.newInstance();

			Method method = serviceclass.getMethod(methodName, parameterTypes);

			Object result = method.invoke(object, arguments);

			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(result);
			socket.close();
		}

	}

}
