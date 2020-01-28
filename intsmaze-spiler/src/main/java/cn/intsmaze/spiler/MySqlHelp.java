package cn.intsmaze.spiler;

import cn.intsmaze.spiler.bean.JobDescription;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class MySqlHelp {

	Connection connection = null;

	PreparedStatement preparedStatement = null;

	String className;

	String url;

	String user;

	String password;

	public void init() throws IOException, ClassNotFoundException {
		InputStream in = MySqlHelp.class.getClassLoader().getResourceAsStream(
				"dbinfo.properties");

		Properties props = new Properties();

		props.load(in);

		className = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://127.0.0.1:2206/test";
		user = "";
		password = "";
		Class.forName(className);
	}

	public synchronized Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public void put(String tableName, List<JobDescription> list) throws SQLException {
		if (list == null || list.size() <= 0) {
			return;
		}

		try {
			getConnection();
			preparedStatement = connection.prepareStatement("insert into test(filename) value(?,?,?)");
			connection.setAutoCommit(false);
			for (int i = 0; i < list.size(); i++) {
				preparedStatement.setString(1, list.get(i).getCompanyName());
				//.................
				preparedStatement.addBatch();
				if (i != 0 && i % 2 == 0 || i == list.size() - 1) {
					preparedStatement.executeBatch();
					connection.commit();
					preparedStatement.clearBatch();
				}
			}
			return;
		} catch (Exception e) {

		} finally {
			closeConnection(preparedStatement);
		}
	}

	private void closeConnection(PreparedStatement preparedStatement) {
		if(preparedStatement!=null)
		{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
