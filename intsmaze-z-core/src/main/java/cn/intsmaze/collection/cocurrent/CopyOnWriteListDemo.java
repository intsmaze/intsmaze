package cn.intsmaze.collection.cocurrent;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CopyOnWriteListDemo {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		CopyOnWriteArrayList list=new CopyOnWriteArrayList();
		list.add("nihao");
		list.get(0);
		ReadWriteLock lock = new ReentrantReadWriteLock();

		lock.readLock().lock();
//		List list=new CopyOnWriteArraySet<E>();

	}

}
