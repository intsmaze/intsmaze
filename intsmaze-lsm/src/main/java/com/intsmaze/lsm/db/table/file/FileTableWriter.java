package com.intsmaze.lsm.db.table.file;

import com.intsmaze.lsm.db.db.Config;
import com.intsmaze.lsm.db.index.IndexRecord;
import com.intsmaze.lsm.db.io.AppendChannelFile;
import com.intsmaze.lsm.db.io.AppendFile;
import com.intsmaze.lsm.db.state.Paths;
import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.index.IndexWriter;
//import com.jordanwilliams.heftydb.io.Throttle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Writes a Table file from a stream of sorted records.
 */
public class FileTableWriter {

    /**
     * Provides a handy runnable for executing a self-contained FileTableWriter on a background thread.
     */
    public static class Task implements Runnable {

//        public static class TupleBlockBuilder {
//
//            private long tableId;
//            private int level = 1;
//            private Iterator<Tuple> source;
//            private long tupleCount;
//            private Paths paths;
//            private Config config;
//            private Callback callback;
////            private Throttle throttle = new Throttle(Integer.MAX_VALUE);
//
//            public TupleBlockBuilder tableId(long tableId) {
//                this.tableId = tableId;
//                return this;
//            }
//
//            public TupleBlockBuilder level(int level) {
//                this.level = level;
//                return this;
//            }
//
//            public TupleBlockBuilder source(Iterator<Tuple> source) {
//                this.source = source;
//                return this;
//            }
//
//            public TupleBlockBuilder tupleCount(long tupleCount) {
//                this.tupleCount = tupleCount;
//                return this;
//            }
//
//            public TupleBlockBuilder paths(Paths paths) {
//                this.paths = paths;
//                return this;
//            }
//
//            public TupleBlockBuilder config(Config config) {
//                this.config = config;
//                return this;
//            }
//
//            public TupleBlockBuilder callback(Callback callback) {
//                this.callback = callback;
//                return this;
//            }
//
////            public TupleBlockBuilder throttle(Throttle throttle) {
////                this.throttle = throttle;
////                return this;
////            }
//
//            public Task buildSortedByteMap() {
//                return new Task(tableId, level, paths, config, source, tupleCount, callback);//, throttle
//            }
//        }

        public interface Callback {
            public void finish();
        }

        private static final AtomicInteger taskId = new AtomicInteger();
        private static final Logger logger = LoggerFactory.getLogger(FileTableWriter.class);

//        private final long tableId;
//        private final int level;
//        private final Iterator<Tuple> tuples;
//        private final long tupleCount;
//        private final Paths paths;
//        private final Config config;
//        private final Callback callback;
////        private final Throttle throttle;
        private long tableId;
        private int level = 1;
        private Iterator<Tuple> tuples;
        private long tupleCount;
        private Paths paths;
        private Config config;
        private Callback callback;

        public Task(long tableId, int level, Paths paths, Config config, Iterator<Tuple> tuples, long tupleCount,
                    Callback callback) {
            this.tableId = tableId;
            this.level = level;
            this.paths = paths;
            this.config = config;
            this.tuples = tuples;
            this.tupleCount = tupleCount;
            this.callback = callback;
//            this.throttle = throttle;
        }

