package com.intsmaze.spring.initdestroy;

public class HelloInitdestroy {
	
	
	public void init(){
		System.out.println("init");
	}
	
	public void destroy(){
		System.out.println("detroy");
	}
	
	public void hello(){
		System.out.println("hello HelloInitdestroy");
	}
}
