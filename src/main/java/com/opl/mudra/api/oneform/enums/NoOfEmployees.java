package com.opl.mudra.api.oneform.enums;

public enum NoOfEmployees {

	ZERO(0,"0","0"),
	ONE(1,"1","1"),
	TWO(2,"2","2"),
	THREE(3,"3","3"),
	FOUR(4,"4","4"),
	FIVE_AND_ABOVE(5,"5 & above","5 & above");

    private final Integer id;
    private final String value;
    private final String description;

    NoOfEmployees(Integer id, String value, String description) {
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

    public static NoOfEmployees getById(Integer id) {
        switch (id) {
            case 0:
                return ZERO;
            case 1:
            	return ONE;
            case 2:
                return TWO;
            case 3:
            	return THREE;
            case 4:
            	return FOUR;
            case 5:
            	return FIVE_AND_ABOVE;
            default:
                return null;
        }
    }
    public static NoOfEmployees[] getAll() {
        return NoOfEmployees.values();
    }

}
