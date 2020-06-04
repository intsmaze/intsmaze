package com.intsmaze.project.model;

import javax.persistence.*;

public class Sourcedata {
    private String uuid;

    @Column(name = "testTime")
    private String testtime;

    @Column(name = "seq_id")
    private Integer seqId;

    @Column(name = "stepNumber")
    private String stepnumber;

    @Column(name = "stepType")
    private String steptype;

    @Column(name = "cycleNumber")
    private Integer cyclenumber;

    @Column(name = "flowId")
    private String flowid;

    @Column(name = "threadName")
    private String threadname;

    @Column(name = "receiveTime")
    private String receivetime;

    /**
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return testTime
     */
    public String getTesttime() {
        return testtime;
    }

    /**
     * @param testtime
     */
    public void setTesttime(String testtime) {
        this.testtime = testtime;
    }

    /**
     * @return seq_id
     */
    public Integer getSeqId() {
        return seqId;
    }

    /**
     * @param seqId
     */
    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }

    /**
     * @return stepNumber
     */
    public String getStepnumber() {
        return stepnumber;
    }

    /**
     * @param stepnumber
     */
    public void setStepnumber(String stepnumber) {
        this.stepnumber = stepnumber;
    }

    /**
     * @return stepType
     */
    public String getSteptype() {
        return steptype;
    }

    /**
     * @param steptype
     */
    public void setSteptype(String steptype) {
        this.steptype = steptype;
    }

    /**
     * @return cycleNumber
     */
    public Integer getCyclenumber() {
        return cyclenumber;
    }

    /**
     * @param cyclenumber
     */
    public void setCyclenumber(Integer cyclenumber) {
        this.cyclenumber = cyclenumber;
    }

    /**
     * @return flowId
     */
    public String getFlowid() {
        return flowid;
    }

    /**
     * @param flowid
     */
    public void setFlowid(String flowid) {
        this.flowid = flowid;
    }

    /**
     * @return threadName
     */
    public String getThreadname() {
        return threadname;
    }

    /**
     * @param threadname
     */
    public void setThreadname(String threadname) {
        this.threadname = threadname;
    }

    /**
     * @return receiveTime
     */
    public String getReceivetime() {
        return receivetime;
    }

    /**
     * @param receivetime
     */
    public void setReceivetime(String receivetime) {
        this.receivetime = receivetime;
    }
}