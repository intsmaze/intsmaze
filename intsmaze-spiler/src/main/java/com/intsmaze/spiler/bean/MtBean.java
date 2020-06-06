package com.intsmaze.spiler.bean;


/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2018/9/10 19:32
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
