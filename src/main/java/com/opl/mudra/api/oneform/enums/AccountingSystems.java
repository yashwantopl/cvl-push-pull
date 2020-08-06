package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum AccountingSystems {
	ERP_YES(1, "ERP_Yes", "ERP-YES"), 
	ERP_NO(2, "ERP_No", "ERP-NO");

	private final Integer id;
	private final String value;
	private final String description;

	AccountingSystems(Integer id, String value, String description) {
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
	
	public static AccountingSystems getById(Integer id) {
		switch (id) {
		case 1:
			return ERP_YES;
			
		case 2 :
			return ERP_NO;

		default:
			return null;
		}
	}
	
	public static AccountingSystems[] getAll(){
		return AccountingSystems.values();
		
	}

}
