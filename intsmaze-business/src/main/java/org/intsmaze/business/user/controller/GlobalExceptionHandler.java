package org.intsmaze.business.user.controller;
  
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.intsmaze.core.util.Result;
import org.intsmaze.core.util.ResultGenerator;
import org.springframework.web.bind.annotation.ControllerAdvice;  
import org.springframework.web.bind.annotation.ExceptionHandler;  
import org.springframework.web.bind.annotation.ResponseBody;  
import org.springframework.web.servlet.config.annotation.EnableWebMvc;  
  
  
@ControllerAdvice  
@EnableWebMvc  
public class GlobalExceptionHandler{  
  
	private static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	
    @ExceptionHandler(Exception.class)  
    @ResponseBody  
    public Result Exception(HttpServletRequest req,Exception e){  
    	
    	logger.error("异常信息为",e);
        return ResultGenerator.genFailResult("接口异常，请检查数据是否正确");  
    }  
  
    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)  
    @ResponseBody  
    public Result HttpRequestMethodNotSupportedException(HttpServletRequest req,Exception e){  
    	logger.error("异常信息为",e);
        return ResultGenerator.genFailResult("接口异常,检查是否是正确的post请求或get请求");  
    }  
    
}
