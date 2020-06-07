package com.intsmaze.classload.service.javac;//package cn.intsmaze.javac;
///** 
// * @author:YangLiu
// * @date:2018年3月18日 下午1:46:00 
// * @describe: 
// */
//
//import java.io.*;  
//import java.lang.reflect.Method;  
//  
///** 
// * Created by rns on 17-1-7. 
// */  
//public class DynamicCompiler {  
//    public static void main(String[] args) throws IOException {  
//        //待编译的源代码放置的文件夹路径  
//        String basedir = "c:/javac/";  
//        //待编译的类名称，不包含.java  
//        String classname = "PersonAction";  
//        //执行代码的路径，下面的路径是本人的idea编译后输出路径  
//        String executedir = "c:/";  
//        //创建编译器  
//        com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();  
//        //设置编译命令参数，与使用javac命令后面的参数一样  
//        String[] params = new String[] {  
//                "-d",  
//                basedir,basedir+"cn/intsmaze/javac/"+classname+".java",  
//                "-verbose"  
//        };  
//        int status = javac.compile(params);  
//        //当编译返回值为0时成功  
//        if(status == 0)  
//            System.out.println("compiled successfully!");  
//        else  
//            System.out.println("errors occurs");  
//        //部署编译好的class到执行目录  
////        copyTo(basedir+"cn/intsmaze/javac/"+classname+".class",executedir+classname+".class");  
//        //加载class字节码并实例化,再调用相应方法  
//        invoke(classname,"say",new Class[]{String.class},new String[]{"Hello"});  
//    }  
//  
//    /** 
//     * 实例化并调用相应方法 
//     * @param classname 类名 
//     * @param methodname 方法名 
//     * @param paramType 方法参数类型 
//     * @param paramValues 方法参数值 
//     */  
//    public static void invoke(String classname, String methodname,  
//                              Class[] paramType, Object[] paramValues){  
//        try {  
//            Class cls = Class.forName(classname);  
//            // 方式一、不转化为具体类型，  
//            // 利用反射创建一个Method实例，继而实现方法调用  
//            Method method = cls.getMethod(methodname, paramType);  
//            method.invoke(cls.newInstance(),paramValues);  
//  
//            // 方式二、转化为具体类型(需要设计相应接口),  
//            // 反射实例化后强制转换为接口类型，再进行方法调用  
//            Action person = (Action) cls.newInstance();  
//            person.say(paramValues[0].toString());  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    }  
//  
//    /** 
//     * 复制文件到指定目录 
//     * @param from 源文件 
//     * @param to 目的文件 
//     * @throws IOException 
//     */  
//    public static void copyTo(String from,String to) throws IOException {  
//        FileInputStream fi = new FileInputStream(from);  
//        FileOutputStream fo = new FileOutputStream(to);  
//        File df = new File(to);  
//        if(!df.exists())  
//            df.createNewFile();  
//        for(int read = fi.read(); read !=-1; read=fi.read()){  
//            fo.write(read);  
//        }  
//        fo.close();  
//        fi.close();  
//    }  
//}  
