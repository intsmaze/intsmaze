package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBCodeValue;

public interface FDFBCodeValueMapper {
    
    FDFBCodeValue getValueByKeyAndCode(String key, String code);
    
    FDFBCodeValue getCodeByKeyAndValue(String key, String value);
    
    List<FDFBCodeValue> getAllCodeValue();
    
    List<FDFBCodeValue> getAllCodeValueByKey(String key);
    
    FDFBCodeValue getCodevalueByKyeAndCode(String key, String code);
    
    List getCodeValuesByParentid(String parentid);
}