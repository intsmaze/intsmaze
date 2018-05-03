/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-18 上午8:55:10
 * @version :
 * 
 */

package org.intsmaze.business.user.service;

import java.util.List;
import java.util.Map;

import org.intsmaze.business.user.vo.FDFBUserVo;
import org.intsmaze.persistence.common.HandlerResult;
import org.intsmaze.persistence.pojos.FDFBUser;


public interface IUserService {
	
	public void saveUserList(List<FDFBUser> userList);

	public FDFBUserVo login(FDFBUserVo user);
	
	public int getAllUsersCount(FDFBUserVo user);
	
	public String addUser(FDFBUserVo user);
	
	public List selectAllByPage(FDFBUserVo user);
	
	public FDFBUserVo selectByPrimaryKey(String seqno);
	
	public FDFBUserVo updateUserDynamic(FDFBUserVo user);
	
	public int deleteUserBySeqno(String seqno);
	
	public List getUsersByRoleidAndDepid(String roleid, String depid);
	
	public int updateUser(FDFBUserVo user);
	
	public int getUserCountByUsername(String username);
	
	public List getUserByUsername(String username);
	
	public List getAllUserWithOrgByPage(FDFBUserVo user);
	
	public int getAllUserWithCount(FDFBUserVo user);

	/**
	 * @param id
	 * @return 
	 * 根据用户的id查询该用户的父机构的org_inner_coding
	 */
	Map<String,Object> getUserOrgParentInfo(Map date);

	/**
	 * @param id
	 * @return  orgName ,orgCode,orgId
	 * b.`NAME`,b.new_org_no,b.id from bl_branch b where b.PARENT_ID=
	 * 根据机构id查询该机构的所有子机构的名称和代码
	 */
	List<Map> getFirstOrgInfo(Map date);

	/**
	 * @param id
	 * @return orgName
	 * b.`NAME` as orgName from bl_branch b where b.ID
	 * 根据机构id得到机构名称
	 */
	Map getOrgInfo(Map date);
	
	Map getOrganInfo(String seqno);
	
}
