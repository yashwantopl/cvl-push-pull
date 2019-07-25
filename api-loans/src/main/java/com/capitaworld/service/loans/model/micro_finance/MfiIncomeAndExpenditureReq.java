package com.capitaworld.service.loans.model.micro_finance;

import java.util.List;

public class MfiIncomeAndExpenditureReq {

	private Long id;
	private Long applicationId;
	private List<MfiIncomeDetailsReq> incomeDetailsReqList;
	private Double shipShgiInstallment;
	private Double otherInstallment;
	private Double loanInstallment;
	private Double educationExpense;
	private Double medicalExpense;
	private Double foodExpense;
	private Double otherExpense;
	private Integer businessInBrief;
	private Double monthlyCashflow;
	private Double monthlyExpenditure;
	private Double monthlyIncome;

	private Integer ppiNoFamilyMember;
	private Integer ppiAcadamicHeadFamily;
	private Integer ppiRafrigeratorInFamily;
	private Integer ppiStoveInFamily;
	private Integer ppiPressureCookerInFamily;
	private Integer ppiTvInFamily;
	private Integer ppiFanInFamily;
	private Integer ppiVehicleInFamily;
	private Integer ppiDressingTableInFamily;
	private Integer ppiOtherTableInFamily;


	public MfiIncomeAndExpenditureReq() {
	}

