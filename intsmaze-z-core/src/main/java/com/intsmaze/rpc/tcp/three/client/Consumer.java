//package com.intsmaze.rpc.tcp.three.client;
//
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//import net.sf.json.JSONObject;
//import cn.intsmaze.tcp.three.ClassWays;
//import cn.intsmaze.tcp.three.ServiceRoute;
//
//public class Consumer {
//
//
//	public Object reallyUse(String provideName,Object[] arguments) throws Exception
//	{
//		//模拟从第三方存储介质拿去数据
//		ServiceRoute serviceRoute=new ServiceRoute();
//		String js=serviceRoute.NAME.get(provideName);
//		JSONObject obj = new JSONObject().fromObject(js);
//	    ClassWays classWays = (ClassWays)JSONObject.toBean(obj,ClassWays.class);
//
//		String classname=classWays.getClassname();
//		String method=classWays.getMethod();
//		Class[] argumentsType=classWays.getArgumentsType();
//		Socket socket=new Socket(classWays.getIp(),classWays.getPort());
//
//		ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
//
//		output.writeUTF(classname);
//		output.writeUTF(method);
//		output.writeObject(argumentsType);
//		output.writeObject(arguments);
//
//		ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
//		Object result=input.readObject();
//		socket.close();
//		return result;
//	}
//
//	@SuppressWarnings({ "unused", "rawtypes" })
//	public static void main(String[] arg) throws Exception
//	{
//		Consumer consumer=new Consumer();
//		Object[] arguments={"intsmaze"};
//		Object result=consumer.reallyUse("SayHello",arguments);
//		System.out.println(result);
//	}
//}
