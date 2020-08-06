package com.opl.mudra.api.oneform.enums;

/**
 * For Liability perticulars
 */
public enum CurrentPerticularsLiabilitiesMfi {

    BUSINESS(1,"Business Liability");

    private Integer id;
    private String value;

    private CurrentPerticularsLiabilitiesMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static CurrentPerticularsLiabilitiesMfi fromValue(String v) {
        for (CurrentPerticularsLiabilitiesMfi c : CurrentPerticularsLiabilitiesMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static CurrentPerticularsLiabilitiesMfi fromId(String v) {
        for (CurrentPerticularsLiabilitiesMfi c : CurrentPerticularsLiabilitiesMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static CurrentPerticularsLiabilitiesMfi[] getAll() {
        return CurrentPerticularsLiabilitiesMfi.values();
    }
}
