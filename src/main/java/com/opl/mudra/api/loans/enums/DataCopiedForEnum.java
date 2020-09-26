package com.opl.mudra.api.loans.enums;

/**
 * @author akshay.patel
 */
public enum DataCopiedForEnum {

	COPIED_DATA_FOR_KPD(1,"Data copied for key person"),
	COPIED_DATA_FOR_ONE_FORM(2,"Data copied for One form");

	Integer id;
	String des;

	DataCopiedForEnum(int id,String des){
		this.id=id;
		this.des=des;
	}

	public static DataCopiedForEnum getById(Integer id){
		for(DataCopiedForEnum dataCopiedForEnum :DataCopiedForEnum.values()){
			if(dataCopiedForEnum.id.equals(id)){
				return dataCopiedForEnum;
			}
		}
		return null;
	}

	public Integer getId(){
		return this.id;
	}

	public String getDes(){
		return this.des;
	}
}
