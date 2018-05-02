/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-18 上午8:55:10
 * @version :
 * 
 */

package org.intsmaze.persistence.common;

public class OracleDialect extends Dialect {

	public String getLimitString(String sql, int offset, int limit) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		
		pagingSelect.append(sql);
		
		pagingSelect.append(" ) row_ ) where rownum_ > "+offset+" and rownum_ <= "+(offset + limit));

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}
		
		return pagingSelect.toString();
	}
}

