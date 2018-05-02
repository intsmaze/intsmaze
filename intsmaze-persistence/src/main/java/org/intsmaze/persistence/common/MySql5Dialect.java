/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-18 上午8:55:10
 * @version :
 * 
 */

package org.intsmaze.persistence.common;


public class MySql5Dialect extends Dialect{
	
	protected static final String SQL_END_DELIMITER = ";";
	
	public String getLimitString(String sql, boolean hasOffset) {
		return MySql5PageHepler.getLimitString(sql,-1,-1);
	}

	public String getLimitString(String sql, int offset, int limit) {
		return MySql5PageHepler.getLimitString(sql, offset, limit);
	}

	public boolean supportsLimit() {
		return true;
	}
}
