package com.opl.mudra.api.utils.scoring;

import java.util.Arrays;
import java.util.List;

public class ScoreParameter {
    public static final String COMBINED_NETWORTH = "COMBINED_NETWORTH";
    public static final String CUSTOMER_ASSOCIATE_CONCERN = "CUSTOMER_ASSOCIATE_CONCERN";
    public static final String CIBIL_TRANSUNION_SCORE = "CIBIL_TRANSUNION_SCORE";
    public static final String DEBT_EQUITY_RATIO = "DEBT_EQUITY_RATIO";
    public static final String TOL_TNW = "TOL_TNW";
    public static final String AVERAGE_CURRENT_RATIO = "AVERAGE_CURRENT_RATIO";
    public static final String WORKING_CAPITAL_CYCLE = "WORKING_CAPITAL_CYCLE";
    public static final String AVERAGE_ANNUAL_GROWTH_GROSS_CASH = "AVERAGE_ANNUAL_GROWTH_GROSS_CASH";
    public static final String AVERAGE_ANNUAL_GROWTH_NET_SALE = "AVERAGE_ANNUAL_GROWTH_NET_SALE";
    public static final String AVERAGE_EBIDTA = "AVERAGE_EBIDTA";
    public static final String AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS = "AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS";
    public static final String AVERAGE_INTEREST_COV_RATIO = "AVERAGE_INTEREST_COV_RATIO";
    public static final String NO_OF_CUSTOMER = "NO_OF_CUSTOMER";
    public static final String CONCENTRATION_CUSTOMER = "CONCENTRATION_CUSTOMER";
    public static final String EXPERIENCE_IN_THE_BUSINESS = "EXPERIENCE_IN_THE_BUSINESS";
    public static final String CREDIT_SUMMATION = "CREDIT_SUMMATION";

    public static final String AGE = "AGE";
    public static final String N0_OF_DEPENDENT = "N0_OF_DEPENDENT";
    public static final String SALES_REVENUE_CAGR = "SALES_REVENUE_CAGR";
    public static final String FLEET_STRENGTH_OWNED_BY_FLEET_OPERATOR = "FLEET_STRENGTH_OWNED_BY_FLEET_OPERATOR";
    public static final String REPAYMENT_ON_TIME_IN_LAST_12_MONTHS = "REPAYMENT_ON_TIME_IN_LAST_12_MONTHS";
    public static final String LOAN_FREE_VEHICLE = "LOAN_FREE_VEHICLE";
    public static final String PROFITABILITY_HISTORY = "PROFITABILITY_HISTORY";
    public static final String ASSURED_ORDER = "ASSURED_ORDER";
    public static final String DEPOSIT_POSITION_POTENTIAL = "DEPOSIT_POSITION_POTENTIAL";
    public static final String OPERATION_SUPPORTED_BY_FAMILY_MEMBERS_DIRECTLY = "OPERATION_SUPPORTED_BY_FAMILY_MEMBERS_DIRECTLY";
    
    
    
    public static final String NO_OF_CHILDREN = "NO_OF_CHILDREN";
    public static final String OWNING_HOUSE = "OWNING_HOUSE";
    public static final String ACADEMIC_QUALIFICATION = "ACADEMIC_QUALIFICATION";
    public static final String EXPERIENCE_IN_THE_LINE_OF_TRADE   = "EXPERIENCE_IN_THE_LINE_OF_TRADE";
    public static final String SPOUSE_DETAILS = "SPOUSE_DETAILS";
    public static final String ASSESSED_FOR_INCOME_TAX = "ASSESSED_FOR_INCOME_TAX";
    public static final String HAVE_LIFE_INSURANCE_POLICY = "HAVE_LIFE_INSURANCE_POLICY";


    public static final String REPAYMENT_PERIOD = "REPAYMENT_PERIOD";
    public static final String CONTINUOUS_NET_PROFIT = "CONTINUOUS_NET_PROFIT";

    public static final String QUALITY_OF_RECEIVABLES = "QUALITY_OF_RECEIVABLES";
    public static final String QUALITY_OF_FINISHED_GOODS_INVENTORY = "QUALITY_OF_FINISHED_GOODS_INVENTORY";
    public static final String KNOW_HOW = "KNOW_HOW";
    public static final String LINE_OF_ACTIVITY = "LINE_OF_ACTIVITY";
    public static final String COMPETITION = "COMPETITION";
    public static final String FACTORY_PREMISES = "FACTORY_PREMISES";
    public static final String SALES_SHOW_A_RISING_TREND = "SALES_SHOW_A_RISING_TREND";
    public static final String YEARS_IN_BUSINESS = "YEARS_IN_BUSINESS";

