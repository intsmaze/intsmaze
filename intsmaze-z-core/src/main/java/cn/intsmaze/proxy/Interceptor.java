package cn.intsmaze.proxy;

public interface Interceptor {

	public void before();

	public void after(String str);
}
