package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBButtonPermit;

public interface FDFBButtonPermitMapper {
	List getButtonPermitByRoleidAndDepidAndPageUrl(FDFBButtonPermit record);
}