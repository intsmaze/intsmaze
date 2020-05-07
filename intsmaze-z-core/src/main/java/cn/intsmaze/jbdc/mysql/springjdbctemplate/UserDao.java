package cn.intsmaze.jbdc.mysql.springjdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserDao extends JdbcDaoSupport{

	public void add(User user){
		String sql = "insert into user values (null,?)";
		this.getJdbcTemplate().update(sql, user.getName());
	}

	public void update(User user){
		String sql = "update user set name = ? where id = ?";
		this.getJdbcTemplate().update(sql, user.getName(),user.getId());
	}

	public void delete(User user){
		String sql = "delete from user where id = ?";
		this.getJdbcTemplate().update(sql, user.getId());
	}

	public String findNameById(int id){
		String sql = "select name from user where id = ?";
		return this.getJdbcTemplate().queryForObject(sql, String.class, id);
	}

	public User findById(int id){
		String sql = "select * from user where id = ?";
		User user = this.getJdbcTemplate().queryForObject(sql, new UserRowMapper(), id);
		return user;
	}

	public List<User> findAll(){
		String sql = "select * from user";
		return this.getJdbcTemplate().query(sql, new UserRowMapper());
	}

	class UserRowMapper implements RowMapper<User>{
		/**
		 * rs:结果集.
		 * rowNum:行号
		 */
		@Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			return user;
		}

	}
}
