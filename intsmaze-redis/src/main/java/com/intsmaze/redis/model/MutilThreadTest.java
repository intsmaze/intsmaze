package com.intsmaze.redis.model;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MutilThreadTest extends Thread {

	String name;

	int number = 1;

	public static JedisPool pool;

	static {
		try {

			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(20); // 连接池中最大空闲的连接数
			config.setMaxIdle(20); // 连接池中最少空闲的连接数
			config.setMinIdle(1);
			config.setMaxWaitMillis(15000);
			config.setMinEvictableIdleTimeMillis(300000);
			config.setNumTestsPerEvictionRun(3);
			config.setTimeBetweenEvictionRunsMillis(60000);
			config.setTestOnBorrow(false);
			config.setTestOnReturn(false);
			config.setTestWhileIdle(false);
			pool = new JedisPool(config, "127.0.0.1", 6379, 30);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MutilThreadTest(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}

	@Override
    public void run() {
		try {

			test(number);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String argc[]) throws Exception {
		MutilThreadTest myThread1 = new MutilThreadTest("线程1", 1);
		MutilThreadTest myThread2 = new MutilThreadTest("线程2", 2);
		MutilThreadTest myThread3 = new MutilThreadTest("线程3", 3);
		MutilThreadTest myThread4 = new MutilThreadTest("线程4", 4);
		MutilThreadTest myThread5 = new MutilThreadTest("线程5", 5);
		MutilThreadTest myThread6 = new MutilThreadTest("线程6", 6);
		myThread1.start();
		Thread.sleep(5);
		myThread2.start();
		Thread.sleep(5);
		myThread3.start();
		Thread.sleep(5);
		myThread4.start();
		Thread.sleep(5);
		myThread5.start();
		Thread.sleep(5);
		myThread6.start();
	}

	public void test(int n) throws Exception {
		Jedis jedis = pool.getResource();// 从pool中获取资源
		System.out.println(name + "........start..........." + number);
		jedis.set("intsmaze", "" + n);
		System.out.println(name + "...........end..........." + number);
		jedis.close();

	}

}