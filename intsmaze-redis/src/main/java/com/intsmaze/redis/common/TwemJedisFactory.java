package com.intsmaze.redis.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

public class TwemJedisFactory implements PooledObjectFactory<Jedis> {

	final Logger logger = LoggerFactory.getLogger(TwemJedisFactory.class);

	private final List<HostAndPort> hostAndPorts = new ArrayList<HostAndPort>();
	private int index = -1;

	private final int timeout;
	private final String password;
	private final int database;
	private final String clientName;

	public TwemJedisFactory(final List<String> addressList, final int timeout, final String password,
			final int database) {
		this(addressList, timeout, password, database, null);
	}

	public TwemJedisFactory(final List<String> addressList, final int timeout, final String password,
			final int database, final String clientName) {
		for (String address : addressList) {
			String[] ss = StringUtils.split(address, ':');
			String host = ss[0];
			int port = Integer.parseInt(ss[1]);
			HostAndPort hostAndPort = new HostAndPort(host, port);
			this.hostAndPorts.add(hostAndPort);
		}
		this.timeout = timeout;
		this.password = password;
		this.database = database;
		this.clientName = clientName;
	}

	  
	@Override
    public void activateObject(PooledObject<Jedis> pooledJedis) throws Exception {
		final BinaryJedis jedis = pooledJedis.getObject();
		if (jedis.getDB() != database) {
			jedis.select(database);
		}

	}

	  
	@Override
    public void destroyObject(PooledObject<Jedis> pooledJedis) throws Exception {
		final BinaryJedis jedis = pooledJedis.getObject();
		if (jedis.isConnected()) {
			try {
				try {
					jedis.quit();
				} catch (Exception e) {
				}
				jedis.disconnect();
			} catch (Exception e) {

			}
		}

	}

	private synchronized int getIndex() {
		index++;
		if (index >= hostAndPorts.size()) {
			index = 0;
		}
		return index;
	}

	  
	@Override
    public PooledObject<Jedis> makeObject() throws Exception {
		final HostAndPort hostAndPort = this.hostAndPorts.get(getIndex());
		logger.info("makeObject Jedis host: {}, port: {}, timeout {}.",
				new Object[] { hostAndPort.getHost(), hostAndPort.getPort(), timeout });
		final Jedis jedis = new Jedis(hostAndPort.getHost(), hostAndPort.getPort(), this.timeout);
		jedis.connect();
		if (this.password != null) {
			jedis.auth(this.password);
		}
		if (database != 0) {
			jedis.select(database);
		}
		if (clientName != null) {
			jedis.clientSetname(clientName);
		}

		return new DefaultPooledObject<Jedis>(jedis);
	}

	  
	@Override
    public void passivateObject(PooledObject<Jedis> pooledJedis) throws Exception {
		// TODO maybe should select db 0? Not sure right now.
	}

	  
	@Override
    public boolean validateObject(PooledObject<Jedis> pooledJedis) {
		final BinaryJedis jedis = pooledJedis.getObject();
		try {
			// HostAndPort hostAndPort = this.hostAndPort.get();
			// String connectionHost = jedis.getClient().getHost();
			// int connectionPort = jedis.getClient().getPort();
			// hostAndPort.getHost().equals(connectionHost) &&
			// hostAndPort.getPort() == connectionPort&&
			return jedis.isConnected() && jedis.ping().equals("PONG");
		} catch (final Exception e) {
			return false;
		}
	}
}