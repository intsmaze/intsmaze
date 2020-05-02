package com.intsmaze.adapter.util;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43 
 * @describe: 
 */
public abstract class AbstractDataBaseUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractDataBaseUtils.class);
	
	private  String user;
	private  String password;
	private  String url;
	private  String dbName;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) throws IOException {
//		logger.debug("user is {}",DecoderUtils.getDecode(user));
//		this.user = DecoderUtils.getDecode(user);
		this.user=user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws IOException {
//		logger.debug("password is {}",DecoderUtils.getDecode(password));
//		this.password = DecoderUtils.getDecode(password);
		this.password=password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) throws IOException {
//		logger.debug("url is {}",DecoderUtils.getDecode(url));
//		this.url = DecoderUtils.getDecode(url);
		this.url=url;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) throws IOException {
//		logger.debug("dbName is {}",DecoderUtils.getDecode(dbName));
//		this.dbName = DecoderUtils.getDecode(dbName);
		this.dbName=dbName;
	}
	
}
