package com.capitaworld.service.loans.utils;

import java.text.SimpleDateFormat;
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
	public static final String EXCEL_TEASER_BASE_URL= "excelTeaserBaseUrl";
	

	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null || (value instanceof String ? (((String) value).isEmpty()
				|| "".equals(((String) value).trim()) || "null".equals(value) || "undefined".equals(value)) : false));
	}

	public static Date getDateByDateMonthYear(Integer date, Integer month, Integer year) {

		System.out.println("date=>" + date);
		System.out.println("Month=>" + month);
		System.out.println("Year=>" + year);
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
			result[0] = null;
			result[1] = null;
			result[2] = null;
			return result;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		result[0] = calendar.get(Calendar.DAY_OF_MONTH);
		result[1] = calendar.get(Calendar.MONTH);
		result[2] = calendar.get(Calendar.YEAR);
		System.out.println("result[0]=>" + result[0]);
		System.out.println("result[1]=>" + result[1]);
		System.out.println("result[2]=>" + result[2]);
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
				"alliedActivityId", "userId" };

		public static final String[] RETAIL_FINAL = { "castId", "castOther", "religion", "religionOther", "birthPlace",
				"fatherName", "motherName", "spouseName", "isSpouseEmployed", "noChildren", "noDependent",
				"highestQualification", "highestQualificationOther", "qualifyingYear", "institute", "residenceType",
				"annualRent", "annualTurnover", "noPartners", "birthDate", "businessStartDate", "currentDepartment",
				"currentDesignation", "currentIndustry", "currentJobMonth", "currentJobYear", "employmentStatus",
				"interestRate", "nameOfEntity", "officeType", "ownershipType", "partnersName", "poaHolderName",
				"presentlyIrrigated", "previousEmployersAddress", "previousEmployersName", "previousJobMonth",
				"previousJobYear", "rainFed", "repaymentCycle", "repaymentMode", "residingMonth", "residingYear",
				"seasonalIrrigated", "shareholding", "totalExperienceMonth", "totalExperienceYear", "totalLandOwned",
				"tradeLicenseExpiryDate", "tradeLicenseNumber", "unattended", "websiteAddress", "userId" };
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
			Calendar now = Calendar.getInstance();
			now.setTimeInMillis(System.currentTimeMillis());

			years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
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
				flag = ((List) object).isEmpty();
				if(flag) return true;
				else continue;
			}
			flag = isObjectNullOrEmpty(object);
			if(flag) return true;
		}
		return false;
	}
}
