package cn.intsmaze.jbdc.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn =DriverManager.getConnection ("jdbc:oracle:thin:@127.0.0.1:1521:orcl","system" , "intsmaze");
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select id,userid from USER_ROLE");
			while (rs.next()) {
				System.out.println(rs.getObject("id") + "\t"
						+ rs.getObject("userid") );
			}
		} finally {
			conn.close();
		}
		
		
	}

}
