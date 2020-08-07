package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesProductServicesOriginal {
	
	@JsonProperty("as-on-date")
	private String asOnDate;
	
	@JsonProperty("description-of-highest-turnover-contributing-product-or-services")
	private String descriptionOfHighestTurnoverContributingProductOrServices;
	
	@JsonProperty("financial-year")
	private String financialYear;

	public String getAsOnDate() {
		return asOnDate;
	}

	public String getDescriptionOfHighestTurnoverContributingProductOrServices() {
		return descriptionOfHighestTurnoverContributingProductOrServices;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

	public void setDescriptionOfHighestTurnoverContributingProductOrServices(
			String descriptionOfHighestTurnoverContributingProductOrServices) {
		this.descriptionOfHighestTurnoverContributingProductOrServices = descriptionOfHighestTurnoverContributingProductOrServices;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	
	
	

}
