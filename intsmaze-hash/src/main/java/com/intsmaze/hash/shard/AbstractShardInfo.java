package com.intsmaze.hash.shard;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:01
 * 
 */
public abstract class AbstractShardInfo<T> {

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * 权重
     * @date : 2020/2/6 16:38
     * 
     */
    private int weight;

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * 我添加的字段表明，为了应对分表的操作，这个时候host是一样的，要hash对应的表中，如果不是分表分库操作，那么该字段可以为空
     * @date : 2020/2/6 16:38
     *
     */
    private String tableName=null;

    public AbstractShardInfo() {
    }

    public AbstractShardInfo(int weight) {
        this.weight = weight;
    }

    public AbstractShardInfo(int weight, String tableName) {
        this.weight = weight;
        this.tableName=tableName;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getTableName() {
        return tableName;
    }

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * @date : 2020/2/6 16:39
     *
     */
    protected abstract T createResource();


    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractShardInfo<?> shardInfo = (AbstractShardInfo<?>) o;

        if (weight != shardInfo.weight)
        {return false;}
        return tableName.equals(shardInfo.tableName);

    }

    @Override
    public int hashCode() {
        int result = weight;
        result = 31 * result + tableName.hashCode();
        return result;
    }
}