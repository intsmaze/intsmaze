package org.intsmaze.business.user.service.impl;

import java.util.List;

import org.intsmaze.business.user.service.UnitService;
import org.intsmaze.persistence.dao.UnitDao;
import org.intsmaze.persistence.pojos.Unit;
import org.springframework.beans.factory.annotation.Autowired;

public class UnitServiceImpl implements UnitService {

	@Autowired
	UnitDao unitDao;
	
	
	 
	public void saveUnitList(List<Unit> unitList){
		for(Unit u:unitList){
			
			Unit unit = new Unit();
			unit.setUnitInnerCoding(u.getUnitInnerCoding());
			
			List list = unitDao.selectAllByPage(unit);
			
			if(list.size() < 1)
				unitDao.insert(u);
			else {
				unit = (Unit) list.get(0);
				unit.setStatus(u.getStatus());
				
				unitDao.updateByPrimaryKey(unit);
			}
		}
	}
	
	
	
	
}
