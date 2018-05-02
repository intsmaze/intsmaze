package org.intsmaze.persistence.pojos;

import java.sql.Timestamp;

import org.intsmaze.persistence.common.BasePojo;


public class FDFBTemplate extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6314651703142937652L;

	private String seqno;

    private String templateName;

    private String templateType;

    private String receiver;

    private String templateAttachment;

    private Timestamp createon;

    private String createby;

    private Timestamp modifyon;

    private String modifyby;

    private byte[] templateContent;
    
    private String templateParams;
    
    private String subject;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType == null ? null : templateType.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getTemplateAttachment() {
        return templateAttachment;
    }

    public void setTemplateAttachment(String templateAttachment) {
        this.templateAttachment = templateAttachment == null ? null : templateAttachment.trim();
    }

    public Timestamp getCreateon() {
        return createon;
    }

    public void setCreateon(Timestamp createon) {
        this.createon = createon;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Timestamp getModifyon() {
        return modifyon;
    }

    public void setModifyon(Timestamp modifyon) {
        this.modifyon = modifyon;
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby == null ? null : modifyby.trim();
    }

    public byte[] getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(byte[] templateContent) {
        this.templateContent = templateContent;
    }

	public String getTemplateParams() {
		return templateParams;
	}

	public void setTemplateParams(String templateParams) {
		this.templateParams = templateParams;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}