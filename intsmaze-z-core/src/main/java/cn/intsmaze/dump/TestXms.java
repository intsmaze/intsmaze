package cn.intsmaze.dump;

/**
 * @author:YangLiu
 * @date:2017年11月17日 上午11:44:23
 * @describe:
 */
public class TestXms {

	public static void main(String[] args) {
		java.lang.management.MemoryUsage usage = java.lang.management.ManagementFactory
				.getMemoryMXBean().getHeapMemoryUsage();
		System.out.println("Max: " + usage.getMax());
		System.out.println("Init: " + usage.getInit());
		System.out.println("Committed: " + usage.getCommitted());
		System.out.println("Used: " + usage.getUsed());
	}
}
