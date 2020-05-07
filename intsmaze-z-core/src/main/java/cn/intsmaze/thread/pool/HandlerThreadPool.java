package cn.intsmaze.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:YangLiu
 * @date:2018年3月11日 下午3:19:55
 * @describe:
 */
public class HandlerThreadPool {

	public static void main(String[] args) {
		// ExecutorService es=new ThreadPoolExecutor(5,5,60L, TimeUnit.SECONDS,
		// new ArrayBlockingQueue<Runnable>(1),
		// Executors.defaultThreadFactory(),new
		// ThreadPoolExecutor.AbortPolicy());

		// ExecutorService es=new ThreadPoolExecutor(5,5,60L, TimeUnit.SECONDS,
		// new ArrayBlockingQueue<Runnable>(5),
		// Executors.defaultThreadFactory(),new
		// ThreadPoolExecutor.CallerRunsPolicy());

		// ExecutorService es=new ThreadPoolExecutor(5,5,60L, TimeUnit.SECONDS,
		// new ArrayBlockingQueue<Runnable>(5),
		// Executors.defaultThreadFactory(),new
		// ThreadPoolExecutor.DiscardPolicy());

		// ExecutorService es=new ThreadPoolExecutor(5,5,60L, TimeUnit.SECONDS,
		// new ArrayBlockingQueue<Runnable>(5),
		// Executors.defaultThreadFactory(),new
		// ThreadPoolExecutor.DiscardOldestPolicy());

		ExecutorService es = new ThreadPoolExecutor(5, 5, 60L,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5),
				Executors.defaultThreadFactory(),
				new RejectedExecutionHandler() {
					@Override
                    public void rejectedExecution(Runnable r,
                                                  ThreadPoolExecutor executor) {
						// System.out.println(r.toString()+":discard");//仅仅打印丢弃任务的信息，比DiscardPolicy策略高级一点。
						throw new RejectedExecutionException("Task "
								+ r.toString() + " rejected from ");// AbortPolicy策略就是直接抛出异常
					}
				});

		for (int i = 0; i < 10000; i++) {
			try {
				System.out.println(i);
				es.submit(new MyTask(i));
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("------------------------" + i);
			}
		}
	}
}
