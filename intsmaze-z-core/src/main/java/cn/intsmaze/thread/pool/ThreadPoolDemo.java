package cn.intsmaze.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:YangLiu
 * @date:2018年3月11日 下午1:28:10
 * @describe:
 */
public class ThreadPoolDemo {

	public static void main(String[] args) {

		ExecutorService es = new ThreadPoolExecutor(3, 8, 60L,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
				Executors.defaultThreadFactory(),
				new RejectedExecutionHandler() {
					@Override
                    public void rejectedExecution(Runnable r,
                                                  ThreadPoolExecutor executor) {
						System.out.println("discard");
					}
				});

		es = Executors.newFixedThreadPool(3);
		es = Executors.newSingleThreadExecutor();

		es = Executors.newCachedThreadPool();

		es = Executors.newScheduledThreadPool(2);
		es = Executors.newSingleThreadScheduledExecutor();

		es = Executors.newWorkStealingPool();
		
		for (int i = 0; i < 10; i++) {
			MyTask task = new MyTask(i);
			es.submit(task);
		}
	}

}
