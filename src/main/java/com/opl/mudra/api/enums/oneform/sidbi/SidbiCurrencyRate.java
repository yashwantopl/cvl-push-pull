package com.opl.mudra.api.enums.oneform.sidbi;
/**
 * @author vijay.chauhan
 *
 */
public enum SidbiCurrencyRate {
	
	RUPEES(1,"Rupees","Rupees",1),
	RUPEES_IN_THOUSAND(2,"Rs.In Thousand","Rs.In Thousand",1000),
	RUPEES_IN_LAKH(3,"Rs.In Lakh","Rs.In Lakh",100000);
	
	private final Integer id;
	private final String value;
	private final String description;
	private final Integer rate;

	SidbiCurrencyRate(Integer id, String value, String description,Integer rate) {
		this.id = id;
		this.value = value;
		this.description = description;
		this.rate=rate;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	public Integer getRate() {
		return rate;
	}

	public static SidbiCurrencyRate getById(Integer id) {
		switch (id) {
		case 1:
			return RUPEES;

		case 2:
			return RUPEES_IN_THOUSAND;

		case 3:
			return RUPEES_IN_LAKH;
		default:
			return null;
		}
	}

	public static SidbiCurrencyRate[] getAll() {
		return SidbiCurrencyRate.values();

	}

}
