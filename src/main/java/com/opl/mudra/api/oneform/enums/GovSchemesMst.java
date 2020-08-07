package com.opl.mudra.api.oneform.enums;

public enum GovSchemesMst {

	PRADHAN_MANTRI_SURAKSHA_BIMA_YOJANA(1,"Pradhan Mantri Suraksha Bima Yojana (PMSBY)", "Pradhan Mantri Suraksha Bima Yojana (PMSBY)"),
	PRADHAN_MANTRI_JEEVAN_JYOTI_BIMA_YOJANA(2,"Pradhan Mantri Jeevan Jyoti Bima Yojana (PMJJBY)","Pradhan Mantri Jeevan Jyoti Bima Yojana (PMJJBY)"),
	ATAL_PENSION_YOJANA(3,"Atal Pension Yojana (APY)","Atal Pension Yojana (APY)"),
	INSURANCE_POLICY(4,"Any other Insurance Policy","Any other Insurance Policy"),
	PENSION_SCHEME(5,"Any other Pension Scheme","Any other Pension Scheme"),
	NOT_COVERED(6,"I do not have any Pension/Insurance scheme","I do not have any Pension/Insurance scheme");

    private final Integer id;
    private final String value;
    private final String description;

    GovSchemesMst(Integer id, String value, String description) {
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

    public static GovSchemesMst getById(Integer id) {
        switch (id) {
            case 1:
                return PRADHAN_MANTRI_SURAKSHA_BIMA_YOJANA;
            case 2:
                return PRADHAN_MANTRI_JEEVAN_JYOTI_BIMA_YOJANA;
            case 3:
            	return ATAL_PENSION_YOJANA;
            case 4:
            	return INSURANCE_POLICY;
            case 5:
            	return PENSION_SCHEME;
            case 6:
            	return NOT_COVERED;
            default:
                return null;
        }
    }
    public static GovSchemesMst[] getAll() {
        return GovSchemesMst.values();
    }

}
