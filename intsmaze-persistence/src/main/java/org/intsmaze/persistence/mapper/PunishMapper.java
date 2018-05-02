package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.Punish;

public interface PunishMapper {
	Punish selectByPrimaryKey(Punish punish);
	/*
    int deleteByPrimaryKey(String seqno);

    int insert(Punish punish);

    int insertSelective(Punish punish);

    Punish selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(Punish punish);

    int updateByPrimaryKey(Punish punish);*/
	
    List selectAllByPage(Punish punish);

	int getAllUsersCount(Punish punish);	
}