package cn.intsmaze.jbdc.mybatis.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
	private int id;
	private String username;// 用户姓名
	private String sex;// 性别
	private Date birthday;// 生日
	private String address;// 地址
	
	//多个订单
	private List<Orders> orderlist;
	
	
		
	public List<Orders> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(List<Orders> orderlist) {
		this.orderlist = orderlist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", sex=" + sex
				+ ", birthday=" + birthday + ", address=" + address + "]";
	}
	
	
}
