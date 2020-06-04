package com.intsmaze.spring.aop.aspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.intsmaze.aop.ProductDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext5.xml")
public class SpringTest2 {

	@Autowired
	@Qualifier("productDao")
	private ProductDao productDao;
	
	@Test
	public void demo1(){
		productDao.add();
		productDao.find();
		productDao.update();
		productDao.delete();
	}
}
