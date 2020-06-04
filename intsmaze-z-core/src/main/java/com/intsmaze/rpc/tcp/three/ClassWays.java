package com.intsmaze.rpc.tcp.three;

import net.sf.json.JSONObject;

public class ClassWays {
	
	String classname;
	
	String method;
	
	Class[] argumentsType;
	
	String ip;
	
	int port;
	
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Class[] getArgumentsType() {
		return argumentsType;
	}
	public void setArgumentsType(Class[] argumentsType) {
		this.argumentsType = argumentsType;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public static void main(String[] arg)
	{
		ClassWays classWays=new ClassWays();
		Object[] arguments={"hello"};
		Class[] argumentsType={String.class};
		classWays.setArgumentsType(argumentsType);
		classWays.setClassname("cn.intsmaze.tcp.three.service.SayHelloServiceImpl");
		classWays.setMethod("sayHello");
		JSONObject js=JSONObject.fromObject(classWays);
	    System.out.println(js.toString());
	    
//	    JSONObject obj = new JSONObject().fromObject(js);
//	    ClassWays demo = (ClassWays)JSONObject.toBean(obj,ClassWays.class);
//	    System.out.println(demo.getClassname());
	}
}
