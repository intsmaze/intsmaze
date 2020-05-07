package com.intsmaze.jetty.handle;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;
import javax.servlet.Servlet;

import com.intsmaze.jetty.handle.model.HandlerAsyn;
import com.intsmaze.jetty.handle.model.HandlerNoAsyn;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
/**
 * @Description bean
 * @Author 刘洋
 * @Date 2018/12/23 11:07
 * @Version 1.0
 **/
public class HttpServer implements DisposableBean{

	final static Logger logger=LoggerFactory.getLogger(HttpServer.class);

	private int port=-1;

	private Server server;

	private Map<String,Servlet> servlets;

	public LinkedBlockingQueue<Runnable> queue= new LinkedBlockingQueue<Runnable>(50) ;

	public void init() throws Exception
	{
		server=new Server(port);
//		server.setThreadPool(new ExecutorThreadPool(4,4,1,TimeUnit.MINUTES,queue));//2018jetty版本没有这个方法了
		ServletContextHandler context=new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
//		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		server.setHandler(context);

		for(Entry<String, Servlet> s: servlets.entrySet())
		{
			ServletHolder servletHolder=new ServletHolder(s.getValue());
			logger.info("监听路径",s.getKey());
			context.addServlet(servletHolder, s.getKey());
		}
		server.start();
		logger.info("启动监听端口{}.",port);
	}


	//http://localhost:8012/jetty-main
	public static void main(String[] args) throws Exception
	{
		HttpServer httpServer=new HttpServer();
		httpServer.setPort(8012);

		HandlerNoAsyn handlerNoAsyn=new HandlerNoAsyn();
		HandlerAsyn handlerAsyn=new HandlerAsyn();

		Map<String,Servlet> servlets=new HashMap<String, Servlet>(10);
		httpServer.setServlets(servlets);
		httpServer.getServlets().put("/jetty-test", handlerNoAsyn);
		httpServer.getServlets().put("/jetty-main", handlerAsyn);
		httpServer.init();
		while (true)
		{
			System.out.println(httpServer.queue.size());
			Thread.sleep(10000);
		}
	}
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Map<String, Servlet> getServlets() {
		return servlets;
	}

	public void setServlets(Map<String, Servlet> servlets) {
		this.servlets = servlets;
	}

	@Override
    public void destroy() throws Exception {
		// TODO Auto-generated method stub

	}


}
