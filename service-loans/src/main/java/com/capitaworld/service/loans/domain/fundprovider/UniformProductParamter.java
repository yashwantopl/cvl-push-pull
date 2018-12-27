package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the fp_working_capital_details database table.
 * 
 */
@Entity
@Table(name = "fp_working_capital_details")
@PrimaryKeyJoinColumn(referencedColumnName = "fp_product_id")
public class UniformProductParamter extends ProductMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "min_amount")
	private Double minAmount;
	@Column(name = "max_amount")
	private Double maxAmount;
	private Double roi;
	private Double pf;

	public UniformProductParamter(){
		super();
	}
	
	public UniformProductParamter(Long id){
		super();
	}
	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public Double getPf() {
		return pf;
	}

	public void setPf(Double pf) {
		this.pf = pf;
	}

	public Double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	@Override
	public String toString() {
		return "UniformProductParamter [minAmount=" + minAmount + ", maxAmount=" + maxAmount + ", roi=" + roi + ", pf="
				+ pf + "]";
	}
}