package com.opl.mudra.api.connect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeleCallCenterRequest {

    private Long id;

    private Long userId;

    private Long applicationId;

    private Long tele_EmpId;

    private Long developerId;

    private String teleComment;

    private String stage;

    private Date createdDate;

    private Date createdFromDate;

    private Date createdToDate;

    private Date modifiedDate;

    private Date modifiedFromDate;

    private Date modifiedToDate;

    private Long teleEmpStatus;

    private String developerStatus;

    public TeleCallCenterRequest(){

    }

    public TeleCallCenterRequest(Long userId,Long applicationId,Long tele_EmpId,
                                 Long developerId,String teleComment,String stage,
                                 Date createdDate,Date modifiedDate,Long teleEmpStatus,
                                 String developerStatus){

        this.userId=userId;
        this.applicationId=applicationId;
        this.tele_EmpId=tele_EmpId;
        this.developerId=developerId;
        this.teleComment=teleComment;
        this.stage=stage;
        this.createdDate=createdDate;
        this.modifiedDate=modifiedDate;
        this.teleEmpStatus=teleEmpStatus;
        this.developerStatus=developerStatus;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getTele_EmpId() {
        return tele_EmpId;
    }

    public void setTele_EmpId(Long tele_EmpId) {
        this.tele_EmpId = tele_EmpId;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    public String getTeleComment() {
        return teleComment;
    }

    public void setTeleComment(String teleComment) {
        this.teleComment = teleComment;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getTeleEmpStatus() {
        return teleEmpStatus;
    }

    public void setTeleEmpStatus(Long teleEmpStatus) {
        this.teleEmpStatus = teleEmpStatus;
    }

    public String getDeveloperStatus() {
        return developerStatus;
    }

    public void setDeveloperStatus(String developerStatus) {
        this.developerStatus = developerStatus;
    }


    public Date getCreatedFromDate() {
        return createdFromDate;
    }

    public void setCreatedFromDate(Date createdFromDate) {
        this.createdFromDate = createdFromDate;
    }

    public Date getCreatedToDate() {
        return createdToDate;
    }

    public void setCreatedToDate(Date createdToDate) {
        this.createdToDate = createdToDate;
    }

    public Date getModifiedFromDate() {
        return modifiedFromDate;
    }

    public void setModifiedFromDate(Date modifiedFromDate) {
        this.modifiedFromDate = modifiedFromDate;
    }

    public Date getModifiedToDate() {
        return modifiedToDate;
    }

    public void setModifiedToDate(Date modifiedToDate) {
        this.modifiedToDate = modifiedToDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}