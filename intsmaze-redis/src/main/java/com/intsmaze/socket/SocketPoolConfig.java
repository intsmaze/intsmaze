package com.intsmaze.socket;

public class SocketPoolConfig extends GenericSocketObjectPoolConfig{

	public SocketPoolConfig() {
		setTestWhileIdle(true);
		setMinEvictableIdleTimeMillis(60000);
		setTimeBetweenEvictionRunsMillis(30000);
	    setNumTestsPerEvictionRun(-1);// TODO Auto-generated constructor stub
	}
	 
}
