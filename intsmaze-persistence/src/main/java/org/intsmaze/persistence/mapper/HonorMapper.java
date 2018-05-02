package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.Honor;

public interface HonorMapper {
	Honor selectByPrimaryKey(Honor honor);
   /* int deleteByPrimaryKey(String seqno);

    int insert(Honor honor);

    int insertSelective(Honor honor);

    Honor selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(Honor honor);

    int updateByPrimaryKey(Honor honor);
    */
	
	List selectAllByPage(Honor honor);

	int getAllUsersCount(Honor honor);
}