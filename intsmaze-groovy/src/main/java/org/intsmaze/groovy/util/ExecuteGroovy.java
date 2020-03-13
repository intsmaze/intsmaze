package org.intsmaze.groovy.util;

import java.util.Map;
import java.util.Map.Entry;

import groovy.lang.Binding;

import org.intsmaze.groovy.GroovyScriptExecutor;
import org.intsmaze.groovy.GroovyDemo;
import org.intsmaze.groovy.RuleCalc;
import org.junit.Test;

/**
 * @author:YangLiu
 * @date:2018年5月9日 上午10:03:06
 * @describe:
 */
public class ExecuteGroovy {

	private GroovyScriptExecutor scriptExecutor;
	
	public GroovyScriptExecutor getScriptExecutor() {
		return scriptExecutor;
	}


	public void setScriptExecutor(GroovyScriptExecutor scriptExecutor) {
		this.scriptExecutor = scriptExecutor;
	}


	public void executeGroovy(Map<String,Object> bindingMap,long ruleId)
	{
		Binding shellContext = new Binding();
		for (Entry<String, Object> entry : bindingMap.entrySet()) {
			shellContext.setVariable(entry.getKey(),entry.getValue());
		}

		// *********************配置脚本核心计算处*******************
		RuleCalc calc = new RuleCalc();
		calc.executeRuleCalc(scriptExecutor, shellContext,ruleId); // 核心计算
	}
	
	
	@Test
	public void test() {
		BeanFactory beanFactory = new BeanFactory("redis-base.xml");
		GroovyScriptExecutor scriptExecutor = beanFactory
				.getBean(GroovyScriptExecutor.class);

		Binding shellContext = new Binding();
		shellContext.setVariable("AGE", "100");
		shellContext.setVariable("GROOVY", new GroovyDemo());

		// *********************配置脚本核心计算处*******************
		RuleCalc calc = new RuleCalc();
		calc.executeRuleCalcAll(scriptExecutor, shellContext); // 核心计算
		// *********************配置脚本核心计算处*******************
		while (true) {

		}

		// Binding binding = new Binding();
		// GroovyShell shell = new GroovyShell(binding);
		// Script scrpt = shell.parse("script.groovy");
		//
		// binding.setVariable("str1", "value1");
		// binding.setVariable("str2", "value2");
		// binding.setVariable("newConcat", scrpt);
		// System.out
		// .println(scrpt.evaluate("newConcat.customConcat(str1, str2)"));
		// System.out.println(scrpt.evaluate("str1.concat(str2)"));
	}
}
