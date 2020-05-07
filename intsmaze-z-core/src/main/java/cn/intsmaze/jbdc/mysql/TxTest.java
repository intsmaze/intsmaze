//package cn.intsmaze.jbdc.mysql;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import cn.intsmaze.druid.DataSourceUtils;
//import cn.intsmaze.jbdc.mysql.utils.DataSourceUtils;
//
//public class TxTest {
//
//	/**
//	 * @param args
//	 * @throws SQLException
//	 */
//	public static void main(String[] args)  {
//		test();
//	}
//
//	static void test()  {
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			conn = DataSourceUtils.getConnection();
//
//			conn.setAutoCommit(false);//如果不设置为false,则默认一条sql语句就好立刻执行。
////			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//
//			st = conn.createStatement();
//			String sql = "update user set money=money-10 where id=1";
//			st.executeUpdate(sql);
//
//			sql = "select money from user where id=2";
//			rs = st.executeQuery(sql);
//			float money = 0.0f;
//			if (rs.next()) {
//				money = rs.getFloat("money");
//			}
//			if (money > 400)
//				throw new RuntimeException("it is too money");
//
//			sql = "update user set money=money+10 where id=2";
//			st.executeUpdate(sql);
//
//			conn.commit();
//
//		} catch (SQLException e) {
//			if (conn != null)
//				try {
//					conn.rollback();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//		} finally {
//			DataSourceUtils.free(rs, st, conn);
//		}
//	}
//}
