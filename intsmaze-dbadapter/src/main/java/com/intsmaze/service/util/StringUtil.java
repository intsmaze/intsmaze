package com.intsmaze.service.util;

import java.util.ArrayList;
import java.util.List;
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

	public static Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

	public static void main(String[] args) {
		
		
	}
	
	/**
	* @author:YangLiu
	* @date:2017年12月13日 下午4:25:13 
	* @describe:判断字符串是否是数值型
	 */
	public static boolean isNumeric(String str){ 

		  return pattern.matcher(str).matches();    
	}
	
	
	/**
	* @author:YangLiu
	* @date:2018年1月11日 下午8:16:25 
	* @describe:返回字符串中包含字符串数组中的对应元素
	 */
	public static String stringIndex(String str,String[] arrs)
	{
		for(String arr:arrs)
		{
			if(str.indexOf(arr)!=-1)
			{
				return arr;
			}
		}
		throw new AmlException("字符串不包含字符串数组中的任何元素");
	}
	/**
	* @author:YangLiu
	* @date:2017年12月13日 上午9:42:45 
	* @describe:判断fileName字段是否包含交易标识.
	 */
	public static String getTardeTypeConditions(String fileName)
	{
		String tardeType="";
		for(String s:Constants.TRADE_TYPE)
		{
			if (fileName.indexOf(s) != -1) {
				tardeType = s;
				break;
			} 
		}
		return tardeType;
	}
	
	/**
	* @author:YangLiu
	* @date:2017年12月11日 下午9:35:16 
	* @describe:得到子表达式
	* days=90 and prem_modify_sum >3
	* days=30 and prem_refund_sum>3
	 */
	public static List<String> getIndexExpress(String express,List<Integer> listStart,List<Integer> listEnd) {
		List<String> list=new ArrayList<String>();
		for(int i=0;i<listStart.size();i++)
		{
			String sonExpress=express.substring(listStart.get(i)+1, listEnd.get(i)).trim();//截取()里面的子串，不包含().
			logger.debug("The son of this express is :"+sonExpress);
//			if(sonExpress.length()!=0)
//			{
//				String[] arg=sonExpress.split("and");
//				for(int j=0;j<arg.length;j++)
//				{
//					String file=arg[j].trim();
//					for(int k=0;k<marks.length;k++)
//					{
//						String mark=marks[k].split("=")[1];
//						mark=mark.substring(1, mark.length()-1);
//						if(file.indexOf(mark)!=-1)
//						{
//							list.add(sonExpress+"#:#"+mark);
			list.add(sonExpress);
//						}
//					}
//				}
//			}
		}
		return list;
	}
	
	
   /**
   * @author:YangLiu
   * @date:2017年12月11日 下午9:28:08 
   * @describe:返回str在字符串sql中所有的下标位置
    */
	public static List<Integer> strIndexOfS(String sql, String str) {
		boolean f = true;
		List<Integer> l = new ArrayList<Integer> ();
		int i = 0;
		while (f) {
			i = sql.indexOf(str, i);
			l.add(i);
			int k = sql.lastIndexOf(str);
			if (i == k) {
				break;
			}
			i++;
		}
		return l;
	}
}
