
package com.intsmaze.lsm.db.db;

import java.nio.file.Path;

/**
 * Encapsulates all of the tunable values for a database instance.
 */
public class Config {

    //  private int memoryTableSize = 8192000;//不是MemoryTable的数据的数量限制，而是里面的数据的key和value的字节数的大小
//    private int memoryTableSize = 60960000;//测试发现，key value 大小为102 插入8000多条会刷磁盘
    private int memoryTableSize = 6096;
    //    private int tableBlockSize = 16384;
    private int tableBlockSize = 1638;
    //    private int indexBlockSize = 65536;
    private int indexBlockSize = 3000;
    private int tableWriterThreads = 1;
    private long tableCacheSize = 128000000;
    private long indexCacheSize = 32000000;
    private long maxCompactionRate = 32768000;
    private long maxWriteRate = 32768000;
    private boolean printMetrics = false;
    private Path tableDirectory;
    private Path logDirectory;

    public Config(int memoryTableSize, int tableBlockSize, int indexBlockSize,
                  int tableWriterThreads, long tableCacheSize, long indexCacheSize,
                  boolean printMetrics, Path tableDirectory, Path logDirectory, long maxCompactionRate,
                  long maxWriteRate) {
        this.memoryTableSize = memoryTableSize;
        this.tableBlockSize = tableBlockSize;
        this.indexBlockSize = indexBlockSize;
        this.tableWriterThreads = tableWriterThreads;
        this.tableCacheSize = tableCacheSize;
        this.indexCacheSize = indexCacheSize;
        this.printMetrics = printMetrics;
        this.tableDirectory = tableDirectory;
        this.logDirectory = logDirectory;
        this.maxCompactionRate = maxCompactionRate;
        this.maxWriteRate = maxWriteRate;
    }

    public Config() {
    }

    public Config directory(Path directory) {
        this.tableDirectory = directory;
        this.logDirectory = directory;
        return this;
    }

    public int memoryTableSize() {
        return memoryTableSize;
    }

    public int tableBlockSize() {
        return tableBlockSize;
    }

    public int indexBlockSize() {
        return indexBlockSize;
    }

    public int tableWriterThreads() {
        return tableWriterThreads;
    }

    public long tableCacheSize() {
        return tableCacheSize;
    }

    public long indexCacheSize() {
        return indexCacheSize;
    }

    public boolean autoPrintMetrics() {
        return printMetrics;
    }

    public Path tableDirectory() {
        return tableDirectory;
    }

    public Path logDirectory() {
        return logDirectory;
    }

    public long maxCompactionRate() {
        return maxCompactionRate;
    }

    public long maxWriteRate() {
        return maxWriteRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config config = (Config) o;

        if (indexBlockSize != config.indexBlockSize) return false;
        if (indexCacheSize != config.indexCacheSize) return false;
        if (maxCompactionRate != config.maxCompactionRate) return false;
        if (maxWriteRate != config.maxWriteRate) return false;
        if (memoryTableSize != config.memoryTableSize) return false;
        if (printMetrics != config.printMetrics) return false;
        if (tableBlockSize != config.tableBlockSize) return false;
        if (tableCacheSize != config.tableCacheSize) return false;
        if (tableWriterThreads != config.tableWriterThreads) return false;
        if (logDirectory != null ? !logDirectory.equals(config.logDirectory) : config.logDirectory != null)
            return false;
        if (tableDirectory != null ? !tableDirectory.equals(config.tableDirectory) : config.tableDirectory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + memoryTableSize;
        result = 31 * result + tableBlockSize;
        result = 31 * result + indexBlockSize;
        result = 31 * result + tableWriterThreads;
        result = 31 * result + (int) (tableCacheSize ^ (tableCacheSize >>> 32));
        result = 31 * result + (int) (indexCacheSize ^ (indexCacheSize >>> 32));
        result = 31 * result + (printMetrics ? 1 : 0);
        result = 31 * result + (tableDirectory != null ? tableDirectory.hashCode() : 0);
        result = 31 * result + (logDirectory != null ? logDirectory.hashCode() : 0);
        result = 31 * result + (int) (maxCompactionRate ^ (maxCompactionRate >>> 32));
        result = 31 * result + (int) (maxWriteRate ^ (maxWriteRate >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Config{" +
                ", memoryTableSize=" + memoryTableSize +
                ", tableBlockSize=" + tableBlockSize +
                ", indexBlockSize=" + indexBlockSize +
                ", tableWriterThreads=" + tableWriterThreads +
                ", tableCacheSize=" + tableCacheSize +
                ", indexCacheSize=" + indexCacheSize +
                ", autoPrintMetrics=" + printMetrics +
                ", tableDirectory=" + tableDirectory +
                ", logDirectory=" + logDirectory +
                ", maxCompactionRate=" + maxCompactionRate +
                ", maxWriteRate=" + maxWriteRate +
                '}';
    }
}
