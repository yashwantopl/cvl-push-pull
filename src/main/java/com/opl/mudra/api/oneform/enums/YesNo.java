package com.opl.mudra.api.oneform.enums;

/**
 * @author Akshay
 *
 */
public enum YesNo {
	YES(0, "Yes", "Yes"), 
	NO(1, "No", "No");

	private final Integer id;
	private final String value;
	private final String description;

	YesNo(Integer id, String value, String description) {
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
	
	public static YesNo fromId(Integer v) {
		for (YesNo c : YesNo.values()) {
			if (c.id.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v != null ? v.toString() : null);
	}
	
	public static YesNo fromValue(String v) {
		for (YesNo c : YesNo.values()) {
			if (c.value.equalsIgnoreCase(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
	
	public static YesNo[] getAll(){
		return YesNo.values();
		
	}

}
