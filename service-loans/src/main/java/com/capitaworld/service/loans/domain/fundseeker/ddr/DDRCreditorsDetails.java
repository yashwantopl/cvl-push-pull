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
 * The persistent class for the fs_ddr_creditors_details database table.
 * 
 */
@Entity
@Table(name="fs_ddr_creditors_details")
@NamedQuery(name="DDRCreditorsDetails.findAll", query="SELECT a FROM DDRCreditorsDetails a")
public class DDRCreditorsDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fs_ddr_form_id")
	private Long ddrFormId;

	private String name;
	
	private Double amount;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDRCreditorsDetails [id=" + id + ", ddrFormId=" + ddrFormId + ", name=" + name + ", amount=" + amount
				+ ", isActive=" + isActive + "]";
	}

	
	

}
