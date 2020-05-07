package cn.intsmaze.thread;
/** 
 * @author:YangLiu
 * @date:2018年3月16日 上午11:29:47 
 * @describe: 
 */
public class TestVolatile {

	public static void main(String[] args) throws InterruptedException {
		MyTask t=new MyTask();//这种方式，多线程间访问没问题，因为stop变量时属于对象的
		t.start();//开启的子线程
		Thread.sleep(1000);
		t.stopMe();//这其实是主线程，这里虽然是调用同一个对象的方法，但是工作内存不同吧，我的天啊，我在说什么。
		
		
		//这种方式无论是否用volatile修饰，都是可见的。
//		MyTask_demo t=new MyTask_demo();
//		Thread t1=new Thread(t);
//		t1.start();
////	t.stopMe();
////	Thread t2=new Thread(t);
	}

}

class MyTask extends Thread
{
	
	private volatile boolean stop=false;
//	private boolean stop=false;
	
	public void stopMe()
	{
		stop=true;
	}
	
	@Override
    public void run() {
		int i=0;
		while(!stop)
		{
			i++;
		}
		System.out.println("stop thread");
	}
	
}

class MyTask_demo implements Runnable
{
	
	private volatile boolean stop=false;
//	private boolean stop=false;
	
	public void stopMe()
	{
		stop=true;
	}
	
	@Override
    public void run() {
		int i=0;
		while(!stop)
		{
//			i++;
		}
		System.out.println("stop thread");
	}
	
}