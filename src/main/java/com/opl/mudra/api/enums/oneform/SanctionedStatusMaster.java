package com.opl.mudra.api.enums.oneform;

public enum SanctionedStatusMaster {

	IN_PROGRES(1, "In Progres"),
	ONLINE_REJECT(2, "Online Reject"),
	OFFLINE_REJECT(3, "Offline Reject"),
	REVERSE_API_ONLINE_REJECT(4, "Reverse API Online Reject"),
	REVERSE_API_OFFLINE_REJECT(5, "Reverse API Offline Reject"),
	ONLINE_HOLD(6, "Online Hold"),
	OFFLINE_HOLD(7, "Offline Hold"),
	REVERSE_API_ONLINE_HOLD(8, "Reverse API Online Hold"),
	REVERSE_API_OFFLINE_HOLD(9, "Reverse API Offline Hold"),
	ONLINE_DISBURSED(10, "Online Disbursed"),
	OFFLINE_DISBURSED(11, "Offline Disbursed"),
	REVERSE_API_ONLINE_DISBURSED(12, "Reverse API Online Disbursed"),
	REVERSE_API_OFFLINE_DISBURSED(13, "Reverse API Offline Disbursed"),
	ONLINE_PARTIALLY_DISBURSED(14, "Online Partially Disbursed"),
	OFFLINE_PARTIALLY_DISBURSED(15, "Offline Partially Disbursed"),
	REVERSE_API_ONLINE_PARTIALLY_DISBURSED(16, "Reverse API Online Partially Disbursed"),
	REVERSE_API_OFFLINE_PARTIALLY_DISBURSED(17, "Reverse API Offline Partially Disbursed");

    private Integer id;
    private String value;

    private SanctionedStatusMaster(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static SanctionedStatusMaster fromValue(String v) {
        for (SanctionedStatusMaster c : SanctionedStatusMaster.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static SanctionedStatusMaster fromId(String v) {
        for (SanctionedStatusMaster c : SanctionedStatusMaster.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static SanctionedStatusMaster[] getAll() {
        return SanctionedStatusMaster.values();
    }

}
