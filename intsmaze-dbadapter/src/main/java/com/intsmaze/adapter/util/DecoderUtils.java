package com.intsmaze.adapter.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;

/** 
 * @author:YangLiu
 * @date:2018年1月5日 下午4:23:18 
 * @describe: 
 */
public class DecoderUtils {
	
	 public static BASE64Decoder decoder = new BASE64Decoder();
	 
	 public static String getDecode(String encode) throws IOException 
	 {
		 return new String (decoder.decodeBuffer(encode));
	 }
}
