package com.opl.mudra.api.enums.oneform;

/**
 * Created by ravina.panchal on 07-10-2018.
 */
public enum WcRenewalType{

    NEW(1,"New","New"),
    RENEWAL(2,"Renewal","Renewal");


    private final Integer id;
    private final String value;
    private final String description;
    WcRenewalType(Integer id, String value, String description) {
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
    public static WcRenewalType getById(Integer id) {
        switch (id) {
            case 1:
                return NEW;
            case 2:
                return RENEWAL;
            default:
                return null;
        }
    }
    public static WcRenewalType[] getAll() {
        return WcRenewalType.values();

    }
}
