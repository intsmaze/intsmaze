package cn.intsmaze.collection;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.TestCase;

/**
 * @author:YangLiu
 * @date:2018年6月6日 下午12:07:52
 * @describe:
 */
public class QueueCase extends TestCase {

	public static void testArrayBlockingQueue() throws InterruptedException {

		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
		queue.add("1");//看源码发现，add里面就是用的是super的offer,只是对增加了无法添加则直接抛异常
		queue.add("2");
		queue.add("3");
		queue.add("4");
		queue.size();
		queue.isEmpty();
		queue.put("5");//无法添加会阻塞着
		System.out.println(queue.offer("intsmaze"));// 不会抛异常，而是返回true,false，这里调用的是子类的offer方法，不是父类的offer方法

		// 集合方式遍历，元素不会被移除
		for (String x : queue) {
			System.out.println(x);
		}
		
		
		System.out.println("-------2-----");
		// 队列方式遍历，元素逐个被移除  queue.peek()获取头部元素，并不删除。
		System.out.println(queue.peek());
		System.out.println(queue.peek());//两次都是一样的
		while (queue.peek() != null) {
			System.out.println("队列方式遍历:"+queue.poll());
		}
		//poll会没有元素，返回为null，take会阻塞一直等待

		System.out.println(queue.poll());
		System.out.println(queue.take());
		/**
		   public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return (count == 0) ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }
    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)
                notEmpty.await();//take会在这里循环等待，
            return dequeue();
        } finally {
            lock.unlock();
        }
    }
		 */

		System.out.println(queue.remove());// 无法删除则直接抛异常
//		Iterable<String> it = (Iterable<String>) queue.iterator();// 队列提供迭代器遍历

	}
	
	public static void LinkedBlockingQueue() throws InterruptedException {

		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(4);//take put进行锁分类
		queue.offer("1");
		queue.offer("2");
		queue.offer("3");
		queue.offer("4");
		
		queue.put("5");
//		
//		queue.remove();
		queue.take();
		queue.poll();
		int a=100;
		queue.isEmpty();
		queue.size();
		int b=20;
		
		int c=12;
		
		a=b=c;
		System.out.println(a);
		System.out.println(b);
		
		AtomicInteger count = new AtomicInteger();
		System.out.println(count.get());
		
	}
}
