package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;

import com.capitaworld.service.loans.model.ProductMasterRequest;

public class UniformProductParamterRequest extends ProductMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double minAmount;
	private Double maxAmount;
	private Double roi;
	private Double pf;

	public UniformProductParamterRequest(){
		super();
	}
	
	public UniformProductParamterRequest(Long id){
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
		return "UniformProductParamterRequest [minAmount=" + minAmount + ", maxAmount=" + maxAmount + ", roi=" + roi + ", pf="
				+ pf + "]";
	}
}