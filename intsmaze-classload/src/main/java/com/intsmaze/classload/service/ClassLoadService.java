package com.intsmaze.classload.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.intsmaze.classload.util.FileSystemClassLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:YangLiu
 * @date:2018年8月7日 下午4:20:12
 * @describe:
 */
public class ClassLoadService {

	FileSystemClassLoader fileSystemClassLoader;

	public void init() {
		try {
			fileSystemClassLoader.setRootDir(CompileService.CLASS_PATH);
            String[] arr = this.getClassName();
			List<String> list = Arrays.asList(arr);
			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				String className = it.next();
				Class<?> clazz = fileSystemClassLoader.loadClass(className);
				DynamicService obj = (DynamicService) clazz.newInstance();
				obj.executeService("123");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author:YangLiu
	 * @date:2018年8月8日 上午10:48:11
	 * @describe:模拟从数据库中读取所有要加载的类的全路径
	 */
	public String[] getClassName() {
        String[] arr = {
                "org.intsmaze.classload.service.impl.ClassLoaderOdps"};
		return arr;
	}

	public FileSystemClassLoader getFileSystemClassLoader() {
		return fileSystemClassLoader;
	}

	public void setFileSystemClassLoader(FileSystemClassLoader fileSystemClassLoader) {
		this.fileSystemClassLoader = fileSystemClassLoader;
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext ct = new ClassPathXmlApplicationContext("spring-classload.xml");
	}


}
