package cn.intsmaze;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 
 * @author:YangLiu
 * @date:2018年3月20日 上午9:31:22 
 * @describe: 
 */
public class StringTest {

	public static void main(String[] args) {

		String.join("1","2");
		StringBuffer s=new StringBuffer();
		
		
		String str=new String("intsmaze");
		String s1="intsmaze";
		String s2="intsmaze";
		System.out.println(s1==s2);//true
		System.out.println(s1==str);//false
		System.out.println(s1==str.intern());//true  str.intern()返回字符串在常量池中的引用
		
		
		
		//时间换空间导致内存OOM的问题，在1.8版本已经没有了，什么版本有不清楚
//		List<String> handler=new ArrayList<String>();
//		for(int i=0;i<1000000;i++)
//		{
////			HugeStr h=new HugeStr();
//			ImprovedHugeStr h=new ImprovedHugeStr();
//			handler.add(h.getSubString(1, 5));
//		}
		
		
		
	}
	
	static class HugeStr
	{
		private String str=new String(new char[100000]);
		
		public String getSubString(int begin,int end)
		{
			return str.substring(begin,end);
		}
	}

	static class ImprovedHugeStr
	{
		private String str=new String(new char[100000]);
		
		public String getSubString(int begin,int end)
		{
			return new String(str.substring(begin,end));
		}
	}
	
}















