package com.capitaworld.service.loans.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
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

import com.capitaworld.service.loans.exceptions.LoansException;

public class CommonUtils {

	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	public static final String USER_ID = "userId";
	public static final String PROPOSAL_MAPPING_ID = "proposalMappingId";
	public static final String USER_TYPE = "userType";
	public static final String USER_ORG_ID = "userOrgId";
	public static final String BUSINESS_TYPE_ID = "businessTypeId";
	public static final String CITY_NAME = "cityName";
	public static final String STATE_NAME = "stateName";
	public static final String COUNTRY_NAME = "countryName";
	public static final int USER_TYPE_SERVICEPROVIDER = 3;
	public static final String INVALID_REQUEST = "Invalid Request !";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong !";
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

	public static final String HUNTER_INELIGIBLE_MESSAGE= "You do not Qualify for Contactless Process, Kindly visit Bank Branch or get your Due Diligence process completed in www.capitaworld.com to connect to Banks";

	public static final Long RETAIL_APPLICANT = 1L;
	public static final Long RETAIL_COAPPLICANT = 2L;
	public static final Long RETAIL_GUARANTOR = 3L;
	public static final Long CORPORATE_USER = 4L;
	public static final Long NP_NHBS = 11L;
	public static final Long CORPORATE_COAPPLICANT = 7L;
	public static final Long CW_SP_USER_ID = 101L;
	public static final Long TL_LESS_TWO = 20000000L;
	
	public static final String DDR_NOT_APPROVED= "DDR is not yet approved by Approver !";
	
	public static final String CW_CMA_EXCEL = "cw_cma.xlsx";
	public static final String CW_TL_WCTL_EXCEL="cw_cma_tl_wctl.xlsx";
	public static final String CO_CMA_EXCEL = "co_cma.xlsx";
   
	public static final String SCORING_EXCEL ="score_result.xlsx";

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
	public static final String INVALID_AGE = "Invalid Age";
	public static final String ONE_FORM_SAVED_SUCCESSFULLY = "Oneform Saved Successfully";
	public static final String SUCCESSFULLY_GET_DATA = "Successfully get data";

	public static final String EXCEPTION = " :: EXCEPTION : ";
	public static final String CLIENT_ID_IS_NOT_VALID = "Client Id is not valid";
	public static final String OBLIGATION_MUST_BE_LESS_THAN_INCOME = "Obligation Must be less than Income";
	public static final String REQUEST_DATA_NULL_OR_EMPTY = "Request Data Null Or Empty !!";
	public static final String INTERNAL_SERVER_ERROR  = "Internal Server Error";
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

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	public static final class PaymentStatus{
		public static final String SUCCESS = "Success";
		public static final String PENDING = "Pending";
		public static final String FAILED = "Failed";
		public static final String BYPASS = "ByPass";
	}
	
	/**
	 *  Date :- 04/06/2018
	 * @author harshit
	 *	USED FOR SAVE AND GET ONEFROM DETAILS ON FS RETAIL JOURNEY 
	 */
	public static final class RetailOneformType {
		public static final int CONTACT_INFO = 2;
		public static final int EMPLOYMENT_INFO = 3;
		public static final int CREDIT_INFO = 4;
		public static final int BASIC_INFO = 1;
	}

	public static final class UsersRoles {
		private UsersRoles(){
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
	}

