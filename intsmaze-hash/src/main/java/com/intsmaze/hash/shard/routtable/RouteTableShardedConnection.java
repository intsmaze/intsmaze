package com.intsmaze.hash.shard.routtable;

import com.intsmaze.hash.shard.Sharded;

import java.util.List;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:01
 *
 */
public class RouteTableShardedConnection extends Sharded<RouteTable, RouteTableShardedInfo> {

    public RouteTableShardedConnection(List<RouteTableShardedInfo> shards) {
        super(shards);
    }

    public String getTable(String key) {
        RouteTableShardedInfo routeTableShardedInfo = getShard(key);
        return routeTableShardedInfo.getTableName();
    }

    public String getFromDB(String key) {
        return null;
    }

}
