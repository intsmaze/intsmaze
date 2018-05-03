package org.intsmaze.business.user.service.impl;

import java.util.List;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.user.service.IDepartmentService;
import org.intsmaze.business.user.vo.FDFBDepartmentVo;
import org.intsmaze.core.exception.FDFBMonitorException;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.DateUtil;
import org.intsmaze.core.util.StringUtil;
import org.intsmaze.persistence.common.HandlerResult;
import org.intsmaze.persistence.dao.FDFBDepartmentDao;
import org.intsmaze.persistence.pojos.FDFBDepartment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBDepServiceImpl extends CommonService implements IDepartmentService {

	private static Logger logger = Logger.getLogger(FDFBDepServiceImpl.class);
	
	@Autowired
	FDFBDepartmentDao departmentDao;
	
	public int getAllFDFBDepsCount(FDFBDepartmentVo record) {
		// TODO Auto-generated method stub
		FDFBDepartment role = new FDFBDepartment();
		role = (FDFBDepartment) copyBean(record, role);
		return departmentDao.getAllFDFBDepsCount(role);
	}

	public void addFDFBDep(FDFBDepartmentVo user) {
		// TODO Auto-generated method stub
		FDFBDepartment userPo = new FDFBDepartment();
		userPo = (FDFBDepartment) copyBean(user, userPo);
		userPo.setCreateon(DateUtil.getTimestamp());
		userPo.setModifyon(DateUtil.getTimestamp());
		int result = departmentDao.insert(userPo);
	}

	public List selectAllByPage(FDFBDepartmentVo user) {
		// TODO Auto-generated method stub
		FDFBDepartment userPo = new FDFBDepartment();
		userPo = (FDFBDepartment) copyBean(user, userPo);
		HandlerResult rs = new HandlerResult();
		rs.setResultObj(departmentDao.selectAllByPage(userPo));
		List queryList = (List) rs.getResultObj();
		
		return copyList(queryList, FDFBDepartment.class, FDFBDepartmentVo.class);
	}

	public FDFBDepartmentVo selectByPrimaryKey(String seqno) {
		// TODO Auto-generated method stub
		if("".equals(StringUtil.isNullString(seqno)))
		{
			return null;
		}
		FDFBDepartment userPo = departmentDao.selectByPrimaryKey(seqno);
		FDFBDepartmentVo user = new FDFBDepartmentVo();
		return (FDFBDepartmentVo) copyBean(userPo, user);
	}

	public FDFBDepartmentVo updateDepDynamic(FDFBDepartmentVo user) {
		// TODO Auto-generated method stub

		FDFBDepartment userPo = new FDFBDepartment();
		userPo = (FDFBDepartment) copyBean(user, userPo);
		FDFBDepartmentVo getUservo = this.selectByPrimaryKey(user.getSeqno());
		userPo.setNewMofifyon(DateUtil.getTimestamp());
		userPo.setModifyon(getUservo.getModifyon());//控制并发时的同时修改
		int count = departmentDao.updateByPrimaryKeySelective(userPo);
		
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
		// TODO Auto-generated method stub
		return copyList(departmentDao.selectAll(), FDFBDepartment.class, FDFBDepartmentVo.class);
	}

	public int deleteDepBySeqno(String seqno) {
		// TODO Auto-generated method stub
		return departmentDao.deleteByPrimaryKey(seqno);
	}
	
}
