package com.intsmaze.hash.service;

import com.intsmaze.hash.shard.model.IntsmazeShardInfo;
import com.intsmaze.hash.shard.model.IntsmazeShardedConnection;

import java.util.Arrays;
import java.util.List;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:00
 *
 */
public class service {

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


}
