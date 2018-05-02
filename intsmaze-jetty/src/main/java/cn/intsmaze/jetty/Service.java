package org.intsmaze.jetty;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;


public class Service extends HttpServlet{
	
	
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException
	{
		PrintWriter out=resp.getWriter();
							
		String msg=IOUtils.toString(req.getInputStream(),Charset.forName("gb18030"));
		out.write("1");
	}

}