        /**
         * 后台线程刷磁盘,当数据满了后异步将log日志里面的数据解析到数据结构文件中
         * 如果在刷的时候识别了，会不会重新刷??????需要验证————————————————
         */
        @Override
        public void run() {
            try {
                int id = taskId.incrementAndGet();
                logger.debug("Starting table writer " + id + " for table " + tableId);

                FileTableWriter tableWriter = FileTableWriter.open(tableId, paths, tupleCount,
                        config.indexBlockSize(), config.tableBlockSize(), level);//这个才是刷文件
                //tuples 已经根据key重新排序了，比如10在2的前面
                while (tuples.hasNext()) {
//                	Thread.sleep(100);
                    System.out.println("异步根据日志写入table文件");
                    Tuple tuple = tuples.next();
//                    throttle.consume(tuple.size());
                    tableWriter.write(tuple);
                }

                tableWriter.finish();

                Files.move(paths.tempPath(tableId), paths.tablePath(tableId), StandardCopyOption.ATOMIC_MOVE);

                if (callback != null) {
                    callback.finish();//这个时候回调
                }

                logger.debug("Finishing table writer " + id);
            } catch (ClosedChannelException e) {
                logger.debug("File table was only partially written " + tableId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        }
    }
    //FileTableWriter----------------------------------
    private final int maxRecordBlockSize;
    private final IndexWriter indexWriter;
    private final TableBloomFilterWriter filterWriter;
    private final TableTrailer.Builder trailerBuilder;
    private final AppendFile tableDataFile;

    private TupleBlock.TupleBlockBuilder recordBlockBuilder;

    int i=0;//intsmaze add

    private FileTableWriter(long tableId, IndexWriter indexWriter, TableBloomFilterWriter filterWriter,
                            AppendFile tableDataFile, int maxRecordBlockSize, int level) throws IOException {
        this.indexWriter = indexWriter;
        this.filterWriter = filterWriter;

        //里面存储了一批的k-v数据，这一批数据存储在一个文件里面
        this.recordBlockBuilder = new TupleBlock.TupleBlockBuilder();

        this.maxRecordBlockSize = maxRecordBlockSize;
        this.trailerBuilder = new TableTrailer.Builder(tableId, level);
        this.tableDataFile = tableDataFile;
    }

    public void write(Tuple tuple) throws IOException {
        //索引块的大小，如果数据库的量太小，则这里永远不会执行，那也没事，执行后面的finish呗

        //maxRecordBlockSize是一个索引文件中每一个节点的大小，索引是b+树，这点要注意。
        //比如memoryTableSize的大小为6000，tableBlockSize就是这里的maxRecordBlockSize大小为2000，那么一次批量写数据，这些数据都归于id为1的文件。1.index文件里面就有3个叶子节点（最少）。
       //tupleBlock里面的数据分别为（1,2,3），（4,5,6），（7,8,9）
        if (recordBlockBuilder.size() >= maxRecordBlockSize) {//当容量大于这么多后才向硬盘写数据
            //199行addRecord方法是添加元素，但是没有排序序列化什么，这个操作在最后刷入磁盘的时候做一次，而不是每次往里面添加一个元素都弄一次
            TupleBlock tupleBlock = recordBlockBuilder.build();//里面是排好序的k-v记录 我把这一行从方法里面移到外面，便于理解
            writeRecordBlock(tupleBlock);//这里面也有些索引
//            System.out.println(i);//每276条向里面写一条key
            i=0;
        }

        i++;
        recordBlockBuilder.addRecord(tuple);//记录存储在这里面
        filterWriter.write(tuple.key());
        trailerBuilder.put(tuple);//这个暂时忽略，没有添加数据操作
    }

    public void finish() throws IOException {
        TupleBlock tupleBlock = recordBlockBuilder.build();//里面是排好序的k-v记录   //我把这一行从方法里面移到外面，便于理解
        writeRecordBlock(tupleBlock);
        writeTrailer();
        filterWriter.finish();//布隆进硬盘
        indexWriter.finish();//索引进硬盘
        tableDataFile.close();//数据进硬盘
    }

    /**
     * @author:YangLiu
     * @date:2018年10月18日 下午8:02:26
     * @describe:重置recordBlockBuilder对象字段值为初始化状态
     */
    private void writeRecordBlock(TupleBlock tupleBlock) throws IOException {

//        TupleBlock tupleBlock = recordBlockBuilder.build();这个我移到外面了，便于理解

        //得到前面已经写入好数据的ByteBuffer
        ByteBuffer recordBlockBuffer = tupleBlock.memory().directBuffer();//这个不晓得是搞啥子

        //这里应该是想data文件里面写入数据，并记录下标
        tableDataFile.appendInt(recordBlockBuffer.capacity());
        long recordBlockOffset = tableDataFile.append(recordBlockBuffer);//这个感觉是存储数据
        recordBlockBuffer.rewind();
        tableDataFile.appendInt(recordBlockBuffer.capacity());

        Tuple startTuple = tupleBlock.first();//取出一条 k-v

        //这个是存储索引 ，但是为什么只存储了一条呢？  我打开索引文件,虽然里面是乱码,但是感觉并没有只存储一条啊
        //这个索引里面的节点开头数据分别为1,4,7.  如果搜索2，则根据二叉搜索可以得到2是在1节点的。
        indexWriter.write(new IndexRecord(startTuple.key(), recordBlockOffset, recordBlockBuffer.capacity()));

        tupleBlock.memory().release();

        //创建一个新的里面是空，老的直接被替换掉了???那里面的元素咋搞
        recordBlockBuilder = new TupleBlock.TupleBlockBuilder();
    }

    private void writeTrailer() throws IOException {
        ByteBuffer trailerBuffer = trailerBuilder.build().buffer();
        tableDataFile.append(trailerBuffer);
    }

    /**
     * 创建索引文件，布隆文件，日志文件
     * @param tableId
     * @param paths
     * @param approxRecordCount
     * @param maxIndexBlockSize
     * @param maxRecordBlockSize
     * @param level
     * @return 1, paths, 500000, 32000, 32000, 1
     * @throws IOException
     */
    public static FileTableWriter open(long tableId, Paths paths, long approxRecordCount, int maxIndexBlockSize,
                                       int maxRecordBlockSize, int level) throws IOException {
        IndexWriter indexWriter = IndexWriter.open(tableId, paths, maxIndexBlockSize);
        TableBloomFilterWriter filterWriter = TableBloomFilterWriter.open(tableId, paths, approxRecordCount);
        //考虑替换掉布隆算法 参数1 文件序号 参数二 文件路径  参数3文件里面的数据的条数
        AppendFile tableDataFile = AppendChannelFile.open(paths.tempPath(tableId));

        return new FileTableWriter(tableId, indexWriter, filterWriter, tableDataFile, maxRecordBlockSize, level);
    }
}
