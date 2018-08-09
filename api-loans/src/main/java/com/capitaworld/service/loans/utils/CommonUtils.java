package com.capitaworld.service.loans.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

public class CommonUtils {

	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
	public static final String USER_ORG_ID = "userOrgId";
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
	
	public static final  DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
	
	public interface UsersRoles {
		public static final Long MAKER = 1l;
		public static final Long CHECKER = 2l;
		public static final Long APPROVER = 3l;
		public static final Long ADMIN_HO = 4l;
		public static final Long HO = 5l;
		public static final Long BO = 6l;
	}

	public interface DenominationInAmount {
		public static final Long LAKHS = 100000l;
		public static final Long MILLIONS = 1000000l;
		public static final Long CRORES = 10000000l;
		public static final Long BILLIONS = 100000000l;
		public static final Long ABSOLUTE = 1l;
	}

	public interface DenominationId {
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
		Integer result[] = new Integer[3];
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

	public interface IgnorableCopy {
		public static final String[] CORPORATE = { "userId", "productId", "name", "categoryCode", "isActive",
				"applicationId" };
		public static final String ID = "id";
		public static final String[] FP_PRODUCT = { "userId", "productId" };
		public static final String[] FP_PRODUCT_TEMP = { "userId","isApproved","isDeleted","isCopied","isEdit","statusId","jobId","fpProductId","id","fpProductMappingId"};
		public static final String[] CORPORATE_PROFILE = {  "id","userId", "clientId", "applicationId","panNo","constitutionId","establishmentMonth",
			"establishmentYear","keyVericalFunding","latitude","longitude","organisationName","firstAddress",
			"websiteAddress","landlineNo","keyVerticalSector","keyVerticalSubsector","gstIn","email"
		};
		public static final String[] CORPORATE_FINAL = { "aadhar","secondAddress","sameAs","creditRatingId",
				"contLiabilityFyAmt","contLiabilitySyAmt" ,"contLiabilityTyAmt" ," contLiabilityYear","notApplicable","aboutUs","id"
		};
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

		public static final String[] RETAIL_FINAL = { "castId", "castOther", "religion", "religionOther", "birthPlace",
				"fatherName", "motherName", "noChildren", "noDependent", "highestQualificationOther", "residenceType",
				"annualRent", "noPartners", "birthDate", "currentDepartment", "currentDesignation", "currentIndustry",
				"employmentStatus", "interestRate", "nameOfEntity", "officeType", "ownershipType", "partnersName",
				"poaHolderName", "presentlyIrrigated", "rainFed", "repaymentCycle", "repaymentMode",
				"seasonalIrrigated", "shareholding", "totalLandOwned", "tradeLicenseExpiryDate", "tradeLicenseNumber",
				"unattended", "websiteAddress", "userId" };
		public static final String[] DIRECTOR_OBJ_EXCEPT_MAIN = {"isItrCompleted", "isCibilCompleted", "isBankStatementCompleted", "isOneFormCompleted",
				"applicationId","dob","din","panNo","directorsName","totalExperience", "isActive","pincode","stateCode","city","mobile","gender","relationshipType",
				"firstName","lastName", "middleName","title", "shareholding","aadhar","maritalStatus","noOfDependent","residenceType","residenceSinceMonth","residenceSinceYear",
				"isFamilyMemberInBusiness","employmentDetailRequest","countryId","premiseNumber","streetName","landmark"
		};
	}

	public interface ApplicantType {
		public static final int APPLICANT = 1;
		public static final int COAPPLICANT = 2;
		public static final int GARRANTOR = 3;

	}

	public interface UserMainType {
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

	public interface ApplicationStatus {
		public static final Long OPEN = 1l;
		public static final Long ASSIGNED = 2l;
		public static final Long SUBMITTED = 3l;
		public static final Long SUBMITTED_TO_APPROVER = 4l;
		public static final Long APPROVED = 5l;
		public static final Long REVERTED = 6l;
		public static final Long ASSIGNED_TO_CHECKER = 7l;
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

	public interface DdrStatus {
		public static final Long OPEN = 1l;
		public static final Long IN_PROGRESS = 2l;
		public static final Long SUBMITTED = 3l;
		public static final Long SUBMITTED_TO_APPROVER = 4l;
		public static final Long APPROVED = 5l;
		public static final Long REVERTED = 6l;
	}

	public interface UserType {
		public static final int FUND_SEEKER = 1;
		public static final int FUND_PROVIDER = 2;
		public static final int SERVICE_PROVIDER = 3;
		public static final int NETWORK_PARTNER = 4;
	}

	public interface UploadUserType {
		public static final String UERT_TYPE_APPLICANT = "applicant";
		public static final String UERT_TYPE_CO_APPLICANT = "coApplicant";
		public static final String UERT_TYPE_GUARANTOR = "guarantor";
		public static final String UERT_TYPE_USER = "user";
	}

	public interface EmployerConstitution {
		public static final int PARTNERSHIP_PROPRIETORSHIP = 1;
		public static final int ANYOTHER = 2;
	}

	public interface EmployementType {
		public static final int SALARIED = 1;
		public static final int BUSINESSMAN = 2;
	}

	public interface ReceiptMode {
		public static final int CASH = 1;
		public static final int BANK = 2;
	}

