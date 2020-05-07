package com.intsmaze.hash.shard;

import com.intsmaze.hash.util.Hashing;
import com.intsmaze.hash.util.SafeEncoder;

import java.util.*;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:00
 *
 */
public class Sharded<R, S extends AbstractShardInfo<R>> {

    //-----------------我额外添加的，其余的都是拷贝jedis的源码---------------------------
//    protected  AtomicInteger failCount=new Atom icInteger(0);
//
//    protected  boolean isDynamic=false;
//
//    protected  int  failNmber=100;
//
//    protected ConcurrentHashMap<S,AtomicInteger> currentHashMap =new ConcurrentHashMap<S,AtomicInteger>();
    //-----------------------------------

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     *  实际物理节点的信息
     * @date : 2020/2/6 17:13
     *
     */
    private List<S> shards;

    public static final int DEFAULT_WEIGHT = 1;//默认权重

    private TreeMap<Long, S> nodes;//key为虚拟节点的位置，value为物理节点，通过要hash的key的值找到对应的虚拟节点的值，然后就知道对应的物理节点信息了

    private final Hashing algo;//选择使用哪种hash算法：MD5 和 MurmurHash两种；默认采用64位的MurmurHash算法；

    private final Map<AbstractShardInfo<R>, R> resources = new LinkedHashMap<AbstractShardInfo<R>, R>();//key为物理节点的信息，value为与该物理节点的连接实例

    public Sharded(List<S> shards) {
        this(shards, Hashing.MURMUR_HASH); // MD5 is really not good as we works
        // with 64-bits not 128
    }

    public Sharded(List<S> shards, Hashing algo) {
        this.algo = algo;
        this.shards=shards;
        initialize(shards);
    }

    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * 把jedis的源码提取出来后，跑了一下，发现没有热点问题，原理不是采用算法的问题，而是一个物理节点对应的虚拟节点的数量的问题导致使用hash算法后，
     * 还是有热点问题。jedis源码物理节点对应虚拟节点时160，而网上大部分代码都是10以下，所以导致了热点问题，
     * 这也告诉我们，实现一致性Hash算法时，不要太吝啬，虚拟节点设置的大点，热点问题就不会再有。
     * @date : 2020/2/6 17:16
     *
     */
    private int num=160;

    /**
     * 初始化物理节点和虚拟节点的对应关系
     * @param shards
     */
    protected void initialize(List<S> shards) {
        nodes = new TreeMap<Long, S>();

        for (int i = 0; i != shards.size(); ++i) {
            final S shardInfo = shards.get(i);
            if (shardInfo.getTableName() == null) {
                for (int n = 0; n < num * shardInfo.getWeight(); n++) {
                    nodes.put(this.algo.hash("SHARD-" + i + "-NODE-" + n), shardInfo);
                }
            } else {
                for (int n = 0; n < num * shardInfo.getWeight(); n++) {
                    nodes.put(this.algo.hash(shardInfo.getTableName() + "*" + shardInfo.getWeight() + n), shardInfo);
                }
            }
            resources.put(shardInfo, shardInfo.createResource());//调用IntsmazeShardInfo的createResource()方法 如果我们的实现不需要控制远程的连接，那么这个方法就不没什么用
        }
    }


    /**
     * 这个是找到key对应的节点后，不是仅仅返回属于的节点名称而是返回对应的实例连接
     * @param key
     * @return
     */
    public R getShardByResources(byte[] key) {
        return resources.get(getShardInfo(key));
    }

    public R getShardByResources(String key) {
        return resources.get(getShardInfo(key));
    }

    /**
     * 这个是找到key对应的节点后，返回属于的节点名称
     * @param key
     * @return
     */
    public S getShard(byte[] key) {
        return getShardInfo(key);
    }

    public S getShard(String key) {
        return getShardInfo(key);
    }

    public S getShardInfo(byte[] key) {
        SortedMap<Long, S> tail = nodes.tailMap(algo.hash(key));
        if (tail.isEmpty()) {
            return nodes.get(nodes.firstKey());
        }
        return tail.get(tail.firstKey());
    }

    public S getShardInfo(String key) {
        return getShardInfo(SafeEncoder.encode(key));
    }


    public Collection<S> getAllShardInfo() {
        return Collections.unmodifiableCollection(nodes.values());
    }

    public Collection<R> getAllShards() {
        return Collections.unmodifiableCollection(resources.values());
    }


}

