package com.opl.mudra.api.scoring.v4.enums;

public enum RoiCalculationMethodEnum {

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
	CIBIL_AND_TENURE(20,"Cibil and Tenure"),
	CIBIL_AND_BANK_ACCOUNT(21,"Cibil and Bank Account"),
	CMR_SCORE(22,"CMR Score"),
	CMR_AND_ASSET_COVERAGE(23,"CMR Score And Asset Coverage"),
	CREDIT_HISTORY_GREATER_THEN_SIX_MONTHS_CONCESS_PF(24,"Credit History Greater Then 6 Months Concess Pf"),
	CREDIT_HISTORY_LESS_THEN_SIX_MONTHS_CONCESS_PF(25,"Credit History Less Then 6 Months Concess Pf"),
	NO_CREDIT_HISTORY_CONCESS_PF(26,"No Credit history Concess Pf"),
	CIBIL_AND_LOAN_AMOUNT(27,"Cibil and Loan Amount"),
	ON_PERCENTAGE(28,"ON PERCENTAGE"),
	ON_AMOUNT(29,"ON AMOUNT"),
	BOTH_ON_AMOUNT_AND_PERCENTAGE(30,"Both - ON AMOUNT AND PERCENTAGE"),
	BORROWER_STATUS_AND_COLLETRAL_COVERAGE(32,"Borrower Status And Colletral Coverage");

	private Integer id;
	private String value;
	
	RoiCalculationMethodEnum(Integer id, String value){
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
