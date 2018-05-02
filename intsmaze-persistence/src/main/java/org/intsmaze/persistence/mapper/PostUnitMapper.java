package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.PostUnit;

public interface PostUnitMapper {
	
	List selectAllByPage(PostUnit postUnit);
}