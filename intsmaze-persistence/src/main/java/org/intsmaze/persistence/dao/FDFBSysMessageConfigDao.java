package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBSysMessageConfigMapper;
import org.intsmaze.persistence.pojos.FDFBSysMessageConfig;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBSysMessageConfigDao extends BaseDao<FDFBSysMessageConfig>{

	@Autowired
	private FDFBSysMessageConfigMapper sysMessageConfigMapper;
	
	public List getSysMessageConfigBySheettypeAndSheetstate(
			String sheettype, String sheetstate, String messagetype) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return sysMessageConfigMapper.getSysMessageConfigBySheettypeAndSheetstate(sheettype, sheetstate, messagetype);
	}
}
