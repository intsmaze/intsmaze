package com.intsmaze.redis.model;

import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelPoolDemo {
	public static void main(String[] args) {
		String host = null;
		int port = 0;

		// 哨兵的集群
		Set<String> sentinels = new HashSet<String>();
		sentinels.add(new HostAndPort("192.168.19.131", 26379).toString());
		sentinels.add("192.168.19.131:26380");

		// 主节点挂了，不影响该实例从新加载，因为该实例加载的是哨兵的集群，如果哨兵的集群宕机了，也不会影响的。
		// 但是发现有bug就是，如果集群当中某一个哨兵宕机了，那么主节点再宕机，则无法选举出新的主节点，必须所以的哨兵都复活才可以。
		// 且，如果哨兵都宕机了，那么就会一直使用宕机前的主节点的ip进行操作。这就是为什么JedisSentinelPool不会因为集群的变化而需要重新实例化了。
		JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster",
				sentinels);// mymaster的名称一定要和集群的名称一样，不然无法连接上

		for (int i = 0; i < 100; i++) {
			try {
				// 无论读写都是主服务器，从服务器并没有接收读请求，没有进行负载，仅仅是备份。即当主节点宕机了，从节点补上。
				host = jedisSentinelPool.getCurrentHostMaster().getHost();
				port = jedisSentinelPool.getCurrentHostMaster().getPort();
				System.out.println(host + ":" + port);

				Jedis jedis = jedisSentinelPool.getResource();
				// jedis.set("001", "ASDFG");
				System.out.println(jedis.get("001"));
				jedis.close(); // 关闭Redis Master服务

				host = jedisSentinelPool.getCurrentHostMaster().getHost();
				port = jedisSentinelPool.getCurrentHostMaster().getPort();
				System.out.println(host + ":" + port);

				jedis = jedisSentinelPool.getResource();
				System.out.println(jedis.get("001"));
				jedis.close();
				Thread.sleep(8000);
			} catch (Exception e) {
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		jedisSentinelPool.close();
		jedisSentinelPool.destroy();
	}
}
