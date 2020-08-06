package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommercialIdentityInfo extends IdentityInfo {

	@JsonProperty("BusinessRegistration")
	private List<IDDetails> businessRegistrationList;

	@JsonProperty("CIN")
	private List<IDDetails> cinList;

	@JsonProperty("TIN")
	private List<IDDetails> tinList;

	@JsonProperty("ServiceTax")
	private List<IDDetails> servicetaxList;

	@JsonProperty("CompanyRegistration")
	private List<IDDetails> companyregistrationList;

	@JsonProperty("Dunsnbr")
	private List<IDDetails> dunsnbrList;

	public List<IDDetails> getDunsnbrList() {
		return dunsnbrList;
	}

	public void setDunsnbrList(List<IDDetails> dunsnbrList) {
		this.dunsnbrList = dunsnbrList;
	}

	public List<IDDetails> getServicetaxList() {
		return servicetaxList;
	}

	public void setServicetaxList(List<IDDetails> servicetaxList) {
		this.servicetaxList = servicetaxList;
	}

	public List<IDDetails> getCompanyregistrationList() {
		return companyregistrationList;
	}

	public void setCompanyregistrationList(List<IDDetails> companyregistrationList) {
		this.companyregistrationList = companyregistrationList;
	}

	public List<IDDetails> getBusinessRegistrationList() {
		if (businessRegistrationList == null) {
			businessRegistrationList = new ArrayList<IDDetails>();
		}

		return businessRegistrationList;
	}

	public void setBusinessRegistrationList(List<IDDetails> businessRegistrationList) {
		this.businessRegistrationList = businessRegistrationList;
	}

	public List<IDDetails> getCinList() {
		if (cinList == null) {
			cinList = new ArrayList<IDDetails>();
		}
		return cinList;
	}

	public void setCinList(List<IDDetails> cinList) {
		this.cinList = cinList;
	}

	public List<IDDetails> getTinList() {
		if (tinList == null) {
			tinList = new ArrayList<IDDetails>();
		}

		return tinList;
	}

	public void setTinList(List<IDDetails> tinList) {
		this.tinList = tinList;
	}

}
