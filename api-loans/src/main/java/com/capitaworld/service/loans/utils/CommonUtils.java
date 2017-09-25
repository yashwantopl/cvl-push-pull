package com.capitaworld.service.loans.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class CommonUtils {

	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
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
		return (value == null || (value instanceof String ? (((String) value).isEmpty()
				|| "".equals(((String) value).trim()) || "null".equals(value) || "undefined".equals(value)) : false));
	}

	public static Date getDateByDateMonthYear(Integer date, Integer month, Integer year) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, date);
		calendar.set(Calendar.MONTH, month);
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
		result[1] = calendar.get(Calendar.MONTH);
		result[2] = calendar.get(Calendar.YEAR);
		return result;
	}

	public enum LoanType {
		WORKING_CAPITAL(1), TERM_LOAN(2), HOME_LOAN(3), CAR_LOAN(12), PERSONAL_LOAN(7), LAP_LOAN(13), LAS_LOAN(14);
		private int value;

		private LoanType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static LoanType getType(Integer x) {
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
			}
			return null;
		}

	}

	public interface IgnorableCopy {
		public static final String[] CORPORATE = { "userId", "productId", "name", "categoryCode", "isActive",
				"applicationId" };
		public static final String ID = "id";
		public static final String[] FP_PRODUCT = { "userId", "productId" };
		public static final String[] RETAIL_PROFILE = { "titleId", "firstName", "middleName", "lastName", "pan",
				"aadharNumber", "monthlyIncome", "firstAddress", "secondAddress", "addressSameAs", "contactNo",
				"companyName", "employedWithId", "employedWithOther", "entityName", "industryTypeId",
				"industryTypeOther", "selfEmployedOccupationId", "selfEmployedOccupationOther", "landSize",
				"alliedActivityId", "userId", "nameAsPerAadharCard", "currentJobMonth", "currentJobYear", "previousJobMonth", 
				"previousJobYear", "totalExperienceMonth", "totalExperienceYear", "monthlyLoanObligation", 
				"previousEmployersAddress", "previousEmployersName", "annualTurnover", "businessStartDate", "patPreviousYear",
				"patCurrentYear", "depreciationPreviousYear", "depreciationCurrentYear", "remunerationPreviousYear", "remunerationCurrentYear"};

		public static final String[] RETAIL_FINAL = { "castId", "castOther", "religion", "religionOther", "birthPlace",
				"fatherName", "motherName", "spouseName", "isSpouseEmployed", "noChildren", "noDependent",
				"highestQualification", "highestQualificationOther", "qualifyingYear", "institute", "residenceType",
				"annualRent", "noPartners", "birthDate", "currentDepartment","currentDesignation", "currentIndustry", "employmentStatus",				
				"interestRate", "nameOfEntity", "officeType", "ownershipType", "partnersName", "poaHolderName",	"presentlyIrrigated",  
				 "rainFed", "repaymentCycle", "repaymentMode", "residingMonth", "residingYear","seasonalIrrigated", "shareholding",  
				"totalLandOwned", "tradeLicenseExpiryDate", "tradeLicenseNumber", "unattended", "websiteAddress", "userId" };
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

	public static int getUserMainType(int productId) {
		if (productId == 1 || productId == 2)
			return 2;
		else
			return 1;
	}

	public static String getUserMainTypeName(int productId) {
		if (isObjectNullOrEmpty(productId)) {
			return "NA";
		}
		if (productId == 1 || productId == 2)
			return CORPORATE;
		else
			return RETAIL;
	}

	public static String getCorporateLoanType(int productId) {
		if (productId == 1 || productId == 2)
			return "DEBT";
		else
			return "EQUITY";
	}

	public interface UserType {
		public static final int FUND_SEEKER = 1;
		public static final int FUND_PROVIDER = 2;
		public static final int SERVICE_PROVIDER = 3;
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
			monthsDiff = monthsDiff + today.get(Calendar.MONTH) - 12;
			Integer ageInMonths = yearsInBetween * 12 + monthsDiff;
			years = ageInMonths / 12;
			System.out.println("Age :===" + years);
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
		urlsBrforeLogin = new ArrayList<String>(3);
		urlsBrforeLogin.add("/loans/loan_application/getUsersRegisteredLoanDetails");
		urlsBrforeLogin.add("/loans/loan_application/getLoanDetailsForAdminPanel");
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
	
}
