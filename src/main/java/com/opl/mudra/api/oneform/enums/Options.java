package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum Options {
			YES(1,"Yes","Yes"),
		NO(0,"No","No");
	
		private final Integer id;
		private final String value;
		private final String description;
		Options(Integer id, String value, String description) {
		this.id = id;
		this.value = value;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
		public static Options getById(Integer id) {
		switch (id) {
		case 1:
			return YES;
		case 0:
			return NO;
		default:
			return null;
		}
	}
		public static Options[] getAll() {
			return Options.values();

		}
}