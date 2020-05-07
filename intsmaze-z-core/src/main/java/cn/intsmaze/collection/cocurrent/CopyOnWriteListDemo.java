package cn.intsmaze.collection.cocurrent;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteListDemo {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		CopyOnWriteArrayList list=new CopyOnWriteArrayList();
		list.add("nihao");
		list.get(0);
		
//		List list=new CopyOnWriteArraySet<E>();

	}

}
