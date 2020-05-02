package com.intsmaze.lsm.db.state;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Generates various file paths for all the files in a database.
 */
public class Paths {

    private static final String TABLE_EXT = ".table";
    private static final String LOG_EXT = ".log";
    private static final String INDEX_EXT = ".index";
    public static final String FILTER_EXT = ".filter";
    private static final String TEMP_EXT = ".temp";

    private final Path logDirectory;
    private final Path tableDirectory;

    public Paths(Path tableDirectory, Path logDirectory) {
        this.tableDirectory = tableDirectory;
        this.logDirectory = logDirectory;
    }

    public Path tablePath(long tableId) {
        return tableDirectory.resolve(tableId + TABLE_EXT);
    }

    public Path indexPath(long tableId) {
        return tableDirectory.resolve(tableId + INDEX_EXT);
    }

    public Path filterPath(long tableId) {
        return tableDirectory.resolve(tableId + FILTER_EXT);
    }

    public Path logPath(long tableId) {
        return tableDirectory.resolve(tableId + LOG_EXT);
    }

    public Path tempPath(long tableId) {
        return tableDirectory.resolve(tableId + TEMP_EXT);
    }

    public SortedSet<Long> tableFileIds() throws IOException {
        return fileIds(tableFilePaths());
    }

    public SortedSet<Long> tempTableFileIds() throws IOException {
        return fileIds(tempFilePaths());
    }

    public SortedSet<Long> logFileIds() throws IOException {
        return fileIds(logFilePaths());
    }

    public SortedSet<Path> tableFilePaths() throws IOException {
        return filePaths(tableDirectory, TABLE_EXT);
    }

    public SortedSet<Path> logFilePaths() throws IOException {
        return filePaths(tableDirectory, LOG_EXT);
    }

    public SortedSet<Path> tempFilePaths() throws IOException {
        return filePaths(tableDirectory, TEMP_EXT);
    }

    private SortedSet<Path> filePaths(Path directory, String extension) throws IOException {
        SortedSet<Path> sortedFilePaths = new TreeSet<Path>();
        //这个调用的jdk的原生方法
        DirectoryStream<Path> filePaths = Files.newDirectoryStream(directory, "*" + extension);

        for (Path filePath : filePaths) {
            sortedFilePaths.add(filePath);
        }

        filePaths.close();

        return sortedFilePaths;
    }

    private SortedSet<Long> fileIds(SortedSet<Path> paths) {
        SortedSet<Long> sortedFileIds = new TreeSet<Long>();

        for (Path path : paths) {
            sortedFileIds.add(tableId(path));
        }

        return sortedFileIds;
    }

    private static long tableId(Path path) {
        String fileName = path.getFileName().toString();
        String id = fileName.split("\\.")[0];
        return Long.parseLong(id);
    }
}