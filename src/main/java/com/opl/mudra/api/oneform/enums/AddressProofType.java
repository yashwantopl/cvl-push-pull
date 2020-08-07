package com.opl.mudra.api.oneform.enums;

public enum AddressProofType {
    PAN_CARD(1,"Pan Card"),
//    PASSPORT(2,"Passport"),
//    DRIVING_LICENCE(3,"Driving License"),
    VOTER_RATION(2,"Voter ID"),
    AADHAR_CARD(3,"Aadhar Card");
//    UTILITIES_BILL(5,"Utilities Bill"),
//    RENT_AGREEMENT(6,"Rent Agreement");





    private Integer id;
    private String value;

    private AddressProofType(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static AddressProofType fromValue(String v) {
        for (AddressProofType c : AddressProofType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static AddressProofType fromId(String v) {
        for (AddressProofType c : AddressProofType.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static AddressProofType[] getAll() {
        return AddressProofType.values();
    }
    
    public static AddressProofType getById(Integer id) {
		switch (id) {
		case 1:
			return PAN_CARD;
		case 2:
			return VOTER_RATION;
		case 3:
			return AADHAR_CARD;	

		default:
			return null;
		}
	}
}
