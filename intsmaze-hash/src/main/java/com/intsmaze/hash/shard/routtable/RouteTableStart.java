package com.intsmaze.hash.shard.routtable;

import org.apache.commons.lang3.StringUtils;
import java.util.*;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:01
 *  这个类是用来模拟，在某一个库中进行了分表操作，表名为 JobDescription+X，根据指定的数据映射对应的表。
 */
public class RouteTableStart {

    private  RouteTableShardedConnection sharding;

    private String tableName;

    private String hostPort="intsmaze:3306";

    public RouteTableStart(String tableName,String hostPort)
    {
        this.tableName=tableName;
        this.hostPort=hostPort;
    }
    public RouteTableStart(String tableName)
    {
        this.tableName=tableName;
    }


    public RouteTableStart()
    {

    }

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * 100个散列表，名为JobDescription+i
     * @date : 2020/2/6 17:08
     *
     */
    public  void init() throws Exception {
        List<RouteTableShardedInfo> shards = new ArrayList<RouteTableShardedInfo>(100);
        for (int i = 0; i < 100; i++) {
            shards.add(new RouteTableShardedInfo(hostPort, StringUtils.join(tableName, "-", i)));
        }
        sharding = new RouteTableShardedConnection(shards);
    }

    public String shardTable(String key) {
        return sharding.getTable(key);
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }
}