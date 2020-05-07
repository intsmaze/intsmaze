package cn.intmsaze.project.configurer;//package cn.intmsaze.project.configurer;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///** 
// * @author:YangLiu
// * @date:2018年5月4日 下午4:36:07 
// * @describe: 
// */
//@Configuration
//public class DataSourceConfig {
//
//    @Bean(name = "MasterDS")
//    @ConfigurationProperties(prefix = "spring.datasource.master") // application.properteis中对应属性的前缀
//    public DataSource dataSource1() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "ds2")
//    @ConfigurationProperties(prefix = "spring.datasource.db2") // application.properteis中对应属性的前缀
//    public DataSource dataSource2() {
//        return DataSourceBuilder.create().build();
//    }
//
//}
