package cn.intsmaze.jbdc.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class hiveJdbcDemo {

	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	public static void main(String[] args) throws SQLException {

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(
				"jdbc:hive2://192.168.19.133:10000/toucun", "hadoop", "hadoop");
		Statement stmt = con.createStatement();
//		String tableName = "person_20170629";
//		stmt.execute("drop table if exists " + tableName);
//		stmt.execute("create table "
//				+ tableName
//				+ "(id int,name string,age int) row format delimited fields terminated by ','");
//		System.out.println("Create table success!");

		// show tables
		String sql = "show tables";
		System.out.println("Running: " + sql);
		ResultSet res = stmt.executeQuery(sql);
		if (res.next()) {
			System.out.println(res.getString(1));
		}

//		// describe table
//		sql = "describe " + tableName;
//		System.out.println("Running: " + sql);
//		res = stmt.executeQuery(sql);
//		while (res.next()) {
//			System.out.println(res.getString(1) + "\t" + res.getString(2));
//		}
//
//		sql = "INSERT OVERWRITE TABLE " + tableName + " select * FROM person";
//		int flag = stmt.executeUpdate(sql);
//		System.out.println(flag);
//
//		sql = "select * from " + tableName;
//		res = stmt.executeQuery(sql);
//		while (res.next()) {
//			System.out.println(String.valueOf(res.getInt(1)) + "\t"
//					+ res.getString(2) + "\t" + res.getString(3));
//		}
//

	}

}
