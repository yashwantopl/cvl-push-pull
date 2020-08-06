package com.opl.mudra.api.oneform.enums;

public enum RepaymentMode {

	CHECK_OFF(1,"Check Off","CHECK_OFF"),
	ECS_OTHER_ACCOUNT(2,"ECS/Standing Instruction (Other Account)","ECS_OTHER_ACCOUNT"),
	ECS_SALARY_ACCOUNT(3,"ECS/Standing Instruction (Salary Account)","ECS_SALARY_ACCOUNT"),
	POST_DATED_CHEQUES(4,"Post Dated Cheques","POST_DATED_CHEQUES"),
	OTHERS(5,"Others","OTHERS");
	
	private final Integer id;
	private final String value;
	private final String description;
	
	
	private RepaymentMode(Integer id, String value, String description) {
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
	
	public static RepaymentMode getById(Integer id) {
		switch (id) {
		case 1:
			return CHECK_OFF;

		case 2:
			return ECS_OTHER_ACCOUNT;
			
		case 3:
			return ECS_SALARY_ACCOUNT;
			
		case 4:
			return POST_DATED_CHEQUES;
			
		case 5:
			return OTHERS;

		default:
			return null;
		}
	}

	public static RepaymentMode[] getAll() {
		return RepaymentMode.values();
	}	
}
