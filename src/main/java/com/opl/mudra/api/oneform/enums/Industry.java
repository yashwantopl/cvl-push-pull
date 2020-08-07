package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum Industry {
			AGRICULTURE_ALLIED_ACTIVITIES(1,"Agriculture and Allied Activities","Agriculture and Allied Activities"),
		AUTO_ANCILLARY(2,"Auto and Ancillary","Auto and Ancillary"),
		AVIATION(3,"Aviation","Aviation"),
		CONSTRUCTION_MATERIAL(4,"Construction Material","Construction Material"),
		CHEMICALS(5,"Chemicals","Chemicals"),
		CONSUMER_DURABLES(6,"Consumer Durables","Consumer Durables"),
		DEFENCE(7,"Defence","Defence"),
		EDUCATION(8,"Education","Education"),
		ENTERTAINMENT_MEDIA(9,"Entertainment and Media","Entertainment and Media"),
		ENGINEERING_CAPITAL_GOODS(10,"Engineering and Capital goods","Engineering and Capital goods"),
		FMCG(11,"FMCG","FMCG"),
		FINANCE_FINANCIAL_SERVICES(12,"Finance and Financial Services","Finance and Financial Services"),
		FOOD_BEVERAGES(13,"Food and Beverages","Food and Beverages"),
		HEALTHCARE(14,"HealthCare","HealthCare"),
		TRAVEL_HOSPITALITY(15,"Travel and Hospitality","Travel and Hospitality"),
		IT_ITES(16,"IT/ITES","IT/ITES"),
		INFRASTRUCTURE(17,"Infrastructure","Infrastructure"),
		MINERALS_COMMODITIES(18,"Minerals and commodities","Minerals and commodities"),
		OILGAS(19,"Oil and Gas","Oil and Gas"),
		POWER(20,"Power","Power"),
		REAL_ESTATE(21,"Real Estate","Real Estate"),
		SHIPPING_LOGISTICS(22,"Shipping and Logistics","Shipping and Logistics"),
		TEXTILES(23,"Textiles","Textiles"),
		TELECOMMUNICATION(24,"Telecommunication","Telecommunication"),
		RETAIL_ECOMMERCE(25,"Retail and E-Commerce","Retail and E-Commerce"),
		OTHERS(26,"Others","Others");
	
		private final Integer id;
		private final String value;
		private final String description;
		
		Industry(Integer id, String value, String description) {
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
		public static Industry getById(Integer id) {
		switch (id) {
		case 1:
			return AGRICULTURE_ALLIED_ACTIVITIES;
		case 2:
			return AUTO_ANCILLARY;
		case 3:
			return AVIATION;
		case 4:
			return CONSTRUCTION_MATERIAL;
		case 5:
			return CHEMICALS;
		case 6:
			return CONSUMER_DURABLES;
		case 7:
			return DEFENCE;
		case 8:
			return EDUCATION;
		case 9:
			return ENTERTAINMENT_MEDIA;
		case 10:
			return ENGINEERING_CAPITAL_GOODS;
		case 11:
			return FMCG;
		case 12:
			return FINANCE_FINANCIAL_SERVICES;
		case 13:
			return FOOD_BEVERAGES;
		case 14:
			return HEALTHCARE;
		case 15:
			return TRAVEL_HOSPITALITY;
		case 16:
			return IT_ITES;
		case 17:
			return INFRASTRUCTURE;
		case 18:
			return MINERALS_COMMODITIES;
		case 19:
			return OILGAS;
		case 20:
			return POWER;
		case 21:
			return REAL_ESTATE;
		case 22:
			return SHIPPING_LOGISTICS;
		case 23:
			return TEXTILES;
		case 24:
			return TELECOMMUNICATION;
		case 25:
			return RETAIL_ECOMMERCE;
		case 26:
			return OTHERS;
		default:
			return null;
		}
	}
		public static Industry[] getAll() {
			return Industry.values();

		}
}