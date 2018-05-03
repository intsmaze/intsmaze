package org.intsmaze.business.springmvc.interaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.intsmaze.business.service.IButtonCustomConfigService;
import org.intsmaze.business.service.IButtonPermitService;
import org.intsmaze.business.service.IColumnPermitService;
import org.intsmaze.business.springmvc.controller.BusinessUtil;
import org.intsmaze.business.vo.FDFBButtonPermitVo;
import org.intsmaze.business.vo.FDFBColumnPermitVo;
import org.intsmaze.core.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FDFBhandlerInterceptor extends HandlerInterceptorAdapter {
	private final Logger log = Logger.getLogger(FDFBhandlerInterceptor.class);

	// 记录每个Controller方法的开始时间，记录在线程中，保证线程安全
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
			"StopWatch-StartTime");

	@Autowired
	IColumnPermitService columnPermitService;
	
	@Autowired
	IButtonPermitService buttonPermitService; 
	
	@Autowired
	IButtonCustomConfigService buttonCustomConfigService;

	
	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		startTimeThreadLocal.set(System.currentTimeMillis());// 线程绑定变量（该数据只有当前请求的线程可见）

		log.info("==============执行顺序: 1、preHandle================");
		String requestUri = request.getRequestURI();
		log.info("requestUri:" + requestUri);

		Map user = (Map) request.getSession().getAttribute(
				Constant.USERINFO_SESSION);

		if (requestUri.indexOf("login") > -1
				|| requestUri.equals("/FDFBweb/code")) {
			if(user!=null)
			{
				request.getRequestDispatcher("/index").forward(request, response);
				return false;
			}
			else
			{
				return true;
			}
		}

		if (user == null) {// 权限控制
			log.info("Interceptor：跳转到login页面！");
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		} else {
			// 判断是否是ajax请求
//			if (request.getHeader("x-requested-with") != null
//					&& request.getHeader("x-requested-with").equalsIgnoreCase(
//							"XMLHttpRequest")) {
//				log.debug("============This is a ajax request=============");
//				// TODO: ajax请求预处理
//			} else {
//				// TODO:正常HTTP请求预处理
//			}
			return true;
		}
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("==============执行顺序: 2、postHandle================");
		//此处进行权限控制，包括页面的字段、button显示、可修改字段显示等
		if(modelAndView != null)
		{
			Map user = (Map) request.getSession().getAttribute(Constant.USERINFO_SESSION);
			String targetUrl = modelAndView.getViewName();
			if(user == null || user.size() < 1)
			{
				//login do nothing
			}
			else
			{
				List<FDFBColumnPermitVo> list = columnPermitService.getColumnPermitByRoleidAndDepidAndPageUrl(String.valueOf(user.get(Constant.USER_ROLE_SESSION)), 
						targetUrl, "");//String.valueOf(user.get(Constant.USER_DEPARTMENT_SESSION))//数据权限只关联到公司和用户
				if(list == null || list.size() < 1)
				{
					//TODO:从另一张配置表获取默认字段
					list = columnPermitService.getColumnPermitByRoleidAndDepidAndPageUrl(Constant.DEFAULT, targetUrl, null);
				}
				
				if(list == null || list.size() < 1)
				{
					list = new ArrayList(0);
				}
				
				List<FDFBButtonPermitVo> buttonList = buttonPermitService.getButtonPermitByRoleidAndDepidAndPageUrl(String.valueOf(user.get(Constant.USER_ROLE_SESSION)), 
						targetUrl, "");//String.valueOf(user.get(Constant.USER_DEPARTMENT_SESSION))
				if(buttonList == null || buttonList.size() < 1)
				{
					//TODO:从另一张配置表获取默认字段
					buttonList = buttonPermitService.getButtonPermitByRoleidAndDepidAndPageUrl(Constant.DEFAULT, targetUrl, null);
				}
				
				if(buttonList == null || buttonList.size() < 1)
				{
					buttonList = new ArrayList(0);
				}
				
				List buttonConfigList = buttonCustomConfigService.getButtonConfigByUsernameAndPageUrl(BusinessUtil.getCurrentUserName(request), targetUrl);
				if(buttonConfigList == null || buttonConfigList.size() < 1)
				{
					buttonConfigList = new ArrayList(0);
				}
				
				modelAndView.addObject(Constant.PAGE_COLUMN_LIST, list);
				modelAndView.addObject(Constant.PAGE_BUTTON_LIST, buttonList);
				modelAndView.addObject(Constant.PAGE_COLUMN_SHOW_OR_HIDE_LIST, buttonConfigList);
				modelAndView.addObject("targetUrl", targetUrl);
			}
		}
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 当有拦截器抛出异常o时,会从当前拦截器往回执行所有的拦截器的afterCompletin()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("==============执行顺序: 3、afterCompletion================");
		// 此处可以捕捉exception，但框架实现了统一的exception捕捉类FDFBexceptionHandler，故此处不做任何处理
		long endTime = System.currentTimeMillis();// 方法运行结束时间
		long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
		long consumeTime = endTime - beginTime;
		if (consumeTime > 2000) {// 此处认为处理时间超过2秒的请求为慢请求 ,可以改为配置
			// TODO 记录到日志文件
			log.warn(String.format(
					"%s consume %d millis",
					"Request process too long time:"
							+ request.getRequestURI(), consumeTime));
		}
	}

}