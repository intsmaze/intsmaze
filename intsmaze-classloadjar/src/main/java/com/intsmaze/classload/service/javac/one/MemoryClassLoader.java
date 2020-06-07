package com.intsmaze.classload.service.javac.one;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:YangLiu
 * @date:2018年3月18日 下午2:20:09
 * @describe:
 */
public class MemoryClassLoader extends URLClassLoader {
	Map<String, byte[]> classBytes = new HashMap<String, byte[]>();

	public MemoryClassLoader(Map<String, byte[]> classBytes) {
		super(new URL[0], MemoryClassLoader.class.getClassLoader());
		this.classBytes.putAll(classBytes);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] buf = classBytes.get(name);
		if (buf == null) {
			return super.findClass(name);
		}
		classBytes.remove(name);
		return defineClass(name, buf, 0, buf.length);
	}
}
