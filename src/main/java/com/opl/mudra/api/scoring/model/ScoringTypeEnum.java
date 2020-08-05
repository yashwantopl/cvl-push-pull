package com.opl.mudra.api.scoring.model;

public enum ScoringTypeEnum {

	RISK(1,"Risk"),
	CIBIL(2,"Cibil"),
	GROSS_INCOME(3,"Gross Income"),
	NET_INCOME(4,"Net Income"),
	CO_APPLICANT(5,"Co-Applicant"),
	LTV(6,"LTV (Loan to Value)"),
	LOAN_AMOUNT(7,"Loan Amount"),
	TENURE(8,"Tenure"),
	PROPERTY_VALUE(9,"Property Value"),
	CREDIT_HISTORY_GREATER_THEN_SIX_MONTHS(10,"Credit History Greater Then 6 Months"),
	CREDIT_HISTORY_LESS_THEN_SIX_MONTHS(11,"Credit History Less Then 6 Months"),
	NO_CREDIT_HISTORY(12,"No Credit history"),
	EX_SHOWROOM_PRICE(13,"Ex-showroom Price of Vehicle"),
	ON_ROAD_PRICE(14,"On-Road Price of Vehicle"),
	AGE_VEHICLE(15,"Age of Vehicle (Old or New)"),
	AGREED_PURCHASE_PIRCE_VEHICLE_VALUE_IDV(16,"Agreed Purchase Price / Valuation of the Vehicle / Insured Declared Value (IDV)"),
	MCLR(17,"Connected to MCLR"),
	VEHICLE_TYPE(18,"Type of Vehicle"),
	ROI(19,"Rate Of Interese"),
	CIBIL_AND_TENURE(20,"Cibil and Tenure");

	private Integer id;
	private String value;
	
	ScoringTypeEnum(Integer id, String value){
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	
}
