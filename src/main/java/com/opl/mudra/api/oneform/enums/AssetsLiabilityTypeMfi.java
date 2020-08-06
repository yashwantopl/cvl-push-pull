package com.opl.mudra.api.oneform.enums;

public enum AssetsLiabilityTypeMfi {

    FIXED(1, "Fixed"),
    CURRENT(2, "Current");

    private Integer id;
    private String value;

    private AssetsLiabilityTypeMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static AssetsLiabilityTypeMfi fromValue(String v) {
        for (AssetsLiabilityTypeMfi c : AssetsLiabilityTypeMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static AssetsLiabilityTypeMfi fromId(String v) {
        for (AssetsLiabilityTypeMfi c : AssetsLiabilityTypeMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static AssetsLiabilityTypeMfi[] getAll() {
        return AssetsLiabilityTypeMfi.values();
    }

}
