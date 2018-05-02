package org.intsmaze.persistence.mapper;

import java.util.List;


import org.intsmaze.persistence.pojos.SusCustomer;

public interface SusCustomerMapper {

	public SusCustomer selectByPrimaryKey(String seqno);
    public SusCustomer selectBySuDataId(String suDataId);
    public SusCustomer selectByCustomerId(String customerId);    
    
	public List selectAllByPage(SusCustomer susCustomer);
	public int getAllCount(SusCustomer susCustomer);
	
    

}