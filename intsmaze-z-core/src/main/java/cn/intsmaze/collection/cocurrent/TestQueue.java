package cn.intsmaze.collection.cocurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

/** 
 * @author:YangLiu
 * @date:2018年6月28日 下午1:20:26 
 * @describe: 
 */
public class TestQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public void testConcurrentLinkedQueue()
	{
		ConcurrentLinkedQueue queue=new ConcurrentLinkedQueue();
		queue.add("aa");
		queue.offer("as");
		
		queue.remove();
		queue.clear();
		
		queue.peek();
		queue.element();
		
		
		queue.toArray();
		queue.stream();
		queue.spliterator();
	}
}
