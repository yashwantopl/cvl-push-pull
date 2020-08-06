package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Data",namespace="https://www.perfios.com/PIR")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountDetails {

	@XmlElement(name = "SummaryInfo")
	private SummaryInfo summaryInfo;
	
	private Long storageId;

	public SummaryInfo getSummaryInfo() {
		return summaryInfo;
	}

	public void setSummaryInfo(SummaryInfo summaryInfo) {
		this.summaryInfo = summaryInfo;
	}

	public Long getStorageId() {
		return storageId;
	}

	public void setStorageId(Long storageId) {
		this.storageId = storageId;
	}

	
	
	
}
