package com.opl.mudra.api.oneform.enums;

public enum HouseOwnershipMfi {

    OWNED(1, "Owned"),
    RENTED(2, "Rented");

    private Integer id;
    private String value;

    private HouseOwnershipMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static HouseOwnershipMfi fromValue(String v) {
        for (HouseOwnershipMfi c : HouseOwnershipMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static HouseOwnershipMfi fromId(String v) {
        for (HouseOwnershipMfi c : HouseOwnershipMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static HouseOwnershipMfi[] getAll() {
        return HouseOwnershipMfi.values();
    }
}
