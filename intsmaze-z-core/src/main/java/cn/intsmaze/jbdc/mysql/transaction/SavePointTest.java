//package cn.intsmaze.jbdc.mysql.transaction;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Savepoint;
//import java.sql.Statement;
//
//import cn.intsmaze.mysql.JdbcUtils;
//
//
//public class SavePointTest {
//
//	/**
//	 * @param args
//	 * @throws SQLException
//	 */
//	public static void main(String[] args) throws SQLException {
//		test();
//	}
//
//	static void test() throws SQLException {
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		Savepoint sp = null;
//		try {
//			conn = JdbcUtils.getInstance().getConnection();
//			conn.setAutoCommit(false);
//			st = conn.createStatement();
//			String sql = "update user set money=money-10 where id=1";
//			st.executeUpdate(sql);
//			//设置一个保存点，回滚时，该sql语句的执行效果不进行回滚。
//			sp = conn.setSavepoint();
//
//			sql = "update user set money=money-10 where id=3";
//			st.executeUpdate(sql);
//
//			sql = "select money from user where id=2";
//			rs = st.executeQuery(sql);
//			float money = 0.0f;
//			if (rs.next()) {
//				money = rs.getFloat("money");
//			}
//			if (money > 300)
//				throw new RuntimeException("it is too money");
//
//			sql = "update user set money=money+10 where id=2";
//			st.executeUpdate(sql);
//
//			conn.commit();
//		} catch (RuntimeException e) {
//			if (conn != null && sp != null) {
//				//回滚部分
//				conn.rollback(sp);
//				conn.commit();
//			}
//		} catch (SQLException e) {
//			if (conn != null)
//				//全部部分
//				conn.rollback();
//		} finally {
//			conn.close();
//		}
//	}
//}
