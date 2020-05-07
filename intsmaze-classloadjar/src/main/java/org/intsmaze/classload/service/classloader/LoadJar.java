package org.intsmaze.classload.service.classloader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import cn.intsmaze.classloader.bean.HelloIface;
import cn.intsmaze.classloader.bean.HelloImpl;


public class LoadJar {
	public static void main(String[] args) {
		
		String classPath = "loader.cn.intsmaze.classloader.bean.HelloImpl";// Jar中的所需要加载的类的类名
		String jarPath = "file:///c:/ClassLoad.jar";// jar所在的文件的URL

		loadJar1(classPath, jarPath);
//		loadClass(classPath);
//		loadJar2(classPath, jarPath);
//		loadClass(classPath);
	}

	public static void loadJar1(String classPath, String jarPath) {
		ClassLoader cl;
		try {
			// 从Jar文件得到一个Class加载器
			cl = new URLClassLoader(new URL[] { new URL(jarPath) });
			// 从加载器中加载Class
			Class<?> c = cl.loadClass(classPath);
			// 从Class中实例出一个对象
			HelloImpl impl = (HelloImpl) c.newInstance();
			// 调用Jar中的类方法
			System.out.println(impl.hello());
			System.out.println(impl.sayHi("zhangsan"));
			System.out.println(HelloImpl.timeOut);
			
			try {
				HelloIface impl2 = (HelloIface) Class.forName(classPath)
						.newInstance();
				System.out.println(impl2.hello());
			} catch (ClassNotFoundException e) {
				System.out.println("非系统加载器加载的JAR,不能通过Class.forName使用");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadJar2(String classPath, String jarPath) {
		URLClassLoader urlLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		Class<URLClassLoader> sysClass = URLClassLoader.class;
		try {
			//改变方法的可见性（即通过反映访问本来不可以访问的方法）
			Method method = sysClass.getDeclaredMethod("addURL", new Class[] { URL.class });
			method.setAccessible(true);
			method.invoke(urlLoader, new URL(jarPath));
			
			Class<?> objClass = urlLoader.loadClass(classPath);
			Object instance = objClass.newInstance();
			Method method2 = objClass.getDeclaredMethod("sayHi", new Class[]{ String.class});
			System.out.println(method2.invoke(instance, "zhangsan"));
			
			HelloIface impl2 = (HelloIface) Class.forName(classPath).newInstance();
			System.out.println(impl2.hello());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadClass(String classPath){
		try {
			HelloIface impl2 = (HelloIface) Class.forName(classPath)
					.newInstance();
			System.out.println(impl2.hello());
		} catch (Exception e) {
			System.out.println("非系统加载器加载的JAR,不能通过Class.forName使用");
		}
	}
}
