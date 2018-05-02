package org.intsmaze.persistence.dao;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.AmlVersionMapper;
import org.intsmaze.persistence.pojos.AmlVersion;
import org.springframework.beans.factory.annotation.Autowired;

public class AmlVersionDao extends BaseDao<AmlVersion> {

	@Autowired
	AmlVersionMapper amlVersionMapper;
	
}
