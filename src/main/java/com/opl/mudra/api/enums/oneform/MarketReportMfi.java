package com.opl.mudra.api.enums.oneform;

public enum MarketReportMfi {
    POOR(1, "Poor"),
    NOT_GOOD(2, "Not Good"),
    NEUTRAL(3, "Neutral"),
    GOOD(4, " Good"),
    VERY_GOOD(5, "Very Good");

    private Integer id;
    private String value;

    private MarketReportMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static MarketReportMfi fromValue(String v) {
        for (MarketReportMfi c : MarketReportMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static MarketReportMfi fromId(String v) {
        for (MarketReportMfi c : MarketReportMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static MarketReportMfi[] getAll() {
        return MarketReportMfi.values();
    }
}
