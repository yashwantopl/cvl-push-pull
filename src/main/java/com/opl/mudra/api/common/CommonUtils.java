
package com.opl.mudra.api.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.GeneralConfigData;

public class CommonUtils {

	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	public static final String USER_ID = "userId";
	public static final String USER_TYPE_ID = "userTypeId";
	public static final String MBL_CLIENTTXNID = "mbl";
	public static final String FALSE = "false";
	public static final String TRUE = "true";
	public static final String STUCK_WHILE_CHECKING_YOUR_DATA = "Oops! We got stuck while checking your data. Kindly logout and login again";
	public static final String ITR_FORM_OTHERS = "ITRForm:Others";
	public static final String EXCEPTION_GET_ITR_FILES_FROM_SCRAPING = "THROW EXCEPTION WHILE GET ITR FILES FROM SCRAPING";
	public static final String ITR_FORM_WORK_IN_PROGRESS = "ITRForm:WorkInProgress";
	public static final String ITR_FORM_TOTAL = "ITRForm:Total";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong";
	public static final String DATA_WE_ARE_LOOKING_FOR_COULD_NOT_BE_FETCHED_PROPERLY = "The data we are looking for could not be fetched properly. Kindly logout and login again to proceed";
	public static final String FILE_UPLOADED_COULD_NOT_BE_READ_PROPERLY = "The file uploaded could not be read properly. Kindly upload the file again";
	public static final String OS_ASSET_LIABILITY_ZERO_ERROR = "OS_ASSET_LIABILITY_ZERO_ERROR";
	public static final String OS_TOTAL_LIABILITY_ZERO_ERROR = "OS_TOTAL_LIABILITY_ZERO_ERROR";
	public static final String OS_TOTAL_ASSETS_ZERO_ERROR = "OS_TOTAL_ASSETS_ZERO_ERROR";
	public static final String OS_ASSETS_LIABILIBTY_PROFIT_ZERO_ERROR = "OS_ASSETS_LIABILIBTY_PROFIT_ZERO_ERROR";
	public static final String OS_NET_PROFIT_ZERO_ERROR = "OS_NET_PROFIT_ZERO_ERROR";
	public static final String NET_PROFIT_ZERO_ERROR_UNIFORM_PRODUCT = "NET_PROFIT_ZERO_ERROR_UNIFORM_PRODUCT";
	public static final String TURN_OVER_PREV_FIN_YEAR_ZERO_ERROR_UNIFORM_PRODUCT = "TURN_OVER_PREV_FIN_YEAR_ZERO_ERROR_UNIFORM_PRODUCT";
	public static final String GROSS_SALES_ZERO_ERROR_UNIFORM_PRODUCT = "GROSS_SALES_ZERO_ERROR_UNIFORM_PRODUCT";
	public static final String EXCEPTION_WHILE_SAVE_FILES = "EXCEPTION_WHILE_SAVE_FILES";
	public static final String EXCEPTION = "Exception : ";
	public static final String PROPOSAL_MAPPING_ID = "proposalMappingId";
	public static final String USER_TYPE = "userType";
	public static final String USER_ORG_ID = "userOrgId";
	public static final String BUSINESS_TYPE_ID = "businessTypeId";
	public static final String CITY_NAME = "cityName";
	public static final String STATE_NAME = "stateName";
	public static final String COUNTRY_NAME = "countryName";
	public static final int USER_TYPE_SERVICEPROVIDER = 3;
	public static final String INVALID_REQUEST = "Invalid Request !";
	public static final String WRONG_FILE_FORMAT = "Please upload PDF file only";
	public static final String CORPORATE = "Corporate";
	public static final String RETAIL = "Retail";
	public static final String ONE_FORM = "oneForm";
	public static final String USER_CLIENT_URL = "userURL";
	public static final String MATCHES_URL = "matchesURL";
	public static final String DMS_BASE_URL_KEY = "dmsURL";
	public static final String NOT_APPLICABLE = "NA";
	public static final String WC_PRIMARY_EXCEL = "Teaser Download-Working Capital.xlsx";
	public static final String EXCEL_TEASER_BASE_URL = "excelTeaserBaseUrl";
	public static final String APPLICATION_LOCKED_MESSAGE = "Your Application is locked. Please Contact Administrator to update the Details.";
	public static final String MAXIMUM = "maximum";
	public static final String MINIMUM = "minimum";
	public static final String GST_VALIDATION_ERROR_MSG = "Please Enter Valid GSTIN and Verify Before Moving ahead.";
	public static final String ITR_VALIDATION_ERROR_MSG = "Please Upload Valid itr and Verify Before Moving ahead.";
	public static final String GENERIC_ERROR_MSG = "The application has encountered an error from Server. Please try again after sometime!!!.";
	public static final String INVALID_PAN = "Invalid Pan";
	public static final String HUNTER_INELIGIBLE_MESSAGE = "You do not Qualify for Contactless Process, Kindly visit Bank Branch or get your Due Diligence process completed in www.opl.com to connect to Banks";

	public static final Long RETAIL_APPLICANT = 1L;
	public static final Long RETAIL_COAPPLICANT = 2L;
	public static final Long RETAIL_GUARANTOR = 3L;
	public static final Long CORPORATE_USER = 4L;
	public static final Long NP_NHBS = 11L;
	public static final Long CORPORATE_COAPPLICANT = 7L;
	public static final Long CW_SP_USER_ID = 101L;
	public static final Long TL_LESS_TWO = 20000000L;
	public static final Integer PENDING = 0;
	public static final Integer APPROVED = 1;
	public static final Integer BASIC_DETAILS = 0;
	public static final Integer PERSONAL_DETAILS = 1;
	public static final Integer BANK_DETAILS = 2;
	public static final Integer INCOME_EXPENDITURE = 3;
	public static final Integer PROJECT_DETAILS = 4;
	public static final Integer ASSETS_LIABILITY = 5;
	public static final Integer LOAN_ASSESMENT = 6;
	public static final Integer LOAN_RECOMANDATION = 7;

	public static final String DDR_NOT_APPROVED = "DDR is not yet approved by Approver !";

	public static final String CW_CMA_EXCEL = "cw_cma.xlsx";
	public static final String CW_TL_WCTL_EXCEL = "cw_cma_tl_wctl.xlsx";
	public static final String CO_CMA_EXCEL = "co_cma.xlsx";

	public static final String SCORING_EXCEL = "score_result.xlsx";

	public static final DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");

	public static final String IN_PROGRESS = "In Progress";
	public static final String COMPLETED = "Completed";
	public static final String SUCCESS = "Success";
	public static final String FALSE_LITERAL = "false";
	public static final String NA = "NA";
	public static final String AUDITED = "Audited";
	public static final String PROJECTED = "Projected";
	public static final String SUCCESS_RESULT = "Success Result";
	public static final String DATA_FOUND = "Data Found.";
	public static final String DATA_NOT_FOUND = "Data Not Found.";
	public static final String SUCCESSFULLY_SAVED = "Successfully Purpose of loan created";
	public static final String SUCCESSFULLY_UPDATED = "Purpose of Loan Model sent for approval";
	public static final String STATUS_UPDATED = "Purpose of Model Saved Successfully";

	public static final String INVALID_AGE = "Invalid Age";
	public static final String ONE_FORM_SAVED_SUCCESSFULLY = "Oneform Saved Successfully";
	public static final String SUCCESSFULLY_GET_DATA = "Successfully get data";

	public static final String CLIENT_ID_IS_NOT_VALID = "Client Id is not valid";
	public static final String OBLIGATION_MUST_BE_LESS_THAN_INCOME = "Obligation Must be less than Income";
	public static final String REQUEST_DATA_NULL_OR_EMPTY = "Request Data Null Or Empty !!";
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	public static final String INSERT_TEXT_HERE = "Insert Text Here";
	public static final String MANDATORY_FIELDS_MUST_NOT_BE_NULL = "Mandatory Fields Must Not be Null";
	public static final String REQUESTED_DATA_CAN_NOT_BE_EMPTY = "Requested data can not be empty.";
	public static final String REQUESTED_DATA_CAN_NOT_BE_NULL_OR_EMPTY = "Requested Data cannot be null or empty";
	public static final String INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND = "Invalid data or Requested data not found.";
	public static final String UNAUTHORIZED_USER_PLEASE_RE_LOGIN_AND_TRY_AGAIN = "Unauthorized User! Please Re-login and try again.";

	public static final String YOU_ARE_NOT_ELIGIBLE_FOR_HOME_LOAN = "You are not eligible for Home Loan";
	public static final String YOU_ARE_NOT_ELIGIBLE_FOR_PERSONAL_LOAN = "You are not eligible for Personal Loan";

	public static final String PARAMETERS_FP_NAME = "fp_name";
	public static final String PARAMETERS_FS_NAME = "fs_name";
	public static final String PARAMETERS_LOAN_TYPE = "loan_type";
	public static final String PARAMETERS_LOAN_AMOUNT = "loan_amount";
	public static final String PARAMETERS_APPLICATION_ID = "application_id";
	public static final String PARAMETERS_EMI_AMOUNT = "emi_amount";
	public static final String PARAMETERS_ADDRESS = "address";
	public static final String PARAMETERS_IS_DYNAMIC = "isDynamic";
	public static final String RATE_INTEREST = "rate_interest";
	public static final String LITERAL_AMOUNT = "amount";
	public static final String GET_LIST = "getList";
	public static final String GET_PRIMARY = "getPrimary";
	public static final String ORG_NAME = "orgName";
	public static final String SAVE_OR_UPDATE = "saveOrUpdate";

	public static final String STORAGE_DETAILS_ID = "storageDetailsId";
	public static final String APPLICATION_ID = "applicationId";
	public static final String CREATED_BY = "createdBy";
	public static final String CREATED_DATE = "createdDate";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String IS_ACTIVE = "isActive";
	public static final String ENTRY_IN = "Entry in ";
	public static final String EXIT_FROM = "Exit From ";

	public static final String CREDIT_CARD = "Credit Card";

	public static final Integer NBFC_FLOW = 1;
	public static final Integer NBFC_BANK_FLOW = 2;

	public static final Integer ELIGIBLE_ONLINE = 1;
	public static final Integer IN_ELIGIBLE_OFFLINE = 2;


	public static final String INVALID_REQUEST_MSG = "Invalid Request {}";
	public static final String COULD_NOT_PARSE_JSON_MSG = "Could Not Parse Json {}";
	public static final String DOCUMENT_PARSE_ERROR_IN_SERVER_SIDE_VALIDATION = "Document Parse Error in Server Side Validation {}";


	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	// payment constants
	public static final String SKIP_TYPE = "skipType";
	public static final String APPLICATION_IID = "application_iId";
	
	//Used in Payment Transactions
	public static final String API_KEY = "api_key";
	public static final String TXN_ID = "txnid";
	public static final String ORDER_ID = "order_id";
	public static final String EMAIL = "email";
	public static final String ORG_ID = "org_id";
	public static final String BRANCH_ID = "branch_id";
	public static final String MOBILE_NO = "mobile_no";
	public static final String AMOUNT = "amount";
	
