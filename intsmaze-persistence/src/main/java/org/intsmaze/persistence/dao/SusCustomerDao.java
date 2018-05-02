package org.intsmaze.persistence.dao;

import java.util.List;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.SusCustomerMapper;

import org.intsmaze.persistence.pojos.SusCustomer;
import org.intsmaze.persistence.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class SusCustomerDao extends BaseDao<SusCustomer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -226471690338013305L;
	
	@Autowired
	SusCustomerMapper  susCustomerMapper;
	
	public List selectAllByPage(SusCustomer susCustomer){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susCustomerMapper.selectAllByPage(susCustomer);
	}
	
	public int getAllCount(SusCustomer susCustomer){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susCustomerMapper.getAllCount(susCustomer);
	}
	
	public SusCustomer selectByCustomerId(String customerId){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susCustomerMapper.selectBySuDataId(customerId);
	}
	public SusCustomer selectByPrimaryKey(String seqno){
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return susCustomerMapper.selectByPrimaryKey(seqno);
	}
	
	
	
	
	
	
}
