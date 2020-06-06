package com.intsmaze.groovy;

import com.intsmaze.groovy.util.BeanFactory;
import groovy.lang.Binding;

import org.junit.Test;

/**
 * @author:YangLiu
 * @date:2018年5月9日 上午10:03:06
 * @describe:
 */
public class ExecuteGroovy {

	@Test
	public void test() {
		BeanFactory beanFactory = new BeanFactory("redis-base.xml");
		CacheGroovyService cacheService = beanFactory.getBean(CacheGroovyService.class);
		cacheService.start();
		GScriptExecutor scriptExecutor = beanFactory.getBean(GScriptExecutor.class);
		
		Binding shellContext = new Binding();
		shellContext.setVariable("AGE", "100");
		shellContext.setVariable("GROOVY", new GroovyDemo());
		
		//*********************配置脚本核心计算处*******************
		RuleCalc calc = new RuleCalc();
		calc.authRuleCalc(scriptExecutor, shellContext); // 核心计算
		//*********************配置脚本核心计算处*******************
		while(true)
		{
			
		}
		
		
//		Binding binding = new Binding();
//		GroovyShell shell = new GroovyShell(binding);
//		Script scrpt = shell.parse("script.groovy");
//
//		binding.setVariable("str1", "value1");
//		binding.setVariable("str2", "value2");
//		binding.setVariable("newConcat", scrpt);
//		System.out
//				.println(scrpt.evaluate("newConcat.customConcat(str1, str2)"));
//		System.out.println(scrpt.evaluate("str1.concat(str2)"));
	}
}