	public interface PropertyType {
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

	public interface TabType {
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

	public static List<String> urlsBrforeLogin = null;
	static {
		urlsBrforeLogin = new ArrayList<String>(8);
		urlsBrforeLogin.add("/loans/loan_application/getUsersRegisteredLoanDetails");
		urlsBrforeLogin.add("/loans/loan_application/getLoanDetailsForAdminPanel");
		urlsBrforeLogin.add("/loans/corporate_upload/downloadCMAAndCoCMAExcelFile/**");
		urlsBrforeLogin.add("/loans/loan_application/save_payment_info_for_mobile");
		urlsBrforeLogin.add("/loans/loan_application/mobile/successUrl");
		urlsBrforeLogin.add("/loans/loan_application/getToken");
		urlsBrforeLogin.add("/loans/loan_application/saveLoanDisbursementDetail");
		urlsBrforeLogin.add("/loans/loan_application/saveLoanSanctionDetail");
		
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
		if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)) {
			age--;

			// If birth date and todays date are of same month and birth day of
			// month is greater than todays day of month then decrement age
		} else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)) {
			age--;
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

		String value = year + " Years " + month + " Months ";
		return value;
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
		// TODO Auto-generated method stub
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
				return new BigDecimal((Double) obj);
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

		String snumber = Long.toString(number);

		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);

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

	public interface PaymentMode {
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
		
		NEW_TO_BUSINESS(2, "New to Business"),EXISTING_BUSINESS(1, "Existing Business");

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
		
		ITR(1, "ITR"),CIBIL(2, "CIBIL"),BANK_STATEMENT(3, "BANK STATEMENT"),ONE_FORM(4, "ONE FORM");

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
		
		Double sub= a-b;
		return sub;
	}
	
	public static Double substractThreeNumbers(Double a, Double b, Double c){
		a= isObjectNullOrEmpty(a) ? 0.0 : a;
		b= isObjectNullOrEmpty(b) ? 0.0 : b;
		c= isObjectNullOrEmpty(c) ? 0.0 : c;
		
		Double sub= a-b-c;
		return sub;
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
		}else {
			return null;
		}
	}
	public static String decode(String encryptedString) {
		return new String(Base64.getDecoder().decode(encryptedString));
	}
	
	public static String getEncodedUserNamePassword(String userName,String password) {
		String keyToEncode = userName + ":" + password;
		String encodedString = "Basic " + Base64.getEncoder().encodeToString(keyToEncode.getBytes());
		return encodedString;
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
	
	public interface Status {
		public static final int OPEN = 1;
		public static final int IN_PROGRESS = 2;
		public static final int REVERTED = 3;
		public static final int APPROVED = 4;
	}
	
	
	/***********************************************CAM UTILS*********************************************************/
	static DecimalFormat decim = new DecimalFormat("#,##0.00");
	static DecimalFormat decim2 = new DecimalFormat("#,###.00");
	
	public static String convertValue(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value)? decim.format(value).toString(): "0";
	}
	public static String convertValueWithoutDecimal(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value)? decim2.format(value).toString(): "0";
	}
	public static Object convertToDoubleForXml (Object obj, Map<String, Object>data) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		 for(Field field : fields) {
			 field.setAccessible(true);
             Object value = field.get(obj);
             if(data != null) {
            	 data.put(field.getName(), value);
             }
             if(!CommonUtils.isObjectNullOrEmpty(value)) {
            	 if(value instanceof Double){
                	 if(!Double.isNaN((Double)value)) {
                		 DecimalFormat decim = new DecimalFormat("0.00");
                    	 value = Double.parseDouble(decim.format(value));
                    	 if(data != null) {
                    		 value = decim.format(value);
                    		 data.put(field.getName(), value);
                    	 }else {
                    		 field.set(obj,value);                    		 
                    	 }
                	 }
                 }
             }
		 }
		 if(data != null) {
			 return data;
		 }
		return obj;
	}
	public static Object printFields(Object obj) throws Exception {
		if(obj instanceof List) {
			List<?> lst = (List)obj;
			for(Object o : lst) {
				escapeXml(o);
			}
		}else if(obj instanceof Map) {
			Map<Object, Object> map = (Map)obj;
			for(Map.Entry<Object, Object> setEntry : map.entrySet()) {
				escapeXml(setEntry.getValue());
			}
		}else {
			escapeXml(obj);
		}
		 return obj;
	}
	public static Object escapeXml(Object obj) throws Exception{
		if(obj instanceof List) {
			List<?> lst = (List<?>)obj;
			for(Object o : lst) {
				escapeXml(o);
			}
		}else if(obj instanceof Map) {
			Map<Object, Object> map = (Map)obj;
			for(Map.Entry<Object, Object> setEntry : map.entrySet()) {
				escapeXml(setEntry.getValue());
			}
		}
		if(obj!=null) {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(obj);
				if (value instanceof String) {
					String value1 = (String) field.get(obj);
					String a = StringEscapeUtils.escapeXml(value1.toString());
					value = a;
					field.set(obj, value);
				}if(value instanceof Double) {
					convertToDoubleForXml(value, null);
				}
				else {
					continue;
				}
			}
		}
		return obj;
    }
	
	
	
}
