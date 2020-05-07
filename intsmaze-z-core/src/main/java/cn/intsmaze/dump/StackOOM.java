package cn.intsmaze.dump;

/**
 * 线程导致内存溢出异常  Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 * 由于线程太多了导致栈溢出
 * VM Args：-Xss2M(这时候不妨设置大些)
 * 容易导致系统假死
 */
public class StackOOM {
	
	private void dontStop() {
		while (true) {
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stackLeakByThread() {
		int number=0;
		while (true) {
			Thread thread = new Thread(new Runnable() {
				@Override
                public void run() {
					dontStop();
				}
			});
			thread.start();
			System.out.println(++number);
		}
	}

	public static void main(String[] args) throws Throwable {
		StackOOM oom = new StackOOM();
		oom.stackLeakByThread();
	}
}
