package com.opl.service.loans.domain.fundseeker.mfi;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mfi_conversation")
public class MFIConversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_id")
    private Integer fromId;

    @Column(name = "to_id")
    private Integer toId;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "message")
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "message_date")
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
}
