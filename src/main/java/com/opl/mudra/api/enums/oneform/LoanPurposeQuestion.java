package com.opl.mudra.api.enums.oneform;

/**
 * @author Harshit
 *
 */
public enum LoanPurposeQuestion {
	PURCHASE_OF_READY_BUILT_HOUSE_FLAT_FROM_THE_EXISTING_OWNERS(1, "Purchase of ready built house/flat from the existing owners. (Years from the existing owners)", "Purchase of ready built house/flat from the existing owners. (Years from the existing owners)",1), 
//	PURCHASE_OF_READY_BUILT_INDEPENDENT_HOUSE(2, "Purchase of ready built independent house or flat from the existing owners and to carry out repairs, renovation and improvement in the same after immediate purchase/possession. (Years from the existing owners)", "Purchase of ready built independent house or flat from the existing owners and to carry out repairs, renovation and improvement in the same after immediate purchase/possession. (Years from the existing owners)",1),
	PURCHASE_OF_READY_BUILT_INDEPENDENT_HOUSE(2, "Purchase of ready built independent house or flat from the existing owners and to carry out repairs, renovation and improvement in the same after immediate purchase/possession. (Years from the existing owners)", "Purchase of ready built independent house or flat from the existing owners and to carry out repairs, renovation and improvement in the same after immediate purchase/possession. (Years from the existing owners)",3),
	PURCHASE_OF_RESIDENTIAL_FLAT_UNDER_CONSTRUCTION_DIRECTLY(3, "Purchase of residential flat under construction directly from the builders/developers.", "Purchase of residential flat under construction directly from the builders/developers.",1),
	PURCHASE_OF_RESIDENTIAL_FLAT_UNDER_CONSTRUCTION(4, "Purchase of residential flat under construction from the allottee where sale deed is yet to be executed.", "Purchase of residential flat under construction from the allottee where sale deed is yet to be executed.",1),
//	PURCHASE_OF_RESIDENTIAL_SITE_OR_PLOT_OF_LAND_TOGETHER(5, "Purchase of residential site or plot of land together with construction of house. (Years)", "Purchase of residential site or plot of land together with construction of house. (Years)",1),
	CONSTRUCTION_OF_RESIDENTIAL_BUILDING_IN_THE_PLOT_OF_LAND(6, "Construction of residential building in the plot of land already owned by the applicant.", "Construction of residential building in the plot of land already owned by the applicant.",2),
	EXPANSION_OF_EXISTING_PRE_OWNED_RESIDENTIAL_BUILDING(7, "Expansion of existing pre-owned residential building. (Years)", "Expansion of existing pre-owned residential building. (Years)",2),
	PURCHASE_OF_RESIDENTIAL_SITE_OR_PLOT_OF_LAND_TOGETHER_WITH_CONSTRUCTION_OF_HOUSE(8, "Purchase of residential site or plot of land together with construction of house. (Years)", "Purchase of residential site or plot of land together with construction of house. (Years)",2),
	REPAIRS_OF_EXISTING_PRE_OWNED_HOUSE_OR_FLAT(9, "Repairs, renovation, improvement of existing pre-owned house or flat. (Years)", "Repairs, renovation, improvement of existing pre-owned house or flat. (Years)",3),
	REFUND_OF_EXCESS_MARGIN_AMOUNT_PAID_FOR_PURCHASING_THE_HOUSE_THROUGH_NORMAL_BANKING_CHANNEL(10, "Refund of excess margin amount paid for purchasing the house through normal banking channel.", "Refund of excess margin amount paid for purchasing the house through normal banking channel.",4),
	LOAN_FOR_REIMBURSEMENT_OF_PURCHASE_PRICE_OF_RECENTLY_PURCHASED_HOUSE_FLAT(11, "Loan for reimbursement of purchase price of recently purchased house / flat. (Years)", "Loan for reimbursement of purchase price of recently purchased house / flat. (Years)",4);

	private final Integer id;
	private final String value;
	private final String description;
	private final Integer purposeTypeId;

	LoanPurposeQuestion(Integer id, String value, String description,Integer purposeTypeId) {
		this.id = id;
		this.value = value;
		this.description = description;
		this.purposeTypeId = purposeTypeId;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	public Integer getPurposeTypeId() {
		return purposeTypeId;
	}
	
	public static LoanPurposeQuestion fromId(Integer v) {
		for (LoanPurposeQuestion c : LoanPurposeQuestion.values()) {
			if (c.id.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v != null ? v.toString() : null);
	}
	
	public static LoanPurposeQuestion fromValue(String v) {
		for (LoanPurposeQuestion c : LoanPurposeQuestion.values()) {
			if (c.value.equalsIgnoreCase(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
	
	public static LoanPurposeQuestion[] getAll(){
		return LoanPurposeQuestion.values();
		
	}

}
