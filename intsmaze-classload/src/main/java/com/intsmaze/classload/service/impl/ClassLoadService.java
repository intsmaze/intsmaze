package com.intsmaze.classload.service.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.intsmaze.classload.service.CompileJavaFileToRedisTemplate;
import com.intsmaze.classload.service.DynamicService;
import com.intsmaze.classload.util.FileSystemClassLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/6/6 18:37
 */
public class ClassLoadService {

    private FileSystemClassLoader fileSystemClassLoader;

    public void init() {
        try {
            fileSystemClassLoader.setRootDir(CompileJavaFileToRedisTemplate.CLASS_PATH);
            String[] arr = this.getClassNames();
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
     * @return
     * @throws
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * 模拟从数据库中读取所有要加载的类的全路径
     * @date : 2020/6/6 18:37
     * @Param
     */
    public String[] getClassNames() {
        String[] classNameArr = {
                "com.intsmaze.classload.service.impl.ClassLoaderOdps"};
        return classNameArr;
    }

    public FileSystemClassLoader getFileSystemClassLoader() {
        return fileSystemClassLoader;
    }

    public void setFileSystemClassLoader(FileSystemClassLoader fileSystemClassLoader) {
        this.fileSystemClassLoader = fileSystemClassLoader;
    }

    public static void main(String[] args)  {
        ApplicationContext ct = new ClassPathXmlApplicationContext("spring-classload.xml");
    }

}
