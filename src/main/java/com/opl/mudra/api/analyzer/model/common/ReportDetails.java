package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.*;

//@XmlRootElement(name = "account")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"customerInfo"})
@XmlRootElement(name="Data")
public class ReportDetails {

	

	@XmlElement(name = "CustomerInfo", required = true)
	private CustomerInfo customerInfo ;

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	

	

}