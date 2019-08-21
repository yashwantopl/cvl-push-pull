package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the fp_home_loan_details database table.
 * 
 */
@Entity
@Table(name = "auto_loan_model")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class AutoLoanModel extends RetailModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "is_personal_use")
    private Boolean isPersonalUse = false;
    
    @Column(name = "is_official_use")
    private Boolean isOfficialUse = false;
    
    @Column(name = "is_small_car")
    private Boolean isSmallCar = false;
   
    @Column(name = "is_mid_car")
    private Boolean isMidCar = false;
    
    @Column(name = "is_luxury_car")
    private Boolean isLuxuryCar = false;
    
    @Column(name = "is_suv_or_muv")
    private Boolean isSuvOrMuv = false;
    
    @Column(name = "is_electric_or_non_conventional_car")
    private Boolean isElectricOrNonConventionalCar = false;
    
    @Column(name = "is_two_wheeler_loan")
    private Boolean isTwoWheelerLoan = false;
    
    @Column(name = "is_electric_or_non_conventional_two_wheeler")
    private Boolean isElectricOrNonConventionalTwoWheeler = false;
    
    @Column(name = "is_second_hand_four_wheeler_loan")
    private Integer isSecondHandFourWheelerLoan;
    
    @Column(name = "is_second_hand_two_wheeler_loan")
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