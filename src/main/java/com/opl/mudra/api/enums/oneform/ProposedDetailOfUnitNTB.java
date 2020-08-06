package com.opl.mudra.api.enums.oneform;

public enum ProposedDetailOfUnitNTB {

    OWNED(1,"Owned","Owned"),
    GOVT_LEASED(2,"Government Leased"," Government Leased"),
    LEASED(3,"Leased for more than 10 years","Leased for more than 10 years"),
    LEASED_LESS(4,"Leased for 10 years or less","Leased for 10 years or less"),
    RENTED(5,"Rented","Rented");

    private final Integer id;
    private final String value;
    private final String description;

    ProposedDetailOfUnitNTB(Integer id, String value, String description) {
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

    public static ProposedDetailOfUnitNTB getById(Integer id) {
        switch (id) {
            case 1:
                return OWNED;
            case 2:
                return GOVT_LEASED;
            case 3:
                return LEASED;
            case 4:
                return LEASED_LESS;
            case 5:
                return RENTED;
            default:
                return null;
        }
    }
    public static ProposedDetailOfUnitNTB[] getAll() {
        return ProposedDetailOfUnitNTB.values();

    }
}
