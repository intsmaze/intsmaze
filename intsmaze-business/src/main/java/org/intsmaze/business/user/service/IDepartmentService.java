package org.intsmaze.business.user.service;

import java.util.List;

import org.intsmaze.business.user.vo.FDFBDepartmentVo;

public interface IDepartmentService {

	public int getAllFDFBDepsCount(FDFBDepartmentVo record);

	public void addFDFBDep(FDFBDepartmentVo record);

	public List selectAllByPage(FDFBDepartmentVo record);

	public FDFBDepartmentVo selectByPrimaryKey(String seqno);
	
	public FDFBDepartmentVo updateDepDynamic(FDFBDepartmentVo record);
	
	public List selectAll();
	
	public int deleteDepBySeqno(String seqno);
}
