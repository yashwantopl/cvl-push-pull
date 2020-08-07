package com.opl.mudra.api.oneform.enums;

public enum AccessToInputs {
	
	ACCESS_TO_INPUTS_LOCALLY_AVAILABLE(1,"Access to inputs locally available/ tied up","Access to inputs locally available/ tied up"),
	ACCESS_TO_INPUTS_NOT_LOCALLY_AVAILABLE_BUT_SOURCE_IDENTIFIED(2,"Access to inputs not locally available but source identified","Access to inputs not locally available but source identified"),
	ACCESS_TO_INPUTS_NOT_LOCALLY_AVAILABLE_NOR_IDENTIFIED(3,"Access to inputs not locally available nor identified","Access to inputs not locally available nor identified");
	

    private final Integer id;
    private final String value;
    private final String description;

    AccessToInputs(Integer id, String value, String description) {
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

    public static AccessToInputs getById(Integer id) {
        switch (id) {
            case 1:
                return ACCESS_TO_INPUTS_LOCALLY_AVAILABLE;
            case 2:
                return ACCESS_TO_INPUTS_NOT_LOCALLY_AVAILABLE_BUT_SOURCE_IDENTIFIED;
            case 3:
            	return ACCESS_TO_INPUTS_NOT_LOCALLY_AVAILABLE_NOR_IDENTIFIED;
            default:
                return null;
        }
    }
    public static AccessToInputs[] getAll() {
        return AccessToInputs.values();

    }
	
}
