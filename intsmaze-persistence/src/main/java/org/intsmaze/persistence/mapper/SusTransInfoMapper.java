package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.SusTransInfo;


public interface SusTransInfoMapper {

    SusTransInfo selectByPrimaryKey(String seqno);
    
    SusTransInfo selectBySuDataId(String suDataId);
    
    List selectForC2T(SusTransInfo susTransInfo); 
    //C2T  Customer to TransInfo
    
}