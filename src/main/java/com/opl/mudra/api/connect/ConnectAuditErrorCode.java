package com.opl.mudra.api.connect;

public class ConnectAuditErrorCode {
	
	public static final Integer OPTION_SUBMIT = 100001;
	public static final Integer GST_YUVA = 200001;
	public static final Integer GST_KARZA = 200002;
	public static final Integer ITR_UPLOAD = 300001;
	public static final Integer ITR_ONLINE = 300002;
	public static final Integer ITR_SCRAPE = 300003;
	public static final Integer BANKSTATEMENT_UPLOAD = 400001;
	public static final Integer BANKSTATEMENT_ONLINE = 400002;
	public static final Integer DIRECTOR_SUBMIT = 500001;
	public static final Integer ONFORM_SUBMIT = 600001;
	public static final Integer CIBIL_SERVICE_ERROR = 600020;
	public static final Integer CIBIL_INTERNAL_SERVIER_ERROR = 600021;
	public static final Integer CIBIL_INSUFFICIANT_DATA_ERROR = 600022;
	public static final Integer CIBIL_MSME_COMPNAY_SUCCESS = 600023;
	public static final Integer CIBIL_MSME_INDIVIDUAL_SUCCESS = 600024;
	public static final Integer CIBIL_SOFT_PING_SUCCESS = 600025;
	public static final Integer CIBIL_ERROR = 600026;
	public static final Integer CIBIL_INVALID_INPUTS = 600027;
	public static final Integer MATCHES = 700001;
	public static final Integer PAYMENT = 800001;
	public static final Integer IN_PRICIPLE = 900001;

	private ConnectAuditErrorCode() {
		//Do Nothing because of X and Y.
	}
}
