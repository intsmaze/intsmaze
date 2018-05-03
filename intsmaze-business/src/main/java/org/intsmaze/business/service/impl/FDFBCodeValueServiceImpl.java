/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-18 上午8:55:10
 * @version :
 * 
 */

package org.intsmaze.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.intsmaze.business.service.CommonService;
import org.intsmaze.business.service.ICodeValueService;
import org.intsmaze.business.vo.FDFBCodeValueVo;
import org.intsmaze.core.util.DateUtil;
import org.intsmaze.persistence.dao.FDFBCodeValueDao;
import org.intsmaze.persistence.pojos.FDFBCodeValue;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;


public class FDFBCodeValueServiceImpl extends CommonService implements ICodeValueService{
	
	private static Logger logger = Logger.getLogger(FDFBCodeValueServiceImpl.class);

	@Autowired
	private FDFBCodeValueDao codeValueDao;

	
	 
	public String getValueByKeyAndCode(String key, String code) {
		// TODO Auto-generated method stub
		logger.debug("I'm querying the DB................");
		FDFBCodeValue cv = codeValueDao.getValueByKeyAndCode(key, code);
		if(cv==null)
		{
			return "";
		}
		return cv.getcValue();
	}
	
	 
	public Map<String,String> getAllCodeValue() {
		// TODO Auto-generated method stub
		logger.debug("----------------------------enter getAllCodeValue----------------------------");
		List list = codeValueDao.getAllCodeValue();
		if(list!=null && list.size() > 0)
		{
			Map result = new HashMap<String, String>(list.size());
			for(int i=0,n=list.size(); i<n; i++)
			{
				FDFBCodeValue cv = (FDFBCodeValue)list.get(i);
				result.put(cv.getcKey()+"#"+cv.getcCode(), cv.getcValue());
			}
			return result;
		}
		return null;
	}

	
	 
	public List<FDFBCodeValueVo> getAllCodeValueByKey(String key) {
		// TODO Auto-generated method stub
		List list = codeValueDao.getAllCodeValueByKey(key);
		return copyList(list, FDFBCodeValue.class, FDFBCodeValueVo.class);
	}
	
	@CacheEvict(key="'KeyCode'", value = "FDFB.application.cache")
	public void removeKeyCodeCache()
	{
		
	}
	
	@CacheEvict(key="'CodeListForKey'", value = "FDFB.application.cache")
	public void removeCodeListForKeyCache()
	{
		
	}
	
	@CacheEvict(key="'AllCodeList'", value = "FDFB.application.cache")
	public void removeAllCodeListCache()
	{
		
	}

	 
	public Map getAllCodeValueMapByKey(String key) {
		// TODO Auto-generated method stub
		List list = codeValueDao.getAllCodeValueByKey(key);
		if(list != null)
		{
			Map resultMap = new HashMap(list.size());
			for(int i=0,n=list.size(); i<n; i++)
			{
				FDFBCodeValue cv = (FDFBCodeValue)list.get(i);
				resultMap.put(cv.getcCode(), cv.getcValue());
			}
			return resultMap;
		}
		return new HashMap(0);
	}

	 
	public String getCodeByKeyAndValue(String key, String value) {
		// TODO Auto-generated method stub
		FDFBCodeValue cv = codeValueDao.getCodeByKeyAndValue(key, value);
		if(cv==null)
		{
			return "";
		}
		return cv.getcCode();
	}

	 
	public FDFBCodeValueVo getCodevalueByKyeAndCode(String key, String code) {
		// TODO Auto-generated method stub
		FDFBCodeValueVo codeValueVo = new FDFBCodeValueVo();
		codeValueVo = (FDFBCodeValueVo)copyBean(codeValueDao.getCodevalueByKyeAndCode(key, code), codeValueVo);
		return codeValueVo;
	}

	 
	public List getCodeValuesByParentid(String parentid) {
		// TODO Auto-generated method stub
		return copyList(codeValueDao.getCodeValuesByParentid(parentid), FDFBCodeValue.class, FDFBCodeValueVo.class);
	}

	 
	public FDFBCodeValueVo getCodevalueBySeqno(String seqno) {
		// TODO Auto-generated method stub
		FDFBCodeValue codeValue = codeValueDao.selectByPrimaryKey(seqno);
		if(codeValue!=null)
		{
			FDFBCodeValueVo vo = new FDFBCodeValueVo();
			vo = (FDFBCodeValueVo)copyBean(codeValue, vo);
			return vo;
		}
		return new FDFBCodeValueVo();
	}

	 
	public String addCodevalue(FDFBCodeValueVo record) {
		// TODO Auto-generated method stub
		FDFBCodeValue pojo = new FDFBCodeValue();
		pojo = (FDFBCodeValue)copyBean(record, pojo);
		pojo.setCreateon(DateUtil.getTimestamp());
		pojo.setModifyon(DateUtil.getTimestamp());
		codeValueDao.insert(pojo);
		return pojo.getSeqno();
	}

	 
	public int modifyCodevalue(FDFBCodeValueVo record) {
		// TODO Auto-generated method stub
		FDFBCodeValue pojo = new FDFBCodeValue();
		pojo = (FDFBCodeValue)copyBean(record, pojo);
		pojo.setModifyon(DateUtil.getTimestamp());
		return codeValueDao.updateByPrimaryKey(pojo);
	}
	
}
