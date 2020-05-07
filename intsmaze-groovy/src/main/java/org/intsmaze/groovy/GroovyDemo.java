package org.intsmaze.groovy;

import java.io.Serializable;

/** 
 * @author:YangLiu
 * @date:2018年5月9日 下午2:15:38 
 * @describe: 
 */
public class GroovyDemo implements Serializable {

	public String getAge(String age)
	{
		return "ai you wei:"+age;
	}
	
	public String getSex(String sex)
	{
		return "-----------------:"+sex;
	}
}
