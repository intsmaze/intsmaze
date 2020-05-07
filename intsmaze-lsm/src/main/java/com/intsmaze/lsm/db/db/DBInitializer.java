package com.intsmaze.lsm.db.db;

import com.intsmaze.lsm.db.data.Tuple;
import com.intsmaze.lsm.db.index.IndexBlock;
import com.intsmaze.lsm.db.state.BlockCaches;
import com.intsmaze.lsm.db.state.Paths;
import com.intsmaze.lsm.db.state.Snapshots;
import com.intsmaze.lsm.db.state.Tables;
import com.intsmaze.lsm.db.table.MutableTable;
import com.intsmaze.lsm.db.table.Table;
import com.intsmaze.lsm.db.table.file.FileTable;
import com.intsmaze.lsm.db.table.file.FileTableWriter;
import com.intsmaze.lsm.db.table.file.TupleBlock;
import com.intsmaze.lsm.db.table.memory.MemoryTable;
import com.intsmaze.lsm.db.write.CommitLog;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Restores the state of an existing database and does any clean up needed to get into a consistent state.
 */
public class DBInitializer {

    private final Config config;
    private final Paths paths;
    private final BlockCaches caches;
    private long maxSnapshotId;

    public DBInitializer(Config config) {
        this.config = config;
        this.paths = new Paths(config.tableDirectory(), config.logDirectory());
        //Caches里面设置了记录和索引块的缓存大小  *****************这个是干啥的？？？？？？
        this.caches = new BlockCaches(new TupleBlock.Cache(config.tableCacheSize()),
                new IndexBlock.Cache(config.indexCacheSize()));
    }

    public LsmDB initialize() throws IOException {
//        deleteTempTables();//删除之前的临时文件
        writeTablesFromLogs();//暂不理会
        //一开始会将索引文件和布隆文件的数据缓存到对应的table中，起到缓存的作用
        List<Table> tables = loadTables();//目前返回为null
        return new LsmDB(config, paths, new Tables(tables),
                new Snapshots(maxSnapshotId), caches);

    }

    private List<Table> loadTables() throws IOException {
        List<Table> tables = new ArrayList<Table>();
        Set<Long> tableIds = paths.tableFileIds();

        for (Long id : tableIds) {
            Table table = FileTable.open(id, paths, caches.recordBlockCache(), caches.indexBlockCache());
            maxSnapshotId = Math.max(table.maxSnapshotId(), maxSnapshotId);
            tables.add(table);
        }

        return tables;
    }

    private void deleteTempTables() throws IOException {
        Set<Long> tempIds = paths.tempTableFileIds();

        for (Long id : tempIds) {
            Files.deleteIfExists(paths.tempPath(id));
            Files.deleteIfExists(paths.indexPath(id));
            Files.deleteIfExists(paths.filterPath(id));
        }
    }

    private void writeTablesFromLogs() throws IOException {
        Set<Long> logIds = paths.logFileIds();

        for (Long id : logIds) {
            CommitLog log = CommitLog.open(id, paths);
            Table memoryTable = readTable(log);
            log.close();

//            FileTableWriter.Task tableWriterTask = new FileTableWriter.Task.TupleBlockBuilder().tableId(id).config(config)
//                    .paths(paths).level(1).tupleCount(memoryTable.tupleCount()).source(memoryTable.ascendingIterator
//                            (Long.MAX_VALUE)).buildSortedByteMap();
            FileTableWriter.Task tableWriterTask = new FileTableWriter.Task(id,1,paths,config,memoryTable.ascendingIterator
                    (Long.MAX_VALUE),memoryTable.tupleCount(),null);
            tableWriterTask.run();

            Files.deleteIfExists(paths.logPath(id));
        }
    }

    private Table readTable(CommitLog log) {
        MutableTable memoryTable = new MemoryTable(log.tableId());

        for (Tuple tuple : log) {
            memoryTable.put(tuple);
        }

        return memoryTable;
    }
}
