package org.intsmaze.persistence.pojos;

import java.util.Date;

import org.intsmaze.persistence.common.BasePojo;

public class RiskScoreDetail extends BasePojo{
    private String seqno;

    private String customerId;

    private String num;

    private String riskSubitem;

    private String one;

    private String two;

    private String three;

    private String four;

    private String score;

    private String fromWeight;

    private String prono;

    private String createby;

    private Date createon;

    private String modifyby;

    private Date modifyon;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getRiskSubitem() {
        return riskSubitem;
    }

    public void setRiskSubitem(String riskSubitem) {
        this.riskSubitem = riskSubitem == null ? null : riskSubitem.trim();
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one == null ? null : one.trim();
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two == null ? null : two.trim();
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three == null ? null : three.trim();
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four == null ? null : four.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getFromWeight() {
        return fromWeight;
    }

    public void setFromWeight(String fromWeight) {
        this.fromWeight = fromWeight == null ? null : fromWeight.trim();
    }

    public String getProno() {
        return prono;
    }

    public void setProno(String prono) {
        this.prono = prono == null ? null : prono.trim();
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreateon() {
        return createon;
    }

    public void setCreateon(Date createon) {
        this.createon = createon;
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby == null ? null : modifyby.trim();
    }

    public Date getModifyon() {
        return modifyon;
    }

    public void setModifyon(Date modifyon) {
        this.modifyon = modifyon;
    }
}