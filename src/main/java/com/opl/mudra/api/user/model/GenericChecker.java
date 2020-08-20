package com.opl.mudra.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericChecker implements Serializable{
    private Long id;
    private Long jobId;
    private Long currentStep;
    private Long toStep;
    private Long actionId;
    private String code;
    private Boolean actionFlag;

    public GenericChecker() {
        // Do nothing because of X and Y.
    }

    //getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Long currentStep) {
        this.currentStep = currentStep;
    }

    public Long getToStep() {
        return toStep;
    }

    public void setToStep(Long toStep) {
        this.toStep = toStep;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Boolean getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(Boolean actionFlag) {
        this.actionFlag = actionFlag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
