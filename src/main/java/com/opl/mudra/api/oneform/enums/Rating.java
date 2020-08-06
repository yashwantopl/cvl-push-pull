package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum Rating {
			AAA(1,"AAA","AAA"),
			AA_plus(2,"AA+","AA+"),
			AA(3,"AA","AA"),
		AA_minus(4,"AA-","AA-"),
		A_plus(5,"A+","A+"),
		A(6,"A","A"),
		A_minus(7,"A-","A-"),
		BBB_plus(8,"BBB+","BBB+"),
		BBB(9,"BBB","BBB"),
	BBB_minus(10,"BBB-","BBB-"),
	BB_plus(11,"BB+","BB+"),
	BB(12,"BB","BB"),
	BB_minus(13,"BB-","BB-"),
	B_plus(14,"B+","B+"),
	B(15,"B","B"),
	B_minus(16,"B-","B-"),
	C_plus(17,"C+","C+"),
C(18,"C","C"),
C_minus(19,"C-","C-"),
D(20,"D","D");
	
		private final Integer id;
		private final String value;
		private final String description;
		Rating(Integer id, String value, String description) {
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
		public static Rating getById(Integer id) {
		switch (id) {
		case 1:
			return AAA;
		case 2:
			return Rating.AA_plus;
		case 3:
			return AA;
		case 4:
			return AA_minus;
		case 5:
			return A_plus;
		case 6:
			return A;
		case 7:
			return A_minus;
		case 8:
			return BBB_plus;
		case 9:
			return BBB;
		case 10:
			return BBB_minus;
		case 11:
			return BB_plus;
		case 12:
			return BB;
		case 13:
			return BB_minus;
		case 14:
			return B_plus;
		case 15:
			return B;
		case 16:
			return B_minus;
		case 17:
			return C_plus;
		case 18:
			return C;
		case 19:
			return C_minus;
		case 20:
			return D;
		default:
			return null;
		}
	}
		public static Rating[] getAll() {
			return Rating.values();

		}
}