package com.intsmaze.redis.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

/** 
 * @author:YangLiu
 * @date:2018年5月20日 上午10:53:06 
 * @describe: 
 */
public class JedisFilterKeys {
	
	private static long OutTime = TimeUnit.MILLISECONDS.toNanos(200);

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	private int expireTime = 60 * 60 * 8;

	private List<JedisPool> jedisPools;

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

	public void setJedisPools(List<JedisPool> jedisPools) {
		this.jedisPools = jedisPools;
	}

	/**
	 * 这里redis服务设置的为三台。
	 * 
	 * 这个地方调用，不需要加锁同步，第一redis是单线程的，虽然这里会向三台redis访问，同一条消息同时重复调用该方法，
	 * 只会若一个消息notExists 2次，exists 1次，那么另一个消息notExists 1次，exists 2次，相互排斥的结果出现。
	 * notExists必须大于等于2才会认为该消息不重复，所有notExists为1的必然过滤掉。
	 * 
	 * 这里要求redis的服务器为奇数，如果为2，或一台宕机，那么会出现notExists=1 小于2次，永远被过滤掉。
	 * 同时redis宕机一台，那么会出现消息被过滤掉，无法处理的请求，notExists 1次，exists 1次，那么这里就GC了。
	 * 
	 * */
	public boolean filter(Object... keys) {
		int exists = 0;
		int notExists = 0;
		int errors = 0;
		String key = RedisKeys.onceFilter(keys);
		byte[] keyBytes = SafeEncoder.encode(key);
		byte[] valueBytes = SafeEncoder.encode(String.valueOf(System
				.currentTimeMillis()));
		
		for (JedisPool jedisPool : jedisPools) {//但是这里要考虑一个jedisPools遍历的问题，遍历的顺序是固定的，
			//所以不会存在同一条消息，A副本在操作A redis时，B副本直接先操作B resi再操作A redis。
			//结合redis单线程，那么只会出现A 副本要么notExists存在，Exists为0，或者Exists有数，notExists为0.
			//这里宕机一台，还是可以保证数据过滤的安全，方法上面的解释没有考虑到jedisPools遍历顺序的问题
			long ss = System.nanoTime();
			
			Jedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				if (jedis == null) {
					errors++;
					continue;
				}
				long rst = jedis.setnx(keyBytes, valueBytes);
				if (rst > 0) {
					notExists++;
					jedis.expire(keyBytes, expireTime);
				} else {
					exists++;
				}
			
			} catch (Exception e) {
				logger.error("", e);
				errors++;
			} finally {
				if (jedis != null) {
					long ee = System.nanoTime();
					if ((ee - ss) > OutTime) {
						logger.warn("Jedis filter used time : {}, host:{}", (ee - ss),
								jedis.getClient().getHost());
					}
					jedis.close();
				}
			}
		}
		if (errors == jedisPools.size()) {
			throw new RuntimeException();
		}
		if (notExists >= jedisPools.size() / 2 + 1) {
			return false;
		}
		logger.info("filter key: {}, exists: {}, notExists: {}, errors: {}.",
				key, exists, notExists, errors);
		return true;
	}
}
