package cn.intsmaze.jbdc.mybatis.cache2;

import cn.intsmaze.jbdc.mybatis.po.User;

public interface UserMapper {
	
	//根据用户id查询用户信息
	public User findUserById(int id) throws Exception;
	
	//修改用户
	public void updateUser(User user) throws Exception;
}
