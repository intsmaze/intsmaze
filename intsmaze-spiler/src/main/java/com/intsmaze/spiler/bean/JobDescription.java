package com.intsmaze.spiler.bean;

public class JobDescription {

	/**
	 * 职位名称+职位url后缀（75869328）为联合主键      需要分表
	 * 
	 * 搜索 公司名称（必选）  地区   职位信息
	 */
	
	String tableName;
	
	//招聘职位
	String position;
	
	//招聘职位详情url https://jobs.51job.com/foshan/75869328.html?s=01&t=0
	String positionUrl;
	
	String positionCode;
	
	//公司地址主页
	String companyUrl;

	//公司地址主页
	String companyCode;
	
	//公司名称详写   作为外键 上海青客公共租赁住房租赁经营管理股份有限公司
	String companyName;
	
	//公司名称简写   上海青客公共租赁住房租赁经营管理...
	String companyNameSimple;
	
	//工作地点
	String workSplace;
	
	//薪水
	String salary;
	
	//发布时间
	String time;
	
	String startTime;
	
	String endTime;
	
	String jobDetail;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(String jobDetail) {
		this.jobDetail = jobDetail;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionUrl() {
		return positionUrl;
	}

	public void setPositionUrl(String positionUrl) {
		this.positionUrl = positionUrl;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNameSimple() {
		return companyNameSimple;
	}

	public void setCompanyNameSimple(String companyNameSimple) {
		this.companyNameSimple = companyNameSimple;
	}

	public String getWorkSplace() {
		return workSplace;
	}

	public void setWorkSplace(String workSplace) {
		this.workSplace = workSplace;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "(" +tableName + "," + position  + "," + positionUrl + "," + positionCode  + "," +
				companyUrl + "," +companyCode+","+ companyName + "," + companyNameSimple + "," + workSplace +
				"," + salary + "," + time + ","  + ')';
	}
}
