package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Xns")
@XmlAccessorType(XmlAccessType.FIELD)
public class Xns {

	@XmlAttribute(name = "accountNo")
	private String accountNo;
	
	@XmlAttribute(name = "accountType")
	private String accountType;
	
	@XmlElement(name = "Xn")
	private List<Xn> xn;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public List<Xn> getXn() {
		return xn;
	}

	public void setXn(List<Xn> xn) {
		this.xn = xn;
	}

	public Xns(List<Xn> xn) {
		super();
		this.xn = xn;
	}

	public Xns() {
		super();
	}

	
	
	
	
	
	
}
