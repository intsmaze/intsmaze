package com.intsmaze.redis.demo;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class BuyCartServiceImpl {
	
	
	private Jedis jedis = null;
	private static final String CART_PRIFIX = "cart:";
	
	@Before
	public void init(){
		jedis = new Jedis("mini1",6379);
	}
	
	/**
	 * 添加商品到购物车
	 */
	@Test
	public void testAddItemToCart(){
		
		jedis.hset(CART_PRIFIX+"user02", "肥皂", "2");
		jedis.hset(CART_PRIFIX+"user02", "手铐", "1");
		jedis.hset(CART_PRIFIX+"user02", "皮鞭", "3");
		jedis.hset(CART_PRIFIX+"user02", "蜡烛", "4");
		jedis.close();
	}
	
	/**
	 * 查询购物车信息
	 */
	@Test
	public void testGetCartInfo(){
		Map<String, String> cart = jedis.hgetAll(CART_PRIFIX+"user02");
		Set<Entry<String, String>> entrySet = cart.entrySet();
		for(Entry<String, String> ent :entrySet){
			
			System.out.println(ent.getKey()+ ":" + ent.getValue());
		}
		jedis.close();
	}
	
	/**
	 * 更改购物车
	 */
	@Test
	public void editCart(){
		//给蜡烛商品项的数量加1
		jedis.hincrBy(CART_PRIFIX+"user02", "蜡烛", 1);
		jedis.close();
		
	}
	
	
	
	/**
	 * 从购物车中删除商品项
	 */
	@Test
	public void delItemFromCart(){
		
		jedis.hdel(CART_PRIFIX+"user02", "手铐");
		jedis.close();
		
	}
	
	
}
