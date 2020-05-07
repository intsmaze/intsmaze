package cn.intsmaze.proxy;

public class LoggerInterceptor implements Interceptor {

	@Override
	public void before() {
		System.out.println("i will start logging");
	}

	@Override
    public void after(String str) {
		System.out.println("logging end");
	}

}
