package com.intsmaze.jetty.handle.hand;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description 将接收的数据发往kafka,同时适用客服进线报文demo
 * @Author 刘洋
 * @Date 2018/12/23 11:07
 * @Version 1.0
 **/
public class StartSurveyService {

	//http://localhost:8012/survey
	public static void main(String[] args) throws Exception
	{
		AbstractApplicationContext applicationContext=new ClassPathXmlApplicationContext(
				new String[] {"surveykafka/survey-jetty.xml"});
		while (true)
		{
			Thread.sleep(10000);
		}

	}

}
