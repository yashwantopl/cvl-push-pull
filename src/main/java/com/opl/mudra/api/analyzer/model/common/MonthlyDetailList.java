package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "MonthlyDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonthlyDetailList {
	
	@XmlElement(name = "MonthlyDetail")
	private List<MonthlyDetail> monthlyDetails;

	public List<MonthlyDetail> getMonthlyDetails() {
		return monthlyDetails;
	}

	public void setMonthlyDetails(List<MonthlyDetail> monthlyDetails) {
		this.monthlyDetails = monthlyDetails;
	}

	public MonthlyDetailList(List<MonthlyDetail> monthlyDetails) {
		super();
		this.monthlyDetails = monthlyDetails;
	}

	public MonthlyDetailList() {
		super();
	}

	 
	
	
}