	public MfiIncomeAndExpenditureReq(Long applicationId,
									  Double shipShgiInstallment, Double otherInstallment, Double loanInstallment, Double educationExpense,
									  Double medicalExpense, Double foodExpense, Double otherExpense, Integer businessInBrief,
									  Double monthlyCashflow, Double monthlyExpenditure, Double monthlyIncome, Integer ppiNoFamilyMember,
									  Integer ppiAcadamicHeadFamily, Integer ppiRafrigeratorInFamily, Integer ppiStoveInFamily,
									  Integer ppiPressureCookerInFamily, Integer ppiTvInFamily, Integer ppiFanInFamily,
									  Integer ppiVehicleInFamily, Integer ppiDressingTableInFamily, Integer ppiOtherTableInFamily) {
		super();
		this.applicationId = applicationId;
		this.shipShgiInstallment = shipShgiInstallment;
		this.otherInstallment = otherInstallment;
		this.loanInstallment = loanInstallment;
		this.educationExpense = educationExpense;
		this.medicalExpense = medicalExpense;
		this.foodExpense = foodExpense;
		this.otherExpense = otherExpense;
		this.businessInBrief = businessInBrief;
		this.monthlyCashflow = monthlyCashflow;
		this.monthlyExpenditure = monthlyExpenditure;
		this.monthlyIncome = monthlyIncome;
		this.ppiNoFamilyMember = ppiNoFamilyMember;
		this.ppiAcadamicHeadFamily = ppiAcadamicHeadFamily;
		this.ppiRafrigeratorInFamily = ppiRafrigeratorInFamily;
		this.ppiStoveInFamily = ppiStoveInFamily;
		this.ppiPressureCookerInFamily = ppiPressureCookerInFamily;
		this.ppiTvInFamily = ppiTvInFamily;
		this.ppiFanInFamily = ppiFanInFamily;
		this.ppiVehicleInFamily = ppiVehicleInFamily;
		this.ppiDressingTableInFamily = ppiDressingTableInFamily;
		this.ppiOtherTableInFamily = ppiOtherTableInFamily;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public List<MfiIncomeDetailsReq> getIncomeDetailsReqList() {
		return incomeDetailsReqList;
	}

	public void setIncomeDetailsReqList(List<MfiIncomeDetailsReq> incomeDetailsReqList) {
		this.incomeDetailsReqList = incomeDetailsReqList;
	}

	public Double getShipShgiInstallment() {
		return shipShgiInstallment;
	}

	public void setShipShgiInstallment(Double shipShgiInstallment) {
		this.shipShgiInstallment = shipShgiInstallment;
	}

	public Double getOtherInstallment() {
		return otherInstallment;
	}

	public void setOtherInstallment(Double otherInstallment) {
		this.otherInstallment = otherInstallment;
	}

	public Double getLoanInstallment() {
		return loanInstallment;
	}

	public void setLoanInstallment(Double loanInstallment) {
		this.loanInstallment = loanInstallment;
	}

	public Double getEducationExpense() {
		return educationExpense;
	}

	public void setEducationExpense(Double educationExpense) {
		this.educationExpense = educationExpense;
	}

	public Double getMedicalExpense() {
		return medicalExpense;
	}

	public void setMedicalExpense(Double medicalExpense) {
		this.medicalExpense = medicalExpense;
	}

	public Double getFoodExpense() {
		return foodExpense;
	}

	public void setFoodExpense(Double foodExpense) {
		this.foodExpense = foodExpense;
	}

	public Double getOtherExpense() {
		return otherExpense;
	}

	public void setOtherExpense(Double otherExpense) {
		this.otherExpense = otherExpense;
	}

	public Integer getBusinessInBrief() {
		return businessInBrief;
	}

	public void setBusinessInBrief(Integer businessInBrief) {
		this.businessInBrief = businessInBrief;
	}

	public Double getMonthlyCashflow() {
		return monthlyCashflow;
	}

	public void setMonthlyCashflow(Double monthlyCashflow) {
		this.monthlyCashflow = monthlyCashflow;
	}

	public Double getMonthlyExpenditure() {
		return monthlyExpenditure;
	}

	public void setMonthlyExpenditure(Double monthlyExpenditure) {
		this.monthlyExpenditure = monthlyExpenditure;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Integer getPpiNoFamilyMember() {
		return ppiNoFamilyMember;
	}

	public void setPpiNoFamilyMember(Integer ppiNoFamilyMember) {
		this.ppiNoFamilyMember = ppiNoFamilyMember;
	}

	public Integer getPpiAcadamicHeadFamily() {
		return ppiAcadamicHeadFamily;
	}

	public void setPpiAcadamicHeadFamily(Integer ppiAcadamicHeadFamily) {
		this.ppiAcadamicHeadFamily = ppiAcadamicHeadFamily;
	}

	public Integer getPpiRafrigeratorInFamily() {
		return ppiRafrigeratorInFamily;
	}

	public void setPpiRafrigeratorInFamily(Integer ppiRafrigeratorInFamily) {
		this.ppiRafrigeratorInFamily = ppiRafrigeratorInFamily;
	}

	public Integer getPpiStoveInFamily() {
		return ppiStoveInFamily;
	}

	public void setPpiStoveInFamily(Integer ppiStoveInFamily) {
		this.ppiStoveInFamily = ppiStoveInFamily;
	}

	public Integer getPpiPressureCookerInFamily() {
		return ppiPressureCookerInFamily;
	}

	public void setPpiPressureCookerInFamily(Integer ppiPressureCookerInFamily) {
		this.ppiPressureCookerInFamily = ppiPressureCookerInFamily;
	}

	public Integer getPpiTvInFamily() {
		return ppiTvInFamily;
	}

	public void setPpiTvInFamily(Integer ppiTvInFamily) {
		this.ppiTvInFamily = ppiTvInFamily;
	}

	public Integer getPpiFanInFamily() {
		return ppiFanInFamily;
	}

	public void setPpiFanInFamily(Integer ppiFanInFamily) {
		this.ppiFanInFamily = ppiFanInFamily;
	}

	public Integer getPpiVehicleInFamily() {
		return ppiVehicleInFamily;
	}

	public void setPpiVehicleInFamily(Integer ppiVehicleInFamily) {
		this.ppiVehicleInFamily = ppiVehicleInFamily;
	}

	public Integer getPpiDressingTableInFamily() {
		return ppiDressingTableInFamily;
	}

	public void setPpiDressingTableInFamily(Integer ppiDressingTableInFamily) {
		this.ppiDressingTableInFamily = ppiDressingTableInFamily;
	}

	public Integer getPpiOtherTableInFamily() {
		return ppiOtherTableInFamily;
	}

	public void setPpiOtherTableInFamily(Integer ppiOtherTableInFamily) {
		this.ppiOtherTableInFamily = ppiOtherTableInFamily;
	}
}
