package com.opl.mudra.api.analyzer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Success")
@XmlAccessorType(XmlAccessType.FIELD)
public class PerfiosData {
	
@XmlElement(name = "perfiosTransactionId", required = true)
private String perfiosTransactionId;

public String getPerfiosTransactionId() {
	return perfiosTransactionId;
}

public void setPerfiosTransactionId(String perfiosTransactionId) {
	this.perfiosTransactionId = perfiosTransactionId;
}


}
