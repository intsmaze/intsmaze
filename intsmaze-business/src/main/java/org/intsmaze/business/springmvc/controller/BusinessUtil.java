package org.intsmaze.business.springmvc.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;

import org.intsmaze.business.Interfaces.IUserApi;
import org.intsmaze.business.service.ICodeValueService;
import org.intsmaze.business.service.ISysConfigService;
import org.intsmaze.business.springInit.SpringContextUtil;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

public class BusinessUtil {
	
	/**
	 * 根据bean的类型获取相应类型的对象，没有使用泛型，获得结果后，需要强制转换为相应的类型
	 * 
	 * @param clazz
	 *            bean的类型，没有使用泛型
	 * @return Object类型的对象
	 */
	private static Object getBean(Class clazz) {
		WebApplicationContext wac = ContextLoader
				.getCurrentWebApplicationContext();
		Object bean = wac.getBean(clazz);
		return bean;
	}// velocity toolbox.xml中配置的工具类貌似无法注入bean，只能通过spring上下文获取
	
	public static String getValueByCodeAndKey(String key, String code)
	{
		if(key==null || code==null)
		{
			return "";
		}
		ICodeValueService codeValueService = (ICodeValueService) getBean(ICodeValueService.class);
		return StringUtil.isNullString(codeValueService.getValueByKeyAndCode(key, code));
	}
	
	public static String getRoleNameByRoleids(String roleid)
	{
		IUserApi userApi = (IUserApi) getBean(IUserApi.class);
		return StringUtil.isNullString(userApi.getRoleNameByRoleids(roleid));
	}
	
	public static String getRoleNameByUserId(String userId)
	{
		IUserApi userApi = (IUserApi) getBean(IUserApi.class);
		return StringUtil.isNullString(userApi.getRoleNameByUserid(userId));
	}
	
	public static String getDepNameByDepid(String depid)
	{
		IUserApi userApi = (IUserApi) getBean(IUserApi.class);
		return StringUtil.isNullString(userApi.getDepNameByDepid(depid));
	}
	
	public static String getCurrentUserName(HttpServletRequest request)
	{
		Map map = (Map)request.getSession().getAttribute(Constant.USERINFO_SESSION);
		return (String)map.get(Constant.USER_NAME_SESSION);
	}
	
	public static String getCurrentUserId(HttpServletRequest request)
	{
		Map map = (Map)request.getSession().getAttribute(Constant.USERINFO_SESSION);
		return (String)map.get(Constant.USER_ID_SESSION);
	}
	
	public static String getCurrentUserOrgInnerCoding(HttpServletRequest request){
		Map map = (Map)request.getSession().getAttribute(Constant.USERINFO_SESSION);
		return (String)map.get(Constant.USER_ORG_INNER_CODING_SESSION);
	}
	
	public static String getCurrentUserOrgName(HttpServletRequest request){
		Map map = (Map)request.getSession().getAttribute(Constant.USERINFO_SESSION);
		return (String)map.get(Constant.USER_ORG_NAME_SESSION);
	}
	
	public static String getCurrentUserOrgNo(HttpServletRequest request){
		Map map = (Map)request.getSession().getAttribute(Constant.USERINFO_SESSION);
		return (String)map.get(Constant.USER_NEW_ORG_NO_SESSION);
	}
	
	
	public static String getCurrentUserXm(HttpServletRequest request)
	{
		Map map = (Map)request.getSession().getAttribute(Constant.USERINFO_SESSION);
		return (String)map.get(Constant.USER_XM_SESSION);
	}
	
	public static String getCodeByValueAndKey(String key, String value)
	{
		if(key==null || value==null)
		{
			return "";
		}
		ICodeValueService codeValueService = (ICodeValueService) getBean(ICodeValueService.class);
		return StringUtil.isNullString(codeValueService.getCodeByKeyAndValue(key, value));
	}
	
	/**
	 * 根据key和code获取SysConfig表中对应的value，无缓存
	 * @param key
	 * @param code
	 * @return
	 */
	public static String getSysConfigValueByKeyAndCode(String key, String code)
	{
		ISysConfigService sysConfigService = (ISysConfigService)getBean(ISysConfigService.class);
		return sysConfigService.getValueByKeyAndCode(key, code);
	}
	
	public static String getCurrentUserDepid(HttpServletRequest request)
	{
		Map map = (Map)request.getSession().getAttribute(Constant.USERINFO_SESSION);
		return (String)map.get(Constant.USER_DEPARTMENT_SESSION);
	}
	
	public static String getUserxmByUserid(String userid)
	{
		IUserApi userApi = (IUserApi) getBean(IUserApi.class);
		return userApi.getUserXmBySeqno(userid);
	}
	
	public static String getUserDepByUserid(String userid)
	{
		IUserApi userApi = (IUserApi) getBean(IUserApi.class);
		String depid = userApi.getUserDepBySeqno(userid);
		return depid;
	}
	
	public static String getUserxmByGh(String userid)
	{
		IUserApi userApi = (IUserApi) getBean(IUserApi.class);
		return userApi.getUserXmByUserid(userid);
	}
	
//	public static String getAreaNameByID(String areaID)
//	{
//		if(areaID==null || "".equals(areaID))
//		{
//			return "";
//		}
//		else
//		{
//			ITbmsApi areaService = (ITbmsApi)getBean(ITbmsApi.class);
//			return areaService.getAreaNameBySeqno(areaID);
//		}
//		
//	}
	
	/**
	 * 获取国际化key对应的描述
	 * @param key
	 * @return
	 */
	public static String getGlobelTextByKey(String key, HttpServletRequest request)
	{
		SpringContextUtil contextUtil = (SpringContextUtil)getBean(SpringContextUtil.class);
		SessionLocaleResolver resolver = (SessionLocaleResolver)getBean(SessionLocaleResolver.class);
		String result = contextUtil.getApplicationContext().getMessage(key, null, resolver.resolveLocale(request));
		if("".equals(StringUtil.isNullString(result)))
		{
			return key;
		}
		else
		{
			return result;
		}
	}
	
	
	public static String baseCheckInputVO(Object requestVo, HttpServletRequest request) {
		String result = null;
		LocalValidatorFactoryBean validator = (LocalValidatorFactoryBean)getBean(LocalValidatorFactoryBean.class);
//		Set<ConstraintViolation<Object>> validResult = validator.getValidator().validate(requestVo, ConstraintValidator.class);
		Set<ConstraintViolation<Object>> validResult = validator.getValidator().validate(requestVo, Default.class);
//		Set<ConstraintViolation<Object>> validResult = Validation.buildDefaultValidatorFactory().getValidator().validate(requestVo);
		if (null != validResult && validResult.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Iterator<ConstraintViolation<Object>> iterator = validResult.iterator(); iterator.hasNext();) {
				ConstraintViolation<Object> constraintViolation = (ConstraintViolation<Object>) iterator.next();
				//这里只取了字段名，如果需要其他信息可以自己处理
				String resultMsg = BusinessUtil.getGlobelTextByKey(constraintViolation.getMessage(), request);
				sb.append(resultMsg).append("、");
			}
			if(sb.lastIndexOf("、") == sb.length()-1){
				sb.delete(sb.length()-1, sb.length());
			}
			result = sb.toString();
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
