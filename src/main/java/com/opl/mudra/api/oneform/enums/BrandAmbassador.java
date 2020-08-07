package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum BrandAmbassador {
	
	NATIONAL_INTERNATION(1,"Any Renowed Nationa/Internatioanal Personality","NATIONAL_INTERNATION"),
	REGIONAL(2,"Any Renowed Regional Personality","REGIONAL"),
	NO_SPECIFIC(3,"No Specific Brand Ambassador","No_SPECIFIC");
	
	private final Integer id;
	private final String value;
	private final String description;

	
	BrandAmbassador(Integer id, String value, String description){
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
	
	public static BrandAmbassador getById(Integer id) {
		switch (id) {
		case 1:
			return NATIONAL_INTERNATION;

		case 2:
			return REGIONAL;
			
		case 3:
			return NO_SPECIFIC;

		default:
			return null;
		}
	}

	public static BrandAmbassador[] getAll() {
		return BrandAmbassador.values();

	}


}
