package com.opl.mudra.api.oneform.enums;

public enum RegistrationWithGovernmentAuthoritiesList {
	
	GST(1,"GST",null),
	REGISTRATION_UNDER_ESTABLISHMENT(2,"Registration under shop and establishment act from Municipality or gram panchayat",null),
	UDYOG_AADHAR(3,"Udyog Aadhar",null),
	NOT_REGISTERED_GOV_AUTHORITIES(5,"I am not registered with any Government Authorities","I am not registered with any Government Authorities"),
	OTHERS(4,"Others",null);
	
	private final Integer id;
	private final String value;
	private final String description;
	
	
	private RegistrationWithGovernmentAuthoritiesList(Integer id, String value, String description) {
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
	
	
	public static RegistrationWithGovernmentAuthoritiesList fromValue(String v) {
		for (RegistrationWithGovernmentAuthoritiesList c : RegistrationWithGovernmentAuthoritiesList.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

	public static RegistrationWithGovernmentAuthoritiesList fromId(Integer v) {
		for (RegistrationWithGovernmentAuthoritiesList c : RegistrationWithGovernmentAuthoritiesList.values()) {
			if (c.id.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v.toString());

	}
	
	public static RegistrationWithGovernmentAuthoritiesList[] getAll() {
		return RegistrationWithGovernmentAuthoritiesList.values();
	}
	
	

}
