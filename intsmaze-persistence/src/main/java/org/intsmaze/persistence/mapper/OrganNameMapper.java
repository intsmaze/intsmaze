package org.intsmaze.persistence.mapper;

import org.intsmaze.persistence.pojos.OrganName;

public interface OrganNameMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(OrganName record);

    int insertSelective(OrganName record);

    OrganName selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(OrganName record);

    int updateByPrimaryKey(OrganName record);
}