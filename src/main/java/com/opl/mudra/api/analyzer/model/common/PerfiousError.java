package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Error")
@XmlAccessorType(XmlAccessType.FIELD)
public class PerfiousError {

	@XmlElement(name = "code")
	private String code;
	
	@XmlElement(name = "message")
	private String message;
	
	@XmlElement(name = "statementErrorCode")
	private String statementErrorCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatementErrorCode() {
		return statementErrorCode;
	}

	public void setStatementErrorCode(String statementErrorCode) {
		this.statementErrorCode = statementErrorCode;
	}
	
	
	
}