    public static final String UTILISATION_PERCENTAGE = "UTILISATION_PERCENTAGE";
    public static final String TURN_OVER_TO_LIMIT_RATIO = "TURN_OVER_TO_LIMIT_RATIO";
    public static final String COLLATERAL_COVERAGE = "COLLATERAL_COVERAGE";

    public static final String DEBT_SERVICE_COVERAGE_RATIO = "DEBT_SERVICE_COVERAGE_RATIO";
    public static final String PAST_YEAR_TURNOVER = "PAST_YEAR_TURNOVER";
    public static final String DEBT_EBITDA = "DEBT_EBITDA";
    public static final String TURNOVER_ATNW  = "TURNOVER_ATNW";
    public static final String NO_OF_CHEQUES_BOUNCED = "NO_OF_CHEQUES_BOUNCED";
    public static final String NO_OF_CHEQUES_BOUNCED_LAST_SIX_MONTH = "NO_OF_CHEQUES_BOUNCED_LAST_SIX_MONTH";
    public static final String PAT_NET_SALES_RATIO= "PAT_NET_SALES_RATIO";
    public static final String STATUTORY_COMPLIANCE = "STATUTORY_COMPLIANCE";
    public static final String PAYMENT_RECORDS_WITH_LENDERS = "PAYMENT_RECORDS_WITH_LENDERS";
    
    // NEW PARAMETER MSME
    public static final String CMR_SCORE_MSME_RANKING = "CMR_SCORE_MSME_RANKING";
    public static final String ISO_CERTIFICATION = "ISO_CERTIFICATION";
    public static final String TOTAL_NO_OF_INWARD_CHEQUE_BOUNCES_LAST_SIX_MONTHS = "TOTAL_NO_OF_INWARD_CHEQUE_BOUNCES_LAST_SIX_MONTHS";
    public static final String MIN_BANKING_RELATIONSHIP_MSME = "MIN_BANKING_RELATIONSHIP_MSME";
    public static final String COLLATERAL_SECURITY_MSME = "COLLATERAL_SECURITY_MSME";
    
    

    public static final List<String> SALARIED_IGNORELIST = Arrays.asList(new String [] {ScoreParameter.Retail.HomeLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED});
    public static final List<String> SELFEMPLOYED_IGNORELIST = Arrays.asList(new String [] {ScoreParameter.Retail.HomeLoan.CURRENT_JOB_EXP,ScoreParameter.Retail.HomeLoan.EMPLOYMENT_CATEG_JOB});

    public final class NTB
    {
        private NTB(){
            // nothing to do.
        }
        public static final String WORKING_EXPERIENCE = "WORKING_EXPERIENCE";
        public static final String IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS = "IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS";
        public static final String CIBIL_TRANSUNION_SCORE = "CIBIL_TRANSUNION_SCORE";
        public static final String AGE_OF_PROMOTOR = "AGE_OF_PROMOTOR";
        public static final String EDUCATION_QUALIFICATION = "EDUCATION_QUALIFICATION";
        public static final String EMPLOYMENT_TYPE = "EMPLOYMENT_TYPE";
        public static final String HOUSE_OWNERSHIP = "HOUSE_OWNERSHIP";
        public static final String MARITIAL_STATUS = "MARITIAL_STATUS";
        public static final String CNW = "CNW";
        public static final String ITR_SALARY_INCOME = "ITR_SALARY_INCOME";
        public static final String CONSTITUTION_OF_BORROWER = "CONSTITUTION_OF_BORROWER";
        public static final String FIXED_OBLIGATION_RATIO = "FIXED_OBLIGATION_RATIO";
        public static final String CHEQUE_BOUNCES = "CHEQUE_BOUNCES";
        public static final String DPD = "DPD";
        public static final String ASSET_COVERAGE_RATIO = "ASSET_COVERAGE_RATIO";
        public static final String UNIT_FACTORY_PREMISES = "UNIT_FACTORY_PREMISES";
        public static final String BALANCE_GESTATION_PERIOD = "BALANCE_GESTATION_PERIOD";
        public static final String ENVIRONMENT_CATEGORY = "ENVIRONMENT_CATEGORY";
    }

