package com.intsmaze.spring.scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {
	
	/**
	 * 在默认情况下，spring容器产生的对象是单例的
	 */
	@Test
	public void testScope_Default(){
		ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloScope helloWorld = (HelloScope)context.getBean("helloScope");
		System.out.println(helloWorld);
		HelloScope helloWorld2 = (HelloScope)context.getBean("helloScope");
		System.out.println(helloWorld2);
	}
	
	
	/**
	 * 如果scope为"prototype"，则为多实例,
	 * lazy-init属性将失去作用
	 * getBean的时候才会调用对象的构造方法而不是在加载创建ApplicationContext时创建bean对象。
	 * 将来将springMVCd的action放到容器中时，action必须是多例的。
	 */
	@Test
	public void testScope_Prototype(){
		ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloScope helloWorld = (HelloScope)context.getBean("helloScope");
		HelloScope helloWorld2 = (HelloScope)context.getBean("helloScope");
	}
}
