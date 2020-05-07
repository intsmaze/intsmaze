package cn.intsmaze.dump;
import javassist.ClassPool;
import javassist.CtClass;
/**
 * @author:YangLiu
 * @date:2018年1月28日 下午4:09:12
 * @describe:这里使用了javassist类，可以研究下这个包，很多地方都说使用了javassist字节码增强技术
 */
public class PermOOM {
	
	static class MyClassLoader extends ClassLoader{

	}

	static MyClassLoader c1=new MyClassLoader();

	// 检查持久代 方法区创建大量类出现内存溢出的情况
	// -XX:PermSize=2M -XX:MaxPermSize=4M -XX:+PrintGCDetails
	/**
	 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=1M; support was removed in 8.0
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=1M; support was removed in 8.0
Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "main"
	 */
	public static void main(String[] args) {
 		int i = 0;
		try {
			for (i = 0; i < Integer.MAX_VALUE; i++) {
				CtClass c = ClassPool.getDefault().makeClass("intsmaze" + i);
				c.setSuperclass(ClassPool.getDefault().get(
						"cn.intsmaze.dump.JavaBeanObject"));
				Class clz = c.toClass();// 新建类
				JavaBeanObject v = (JavaBeanObject) clz.newInstance();
				v.getName();
			}
		} catch (Exception e) {
			System.out.println("..............." + i);
			e.printStackTrace();
		}
	}
	
	
	
	public void notoom()
	{
		int i = 0;
		try {
			for (i = 0; i < Integer.MAX_VALUE; i++) {
				CtClass c = ClassPool.getDefault().makeClass("intsmaze" + i);
				c.setSuperclass(ClassPool.getDefault().get(
						"cn.intsmaze.jvm.JavaBeanObject"));
				Class clz = c.toClass(c1,null);// 新建类
				JavaBeanObject v = (JavaBeanObject) clz.newInstance();
				if(i%10==0)
				{
					c1=new MyClassLoader();
				}
				v.getName();
			}
		} catch (Exception e) {
			System.out.println("..............." + i);
			e.printStackTrace();
		}
	}
	static class MyCrashHandler implements Thread.UncaughtExceptionHandler {

		@Override
		public void uncaughtException(Thread t, Throwable e) {

			System.out.println("捕获到异常:" + e.getMessage());
			e.printStackTrace();
		}
	}
}
