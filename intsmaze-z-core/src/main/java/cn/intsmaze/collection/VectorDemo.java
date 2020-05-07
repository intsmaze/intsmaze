package cn.intsmaze.collection;

import java.util.Vector;

public class VectorDemo {

	public static void main(String[] args) {
		Vector list = new Vector();
		list.add("nihao");// 简单暴力，涉及到多线程操作的方法都会加上synchronized关键字
	}

}
