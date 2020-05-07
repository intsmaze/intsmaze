package cn.intsmaze.collection.cocurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/** 
 * @author:YangLiu
 * @date:2018年1月29日 上午10:45:28 
 * @describe: 
 */
public class CounterPoolExector extends ThreadPoolExecutor{
	
	public CounterPoolExector(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	private AtomicInteger count=new AtomicInteger(0);//统计执行次数
	
	public long startTime=0;
	
	public String funcname="";
	
	
	@Override
    protected void afterExecute(Runnable r, Throwable t) {
		int i=count.addAndGet(1);
	}
	
}
