package cn.intsmaze.proxy.cglib;

import java.util.ArrayList;
import java.util.List;
import cn.intsmaze.proxy.Interceptor;
import cn.intsmaze.proxy.LoggerInterceptor;
import cn.intsmaze.proxy.PrivilegeInterceptor;
import cn.intsmaze.proxy.SalaryServiceImpl;
import cn.intsmaze.proxy.SalaryServiceImplCopy;
import net.sf.cglib.core.DebuggingClassWriter;

public class SalaryTest {

	public static void main(String[] args)
	{
		new SalaryTest().test();
	}

	public void test(){
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "d:\\test");
		List<Interceptor> interceptors = new ArrayList<Interceptor>();
		interceptors.add(new LoggerInterceptor());
		interceptors.add(new PrivilegeInterceptor());
		
		CglibInterceptor interceptor = new CglibInterceptor(interceptors);
		
		//动态生成子类的方式创建代理类，这里返回的是SalaryServiceImpl类的一个新子类
		SalaryServiceImplCopy proxy = (SalaryServiceImplCopy)interceptor.createProxy(SalaryServiceImplCopy.class);
		System.out.println("---"+new SalaryServiceImplCopy());
		System.out.println("+++"+proxy);//这里也进入了invoke方法。 这里打null
		
		proxy.showSalary("123");
		
		
		
	}
}
