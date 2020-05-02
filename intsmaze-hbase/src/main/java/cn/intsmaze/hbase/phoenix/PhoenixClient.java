package cn.intsmaze.hbase.phoenix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class PhoenixClient {  
    private String url = "jdbc:phoenix:mini1,mini2,mini3:2181";  
    private String driver = "org.apache.phoenix.jdbc.PhoenixDriver";  
    private Connection connection = null;  
  
    public PhoenixClient() {  
        try {  
            Class.forName(driver);  
            connection = DriverManager.getConnection(url);  
            System.out.println("Connect HBase success..");  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        if (connection != null) {  
            try {  
                connection.close();  
            } catch (Exception e) {  
            } finally {  
                connection = null;  
            }  
        }  
    }  
  
    public void createTable() {  
        String sql = "create table IF NOT EXISTS phoenix_test(stuid integer not null primary key,name VARCHAR,age integer,score integer,classid integer)";  
        try {  
            Statement statement = connection.createStatement();  
            statement.executeUpdate(sql);  
            connection.commit();  
            System.out.println("create table success: " + sql);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void deleteTable() {  
        String sql = "drop table if exists pho_test";  
        try {  
            Statement statement = connection.createStatement();  
            statement.executeUpdate(sql);  
            connection.commit();  
            System.out.println("delete table success: " + sql);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void insertRecord() {  
        String sql = "upsert into phoenix_test(stuid,name,age,score,classid) values l (?,?,?,?,?)";  
        try {  
            PreparedStatement statement = connection.prepareStatement(sql);  
            Random random = new Random();  
            for (int i = 1; i <= 100; i++) {  
                statement.setInt(1,i);  
                statement.setString(2,"phoenix_test" + i);  
                statement.setInt(3, random.nextInt(18));  
                statement.setInt(4, random.nextInt(100));  
                statement.setInt(5,random.nextInt(3));  
                statement.execute();  
            }  
            connection.commit();  
            statement.close();
            connection.close();
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void selectRecord() {  
        String sql = "select stuid,name,age,score,classid from phoenix_test";  
        ResultSet resultSet = null;  
        try {  
            Statement statement = connection.createStatement();  
            resultSet = statement.executeQuery(sql);  
            while (resultSet != null && resultSet.next()) {  
                System.out.println("stuid: " + resultSet.getString(1) + "\tname: " + resultSet.getString(2)  
                 + "\tage: " + resultSet.getString(3) + "\tscore: " + resultSet.getString(4) + "\tclassid: " + resultSet.getString(5));  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String[] args) {  
        PhoenixClient client = new PhoenixClient();  
        //client.createTable();  
        client.insertRecord();  
//        client.selectRecord();  
//        client.deleteTable();  
    }  
}  
