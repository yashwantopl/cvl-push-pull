package com.opl.mudra.api.matchengine.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.ibm.icu.text.NumberFormat;

public class CommonUtils {

	public static final String USER_ORG_ID = "userOrgId";

	public static final String ONE_FORM = "oneForm";
	public static final String WORKING_CAPITAL = "WORKING_CAPITAL";
	public static final String TERM_LOAN = "TERM_LOAN";
	public static final String HOME_LOAN = "HOME_LOAN";
	public static final String CAR_LOAN = "CAR_LOAN";
	public static final String PERSONAL_LOAN = "PERSONAL_LOAN";
	public static final String LAP_LOAN = "LAP_LOAN";
	public static final String LAS_LOAN = "LAS_LOAN";

	public static final Long USER_TYPE_FUNDSEEKER = 1l;
	public static final Long USER_TYPE_FUNDPROVIDER = 2l;
	public static final Long USER_TYPE_SERVICEPROVIDER = 3l;
	public static final String EXCEPTION = "Exception :: {}";
	public static final String COMMON_ERROR_MSG = "The application has encountered an error, please try again after sometime!!!.";
	public static final SimpleDateFormat DATE_FOMATE = new SimpleDateFormat("dd-MM-yyyy");
	
	public final class ParameterTypes {
		private ParameterTypes(){
			// Do nothing because of X and Y.
		}
		public static final String CIBIL_CMR = "CIBIL_CMR";
		public static final String CIBIL_SCORE = "CIBIL_SCORE";
		public static final String LOAN_AMOUNT = "LOAN_AMOUNT";
		public static final String CIBIL_DPD = "CIBIL_DPD";
	}

