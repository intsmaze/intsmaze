package com.intsmaze.spring.di;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DIXMLSetterTest {
	

	/**
	 * 	先调用bean的的setter方法进行装配
	 *  再调用bean的init方法
	 */
	@Test
	public void testDI_XML_Setter_Default(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonDi person = (PersonDi)context.getBean("personDi");
		System.out.println(person.toString());
	}
	
	/**
	 * 1、启动spring容器
	 * 2、创建person对象
	 * 3、创建多个student对象   student的scope为"prototype"，因为Person中的很多属性都依赖于student,而这些属性的赋值发生在spring容器启动的时候
	 * 4、调用setter方法赋值
	 * 5、 context.getBean
	 * 6、对象调用方法
	 */
	@Test
	public void testDI_XML_Setter_Person_Scope_Default_Student_Scope_Prototype(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonDi person = (PersonDi)context.getBean("personDi");
		System.out.println(person.toString());
	}
}