    public final class Retail
    {
        private Retail()
        { // nothing to do.
            }
        public static final String WORKING_EXPERIENCE_PL = "WORKING_EXPERIENCE_PL";
        public static final String CIBIL_SCORE_PL = "CIBIL_SCORE_PL";
        public static final String AGE_PL = "AGE_PL";
        public static final String EDUCATION_QUALI_PL = "EDUCATION_QUALI_PL";
        public static final String EMPLOYEMENT_TYPE_PL = "EMPLOYEMENT_TYPE_PL";
        public static final String HOUSE_OWNERSHIP_PL = "HOUSE_OWNERSHIP_PL";
        public static final String MARITAL_STATUS_PL = "MARITAL_STATUS_PL";
        public static final String CATEGORY_INFO_PL = "CATEGORY_INFO_PL";
        public static final String FIXED_OBLI_INFO_RATIO_PL = "FIXED_OBLI_INFO_RATIO_PL";
        public static final String CHEQUE_BOUNCE_PAST_SIX_MONTH_PL = "CHEQUE_BOUNCE_PAST_SIX_MONTH_PL";
        public static final String DAY_PAST_DUE_PL = "DAY_PAST_DUE_PL";
        public static final String NET_ANNUAL_INCOME_PL = "NET_ANNUAL_INCOME_PL";
        public static final String EMI_NMI_PL = "EMI_NMI_PL";
        public static final String NO_OF_YEAR_CURRENT_LOCATION_PL = "NO_OF_YEAR_CURRENT_LOCATION_PL";
        public static final String SPOUSE_EMPLOYMENT_DETAILS_PL = "SPOUSE_EMPLOYMENT_DETAILS_PL";
        public static final String NUMBER_OF_DEPENDENTS_PL = "NUMBER_OF_DEPENDENTS_PL";
        public static final String DESIGNATION_PL= "DESIGNATION_PL";
        public static final String LOAN_TO_INCOME_RATIO_PL= "LOAN_TO_INCOME_RATIO_PL";
        public static final String NET_WROTH_TO_LOAN_AMOUNT_PL= "NET_WROTH_TO_LOAN_AMOUNT_PL";
        public static final String AVG_EOD_BAL_TO_TOTAL_DEPOSITE_PL= "AVG_EOD_BAL_TO_TOTAL_DEPOSITE_PL";
        public static final String TENURE_OF_THE_LOAN_PL = "TENURE_OF_THE_LOAN_PL";
        public static final String EMPLOYMENT_CATEGORY_AND_SALARY_INFORMATION = "EMPLOYMENT_CATEGORY_AND_SALARY_INFORMATION";
        public static final String ANNUAL_INCOME_PL = "ANNUAL_INCOME_PL";
        public static final String INCOME_PROOF_PL = "INCOME_PROOF_PL";
        public static final String MON_INCOME_DEPENDANT_PL = "MON_INCOME_DEPENDANT_PL";
        public static final String AVG_INCREASE_INCOME_REPORT_3_YEARS_PL = "AVG_INCREASE_INCOME_REPORT_3_YEARS_PL";
        public static final String AVAILABLE_INCOME_PL = "AVAILABLE_INCOME_PL";
        public static final String AVG_DEPOS_LAST_6_MONTH_PL = "AVG_DEPOS_LAST_6_MONTH_PL";
        public static final String CHEQUE_BOUNCE_LAST_1_MONTH_PL = "CHEQUE_BOUNCE_LAST_1_MONTH_PL";
        public static final String ADDI_INCOME_SPOUSE_PL = "ADDI_INCOME_SPOUSE_PL";
        public static final String EMI_NMI_RATIO_PL = "EMI_NMI_RATIO_PL";
        public static final String CURRENT_JOB_EXP_PL = "CURRENT_JOB_EXP_PL";
        public static final String CURRENT_EMPLOYMENT_STATUS_PL = "CURRENT_EMPLOYMENT_STATUS_PL";
        public static final String MIN_BANKING_RELATIONSHIP_PL = "MIN_BANKING_RELATIONSHIP_PL";
        public static final String RESIDENCE_TYPE_PL = "RESIDENCE_TYPE_PL";
        public static final String REPAYMENT_MODE_PL = "REPAYMENT_MODE_PL";


        //Home Loan Parameter Constants
        public final class HomeLoan{
        	 private HomeLoan(){}
        	 
