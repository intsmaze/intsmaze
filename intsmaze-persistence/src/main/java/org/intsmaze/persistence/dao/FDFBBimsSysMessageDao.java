package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBBimsSysMessageMapper;
import org.intsmaze.persistence.pojos.FDFBBimsSysMessage;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBBimsSysMessageDao extends BaseDao<FDFBBimsSysMessage>{

	@Autowired
	private FDFBBimsSysMessageMapper bimsSysMessageMapper;
	
	public List<FDFBBimsSysMessage> selectMessagesByUseridAndStatus(
			String userid, String status, String messagetype) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return bimsSysMessageMapper.selectMessagesByUseridAndStatus(userid, status, messagetype);
	}
}
