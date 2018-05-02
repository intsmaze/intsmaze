package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.LegalInfo;

public interface LegalInfoMapper {
	
	void insert(LegalInfo legalInfo);
	
	List selectAllByPage(LegalInfo legalInfo);
	
	List selectAll(LegalInfo legalInfo);
	
	int selectAllCount(LegalInfo legalInfo);
}