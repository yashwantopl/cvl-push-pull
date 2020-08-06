package com.opl.mudra.api.enums.oneform;

public enum DirectorRelationshipType {

    SHAREHOLDER(1,"Shareholder","Shareholder"),
    HOLDING_COMPANY(2,"Holding Company","Holding Company"),
    SUBSIDIARY_COMPANY(3,"Subsidary Company","Subsidary Company"),
    PROPRIETOR(4,"Proprietor","Proprietor"),
    PARTNER(5,"Partner","Partner"),
    TRUSTEE(6,"Trustee","Trustee"),
    PROMOTER_DIRECTOR(7,"Promoter Director","Promoter Director"),
    NOMINEE_DIRECTOR(8,"Nominee Director","Nominee Director"),
    INDEPENDENT_DIRECTOR(9,"Independent Director","Independent Director"),
    DIRECTORE_SINCE(10,"Director - Since Resigned","Director - Since Resigned"),
    INDIVIDUAL_SHG(11,"Individual Member of SHG","Individual Member of SHG"),
    OTHER_DIRECTOR(12,"Other Director","Other Director"),
    OTHERS(13,"Others","Others");

    private final Integer id;
    private final String value;
    private final String description;

    DirectorRelationshipType(Integer id, String value, String description) {
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
    public static DirectorRelationshipType getById(Integer id) {
        switch (id) {
            case 1:
                return SHAREHOLDER;
            case 2:
                return HOLDING_COMPANY;
            case 3:
                return SUBSIDIARY_COMPANY;
            case 4:
                return PROPRIETOR;
            case 5:
                return PARTNER;
            case 6:
                return TRUSTEE;
            case 7:
                return PROMOTER_DIRECTOR;
            case 8:
                return NOMINEE_DIRECTOR;
            case 9:
                return INDEPENDENT_DIRECTOR;
            case 10:
                return DIRECTORE_SINCE;
            case 11:
                return INDIVIDUAL_SHG;
            case 12:
                return OTHER_DIRECTOR;
            case 13:
                return OTHERS;
            default:
                return null;
        }
    }
    public static DirectorRelationshipType[] getAll() {
        return DirectorRelationshipType.values();

    }
}
