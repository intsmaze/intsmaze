package org.intsmaze.persistence.mapper;

import java.util.List;
import java.util.Map;

import org.intsmaze.persistence.pojos.FDFBReceiptManage;

public interface FDFBReceiptManageMapper {
    int insert(FDFBReceiptManage record);

    int insertSelective(FDFBReceiptManage record);
    
    List selectAllByPage(FDFBReceiptManage record);
    
    int getAllFDFBReceiptCount(FDFBReceiptManage record);
    
    List<FDFBReceiptManage> oneReceipt(String filename);
    
    void insertReceipt(Map<String,String> mapXml);
    
    String oneMark(String mark);
    
    void insertMark(Map map);
    
    List<FDFBReceiptManage> receiptDetail(String otic);
    
    List businessNumbers(String otic);
    
    void updateReceipt(FDFBReceiptManage record);
    
    List delReceipt(String seqno);
    
    String getXmlName(String seqno);
    
    void updateReceiptBySeqno(FDFBReceiptManage record);
    
    void updateReceiptPath(FDFBReceiptManage record);
    
    String getReceiptPath(String seqno);
    
    List bigTransKey(String filename);
}