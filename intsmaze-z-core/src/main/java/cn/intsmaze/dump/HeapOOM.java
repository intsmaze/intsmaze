package cn.intsmaze.dump;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 * 通过run configurations配置下列参数
 * VM Args：-Xms20m -Xmx20m
 *
 * //-XX:+PrintGCDetails -XX:SurvivorRatio=8 -Xms40M -Xmx40M -Xmn20M  **-XX:MaxTenuringThreShold=15这个参数有问题
 */
public class HeapOOM {

	public static void main(String[] args) {
		List<byte[]> list = new ArrayList<byte[]>();
		for (int i=0;i<=10000000;i++) {
			list.add(new byte[1024*1024/2]);
		}
		list=null;
		System.gc();//强制GC
	}
}
