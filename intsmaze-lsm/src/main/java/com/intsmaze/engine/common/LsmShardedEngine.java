package com.intsmaze.engine.common;


import cn.intsmaze.hash.shard.Sharded;

import java.util.List;

/**
* @Description:
* @Param:
* @return:
* @Author: intsmaze
* @Date: 2019/1/21
*/
public class LsmShardedEngine extends Sharded<LsmBean, LsmShardInfo> {

    public LsmShardedEngine(List<LsmShardInfo> shards) {
        super(shards);
    }

    public String getTable(byte[] key) {
        LsmShardInfo intsmazeShardInfo = getShard(key);
        return intsmazeShardInfo.getTableName();
    }

    public EngineRace getFromDB(byte[] key) {
        LsmBean intsmaze = getShardByResources(key);
        return intsmaze.getEngineRace();
    }


}
