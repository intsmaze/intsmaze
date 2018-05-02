package org.intsmaze.persistence.pojos;

import org.intsmaze.persistence.common.BasePojo;

public class SusRule extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String seqno;

    private Integer ruleid;

    private String ruleDesc;

    private String tradeFeature;

    private Integer istotal;

    private String tradeType;

    private String express;

    private String ruleType;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public Integer getRuleid() {
        return ruleid;
    }

    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
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

    public Integer getIstotal() {
        return istotal;
    }

    public void setIstotal(Integer istotal) {
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
}