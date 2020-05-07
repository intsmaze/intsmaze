package org.intsmaze.lucene;
//package cn.intsmaze.lucene;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.Field.Store;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TermQuery;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.util.Version;
//import org.junit.Test;
//import org.apache.lucene.queryparser.classic.ParseException;
//import org.apache.lucene.queryparser.classic.QueryParser;
//
//public class TestLucene {
//
//	public static Document article2Document(Article article) {
//		Document doc = new Document();
//		doc.add(new Field("id", article.getId().toString(),
//				TextField.TYPE_STORED));
//		doc.add(new Field("title", article.getTitle(), TextField.TYPE_STORED));
//		doc.add(new Field("content", article.getContent(),
//				TextField.TYPE_STORED));
//		return doc;
//	}
//
//	public static Article document2Ariticle(Document doc) {
//		Article article = new Article();
//		article.setId(Integer.parseInt(doc.get("id")));
//		article.setTitle(doc.get("title"));
//		article.setContent(doc.get("content"));
//		return article;
//	}
//
//	// //使用indexWriter对数据建立索引
//	// @Test
//	// public void testCreateIndex() throws IOException{
//	// //索引库存放的位置(相对目录的文件夹名称)
//	// Directory directory=FSDirectory.open(new File("intsmaze"));
//	// //lucene 当前使用的匹配版本
//	// Version matchVersion=Version.LUCENE_44;
//	// /**
//	// *分词器 (对文本进行分词)
//	// *因为Analyzer是一个抽象类，每个国家使用的语言都不一样。所以要使用对应的实现类。
//	// *中国的应用需要对中文进行分词，所以我们 new 中文对应的分词器的实现。
//	// *分词器jar包是单独写的，与Lucene核心包无关，所以不同的分词器分词的规则不一样，而且这些分词器要对索引库文档进行分词，所以要给分词器Lucene的版本号，以免不兼容吧。
//	// * */
//	// Analyzer analyzer=new StandardAnalyzer(matchVersion);
//	// //索引写入的配置
//	// IndexWriterConfig writerConfig=new IndexWriterConfig(matchVersion,
//	// analyzer);
//	// //构建用于操作索引的类
//	// IndexWriter indexWriter=new IndexWriter(directory, writerConfig);
//	//
//	// //索引库里面的要遵守一定的结构，该结构叫document（索引文档），（索引结构类似与数据库中表的一个记录）
//	// Document doc=new Document();
//	// //索引document
//	// 里面也有很多的字段（数据整理成结构化数据放到一个索引文档中的，比如非结构化数据网页，就会有字段存储网页内容，有字段存储网页链接，然后把这些整理成一起放到索引文档中）
//	//
//	// /**
//	// * 1:字段的名称
//	// * 2：字段对应的值
//	// * 3：该字段在索引库当中是否存储
//	// * */
//	// //不同数据类型对应索引document不同字段
//	// Field field=new IntField("id", 1, Store.YES);
//	// Field title=new StringField("title",
//	// "洋洋哥，本科，软件工程专业，国家软考：程序员，软件设计师，软件架构师。", Store.YES);
//	// Field content=new TextField("content",
//	// "一、《中国银行业监督管理委员会关于印发<农村信用社省（自治区、直辖市）联合社管理暂行规定>的通知》（银监发〔2003〕14号）等499件主要规范性文件（见附件1），继续有效。",Store.YES);
//	// Field content1=new TextField("content",
//	// "二、《中国银行业监督管理委员会关于印发<农村商业银行管理暂行规定>和<农村合作银行管理暂行规定>的通知》（银监发〔2003〕10号）等50件规范性文件（见附件2），自公告之日起废止。",Store.YES);
//	// Field content2=new TextField("content",
//	// "三、《中国银行业监督管理委员会关于加强信托投资公司风险监管防范交易对手风险的通知》（银监发〔2004〕93号）等34件规范性文件（见附件3），自公告之日起失效。",Store.YES);
//	// doc.add(field);
//	// doc.add(title);
//	// doc.add(content);
//	// doc.add(content1);
//	// doc.add(content2);
//	// indexWriter.addDocument(doc);
//	// indexWriter.close();
//	// }
//	//
//	//
//	// // 使用indexSearcher 对数据进行搜索
//	// @Test
//	// public void testSearcher() throws IOException, ParseException{
//	//
//	// //索引存放的位置
//	// Directory directory=FSDirectory.open(new File("intsmaze"));
//	//
//	// IndexReader indexReader=DirectoryReader.open(directory);
//	// //通过indexSearcher 去检索索引目录
//	// IndexSearcher indexSearcher=new IndexSearcher(indexReader);
//	//
//	// //我们以后只要根据索引查找，整个过程要分两次
//	// //这个是一个搜索条件，通过定义条件来进行查找
//	// //term 我需要根据那个字段进行检索字段对应的值
//	// //TermQuery是lucene查询中最基本的一种原子查询，从它的名字Term我们可以看出，它只能针对一个字段进行查询。
//	//
//	//
//	// //lucene 当前使用的匹配版本
//	// Version matchVersion=Version.LUCENE_44;
//	// Analyzer analyzer=new StandardAnalyzer(matchVersion);
//	//
//	// QueryParser parser = new QueryParser(matchVersion, "desc", analyzer);
//	// //查询解析器
//	// Query query = parser.parse("洋洋哥"); //通过解析要查询的String，获取查询对象
//	//
//	// //搜索索引目录，找到符合query条件的前面100条记录
//	// TopDocs topDocs=indexSearcher.search(query,100);
//	// System.out.println("总记录数==="+topDocs.totalHits);
//	//
//	// ScoreDoc scoreDocs[]=topDocs.scoreDocs;
//	// //返回一个击中
//	// for(ScoreDoc scoreDoc:scoreDocs){
//	// float score = scoreDoc.score; // 相关度得分
//	// int docId = scoreDoc.doc; // Document的内部编号
//	// System.out.println(score+"---"+docId);
//	// Document document=indexSearcher.doc(docId);
//	// System.out.println(document.get("id"));
//	// System.out.println(document.get("title"));
//	// System.out.println(document.get("content"));
//	// // 等价于
//	// System.out.println(document.getField("content").stringValue());
//	// }
//	// }
//}