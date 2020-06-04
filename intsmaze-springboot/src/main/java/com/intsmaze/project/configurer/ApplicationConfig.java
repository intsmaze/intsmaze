package com.intsmaze.project.configurer;//package cn.intmsaze.project.configurer;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
///** 
// * @author:YangLiu
// * @date:2018年5月4日 下午4:10:59 
// * @describe: 
// */
//@ComponentScan
//@Configuration
//@ConfigurationProperties(prefix="spring.datasource")
//public class ApplicationConfig {
//
//    private String url;
//    private String username;
//    private String password;
//
//    @Bean
//    public DataSource getDataSource() {
//    	System.out.println("DruidDataSource is ...............");
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);// 用户名
//        dataSource.setPassword(password);// 密码
//        return dataSource;
//    }
//
//    public String getUrl() {
//     return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
