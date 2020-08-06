package com.opl.mudra.api.oneform.enums;

public enum ClientTypeMfi {
    NEW(1, "New Client"),
    EXISTING(2, "Existing Client");
    
    private Integer id;
    private String value;

    private ClientTypeMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static ClientTypeMfi fromValue(String v) {
        for (ClientTypeMfi c : ClientTypeMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static ClientTypeMfi fromId(String v) {
        for (ClientTypeMfi c : ClientTypeMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static ClientTypeMfi[] getAll() {
        return ClientTypeMfi.values();
    }
    
    public static ClientTypeMfi getById(Integer id) {
		switch (id) {
		case 1:
			return NEW;
		case 2:
			return EXISTING;
		
		default:
			return null;
		}
    }
    
}
