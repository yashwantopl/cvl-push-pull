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
@Table(name = "home_loan_model_temp")
@PrimaryKeyJoinColumn(referencedColumnName = "retail_model_id")
public class HomeLoanModelTemp extends RetailModelTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "is_pur_ready_built_house")
	private Boolean isPurReadyBuiltHouse = false;
	
	@Column(name = "is_pur_ready_built_independent_house")
	private Boolean isPurReadyBuiltIndependentHouse = false;
	
	@Column(name = "is_pur_residetial_flat")
	private Boolean isPurResidetialFlat	 = false;
	
	@Column(name = "is_pur_residetial_flat_allotee")
	private Boolean isPurResidetialFlatAllotee = false;
	
	@Column(name = "is_pur_residetial_site")
	private Boolean isPurResidetialSite = false;
	
	@Column(name = "is_constru_residetial_buid")
	private Boolean isConstruResidetialBuid = false;
	
	@Column(name = "is_constru_expa_res_build")
	private Boolean isConstruExpaResBuild = false;
	
	@Column(name = "is_constru_pur_res_site")
	private Boolean isConstruPurResSite = false;
	
	@Column(name = "is_rep_pur_ready_built_independant")
	private Boolean isRepPurReadyBuiltIndependant = false;
	
	@Column(name = "is_rep_ren_imp_flat_house")
	private Boolean isRepRenImpFlatHouse = false;
	
	@Column(name = "is_oth_ref_excess_margin_paid")
	private Boolean isOthRefExcessMarginPaid = false;
	
	@Column(name = "is_oth_loan_reimbursement_flat")
	private Boolean isOthLoanReimbursementFlat = false;

	public Boolean getIsPurReadyBuiltHouse() {
		return isPurReadyBuiltHouse;
	}

	public void setIsPurReadyBuiltHouse(Boolean isPurReadyBuiltHouse) {
		this.isPurReadyBuiltHouse = isPurReadyBuiltHouse;
	}

	public Boolean getIsPurReadyBuiltIndependentHouse() {
		return isPurReadyBuiltIndependentHouse;
	}

	public void setIsPurReadyBuiltIndependentHouse(Boolean isPurReadyBuiltIndependentHouse) {
		this.isPurReadyBuiltIndependentHouse = isPurReadyBuiltIndependentHouse;
	}

	public Boolean getIsPurResidetialFlat() {
		return isPurResidetialFlat;
	}

	public void setIsPurResidetialFlat(Boolean isPurResidetialFlat) {
		this.isPurResidetialFlat = isPurResidetialFlat;
	}

	public Boolean getIsPurResidetialFlatAllotee() {
		return isPurResidetialFlatAllotee;
	}

	public void setIsPurResidetialFlatAllotee(Boolean isPurResidetialFlatAllotee) {
		this.isPurResidetialFlatAllotee = isPurResidetialFlatAllotee;
	}

	public Boolean getIsPurResidetialSite() {
		return isPurResidetialSite;
	}

	public void setIsPurResidetialSite(Boolean isPurResidetialSite) {
		this.isPurResidetialSite = isPurResidetialSite;
	}

	public Boolean getIsConstruResidetialBuid() {
		return isConstruResidetialBuid;
	}

	public void setIsConstruResidetialBuid(Boolean isConstruResidetialBuid) {
		this.isConstruResidetialBuid = isConstruResidetialBuid;
	}

	public Boolean getIsConstruExpaResBuild() {
		return isConstruExpaResBuild;
	}

	public void setIsConstruExpaResBuild(Boolean isConstruExpaResBuild) {
		this.isConstruExpaResBuild = isConstruExpaResBuild;
	}

	public Boolean getIsConstruPurResSite() {
		return isConstruPurResSite;
	}

	public void setIsConstruPurResSite(Boolean isConstruPurResSite) {
		this.isConstruPurResSite = isConstruPurResSite;
	}

	public Boolean getIsRepPurReadyBuiltIndependant() {
		return isRepPurReadyBuiltIndependant;
	}

	public void setIsRepPurReadyBuiltIndependant(Boolean isRepPurReadyBuiltIndependant) {
		this.isRepPurReadyBuiltIndependant = isRepPurReadyBuiltIndependant;
	}

	public Boolean getIsRepRenImpFlatHouse() {
		return isRepRenImpFlatHouse;
	}

	public void setIsRepRenImpFlatHouse(Boolean isRepRenImpFlatHouse) {
		this.isRepRenImpFlatHouse = isRepRenImpFlatHouse;
	}

	public Boolean getIsOthRefExcessMarginPaid() {
		return isOthRefExcessMarginPaid;
	}

	public void setIsOthRefExcessMarginPaid(Boolean isOthRefExcessMarginPaid) {
		this.isOthRefExcessMarginPaid = isOthRefExcessMarginPaid;
	}

	public Boolean getIsOthLoanReimbursementFlat() {
		return isOthLoanReimbursementFlat;
	}

	public void setIsOthLoanReimbursementFlat(Boolean isOthLoanReimbursementFlat) {
		this.isOthLoanReimbursementFlat = isOthLoanReimbursementFlat;
	}

}