	//Used in Inprinciple Details
	public static final String FS_NAME= "fs_name";
	public static final String FS_ADDRESS = "fs_address";
	public static final String TENURE = "tenure";
	public static final String LOAN_TYPE = "loan_type";
	public static final String YEARS = " years";
	public static final String LOAN_PURPOSE = "loan_purpose";
	public static final String EMPLOYMENT_TYPE = "employment_type";
	public static final String EMI_AMOUNT ="emi_amount";
	public static final String ORG_NAME_ = "org_name";
	public static final String ORGANISATION_NAME = "organisationName";
	public static final String MINPF = "minPf";
	public static final String MAXPF = "maxPf";
	public static final String ADDITIONAL_LOAN_AMOUNT = "additional_loan_amount";
	public static final String EXISTING_LOAN_AMOUNT = "existing_loan_amount";
	public static final String RATE_INTEREST_ = "rate_interest";
	public static final String WC_RENEWABLE = "wc_renewable";
	public static final String BANKER_NAME = "bankerName";
	public static final String PRODUCT_TYPE= "productType";
	public static final String LOAN_TYPE_ID = "loanTypeId";
	public static final String BANK_URL = "bank_url";
	public static final String IS_SBI = "isSBI";
	public static final String IS_CENTRAL = "isCentral";
	public static final String IS_KOTAK = "isKotak";
	public static final String IS_ANDHRA= "isAndhra";
	public static final String IS_CANARA= "isCanara";
	public static final String IS_OBC= "isObc";
	public static final String IS_INDUSIND = "isIndusInd";
	public static final String IS_ICICI = "isICICI";
	public static final String CC_EMAIL_LIST = "ccEmailList";
	
	public static final String PARAMETER_IS_NULL_OR_EMPTY = "Parameter is null or empty";
	public static final String SIDBI_INVOICE_FOR_FS = "SIDBIINVOICEFS";
	public static final String SIDBI_INVOICE_FOR_SUPPLIER = "SIDBIINVOICESUPPLIER";
	public static final String SCORINGMODELID_OR_SCORINGMODELNAME_IS_NULL_OR_EMPTY = "scoringmodelId is null or empty";
	
	public static final String ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING = "Error while getting retail applicant detail for personal loan scoring : ";
	public static final String ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_HOME_LOAN_SCORING = "Error while getting retail applicant detail for Home loan scoring : ";
	public static final String ERROR_WHILE_GETTING_FIELD_LIST = "Error while getting field list : ";
	public static final String ERROR_WHILE_CALLING_SCORING = "Error while calling scoring : ";
    public static final String ERROR_WHILE_CHECK_CIBIL = "Application has encountered error while check CIBIL bureau score.";
    public static final String ERROR_WHILE_GETTING_CURRENT_YEAR = "Error while getting current year from itr.";
    
    public static final String SAVING_SCORING_REQUEST_DATA_FOR = "Saving Scoring Request Data for  =====> ";
    public static final String SCORE_IS_SUCCESSFULLY_CALCULATED = "score is successfully calculated=====>{}";
    public static final String MSG_APPLICATION_ID = " APPLICATION ID   :: ";
    public static final String MSG_FP_PRODUCT_ID = " FP PRODUCT ID    :: ";
    public static final String MSG_SCORING_MODEL_ID = " SCORING MODEL ID :: ";
    public static final String MSG_SCORE_PARAMETER = "SCORE PARAMETER ::::::::::";
    public static final String ORG_ID_IS_NULL_OR_EMPTY  = "org id is null or empty : ";
    public static final String NO_COLLATERAL = "No Collateral";
	
