package org.intsmaze.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.intsmaze.persistence.common.BaseDao;
import org.intsmaze.persistence.mapper.FDFBReceiptManageMapper;
import org.intsmaze.persistence.mapper.FDFBRuleMapper;
import org.intsmaze.persistence.pojos.FDFBReceiptManage;
import org.intsmaze.persistence.pojos.FDFBRule;
import org.intsmaze.persistence.pojos.FDFBRuleOperate;
import org.intsmaze.persistence.pojos.FDFBRuleParam;
import org.intsmaze.persistence.pojos.FDFBRuleTable;
import org.springframework.beans.factory.annotation.Autowired;

public class FDFBReceiptManageDao extends BaseDao<FDFBRule> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5369416648050006360L;
	
	@Autowired
	FDFBReceiptManageMapper receiptManageMapper;
	
	public List selectAllByPage(FDFBReceiptManage record){
		return receiptManageMapper.selectAllByPage(record);
	}

	public int getAllFDFBReceiptCount(FDFBReceiptManage receipt){
		return receiptManageMapper.getAllFDFBReceiptCount(receipt);
	}
	
	public List<FDFBReceiptManage> oneReceipt(String filename){
		return receiptManageMapper.oneReceipt(filename);
	}
	
	public void insertReceipt(Map<String,String> mapXml){
		receiptManageMapper.insertReceipt(mapXml);
	}
	
	public String oneMark(String mark){
		return receiptManageMapper.oneMark(mark);
	}
	
	public void insertMark(Map map){
		receiptManageMapper.insertMark(map);
	}
	
	public List<FDFBReceiptManage> receiptDetail(String otic){
		return receiptManageMapper.receiptDetail(otic);
	}
	
	public List businessNumbers(String otic){
		return receiptManageMapper.businessNumbers(otic);
	}
	
	public void updateReceipt(FDFBReceiptManage record){
		receiptManageMapper.updateReceipt(record);
	}
	
	public List delReceipt(String seqno){
		return receiptManageMapper.delReceipt(seqno);
	}
	
	public String getXmlName(String seqno){
		return receiptManageMapper.getXmlName(seqno);
	}
	
	public void updateReceiptBySeqno(FDFBReceiptManage record){
		receiptManageMapper.updateReceiptBySeqno(record);
	}
	
	public void updateReceiptPath(FDFBReceiptManage record){
		receiptManageMapper.updateReceiptPath(record);
	}
	
	public String getReceiptPath(String seqno){
		return receiptManageMapper.getReceiptPath(seqno);
	}
	
	public List bigTransKey(String filename){
		return receiptManageMapper.bigTransKey(filename);
	}
}