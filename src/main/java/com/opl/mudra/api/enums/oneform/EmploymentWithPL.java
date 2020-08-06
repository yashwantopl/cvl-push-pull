package com.opl.mudra.api.enums.oneform;

public enum EmploymentWithPL {
    CENTRAL_GOVERNMENT(1, "Central Government", "Central Government"),
    STATE_GOVERNMENT(2, "State Government", "State Government"),
    PSU(3, "Public Sector Units", "Public Sector Units"),
    CORPORATE(4,"Company","Company"),
    EDUCATIONAL_INSTITUTE(5,"Educational Institute","Educational Institute"),
    QUASI_GOVERNMENT(7,"Quasi Government","Quasi Government"),
    BANK(8, "Bank", "Bank"),
    INSURANCE_COMPANY(9, "Insurance Company", "Insurance Company"),
    SMALL_SECTOR_PVT_LTD_COMPANIES(10, "Small Sector (Pvt Ltd. Companies)", "Small Sector (Pvt Ltd. Companies)"),
    SMALL_SECTOR_PARTNERSHIP(11, "Small Sector (Partnership)", "Small Sector (Partnership)"),
    SMALL_SECTOR_PROPRIETORSHIP(12, "Small Sector (Proprietorship)", "Small Sector (Proprietorship)"),
    UNORGANISED_SECTOR(13, "Unorganised Sector", "Unorganised Sector)"),
    OTHERS(6,"Others","Others");

    private final Integer id;
    private final String value;
    private final String description;

    EmploymentWithPL(Integer id, String value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public static EmploymentWithPL[] getAll() {
        return EmploymentWithPL.values();

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

    public static EmploymentWithPL getById(Integer id) {
        switch (id) {
            case 1:
                return CENTRAL_GOVERNMENT;
            case 2:
                return STATE_GOVERNMENT;
            case 3:
                return PSU;
            case 4:
                return CORPORATE;
            case 5:
                return EDUCATIONAL_INSTITUTE;
            case 6:
                return OTHERS;
            case 7:
                return QUASI_GOVERNMENT;
            case 8:
                return BANK;
            case 9:
                return INSURANCE_COMPANY;
            case 10:
                return SMALL_SECTOR_PVT_LTD_COMPANIES;
            case 11:
                return SMALL_SECTOR_PARTNERSHIP;
            case 12:
                return SMALL_SECTOR_PROPRIETORSHIP;
            case 13:
                return UNORGANISED_SECTOR;
            default:
                return null;
        }
    }
}
