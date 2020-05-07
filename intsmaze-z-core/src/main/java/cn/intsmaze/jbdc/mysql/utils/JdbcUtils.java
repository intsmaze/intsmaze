package cn.intsmaze.jbdc.mysql.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public final class JdbcUtils {
	
	private String url = "jdbc:mysql://localhost:3306/test";
	private String user = "root";
	private String password = "intsmaze";
	
//	private String url = "jdbc:mysql://rm-p2ug5c4l1f9003732.mysql.rds.aliyuncs.com:3306/cathay_aml";
//	private String user = "rds";
//	private String password = "Cathay123";

	// private static JdbcUtils instance = new JdbcUtils();
	private static JdbcUtils instance = null;

	private JdbcUtils() {
	}

	public static JdbcUtils getInstance() {
		if (instance == null) {
			synchronized (JdbcUtils.class) {
				if (instance == null) {
					instance = new JdbcUtils();
				}
			}
		}
		return instance;
	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void setInstance(JdbcUtils instance) {
		JdbcUtils.instance = instance;
	}
}
