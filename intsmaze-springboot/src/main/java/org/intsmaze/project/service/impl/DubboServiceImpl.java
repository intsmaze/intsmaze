package cn.intmsaze.project.service.impl;

import com.alibaba.fastjson.JSON;
import org.intsmaze.common.rpc.service.DubboJsonService;
import cn.intmsaze.project.core.AbstractService;
import cn.intmsaze.project.dao.PersonMapper;
import cn.intmsaze.project.model.Person;
import cn.intmsaze.project.service.DubboService;
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
