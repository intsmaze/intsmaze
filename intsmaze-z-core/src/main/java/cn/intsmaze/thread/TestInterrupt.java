package cn.intsmaze.thread;
/** 
 * @author:YangLiu
 * @date:2018年6月26日 下午8:24:57 
 * @describe: 
 */
public class TestInterrupt {

	public static void main(String[] args) throws InterruptedException {
		Thread t1=new Thread()
		{
			@Override
            public void run()
			{
				while(true)
				{
					if(Thread.currentThread().isInterrupted())
					{
						System.out.println("Interrupt");
						break;
					}
					
					try {
						Thread.sleep(60000);//sleep会交出cpu进入睡眠状态，如果是在上锁代码内sleep，那么不会释放锁的，所以sleep会响应中断，抛出异常，使得业务代码可以做出响应
					} catch (InterruptedException e) {
						System.out.println("Interrupt when sleep");
						Thread.currentThread().interrupt();//设置中断状态
					}
//					Thread.yield();
				}
			}
		};

		t1.start();
		Thread.sleep(2000);
		t1.interrupt();//观察发现，线程都睡眠了，怎么还能立刻响应中断?? https://blog.csdn.net/tomorrow_fine/article/details/73834532 貌似是操作系统进行干预的，将睡眠状态直接改为就绪状态，然后cpu调度响应中断
	}
}
