package com.intsmaze.redis.common;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class JedisFactory {

	private Boolean blockWhenExhausted = null;
	private Integer maxIdle = 2;
	private Integer maxTotal = 3;
	private Long maxWaitMillis = null;
	private Long minEvictableIdleTimeMillis = null;
	private Integer minIdle = null;
	private Integer numTestsPerEvictionRun = null;
	private Boolean testOnBorrow = true;
	private Boolean testOnCreate = false;
	private Boolean testOnReturn = false;
	private Boolean testWhileIdle = null;
	private Long timeBetweenEvictionRunsMillis = null;
	private int timeout = 2000;

	private List<String> addressList;

	public void setBlockWhenExhausted(Boolean blockWhenExhausted) {
		this.blockWhenExhausted = blockWhenExhausted;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public void setMaxWaitMillis(Long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public void setTestOnCreate(Boolean testOnCreate) {
		this.testOnCreate = testOnCreate;
	}

	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public void setTimeBetweenEvictionRunsMillis(
			Long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}

	private JedisPoolConfig getJedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		if (blockWhenExhausted != null) {
			poolConfig.setBlockWhenExhausted(blockWhenExhausted);
		}
		if (maxIdle != null) {
			poolConfig.setMaxIdle(maxIdle);
		}
		if (maxTotal != null) {
			poolConfig.setMaxTotal(maxTotal);
		}
		if (maxWaitMillis != null) {
			poolConfig.setMaxWaitMillis(maxWaitMillis);
		}
		if (minEvictableIdleTimeMillis != null) {
			poolConfig
					.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		}
		if (minIdle != null) {
			poolConfig.setMinIdle(minIdle);
		}
		if (numTestsPerEvictionRun != null) {
			poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		}
		if (testOnBorrow != null) {
			poolConfig.setTestOnBorrow(testOnBorrow);
		}
		if (testOnCreate != null) {
			poolConfig.setTestOnCreate(testOnCreate);
		}
		if (testOnReturn != null) {
			poolConfig.setTestOnReturn(testOnReturn);
		}
		if (testWhileIdle != null) {
			poolConfig.setTestWhileIdle(testWhileIdle);
		}
		if (timeBetweenEvictionRunsMillis != null) {
			poolConfig
					.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		}
		return poolConfig;
	}

	public List<JedisPool> createJedisPools() {
		Preconditions.checkArgument(
				(addressList != null && !addressList.isEmpty()),
				"未定义addressList列表");
		List<JedisPool> addrGroup = Lists.newArrayListWithCapacity(addressList
				.size());
		JedisPoolConfig poolConfig = getJedisPoolConfig();
		for (String addr : addressList) {
			String[] ss = StringUtils.split(addr, ':');
			JedisPool jedisPool = new JedisPool(poolConfig, ss[0],
					Integer.parseInt(ss[1]), timeout);
			addrGroup.add(jedisPool);
		}
		return addrGroup;
	}

	public JedisPool createJedisPool() {
		JedisPoolConfig poolConfig = getJedisPoolConfig();
		String[] ss = StringUtils.split(addressList.get(0), ':');
		JedisPool jedisPool = new JedisPool(poolConfig, ss[0],
					Integer.parseInt(ss[1]), timeout);
		return jedisPool;
	}

	public TwemJedisPool createTwemJedisPool() {
		JedisPoolConfig poolConfig = getJedisPoolConfig();
		TwemJedisPool jedisPool = new TwemJedisPool(poolConfig, addressList,
				timeout);
		return jedisPool;
	}

	public static void closeQuietly(Jedis conn) {
		if (conn != null) {
			conn.close();
			conn = null;
		}
	}

}