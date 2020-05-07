package cn.intsmaze.dump;

/**
 * 虚拟机栈和本地方法栈OOM测试
 * 方法递归，栈的深度不够
 * VM Args：-Xss128k   递归深度 957
 * Exception in thread "main" java.lang.StackOverflowError
 */
public class StackSOF {
	private int stackLength = 1;

	public void stackLeak() {
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) throws Throwable {
		StackSOF oom = new StackSOF();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length：" + oom.stackLength);
			throw e;
		}
	}
}