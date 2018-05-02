package org.intsmaze.persistence.mapper;

import java.util.List;


import org.intsmaze.persistence.pojos.SusTrade;

public interface SusTradeMapper {
	public List selectAllByPage(SusTrade susTrade);

	public int selectAllCount(SusTrade susTrade);

	public List selectSusTradeList(SusTrade susTrade);

	public int selectSusTradeListCount(SusTrade susTrade);
}