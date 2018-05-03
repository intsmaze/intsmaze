package org.intsmaze.core.util;

import java.io.IOException;

import org.apache.commons.dbcp.BasicDataSource;

import sun.misc.BASE64Decoder;

public class DBUtil extends BasicDataSource{
	
	private BASE64Decoder decoder = new BASE64Decoder(); 
	
	public void setUsername(String username){
		try {
			super.setUsername(new String(decoder.decodeBuffer( username )));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setPassword(String password){
		try {
			super.setPassword(new String(decoder.decodeBuffer( password )));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
