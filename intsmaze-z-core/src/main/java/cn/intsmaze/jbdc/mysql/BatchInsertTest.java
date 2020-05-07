package cn.intsmaze.jbdc.mysql;
import cn.intsmaze.jbdc.mysql.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchInsertTest {

	
	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
//		for (int i = 0; i < 100; i++)
//			create(i);

		createBatch();
	}

	static void create(int i) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtils.getInstance().getConnection();
			String sql = "insert into test_capability(name,birthday, money) values (?, ?, ?) ";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "no batch name" + i);
			ps.setDate(2, new Date(System.currentTimeMillis()));
			ps.setFloat(3, 100f + i);
			ps.executeUpdate();
		} finally {
			ps.close();
			conn.close();
		}
	}

	//批处理
	static void createBatch() throws SQLException  {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtils.getInstance().getConnection();
			String sql = "insert into testoom_1(account,trade_type,amount,oppo_account,oppo_adress,trade_date,oppo_flag,remark,oppo_rank,retain1) values (?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < 90000000; i++) {
				try {
				ps.setInt(1, i);
				ps.setString(2, i+"");
				ps.setString(3, "my name is" + i);
				ps.setString(4, "my age is" + i);
				ps.setString(5, "my address is" + i);
				ps.setString(6, "my sex is" + i);
				ps.setString(7, "my phone is" + i);
				ps.setString(8, "my email is" + i);
				ps.setString(9, "my firstname is" + i);
				ps.setString(10, "my lastname is" + i);
				ps.addBatch();
				if(i % 2000 == 0) {
					System.out.println(i);
			        ps.executeBatch();
			    }
			}
			catch (SQLException e) {
				ps.clearBatch();
				e.printStackTrace();
			}
			}
			ps.executeBatch();
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
		finally {
			conn.close();
		}
	}

}
