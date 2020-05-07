package cn.intsmaze.jbdc.mysql.switchDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

public class MutilThreadTest extends Thread {
	
	String name;
	
	public static DataSource dataSource;

	static {
		try {
			dataSource = DataSourceUtil
					.getDataSource(DataSourceUtil.DRUID_MYSQL_SOURCE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MutilThreadTest(String name) {
		super();
		this.name = name;
	}

	@Override
    public void run() {
		try {
			System.out.println(name+"........start...........");
			selectDataSource();
			System.out.println(name+"...........end...........");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String argc[]) throws Exception {
		MutilThreadTest myThread1 = new MutilThreadTest("线程1");
		MutilThreadTest myThread2 = new MutilThreadTest("线程2");
		myThread1.start();
		myThread2.start();
	}

	public static void selectDataSource() throws Exception {
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from test1");
			while (rs.next()) {
				System.out.println(rs.getObject(2) + "\t");
			}
		} finally {
			con.close();
		}
	}
}