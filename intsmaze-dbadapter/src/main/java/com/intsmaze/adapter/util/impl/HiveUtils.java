package com.intsmaze.adapter.util.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.intsmaze.adapter.util.AbstractDataBaseUtils;
/** 
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43 
 * @describe: 
 */
public final class HiveUtils extends AbstractDataBaseUtils {
	
	private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
	
	public HiveUtils() {
	}
	
	public void init() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:hive2://"+this.getUrl()+"/"+this.getDbName(), this.getUser(), this.getPassword());
	}

	public void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
                rs.close();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
                    st.close();
                }
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
			}
		}
	}

	public void free(ResultSet rs, Statement st) {
		try {
			if (rs != null) {
                rs.close();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
                    st.close();
                }
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}

}
