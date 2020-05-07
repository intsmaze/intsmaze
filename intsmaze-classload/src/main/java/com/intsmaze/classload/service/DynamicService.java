package com.intsmaze.classload.service;
/** 
 * @author:YangLiu
 * @date:2018年8月7日 下午4:16:20 
 * @describe: 
 */
public interface DynamicService {

	/**
	* @Description: 新增的java类，要实现该接口，业务方法写在该方法里面
	* @Param: json 模拟的参数，可以为null
	* @return: void
	* @Author: intsmaze
	* @Date: 2019/1/8
	*/
	public void executeService(String json);
	
}
