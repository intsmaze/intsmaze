package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBSysMessageConfig;

public interface FDFBSysMessageConfigMapper {

	/**
	 * 根据工单类型和工单状态获取对应配置
	 * @param sheettype
	 * @param sheetstate
	 * @return
	 */
	public List getSysMessageConfigBySheettypeAndSheetstate(String sheettype, String sheetstate, String messagetype);
}