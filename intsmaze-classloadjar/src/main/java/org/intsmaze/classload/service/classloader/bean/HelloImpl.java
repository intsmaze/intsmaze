package org.intsmaze.classload.service.classloader.bean;


public class HelloImpl implements HelloIface{
	
	public static int timeOut=100;
	
	public String hello() {
		return "hello,JAVA世界";
	}

	public String sayHi(String name) {
		return "Hi,JAVA World " + name;
	}
	
}
