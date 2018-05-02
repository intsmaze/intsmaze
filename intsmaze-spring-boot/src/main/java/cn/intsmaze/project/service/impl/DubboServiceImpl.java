package org.intsmaze.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.intsmaze.project.core.AbstractService;
import org.intsmaze.project.dao.DubboMapper;
import org.intsmaze.project.dao.PersonMapper;
import org.intsmaze.project.model.Dubbo;
import org.intsmaze.project.model.Person;
import org.intsmaze.project.service.DubboService;
import org.intsmaze.project.service.PersonService;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/30.
 */
@Service
@Transactional
public class DubboServiceImpl extends AbstractService<Person> implements DubboService {
	
    @Resource
    private DubboMapper dubboMapper;

}