            public static final String AGE = "AGE_HL";
            public static final String TOTAL_WORK_EXP = "TOTAL_WORK_EXP_HL";
            public static final String CURRENT_JOB_EXP = "CURRENT_JOB_EXP_HL";
            public static final String RESIDENCE_TYPE = "RESIDENCE_TYPE_HL";
            public static final String NO_YEARS_STAY_CURR_LOC = "NO_YEARS_STAY_CURR_LOC_HL";
            public static final String BUREAU_SCORE = "BUREAU_SCORE_HL";
            public static final String MARITAL_STATUS = "MARITAL_STATUS_HL";
            public static final String EMPLOYMENT_TYPE = "EMPLOYMENT_TYPE_HL";
            public static final String EMPLOYMENT_CATEG_JOB = "EMPLOYMENT_CATEG_JOB_HL";
            public static final String EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED = "EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED_HL";
            public static final String CURRENT_EMPLOYMENT_STATUS = "CURRENT_EMPLOYMENT_STATUS_HL";
            public static final String MIN_BANKING_RELATIONSHIP = "MIN_BANKING_RELATIONSHIP_HL";
            public static final String SPOUSE_EMPLOYEMENT = "SPOUSE_EMPLOYEMENT_HL";
            public static final String NO_OF_DEPENDANTS = "NO_OF_DEPENDANTS_HL";
            public static final String DESIGNATION = "DESIGNATION_HL";
            public static final String EDUCATION_QUALIFICATION = "EDUCATION_QUALIFICATION_HL";
            public static final String INCOME_PROOF = "INCOME_PROOF_HL";
            public static final String EMI_NMI = "EMI_NMI_HL";
            public static final String AVG_EOD_BALANCE = "AVG_EOD_BALANCE_HL";
            public static final String INCOME_TO_INSTALLMENT_RATIO = "INCOME_TO_INSTALLMENT_RATIO_HL";
            public static final String LOAN_TO_INCOME_RATIO = "LOAN_TO_INCOME_RATIO_HL";
            public static final String NO_OF_APPLICANTS = "NO_OF_APPLICANTS_HL";
            public static final String ANNUAL_INCOME = "ANNUAL_INCOME_HL";
            public static final String AVAILABLE_INCOME = "AVAILABLE_INCOME_HL";
            public static final String ADDI_INCOME_SPOUSE = "ADDI_INCOME_SPOUSE_HL";
            public static final String MON_INCOME_DEPENDANT = "MON_INCOME_DEPENDANT_HL";
            public static final String AVG_INCREASE_INCOME_REPORT_3_YEARS = "AVG_INCREASE_INCOME_REPORT_3_YEARS_HL";
            public static final String REPAYMENT_PERIOD = "REPAYMENT_PERIOD_HL";
            public static final String TENURE = "TENURE_HL";
            public static final String AGE_PROPERTY = "AGE_PROPERTY_HL";
            public static final String AVG_DEPOS_LAST_6_MONTH = "AVG_DEPOS_LAST_6_MONTH_HL";
            public static final String CHECQUE_BOUNSE_LAST_1_MONTH = "CHECQUE_BOUNSE_LAST_1_MONTH_HL";
            public static final String CHECQUE_BOUNSE_LAST_6_MONTH = "CHECQUE_BOUNSE_LAST_6_MONTH_HL";
            public static final String DPD = "DPD_HL";
            public static final String LTV = "LTV_HL";
            public static final String EMI_NMI_RATIO = "EMI_NMI_RATIO_HL";
            public static final String LOAN_PURPOSE = "LOAN_PURPOSE_HL";
            public static final String APPLICANT_NW_TO_LOAN_AMOUNT = "APPLICANT_NW_TO_LOAN_AMOUNT_HL";
            
