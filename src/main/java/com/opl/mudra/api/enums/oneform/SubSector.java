package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum SubSector {
			MANUFACTURER(1,"Manufacturer","Manufacturer"),
		DISTRIBUTOR(2,"Distributor","Distributor"),
		RETAILER(3,"Retailer","Retailer"),
		PROCESSER(4,"Processer","Processer"),
		WHOLESELLER(5,"Wholeseller","Wholeseller"),
		TRADER(6,"Trader","Trader"),
		SERVICE_PROVIDER(7,"Service Provider","Service Provider"),
		OWNERS(8,"Owners","Owners"),
		DEVELOPERS(9,"Developers","Developers"),
		OPERATORS(10,"Operators","Operators"),
		EPC(11,"EPC","EPC"),
		PMC(12,"PMC","PMC"),
		MINE_OWNERS(13,"Mine Owners","Mine Owners"),
		MINE_OPERATORS(14,"Mine Operators","Mine Operators"),
		SERVICE(15,"Service","Service"),
		DEALER(16,"Dealer","Dealer"),
		INFRA(17,"Infra","Infra"),
		UTILITIES(18,"Utilities","Utilities");
		
		private final Integer id;
		private final String value;
		private final String description;
		SubSector(Integer id, String value, String description) {
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
		public static SubSector getById(Integer id) {
		switch (id) {
		case 1:
			return MANUFACTURER;
		case 2:
			return DISTRIBUTOR;
		case 3:
			return RETAILER;
		case 4:
			return PROCESSER;
		case 5:
			return WHOLESELLER;
		case 6:
			return TRADER;
		case 7:
			return SERVICE_PROVIDER;
		case 8:
			return OWNERS;
		case 9:
			return DEVELOPERS;
		case 10:
			return OPERATORS;
		case 11:
			return EPC;
		case 12:
			return PMC;
		case 13:
			return MINE_OWNERS;
		case 14:
			return MINE_OPERATORS;
		case 15:
			return SERVICE;
		case 16:
			return DEALER;
		case 17:
			return INFRA;
		case 18:
			return UTILITIES;
		default:
			return null;
		}
	}
		public static SubSector[] getAll() {
			return SubSector.values();

		}
}