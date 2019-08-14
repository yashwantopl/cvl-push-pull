package com.capitaworld.service.loans.model.micro_finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MfiIncomeAndExpenditureReq {

	private Long id;
	private Long applicationId;
	//1. Family Income
	private List<MfiIncomeDetailsReq> incomeDetailsReqList;

	//2. Family Monthly Expenses
	private Double educationExpense;
	private Double medicalExpense;
	private Double foodExpense;
	private Double otherExpense;
	private Double houseHoldExpense;
	private Double clothesExpense;

	//3. Expected Increase in Income
	private Integer businessInBrief;
	private Double monthlyCashflow;
	private Double monthlyExpenditure;
	private Double monthlyIncome;

	private Integer type;
	//4. Progress out of Poverty Index (PPI)
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

	//for flag filled
	private Boolean isIncomeDetailsFilled;
	private Boolean isFamilyIncomeFilled;
	private Boolean isFamilyExpenseFilled;
	private Boolean isExpectedIncomeFilled;
	private Boolean isPPIFilled;
	
	
	public MfiIncomeAndExpenditureReq() {
	}

	public MfiIncomeAndExpenditureReq(Long applicationId, Double educationExpense, Double medicalExpense, Double foodExpense,
			Double otherExpense, Integer ppiNoFamilyMember, Integer ppiAcadamicHeadFamily,
			Integer ppiRafrigeratorInFamily, Integer ppiStoveInFamily, Integer ppiPressureCookerInFamily,
			Integer ppiTvInFamily, Integer ppiFanInFamily, Integer ppiVehicleInFamily, Integer ppiDressingTableInFamily,
			Integer ppiOtherTableInFamily,Double houseHoldExpense, Double clothesExpense,Integer type) {
		this.applicationId = applicationId;
		this.educationExpense = educationExpense;
		this.medicalExpense = medicalExpense;
		this.foodExpense = foodExpense;
		this.otherExpense = otherExpense;
//		this.businessInBrief = businessInBrief;
//		this.monthlyCashflow = monthlyCashflow;
//		this.monthlyExpenditure = monthlyExpenditure;
//		this.monthlyIncome = monthlyIncome;
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
		this.clothesExpense = clothesExpense;
		this.houseHoldExpense = houseHoldExpense;
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Boolean getIsFamilyIncomeFilled() {
		return isFamilyIncomeFilled;
	}

	public void setIsFamilyIncomeFilled(Boolean isFamilyIncomeFilled) {
		this.isFamilyIncomeFilled = isFamilyIncomeFilled;
	}

	public Boolean getIsFamilyExpenseFilled() {
		return isFamilyExpenseFilled;
	}

	public void setIsFamilyExpenseFilled(Boolean isFamilyExpenseFilled) {
		this.isFamilyExpenseFilled = isFamilyExpenseFilled;
	}

	public Boolean getIsExpectedIncomeFilled() {
		return isExpectedIncomeFilled;
	}

	public void setIsExpectedIncomeFilled(Boolean isExpectedIncomeFilled) {
		this.isExpectedIncomeFilled = isExpectedIncomeFilled;
	}

	public Boolean getIsPPIFilled() {
		return isPPIFilled;
	}

	public void setIsPPIFilled(Boolean isPPIFilled) {
		this.isPPIFilled = isPPIFilled;
	}

	public Boolean getIsIncomeDetailsFilled() {
		return isIncomeDetailsFilled;
	}

	public void setIsIncomeDetailsFilled(Boolean isIncomeDetailsFilled) {
		this.isIncomeDetailsFilled = isIncomeDetailsFilled;
	}

	public Double getHouseHoldExpense() {
		return houseHoldExpense;
	}

	public void setHouseHoldExpense(Double houseHoldExpense) {
		this.houseHoldExpense = houseHoldExpense;
	}

	public Double getClothesExpense() {
		return clothesExpense;
	}

	public void setClothesExpense(Double clothesExpense) {
		this.clothesExpense = clothesExpense;
	}
}
