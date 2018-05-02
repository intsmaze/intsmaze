package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.PostUnitMapper;
import org.intsmaze.persistence.pojos.PostUnit;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class PostUnitDao extends BaseDao<PostUnit> {

	@Autowired
	PostUnitMapper postUnitMapper;
	
	public List selectAllByPage(PostUnit postUnit){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return postUnitMapper.selectAllByPage(postUnit);
	}
	
}
