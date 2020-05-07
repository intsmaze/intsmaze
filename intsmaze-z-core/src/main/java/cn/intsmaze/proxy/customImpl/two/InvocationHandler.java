package cn.intsmaze.proxy.customImpl.two;

import java.lang.reflect.Method;

//定义一个接口，用户实现该接口的方法。
public interface InvocationHandler {
	public void invoke(Object o, Method m);
}
