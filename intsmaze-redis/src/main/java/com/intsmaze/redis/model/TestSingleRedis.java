package com.intsmaze.redis.model;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
 
public class TestSingleRedis {
 
    private static Jedis jedis;
    
    private static ShardedJedis shard;
    
    private static ShardedJedisPool pool;
 
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	
    	//单个节点
    	jedis = new Jedis("192.168.19.131", 6379);
    	
    	//分片
        List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("192.168.1.106",6379)); 
        
        shard = new ShardedJedis(shards);
 
        GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
        goConfig.setMaxTotal(100);
        goConfig.setMaxIdle(20);
        goConfig.setMaxWaitMillis(-1);
        goConfig.setTestOnBorrow(true);
        
        pool = new ShardedJedisPool(goConfig, shards);
    }
 
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        jedis.disconnect();
        shard.disconnect();
//      pool.destroy();//实际中，连接池是不释放的
    }
    
    @Test
    public void testPipelined() {
        Pipeline pipeline = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            pipeline.set("p" + i, "p" + i);
        }
        //System.out.println(pipeline.get("p1000").get());
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined SET: " + ((end - start)/1000.0) + " seconds");
    }
 
    @Test
    public void testPipelineTrans() {
        long start = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("" + i, "" + i);
        }
        pipeline.exec();
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined transaction SET: " + ((end - start)/1000.0) + " seconds");
    }
 
    @Test
    public void testShard() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String result = shard.set("shard" + i, "n" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("shard SET: " + ((end - start)/1000.0) + " seconds");
    }
 
    @Test
    public void testShardpipelined() {
        ShardedJedisPipeline pipeline = shard.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("sp" + i, "p" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("shardPipelined SET: " + ((end - start)/1000.0) + " seconds");
    }
 
    @Test
    public void testShardPool() {
        ShardedJedis sj = pool.getResource();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String result = sj.set("spn" + i, "n" + i);
        }
        long end = System.currentTimeMillis();
        pool.returnResource(sj);
        System.out.println("shardPool SET: " + ((end - start)/1000.0) + " seconds");
    }
 
}