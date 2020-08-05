package com.opl.mudra.api.loans.model;

import java.io.Serializable;

import javax.persistence.Column;

public class AutoLoanModelRequest extends RetailModelRequest implements Serializable {
	private static final long serialVersionUID = 1L;

    private Boolean isPersonalUse;
    private Boolean isOfficialUse;
    private Boolean isSmallCar;
    private Boolean isMidCar;
    private Boolean isLuxuryCar;
    private Boolean isSuvOrMuv;
    private Boolean isElectricOrNonConventionalCar;
    private Boolean isTwoWheelerLoan;
    private Boolean isElectricOrNonConventionalTwoWheeler;
    private Integer isSecondHandFourWheelerLoan;
    private Integer isSecondHandTwoWheelerLoan;

    public Boolean getIsPersonalUse() {
		return isPersonalUse;
	}

	public void setIsPersonalUse(Boolean isPersonalUse) {
		this.isPersonalUse = isPersonalUse;
	}

	public Boolean getIsOfficialUse() {
		return isOfficialUse;
	}

	public void setIsOfficialUse(Boolean isOfficialUse) {
		this.isOfficialUse = isOfficialUse;
	}

	public Boolean getIsSmallCar() {
		return isSmallCar;
	}

	public void setIsSmallCar(Boolean isSmallCar) {
		this.isSmallCar = isSmallCar;
	}

	public Boolean getIsMidCar() {
		return isMidCar;
	}

	public void setIsMidCar(Boolean isMidCar) {
		this.isMidCar = isMidCar;
	}

	public Boolean getIsLuxuryCar() {
		return isLuxuryCar;
	}

	public void setIsLuxuryCar(Boolean isLuxuryCar) {
		this.isLuxuryCar = isLuxuryCar;
	}

	public Boolean getIsSuvOrMuv() {
		return isSuvOrMuv;
	}

	public void setIsSuvOrMuv(Boolean isSuvOrMuv) {
		this.isSuvOrMuv = isSuvOrMuv;
	}

	public Boolean getIsElectricOrNonConventionalCar() {
		return isElectricOrNonConventionalCar;
	}

	public void setIsElectricOrNonConventionalCar(Boolean isElectricOrNonConventionalCar) {
		this.isElectricOrNonConventionalCar = isElectricOrNonConventionalCar;
	}

	public Boolean getIsTwoWheelerLoan() {
		return isTwoWheelerLoan;
	}

	public void setIsTwoWheelerLoan(Boolean isTwoWheelerLoan) {
		this.isTwoWheelerLoan = isTwoWheelerLoan;
	}

	public Boolean getIsElectricOrNonConventionalTwoWheeler() {
		return isElectricOrNonConventionalTwoWheeler;
	}

	public void setIsElectricOrNonConventionalTwoWheeler(Boolean isElectricOrNonConventionalTwoWheeler) {
		this.isElectricOrNonConventionalTwoWheeler = isElectricOrNonConventionalTwoWheeler;
	}

	public Integer getIsSecondHandFourWheelerLoan() {
		return isSecondHandFourWheelerLoan;
	}

	public void setIsSecondHandFourWheelerLoan(Integer isSecondHandFourWheelerLoan) {
		this.isSecondHandFourWheelerLoan = isSecondHandFourWheelerLoan;
	}

	public Integer getIsSecondHandTwoWheelerLoan() {
		return isSecondHandTwoWheelerLoan;
	}

	public void setIsSecondHandTwoWheelerLoan(Integer isSecondHandTwoWheelerLoan) {
		this.isSecondHandTwoWheelerLoan = isSecondHandTwoWheelerLoan;
	}
	
}