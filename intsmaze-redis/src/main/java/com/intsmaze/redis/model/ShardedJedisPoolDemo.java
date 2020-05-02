package com.intsmaze.redis.model;

import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedJedisPoolDemo {
	public static void main(String[] args){    
		
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("192.168.19.131", "8003"));
        shards.add(new JedisShardInfo("192.168.19.131", "8005"));

//        shards.add(new JedisShardInfo("192.168.19.131", "Redis001", 8003, 20 * 1000, 1));
//        shards.add(new JedisShardInfo("192.168.19.131", "Redis002", 8004, 20 * 1000, 2));


        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal(30);
        config.setMaxWaitMillis(3*1000);

        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(config, shards);

		for (int i = 0; i < 10; i++)
		{
			ShardedJedis shardedJedis = shardedJedisPool.getResource();//发现Sharded的构造方法其实是在我们 jedisPool.getResource() 时就完成的,每次jedisPool.getResource() 都会初始化一次，所以通过这个功能完成了动态上下节点功能啪啪啪·
			String key = "shard" + i;
			shardedJedis.set(key, "v-" + i);
			System.out.println(shardedJedis.get(key));
			JedisShardInfo shardInfo = shardedJedis.getShardInfo(key);
			System.out.println("getHost:" + shardInfo.getHost());
			shardedJedis.close();
		}
		shardedJedisPool.close();
		shardedJedisPool.destroy();
	}
}
