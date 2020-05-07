package cn.intsmaze.future.jdk;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author:YangLiu
 * @date:2018年6月3日 下午12:43:48
 * @describe:
 */
public class RealData implements Callable<String> {

	protected String result;

	public RealData(String result) {
		this.result = result;
	}

	@Override
	public String call() throws Exception {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(result);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();

	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {

		FutureTask<String> future = new FutureTask<String>(new RealData(
				"intsmaze"));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		// 开启线程，进行realdata的call()执行
		executor.submit(future);

		System.out.println("请求完毕");

		try {
			// 用一个sleep模拟其他业务的处理逻辑，在处理过程中，realData被创建，充分利用了等待的时间
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("数据=" + future.get());
	}
}
