package org.intsmaze.business.user.vo;

import java.sql.Timestamp;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.intsmaze.business.springmvc.controller.BusinessUtil;
import org.intsmaze.business.user.vo.validator.IFDFBUserValidator;
import org.intsmaze.business.vo.BaseVo;
import org.intsmaze.business.vo.validator.InsertGroup;
import org.intsmaze.business.vo.validator.UpdateGroup;
import org.intsmaze.core.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;

//@GroupSequence({InsertGroup.class, UpdateGroup.class, FDFBUserVo.class})//如果有的字段两个分组都要验证，则按照此处指定的顺序，先验证insertgroup，再眼恒updategroup 
@IFDFBUserValidator
public class FDFBUserVo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5238591450904092640L;

	@NotNull(message = "user.seqno.null", groups = {UpdateGroup.class}) 
    private String seqno;

//	@NotNull(message = "user.name.null", groups = {InsertGroup.class, UpdateGroup.class}) 
//    @Size(min = 1, max = 16,message="user.name.length.illegle", groups = {InsertGroup.class, UpdateGroup.class})
//	@Pattern(regexp = "[A-Za-z\u4e00-\u9fa5]*", message = "user.name.illegle", groups = {InsertGroup.class, UpdateGroup.class})
	@NotNull(message = "user.name.null") 
    @Size(min = 1, max = 16,message="user.name.length.illegle")
	@Pattern(regexp = "[A-Za-z\u4e00-\u9fa5]*", message = "user.name.illegle")
    private String name;

	private String password;
    
//	@NotNull(message = "user.username.null", groups = {InsertGroup.class, UpdateGroup.class}) 
//    @Size(min = 8, max = 16,message="user.username.length.illegle", groups = {InsertGroup.class, UpdateGroup.class})
	@NotNull(message = "user.username.null") 
    @Size(min = 8, max = 16,message="user.username.length.illegle")
    private String username;
    
    private String email;
    
    private String xb;
    
    private String roleids;
    
    @NotNull(message = "user.roledepid.null")
    @Size(min = 1,message="user.roledepid.null")
    private String departmentid;
    
    private String verifyCode;
    
    private Timestamp createon;

    private Timestamp modifyon;

	private String createby;

    private String modifyby;
    
    private String groupid;
    
    private String xbDesc;
    
    private String depDesc;
    
    private String roleDesc;
    
    private String roletext;
    
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

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	public String getXbDesc()
    {
    	return BusinessUtil.getValueByCodeAndKey(Constant.EDITOR_FIELDS_TYPE_KEY_SEX, this.getXb());
    }
    
    public void setXbDesc(String xbDesc) {
		this.xbDesc = xbDesc;
	}

	public String getDepDesc() {
		return BusinessUtil.getDepNameByDepid(this.getDepartmentid());
	}

	public void setDepDesc(String depDesc) {
		this.depDesc = depDesc;
	}

	public String getRoleDesc() {
		return BusinessUtil.getRoleNameByUserId(this.getSeqno());
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}

	public String getRoletext() {
		return roletext;
	}

	public void setRoletext(String roletext) {
		this.roletext = roletext;
	}
    
}
