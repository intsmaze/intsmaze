package com.intsmaze.socket;import java.net.*;
import java.io.*;

public class Spider
{

	public static void main(String[] args) throws MalformedURLException
	{
		try{
			URL url = new URL("http://search.51job.com/list/020000,000000,0000,00,9,99,%25E9%2594%2580%25E5%2594%25AE,2,1.html?lang=c&degreefrom=99&stype=&workyear=99&cotype=99&jobterm=99&companysize=99&radius=-1&address=&lonlat=&postchannel=&list_type=&ord_field=&curr_page=&dibiaoid=0&landmark=&welfare=");
			try {
				URLConnection urlConn=url.openConnection();
				InputStream is=urlConn.getInputStream();
	            InputStreamReader isr=new InputStreamReader(is);
	            BufferedReader br=new BufferedReader(isr);
				FileOutputStream fos=new FileOutputStream("1.txt");
				String strLine;
				StringBuffer sb = new StringBuffer();
					
				int i=0;
				while((strLine=br.readLine())!=null)
				{
//					if(i==1)
//					{					
//						String n=strLine.replaceAll("<([^>]*)>", " ");
//						System.out.println(n);
//						i=0;
//					}
//					if(strLine.contains("<strong>电  话</strong>")||strLine.contains("<strong>邮  编</strong>")
//							||strLine.contains("<strong>地  址</strong>"))				
//					{
//						i=1;
//					}	
//					sb.append(strLine);
				}

				is.close();
				fos.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
	}
}