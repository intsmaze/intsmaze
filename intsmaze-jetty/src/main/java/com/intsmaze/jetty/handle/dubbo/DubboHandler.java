package com.intsmaze.jetty.handle.dubbo;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import com.intsmaze.common.rpc.service.DubboJsonService;
import com.intsmaze.common.util.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;

/**
 * @Description 将接收的消息通过dubbo转发
 * @Author 刘洋
 * @Date 2018/12/23 11:07
 * @Version 1.0
 **/
public class DubboHandler extends HttpServlet implements Cloneable{
	
	final static Logger logger=LoggerFactory.getLogger(DubboHandler.class);
	
	DubboJsonService dubboJsonService;
	
	public DubboJsonService getDubboJsonService() {
		return dubboJsonService;
	}

	public void setDubboJsonService(DubboJsonService dubboJsonService) {
		this.dubboJsonService = dubboJsonService;
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String msg="";
		try{
			msg=IOUtils.toString(request.getInputStream(),Charset.forName("utf-8"));
			logger.info("接收到消息:"+msg);
			out.write(JSON.toJSONString(ResultGenerator.genSuccessResult(dubboJsonService.findComputer(1))));
		}catch(Exception e)
		{
			logger.error("",e);
			out.write(JSON.toJSONString(ResultGenerator.genFailResult(msg+"  处理失败")));
		}finally
		{
			if(out!=null)
			{
				out.close();
			}
		}
	}
	
}
