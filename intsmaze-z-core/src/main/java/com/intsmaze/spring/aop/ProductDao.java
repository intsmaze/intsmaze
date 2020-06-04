package com.intsmaze.spring.aop;

public class ProductDao {
	public int add(){
		System.out.println("添加商品...");
//		int d = 10/0;
		return 100;
	}
	public void update(){
		System.out.println("修改商品...");
	}
	public void delete(){
		System.out.println("删除商品...");
	}
	public void find(){
		System.out.println("查询商品...");
	}
}
