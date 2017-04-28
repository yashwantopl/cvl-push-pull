package com.capitaworld.service.loans.utils;

import java.util.Collection;

public class CommonUtils {

	public static boolean isListNullOrEmpty(Collection data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null || (value instanceof String ? (((String) value).isEmpty()
				|| "".equals(((String) value).trim()) || "null".equals(value) || "undefined".equals(value)) : false));
	}

	public interface LoanType {
		public static final int CORPORATE = 1;
		public static final int RETAIL = 2;
	}

	public interface IgnorableCopy {
		public static final String[] CORPORATE = { "categoryCode", "productId", "name", "tenure", "userId" };
	}
}
