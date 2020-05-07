package cn.intsmaze.jbdc.connectpool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/3/9 18:22
 * @description： https://www.cnblogs.com/intsmaze/
 * @modified By：
 */
public class PoolTemplate {


    public static void main(String[] args) throws Exception {

        Properties druidP=new Properties();
        druidP.put("driverClassName","com.mysql.jdbc.Driver");
        druidP.put("url","jdbc:mysql://localhost:3306/test");
        druidP.put("username","root");
        druidP.put("password","intsmaze");
        DataSource dataSource = DruidDataSourceFactory.createDataSource(druidP);

        Connection connection = dataSource.getConnection();

        String sql = "select id from book ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
        }

    }


}
