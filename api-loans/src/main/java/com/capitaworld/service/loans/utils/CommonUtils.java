package com.capitaworld.service.loans.utils;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class CommonUtils {

	public static final String USER_ID = "userId";
	public static final String INVALID_REQUEST = "Invalid Request !";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong !";

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
		public static final String[] CORPORATE = { "userId", "productId", "name", "categoryCode" };
		public static final String ID = "id";
		public static final String[] FP_PRODUCT = { "userId" };
		public static final String[] RETAIL_PROFILE = {"titleId", "firstName", "middleName", "lastName",
				"statusId", "occupationId", "pan", "aadharNumber", "monthlyIncome", "currencyId", "firstAddress",
				"secondAddress", "addressSameAs", "contactNo", "companyName", "employedWithId", "employedWithOther",
				"entityName", "industryTypeId", "industryTypeOther", "selfEmployedOccupationId",
				"selfEmployedOccupationOther", "landSize", "alliedActivityId", "userId" };

		public static final String[] RETAIL_FINAL = { "castId", "castOther", "religion", "religionOther",
				"birthPlace", "fatherName", "motherName", "spouseName", "isSpouseEmployed", "noChildren", "noDependent",
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
}
