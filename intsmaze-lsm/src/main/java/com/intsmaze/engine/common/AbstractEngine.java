package com.intsmaze.engine.common;

import com.intsmaze.engine.common.exceptions.EngineException;

/**
 * Copyright [2018] Alibaba Cloud All rights reserved
 * 
 * Complete the functions below to implement your own engine
 * 
 */
 
public abstract class AbstractEngine {

	/**
	 * close Engine
	 */
	public abstract void close();
	
	/**
	 *  write a key-value pair into engine
	 * @param key
	 * @param value
	 * @throws EngineException
	 */
	public abstract void write(byte[] key, byte[] value) throws EngineException;
	
	/**
	 * read value of a key
	 * @param key
	 * @return value
	 * @throws EngineException
	 */
	public abstract byte[] read(byte[] key) throws EngineException;
	

}
