package cn.intsmaze.jbdc.mybatis.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.intsmaze.jbdc.mybatis.po.User;

public class UserMapperTest {
	private SqlSessionFactory sqlSessionFactory;
	@Before
	public void init() throws IOException {
		// 配置文件（SqlMapConfig.xml）
		String resource = "cn/intsmaze/mybatis/mapper/SqlMapConfig.xml";
		// 加载配置文件到输入流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void getMtypeList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Map map=new HashMap();
		map.put("id", 1);
		map.put("name", "a1");
		System.out.println(userMapper.getMtypeList(map));
	}
	
	@Test
	public void testFindUserById() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}

	@Test
	public void testFindUserByUsername() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.findUserByName("小明");
		System.out.println(list);
	}

	@Test
	public void testInsertUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("李奎");
		userMapper.insertUser(user);
		sqlSession.commit();
		sqlSession.close();
	}

}
