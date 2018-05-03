package org.intsmaze.business.vo;

import java.sql.Timestamp;

public class FDFBColumnPermitVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2468877986646102766L;

	private String seqno;

	private String pageName;

	private String pageUrl;

	private String roleid;

	private String columnName;

	private String columnDesc;

	private String canModify;

	private String processstatus;

	private int columnOrder;

	private String columnShowValue;

	private String columnHighLight;
	
	private String columnType;
	
	private Timestamp createon;

    private Timestamp modifyon;
    
    private String createby;
    
    private String modifyby;
    
    private String columnCodeKey;

    private String depid;
    
    private String sortable;
    
	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno == null ? null : seqno.trim();
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName == null ? null : pageName.trim();
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl == null ? null : pageUrl.trim();
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid == null ? null : roleid.trim();
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName == null ? null : columnName.trim();
	}

	public String getColumnDesc() {
		return columnDesc;
	}

	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc == null ? null : columnDesc.trim();
	}

	public String getCanModify() {
		return canModify;
	}

	public void setCanModify(String canModify) {
		this.canModify = canModify == null ? null : canModify.trim();
	}

	public String getProcessstatus() {
		return processstatus;
	}

	public void setProcessstatus(String processstatus) {
		this.processstatus = processstatus == null ? null : processstatus
				.trim();
	}

	public int getColumnOrder() {
		return columnOrder;
	}

	public void setColumnOrder(int columnOrder) {
		this.columnOrder = columnOrder;
	}

	public String getColumnShowValue() {
		return columnShowValue;
	}

	public void setColumnShowValue(String columnShowValue) {
		this.columnShowValue = columnShowValue;
	}

	public String getColumnHighLight() {
		return columnHighLight;
	}

	public void setColumnHighLight(String columnHighLight) {
		this.columnHighLight = columnHighLight;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public Timestamp getCreateon() {
		return createon;
	}

	public void setCreateon(Timestamp createon) {
		this.createon = createon;
	}

	public Timestamp getModifyon() {
		return modifyon;
	}

	public void setModifyon(Timestamp modifyon) {
		this.modifyon = modifyon;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public String getModifyby() {
		return modifyby;
	}

	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
	}

	public String getColumnCodeKey() {
		return columnCodeKey;
	}

	public void setColumnCodeKey(String columnCodeKey) {
		this.columnCodeKey = columnCodeKey;
	}

	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}
}