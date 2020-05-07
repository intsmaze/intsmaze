package com.intsmaze.engine.common;

import com.intsmaze.engine.common.exceptions.EngineException;

/**
* @Description:
* @Param:
* @return:
* @Author: intsmaze
* @Date: 2019/1/21
*/
public class LsmBean {

    private EngineRace engineRace ;

    /**
    * @Description: 一个EngineRace对应一个目录，通过对keyhash将目录切小.将会通过参数lsmShardInfo得到绑定的目录
    * @Param: 
    * @return: 
    * @Author: intsmaze
    * @Date: 2019/1/21
    */ 
    public LsmBean(LsmShardInfo lsmShardInfo) throws EngineException {
        engineRace= new EngineRace(lsmShardInfo.getTableName());
    }

    public EngineRace getEngineRace() {
        return engineRace;
    }
}
