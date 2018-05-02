package org.intsmaze.persistence.common;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-20 上午7:38:21
 * @version :
 * 
 */
public class BasePojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2135885641940748739L;

	private String orderByStr;

	private Timestamp newMofifyon;// 乐观锁

	private String beginDate;

	private String endDate;

	public Timestamp getNewMofifyon() {
		return newMofifyon;
	}

	public void setNewMofifyon(Timestamp newMofifyon) {
		this.newMofifyon = newMofifyon;
	}

	public String getOrderByStr() {
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

}
