package com.intsmaze.spring.aop;

/**
 * 目标对象
 */
public class OrderDao {
	public void add() {
		System.out.println("添加订单");
	}

	public void update() {
		System.out.println("修改订单");
	}

	public void delete() {
		System.out.println("删除订单");
	}

	public void find() {
		System.out.println("查询订单");
	}
}
