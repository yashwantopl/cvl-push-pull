package com.opl.mudra.api.oneform.enums;

public enum EmploymentWithRetail {

    SMALL_SECTOR_PVT_LTD_COMPANIES(10, "Small Sector (Pvt Ltd. Companies)", "Small Sector (Pvt Ltd. Companies)"),
    SMALL_SECTOR_PARTNERSHIP(11, "Small Sector (Partnership)", "Small Sector (Partnership)"),
    SMALL_SECTOR_PROPRIETORSHIP(12, "Small Sector (Proprietorship)", "Small Sector (Proprietorship)"),
    UNORGANISED_SECTOR(13, "Unorganised Sector", "Unorganised Sector)"),
    OTHERS(6,"Others","Others");

    private final Integer id;
    private final String value;
    private final String description;

    EmploymentWithRetail(Integer id, String value, String description) {
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

    public static EmploymentWithRetail getById(Integer id) {
        switch (id) {
            case 6:
                return OTHERS;
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


    public static EmploymentWithRetail[] getAll() {
        return EmploymentWithRetail.values();

    }
}
