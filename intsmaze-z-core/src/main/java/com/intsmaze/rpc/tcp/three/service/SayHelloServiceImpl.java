package com.intsmaze.rpc.tcp.three.service;


public class SayHelloServiceImpl  {

	public String sayHello(String helloArg) {
		if(helloArg.equals("hello"))
		{
			return "hello";
		}
		else
		{
			return "bye bye";
		}
	}

}
