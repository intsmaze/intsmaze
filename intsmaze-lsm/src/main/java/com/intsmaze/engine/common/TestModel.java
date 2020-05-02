package com.intsmaze.engine.common;

import java.util.*;

public class TestModel {

    private LsmShardedEngine sharding;

    /**
     * @Description: 因为引擎本身是读写分离，读多写少的情况下，多线程读可以认为是无锁的，这里在一致性hash一下，在这种情况下，没有提供读的速度，
     * 只是减少读文件的情况。但是发现性能是下降的。下降了50%，这是因为根本没有减少读文件次数，因为有布隆过滤器，它决定了是否读文件，所有id次数是没有优化，
     * 一致性hash反而因为算法性能导致性能下降。
     * @Param:
     * @return:
     * @Author: intsmaze
     * @Date: 2019/1/21
     */
    public TestModel() {
        List<LsmShardInfo> shards = Arrays.asList(
                new LsmShardInfo("c:///home/intsmaze/lsm/db_1/"),
                new LsmShardInfo("c:///home/intsmaze/lsm/db_2/"),
                new LsmShardInfo("c:///home/intsmaze/lsm/db_3/"),
                new LsmShardInfo("c:///home/intsmaze/lsm/db_4/"));
        sharding = new LsmShardedEngine(shards);
    }

    public static void main(String[] args) throws Exception {
        TestModel testModel = new TestModel();

        //每个线程一个目录，发现没有太大提升
        new checkNode(testModel.sharding).start();
    }

    static class checkNode extends Thread {

        private LsmShardedEngine sharding;

        public checkNode(LsmShardedEngine sharding) {
            this.sharding = sharding;
        }

        @Override
        public void run() {
            try {
                long startTime = System.currentTimeMillis();
//                for (int i = 0; i <= 7995150; i++) {
                EngineRace engineRace = sharding.getFromDB((10000 + "--key").getBytes());
                byte[] bytes = engineRace.read((1000 + "--key").getBytes());
                if (bytes != null) {
                    String str = new String(bytes);
                    if (!(1 + "").equals(str.split("--")[0])) {
                        System.out.println(str);
                    }
                }
//                }
                System.out.println(System.currentTimeMillis() - startTime);
            } catch (Exception e) {

            }
        }
    }

}