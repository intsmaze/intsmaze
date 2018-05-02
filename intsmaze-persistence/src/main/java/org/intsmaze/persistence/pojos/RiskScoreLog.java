package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;
import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class RiskScoreLog extends BasePojo {
    private String seqno;

    private String customerId;

    private String customerName;

    private String oldLevel;

    private String newLevel;

    private String suggestion;

    private String operation;

    private Date operationTime;

    private String operationPerson;
    
    private Date modifyon;
    
    private Timestamp createon;

    public Timestamp getCreateon() {
		return createon;
	}

	public void setCreateon(Timestamp createon) {
		this.createon = createon;
	}

	public Date getModifyon() {
		return modifyon;
	}

	public void setModifyon(Date modifyon) {
		this.modifyon = modifyon;
	}
	
	public void setModifyon(Timestamp modifyon) {
		this.modifyon = modifyon;
	}

	public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getOldLevel() {
        return oldLevel;
    }

    public void setOldLevel(String oldLevel) {
        this.oldLevel = oldLevel == null ? null : oldLevel.trim();
    }

    public String getNewLevel() {
        return newLevel;
    }

    public void setNewLevel(String newLevel) {
        this.newLevel = newLevel == null ? null : newLevel.trim();
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion == null ? null : suggestion.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationPerson() {
        return operationPerson;
    }

    public void setOperationPerson(String operationPerson) {
        this.operationPerson = operationPerson == null ? null : operationPerson.trim();
    }
}