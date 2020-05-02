package com.intsmaze.classload.service;

import com.intsmaze.classload.util.FileSystemClassLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

/**
 * @author:YangLiu
 * @date:2018年8月7日 下午4:38:39
 * @describe: 该类负责将编写的java类编译为class文件后加载进redis中
 */
public class CompileService {

	private JedisPool jedisPool ;

	FileSystemClassLoader fileSystemClassLoader;

	public static final String CLASS_PATH ="D:/home/intsmaze/classload";
	
//	public static final String CLASS_PATH="./bin";
	
	public static final String CLASS_NAME ="ClassLoaderOdps";

    public static final String KEY_NAME ="classload:";

    /**
    * @Description:  将编写的java类动态编译为class文件后，加载进redis中
    * @Author: intsmaze
    * @Date: 2019/1/8
    */
	public static void main(String[] args) throws Exception {

		ApplicationContext ct = new ClassPathXmlApplicationContext("spring-classload.xml");

		 String classContent = "package org.intsmaze.classload.service.impl;"
		 		+ "import org.intsmaze.classload.service.DynamicService; "
					+ "public class "+ CLASS_NAME +" implements DynamicService{"
					+ "public void executeService(String json) {"
					+ "System.out.println(\"我经过了redis后得以执行，看我72变\"" + ");}}";

		CompileService compileService=(CompileService)ct.getBean("compileService");
        
		compileService.executeCompile(classContent, CLASS_NAME,"org.intsmaze.classload.service.impl."+ CLASS_NAME);

		 
	}

	/**
	* @Description: 该java类在redis中key名称为"classload:"+packageName，在本地生成的class文件位于CLASS_PATH
	* @Param: 
	* @return: 
	* @Author: intsmaze
	* @Date: 2019/1/8
	*/ 
	public  boolean executeCompile(String fileSource, String className,String packageName) {
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		JavaFileObject fileObject = new JavaStringObject(className, fileSource);
		// 编译过程
		CompilationTask task = javaCompiler.getTask(null, null, null,
				Arrays.asList("-d", CLASS_PATH), null,
				Arrays.asList(fileObject));
		if (!task.call()) {
			System.out.println("编译失败!");
			return false;
		} else {
			System.out.println("编译成功！");
			loadClassRedis(packageName);
			return true;
		}
	}


	static class JavaStringObject extends SimpleJavaFileObject {
		private String code;

		public JavaStringObject(String name, String code) {
			super(URI.create(name + ".java"), Kind.SOURCE);
			this.code = code;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors)
				throws IOException {
			return code;
		}
	}

	public  boolean loadClassRedis(String packageName)
	{
//		FileSystemClassLoader fscl = new FileSystemClassLoader(
//				CompileService.CLASS_PATH);
		fileSystemClassLoader.setRootDir(CompileService.CLASS_PATH);

		byte[] classData =fileSystemClassLoader.getClassData(packageName);
		Jedis jedis = jedisPool.getResource();
		jedis.set((KEY_NAME+packageName).getBytes(),classData);
		return true;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public FileSystemClassLoader getFileSystemClassLoader() {
		return fileSystemClassLoader;
	}

	public void setFileSystemClassLoader(FileSystemClassLoader fileSystemClassLoader) {
		this.fileSystemClassLoader = fileSystemClassLoader;
	}
}