	public static final class DenominationInAmount {
		private DenominationInAmount(){
			// Do nothing because of X and Y.
		}
		public static final Long LAKHS = 100000l;
		public static final Long MILLIONS = 1000000l;
		public static final Long CRORES = 10000000l;
		public static final Long BILLIONS = 100000000l;
		public static final Long ABSOLUTE = 1l;
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

	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static String getYesNo(Boolean value) {
		if (!isObjectNullOrEmpty(value)) {
			return value ? "Yes" : "No";
		}
		return "";
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
		WORKING_CAPITAL(1,"Working Capital","WC"), TERM_LOAN(2,"Term Loan","TL"), HOME_LOAN(3,"Home Loan","HL"), CAR_LOAN(12,"Car Loan","CL"), PERSONAL_LOAN(7,"Personal Loan","PL"), LAP_LOAN(13,"Loan Against Property","LAP"), LAS_LOAN(
				14,"Loan Against Shares","LAS"), UNSECURED_LOAN(15,"UnSecured Loan","USL"), WCTL_LOAN(16,"Working Capital Term Loan","wctl");
		private int value;
		private String name;
		private String code;

		private LoanType(int value,String name,String code) {
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

		public static LoanType getType(Integer x) {
			if(x == null) {
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
			default :
				return null;
			}
		}
//		public static String getLoanTypeName(Integer x) {
//			switch (x) {
//			case 1:
//				return "WORKING CAPITAL";
//			case 2:
//				return "TERM LOAN";
//			case 3:
//				return "HOME LOAN";
//			case 12:
//				return "CAR_LOAN";
//			case 7:
//				return "PERSONALLOAN";
//			case 13:
//				return "LAP LOAN";
//			case 14:
//				return "LAS LOAN";
//			case 15:
//				return "UNSECURED LOAN";
//			case 16:
//				return "WCTL_LOAN";
//			default :
//				return null;
//			}
//		}

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

		public static final String ID = "id";

		private static final String[] FP_PRODUCT = { "userId", "productId" };

		public static String[] getFpProduct() {
			return FP_PRODUCT;
		}

		private static final String[] FP_PRODUCT_TEMP = { "userId","isApproved","isDeleted","isCopied","isEdit","statusId","jobId","fpProductId","id","fpProductMappingId"};

		public static String[] getFpProductTemp() {
			return FP_PRODUCT_TEMP;
		}

		public static final String[] CORPORATE_PROFILE = {  "id","userId", "clientId", "applicationId","panNo","constitutionId","establishmentMonth",
			"establishmentYear","keyVericalFunding","latitude","longitude","organisationName","firstAddress",
			"websiteAddress","landlineNo","keyVerticalSector","keyVerticalSubsector","gstIn","email"
		};

		public static String[] getCorporateProfile() {
			return CORPORATE_PROFILE;
		}

		private static final String[] CORPORATE_FINAL = { "aadhar","secondAddress","sameAs","creditRatingId",
				"contLiabilityFyAmt","contLiabilitySyAmt" ,"contLiabilityTyAmt" ," contLiabilityYear","notApplicable","aboutUs","id"
		};

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

		private static final String[] NTB_FINAL_EXCLUSION = {"id","userId", "clientId", "applicationId","establishmentMonth","establishmentYear","groupName","keyVericalFunding"
				,"latitude","longitude","websiteAddress","gstIn","email","keyVerticalSector","keyVerticalSubsector","aadhar","creditRatingId"
				,"contLiabilityFyAmt","contLiabilitySyAmt" ,"contLiabilityTyAmt","notApplicable","msmeRegistrationNumber"} ;

		public static String[] getNtbFinalExclusion() {
			return NTB_FINAL_EXCLUSION;
		}

		public static final String[] RETAIL_FINAL = { "castId", "castOther", "religion", "religionOther", "birthPlace",
				"fatherName", "motherName", "noChildren", "noDependent", "highestQualificationOther", "residenceType",
				"annualRent", "noPartners", "birthDate", "currentDepartment", "currentDesignation", "currentIndustry",
				"employmentStatus", "interestRate", "nameOfEntity", "officeType", "ownershipType", "partnersName",
				"poaHolderName", "presentlyIrrigated", "rainFed", "repaymentCycle", "repaymentMode",
				"seasonalIrrigated", "shareholding", "totalLandOwned", "drivingLicenseExpiryDate", "drivingLicenseNumber",
				"unattended", "websiteAddress", "userId" };

		public static String[] getRetailFinal() {
			return RETAIL_FINAL;
		}

		private static final String[] RETAIL_FINAL_WITH_ID = { "castId", "castOther", "religion", "religionOther", "birthPlace",
				"fatherName", "motherName", "noChildren", "noDependent", "highestQualificationOther", "residenceType",
				"annualRent", "noPartners", "birthDate", "currentDepartment", "currentDesignation", "currentIndustry",
				"employmentStatus", "interestRate", "nameOfEntity", "officeType", "ownershipType", "partnersName",
				"poaHolderName", "presentlyIrrigated", "rainFed", "repaymentCycle", "repaymentMode",
				"seasonalIrrigated", "shareholding", "totalLandOwned", "drivingLicenseExpiryDate", "drivingLicenseNumber",
				"unattended", "websiteAddress", "userId" , "id"};

		public static String[] getRetailFinalWithId() {
			return RETAIL_FINAL_WITH_ID;
		}

		private static final String[] DIRECTOR_OBJ_EXCEPT_MAIN = {"isItrCompleted", "isCibilCompleted", "isBankStatementCompleted", "isOneFormCompleted",
				"applicationId","dob","din","panNo","directorsName","totalExperience", "isActive","pincode","stateCode","city","mobile","gender","relationshipType",
				"firstName","lastName", "middleName","title", "shareholding","aadhar","maritalStatus","noOfDependent","residenceType","residenceSinceMonth","residenceSinceYear",
				"isFamilyMemberInBusiness","employmentDetailRequest","countryId","premiseNumber","streetName","landmark"
		};

		public static String[] getDirectorObjExceptMain() {
			return DIRECTOR_OBJ_EXCEPT_MAIN;
		}

		private static final String[] PL_RETAIL_PROFILE = {"titleId", "firstName", "middleName", "lastName", "genderId", "pan", "aadharNumber",
				"mobile", "educationQualification", "statusId", "residenceType", "birthDate", "employmentType", "employmentWith", "centralGovId",
				"stateGovId", "psuId", "corporateId", "eduInstId", "nameOfEmployer", "employmentStatus", "currentJobMonth", "currentJobYear",
				"totalExperienceMonth", "totalExperienceYear", "keyVerticalFunding", "keyVerticalSector", "keyVerticalSubSector", "contactNo", "email","id" };

		public static String[] getPlRetailProfile() {
			return PL_RETAIL_PROFILE;
		}

		private static final String[] PL_RETAIL_PRIMARY = {"loanAmountRequired", "loanPurpose", "tenureRequired", "repayment", "monthlyIncome" };

		public static String[] getPlRetailPrimary() {
			return PL_RETAIL_PRIMARY;
		}
		
		private static final String[] RETAIL_PRIMARY = {"loanAmountRequired", "loanPurpose", "tenureRequired", "repayment", "monthlyIncome","createdBy","createdDate","isActive","applicationId","modifiedBy","modifiedDate"};

		public static String[] getRetailPrimary() {
			return PL_RETAIL_PRIMARY;
		}

		private static final String[] PL_RETAIL_FINAL = {"addressSameAs","religion","qualifyingYear","noChildren","fatherName","motherName","spouseName","noDependent",
				"residingMonth","residingYear","nationality","residentialStatus","castId","birthPlace","disabilityType","drivingLicenseNumber","drivingLicenseExpiryDate",
				"passport","passportValidity","voterId","residentialProofNo","addressSameAs","permanentAddress","officeAddress","officeNameOfOrg","officeEmail","previousJobYear",
				"previousJobMonth","previousEmployersName","previousEmployersAddress","previousEmployersContact","ddoWebsite","ddoRemainingSerYrs","ddoRemainingSerMonths","ddoEmployeeNo",
				"ddoDesignation","ddoDepartment","ddoOrganizationType","isApplicantFinalFilled"};

		public static String[] getPlRetailFinal() {
			return PL_RETAIL_FINAL;
		}

		private static final String[] RETAIL_PL_PROFILE = {
				"titleId", "firstName", "middleName", "lastName", "genderId", "pan", "aadharNumber", "mobile", "educationQualification",
				"statusId", "residenceType", "birthDate", "employmentType", "employmentWith", "centralGovId", "stateGovId", "psuId",
				"corporateId", "eduInstId", "nameOfEmployer", "employmentStatus", "currentJobMonth", "currentJobYear", "totalExperienceMonth",
				"totalExperienceYear", "keyVerticalFunding", "keyVerticalSector", "keyVerticalSubSector", "contactAddress", "contactNo", "email",
				"loanAmountRequired", "loanPurpose", "tenureRequired", "repayment", "monthlyIncome","isApplicantDetailsFilled" };

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

	public enum ApplicationStatusMessage {

		IN_PROGRESS(1,"In Progress"),
		DDR_IN_PROGRESS(2,"Due Diligence in Progress"),
		DDR_APPROVED_BUT_NOT_SANCTIONED(3,"DDR Approved"),
		DISBURSED(4,"Disbursed"),
		HOLD(5,"On Hold"),
		REJECT(6,"Rejected"),
		SANCTIONED(7,"Sanctioned"),
		IN_ELIGIBLE(8,"In Eligible");

		private int id;
		private String value;

		ApplicationStatusMessage(int id, String value)
		{
			this.id =id;
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
	}
	

	public static final class InEligibleProposalStatus {
		private InEligibleProposalStatus() {}
		public static final Integer PENDING = 1;
		public static final Integer SANCTIONED = 2;
        public static final Integer DISBURED = 3;
        public static final Integer DECLINE = 4;
        public static final Integer SANCTIONED_BY_OTHER_BANK = 5;
        public static final Integer SANCTIONED_BY_OTHER_BRANCH = 6;
        public static final Integer OTHER_BRANCH = 7;
        public static final Integer OTHER_BANK = 8;
        public static final Integer ALREADY_ONLINE_IN_PRINCIPLE = 9;
	}

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

	public static final class UploadUserType {
		private UploadUserType() {
			// Do nothing because of X and Y.
		}
		public static final String UERT_TYPE_APPLICANT = "applicant";
		public static final String UERT_TYPE_CO_APPLICANT = "coApplicant";
		public static final String UERT_TYPE_GUARANTOR = "guarantor";
		public static final String UERT_TYPE_USER = "user";
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
		private PropertyType () {
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
	
	public static Integer []  getExactAgeFromDate(Date birtDate) {
		  Calendar c = Calendar.getInstance();
		  c.setTime(birtDate);
		  int year = c.get(Calendar.YEAR);
		  int month = c.get(Calendar.MONTH) + 1;
		  int date = c.get(Calendar.DATE);
		  LocalDate l1 = LocalDate.of(year, month, date);
		  LocalDate now1 = LocalDate.now();
		  Period diff1 = Period.between(l1, now1);
		  return new Integer [] {diff1.getYears(),diff1.getMonths(),diff1.getDays()};
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

	public static boolean isObjectListNull(Object... args) {
		for (Object object : args) {
			boolean flag = false;
			if (object instanceof List) {
				flag = isListNullOrEmpty((List<?>) object);
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

	public static Double getBowlCount(String count, Integer tabNumber) {
		if (!isObjectListNull(count) && count != "0") {
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
		if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth) || ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth))) {
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

		Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
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
		} else if ("CL".equalsIgnoreCase(code)) {
			return LoanType.CAR_LOAN;
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
		if (denomination == DenominationId.LAKHS) {
			return (long) (DenominationInAmount.LAKHS * amount);
		} else if (denomination == DenominationId.MILLIONS) {
			return (long) (DenominationInAmount.MILLIONS * amount);
		} else if (denomination == DenominationId.CRORES) {
			return (long) (DenominationInAmount.CRORES * amount);
		} else if (denomination == DenominationId.BILLIONS) {
			return (long) (DenominationInAmount.BILLIONS * amount);
		} else if (denomination == DenominationId.ABSOLUTE) {
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
		AUTHORIZED_SIGN_DETAILS(1), CREDIT_CARD_DETAILS(2), CREDITORS_DETAILS(3), REGISTERED_OFFICE(
				4), OPERATING_OFFICE(5), OTHER_BANK_LOAN_DETAILS(6), REL_WITH_DBS_DETAILS(7), VEHICLES_OWNED_DETAILS(8);

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
		FIRST_TOTAL_SALES(1, "Total Sales"), INTEREST_COST(2, "Interest Cost"), PROFIT_BEFORE_TAX(3,
				"Profit Before Tax (PBT)"), PROFIT_AFTER_TAX(4, "Profit After Tax (PAT)"), NET_WORTH(5,
						"Net Worth"), ADJUSTED_NET_WORTH(6,
								"Adjusted NetWorth (Treating unsecured loan as quasi capital)**"), TOTAL_DEBT(7,
										"Total Debt"), SECURE_LOAN(8, "Secure Loan"), UNSECURE_LOAN(9,
												"Unsecured Loan"), UNSECURE_LOAN_FROM_FRIEND(10,
														"Unsecured Loan from Friends And Relatives treated ad Qausi"), CAPITAL(
																11, "Capital"), TOTAL_CURRENT_ASSET(12,
																		"Total Current Asset"), TOTAL_CURRENT_LIABILITY(
																				13,
																				"Total Current Liabilities"), TOTAL_LIABILITY(
																						14,
																						"Total Liabilities (TOL)"), LEVERAGE(
																								15,
																								"Leverage (TOL/TNW)"), ADJUSTED_LEVERAGE(
																										16,
																										"Adjusted Leverage (TOL/Adjusted TNW)"), CAPITAL_EMPLOYED(
																												17,
																												"Capital Employed"), GEARING(
																														18,
																														"Gearing (Total Debt/TNW)"), ADJUSTED_GEARING(
																																19,
																																"Adjusted Gearing (Total Debt/Adjusted TNW)"), CURRENT_RATIO(
																																		20,
																																		"Current Ratio"), INVENTORY_TURNOVER(
																																				21,
																																				"Inventory Turnover(Days)"), LAST_TOTAL_SALES(
																																						22,
																																						"Total Sales"), WORKING_CAPITAL_CYCLE(
																																								23,
																																								"Working Capital Cycle(Days)");

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
		private PaymentMode () {
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
	
	public enum BusinessType {
		
		NEW_TO_BUSINESS(2, "New to Business"),
		EXISTING_BUSINESS(1, "Existing Business"),
		RETAIL_PERSONAL_LOAN(3, "Retail Personal Loan"),
		ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS(4, "One Pager Eligibility For Existing Business"),
		RETAIL_HOME_LOAN(5, "Retail Home Loan");

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
			for (BusinessType c : BusinessType.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static BusinessType fromId(Integer v) {
			for (BusinessType c : BusinessType.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v.toString());

		}

	}
	
public enum APIFlags {
		
		ITR(1, "ITR"),CIBIL(2, "CIBIL"),BANK_STATEMENT(3, "BANK STATEMENT"),ONE_FORM(4, "ONE FORM"),GST(5, "GST");

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
	
	public static Double addNumbers(Double... a){
		Double sum = 0.0;
		if(!isObjectNullOrEmpty(a)) {
			for(Double b : a){
				if(!isObjectNullOrEmpty(b))
					sum += b;
			}
		}
		return sum;
	}
	
	public static Double substractNumbers(Double a, Double b){
		a= isObjectNullOrEmpty(a) ? 0.0 : a;
		b= isObjectNullOrEmpty(b) ? 0.0 : b;
		
		return a-b;
	}
	
	public static Double substractThreeNumbers(Double a, Double b, Double c){
		a= isObjectNullOrEmpty(a) ? 0.0 : a;
		b= isObjectNullOrEmpty(b) ? 0.0 : b;
		c= isObjectNullOrEmpty(c) ? 0.0 : c;
		
		return a-b-c;
	}
	public static Double divideNumbers(Double a1,Double a2) {
		return !isObjectListNull(a1,a2) && a1 != 0 && a2 != 0 ? (a1 / a2) : 0.0;
	}
	public static Double multiplyNumbers(Double a1,Double a2) {
		return !isObjectListNull(a1,a2) ? (a1 * a2) : 0.0;
	}
	public static String getOrganizationName(Long x) {
		if(x == 1L) {
			return "UNION";
		}else if(x == 2L) {
			return "SARASWAT";
		}else if(x == 3L) {
			return "AXIS";
		}else if(x == 4L) {
			return "ICICI";
		}else if(x == 5L) {
			return "IDBI";
		}else if(x == 6L) {
			return "RBL";
		}else if(x == 7L) {
			return "Tata Capital";
		}else if(x == 8L) {
			return "IDFC";
		}else if(x == 9L) {
			return "Dena Bank";
		}else if(x == 10L) {
			return "SIDBI";
		}else if(x == 11L) {
			return "NHBS";
		}else if(x == 12L) {
			return "CANARA BANK";
		}else if(x == 13L) {
			return "Indian Bank";
		}else if(x == 14L) {
			return "BOI";
		}else if(x == 15L) {
			return "Vijaya Bank";
		}else if(x == 16L) {
			return "SBI";
		}else if(x == 17L) {
			return "BOB";
		}else if(x == 18L) {
			return "PNB";
		}else if(x == 19L) {
			return "UCO Bank";
		}else if(x == 20L) {
			return "PSB";
		}else if(x == 21L) {
			return "Oriental Bank of Commerce";
		}else if(x == 22L) {
			return "Syndicate Bank";
		}else if(x == 23L) {
			return "Allahabad bank";
		}else if(x == 24L) {
			return "Corporation Bank";
		}else if(x == 25L) {
			return "Central Bank";
		}else if(x == 26L) {
			return "Andhra Bank";
		}else if(x == 27L) {
			return "Bank of Maharashtra";
		}else if(x == 28L) {
			return "Indian Overseas Bank";
		}else if(x == 29L) {
			return "United Bank of India";
		}else if(x == 30L) {
			return "Kotak Bank";
		}
		else {
			return null;
		}
	}
	public static String decode(String encryptedString) {
		return new String(Base64.getDecoder().decode(encryptedString));
	}
	
	public static String getEncodedUserNamePassword(String userName,String password) {
		String keyToEncode = userName + ":" + password;
		return  "Basic " + Base64.getEncoder().encodeToString(keyToEncode.getBytes());
	}
	
	public static String getCMAFilterYear(String year) {
		if(!isObjectNullOrEmpty(year)) {
			String[] split = year.split("\\.");
			if(split.length > 1) {
				return split[0]; 
			}
			return year;
		}
		return null;
	}
	
	public static final class Status {
		private Status () {
			// Do nothing because of X and Y.
		}
		public static final int OPEN = 1;
		public static final int IN_PROGRESS = 2;
		public static final int REVERTED = 3;
		public static final int APPROVED = 4;
		public static final int MODIFIED = 5;
	}
	
	
	/***********************************************CAM UTILS*********************************************************/
	static DecimalFormat decimal = new DecimalFormat("#,##0.00");
	static DecimalFormat decim2 = new DecimalFormat("#,###");
	
	public static String convertValue(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value)? decimal.format(value) : "0";
	}
	public static String convertValueWithoutDecimal(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value)? decim2.format(value) : "0";
	}
	/*Return Round Value with CommaStyle*/
	public static String convertValueRound(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value)? decim2.format(Long.valueOf(Math.round(value)))  : "0";
	}

	public static String formatValueWithoutDecimal(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value)? decim2.format(value)  : "0";
	}
	public static Object convertToDoubleForXml(Object obj, Map<String, Object>data) throws LoansException {
		try {
			if(obj ==  null) {
				return null;
			}
			DecimalFormat decim = new DecimalFormat("0.00");
			if(obj instanceof Double) {
				obj = Double.parseDouble(decim.format(obj));
				return obj;
			}else if(obj.getClass().getName().startsWith("com.capitaworld")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for(Field field : fields) {
					field.setAccessible(true);
					Object value = field.get(obj);
					if(data != null) {
						data.put(field.getName(), value);
					}
					if(!CommonUtils.isObjectNullOrEmpty(value) && value instanceof Double && !Double.isNaN((Double)value)) {
						value = Double.parseDouble(decim.format(value));
						if(data != null) {
							value = decimal.format(value);
							data.put(field.getName(), value);
						}else {
							field.set(obj,value);
						}
					}
				}
			}
			if(data != null) {
				return data;
			}
			return obj;
		}
		catch (Exception e){
			throw new LoansException(e);
		}

	}
	public static Object convertToValueForXml(Object obj, Map<String, Object>data) throws LoansException {
		try {
			if(obj ==  null) {
				return null;
			}
			DecimalFormat decim = new DecimalFormat("0.00");
			if(obj instanceof Double) {
				obj = Double.parseDouble(decim.format(obj));
				return obj;
			}else if(obj.getClass().getName().startsWith("com.capitaworld")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for(Field field : fields) {
					field.setAccessible(true);
					Object value = field.get(obj);
					if(data != null) {
						data.put(field.getName(), value);
					}
					if(!CommonUtils.isObjectNullOrEmpty(value) && value instanceof Double && !Double.isNaN((Double)value)) {
						value = Double.parseDouble(decim.format(value));
						if(data != null) {
							value = decim2.format(value);
							data.put(field.getName(), value);
						}else {
							field.set(obj,value);
						}
					}
				}
			}
			if(data != null) {
				return data;
			}
			return obj;
		}
		catch (Exception e){
			throw new LoansException(e);
		}

	}
	public static Object printFields(Object obj, Map<String, Object>data) throws Exception {
		if(obj != null) {
			if(obj.getClass().isArray()) {
				// Do nothing because of X and Y.
		}
		}else {
			return obj;
		}
		if(obj instanceof List) {
			List<?> lst = (List)obj;
			for(Object o : lst) {
				o = printFields(o,data);
			}
		}else if(obj instanceof Map) {
			Map<Object, Object> map = (Map)obj;
			for(Map.Entry<Object, Object> setEntry : map.entrySet()) {
				setEntry.setValue(printFields(setEntry.getValue(),data));
			}
		}else if(obj instanceof String) {
			obj = StringEscapeUtils.escapeXml(((String)obj).replaceAll("--", ""));
			return obj;
		}else if(obj instanceof Double) {
			if(!Double.isNaN((Double)obj)) {
				return convertToDoubleForXml(obj, null);
			}
		}else {
			if(obj.getClass().getName().startsWith("com.capitaworld")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					if((field.getModifiers()& Modifier.STATIC) == Modifier.STATIC){
						// Do nothing because of X and Y.
					}else {
						field.setAccessible(true);
						Object value = field.get(obj);
						field.set(obj, printFields(value,data));	
					}
				}
			}
		}
		 return obj;
	}
	
	public enum BankName {
		UNION_BANK_OF_INDIA(1,"Union Bank of India",""),
		SARASWAT(2,"Saraswat",""),
		AXIS(3,"Axis",""),
		ICICI(4,"ICICI",""),
		IDBI(5,"IDBI","https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/IDBI.jpg"),
		RBL(6,"RBL",""),
		TATA_CAPITAL(7,"Tata Capital",""),
		IDFC(8,"IDFC",""),
		DENA_BANK(9,"Dena Bank",""),
		SIDBI(10,"SIDBI","https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Sidbi.jpg"),
		NHBS(11,"NHBS",""),
		CANARA_BANK(12,"CANARA BANK","https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Canara-Bank.jpg"),
		INDIAN_BANK(13,"Indian Bank","https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Indian-Bank.jpg"),
		BOI(14,"BOI","https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/BOI.jpg"),
		VIJAYA_BANK(15,"Vijaya Bank","https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/vijya.png"),
		SBI(16,"SBI","https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/sbi.png"),
		BOB(17,"BOB","https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/BOB.png"),
		PNB(18,"PNB","https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/PNB.jpg"),
		UCO_BANK(19,"UCO Bank",""),
		PSB(20,"PSB",""),
		ORIENTAL_BANK_OF_COMMERCE(21,"Oriental Bank of Commerce",""),
		SYNDICATE_BANK(22,"Syndicate Bank",""),
		ALLAHABAD_BANK(23,"Allahabad bank",""),
		CORPORATION_BANK(24,"Corporation Bank",""),
		CENTRAL_BANK(25,"Central Bank",""),
		ANDHRA_BANK(26,"Andhra Bank",""),
		BANK_OF_MAHARASHTRA(27,"Bank of Maharashtra",""),
		INDIAN_OVERSEAS_BANK(28,"Indian Overseas Bank",""),
		UNITED_BANK_OF_INDIA(29,"United Bank of India","");

		private Integer id;
		private String value;
		private String imageUrl;

		private BankName(Integer id) {
			this.id = id;
		}

		private BankName(Integer id, String value,String imageUrl) {
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

		public static BankName getDataFormBankId(Integer id){
			for (BankName bankName:BankName.values()) {
				if (bankName.id == id) {
					return bankName;
				}
			}
			return null;
		}
	}
	
	public static Boolean convertBoolean(Object obj){
    	try {
    		if(!CommonUtils.isObjectNullOrEmpty(obj)) {
    			return (Boolean) obj;
    		}	
		} catch (Exception e) {
			logger.error(EXCEPTION,e);
		}
		return null;
	}
    
    public static Long convertLong(Object obj){
    	try {
    		if(!CommonUtils.isObjectNullOrEmpty(obj)) {
    			if(obj instanceof BigInteger) {
    				BigInteger value =  (BigInteger) obj;
        			return value.longValue();
    			} else {
    				return (Long) obj;
    			}
    		}	
		} catch (Exception e) {
			logger.error(EXCEPTION,e);
		}
		return null;
	}
    
    public static Integer convertInteger(Object obj){
    	try {
    		if(!CommonUtils.isObjectNullOrEmpty(obj)) {
    			if(obj instanceof BigInteger) {
    				BigInteger value =  (BigInteger) obj;
        			return value.intValue();
    			} else {
    				return (Integer) obj;
    			}
    		}	
		} catch (Exception e) {
			logger.error(EXCEPTION,e);
		}
		return null;
	}
    
    public static Date convertDate(Object obj){
    	try {
    		if(!CommonUtils.isObjectNullOrEmpty(obj)) {
    			return (Date) obj;
    		}	
		} catch (Exception e) {
			logger.error(EXCEPTION,e);
		}
		return null;
	}
    
    public static Double convertDouble(Object obj){
    	try {
    		if(!CommonUtils.isObjectNullOrEmpty(obj)) {
    			if(obj instanceof BigDecimal) {
    				BigDecimal value = (BigDecimal) obj;
        			return value.doubleValue();	
    			} else {
    				return (Double) obj;
    			}
    		}	
		} catch (Exception e) {
			logger.error(EXCEPTION,e);
		}
		return null;
	}
    
    public static String convertString(Object obj){
    	try {
    		if(!CommonUtils.isObjectNullOrEmpty(obj)) {
    			if(obj instanceof String) {
    				return  (String) obj;
    			} else {
    				return String.valueOf(obj);
    			}
    		}	
		} catch (Exception e) {
			logger.error(EXCEPTION,e);
		}
		return null;
	}
	
	// commaReplace method teaser and final view...
	
	public static String commaReplace(String value) {

		if(value != null && !value.equals("") && value.charAt(value.length()-1) != ',') {
			return value+", ";
		}
		return value;
	}
	public static final class sanctionedFrom {
		private sanctionedFrom () {
			// Do nothing because of X and Y.
		}
		public static final long ELIGIBLE_USERS = 1;
		public static final long INELIGIBLE_USERS_OFFLINE_APPLICATION = 2;
		public static final long FROM_API = 3;
	}

	/**
	 * to get financial year by date
	 * @return String
	 * @author nilay.darji
	 */
	public static String getFinancialYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if(calendar.get(Calendar.MONTH)<3) {
			String s=calendar.get(Calendar.YEAR)-1 +"-" +calendar.get(Calendar.YEAR);
			return s;
		}else if (calendar.get(Calendar.MONTH)>=3) {
			String s=calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.YEAR) + 1 );
			return s;
		}
		return "-";
	}
	
}
