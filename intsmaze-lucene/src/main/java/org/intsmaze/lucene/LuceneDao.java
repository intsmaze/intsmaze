package org.intsmaze.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

/** 
 * @author:YangLiu
 * @date:2018年4月10日 下午4:42:39 
 * @describe: 
 */
public class LuceneDao {
	 /**
     * 增加索引
     */
    public void addIndex(Article article) throws IOException {
        IndexWriter indexWriter = LuceneUtils.getIndexWriter();
        Document doc = ArticleUtils.articleToDocument(article);
        indexWriter.addDocument(doc);
        indexWriter.close();
    }
     /** 删除索引，字段值符合条件的document都被删除，在和数据库同步中，删除字段是根据id进行删除。*/
    public void delIndex(String fieldName, String fieldValue)  throws IOException {
        IndexWriter indexWriter = LuceneUtils.getIndexWriter();
        Term term = new Term(fieldName, fieldValue);
        indexWriter.deleteDocuments(term);
        indexWriter.close();
    }
    /**更新索引库(方法内部是先删除后创建）
      维护倒排索引有三个操作：添加、删除和更新文档。但是更新操作需要较高的代价。因为文档修改后（即使是很小的修改），就可能会造成文档中的很多的关键词的位置都发生了变化，这就需要频繁的读取和修改记录，这种代价是相当高的。因此，一般不进行真正的更新操作，而是使用“先删除，再创建”的方式代替更新操作。
*/
    public void updateIndex(String fieldName, String fieldValue, Article article) 
            throws IOException {
        IndexWriter indexWriter = LuceneUtils.getIndexWriter();
        Term term = new Term(fieldName, fieldValue);
        Document doc = ArticleUtils.articleToDocument(article);
        /** 1:设置更新的条件 2：设置更新的内容的对象 */
        indexWriter.updateDocument(term, doc);
        indexWriter.close();
    }

     /**
     * 显示第一页的数据 0,10 显示第二页的数据 10,20 显示第三页的数据 20,30
     * 显示效果自己实现，这里要想显示20—30之间的数据，其实还是要查前30个数据，只是最后显示出20-30之间的数据。
     */
    public List<Article> findIndex(String keywords, int start, int rows)
            throws Exception {    
        IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
        
        // 需要根据那几个字段进行检索(查询哪几个字段包含搜索条件)
        String fields[] = { "title","author"};
        // 多字段查询
        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),   
                                                    fields,LuceneUtils.getAnalyzer());
        Query query = queryParser.parse(keywords);
        // 这里检索的是索引目录，会把整个索引目录都读取一边
        // 检索符合query前面N条记录
        TopDocs topDocs = indexSearcher.search(query, start + rows);
        
        System.out.println("总记录数==total==" + topDocs.totalHits);
        ScoreDoc scoreDocs[] = topDocs.scoreDocs;
        
     
        List<Article> articlelist = new ArrayList<Article>();
        // scoreDocs.length vs（比较） start+rows 取小值
        // 在java jdk 里面提供了一个类，可以用来比较两个数字类型的值，取小值
        int endResult = Math.min(scoreDocs.length, start + rows);
        for (int i = start; i < endResult; i++) {
            // docID（lucene的索引库里面有很多的document，lucene 为每个document 定义一个编号，唯一标识）
            // 自增长
            int docID = scoreDocs[i].doc;
            System.out.println("编号的标识===" + docID);
            Document document = indexSearcher.doc(docID);
            Article article=ArticleUtils.documentToArticle(document);      
            articlelist.add(article);
        }
        return articlelist;
    }
}
