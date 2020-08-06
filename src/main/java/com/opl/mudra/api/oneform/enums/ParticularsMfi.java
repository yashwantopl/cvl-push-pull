package com.opl.mudra.api.oneform.enums;

public enum ParticularsMfi {

    AGRICULTURE(7, "Agricultural (F)",1),
    BUSINESS_RECE(3, "Business Receivable (C)",2),
    CUTTLE_STOCK(1, "Cattle Stock (C)",2),
    CASE_BANK_BALANSE(5, "Cash & Bank Balance (C)",2),
    EXISTING_STOCK(2, "Existing stock in trade (C)",2),
    PLANT_MACHINE(4, "Plant, Machine & Furniture (C)",2),
    SHOP_GODOWN(9, "Shop/Godown (F)",1),
    VALUE_HOUSE(8, "Value of House (F)",1),
    OTHER_ASSETS(10, "Other fixed assets (F)",1),
    OTHER(6, "Other current assets (C)",2);

    private Integer id;
    private String value;
    private Integer type;

    private ParticularsMfi(Integer id, String value, Integer type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public static ParticularsMfi fromValue(String v) {
        for (ParticularsMfi c : ParticularsMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static ParticularsMfi fromId(String v) {
        for (ParticularsMfi c : ParticularsMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static ParticularsMfi[] getAll() {
        return ParticularsMfi.values();
    }

}
