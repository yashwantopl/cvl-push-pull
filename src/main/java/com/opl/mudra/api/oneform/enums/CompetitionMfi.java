package com.opl.mudra.api.oneform.enums;

public enum CompetitionMfi {
    MORETHAN_TEN(1, "Having more than 10 business in the same area"),
    UPTO_TEN_SAME_AREA(2, "Having 6-10 business in the same area"),
    UPTO_FIVE_SAME_AREA(3, "Having 1-5 business in the same area");

    private Integer id;
    private String value;

    private CompetitionMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static CompetitionMfi fromValue(String v) {
        for (CompetitionMfi c : CompetitionMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static CompetitionMfi fromId(String v) {
        for (CompetitionMfi c : CompetitionMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static CompetitionMfi[] getAll() {
        return CompetitionMfi.values();
    }
    
    public static CompetitionMfi getById(Integer id) {
		switch (id) {
		case 1:
			return MORETHAN_TEN;
		case 2:
			return UPTO_TEN_SAME_AREA;
		case 3:
			return UPTO_FIVE_SAME_AREA;
		default:
			return null;
		}
    }
    
    
}
