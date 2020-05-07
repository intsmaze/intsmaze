package org.intsmaze.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

/** 
 * @author:YangLiu
 * @date:2018年4月10日 下午4:41:41 
 * @describe: 
 */
public class ArticleUtils {
	/**将article转换成document
     * 无非article的值设置document里面去*/
    public static Document articleToDocument(Article article){  
        Document document=new Document();
        IntField idfield=new IntField("id",article.getId(),Store.YES); 
        StringField authorfield=new StringField("author", article.getAuthor(), Store.YES);
        TextField title=new TextField("title", article.getTitle(),Store.YES);      
        document.add(idfield);
        document.add(authorfield);
        document.add(title);
        return document;     
    }
    /**将document转换成article*/
    public static Article documentToArticle(Document document){ 
        Article article = new Article();
        article.setId(Integer.parseInt(document.get("id")));
        article.setTitle(document.get("title"));
        article.setAuthor(document.get("author"));
        return article;
    }  
}
