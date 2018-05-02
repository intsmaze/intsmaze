package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;


public class AmlVersion extends BasePojo{
	private String seqno;
	
    private String name;

    private String version;

    private String description;
    
    private Date modifyon;
    
    private Date createon;
    
    

    public String getSeqno() {
		return "";
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public Date getModifyon() {
		return modifyon;
	}

	public void setModifyon(Date modifyon) {
		this.modifyon = modifyon;
	}

	public Date getCreateon() {
		return createon;
	}

	public void setCreateon(Date createon) {
		this.createon = createon;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}