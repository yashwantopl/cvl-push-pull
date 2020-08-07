package com.opl.mudra.api.oneform.enums;

public enum OrderBook {
	EXCELLENT_PRODUCT(1,"The current order book position/ demand for company products is excellent and is expected to remain the same in the medium term ","The current order book position/ demand for company products is excellent and is expected to remain the same in the medium term "),
	GOOD_PRODUCT(2,"The current order book position/ demand for company products is good, although its sustenance is not assured","The current order book position/ demand for company products is good, although its sustenance is not assured"),
	ORDERS_AVAILABLE(3,"The current order book position for company products is not sufficiently robust, but opportunities for getting orders are available","The current order book position for company products is not sufficiently robust, but opportunities for getting orders are available"),
	POOR_PRODUCT(4,"The current order book position/ demand for company products is poor and it is unlikely to improve in the medium term","The current order book position/ demand for company products is poor and it is unlikely to improve in the medium term");
	
		private final Integer id;
		private final String value;
		private final String description;
		OrderBook(Integer id, String value, String description) {
		this.id = id;
		this.value = value;
		this.description = description;
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
		public static OrderBook getById(Integer id) {
		switch (id) {
		case 1:
			return EXCELLENT_PRODUCT;
		case 2:
			return GOOD_PRODUCT;
		case 3:
			return ORDERS_AVAILABLE;
		case 4:
			return POOR_PRODUCT;
		default:
			return null;
		}
	}
		public static OrderBook[] getAll() {
			return OrderBook.values();

		}
}