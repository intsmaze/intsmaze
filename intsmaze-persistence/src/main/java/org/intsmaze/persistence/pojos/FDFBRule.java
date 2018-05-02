package org.intsmaze.persistence.pojos;

import java.util.List;
import java.util.Map;

import org.intsmaze.persistence.common.BasePojo;

public class FDFBRule extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String seqno;

    private String ruleDesc;

    private String tradeFeature;

    private int istotal;

    private String tradeType;

    private String express;

    private String ruleType;
    
	public FDFBRule(String ruleDesc, String tradeFeature, int istotal, String tradeType, String express,String ruleType) {
		super();
		this.ruleDesc = ruleDesc;
		this.tradeFeature = tradeFeature;
		this.istotal = istotal;
		this.tradeType = tradeType;
		this.express = express;
		this.ruleType = ruleType;
	}
    
    public FDFBRule() {
		// TODO Auto-generated constructor stub
	}

    public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
    }

    public String getTradeFeature() {
        return tradeFeature;
    }

    public void setTradeFeature(String tradeFeature) {
        this.tradeFeature = tradeFeature == null ? null : tradeFeature.trim();
    }

    public int getIstotal() {
        return istotal;
    }

    public void setIstotal(int istotal) {
        this.istotal = istotal;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express == null ? null : express.trim();
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType == null ? null : ruleType.trim();
    }
    
    public String toString(){
    	return "ruleDesc="+getRuleDesc();
    }
    
}