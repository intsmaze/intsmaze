package com.intsmaze.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.intsmaze.project.core.AbstractService;
import com.intsmaze.project.dao.PersonMapper;
import com.intsmaze.project.model.Person;
import com.intsmaze.project.service.DubboService;
import org.intsmaze.common.rpc.service.DubboJsonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/30.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class DubboServiceImpl extends AbstractService<Person> implements DubboService,DubboJsonService{
	
    @Resource
    private PersonMapper personMapper;

	@Override
    public String findComputer(Integer id) {
		Person p=this.findById(id);
		return JSON.toJSONString(p);
		 
	}

}
