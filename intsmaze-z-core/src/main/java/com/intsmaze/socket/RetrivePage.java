package com.intsmaze.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * 下载指定页面
 *
 */
public class RetrivePage {
    public static String downloadPage(String path){
    	try {
    		//根据传入的路径构造URL
			URL pageURL = new URL(path);
			
			//创建网络流
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					pageURL.openStream(),"utf-8"));
					
			//指定编码格式
			Scanner scanner = new Scanner(new InputStreamReader(
					pageURL.openStream(),"utf-8"));
					
			scanner.useDelimiter("\\z"); //可以用正则表达式分段读取网页
			//读取网页内容
			StringBuilder pageBuffer = new StringBuilder();
			while (scanner.hasNext())
			{
				pageBuffer.append( scanner.next() );
			}
			return pageBuffer.toString();

			/*String line;
		   //读取网页内容
			StringBuilder pageBuffer = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				pageBuffer.append(line);
			}
			//返回网页内容
			return pageBuffer.toString();*/

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		return null;
    }
    
	/**
	 * 测试代码
	 */
	public static void main(String[] args) {
		//抓取lietu首页,输出
		System.out.println(RetrivePage.downloadPage("http://www.lietu.com"));
	}
}
