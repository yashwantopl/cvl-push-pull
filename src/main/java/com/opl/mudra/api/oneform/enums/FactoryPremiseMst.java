package com.opl.mudra.api.oneform.enums;

public enum FactoryPremiseMst {

    OWNED(1,"Owned","Owned","Owned"),
    LEASED_OVER(3,"Leased over 5 years","Leased","Leased over 5 years"),
    LEASED_BELOW(4,"Leased below 5 years","Leased","Leased below 5 years"),
	RENTED(2,"Rented","Rented","Rented");

    private final Integer id;
    private final String value;
    private final String sidbiValue;
    private final String description;

    FactoryPremiseMst(Integer id, String value,String sidbiValue, String description) {
        this.id = id;
        this.value = value;
        this.sidbiValue = sidbiValue;
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
    

    public String getSidbiValue() {
		return sidbiValue;
	}

	public static FactoryPremiseMst getById(Integer id) {
        switch (id) {
            case 1:
                return OWNED;
            case 2:
                return RENTED;
            case 3:
                return LEASED_OVER;
            case 4:
                return LEASED_BELOW;
            default:
                return null;
        }
    }
    public static FactoryPremiseMst[] getAll() {
        return FactoryPremiseMst.values();

    }
}
