package com.intsmaze.redis.queue;

import java.util.Random;
import java.util.UUID;

import redis.clients.jedis.Jedis;

/**
 * 模拟生成任务
 * @author
 */
public class TaskProducer {
	private static Jedis jedis = null;
	
	public static void main(String[] args) throws Exception {
		
		jedis = new Jedis("mini1", 6379);
		Random random = new Random();
		while(true){
			
			int nextInt = random.nextInt(1000);
			Thread.sleep(1000+nextInt);
			
			//生成一个任务的id
			String taskid = UUID.randomUUID().toString();
			
			jedis.lpush("task-queue", taskid);
			
			System.out.println("生成了一个任务： " + taskid);
		}
	}
}
