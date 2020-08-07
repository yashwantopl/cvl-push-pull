package com.opl.mudra.api.oneform.enums;

public enum IdProofMst {

	AADHAR_CARD(1,"Aadhar Card","Aadhar Card"),
	PASSPORT(2,"Passport","Passport"),
	VOTER_CARD(4,"Voter Card","Voter Card"),
	DRIVING_LICENSE(5,"Driving License","Driving License"),
	PAN_CARD(6,"PAN Card","PAN Card"),
	OTHER(7,"Other","Other");

    private final Integer id;
    private final String value;
    private final String description;

    IdProofMst(Integer id, String value, String description) {
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

    public static IdProofMst getById(Integer id) {
        switch (id) {
            case 1:
                return AADHAR_CARD;
            case 2:
                return PASSPORT;
            case 4:
            	return VOTER_CARD;
            case 5:
            	return DRIVING_LICENSE;
            case 6:
            	return PAN_CARD;
            case 7:
            	return OTHER;
            default:
                return null;
        }
    }
    public static IdProofMst[] getAll() {
        return IdProofMst.values();

    }
}
