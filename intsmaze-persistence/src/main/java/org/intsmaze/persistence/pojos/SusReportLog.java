package org.intsmaze.persistence.pojos;

import org.intsmaze.persistence.common.BasePojo;

public class SusReportLog extends BasePojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3378298135840487024L;

	private String seqno;

    private String reportType;

    private String tradeFeature;

    private String fileType;

    private String fileName;

    private String generateTime;

    private String reportTime;

    private String zipName;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
    }

    public String getTradeFeature() {
        return tradeFeature;
    }

    public void setTradeFeature(String tradeFeature) {
        this.tradeFeature = tradeFeature == null ? null : tradeFeature.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(String generateTime) {
        this.generateTime = generateTime == null ? null : generateTime.trim();
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime == null ? null : reportTime.trim();
    }

    public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName == null ? null : zipName.trim();
    }
}