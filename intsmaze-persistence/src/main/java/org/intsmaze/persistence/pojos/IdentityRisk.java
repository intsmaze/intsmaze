package org.intsmaze.persistence.pojos;

import org.intsmaze.persistence.common.BasePojo;

public class IdentityRisk extends BasePojo{
    private Long seqno;

    private String lv2DivName;

    private String lv3DivName;

    private String counts;

    private String id;
    
    private int addTime;

    public int getAddTime() {
		return addTime;
	}

	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}

    public Long getSeqno() {
        return seqno;
    }

    public void setSeqno(Long seqno) {
        this.seqno = seqno;
    }

    public String getLv2DivName() {
        return lv2DivName;
    }

    public void setLv2DivName(String lv2DivName) {
        this.lv2DivName = lv2DivName == null ? null : lv2DivName.trim();
    }

    public String getLv3DivName() {
        return lv3DivName;
    }

    public void setLv3DivName(String lv3DivName) {
        this.lv3DivName = lv3DivName == null ? null : lv3DivName.trim();
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts == null ? null : counts.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
}