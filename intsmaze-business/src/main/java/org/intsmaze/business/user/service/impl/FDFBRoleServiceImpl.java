package org.intsmaze.business.user.service.impl;

import java.util.List;
import java.util.Map;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.user.service.IRoleService;
import org.intsmaze.business.user.vo.FDFBRoleVo;
import org.intsmaze.core.exception.FDFBMonitorException;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.DateUtil;
import org.intsmaze.persistence.common.HandlerResult;
import org.intsmaze.persistence.dao.FDFBRoleDao;
import org.intsmaze.persistence.pojos.FDFBRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBRoleServiceImpl  extends CommonService implements IRoleService {

	private static Logger logger = Logger.getLogger(FDFBRoleServiceImpl.class);

	@Autowired
	FDFBRoleDao fdfbRoleDao;
	
	 
	public int getAllFDFBRolesCount(FDFBRoleVo user) {
		// TODO Auto-generated method stub
		FDFBRole role = new FDFBRole();
		role = (FDFBRole) copyBean(user, role);
		return fdfbRoleDao.getAllFDFBRolesCount(role);
	}

	 
	public void addFDFBRole(FDFBRoleVo user) {
		// TODO Auto-generated method stub
		FDFBRole userPo = new FDFBRole();
		userPo = (FDFBRole) copyBean(user, userPo);
		userPo.setCreateon(DateUtil.getTimestamp());
		userPo.setModifyon(DateUtil.getTimestamp());
		int result = fdfbRoleDao.insert(userPo);
	}

	 
	public List selectAllByPage(FDFBRoleVo user) {
		// TODO Auto-generated method stub
		FDFBRole userPo = new FDFBRole();
		userPo = (FDFBRole) copyBean(user, userPo);
		HandlerResult rs = new HandlerResult();
		rs.setResultObj(fdfbRoleDao.selectAllByPage(userPo));
		List queryList = (List) rs.getResultObj();
		return copyList(queryList, FDFBRole.class, FDFBRoleVo.class);
	}

	 
	public FDFBRoleVo selectByPrimaryKey(String seqno) {
		// TODO Auto-generated method stub
		FDFBRole userPo = fdfbRoleDao.selectByPrimaryKey(seqno);
		FDFBRoleVo user = new FDFBRoleVo();
		return (FDFBRoleVo) copyBean(userPo, user);
	}

	 
	public FDFBRoleVo updateRoleDynamic(FDFBRoleVo user) {
		// TODO Auto-generated method stub

		FDFBRole userPo = new FDFBRole();
		userPo = (FDFBRole) copyBean(user, userPo);
		FDFBRoleVo getUservo = this.selectByPrimaryKey(user.getSeqno());
//		userPo.setNewMofifyon(DateUtil.getTimestamp());
		userPo.setModifyon(getUservo.getModifyon());//控制并发时的同时修改
		int count = fdfbRoleDao.updateByPrimaryKeySelective(userPo);
		
		if (count < 1) {
			//TODO:concurrency exception后的处理
			userPo.setNewMofifyon(null);
			throw new FDFBMonitorException("Concurrency Exception happend!");
		}
		//TODO:正常更新后的处理（audit log等）
		logger.info("=============================test===============乐观锁===============");
		return selectByPrimaryKey(user.getSeqno());
		
	}

	 
	public List selectAll() {
		return copyList(fdfbRoleDao.selectAll(), FDFBRole.class, FDFBRoleVo.class);
	}

	 
	public List<FDFBRoleVo> selectRolesByRoleIds(String roleIds) {
		// TODO Auto-generated method stub
		FDFBRole fr = new FDFBRole();
		fr.setRoleIds(roleIds);
		return copyList(fdfbRoleDao.selectRolesByRoleIds(fr), FDFBRole.class, FDFBRoleVo.class);
	}

	 
	public int deleteUserBySeqno(String seqno) {
		// TODO Auto-generated method stub
		return fdfbRoleDao.deleteByPrimaryKey(seqno);
	}

	 
	public List<Map> selectUserList(Map mapBean) {
		// TODO Auto-generated method stub
		return fdfbRoleDao.selectUserList(mapBean);
	}
	
}
