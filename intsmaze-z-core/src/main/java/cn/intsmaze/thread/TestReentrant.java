package cn.intsmaze.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 
 * @author:YangLiu
 * @date:2018年6月27日 上午10:26:43 
 * @describe: 
 */
public class TestReentrant {

	public static void main(String[] args) {
		
		ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();
		
		
		ReentrantLock lock=new ReentrantLock(true);
		lock.lock();
		
		
		lock.tryLock();
		
		
		try {
			lock.tryLock(100, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			lock.lockInterruptibly();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lock.unlock();
	}

}
