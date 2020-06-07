package com.intsmaze.hbase.phoenix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class PhoenixDao {

	Connection conn = null;

	@Before
	public void init() throws Exception {
		Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
		conn = DriverManager.getConnection("jdbc:phoenix:intsmaze-201,intsmaze-202:2181", new Properties());
	}

	@Test
	public void createTable() throws Exception {
		Statement stmt = conn.createStatement();
		boolean execute = stmt.execute("CREATE TABLE dba.t_test(id CHAR(10) not null primary key,name CHAR(30),age BIGINT)");
		// boolean execute =
		// stmt.execute("CREATE TABLE my_schema.my_table ( id BIGINT not null primary key, date DATE)");
		System.out.println(execute);
	}

	@Test
	public void testFindById() throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from dba.t_test where id='001'");
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			System.out.println(id);
			System.out.println(name);
		}

	}

	@Test
	public void testFindAll() throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from dba.t_test");
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			System.out.println(id);
			System.out.println(name);
		}
	}

	@Test
	public void testFindByName() throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from dba.t_test where name='bbbbb'");
		while (rs.next()) {
			String id = rs.getString(1);
			String name = rs.getString(2);
			System.out.println(id);
			System.out.println(name);
		}

	}

	@Test
	public void testInsertEntity() throws Exception {

		Statement stmt = conn.createStatement();
//		stmt.execute("UPSERT into dba.t_test(id,name,age) values('001','aaaaa',20)");
//		stmt.executeUpdate("UPSERT into dba.t_test(id,name,age) values('002','bbbbb',20)");
//		stmt.executeUpdate("UPSERT into dba.t_test(id,name,age) values('003','ccccc',30)");
//		stmt.executeUpdate("UPSERT into dba.t_test(id,name,age) values('005','eeeee',40)");
		stmt.executeUpdate("UPSERT into dba.t_test(id,name,age) values('006','xxxxx',50)");
		conn.commit();
		stmt.close();
		conn.close();
	}



}
