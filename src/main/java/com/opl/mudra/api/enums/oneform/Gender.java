package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum Gender {
			MALE(2,"Male","Male","Male"),
		FEMALE(3,"Female","Female","Female"),
		THIRD_GENDER(4,"Third Gender","Third Gender","Transgender");
	
		private final Integer id;
		private final String value;
		private final String description;
		private final String cibilValue;
		Gender(Integer id, String value, String description, String cibilValue) {
		this.id = id;
		this.value = value;
		this.description = description;
		this.cibilValue = cibilValue;
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
		public String getCibilValue() {
		return cibilValue;
	}

		public static Gender getById(Integer id) {
		switch (id) {
		case 2:
			return MALE;
		case 3:
			return FEMALE;
		case 4:
			return THIRD_GENDER;
		default:
			return null;
		}
	}
		public static Gender[] getAll() {
			return Gender.values();

		}
}