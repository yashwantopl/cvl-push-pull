package com.opl.mudra.api.enums.oneform;

/**
 * Created by ravina.panchal on 05-10-2018.
 */
public enum AccountType {
    CURRENT(1,"Current","Current"),
    SAVING(2,"Savings","Savings"),
	SALARY(3,"Salary","Salary");

    private final Integer id;
    private final String value;
    private final String description;
    AccountType(Integer id, String value, String description) {
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
    public static AccountType getById(Integer id) {
        switch (id) {
            case 1:
                return CURRENT;
            case 2:
                return SAVING;
            case 3:
            	return SALARY;
            default:
                return null;
        }
    }
    public static AccountType[] getAll() {
        return AccountType.values();

    }

}
