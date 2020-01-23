package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "fs_corporate_primary_details_mudra_loan")
@NamedQuery(name = "PrimaryCorporateDetailMudraLoan.findAll", query = "SELECT p FROM PrimaryCorporateDetailMudraLoan p")
public class PrimaryCorporateDetailMudraLoan implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "mrkt_arragement_finished_goods")
	private Integer mrktArragementFinishedGoods;
	
	@Column (name = "tl_type_of_machine")
	private String typeOfMachine;
	
	@Column (name = "tl_purpose_of_machine")
	private String purposeOfMachine;
	
	@Column (name = "tl_name_of_supplier")
	private String nameOfSupplier;
	
	@Column (name = "wc_avg_monthly_sale")
	private Double avgMonthlySale;
	
	@Column (name = "wc_raw_materials_stock")
	private Double rawMaterialsStock;
	
	@Column (name = "wc_wages_salaries")
	private Double wagesSalaries;
	
	@Column (name = "wc_sustenance_of_proprietor_partner")
	private Double sustenanceOfProprietorPartner;
	
	@Column (name = "wc_other_expenses")
	private Double otherExpenses;
	
	@Column (name = "wc_total_expenses")
	private Double totalExpenses;
	
	@Column (name = "wc_monthly_surplus")
	private Double monthlySurplus;
	
	@Column (name = "ba_existing")
	private String existing;
	
	@Column (name = "ba_proposed")
	private String proposed;
	
	@Column (name = "gov_auth_other")
	private String othergovauthorities;

	@Column (name = "register_under_shop_est_act")
	private String registerUnderShopEstAct;
	
	@Column (name = "register_under_msme")
	private String registerUnderMsme;
	
	@Column (name = "drug_license")
	private String drugLicense;
	
	@Column (name = "latest_gst_return_filled")
	private String latestGstReturnFilled;
	
	@Column (name = "latest_itr_filled")
	private String latestItrFilled;
	
	@Column (name = "other_statutory")
	private String otherStatutory;
	
	@Column (name = "application_id")
	private Long applicationId;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMrktArragementFinishedGoods() {
		return mrktArragementFinishedGoods;
	}

	public void setMrktArragementFinishedGoods(Integer mrktArragementFinishedGoods) {
		this.mrktArragementFinishedGoods = mrktArragementFinishedGoods;
	}

	public String getTypeOfMachine() {
		return typeOfMachine;
	}

	public void setTypeOfMachine(String typeOfMachine) {
		this.typeOfMachine = typeOfMachine;
	}

	public String getPurposeOfMachine() {
		return purposeOfMachine;
	}

	public void setPurposeOfMachine(String purposeOfMachine) {
		this.purposeOfMachine = purposeOfMachine;
	}

	public String getNameOfSupplier() {
		return nameOfSupplier;
	}

	public void setNameOfSupplier(String nameOfSupplier) {
		this.nameOfSupplier = nameOfSupplier;
	}


	public Double getAvgMonthlySale() {
		return avgMonthlySale;
	}

	public void setAvgMonthlySale(Double avgMonthlySale) {
		this.avgMonthlySale = avgMonthlySale;
	}

	public Double getRawMaterialsStock() {
		return rawMaterialsStock;
	}

	public void setRawMaterialsStock(Double rawMaterialsStock) {
		this.rawMaterialsStock = rawMaterialsStock;
	}

	public Double getWagesSalaries() {
		return wagesSalaries;
	}

	public void setWagesSalaries(Double wagesSalaries) {
		this.wagesSalaries = wagesSalaries;
	}

	public Double getSustenanceOfProprietorPartner() {
		return sustenanceOfProprietorPartner;
	}

	public void setSustenanceOfProprietorPartner(Double sustenanceOfProprietorPartner) {
		this.sustenanceOfProprietorPartner = sustenanceOfProprietorPartner;
	}

	public Double getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(Double otherExpenses) {
		this.otherExpenses = otherExpenses;
	}

	public Double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public Double getMonthlySurplus() {
		return monthlySurplus;
	}

	public void setMonthlySurplus(Double monthlySurplus) {
		this.monthlySurplus = monthlySurplus;
	}

	public String getExisting() {
		return existing;
	}

	public void setExisting(String existing) {
		this.existing = existing;
	}

	public String getProposed() {
		return proposed;
	}

	public void setProposed(String proposed) {
		this.proposed = proposed;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getOthergovauthorities() {
		return othergovauthorities;
	}

	public void setOthergovauthorities(String othergovauthorities) {
		this.othergovauthorities = othergovauthorities;
	}

	public String getRegisterUnderShopEstAct() {
		return registerUnderShopEstAct;
	}

	public void setRegisterUnderShopEstAct(String registerUnderShopEstAct) {
		this.registerUnderShopEstAct = registerUnderShopEstAct;
	}

	public String getRegisterUnderMsme() {
		return registerUnderMsme;
	}

	public void setRegisterUnderMsme(String registerUnderMsme) {
		this.registerUnderMsme = registerUnderMsme;
	}

	public String getDrugLicense() {
		return drugLicense;
	}

	public void setDrugLicense(String drugLicense) {
		this.drugLicense = drugLicense;
	}

	public String getLatestGstReturnFilled() {
		return latestGstReturnFilled;
	}

	public void setLatestGstReturnFilled(String latestGstReturnFilled) {
		this.latestGstReturnFilled = latestGstReturnFilled;
	}

	public String getLatestItrFilled() {
		return latestItrFilled;
	}

	public void setLatestItrFilled(String latestItrFilled) {
		this.latestItrFilled = latestItrFilled;
	}

	public String getOtherStatutory() {
		return otherStatutory;
	}

	public void setOtherStatutory(String otherStatutory) {
		this.otherStatutory = otherStatutory;
	}
	
}
