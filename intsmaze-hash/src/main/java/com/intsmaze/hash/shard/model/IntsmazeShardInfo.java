package com.intsmaze.hash.shard.model;

import com.intsmaze.hash.shard.AbstractShardInfo;
import com.intsmaze.hash.shard.Sharded;

import java.net.URI;
/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:01
 *
 */
public class IntsmazeShardInfo extends AbstractShardInfo<Intsmaze> {

    private String host;

    private int port;

    public IntsmazeShardInfo(String host) {
        super(Sharded.DEFAULT_WEIGHT,null);
        URI uri = URI.create(host);
        this.host = uri.getHost();
        this.port = uri.getPort();
    }

    public IntsmazeShardInfo(String host, String tableName) {
        super(Sharded.DEFAULT_WEIGHT, tableName);
        URI uri = URI.create(host);
        this.host = uri.getHost();
        this.port = uri.getPort();
    }

    public IntsmazeShardInfo(String host,int weight) {
        super(weight,null);
        URI uri = URI.create(host);
        this.host = uri.getHost();
        this.port = uri.getPort();
    }

    /**
     * 设置每一个物理节点对应的虚拟节点的权重，权重越大该物理节点对应的虚拟节点的数量就越多，默认权重为1，虚拟节点为160*1
     * @param host
     * @param tableName
     * @param weight
     */
    public IntsmazeShardInfo(String host, String tableName,int weight) {
        super(weight,tableName);
        URI uri = URI.create(host);
        this.host = uri.getHost();
        this.port = uri.getPort();
    }


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    /**
     * 创建对应的物理节点的连接，如果仅仅是确定hash的名称，不需要用实例连接，则里面就是空即可。
     * @return
     */
    @Override
    public Intsmaze createResource() {
        return new Intsmaze(this);
    }

    @Override
    public String toString() {
        return host + ":" + port + "-" + getTableName()+"*"+getWeight();
    }
}
