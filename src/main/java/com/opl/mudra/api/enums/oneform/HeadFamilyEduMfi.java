package com.opl.mudra.api.enums.oneform;

public enum  HeadFamilyEduMfi {
    ILLITERATE(1, "Illiterate"),
    SECONDARY(2, "Secondary (7th Standard & Above)"),
    MATRICULATE_ABOVE(3, "Matriculate & Above");



    private Integer id;
    private String value;

    private HeadFamilyEduMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static HeadFamilyEduMfi fromValue(String v) {
        for (HeadFamilyEduMfi c : HeadFamilyEduMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static HeadFamilyEduMfi fromId(String v) {
        for (HeadFamilyEduMfi c : HeadFamilyEduMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static HeadFamilyEduMfi[] getAll() {
        return HeadFamilyEduMfi.values();
    }
    
    public static HeadFamilyEduMfi getById(Integer id) {
		switch (id) {
		case 1:
			return ILLITERATE;
		case 2:
			return SECONDARY;
		case 3:
			return MATRICULATE_ABOVE;
		default:
			return null;
		}
    }
}
