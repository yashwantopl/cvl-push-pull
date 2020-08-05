package com.opl.mudra.api.eligibility.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class EligibilityUtils {

	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
	public static final String INVALID_REQUEST = "Invalid Request !";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong !";
	public static final String ELIGIBILITY_SERVICE_NOT_AVAILABLE = "Loan Eligibility Service is not available !";
	public static final String ENTITY_MANAGER_REFERENCE = "eligibilityDataStoreEM";
	public static final String TRANSACTION_MANAGER_REFERENCE = "eligibilityDataStoreTM";
	public static final String DATASTORE = "eligibilityDataStore";
	private static final String DATE_FORMATE = "dd-MM-yyyy";
	public static final String MAXIMUM = "maximum";
	public static final String MINIMUM = "minimum";
	public static final String UBI_HLINTEREST_RATE_UPTO_75 = "ubi.hl.interestrate.upto75";
	public static final String UBI_HLINTEREST_RATE_HIGHER_75 = "ubi.hl.interestrate.higher75";
	public static final String UBI_HLINTEREST_PER_MONTH = "ubi.hl.interestrate.interestratepermonth";
	public static final Long UBI_UPTO_LIMIT = 7500000l;

	public static final String EXCEPTION = "Exception :: ";

	private static final List<String> URLS_BRFORE_LOGIN  = new ArrayList<String>(8);
	
	public static List<String> getUrlsBrforeLogin() {
	return URLS_BRFORE_LOGIN;
}

	static {
		URLS_BRFORE_LOGIN.add("/loan_eligibility/error".toLowerCase());
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

	public static String formateDate(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATE);
			return dateFormat.format(date);
		} else {
			return null;
		}
	}

	public static String formateDate(Date date, String formate) {
		if (!isObjectNullOrEmpty(date)) {
			if (!isObjectNullOrEmpty(formate)) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
				return dateFormat.format(date);
			}
			return formateDate(date);
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

	public enum LoanType {
		WORKING_CAPITAL(1), TERM_LOAN(2), HOME_LOAN(3), CAR_LOAN(12), PERSONAL_LOAN(7), LAP_LOAN(13), LAS_LOAN(
				14), UNSECURED_LOAN(15);
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
			case 15:
				return UNSECURED_LOAN;
			default:
				return null;
			}
		}

	}

	public static Integer getAgeFromBirthDate(Date date) {
		if (EligibilityUtils.isObjectNullOrEmpty(date)) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		//MODIFIED AGE CALCULATION ACCORDING TO ADDITIONAL VALIDATION FOR LAP SHEET
		Integer age=Period.between(birthday, today).getYears();
		Integer months =Period.between(birthday, today).getMonths();
		Integer days = Period.between(birthday, today).getDays();
		Integer newMonths= months;
		if(days >=15){
			newMonths = months + 1;
		}
		if(newMonths >=6){
			age = age + 1;
		}
		return age;
	}

	public final class UserType {
		private UserType(){}

		public static final int FUND_SEEKER = 1;
		public static final int FUND_PROVIDER = 2;
		public static final int SERVICE_PROVIDER = 3;
	}
	
	public final class Constitution {
		private Constitution(){}

		public static final int PARTNERSHIP_FIRM = 2;
		public static final int MNC = 3;
		public static final int PRIVATE_LTD = 4;
		public static final int PROPRIETORSHIP = 5;
		public static final int PUBLIC_LTD = 6;
		public static final int PRIVATE_SECTOR = 7;
		public static final int OTHER = 8;
		public static final int LIMITED_LIABILITY_PARTNERSHIP = 9;
	}

}
