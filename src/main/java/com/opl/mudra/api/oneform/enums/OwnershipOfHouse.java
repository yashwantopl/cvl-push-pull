package com.opl.mudra.api.oneform.enums;

public enum OwnershipOfHouse {
    OWN(1, "Own", "Own"),
    RENTED(2, "Rented", "Rented");

    private final Integer id;
    private final String value;
    private final String description;

    OwnershipOfHouse(Integer id, String value, String description) {
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

    public static OwnershipOfHouse getById(Integer id) {
        switch (id) {
            case 1:
                return OWN;
            case 2:
                return RENTED;
            default:
                return null;
        }
    }

    public static OwnershipOfHouse[] getAll() {
        return OwnershipOfHouse.values();

    }
}