package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.Cordoned;

public interface CordonedMapper {

	Cordoned selectByPrimaryKey(Cordoned record);
	
    String selectByPrimaryId(Cordoned cordoned);
    
   /* 
    
    List selectAll();
    
    void deleteByPrimaryKey(Cordoned record);
    
    void updateByPrimaryKey(Cordoned record);
    
    int insert(Cordoned record);

    int insertSelective(Cordoned record); */

	List selectAllByPage(Cordoned record);

	int getAllUsersCount(Cordoned user);

    
}