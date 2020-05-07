package cn.intsmaze.jmx;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @author:YangLiu
 * @date:2017年12月10日 下午6:21:28
 * @describe:
 */
public class HelloAgent {
	public static void main(String[] args) throws Exception {
		// create mbean server
		// 首先建立一个MBeanServer，MBeanServer用来管理我们的MBean，通常是通过MBeanServer来获取我们MBean的信息
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();

		// create object name
		// 为MBean（下面的new Hello()）创建ObjectName实例
		//参数必须以jmxBean:name=开头
		ObjectName objectName = new ObjectName("jmxBean:name=hello");
		// create mbean and register mbean
		// 将new Hello()这个对象注册到MBeanServer上去
		server.registerMBean(new Hello(), objectName);

		/**
		 * JMXConnectorServer service
		 */
		// 这句话非常重要，不能缺少！注册一个端口，绑定url后，客户端就可以使用rmi通过url方式来连接JMXConnectorServer
		Registry registry = LocateRegistry.createRegistry(1099);

		// 构造JMXServiceURL
		JMXServiceURL jmxServiceURL = new JMXServiceURL(
				"service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
		// 创建JMXConnectorServer
		JMXConnectorServer cs = JMXConnectorServerFactory
				.newJMXConnectorServer(jmxServiceURL, null, server);
		// 启动
		cs.start();
	}
}
