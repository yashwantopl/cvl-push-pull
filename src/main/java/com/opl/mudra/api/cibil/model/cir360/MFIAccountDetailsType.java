package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MFIAccountDetailsType extends AccountDetailsType {

	@JsonProperty("AdditionalMFIDetails")
	private AdditionalMFIDetails additionalMFIDetails;
	
	@JsonProperty("BranchIDMFI")
	private String BranchIDMFI;
	
	@JsonProperty("KendraIDMFI")
	private String KendraIDMFI;
	
	@JsonProperty("History24Months")
	private List<MonthlyDetailType> history24Months;

	public AdditionalMFIDetails getAdditionalMFIDetails() {
		return additionalMFIDetails;
	}

	public void setAdditionalMFIDetails(AdditionalMFIDetails additionalMFIDetails) {
		this.additionalMFIDetails = additionalMFIDetails;
	}

	public String getBranchIDMFI() {
		return BranchIDMFI;
	}

	public void setBranchIDMFI(String branchIDMFI) {
		BranchIDMFI = branchIDMFI;
	}

	public String getKendraIDMFI() {
		return KendraIDMFI;
	}

	public void setKendraIDMFI(String kendraIDMFI) {
		KendraIDMFI = kendraIDMFI;
	}

	public List<MonthlyDetailType> getHistory24Months() {
		return history24Months;
	}

	public void setHistory24Months(List<MonthlyDetailType> history24Months) {
		this.history24Months = history24Months;
	}
	
	
}
