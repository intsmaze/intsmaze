package cn.intsmaze.jbdc.mybatis.onetoone;

import java.util.List;

import cn.intsmaze.jbdc.mybatis.po.User;
import cn.intsmaze.jbdc.mybatis.po.UserQueryVo;


public interface UserMapper {
	
	//根据用户id查询用户信息
	public User findUserById(int id) throws Exception;
}
