package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "SummaryInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class SummaryInfo {

	@XmlAttribute(name = "instName")
	private String instName;
	
	@XmlAttribute(name = "accNo")
	private String accNo;
	
	@XmlAttribute(name = "accType")
	private String accType;
	
	@XmlAttribute(name = "fullMonthCount")
	private String fullMonthCount;
	
	@XmlElement(name = "Total")
	private SummaryInfoTotalDetails summaryInfoTotalDetails;

	@XmlElement(name = "Average")
	private SummaryInfoAverageDetails summaryInfoAverageDetails;


	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public SummaryInfoTotalDetails getSummaryInfoTotalDetails() {
		return summaryInfoTotalDetails;
	}

	public void setSummaryInfoTotalDetails(SummaryInfoTotalDetails summaryInfoTotalDetails) {
		this.summaryInfoTotalDetails = summaryInfoTotalDetails;
	}

	public SummaryInfoAverageDetails getSummaryInfoAverageDetails() {
		return summaryInfoAverageDetails;
	}

	public void setSummaryInfoAverageDetails(SummaryInfoAverageDetails summaryInfoAverageDetails) {
		this.summaryInfoAverageDetails = summaryInfoAverageDetails;
	}

	public String getFullMonthCount() {
		return fullMonthCount;
	}

	public void setFullMonthCount(String fullMonthCount) {
		this.fullMonthCount = fullMonthCount;
	}

	
	
	
	
	
	
}
