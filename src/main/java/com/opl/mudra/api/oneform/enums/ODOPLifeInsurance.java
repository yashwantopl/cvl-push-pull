package com.opl.mudra.api.oneform.enums;

public enum ODOPLifeInsurance {

	PRADHAN_MANTRI_SURAKSHA_BIMA_YOJANA(1,"Pradhan Mantri Suraksha Bima Yojana (PMSBY)", "Pradhan Mantri Suraksha Bima Yojana (PMSBY)"),
	PRADHAN_MANTRI_JEEVAN_JYOTI_BIMA_YOJANA(2,"Pradhan Mantri Jeevan Jyoti Bima Yojana (PMJJBY)","Pradhan Mantri Jeevan Jyoti Bima Yojana (PMJJBY)"),
	ATAL_PENSION_YOJANA(3,"Atal Pension Yojana (APY)","Atal Pension Yojana (APY)"),
	ANY_OTHER(7,"Any Others (Kindly Specify)","Any Others (Kindly Specify)");

    private final Integer id;
    private final String value;
    private final String description;

    ODOPLifeInsurance(Integer id, String value, String description) {
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

    public static ODOPLifeInsurance getById(Integer id) {
        switch (id) {
            case 1:
                return PRADHAN_MANTRI_SURAKSHA_BIMA_YOJANA;
            case 2:
                return PRADHAN_MANTRI_JEEVAN_JYOTI_BIMA_YOJANA;
            case 3:
            	return ATAL_PENSION_YOJANA;
            case 7:
            	return ANY_OTHER;
            default:
                return null;
        }
    }
    public static ODOPLifeInsurance[] getAll() {
        return ODOPLifeInsurance.values();
    }
	
		public static ODOPLifeInsurance fromId(Integer v) {
		for (ODOPLifeInsurance c : ODOPLifeInsurance.values()) {
			if (c.id.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v.toString());

	}

}
