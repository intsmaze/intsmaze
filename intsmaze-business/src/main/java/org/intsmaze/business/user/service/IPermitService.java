package org.intsmaze.business.user.service;

import java.util.List;

import org.intsmaze.business.vo.FDFBPermitVo;
import org.springframework.cache.annotation.Cacheable;

public interface IPermitService {

//	@Cacheable(value="FDFB.application.cache",key="'MenuCache'")//使用的都是springMvc自带的cache声明，可以方便的更换cache
	public List<FDFBPermitVo> getPermitByParentSeqno(String seqno, String permittype, String orderByStr, String roleId);
	
//	@Cacheable(value="FDFB.application.cache",key="'RootMenuCache'")//使用的都是springMvc自带的cache声明，可以方便的更换cache
	public List<FDFBPermitVo> getRootPermit(String permittype,  String orderByStr, String roleId);
	
	public List<FDFBPermitVo> getLeafPermit(String permittype, String orderByStr, String roleId);
}
