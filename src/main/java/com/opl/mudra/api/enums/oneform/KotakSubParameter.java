package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum KotakSubParameter {
	LOWER_OF_ABOVE(1,"Lower of above selected","Lowe of above selected");
		
		private final Integer id;
		private final String value;
		private final String description;
		KotakSubParameter(Integer id, String value, String description) {
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
  public static KotakSubParameter getById(Integer id) {
      switch (id) {
          case 1:
              return LOWER_OF_ABOVE;
          default:
              return null;
      }
  }
  public static KotakSubParameter[] getAll() {
      return KotakSubParameter.values();

  }
}