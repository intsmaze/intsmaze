package com.intsmaze.classload.service.impl;

import com.intsmaze.classload.service.DynamicService;

/**
 * @author:YangLiu
 * @date:2018年5月18日 下午1:22:12
 * @describe:这种方式是，开发人员编写对应接口的新的实现类，然后将该工程编译打包后上传到指定位置，
 * 然后运行的程序定时去该位置读取jar包，然后动态的加载新增的实现类，从而实现不停应用即可发布。
 */
public class UrlClassLoaderJarImpl implements DynamicService{
	
	public int age = 30;
	
	// 静态代码块，类被装在的时候自动运行。
	static {
		System.out.println("动态类被加载进来了");
		System.out.println(UrlClassLoaderJarImpl.class.getClassLoader());// 输出类装载器的类型
	}


	@Override
    public void executeService(String json) {
		System.out.println("我是程序运行中，动态加载进来的业务逻辑和类----------"+json);
	}
	
	
}
