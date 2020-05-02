package com.intsmaze.spiler;

import java.util.Stack;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author:YangLiu
 * @date:2017年12月11日 下午8:25:35
 * @describe:
 */
public class StringUtil {
	
	private static final Logger logger = LoggerFactory
			.getLogger(StringUtil.class);

	public static void main(String[] args) {
		
		System.out.println(StringUtil.getPositionCode("https://jobs.51job.com/foshan/75869328.html?s=01&t=0"));
		String url="https://search.51job.com/list/020000,000000,0000,46,0,99,%2B,1,142.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=5&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
		String[] str=url.split(".html");
		System.out.println(str[0]);
		System.out.println(str[1]);
		System.out.println(str[0].lastIndexOf(","));
		System.out.println(str[0].substring(0,63));
	}
	
	/**
	* @author:YangLiu
	* @date:2018年4月9日 下午1:20:47 
	* @describe: https://jobs.51job.com/foshan/75869328.html?s=01&t=0
	 */
	public static String getPositionCode(String str){ 
		 str=str.split("\\?")[0];
		 String arr[]=str.split("/");
		 str=arr[arr.length-1];
		 return str.split("\\.")[0];
	}

	//https://jobs.51job.com/all/co225825.html
	public static String getCompanyCode(String str){
		String arr[]=str.split("/");
		str=arr[arr.length-1];
		return str.split("\\.")[0];
	}

	
	
	/**
	* @author:YangLiu
	* @date:2017年12月13日 下午4:25:13 
	* @describe:判断字符串是否是数值型
	 */
	public static boolean isNumeric(String str){ 
		  Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
		  return pattern.matcher(str).matches();    
	}
	
	
//	/**
//	* @author:YangLiu
//	* @date:2018年1月11日 下午8:16:25 
//	* @describe:返回字符串中包含字符串数组中的对应元素
//	 */
//	public static String stringIndex(String str,String[] arrs)
//	{
//		for(String arr:arrs)
//		{
//			if(str.indexOf(arr)!=-1)
//			{
//				return arr;
//			}
//		}
//	}
	
	
	
   /**
   * @author:YangLiu
   * @date:2017年12月11日 下午9:28:08 
   * @describe:返回str在字符串sql中所有的下标位置
    */
	public static Stack<Integer>   StrIndexOfS(String sql,String str) {
		boolean f = true;
		Stack<Integer> stack=new Stack<>();
//		List<Integer> l = new ArrayList<Integer> ();
		int i = 0;
		while (f) {
			i = sql.indexOf(str, i);
			stack.add(i);
			int k = sql.lastIndexOf(str);
			if (i == k) {
				break;
			}
			i++;
		}
		return stack;
	}



}
