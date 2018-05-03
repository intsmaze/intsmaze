///**
// * @author Allan
// * @belong to  信息科技有限公司
// * @date:2016-5-18 上午8:55:10
// * @version :
// * 
// */
//
//package org.intsmaze.business.springmvc.exception;
//
//import java.util.HashMap;
//import java.util.Map;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.intsmaze.business.service.ILoginfoService;
//import org.intsmaze.core.exception.FDFBMonitorException;
//import org.intsmaze.core.exception.FDFBRuntimeException;
//import org.intsmaze.core.exception.FDFBexception;
//import org.intsmaze.core.util.Constant;
//import org.intsmaze.core.util.StringPrintWriter;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//public class FDFBexceptionHandler implements HandlerExceptionResolver{
//	
//	Logger logger = Logger.getLogger(FDFBexceptionHandler.class);
//	
//	@Autowired
//	ILoginfoService loginfoService;
//	
//	@Override
//	public ModelAndView resolveException(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex) {
//		logger.info("FDFBRuntimeException FDFBRuntimeException FDFBRuntimeException FDFBRuntimeException FDFBRuntimeException FDFBRuntimeException  FDFBRuntimeException");
//		logger.error("Catch Exception: ",ex);//把漏网的异常信息记入日志  
//        Map<String,Object> map = new HashMap<String,Object>();  
//        StringPrintWriter strintPrintWriter = new StringPrintWriter();  
//        ex.printStackTrace(strintPrintWriter);  
//        map.put("errorMsg", strintPrintWriter.getString());//将错误信息传递给view  
//        String type = "application";//默认为系统级exception，try catch捕捉到的都为此类型
//        //根据不同的exception类型进行记录，可自行添加需要的type和对应的exception，从而达到分类的效果。代码中需要进行throw new 对应exception类型("需要记录的exception内容");
//        if(ex instanceof FDFBRuntimeException)
//        {
//        	logger.info("this is a FDFBRuntimeException");
//        	type = "FDFB";
//        }
//        else if(ex instanceof FDFBexception)
//        {
//        	logger.info("this is a FDFBexception");
//        	type = "FDFB";
//        }
//        else if(ex instanceof FDFBMonitorException)
//        {
//        	logger.info("this is a FDFBMonitorException");
//        	type = "monitor";
//        }
//        
//        loginfoService.addInfo(type,strintPrintWriter.getString(), Constant.BY_ENGINE, Constant.BY_ENGINE);
//        return new ModelAndView("error",map);  
//	}
//
//}
