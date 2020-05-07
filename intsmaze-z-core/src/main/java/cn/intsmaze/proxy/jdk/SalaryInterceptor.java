package cn.intsmaze.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import cn.intsmaze.proxy.Interceptor;

/**
 * @author YangLiu
 * @date 2018年5月6日
 * @version 1.0 jdk动态代理设计reflect包中两个类:proxy和InvocationHandler。
 *          InvocationHandler是一个接口
 *          ，通过实现该接口定义横切逻辑，并通过反射机制调用目标类的代码，动态的将横切逻辑和业务编织在一起。
 *          proxy利用InvocationHandler动态创建一个符合某一接口的实例，生成目标类的代理对象,就是生成一个新的类
 */
public class SalaryInterceptor implements InvocationHandler {

	private Object target;// 目标业务类

	private List<Interceptor> interceptors;

	public SalaryInterceptor(Object target, List<Interceptor> interceptors) {
		this.target = target;
		this.interceptors = interceptors;
	}

	public Object createProxy() {
		// 参数一:类加载器
		// 参数二:需要代理实例实现的接口列表
		// 参数三:业务逻辑和横切逻辑的编织对象
		Object proxy = Proxy.newProxyInstance(target.getClass()
				.getClassLoader(), target.getClass().getInterfaces(), this);
		return proxy;
	}

	@Override
	// proxy是最终生成的代理实例，一般不会用到，method是被代理目标实例的某个具体方法，通过它可以发起实例方法的反射调用；
	// args是被代理实例某个方法的入参，在方法反射调用时使用
	// 调用目标对象的任何一个方法 都相当于调用invoke();
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		for (Interceptor interceptor : interceptors) {
			interceptor.before();
		}

		System.out.println("target对象:"+target.getClass().getName() + "." + method.getName());

		if (args != null)// args是方法的传入实参
		{
			for (Object object : args) {
				System.out.println("....参数......." + object);
			}
		}

		// useSalary方法前后都增强
		if ("useSalary".equals(method.getName())) {
			// 记录日志:
			Object object = method.invoke(this.target, args);//反射调用目标业务类的方法
			for (Interceptor interceptor : interceptors) {
				interceptor.after("111");
			}
			return object;
		} else {
			Object object = method.invoke(this.target, args);// 反射调用目标业务类的方法
			return object;
		}
	}

}
