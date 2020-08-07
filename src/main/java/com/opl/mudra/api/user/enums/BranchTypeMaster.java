package com.opl.mudra.api.user.enums;

public enum BranchTypeMaster {

	BO(1,"Branch Office"),
	RO(2,"Regional Office"), 
	ZO(3,"Zonal Office"), 
	HO(4,"Head Office");
	
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	

	private BranchTypeMaster(int id, String name) {
		this.id =id ;
		this.name= name;
	}
	
	
	public static BranchTypeMaster getById(Integer id) {
		switch (id) {
		case 1:
			return BO;
		case 2:
			return RO;
		case 3:
			return ZO;
		case 4:
			return HO;
		default:
			return null;
		}
	}
	
	
	
}
