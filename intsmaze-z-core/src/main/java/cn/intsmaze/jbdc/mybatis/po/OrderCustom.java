package cn.intsmaze.jbdc.mybatis.po;

import java.util.Date;

/**
 * <p>Description:订单扩展对象，用于完成订单和用户查询结果 映射 </p>
 */
public class OrderCustom extends Orders {
	
	//补充用户信息
	private String username;
	
	private String sex;
	
	private String address;
	
	private Date birthday;
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		
		return super.toString()+"username=" + username + ", sex=" + sex+"。";
	}
	
	

}
