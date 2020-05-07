
package com.intsmaze.lsm.db.table.file;

//import com.google.common.hash.BloomFilter;
import com.intsmaze.lsm.db.data.Key;
import com.intsmaze.lsm.db.io.AppendChannelFile;
import com.intsmaze.lsm.db.io.AppendFile;
import com.intsmaze.lsm.db.offheap.BloomFilter;
import com.intsmaze.lsm.db.state.Paths;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

/**
 * Writes a BloomFilter out to a file.
 */
public class TableBloomFilterWriter {

    private static final double FALSE_POSITIVE_PROBABILITY = 0.01;

    private final BloomFilter.Builder filterBuilder;
    private final AppendFile filterFile;
//    private final  BloomFilter<Key> guavaFilter;
//    private final long tableId;

    private TableBloomFilterWriter(AppendFile filterFile, long approxRecordCount) {
//    private TableBloomFilterWriter(long tableId, long approxRecordCount) throws FileNotFoundException {
        this.filterBuilder = new BloomFilter.Builder(approxRecordCount, FALSE_POSITIVE_PROBABILITY);
        this.filterFile = filterFile;
//    	this.tableId=tableId;
//    	
//		guavaFilter = BloomFilter.create(new Funnel<Key>() {
//			@Override
//			public void funnel(Key key, PrimitiveSink primitiveSink) {
//				primitiveSink.putBytes(key.data().array());
//			}
//		},1000000, FALSE_POSITIVE_PROBABILITY);
    }

    /**
     *
     * @author:YangLiu
     * @date:2018年10月18日 下午6:58:11
     * @describe:这个貌似像布隆过滤器添加数据
     */
    public void write(Key key) throws IOException {
        filterBuilder.put(key);
//    	guavaFilter.put(key);
    }

    public void finish() throws IOException {
        BloomFilter filter = filterBuilder.build();
        ByteBuffer filterBuffer = filter.memory().directBuffer();
        filterFile.append(filterBuffer);
        filterFile.close();
        filter.memory().release();
//    	
//    	
//    	FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
//    	FSTObjectOutput out=conf.getObjectOutput(new FileOutputStream("/tmp/heftytest/"+tableId+Paths.FILTER_EXT));
//		out.writeObject(guavaFilter, BloomFilter.class);
//		out.flush();
//		out.close();
    }

    public static TableBloomFilterWriter open(long tableId, Paths paths, long approxRecordCount) throws IOException {
//    	return new TableBloomFilterWriter(tableId,approxRecordCount);
        AppendFile filterFile = AppendChannelFile.open(paths.filterPath(tableId));
        return new TableBloomFilterWriter(filterFile, approxRecordCount);

    }
}
