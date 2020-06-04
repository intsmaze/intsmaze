package com.intsmaze.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
//@ContextConfiguration("classpath:applicationContext3.xml")
public class SpringTest5 {

	@Autowired
	@Qualifier("orderDao")
	private OrderDao orderDao;
	@Autowired
	@Qualifier("customerDao")
	private CustomerDao customerDao;	
	
	@Test
	public void demo1(){
		orderDao.add();
		orderDao.update();
		orderDao.delete();
		
		customerDao.add();
		customerDao.update();
	}
}
