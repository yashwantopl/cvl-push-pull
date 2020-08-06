package com.opl.mudra.api.enums.oneform;

public enum AreaTypeMfi {
    
    RURAL(1, "Rural"),
//    SEMI_URBAN(2, "Semi-Urban"),
    URBAN(2, "Urban");

    private Integer id;
    private String value;

    private AreaTypeMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static AreaTypeMfi fromValue(String v) {
        for (AreaTypeMfi c : AreaTypeMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static AreaTypeMfi fromId(String v) {
        for (AreaTypeMfi c : AreaTypeMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static AreaTypeMfi[] getAll() {
        return AreaTypeMfi.values();
    }
    
    public static AreaTypeMfi getById(Integer id) {
		switch (id) {
		case 1:
			return RURAL;
		case 2:
			return URBAN;
		default:
			return null;
		}
	}
    

}
