package org.intsmaze.core.util;

public class UrlConst {
	
	public static final String RELAY_COOKIE_URL = "http://sso.cathay-ins.com.cn/sapi/v1/sso/caToken/";
	public static final String DELETE_COOKIE_URL = "http://sso.cathay-ins.com.cn/sapi/v1/sso/caToken/";
	
	public static final String QUERY_ALL_EMPLOYEE_DATA_URL = "http://ygzx.cathay-ins.com.cn/api/v1/emp_center_data/qryAllEmployeeData";
	public static final String QUERY_MAX_VERSION_URL = "http://ygzx.cathay-ins.com.cn/api/v1/emp_center_data/qryMaxVersion";
	public static final String QUERY_BY_VERSION_URL = "http://ygzx.cathay-ins.com.cn/api/v1/emp_center_data/qryByVersion";
	
	public static final String getLoginUrl(String redirect,String appName){
		return "http://sso.cathay-ins.com.cn/sso/login?redirect=" + redirect + "&appName=" + appName;
	}
	
	public static final String getLogoutUrl(String redirect){
		return "http://sso.cathay-ins.com.cn/sso/logout?redirect=" + redirect;
	}
	
	
	
	
}