	public static final List<String> ITR_URLS_BRFORE_LOGIN = new ArrayList<String>(8);
	static {
		ITR_URLS_BRFORE_LOGIN.add("/itr/error".toLowerCase());
	}

	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static String isStringEmpty(String value) {
		value = value.trim();
		if (value.isEmpty() || value == null || "undefined".equals(value) || "null".equals(value) || "".equals(value)) {
			return "";
		}
		return value;
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null
				|| (value instanceof String
						? (((String) value).isEmpty() || "".equals(((String) value).trim()) || "null".equals(value)
								|| "undefined".equals(value))
						: false));
	}

	public static boolean isObjectNullOrEmptyOrDash(Object value) {
		return (value == null || (value instanceof String
				? (((String) value).isEmpty() || "".equals(((String) value).trim()) || "null".equals(value)
						|| "-".equals(value) || "undefined".equals(value))
				: false));
	}

	public static <T> T convertTypeObj(Object obj, Class<?> clazz) {
		if (!isObjectNullOrEmpty(obj)) {
			return (T) obj;
		}
		return null;
	}

	public static boolean isObjectListNull(Object... args) {
		for (Object object : args) {
			boolean flag = false;
			if (object instanceof List) {
				flag = isListNullOrEmpty((List) object);
				if (flag)
					return true;
				else
					continue;
			}
			flag = isObjectNullOrEmpty(object);
			if (flag)
				return true;
		}
		return false;
	}

	public final class ITRResponseStatus {
		private ITRResponseStatus() {
		}

		public static final String COMPLETED = "COMPLETED";
		public static final String REPORT_DELIVERY_FAILED = "REPORT_DELIVERY_FAILED";
		public static final String ERROR = "ERROR";
		public static final String CANCELLED = "CANCELLED";
		public static final String TIMEOUT = "TIMEOUT";
	}

	public final class ITRErrorStatus {
		private ITRErrorStatus() {
		}

		public static final String USERACT = "USERACT";
		public static final String PASSWORD = "PASSWORD";
		public static final String PAGE = "PAGE";
		public static final String SITE = "SITE";
		public static final String INVALID = "INVALID";
		public static final String ERROR = "ERROR";
	}

	public static final class PaymentStatus {
		public static final String SUCCESS = "Success";
		public static final String PENDING = "Pending";
		public static final String FAILED = "Failed";
		public static final String BYPASS = "ByPass";
	}

	public static final class ConnectFlowTypes {
		public static final Integer TYPE_1 = 1;
	}

	/**
	 * Date :- 04/06/2018
	 *
	 * @author harshit USED FOR SAVE AND GET ONEFROM DETAILS ON FS RETAIL JOURNEY
	 */
	public static final class RetailOneformType {
		public static final int CONTACT_INFO = 2;
		public static final int EMPLOYMENT_INFO = 3;
		public static final int CREDIT_INFO = 4;
		public static final int BASIC_INFO = 1;
	}

	public static final class UsersRoles {
		private UsersRoles() {
			// Do nothing because of X and Y.
		}

		public static final Long MAKER = 1l;
		public static final Long CHECKER = 2l;
		public static final Long APPROVER = 3l;
		public static final Long ADMIN_HO = 4l;
		public static final Long HO = 5l;
		public static final Long BO = 6l;
		public static final Long DEFAULT_FS = 7l;
		public static final Long FP_MAKER = 8l;
		public static final Long FP_CHECKER = 9l;
		public static final Long ADMIN_MAKER = 10l;
		public static final Long ADMIN_CHECKER = 11l;
		public static final Long SMECC = 12l;
		public static final Long ZO = 14l;
		public static final Long RO = 13l;
	}

	public static final class DenominationInAmount {
		private DenominationInAmount() {
			// Do nothing because of X and Y.
		}

		public static final Long LAKHS = 100000l;
		public static final Long MILLIONS = 1000000l;
		public static final Long CRORES = 10000000l;
		public static final Long BILLIONS = 100000000l;
		public static final Long ABSOLUTE = 1l;
	}

	public static final class OfflineApplicationConfig {
		private OfflineApplicationConfig() {
			// Do nothing because of X and Y.
		}

		public static final class BankSpecific {
			private BankSpecific() {
				// Do nothing because of X and Y.
			}

			public static final String ON = "1";
			public static final String OFF = "0";
		}

		public static final class MarketPlace {
			private MarketPlace() {
				// Do nothing because of X and Y.
			}

			public static final String ON = "1";
			public static final String OFF = "0";
		}
	}

	public enum AgriLoanStatus {
		WAITING(12l, "Waiting"), PENDING(11l, "Pending"), SUBMITTED(3l, "Submitted"); // Available in
		// fs_application_status_master
		private Long id;
		private String value;

		private AgriLoanStatus(Long id, String value) {
			this.id = id;
			this.value = value;
		}

		public Long getId() {
			return id;
		}

		public String getValue() {
			return value;
		}

		public static AgriLoanStatus fromValue(String v) {
			for (AgriLoanStatus c : AgriLoanStatus.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static AgriLoanStatus fromId(Long v) {
			for (AgriLoanStatus c : AgriLoanStatus.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v != null ? v.toString() : null);
		}
	}

	public static final class DenominationId {
		private DenominationId() {
			// Do nothing because of X and Y.
		}

		public static final Integer LAKHS = 1;
		public static final Integer MILLIONS = 2;
		public static final Integer CRORES = 3;
		public static final Integer BILLIONS = 4;
		public static final Integer ABSOLUTE = 5;
	}

	public static String getYesNo(Boolean value) {
		if (!isObjectNullOrEmpty(value)) {
			return value ? "Yes" : "No";
		}
		return "";
	}

	public static Date getDateByDateMonthYear(Integer date, Integer month, Integer year) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, date);
		calendar.set(Calendar.MONTH, (month - 1));
		calendar.set(Calendar.YEAR, year);
		return calendar.getTime();
	}

	public static Integer[] saperateDayMonthYearFromDate(Date date) {
		Integer[] result = new Integer[3];
		if (date == null) {
			return result;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		result[0] = calendar.get(Calendar.DAY_OF_MONTH);
		result[1] = calendar.get(Calendar.MONTH) + 1;
		result[2] = calendar.get(Calendar.YEAR);
		return result;
	}

	public enum LoanType {
		WORKING_CAPITAL(1, "Working Capital", "WC"), TERM_LOAN(2, "Term Loan", "TL"), HOME_LOAN(3, "Home Loan", "HL"),
		AUTO_LOAN(12, "Auto Loan", "AL"), PERSONAL_LOAN(7, "Personal Loan", "PL"),
		LAP_LOAN(13, "Loan Against Property", "LAP"), LAS_LOAN(14, "Loan Against Shares", "LAS"),
		UNSECURED_LOAN(15, "UnSecured Loan", "USL"), WCTL_LOAN(16, "Working Capital Term Loan", "wctl"),
		MFI(17, "Micro Finance Loan", "mfi"), AGRI(18, "Agriculture", "agri");
		private int value;
		private String name;
		private String code;

		private LoanType(int value, String name, String code) {
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

		public static LoanType getType(Integer x) {
			if (x == null) {
				return null;
			}

			switch (x) {
			case 1:
				return WORKING_CAPITAL;
			case 2:
				return TERM_LOAN;
			case 3:
				return HOME_LOAN;
			case 12:
				return AUTO_LOAN;
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
			case 18:
				return AGRI;
			default:
				return null;
			}
		}

		public static LoanType fromValue(int v) {
			for (LoanType c : LoanType.values()) {
				if (c.value == v) {
					return c;
				}
			}
			throw new IllegalArgumentException(Integer.toString(v));
		}
	}

	public static final class IgnorableCopy {
		private IgnorableCopy() {
			// Do nothing because of X and Y.
		}

		private static final String[] CORPORATE = { "userId", "productId", "name", "categoryCode", "isActive",
				"applicationId" };

		public static String[] getCORPORATE() {
			return CORPORATE;
		}

		private static final String[] AUDIT_FIELDS = { "createdBy", "modifiedBy", "createdDate", "modifiedDate",
				"isActive" };

		public static String[] getAuditFields() {
			return AUDIT_FIELDS;
		}

		private static final String[] AUDIT_FIELDS_WITH_ID = { "createdBy", "modifiedBy", "createdDate", "modifiedDate",
				"isActive", "id" };

		public static String[] getAuditFieldsWithId() {
			return AUDIT_FIELDS_WITH_ID;
		}

		public static final String ID = "id";

		private static final String[] FP_PRODUCT = { "userId", "productId" };

		public static String[] getFpProduct() {
			return FP_PRODUCT;
		}

		private static final String[] FP_PRODUCT_TEMP = { "userId", "isApproved", "isDeleted", "isCopied", "isEdit",
				"statusId", "jobId", "fpProductId", "id", "fpProductMappingId" };

		public static String[] getFpProductTemp() {
			return FP_PRODUCT_TEMP;
		}

		public static final String[] CORPORATE_PROFILE = { "id", "userId", "clientId", "applicationId", "panNo",
				"constitutionId", "establishmentMonth", "establishmentYear", "keyVericalFunding", "latitude",
				"longitude", "organisationName", "firstAddress", "websiteAddress", "landlineNo", "keyVerticalSector",
				"keyVerticalSubsector", "gstIn", "email" };

		public static String[] getCorporateProfile() {
			return CORPORATE_PROFILE;
		}

		private static final String[] CORPORATE_FINAL = { "aadhar", "secondAddress", "sameAs", "creditRatingId",
				"contLiabilityFyAmt", "contLiabilitySyAmt", "contLiabilityTyAmt", " contLiabilityYear", "notApplicable",
				"aboutUs", "id" };

		public static String[] getCorporateFinal() {
			return CORPORATE_FINAL;
		}

		public static final String[] RETAIL_PROFILE = { "titleId", "firstName", "middleName", "lastName", "pan",
				"aadharNumber", "monthlyIncome", "firstAddress", "secondAddress", "addressSameAs", "contactNo",
				"companyName", "employedWithId", "employedWithOther", "entityName", "industryTypeId",
				"industryTypeOther", "selfEmployedOccupationId", "selfEmployedOccupationOther", "landSize",
				"alliedActivityId", "userId", "nameAsPerAadharCard", "currentJobMonth", "currentJobYear",
				"previousJobMonth", "previousJobYear", "totalExperienceMonth", "totalExperienceYear",
				"monthlyLoanObligation", "previousEmployersAddress", "previousEmployersName", "annualTurnover",
				"businessStartDate", "patPreviousYear", "patCurrentYear", "depreciationPreviousYear",
				"depreciationCurrentYear", "remunerationPreviousYear", "remunerationCurrentYear",
				"highestQualification", "qualifyingYear", "institute", "residingYear", "residingMonth", "spouseName",
				"isSpouseEmployed" };

		public static String[] getRetailProfile() {
			return RETAIL_PROFILE;
		}

		private static final String[] NTB_FINAL_EXCLUSION = { "id", "userId", "clientId", "applicationId",
				"establishmentMonth", "establishmentYear", "groupName", "keyVericalFunding", "latitude", "longitude",
				"websiteAddress", "gstIn", "email", "keyVerticalSector", "keyVerticalSubsector", "aadhar",
				"creditRatingId", "contLiabilityFyAmt", "contLiabilitySyAmt", "contLiabilityTyAmt", "notApplicable",
				"msmeRegistrationNumber" };

		public static String[] getNtbFinalExclusion() {
			return NTB_FINAL_EXCLUSION;
		}

		public static final String[] RETAIL_FINAL = { "castId", "castOther", "religion", "religionOther", "birthPlace",
				"fatherName", "motherName", "noChildren", "noDependent", "highestQualificationOther", "residenceType",
				"annualRent", "noPartners", "birthDate", "currentDepartment", "currentDesignation", "currentIndustry",
				"employmentStatus", "interestRate", "nameOfEntity", "officeType", "ownershipType", "partnersName",
				"poaHolderName", "presentlyIrrigated", "rainFed", "repaymentCycle", "repaymentMode",
				"seasonalIrrigated", "shareholding", "totalLandOwned", "drivingLicenseExpiryDate",
				"drivingLicenseNumber", "unattended", "websiteAddress", "userId" };

		public static String[] getRetailFinal() {
			return RETAIL_FINAL;
		}

		private static final String[] RETAIL_FINAL_WITH_ID = { "castId", "castOther", "religion", "religionOther",
				"birthPlace", "fatherName", "motherName", "noChildren", "noDependent", "highestQualificationOther",
				"residenceType", "annualRent", "noPartners", "birthDate", "currentDepartment", "currentDesignation",
				"currentIndustry", "employmentStatus", "interestRate", "nameOfEntity", "officeType", "ownershipType",
				"partnersName", "poaHolderName", "presentlyIrrigated", "rainFed", "repaymentCycle", "repaymentMode",
				"seasonalIrrigated", "shareholding", "totalLandOwned", "drivingLicenseExpiryDate",
				"drivingLicenseNumber", "unattended", "websiteAddress", "userId", "id", "loanAmountRequired",
				"monthlyIncome" };

		public static String[] getRetailFinalWithId() {
			return RETAIL_FINAL_WITH_ID;
		}

		private static final String[] DIRECTOR_OBJ_EXCEPT_MAIN = { "isItrCompleted", "isCibilCompleted",
				"isBankStatementCompleted", "isOneFormCompleted", "applicationId", "dob", "din", "panNo",
				"directorsName", "totalExperience", "isActive", "pincode", "stateCode", "city", "mobile", "gender",
				"relationshipType", "firstName", "lastName", "middleName", "title", "shareholding", "aadhar",
				"maritalStatus", "noOfDependent", "residenceType", "residenceSinceMonth", "residenceSinceYear",
				"isFamilyMemberInBusiness", "employmentDetailRequest", "countryId", "premiseNumber", "streetName",
				"landmark" };

		public static String[] getDirectorObjExceptMain() {
			return DIRECTOR_OBJ_EXCEPT_MAIN;
		}

		private static final String[] PL_RETAIL_PROFILE = { "titleId", "firstName", "middleName", "lastName",
				"genderId", "pan", "aadharNumber", "mobile", "educationQualification", "statusId", "residenceType",
				"birthDate", "employmentType", "employmentWith", "centralGovId", "stateGovId", "psuId", "corporateId",
				"eduInstId", "nameOfEmployer", "employmentStatus", "currentJobMonth", "currentJobYear",
				"totalExperienceMonth", "totalExperienceYear", "keyVerticalFunding", "keyVerticalSector",
				"keyVerticalSubSector", "contactNo", "email", "id" };

		public static String[] getPlRetailProfile() {
			return PL_RETAIL_PROFILE;
		}

		private static final String[] PL_RETAIL_PRIMARY = { "loanAmountRequired", "loanPurpose", "tenureRequired",
				"repayment", "monthlyIncome" };

		public static String[] getPlRetailPrimary() {
			return PL_RETAIL_PRIMARY;
		}

		private static final String[] RETAIL_PRIMARY = { "loanAmountRequired", "loanPurpose", "tenureRequired",
				"repayment", "monthlyIncome", "createdBy", "createdDate", "isActive", "applicationId", "modifiedBy",
				"modifiedDate" };

		public static String[] getRetailPrimary() {
			return RETAIL_PRIMARY;
		}

		private static final String[] PL_RETAIL_FINAL = { "addressSameAs", "religion", "qualifyingYear", "noChildren",
				"fatherName", "motherName", "spouseName", "noDependent", "residingMonth", "residingYear", "nationality",
				"residentialStatus", "castId", "birthPlace", "disabilityType", "drivingLicenseNumber",
				"drivingLicenseExpiryDate", "passport", "passportValidity", "voterId", "residentialProofNo",
				"addressSameAs", "permanentAddress", "officeAddress", "officeNameOfOrg", "officeEmail",
				"previousJobYear", "previousJobMonth", "previousEmployersName", "previousEmployersAddress",
				"previousEmployersContact", "ddoWebsite", "ddoRemainingSerYrs", "ddoRemainingSerMonths",
				"ddoEmployeeNo", "ddoDesignation", "ddoDepartment", "ddoOrganizationType", "isApplicantFinalFilled" };

		public static String[] getPlRetailFinal() {
			return PL_RETAIL_FINAL;
		}

		private static final String[] RETAIL_PL_PROFILE = { "titleId", "firstName", "middleName", "lastName",
				"genderId", "pan", "aadharNumber", "mobile", "educationQualification", "statusId", "residenceType",
				"birthDate", "employmentType", "employmentWith", "centralGovId", "stateGovId", "psuId", "corporateId",
				"eduInstId", "nameOfEmployer", "employmentStatus", "currentJobMonth", "currentJobYear",
				"totalExperienceMonth", "totalExperienceYear", "keyVerticalFunding", "keyVerticalSector",
				"keyVerticalSubSector", "contactAddress", "contactNo", "email", "loanAmountRequired", "loanPurpose",
				"tenureRequired", "repayment", "monthlyIncome", "isApplicantDetailsFilled" };

		public static String[] getRetailPlProfile() {
			return RETAIL_PL_PROFILE;
		}
	}

	public static final class ApplicantType {
		private ApplicantType() {
			// Do nothing because of X and Y.
		}

		public static final int APPLICANT = 1;
		public static final int COAPPLICANT = 2;
		public static final int GARRANTOR = 3;

	}

	public static final class UserMainType {
		private UserMainType() {
			// Do nothing because of X and Y.
		}

		public static final int RETAIL = 1;
		public static final int CORPORATE = 2;
	}

	public static int getUserMainType(Integer productId) {
		if (isObjectNullOrEmpty(productId)) {
			return 0;
		}
		if (productId == 1 || productId == 2 || productId == 15 || productId == 16)
			return UserMainType.CORPORATE;
		else
			return UserMainType.RETAIL;
	}

	public static String getUserMainTypeName(Integer productId) {
		if (isObjectNullOrEmpty(productId)) {
			return "NA";
		}
		if (productId == 1 || productId == 2 || productId == 15 || productId == 16)
			return CORPORATE;
		else
			return RETAIL;
	}

	public static String getCorporateLoanType(Integer productId) {
		if (productId == 1 || productId == 2 || productId == 15 || productId == 16)
			return "DEBT";
		else
			return "EQUITY";
	}

	public static class MFIApplicationStatus {

		public static final Long MFI_OPEN = 9l;
		public static final Long MFI_PENDING = 10l;
		public static final Long MFI_Submitted = 14l;
		public static final Long MFI_Submitted_Sidbi = 12l;
		public static final Long MFI_Approve = 13l;
		public static final Long MFI_Ineligible = 6l;
		public static final Long MFI_Sanction = 5l;
		public static final Long MFI_Disbursed = 11l;
		public static final Long MFI_Rejected = 4l;
	}

	public enum ApplicationStatusMessage {

		IN_PROGRESS(1, "In Progress"), DDR_IN_PROGRESS(2, "Due Diligence in Progress"),
		DDR_APPROVED_BUT_NOT_SANCTIONED(3, "DDR Approved"), DISBURSED(4, "Disbursed"), HOLD(5, "On Hold"),
		REJECT(6, "Rejected"), SANCTIONED(7, "Sanctioned"), IN_ELIGIBLE(8, "In Eligible");

		private int id;
		private String value;

		ApplicationStatusMessage(int id, String value) {
			this.id = id;
			this.value = value;
		}

		public int getId() {
			return id;
		}

		public String getValue() {
			return value;
		}
	}

	public static final class ApplicationStatus {
		private ApplicationStatus() {
			// Do nothing because of X and Y.
		}

		public static final Long OPEN = 1l;
		public static final Long ASSIGNED = 2l;
		public static final Long SUBMITTED = 3l;
		public static final Long SUBMITTED_TO_APPROVER = 4l;
		public static final Long APPROVED = 5l;
		public static final Long REVERTED = 6l;
		public static final Long ASSIGNED_TO_CHECKER = 7l;
		public static final Long MFI_OPEN = 9l;
		public static final Long MFI_PENDING = 10l;
		public static final Long PENDING_DEALER = 15l;
		public static final Long DEALER_SUBMMITED = 16l;
		public static final Long LOAN_STATUS = 17l;
		public static final Long DISBURSED = 8l;

	}

	public static final class ParameterTypes {
		private ParameterTypes() {
			// Do nothing because of X and Y.
		}

		public static final Integer CURRENT_EMPLOYMENT = 1;
		public static final Integer RESIDENTIAL = 2;
		public static final Integer BORROWER_TYPE = 3;
		public static final Integer SALARY_MODE = 4;
		public static final Integer BORROWER_SALARY_ACCOUNT = 5;
		public static final Integer EMPLOYMENT_WITH = 6;
		public static final Integer SLEF_EMPLOYMENT_WITH = 7;
		public static final Integer DISTRICT = 8;
		public static final Integer CROP = 9;
		public static final Integer IRRIGATED_UNIRRIGATED = 10;
		public static final Integer REPAYMENT_MODE = 11;
		public static final Integer BUREAU_SCORE = 12;
	}

//	public static final class InEligibleProposalStatus {
//		private InEligibleProposalStatus() {
//		}
//
//		public static final Integer PENDING = 1;
//		public static final Integer SANCTIONED = 2;
//		public static final Integer DISBURED = 3;
//		public static final Integer DECLINE = 4;
//		public static final Integer SANCTIONED_BY_OTHER_BANK = 5;
//		public static final Integer SANCTIONED_BY_OTHER_BRANCH = 6;
//		public static final Integer OTHER_BRANCH = 7;
//		public static final Integer OTHER_BANK = 8;
//		public static final Integer ALREADY_ONLINE_IN_PRINCIPLE = 9;
//		public static final Integer DECLINE_AFTER_SANTIONED = 10;
//	}

	public static String getDdrStatusString(Integer ddrStatusId) {
		if (isObjectNullOrEmpty(ddrStatusId)) {
			return "NA";
		}
		switch (ddrStatusId) {
		case 1:
			return "Open";
		case 2:
			return "In Progress";
		case 3:
			return "Submitted";
		case 4:
			return "Submitted To Approver";
		case 5:
			return "Approved";
		case 6:
			return "Reverted";
		default:
			return "NA";
		}
	}

	public static final class DdrStatus {
		private DdrStatus() {
			// Do nothing because of X and Y.
		}

		public static final Long OPEN = 1l;
		public static final Long IN_PROGRESS = 2l;
		public static final Long SUBMITTED = 3l;
		public static final Long SUBMITTED_TO_APPROVER = 4l;
		public static final Long APPROVED = 5l;
		public static final Long REVERTED = 6l;
	}

	public static final class UserType {
		private UserType() {
			// Do nothing because of X and Y.
		}

		public static final int FUND_SEEKER = 1;
		public static final int FUND_PROVIDER = 2;
		public static final int SERVICE_PROVIDER = 3;
		public static final int NETWORK_PARTNER = 4;
	}

	public static final class mfiDataDisplayType {
		private mfiDataDisplayType() {
			// Do nothing because of X and Y.
		}

		public static final int MFI_APPLICANT_DETAILS_DETAILS_TYPE = 1;
		public static final int MFI_MATCHES_DISPLAY_TYPE = 2;
		public static final int MFI_SCORING_DISPLAY_TYPE = 3;
		public static final int MFI_ASSESSMENT_TYPE = 4;
	}

	public static final class UploadUserType {
		private UploadUserType() {
			// Do nothing because of X and Y.
		}

		/*public static final String UERT_TYPE_APPLICANT = "applicant";
		public static final String UERT_TYPE_CO_APPLICANT = "coApplicant";
		public static final String UERT_TYPE_GUARANTOR = "guarantor";
		public static final String UERT_TYPE_USER = "user";*/
	}

	public static final class EmployerConstitution {
		private EmployerConstitution() {
			// Do nothing because of X and Y.
		}

		public static final int PARTNERSHIP_PROPRIETORSHIP = 1;
		public static final int ANYOTHER = 2;
	}

	public static final class EmployementType {
		private EmployementType() {
			// Do nothing because of X and Y.
		}

		public static final int SALARIED = 1;
		public static final int BUSINESSMAN = 2;
	}

	public static final class ReceiptMode {
		private ReceiptMode() {
			// Do nothing because of X and Y.
		}

		public static final int CASH = 1;
		public static final int BANK = 2;
	}

	public static final class PropertyType {
		private PropertyType() {
			// Do nothing because of X and Y.
		}

		public static final int RESIDENTIAL = 1;
		public static final int COMMERCIAL = 2;
		public static final int INDUSTRIAL = 3;
		public static final int PLOT = 4;
	}

	public static String getStringDateFromDate(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.format(date);
		} else {
			return null;
		}
	}

	public static Integer getAgeFromBirthDate(Date date) {
		if (date != null) {
			Integer years = 0;
			Calendar birthDay = Calendar.getInstance();
			birthDay.setTime(date);
			Calendar today = Calendar.getInstance();
			today.setTime(new Date());

			Integer yearsInBetween = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
			Integer monthsDiff = 1;
			// monthsDiff = monthsDiff + today.get(Calendar.MONTH) - 12;
			monthsDiff = monthsDiff + today.get(Calendar.MONTH) - birthDay.get(Calendar.MONTH);
			Integer ageInMonths = yearsInBetween * 12 + monthsDiff;
			years = ageInMonths / 12;
			return years;
		} else {
			return null;
		}
	}

	public static Integer[] getExactAgeFromDate(Date birtDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(birtDate);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		LocalDate l1 = LocalDate.of(year, month, date);
		LocalDate now1 = LocalDate.now();
		Period diff1 = Period.between(l1, now1);
		return new Integer[] { diff1.getYears(), diff1.getMonths(), diff1.getDays() };
	}

	public static final class TabType {
		private TabType() {
			// Do nothing because of X and Y.
		}

		public static final int PROFILE = 1;
		public static final int PROFILE_CO_APPLICANT = 2;
		public static final int PROFILE_GUARANTOR = 3;
		public static final int PRIMARY_INFORMATION = 4;
		public static final int PRIMARY_UPLOAD = 5;
		public static final int FINAL_UPLOAD = 6;
		public static final int FINAL_DPR_UPLOAD = 7;
		public static final int FINAL_CO_APPLICANT = 8;
		public static final int FINAL_GUARANTOR = 9;
		public static final int FINAL_MCQ = 10;
		public static final int FINAL_INFORMATION = 11;
		public static final int CONNECTIONS = 12;
		public static final int MATCHES = 13;

	}

	public static Double getBowlCount(String count, Integer tabNumber) {
		if (!isObjectListNull(count) && count.equals("0")) {
			String[] split = count.split("\\|");
			if (split.length > 0) {
				if (!isObjectListNull(tabNumber)) {
					return !isObjectListNull(split[tabNumber]) ? Double.parseDouble(split[tabNumber]) : 0.0;
				} else {
					return !isObjectListNull(split[split.length - 1]) ? Double.parseDouble(split[split.length - 1])
							: 0.0;
				}
			}
		}
		return 0.0;
	}

	public static Double getTotalBowlCount(String profileCount, String primaryCount, String finalCount) {
		return getBowlCount(profileCount, null) + getBowlCount(primaryCount, null) + getBowlCount(finalCount, null);
	}

	public static final List<String> URLS_BRFORE_LOGIN = new ArrayList<String>(8);

	public static List<String> getUrlsBrforeLogin() {
		return URLS_BRFORE_LOGIN;
	}

	static {
		URLS_BRFORE_LOGIN.add("/loans/loan_application/getUsersRegisteredLoanDetails".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/loan_application/getLoanDetailsForAdminPanel".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/corporate_upload/downloadCMAAndCoCMAExcelFile/**".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/loan_application/save_payment_info_for_mobile".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/loan_application/mobile/successUrl".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/loan_application/getToken".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/loan_application/saveLoanDisbursementDetail".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/loan_application/saveLoanSanctionDetail".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/loan_application/saveLoanSanctionDisbursementDetailFromBank".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/ddr/getCustomerNameById".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/error".toLowerCase());
		URLS_BRFORE_LOGIN.add("/mca/error".toLowerCase());
		URLS_BRFORE_LOGIN.add("/loans/loan_application/getBsData".toLowerCase());
	}

	public static int calculateAge(Date dateOfBirth) {
		Calendar today = Calendar.getInstance();
		Calendar birthDate = Calendar.getInstance();
		birthDate.setTime(dateOfBirth);
		if (birthDate.after(today)) {
			throw new IllegalArgumentException("You don't exist yet");
		}
		int todayYear = today.get(Calendar.YEAR);
		int birthDateYear = birthDate.get(Calendar.YEAR);
		int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
		int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
		int todayMonth = today.get(Calendar.MONTH);
		int birthDateMonth = birthDate.get(Calendar.MONTH);
		int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
		int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
		int age = todayYear - birthDateYear;

		// If birth date is greater than todays date (after 2 days adjustment of
		// leap year) then decrement age one year
		if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)
				|| ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth))) {
			age--;

			// If birth date and todays date are of same month and birth day of
			// month is greater than todays day of month then decrement age
		}
		return age;
	}

	public static String calculateBusinessExperience(Date establishmentYear) {

		Calendar today = Calendar.getInstance();
		Calendar establishment = Calendar.getInstance();
		establishment.setTime(establishmentYear);

		int estYear = establishment.get(Calendar.YEAR);
		int estMonth = establishment.get(Calendar.MONTH);

		int todayYear = today.get(Calendar.YEAR);
		int todayMonth = today.get(Calendar.MONTH) + 1;

		int year = todayYear - estYear;
		int month = todayMonth - estMonth;

		return year + " Years " + month + " Months ";
	}

	public static String CurrencyFormat(String value) {

		Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		return format.format(new BigDecimal(value)).substring(4);

		/*
		 * Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new
		 * Locale("en", "in")); return format.format(new BigDecimal(value));
		 */

		/*
		 * NumberFormat nf = NumberFormat.getInstance(); return nf.format(new
		 * BigDecimal(new BigDecimal(value).toPlainString())) + " ";
		 */
	}

	public static String getLoanName(Integer x) {
		switch (x) {
		case 1:
			return "Working Capital";
		case 2:
			return "Term Loan";
		case 3:
			return "Home Loan";
		case 12:
			return "Car Loan";
		case 7:
			return "Personal Loan";
		case 13:
			return "Loan Against Property";
		case 14:
			return "Loan Against Securities & Shares";
		case 15:
			return "Unsecured Loan";
		default:
			return null;
		}
	}

	public static String getLoanNameForMail(Integer x) {
		switch (x) {
		case 1:
			return "Working Capital";
		case 2:
			return "Term";
		case 3:
			return "Home";
		case 12:
			return "Car";
		case 7:
			return "Personal";
		case 13:
			return "Loan Against Property";
		case 14:
			return "Loan Against Securities & Shares";
		case 15:
			return "Unsecured ";
		default:
			return null;
		}
	}

	public static LoanType getProductByLoanCode(String code) {
		code = code.toUpperCase();
		if ("WC".equalsIgnoreCase(code)) {
			return LoanType.WORKING_CAPITAL;
		} else if ("TL".equalsIgnoreCase(code)) {
			return LoanType.TERM_LOAN;
		} else if ("HL".equalsIgnoreCase(code)) {
			return LoanType.HOME_LOAN;
		} else if ("AL".equalsIgnoreCase(code)) {
			return LoanType.AUTO_LOAN;
		} else if ("PL".equalsIgnoreCase(code)) {
			return LoanType.PERSONAL_LOAN;
		} else if ("LAP".equalsIgnoreCase(code)) {
			return LoanType.LAP_LOAN;
		} else if ("LAS".equalsIgnoreCase(code)) {
			return LoanType.LAS_LOAN;
		} else if ("USL".equalsIgnoreCase(code)) {
			return LoanType.UNSECURED_LOAN;
		} else if ("WCTL".equalsIgnoreCase(code)) {
			return LoanType.WCTL_LOAN;
		} else {
			return null;
		}
	}

	public static Boolean isTermLoanLessThanLimit(Integer denomination, Double amount) {
		if (isObjectNullOrEmpty(denomination) || isObjectNullOrEmpty(amount)) {
			return false;
		}
		if (convertDenominationToValue(denomination, amount) < TL_LESS_TWO)
			return true;
		else
			return false;

	}

	private static Long convertDenominationToValue(Integer denomination, Double amount) {

		if (isObjectNullOrEmpty(denomination) || isObjectNullOrEmpty(amount)) {
			return null;
		}
		if (DenominationId.LAKHS.equals(denomination)) {
			return (long) (DenominationInAmount.LAKHS * amount);
		} else if (DenominationId.MILLIONS.equals(denomination)) {
			return (long) (DenominationInAmount.MILLIONS * amount);
		} else if (DenominationId.CRORES.equals(denomination)) {
			return (long) (DenominationInAmount.CRORES * amount);
		} else if (DenominationId.BILLIONS.equals(denomination)) {
			return (long) (DenominationInAmount.BILLIONS * amount);
		} else if (DenominationId.ABSOLUTE.equals(denomination)) {
			return (long) (DenominationInAmount.ABSOLUTE * amount);
		} else {
			return null;
		}
	}

	private static final String[] tensNames = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] numNames = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };
	private static final String[] specialNames = { "", " thousand", " million", " billion", " trillion", " quadrillion",
			" quintillion" };

	private static String convertLessThanOneThousand(int number) {
		String soFar;

		if (number % 100 < 20) {
			soFar = numNames[number % 100];
			number /= 100;
		} else {
			soFar = numNames[number % 10];
			number /= 10;

			soFar = tensNames[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0)
			return soFar;
		return numNames[number] + " hundred" + soFar;
	}

	public static String convertRupeesInWords(int number) {

		if (number == 0) {
			return "zero";
		}

		String prefix = "";

		if (number < 0) {
			number = -number;
			prefix = "negative";
		}

		String current = "";
		int place = 0;

		do {
			int n = number % 1000;
			if (n != 0) {
				String s = convertLessThanOneThousand(n);
				current = s + specialNames[place] + current;
			}
			place++;
			number /= 1000;
		} while (number > 0);

		return (prefix + current).trim();
	}

	public static BigDecimal convertInBigDecimal(Object obj) {

		if (!CommonUtils.isObjectNullOrEmpty(obj)) {
			if (obj instanceof String) {
				return new BigDecimal((String) obj.toString().replaceAll(",", ""));
			}
			if (obj instanceof Double) {
				return BigDecimal.valueOf((Double) obj);
			}
			if (obj instanceof Long) {
				return new BigDecimal((Long) obj);
			}
			if (obj instanceof Integer) {
				return new BigDecimal((Integer) obj);
			}
		}
		return new BigDecimal(0);

	}

	public static String amountInWords(long number) {
		// 0 to 999 999 999 999
		if (number == 0) {
			return "zero";
		}

		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		String snumber = df.format(number);

		// XXXnnnnnnnnn
		int billions = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnnnnn
		int millions = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXXnnn
		int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
		// nnnnnnnnnXXX
		int thousands = Integer.parseInt(snumber.substring(9, 12));

		String tradBillions;
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(billions) + " billion ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(billions) + " billion ";
		}
		String result = tradBillions;

		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(millions) + " million ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(millions) + " million ";
		}
		result = result + tradMillions;

		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "one thousand ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " thousand ";
		}
		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;

		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}

	public enum DDRFrames {
		AUTHORIZED_SIGN_DETAILS(1), CREDIT_CARD_DETAILS(2), CREDITORS_DETAILS(3), REGISTERED_OFFICE(4),
		OPERATING_OFFICE(5), OTHER_BANK_LOAN_DETAILS(6), REL_WITH_DBS_DETAILS(7), VEHICLES_OWNED_DETAILS(8);

		private int value;

		private DDRFrames(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static DDRFrames getType(Integer x) {
			switch (x) {
			case 1:
				return AUTHORIZED_SIGN_DETAILS;
			case 2:
				return CREDIT_CARD_DETAILS;
			case 3:
				return CREDIT_CARD_DETAILS;
			case 4:
				return REGISTERED_OFFICE;
			case 5:
				return OPERATING_OFFICE;
			case 6:
				return OTHER_BANK_LOAN_DETAILS;
			case 7:
				return REL_WITH_DBS_DETAILS;
			case 8:
				return VEHICLES_OWNED_DETAILS;
			default:
				return null;
			}
		}

	}

	public enum DDRFinancialSummaryFields {
		FIRST_TOTAL_SALES(1, "Total Sales"), INTEREST_COST(2, "Interest Cost"),
		PROFIT_BEFORE_TAX(3, "Profit Before Tax (PBT)"), PROFIT_AFTER_TAX(4, "Profit After Tax (PAT)"),
		NET_WORTH(5, "Net Worth"),
		ADJUSTED_NET_WORTH(6, "Adjusted NetWorth (Treating unsecured loan as quasi capital)**"),
		TOTAL_DEBT(7, "Total Debt"), SECURE_LOAN(8, "Secure Loan"), UNSECURE_LOAN(9, "Unsecured Loan"),
		UNSECURE_LOAN_FROM_FRIEND(10, "Unsecured Loan from Friends And Relatives treated ad Qausi"),
		CAPITAL(11, "Capital"), TOTAL_CURRENT_ASSET(12, "Total Current Asset"),
		TOTAL_CURRENT_LIABILITY(13, "Total Current Liabilities"), TOTAL_LIABILITY(14, "Total Liabilities (TOL)"),
		LEVERAGE(15, "Leverage (TOL/TNW)"), ADJUSTED_LEVERAGE(16, "Adjusted Leverage (TOL/Adjusted TNW)"),
		CAPITAL_EMPLOYED(17, "Capital Employed"), GEARING(18, "Gearing (Total Debt/TNW)"),
		ADJUSTED_GEARING(19, "Adjusted Gearing (Total Debt/Adjusted TNW)"), CURRENT_RATIO(20, "Current Ratio"),
		INVENTORY_TURNOVER(21, "Inventory Turnover(Days)"), LAST_TOTAL_SALES(22, "Total Sales"),
		WORKING_CAPITAL_CYCLE(23, "Working Capital Cycle(Days)");

		private int id;
		private String value;

		private DDRFinancialSummaryFields(int id, String value) {
			this.id = id;
			this.value = value;
		}

		public int getId() {
			return id;
		}

		public String getValue() {
			return value;
		}

		public static DDRFinancialSummaryFields[] getAll() {
			return DDRFinancialSummaryFields.values();
		}

	}

	public enum DDRFinancialSummaryToBeFields {
		// PER_OF_SALES_OF_ANCHORE_PRODUCT(1,"% of sales of Anchor Products"),
		// SALES_OF_ANCHOR_PODUCTS(2,"Sales of Anchor Products"),
		RECEIVAVLES_TURNOVER(3, "Receivables turnover (Days)"), CREDITORS_TURNOVER(4, "Creditors Turnover (Days)");

		private int id;
		private String value;

		private DDRFinancialSummaryToBeFields(int id, String value) {
			this.id = id;
			this.value = value;
		}

		public int getId() {
			return id;
		}

		public String getValue() {
			return value;
		}

		public static DDRFinancialSummaryToBeFields getType(Integer x) {
			switch (x) {
			// case 1:
			// return PER_OF_SALES_OF_ANCHORE_PRODUCT;
			// case 2:
			// return SALES_OF_ANCHOR_PODUCTS;
			case 3:
				return RECEIVAVLES_TURNOVER;
			case 4:
				return CREDITORS_TURNOVER;
			default:
				return null;
			}
		}

		public static DDRFinancialSummaryFields[] getAll() {
			return DDRFinancialSummaryFields.values();
		}

	}

	public static double checkDoubleNull(Double value) {
		return !isObjectNullOrEmpty(value) ? value : 0.0;
	}

	public static double checkDouble(Double value) {
		try {
			if (!isObjectNullOrEmpty(value)) {
				DecimalFormat decimalFormat1 = new DecimalFormat("0.00");
				return Double.valueOf(decimalFormat1.format(value));

			} else {
				return 0.0;
			}
		} catch (Exception e) {
			return 0.00;
		}
		// return isObjectNullOrEmpty(value) ? 0.0 : value;
	}

	public static final class PaymentMode {
		private PaymentMode() {
			// Do nothing because of X and Y.
		}

		public static final String ONLINE = "ONLINE";
		public static final String CHEQUE = "CHEQUE";
		public static final String CASH = "CASH";
	}

	public static String checkString(Double value) {
		try {
			DecimalFormat decimalFormat1 = new DecimalFormat("0.00");
			return decimalFormat1.format(value);
		} catch (Exception e) {
			return "0.00";
		}
	}

	public enum CampaignCodes {
		ALL1MSME(1, "ALL1MSME");

		private int id;
		private String value;

		private CampaignCodes(int id) {
			this.id = id;
		}

		private CampaignCodes(int id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public int getId() {
			return id;
		}

		public static CampaignCodes getType(Integer x) {
			switch (x) {
			case 1:
				return CampaignCodes.ALL1MSME;
			default:
				return null;
			}
		}

	}

	public enum APIFlags {

		ITR(1, "ITR"), CIBIL(2, "CIBIL"), BANK_STATEMENT(3, "BANK STATEMENT"), ONE_FORM(4, "ONE FORM"), GST(5, "GST");

		private Integer id;
		private String value;

		private APIFlags(Integer id) {
			this.id = id;
		}

		private APIFlags(Integer id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public int getId() {
			return id;
		}

		public static APIFlags fromValue(String v) {
			for (APIFlags c : APIFlags.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static APIFlags fromId(Integer v) {
			for (APIFlags c : APIFlags.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v.toString());

		}

	}

	public static Double addNumbers(Double... a) {
		Double sum = 0.0;
		if (!isObjectNullOrEmpty(a)) {
			for (Double b : a) {
				if (!isObjectNullOrEmpty(b))
					sum += b;
			}
		}
		return sum;
	}

	public static Double substractNumbers(Double a, Double b) {
		a = isObjectNullOrEmpty(a) ? 0.0 : a;
		b = isObjectNullOrEmpty(b) ? 0.0 : b;

		return a - b;
	}

	public static Double substractThreeNumbers(Double a, Double b, Double c) {
		a = isObjectNullOrEmpty(a) ? 0.0 : a;
		b = isObjectNullOrEmpty(b) ? 0.0 : b;
		c = isObjectNullOrEmpty(c) ? 0.0 : c;

		return a - b - c;
	}

	public static Double divideNumbers(Double a1, Double a2) {
		return !isObjectListNull(a1, a2) && a1 != 0 && a2 != 0 ? (a1 / a2) : 0.0;
	}

	public static Double multiplyNumbers(Double a1, Double a2) {
		return !isObjectListNull(a1, a2) ? (a1 * a2) : 0.0;
	}

	public static String getOrganizationName(Long x) {
		if (x == 1L) {
			return "UNION";
		} else if (x == 2L) {
			return "SARASWAT";
		} else if (x == 3L) {
			return "AXIS";
		} else if (x == 4L) {
			return "ICICI";
		} else if (x == 5L) {
			return "IDBI";
		} else if (x == 6L) {
			return "RBL";
		} else if (x == 7L) {
			return "Tata Capital";
		} else if (x == 8L) {
			return "IDFC";
		} else if (x == 9L) {
			return "Dena Bank";
		} else if (x == 10L) {
			return "SIDBI";
		} else if (x == 11L) {
			return "NHBS";
		} else if (x == 12L) {
			return "CANARA BANK";
		} else if (x == 13L) {
			return "Indian Bank";
		} else if (x == 14L) {
			return "BOI";
		} else if (x == 15L) {
			return "Vijaya Bank";
		} else if (x == 16L) {
			return "SBI";
		} else if (x == 17L) {
			return "BOB";
		} else if (x == 18L) {
			return "PNB";
		} else if (x == 19L) {
			return "UCO Bank";
		} else if (x == 20L) {
			return "PSB";
		} else if (x == 21L) {
			return "Oriental Bank of Commerce";
		} else if (x == 22L) {
			return "Syndicate Bank";
		} else if (x == 23L) {
			return "Allahabad bank";
		} else if (x == 24L) {
			return "Corporation Bank";
		} else if (x == 25L) {
			return "Central Bank";
		} else if (x == 26L) {
			return "Andhra Bank";
		} else if (x == 27L) {
			return "Bank of Maharashtra";
		} else if (x == 28L) {
			return "Indian Overseas Bank";
		} else if (x == 29L) {
			return "United Bank of India";
		} else if (x == 30L) {
			return "Kotak Bank";
		} else {
			return null;
		}
	}

	public static String decode(String encryptedString) {
		return new String(Base64.getDecoder().decode(encryptedString));
	}

	public static String getEncodedUserNamePassword(String userName, String password) {
		String keyToEncode = userName + ":" + password;
		return "Basic " + Base64.getEncoder().encodeToString(keyToEncode.getBytes());
	}

	public static String getCMAFilterYear(String year) {
		if (!isObjectNullOrEmpty(year)) {
			String[] split = year.split("\\.");
			if (split.length > 1) {
				return split[0];
			}
			return year;
		}
		return null;
	}

	public static final class Status {
		private Status() {
			// Do nothing because of X and Y.
		}

		public static final int OPEN = 1;
		public static final int IN_PROGRESS = 2;
		public static final int REVERTED = 3;
		public static final int APPROVED = 4;
		public static final int MODIFIED = 5;
	}

	/***********************************************
	 * CAM UTILS
	 *********************************************************/
	static DecimalFormat decimal = new DecimalFormat("#,##0.00");
	static DecimalFormat decimalForDouble = new DecimalFormat("###0.00");
	static DecimalFormat decim2 = new DecimalFormat("#,###");

	public static String convertValue(Double value) {
		NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
		formatter.setMinimumFractionDigits(2);
		formatter.setMaximumFractionDigits(2);
		return !CommonUtils.isObjectNullOrEmpty(value) ? formatter.format(value) : "0";
	}

	public static String convertValueWithoutDecimal(Double value) {
		NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
		formatter.setMaximumFractionDigits(0);
		return !CommonUtils.isObjectNullOrEmpty(value) ? formatter.format(value) : "0";
	}

	/* Return Round Value with CommaStyle */
	public static String convertValueRound(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value) ? decim2.format(Long.valueOf(Math.round(value))) : "0";
	}

	public static Object convertValueIndianCurrency(Object value) {
		if (value != null) {
			NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
			formatter.setMinimumFractionDigits(0);
			formatter.setMaximumFractionDigits(0);
			return formatter.format(value);
		} else {
			return 0;
		}

	}

	public static Double convertStringCurrencyToDouble(String value) {
		if (value != null) {
			NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
			try {
				Number num = formatter.parse(value);
				return num.doubleValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				logger.info("error==>" + e);
			}
			/* formatter.setMinimumFractionDigits(0); */

		}
		return 0d;
	}

	public static Object convertValueIndianCurrencyWithDecimal(Object value) {
		if (value != null) {
			NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
			formatter.setMinimumFractionDigits(2);
			formatter.setMaximumFractionDigits(2);
			return formatter.format(value);
		} else {
			return "-";
		}

	}

	public static Object convertStringFormate(Object value) {

//		Double a;
		try {
//			a = (Double) decimal.parse(value);

			return String.valueOf(convertValueIndianCurrency(decimal.parse(String.valueOf(value))));
		} catch (ParseException e) {
			logger.error(EXCEPTION, e);
		}
		return "0";
	}

	public static String formatValueWithoutDecimal(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value) ? decim2.format(value) : "0";
	}

	public static Object convertToDoubleForXml(Object obj, Map<String, Object> data) throws LoansException {
		try {
			if (obj == null) {
				return null;
			}
			DecimalFormat decim = new DecimalFormat("0.00");
			if (obj instanceof Double) {
				obj = Double.parseDouble(decim.format(obj));
				return obj;
			} else if (obj.getClass().getName().startsWith("com.opl")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					Object value = field.get(obj);
					if (data != null) {
						data.put(field.getName(), value);
					}
					if (!CommonUtils.isObjectNullOrEmpty(value) && value instanceof Double
							&& !Double.isNaN((Double) value)) {
						value = Double.parseDouble(decim.format(value));
						if (data != null) {
							value = decimal.format(value);
							data.put(field.getName(), value);
						} else {
							field.set(obj, value);
						}
					}
				}
			}
			if (data != null) {
				return data;
			}
			return obj;
		} catch (Exception e) {
			throw new LoansException(e);
		}

	}

	public static Object convertToValueForXml(Object obj, Map<String, Object> data) throws LoansException {
		try {
			if (obj == null) {
				return null;
			}
			DecimalFormat decim = new DecimalFormat("0.00");
			if (obj instanceof Double) {
				obj = Double.parseDouble(decim.format(obj));
				return obj;
			} else if (obj.getClass().getName().startsWith("com.opl")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					Object value = field.get(obj);
					if (data != null) {
						data.put(field.getName(), value);
					}
					if (!CommonUtils.isObjectNullOrEmpty(value) && value instanceof Double
							&& !Double.isNaN((Double) value)) {
						value = Double.parseDouble(decim.format(value));
						if (data != null) {
							value = decim2.format(value);
							data.put(field.getName(), value);
						} else {
							field.set(obj, value);
						}
					}
				}
			}
			if (data != null) {
				return data;
			}
			return obj;
		} catch (Exception e) {
			throw new LoansException(e);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object printFields(Object obj, Map<String, Object> data) throws Exception {
		if (obj != null) {
			if (obj.getClass().isArray()) {
				// Do nothing because of X and Y.
			}
		} else {
			return obj;
		}
		if (obj instanceof List) {
			List<?> lst = (List) obj;
			for (Object o : lst) {
				o = printFields(o, data);
			}
		} else if (obj instanceof Map) {
			Map<Object, Object> map = (Map) obj;
			for (Map.Entry<Object, Object> setEntry : map.entrySet()) {
				setEntry.setValue(printFields(setEntry.getValue(), data));
			}
		} else if (obj instanceof String) {
			obj = StringEscapeUtils.escapeXml(((String) obj).replaceAll("--", ""));
			return obj;
		} else if (obj instanceof Double) {
			if (!Double.isNaN((Double) obj)) {
				return convertToDoubleForXml(obj, null);
			}
		} else {
			if (obj.getClass().getName().startsWith("com.opl")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
						// Do nothing because of X and Y.
					} else {
						field.setAccessible(true);
						Object value = field.get(obj);
						field.set(obj, printFields(value, data));
					}
				}
			}
		}
		return obj;
	}

	public static Object convertToDoubleForXmlIndianCurr(Object obj, Map<String, Object> data) throws LoansException {
		try {
			if (obj == null) {
				return null;
			}
			DecimalFormat decim = new DecimalFormat("0.00");
			if (obj instanceof Double) {
				obj = Double.parseDouble(decim.format(obj));
				return obj;
			} else if (obj.getClass().getName().startsWith("com.opl")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					Object value = field.get(obj);
					if (data != null) {
						data.put(field.getName(), value);
					}
					if (!CommonUtils.isObjectNullOrEmpty(value) && value instanceof Double
							&& !Double.isNaN((Double) value)) {
						value = Double.parseDouble(decim.format(value));
						if (data != null) {
							value = convertValueIndianCurrencyWithDecimal(value);
							data.put(field.getName(), value);
						} else {
							field.set(obj, value);
						}
					}
				}
			}
			if (data != null) {
				return data;
			}
			return obj;
		} catch (Exception e) {
			throw new LoansException(e);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object printFieldsForValue(Object obj, Map<String, Object> data) throws Exception {
		if (obj != null) {
			if (obj.getClass().isArray()) {
				// Do nothing because of X and Y.
			}
		} else {
			return obj;
		}
		if (obj instanceof List) {
			List<?> lst = (List) obj;
			for (Object o : lst) {
				o = printFieldsForValue(o, data);
			}
		} else if (obj instanceof Map) {
			Map<Object, Object> map = (Map) obj;
			for (Map.Entry<Object, Object> setEntry : map.entrySet()) {
				setEntry.setValue(printFieldsForValue(setEntry.getValue(), data));
			}
		} else if (obj instanceof String) {
			obj = StringEscapeUtils.escapeXml(((String) obj).replaceAll("--", ""));
			return obj;
		} else if (obj instanceof Double) {
			if (!Double.isNaN((Double) obj)) {
				return convertValueWithoutDecimal((Double) obj);
			}
		} else {
			if (obj.getClass().getName().startsWith("com.opl")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
						// Do nothing because of X and Y.
					} else {
						field.setAccessible(true);
						Object value = field.get(obj);
						field.set(obj, printFieldsForValue(value, data));
					}
				}
			}
		}
		return obj;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object printFieldsDecimalForValue(Object obj, Map<String, Object> data) throws Exception {
		if (obj != null) {
			if (obj.getClass().isArray()) {
				// Do nothing because of X and Y.
			}
		} else {
			return obj;
		}
		if (obj instanceof List) {
			List<?> lst = (List) obj;
			for (Object o : lst) {
				o = printFieldsDecimalForValue(o, data);
			}
		} else if (obj instanceof Map) {
			Map<Object, Object> map = (Map) obj;
			for (Map.Entry<Object, Object> setEntry : map.entrySet()) {
				setEntry.setValue(printFieldsDecimalForValue(setEntry.getValue(), data));
			}
		} else if (obj instanceof String) {
			obj = StringEscapeUtils.escapeXml(((String) obj).replaceAll("--", ""));
			return obj;
		} else if (obj instanceof Double) {
			if (!Double.isNaN((Double) obj)) {
				return convertValueWithoutDecimal((Double) obj);
			}
		} else {
			if (obj.getClass().getName().startsWith("com.opl")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
						// Do nothing because of X and Y.
					} else {
						field.setAccessible(true);
						Object value = field.get(obj);
						field.set(obj, printFieldsForValue(value, data));
					}
				}
			}
		}
		return obj;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object printFieldsForDecimalValue(Object obj, Map<String, Object> data) throws Exception {
		if (obj != null) {
			if (obj.getClass().isArray()) {
				// Do nothing because of X and Y.
			}
		} else {
			return obj;
		}
		if (obj instanceof List) {
			List<?> lst = (List) obj;
			for (Object o : lst) {
				o = printFieldsForDecimalValue(o, data);
			}
		} else if (obj instanceof Map) {
			Map<Object, Object> map = (Map) obj;
			for (Map.Entry<Object, Object> setEntry : map.entrySet()) {
				setEntry.setValue(printFieldsForDecimalValue(setEntry.getValue(), data));
			}
		} else if (obj instanceof String) {
			obj = StringEscapeUtils.escapeXml(((String) obj).replaceAll("--", ""));
			return obj;
		} else if (obj instanceof Double) {
			if (!Double.isNaN((Double) obj)) {
				return convertValueIndianCurrencyWithDecimal((Double) obj);
			}
		} else {
			if (obj.getClass().getName().startsWith("com.opl")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
						// Do nothing because of X and Y.
					} else {
						field.setAccessible(true);
						Object value = field.get(obj);
						field.set(obj, printFieldsForDecimalValue(value, data));
					}
				}
			}
		}
		return obj;
	}

	public enum BankName {
		UNION_BANK_OF_INDIA(1, "Union Bank of India", ""), SARASWAT(2, "Saraswat", ""), AXIS(3, "Axis", ""),
		ICICI(4, "ICICI", ""), IDBI(5, "IDBI", "https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/IDBI.jpg"),
		RBL(6, "RBL", ""), TATA_CAPITAL(7, "Tata Capital", ""), IDFC(8, "IDFC", ""), DENA_BANK(9, "Dena Bank", ""),
		SIDBI(10, "SIDBI", "https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Sidbi.jpg"), NHBS(11, "NHBS", ""),
		CANARA_BANK(12, "CANARA BANK", "https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Canara-Bank.jpg"),
		INDIAN_BANK(13, "Indian Bank", "https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Indian-Bank.jpg"),
		BOI(14, "BOI", "https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/BOI.jpg"),
		VIJAYA_BANK(15, "Vijaya Bank", "https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/vijya.png"),
		SBI(16, "SBI", "https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/sbi.png"),
		BOB(17, "BOB", "https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/BOB.png"),
		PNB(18, "PNB", "https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/PNB.jpg"),
		UCO_BANK(19, "UCO Bank", ""), PSB(20, "PSB", ""),
		ORIENTAL_BANK_OF_COMMERCE(21, "Oriental Bank of Commerce", ""), SYNDICATE_BANK(22, "Syndicate Bank", ""),
		ALLAHABAD_BANK(23, "Allahabad bank", ""), CORPORATION_BANK(24, "Corporation Bank", ""),
		CENTRAL_BANK(25, "Central Bank", ""), ANDHRA_BANK(26, "Andhra Bank", ""),
		BANK_OF_MAHARASHTRA(27, "Bank of Maharashtra", ""), INDIAN_OVERSEAS_BANK(28, "Indian Overseas Bank", ""),
		UNITED_BANK_OF_INDIA(29, "United Bank of India", "");

		private Integer id;
		private String value;
		private String imageUrl;

		private BankName(Integer id) {
			this.id = id;
		}

		private BankName(Integer id, String value, String imageUrl) {
			this.id = id;
			this.value = value;
			this.imageUrl = imageUrl;
		}

		public String getValue() {
			return value;
		}

		public Integer getId() {
			return id;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public static BankName getDataFormBankId(Integer id) {
			for (BankName bankName : BankName.values()) {
				if (bankName.id.equals(id)) {
					return bankName;
				}
			}
			return null;
		}
	}

	public static Boolean convertBoolean(Object obj) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(obj)) {
				return (Boolean) obj;
			}
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
		return null;
	}

	public static Long convertLong(Object obj) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(obj)) {
				if (obj instanceof BigInteger) {
					BigInteger value = (BigInteger) obj;
					return value.longValue();
				} else {
					return (Long) obj;
				}
			}
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
		return null;
	}

	public static Integer convertInteger(Object obj) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(obj)) {
				if (obj instanceof BigInteger) {
					BigInteger value = (BigInteger) obj;
					return value.intValue();
				} else {
					return (Integer) obj;
				}
			}
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
		return null;
	}

	public static Date convertDate(Object obj) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(obj)) {
				return (Date) obj;
			}
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
		return null;
	}

	public static Double convertDouble(Object obj) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(obj)) {
				if (obj instanceof BigDecimal) {
					BigDecimal value = (BigDecimal) obj;
					return value.doubleValue();
				} else {
					return (Double) obj;
				}
			}
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
		return null;
	}

	public static String convertString(Object obj) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(obj)) {
				if (obj instanceof String) {
					return (String) obj;
				} else {
					return String.valueOf(obj);
				}
			}
		} catch (Exception e) {
			logger.error(EXCEPTION, e);
		}
		return null;
	}

	// commaReplace method teaser and final view...

	public static String commaReplace(String value) {

		if (value != null && !value.equals("") && value.charAt(value.length() - 1) != ',') {
			return value + ", ";
		}
		return value;
	}

	public static final class sanctionedFrom {
		private sanctionedFrom() {
			// Do nothing because of X and Y.
		}

		public static final long ELIGIBLE_USERS = 1;
		public static final long INELIGIBLE_USERS_OFFLINE_APPLICATION = 2;
		public static final long FROM_API = 3;
	}

	/**
	 * to get financial year by date
	 *
	 * @return String
	 * @author nilay.darji
	 */
	public static String getFinancialYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (calendar.get(Calendar.MONTH) < 3) {
			String s = calendar.get(Calendar.YEAR) - 1 + "-" + calendar.get(Calendar.YEAR);
			return s;
		} else if (calendar.get(Calendar.MONTH) >= 3) {
			String s = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.YEAR) + 1);
			return s;
		}
		return "-";
	}

	/**
	 * @author nilay.darji
	 * @param index of array
	 *
	 */
	public static String getMonthsByIndex(String index) {
		String getMonths[] = { null, "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };
		return getMonths[Integer.valueOf(index)];
	}

//	public static void main(String[] args) throws ParseException {
//		String strDate = "01/09/2009";
//		  Calendar c = Calendar.getInstance();
//		  c.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(strDate));
//		  int year = c.get(Calendar.YEAR);
//		  int month = c.get(Calendar.MONTH) + 1;
//		  int date = c.get(Calendar.DATE);
//		  System.out.println(year);
//		  System.out.println(month);
//		  System.out.println(date);
//		  LocalDate l1 = LocalDate.of(year, month, date);
//		  LocalDate now1 = LocalDate.now();
//		  System.out.println(now1);
//		  Period diff1 = Period.between(l1, now1);
//		  System.out.println("Years=====>"+diff1.getYears());
//		  System.out.println("Months=====>"+ diff1.getMonths());
//		  System.out.println("Days=====>"+diff1.getDays());
//		  System.out.println(((double)(diff1.getMonths() / 12.0d)));
//		  System.out.println(((double)diff1.getYears()) + ((double)(diff1.getMonths() / 12.0d)));
//
//	}

	public static Double convertTwoDecimalValuesIn(Double amount, Integer rate) {
		if (amount != null) {
//			Comment for not convert to AbsoluteValues
			amount = amount / rate;
			amount = convertTwoDecimal(amount);
		}
		return amount;
	}

	public static Double convertTwoDecimalAbsoluteValues(Double amount, Integer rate) {
		if (amount != null) {
//			Comment for not convert to AbsoluteValues
//			amount=amount*rate;

			amount = convertTwoDecimal(amount);
		}
		return amount;
	}

	public static Double convertTwoDecimal(Double amount) {
		if (amount != null) {
			DecimalFormat decim = new DecimalFormat("0.00");
			amount = Double.parseDouble(decim.format(amount));
		}
		return amount;
	}

	// get current date with custom format
	public static String getCurrentDate(String format) {
		String date = null;
		if (format != null && !format.equals("")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			date = dateFormat.format(new Date());
		}
		return date;
	}

	public static GeneralConfigData convertJSONToGeneralConfigDataRespo(String response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, GeneralConfigData.class);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @author nilay.darji
	 * @param score
	 * @return CIBIL Version 2 Score Range Return
	 *
	 */
	public static String getCibilV2ScoreRange(String score) {
		if (score.equals("-1") || score.equals("000-1") || score.equals("-1.0")) {
			return "-1";
		} else if (score.equals("0") || score.equals("1")) {
			return "1 - 5";
		} else if (isBetween(score, 300, 550)) {
			return "300 - 514";
		} else if (isBetween(score, 551, 600)) {
			return "515 - 520";
		} else if (isBetween(score, 601, 650)) {
			return "521 - 566";
		} else if (isBetween(score, 651, 700)) {
			return "567 - 618";
		} else if (isBetween(score, 701, 750)) {
			return "619 - 661";
		} else if (isBetween(score, 751, 800)) {
			return "662 - 697";
		} else if (isBetween(score, 801, 850)) {
			return "698 - 840";
		} else if (isBetween(score, 851, 900)) {
			return "841 - 900";
		} else {
			return null;
		}
	}

	public static boolean isBetween(String actualVal, int min, int max) {
		return min <= Double.valueOf(actualVal).intValue() && Double.valueOf(actualVal).intValue() <= max;
	}

	public enum SalaryPackageType {
		DSP(Integer.valueOf(1), "Defence Salary Package", "DSP"),
		PMSP(Integer.valueOf(2), "Para Military Salary Package", "PMSP"),
		ICGSP(Integer.valueOf(3), "Indian Coast Guard Salary Package", "ICGSP"),
		CGSP(Integer.valueOf(4), "Central Governent Salary Package", "CGSP"),
		SGSP(Integer.valueOf(5), "State Government Salary Package", "SGSP"),
		CSP(Integer.valueOf(6), "Corporate Salary Package", "CSP"),
		PSP(Integer.valueOf(7), "Police Salary Package", "PSP"),
		RSP(Integer.valueOf(8), "Railway Salary Package", "RSP");

		Integer type;
		String desc;
		String code;

		private SalaryPackageType(Integer type, String desc, String code) {
			this.type = type;
			this.desc = desc;
			this.code = code;
		}

		public Integer getType() {
			return this.type;
		}

		public String getDesc() {
			return this.desc;
		}

		public String getCode() {
			return this.code;
		}

		public static SalaryPackageType getById(Integer id) {
			switch (id.intValue()) {
			case 1:
				return DSP;
			case 2:
				return PMSP;
			case 3:
				return ICGSP;
			case 4:
				return CGSP;
			case 5:
				return SGSP;
			case 6:
				return CSP;
			case 7:
				return PSP;
			case 8:
				return RSP;
			default:
				return null;
			}
		}

		public static SalaryPackageType getByFullName(String fullAccName) {
			String[] nameParts = fullAccName.split("-");
			if (nameParts != null && nameParts.length > 0) {
				String[] var2 = nameParts;
				int var3 = nameParts.length;

				for (int var4 = 0; var4 < var3; ++var4) {
					String namePart = var2[var4];
					if (DSP.getCode().equals(namePart)) {
						return DSP;
					}

					if (PMSP.getCode().equals(namePart)) {
						return PMSP;
					}

					if (ICGSP.getCode().equals(namePart)) {
						return ICGSP;
					}

					if (CGSP.getCode().equals(namePart)) {
						return CGSP;
					}

					if (SGSP.getCode().equals(namePart)) {
						return SGSP;
					}

					if (CSP.getCode().equals(namePart)) {
						return CSP;
					}

					if (PSP.getCode().equals(namePart)) {
						return PSP;
					}

					if (RSP.getCode().equals(namePart)) {
						return RSP;
					}
				}
			}

			return null;
		}

		public static SalaryPackageType getByCode(String code) {
			if (DSP.getCode().equals(code)) {
				return DSP;
			} else if (PMSP.getCode().equals(code)) {
				return PMSP;
			} else if (ICGSP.getCode().equals(code)) {
				return ICGSP;
			} else if (CGSP.getCode().equals(code)) {
				return CGSP;
			} else if (SGSP.getCode().equals(code)) {
				return SGSP;
			} else if (CSP.getCode().equals(code)) {
				return CSP;
			} else if (PSP.getCode().equals(code)) {
				return PSP;
			} else if (RSP.getCode().equals(code)) {
				return RSP;
			} else {
				return null;
			}
		}
	}

	public static <T> T getObjectFromMap(Map map, Class<?> clazz) throws IOException {
		final ObjectMapper mapper = new ObjectMapper(); // jackson's
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return (T) mapper.convertValue(map, clazz);
	}

	public static final class ThirdPartyStatus {
		private ThirdPartyStatus() {
		}

		public static final String SUCCESS_STATUS = "S";
		public static final String FAILURE_STATUS = "F";
		public static final String PENDING_STATUS = "P";
		public static final String FAILURE_STATUS2 = "F2";
	}


	public interface BusinessTypeForItr{
		public static Integer MANUFACTURING = 1;
		public static Integer SERVICE = 2;
		public static Integer TRADING = 3;
	}

	public enum BusinessType {

		NEW_TO_BUSINESS(2, "New to Business"),
		EXISTING_BUSINESS(1, "Existing Business"),
		RETAIL_PERSONAL_LOAN(3, "Retail Personal Loan"),
		ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS(4, "One Pager Eligibility For Existing Business"),
		RETAIL_HOME_LOAN(5, "Retail Home Loan"),
		MFI(6, "Micro FInance Institute"),
		AGRICULTURE(7, "Agriculture"),
		RETAIL_AUTO_LOAN(8, "Retail Auto Loan"),
		CO_ORIGINATION(9, "Co Origination"),
		MUDRA_LOAN(10, "Mudra Loan"),
		DFS_LOAN(21, "Dfs Loan"),
		ODOP_LOAN(22, "Odop Loan"),
		VFS_LOAN(24, "VFS Loan"),
		CVL_MUDRA_LOAN(26, "CVL Mudra Loan");

		private Integer id;
		private String value;

		private BusinessType(Integer id) {
			this.id = id;
		}

		private BusinessType(Integer id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public Integer getId() {
			return id;
		}

		public static BusinessType fromValue(String v) {
			for (BusinessType c : values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static BusinessType fromId(Integer v) {
			for (BusinessType c : values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v.toString());

		}

	}


	public static final Integer VALUE = 1;
	public static final Integer DROPDOWN = 2;
	public static final Integer FORMULA = 3;

	public final class CommonText
	{
		private CommonText(){
			// Do nothing because of X and Y.
		}
		public static final String NO_LIMIT = "NO LIMIT";
		public static final String NEGATIVE_ELIGIBLITY = "NEGATIVE ELIGIBLITY";
		public static final String NO_LOAN_OBLIGATION = "NO LOAN OBLIGATION";
		public static final String NO_DEBT = "NO DEBT";
		public static final String NO_EBITDA = "NO EBITDA";
		public static final String NO_TURNOVER = "NO TURNOVER";
		public static final String ONE_YEAR_ITR = "ONE_YEAR_ITR";
		public static final String NO_ATNW = "NO ATNW";
		public static final String CMR_SCORE = "No CMR";


	}

	public static Double getPMTCalculationByLoanAmt(double roi, double tenure, double circularLoanAmount) {
		return ((roi) / (1 - Math.pow(1 + roi, - tenure)) * circularLoanAmount)*12;
	}
	
	//Inactive Check for payment
	public static final String IS_PAYMENT_DONE = "isPaymentDone";
	public static final String PRODUCT_INACTIVE = "productInActive";
	public static final String BRANCH_INACTIVE= "branchInActive";
	public static final String DELAY_STATUS = "delayStatus";
	
	
	public static final class GatewayProvider {
		private GatewayProvider() {
			// nothing to do.
		}
		public static final Long PAYUMONEY_PROVIDER = 1l;
		public static final Long BILLDESK_PROVIDER = 2l;
		public static final Long AGGREPAY_PROVIDER = 4l;
	}
	
	public static final class SkipType {
		private SkipType(){
			// nothing to do.
		}
		public static final String ONE_PAGER_ELIGIBILITY = "OnePagerEligibility";
		public static final String BANK_SPECIFIC = "BankSpecific";
		public static final String SBI_SPECIFIC = "SbiSpecific";
		public static final String SIDBI_SPECIFIC = "SidbiSpecific";
		public static final String MULTIBANK = "multibank";
		public static final String SAME_PAN_NO_AND_SAME_PRODUCT_ID = "SameProductIdSamePan";
		public static final String PERSONAL_LOAN = "PersonalLoan";
		public static final String HOME_LOAN = "HomeLoan";
		public static final String AUTO_LOAN = "AutoLoan";
		public static final String MUDRA_LOAN = "MudraLoan";
		public static final String PERSONAL_LOAN_BANK_SPECIFIC = "PersonalLoanBankSpecific";
		public static final String HOME_LOAN_BANK_SPECIFIC = "HomeLoanBankSpecific";
		public static final String AUTO_LOAN_BANK_SPECIFIC = "AutoLoanBankSpecific";
		public static final String MUDRA_LOAN_BANK_SPECIFIC = "MudraLoanBankSpecific";
		public static final String NBFC = "NBFC";
		public static final String DFS_LOAN = "DFSLoan";
		public static final String ODOP_LOAN = "ODOPLoan";
		public static final String VFS_LOAN = "VFSLoan";
	}
	public static enum PaymentTypeMaster {
		
		ONLINE_PAYMENT(1),
		SBI_SPECIFIC_SKIP_PAYMENT(2),
		SIDBI_SPECIFIC_SKIP_PAYMENT(3),
		SAME_PAN_NO_AND_SAME_PRODUCT_ID_SKIP_PAYMENT(4),
		MULTIBANK_SKIP_PAYMENT(5),
		PERSONAL_LOAN_SKIP_PAYMENT(6),
		HOME_LOAN_SKIP_PAYMENT(7),
		AUTO_LOAN_SKIP_PAYMENT(8),
		MUDRA_LOAN_SKIP_PAYMENT(9),
		ODOP_LOAN_SKIP_PAYMENT(10),
		DFS_LOAN_SKIP_PAYMENT(11),
		VFS_LOAN_SKIP_PAYMENT(12),
		CO_ORIGINATION_SKIP_PAYMENT(13),
		ONE_PAGER_SKIP_PAYMENT(14),
		BANK_SPECIFIC_SKIP_PAYMENT(15);
		
		private Integer id;

		private PaymentTypeMaster(Integer id) {
			this.id = id;
		}

		private PaymentTypeMaster(Integer id, String value) {
			this.id = id;
		}

		public Integer getId() {
			return id;
		}

		public static PaymentTypeMaster fromId(Integer v) {
			for (PaymentTypeMaster c : values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			return null;
		}
	}
	
	
}
