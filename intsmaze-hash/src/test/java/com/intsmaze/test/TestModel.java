package com.intsmaze.test;


import com.intsmaze.hash.shard.model.IntsmazeShardInfo;
import com.intsmaze.hash.shard.model.IntsmazeShardedConnection;

import java.util.*;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * jedis的源码里面其实每次jedisPool.getResource() 都会初始化一次，所以通过这个功能完成了动态上下节点功能，而我拷贝过来改造的时候没有这么去弄，
 * 后面也可以每个调用方法的时候重新初始化一下吧。
 *
 * @date : 2020/2/6 10:01
 *
 */
public class TestModel {

    private static IntsmazeShardedConnection sharding;


    public static void setUpBeforeClass() throws Exception {
        List<IntsmazeShardInfo> shards = Arrays.asList(
                new IntsmazeShardInfo("localhost:6379", "table-A"),
                new IntsmazeShardInfo("localhost::6379", "table-B"),
                new IntsmazeShardInfo("localhost::6379", "table-C"),
                new IntsmazeShardInfo("localhost::6379", "table-D"),
                new IntsmazeShardInfo("localhost::6379", "table-E"));
        sharding = new IntsmazeShardedConnection(shards);
    }

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * @date : 2020/2/6 16:41
     *
     */
    public void shardNormal() {
        Map<String,Long> map=new HashMap<String,Long>();
        for (int i = 0; i < 10000000; i++) {

            String result = sharding.getTable("sn" + i);

            Long num=map.get(result);
            if(num==null)
            {
                map.put(result,1L);
            }
            else
            {
                num=num+1;
                map.put(result,num);
            }
        }
        Set<Map.Entry<String, Long>> entries = map.entrySet();
        Iterator<Map.Entry<String, Long>> iterator = entries.iterator();
        while(iterator.hasNext())
        {
            Map.Entry<String, Long> next = iterator.next();
            System.out.println(next.getKey()+"--->>>"+next.getValue());
        }
    }

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * @date : 2020/2/6 16:40
     *
     */
    public static void main(String[] args) throws Exception {
        TestModel t=new TestModel();
        setUpBeforeClass();
        t.shardNormal();
    }



    //    class checkNode extends Thread{
//
//        @Override
//        public void run() {
//            while(true)
//            {
//                try {
//                    Thread.sleep(30*1000);
//
//                    Set<Map.Entry<S, AtomicInteger>> entries = currentHashMap.entrySet();
//                    Iterator<Map.Entry<S, AtomicInteger>> iterator = entries.iterator();
//                    while(iterator.hasNext())
//                    {
//                        Map.Entry<S, AtomicInteger> next = iterator.next();
//                        if(next.getValue().get()>=failNmber)
//                        {
//                            shards.remove(next.getKey());
//                            sharding.initialize();
//                        }
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
}