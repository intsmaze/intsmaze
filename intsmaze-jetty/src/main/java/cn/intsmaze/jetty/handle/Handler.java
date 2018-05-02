package org.intsmaze.jetty.handle;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.Queues;

@SuppressWarnings("serial")
public class Handler extends HttpServlet implements Cloneable{
	
	final static Logger logger=LoggerFactory.getLogger(Handler.class);
	
	private BlockingQueue<String> cacheQueue=Queues.newArrayBlockingQueue(50000);
	
	private List<Thread> threads=new ArrayList<Thread>();
	
	private AtomicBoolean shutdown=new AtomicBoolean(false);
	
	private int threadNum=5;
	
	public void init()
	{
		for(int i=0;i<threadNum;i++)
		{
			logger.info("启动后台线程DoThread-"+i);
			Thread t=new DoThread("DoThread-"+i);
			threads.add(t);
			t.start();
		}
	}
	
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		
		try{
			String msg=IOUtils.toString(request.getInputStream(),Charset.forName("gb18030"));
			logger.info("接收到消息:"+msg);
			boolean result=cacheQueue.offer(msg);
			if(!result)
			{
				logger.error("cacheQueue is full");
			}
			out.write("sucess");
		}catch(Exception e)
		{
			logger.error("",e);
			out.write("error");
		}finally
		{
			if(out!=null)
			{
				out.close();
			}
		}
	}
	
	
	private class DoThread extends Thread
	{
		public DoThread(String name)
		{
			super(name);
		}
		
		public void run()
		{
			logger.info("启动后台线程进行处理---前"+cacheQueue.size());
			while(shutdown.get()==false || cacheQueue.size()>0)
			{
				logger.info("后台线程进行处理");
				try
				{
					doRun();
				}catch(Exception e)
				{
					logger.error("",e);
				}
			}
		}

		private void doRun() throws InterruptedException {
			String msg=cacheQueue.poll(15,TimeUnit.SECONDS);
			if(msg==null)
			{
				return;
			}
			logger.info(msg);
		}
	}
}
