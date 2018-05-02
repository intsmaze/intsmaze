package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.SusData;

public interface SusDataMapper {
	
	List  selectAllByPage(SusData susDataPo);
	
	int getAllSusDataCount(SusData susDataPo);
	//页面显示数据
    List selectAllFirstInspectByPage(SusData susDataPo);
    
    int getAllFirstInspectCount(SusData susDataPo);
    
    List selectAllSecondInspectByPage(SusData susDataPo);
    
    int getAllSecondInspectCount(SusData susDataPo);
	
    List selectAllFinalInspectByPage(SusData susDataPo);
    
    int getAllFinalInspectCount(SusData susDataPo);
    


    
    SusData  updateByPrimaryKeySelectiveForInsert(SusData susDataPo);
} 