package com.intsmaze.spring.before;


import com.intsmaze.spring.before.deal.Hello;
import com.intsmaze.spring.before.deal.World;
import org.springframework.context.*;
import org.springframework.context.support.*;


public class BeanTest
{
	public static void main(String[] args)
	{
		// 创建Spring容器
		ApplicationContext ctx = new
			ClassPathXmlApplicationContext("beans.xml");
	
		Hello hello = ctx.getBean("hello" , Hello.class);

		hello.addUser("孙悟空" , "7788");
		World world = ctx.getBean("world" , World.class);
		world.bar();

		
	}
}