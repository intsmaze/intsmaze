package cn.intsmaze.project.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.intsmaze.project.core.AbstractService;
import cn.intsmaze.project.dao.UserMapper;
import cn.intsmaze.project.model.User;
import cn.intsmaze.project.service.UserService;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/30.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
