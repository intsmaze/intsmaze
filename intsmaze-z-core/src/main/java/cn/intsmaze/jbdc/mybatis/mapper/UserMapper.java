package cn.intsmaze.jbdc.mybatis.mapper;

import java.util.List;
import java.util.Map;

import cn.intsmaze.jbdc.mybatis.po.User;
import cn.intsmaze.jbdc.mybatis.po.UserQueryVo;

/**
 * <p>Description: 用户mapper</p>
 */
public interface UserMapper {
	
	public List<User> findUserByName(String id) throws Exception;
	
	//根据用户id查询用户信息
	public User findUserById(int id) throws Exception;
	//插入用户
	public void insertUser(User user)throws Exception;
	//删除用户
	public void deleteUser(int id) throws Exception;
	//修改用户
	public void updateUser(User user) throws Exception;
	
	public Map getMtypeList(Map map) throws Exception;
}
