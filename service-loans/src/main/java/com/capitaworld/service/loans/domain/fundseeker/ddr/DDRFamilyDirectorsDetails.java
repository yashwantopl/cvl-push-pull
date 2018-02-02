package com.capitaworld.service.loans.domain.fundseeker.ddr;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by : harshit
 * The persistent class for the fs_ddr_family_directors_details database table.
 * 
 */
@Entity
@Table(name="fs_ddr_family_directors_details")
@NamedQuery(name="DDRFamilyDirectorsDetails.findAll", query="SELECT a FROM DDRFamilyDirectorsDetails a")
public class DDRFamilyDirectorsDetails  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fs_ddr_form_id")
	private Long ddrFormId;
	
	@Column(name = "background_id")
	private Long backgroundId;
	
	private String name;
	
	@Column(name = "marital_status")
	private Integer maritalStatus;
	
	@Column(name = "is_house_owned")
	private String isHouseOwned;
	
	@Column(name="address_of_other_property")
	private String addressOfOtherProperty;
	
	@Column(name="name_occupation_of_spouse")
	private String nameOccupationOfSpouse;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name = "modify_by")
	private Long modifyBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "is_active")
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDdrFormId() {
		return ddrFormId;
	}

	public void setDdrFormId(Long ddrFormId) {
		this.ddrFormId = ddrFormId;
	}

	public Long getBackgroundId() {
		return backgroundId;
	}

	public void setBackgroundId(Long backgroundId) {
		this.backgroundId = backgroundId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getIsHouseOwned() {
		return isHouseOwned;
	}

	public void setIsHouseOwned(String isHouseOwned) {
		this.isHouseOwned = isHouseOwned;
	}

	public String getAddressOfOtherProperty() {
		return addressOfOtherProperty;
	}

	public void setAddressOfOtherProperty(String addressOfOtherProperty) {
		this.addressOfOtherProperty = addressOfOtherProperty;
	}

	public String getNameOccupationOfSpouse() {
		return nameOccupationOfSpouse;
	}

	public void setNameOccupationOfSpouse(String nameOccupationOfSpouse) {
		this.nameOccupationOfSpouse = nameOccupationOfSpouse;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	

}
