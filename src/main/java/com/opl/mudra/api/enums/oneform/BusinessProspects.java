package com.opl.mudra.api.enums.oneform;

public enum BusinessProspects {

	HIGH_POTENTIAL(1,"High Potential","High Potential"),
	AVERAGE_POTENTIAL(2,"Average Potential","Average Potential"),
	MARGINAL_POTENTIAL(3,"Marginal Potential","Marginal Potential");

    private final Integer id;
    private final String value;
    private final String description;

    BusinessProspects(Integer id, String value, String description) {
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

    public static BusinessProspects getById(Integer id) {
        switch (id) {
            case 1:
                return HIGH_POTENTIAL;
            case 2:
                return AVERAGE_POTENTIAL;
            case 3:
            	return MARGINAL_POTENTIAL;
            default:
                return null;
        }
    }
    public static BusinessProspects[] getAll() {
        return BusinessProspects.values();

    }
	

	
}
