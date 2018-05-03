package org.intsmaze.core.util;
public class SqlInjectFilterUtil {

//	private static String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|; |or|-|+|,";
	private static String inj_str = "'|exec|insert|select|delete|update|,";
	
	private static boolean sql_inj(String str) {
		String[] inj_stra = inj_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.indexOf("" + inj_stra[i] + "") >= 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判斷字段是否有sql注入嫌疑，如果有，則返回空字符串，沒有則返回原字符串
	 * @param str
	 * @return
	 * 对于order by、like等动态sql拼接的字段都需要做此判断，like的%需要自行拼接到sql中
	 * <br>
	 */
	public static String filterSqlInject(String str)
	{
		if(sql_inj(str))
		{
			return "";
		}
		else
		{
			return str;
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "seqno desc %";
		str =  SqlInjectFilterUtil.filterSqlInject(str);
		System.out.println(str);
	}

}
