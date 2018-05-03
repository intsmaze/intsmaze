package org.intsmaze.business.login.vo;

import org.intsmaze.business.login.vo.validator.IChangePasswordValidator;
import org.intsmaze.business.vo.BaseVo;


@IChangePasswordValidator
public class FDFBChangePasswordVo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2113614244073471048L;

	private String userid;
	
	private String username;
	
	private String oldpassword;
	
	private String newpassword;
	
	private String confirmpassword;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	
}
