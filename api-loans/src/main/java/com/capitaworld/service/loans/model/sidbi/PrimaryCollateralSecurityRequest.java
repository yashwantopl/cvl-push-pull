/**
 * 
 */
package com.capitaworld.service.loans.model.sidbi;

/**
 * @author vijay.chauhan
 *
 */
public class PrimaryCollateralSecurityRequest {

	private Long id;
	private Integer particularsId;
	private String particularsName;
	private String otherParticular;
	private String nameOfOwner;
	private String nature;
	private String details;
	private Double marketValue;
	private String particularsOfCharge;
	private String chargeOfferedToSIDBI;
	/**
	 * @return the particularsId
	 */
	public Integer getParticularsId() {
		return particularsId;
	}
	/**
	 * @param particularsId the particularsId to set
	 */
	public void setParticularsId(Integer particularsId) {
		this.particularsId = particularsId;
	}
	/**
	 * @return the particularsName
	 */
	public String getParticularsName() {
		return particularsName;
	}
	/**
	 * @param particularsName the particularsName to set
	 */
	public void setParticularsName(String particularsName) {
		this.particularsName = particularsName;
	}
	/**
	 * @return the otherParticular
	 */
	public String getOtherParticular() {
		return otherParticular;
	}
	/**
	 * @param otherParticular the otherParticular to set
	 */
	public void setOtherParticular(String otherParticular) {
		this.otherParticular = otherParticular;
	}
	/**
	 * @return the nameOfOwner
	 */
	public String getNameOfOwner() {
		return nameOfOwner;
	}
	/**
	 * @param nameOfOwner the nameOfOwner to set
	 */
	public void setNameOfOwner(String nameOfOwner) {
		this.nameOfOwner = nameOfOwner;
	}
	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}
	/**
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * @return the marketValue
	 */
	public Double getMarketValue() {
		return marketValue;
	}
	/**
	 * @param marketValue the marketValue to set
	 */
	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}
	/**
	 * @return the particularsOfCharge
	 */
	public String getParticularsOfCharge() {
		return particularsOfCharge;
	}
	/**
	 * @param particularsOfCharge the particularsOfCharge to set
	 */
	public void setParticularsOfCharge(String particularsOfCharge) {
		this.particularsOfCharge = particularsOfCharge;
	}
	/**
	 * @return the chargeOfferedToSIDBI
	 */
	public String getChargeOfferedToSIDBI() {
		return chargeOfferedToSIDBI;
	}
	/**
	 * @param chargeOfferedToSIDBI the chargeOfferedToSIDBI to set
	 */
	public void setChargeOfferedToSIDBI(String chargeOfferedToSIDBI) {
		this.chargeOfferedToSIDBI = chargeOfferedToSIDBI;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
