//package cn.intsmaze.redis;
//
//import java.util.Arrays;
//import java.util.List;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisShardInfo;
//import redis.clients.jedis.Pipeline;
//import redis.clients.jedis.ShardedJedis;
//import redis.clients.jedis.ShardedJedisPipeline;
//import redis.clients.jedis.ShardedJedisPool;
//import redis.clients.jedis.Transaction;
//import org.junit.FixMethodOrder;
//import org.junit.runners.MethodSorters;
//
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class test {
//
//    private static ShardedJedis sharding;
//    private static ShardedJedisPool pool;
//
//    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//        List<JedisShardInfo> shards = Arrays.asList(
//                new JedisShardInfo("localhost",6379),
//                new JedisShardInfo("localhost",6379)); //使用相同的ip:port,仅作测试
//
//        sharding = new ShardedJedis(shards);
//
//        pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
//    }
//
//    @AfterClass
//    public static void tearDownAfterClass() throws Exception {
//        sharding.disconnect();
//        pool.destroy();
//    }
//
//
//    @Test
//    public void jedisShardNormal() {
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
//            String result = sharding.set("sn" + i, "n" + i);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("Simple@Sharing SET: " + ((end - start)/1000.0) + " seconds");
//    }
//
//
//
//
//    @Test
//    public void jedisShardSimplePool() {
//        ShardedJedis one = pool.getResource();
//
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
//            String result = one.set("spn" + i, "n" + i);
//        }
//        long end = System.currentTimeMillis();
//        pool.returnResource(one);
//        System.out.println("Simple@Pool SET: " + ((end - start)/1000.0) + " seconds");
//    }
//
//    @Test
//    public void jedisShardPipelinedPool() {
//        ShardedJedis one = pool.getResource();
//
//        ShardedJedisPipeline pipeline = one.pipelined();
//
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
//            pipeline.set("sppn" + i, "n" + i);
//        }
//        List<Object> results = pipeline.syncAndReturnAll();
//        long end = System.currentTimeMillis();
//        pool.returnResource(one);
//        System.out.println("Pipelined@Pool SET: " + ((end - start)/1000.0) + " seconds");
//    }
//}