package com.intsmaze.spring;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring容器做的事情：解析spring的配置文件，利用Java的反射机制创建对象
 */
public class HelloWorldTest {
	
	@Test
	public void testHelloWorld(){
		//启动spring容器
		AbstractApplicationContext   context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//从spring容器中把对象提取出来
		HelloWorld helloWorld = (HelloWorld)context.getBean("helloWorldt");
		helloWorld.setName("234");
		System.out.println(helloWorld.getName());
		
		HelloWorld helloWorld1 = (HelloWorld)context.getBean("helloWorldt");
		System.out.println(helloWorld1.getName());
		
		helloWorld.hello();
		context.close();
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//从spring容器中把对象提取出来
		helloWorld = (HelloWorld)context.getBean("helloWorldt");
		System.out.println(helloWorld.getName());
	}
}
