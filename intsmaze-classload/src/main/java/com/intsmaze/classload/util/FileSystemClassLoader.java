package com.intsmaze.classload.util;
import com.intsmaze.classload.service.CompileService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/** 
 * @author:YangLiu
 * @date:2018年8月9日 上午8:55:08 
 * @describe: 
 */
public class FileSystemClassLoader extends ClassLoader {

    private JedisPool jedisPool ;
 
    public  String rootDir;
 
//    public FileSystemClassLoader(String rootDir) {
//        this.rootDir = rootDir;
//    }

    /**
    * @Description: 从redis中获取指定类的字节码
    * @Param:类的完整名称
    * @return:
    * @Author: intsmaze
    * @Date: 2019/1/8
    */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        byte[] classData = getClassData(name);  // 获取类的字节数组
        byte[] classData =getClassDataFormRedis(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassDataFormRedis(String className) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get((CompileService.KEY_NAME+className).getBytes());

    }

    public  byte[] getClassData(String className) {
        // 读取类文件的字节
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            // 读取类文件的字节码
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public  String classNameToPath(String className) {
        // 得到类文件的完全路径
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }
}
