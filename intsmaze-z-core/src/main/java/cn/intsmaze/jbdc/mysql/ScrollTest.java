package cn.intsmaze.jbdc.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.intsmaze.jbdc.mysql.utils.DataSourceUtils;

public class ScrollTest {

	/**
	 * 可滚动的结果集
	 */
	public static void main(String[] args) throws SQLException {
		scroll();
	}

	static void scroll() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataSourceUtils.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = st
					.executeQuery("select id, name, money, birthday  from user limit 150, 10");
			while (rs.next()) {
				System.out.println(rs.getObject("id") + "\t"
						+ rs.getObject("name") + "\t"
						+ rs.getObject("birthday") + "\t"
						+ rs.getObject("money"));
			}

			System.out.println("------------");
			rs.absolute(150);
			int i = 0;
			while (rs.next() && i < 10) {
				i++;
				System.out.println(rs.getObject("id") + "\t"
						+ rs.getObject("name") + "\t"
						+ rs.getObject("birthday") + "\t"
						+ rs.getObject("money"));
			}

			// if (rs.previous())
			// System.out.println(rs.getObject("id") + "\t"
			// + rs.getObject("name") + "\t"
			// + rs.getObject("birthday") + "\t"
			// + rs.getObject("money"));
		} finally {
			DataSourceUtils.free(rs, st, conn);
		}
	}
}
