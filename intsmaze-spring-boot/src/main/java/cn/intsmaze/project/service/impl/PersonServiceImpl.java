package cn.intsmaze.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.intsmaze.project.core.AbstractService;
import cn.intsmaze.project.dao.PersonMapper;
import cn.intsmaze.project.model.Person;
import cn.intsmaze.project.service.PersonService;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/30.
 */
@Service
@Transactional
public class PersonServiceImpl extends AbstractService<Person> implements PersonService {
    @Resource
    private PersonMapper personMapper;

}
