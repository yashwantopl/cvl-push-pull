/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProductServices {

	@JsonProperty("file-id")
	private String fileId;

	@JsonProperty("description-of-highest-turnover-contributing-product-or-services")
	private String descriptionOfHighestTurnoverContributingProductOrServices;

	@JsonProperty("as-on-date")
	private String asOnDate;

	@JsonProperty("financial-year")
	private String financialYear;

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the descriptionOfHighestTurnoverContributingProductOrServices
	 */
	public String getDescriptionOfHighestTurnoverContributingProductOrServices() {
		return descriptionOfHighestTurnoverContributingProductOrServices;
	}

	/**
	 * @param descriptionOfHighestTurnoverContributingProductOrServices
	 *            the descriptionOfHighestTurnoverContributingProductOrServices to
	 *            set
	 */
	public void setDescriptionOfHighestTurnoverContributingProductOrServices(
			String descriptionOfHighestTurnoverContributingProductOrServices) {
		this.descriptionOfHighestTurnoverContributingProductOrServices = descriptionOfHighestTurnoverContributingProductOrServices;
	}

	/**
	 * @return the asOnDate
	 */
	public String getAsOnDate() {
		return asOnDate;
	}

	/**
	 * @param asOnDate
	 *            the asOnDate to set
	 */
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

	/**
	 * @return the financialYear
	 */
	public String getFinancialYear() {
		return financialYear;
	}

	/**
	 * @param financialYear
	 *            the financialYear to set
	 */
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

}
