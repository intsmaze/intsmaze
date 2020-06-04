package com.intsmaze.spring.createways;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CreateObjectMethodTest {
	/**
	 * spring容器在默认的情况下使用默认的构造函数创建对象
	 */
	@Test
	public void testCreateObject_Default(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloWorld helloWorld = (HelloWorld)context.getBean("helloWorld");
		helloWorld.hello();
	}
	
	/**
	 * 在spring容器 内部，调用了HelloWorldFactory中的getInstance方法
	 * 而该方法的内容就是创建对象的过程，是由程序员来完成
	 */
	@Test
	public void testCreateObject_StaticFactory(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloWorld helloWorld = (HelloWorld)context.getBean("helloWorld2");
		helloWorld.hello();
	}
	
	/**
	 * 实例工厂
	 *   1、spring容器创建一个实例工厂bean
	 *   2、该bean调用了工厂方法getInstance产生对象
	 */
	@Test
	public void testCreateObject_InstanceFactory(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloWorld helloWorld = (HelloWorld)context.getBean("helloWorld3");
		helloWorld.hello();
	}
}
