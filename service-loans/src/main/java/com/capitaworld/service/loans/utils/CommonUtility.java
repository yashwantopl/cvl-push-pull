package com.capitaworld.service.loans.utils;

import com.capitaworld.cibil.api.utility.CibilUtils;

public class CommonUtility {

	public interface ApiType{
		public static final Integer SANCTION= 1; 
		public static final Integer DISBURSEMENT= 2;
		public static final Integer DDR_API= 3; 
		public static final Integer DETAILED_API= 4;
		public static final Integer GENERATING_TOKEN= 5;
		public static final Integer TOKEN_AS_EXPIRED= 6;
		public static final Integer SANCTION_AND_DISBURSEMENT= 7;
	}
	
	public static Boolean getCashCredit(CibilUtils.CreditTypeEnum name) {
		switch (name) {
			case CASH_CREDIT: return true;   
			case OVERDRAFT:return true;
			default: return false;
		}
	}
	public static Boolean getTermLoan(CibilUtils.CreditTypeEnum  name) {
		switch (name) {
			case DEMAND_LOAN : return true;   
			case MEDIUM_TERM_LOAN_PERIOD_ABOVE_1_YEAR_AND_UP_TO_3_YEARS:return true;
			case LONG_TERM_LOAN_PERIOD_ABOVE_3_YEARS:return true;
			case COMMERCIAL_VEHICLE_LOAN:return true;
			case LEASE_FINANCE:return true;
			case EQUIPMENT_FINANCING_CONSTRUCTION_OFFICE_MEDICAL:return true;
			case UNSECURED_BUSINESS_LOAN:return true;
			case GOLD_LOAN:return true;
			case PROPERTY_LOAN:return true;
			case AUTO_LOAN:return true;
			case SHORT_TERM_LOAN_LESS_THAN_1_YEAR:return true;
			case AGGREGATE_OF_ALL_BORROWINGS_DUE_TO_FILING_OF_SUIT:return true;
			case AGGREGATION_OF_ALL_FUND_BASED_FACILITIES:return true;
			case AGGREGATION_OF_ALL_NON_FUND_BASED_FACILITIES:return true;
			case FACILITIES_INTERCHANGE_BETWEEN_FUND_NON_FUND_BASED:return true;
			case LOAN_AGAINST_SHARES_SECURITIES:return true;
			case LOAN_EXTENDED_THROUGH_CREDIT_CARDS:return true;
			case CORPORATE_CREDIT_CARD:return true;
			case HIRE_PURCHASE:return true;
			case OTHERS:return true;
			default : 
			return false;
		}
	}
	
	public static  Boolean getLcBg(CibilUtils.CreditTypeEnum name) {
		switch (name) {
			case LETTERS_OF_CREDIT: return true;
			case BANK_GUARANTEE:return true;
			case EXPORT_BILLS_PURCHASED:return true;
			case EXPORT_BILLS_DISCOUNTED:return true;
			case EXPORT_BILLS_ADVANCED_AGAINST:return true;
			case INLAND_BILLS_PURCHASED:return true;
			case INLAND_BILLS_DISCOUNTED:return true;
			case ADVANCES_AGAINST_IMPORT_BILLS:return true;
			case DEFERRED_PAYMENT_GUARANTEE:return true;
			case ADVANCES_AGAINST_EXPORT_CASH_INCENTIVES_AND_DUTY_DRAW_BACK_CLAIMS:return true;
			case DERIVATIVES:return true;
			case ANY_COMPLEX_DERIVATIVE_LOAN_INVOLVING_FOREIGN_CURRENCY_WITH_OPTION:return true;
			case COMPLEX_INT_RATE_DERV_WITH_OPTIONALITIES:return true;
			case FOREIGN_CURRENCY_CHEQUES_TCS_DDS_TTS_MTS_PURCHASED:return true;
			case PLAIN_VANILLA_FOREX_FORWARD_CONTRACTS:return true;
			case PLAIN_VANILA_INT_RATE_SWAP_ALL_INCLUDING_INR_AS_COUPON:return true;
			case PLAIN_VANILA_FOREIGN_CURRENCY_OPTION_INCLUDING_INR_CROSS_CURRENCY:return true;
			case PACKING_CREDIT_ALL_EXPORT_PRE_SHIPMENT_FINANCE:return true;
			case CONTRACTS_ON_PAST_PERFORMANCE_EXPORTS:return true;
			case CONTRACTS_ON_PAST_PERFORMANCE_IMPORTS:return true;
			default : return false;
		}
	}
}
