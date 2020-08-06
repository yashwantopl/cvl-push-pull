package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "MonthlyTopNHighValueCreditXns")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonthlyTopNHighValueCreditXns {
	@XmlElement(name = "MonthlyTopNHighValueCreditXn")
	private List<MonthlyTopNHighValueCreditXn> monthlyTopNHighValueCreditXn;

	public List<MonthlyTopNHighValueCreditXn> getMonthlyTopNHighValueCreditXn() {
		return monthlyTopNHighValueCreditXn;
	}

	public void setMonthlyTopNHighValueCreditXn(List<MonthlyTopNHighValueCreditXn> monthlyTopNHighValueCreditXn) {
		this.monthlyTopNHighValueCreditXn = monthlyTopNHighValueCreditXn;
	}

	public MonthlyTopNHighValueCreditXns(List<MonthlyTopNHighValueCreditXn> monthlyTopNHighValueCreditXn) {
		super();
		this.monthlyTopNHighValueCreditXn = monthlyTopNHighValueCreditXn;
	}

	public MonthlyTopNHighValueCreditXns() {
		super();
	}
	
	
}
