package com.intsmaze.hash.shard.model;


import com.intsmaze.hash.shard.Sharded;

import java.util.List;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:01
 * 
 */
public class IntsmazeShardedConnection extends Sharded<Intsmaze, IntsmazeShardInfo> {

    public IntsmazeShardedConnection(List<IntsmazeShardInfo> shards) {
        super(shards);
    }



    public String getTable(byte[] key) {
        IntsmazeShardInfo intsmazeShardInfo = getShard(key);
        return intsmazeShardInfo.getTableName();
    }

    public String getTable(String key) {
        IntsmazeShardInfo intsmazeShardInfo = getShard(key);
        return intsmazeShardInfo.getTableName();
    }

    public String getFromDB(String key) {
        Intsmaze intsmaze = getShardByResources(key);
        return intsmaze.getClient().get(key);
    }

//    public void setFailNoStop(int failCount)
//    {
//        failNmber=failCount;
//        isDynamic=true;
//    }

}
