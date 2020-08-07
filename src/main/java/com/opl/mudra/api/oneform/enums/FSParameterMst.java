package com.opl.mudra.api.oneform.enums;

public enum FSParameterMst {

	GOV_SCHEMES(1,"Government Schemes","Government  Schemes"),
	CERTIFICATION_COURSE(2,"Certification course","Certification course"),
	GOV_AUTHORITIES(3,"Gov authorities","Gov authorities"),
	LIFE_INSURANCE_POLICY(3,"life Insurance Policy","life Insurance Policy");
	

    private final Integer id;
    private final String value;
    private final String description;

    FSParameterMst(Integer id, String value, String description) {
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

    public static FSParameterMst getById(Integer id) {
        switch (id) {
            case 1:
                return GOV_SCHEMES;
            case 2:
                return CERTIFICATION_COURSE;
            case 3:
            	return GOV_AUTHORITIES;
            default:
                return null;
        }
    }
    public static FSParameterMst[] getAll() {
        return FSParameterMst.values();

    }
	
}
