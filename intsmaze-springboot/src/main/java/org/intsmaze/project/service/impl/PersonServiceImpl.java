package cn.intmsaze.project.service.impl;

import cn.intmsaze.project.core.AbstractService;
import cn.intmsaze.project.dao.PersonMapper;
import cn.intmsaze.project.model.Person;
import cn.intmsaze.project.service.PersonService;
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
