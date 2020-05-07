package cn.intsmaze.jbdc.mysql.customDataSource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
//使用jdk动态代理去实现
class MyConnectionHandler implements InvocationHandler {

	private Connection realConnection;
	private Connection warpedConnection;
	private MyDataSource2 dataSource;

	private int maxUseCount = 5;
	private int currentUserCount = 0;

	MyConnectionHandler(MyDataSource2 dataSource) {
		this.dataSource = dataSource;
	}

	Connection bind(Connection realConn) {
		this.realConnection = realConn;
		this.warpedConnection = (Connection) Proxy.newProxyInstance(this
				.getClass().getClassLoader(), new Class[] { Connection.class },
				this);
		return warpedConnection;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if ("close".equals(method.getName())) {
			this.currentUserCount++;
			if (this.currentUserCount < this.maxUseCount) {
                this.dataSource.connectionsPool.addLast(this.warpedConnection);
            } else {
				this.realConnection.close();
				this.dataSource.currentCount--;
			}
		}
		return method.invoke(this.realConnection, args);
	}

}
