package cn.intsmaze.proxy.customImpl.two;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

public class Proxy {
	//参数2的作用：调用别人指定给我的处理方式
	public static Object newProxyInstance(Class infce, InvocationHandler h) throws Exception { //JDK6 Complier API, CGLib, ASM
		String methodStr = "";
		String rt = "\r\n";
		
		Method[] methods = infce.getMethods();
		//进行思考，我们如果要是实现一个代理给其他开发者使用，不应该要求开发者把增强的代码片段写在这里，我们应该提供一个接口，开发者实现该接口，
		//然后在该接口中提供增强方法即可。
		for(Method m : methods) {
			methodStr += "@Override" + rt + 
						 "public "+m.getReturnType()+" "+ m.getName() + "() {" + rt +
						 "    try {" + rt +
						 "    Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt +
						 "    h.invoke(this, md);" + rt +
						 "    }catch(Exception e) {e.printStackTrace();}" + rt +
						 "}";
		}
		
		String src = 
			"package cn.intsmaze.analyse;" +  rt +
			"import cn.intsmaze.three.InvocationHandler;" +  rt +
			"import java.lang.reflect.Method;" + rt +
			"public class $Proxy1 implements " + infce.getName() + "{" + rt +
			"    public $Proxy1(InvocationHandler h) {" + rt +//参数类型
			"        this.h = h;" + rt +
			"    }" + rt +
			"    cn.intsmaze.three.InvocationHandler h;" + rt +//参数类型
			methodStr +
			"}";
		
		String fileName = 
			"d:/src/cn/intsmaze/$Proxy1.java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write(src);
		fw.flush();
		fw.close();
		
		//compile
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
		Iterable units = fileMgr.getJavaFileObjects(fileName);
		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
		t.call();
		fileMgr.close();
		
		//load into memory and create an instance
		URL[] urls = new URL[] {new URL("file:/" + "d:/src/")};//这个地方是防止IDE生成的class和我们动态生成class冲突，所以把文件写到其他目录，其实通过打成可执行jar包就没有这个问题了。
		URLClassLoader ul = new URLClassLoader(urls);
		Class c = ul.loadClass("cn.intsmaze.$Proxy1");
		
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		Object m = ctr.newInstance(h);
		return m;
	}
}

