package org.intsmaze.lucene;

import java.util.List;

/** 
 * @author:YangLiu
 * @date:2018年4月10日 下午4:33:22 
 * @describe: 
 */
public class QueryResult {
	private int count;  
    private List list;  
  
    public QueryResult() {  
        super();  
    }  
  
    public QueryResult(int count, List list) {  
        super();  
        this.count = count;  
        this.list = list;  
    }  
}
