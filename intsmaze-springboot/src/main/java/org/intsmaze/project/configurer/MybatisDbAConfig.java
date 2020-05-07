package cn.intmsaze.project.configurer;//package cn.intmsaze.project.configurer;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///** 
// * @author:YangLiu
// * @date:2018年5月4日 下午4:37:08 
// * @describe: 
// */
//@Configuration
//@MapperScan(basePackages = {"cn.intmsaze.project.dao.PersonMapper"}, sqlSessionFactoryRef = "sqlSessionFactory1")
//public class MybatisDbAConfig {
//
//    @Autowired
//    @Qualifier("MasterDS")
//    private DataSource ds1;
//
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory1() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(ds1); // 使用titan数据源, 连接titan库
//        return factoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
//        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1()); // 使用上面配置的Factory
//        return template;
//    }
//}
