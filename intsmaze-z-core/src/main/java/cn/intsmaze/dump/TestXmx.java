package cn.intsmaze.dump;

import java.util.Vector;

/** 
 * @author:YangLiu
 * @date:2017年11月17日 上午11:33:19 
 * @describe: 
 */
public class TestXmx {

	public static void main(String[] args) {
		
		Vector v=new Vector();
		//默认1875378176:83142664:126353408
		//指定50M时
		//50331648:49240376:50331648
		System.out.println(Runtime.getRuntime().maxMemory()+":"+Runtime.getRuntime().freeMemory()+":"+Runtime.getRuntime().totalMemory());
		for(int i=1;i<=10;i++)
		{
			Byte[] b=new Byte[1024*1024];
			v.add(b);
			System.out.println(i+"M is allocated");
		}
		System.out.println(Runtime.getRuntime().maxMemory()+":"+Runtime.getRuntime().freeMemory()+":"+Runtime.getRuntime().totalMemory());
	}

}
