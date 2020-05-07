package cn.intsmaze.thread.pool;

/**
 * @author:YangLiu
 * @date:2018年3月11日 下午2:19:01
 * @describe:
 */
public class MyTask implements Runnable {

	private int number;

	public MyTask(int number) {
		super();
		this.number = number;
	}

	@Override
    public void run() {
		System.out.println(System.currentTimeMillis() + "thread id:"
				+ Thread.currentThread().getId() + "===" + number);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "MyTask [number=" + number + "]";
	}
}
