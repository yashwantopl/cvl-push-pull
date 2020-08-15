package com.opl.service.loans.domain.fundprovider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fp_co_lending_banks")
public class FpCoLendingBanks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bank")
	private String bank;

	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "is_nbfc")
	private Boolean isNbfc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsNbfc() {
		return isNbfc;
	}

	public void setIsNbfc(Boolean isNbfc) {
		this.isNbfc = isNbfc;
	}
	
	
	
	
	
}
