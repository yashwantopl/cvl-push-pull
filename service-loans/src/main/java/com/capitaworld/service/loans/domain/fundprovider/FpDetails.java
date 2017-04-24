package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
@Table(name="fp_details")
@NamedQuery(name="FpDetail.findAll", query="SELECT f FROM FpDetail f")
public class FpDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private Long userId;

	@Column(name="about_us")
	private String aboutUs;

	@Column(name="address_city")
	private Long addressCity;

	@Column(name="address_country")
	private Integer addressCountry;

	@Column(name="address_land_mark")
	private String addressLandMark;

	@Column(name="address_pincode")
	private Long addressPincode;

	@Column(name="address_premise")
	private String addressPremise;

	@Column(name="address_state")
	private Long addressState;

	@Column(name="address_street")
	private String addressStreet;

	@Column(name="contact_person_name")
	private String contactPersonName;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private String designation;

	@Column(name="establishment_month")
	private Integer establishmentMonth;

	@Column(name="establishment_year_id")
	private Integer establishmentYearId;

	private Boolean isActive;

	private Double lattitude;

	private Double longitude;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="organisation_name")
	private String organisationName;

	private String pan;

	private String remark;

	@Column(name="unIntegererested_industry")
	private Long unIntegererestedIndustry;

	@Column(name="website_address")
	private String websiteAddress;

	public FpDetails() {
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAboutUs() {
		return this.aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public Long getAddressCity() {
		return this.addressCity;
	}

	public void setAddressCity(Long addressCity) {
		this.addressCity = addressCity;
	}

	public Integer getAddressCountry() {
		return this.addressCountry;
	}

	public void setAddressCountry(Integer addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressLandMark() {
		return this.addressLandMark;
	}

	public void setAddressLandMark(String addressLandMark) {
		this.addressLandMark = addressLandMark;
	}

	public Long getAddressPincode() {
		return this.addressPincode;
	}

	public void setAddressPincode(Long addressPincode) {
		this.addressPincode = addressPincode;
	}

	public String getAddressPremise() {
		return this.addressPremise;
	}

	public void setAddressPremise(String addressPremise) {
		this.addressPremise = addressPremise;
	}

	public Long getAddressState() {
		return this.addressState;
	}

	public void setAddressState(Long addressState) {
		this.addressState = addressState;
	}

	public String getAddressStreet() {
		return this.addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Integer getEstablishmentMonth() {
		return this.establishmentMonth;
	}

	public void setEstablishmentMonth(Integer establishmentMonth) {
		this.establishmentMonth = establishmentMonth;
	}

	public Integer getEstablishmentYearId() {
		return this.establishmentYearId;
	}

	public void setEstablishmentYearId(Integer establishmentYearId) {
		this.establishmentYearId = establishmentYearId;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getLattitude() {
		return this.lattitude;
	}

	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getOrganisationName() {
		return this.organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getPan() {
		return this.pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUnIntegererestedIndustry() {
		return this.unIntegererestedIndustry;
	}

	public void setUnIntegererestedIndustry(Long unIntegererestedIndustry) {
		this.unIntegererestedIndustry = unIntegererestedIndustry;
	}

	public String getWebsiteAddress() {
		return this.websiteAddress;
	}

	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}
}