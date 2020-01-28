package cn.intsmaze.spiler.bean;

import org.jsoup.nodes.Element;

/** 
 * @author:YangLiu
 * @date:2018年4月9日 下午5:37:37 
 * @describe: 
 */
public class MtBean {

   //职位名称
	private String pisition;

   //职位总页数
   private int pageNum=0;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getPisition() {
		return pisition;
	}

	public void setPisition(String pisition) {
		this.pisition = pisition;
	}
}
