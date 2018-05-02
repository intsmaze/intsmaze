package org.intsmaze.persistence.pojos;

public class FDFBRuleParam	{
	
	private String ruleid;

    private String paramid;
	
    private String param;

    private String paramSource;

    private String paramDesc;

    private String paramType;
    
    public String getRuleid() {
		return ruleid;
	}

	public void setRuleid(String ruleid) {
		this.ruleid = ruleid;
	}

	public String getParamid() {
		return paramid;
	}

	public void setParamid(String paramid) {
		this.paramid = paramid;
	}

	public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public String getParamSource() {
        return paramSource;
    }

    public void setParamSource(String paramSource) {
        this.paramSource = paramSource == null ? null : paramSource.trim();
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc == null ? null : paramDesc.trim();
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }
}