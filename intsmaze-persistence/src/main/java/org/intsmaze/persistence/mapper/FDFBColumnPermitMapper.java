package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBColumnPermit;

public interface FDFBColumnPermitMapper {
    
    List getColumnPermitByRoleidAndDepidAndPageUrl(FDFBColumnPermit record);
}