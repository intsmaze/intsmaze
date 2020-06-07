package com.intsmaze.classload.service.classloader;

import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader  extends ClassLoader {
	
	//现在我们在测试一下之前的代码，发现都能正常的通过了，其实是这些类委托给了AppClassLoader。
	protected Class<?> findClass(String name)throws ClassNotFoundException {
		InputStream is = null;
		try {
			is = new FileInputStream(name);
			byte[] b = new byte[is.available()];
			is.read(b);
			return defineClass(b,0,b.length);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				is.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return super.findClass(name);
	}
	
//	public Class<?> loadClass(String name) throws ClassNotFoundException{
//		System.out.println("name："+name);
//		try{
//			String fileName = "/"+name.replace(".", "/")+".class";
//			InputStream is = getClass().getResourceAsStream(fileName);
//			if(is == null){
//				return super.loadClass(name);
//			}
//			byte[] b = new byte[is.available()];
//			is.read(b);
//			return defineClass(name, b, 0, b.length);
//		}catch(IOException e){
//			throw new ClassNotFoundException();
//		}
//	}
}
