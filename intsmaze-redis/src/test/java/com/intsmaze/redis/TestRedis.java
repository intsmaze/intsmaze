package com.intsmaze.redis;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.List;

/**
 * Created by 刘洋 on 2018/8/15.
 */
public class TestRedis extends TestCase {


    public void testCommonRedis() {
        ApplicationContext ct = new ClassPathXmlApplicationContext("spring-redis.xml");

        List<JedisPool> jedisPoolList = (List<JedisPool>) ct.getBean("jedisPools");

        Iterator<JedisPool> jedisPoolIterator = jedisPoolList.iterator();
        while (jedisPoolIterator.hasNext()) {
            JedisPool jedisPool = jedisPoolIterator.next();
            Jedis jedis = null;
                jedis = jedisPool.getResource();
                if (jedis == null) {
                    System.out.println("连接不存在");
                    continue;
                }
                long rst = jedis.setnx("intsmaze", "my name is intsmaze");
                if (rst > 0) {
                    System.out.println("数据不存在");
                    jedis.expire("intsmaze", 1000);
                } else {
                    System.out.println("数据存在");
                }
        }



        JedisPool jedisPool=(JedisPool)ct.getBean("jedisPool");
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        long rst = jedis.setnx("intsmaze", "my name is intsmaze");

    }
}
