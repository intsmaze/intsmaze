package org.intsmaze.lucene;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
public class LuceneUtils { 
    private static Directory directory=null; 
    private static IndexWriterConfig config=null;
    private static Version matchVersion=null; 
    private static Analyzer analyzer=null; 
    static{     
        try {
            directory=FSDirectory.open(new File("intsmaze"));         
            matchVersion=Version.LUCENE_44;     
            analyzer=new StandardAnalyzer(matchVersion);  
            config=new IndexWriterConfig(matchVersion, analyzer);    
        } catch (IOException e) {e.printStackTrace();}  
    }

    /** 返回用于操作索引的对象*/
    public static IndexWriter getIndexWriter() throws IOException{
        IndexWriter indexWriter=new IndexWriter(directory,config);
        return indexWriter;
    }
    /**返回用于读取索引的对象*/
    public static IndexSearcher getIndexSearcher() throws IOException{   
        IndexReader indexReader=DirectoryReader.open(directory);    
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);   
        return indexSearcher;    
    }  
    /**返回lucene 当前使用的版本信息*/
    public static Version getMatchVersion() {
        return matchVersion;
    }
    /**返回lucene 使用的分词器*/
    public static Analyzer getAnalyzer() {
        return analyzer;
    }
}  