/**
 * 
 */
package com.opl.mudra.api.loans.model.sidbi;

/**
 * @author vijay.chauhan
 *
 */
public class PersonalCorporateGuaranteeRequest {

	private Long id;
	private String nameOfGuarantor;
	private String panCinAnyNo;
	private Double netWorth;
	private Boolean isActive;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameOfGuarantor() {
		return nameOfGuarantor;
	}
	public void setNameOfGuarantor(String nameOfGuarantor) {
		this.nameOfGuarantor = nameOfGuarantor;
	}
	public String getPanCinAnyNo() {
		return panCinAnyNo;
	}
	public void setPanCinAnyNo(String panCinAnyNo) {
		this.panCinAnyNo = panCinAnyNo;
	}
	public Double getNetWorth() {
		return netWorth;
	}
	public void setNetWorth(Double netWorth) {
		this.netWorth = netWorth;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
