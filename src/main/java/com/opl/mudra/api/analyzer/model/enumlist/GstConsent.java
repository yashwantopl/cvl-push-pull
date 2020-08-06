package com.opl.mudra.api.analyzer.model.enumlist;

public enum GstConsent {

	
	MY_SALES_THROUGH_CASH(1,"Receipt of most of my Sales is through Cash"),
	MONEY_RECEIVED_IN_ADVANCE(2, "Money is received in advance against the order"),
	LONG_PAYMENT_CYCLE(3,"There is Long Payment Cycle and therefore receipt of funds is less compared to sales");
	
	private GstConsent(int id,String value) {
		this.id = id;
		this.value = value;
	}
	
	private int id;
	private String value;
	
	public int getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	
	 public static GstConsent getById(Integer id) {
	        switch (id) {
	            case 1:
	                return MY_SALES_THROUGH_CASH;
	            case 2:
	                return MONEY_RECEIVED_IN_ADVANCE;
	            case 3:
	                return LONG_PAYMENT_CYCLE;
	            default:
	                return null;
	        }
	    }
	
	
	
}
