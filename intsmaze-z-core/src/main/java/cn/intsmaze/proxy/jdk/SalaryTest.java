package cn.intsmaze.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import cn.intsmaze.proxy.Interceptor;
import cn.intsmaze.proxy.LoggerInterceptor;
import cn.intsmaze.proxy.PrivilegeInterceptor;
import cn.intsmaze.proxy.SalaryService;
import cn.intsmaze.proxy.SalaryServiceImpl;
import sun.misc.ProxyGenerator;

/**
 * 
 * @author YangLiu
 * @date 2018年5月6日
 * @version 1.0 jdk的动态代理是通过反射进行切入的。
 */
public class SalaryTest {

	public static void main(String[] args)
	{
		// 从源码中得知，设置这个值，可以把生成的代理类，输出出来。
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

		new SalaryTest().test();
	}

	public void test() {

		Object target = new SalaryServiceImpl();

		List<Interceptor> interceptors = new ArrayList<Interceptor>();
		interceptors.add(new LoggerInterceptor());
		interceptors.add(new PrivilegeInterceptor());

		SalaryInterceptor interceptor = new SalaryInterceptor(target,interceptors);

		// 这一步是核心，封装到createProxy方法里面
		// SalaryService salaryManager =
		// (SalaryService)Proxy.newProxyInstance(target.getClass().getClassLoader(),
		// target.getClass().getInterfaces(), interceptor);
		SalaryService salaryProxy = (SalaryService) interceptor.createProxy();//这个其实就是代理对象咯,并不是SalaryServiceImpl对象
		System.out.println("---"+new SalaryServiceImpl());
		System.out.println("+++"+salaryProxy);//这里也进入了invoke方法。
		// 调用的是代理类实例
		System.out.println(salaryProxy.showSalary("1231"));
		salaryProxy.useSalary();

		final List list = new ArrayList();

		// 这是被代理的 public final class $Proxy1 extends Proxy implements List, RandomAccess, Cloneable, Serializable
		Object oo = Proxy.newProxyInstance(List.class.getClassLoader(), list
				.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				System.err.println("加入一个对象：");
				Object returnValue = method.invoke(list, args);// 反射
				System.err.println("加入完成。。。。");
				if (method.getName().equals("size")) {
					return 100;
				}
				return returnValue;
			}
		});

		List list2 = (List) oo;
		boolean add = list2.add("aaa");
		boolean add2 = list2.add("bbb");
		System.out.println("aaa add:" + add);
		System.out.println("bbb add:" + add2);

		System.err.println("size:" + list2.size() + "," + list.size());// 100,2
	}
}
