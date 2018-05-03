package org.intsmaze.business.vo;

import java.io.Serializable;

import org.intsmaze.core.util.SqlInjectFilterUtil;

/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-23 上午7:58:08
 * @version :
 * 基础vo类，确保persistence无业务逻辑，只负责与数据库交互
 */
public class BaseVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2278944441145957113L;

	private String DT_RowId;
	
	private boolean isValidSuccess;
	
	private String orderByStr;
	
	private String beginDate;

	private String endDate;
	
	private String validMsg;
	
	public String getDT_RowId() {
		return DT_RowId;
	}

	public void setDT_RowId(String dT_RowId) {
		DT_RowId = dT_RowId;
	}
	
	public boolean isValidSuccess() {
		return isValidSuccess;
	}

	public void setValidSuccess(boolean isValidSuccess) {
		this.isValidSuccess = isValidSuccess;
	}

	public String getOrderByStr() {
		//排序内容需要防止sql注入
		return orderByStr;
	}

	public void setOrderByStr(String orderByStr) {
		this.orderByStr = orderByStr;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getValidMsg() {
		return validMsg;
	}

	public void setValidMsg(String validMsg) {
		this.validMsg = validMsg;
	}
	
}
