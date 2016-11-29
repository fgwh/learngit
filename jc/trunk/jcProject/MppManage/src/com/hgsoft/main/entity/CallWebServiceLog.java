package com.hgsoft.main.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Bruce.Zhan on 2014/8/7.
 */
public class CallWebServiceLog implements Serializable {

    private Integer id;
    private Integer sourceSystemId;
    private String accountNum;
    private String returnFlage;
    private String callUserName;
    private Timestamp callTime;
    private Integer currentStep;
    private Integer status;
    private String remark;

    public CallWebServiceLog() {
    }

    public CallWebServiceLog(String returnFlage, Integer status, String remark) {
        this.returnFlage = returnFlage;
        this.status = status;
        this.remark = remark;
    }

    public CallWebServiceLog(Integer sourceSystemId, String returnFlage, Integer status, String remark) {
        this.sourceSystemId = sourceSystemId;
        this.returnFlage = returnFlage;
        this.status = status;
        this.remark = remark;
    }

    public CallWebServiceLog(Integer sourceSystemId, String returnFlage, String callUserName, Integer status, String remark) {
        this.sourceSystemId = sourceSystemId;
        this.returnFlage = returnFlage;
        this.callUserName = callUserName;
        this.status = status;
        this.remark = remark;
    }

    public CallWebServiceLog(Integer sourceSystemId, String accountNum, String returnFlage, String callUserName, Integer status, String remark) {
        this.sourceSystemId = sourceSystemId;
        this.accountNum = accountNum;
        this.returnFlage = returnFlage;
        this.callUserName = callUserName;
        this.status = status;
        this.remark = remark;
    }

    public CallWebServiceLog(Integer id, Integer sourceSystemId, String accountNum, String returnFlage, String callUserName, Timestamp callTime, Integer status, String remark) {
        this.id = id;
        this.sourceSystemId = sourceSystemId;
        this.accountNum = accountNum;
        this.returnFlage = returnFlage;
        this.callUserName = callUserName;
        this.callTime = callTime;
        this.status = status;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceSystemId() {
        return sourceSystemId;
    }

    public void setSourceSystemId(Integer sourceSystemId) {
        this.sourceSystemId = sourceSystemId;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getReturnFlage() {
        return returnFlage;
    }

    public void setReturnFlage(String returnFlage) {
        this.returnFlage = returnFlage;
    }

    public String getCallUserName() {
        return callUserName;
    }

    public void setCallUserName(String callUserName) {
        this.callUserName = callUserName;
    }

    public Timestamp getCallTime() {
        return callTime;
    }

    public void setCallTime(Timestamp callTime) {
        this.callTime = callTime;
    }

    public Integer getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
