/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-18 上午8:55:10
 * @version :
 * 
 */

package org.intsmaze.business.user.service.impl;

import java.util.List;
import java.util.Map;
import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.user.service.IUserService;
import org.intsmaze.business.user.vo.FDFBUserVo;
import org.intsmaze.core.exception.FDFBMonitorException;
import org.intsmaze.core.util.DateUtil;
import org.intsmaze.core.util.StringUtil;
import org.intsmaze.persistence.common.HandlerResult;
import org.intsmaze.persistence.dao.FDFBUserDao;
import org.intsmaze.persistence.pojos.FDFBUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBUserServiceImpl extends CommonService implements IUserService{

	private static Logger logger = Logger.getLogger(FDFBUserServiceImpl.class);
	@Autowired
	private FDFBUserDao userDao;
	
	 
	public void saveUserList(List<FDFBUser> userList){
		for(FDFBUser u:userList){
			
			FDFBUser user = new FDFBUser();
			user.setEmpInnerCoding(u.getEmpInnerCoding());
			
			List list = userDao.selectAllByPage(user);
			
			if(list.size() < 1){
				//u.设置权限
				userDao.insertSelective(u);
			}else{
				user = (FDFBUser) list.get(0);
				u.setSeqno(user.getSeqno());
				
				userDao.updateByPrimaryKeySelective(u);
				
			}
			
		}
	}
	
	 
	public FDFBUserVo login(FDFBUserVo user) {
		// TODO Auto-generated method stub
		FDFBUser userPo = new FDFBUser();
		userPo = (FDFBUser) copyBean(user, userPo);
		userPo = userDao.selectByUsernameAndPassword(userPo);
		if(userPo != null)
		{
			user = (FDFBUserVo) copyBean(userPo, user);
			return user;
		}
		else
		{
			return null;
		}
	}

	 
	public int getAllUsersCount(FDFBUserVo user) {
		// TODO Auto-generated method stub
		FDFBUser userPo = new FDFBUser();
		userPo = (FDFBUser) copyBean(user, userPo);
		return userDao.getAllUsersCount(userPo);
	}

	 
	public String addUser(FDFBUserVo user) {
		// TODO Auto-generated method stub
		FDFBUser userPo = new FDFBUser();
		userPo = (FDFBUser) copyBean(user, userPo);
		userPo.setCreateon(DateUtil.getTimestamp());
		userPo.setModifyon(DateUtil.getTimestamp());
		int result = userDao.insert(userPo);
		return userPo.getSeqno();
//		throw new RuntimeException("Error"); //测试事务成功
	}
	
	/**
	 * 采用本地线程的方式分页
	 * @return
	 */
	public List selectAllByPage(FDFBUserVo user){
		
		FDFBUser userPo = new FDFBUser();
		userPo = (FDFBUser) copyBean(user, userPo);
		HandlerResult rs = new HandlerResult();
		rs.setResultObj(userDao.selectAllByPage(userPo));
		List queryList = (List)rs.getResultObj();
		return copyList(queryList, FDFBUser.class, FDFBUserVo.class);
	}
	
	 
	public List getAllUserWithOrgByPage(FDFBUserVo user){
		
		FDFBUser userPo = new FDFBUser();
		userPo = (FDFBUser) copyBean(user, userPo);
		HandlerResult rs = new HandlerResult();
		rs.setResultObj(userDao.getAllUserWithOrgByPage(userPo));
		List queryList = (List)rs.getResultObj();
		return copyList(queryList, FDFBUser.class, FDFBUserVo.class);
	}
	
	
	 
	public int getAllUserWithCount(FDFBUserVo user){
		FDFBUser userPo = new FDFBUser();
		userPo = (FDFBUser) copyBean(user, userPo);
		return userDao.getAllUserWithCount(userPo);
	}

	 
	public FDFBUserVo selectByPrimaryKey(String seqno) {
		// TODO Auto-generated method stub
		if("".equals(StringUtil.isNullString(seqno)))
		{
			return null;
		}
		FDFBUser userPo = userDao.selectByPrimaryKey(seqno);
		FDFBUserVo user = new FDFBUserVo();
		return (FDFBUserVo) copyBean(userPo, user);
	}

	 
	public FDFBUserVo updateUserDynamic(FDFBUserVo user) {
		// TODO Auto-generated method stub

		FDFBUser userPo = new FDFBUser();
		userPo = (FDFBUser) copyBean(user, userPo);
		FDFBUserVo getUservo = this.selectByPrimaryKey(user.getSeqno());
		userPo.setNewMofifyon(DateUtil.getTimestamp());
		userPo.setModifyon(getUservo.getModifyon());//控制并发时的同时修改
		int count = userDao.updateByPrimaryKeySelective(userPo);
		
		if (count < 1) {
			//TODO:concurrency exception后的处理
			userPo.setNewMofifyon(null);
			throw new FDFBMonitorException("Concurrency Exception happend!");
		}
		//TODO:正常更新后的处理（audit log等）
		logger.info("=============================test===============乐观锁===============");
		return selectByPrimaryKey(user.getSeqno());
		
	}

	 
	public int deleteUserBySeqno(String seqno) {
		// TODO Auto-generated method stub
		int i = userDao.deleteByPrimaryKey(seqno);
		return i;
	}

	 
	public List getUsersByRoleidAndDepid(String roleid, String depid) {
		// TODO Auto-generated method stub
		return copyList(userDao.getUsersByRoleidAndDepid(roleid, depid), FDFBUser.class, FDFBUserVo.class);
	}

	 
	public int updateUser(FDFBUserVo user) {
		// TODO Auto-generated method stub
		FDFBUser userPo = new FDFBUser();
		userPo = (FDFBUser) copyBean(user, userPo);
		return userDao.updateByPrimaryKey(userPo);
	}

	 
	public int getUserCountByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.getUserCountByUsername(username);
	}

	 
	public List getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return copyList(userDao.getUserByUsername(username), FDFBUser.class, FDFBUserVo.class);
	}
	
	 
	public Map getUserOrgParentInfo(Map date) {
		return userDao.getUserOrgParentInfo(date);
	}
	
	
	 
	public List<Map> getFirstOrgInfo(Map date) {
		return userDao.getFirstOrgInfo(date);
	}

	
	 
	public Map getOrgInfo(Map date) {
		return userDao.getOrgInfo(date);
	}
	
	 
	public Map getOrganInfo(String seqno){
		return userDao.getOrganInfo(seqno);
	}
	
	
}
