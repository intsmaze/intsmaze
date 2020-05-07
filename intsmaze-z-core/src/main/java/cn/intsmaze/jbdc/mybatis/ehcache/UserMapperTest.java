package cn.intsmaze.jbdc.mybatis.ehcache;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.intsmaze.jbdc.mybatis.po.User;


public class UserMapperTest {
	// 会话工厂
	private SqlSessionFactory sqlSessionFactory;
	// 创建工厂
	@Before
	public void init() throws IOException {
		// 配置文件（SqlMapConfig.xml）
		String resource = "cn/intsmaze/mybatis/ehcache/SqlMapConfig.xml";
		// 加载配置文件到输入流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	//二级缓存的测试
	@Test
	public void testCache2() throws Exception {

		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		
		SqlSession sqlSession4 = sqlSessionFactory.openSession();
		SqlSession sqlSession5 = sqlSessionFactory.openSession();
		SqlSession sqlSession6 = sqlSessionFactory.openSession();
		UserMapper userMapper4 = sqlSession4.getMapper(UserMapper.class);
		UserMapper userMapper5 = sqlSession5.getMapper(UserMapper.class);
		UserMapper userMapper6 = sqlSession6.getMapper(UserMapper.class);
		
		//第一次查询用户id为1的用户
		User user = userMapper1.findUserById(1);
		System.out.println(user);
		sqlSession1.close();
		
		User user1 = userMapper4.findUserById(10);
		System.out.println(user1);
		sqlSession4.close();
		
		user1 = userMapper6.findUserById(10);
		System.out.println(user1);
		sqlSession6.close();
		
		//中间修改用户要清空缓存，目的防止查询出脏数据
		user.setUsername("测试用户5");
		userMapper3.updateUser(user);
		sqlSession3.commit();
		sqlSession3.close();	
		
		//第二次查询用户id为1的用户
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);		
		sqlSession2.close();
		
		User user3 = userMapper5.findUserById(10);
		System.out.println(user3);
		sqlSession5.close();
	}
}
