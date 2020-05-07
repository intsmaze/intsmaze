package cn.intsmaze.jbdc.mysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.intsmaze.jbdc.mysql.utils.DataSourceUtils;


public class ClobTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException, IOException {
		// create();
		read();
	}

	static void read() throws SQLException, IOException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DataSourceUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select big_text  from clob_test");
			while (rs.next()) {
				Clob clob = rs.getClob(1);
				Reader reader = clob.getCharacterStream();

				File file = new File("JdbUtils_bak.java");
				Writer writer = new BufferedWriter(new FileWriter(file));
				char[] buff = new char[1024];
				for (int i = 0; (i = reader.read(buff)) > 0;) {
					writer.write(buff, 0, i);
				}
				writer.close();
				reader.close();
			}
		} finally {
			DataSourceUtils.free(rs, st, conn);
		}
	}

	static void create() throws SQLException, IOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 2.建立连接
			conn = DataSourceUtils.getConnection();
			// 3.创建语句
			String sql = "insert into clob_test(big_text) values (?) ";
			ps = conn.prepareStatement(sql);
			File file = new File("src/cn/itcast/jdbc/JdbcUtils.java");
			Reader reader = new BufferedReader(new FileReader(file));

			ps.setCharacterStream(1, reader, (int) file.length());
			// ps.setString(1, x);
			// 4.执行语句
			int i = ps.executeUpdate();

			reader.close();

			System.out.println("i=" + i);
		} finally {
			DataSourceUtils.free(rs, ps, conn);
		}
	}

}
