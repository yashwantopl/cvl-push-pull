package com.opl.mudra.api.enums.oneform;

public enum OwningHouseMst {

    HOME_OWNER(1,"Home Owner","HOME OWNER","H"),
    LIVING_FAMILY(2,"Living In Family Home","LIVING IN FAMILY HOME","F"),
    OTHER(3,"Other","OTHER","O"),
    TENANT(4,"Tenant","TENANT","T");

    private final Integer id;
    private final String value;
    private final String description;
    private final String sbiCode;

    OwningHouseMst(Integer id, String value, String description, String sbiCode) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.sbiCode = sbiCode;
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

    public String getSbiCode() {
        return sbiCode;
    }

    public static OwningHouseMst getById(Integer id) {
        switch (id) {
            case 1:
                return HOME_OWNER;
            case 2:
                return LIVING_FAMILY;
            case 3:
                return OTHER;
            case 4:
                return TENANT;
            default:
                return null;
        }
    }
    public static OwningHouseMst[] getAll() {
        return OwningHouseMst.values();

    }

    public static OwningHouseMst fromSbiCode(String v) {
        for (OwningHouseMst c : OwningHouseMst.values()) {
            if (c.sbiCode.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
