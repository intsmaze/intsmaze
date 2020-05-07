package com.intsmaze.jetty.handle.zjrc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 * @Description
 * @Author 刘洋
 * @Date 2018/12/23 11:07
 * @Version 1.0
 **/
public class UserDao extends JdbcDaoSupport{

	public void add(UserBean userBean){
		String sql = "insert into user values (null,?)";
		this.getJdbcTemplate().update(sql, userBean.getName());
	}

	public void update(UserBean userBean){
		String sql = "update user set name = ? where id = ?";
		this.getJdbcTemplate().update(sql, userBean.getName(), userBean.getId());
	}

	public void delete(UserBean userBean){
		String sql = "delete from user where id = ?";
		this.getJdbcTemplate().update(sql, userBean.getId());
	}

	public String findNameById(int id){
		String sql = "select name from user where id = ?";
		return this.getJdbcTemplate().queryForObject(sql, String.class, id);
	}

	public UserBean findById(int id){
		String sql = "select * from user where id = ?";
		UserBean userBean = this.getJdbcTemplate().queryForObject(sql, new UserRowMapper(), id);
		return userBean;
	}

	public List<UserBean> findAll(){
		String sql = "select * from user";
		return this.getJdbcTemplate().query(sql, new UserRowMapper());
	}

	class UserRowMapper implements RowMapper<UserBean>{
		/**
		 * rs:结果集.
		 * rowNum:行号
		 */
		@Override
        public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserBean userBean = new UserBean();
			userBean.setId(rs.getInt("id"));
			userBean.setName(rs.getString("name"));
			return userBean;
		}

	}
}
