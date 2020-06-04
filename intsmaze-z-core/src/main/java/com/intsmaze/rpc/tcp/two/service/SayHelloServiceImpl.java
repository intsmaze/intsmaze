package com.intsmaze.rpc.tcp.two.service;
public class SayHelloServiceImpl  {
	public String sayHello(String helloArg) {
		if(helloArg.equals("intsmaze"))
		{
			return "intsmaze";
		}
		else
		{
			return "bye bye";
		}
	}
}
