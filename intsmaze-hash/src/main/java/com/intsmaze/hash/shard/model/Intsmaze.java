package com.intsmaze.hash.shard.model;

import redis.clients.jedis.Jedis;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:01
 * 
 */
public class Intsmaze {

    private Jedis client ;

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * 这里是一个示例，用的redis连接，也可以修改为jdbc连接，http连接都可以，如果不需要连接，注释掉该构造方法里面的代码即可
     * @date : 2020/2/6 16:37
     *
     */
    public Intsmaze(IntsmazeShardInfo intsmazeShardInfo)
    {
        //初始化对应的连接
        client= new Jedis(intsmazeShardInfo.getHost(), intsmazeShardInfo.getPort());
    }

    public Jedis getClient() {
        return client;
    }

    public void setClient(Jedis client) {
        this.client = client;
    }
}
