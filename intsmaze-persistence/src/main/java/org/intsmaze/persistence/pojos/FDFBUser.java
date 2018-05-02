/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-18 上午8:55:10
 * @version :
 * 
 */

package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;

import org.intsmaze.persistence.common.BasePojo;

public class FDFBUser extends BasePojo{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1064275362774256782L;

	private String seqno;

    private String name;

    private String password;
    
    private String username;
    
    private String email;
    
    private String xb;
    
    private String roleid;
    
    private String departmentid;
    
    private String verifyCode;
    
    private Timestamp createon;

    private Timestamp modifyon;

	private String createby;

    private String modifyby;
    
    private String groupid;
    
    private String id;
    
    private String unitInnerCoding;
    
    private String status;
    
    private String empInnerCoding;
    
    private String empNo;
    
    private String newOrgNo;
    
    private String orgName;

	public String getNewOrgNo() {
		return newOrgNo;
	}

	public void setNewOrgNo(String newOrgNo) {
		this.newOrgNo = newOrgNo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getUnitInnerCoding() {
		return unitInnerCoding;
	}

	public void setUnitInnerCoding(String unitInnerCoding) {
		this.unitInnerCoding = unitInnerCoding;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmpInnerCoding() {
		return empInnerCoding;
	}

	public void setEmpInnerCoding(String empInnerCoding) {
		this.empInnerCoding = empInnerCoding;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

//	public String getRoleid() {
//		return roleid;
//	}
//
//	public void setRoleid(String roleid) {
//		this.roleid = roleid;
//	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	
}