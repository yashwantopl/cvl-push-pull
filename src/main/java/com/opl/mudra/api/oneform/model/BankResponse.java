package com.opl.mudra.api.oneform.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BankResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7603818892494209423L;

	private Long id;

	private String name;

	private String code;

	private Boolean isActive;

	//For Salaried/pensioner
	
	private Float bonus;

	private Float incentive;

	private Boolean taxes;
	
    //Other than Salaried/pensioner
    
    private Float patCur;
    
    private Float patPrev;
    
    private Float manRemuCur;
    
    private Float manRemuPrev;
    
    private Float depreciationCur;
    
    private Float depreciationPrev;
    
    //Common For All type Of Employed
    private Float otherIncome;
    
    private Boolean otherInvestment;
    
    private Float marketValue;
    
    private Float saleDeedValue;
    
    private Float originalValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Float getBonus() {
		return bonus;
	}

	public void setBonus(Float bonus) {
		this.bonus = bonus;
	}

	public Float getIncentive() {
		return incentive;
	}

	public void setIncentive(Float incentive) {
		this.incentive = incentive;
	}

	public Boolean getTaxes() {
		return taxes;
	}

	public void setTaxes(Boolean taxes) {
		this.taxes = taxes;
	}

	public Float getPatCur() {
		return patCur;
	}

	public void setPatCur(Float patCur) {
		this.patCur = patCur;
	}

	public Float getPatPrev() {
		return patPrev;
	}

	public void setPatPrev(Float patPrev) {
		this.patPrev = patPrev;
	}

	public Float getManRemuCur() {
		return manRemuCur;
	}

	public void setManRemuCur(Float manRemuCur) {
		this.manRemuCur = manRemuCur;
	}

	public Float getManRemuPrev() {
		return manRemuPrev;
	}

	public void setManRemuPrev(Float manRemuPrev) {
		this.manRemuPrev = manRemuPrev;
	}

	public Float getDepreciationCur() {
		return depreciationCur;
	}

	public void setDepreciationCur(Float depreciationCur) {
		this.depreciationCur = depreciationCur;
	}

	public Float getDepreciationPrev() {
		return depreciationPrev;
	}

	public void setDepreciationPrev(Float depreciationPrev) {
		this.depreciationPrev = depreciationPrev;
	}

	public Float getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(Float otherIncome) {
		this.otherIncome = otherIncome;
	}

	public Boolean getOtherInvestment() {
		return otherInvestment;
	}

	public void setOtherInvestment(Boolean otherInvestment) {
		this.otherInvestment = otherInvestment;
	}
	public Float getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Float marketValue) {
		this.marketValue = marketValue;
	}

	public Float getSaleDeedValue() {
		return saleDeedValue;
	}

	public void setSaleDeedValue(Float saleDeedValue) {
		this.saleDeedValue = saleDeedValue;
	}

	public Float getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(Float originalValue) {
		this.originalValue = originalValue;
	}
    
}
