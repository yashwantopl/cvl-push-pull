package com.opl.mudra.api.oneform.enums;

public enum  RePaymentTrackRecordMfi {
    ON_TIME(1," Payment on Time"),
    SEVEN_DUE_DATE(2,"Payment within 7 days of due date"),
    FIFTEEN_DUE_DATE(3,"Payment within 15 days due date"),
    THIRTY_DUEDAY(4,"Payment within 30 days of due date");

    private Integer id;
    private String value;

    private RePaymentTrackRecordMfi(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static RePaymentTrackRecordMfi fromValue(String v) {
        for (RePaymentTrackRecordMfi c : RePaymentTrackRecordMfi.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static RePaymentTrackRecordMfi fromId(String v) {
        for (RePaymentTrackRecordMfi c : RePaymentTrackRecordMfi.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static RePaymentTrackRecordMfi[] getAll() {
        return RePaymentTrackRecordMfi.values();
    }
}