            //Loan Purpose Model Related Changes
            public static final String PUR_READY_BUILT_HOUSE = "PUR_READY_BUILT_HOUSE_HL";
            public static final String PUR_READY_BUILT_INDEPENDENT_HOUSE = "PUR_READY_BUILT_INDEPENDENT_HOUSE_HL";
            public static final String PUR_RESIDETIAL_FLAT = "PUR_RESIDETIAL_FLAT_HL";
            public static final String PUR_RESIDETIAL_FLAT_ALLOTEE = "PUR_RESIDETIAL_FLAT_ALLOTEE_HL";
            public static final String PUR_RESIDETIAL_SITE = "PUR_RESIDETIAL_SITE_HL";
            public static final String CONSTRU_RESIDETIAL_BUID = "CONSTRU_RESIDETIAL_BUID_HL";
            public static final String CONSTRU_EXPA_RES_BUILD = "CONSTRU_EXPA_RES_BUILD_HL";
            public static final String CONSTRU_PUR_RES_SITE = "CONSTRU_PUR_RES_SITE_HL";
            public static final String REP_PUR_READY_BUILT_INDEPENDANT = "REP_PUR_READY_BUILT_INDEPENDANT_HL";
            public static final String REP_REN_IMP_FLAT_HOUSE = "REP_REN_IMP_FLAT_HOUSE_HL";
            public static final String OTH_REF_EXCESS_MARGIN_PAID = "OTH_REF_EXCESS_MARGIN_PAID_HL";
            public static final String OTH_LOAN_REIMBURSEMENT_FLAT = "OTH_LOAN_REIMBURSEMENT_FLAT_HL";
        }
        
        public final class AutoLoan{
        	private AutoLoan(){}
        	 public static final String AGE = "AGE_AL";
        	 public static final String CURRENT_JOB_EXP = "CURRENT_JOB_EXP_AL";
        	 public static final String TOTAL_WORK_EXP = "TOTAL_WORK_EXP_AL";
        	 public static final String RESIDENCE_TYPE = "RESIDENCE_TYPE_AL";
             public static final String NO_YEARS_STAY_CURR_LOC = "NO_YEARS_STAY_CURR_LOC_AL";
             public static final String BUREAU_SCORE = "BUREAU_SCORE_AL";
             public static final String MARITAL_STATUS = "MARITAL_STATUS_AL";
             public static final String EMPLOYMENT_TYPE = "EMPLOYMENT_TYPE_AL";
             public static final String EMPLOYMENT_CATEG_JOB = "EMPLOYMENT_CATEG_JOB_AL";
             public static final String EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED = "EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED_AL";
             public static final String CURRENT_EMPLOYMENT_STATUS = "CURRENT_EMPLOYMENT_STATUS_AL";
             public static final String MIN_BANKING_RELATIONSHIP = "MIN_BANKING_RELATIONSHIP_AL";
             public static final String NO_OF_DEPENDANTS = "NO_OF_DEPENDANTS_AL";
             public static final String CAR_SEGMENT = "CAR_SEGMENT_AL";
             public static final String DESIGNATION = "DESIGNATION_AL";
             public static final String EDUCATION_QUALIFICATION = "EDUCATION_QUALIFICATION_AL";
             public static final String LOAN_PURPOSE = "LOAN_PURPOSE_AL";
             public static final String SPOUSE_EMPLOYEMENT = "SPOUSE_EMPLOYEMENT_AL";
             public static final String AGE_OF_VEHICLE = "AGE_OF_VEHICLE_AL";
             public static final String INCOME_PROOF = "INCOME_PROOF_AL";
             public static final String EMI_NMI = "EMI_NMI_AL";
             public static final String AVG_EOD_BALANCE = "AVG_EOD_BALANCE_AL";
             public static final String INCOME_TO_INSTALLMENT_RATIO = "INCOME_TO_INSTALLMENT_RATIO_AL";
             public static final String SECURITY_COVERAGE = "SECURITY_COVERAGE_AL";
             public static final String LOAN_TO_INCOME_RATIO = "LOAN_TO_INCOME_RATIO_AL";
             public static final String NO_OF_APPLICANTS = "NO_OF_APPLICANTS_AL";
             public static final String ANNUAL_INCOME = "ANNUAL_INCOME_AL";
             public static final String TAKE_HOME_PAY = "TAKE_HOME_PAY_AL";
             public static final String AVAILABLE_INCOME = "AVAILABLE_INCOME_AL";
             public static final String ADDI_INCOME_SPOUSE = "ADDI_INCOME_SPOUSE_AL";
             public static final String MON_INCOME_DEPENDANT = "MON_INCOME_DEPENDANT_AL";
             public static final String AVG_INCREASE_INCOME_REPORT_3_YEARS = "AVG_INCREASE_INCOME_REPORT_3_YEARS_AL";
             public static final String REPAYMENT_PERIOD = "REPAYMENT_PERIOD_AL";
             public static final String TENURE = "TENURE_AL";
             public static final String AVG_DEPOS_LAST_6_MONTH = "AVG_DEPOS_LAST_6_MONTH_AL";
             public static final String CHECQUE_BOUNSE_LAST_1_MONTH = "CHECQUE_BOUNSE_LAST_1_MONTH_AL";
             public static final String CHECQUE_BOUNSE_LAST_6_MONTH = "CHECQUE_BOUNSE_LAST_6_MONTH_AL";
             public static final String DPD = "DPD_AL";
             public static final String LTV = "LTV_AL";
             public static final String EMI_NMI_RATIO =	 "EMI_NMI_RATIO_AL";
             public static final String APPLICANT_NW_TO_LOAN_AMOUNT = "APPLICANT_NW_TO_LOAN_AMOUNT_AL";
             public static final String BORROWER_MARGIN = "BORROWER_MARGIN_AL";
             public static final String PERSONAL_RELATIONSHIP_WITH_BANK = "PERSONAL_RELATIONSHIP_WITH_BANK_AL";
             public static final String IS_ADHAAR_CARD = "IS_ADHAAR_CARD_AL";
        }
    }

