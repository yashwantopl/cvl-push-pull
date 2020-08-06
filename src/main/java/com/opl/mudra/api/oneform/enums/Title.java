package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum Title {
		DR(4,"Dr.","Dr."),
		MR(1,"Mr.","Mr."),
		MS(3,"Ms.","Ms."),
		MRS(2,"Mrs.","Mrs.");

		private final Integer id;
		private final String value;
		private final String description;
		Title(Integer id, String value, String description) {
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
		public static Title getById(Integer id) {
		switch (id) {
		case 1:
			return MR;
		case 2:
			return MRS;
		case 3:
			return MS;
		case 4:
			return DR;
		default:
			return null;
		}
	}
		public static Title[] getAll() {
			return Title.values();

		}
}