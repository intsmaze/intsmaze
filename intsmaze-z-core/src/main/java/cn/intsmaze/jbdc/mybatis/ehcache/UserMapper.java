package cn.intsmaze.jbdc.mybatis.ehcache;

import java.util.List;

import cn.intsmaze.jbdc.mybatis.po.User;
import cn.intsmaze.jbdc.mybatis.po.UserQueryVo;

/**
 * <p>Description: 用户mapper</p>
 */
public interface UserMapper {
	
	//根据用户id查询用户信息
	public User findUserById(int id) throws Exception;
	
	//修改用户
	public void updateUser(User user) throws Exception;
}
