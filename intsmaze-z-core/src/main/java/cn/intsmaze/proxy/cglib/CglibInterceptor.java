package cn.intsmaze.proxy.cglib;

import java.lang.reflect.Method;
import java.util.List;
import cn.intsmaze.proxy.Interceptor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
* @author YangLiu
* @date 2018年5月6日
* @version 1.0
* jdk创建代理只能为接口创建代理实例，对于没有通过接口定义业务方法的类，如何动态创建代理实例？这就需要用CGLib技术。
* CGLIB采用底层的字节码技术，为一个类创建一个子类，在子类中采用方法拦截的技术来拦截所有父类方法的调用并顺势织入横切逻辑。
CGLIB动态创建子类（动态生成一个新的类）的方式生成代理对象，所以不能对目标类中的final或private方法进行代理
 Spring已经将CGLib开发类引入spring-core-3.2.0.RELEASE.jar
 */
public class CglibInterceptor implements MethodInterceptor{
	// 使用CGLIB生成代理:
	// 1.创建核心类:
	Enhancer enhancer = new Enhancer();
	
	private List<Interceptor> interceptors;
	
	public CglibInterceptor(List<Interceptor> interceptors){
		this.interceptors = interceptors;
	}
	
	//为一个类创建动态代理对象，该对象通过扩展clazz实现代理。
	public Object createProxy(Class clazz){
		enhancer.setSuperclass(clazz);//设置需要创建的子类的类
		enhancer.setCallback(this);
		return enhancer.create();//通过字节码技术动态创建子类实例
	}

	
	@Override
	//拦截父类所有方法的调用，obj CGlib根据指定父类生成的代理对象，method为拦截的方法，args为拦截方法的参数数组，
	//为方法的代理对象，用于执行父类的方法 
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		for(Interceptor interceptor:interceptors){
			interceptor.before();
		}
		//输出代理类的名称
		System.out.println(obj.getClass().getName()+"."+method.getName());
		
		proxy.invokeSuper(obj, args);//通过代理类调用父类中的方法
		
		for(Interceptor interceptor:interceptors){
			interceptor.after("123");
		}
		
		return null;
	}
	

}
