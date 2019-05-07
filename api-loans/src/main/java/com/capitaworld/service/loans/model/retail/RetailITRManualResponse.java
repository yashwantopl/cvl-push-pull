/*
* @author harshit
*/
/**
 * @author harshit
 */
package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetailITRManualResponse  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Long applicationId;
	private Long coAppId;
	private String firstName;
	private String secondName;
	private String lastName;
	private Date dob;
	private String pan;
	private String premiseNo;
	private String streetName;
	private String landmark;
	private Integer countryId;
	private Integer stateId;
	private Long cityId;
	private Long pincode;
	private String subDistrict;
	private Long distId;
	private String district;
	private String email;
	private String telephone;
	private Double latestYear;
	private Double firstPreYearSalary;
	private Double secondPreYearSalary;
	private Integer businessTypeId;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPremiseNo() {
		return premiseNo;
	}
	public void setPremiseNo(String premiseNo) {
		this.premiseNo = premiseNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	public String getSubDistrict() {
		return subDistrict;
	}
	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}
	public Long getDistId() {
		return distId;
	}
	public void setDistId(Long distId) {
		this.distId = distId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Double getLatestYear() {
		return latestYear;
	}
	public void setLatestYear(Double latestYear) {
		this.latestYear = latestYear;
	}
	public Double getFirstPreYearSalary() {
		return firstPreYearSalary;
	}
	public void setFirstPreYearSalary(Double firstPreYearSalary) {
		this.firstPreYearSalary = firstPreYearSalary;
	}
	public Double getSecondPreYearSalary() {
		return secondPreYearSalary;
	}
	public void setSecondPreYearSalary(Double secondPreYearSalary) {
		this.secondPreYearSalary = secondPreYearSalary;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getCoAppId() {
		return coAppId;
	}
	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	@Override
	public String toString() {
		return "ITRPLFormRequest [userId=" + userId + ", applicationId=" + applicationId + ", coAppId=" + coAppId
				+ ", firstName=" + firstName + ", secondName=" + secondName + ", lastName=" + lastName + ", dob=" + dob
				+ ", pan=" + pan + ", premiseNo=" + premiseNo + ", streetName=" + streetName + ", landmark=" + landmark
				+ ", countryId=" + countryId + ", stateId=" + stateId + ", cityId=" + cityId + ", pincode=" + pincode
				+ ", subDistrict=" + subDistrict + ", distId=" + distId + ", district=" + district + ", email=" + email
				+ ", telephone=" + telephone + ", latestYear=" + latestYear + ", firstPreYearSalary="
				+ firstPreYearSalary + ", secondPreYearSalary=" + secondPreYearSalary + ", businessTypeId="
				+ businessTypeId + "]";
	}
	

}
