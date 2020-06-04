package com.intsmaze.spring.before.deal;

import org.springframework.stereotype.Component;



@Component("hello")
public class HelloImpl implements Hello
{
	// 定义一个简单方法，模拟应用中的业务逻辑方法
	public void foo()
	{
		System.out.println("执行Hello组件的foo()方法");
	}
	
	// 定义一个addUser()方法，模拟应用中的添加用户的方法
	public int addUser(String name , String pass)
	{
		System.out.println("执行Hello组件的addUser添加用户：" + name);
		this.foo();
		return 20;
	}
}
