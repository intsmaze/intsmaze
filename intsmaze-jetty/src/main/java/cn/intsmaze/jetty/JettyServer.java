package org.intsmaze.jetty;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.springframework.beans.factory.DisposableBean;

public class JettyServer implements DisposableBean{

	private int port=-1;
	
	private Server server;
	
	private Map<String,Servlet> servlets;
	
	public void init()
	{
		server=new Server(port);
		server.setThreadPool(new ExecutorThreadPool(50,200,1,TimeUnit.MINUTES));
		ServletContextHandler context=new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		server.setHandler(context);
		
		for(Entry<String, Servlet> s: servlets.entrySet())
		{
			ServletHolder servletHolder=new ServletHolder(s.getValue());
			context.addServlet(servletHolder, s.getKey());
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

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
