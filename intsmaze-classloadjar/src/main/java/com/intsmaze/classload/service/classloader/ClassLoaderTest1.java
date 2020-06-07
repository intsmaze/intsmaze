package com.intsmaze.classload.service.classloader;



/**通过运行结果我们可以看到，MyClassLoader除了加载ClassLoaderTest还会在加载相关的类。但是使用自定义的加载器加载并实例化的对象做类型检查返回false，也不能调用所属的方法。

这是因为，系统中存在了两个ClassLoaderTest，一个是MyClassLoader加载的，一个是AppClassLoader加载的。不同的类加载器加载的类，不相等。*/
public class ClassLoaderTest1 {
	
	public static void main(String[] args) throws Exception {
		excute1();
	}
	static void excute1() throws Exception{
		ClassLoader myLoader = new MyClassLoader1();
		Object obj = myLoader.loadClass("cn.intsmaze.Aliyun.ClassLoaderTest").newInstance();
		System.out.println(obj instanceof com.intsmaze.classload.service.classloader.ClassLoaderTest1);
		((ClassLoaderTest1) obj).printMsg();//不能调用所属的方法
	}
    public void printMsg(){
    	System.out.println("测试");
    }
}
