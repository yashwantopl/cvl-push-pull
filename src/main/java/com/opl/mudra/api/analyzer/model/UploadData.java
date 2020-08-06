package com.opl.mudra.api.analyzer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadData {

	@XmlElement(name = "account")
	private Account[] account;



	public Account[] getAccount() {
		return account;
	}

	public void setAccount(Account[] account) {
		this.account = account;
	}
	
	
}
	
