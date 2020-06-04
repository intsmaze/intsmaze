package com.intsmaze.spring.initdestroy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitDestroyTest {
	
	/**
	 * 1、启动spring容器
	 * 2、创建helloInitdestroy对象
	 * 3、执行init方法  该方法是由spring容器内部调用
	 * 4、context.getBean把对象提取出来
	 * 5、对象调用方法
	 * 6、当执行close方法的时候，执行该对象的destroy方法  是由spring容器内部调用
	 */
	@Test
	public void testInitDestroy_Scope_Default(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloInitdestroy helloInitdestroy = (HelloInitdestroy)context.getBean("helloInitdestroy");
		helloInitdestroy.hello();
		
		ClassPathXmlApplicationContext applicationContext = (ClassPathXmlApplicationContext)context;
		applicationContext.close();
	}
	
	
	/**
	 * 如果设置 scope="prototype"，则spring容器不负责销毁方法的调用。销毁是由JVM控制（垃圾回收）。
	 * 1、启动spring容器
	 * 2、context.getBean把对象提取出来
	 * 3、创建helloWorld对象
	 * 4、执行init方法  该方法是由spring容器内部调用
	 * 5、对象调用方法
	 * 说明：不负责销毁
	 */
	@Test
	public void testInitDestroy_Scope_Prototype(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloInitdestroy helloInitdestroy = (HelloInitdestroy)context.getBean("helloInitdestroy");
		helloInitdestroy.hello();
		ClassPathXmlApplicationContext applicationContext = (ClassPathXmlApplicationContext)context;
		applicationContext.close();
	}
}
