package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.TrainMapper;
import org.intsmaze.persistence.pojos.Train;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class TrainDao extends BaseDao<Train> {
	@Autowired
	TrainMapper trainMapper;
    
    public List selectAllByPage(Train train){
    	DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
    	return trainMapper.selectAllByPage(train);
    }
    
    public int getAllTrainsCount(Train train){
    	DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
    	return trainMapper.getAllTrainsCount(train);
    }
}
