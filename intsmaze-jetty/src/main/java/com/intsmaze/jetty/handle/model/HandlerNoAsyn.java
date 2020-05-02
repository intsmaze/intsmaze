package com.intsmaze.jetty.handle.model;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.concurrent.Semaphore;

/**
 * @Description bean
 * @Author 刘洋
 * @Date 2018/12/23 11:07
 * @Version 1.0
 **/
public class HandlerNoAsyn extends HttpServlet implements Cloneable{
	
	final static Logger logger=LoggerFactory.getLogger(HandlerNoAsyn.class);

	//允许最大并发数为2
	private final Semaphore limiter = new Semaphore(1, true);

//	RateLimiter limiter = RateLimiter.create(5.0);

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		limiter.tryAcquire();

		if(limiter.tryAcquire())
		{
			try {
				String msg = IOUtils.toString(request.getInputStream(), Charset.forName("utf-8"));
				logger.info("接收到消息>>>>>>>>>>>>>>>>>>" + msg);
				Thread.sleep(500);
				out.write("sucess------------------------" + msg);
			} catch (Exception e) {
				logger.error("", e);
				out.write("error");
			} finally {
				if (out != null) {
					out.close();
				}
			}
//			limiter.release();
		}
//		else
//		{
//			logger.info("--------------------------------" );
//			out.write("-------------------------------");
//		}
	}
	

}
