package com.intsmaze.redis.demo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

/**
 * String结构最适合用来做缓存
 */
public class StringStructureData {
	
	private Jedis jedis =null;
	
	@Before
	public void init(){
		jedis = new Jedis("mini1",6379);
	}
	
	
	/**
	 * 将对象缓存到redis的string结构数据中
	 */
	@Test
	public void testObjectCache() throws Exception{
		
		ProductInfo p = new ProductInfo();
		
		p.setName("苏菲");
		p.setDescription("angelababy专用");
		p.setCatelog("unknow");
		p.setPrice(10.8);

		//将对象序列化成字节数组
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(ba);
		
		//用对象序列化流来将p对象序列化，然后把序列化之后的二进制数据写到ba流中
		oos.writeObject(p);
		
		//将ba流转成byte数组
		byte[] pBytes = ba.toByteArray();
		
		//将对象序列化之后的byte数组存到redis的string结构数据中
		jedis.set("product:01".getBytes(), pBytes);
	
		//根据key从redis中取出对象的byte数据
		byte[] pBytesResp = jedis.get("product:01".getBytes());
		
		//将byte数据反序列出对象
		ByteArrayInputStream bi = new ByteArrayInputStream(pBytesResp);
		
		ObjectInputStream oi = new ObjectInputStream(bi);
		
		//从对象读取流中读取出p对象
		ProductInfo pResp = (ProductInfo) oi.readObject();
		
		System.out.println(pResp);
	}
	
	/**
	 * 将对象转成json字符串缓存到redis的string结构数据中
	 */
	@Test
	public void testObjectToJsonCache(){
		ProductInfo p = new ProductInfo();
		
		p.setName("ABC");
		p.setDescription("刘亦菲专用");
		p.setCatelog("夜用型");
		p.setPrice(10.8);

		//利用gson将对象转成json串
		Gson gson = new Gson();
		String pJson = gson.toJson(p);
		
		//将json串存入redis
		jedis.set("prodcut:02", pJson);
		
		//从redis中取出对象的json串
		String pJsonResp = jedis.get("prodcut:02");
		
		//将返回的json解析成对象
		ProductInfo pResponse = gson.fromJson(pJsonResp, ProductInfo.class);
		
		//显示对象的属性
		System.out.println(pResponse);
	}
}
