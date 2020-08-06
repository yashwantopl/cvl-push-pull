package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EnhancedAddressType extends BasicAddressType {
	
	@JsonProperty("AddrReportedDate")
	private String addrReportedDate;
	
	@JsonProperty("AcctReportedDate")
	private String acctReportedDate;

	@JsonProperty("AcctOpenedDate")
	private String acctOpenedDate;

	public String getAddrReportedDate() {
		return addrReportedDate;
	}

	public void setAddrReportedDate(String addrReportedDate) {
		this.addrReportedDate = addrReportedDate;
	}

	public String getAcctReportedDate() {
		return acctReportedDate;
	}

	public void setAcctReportedDate(String acctReportedDate) {
		this.acctReportedDate = acctReportedDate;
	}

	public String getAcctOpenedDate() {
		return acctOpenedDate;
	}

	public void setAcctOpenedDate(String acctOpenedDate) {
		this.acctOpenedDate = acctOpenedDate;
	}
	
	
}
