package com.intsmaze.hash.shard.routtable;


import com.intsmaze.hash.shard.AbstractShardInfo;
import com.intsmaze.hash.shard.Sharded;

import java.net.URI;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:01
 *
 */
public class RouteTableShardedInfo extends AbstractShardInfo<RouteTable> {

    private String host;

    private int port;

    public RouteTableShardedInfo(String host, String tableName) {
        super(Sharded.DEFAULT_WEIGHT, tableName);
        URI uri = URI.create(host);
        this.host = uri.getHost();
        this.port = uri.getPort();
    }

    public RouteTableShardedInfo(String host, String tableName,int weight) {
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

    @Override
    public RouteTable createResource() {
        return new RouteTable(this);
    }

    @Override
    public String toString() {
        return host + ":" + port + "-" + getTableName()+"*"+getWeight();
    }
}
