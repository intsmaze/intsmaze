package cn.intsmaze.jbdc.mysql.transaction;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class two {
	
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
        
        connection.setAutoCommit(false);
		System.out.println("================事务开始====================");
		
        Statement stmt = connection.createStatement();
        
        //向表中插入一行记录
        stmt.executeUpdate("update person set name='123' where name='音乐4'");
//		stmt.executeUpdate("INSERT INTO person VALUES('10004','音乐4','China')");
		
        ResultSet rs = stmt.executeQuery("SELECT name  FROM person"); 
        while (rs.next()) { 
            String name = rs.getString("name"); 
            System.out.println("name is:"+ name); 
        } 
        rs.close();
        connection.commit();
        stmt.close();
        connection.close();
    }
}  
