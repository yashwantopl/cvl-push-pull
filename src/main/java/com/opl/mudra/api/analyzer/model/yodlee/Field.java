package com.opl.mudra.api.analyzer.model.yodlee;

public class Field {
	private Integer id;
	private String name;
	private Integer maxLength;
	private String type;
	private String value;
	private Boolean isOptional;
	private Boolean valueEditable;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getIsOptional() {
		return isOptional;
	}
	public void setIsOptional(Boolean isOptional) {
		this.isOptional = isOptional;
	}
	public Boolean getValueEditable() {
		return valueEditable;
	}
	public void setValueEditable(Boolean valueEditable) {
		this.valueEditable = valueEditable;
	}
	
	
}
