package cn.intsmaze.io.bio.pool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.ResourceBundle;

public class MyConnectionProvider implements ConnectionProvider {

	private Properties pro = null;
	private static ConnectionProvider provider = null;
	private static Object object_lock = new Object();
	private static Object[] object_lock_list = new Object[Integer
			.parseInt(ResourceBundle.getBundle("mailsocket").getString(
					"max_size"))];
	private String ip;
	private String port;
	private static InetSocketAddress inetSockAddr = null;

	/**
	 * 默认的最大连接数
	 */
	private int max_size = 20;

	/**
	 * 默认的最小连接数 。。。没用
	 */
	private int min_size = 10;

	/**
	 * Socket connection
	 */
	private SocketAdapter[] socketpool = null;

	/**
	 * 构造对象的时候初始化连接池
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private MyConnectionProvider() throws UnknownHostException, IOException {
		ResourceBundle res = ResourceBundle.getBundle("mailsocket");
		ip = res.getString(SERVER_IP);
		port = res.getString(SERVER_PORT);
		String max_size_s = res.getString(MAX_SIZE);
		String min_size_s = res.getString(MIN_SIZE);
		if (max_size_s != null) {
			max_size = Integer.parseInt(max_size_s);
		}
		if (min_size_s != null) {
			min_size = Integer.parseInt(min_size_s);
		}
		init(); // 构造对象的时候初始化连接池
	}

	/**
	 * 判断是否已经池化
	 * 
	 * @return boolean 如果池化返回ture,反之返回false
	 */
	@Override
	public boolean isPooled() {
		if (socketpool != null) {
			return true;
		} else {
            return false;
        }
	}

	/**
	 * 返回一个连接
	 * 
	 * @return a Connection object.
	 * @throws IOException
	 */
	@Override
	public SocketAdapter getConnection() {
		SocketAdapter s = null;
		// System.out.println("loop");
		for (int i = 0; i < socketpool.length; i++) {
			// System.out.println("loop detail i="+i);
			if (socketpool[i] != null) {
				// 如果有空闲的连接，返回一个空闲连接，如果没有，继续循环

				if (!socketpool[i].isFree()) {
					continue;
				} else {
					synchronized (object_lock_list[i]) {
						if (socketpool[i].isFree()) {
							s = socketpool[i];

							// System.out.println(i+" socket isConnected()="+s.isConnected()+" binding"+s.isBound());
							try {
								s = socketpool[i] = new SocketAdapter(ip,
										Integer.parseInt(port));
								socketpool[i].setBusy();
								// System.out.println("socket"+i);
							} catch (IOException e) {

								e.printStackTrace();
								return null;
							}
							return s;
						} else {
							continue;
						}
					}
				}

			} else { // 如果连接为空，证明超过最小连接数，重新生成连接

				try {
					System.out.println("new socket" + i);
					s = socketpool[i] = new SocketAdapter(ip,
							Integer.parseInt(port));
					return s;
				} catch (Exception e) {
					// never throw
					return null;
				}
			}
		}
		return s;
	}

	/**
	 * 初始化连接池
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@Override
	public void init() throws UnknownHostException, IOException {

		socketpool = new SocketAdapter[max_size];

		for (int i = 0; i < max_size; i++) {
			object_lock_list[i] = new Object();
		}

		System.out.println("System init success ....");
	}

	/**
	 * 重新启动连接池
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@Override
	public void restart() throws UnknownHostException, IOException {
		destroy();
		init();
	}

	/**
	 * 注销此连接池
	 * 
	 * @throws IOException
	 */
	@Override
	public void destroy() throws IOException {
		for (int i = 0; i < socketpool.length; i++) {
			if (socketpool[i] != null) {
				SocketAdapter adapter = (SocketAdapter) socketpool[i];
				adapter.destroy();
				// System.out.print("" );
			}
		}
		System.out.println("\ndestory success ....");
	}

	/**
	 * 生成此连接池实现的对象
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 * @return ConnectionProvider
	 */
	public static ConnectionProvider newInstance() throws UnknownHostException,
			IOException {
		if (provider == null) {
			synchronized (object_lock) {
				if (provider == null) {
					provider = new MyConnectionProvider();
				}
			}
		}
		return provider;
	}

	/**
	 * 读取properties文件。
	 * 
	 * @param key
	 * @param properName
	 * @return
	 */
	public static String readProperties(String key, String properName) {
		ResourceBundle rsrc = null;
		String value = "";
		rsrc = ResourceBundle.getBundle(properName);
		value = rsrc.getString(key);
		return value;

	}
}