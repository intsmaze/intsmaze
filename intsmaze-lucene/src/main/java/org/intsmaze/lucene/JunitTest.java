package org.intsmaze.lucene;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

/** 
 * @author:YangLiu
 * @date:2018年4月10日 下午4:43:36 
 * @describe: 
 */
public class JunitTest {
	 private LuceneDao luceneDao=new LuceneDao();
	    
	    @Test
	    public void addIndex() throws IOException{
	        for(int i=0;i<1;i++){
	            Article article=new Article();
	            article.setId(i);
	            article.setTitle("第六条	银行业金融机构董事会对从业人员的行为管理承担最终责任，并履行以下职责：（一）培育依法合规、诚实守信的从业人员行为管理文化；（二）审批本机构制定的行为守则及其细则；（三）监督高级管理层实施从业人员行为管理。"+i);
	            article.setAuthor("第八条	高级管理层承担从业人员行为管理的实施责任，执行董事会决议，履行以下职责：（一）建立覆盖全面的从业人员行为管理体系，明确相关行为管理部门的职责范围；（二）制定行为守则及其细则，并确保实施；（三）每年将从业人员行为评估结果向董事会报告；（四）建立全机构从业人员管理信息系统。");
	            article.setDate(""+100+i);
	            luceneDao.addIndex(article);
	        }    
	    }
	    
	    
	    
	    @Test
	    public void testDel() throws IOException{
	        luceneDao.delIndex("title", "5");    
	    }
	    
	    
	    @Test
	    public void testUpdate() throws IOException{
	        
	       String fieldName="title";
	       String fieldValue="solr"; 
	       Article article=new Article();   
	       article.setId(9527);   
	       article.setAuthor("王昭厅");   
	       article.setTitle("你厅歌");    
	       luceneDao.updateIndex(fieldName, fieldValue, article);
	        
	    }
	    
	    
	    @Test
	    public void testsearcher() throws Exception{
	        String keywords="高级管理层";
	        List<Article> listArticles=luceneDao.findIndex(keywords,0,10);
	        for(Article article:listArticles){
	            System.out.println(article.getId());
	            System.out.println(article.getAuthor());
	            System.out.println(article.getTitle());
	        }
	    }
}
