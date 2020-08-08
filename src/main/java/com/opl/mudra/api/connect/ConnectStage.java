package com.opl.mudra.api.connect;

public enum ConnectStage {
	
    MUDRA_MCQ(1000, "MUDRA MCQ"),
	MUDRA_GST(1001, "MUDRA GST"),
	MUDRA_ITR(1002, "MUDRA ITR"),
	MUDRA_BANK_STATEMENT(1003, "MUDRA BANK STATEMENT"),
	MUDRA_DIRECTOR_BACKGROUND(1004, "MUDRA DIRECTOR BACKGROUND"),
	MUDRA_ONE_FORM(1005, "MUDRA ONE FORM"),
	MUDRA_MATCHES(1006, "MUDRA MATCHES"),
	MUDRA_PAYMENT(1007, "MUDRA PAYMENT"),
	MUDRA_IN_PRINCIPLE(1008, "MUDRA IN PRINCIPLE"),
	MUDRA_COMPLETE(1009, "MUDRA COMPLETE"),
    BRANCH_SELECTION(1010, "BRANCH SELECTION"),
	COMPLETE(7, "COMPLETE");
	

    private Integer id;
    private String value;

    private ConnectStage(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static ConnectStage fromValue(String v) {
        for (ConnectStage c : ConnectStage.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static ConnectStage fromId(Integer v) {
        for (ConnectStage c : ConnectStage.values()) {
            if (c.id.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());

    }
}
