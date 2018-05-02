package org.intsmaze.persistence.util;

import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	/* 
     * (non-Javadoc) 
     * @see javax.sql.CommonDataSource#getParentLogger() 
     */  
    @Override  
    public Logger getParentLogger() {  
        // TODO Auto-generated method stub  
        return null;  
    }  
  
    /** 
     *  
     * override determineCurrentLookupKey 
     * <p> 
     * Title: determineCurrentLookupKey 
     * </p> 
     * <p> 
     * Description: 自动查找datasource 
     * </p> 
     *  
     * @return 
     */  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DBContextHolder.getDbType();  
    }  
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
