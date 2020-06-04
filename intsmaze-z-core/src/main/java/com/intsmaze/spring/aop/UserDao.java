package com.intsmaze.spring.aop;

public class UserDao {
	public void add(){
		System.out.println("添加用户");
	}
	public int update(){
		System.out.println("修改用户");
		return 1;
	}
	public void delete(){
		System.out.println("删除用户");
	}
	public void find(){
		System.out.println("查询用户");
		//int d = 1/ 0;
	}
}
