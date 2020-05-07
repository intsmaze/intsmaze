package com.intsmaze.engine.common;

import cn.intsmaze.hash.shard.AbstractShardInfo;
import cn.intsmaze.hash.shard.Sharded;
import com.intsmaze.engine.common.exceptions.EngineException;

public class LsmShardInfo extends AbstractShardInfo<LsmBean> {

    public LsmShardInfo(String path) {
        super(Sharded.DEFAULT_WEIGHT,path);
    }

    public LsmShardInfo(int weight) {
        super(weight,null);
    }

    /**
     * 创建对应的物理节点的连接，如果仅仅是确定hash的名称，不需要用实例连接，则里面就是空即可。
     * @return
     */
    @Override
    public LsmBean createResource() {
        try {
            return new LsmBean(this);
        } catch (EngineException e) {
            return null;
        }
    }

}
