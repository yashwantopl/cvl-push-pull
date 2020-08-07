package com.opl.mudra.api.oneform.enums;

public enum  HouseTypeMfi {
    CEMENTED(2, "Cemented"),
    THATCHED(1, "Thatched (Kuccha)");


    private Integer id;
    private String value;

    private HouseTypeMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static HouseTypeMfi fromValue(String v) {
        for (HouseTypeMfi c : HouseTypeMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static HouseTypeMfi fromId(String v) {
        for (HouseTypeMfi c : HouseTypeMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static HouseTypeMfi[] getAll() {
        return HouseTypeMfi.values();
    }
    
    public static HouseTypeMfi getById(Integer id) {
		switch (id) {
		case 1:
			return CEMENTED;
		case 2:
			return THATCHED;
		
		default:
			return null;
		}
    }
    
    
    

}
