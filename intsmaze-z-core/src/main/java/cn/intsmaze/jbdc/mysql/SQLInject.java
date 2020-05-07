package cn.intsmaze.jbdc.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cn.intsmaze.jbdc.mysql.utils.DataSourceUtils;

public class SQLInject {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
//		read("name1");

		read1("'or 1 or'");
//		read1("';delete from stockbase where 1 or'");//这种无法注入的，同时执行两条sql语句的。
	}

	static void read(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DataSourceUtils.getConnection();
			String sql = "select stockid, stockname from stockbase where stockname=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				 System.out.println(rs.getObject("stockid") + "\t"
						 + rs.getObject("stockname"));
			}
		} finally {
			DataSourceUtils.free(rs, ps, conn);
		}
	}

	static void read1(String name) throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataSourceUtils.getConnection();
			String sql = "select stockid, stockname  from stockbase where stockname='"+ name + "'";
			System.out.println(sql);
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				 System.out.println(rs.getObject("stockid") + "\t"
				 + rs.getObject("stockname"));
			}
		} finally {
			DataSourceUtils.free(rs, st, conn);
		}
	}

}
