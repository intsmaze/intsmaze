package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.Cordoned;
import org.intsmaze.persistence.pojos.Inspect;

public interface InspectMapper {
   /* int deleteByPrimaryKey(String seqno);

    int insert(Inspect inspect);

    int insertSelective(Inspect inspect);

    Inspect selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(Inspect inspect);

    int updateByPrimaryKey(Inspect inspect); */
    
    List selectAllByPage(Inspect inspect);

	int getAllUsersCount(Inspect inspect);
}