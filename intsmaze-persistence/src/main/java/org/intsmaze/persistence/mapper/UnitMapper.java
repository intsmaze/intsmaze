package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.Unit;

public interface UnitMapper {

	List selectAllByPage(Unit unit);
}