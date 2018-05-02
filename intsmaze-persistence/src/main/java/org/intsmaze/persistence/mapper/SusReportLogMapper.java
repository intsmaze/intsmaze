package org.intsmaze.persistence.mapper;

import java.util.List;


import org.intsmaze.persistence.pojos.SusReportLog;

public interface SusReportLogMapper {
	
	public SusReportLog selectByPrimaryKey(String seqno);
    
    public int getSelectiveCount(SusReportLog susReportLog);
    //计数用于命名
    public int insertSelective(SusReportLog susReportLog);
    
	public List selectAllByPage(SusReportLog susReportLog);


}