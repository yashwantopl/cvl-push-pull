package com.opl.mudra.api.oneform.enums;

public enum CGTMSEParameterFP {

    EXISTING(1,"Existing CGTMSE Coverage","Existing CGTMSE Coverage"),
    NEW(2,"New to CGTMSE Coverage","New to CGTMSE Coverage"),
    BOTH(3,"Both","Both");

    private final Integer id;
    private final String value;
    private final String description;

    CGTMSEParameterFP(Integer id, String value, String description) {
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

    public static CGTMSEParameterFP getById(Integer id) {
        switch (id) {
            case 1:
                return EXISTING;

            case 2:
                return NEW;

            case 3:
                return BOTH;

            default:
                return null;
        }
    }

    public static CGTMSEParameterFP[] getAll() {
        return CGTMSEParameterFP.values();

    }
}
