package cn.intsmaze.proxy.customImpl.one;

import cn.intsmaze.proxy.customImpl.Moveable;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;


public class Proxy {

	/**
	 * 通过参数class我们可以实现对任意的接口生成一个实现类。，增加一个参数类型为Object,然后把被代理对象作为参数传进去。
	 */
	public static Object newProxyInstance(Class infce, Object object)
			throws Exception { 
		String methodStr = "";
		String rt = "\r\n";
		
		// 通过反射获取接口的所有方法。
		Method[] methods = infce.getMethods();
		
		//自动对重写每个方法，添加自己的业务逻辑
		for (Method m : methods) {
			methodStr += "@Override" + rt 
					+ "public " + m.getReturnType() + " "+ m.getName() + "() {" + rt
					+ "   long start = System.currentTimeMillis();" + rt
					+ "   System.out.println(\"starttime:\" + start);" + rt
					+ "   t." + m.getName() + "();" + rt
					+ "   long end = System.currentTimeMillis();" + rt
					+ "   System.out.println(\"time:\" + (end-start));" + rt
					+ "}";
		}

		String javaSrc = "package cn.intsmaze.analyse;" + rt
				+"import cn.intsmaze.analyse.Moveable;" + rt
				+ "public class TankTimeProxy implements Moveable {" + rt
				+ "    public TankTimeProxy(Moveable t) {" + rt
				+ "        super();" + rt 
				+ "        this.t = t;" + rt
				+ "    }" + rt 
				+ "    Moveable t;" + rt 
				+ methodStr 
				+ "}";
		
		
		String fileName = System.getProperty("user.dir")
				+ "/src/cn/intsmaze/analyse/TankTimeProxy.java";
		
		
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write(javaSrc);
		fw.flush();
		fw.close();

		// compile
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null,
				null, null);
		Iterable units = fileMgr.getJavaFileObjects(fileName);
		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null,units);
		t.call();
		fileMgr.close();

		// load into memory and create an instance
		URL[] urls = new URL[] { new URL("file:/"
				+ System.getProperty("user.dir") + "/src") };
		URLClassLoader ul = new URLClassLoader(urls);
		Class c = ul.loadClass("cn.intsmaze.analyse.TankTimeProxy");

		Constructor ctr = c.getConstructor(infce);
		// *************************************************************
		Moveable m = (Moveable) ctr.newInstance(object);
		// 这个地方要进行改变，不能写的太死。这个地方是调用我们生成的代理类的构造方法，里面是写死的参数
		return m;
	}
}
