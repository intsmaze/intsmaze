package com.intsmaze.spring.before.deal;

import org.springframework.stereotype.Component;



@Component("world")
public class WorldImpl implements World
{
	// 定义一个简单方法，模拟应用中的业务逻辑方法
	public void bar()
	{
		System.out.println("执行World组件的bar()方法");
	}
}
