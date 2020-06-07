package com.intsmaze.classload.service.classloader;

public class ClassLoaderTest {
	
	public static void main(String[] args) throws Exception {
		excute();
	}
	static void excute() throws Exception{
		ClassLoader myLoader = new MyClassLoader();
		Object obj = myLoader.loadClass("java.lang.String").newInstance();
		/**自己定义的类加载器是没有权限加载java.lang下面的类的。
		 * 类加载使用了委托机制，我们自定义的类加载器加载类的时候，应该会委托到上一层了。
		 * 这里主要原因是，这个类加载器，是有些问题的。它太暴力了，直接在loadClass()这个方法强制加载类了。
		 * Jdk1.2之后已不提倡用户再去覆盖loadClass()方法了，而应当把自己的类加载逻辑写到findClass()方法中，
		 * 在loadClass()方法的逻辑里如果父类加载失败，则会调用自己的findClass()方法来完成加载，这样就可以保证新写出来的类加载器是符合委托机制的。*/
	}
    public void printMsg(){
    	System.out.println("测试");
    }
}