    public final class MFI
    {
        private MFI()
        { // nothing to do.
        }
        public static final String AGE_OF_BORROWER_MFI = "AGE_OF_BORROWER_MFI";
        public static final String ACADEMIC_QUALIFICATION_MFI = "ACADEMIC_QUALIFICATION_MFI";
        public static final String DEPENDENTS_IN_THE_FAMILY_MFI = "DEPENDENTS_IN_THE_FAMILY_MFI";
        public static final String OWNERSHIP_OF_HOUSE_MFI = "OWNERSHIP_OF_HOUSE_MFI";
        public static final String ANNUAL_INCOME_AS_APPLICABLE_MFI = "ANNUAL_INCOME_AS_APPLICABLE_MFI";
        public static final String EXPERIENCE_IN_THE_BUSINESS_WORKING_MFI = "EXPERIENCE_IN_THE_BUSINESS_WORKING_MFI";
        public static final String PURPOSE_OF_LOAN_MFI = "PURPOSE_OF_LOAN_MFI";
    }
    public static final class BusinessType
    {
        private BusinessType()
        {
            // nothing to do.
        }
        public static final Long EXISTING_BUSINESS=1l;
        public static final Long NTB=2l;
        public static final Long RETAIL_PERSONAL_LOAN=3l;
        public static final Long RETAIL_HOME_LOAN = 5l;
        public static final Long MFI_LOAN = 6l;
        public static final Long RETAIL_AUTO_LOAN = 8l;
        public static final Long MSME_CVL_LOAN = 25l;
    }

    public static final class Status {
        private Status()
        {
            // nothing to do.
        }
		public static final Integer OPEN = 1;
		public static final Integer IN_PROGRESS = 2;
		public static final Integer REVERTED = 3;
		public static final Integer APPROVED = 4;
		public static final Integer REJECTED = 5;
		public static final Integer EXPIRED = 6;
		
	}
    
    public static final class NoOfApplicants {
        private NoOfApplicants(){
            // nothing to do.
        }
		public static final Integer SINGLE = 1;
		public static final Integer JOINT = 2;
		public static final Integer JOINT_WHERE_CO_APPLICANT_IS_EARNING = 3;
	}
    
    public static final class IncomeProof {
        private IncomeProof(){
            // nothing to do.
        }
		public static final Integer BANK_STATEMENT = 1;
		public static final Integer IT_RETURN_AND_BANK_STATEMENT = 2;
		public static final Integer NOT_AVAILABLE = 3;
	}
    
    public static final class IncomeType {
        private IncomeType(){
            // nothing to do.
        }
        public static final String GMI = "1";
		public static final String NMI = "2";
	}
    
    public static final class BureauVersion {
        private BureauVersion(){
            // nothing to do.
        }
        public static final String V1 = "1";
		public static final String V2 = "2";
	}

    public static final class FinancialType
    {
        private FinancialType(){
            // nothing to do.
        }
        public static final Integer ONE_YEAR_ITR =1;
        public static final Integer THREE_YEAR_ITR=3;
        public static final Integer PRESUMPTIVE=4;
    }

    public static final class FinancialTypeForITR
    {
        private FinancialTypeForITR()
        {
            // nothing to do.
        }
        public static final Integer ONE_YEAR_ITR =1;
        public static final Integer TWO_YEAR_ITR=2;
        public static final Integer THREE_YEAR_ITR=3;
        public static final Integer PRESUMPTIVE=4;
    }

}
