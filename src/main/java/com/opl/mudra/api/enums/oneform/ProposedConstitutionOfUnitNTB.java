package com.opl.mudra.api.enums.oneform;

public enum ProposedConstitutionOfUnitNTB {

    PROPRIETORSHIP(1,"Proprietership","Proprietership"),
    PARTNERSHIP(2,"Partnership"," Partnership"),
    LIMITED_COMPANY(3,"Limited Company","Limited Company"),
    LLP(4,"LLP","LLP"),
    OTHERS(5,"Others","Others");

    private final Integer id;
    private final String value;
    private final String description;

    ProposedConstitutionOfUnitNTB(Integer id, String value, String description) {
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

    public static ProposedConstitutionOfUnitNTB getById(Integer id) {
        switch (id) {
            case 1:
                return PROPRIETORSHIP;
            case 2:
                return PARTNERSHIP;
            case 3:
                return LIMITED_COMPANY;
            case 4:
                return LLP;
            case 5:
                return OTHERS;
            default:
                return null;
        }
    }
    public static ProposedConstitutionOfUnitNTB[] getAll() {
        return ProposedConstitutionOfUnitNTB.values();

    }
}
