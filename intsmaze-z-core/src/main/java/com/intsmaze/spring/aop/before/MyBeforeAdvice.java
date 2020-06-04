package com.intsmaze.spring.aop.before;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 前置增强
 */
public class MyBeforeAdvice implements MethodBeforeAdvice{

	/**
	 * method:执行的方法
	 * args:参数
	 * target:目标对象
	 */
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("前置增强...");
	}
}
