package cn.intsmaze.jbdc.mysql.springjdbctemplate;

import java.io.IOException;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoTest {

	private UserDao userDao;

	public void init()
	{
		ApplicationContext ct=new ClassPathXmlApplicationContext(new String[] {"jdbctemplate-applicationcontext.xml"});
		userDao= (UserDao) ct.getBean("userDao");
	}

	public static void main(String[] args) throws IOException {
		try {
			UserDaoTest u=new UserDaoTest();
			u.init();
			u.findNameById();
		} catch (Exception e) {

		}
	}

	public void demo1(){
		User user = new User();
		user.setName("小胖");
		userDao.add(user);
	}

	public void demo2(){
		User user = new User();
		user.setId(1);
		user.setName("小边");
		userDao.update(user);
	}

	public void demo3(){
		User user = new User();
		user.setId(1);
		userDao.delete(user);
	}

	public void findNameById(){
		String name = userDao.findNameById(1);
		System.out.println(name);
	}

	public void demo6(){
		User user = userDao.findById(3);
		System.out.println(user);
	}

	public void demo7(){
		List<User> list = userDao.findAll();
		for (User user : list) {
			System.out.println(user);
		}
	}
}
