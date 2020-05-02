package com.intsmaze.service.exe.bean;

import java.util.Date;

/** 
 * @author:YangLiu
 * @date:2018年6月12日 上午11:20:47 
 * @describe: 
 */
public class TestGroup {
	
	private long id;
	
	private String cny;
	
	private Date d;
	
	private String partyId;
	
	private Long age;
	
	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCny() {
		return cny;
	}

	public void setCny(String cny) {
		this.cny = cny;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	@Override
	public String toString() {
		return "TestGroup [id=" + id + ", cny=" + cny + ", d=" + d
				+ ", partyId=" + partyId + "]";
	}
	
}
