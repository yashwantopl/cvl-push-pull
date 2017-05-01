package com.capitaworld.service.loans.utils;

import java.util.Collection;

public class CommonUtils {

	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null || (value instanceof String ? (((String) value).isEmpty()
				|| "".equals(((String) value).trim()) || "null".equals(value) || "undefined".equals(value)) : false));
	}

	public enum LoanType {
		WORKING_CAPITAL(1), TERM_LOAN(2), HOME_LOAN(3), CAR_LOAN(4), PERSONAL_LOAN(5), LAP_LOAN(6), LAS_LOAN(7);
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
			}
			return null;
		}

	}

	public interface IgnorableCopy {
		public static final String[] CORPORATE = { "userId", "productId", "name", "categoryCode" };
		public static final String[] FP_PRODUCT = { "userId" };
	}
}
