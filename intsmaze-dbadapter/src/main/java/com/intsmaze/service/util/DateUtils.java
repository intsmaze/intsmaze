package com.intsmaze.service.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * @author:YangLiu
 * @date:2017年11月24日 下午2:32:32 
 * @describe: 
 */
public class DateUtils {
	
	
	/**
	* @author:YangLiu
	* @date:2017年12月26日 上午10:15:17 
	* @describe:得到当前时间前n天的日期。
	* @param 参数为1，则是前一天的日期
	 */
	public static String getDateToTheDay(int number)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -number);
		Date d = c.getTime();
		String dateString = format.format(d);
		return dateString;
	}
	
	public static int getYear(int mumber)
	{
		 Date currentTime = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		 String dateString = formatter.format(currentTime);
		 int year=Integer.valueOf(dateString);
		 return year-mumber;
	}
	
	public static String getMonth()
	{
		 Date currentTime = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
		 String dateString = formatter.format(currentTime);
		 return dateString;
	}

	/**
	* @author:YangLiu
	* @date:2017年11月24日 下午2:38:55 
	* @describe:yyyy-MM-dd
	 */
	public static String getToday()
	{
		 Date currentTime = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String dateString = formatter.format(currentTime);
		 return dateString;
	}
	
	/**
	* @author:YangLiu
	* @date:2017年11月24日 下午2:39:00 
	* @describe:yyyy-MM-dd
	 */
	public static String getYesterday()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		Date d = c.getTime();
		String dateString = format.format(d);
		return dateString;
	}
	
	public static void main(String[] args)
	{
		System.out.println(DateUtils.getMonth());
		String date=DateUtils.getMonth();
		if("01-05".equals(date))
		{
			System.out.println("123123");
		}
	}
}
