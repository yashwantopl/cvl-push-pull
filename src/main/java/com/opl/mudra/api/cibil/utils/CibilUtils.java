package com.opl.mudra.api.cibil.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class CibilUtils {
	private  static final Logger logger = LoggerFactory.getLogger(CibilUtils.class);
	public static final String CIBIL_SCORE_VERSION_2 = "CibilScoreVersion2";
	public static final Long SIDBI_ORD_ID = 10L;
	public static final Long CAPITAWORLD_ORD_ID = 31L;
	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
	public static final int USER_TYPE_SERVICEPROVIDER = 3;
	public static final String INVALID_REQUEST = "Invalid Request !";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong !";
	public static final String ORG_CONFIG_EMPTY = "You can not proceed further as your bureau check can not perform, Please contact your Bank.";
	public static final String SIDBI_CONFIG_EMPTY = "You can not proceed further as your bureau check can not perform, Please contact support.";
	public static final String ORG_CONFIG_ERROR = "Something went wrong !, Please contact support.";
	public static final String CIBIL_SERVICE_NOT_AVAILABLE = "Cibil Service is not available.";
	public static final String EQUIFAX_SERVICE_NOT_AVAILABLE = "Equifax Service is not available.";
	public static final String ENTITY_MANAGER_REFERENCE = "cibilDataStoreEM";
	public static final String TRANSACTION_MANAGER_REFERENCE = "cibilDataStoreTM";
	public static final String DATASTORE = "cibilDataStore";
	public static final String FAILURE_MSG = "Data filled does not match with CIBIL database.Kindly Refill the data you have entered to get your CIBIL report";
	public static final String FAILURE_KEY = "FAILURE";
	public static final String STAGE_KEY = "STAGE";
	public static final String SERVICE_ERROR_KEY = "SERVICE_ERROR";
	public static final String STATUS_KEY = "STATUS";
	public static final String CLIENT_KEY_PREFIX = "CW-CIBIL-";
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final String SERVER_ERROR = "Internal Server Error. Please try again after sometime.!!";
	public static final String CIBIL_SERVER_ERROR = "SOMETHING GOES WRONG WHILE PROCESSING YOUR REQUEST AT CIBIL END.";
	public static final String INSUFFICIANT_DATA_ERROR = "Data not sufficiant for bureau report. Please Enter Correct Details.";
	private static final String [] SOFT_PING_MANDATORY_VALUES = {"SurName","ForName","Pan","PhoneNumber","DateOfBirth","City","State","Pincode","Gender","Email","Premise No","Street Name","Land Mark"};
	public static final String LITERAL_INDIVIDUAL = "Individual";
	public static final String LITERAL_NON_FUND_BASED_LIMITS  = "Non-Fund based limits";
	public static final String LITERAL_PROPERTY_LOAN = "Property Loan";
	public static final String LITERAL_TERM_LOAN = "Term Loan";
	public static final String DIRECTOR = "DIRECTOR";
	public static final String LITERAL_OTHER = "Other";
	public static final String LITERAL_OTHERS = "Others";
	public static final String LITERAL_GOLD_LOAN = "Gold Loan";
	public static final String LITERAL_OVERDRAFT = "Overdraft";
	public static final String WORKING_CAPITAL = "Working Capital";
	public static final String EXCEPTION = "Exception :: {}";
	public static final String POST_ONEFORM_BASIC_DETAILS = "postOneFormBasicDetails : {}";
	public static final String POST_CIBIL_AUTHENTICATION = "postCibilAuthentication : {}";
	
	public static final String LITERAL_AGRICULTURE_AND_FORESTRY = "AGRICULTURE AND FORESTRY";
	public static final String LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES = "OTHER COMMUNITY, SOCIAL AND PERSONAL SERVICE ACTIVITIES";
	public static final String LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS = "WHOLESALE AND RETAIL TRADE; REPAIR OF MOTOR VEHICLES, MOTORCYCLES AND PERSONAL/HOUSEHOLD GOODS";
	public static final String LITERAL_MINING_AND_QUARRYING = "MINING AND QUARRYING";
	public static final String LITERAL_FISHING = "FISHING";
	public static final String LITERAL_MANUFACTURING = "MANUFACTURING";
	public static final String LITERAL_ELECTRICITY_GAS_AND_WATER_SUPPLY = "ELECTRICITY, GAS AND WATER SUPPLY";
	public static final String LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS = "TRANSPORT, STORAGE AND COMMUNICATIONS";
	public static final String LITERAL_FINANCIAL_INTERMEDIATION = "FINANCIAL INTERMEDIATION";
	public static final String LITERAL_REAL_ESTATE_RENTING_AND_BUSINESS_ACTIVITIES = "REAL ESTATE, RENTING AND BUSINESS ACTIVITIES";
	public static final String LITERAL_HEALTH_AND_SOCIAL_WORK = "HEALTH AND SOCIAL WORK";
	public static final String LITERAL_HOTELS_AND_RESTAURANTS = "HOTELS AND RESTAURANTS";
	public static final String LITERAL_CONSTRUCTION = "CONSTRUCTION";
	private static final List<String> CREDIT_CARD_CODES = Arrays.asList("10", "31", "36", "35");
	private static final List<String> EXPERIAN_RETAIL_CREDIT_CARD_CODES = Arrays.asList("10", "31", "36", "35");
	private static final List<String> CIBIL_RETAIL_LOANS_CODES_FOR_EMI_CALCULATION = Arrays.asList("01", "02", "05", "06", "13", "32", "17"); // As discussed with Shivangi Sharda
	private static final List<String> EXPERIAN_RETAIL_LOANS_CODES_FOR_EMI_CALCULATION = Arrays.asList("1", "2", "5", "6", "13", "32", "17");
	private static final List<String> EQUIFAX_RETAIL_LOANS_CODES_FOR_EMI_CALCULATION = Arrays.asList("Auto Loan", "Two-wheeler Loan", "Commercial Vehicle Loan", "Personal Loan", "Consumer Loan", "Housing Loan"); 
	private static final List<String> HIGHMARK_RETAIL_LOANS_CODES_FOR_EMI_CALCULATION = Arrays.asList("Auto Loan (Personal)", "Two-wheeler Loan", "Commercial Vehicle Loan", "Personal Loan", "Consumer Loan", "Housing Loan"); 
	private static final List<Integer> EXPERIAN_CREDIT_CARD_CODES = Arrays.asList(13,16,17,18,19,213,214,220,224,20,3,4,5,6,7);
	private static final List<String> HIGHMARK_CREDIT_CARD_CODES = Arrays.asList("Credit Card");
	public static final String EXPERIAN_SERVICE_NOT_AVAILABLE = "Experian service is not available.";
	public static final String HIGHMARK_SERVICE_NOT_AVAILABLE = "Highmark service is not available.";
	public static final String EXPERIAN_FAILURE_MSG = "Data filled does not match with EXPERIAN database. Kindly Refill the data you have entered to get your EXPERIAN report";
	public static final String SUCCESS = "Success";
	public static final String SSL = "SSL";
	public static final String CIBIL_LOCKED_MSG = "Your account has been blocked by Bureau. Kindly send request email to support@psbloansin59minutes.com if you want the same to be unblocked. You can also mention your comments / inputs if any in the said mail.";
	
	public static final String SOMETHING_WENT_WRONG_ERROR = "Something went wrong while processing your requst. Please try again.";
	
	public interface APIType {
//		public static final int TEST_API = 2;
		public static final int GENERATE_TOKEN = 10;
		public static final int GET_BUREAU_REPORT = 20;
		public static final int GET_MATCHES_RESULT = 30;
		public static final int GET_SCORING_RESULT = 40;
	}
	
	public interface Provider {
		public static final Integer CIBIL = 1;
		public static final Integer EXPERIAN = 2;
		public static final Integer HIGHMARK = 3;
		public static final Integer EQUIFAX = 4;
	}

	public interface CallType {
		public static final Integer DIRECT_HIT = 1;
		public static final Integer UPLOAD = 2;
	}
	
	public interface ReportPrefixName {
		public static final String CIBIL_MSME = "CibilCommercialReport_";
		public static final String CIBIL_INDIVIDUAL = "CibilIndividualReport_";
		public static final String EXPERIAN_MSME = "ExperianCommercialReport_";
		public static final String EXPERIAN_INDIVIDUAL = "ExperianIndividualReport_";
		public static final String HIGHMARK_INDIVIDUAL = "HighmarkIndividualReport_";
		public static final String HIGHMARK_MSME = "HighmarkCommercialReport_";
		
		public static final String CIBIL_SOFTPING = "CibilSoftpingReport_";
	}
	
	public interface HighmarkResponseType {
		public static final String ACKNOWLEDGEMENT = "ACKNOWLEDGEMENT";
		public static final String ERROR = "ERROR";
	}
	
	public interface HighmarkResponseKey {
		public static final String REPORT_ID = "reportId";
		public static final String STATUS = "status";
		public static final String REDIRECT_URL = "redirectURL";
		public static final String STATUS_DESC = "statusDesc";
	}
	
	public enum HighmarkStatusCode {
		STATUS_401("401", "Authentication failure - Crif Highmark"), // Stage 1.2,3
		S00("S00", "Any Transaction Error in inquiry"), // Stage 1
		S01("S01", "User Authentication Successful"),  // Stage 2
		S02("S02", "User Authentication Failure"), // Stage 2
		S03("S03", "User Cancelled the Transaction"), // Stage 2
		S04("S04", "Authorization for the report ID: not complete"), // Stage 3
		S05("S05", "User Validations Failure"), // Stage 1,2
		S06("S06", "Request is accepted by Bureau"), // Stage 1
		S07("S07", "Error in request format"), // Stage 1
		S08("S08", "Technical Error"), // Stage 1,2,3
		S09("S09", "No hit"), // Stage 2
		S10("S10", "Auto Authentication – Confident match from Bureau"), // Stage 2
		S11("S11", "Authentication Questionnaire phase"); // Stage 2

		private String code;
		private String description;

		private HighmarkStatusCode(String code, String description) {
			this.code = code;
			this.description = description;
		}

		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}
		
		public static String getDescription(String v) {
			for (HighmarkStatusCode c : HighmarkStatusCode.values()) {
				if (c.code.equals(v)) {
					return c.getDescription();
				}
			}
			return CibilUtils.SOMETHING_WENT_WRONG;
		}
	}

	public static List<String> getCreditCardCodes() {
		return CREDIT_CARD_CODES;
	}
	
	public static List<String> getCibilRetailCodesForEmiCalculation() {
		return CIBIL_RETAIL_LOANS_CODES_FOR_EMI_CALCULATION;
	}
	
	public static List<String> getExperianRetailCodesForEmiCalculation() {
		return EXPERIAN_RETAIL_LOANS_CODES_FOR_EMI_CALCULATION;
	}
	
	public static List<String> getEquifaxRetailCodesForEmiCalculation() {
		return EQUIFAX_RETAIL_LOANS_CODES_FOR_EMI_CALCULATION;
	}
	
	public static List<String> getHighmarkRetailCodesForEmiCalculation() {
		return HIGHMARK_RETAIL_LOANS_CODES_FOR_EMI_CALCULATION;
	}
	
	public static List<Integer> getExperianCreditCardCodes() {
		return EXPERIAN_CREDIT_CARD_CODES;
	}
	
	public static List<String> getExperianRetailCreditCardCodes() {
		return EXPERIAN_RETAIL_CREDIT_CARD_CODES;
	}
	
	public static List<String> getHigmarkCreditCardCodes() {
		return HIGHMARK_CREDIT_CARD_CODES;
	}
	
	private static final String DATE_FORMATE = "dd/MM/yyyy";
	public static final String MSME_DATE_FORMATE = "ddMMYYYY";

	public static String[] getSoftPingMandatoryValues() {
		return SOFT_PING_MANDATORY_VALUES;
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
						? (((String) value).isEmpty() || "".equals(((String) value).trim())
								|| "null".equalsIgnoreCase((String) value) || "undefined".equals((String) value)
								|| "{}".equals((String) value) || "[]".equals((String) value))
						: false));
	}

	public static String getStringDateFromDate(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATE);
			return dateFormat.format(date);
		} else {
			return null;
		}
	}

	public static String getStringDateFromDate(Date date, String formate) {
		if (date != null) {
			if (isObjectNullOrEmpty(formate)) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATE);
				return dateFormat.format(date);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
			return dateFormat.format(date);
		} else {
			return null;
		}
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

	public static final class UserType {
		private UserType(){ /* Do nothing because of X and Y.*/ }

		public static final int FUND_SEEKER = 1;
		public static final int FUND_PROVIDER = 2;
		public static final int SERVICE_PROVIDER = 3;
		public static final int NETWORK_PARTNER = 4;
	}

	public static final class Gender {
		private Gender(){ /* Do nothing because of X and Y.*/ }

		public static final Integer MALE = 1;
		public static final Integer FEMALE = 2;
		public static final Integer TRANSGENDER = 3;
	}

	public static final class IdentificationType {
		private IdentificationType(){ /* Do nothing because of X and Y.*/ }

		public static final Integer PAN = 1;
		public static final Integer ADHAAR = 2;
	}

	public static final class ContentType {
		private ContentType(){ /* Do nothing because of X and Y.*/ }

		public static final String XML = "XML";
		public static final String JSON = "JSON";
		public static final String FILE = "FILE";
	}

	public static final class CibilStage {
		private CibilStage(){ /* Do nothing because of X and Y.*/ }

		public static final String FULFILL_OFFER = "FULFILL_OFFER";
		public static final String AUTHENTICATE_QUESTION = "AUTHENTICATE_QUESTION";
		public static final String VERIFY_ANSWER = "VERIFY_ANSWER";
		public static final String CUSTOMER_ASSET = "CUSTOMER_ASSET";
		public static final String CREDIT_SCORE = "CREDIT_SCORE";
		public static final String EQUIFAX_FAILED = "EQUIFAX_FAILED";
		public static final String EXPERIAN_FAILED = "EXPERIAN_FAILED";
		public static final String HIGHMARK_FAILED = "HIGHMARK_FAILED";
		public static final String HIGHMARK_INITIATE = "HIGHMARK_INITIATE";
		public static final String HIGHMARK_AUTHENTICATION = "HIGHMARK_AUTHENTICATION";
		public static final String FINISHED = "FINISHED";
		public static final String FAILED = "FAILED";
		public static final String BUREAU_CONFIGURATION = "BUREAU_CONFIGURATION";
		public static final String MSME_INDIVIDUAL = "MSME_INDIVIDUAL";
		public static final String MSME_INDIVIDUAL_ISSUE_REQUEST = "MSME_INDIVIDUAL_ISSUE_REQUEST";
		public static final String MSME_INDIVIDUAL_RETRIVE_DOCUMENT_ID = "MSME_INDIVIDUAL_RETRIVE_DOCUMENT_ID";
		public static final String MSME_INDIVIDUAL_DOWNLOAD_DOCUMENT = "MSME_INDIVIDUAL_DOWNLOAD_DOCUMENT";
		public static final String MSME_COMPANY = "MSME_COMPANY";
		public static final String MSME_COMPANY_ISSUE_REQUEST = "MSME_COMPANY_ISSUE_REQUEST";
		public static final String MFI = "MFI";
		public static final String MFI_DPD = "MFI_DPD";
		public static final String MFI_DOWNLOAD_DOCUMENT = "MFI_DOWNLOAD_DOCUMENT";
		public static final String MSME_HUF = "MSME_HUF";
		public static final String MSME_COMPANY_DPD = "MSME_COMPANY_DPD";
		public static final String MSME_INDIVIDUAL_DPD = "MSME_INDIVIDUAL_DPD";
		public static final String MSME_NILL_DPD_YEARS = "MSME_NILL_DPD_YEARS";
//		public static final String INDIVIDUAL_DPD_SOFT_PING_COAPPLICANT = "INDIVIDUAL_DPD_SOFT_PING_COAPPLICANT";
		//public static final String INDIVIDUAL_SOFT_PING = "INDIVIDUAL_SOFT_PING";
		public static final String INDIVIDUAL_DPD_SOFT_PING = "INDIVIDUAL_DPD_SOFT";
		public static final String MSME_DIRECTOR_AVERAGE_SCORE = "MSME_DIRECTOR_AVERAGE_SCORE";
		public static final String MSME_DIRECTOR_SCORE = "MSME_DIRECTOR_SCORE";
		public static final String MSME_FIRM = "MSME_FIRM";
		public static final String MSME_GOVERMENT = "MSME_GOVERMENT";
		public static final String MSME_PUBLIC_LIMITED = "MSME_PUBLIC_LIMITED";
		public static final String MSME_TRUST = "MSME_TRUST";
		public static final String MSME_ASSOCIATION_OF_PERSONS = "MSME_ASSOCIATION_OF_PERSONS";
		public static final String MSME_PROPRIETORSHIP = "MSME_PROPRIETORSHIP";
		public static final String MSME_HINDU_UNDIVIDED_FAMILY = "MSME_HINDU_UNDIVIDED_FAMILY";
		public static final String MSME_NOT_CLASSIFIED = "MSME_NOT_CLASSIFIED";
	}

	public static final class QueName {
		private QueName(){ /* Do nothing because of X and Y.*/ }

		public static final String OTP_IDM_EMAIL_QUEUE = "OTP_IDM_Email_Queue";
		public static final String OTP_IDM_MOBILE_QUEUE = "OTP_IDM_Mobile_Queue";
		public static final String OTP_ALTERNATEEMAIL_ENTRY_QUEUE = "OTP_AlternateEmail_Entry_Queue";
		public static final String OTP_IDM_ALTERNATEEMAIL_QUEUE = "OTP_IDM_AlternateEmail_Queue";
		public static final String OTP_ACCOUNTDETAILS_QUEUE = "OTP_AccountDetails_Queue";
		public static final String IDM_KBA_QUEUE = "IDM_KBA_Queue";
	}

	public static Integer getGender(String gender) {
		if (isObjectNullOrEmpty(gender)) {
			return null;
		}
		if ("female".equalsIgnoreCase(gender)) {
			return Gender.FEMALE;
		} else if ("male".equalsIgnoreCase(gender)) {
			return Gender.MALE;
		} else if ("transgender".equalsIgnoreCase(gender)) {
			return Gender.TRANSGENDER;
		} else {
			logger.info("Invalid Gender==>");
			return null;
		}
	}

	public static String generateRequestKey(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (new Random().nextInt() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public enum IdentityTypeEnum {
		PAN_CARD("01", "Pan Card"), PASSPORT("02", "Passport"), VOTER_ID("03", "Voter Id"), DRIVING_LICENCE("04",
				"Driving license"), RATION_CARD("05", "Ration Card"), UID("06", "UID");

		private String id;
		private String value;

		private IdentityTypeEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static IdentityTypeEnum fromValue(String v) {
			for (IdentityTypeEnum c : IdentityTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static IdentityTypeEnum fromId(String v) {
			for (IdentityTypeEnum c : IdentityTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

	}

	public enum TelephoneTypeEnum {
		NOT_CLASSIFIED("00", "Not Classified","NA"), MOBILE_PHONE("01", "Mobile Phone","MOBILE"), HOME_PHONE("02",
				"Home Phone","HOME"), OFFICE_PHONE("03", "Office Phone","OFFICE");

		private String id;
		private String value;
		private String code;

		private TelephoneTypeEnum(String id, String value, String code) {
			this.id = id;
			this.value = value;
			this.code = code;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}
		
		public String getCode() {
			return this.code;
		}

		public static TelephoneTypeEnum fromValue(String v) {
			for (TelephoneTypeEnum c : TelephoneTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static TelephoneTypeEnum fromId(String v) {
			for (TelephoneTypeEnum c : TelephoneTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
		public static TelephoneTypeEnum fromCode(String v) {
			for (TelephoneTypeEnum c : TelephoneTypeEnum.values()) {
				if (c.code.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}

	public enum AddressTypeEnum {
		PERMANENT_ADDRESS("01", "Permanent Address"), RESIDENCE_ADDRESS("02", "Residence Address"), OFFICE_ADDRESS("03",
				"Office Address"), NOT_CATEGORIZED("04", "Not Categorized"), REGISTERED_OFFICE("05", "REGISTERED OFFICE"),
		BRANCH_REGIONAL_OFFICE("06", "BRANCH / REGIONAL OFFICE"),WAREHOUSE("07", "WAREHOUSE"),PLANT_FACTORY_ADDRESS("07", "PLANT / FACTORY ADDRESS"),
		OTHERS("08", "OTHERS");

		private String id;
		private String value;

		private AddressTypeEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static AddressTypeEnum fromValue(String v) {
			for (AddressTypeEnum c : AddressTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static AddressTypeEnum fromId(String v) {
			for (AddressTypeEnum c : AddressTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}
	
	public enum AddressTypeEnumExperian {
		CURRENT_RESIDENCE_ADDRESS("2", "Current Residence Address"), PERMANENT_ADDRESS("12", "Permanent Address"), OFFICE_ADDRESS("13",
				"Office Address"), NOT_CATEGORIZED("04", "Not Categorized"),OTHERS("99", LITERAL_OTHER);

		private String id;
		private String value;

		private AddressTypeEnumExperian(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static AddressTypeEnumExperian fromValue(String v) {
			for (AddressTypeEnumExperian c : AddressTypeEnumExperian.values()) {
				if (c.value.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static AddressTypeEnumExperian fromId(String v) {
			for (AddressTypeEnumExperian c : AddressTypeEnumExperian.values()) {
				if (c.id.equalsIgnoreCase(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}

	public enum APIStatusTypeEnum {
		SUCCESS("01", "SUCCESS"), FAILED("02", CibilStage.FAILED), INTERNAL_FAILED("03", "INTERNAL_FAILED");

		private String id;
		private String value;

		private APIStatusTypeEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static APIStatusTypeEnum fromValue(String v) {
			for (APIStatusTypeEnum c : APIStatusTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static APIStatusTypeEnum fromId(String v) {
			for (APIStatusTypeEnum c : APIStatusTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}
	
	public enum ResidenceTypeEnum {
		OWNED("01", "Owned"), RENTED("02", "Rented");

		private String id;
		private String value;

		private ResidenceTypeEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static ResidenceTypeEnum fromValue(String v) {
			for (ResidenceTypeEnum c : ResidenceTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static ResidenceTypeEnum fromId(String v) {
			for (ResidenceTypeEnum c : ResidenceTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}

	public enum RequestTypeEnum {
		INDIVIDUAL("01", LITERAL_INDIVIDUAL), COMPANNY("02", "Company");

		private String id;
		private String value;

		private RequestTypeEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static RequestTypeEnum fromValue(String v) {
			for (RequestTypeEnum c : RequestTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static RequestTypeEnum fromId(String v) {
			for (RequestTypeEnum c : RequestTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}

	public enum ApplicantTypeEnum {
		INDIVIDUAL("01", LITERAL_INDIVIDUAL), COMPANNY("02", "Company"), DIRECTOR("03", "Director"), MAIN("04", "MAIN");

		private String id;
		private String value;

		private ApplicantTypeEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static ApplicantTypeEnum fromValue(String v) {
			for (ApplicantTypeEnum c : ApplicantTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static ApplicantTypeEnum fromId(String v) {
			for (ApplicantTypeEnum c : ApplicantTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}

	public enum GenderTypeEnum {
		MALE("1", "MALE",2, "M", "Male"),
		FEMALE("2", "FEMALE",3, "F", "Female"),
		TRANSGENDER("3", "TRANSGENDER",4, "U", "Unknown/Third Gender");

		private String id;
		private String value;
		private Integer mappingId;
		private String sbiCode;
		private String sbiValue;

		private GenderTypeEnum(String id, String value,Integer mappingId, String sbiCode, String sbiValue) {
			this.id = id;
			this.value = value;
			this.mappingId = mappingId;
			this.sbiCode = sbiCode;
			this.sbiValue = sbiValue;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}
		
		public Integer getMappingId() {
			return mappingId;
		}

		public String getSbiCode() {
			return sbiCode;
		}

		public String getSbiValue() {
			return sbiValue;
		}

		public static GenderTypeEnum fromValue(String v) {
			for (GenderTypeEnum c : GenderTypeEnum.values()) {
				if (c.value.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		public static GenderTypeEnum fromSbiCode(String v) {
			for (GenderTypeEnum c : GenderTypeEnum.values()) {
				if (c.sbiCode.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		public static GenderTypeEnum fromSbiValue(String v) {
			for (GenderTypeEnum c : GenderTypeEnum.values()) {
				if (c.sbiValue.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static GenderTypeEnum fromId(String v) {
			for (GenderTypeEnum c : GenderTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
		
		public static GenderTypeEnum fromMappingId(Integer v) {
			for (GenderTypeEnum c : GenderTypeEnum.values()) {
				if (c.mappingId.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v.toString());

		}
	}
	
	public enum RelationShipTypeEnum {
		SHAREHOLDER("01", "Shareholder", "10", "SHAREHOLDER"),
		HOLDING_COMPANY("02", "Holding Company", "35", "HOLDING COMPANY"),
		SUBSIDIARY_COMPANY("03", "Subsidiary Company", "41", "GROUP COMPANY"),
		PROPRIETOR("04", "Proprietor", "11", "SOLE PROPRIETOR"),
		PARTNER("05", "Partner", "12", "PARTNER"),
		TRUSTEE("06", "Trustee", "4", "TRUSTEE: CIF-ACC"),
		PROMOTER_DIRECTOR("07", "Promoter Director", "17", DIRECTOR),
		NOMINEE_DIRECTOR("08", "Nominee Director", "17", DIRECTOR),
		INDEPENDENT_DIRECTOR("09", "Independent Director", "17", DIRECTOR),
		DIRECTOR_SINCE_RESIGNED("10", "Director - Since Resigned", "17", DIRECTOR),
		INDIVIDUAL_MEMBER_OF_SHG("11", "Individual Member of SHG", "26", "MANAGING PARTNER"),
		OTHER_DIRECTOR("12", "Other Director", "17", DIRECTOR),
		OTHER("13", LITERAL_OTHER, "", "");

		private String id;
		private String value;
		private String sbiCode;
		private String sbiValue;

		private RelationShipTypeEnum(String id, String value, String sbiCode, String sbiValue) {
			this.id = id;
			this.value = value;
			this.sbiCode = sbiCode;
			this.sbiValue = sbiValue;
		}

		public String getSbiCode() {
			return sbiCode;
		}

		public String getSbiValue() {
			return sbiValue;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static RelationShipTypeEnum fromValue(String v) {
			for (RelationShipTypeEnum c : RelationShipTypeEnum.values()) {
				if (c.value.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		public static RelationShipTypeEnum fromSbiCode(String v) {
			for (RelationShipTypeEnum c : RelationShipTypeEnum.values()) {
				if (c.sbiCode.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		public static RelationShipTypeEnum fromSbiValue(String v) {
			for (RelationShipTypeEnum c : RelationShipTypeEnum.values()) {
				if (c.sbiValue.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static RelationShipTypeEnum fromId(String v) {
			for (RelationShipTypeEnum c : RelationShipTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}
	
	public enum EntityTypeEnum {
		ASSOCIATION_OF_PERSONS("01", "ASSOCIATION OF PERSONS",20, "", "", "", "", "70", "70"),
		BUSINESS_ENTITIES_CREATED_BY_STATUTE("02", "BUSINESS ENTITIES CREATED BY STATUTE",16, "", "", "", "", "20", "20"),
		CO_OPERATIVE_SOCIETY("03", "CO-OPERATIVE SOCIETY",19, "", "", "", "", "60", "60"),
		GOVERNMENT("04", "GOVERNMENT",15, "20101", "NON PERSONAL-GOVERNMENT-CENTRAL", "OT", LITERAL_OTHERS, "80", "80"),
		HINDU_UNDIVIDED_FAMILY("05", "HINDU UNDIVIDED FAMILY",14, "216", "NON PERSONAL-HINDU UNDIVIDED FAMILIES", "7", "HUF", "55", "55"),
		NOT_CLASSIFIED("06", "NOT CLASSIFIED",22, "", "", "", "", "99", "99"),
		PARTNERSHIP("07", "PARTNERSHIP",5, "20602", "NON PERSONAL-FIRMS-PARTNERSHIP", "2", "Partnership firm", "40", "40"),
		PRIVATE_LIMITED("08", "PRIVATE LIMITED",1, "2040301", "NON PERSONAL - LIMITED COMPANIES - PRIVATE-( NPO )", "4", "Pvt Ltd co", "11", "11"),
		PROPRIETORSHIP("09", "PROPRIETORSHIP",7, "20601", "NON PERSONAL-FIRMS-PROPRIETORY", "11", "Proprietorship", "30", "30"),
		PUBLIC_LIMITED("10", "PUBLIC LIMITED",2, "", "", "", "", "12", "12"),
		SELF_HELP_GROUP("11", "SELF HELP GROUP",21, "", "", "", "", "", "85"),
		TRUST("12", "Trust",12, "", "", "", "", "50", "50"),
		PARTNERSHIP_LLP("07", "PARTNERSHIP",13, "20602", "NON PERSONAL-FIRMS-PARTNERSHIP", "2", "Partnership firm", "", "40");

		private String id;
		private String value;
		private Integer mappingId;
		private String sbiCustomerCode;
		private String sbiCustomerValue;
		private String sbiConstitutionCode;
		private String sbiConstitutionValue;
		private String hmValue;
		private String experianValue;

		private EntityTypeEnum(String id, String value,Integer mappingId, String sbiCustomerCode, String sbiCustomerValue, String sbiConstitutionCode, String sbiConstitutionValue,String hmValue,String experianValue) {
			this.id = id;
			this.value = value;
			this.mappingId = mappingId;
			this.sbiCustomerCode = sbiCustomerCode;
			this.sbiCustomerValue = sbiCustomerValue;
			this.sbiConstitutionCode = sbiConstitutionCode;
			this.sbiConstitutionValue = sbiConstitutionValue;
			this.hmValue = hmValue;
			this.experianValue = experianValue;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public Integer getMappingId() {
			return mappingId;
		}

		public String getSbiCustomerCode() {
			return sbiCustomerCode;
		}

		public String getSbiCustomerValue() {
			return sbiCustomerValue;
		}

		public String getSbiConstitutionCode() {
			return sbiConstitutionCode;
		}

		public String getSbiConstitutionValue() {
			return sbiConstitutionValue;
		}

		public String getHmValue() {
			return hmValue;
		}
		
		public String getExperianValue() {
			return experianValue;
		}

		public static EntityTypeEnum fromSbiCustomerCode(String v) {
			for (EntityTypeEnum c : EntityTypeEnum.values()) {
				if (c.sbiCustomerCode.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		public static EntityTypeEnum fromSbiCustomerValue(String v) {
			for (EntityTypeEnum c : EntityTypeEnum.values()) {
				if (c.sbiCustomerValue.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		public static EntityTypeEnum fromSbiConstitutionCode(String v) {
			for (EntityTypeEnum c : EntityTypeEnum.values()) {
				if (c.sbiConstitutionCode.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		public static EntityTypeEnum fromSbiConstitutionValue(String v) {
			for (EntityTypeEnum c : EntityTypeEnum.values()) {
				if (c.sbiConstitutionValue.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		public static EntityTypeEnum fromValue(String v) {
			for (EntityTypeEnum c : EntityTypeEnum.values()) {
				if (c.value.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static EntityTypeEnum fromId(String v) {
			for (EntityTypeEnum c : EntityTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}

		public static EntityTypeEnum fromMappingId(Integer v) {
			for (EntityTypeEnum c : EntityTypeEnum.values()) {
				if (c.mappingId.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v.toString());
		}
		
		public static EntityTypeEnum fromHmValue(String v) {
			for (EntityTypeEnum c : EntityTypeEnum.values()) {
				if (c.hmValue.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
		public static EntityTypeEnum fromExperianValue(String v) {
			for (EntityTypeEnum c : EntityTypeEnum.values()) {
				if (c.experianValue.equalsIgnoreCase(v)) {
					return c;
				}
			}
			return null;
		}
	}
	
	public enum EnquiryTypeEnum {
		NEW_LOAN("01", "NEW LOAN"), RENEWAL_ENHANCEMENT("02", "RENEWAL/ENHANCEMENT"), COLLECTIONS("03", "COLLECTIONS");

		private String id;
		private String value;

		private EnquiryTypeEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static EnquiryTypeEnum fromValue(String v) {
			for (EnquiryTypeEnum c : EnquiryTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static EnquiryTypeEnum fromId(String v) {
			for (EnquiryTypeEnum c : EnquiryTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}
	
	public enum AccountTypeEnum {

		AUTO_LOAN_PERSONAL("01","Auto Loan (Personal)", "Auto Loan", "1", "Auto Loan (Personal)"),
		HOUSING_LOAN("02","Housing Loan", "Housing Loan", "2", "Housing Loan"),
		PROPERTY_LOAN("03",LITERAL_PROPERTY_LOAN, "Property Loan", "3", "Property Loan"),
		LOAN_AGAINST_SHARES_SECURITIES("04","Loan Against SHARES/Securities", "Loan against Shares/Securities", "4", "Loan Against Shares / Securities"),
		PERSONAL_LOAN("05","Personal Loan", "Personal Loan", "5", "Personal Loan"),
		CONSUMER_LOAN("06","Consumer Loan", "Consumer Loan", "6", "Consumer Loan"),
		GOLD_LOAN("07",LITERAL_GOLD_LOAN, "Gold Loan", "7", "Gold Loan"),
		EDUCATION_LOAN("08","Education Loan", "Education Loan", "8", "Education Loan"),
		LOAN_TO_PROFESSIONAL("09","Loan to Professional", "Loan to Professional", "9", "Loan to Professional"),
		CREDIT_CARD("10","Credit Card", "Credit Card", "10", "Credit Card"),
		LEASING("11","Leasing", "Lease", "11", "Leasing"),
		OVERDRAFT("12",LITERAL_OVERDRAFT, "Overdraft", "12", "Overdraft"),
		TWO_WHEELER_LOAN("13","Two wheeler Loan", "Two-wheeler Loan", "13", "Two-Wheeler Loan"),
		NON_FUNDED_CREDIT_FACILITY("14","Non Funded Credit Facility", "Non-Funded Credit Facility", "14", "Non-Funded Credit Facility"),
		LOAN_AGAINST_BANK_DEPOSITS("15","Loan Against Bank Deposits", "Loan Against Bank Deposits", "15", "Loan Against Bank Deposits"),
		FLEET_CARD("16","Fleet Card", "Fleet Card", "16", "Fleet Card"),
		COMMERCIAL_VEHICLE_LOAN("17","Commercial Vehicle Loan", "Commercial Vehicle Loan", "17", "Commercial Vehicle Loan"),
		TELCO_WIRELESS("18","Telco – Wireless", "Telco - Wireless", "18", "Telco Wireless"),
		TELCO_BROADBAND("19","Telco – Broadband", "Telco - Broadband", "19", "Telco Broadband"),
		TELCO_LANDLINE("20","Telco – Landline", "Telco - Landline", "20", "Telco Landline"),
		SECURED_CREDIT_CARD("31","Secured Credit Card", "Secured Credit Card", "31", "Secured Credit Card"),
		USED_CAR_LOAN("32","Used Car Loan", "Used Car Loan", "32", "Used Car Loan"),
		CONSTRUCTION_EQUIPMENT_LOAN("33","Construction Equipment Loan", "Construction Equipment Loan", "33", "Construction Equipment Loan"),
		TRACTOR_LOAN("34","Tractor Loan", "Tractor Loan", "34", "Used Tractor Loan"),
		CORPORATE_CREDIT_CARD("35","Corporate Credit Card", "Corporate Credit Card", "35", "Corporate Credit Card"),
		KISAN_CREDIT_CARD("36","Kisan Credit Card", "", "36", "Kisan Credit Card"), 
		LOAN_ON_CREDIT_CARD("37","Loan on Credit Card","", "37", "Loan on Credit Card"), 
		PRIME_MINISTER_JAAN_DHAN_YOJANA_OVERDRAFT("38","Prime Minister Jaan Dhan Yojana - Overdraft", "","38", "Prime Minister Jaan Dhan Yojana - Overdraft"), 
		MUDRA_LOANS_SHISHU_KISHOR_TARUN("39","Mudra Loans – Shishu / Kishor / Tarun", "", "39", "Mudra Loans – Shishu / Kishor / Tarun"), 
		MICROFINANCE_BUSINESS_LOAN("40","Microfinance – Business Loan", "MicroFinance Business Loan", "", "Microfinance Business Loan"),
		MICROFINANCE_PERSONAL_LOAN("41","Microfinance – Personal Loan", "MicroFinance Personal Loan", "", "Microfinance Personal Loan"),
		MICROFINANCE_HOUSING_LOAN("42","Microfinance – Housing Loan", "MicroFinance Housing Loan", "", "Microfinance Housing Loan"),
		MICROFINANCE_OTHER("43","Microfinance – Other", "MicroFinance Others", "43", "Microfinance Others"),
		PRADHAN_MANTRI_AWAS_YOJANA_CREDIT_LINK_SUBSIDY_SCHEME_MAY_CLSS("44","Pradhan Mantri Awas Yojana - Credit Link Subsidy Scheme MAY CLSS", "", "", "Pradhan Mantri Awas Yojana - CLSS"),
		BUSINESS_LOAN_SECURED("50","Business Loan - Secured", "", "", "Business Loan - Secured"), 
		BUSINESS_LOAN_GENERAL("51","Business Loan – General", "Business Loan", "51", "Business Loan General"),
		BUSINESS_LOAN_PRIORITY_SECTOR_SMALL_BUSINESS("52","Business Loan – Priority Sector – Small Business", "Business Loan-Priority Sector-Small Business", "52", "Business Loan Priority Sector Small Business"),
		BUSINESS_LOAN_PRIORITY_SECTOR_AGRICULTURE("53","Business Loan – Priority Sector – Agriculture", "Business Loan - Priority Sector- Agriculture", "53", "Business Loan Priority Sector Agriculture"),
		BUSINESS_LOAN_PRIORITY_SECTOR_OTHERS("54","Business Loan – Priority Sector – Others", "Business Loan - Priority Sector- Others", "54", "Business Loan Priority Sector Others"),
		BUSINESS_NON_FUNDED_CREDIT_FACILITY_GENERAL("55","Business NON_Funded Credit Facility – General", "Business Non-Funded Credit Facility", "55", "Business Non-Funded Credit Facility General"),
		BUSINESS_NON_FUNDED_CREDIT_FACILITY_PRIORITY_SECTOR_SMALL_BUSINESS("56","Business NON_Funded Credit Facility – Priority Sector – Small Business", "Business Non-Funded Credit Facility - Priority Sector - Small Business", "56", "Business Non-Funded Credit Facility-Priority Sector- Small Business"),
		BUSINESS_NON_FUNDED_CREDIT_FACILITY_PRIORITY_SECTOR_AGRICULTURE("57","Business NON_Funded Credit Facility – Priority Sector – Agriculture", "Business Non-Funded Credit Facility - Priority Sector - Agriculture", "57", "Business Non-Funded Credit Facility-Priority Sector-Agriculture"),
		BUSINESS_NON_FUNDED_CREDIT_FACILITY_PRIORITY_SECTOR_OTHERS("58","Business NON_Funded Credit Facility – Priority SECTOR_Others", "Business Non-Funded Credit Facility - Priority Sector - Other", "58", "Business Non-Funded Credit Facility-Priority Sector-Others"),
		BUSINESS_LOAN_AGAINST_BANK_DEPOSITS("59","Business Loan Against Bank Deposits", "Business Loan Against Bank Deposits", "59", "Business Loan Against Bank Deposits"),
		BUSINESS_LOAN_UNSECURED("61","Business Loan - Unsecured", "", "61", "Business Loan Unsecured"), 
		MICROFINANCE_DETAILED_REPORT_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("80","Microfinance Detailed Report (Applicable to Enquiry Purpose only)", "", "", ""),
		SUMMARY_REPORT_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("81","Summary Report (Applicable to Enquiry Purpose only)", "", "", ""), 
		LOCATE_PLUS_FOR_INSURANCE_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("88","Locate Plus for Insurance (Applicable to Enquiry Purpose only)", "", "", ""),
		ACCOUNT_REVIEW_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("90","Account Review (Applicable to Enquiry Purpose only)", "", "", ""),
		RETRO_ENQUIRY_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("91","Retro Enquiry (Applicable to Enquiry Purpose only)", "", "", ""),
		LOCATE_PLUS_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("92","Locate Plus (Applicable to Enquiry Purpose only)", "", "", ""),
		ADVISER_LIABILITY_APPLICABLE_TO_ENQUIRY_PURPOSE_ONLY("97","Adviser Liability (Applicable to Enquiry Purpose only)", "", "", ""),
		OTHER("00",LITERAL_OTHER, "Other", "0", "Other"),
		SECURED_ACCOUNT_GROUP_FOR_PORTFOLIO_REVIEW_RESPONSE("98","Secured (Account Group for Portfolio Review response)", "", "", ""),
		UNSECURED_ACCOUNT_GROUP_FOR_PORTFOLIO_REVIEW_RESPONSE("99","Unsecured (Account Group for Portfolio Review response)", "", "", ""),
		// Not Matched loan type with cibil
		AUTO_LEASE("","Auto Lease", "Auto Lease", "", ""),
		STAFF_LOAN("60","Staff Loan", "Staff Loan", "60", "Staff Loan"),
		DISCLOSURE("","Disclosure", "Disclosure", "", "");
		//		Highmark not matched loans
		// Auto Overdraft
		// Commercial Equipment Loan
		// Charge Card
		// Loan against Card
		// OD on Savings Account
		// Used Tractor Loan
		// JLG Individual
		// JLG Group
		// Individual
		// SHG Group
		// SHG Individual
		// SHG Group – Govt
		// SHD Intra - Group



		private String id;
		private String value;
		private String equifaxValue;
		private String experianValue;
		private String highmarkValue;

		private AccountTypeEnum(String id, String value, String equifaxValue, String experianValue, String highmarkValue) {
			this.id = id;
			this.value = value;
			this.equifaxValue = equifaxValue;
			this.experianValue = experianValue;
			this.highmarkValue = highmarkValue;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static AccountTypeEnum fromValue(String v) {
			for (AccountTypeEnum c : AccountTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static AccountTypeEnum fromId(String v) {
			for (AccountTypeEnum c : AccountTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static AccountTypeEnum fromEquifaxValue(String v) {
			for (AccountTypeEnum c : AccountTypeEnum.values()) {
				if (c.equifaxValue.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static AccountTypeEnum fromExperianRetailValue(String v) {
			for (AccountTypeEnum c : AccountTypeEnum.values()) {
				if (c.experianValue.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
		public static AccountTypeEnum fromHighmarkRetailValue(String v) {
			for (AccountTypeEnum c : AccountTypeEnum.values()) {
				if (c.highmarkValue.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

	}
	
	public enum CreditTypeEnum {
		CASH_CREDIT("01", "Cash credit",WORKING_CAPITAL,"100"),
		OVERDRAFT("02", LITERAL_OVERDRAFT,WORKING_CAPITAL,"200"),
		DEMAND_LOAN("03", "Demand loan",LITERAL_TERM_LOAN,""),
		LOAN_EXTENDED_THROUGH_CREDIT_CARDS("04", "Loan extended through credit cards","","6"),
		MEDIUM_TERM_LOAN_PERIOD_ABOVE_1_YEAR_AND_UP_TO_3_YEARS("05", "Medium term loan (period above 1 year and up to 3 years)",LITERAL_TERM_LOAN,"640"),
		LONG_TERM_LOAN_PERIOD_ABOVE_3_YEARS("06", "Long term loan (period above 3 years)",LITERAL_TERM_LOAN,"640"),
		PACKING_CREDIT_ALL_EXPORT_PRE_SHIPMENT_FINANCE("07", "Packing credit (all export pre-shipment finance)",LITERAL_NON_FUND_BASED_LIMITS,"500"),
		EXPORT_BILLS_PURCHASED("08", "Export bills purchased","","630"),
		EXPORT_BILLS_DISCOUNTED("09", "Export bills discounted","","630"),
		EXPORT_BILLS_ADVANCED_AGAINST("10", "Export bills advanced against","","630"),
		ADVANCES_AGAINST_EXPORT_CASH_INCENTIVES_AND_DUTY_DRAW_BACK_CLAIMS("11", "Advances against export cash incentives and duty draw-back claims","","800"),
		INLAND_BILLS_PURCHASED("12", "Inland bills purchased","","710"),
		INLAND_BILLS_DISCOUNTED("13", "Inland bills discounted","","710"),
		ADVANCES_AGAINST_IMPORT_BILLS("14", "Advances against import bills","","800"),
		FOREIGN_CURRENCY_CHEQUES_TCS_DDS_TTS_MTS_PURCHASED("15", "Foreign currency cheques TCS/DDS/TTS/MTS purchased","","900"),
		LEASE_FINANCE("16", "Lease finance",LITERAL_TERM_LOAN,""),
		HIRE_PURCHASE("17", "Hire purchase",LITERAL_TERM_LOAN,"1100"),
		BANK_GUARANTEE("18", "Bank guarantee",LITERAL_NON_FUND_BASED_LIMITS,"2000"),
		DEFERRED_PAYMENT_GUARANTEE("19", "Deferred payment guarantee","",""),
		LETTERS_OF_CREDIT("20", "Letters of credit",LITERAL_NON_FUND_BASED_LIMITS,"3000"),
		CORPORATE_CREDIT_CARD("21", "Corporate credit card","",""),
		COMMERCIAL_VEHICLE_LOAN("22", "Commercial vehicle loan",LITERAL_TERM_LOAN,"4000"),
		EQUIPMENT_FINANCING_CONSTRUCTION_OFFICE_MEDICAL("23", "Equipment financing (construction office medical)",LITERAL_TERM_LOAN,""),
		UNSECURED_BUSINESS_LOAN("24", "Unsecured business loan",LITERAL_TERM_LOAN,""),
		SHORT_TERM_LOAN_LESS_THAN_1_YEAR("25", "Short term loan (less than 1 year)",WORKING_CAPITAL,"640"),
		AGGREGATION_OF_ALL_FUND_BASED_FACILITIES("26", "Aggregation of all fund based facilities","",""),
		AGGREGATION_OF_ALL_NON_FUND_BASED_FACILITIES("27", "Aggregation of all non fund based facilities","",""),
		FACILITIES_INTERCHANGE_BETWEEN_FUND_NON_FUND_BASED("28", "Facilities interchange between fund & non fund based","",""),
		DERIVATIVES("29", "Derivatives","",""),
		PLAIN_VANILLA_FOREX_FORWARD_CONTRACTS("30", "Plain vanilla forex forward contracts","",""),
		PLAIN_VANILA_INT_RATE_SWAP_ALL_INCLUDING_INR_AS_COUPON("31", "Plain vanila int rate swap(all including INR as coupon)","",""),
		PLAIN_VANILA_FOREIGN_CURRENCY_OPTION_INCLUDING_INR_CROSS_CURRENCY("32", "Plain vanila foreign currency option (including INR cross currency)","","900"),
		COMPLEX_INT_RATE_DERV_WITH_OPTIONALITIES("33", "Complex int rate derv with optionalities","",""),
		ANY_COMPLEX_DERIVATIVE_LOAN_INVOLVING_FOREIGN_CURRENCY_WITH_OPTION("34", "Any complex derivative loan involving foreign currency with option","","900"),
		CONTRACTS_ON_PAST_PERFORMANCE_IMPORTS("35", "Contracts on past performance – imports","",""),
		CONTRACTS_ON_PAST_PERFORMANCE_EXPORTS("36", "Contracts on past performance – exports","",""),
		AGGREGATE_OF_ALL_BORROWINGS_DUE_TO_FILING_OF_SUIT("37", "Aggregate of all borrowings due to filing of suit","",""),
		AUTO_LOAN("38", "Auto Loan",LITERAL_TERM_LOAN,""),
		PROPERTY_LOAN("39", LITERAL_PROPERTY_LOAN,LITERAL_TERM_LOAN,""),
		GOLD_LOAN("40", LITERAL_GOLD_LOAN,LITERAL_TERM_LOAN,""),
		LOAN_AGAINST_SHARES_SECURITIES("41", "LOAN AGAINST SHARES/SECURITIES","",""),
		OTHERS("42", LITERAL_OTHERS,LITERAL_TERM_LOAN,"9999");

		private String id;
		private String value;
		private String mainLoanType;
		private String hmCode;

		private CreditTypeEnum(String id, String value,String mainLoanType,String hmCode) {
			this.id = id;
			this.value = value;
			this.mainLoanType = mainLoanType;
			this.hmCode = hmCode;
		}

		public String getValue() {
			return value;
		}

		public String getMainLoanType() {
			return mainLoanType;
		}

		public String getId() {
			return this.id;
		}

		public String getHmCode() {
			return hmCode;
		}

		public static CreditTypeEnum fromValue(String v) {
			for (CreditTypeEnum c : CreditTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static CreditTypeEnum fromId(String v) {
			for (CreditTypeEnum c : CreditTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
		public static CreditTypeEnum fromHmCode(String v) {
			for (CreditTypeEnum c : CreditTypeEnum.values()) {
				if (c.hmCode.equals(v) ||  (!isObjectNullOrEmpty(c.hmCode) && Integer.parseInt(v) == Integer.parseInt(c.hmCode))) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
	}
	public enum EnquiryPurposeTypeEnum {
		MEDIUM_TERM_LOAN_PERIOD_ABOVE_1_YEAR_AND_UP_TO_3_YEARS("01", "Medium term loan (period above 1 year and up to 3 years)","640"),
		ADVANCES_AGAINST_EXPORT_CASH_INCENTIVES_AND_DUTY_DRAW_BACK_CLAIMS("02", "Advances against export cash incentives and duty draw-back claims","800"),
		FOREIGN_CURRENCY_CHEQUES_TCS_DDS_TTS_MTS_PURCHASED("03", "Foreign currency cheques TCS/DDS/TTS/MTS purchased","900"),
		CASH_CREDIT("04", "Cash credit","100"),
		LEASE_FINANCE("05", "Lease finance",""),
		HIRE_PURCHASE("06", "Hire purchase","1100"),
		OVERDRAFT("07", LITERAL_OVERDRAFT,"200"),
		BANK_GUARANTEE("08", "Bank guarantee","2000"),
		DEFERRED_PAYMENT_GUARANTEE("09", "Deferred payment guarantee",""),
		DEMAND_LOAN("10", "Demand loan",""),
		LETTERS_OF_CREDIT("11", "Letters of credit","3000"),
		LOAN_EXTENDED_THROUGH_CREDIT_CARDS("12", "Loan extended through credit cards",""),
		CORPORATE_CREDIT_CARD("13", "Corporate credit card",""),
		COMMERCIAL_VEHICLE_LOAN("14", "Commercial vehicle loan","4000"),
		EQUIPMENT_FINANCING_CONSTRUCTION_OFFICE_MEDICAL("15", "Equipment financing (construction office medical)",""),
		LONG_TERM_LOAN_PERIOD_ABOVE_3_YEARS("16", "Long term loan (period above 3 years)","640"),
		PACKING_CREDIT_ALL_EXPORT_PRE_SHIPMENT_FINANCE("17", "Packing credit (all export pre-shipment finance)","500"),
		UNSECURED_BUSINESS_LOAN("18", "Unsecured business loan",""),
		SHORT_TERM_LOAN_LESS_THAN_1_YEAR("19", "Short term loan (less than 1 year)","640"),
		AGGREGATION_OF_ALL_FUND_BASED_FACILITIES("20", "Aggregation of all fund based facilities",""),
		AGGREGATION_OF_ALL_NON_FUND_BASED_FACILITIES("21", "Aggregation of all non fund based facilities",""),
		FACILITIES_INTERCHANGE_BETWEEN_FUND_NON_FUND_BASED("22", "Facilities interchange between fund & non fund based",""),
		DERIVATIVES("23", "Derivatives",""),
		PLAIN_VANILLA_FOREX_FORWARD_CONTRACTS("24", "Plain vanilla forex forward contracts",""),
		PLAIN_VANILA_INT_RATE_SWAP_ALL_INCLUDING_INR_AS_COUPON("25", "Plain vanila int rate swap(all including INR as coupon)",""),
		PLAIN_VANILA_FOREIGN_CURRENCY_OPTION_INCLUDING_INR_CROSS_CURRENCY("26", "Plain vanila foreign currency option (including INR cross currency)","900"),
		COMPLEX_INT_RATE_DERV_WITH_OPTIONALITIES("27", "Complex int rate derv with optionalities",""),
		ANY_COMPLEX_DERIVATIVE_LOAN_INVOLVING_FOREIGN_CURRENCY_WITH_OPTION("28", "Any complex derivative loan involving foreign currency with option","900"),
		EXPORT_BILLS_PURCHASED("29", "Export bills purchased","630"),
		CONTRACTS_ON_PAST_PERFORMANCE_IMPORTS("30", "Contracts on past performance – imports",""),
		EXPORT_BILLS_DISCOUNTED("31", "Export bills discounted","630"),
		CONTRACTS_ON_PAST_PERFORMANCE_EXPORTS("32", "Contracts on past performance – exports",""),
		EXPORT_BILLS_ADVANCED_AGAINST("33", "Export bills advanced against","630"),
		INLAND_BILLS_PURCHASED("34", "Inland bills purchased","710"),
		INLAND_BILLS_DISCOUNTED("35", "Inland bills discounted","710"),
		ADVANCES_AGAINST_IMPORT_BILLS("36", "Advances against import bills","800"),
		AGGREGATE_OF_ALL_BORROWINGS_DUE_TO_FILING_OF_SUIT("37", "Aggregate of all borrowings due to filing of suit",""),
		AUTO_LOAN("38", "Auto Loan",""),
		PROPERTY_LOAN("39", LITERAL_PROPERTY_LOAN,""),
		GOLD_LOAN("40", LITERAL_GOLD_LOAN,""),
		LOAN_AGAINST_SHARES_SECURITIES("41", "Loan Against Shares/Securities",""),
		HEALTHCARE_FINANCE("42", "HealthCare Finance",""),
		INFRASTRUCTURE_FINANCE("43", "Infrastructure Finance",""),
		FACTORING("44", "Factoring",""),
		COMMERCIAL_PAPER("45", "Commercial Paper",""),
		NCD_NON_CONVERTIBLE_DEBENTURES("46", "NCD – Non Convertible Debentures",""),
		UNHEDGED_FOREIGN_CURRENCY_EXPOSURE("47", "Unhedged Foreign Currency Exposure",""),
		PAYMENT_ACCOUNT_POINT_OF_SALE_PAYMENT_GATEWAYS("48", "Payment Account (Point of Sale & Payment Gateways)",""),
		CURRENT_ACCOUNT("49", "Current Account",""),
		OTHERS("50", LITERAL_OTHERS,"9999"),
		SEARCH("51", "Search","");

		private String id;
		private String value;
		private String hmCode;

		private EnquiryPurposeTypeEnum(String id, String value,String hmCode) {
			this.id = id;
			this.value = value;
			this.hmCode = hmCode;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public String getHmCode() {
			return hmCode;
		}

		public static EnquiryPurposeTypeEnum fromValue(String v) {
			for (EnquiryPurposeTypeEnum c : EnquiryPurposeTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static EnquiryPurposeTypeEnum fromId(String v) {
			for (EnquiryPurposeTypeEnum c : EnquiryPurposeTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
		public static EnquiryPurposeTypeEnum fromHmCode(String v) {
			for (EnquiryPurposeTypeEnum c : EnquiryPurposeTypeEnum.values()) {
				if (c.hmCode.equals(v) ||  (!isObjectNullOrEmpty(c.hmCode) && Integer.parseInt(v) == Integer.parseInt(c.hmCode))) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
	}
	
	public enum ProductTypeEnum {
		PR("01", "PR","P1"), LPR("02", "LPR","P2"), RETRO_BATCH("03", "RETRO_BATCH","P3"),
		CIR("04", "CIR","P4"), D2C_CIR("05", "D2C_CIR","P5"), ISCAN("06", "ISCAN","P6"),
		IPROBE("07", "IPROBE","P7"), WORK_ORDER_NUM_CIR("08", "WORK_ORDER_NUM_CIR","P8"), SUMMARY_REPORT("09", "SUMMARY_REPORT","P9");

		private String id;
		private String value;
		private String code;

		private ProductTypeEnum(String id, String value,String code) {
			this.id = id;
			this.value = value;
			this.code = code;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}
		public String getCode() {
			return this.code;
		}

		public static ProductTypeEnum fromValue(String v) {
			for (ProductTypeEnum c : ProductTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static ProductTypeEnum fromId(String v) {
			for (ProductTypeEnum c : ProductTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
		
		public static ProductTypeEnum fromCode(String v) {
			for (ProductTypeEnum c : ProductTypeEnum.values()) {
				if (c.code.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}
	
	public enum OutputFormateTypeEnum {
		CSV("01", "CSV"), PDF("02", "PDF"), XML("03", "XML"),
		JSON("04", "JSON"),EXCEL("05", "EXCEL");

		private String id;
		private String value;

		private OutputFormateTypeEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static OutputFormateTypeEnum fromValue(String v) {
			for (OutputFormateTypeEnum c : OutputFormateTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static OutputFormateTypeEnum fromId(String v) {
			for (OutputFormateTypeEnum c : OutputFormateTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}

	public enum StateEnum {
		ANDAMAN_AND_NICOBAR_ISLANDS(1l, "Andaman and Nicobar Islands","35","AN","AN"),
		ANDHRA_PRADESH(2l, "Andhra Pradesh","28","AP","AP"),
		ARUNACHAL_PRADESH(3l, "Arunachal Pradesh","12","AR","AR"),
		ASSAM(4l, "Assam","18","AS","AS"),
		BIHAR(5l, "Bihar","10","BR","BR"),
		CHANDIGARH(6l, "Chandigarh","04","CH","CH"),
		CHHATTISGARH(7l, "Chhattisgarh","22","CG","CG"),
		DADRA_AND_NAGAR_HAVELI(8l, "Dadra and Nagar Haveli","26","DN","DN"),
		DAMAN_AND_DIU(9l, "Daman and Diu","25","DD","DD"),
		GOA(11l, "Goa","30","GA","GA"),
		GUJARAT(12l, "Gujarat","24","GJ","GJ"),
		HARYANA(13l, "Haryana","06","HR","HR"),
		HIMACHAL_PRADESH(14l, "Himachal Pradesh","02","HP","HP"),
		JAMMU_AND_KASHMIR(15l, "Jammu and Kashmir","01","JK","JK"),
		JHARKHAND(16l, "Jharkhand","20","JH","JH"),
		KARNATAKA(17l, "Karnataka","29","KA","KA"),
		KERALA(19l, "Kerala","32","KL","KL"),
		LAKSHADWEEP(20l, "Lakshadweep","31","LD","LD"),
		MADHYA_PRADESH(21l, "Madhya Pradesh","23","MP","MP"),
		MAHARASHTRA(22l, "Maharashtra","27","MH","MH"),
		MANIPUR(23l, "Manipur","14","MN","MN"),
		MEGHALAYA(24l, "Meghalaya","17","ML","ML"),
		MIZORAM(25l, "Mizoram","15","MZ","MZ"),
		NAGALAND(26l, "Nagaland","13","NL","NL"),
		NEW_DELHI(10l, "New Delhi","07","DL","DL"), 
		DELHI(10l, "Delhi","07","DL","DL"),
		ORISSA(29l, "Orissa","21","OR","OR"),
		ODISHA(29l, "Odisha","21","OR","OR"),
		PUDUCHERRY(31l, "Puducherry","34","PY","PY"),
		PUNJAB(32l, "Punjab","03","PB","PB"),
		RAJASTHAN(33l, "Rajasthan","08","RJ","RJ"),
		SIKKIM(34l, "Sikkim","11","SK","SK"),
		TAMIL_NADU(35l, "Tamil Nadu","33","TN","TN"),
		TRIPURA(37l, "Tripura","16","TR","TR"),
		UTTAR_PRADESH(38l, "Uttar Pradesh","09","UP","UP"),
		UTTARAKHAND(39l, "Uttarakhand","05","UK","UK"),
		WEST_BENGAL(41l, "West Bengal","19","WB","WB"),
		TELANGANA(36l, "Telangana","36","TS","TS"),
		APO_ADDRESS(-1l, "APO Address","99","","");

		private Long id;
		private String value;
		private String code;
		private String hmCode;
		private String cibilMfiCode;

		private StateEnum(Long id, String value,String code,String hmCode,String cibilMfiCode) {
			this.id = id;
			this.value = value;
			this.code = code;
			this.hmCode = hmCode;
			this.cibilMfiCode = cibilMfiCode;
		}

		public String getValue() {
			return this.value;
		}
		
		public String getCode() {
			return this.code;
		}

		public Long getId() {
			return this.id;
		}

		public String getHmCode() {
			return hmCode;
		}
		
		public String getCibilMfiCode() {
			return cibilMfiCode;
		}

		public static StateEnum fromValue(String v) {
			for (StateEnum c : StateEnum.values()) {
				if (c.value.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static StateEnum fromId(Long v) {
			for (StateEnum c : StateEnum.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v.toString());

		}
		
		public static StateEnum fromCode(String v) {
			for (StateEnum c : StateEnum.values()) {
				if (c.code.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
		public static StateEnum fromHMCode(String v) {
			for (StateEnum c : StateEnum.values()) {
				if (c.hmCode.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
	}
	public enum StateEnumForEquifax {
		ANDAMAN_AND_NICOBAR_ISLANDS(1l, "Andaman and Nicobar Islands","AN"),
		ANDHRA_PRADESH(2l, "Andhra Pradesh","AP"),
		ARUNACHAL_PRADESH(3l, "Arunachal Pradesh","AR"),
		ASSAM(4l, "Assam","AS"),
		BIHAR(5l, "Bihar","BR"),
		CHANDIGARH(6l, "Chandigarh","CH"),
		CHHATTISGARH(7l, "Chhattisgarh","CG"),
		DADRA_AND_NAGAR_HAVELI(8l, "Dadra and Nagar Haveli","DN"),
		DAMAN_AND_DIU(9l, "Daman and Diu","DD"),
		GOA(11l, "Goa","GA"),
		GUJARAT(12l, "Gujarat","GJ"),
		HARYANA(13l, "Haryana","HR"),
		HIMACHAL_PRADESH(14l, "Himachal Pradesh","HP"),
		JAMMU_AND_KASHMIR(15l, "Jammu and Kashmir","JK"),
		JHARKHAND(16l, "Jharkhand","JH"),
		KARNATAKA(17l, "Karnataka","KA"),
		KERALA(19l, "Kerala","KL"),
		LAKSHADWEEP(20l, "Lakshadweep","LD"),
		MADHYA_PRADESH(21l, "Madhya Pradesh","MP"),
		MAHARASHTRA(22l, "Maharashtra","MH"),
		MANIPUR(23l, "Manipur","MN"),
		MEGHALAYA(24l, "Meghalaya","ML"),
		MIZORAM(25l, "Mizoram","MZ"),
		NAGALAND(26l, "Nagaland","NL"),
		NEW_DELHI(10l, "New Delhi","DL"), // 
		DELHI(10l, "Delhi","DL"), //  Change
		ORISSA(29l, "Orissa","OR"), //
		ODISHA(29l, "Odisha","OR"), // Change
		PUDUCHERRY(31l, "Puducherry","PY"),
		PUNJAB(32l, "Punjab","PB"),
		RAJASTHAN(33l, "Rajasthan","RJ"),
		SIKKIM(34l, "Sikkim","SK"),
		TAMIL_NADU(35l, "Tamil Nadu","TN"),
		TRIPURA(37l, "Tripura","TR"),
		UTTAR_PRADESH(38l, "Uttar Pradesh","UP"),
		UTTARAKHAND(39l, "Uttarakhand","UL"),
		UTTARANCHAL(39l, "Uttaranchal","UL"),
		WEST_BENGAL(41l, "West Bengal","WB"),
		TELANGANA(36l, "Telangana","TG");
		
		private Long id;
		private String value;
		private String code;
		
		private StateEnumForEquifax (Long id, String value, String code) {
			this.id = id;
			this.value = value;
			this.code = code;
		}
		
		public String getValue() {
			return this.value;
		}
		
		public String getCode() {
			return this.code;
		}
		
		public Long getId() {
			return this.id;
		}
		public static StateEnumForEquifax fromValue(String v) {
			for (StateEnumForEquifax c : StateEnumForEquifax.values()) {
				if (c.value.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
		public static StateEnumForEquifax fromId(Long v) {
			for (StateEnumForEquifax c : StateEnumForEquifax.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v.toString());
			
		}
		
		public static StateEnumForEquifax fromCode(String v) {
			for (StateEnumForEquifax c : StateEnumForEquifax.values()) {
				if (c.code.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
	}
	
	public enum ClassOfActivityTypeEnum {

		GROWING_OF_TEXTILE_FIBRE_PLANTS("GROWING OF TEXTILE FIBRE PLANTS",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_OF_TABACCO("GROWING OF TABACCO",LITERAL_AGRICULTURE_AND_FORESTRY),
		OLERICULTURE("OLERICULTURE",LITERAL_AGRICULTURE_AND_FORESTRY),
		FLORICULTURE("FLORICULTURE",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_OF_MEDICINAL_PLANTS("GROWING OF MEDICINAL PLANTS",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_OF_CROPS_WITH_HIGH_STARCH_INSULIN_CONTENT("GROWING OF CROPS WITH HIGH STARCH/INSULIN CONTENT",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_OF_TEA_NOT_HAVING_OWN_PROCESSING_UNITS("GROWING OF TEA(NOT HAVING OWN PROCESSING UNITS)",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_AND_PROCESSING_OF_TEA_HAVING_OWN_PROCESSING_AND_BLENDING_UNITS("GROWING AND PROCESSING OF TEA(HAVING OWN PROCESSING AND BLENDING UNITS)",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_OF_COFFEE_OR_COCOA_BEANS("GROWING OF COFFEE OR COCOA BEANS",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_OF_RUBBER_TREES("GROWING OF RUBBER TREES",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_OF_FRUIT_CROPS("GROWING OF FRUIT CROPS",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_OF_EDIBLE_NUTS_INCLUDING_COCONUTS("GROWING OF EDIBLE NUTS INCLUDING COCONUTS",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_OF_SPICE_CROPS_SEEDS_FLOWERS("GROWING OF SPICE CROPS, SEEDS, FLOWERS",LITERAL_AGRICULTURE_AND_FORESTRY),
		GROWING_OF_ANY_OTHER_PLANTATION_CROPS("GROWING OF ANY OTHER PLANTATION CROPS",LITERAL_AGRICULTURE_AND_FORESTRY),
		FARM_MACHINERY_AND_IMPLEMENTS("FARM MACHINERY AND IMPLEMENTS",LITERAL_AGRICULTURE_AND_FORESTRY),
		FARM_TRANSPORT_VEHICLES_ACCESSORIES("FARM TRANSPORT VEHICLES/ACCESSORIES",LITERAL_AGRICULTURE_AND_FORESTRY),
		SOIL_LAND_FARM_DEVELOPMENT_ACTIVITIES("SOIL/LAND/FARM DEVELOPMENT ACTIVITIES",LITERAL_AGRICULTURE_AND_FORESTRY),
		FARM_IRRIGATION("FARM IRRIGATION",LITERAL_AGRICULTURE_AND_FORESTRY),
		CONSTRUCTION_OF_PUMP_HOUSES_CATTLE_SHEDS_POULTRY_SHEDS_ETC("CONSTRUCTION OF PUMP HOUSES, CATTLE SHEDS, POULTRY SHEDS ETC.",LITERAL_AGRICULTURE_AND_FORESTRY),
		STORAGE_AND_MARKETING_OF_AGRICULTURE_PRODUCE("STORAGE AND MARKETING OF AGRICULTURE PRODUCE",LITERAL_AGRICULTURE_AND_FORESTRY),
		CULTURING_MICROORGANISM_FOR_AGRICULTURE_RELATED_PURPOSE("CULTURING MICROORGANISM FOR AGRICULTURE RELATED PURPOSE",LITERAL_AGRICULTURE_AND_FORESTRY),
		ANY_OTHER_DIRECT_FINANCE_TO_AGRICULTURE("ANY OTHER DIRECT FINANCE TO AGRICULTURE",LITERAL_AGRICULTURE_AND_FORESTRY),
		INDIRECT_FINANCE_TO_AGRICULTURE("INDIRECT FINANCE TO AGRICULTURE",LITERAL_AGRICULTURE_AND_FORESTRY),
		DAIRYING("DAIRYING",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		REARING_OF_PACK_ANIMALS("REARING OF PACK ANIMALS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		POULTRY_FARMING("POULTRY FARMING",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MANUFACTURE_OF_PREPARED_ANIMAL_FEED("MANUFACTURE OF PREPARED ANIMAL FEED",LITERAL_MANUFACTURING),
		SERICULTURE_BEE_KEEPING_RAISING_OF_PIGS_SWINE_RABBITS("SERICULTURE, BEE-KEEPING, RAISING OF PIGS/SWINE/RABBITS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		GROWING_OF_CROPS_ALONG_WITH_FARMING_OF_ANIMALS("GROWING OF CROPS ALONG WITH FARMING OF ANIMALS",LITERAL_AGRICULTURE_AND_FORESTRY),
		COTTON_GINNING_CLEANING_AND_BAILING("COTTON GINNING, CLEANING AND BAILING",LITERAL_AGRICULTURE_AND_FORESTRY),
		BIOTECHNOLOGY("BIOTECHNOLOGY",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		AGRICULTURAL_CUSTOM_SERVICE_UNITS("AGRICULTURAL CUSTOM SERVICE UNITS",LITERAL_AGRICULTURE_AND_FORESTRY),
		FORESTRY_LOGGING_AND_RELATED_SERVICE_ACTIVITIES("FORESTRY, LOGGING AND RELATED SERVICE ACTIVITIES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		AQUACULTURE("AQUACULTURE",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MINING_AND_AGGLOMERATION_OF_HARD_COAL_LIGNITE_PEAT("MINING AND AGGLOMERATION OF HARD COAL, LIGNITE, PEAT",LITERAL_MINING_AND_QUARRYING),
		EXTRACTION_OF_CRUDE_PETROLEUM_AND_NATURAL_GAS("EXTRACTION OF CRUDE PETROLEUM AND NATURAL GAS",LITERAL_MINING_AND_QUARRYING),
		SERVICE_ACTIVITIES_INCIDENTAL_TO_OIL_AND_GAS_EXTRACTION("SERVICE ACTIVITIES INCIDENTAL TO OIL AND GAS EXTRACTION",LITERAL_MINING_AND_QUARRYING),
		MINING_OF_URANIUM_AND_THORIUM_ORES("MINING OF URANIUM AND THORIUM ORES",LITERAL_MINING_AND_QUARRYING),
		MINING_OF_IRON_ORES("MINING OF IRON ORES",LITERAL_MINING_AND_QUARRYING),
		MINING_OF_NON_FERROUS_METAL_ORES("MINING OF NON-FERROUS METAL ORES",LITERAL_MINING_AND_QUARRYING),
		MINING_QUARRYING_OF_CONSTRUCTION_MATERIALS("MINING & QUARRYING OF CONSTRUCTION MATERIALS",LITERAL_MINING_AND_QUARRYING),
		MINING_OF_CHEMICALS_AND_FERTILIZERS("MINING OF CHEMICALS AND FERTILIZERS",LITERAL_MINING_AND_QUARRYING),
		EXTRACTION_OF_SALT("EXTRACTION OF SALT",LITERAL_MINING_AND_QUARRYING),
		OTHER_MINING_AND_QUARRYING("OTHER LITERAL_MINING_AND_QUARRYING",LITERAL_MINING_AND_QUARRYING),
		SLAUGHTERING_PREPARATION_AND_PRESERVATION_OF_MEAT("SLAUGHTERING, PREPARATION AND PRESERVATION OF MEAT",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		PROCESSING_CANNING_FREEZING_AND_PRESERVING_OF_FISH_CRUSTACEAN_AND_SIMILAR_FOODS("PROCESSING, CANNING, FREEZING AND PRESERVING OF FISH, CRUSTACEAN AND SIMILAR FOODS",LITERAL_FISHING),
		PROCESSING_CANNING_AND_PRESERVING_OF_FRUITS_AND_VEGETABLES("PROCESSING, CANNING AND PRESERVING OF FRUITS AND VEGETABLES",LITERAL_AGRICULTURE_AND_FORESTRY),
		MANUFACTURE_OF_VEGETABLE_AND_ANIMAL_OILS_AND_FATS("MANUFACTURE OF VEGETABLE AND ANIMAL OILS AND FATS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_DAIRY_PRODUCTS("MANUFACTURE OF DAIRY PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_GRAIN_MILL_PRODUCTS("MANUFACTURE OF GRAIN MILL PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_STARCHES_AND_STARCH_PRODUCTS("MANUFACTURE OF STARCHES AND STARCH PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_CONFECTIONERY_BAKERY_PRODUCT("MANUFACTURE OF CONFECTIONERY/BAKERY PRODUCT",LITERAL_MANUFACTURING),
		MANUFACTURE_AND_REFINING_OF_SUGAR_SUGAR_PRODUCTS("MANUFACTURE AND REFINING OF SUGAR & SUGAR PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_INDIGENOUS_SUGAR_BOORA_GUR_AND_KHANDSARI("MANUFACTURE OF INDIGENOUS SUGAR, 'BOORA', 'GUR' AND 'KHANDSARI'",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_COCOA_CHOCOLATE_AND_SUGAR_CONFECTIONERY("MANUFACTURE OF COCOA, CHOCOLATE AND SUGAR CONFECTIONERY",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_SEMI_PROCESSED_AND_READY_TO_EAT_FOOD_ITEMS("MANUFACTURE OF SEMI-PROCESSED AND READY TO EAT FOOD ITEMS",LITERAL_MANUFACTURING),
		TEA_PROCESSING_AND_BLENDING_UNITS_NO_HAVING_OWN_TEAGARDENS_ESTATES("TEA PROCESSING AND BLENDING UNITS NO HAVING OWN TEAGARDENS/ESTATES",LITERAL_MANUFACTURING),
		COFFEE_CURING_ROASTING_GRINDING_AND_BLENDING_MANUFACTURE_OF_INSTANT_COFFEE("COFFEE CURING, ROASTING, GRINDING AND BLENDING, MANUFACTURE OF INSTANT COFFEE",LITERAL_MANUFACTURING),
		PROCESSING_OF_EDIBLE_NUTS("PROCESSING OF EDIBLE NUTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_ALL_OTHER_FOOD_PRODUCTS("MANUFACTURE OF ALL OTHER FOOD PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_SPIRITS_DISTILLING_RECTIFYING_AND_BLENDING_OF_SPIRITS("MANUFACTURE OF SPIRITS & DISTILLING, RECTIFYING AND BLENDING OF SPIRITS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_SOFT_DRINKS("MANUFACTURE OF SOFT DRINKS",LITERAL_MANUFACTURING),
		TOBACCO_PROCESSING("TOBACCO PROCESSING",LITERAL_MANUFACTURING),
		LITERAL_MANUFACTURING_OF_TOBACCO_PRODUCTS("LITERAL_MANUFACTURING OF TOBACCO PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_CHEWING_ITEMS_NOT_CONTAINING_TOBACCO_OR_TOBACCO_PRODUCTS("MANUFACTURE OF CHEWING ITEMS(NOT CONTAINING TOBACCO OR TOBACCO PRODUCTS)",LITERAL_MANUFACTURING),
		COTTON_TEXTILE("COTTON TEXTILE",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		JUTE_OTHER_NATURAL_FIBRE_TEXTILE("JUTE & OTHER NATURAL FIBRE TEXTILE",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		SILK_SYNTHETIC_TEXTILE("SILK & SYNTHETIC TEXTILE",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		WOOLEN_TEXTILE("WOOLEN TEXTILE",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		HANDLOOM_TEXTILES_AND_KHADI("HANDLOOM TEXTILES AND KHADI",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MANUFACTURE_OF_MADE_UP_TEXTILE_ARTICLES_TARPAULIN("MANUFACTURE OF MADE-UP TEXTILE ARTICLES, TARPAULIN",LITERAL_MANUFACTURING),
		MANUFACTURE_VEGETABLE_FIBRE_PRODUCTS_EXCLUDING_COIR("MANUFACTURE VEGETABLE FIBRE PRODUCTS EXCLUDING COIR",LITERAL_MANUFACTURING),
		COIR_AND_COIR_PRODUCTS("COIR AND COIR PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_SHAWLS_BLANKETS_CARPETS_DURRIES_DRUGGETS_AND_RUGS("MANUFACTURE OF SHAWLS, BLANKETS, CARPETS, DURRIES, DRUGGETS AND RUGS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_THREAD_NETS_TAPES_NEWAR_WICKS_ROPES_AND_CORDAGE("MANUFACTURE OF THREAD, NETS, TAPES, NEWAR, WICKS, ROPES AND CORDAGE",LITERAL_MANUFACTURING),
		EMBROIDERY_ZARI_MANUFACTURE_OF_OTHER_TEXTILE_PRODUCTS("EMBROIDERY/ZARI, MANUFACTURE OF OTHER TEXTILE PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_KNITTED_AND_CROCHETED_FABRICS_AND_ARTICLES("MANUFACTURE OF KNITTED AND CROCHETED FABRICS AND ARTICLES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_WEARING_READYMADE_APPARELS_EXCEPT_KHADI("MANUFACTURE OF WEARING/READYMADE APPARELS(EXCEPT KHADI)",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_KHADI_GARMENTS("MANUFACTURE OF KHADI GARMENTS",LITERAL_MANUFACTURING),
		MANUFACTURE_DRESSING_DYEING_OF_FUR_EMBROIDERING_AND_EMBOSSING_OF_LEATHER_ARTICLES("MANUFACTURE, DRESSING, DYEING OF FUR; EMBROIDERING AND EMBOSSING OF LEATHER ARTICLES",LITERAL_MANUFACTURING),
		LEATHER_PROCESSING("LEATHER PROCESSING",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_LEATHER_AND_LEATHER_PRODUCTS_EXCLUDING_FOOTWEAR("MANUFACTURE OF LEATHER AND LEATHER PRODUCTS(EXCLUDING FOOTWEAR)",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_LEATHER_FOOTWEAR("MANUFACTURE OF LEATHER FOOTWEAR",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_ALL_OTHER_TYPES_OF_FOOTWEAR("MANUFACTURE OF ALL OTHER TYPES OF FOOTWEAR",LITERAL_MANUFACTURING),
		SAW_MILLING_AND_PLANKING_OF_WOOD_OTHER_THAN_PLYWOOD("SAW MILLING AND PLANKING OF WOOD(OTHER THAN PLYWOOD)",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_VENEER_SHEETS_PLYWOOD_LAMINATED_PARTICLE_BOARD("MANUFACTURE OF VENEER SHEETS, PLYWOOD/LAMINATED/PARTICLE BOARD",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_WOOD_PRODUCTS("MANUFACTURE OF WOOD PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_PULP_PAPER_AND_PAPER_BOARDS_EXCEPT_NEWSPRINT("MANUFACTURE OF PULP, PAPER AND PAPER BOARDS EXCEPT NEWSPRINT",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_NEWSPRINT("MANUFACTURE OF NEWSPRINT",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_CORRUGATED_PAPER_PAPER_BOARD_CRAFT_PAPER("MANUFACTURE OF CORRUGATED PAPER, PAPER BOARD, CRAFT PAPER",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_OTHER_ARTICLES_OF_PAPER_AND_PAPER_BOARD("MANUFACTURE OF OTHER ARTICLES OF PAPER AND PAPER BOARD",LITERAL_MANUFACTURING),
		PUBLISHING_OF_BOOKS_BROCHURE_MUSICAL_BOOKS_AND_OTHER_PUBLICATIONS("PUBLISHING OF BOOKS, BROCHURE, MUSICAL BOOKS AND OTHER PUBLICATIONS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		PUBLISHING_OF_NEWSPAPER_PERIODICALS_AND_JOURNALS("PUBLISHING OF NEWSPAPER, PERIODICALS AND JOURNALS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		PUBLISHING_OF_RECORDED_MEDIA("PUBLISHING OF RECORDED MEDIA",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		PRINTING("PRINTING",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		REPRODUCTION_OF_RECORDED_MEDIA("REPRODUCTION OF RECORDED MEDIA",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MANUFACTURE_OF_COKE_OR_COKE_OVEN_PRODUCTS("MANUFACTURE OF COKE OR COKE OVEN PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_REFINED_PETROLEUM_PRODUCTS("MANUFACTURE OF REFINED PETROLEUM PRODUCTS",LITERAL_MANUFACTURING),
		PROCESSING_OF_NUCLEAR_FUEL("PROCESSING OF NUCLEAR FUEL",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_BASIC_CHEMICALS_EXCEPT_FERTILIZERS_AND_NITROGEN_COMPOUNDS("MANUFACTURE OF BASIC CHEMICALS EXCEPT FERTILIZERS AND NITROGEN COMPOUNDS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_TURPENTINE_AND_RESINS_OF_VEGETABLE_ORIGIN("MANUFACTURE OF TURPENTINE AND RESINS OF VEGETABLE ORIGIN",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_FERTILIZERS_AND_NITROGEN_COMPOUNDS("MANUFACTURE OF FERTILIZERS AND NITROGEN COMPOUNDS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_PLASTIC_SYNTHETIC_RUBBER_IN_PRIMARY_FORM("MANUFACTURE OF PLASTIC & SYNTHETIC RUBBER IN PRIMARY FORM",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_PESTICIDES_AND_OTHER_AGRO_CHEMICAL_PRODUCTS("MANUFACTURE OF PESTICIDES AND OTHER AGRO CHEMICAL PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_PAINTS_VARNISHES_AND_SIMILAR_PRODUCTS("MANUFACTURE OF PAINTS, VARNISHES AND SIMILAR PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_PHARMACEUTICALS_MEDICINAL_CHEMICALS_AND_BOTANICAL_PRODUCTS("MANUFACTURE OF PHARMACEUTICALS, MEDICINAL CHEMICALS AND BOTANICAL PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_TOILETRIES("MANUFACTURE OF TOILETRIES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_MATCHES_FIREWORKS_EXPLOSIVES_AMMUNITIONS("MANUFACTURE OF MATCHES & FIREWORKS, EXPLOSIVES, AMMUNITIONS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_ESSENTIAL_OILS("MANUFACTURE OF ESSENTIAL OILS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_PHOTOCHEMICAL_SENSITISED_FILMS_AND_PAPERS("MANUFACTURE OF PHOTOCHEMICAL, SENSITISED FILMS AND PAPERS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_GELATIN_DERIVATIVES_AND_ADHESIVES("MANUFACTURE OF GELATIN/DERIVATIVES AND ADHESIVES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_FINE_CHEMICALS_AND_OTHER_CHEMICAL_PRODUCTS("MANUFACTURE OF FINE CHEMICALS AND OTHER CHEMICAL PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_MAN_MADE_FIBRES("MANUFACTURE OF MAN-MADE FIBRES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_RUBBER_TYRES_TUBES("MANUFACTURE OF RUBBER TYRES, TUBES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_OTHER_RUBBER_PRODUCTS("MANUFACTURE OF OTHER RUBBER PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_PLASTIC_PRODUCTS("MANUFACTURE OF PLASTIC PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_FIBRE_GLASS_AND_PRODUCTS("MANUFACTURE OF FIBRE GLASS AND PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_GLASS_AND_GLASS_PRODUCTS("MANUFACTURE OF GLASS AND GLASS PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_NON_STRUCTURAL_NON_REFRACTORY_CERAMIC_WARE("MANUFACTURE OF NON-STRUCTURAL NON-REFRACTORY CERAMIC WARE",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_CEMENT_LIME_AND_PLASTER("MANUFACTURE OF CEMENT, LIME AND PLASTER",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_CONCRETE_CEMENT_AND_PLASTER_INCLUDING_ASBESTOS_CEMENT("MANUFACTURE OF CONCRETE, CEMENT AND PLASTER INCLUDING ASBESTOS CEMENT",LITERAL_MANUFACTURING),
		CUTTING_SHAPING_AND_FINISHING_OF_STONE("CUTTING, SHAPING AND FINISHING OF STONE",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MANUFACTURE_OF_MICA_GRAPHITE_PRODUCTS_MINERAL_INSULATING_HEAT_INSULATING_MATERIAL("MANUFACTURE OF MICA/GRAPHITE PRODUCTS, MINERAL INSULATING & HEAT INSULATING MATERIAL",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_BASIC_IRON_STEEL("MANUFACTURE OF BASIC IRON & STEEL",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_BASIC_PRECIOUS_AND_NON_FERROUS_METALS("MANUFACTURE OF BASIC PRECIOUS AND NON-FERROUS METALS",LITERAL_MANUFACTURING),
		CASTING_OF_IRON_AND_STEEL("CASTING OF IRON AND STEEL",LITERAL_MANUFACTURING),
		CASTING_OF_NON_FERROUS_METALS("CASTING OF NON-FERROUS METALS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_STRUCTURAL_METAL_PRODUCTS("MANUFACTURE OF STRUCTURAL METAL PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_OTHER_FABRICATED_METAL_PRODUCTS("MANUFACTURE OF OTHER FABRICATED METAL PRODUCTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_GENERAL_ENGINEERING_MACHINERY_GOODS("MANUFACTURE OF GENERAL ENGINEERING MACHINERY & GOODS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_OTHER_GENERAL_PURPOSE_MACHINERY("MANUFACTURE OF OTHER GENERAL PURPOSE MACHINERY",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_TRACTORS_HARVESTERS_AND_OTHER_AGRI_FORESTRY_BASED_HEAVY_MACHINERY("MANUFACTURE OF TRACTORS, HARVESTERS AND OTHER AGRI/FORESTRY BASED HEAVY MACHINERY",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_MACHINE_TOOLS("MANUFACTURE OF MACHINE-TOOLS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_MACHINERY_AND_PARTS_FOR_MINING_QUARRYING_AND_CONSTRUCTION("MANUFACTURE OF MACHINERY(AND PARTS) FOR MINING, QUARRYING AND CONSTRUCTION",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_MACHINERY_FOR_FOOD_BEVERAGE_AND_TOBACCO_PROCESSING("MANUFACTURE OF MACHINERY FOR FOOD, BEVERAGE AND TOBACCO PROCESSING",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_MACHINERY_FOR_TEXTILE_APPAREL_AND_LEATHER_PRODUCTION("MANUFACTURE OF MACHINERY FOR TEXTILE, APPAREL AND LEATHER PRODUCTION",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_LIGHT_AGRICULTURAL_AND_FORESTRY_MACHINERY_AND_EQUIPMENT("MANUFACTURE OF LIGHT AGRICULTURAL AND FORESTRY MACHINERY AND EQUIPMENT",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_MACHINERY_FOR_METALLURGY("MANUFACTURE OF MACHINERY FOR METALLURGY",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_WEAPONS_AND_AMMUNITION("MANUFACTURE OF WEAPONS AND AMMUNITION",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_OTHER_SPECIAL_PURPOSE_MACHINERY("MANUFACTURE OF OTHER SPECIAL PURPOSE MACHINERY",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_DOMESTIC_APPLIANCES("MANUFACTURE OF DOMESTIC APPLIANCES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_COMPUTERS_PRINTERS_OTHER_COMPUTER_PERIPHERALS_AND_OTHER_COMPUTING_MACHINERY("MANUFACTURE OF COMPUTERS, PRINTERS, OTHER COMPUTER PERIPHERALS AND OTHER COMPUTING MACHINERY",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_OFFICE_ACCOUNTING_AND_COMPUTING_MACHINERY("MANUFACTURE OF OFFICE, ACCOUNTING AND COMPUTING MACHINERY",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_ELECTRIC_MOTORS_GENERATORS_AND_TRANSFORMERS("MANUFACTURE OF ELECTRIC MOTORS, GENERATORS AND TRANSFORMERS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_ELECTRICITY_DISTRIBUTION_AND_CONTROL_APPARATUS("MANUFACTURE OF ELECTRICITY DISTRIBUTION AND CONTROL APPARATUS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_INSULATED_WIRE_AND_CABLE("MANUFACTURE OF INSULATED WIRE AND CABLE",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_ACCUMULATORS_PRIMARY_CELLS_AND_PRIMARY_BATTERIES("MANUFACTURE OF ACCUMULATORS, PRIMARY CELLS AND PRIMARY BATTERIES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_ELECTRIC_LAMPS_AND_LIGHTING_EQUIPMENTS("MANUFACTURE OF ELECTRIC LAMPS AND LIGHTING EQUIPMENTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_OTHER_ELECTRICAL_EQUIPMENTS("MANUFACTURE OF OTHER ELECTRICAL EQUIPMENTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_ELECTRONIC_VALVES_TUBES_AND_OTHER_ELECTRONIC_COMPONENTS("MANUFACTURE OF ELECTRONIC VALVES, TUBES AND OTHER ELECTRONIC COMPONENTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_TELEVISION_AND_RADIO_TRANSMITTERS_AND_APPARATUS_FOR_LINE_TELEPHONY_AND_LINE_TELEGRAPHY("MANUFACTURE OF TELEVISION AND RADIO TRANSMITTERS AND APPARATUS FOR LINE TELEPHONY AND LINE TELEGRAPHY",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_TELEVISION_AND_RADIO_RECEIVERS("MANUFACTURE OF TELEVISION AND RADIO RECEIVERS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_MEDICAL_SURGICAL_EQUIPMENT_ORTHOPEDIC_APPLIANCES_AND_PROCESS_CONTROL_EQUIPMENTS("MANUFACTURE OF MEDICAL/SURGICAL EQUIPMENT, ORTHOPEDIC APPLIANCES AND PROCESS CONTROL EQUIPMENTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_OPTICAL_INSTRUMENTS_AND_PHOTOGRAPHIC_EQUIPMENTS("MANUFACTURE OF OPTICAL INSTRUMENTS AND PHOTOGRAPHIC EQUIPMENTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_WATCHES_AND_CLOCKS("MANUFACTURE OF WATCHES AND CLOCKS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_MOTOR_VEHICLES("MANUFACTURE OF MOTOR VEHICLES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_COACH_WORK_FOR_MOTOR_VEHICLES_TRANSPORT_CONTAINERS("MANUFACTURE OF COACH WORK FOR MOTOR VEHICLES, TRANSPORT CONTAINERS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_PARTS_AND_ACCESSORIES_FOR_MOTOR_VEHICLES_AND_THEIR_ENGINES("MANUFACTURE OF PARTS AND ACCESSORIES FOR MOTOR VEHICLES AND THEIR ENGINES",LITERAL_MANUFACTURING),
		BUILDING_AND_REPAIRING_OF_SHIPS_AND_LITERAL_FISHING_TRAWLERS("BUILDING AND REPAIRING OF SHIPS AND LITERAL_FISHING TRAWLERS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_RAILWAY_AND_TRAMWAY_LOCOMOTIVES_AND_ROLLING_STOCK("MANUFACTURE OF RAILWAY AND TRAMWAY LOCOMOTIVES AND ROLLING STOCK",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_AIRCRAFT_AND_SPACECRAFT("MANUFACTURE OF AIRCRAFT AND SPACECRAFT",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_TWO_THREE_WHEELERS_ENGINES_AND_OTHER_SPECIALIZED_PARTS_ACCESSORIES("MANUFACTURE OF TWO/THREE WHEELERS, ENGINES AND OTHER SPECIALIZED PARTS & ACCESSORIES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_TRANSPORT_EQUIPMENTS_AND_PARTS_LIKE_BICYCLES_CYCLE_RICKSHAWS_ETC("MANUFACTURE OF TRANSPORT EQUIPMENTS AND PARTS LIKE BICYCLES, CYCLE RICKSHAWS, ETC.",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_FURNITURE_FIXTURES("MANUFACTURE OF FURNITURE & FIXTURES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_JEWELLERY_RELATED_ARTICLES_OTHER_THAN_DIAMOND_AND_MINTING_OF_CURRENCY_COINS("MANUFACTURE OF JEWELLERY & RELATED ARTICLES(OTHER THAN DIAMOND) AND MINTING OF CURRENCY COINS",LITERAL_MANUFACTURING),
		DIAMOND_CUTTING_AND_POLISHING("DIAMOND CUTTING AND POLISHING",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_MUSICAL_INSTRUMENTS("MANUFACTURE OF MUSICAL INSTRUMENTS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_SPORTS_AND_ATHLETIC_GOODS("MANUFACTURE OF SPORTS AND ATHLETIC GOODS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_GAMES_AND_TOYS("MANUFACTURE OF GAMES AND TOYS",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_STATIONERY_ARTICLES("MANUFACTURE OF STATIONERY ARTICLES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_IMITATION_JEWELLERY_CLEANING_MATERIALS_PERSONAL_USE_ARTICLES("MANUFACTURE OF IMITATION JEWELLERY, CLEANING MATERIALS, PERSONAL USE ARTICLES",LITERAL_MANUFACTURING),
		MANUFACTURE_OF_LINOLEUM_AND_HARD_SURFACE_FLOOR_COVERINGS_MISCELLANEOUS_DECORATIVE_ARTICLES("MANUFACTURE OF LINOLEUM AND HARD SURFACE FLOOR COVERINGS & MISCELLANEOUS DECORATIVE ARTICLES",LITERAL_MANUFACTURING),
		RECYCLING_OF_METAL_NON_METAL_WASTE_AND_SCRAP("RECYCLING OF METAL & NON-METAL WASTE AND SCRAP",LITERAL_MANUFACTURING),
		GENERATION_OF_ELECTRICITY("GENERATION OF ELECTRICITY",LITERAL_ELECTRICITY_GAS_AND_WATER_SUPPLY),
		ENERGISATION_OF_PUMPSETS_WELLS_INCLUDING_ADVANCES_GIVEN_TO_STATE_ELECTRICITY_BOARDS("ENERGISATION OF PUMPSETS/WELLS INCLUDING ADVANCES GIVEN TO STATE ELECTRICITY BOARDS",LITERAL_ELECTRICITY_GAS_AND_WATER_SUPPLY),
		COLLECTION_AND_DISTRIBUTION_OF_ELECTRIC_ENERGY("COLLECTION AND DISTRIBUTION OF ELECTRIC ENERGY",LITERAL_ELECTRICITY_GAS_AND_WATER_SUPPLY),
		GENERATION_AND_DISTRIBUTION_OF_BIO_GAS_ENERGY("GENERATION AND DISTRIBUTION OF BIO-GAS ENERGY",LITERAL_ELECTRICITY_GAS_AND_WATER_SUPPLY),
		GENERATION_AND_DISTRIBUTION_OF_SOLAR_ENERGY_AND_ENERGY_FROM_OTHER_NON_CONVENTIONAL_SOURCES("GENERATION AND DISTRIBUTION OF SOLAR ENERGY AND ENERGY FROM OTHER NON-CONVENTIONAL SOURCES",LITERAL_ELECTRICITY_GAS_AND_WATER_SUPPLY),
		MANUFACTURE_OF_GAS_DISTRIBUTION_OF_GASEOUS_FUELS_THROUGH_MAINS("MANUFACTURE OF GAS; DISTRIBUTION OF GASEOUS FUELS THROUGH MAINS",LITERAL_ELECTRICITY_GAS_AND_WATER_SUPPLY),
		STEAM_AND_HOT_WATER_SUPPLY("STEAM AND HOT WATER SUPPLY",LITERAL_ELECTRICITY_GAS_AND_WATER_SUPPLY),
		COLLECTION_PURIFICATION_AND_DISTRIBUTION_OF_WATER("COLLECTION, PURIFICATION AND DISTRIBUTION OF WATER",LITERAL_ELECTRICITY_GAS_AND_WATER_SUPPLY),
		GENERAL_CONSTRUCTION_OF_RESIDENTIAL_OR_NON_RESIDENTIAL_BUILDINGS("GENERAL CONSTRUCTION OF RESIDENTIAL OR NON-RESIDENTIAL BUILDINGS",LITERAL_CONSTRUCTION),
		SPECIALISED_CONSTRUCTION("SPECIALISED CONSTRUCTION",LITERAL_CONSTRUCTION),
		LOAN_FOR_SETTING_UP_OF_INDUSTRIAL_ESTATES("LOAN FOR SETTING UP OF INDUSTRIAL ESTATES",LITERAL_FINANCIAL_INTERMEDIATION),
		BUILDING_INSTALLATION_AND_BUILDING_COMPLETION("BUILDING INSTALLATION AND BUILDING COMPLETION",LITERAL_CONSTRUCTION),
		CONSTRUCTION_ERECTION_AND_MAINTENANCE_OF_POWER_AND_TRANSMISSION_LINES("CONSTRUCTION / ERECTION AND MAINTENANCE OF POWER AND TRANSMISSION LINES",LITERAL_CONSTRUCTION),
		CONSTRUCTION_ERECTION_AND_MAINTENANCE_OF_TELECOMMUNICATION_AND_TRANSMISSION_LINES("CONSTRUCTION/ERECTION AND MAINTENANCE OF TELECOMMUNICATION AND TRANSMISSION LINES",LITERAL_CONSTRUCTION),
		CONSTRUCTION_AND_MAINTENANCE_OF_ROADS_PORTS_WATER_WAYS("CONSTRUCTION AND MAINTENANCE OF ROADS, PORTS, WATER WAYS",LITERAL_CONSTRUCTION),
		OTHER_INFRASTRUCTURE_CONSTRUCTION("OTHER INFRASTRUCTURE CONSTRUCTION",LITERAL_CONSTRUCTION),
		SALE_OF_ALL_KINDS_OF_MOTOR_VEHICLES_TWO_THREE_WHEELERS("SALE OF ALL KINDS OF MOTOR VEHICLES, TWO/THREE WHEELERS",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		SALE_OF_ALL_KINDS_OF_PARTS_AND_ACCESSORIES_OF_MOTOR_VEHICLES_TWO_THREE_WHEELERS("SALE OF ALL KINDS OF PARTS AND ACCESSORIES OF MOTOR VEHICLES, TWO/THREE WHEELERS",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		MAINTENANCE_AND_REPAIR_OF_MOTOR_VEHICLES_TWO_THREE_WHEELERS("MAINTENANCE AND REPAIR OF MOTOR VEHICLES, TWO/THREE WHEELERS",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		RETAIL_SALE_OF_AUTOMOTIVE_FUEL("RETAIL SALE OF AUTOMOTIVE FUEL",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		WHOLESALE_ON_A_FEE_OR_CONTRACT_BASIS("WHOLESALE ON A FEE OR CONTRACT BASIS",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		FOOD_PROCUREMENT("FOOD PROCUREMENT",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		FOOD_GRAINS_CEREALS_AND_PULSES("FOOD GRAINS(CEREALS AND PULSES)",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		TOBACCO_TOBACCO_PRODUCTS("TOBACCO & TOBACCO PRODUCTS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		FOOD_AND_BEVERAGES("FOOD AND BEVERAGES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		SEEDS_AGRICULTURAL_RAW_MATERIAL_LIVE_ANIMALS("SEEDS, AGRICULTURAL RAW MATERIAL & LIVE ANIMALS",LITERAL_AGRICULTURE_AND_FORESTRY),
		DIAMONDS_GEMS_AND_JEWELLERY("DIAMONDS, GEMS AND JEWELLERY",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		PHARMACEUTICAL_AND_MEDICAL_GOODS("PHARMACEUTICAL AND MEDICAL GOODS",LITERAL_HEALTH_AND_SOCIAL_WORK),
		TEXTILES_OTHER_HOUSEHOLD_GOODS_PAPER_OTHER_STATIONERY_ITEMS_LEATHER_GOODS_TRAVEL_ACCESSORIES("TEXTILES, OTHER HOUSEHOLD GOODS, PAPER & OTHER STATIONERY ITEMS, LEATHER GOODS & TRAVEL ACCESSORIES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		COTTON("COTTON",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		JUTE_AND_MESTA("JUTE AND MESTA",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		FERTILIZERS_AND_PESTICIDES("FERTILIZERS AND PESTICIDES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		WHOLESALE_OF_SOLID_LIQUID_AND_GASEOUS_FUELS_AND_RELATED_PRODUCTS("WHOLESALE OF SOLID, LIQUID AND GASEOUS FUELS AND RELATED PRODUCTS",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		WHOLESALE_OF_METALS_AND_METAL_ORES("WHOLESALE OF METALS AND METAL ORES",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		WHOLESALE_OF_CONSTRUCTION_MATERIALS_HARDWARE_PLUMBING_AND_HEATING_EQUIPMENT_AND_SUPPLIES("WHOLESALE OF CONSTRUCTION MATERIALS, HARDWARE, PLUMBING AND HEATING EQUIPMENT AND SUPPLIES",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		WHOLESALE_OF_OTHER_INTERMEDIATE_PRODUCTS_WASTE_AND_SCRAP("WHOLESALE OF OTHER INTERMEDIATE PRODUCTS, WASTE AND SCRAP",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		AGRICULTURAL_MACHINERY_AND_EQUIPMENT("AGRICULTURAL MACHINERY AND EQUIPMENT",LITERAL_AGRICULTURE_AND_FORESTRY),
		MACHINERY_EQUIPMENT_AND_SUPPLIES_OTHER_THAN_AGRICULTURAL_MACHINERY_AND_EQUIPMENT("MACHINERY, EQUIPMENT AND SUPPLIES(OTHER THAN AGRICULTURAL MACHINERY AND EQUIPMENT)",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		HANDICRAFTS("HANDICRAFTS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		CARPETS("CARPETS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		OTHER_WHOLESALE("OTHER WHOLESALE",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		NON_SPECIALIZED_RETAIL_TRADE_GENERAL_MERCHANDISE("NON-SPECIALIZED RETAIL TRADE(GENERAL MERCHANDISE)",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		RETAIL_OUTLETS_UNDER_PUBLIC_DISTRIBUTION_SYSTEM("RETAIL OUTLETS UNDER PUBLIC DISTRIBUTION SYSTEM",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		RETAIL_SALE_IN_SPECIALIZED_STORES_OF_FOOD_BEVERAGES_AND_TOBACCO("RETAIL SALE IN SPECIALIZED STORES OF FOOD, BEVERAGES AND TOBACCO",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		RETAIL_SALE_OF_FERTILIZERS_AND_PESTICIDES("RETAIL SALE OF FERTILIZERS AND PESTICIDES",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		RETAIL_SALE_OF_SEEDS("RETAIL SALE OF SEEDS",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		RETAIL_SALE_OF_AGRICULTURAL_IMPLEMENTS_AND_MACHINERY("RETAIL SALE OF AGRICULTURAL IMPLEMENTS AND MACHINERY",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		RETAIL_SALE_OF_DIAMONDS_GEMS_JEWELLERY("RETAIL SALE OF DIAMONDS, GEMS & JEWELLERY",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		RETAIL_SALE_OF_CONSTRUCTION_MATERIALS_HARDWARE_PAINTS_AND_GLASS("RETAIL SALE OF CONSTRUCTION MATERIALS, HARDWARE, PAINTS AND GLASS",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		RETAIL_SALE_IN_SPECIALISED_STORES("RETAIL SALE IN SPECIALISED STORES",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		OTHER_RETAIL_TRADE("OTHER RETAIL TRADE",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		RETAIL_SALE_OF_SECOND_HAND_GOODS_IN_STORES("RETAIL SALE OF SECOND HAND GOODS IN STORES",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		RETAIL_TRADE_NOT_IN_STORES("RETAIL TRADE NOT IN STORES",LITERAL_WHOLESALE_AND_RETAIL_TRADE_REPAIR_OF_MOTOR_VEHICLES_MOTORCYCLES_AND_PERSONAL_HOUSEHOLD_GOODS),
		HOTELS_MOTELS_AND_RESORTS_AND_OTHER_SHORT_STAY_ACCOMMODATION("HOTELS, MOTELS AND RESORTS AND OTHER SHORT-STAY ACCOMMODATION",LITERAL_HOTELS_AND_RESTAURANTS),
		RESTAURANTS_BARS_CANTEENS_CATERERS("RESTAURANTS, BARS, CANTEENS, CATERERS",LITERAL_HOTELS_AND_RESTAURANTS),
		TRANSPORT_VIA_RAILWAYS("TRANSPORT VIA RAILWAYS",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		SCHEDULED_PASSENGER_LAND_TRANSPORT("SCHEDULED PASSENGER LAND TRANSPORT",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		NON_SCHEDULED_PASSENGER_LAND_TRANSPORT_BY_MAN_OR_ANIMAL_DRAWN_VEHICLES("NON-SCHEDULED PASSENGER LAND TRANSPORT BY MAN OR ANIMAL DRAWN VEHICLES",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		NON_SCHEDULED_PASSENGER_LAND_TRANSPORT("NON-SCHEDULED PASSENGER LAND TRANSPORT",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		FREIGHT_TRANSPORT_BY_MOTOR_VEHICLES_INCLUDING_REFRIGERATED_VANS("FREIGHT TRANSPORT BY MOTOR VEHICLES INCLUDING REFRIGERATED VANS",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		TRANSPORT_VIA_PIPELINES("TRANSPORT VIA PIPELINES",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		OCEAN_AND_COASTAL_WATER_TRANSPORT("OCEAN AND COASTAL WATER TRANSPORT",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		INLAND_WATER_TRANSPORT("INLAND WATER TRANSPORT",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		SCHEDULED_AIR_TRANSPORT("SCHEDULED AIR TRANSPORT",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		NON_SCHEDULED_AIR_TRANSPORT("NON-SCHEDULED AIR TRANSPORT",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		WAREHOUSING("WAREHOUSING",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		COLD_STORAGE("COLD STORAGE",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		SUPPORTING_SERVICES_TO_LAND_TRANSPORT("SUPPORTING SERVICES TO LAND TRANSPORT",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		SUPPORTING_SERVICES_INCIDENTAL_TO_WATERTRANSPORT("SUPPORTING SERVICES INCIDENTAL TO WATERTRANSPORT",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		SUPPORTING_SERVICES_TO_AIR_TRANSPORT("SUPPORTING SERVICES TO AIR TRANSPORT",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		ACTIVITIES_OF_TRAVEL_AGENCIES_AND_TOUR_OPERATORS("ACTIVITIES OF TRAVEL AGENCIES AND TOUR OPERATORS",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		ACTIVITIES_OF_OTHER_TRANSPORT_AGENCIES("ACTIVITIES OF OTHER TRANSPORT AGENCIES",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		POST_AND_COURIER_ACTIVITIES("POST AND COURIER ACTIVITIES",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		TELECOMMUNICATION_SERVICES("TELECOMMUNICATION SERVICES",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		PROVISION_OF_BASIC_TELECOM_SERVICES_MAINTENANCE_OF_TELECOM_NETWORK("PROVISION OF BASIC TELECOM SERVICES & MAINTENANCE OF TELECOM NETWORK",LITERAL_TRANSPORT_STORAGE_AND_COMMUNICATIONS),
		COMMERCIAL_BANKS("COMMERCIAL BANKS",LITERAL_FINANCIAL_INTERMEDIATION),
		REGIONAL_RURAL_BANKS("REGIONAL RURAL BANKS",LITERAL_FINANCIAL_INTERMEDIATION),
		CO_OPERATIVE_BANKS("CO-OPERATIVE BANKS",LITERAL_FINANCIAL_INTERMEDIATION),
		LOCAL_AREA_BANKS("LOCAL AREA BANKS",LITERAL_FINANCIAL_INTERMEDIATION),
		NEW_LOCAL_AREA_BANKS("NEW LOCAL AREA BANKS",LITERAL_FINANCIAL_INTERMEDIATION),
		PRIMARY_AGRICULTURAL_CREDIT_SOCIETIES_PACS("PRIMARY AGRICULTURAL CREDIT SOCIETIES(PACS)",LITERAL_FINANCIAL_INTERMEDIATION),
		FARMERS_SERVICE_SOCIETIES_FSS_AND_LARGE_SIZED_ADIVASI_MULTI_PURPOSE_SOCIETIES_LAMPS("FARMERS SERVICE SOCIETIES(FSS) AND LARGE SIZED ADIVASI MULTI-PURPOSE SOCIETIES(LAMPS)",LITERAL_FINANCIAL_INTERMEDIATION),
		APEX_CO_OPERATIVE_HOUSING_FINANCE_SOCIETIES("APEX CO-OPERATIVE HOUSING FINANCE SOCIETIES",LITERAL_FINANCIAL_INTERMEDIATION),
		CO_OPERATIVE_MARKETING_SOCIETIES("CO-OPERATIVE MARKETING SOCIETIES",LITERAL_FINANCIAL_INTERMEDIATION),
		LAND_DEVELOPMENT_BANKS_SLDBS_AND_PLDBS("LAND DEVELOPMENT BANKS-SLDBS AND PLDBS",LITERAL_FINANCIAL_INTERMEDIATION),
		DISTRICT_CENTRAL_CO_OPERATIVE_BANKS_DCCBS("DISTRICT CENTRAL CO-OPERATIVE BANKS(DCCBS)",LITERAL_FINANCIAL_INTERMEDIATION),
		URBAN_CO_OPERATIVE_BANKS_UCBS("URBAN CO-OPERATIVE BANKS(UCBS)",LITERAL_FINANCIAL_INTERMEDIATION),
		NEW_APEX_CO_OPERATIVE_HOUSING_FINANCE_SOCIETIES("NEW APEX CO-OPERATIVE HOUSING FINANCE SOCIETIES",LITERAL_FINANCIAL_INTERMEDIATION),
		OTHER_NON_AGRICULTURAL_CREDIT_SOCIETIES("OTHER NON-AGRICULTURAL CREDIT SOCIETIES",LITERAL_FINANCIAL_INTERMEDIATION),
		NEW_OTHER_CO_OPERATIVE_CREDIT_INSTITUTIONS("NEW OTHER CO-OPERATIVE CREDIT INSTITUTIONS",LITERAL_FINANCIAL_INTERMEDIATION),
		DEVELOPMENT_FINANCIAL_INSTITUTIONS("DEVELOPMENT FINANCIAL INSTITUTIONS",LITERAL_FINANCIAL_INTERMEDIATION),
		HOUSING_FINANCE_COMPANIES("HOUSING FINANCE COMPANIES",LITERAL_FINANCIAL_INTERMEDIATION),
		FACTORING_HIRE_PURCHASE_FINANCE_COMPANIES("FACTORING/HIRE PURCHASE FINANCE COMPANIES",LITERAL_FINANCIAL_INTERMEDIATION),
		CHIT_FUNDS_KURI_COMPANIES("CHIT FUNDS/KURI COMPANIES",LITERAL_FINANCIAL_INTERMEDIATION),
		SAVING_LOAN_COMPANIES("SAVING & LOAN COMPANIES",LITERAL_FINANCIAL_INTERMEDIATION),
		NIDHIS_MUTUAL_BENEFIT_FINANCIAL_COMPANIES("NIDHIS/MUTUAL BENEFIT FINANCIAL COMPANIES",LITERAL_FINANCIAL_INTERMEDIATION),
		NON_OPERATING_FINANCIAL_HOLDING_COMPANIES_INVESTMENT_COMPANIES("NON-OPERATING FINANCIAL HOLDING COMPANIES(INVESTMENT COMPANIES)",LITERAL_FINANCIAL_INTERMEDIATION),
		FINANCIAL_LEASING_COMPANIES_EQUIPMENT_LEASING_COMPANIES("FINANCIAL LEASING COMPANIES(EQUIPMENT LEASING COMPANIES)",LITERAL_FINANCIAL_INTERMEDIATION),
		OTHER_NBFCS("OTHER NBFCS",LITERAL_FINANCIAL_INTERMEDIATION),
		AGRICULTURAL_FINANCE_CORPORATIONS("AGRICULTURAL FINANCE CORPORATIONS",LITERAL_FINANCIAL_INTERMEDIATION),
		MUTUAL_FUNDS_INCLUDING_UNIT_TRUST_OF_INDIA("MUTUAL FUNDS INCLUDING UNIT TRUST OF INDIA",LITERAL_FINANCIAL_INTERMEDIATION),
		SHROFFS_AND_OTHER_INDIGENOUS_BANKERS("SHROFFS AND OTHER INDIGENOUS BANKERS",LITERAL_FINANCIAL_INTERMEDIATION),
		INDIRECT_FINANCE_TO_SMALL_AND_MICRO_LITERAL_MANUFACTURING_UNITS("INDIRECT FINANCE TO SMALL AND MICRO LITERAL_MANUFACTURING UNITS",LITERAL_FINANCIAL_INTERMEDIATION),
		INDIRECT_FINANCE_TO_HOUSING_SECTOR("INDIRECT FINANCE TO HOUSING SECTOR",LITERAL_FINANCIAL_INTERMEDIATION),
		OTHER_FINANCIAL_INTERMEDIATION("OTHER FINANCIAL INTERMEDIATION",LITERAL_FINANCIAL_INTERMEDIATION),
		LIFE_INSURANCE("LIFE INSURANCE",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		NON_LIFE_INSURANCE("NON-LIFE INSURANCE",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		PENSION_FUNDING("PENSION FUNDING",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		AGRO_INDUSTRIES_CORPORATIONS("AGRO-INDUSTRIES CORPORATIONS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		SECURITIES_TRADING_COMPANIES_BROKING_FIRMS("SECURITIES TRADING COMPANIES/BROKING FIRMS",LITERAL_FINANCIAL_INTERMEDIATION),
		INDUSTRIAL_DEVELOPMENT_BOARDS_CORPORATIONS_FEDERATIONS("INDUSTRIAL DEVELOPMENT BOARDS/CORPORATIONS/FEDERATIONS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MERCHANT_BANKING_FINANCIAL_SERVICES_COMPANIES("MERCHANT BANKING/FINANCIAL SERVICES COMPANIES",LITERAL_FINANCIAL_INTERMEDIATION),
		LOANS_FOR_ACTIVITIES_AUXILIARY_TO_FINANCIAL_INTERMEDIATION_EXCEPT_INSURANCE_AND_PENSION_FUNDING("LOANS FOR ACTIVITIES AUXILIARY TO FINANCIAL INTERMEDIATION EXCEPT INSURANCE AND PENSION FUNDING",LITERAL_FINANCIAL_INTERMEDIATION),
		NEW_LOANS_FOR_ACTIVITIES_AUXILIARY_TO_INSURANCE_AN("NEW LOANS FOR ACTIVITIES AUXILIARY TO INSURANCE AN",LITERAL_FINANCIAL_INTERMEDIATION),
		REAL_ESTATE_ACTIVITIES_INCLUDING_LEASING_OF_REAL_PROPERTY("REAL ESTATE ACTIVITIES INCLUDING LEASING OF REAL PROPERTY",LITERAL_REAL_ESTATE_RENTING_AND_BUSINESS_ACTIVITIES),
		RENTING_OF_LAND_WATER_AND_AIR_TRANSPORT_EQUIPMENTS("RENTING OF LAND, WATER AND AIR TRANSPORT EQUIPMENTS",LITERAL_REAL_ESTATE_RENTING_AND_BUSINESS_ACTIVITIES),
		RENTING_OF_CONSTRUCTION_AND_CIVIL_ENGINEERING_AND_OFFICE_MACHINERY_AND_EQUIPMENTS("RENTING OF CONSTRUCTION AND CIVIL ENGINEERING AND OFFICE MACHINERY AND EQUIPMENTS",LITERAL_REAL_ESTATE_RENTING_AND_BUSINESS_ACTIVITIES),
		RENTING_OF_PERSONAL_AND_HOUSEHOLD_GOODS("RENTING OF PERSONAL AND HOUSEHOLD GOODS",LITERAL_REAL_ESTATE_RENTING_AND_BUSINESS_ACTIVITIES),
		LOANS_FOR_ACTIVITIES_AUXILIARY_TO_INSURANCE_AND_PENSION_FUNDING("LOANS FOR ACTIVITIES AUXILIARY TO INSURANCE AND PENSION FUNDING",LITERAL_FINANCIAL_INTERMEDIATION),
		SOFTWARE_CONSULTANCY_AND_SUPPLY("SOFTWARE CONSULTANCY AND SUPPLY",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		DATA_PROCESSING_AND_DATABASE_ACTIVITIES("DATA PROCESSING AND DATABASE ACTIVITIES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		IT_PLACEMENT_SERVICES("IT PLACEMENT SERVICES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		OTHER_COMPUTER_RELATED_ACTIVITIES("OTHER COMPUTER RELATED ACTIVITIES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		RESEARCH_AND_EXPERIMENTAL_DEVELOPMENT_ON_NATURAL_SOCIAL_SCIENCES_HUMANITIES_AND_ENGINEERING("RESEARCH AND EXPERIMENTAL DEVELOPMENT ON NATURAL/SOCIAL SCIENCES, HUMANITIES AND ENGINEERING",LITERAL_HEALTH_AND_SOCIAL_WORK),
		PROFESSIONAL_SERVICES("PROFESSIONAL SERVICES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		ARCHITECTURAL_ENGINEERING_AND_OTHER_TECHNICAL_ACTIVITIES("ARCHITECTURAL, ENGINEERING AND OTHER TECHNICAL ACTIVITIES.",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		ADVERTISING_AND_PUBLICITY_CONCERNS("ADVERTISING AND PUBLICITY CONCERNS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MERGER_ACQUISITION_AND_RE_STRUCTURING_OF_COMPANIES("MERGER, ACQUISITION AND RE-STRUCTURING OF COMPANIES.",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		LABOUR_RECRUITMENT("LABOUR RECRUITMENT",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		ACTIVITIES_OF_UNION_STATE_LOCAL_AND_QUASI_GOVERNMENT_AGENCIES("ACTIVITIES OF UNION, STATE, LOCAL AND QUASI-GOVERNMENT AGENCIES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		GENERAL_PRIMARY_AND_SECONDARY_EDUCATION("GENERAL PRIMARY AND SECONDARY EDUCATION",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		HIGHER_EDUCATION("HIGHER EDUCATION",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		TECHNICAL_AND_VOCATIONAL_EDUCATION("TECHNICAL AND VOCATIONAL EDUCATION",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		HOSPITAL_ACTIVITIES("HOSPITAL ACTIVITIES",LITERAL_HEALTH_AND_SOCIAL_WORK),
		MEDICAL_AND_DENTAL_PRACTICE_ACTIVITIES_OTHER_HUMAN_HEALTH_ACTIVITIES("MEDICAL AND DENTAL PRACTICE ACTIVITIES & OTHER HUMAN HEALTH ACTIVITIES",LITERAL_HEALTH_AND_SOCIAL_WORK),
		VETERINARY_ACTIVITIES("VETERINARY ACTIVITIES",LITERAL_HEALTH_AND_SOCIAL_WORK),
		SOCIAL_WORK("SOCIAL WORK",LITERAL_HEALTH_AND_SOCIAL_WORK),
		SEWAGE_AND_REFUSE_DISPOSAL_SANITATION_AND_SIMILAR_ACTIVITIES("SEWAGE AND REFUSE DISPOSAL, SANITATION AND SIMILAR ACTIVITIES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		ACTIVITIES_OF_BUSINESS_EMPLOYERS_AND_PROFESSIONAL_ORGANISATIONS("ACTIVITIES OF BUSINESS, EMPLOYERS AND PROFESSIONAL ORGANISATIONS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		SERVICES_RENDERED_BY_RELIGIOUS_POLITICAL_AND_OTHER_MEMBERSHIP_ORGANISATIONS("SERVICES RENDERED BY RELIGIOUS, POLITICAL AND OTHER MEMBERSHIP ORGANISATIONS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MOTION_PICTURE_AND_VIDEO_PRODUCTION_AND_DISTRIBUTION("MOTION PICTURE AND VIDEO PRODUCTION AND DISTRIBUTION",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		OTHER_ENTERTAINMENT_ACTIVITIES("OTHER ENTERTAINMENT ACTIVITIES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		NEWS_AGENCY_ACTIVITIES("NEWS AGENCY ACTIVITIES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		LIBRARY_ARCHIVES_MUSEUMS_AND_OTHER_CULTURAL_ACTIVITIES("LIBRARY, ARCHIVES, MUSEUMS AND OTHER CULTURAL ACTIVITIES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		SPORTING_AND_OTHER_RECREATIONAL_ACTIVITIES("SPORTING AND OTHER RECREATIONAL ACTIVITIES",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		ARTISANS_AND_CRAFTSMEN("ARTISANS AND CRAFTSMEN",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		SERVICES_OF_OTHER_SELF_EMPLOYED_PERSONS("SERVICES OF OTHER SELF EMPLOYED PERSONS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		BPOS_BUSINESS_PROCESS_OUTSOURCING_CALL_CENTRES("BPOS(BUSINESS PROCESS OUTSOURCING CALL CENTRES,",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		REPAIRS_OF_PERSONAL_AND_HOUSEHOLD_GOODS("REPAIRS OF PERSONAL AND HOUSEHOLD GOODS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MAINTENANCE_AND_REPAIR_OF_OFFICE_ACCOUNTING_AND_COMPUTING_MACHINERY("MAINTENANCE AND REPAIR OF OFFICE, ACCOUNTING AND COMPUTING MACHINERY",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MAINTENANCE_AND_REPAIR_OF_COMPUTER_HARDWARE_PERIPHERALS("MAINTENANCE AND REPAIR OF COMPUTER HARDWARE & PERIPHERALS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MAINTENANCE_OF_COMPUTER_SOFTWARE("MAINTENANCE OF COMPUTER SOFTWARE",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MAINTENANCE_AND_REPAIR_OF_COMMUNICATION_EQUIPMENTS("MAINTENANCE AND REPAIR OF COMMUNICATION EQUIPMENTS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		MAINTENANCE_AND_REPAIR_OF_OTHER_ITEMS("MAINTENANCE AND REPAIR OF OTHER ITEMS",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		STAFF_HOUSING_LOANS("STAFF HOUSING LOANS",LITERAL_FINANCIAL_INTERMEDIATION),
		HOUSING_LOANS("HOUSING LOANS",LITERAL_FINANCIAL_INTERMEDIATION),
		LOANS_FOR_PURCHASE_OF_MOTOR_VEHICLE_BY_INDIVIDUALS_FOR_PERSONAL_USE_OTHER_THAN_STAFF("LOANS FOR PURCHASE OF MOTOR VEHICLE BY INDIVIDUALS FOR PERSONAL USE - OTHER THAN STAFF",LITERAL_FINANCIAL_INTERMEDIATION),
		LOANS_FOR_PURCHASE_OF_CONSUMER_DURABLE_GOODS_EXCLUDING_THOSE_GIVEN_TO_STAFF_MEMBERS("LOANS FOR PURCHASE OF CONSUMER DURABLE GOODS EXCLUDING THOSE GIVEN TO STAFF MEMBERS",LITERAL_FINANCIAL_INTERMEDIATION),
		EDUCATION_LOANS_OTHER_THAN_STAFF("EDUCATION LOANS - OTHER THAN STAFF",LITERAL_FINANCIAL_INTERMEDIATION),
		LOANS_TO_STAFF_FOR_PURPOSES_OTHER_THAN_HOUSING("LOANS TO STAFF FOR PURPOSES OTHER THAN HOUSING",LITERAL_FINANCIAL_INTERMEDIATION),
		PERSONAL_LOANS_OTHER_THAN_TO_STAFF_MEMBERS("PERSONAL LOANS OTHER THAN TO STAFF MEMBERS",LITERAL_FINANCIAL_INTERMEDIATION),
		LOANS_ADVANCED_THROUGH_CREDIT_CARDS_OTHER_THAN_STAFF("LOANS ADVANCED THROUGH CREDIT CARDS - OTHER THAN STAFF",LITERAL_FINANCIAL_INTERMEDIATION),
		OTHER_PERSONAL_LOANS_OTHER_THAN_STAFF("OTHER PERSONAL LOANS - OTHER THAN STAFF",LITERAL_FINANCIAL_INTERMEDIATION),
		STAFF_HOUSING_LOANS_INCLUDING_THE_LOANS_TO_CO_OPE("STAFF HOUSING LOANS(INCLUDING THE LOANS TO CO-OPE",LITERAL_FINANCIAL_INTERMEDIATION),
		STAFF_VEHICLE_LOANS("STAFF VEHICLE LOANS",LITERAL_FINANCIAL_INTERMEDIATION),
		STAFF_LOANS_FOR_PURCHASE_OF_CONSUMER_DURABLE_GOODS_GIVEN("STAFF LOANS FOR PURCHASE OF CONSUMER DURABLE GOODS GIVEN",LITERAL_OTHER_COMMUNITY_SOCIAL_AND_PERSONAL_SERVICE_ACTIVITIES),
		STAFF_EDUCATION_LOANS("STAFF EDUCATION LOANS",LITERAL_FINANCIAL_INTERMEDIATION),
		STAFF_CREDIT_CARDS("STAFF CREDIT CARDS",LITERAL_FINANCIAL_INTERMEDIATION),
		OTHER_PERSONAL_LOANS_TO_STAFF_MEMBERS("OTHER PERSONAL LOANS TO STAFF MEMBERS",LITERAL_FINANCIAL_INTERMEDIATION),
		HOUSING_LOANS_OTHER_THAN_STAFF("HOUSING LOANS - OTHER THAN STAFF",LITERAL_FINANCIAL_INTERMEDIATION),
		LOANS_FOR_PURCHASE_OF_MOTOR_VEHICLE_TWO_WHEELERS_OTHER_THAN_STAFF("LOANS FOR PURCHASE OF MOTOR VEHICLE & TWO WHEELERS - OTHER THAN STAFF",LITERAL_FINANCIAL_INTERMEDIATION),
		LOANS_FOR_PURCHASE_OF_CONSUMER_DURABLE_GOODS_OTHER_THAN_STAFF("LOANS FOR PURCHASE OF CONSUMER DURABLE GOODS - OTHER THAN STAFF",LITERAL_FINANCIAL_INTERMEDIATION),
		FOR_INVESTMENT_IN_SHARES_OTHER_THAN_STAFF("FOR INVESTMENT IN SHARES - OTHER THAN STAFF",LITERAL_FINANCIAL_INTERMEDIATION),
		ALL_OTHER_LOANS_NOT_CLASSIFIED_ELSEWHERE_OR_ACTIVITIES_NOT_ADEQUATELY_DESCRIBED_THIS_CODE_SHOULD_BE_USED_SPARINGLY("ALL OTHER LOANS NOT CLASSIFIED ELSEWHERE OR ACTIVITIES NOT ADEQUATELY DESCRIBED(THIS CODE SHOULD BE USED SPARINGLY)",LITERAL_FINANCIAL_INTERMEDIATION);

		
		private String value;
		private String hmValue;

		private ClassOfActivityTypeEnum(String value,String hmValue) {
			this.value = value;
			this.hmValue = hmValue;
		}

		public String getValue() {
			return this.value;
		}

		public String getHmValue() {
			return hmValue;
		}

		public static ClassOfActivityTypeEnum fromValue(String v) {
			for (ClassOfActivityTypeEnum c : ClassOfActivityTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
		public static ClassOfActivityTypeEnum fromHmValue(String v) {
			for (ClassOfActivityTypeEnum c : ClassOfActivityTypeEnum.values()) {
				if (c.hmValue.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
	}
	
	public enum KeyPersonTypeEnum {
		FATHER("K01","Father"),
		HUSBAND("K02","Husband"),
		MOTHER("K03","Mother"),
		SON("K04","Son"),
		DAUGHTER("K05","Daughter"),
		WIFE("K06","Wife"),
		BROTHER("K07","Brother"),
		MOTHER_IN_LAW("K08","Mother In law"),
		FATHER_IN_LAW("K09","Father In law"),
		DAUGHTER_IN_LAW("K10","Daughter In law"),
		SISTER_IN_LAW("K11","Sister In law"),
		SON_IN_LAW("K12","Son In law"),
		BROTHER_IN_LAW("K13","Brother In law"),
		OTHER("K15",LITERAL_OTHER);

		private String id;
		private String value;

		private KeyPersonTypeEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static KeyPersonTypeEnum fromValue(String v) {
			for (KeyPersonTypeEnum c : KeyPersonTypeEnum.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static KeyPersonTypeEnum fromId(String v) {
			for (KeyPersonTypeEnum c : KeyPersonTypeEnum.values()) {
				if (c.id.equals(v) || Integer.parseInt(v) == Integer.parseInt(c.id)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);

		}
	}
	
	public enum OwenershipIndicator {
		INDIVIDUAL(1,LITERAL_INDIVIDUAL),
		AUTHORIZED(2,"Authorized User"),
		GUARANTOR(3,"Guarantor"),
		JOINT(4,"Joint");
		private Integer id;
		private String value;

		private OwenershipIndicator(Integer id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public Integer getId() {
			return this.id;
		}

		public static OwenershipIndicator fromValue(String v) {
			for (OwenershipIndicator c : OwenershipIndicator.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static OwenershipIndicator fromId(Integer v) {
			for (OwenershipIndicator c : OwenershipIndicator.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v.toString());
		}
	}
	
	public enum SuitFiledOrWilfulDefault {
		NO_SUIT_FILED("00","No Suit Filed"),
		AUTHORIZED("01"," Suit filed"),
		GUARANTOR("02","Wilful default"),
		JOINT("03","Suit filed (Wilful default)");
		
		private String id;
		private String value;

		private SuitFiledOrWilfulDefault(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static SuitFiledOrWilfulDefault fromValue(String v) {
			for (SuitFiledOrWilfulDefault c : SuitFiledOrWilfulDefault.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static SuitFiledOrWilfulDefault fromId(Integer v) {
			for (SuitFiledOrWilfulDefault c : SuitFiledOrWilfulDefault.values()) {
				if (Integer.parseInt(c.id) == v) {
					return c;
				}
			}
			throw new IllegalArgumentException(v.toString());
		}
	}
	
	public interface ExperianResponseCode {
		public static final String SUCCESS = "0";
		public static final String LIST_OF_SIMILARS = "2";
		public static final String NO_RECORD_FOUND = "4";
		public static final String NORMAL_RESPONSE = "Normal Response";
	}
	
	public interface ExperianProduct {
		public static final String INBCIR001 = "INBCIR001";
		public static final String INCCIR002 = "INCCIR002";
		public static final String INBPCL001 = "INBPCL001";
		public static final String INBCIR003 = "INBCIR003";
		public static final String SIDBI_103 = "103";
	}
	
	public interface ExperianProductFlag {
		public static final String SIDBI_103 = "103";
	}
	
	public interface ExperianPurposeCode {
		public static final String Application_for_New_Credit_OR_Loan  = "1";
		public static final String Application_for_Credit_OR_Loan_Increase  = "2";
		public static final String Credit_Review = "3";
		public static final String Collection_of_Debt  = "4";
		public static final String Debt_Purchase = "5";
		public static final String Insurance_Underwriting  = "8";
	}

	public interface ExperianEnquirySearchType {
		public static final String Precise_Search = "1";
		public static final String Expanded_Search  = "2";
	}
	
	public interface ExperianEnquiryAccountType {
		
		public static final String Cash_Credit = "10";
		public static final String Overdraft = "020";
		public static final String Demand_Loan = "030";
		public static final String Loan_Extended_Through_Credit_Cards = "031";
		public static final String Medium_Term_Loan = "041";
		public static final String Long_Term_Loan = "042";
		public static final String Packing_Credit = "050";
		public static final String Export_Bills_Purchased = "061";
		public static final String Export_Bills_Discounted = "062";
		public static final String Export_Bills_Advanced_Against = "063";
		public static final String Advances_Against_Export_Cash_Incentives_and_Duty_Draw_back_Claims = "064";
		public static final String Inland_Bills_Purchased = "071";
		public static final String Inland_Bills_Discounted = "072"; 
		
	}
	
	public enum ExperianAccountType {
		CREDIT_LINE_OPEN("10","Credit Line - Open","NA"),
		LEASE_OPERATING("100","Lease - Operating","Term Loan"),
		HIRE_PURCHASE("101","Hire Purchase","Term Loan"),
		LEASE_REAL_ESTATE("102","Lease - Real Estate","Term Loan"),
		LEASE_RENTAL("103","Lease - Rental","Term Loan"),
		LEASE_RENTAL_AGREEMENT("104","Lease - Rental Agreement","Term Loan"),
		LEASE_SERVICE_ESTABLISH("105","Lease - Service Establish","Term Loan"),
		LEASE_TRUE_FAIR_MARKET_VALUE("106","Lease - TRUE fair market value","Term Loan"),
		CREDIT_LINE_REAL_ESTATE_EQUITY("11","Credit Line - Real Estate Equity","Working Capital"),
		LOAN("112","Loan","Term Loan"),
		LOAN_AGAINST_INSURANCE_POLICY("113","Loan - Against Insurance Policy","NA"),
		LOAN_ASSET_BASED("114","Loan - Asset Based","Term Loan"),
		LOAN_CURRENCY_EXCHANGE("116","Loan - Currency Exchange","Term Loan"),
		LOAN_DEMAND_SECURED("117","Loan - Demand Secured","Term Loan"),
		LOAN_DEMAND_UN_SECURED("118","Loan - Demand Un-Secured","Term Loan"),
		LOAN_ESTATE("119","Loan - Estate","Term Loan"),
		CREDIT_LINE_TRUST("12","Credit Line - Trust","Working Capital"),
		LOAN_FINANCIAL_SERVICES("120","Loan - Financial Services","Working Capital"),
		OVERDRAFT("121","Overdraft","Working Capital"),
		LOAN_PAYDAY("122","Loan - Payday","Non-Fund based limits"),
		LOAN_PERSONAL_CASH("123","Loan - Personal Cash","NA"),
		LOAN_PRIVATE("124","Loan - Private","NA"),
		LOAN_REAL_ESTATE("125","Loan - Real Estate","Term Loan"),
		LOAN_RECEIVABLES_FINANCING("126","Loan - Receivables Financing","Term Loan"),
		LOAN_RETAIL("127","Loan - Retail","Term Loan"),
		SHORT_TERM_LOAN_LESS_THAN_1_YEAR("128","Short Term Loan (less than 1 year)","Working Capital"),
		LOAN_SMALL_BUSINESS("129","Loan - Small Business","Term Loan"),
		CREDIT_MERCHANT_CARD("13","Credit Merchant Card","NA"),
		LOAN_STUDENT("130","Loan - Student","Term Loan"),
		LOAN_SYNDICATED("131","Loan - Syndicated","Term Loan"),
		LOAN_TITLE("132","Loan - Title","Term Loan"),
		LOAN_UNSECURED("133","Loan - Unsecured","Term Loan"),
		LOAN_VACATION("134","Loan - Vacation","NA"),
		CREDIT_OPEN_LINE_SECURED("14","Credit Open Line Secured","Working Capital"),
		SERVICE_CABLE_INTERNET("141","Service - Cable/Internet","Working Capital"),
		SERVICE_GAS_ELECTRICITY_WATER("142","Service - Gas - Electricity - Water","Working Capital"),
		SERVICE_HOME_SECURITY("143","Service - Home Security","Working Capital"),
		SERVICE_TELEPHONE("144","Service - Telephone","Working Capital"),
		SERVICE_TELEPHONE_CELL("145","Service - Telephone Cell","Working Capital"),
		CREDIT_OPEN_LINE_UN_SECURED("15","Credit Open Line Un-Secured","Working Capital"),
		SUPPLIER_COMPUTER_EQUIPMENT("151","Supplier - Computer Equipment","Working Capital"),
		SUPPLIER_EMPLOYMENT_PRACTICES("152","Supplier - Employment Practices","Working Capital"),
		SUPPLIER_FAX_COPIER("153","Supplier - Fax/Copier","Working Capital"),
		SUPPLIER_FURNITURE("154","Supplier - Furniture","Working Capital"),
		SUPPLIER_MANUFACTURING("155","Supplier - Manufacturing","Working Capital"),
		SUPPLIER_MATERIAL_HANDLING("156","Supplier - Material Handling","Working Capital"),
		SUPPLIER_CONSTRUCTION_EQUIPMENT("157","Supplier - Construction Equipment","Working Capital"),
		SUPPLIER_MEDICAL_EQUIPMENT("158","Supplier - Medical Equipment","Working Capital"),
		CONTRACTS_ON_PAST_PERFORMANCE_EXPORTS("159","Contracts on Past Performance Exports","NA"),
		CREDIT_PREMIUM_CARD("16","Credit Premium Card","NA"),
		CONTRACTS_ON_PAST_PERFORMANCE_IMPORTS("160","Contracts on Past Performance Imports","NA"),
		DERIVATIVES("161","Derivatives","NA"),
		DERIVATIVES_ANY_COMPLEX_DERIVATIVE_LOAN_INVOLVING_FOREIGN_CURRENCY_WITH_OPTION("162","Derivatives - Any Complex Derivative Loan Involving Foreign Currency with Option","NA"),
		LOAN_COMPENSATION("115","Loan - Compensation","NA"),
		DERIVATIVES_PLAIN_VANILLA_FOREIGN_CURRENCY_OPTION_INCLUDING_INR_CROSS_CURRENCY("164","Derivatives - Plain Vanilla Foreign Currency Option (Including INR Cross Currency)","NA"),
		DERIVATIVES_PLAIN_VANILLA_FOREX_FORWARD_CONTRACTS("165","Derivatives - Plain Vanilla Forex Forward Contracts","NA"),
		DERIVATIVES_PLAIN_VANILLA_INTEREST_RATE_SWAP_ALL_INCLUDING_INR_AS_COUPON("166","Derivatives - Plain Vanilla Interest Rate Swap (All including INR as Coupon)","NA"),
		MICROFINANCE_BUSINESS("167","Microfinance - Business","Term Loan"),
		MICROFINANCE_HOUSING_LOAN("168","Microfinance - Housing Loan","NA"),
		MICROFINANCE_PERSONAL("169","Microfinance - Personal","NA"),
		CREDIT_RETAIL_CARD("17","Credit Retail Card","NA"),
		MICROFINANCE_OTHERS("170","Microfinance - Others","Term Loan"),
		INSTALMENT_LOAN_COMMERCIAL_VEHICLE("171","Instalment Loan - Commercial Vehicle","NA"),
		INSTALMENT_LOAN_COMMERCIAL_VEHICLE_COPY("172","Instalment Loan - Commercial Vehicle","NA"),
		INSTALMENT_LOAN_TWO_WHEELER("173","Instalment Loan - Two-Wheeler","NA"),
		BANK_GUARANTEE("174","Bank Guarantee","Non-Fund based limits"),
		BUSINESS_LOAN_AGAINST_BANK_DEPOSITS("175","Business Loan Against Bank Deposits","Term Loan"),
		BUSINESS_LOAN_GENERAL("176","Business Loan - General","Term Loan"),
		BUSINESS_LOAN_PRIORITY_SECTOR_SMALL_BUSINESS("177","Business Loan - Priority Sector - Small Business","Term Loan"),
		BUSINESS_LOAN_PRIORITY_SECTOR_AGRICULTURE("178","Business Loan - Priority Sector - Agriculture","Term Loan"),
		BUSINESS_LOAN_PRIORITY_SECTOR_OTHERS("179","Business Loan - Priority Sector - Others","Term Loan"),
		CREDIT_SECURED_CARD("18","Credit Secured Card","NA"),
		BUSINESS_LOAN_UNSTRUCTURED("180","Business Loan - Unstructured","Term Loan"),
		CREDIT_FACILITY_NON_FUNDED("181","Credit Facility - Non-Funded","Non-Fund based limits"),
		DEFERRED_PAYMENT_GUARANTEE("182","Deferred Payment Guarantee","NA"),
		LETTERS_OF_CREDIT("183","Letters of Credit","Non-Fund based limits"),
		LOAN_AGAINST_BANK_DEPOSITS("184","Loan Against Bank Deposits","Term Loan"),
		LOAN_AGAINST_SHARES_SECURITIES("185","Loan Against Shares/Securities","NA"),
		LOAN_EXTENDED_THROUGH_CREDIT_CARDS("186","Loan Extended Through Credit Cards","NA"),
		LOAN_TO_PROFESSIONAL("187","Loan to Professional","NA"),
		LOAN_COMMERCIAL_CASH_CREDIT("188","Loan - Commercial Cash Credit","Working Capital"),
		LOAN_CONSUMER("189","Loan - Consumer","NA"),
		CREDIT_SINGLE_LIMITED_PURPOSE_CARD("19","Credit Single - Limited Purpose Card","NA"),
		LOAN_DEMAND("190","Loan - Demand","Term Loan"),
		LOAN_GOLD("191","Loan - Gold","Term Loan"),
		LOAN_LONG_TERM("192","Loan - Long Term","Term Loan"),
		LOAN_MEDIUM_TERM("193","Loan - Medium Term","Term Loan"),
		LOAN_PACKING_CREDIT("194","Loan - Packing Credit","Non-Fund based limits"),
		LOAN_PROPERTY("195","Loan - Property","Term Loan"),
		LOAN_STAFF("196","Loan - Staff","Term Loan"),
		NON_FUNDED_CREDIT_FACILITY_GENERAL("197","Non-Funded Credit Facility - General","Non-Fund based limits"),
		NON_FUNDED_CREDIT_FACILITY_PRIORITY_SECTOR_SMALL_BUSINESS("198","Non-Funded Credit Facility - Priority Sector - Small Business","Non-Fund based limits"),
		NON_FUNDED_CREDIT_FACILITY_PRIORITY_SECTOR_AGRICULTURE("199","Non-Funded Credit Facility - Priority Sector - Agriculture","Non-Fund based limits"),
		DERIVATIVES_COMPLEX_INTEREST_RATE_DERIVATIVE_WITH_OPTIONALITIES("163","Derivatives - Complex Interest Rate Derivative with Optionalities","NA"),
		NON_FUNDED_CREDIT_FACILITY_PRIORITY_SECTOR_OTHERS("200","Non-Funded Credit Facility - Priority Sector - Others","Non-Fund based limits"),
		ADVANCES_AGAINST_EXPORT_CASH_INCENTIVES_AND_DUTY_DRAW_BACK_CLAIMS("201","Advances Against Export Cash Incentives and Duty Draw-back Claims","NA"),
		ADVANCES_AGAINST_IMPORT_BILLS("202","Advances Against Import Bills","NA"),
		AGGREGATION_OF_ALL_BORROWINGS_DUE_TO_FILING_OF_SUIT("203","Aggregation of all borrowings due to Filing of Suit","NA"),
		AGGREGATION_OF_ALL_FUND_BASED_FACILITIES("204","Aggregation of All Fund Based Facilities","NA"),
		AGGREGATION_OF_ALL_NON_FUND_BASED_FACILITIES("205","Aggregation of All Non Fund Based Facilities","NA"),
		EXPORT_BILLS_ADVANCED_AGAINST("206","Export Bills Advanced Against","NA"),
		EXPORT_BILLS_DISCOUNTED("207","Export Bills Discounted","NA"),
		EXPORT_BILLS_PURCHASED("208","Export Bills Purchased","NA"),
		FACILITIES_INTERCHANGE_BETWEEN_FUND_AND_NON_FUND_BASED("209","Facilities Interchange Between Fund and Non Fund Based","NA"),
		FOREIGN_CURRENCY_CHEQUES_TCS_DDS_TTS_MTS_PURCHASED("210","Foreign Currency Cheques - TCs/DDs/TTs/MTs Purchased","NA"),
		INLAND_BILLS_DISCOUNTED("211","Inland Bills Discounted","NA"),
		INLAND_BILLS_PURCHASED("212","Inland Bills Purchased","NA"),
		CORPORATE_CREDIT_CARD("213","Corporate Credit Card","NA"),
		CREDIT_CARD_FLEET("214","Credit Card - Fleet","NA"),
		TELCO_BROADBAND("215","Telco Broadband","NA"),
		TELCO_LANDLINE("216","Telco Landline","NA"),
		TELCO_WIRELESS("217","Telco Wireless","NA"),
		LEASE_FINANCE("218","Lease - Finance","Term Loan"),
		LEASING_OTHER("219","Leasing - Other","Term Loan"),
		SECURED_CREDIT_CARD("220","Secured Credit Card","NA"),
		USED_CAR_LOAN("221","Used Car Loan","Term Loan"),
		CONSTRUCTION_EQUIPMENT_LOAN("222","Construction Equipment Loan","Term Loan"),
		TRACTOR_LOAN("223","Tractor Loan","Term Loan"),
		KISAN_CREDIT_CARD("224","Kisan Credit Card","NA"),
		LOAN_ON_CREDIT_CARD("225","Loan on Credit Card","NA"),
		PRIME_MINISTER_JAAN_DHAN_YOJANA_OVERDRAFT("226","Prime Minister Jaan Dhan Yojana - Overdraft","Working Capital"),
		MUDRA_LOANS_SHISHU_KISHOR_TARUN("227","Mudra Loans - Shishu / Kishor / Tarun","Term Loan"),
		BUSINESS_LOAN_UNSECURED("228","Business Loan - Unsecured","Term Loan"),
		AUTO_LOAN_COMMERCIAL("229","Auto Loan Commercial","Term Loan"),
		PROPERTY_LOAN_COMMERCIAL("230","Property Loan Commercial","Term Loan"),
		GOLD_LOAN_COMMERCIAL("231","Gold Loan Commercial","Term Loan"),
		LOAN_AGAINST_SHARES_SECURITIES_COPY("232","LOAN AGAINST SHARES/SECURITIES","NA"),
		HEALTHCARE_FINANCE("233","Healthcare Finance","NA"),
		INFRASTRUCTURE_FINANCE("234","Infrastructure Finance","NA"),
		FACTORING("235","FACTORING","NA"),
		COMMERCIAL_PAPERS("236","Commercial Papers","Term Loan"),
		NCD_NON_CONVERTIBLE_DEBENTURES("237","NCD - Non Convertible Debentures","NA"),
		UNHEDGED_FOREIGN_CURRENCY_EXPOSURE("238","Unhedged Foreign Currency Exposure","NA"),
		CREDIT_STORED_VALUE_SMART_CARD("20","Credit Stored-Value Smart Card","NA"),
		PRADHAN_MANTRI_AWAS_YOJANA("240","Pradhan Mantri Awas Yojana","Term Loan"),
		CREDIT_CO_BRANDED_CREDIT_CARD("3","Credit Co-Branded Credit Card","NA"),
		GOVERNMENT_ADVANCES("30","Government Advances","NA"),
		GOVERNMENT_AUDIT("31","Government Audit","NA"),
		GOVERNMENT_CLAIM("32","Government Claim","NA"),
		GOVERNMENT_DAMAGES("33","Government Damages","NA"),
		GOVERNMENT_FEE("34","Government Fee","NA"),
		GOVERNMENT_FINE("35","Government Fine","NA"),
		GOVERNMENT_FORFEITURE("36","Government Forfeiture","NA"),
		GOVERNMENT_OVERPAYMENT("37","Government Overpayment","NA"),
		GOVERNMENT_PENALTY("38","Government - Penalty","NA"),
		GOVERNMENT_RENT("39","Government Rent","NA"),
		CREDIT_AFFINITY_CREDIT_CARD("4","Credit Affinity Credit Card","NA"),
		GOVERNMENT_ROYALTY("40","Government Royalty","NA"),
		GOVERNMENT_SALES("41","Government Sales","NA"),
		GOVERNMENT_POLICY_STD("44","Government Policy Std","NA"),
		INSTALLMENT_LOAN_AUTOMOBILE("47","Installment Loan - Automobile","Term Loan"),
		INSTALLMENT_LOAN_BOAT("48","Installment Loan - Boat/R.V","Term Loan"),
		INSTALLMENT_LOAN_BUILDING("49","Installment Loan - Building","Term Loan"),
		CREDIT_CARD("5","Credit Card","NA"),
		INSTALLMENT_LOAN_COMMERCIAL_FLEET("50","Installment Loan - Commercial Fleet","NA"),
		INSTALLMENT_LOAN_CRISIS("51","Installment Loan - Crisis","NA"),
		INSTALLMENT_LOAN_DEBT_CONSOLIDATION("52","Installment Loan - Debt Consolidation","NA"),
		INSTALLMENT_LOAN_HOME_CONSTRUCTION("53","Installment Loan - Home Construction","NA"),
		INSTALLMENT_LOAN_HOME_IMPROVEMENT("54","Installment Loan - Home Improvement","NA"),
		INSTALLMENT_LOAN_LAND("55","Installment Loan - Land","NA"),
		INSTALLMENT_LOAN_LONG_TERM_INSURANCE("56","Installment Loan - Long Term Insurance","NA"),
		INSTALLMENT_LOAN_MEDICAL_DEBT("57","Installment Loan - Medical Debt","NA"),
		INSTALLMENT_LOAN_MORTGAGE("58","Installment Loan - Mortgage","NA"),
		INSTALLMENT_LOAN_REAL_ESTATE_EQUITY("59","Installment Loan - Real Estate Equity","NA"),
		CREDIT_CHARGE_CARD("6","Credit Charge Card","NA"),
		INSTALLMENT_LOAN_SECOND_MORTGAGE("60","Installment Loan - Second Mortgage","NA"),
		INSTALLMENT_LOAN_SECURED("61","Installment Loan - Secured","NA"),
		INSTALLMENT_LOAN_SECURED_PENSION_POLICY("62","Installment Loan - Secured Pension Policy","NA"),
		CREDIT_COMMERCIAL_CARD("7","Credit Commercial Card","NA"),
		INSURANCE_LONG_TERM("70","Insurance - Long Term","NA"),
		INSURANCE_SHORT_TERM("71","Insurance - Short Term","NA"),
		INSURANCE_BOND_BUSINESS("72","Insurance/Bond Business","NA"),
		INSURANCE_BOND_BUSINESS_COPY("73","Insurance/Bond Business","NA"),
		INSURANCE_BOND_DISABILITY("74","Insurance/Bond Disability","NA"),
		PAYMENT_ACCOUNT_POINT_OF_SALE_AND_PAYMENT_GATEWAYS("239","Payment Account (Point Of Sale & Payment Gateways)","Term Loan"),
		INSURANCE_BOND_GROUP("76","Insurance/Bond Group","NA"),
		INSURANCE_BOND_KEY("77","Insurance/Bond Key","NA"),
		INSURANCE_BOND_LIFE("78","Insurance/Bond Life","NA"),
		INSURANCE_BOND_PROPERTY("79","Insurance/Bond - Property","NA"),
		CREDIT_GENERAL_PURPOSE_CARD("8","Credit General Purpose Card","NA"),
		INSURANCE_BOND_SPECIALTY("80","Insurance/Bond Specialty","NA"),
		INSURANCE_BOND_UMBRELLA("81","Insurance/Bond - Umbrella","NA"),
		INSURANCE_BOND_WORKERS("82","Insurance/Bond - Workers","NA"),
		LEASE_AUTOMOBILE("87","Lease - Automobile","Term Loan"),
		LEASE_BUSINESS_FARM("88","Lease - Business Farm","Term Loan"),
		LEASE_COMPUTER_EQUIPMENT("89","Lease - Computer Equipment","Term Loan"),
		CREDIT_LINE_REVOLVING("9","Credit Line Revolving","NA"),
		LEASE_CONDITIONAL_SALES("90","Lease - Conditional sales","Term Loan"),
		LEASE_CONSTRUCTION_EQUIPMENT("91","Lease - Construction Equipment","Term Loan"),
		EQUIPMENT_FINANCING_CONSTRUCTION_OFFICE_MEDICAL("92","Equipment Financing (Construction Office Medical)","Term Loan"),
		LEASE_FAX_AND_COPIER_EQUIPMENT("93","Lease - Fax and Copier Equipment","Term Loan"),
		LEASE_FURNITURE("94","Lease - Furniture","Term Loan"),
		LEASE_MANUFACTURING_EQUIPMENT("95","Lease - Manufacturing Equipment","Term Loan"),
		LEASE_MATERIAL_HANDLING("96","Lease - Material Handling","Term Loan"),
		LEASE_MEDICAL_EQUIPMENT("97","Lease - Medical Equipment","Term Loan"),
		LEASE_NON_AUTO("98","Lease - Non-Auto","Term Loan"),
		LEASE_OFFICE_EQUIPMENT("99","Lease - Office Equipment","Term Loan"),
		OTHER("999","Other","Term Loan"),
		INSURANCE_BOND_GENERAL("75","Insurance/Bond General","NA");
		
		private String id;
		private String value;
		private String loanType;

		private ExperianAccountType(String id, String value, String loanType) {
			this.id = id;
			this.value = value;
			this.loanType = loanType;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}
		
		public String getLoanType() {
			return this.loanType;
		}

		public static ExperianAccountType fromValue(String v) {
			for (ExperianAccountType c : ExperianAccountType.values()) {
				if (c.value.equalsIgnoreCase(v)) {
					return c;
				}
			}
			return OTHER;
		}

		public static ExperianAccountType fromId(String v) {
			for (ExperianAccountType c : ExperianAccountType.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			return OTHER;
		}
	}
	
	public interface ExperianFrequencyOfPayment {
		
		public static final String Monthly = "6";
		public static final String Quarterly = "7";
		public static final  String  Annually = "8";
		public static final String Half_Yearly = "41";
		public static final String On_Demand = "42";
		public static final String Bullet = "43";
		public static final String Rolling = "44";
		public static final String Others  = "99";
		
	}
	
	public enum RelationShipTypeEnumExperian {

		COMPDIRECTOR(3,"Promoter Director"),
		BUSSOWNER(5,"Business owner"),
		SHAREHOLDER(8,"Shareholder"),
		OTHER(8,"Other");

		int code;
		String value;

		RelationShipTypeEnumExperian(int code,String value){
			this.code=code;
			this.value=value;
		}
		
		static public int getCodeFromValue(String value){
			for(RelationShipTypeEnumExperian relationShipTypeEnumExperian : RelationShipTypeEnumExperian.values()){
				if(relationShipTypeEnumExperian.value.equals(value)){
					return relationShipTypeEnumExperian.code;
				}
			}
			return 99;
		}
	}
	
	public enum IndustryTypeExperian {

		MANUFACTURING("27", "Manufacturing"),
		DISTRIBUTION("28", "Distribution"),
		WHOLESALE("29", "Wholesale"),
		TRADING("30", "Trading"),
		BROKING("31", "Broking"),
		SERVICE_PROVIDER("32", "Service Provider"),
		IMPORTING("33", "Importing"),
		EXPORTING("34", "Exporting"),
		AGRICULTURE("35", "Agriculture"),
		DEALERS("36", "Dealers"),
		BANK("37", "BANK"),
		NBFC("43", "NBFC"),
		HOUSING_FINANCE_COMPANY("39", "HOUSING FINANCE COMPANY"),
		TELECOM("40", "TELECOM"),
		INSURANCE("41", "INSURANCE"),
		MICROFINANCE_INSTITUTION("42", "MICROFINANCE INSTITUTION"),
		CREDIT_RATING_AGENCY("38", "CREDIT RATING AGENCY"),
		OTHER("99", "OTHER");

		String code;
		String value;

		IndustryTypeExperian(String code,String value){
			this.code=code;
			this.value=value;
		}
		
		static public String getValueFromCode(String code){
			for(IndustryTypeExperian industryTypeExperian : IndustryTypeExperian.values()){
				if(industryTypeExperian.code.equals(code)){
					return industryTypeExperian.value;
				}
			}
			return "OTHER";
		}
	}
	
	public enum month {
        Jan(1, "Jan"),
        Feb(2, "Feb"),
        Mar(3, "Mar"),
        Apr(4, "Apr"),
        May(5, "May"),
        Jun(6, "Jun"),
        Jul(7, "Jul"),
        Aug(8, "Aug"),
        Sep(9, "Sep"),
        Oct(10, "Oct"),
        Nov(11, "Nov"),
        Dec(12, "Dec");

        int code;
        String value;

        month(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode(){
            return code;
        }
    }
	
	public enum RelationshipTypeExperian {

		COMPANY_DIRECTOR("3", "Company Director"),
		BUSINESS_OWNER("5", "Business Owner"),
		SHAREHOLDER("8", "Shareholder"),
		OTHER("99", "Other"); 

		String code;
		String value;

		RelationshipTypeExperian(String code, String value) {
			this.code = code;
			this.value = value;
		}

		static public String getValueFromCode(String code){
			for(RelationshipTypeExperian relationshipTypeExperian : RelationshipTypeExperian.values()){
				if(relationshipTypeExperian.code.equals(code)){
					return relationshipTypeExperian.value;
				}
			}
			return "Other";
		}
	}
	
	public enum SuitFiledStatusExperian {

		NOT_A_SUIT_FILED_CASE("0", "Not a Suit Filed Case"),
		SUIT_FILED("1", "Suit Filed"),
		TRIAL_IN_PROGRESS("2", "Trial in Progress"),
		DECREE_ISSUED_BY_COURT("3", "Decree issued by court"),
		EXECUTION_OF_DECREE("4", "Execution of Decree"); 

		String code;
		String value;

		SuitFiledStatusExperian(String code, String value) {
			this.code = code;
			this.value = value;
		}

		static public String getValueFromCode(String code){
			for(SuitFiledStatusExperian suitFiledStatusExperian : SuitFiledStatusExperian.values()){
				if(suitFiledStatusExperian.code.equals(code)){
					return suitFiledStatusExperian.value;
				}
			}
			return "";
		}
	}
	
	public static String getPaymentStatus(String code) {
		String status;
		switch (Integer.parseInt(code)) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 45:
		case 46:
		case 47:
		case 48:
		case 49:
		case 59:
		case 60:
			status = "ACTIVE";
			break;
		case 109:
		case 110:
		case 111:
		case 112:
		case 113:
		case 114:
		case 115:
		case 116:
			status = "CLOSED";
			break;
		case 43:
			status = "SF/WD/WO/SETTLED";
			break;
		default:
			status = null;
			break;
		}
		return status;
	}
	
	public static String getBase64Encryption(String str) {
		try {
			if(isObjectNullOrEmpty(str)) {
				return null;
			}
			return Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("Error while encoding String, ERROR ==> ", e);
			return null;
		}
	}
	
	public static Double convertToDouble(Object obj) {
		if (!isObjectNullOrEmpty(obj)) {
			if (obj instanceof String) {
				return Double.parseDouble(obj.toString().replaceAll(",", "").trim());
			}
			if (obj instanceof BigDecimal) {
				return ((BigDecimal) obj).doubleValue();
			}
		}
		return  0.0;
	}
	
	public static Long convertToLong(Object obj) {
		if (!isObjectNullOrEmpty(obj)) {
			if (obj instanceof String) {
				return Long.parseLong(obj.toString().replaceAll(",", "").trim());
			}
		}
		return  0L;
	}
	
	public static String getConstituionByPan(String pan){
		String constitution = null;
		if(pan.charAt(3) == 'F' || pan.charAt(3) == 'f'){
			constitution = EntityTypeEnum.PARTNERSHIP.getExperianValue();
		}else if(pan.charAt(3) == 'G' || pan.charAt(3) == 'g'){
			constitution = EntityTypeEnum.GOVERNMENT.getExperianValue();
		}else if(pan.charAt(3) == 'C' || pan.charAt(3) == 'c'){
			constitution = EntityTypeEnum.PUBLIC_LIMITED.getExperianValue();
		}else if(pan.charAt(3) == 'T' || pan.charAt(3) == 't'){
			constitution = EntityTypeEnum.TRUST.getExperianValue();
		}else if(pan.charAt(3) == 'A' || pan.charAt(3) == 'a'){
			constitution = EntityTypeEnum.ASSOCIATION_OF_PERSONS.getExperianValue();
		}else if(pan.charAt(3) == 'P' || pan.charAt(3) == 'p'){
			constitution = EntityTypeEnum.PROPRIETORSHIP.getExperianValue();
		}else if(pan.charAt(3) == 'H' || pan.charAt(3) == 'h'){
			constitution = EntityTypeEnum.HINDU_UNDIVIDED_FAMILY.getExperianValue();
		}else{
			constitution = EntityTypeEnum.NOT_CLASSIFIED.getExperianValue();
		}
		return constitution;
	}
	
	public final class CibilMfiKey {
		// Header
		public static final String CONTROL_NUMBER = "control number";
		public static final String MEMBER_REFERENCE_NO = "member reference no";
		// Score
		public static final String SCORE_NAME = "score name";
		public static final String SCORE = "score";
		public static final String SCORING_FACTORS = "scoring factors";
		// AccountInformation
		public static final String MEMBER_NAME = "member name";
//		public static final String ACCOUNT_NO = "account no.";
//		public static final String NUMBER_OF_INSTALLMENTS = "no. of installments";
		public static final String TYPE = "type";
		// Dates
		public static final String OPEN_OR_DISBURSED_DATE = "open / disbursed date";
//		public static final String SANCTIONED_DATE = "sanctioned date";
		public static final String REPORTED_DATE = "reported and certified";
		// Amount
		public static final String DISBURSED_AMOUNT = "disbursed amount";
		public static final String CURRENT_BALANCE = "current balance";
		public static final String SANCTIONED_AMOUNT = "sanctioned amount";
		public static final String INSTALLMENT_AMOUNT = "installment";
	}

	
//	---------------------------------------------------------------EQUIFAX------------------------------------------------------
	public interface EquifaxResponseCode {
		public static final String SUCCESS = "1";
		public static final String ERROR = "0";
	}
	
	public interface EquifaxHitCode {
		public static final String NO_HIT = "00";
		public static final String INQUIRY_ONLY_HIT = "01";
		public static final String HIT_10 = "10";
		public static final String HIT_11 = "11";
	}
	
	public interface EquifaxScoreType {
		public static final String ERS = "ERS";
		public static final String GS = "GS";
	}
	
	public interface BureauVersion {
		public static final int V1 = 1;
		public static final int V2 = 2;
	}
	
	public enum PaymentStatusEquifax {

		STATUS_000("000", 0),
		STATUS_01("01+", 1),
		STATUS_30("30+", 30),
		STATUS_60("60+", 60),
		STATUS_90("90+", 90),
		STATUS_120("120+", 120),
		STATUS_180("180+", 180),
		STATUS_360("360+", 360),
		STATUS_540("540+", 540),
		STATUS_720("720+", 720);

		String code;
		Integer value;

		PaymentStatusEquifax(String code, Integer value){
			this.code=code;
			this.value=value;
		}

		static public Integer getValueFromCode(String code){
			for(PaymentStatusEquifax status : PaymentStatusEquifax.values()){
				if(status.code.equals(code)){
					return status.value;
				}
			}
			return 0;
		}
	}
	
	public enum LoanOwnerShipType {
		INDIVIDUAL(1,"Individual","Individual","1", "Individual"),
		AUTHORIZED_USER(2,"Authorized User","Authorized User","3", ""),
		GUARANTOR(3,"Guarantor","Guarantor","7", "Guarantor"),
		JOINT(4,"Joint","Joint holder","2", "Joint"),
		// Need to be discussed  - Gaurav | Bureau team
		DECEASED(5,"","","Z", ""), // Experian
		SUPL_CARD_HOLDER(6,"","","", "Supl Card Holder"), // Highmark
		OTHERS(7,"","","", "Others"); // Highmark
		
		private Integer id;
		private String cibilLoanOwnershipType;
		private String equifaxLoanOwnershipType;
		private String experianLoanOwnershipType;
		private String highmarkLoanOwnershipType;


		private LoanOwnerShipType(Integer id, String cibilLoanOwnershipType, String equifaxLoanOwnershipType, String experianLoanOwnershipType, String highmarkLoanOwnershipType) {
			this.id = id;
			this.cibilLoanOwnershipType = cibilLoanOwnershipType;
			this.equifaxLoanOwnershipType = equifaxLoanOwnershipType;
			this.experianLoanOwnershipType = experianLoanOwnershipType;
			this.highmarkLoanOwnershipType = highmarkLoanOwnershipType;
		}

		public Integer getId() {
			return id;
		}

		public String getCibilLoanOwnershipType() {
			return cibilLoanOwnershipType;
		}

		public String getEquifaxLoanOwnershipType() {
			return equifaxLoanOwnershipType;
		}

		public String getExperianLoanOwnershipType() {
			return experianLoanOwnershipType;
		}

		public static LoanOwnerShipType fromEquifaxValue(String v) {
			for (LoanOwnerShipType c : LoanOwnerShipType.values()) {
				if (c.equifaxLoanOwnershipType.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
		public static LoanOwnerShipType fromExperianValue(String v) {
			for (LoanOwnerShipType c : LoanOwnerShipType.values()) {
				if (c.experianLoanOwnershipType.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
		public static LoanOwnerShipType fromHighmarkValue(String v) {
			for (LoanOwnerShipType c : LoanOwnerShipType.values()) {
				if (c.highmarkLoanOwnershipType.equalsIgnoreCase(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
	}
	
	public enum AccountConditionEnum {
		RESTRUCTURED_LOAN("00", "Restructured Loan"), 
		RESTRUCTURED_LOAN_GOVT_MANDATED("01", "Restructured Loan (Govt. Mandated)"),
		WRITTEN_OFF("02", "Written-off"),
		SETTLED("03", "Settled"),
		POST_SETTLED("04", "Post (WO) Settled"),
		ACCOUNT_SOLD("05", "Account Sold"),
		WRITTEN_OFF_AND_ACCOUNT_SOLD("06", "Written Off and Account Sold"),
		ACCOUNT_PURCHASED("07", "Account Purchased"),
		ACCOUNT_PURCHASED_AND_WRITTEN_OFF("08", "Account Purchased and Written Off"),
		ACCOUNT_PURCHASED_AND_SETTLED("09", "Account Purchased and Settled"),
		ACCOUNT_PURCHASED_AND_RESTRUCTURED("10", "Account Purchased and Restructured"),
		RESTRUCTURED_DUE_TO_NATURAL_CALAMITY("11", "Restructured due to Natural Calamity");

		private String id;
		private String value;

		private AccountConditionEnum(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public String getId() {
			return this.id;
		}

		public static AccountConditionEnum fromId(String v) {
			for (AccountConditionEnum c : AccountConditionEnum.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
	}
	
}
