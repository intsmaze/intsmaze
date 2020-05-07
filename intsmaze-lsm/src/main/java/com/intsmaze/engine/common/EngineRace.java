package com.intsmaze.engine.common;

import com.intsmaze.engine.common.exceptions.EngineException;
import com.intsmaze.engine.common.exceptions.RetCodeEnum;
import com.intsmaze.lsm.db.db.Config;
import com.intsmaze.lsm.db.db.DB;
import com.intsmaze.lsm.db.db.LsmDB;
import com.intsmaze.lsm.db.db.Record;
import com.intsmaze.lsm.db.util.ByteBuffers;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.*;

import org.slf4j.Logger;

/**
 * @Description:
 * @Param:  B+树索引的大致原理如下:
 *          首先会设置每一批数据的大小memoryTableSize，例如每写入90条记录，就将这90条记录归一个特定的X.table,X.index,X.filter文件。
 *          咋先写入index文件，在写index文件的时候，又根据tableBlockSize 我理解就是索引块的大小，一个index文件就是一个B+树的结构，文件里面由很多索引块组成，也就是叶子节点（具体B+树）。
 *          例如tableBlockSize为30条一个节点，那么这里就会有3个索引块，每个索引块只会存储一条数据，比如根据排序规则，1-30的数据记录归一个索引块，但是该索引块里面只会存储数据1在table的位置。
 *          31,61分别代表一个索引块。
 *
 *          进行查询时，如果查询的数据为20，则能够知道他是1的索引块，然后根据1索引块里面会存储1-30的数据在table表的下标和长度，然后就会去table表读取这30条数据，然后这30条数据在某个数据结构
 *          里面，根据二分查找快速得到这个数据的位置了。
 *
 *          比我现在的里面，写入了0,1,2,3,4,5,6,7,8,9,10 十条记录，
 *          对应的索引块为0,2,5,8.  当输入为2的时候，其实会到table把0,1,10对应的数据全部读取出来，然后搜索出她具体的值。
 *
 *          具体可以看D:\home\intsmaze\2019 下的inde文件，里面就只写了0,2,5,8
 *
 * @return:
 * @Author: intsmaze
 * @Date: 2019/1/21
 */
public class EngineRace extends AbstractEngine {

    private Config config;
    private DB db;

    private static final Logger logger = LoggerFactory
            .getLogger(EngineRace.class);

    public EngineRace(String path) throws EngineException {
        try {
            if (!Files.exists(Paths.get(path))) {
                Files.createDirectory(Paths.get(path));
            }
            config = new Config()
                    .directory(Paths.get(path));
            db = LsmDB.open(config);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new EngineException(RetCodeEnum.NOT_FOUND, "文件创建失败");
        }
    }

    public static void main(String[] args) throws EngineException {
        EngineRace engineRace = new EngineRace("d:///home/intsmaze/2019/");
        /**
         * 读操作纯磁盘，不消耗内存
         * 16个线程，多线程顺序读用了650秒，平均每秒18万；随机读用了1664秒，平均每秒7万6
         * 单线程随机读用时350秒，每秒2.3万，单线程顺序读157秒，每秒5万。
         */
        ExecutorService es = new ThreadPoolExecutor(16, 16, 60L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                (Runnable r, ThreadPoolExecutor executor) -> System.out.println("discard"));
        for (int i = 0; i < 1; i++) {
            es.submit(new ReadThread(engineRace));
//            es.submit(new WriteThread(engineRace));
        }
    }

    static class ReadThread extends Thread {

        EngineRace engineRace;

        public ReadThread(EngineRace engineRace) {
            this.engineRace = engineRace;
        }

        @Override
        public void run() {
            try {
                Random ra = new Random();
                long startTime = System.currentTimeMillis();
                for (int i = 0; i <= 10; i++) {
                    int number = ra.nextInt(7995150) + 1;
                    number=i;
                    byte[] bytes = engineRace.read((number + "--key").getBytes());
                    if (bytes != null) {
                        String str = new String(bytes);
                        if (!(number + "").equals(str.split("--")[0])) {
                            System.out.println(str);
                        }
                    }
                }
                System.out.println(System.currentTimeMillis() - startTime);
            } catch (Exception e) {
            }
        }
    }

    static class WriteThread extends Thread {

        EngineRace engineRace;

        public WriteThread(EngineRace engineRace) {
            this.engineRace = engineRace;
        }

        @Override
        public void run() {
            try {
                Random ra = new Random();
                long startTime = System.currentTimeMillis();
                for (int i = 0; i <= 7995150; i++) {
                    int number = ra.nextInt(7995150) + 1;
                     number=i;
                    Thread.sleep(100);
                    engineRace.write((i + "--key").getBytes(), (i + "--" + Constant.string).getBytes());
                }
                System.out.println(System.currentTimeMillis() - startTime);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void write(byte[] key, byte[] value) throws EngineException {
        try {
            db.put(ByteBuffer.wrap(key), ByteBuffer.wrap(value));
        } catch (IOException e) {
            throw new EngineException(RetCodeEnum.NOT_FOUND, "写入数据失败");
        }
    }

    @Override
    public byte[] read(byte[] key) throws EngineException {
        try {
            Record record = db.get(ByteBuffer.wrap(key));
            if (record == null) {
                return null;
            }
            return ByteBuffers.toBytes(record.value());
        } catch (IOException e) {
            throw new EngineException(RetCodeEnum.NOT_FOUND, "读取数据失败");
        }
    }

    @Override
    public void close() {
    }

}
