//package com.intsmaze.spring.aop.before;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import cn.intsmaze.aop.CustomerDao;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
//public class SpringTest3 {
//
//	@Autowired
//	// @Qualifier("customerDao")// 注入是真实的对象,必须注入代理对象.
//	@Qualifier("customerDaoProxy")
//	private CustomerDao customerDao;
//
//	@Test
//	public void demo1(){
//		customerDao.add();
//		customerDao.update();
//		customerDao.delete();
//		customerDao.find();
//	}
//}
