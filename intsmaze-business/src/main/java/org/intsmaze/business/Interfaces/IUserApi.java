package org.intsmaze.business.Interfaces;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-26 上午5:38:08
 * @version :
 * user 模塊向外提供的api調用接口,有新的business module都應該提供這樣一個api,並在module中實現該接口
 */
public interface IUserApi {
	
	/**
	 * 验证用户信息，如果验证通过，将User的信息按照column，value的形式返回map
	 * @param username
	 * @param password
	 * @return map 格式如下：
	 * map.put(Constant.USER_NAME_SESSION, user.getUsername());
	 * map.put(Constant.USER_XM_SESSION, user.getName());
	 * map.put(Constant.USER_DEPARTMENT_SESSION, user.getDepartmentid());
	 * map.put(Constant.USER_ROLE_SESSION, user.getRoleid());
	 * map.put(Constant.USER_XB_SESSION, user.getXb());
	 */
	
	public void saveMqInfo(JSONObject mqInfoJson);
	
	public Map getUserById(String id);
	
	public Map checkUserNameAndPassword(String username, String password);

	public String getPermitByPermitTypeAndRole(String permitType, String roleId, String orderByColumn, String displayType, HttpServletRequest request);
	
	public Map getDepMap();
	
	public Map getRoleMap();
	
	/**
	 * 通过角色表的主键查询对应的角色名称
	 * @param roleid
	 * @return
	 */
	public String getRoleNameByUserid(String userid);
	
	public String getDepNameByDepid(String depid);
	
	/**
	 * 通过角色表的主键拼接字段查询所有角色名称，以","分割
	 * @param roleids '1','2' 形式
	 * @return
	 */
	public String getRoleNameByRoleids(String roleids);
	
	/**
	 * 根据角色主键和公司主键获取对应的用户Map,key为用户seqno，value为姓名
	 * @param roleid
	 * @param depid
	 * @return
	 */
	public Map getUserMapByRoleidAndDepid(String roleid, String depid);
	
	/**
	 * 判断用户是否有对应的角色
	 * @param userid
	 * @param roleid
	 * @return
	 */
	public boolean hasRole(String userid, String roleid);
	
	/**
	 * 根据userid（主键）获取工号
	 * @param seqno
	 * @return
	 */
	public String getUsernameBySeqno(String seqno);
	
	public boolean ChangePassword(String userid, String newpassword);
	
	public String getUserXmBySeqno(String seqno);
	
	public String getUserDepBySeqno(String seqno);
	
	
	/**
	 * 获取所有维修人员和施工人员
	 * @param roleid
	 * @param depid
	 * @return
	 */
	public Map getAllMaintainerAndSgry();
	
	public String getUserXmByUserid(String userid);
}
