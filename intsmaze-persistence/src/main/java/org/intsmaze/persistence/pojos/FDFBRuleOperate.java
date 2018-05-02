package org.intsmaze.persistence.pojos;

import org.intsmaze.persistence.common.BasePojo;

public class FDFBRuleOperate extends BasePojo {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ruleid;

    private String judgeId;

	private String operate;

    private String isDate;
    
    private String type;

	public String getRuleid() {
		return ruleid;
	}

	public void setRuleid(String ruleid) {
		this.ruleid = ruleid;
	}

	public String getJudgeId() {
		return judgeId;
	}

	public void setJudgeId(String judgeId) {
		this.judgeId = judgeId;
	}

	public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

	public String getIsDate() {
		return isDate;
	}

	public void setIsDate(String isDate) {
		this.isDate = isDate;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type=type;
	}
}