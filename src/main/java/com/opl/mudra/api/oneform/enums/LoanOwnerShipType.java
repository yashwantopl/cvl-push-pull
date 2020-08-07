package com.opl.mudra.api.oneform.enums;

public enum LoanOwnerShipType {

	INDIVIDUAL(1,"Individual","Individual"),
	AUTHORIZED_USER(2,"Authorized User","Authorized User"),
	GUARANTOR(3,"Guarantor","Guarantor"),
	JOINT(4,"Joint","Joint");

    private final Integer id;
    private final String value;
    private final String description;

    LoanOwnerShipType(Integer id, String value, String description) {
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

    public static LoanOwnerShipType getById(Integer id) {
        switch (id) {
            case 1:
                return INDIVIDUAL;
            case 2:
                return AUTHORIZED_USER;
            case 3:
            	return GUARANTOR;
            case 4:
            	return JOINT;
            default:
                return null;
        }
    }
    public static LoanOwnerShipType[] getAll() {
        return LoanOwnerShipType.values();

    }


	
}
