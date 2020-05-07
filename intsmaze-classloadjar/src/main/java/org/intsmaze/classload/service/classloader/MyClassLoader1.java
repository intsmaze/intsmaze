package org.intsmaze.classload.service.classloader;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader1  extends ClassLoader {
	public Class<?> loadClass(String name) throws ClassNotFoundException{
		System.out.println("name："+name);
		try{
			String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
			
			InputStream is = getClass().getResourceAsStream(fileName);
			if(is == null){
				return super.loadClass(name);
			}
			byte[] b = new byte[is.available()];
			is.read(b);
			return defineClass(name, b, 0, b.length);
		}catch(IOException e){
			throw new ClassNotFoundException();
		}
	}
}
