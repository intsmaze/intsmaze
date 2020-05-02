package com.intsmaze.redis.queue;

import java.util.Random;

import redis.clients.jedis.Jedis;

/**
 * 任务处理模块
 * @author
 */
public class TaskProcessor {
	private static Jedis jedis = null;
	
	public static void main(String[] args) throws Exception {
		
		Random random = new Random();
		
		jedis = new Jedis("mini1", 6379);
		
		while(true){
			Thread.sleep(1500);
			
			//从任务队列中取出一个任务，同时放入到暂存队列中，该操作时原子性的，就是不会出现取出任务成功，存放任务失败
			String taskid = jedis.rpoplpush("task-queue", "tmp-queue");
			
			//处理任务
			if(random.nextInt(19) % 9 ==0){
				//模拟失败
				//失败的情况下，需要将任务从暂存队列弹回任务队列
				jedis.rpoplpush("tmp-queue", "task-queue");
				System.out.println("该任务处理失败：" + taskid);
				
			}else{
				//模拟成功
				//成功的情况下，只需要将任务从暂存队列清除
				jedis.rpop("tmp-queue");
				System.out.println("任务处理成功： " + taskid);
			}
		}
	}
}
