package cn.intsmaze.proxy;

public class PrivilegeInterceptor implements Interceptor {

	@Override
	public void before() {
		System.out.println("start 校验权限");
	}

	@Override
	public void after(String str) {
		System.out.println("edn 校验权限");
	}

}
