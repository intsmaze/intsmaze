package cn.intsmaze.proxy.customImpl.two;

import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler{
	
	private Object target;

	public TimeHandler(Object target) {
		super();
		this.target = target;
	}

	//这里模拟最简单的，增强的方法没有返回值的。
	@Override
	public void invoke(Object o, Method m) {
		long start = System.currentTimeMillis();
		System.out.println("starttime:" + start);
		System.out.println(o.getClass().getName());
		try {
			m.invoke(target);//要求用户在方法里面调用该代码。通过参数对象，来确定调用哪一个对象的哪一个方法。
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time:" + (end-start));
	}

}
