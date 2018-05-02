package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBBimsArea;

public interface FDFBBimsAreaMapper {
	List selectAllByPage(FDFBBimsArea record);

	List selectAll();

	int getAllBimsAreasCount(FDFBBimsArea record);

}