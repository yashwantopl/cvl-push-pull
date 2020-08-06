package com.opl.mudra.api.enums.oneform;

public enum BusinessTypeMfi {
    MANUFACTURING(1, "Manufacturing"),
    SERVICE(3, "Services"),
    TRADE(2, "Trade"),
    OTHERS(4, "Others");

    private Integer id;
    private String value;

    private BusinessTypeMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static BusinessTypeMfi fromValue(String v) {
        for (BusinessTypeMfi c : BusinessTypeMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static BusinessTypeMfi fromId(String v) {
        for (BusinessTypeMfi c : BusinessTypeMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static BusinessTypeMfi[] getAll() {
        return BusinessTypeMfi.values();
    }
    
    public static BusinessTypeMfi getById(Integer id) {
		switch (id) {
		case 1:
			return MANUFACTURING;
		case 2:
			return TRADE;
		case 3:
			return SERVICE;
		case 4:
			return OTHERS;	
		default:
			return null;
		}
	}
    
    

}
