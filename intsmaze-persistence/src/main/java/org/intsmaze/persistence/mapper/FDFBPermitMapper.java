package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBPermit;

public interface FDFBPermitMapper {
    
    List<FDFBPermit> getPermitByParentSeqno(FDFBPermit record);
    
    List<FDFBPermit> getRootPermit(FDFBPermit record);
    
    List<FDFBPermit> getLeafPermit(FDFBPermit record);
}