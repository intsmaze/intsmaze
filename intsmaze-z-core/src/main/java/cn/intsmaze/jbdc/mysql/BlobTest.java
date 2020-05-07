package cn.intsmaze.jbdc.mysql;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.intsmaze.jbdc.mysql.utils.DataSourceUtils;


public class BlobTest {

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
			rs = st.executeQuery("select big_bit  from blob_test");
			while (rs.next()) {
				// Blob blob = rs.getBlob(1);
				// InputStream in = blob.getBinaryStream();
				InputStream in = rs.getBinaryStream("big_bit");
				File file = new File("IMG_0002_bak.jpg");
				OutputStream out = new BufferedOutputStream(
						new FileOutputStream(file));
				byte[] buff = new byte[1024];
				for (int i = 0; (i = in.read(buff)) > 0;) {
					out.write(buff, 0, i);
				}
				out.close();
				in.close();
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
			File file = new File("IMG_0002.jpg");
			InputStream in = new BufferedInputStream(new FileInputStream(file));

			conn = DataSourceUtils.getConnection();
			String sql = "insert into blob_test(big_bit) values (?) ";
			ps = conn.prepareStatement(sql);
			ps.setBinaryStream(1, in, (int) file.length());

			ps.executeUpdate();
			in.close();

		} finally {
			DataSourceUtils.free(rs, ps, conn);
		}
	}
}
