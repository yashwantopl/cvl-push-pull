package com.opl.mudra.api.oneform.enums;

public enum FamilyMembersMfi {
    EIGHT_OR_MORE(1, "8 or more"),
    SEVEN_MEMBERS(2, "7"),
    SIX_MEMBERS(3, "6"),
    FIVE_MEMBERS(4, "5"),
    FOUR_MEMBERS(5, "4"),
    THREE_MEMBERS(6, "3"),
    TWO_MEMBERS(7, "2"),
    ONE_MEMBERS(8, "1");

    private Integer id;
    private String value;

    private FamilyMembersMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static FamilyMembersMfi fromValue(String v) {
        for (FamilyMembersMfi c : FamilyMembersMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static FamilyMembersMfi fromId(String v) {
        for (FamilyMembersMfi c : FamilyMembersMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static FamilyMembersMfi[] getAll() {
        return FamilyMembersMfi.values();
    }

}
