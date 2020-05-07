package cn.intsmaze.io.bio.pool;

import java.io.IOException;
import java.net.UnknownHostException;

public interface ConnectionProvider {
	public static final String SERVER_IP = "host";
	public static final String SERVER_PORT = "port";
	public static final String MAX_SIZE = "max_size";
	public static final String MIN_SIZE = "min_size";

	/**
	 * 判断连接池内是否有连接
	 * 
	 * @return true 有连接返回true,否则返回false
	 */
	public boolean isPooled();

	/**
	 * 当此方法被调用的时候提供一个 socket
	 * 
	 * @see Socket
	 * @return Socket a Connection object.
	 */
	public SocketAdapter getConnection() throws java.net.SocketException;

	/**
	 * 连接池初始化
	 */
	public void init() throws UnknownHostException, IOException;

	/**
	 * 连接池重新启动
	 */
	public void restart() throws UnknownHostException, IOException;

	/**
	 * 注销连接池
	 */
	public void destroy() throws IOException;
}
