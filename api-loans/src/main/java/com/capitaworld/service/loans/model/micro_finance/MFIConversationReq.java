package com.capitaworld.service.loans.model.micro_finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MFIConversationReq {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer fromId;

    private Integer toId;

    private Integer superToId;

    private Long applicationId;

    private String message;

    private Date messageDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public Integer getSuperToId() {
        return superToId;
    }

    public void setSuperToId(Integer superToId) {
        this.superToId = superToId;
    }

    @Override
    public String toString() {
        return "MFIConversationReq{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", superToId=" + superToId +
                ", applicationId=" + applicationId +
                ", message='" + message + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}
