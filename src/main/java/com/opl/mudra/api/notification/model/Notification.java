package com.opl.mudra.api.notification.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opl.mudra.api.notification.utils.AckType;
import com.opl.mudra.api.notification.utils.ContentType;
import com.opl.mudra.api.notification.utils.NotificationType;

/**
 * @author Sanket
 *
 */
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9151646917155081519L;

	protected NotificationType type;
	protected String[] to;
	protected String[] cc;
	protected String from;
	protected ContentType contentType;
	protected String content;
	protected String title;
	protected Object subject;
	protected Long templateId;
	protected Map<String, Object> parameters = new HashMap<String, Object>();
	protected AckType ackType;
	protected Integer ackTimeout;
	protected Integer retryCount;
	protected Integer order;
	protected Boolean secure;
	protected String attachmentIds;
	protected String fileName;
	protected String fileFormat;
	protected byte[] contentInBytes;
	protected Long readSysNotifId;
	protected Boolean readStatusSysNotif;
	protected Long applicationId;
	protected Long productId;
	protected List<ContentAttachment> contentAttachments = new ArrayList<ContentAttachment>();
	protected Boolean isDynamic;
	protected String[] bcc;
	protected String status;
	protected Date createdDate;
	protected Long emailSubjectId;
	
	protected Integer loanTypeId;
	protected Long userOrgId;
	protected Long masterId;
	
	
	
	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public Integer getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(Integer loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public Boolean getIsDynamic() {
		return isDynamic;
	}

	public void setIsDynamic(Boolean isDynamic) {
		this.isDynamic = isDynamic;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public List<ContentAttachment> getContentAttachments() {
		return contentAttachments;
	}

	public void setContentAttachments(List<ContentAttachment> contentAttachments) {
		this.contentAttachments = contentAttachments;
	}

	public void addAttachment(ContentAttachment attachments) {
		getContentAttachments().add(attachments);
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Boolean getReadStatusSysNotif() {
		return readStatusSysNotif;
	}

	public void setReadStatusSysNotif(Boolean readStatusSysNotif) {
		this.readStatusSysNotif = readStatusSysNotif;
	}

	public Long getReadSysNotifId() {
		return readSysNotifId;
	}

	public void setReadSysNotifId(Long readSysNotifId) {
		this.readSysNotifId = readSysNotifId;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public AckType getAckType() {
		return ackType;
	}

	public void setAckType(AckType ackType) {
		this.ackType = ackType;
	}

	public Integer getAckTimeout() {
		return ackTimeout;
	}

	public void setAckTimeout(Integer ackTimeout) {
		this.ackTimeout = ackTimeout;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Boolean getSecure() {
		return secure;
	}

	public void setSecure(Boolean secure) {
		this.secure = secure;
	}

	public String getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(String attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public byte[] getContentInBytes() {
		return contentInBytes;
	}

	public void setContentInBytes(byte[] contentInBytes) {
		this.contentInBytes = contentInBytes;
	}
 
	public Object getSubject() {
		return subject;
	}

	public void setSubject(Object subject) {
		this.subject = subject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getEmailSubjectId() {
		return emailSubjectId;
	}

	public void setEmailSubjectId(Long emailSubjectId) {
		this.emailSubjectId = emailSubjectId;
	}

	@Override
	public String toString() {
		return "Notification [type=" + type + ", to=" + Arrays.toString(to) + ", cc=" + Arrays.toString(cc) + ", from="
				+ from + ", contentType=" + contentType + ", content=" + content + ", title=" + title + ", subject="
				+ subject + ", templateId=" + templateId + ", parameters=" + parameters + ", ackType=" + ackType
				+ ", ackTimeout=" + ackTimeout + ", retryCount=" + retryCount + ", order=" + order + ", secure="
				+ secure + ", attachmentIds=" + attachmentIds + ", fileName=" + fileName + ", fileFormat=" + fileFormat
				+ ", contentInBytes=" + Arrays.toString(contentInBytes) + ", readSysNotifId=" + readSysNotifId
				+ ", readStatusSysNotif=" + readStatusSysNotif + ", applicationId=" + applicationId + ", productId="
				+ productId + ", contentAttachments=" + contentAttachments + ", isDynamic=" + isDynamic + ", bcc="
				+ Arrays.toString(bcc) + ", status=" + status + ", createdDate=" + createdDate + ", emailSubjectId="
				+ emailSubjectId + ", loanTypeId=" + loanTypeId + ", userOrgId=" + userOrgId + ", masterId=" + masterId
				+ "]";
	}
	
}
