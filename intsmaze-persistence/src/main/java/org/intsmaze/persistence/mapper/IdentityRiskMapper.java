package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.IdentityRisk;

public interface IdentityRiskMapper {
	
	public int selectYearNatureCount();

	public int selectYearNatureCounts(IdentityRisk identityRisk);
	
}