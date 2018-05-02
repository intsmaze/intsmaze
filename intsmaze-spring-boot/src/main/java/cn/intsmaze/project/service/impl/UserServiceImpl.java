package org.intsmaze.project.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.intsmaze.project.core.AbstractService;
import org.intsmaze.project.dao.UserMapper;
import org.intsmaze.project.model.User;
import org.intsmaze.project.service.UserService;

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
