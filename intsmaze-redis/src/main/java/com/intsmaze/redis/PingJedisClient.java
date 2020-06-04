package com.intsmaze.redis;

import redis.clients.jedis.Jedis;
import java.util.Set;

public class PingJedisClient {
	
	
	public static void main(String[] args) {
		
		//创建一个jedis客户端对象（redis的客户端连接）
		Jedis client = new Jedis("127.0.0.1", 6379);
		
		//测试服务器是否连通
		String resp = client.ping();

		System.out.println(resp);

	}
}
