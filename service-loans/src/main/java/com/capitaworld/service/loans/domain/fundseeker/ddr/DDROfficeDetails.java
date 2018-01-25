package com.capitaworld.service.loans.domain.fundseeker.ddr;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by : harshit
 * The persistent class for the fs_ddr_office_details database table.
 * 
 */
@Entity
@Table(name="fs_ddr_office_details")
@NamedQuery(name="DDROfficeDetails.findAll", query="SELECT a FROM DDROfficeDetails a")
public class DDROfficeDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fs_ddr_form_id")
	private Long ddrFormId;

	@Column(name="area_in_sqft")
	private String areaInSqft;
	
	@Column(name ="no_employee")
	private Integer noEmployee;

	@Column(name ="any_other_showroom")
	private String anyOtherShowroom;
	
	@Column(name ="office_type")
	private Integer officeType;
	
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

	public String getAreaInSqft() {
		return areaInSqft;
	}

	public void setAreaInSqft(String areaInSqft) {
		this.areaInSqft = areaInSqft;
	}

	public Integer getNoEmployee() {
		return noEmployee;
	}

	public void setNoEmployee(Integer noEmployee) {
		this.noEmployee = noEmployee;
	}

	public String getAnyOtherShowroom() {
		return anyOtherShowroom;
	}

	public void setAnyOtherShowroom(String anyOtherShowroom) {
		this.anyOtherShowroom = anyOtherShowroom;
	}

	public Integer getOfficeType() {
		return officeType;
	}

	public void setOfficeType(Integer officeType) {
		this.officeType = officeType;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDROfficeDetails [id=" + id + ", ddrFormId=" + ddrFormId + ", areaInSqft=" + areaInSqft
				+ ", noEmployee=" + noEmployee + ", anyOtherShowroom=" + anyOtherShowroom + ", officeType=" + officeType
				+ ", isActive=" + isActive + "]";
	}

	
	
}
