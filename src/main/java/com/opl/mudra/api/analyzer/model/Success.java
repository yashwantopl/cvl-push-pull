package com.opl.mudra.api.analyzer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Success")
@XmlAccessorType(XmlAccessType.FIELD)
public class Success {
	
	@XmlElement(name = "accounts")
	private UploadData accounts;

	@XmlElement(name = "statementId", required = true)
	private String statementId;
	
	
	
	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

	public UploadData getAccounts() {
		return accounts;
	}

	public void setAccounts(UploadData accounts) {
		this.accounts = accounts;
	}
	
	

}
