package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Part")
@XmlAccessorType(XmlAccessType.FIELD)
public class Part {

	@XmlAttribute(name = "errorCode")
	private String errorCode;
	
	@XmlAttribute(name = "perfiosTransactionId")
	private String perfiosTransactionId;
	
	@XmlAttribute(name = "reason")
	private String reason;
	
	@XmlAttribute(name = "status")
	private String status;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getPerfiosTransactionId() {
		return perfiosTransactionId;
	}

	public void setPerfiosTransactionId(String perfiosTransactionId) {
		this.perfiosTransactionId = perfiosTransactionId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
