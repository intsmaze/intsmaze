package com.intsmaze.project.service.impl;

import com.intsmaze.project.core.AbstractService;
import com.intsmaze.project.dao.PersonMapper;
import com.intsmaze.project.model.Person;
import com.intsmaze.project.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
