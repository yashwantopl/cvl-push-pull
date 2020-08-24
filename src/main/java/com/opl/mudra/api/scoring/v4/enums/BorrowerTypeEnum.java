package com.opl.mudra.api.scoring.v4.enums;

public enum BorrowerTypeEnum {

	MICRO(1, "Micro"),
	SMALL(2, "Small"),
	MEDIUM(3, "Medium");
	
	Integer id;
	String value;
	
	public Integer getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	
	private BorrowerTypeEnum(Integer id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public static BorrowerTypeEnum[] getAll() {
		return BorrowerTypeEnum.values();
	}
	
	public static BorrowerTypeEnum findByValue(String value) {
		for(BorrowerTypeEnum obj : BorrowerTypeEnum.values()) {
			if(obj.getValue().equals(value)) {
				return obj;
			}
		}
		return null;
	}
	
	
}
