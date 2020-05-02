package com.intsmaze.redis.demo;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestSet {
	
	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("192.168.2.70", 6379);
		
		
		jedis.sadd("friends:shuangshuang", "dandan","lulu","lili");
		jedis.sadd("friends:laobi", "laowang","laodu","laoli","lili","lulu");
		
		
		//判断一个成员是否属于指定的集合
		Boolean isornot = jedis.sismember("friends:laobi", "shuangshuang");
		System.out.println(isornot);
		
		//求两个集合的差并交集
		
		Set<String> ssDiffbb = jedis.sdiff("friends:shuangshuang","friends:laobi");
		
		Set<String> ssUnionbb = jedis.sunion("friends:shuangshuang","friends:laobi");
		
		Set<String> ssInterbb = jedis.sinter("friends:shuangshuang","friends:laobi");
		
		
		//打印结果
		for(String mb:ssUnionbb){
			System.out.println(mb);
		}
		
		
		
	}
	
	

}
