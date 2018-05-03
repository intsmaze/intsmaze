package org.intsmaze.business.springmvc.util;

import java.util.ArrayList;

public class DatatableTo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4926196003257888798L;
	private int sEcho;// 参数值是不变的
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	private String[][] aaData;// 返回的数据
	private int iColumns;

	private boolean bRegex;
	private int iDisplayLength;
	private int iDisplayStart;
	private int iSortingCols;
	private int iSortCol_0;//排序列号
	private String sSortDir_0;//排序类型 desc asc
	
	private ArrayList<String> mDataProp;
	
	private String orderStr;
//	private String sColumns;// platform,version,engine,browser,grade
//	private String sSearch;

	public String getOrderStr() {
		String columnName = this.getmDataProp().get(this.getiSortCol_0());
		return columnName + " " + this.getsSortDir_0();
	}

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public boolean isbRegex() {
		return bRegex;
	}

	public void setbRegex(boolean bRegex) {
		this.bRegex = bRegex;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public int getiSortCol_0() {
		return iSortCol_0;
	}

	public void setiSortCol_0(int iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

	public String getsSortDir_0() {
		return sSortDir_0;
	}

	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}

	public ArrayList<String> getmDataProp() {
		return mDataProp;
	}

	public void setmDataProp(ArrayList<String> mDataProp) {
		this.mDataProp = mDataProp;
	}

	
	
}
