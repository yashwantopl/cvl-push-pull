package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "MonthlyTopNHighValueDebitXns")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonthlyTopNHighValueDebitXns {
	@XmlElement(name = "MonthlyTopNHighValueDebitXn")
	private List<MonthlyTopNHighValueDebitXn> monthlyTopNHighValueDebitXn;

	public List<MonthlyTopNHighValueDebitXn> getMonthlyTopNHighValueDebitXn() {
		return monthlyTopNHighValueDebitXn;
	}

	public void setMonthlyTopNHighValueDebitXn(List<MonthlyTopNHighValueDebitXn> monthlyTopNHighValueDebitXn) {
		this.monthlyTopNHighValueDebitXn = monthlyTopNHighValueDebitXn;
	}

	/**
	 * @param monthlyTopNHighValueDebitXn
	 */
	public MonthlyTopNHighValueDebitXns(List<MonthlyTopNHighValueDebitXn> monthlyTopNHighValueDebitXn) {
		super();
		this.monthlyTopNHighValueDebitXn = monthlyTopNHighValueDebitXn;
	}

	/**
	 * 
	 */
	public MonthlyTopNHighValueDebitXns() {
		super();
	}
	
	
	
}
