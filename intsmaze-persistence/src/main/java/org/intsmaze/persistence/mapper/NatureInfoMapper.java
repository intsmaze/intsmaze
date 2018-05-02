package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.NatureInfo;

public interface NatureInfoMapper {
	
	void insert(NatureInfo natureInfo);
	
	List selectAllByPage(NatureInfo natureInfo);
	
	List selectAll(NatureInfo natureInfo);
	
	int selectAllCount(NatureInfo natureInfo);
	
}