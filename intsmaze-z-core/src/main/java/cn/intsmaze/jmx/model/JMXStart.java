package cn.intsmaze.jmx.model;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * @author:YangLiu
 * @date:2018年7月3日 下午2:50:17 
 * @describe: 
 */
public class JMXStart {

	public static void main(String[] args) throws InterruptedException {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(  
	                "jmx.xml");  
	  
		 HusbandLocal hu=(HusbandLocal) context.getBean("HusbandLocal");
	        while (true) {  
	        	System.out.println("------------"+hu.getName());
			System.out.println("------------" + HusbandLocal.getNumber());
	        	Thread.sleep(10000);
	        }  
	}

}
