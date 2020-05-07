package cn.intsmaze.jbdc.mysql.springjdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.util.List;
import java.util.Map;

public class SpringTest {

    public static void main(String[] args) throws IOException {
        ApplicationContext ct = new ClassPathXmlApplicationContext(new String[]{"jdbctemplate-applicationcontext.xml"});

        // 使用JDBC的模板:
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ct.getBean("jdbcTemplate");
        List<Map<String, Object>> showTables = jdbcTemplate.queryForList("Show tables");


        System.out.println(showTables);
//            jdbcTemplate.execute("create table user2018 (id int primary key auto_increment,name varchar(20))");
//        System.out.println(jdbcTemplate.queryForList("SELECT  * from user", User.class));
    }


}
