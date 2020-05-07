package cn.intsmaze.jbdc.mybatis.onetomany;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.intsmaze.jbdc.mybatis.po.OrderCustom;
import cn.intsmaze.jbdc.mybatis.po.Orders;
import cn.intsmaze.jbdc.mybatis.po.User;

public class OrdersMapperCustomTest {	
	// 会话工厂
	private SqlSessionFactory sqlSessionFactory;
	// 创建工厂
	@Before
	public void init() throws IOException {
		// 配置文件（SqlMapConfig.xml）
		String resource = "cn/intsmaze/mybatis/onetomany/SqlMapConfig.xml";
		// 加载配置文件到输入 流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	// 一对多查询使用resultMap
	@Test
	public void testFindOrderAndOrderDetails() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		// 调用方法
		List<Orders> list = ordersMapperCustom.findOrderAndOrderDetails();
		System.out.println(list);
	}

	
	// 一对多查询使用resultMap
	@Test
	public void testFindUserOrderDetail() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		// 调用方法
		List<User> list = ordersMapperCustom.findUserOrderDetail();
		System.out.println(list);
	}
}
