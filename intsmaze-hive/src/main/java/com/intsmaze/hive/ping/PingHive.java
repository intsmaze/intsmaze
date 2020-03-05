package com.intsmaze.hive.ping;

import java.sql.*;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/3/5 17:37
 * @description： https://www.cnblogs.com/intsmaze/
 * @modified By：
 */
public class PingHive {

    public static void main(String[] args) throws SQLException {

        Connection coon = DriverManager.getConnection(
                "jdbc:hive2://intsmaze-203:10000/default",
                "hive",
                "intsmaze");


        PreparedStatement sta = coon.prepareStatement("select area from test ");
        ResultSet rs = sta.executeQuery();
        if (rs.next()) {
            System.out.println(rs.getString("area"));
        }
        coon.close();
    }
}
