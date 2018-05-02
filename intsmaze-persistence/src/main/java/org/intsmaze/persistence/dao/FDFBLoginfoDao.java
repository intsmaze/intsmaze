package org.intsmaze.persistence.dao;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBLoginfoMapper;
import org.intsmaze.persistence.pojos.FDFBLoginfo;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBLoginfoDao extends BaseDao<FDFBLoginfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3388260115660470150L;
	
	@Autowired
	FDFBLoginfoMapper loginfoMapper;

}