	public static boolean isListNullOrEmpty(Collection data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null || (value instanceof String ? (((String) value).isEmpty()
				|| "".equals(((String) value).trim()) || "null".equals(value) || "undefined".equals(value)) : false));
	}
	
	public static Integer convertInteger(Object obj){
    	try {
    		if(!CommonUtils.isObjectNullOrEmpty(obj)) {
    			if(obj instanceof BigInteger) {
    				BigInteger value = (BigInteger) obj;
        			return value.intValue();	
    			} else {
    				return (Integer) obj;
    			}
    		}	
		} catch (Exception e) {
		}
		return null;
	}

	public enum LoanType {
		WORKING_CAPITAL(1), TERM_LOAN(2), HOME_LOAN(3), CAR_LOAN(12), PERSONAL_LOAN(7), LAP_LOAN(13), LAS_LOAN(
				14), UNSECURED_LOAN(15), WCTL_LOAN(16),MFI(17);
		private int value;

		private LoanType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static LoanType getType(Long x) {
			switch (x.intValue()) {
			case 1:
				return WORKING_CAPITAL;
			case 2:
				return TERM_LOAN;
			case 3:
				return HOME_LOAN;
			case 12:
				return CAR_LOAN;
			case 7:
				return PERSONAL_LOAN;
			case 13:
				return LAP_LOAN;
			case 14:
				return LAS_LOAN;
			case 15:
				return UNSECURED_LOAN;
			case 16:
				return WCTL_LOAN;
			case 17:
				return MFI;
			default:
				return null;
			}
		}
	}
	
	public enum ITRYear {
		ONE(1),
		TWO(2),
		THREE(3);
		private int value;

		private ITRYear(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static ITRYear getType(Integer x) {
			for(ITRYear type : ITRYear.values()){
				if(type.value == x){
					return type; 
				}
			}
			return null;
		}
	}
	
	public enum ITRType {
		AUDITED(1),
		NO_AUDITED(1);
		private int value;

		private ITRType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static ITRType getType(Integer x) {
			for(ITRType type : ITRType.values()){
				if(type.value == x){
					return type; 
				}
			}
			return null;
		}
	}

	public static String getFPTableName(Long productId) {
		switch (productId.intValue()) {
		case 1:
			return "fp_working_capital_details";
		case 2:
			return "fp_term_loan_details";
		case 3:
			return "fp_home_loan_details";
		case 12:
			return "fp_auto_loan_details";
		case 7:
			return "fp_personal_loan_details";
		case 13:
			return "fp_loan_against_property_details";
		case 14:
			return "fp_loan_against_share_details";
		case 15:
			return "fp_unsecure_loan_details";
		case 16:
			return "fp_wc_tl_details";
		case 17:
			return "mfi_loan_details";
		default:
			return null;
		}
	}

	public static String getFPTableNameNTB(Long productId) {
		switch (productId.intValue()) {
			case 2:
				return "fp_ntb_tl_details";
			default:
				return null;
		}
	}
	
	public static String getFPRetailTableName(Integer businessTypeId) {
		switch (businessTypeId) {
		case 5:
			return "fp_home_loan_details";
		case 8:
			return "fp_auto_loan_details";
		case 3:
			return "fp_personal_loan_details";
		default:
			return null;
		}
	}

	public static String getFSTableName(Long productId) {
		switch (productId.intValue()) {
		case 1:
			return "fs_corporate_primary_wc_loan_details";
		case 2:
			return "fs_corporate_primary_term_loan_details";
		case 3:
			return "fs_retail_primary_home_loan_details";
		case 12:
			return "fs_retail_primary_auto_loan_details";
		case 7:
			return "fp_personal_loan_details";
		case 13:
			return "fp_loan_against_property_details";
		case 14:
			return "fp_loan_against_share_details";
		case 15:
			return "fs_corporate_primary_unsecured_loan_details";
		default:
			return null;
		}
	}

	public static int getUserMainType(int productId) {
		if (productId == 1 || productId == 2 || productId == 15)
			return 2;
		else
			return 1;
	}

	public static String getValueOfPropertyforHomeLoan(int id) {
		switch (id) {
		case 3:
			return "property_price";
		case 4:
			return "construction_cost";
		case 5:
			return "renovation_cost";
		case 6:
			return "land_plot_cost";
		default:
			return null;
		}
	}

	public static String CurrencyFormat(String value) {
		NumberFormat format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(0);
		return format.format(new BigDecimal(value)).substring(4);

	}

	public static long getDateDifference(Date from, Date to) {
		long diffMiliSec = from.getTime() - to.getTime();
		return TimeUnit.DAYS.convert(diffMiliSec, TimeUnit.MILLISECONDS);
	}

	/*public static int getCurrentFinancialYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Integer currentFinYear = cal.get(Calendar.YEAR);
		return currentFinYear - 1;
	}*/

	public enum MatchesStages {
		IN_ELIGIBILE(0, "IN_ELIGIBILE", "IN_ELIGIBILE"),
		GST(1, "GST", "GST"), ITR(2, "ITR", "ITR"),
		BANK_STATEMENT(3,"BANK STATEMENT", "BANK_STATEMENT"),
		ONE_FORM(4, "ONE FORM", "ONE_FORM"),
		CIBIL(5, "CIBIL", "CIBIL"),

		NTB_ITR(103, "NTB ITR", "NTB_ITR"),
		NTB_BANK_STATEMENT(104, "NTB BANK STATEMENT", "NTB_BANK_STATEMENT"),
		NTB_ONE_FORM(105, "NTB ONE FORM", "NTB_ONE_FORM"),
		NTB_ONE_FORM_OTHER(106, "NTB ONE FORM OTHER", "NTB_ONE_FORM_OTHER"),

		RETAIL_AADHAR(202, "RETAIL AADHAR",""),
		RETAIL_ITR(203, "RETAIL ITR","RETAIL_ITR"),
		RETAIL_BANK_STATEMENT(204, "RETAIL BANK STATEMENT","RETAIL_BANK_STATEMENT"),
		RETAIL_ONE_FORM_BASIC_DETAILS(205, "RETAIL ONE FORM BASIC DETAILS","RETAIL_ONE_FORM_BASIC_DETAILS"),
		RETAIL_ONE_FORM_LOAN_DETAILS(206, "RETAIL ONE FORM LOAN DETAILS","RETAIL_ONE_FORM_LOAN_DETAILS"),

		ONEPAGER_ONEFORM(301, "ONEPAGER ONE FORM", "OPOF"),
	    ONEPAGER_CIBIL(302, "ONEPAGER_CIBIL", "OPCB"),
	    ONEPAGER_MATCHES(303, "ONEPAGER MATCHES", "OPM"),
	    ONEPAGER_PAYMENT(304, "PAYMENT", "OPP"),
	    ONEPAGER_IN_PRICIPLE(305, "IN PRINCIPLE", "OPI"),
	    ONEPAGER_COMPLETE(306, "COMPLETE", "OPC"),
		
		HL_ITR(502, "HL ITR","HL ITR"),
	    HL_ITR_CO_APP(503, "HL ITR CO-APP","HL ITR CO-APP"),
	    HL_BANK_STATEMENT(504, "HL BANK STATEMENT","HL BANK STATEMENT"),
	    HL_BANK_STATEMENT_CO_APP(505, "HL BANK STATEMENT CO-APP","HL BANK STATEMENT CO-APP"),
	    HL_ONE_FORM_BASIC_DETAILS(506, "HL ONE FORM BASIC DETAILS","HL ONE FORM BASIC DETAILS"),
	    HL_ONE_FORM_CIBIL_AUTHENTICATION(507, "HL ONE FORM CIBIL AUTHENTICATION","HL ONE FORM CIBIL AUTHENTICATION"),
	    HL_ONE_FORM_CIBIL_AUTH_CO_APP(508, "HL ONE FORM CIBIL AUTHENTICATION CO-APP","HL ONE FORM CIBIL AUTHENTICATION CO-APP"),
	    HL_ONE_FORM_LOAN_DETAILS(509, "HL ONE FORM LOAN DETAILS","HL ONE FORM LOAN DETAILS"),
	    HL_MATCHES(510, "HL MATCHES","HL MATCHES"),
	    
		MUDRA_MCQ(1000, "MUDRA MCQ", "MUDRA MCQ"),
	    MUDRA_GST(1001, "MUDRA GST", "MUDRA GST"),
	    MUDRA_ITR(1002, "MUDRA ITR", "MUDRA ITR"),
	    MUDRA_BANK_STATEMENT(1003, "MUDRA BANK STATEMENT", "MUDRA BANK STATEMENT"),
	    MUDRA_DIRECTOR_BACKGROUND(1004, "MUDRA DIRECTOR BACKGROUND", "MUDRA DIRECTOR BACKGROUND"),
	    MUDRA_ONE_FORM(1005, "MUDRA ONE FORM", "MUDRA ONE FORM"),
	    MUDRA_MATCHES(1006, "MUDRA MATCHES", "MUDRA MATCHES"),
	    MUDRA_PAYMENT(1007, "MUDRA PAYMENT", "MUDRA PAYMENT"),
	    MUDRA_IN_PRINCIPLE(1008, "MUDRA IN PRINCIPLE", "MUDRA IN PRINCIPLE"),
	    MUDRA_COMPLETE(1009, "MUDRA COMPLETE", "MUDRA COMPLETE");


		private int value;
		private String name;
		private String code;

		private MatchesStages(int value, String name, String code) {
			this.value = value;
			this.name = name;
			this.code = code;
		}

		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public String getCode(boolean inLowerCase) {
			if (inLowerCase) {
				return code.toLowerCase();
			}
			return code;
		}

		public static MatchesStages getStagesType(Integer x) {
			switch (x) {
			case 1:
				return GST;
			case 2:
				return ITR;
			case 3:
				return BANK_STATEMENT;
			case 4:
				return ONE_FORM;
			case 5:
				return CIBIL;
			case 103:
				return NTB_ITR;
			case 104:
				return NTB_BANK_STATEMENT;
			case 105:
				return NTB_ONE_FORM;
			case 106:
				return NTB_ONE_FORM_OTHER;
			default:
				return null;
			}
		}
	}

	public enum DdrFlow {
		OWN_FLOW(1,"Own Flow","OWN_FLOW"), NHBS_FLOW(2,"Nhbs Flow","NHBS_FLOW");
		private int value;
		private String name;
		private String code;

		private DdrFlow(int value,String name,String code) {
			this.value = value;
			this.name = name;
			this.code = code;
		}

		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
		public String getCode(boolean inLowerCase) {
			if(inLowerCase) {
				return code.toLowerCase();
			}
			return code;
		}

		public static DdrFlow getStagesType(Integer x) {
			switch (x) {
				case 1:
					return OWN_FLOW;
				case 2:
					return NHBS_FLOW;
				default:
					return null;
			}
		}

	}
	static DecimalFormat decim2 = new DecimalFormat("#,###");
	public static String convertValueWithoutDecimal(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value)? decim2.format(value) : "0";
	}
	
	public static Object convertValueIndianCurrency(Object value) {
		if(value != null) {
			NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
			formatter.setMinimumFractionDigits(0);
			formatter.setMaximumFractionDigits(0);
			/*formatter.setMinimumFractionDigits(0);*/
			return formatter.format(value);
		}else {
			return "-";
		}
		
	}
	static DecimalFormat decimal = new DecimalFormat("#,##0.00");
	public static Object convertStringFormate(Object value) {
		try {
			return String.valueOf(convertValueIndianCurrency(decimal.parse(String.valueOf(value))));
		} catch (Exception e) {
		}
		return "0";
	}
	
	public static Object convertValueIndianCurrencyWithDecimal(Object value) {
		if(value != null) {
			NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
			formatter.setMinimumFractionDigits(2);
			return formatter.format(value);
		}else {
			return "-";
		}
		
	}

	public enum TotalLimitType {
		EXISTING_ONLY(1,"Existing Loan only (Takeover)","EXISTING_ONLY"),
		ADDITIONAL_ONLY(2,"Additional loan only (No Takeover/Multiple Banking)","ADDITIONAL_ONLY"),
		EXISTING_ADDITIONAL_ONLY(3,"Existing/Takeover + Additional Loan (Sole Banking)","EXISTING_ADDITIONAL_ONLY");
		private int value;
		private String name;
		private String code;

		private TotalLimitType(int value,String name,String code) {
			this.value = value;
			this.name = name;
			this.code = code;
		}

		public int getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
		public String getCode(boolean inLowerCase) {
			if(inLowerCase) {
				return code.toLowerCase();
			}
			return code;
		}

		public static TotalLimitType getType(Integer x) {
			switch (x) {
				case 1:
					return EXISTING_ONLY;
				case 2:
					return ADDITIONAL_ONLY;
				case 3:
					return EXISTING_ADDITIONAL_ONLY;
				default:
					return null;
			}
		}

	}


	public enum CampaignLoanType {
		Msme(Long.valueOf(1), "Msme", "Msme"),
		Retail(Long.valueOf(2), "Retail", "Retail"),
		Both(Long.valueOf(3), "Both", "Both");

		private final Long id;
		private final String value;
		private final String description;

		private CampaignLoanType(Long id, String value, String description) {
			this.id = id;
			this.value = value;
			this.description = description;
		}

		public Long getId() {
			return this.id;
		}

		public String getValue() {
			return this.value;
		}

		public String getDescription() {
			return this.description;
		}

		public static CampaignLoanType getById(Integer id) {
			switch(id) {
				case 1:
					return Msme;
				case 2:
					return Retail;
				case 3:
					return Both;
				default:
					return null;
			}
		}

		public static CampaignLoanType[] getAll() {
			return values();
		}
	}
	
	public enum BankBureauResponseType {
		MATCHES(1, "Matches"), SCORING(2, "Scoring"), LOANS(3, "Loans");

		private Integer id;
		private String value;

		private BankBureauResponseType(Integer id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public Integer getId() {
			return this.id;
		}
		
		public static BankBureauResponseType fromValue(String v) {
			for (BankBureauResponseType c : BankBureauResponseType.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			return null;
		}

		public static BankBureauResponseType fromId(Integer v) {
			for (BankBureauResponseType c : BankBureauResponseType.values()) {
				if (c.id.equals(v) || v == c.id) {
					return c;
				}
			}
			return null;
		}
	}
}

