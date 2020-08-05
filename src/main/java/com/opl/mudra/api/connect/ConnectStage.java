package com.opl.mudra.api.connect;

public enum ConnectStage {

    GST(0, "GST"),
    ITR(1, "ITR"),
    BANK_STATEMENT(2, "BANK STATEMENT"),
    DIRECTOR_BACKGROUND(3, "DIRECTOR BACKGROUND"),
    ONE_FORM(4, "ONE FORM"),
    MATCHES(5, "MATCHES"),
    PAYMENT(6, "PAYMENT"),
    COMPLETE(7, "COMPLETE"),
    MCQ(8, "MCQ"),
    IN_PRINCIPLE(9, "IN PRINCIPLE"),
    NBFC_MATCHES(10, "NBFC MATCHES"),

    NTB_BUSINESS_TYPE_SELECTION(101, "NTB BUSINESS TYPE SELECTION"),
    NTB_MCQ(102, "NTB MCQ"),
    NTB_ITR(103, "NTB ITR"),
    NTB_BANK_STATEMENT(104, "NTB BANK STATEMENT"),
    NTB_ONE_FORM(105, "NTB ONE FORM"),
    NTB_ONE_FORM_OTHER_DETAILS(106, "NTB ONE FORM OTHER DETAILS"),
    NTB_MATCHES(107, "NTB MATCHES"),
    NTB_PAYMENT(108, "NTB PAYMENT"),
    NTB_COMPLETE(109, "NTB COMPLETE"),
    NTB_IN_PRINCIPLE(110, "NTB IN PRINCIPLE"),

    RETAIL_MCQ(201, "RETAIL MCQ"),
    RETAIL_AADHAR(202, "RETAIL AADHAR"),
    RETAIL_ITR(203, "RETAIL ITR"),
    RETAIL_BANK_STATEMENT(204, "RETAIL BANK STATEMENT"),
    RETAIL_ONE_FORM_BASIC_DETAILS(205, "RETAIL ONE FORM BASIC DETAILS"),
    RETAIL_ONE_FORM_CIBIL_AUTHENTICATION(206, "RETAIL ONE FORM CIBIL AUTHENTICATION"),
    RETAIL_ONE_FORM_LOAN_DETAILS(207, "RETAIL ONE FORM LOAN DETAILS"),
    RETAIL_MATCHES(208, "RETAIL MATCHES"),
    RETAIL_PAYMENT(209, "RETAIL PAYMENT"),
    RETAIL_IN_PRINCIPLE(210, "RETAIL IN PRINCIPLE"),
    RETAIL_COMPLETE(211, "RETAIL COMPLETE"),
    RETAIL_LOANTYPE_SELECTION_NEW(212, "RETAIL LOANTYPE SELECTION NEW"),
    
    ONEPAGER_ONEFORM(301, "ONEPAGER ONE FORM"),
    ONEPAGER_CIBIL(302, "ONEPAGER_CIBIL"),
    ONEPAGER_MATCHES(303, "ONEPAGER MATCHES"),
    ONEPAGER_PAYMENT(304, "PAYMENT"),
    ONEPAGER_IN_PRICIPLE(305, "IN PRINCIPLE"),
    ONEPAGER_COMPLETE(306, "COMPLETE"),
    
    HL_MCQ(501, "HL MCQ"),
    HL_ITR(502, "HL ITR"),
    HL_ITR_CO_APP(503, "HL ITR CO-APP"),
    HL_BANK_STATEMENT(504, "HL BANK STATEMENT"),
    HL_BANK_STATEMENT_CO_APP(505, "HL BANK STATEMENT CO-APP"),
    HL_ONE_FORM_BASIC_DETAILS(506, "HL ONE FORM BASIC DETAILS"),
    HL_ONE_FORM_CIBIL_AUTHENTICATION(507, "HL ONE FORM CIBIL AUTHENTICATION"),
    HL_ONE_FORM_CIBIL_AUTH_CO_APP(508, "HL ONE FORM CIBIL AUTHENTICATION CO-APP"),
    HL_ONE_FORM_LOAN_DETAILS(509, "HL ONE FORM LOAN DETAILS"),
    HL_MATCHES(510, "HL MATCHES"),
    HL_PAYMENT(511, "HL PAYMENT"),
    HL_IN_PRINCIPLE(512, "HL IN PRINCIPLE"),
    HL_COMPLETE(513, "HL COMPLETE"),
	
	
    MUDRA_MCQ(1000, "MUDRA MCQ"),
	MUDRA_GST(1001, "MUDRA GST"),
	MUDRA_ITR(1002, "MUDRA ITR"),
	MUDRA_BANK_STATEMENT(1003, "MUDRA BANK STATEMENT"),
	MUDRA_DIRECTOR_BACKGROUND(1004, "MUDRA DIRECTOR BACKGROUND"),
	MUDRA_ONE_FORM(1005, "MUDRA ONE FORM"),
	MUDRA_MATCHES(1006, "MUDRA MATCHES"),
	MUDRA_PAYMENT(1007, "MUDRA PAYMENT"),
	MUDRA_IN_PRINCIPLE(1008, "MUDRA IN PRINCIPLE"),
	MUDRA_COMPLETE(1009, "MUDRA COMPLETE");
	

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
