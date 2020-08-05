package com.opl.mudra.api.matchengine.model;

public class MatchDisplayObject {

	private Boolean isMatched;
	
	private Object value;
	
	private Object fpValue;

	private Boolean isParameterSelected;

	private Boolean isParameterMandatory;
	
	private String type;
	
	public MatchDisplayObject() {
		super();
		this.isMatched = false;
		this.value = "-";
		this.fpValue = "-";
		this.isParameterSelected = false;
		this.isParameterMandatory = false;
	}

	public Object getFpValue() {
		return fpValue;
	}

	public void setFpValue(Object fpValue) {
		this.fpValue = fpValue;
	}

	public Boolean getIsMatched() {
		return isMatched;
	}

	public void setIsMatched(Boolean isMatched) {
		this.isMatched = isMatched;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Boolean getIsParameterSelected() {
		return isParameterSelected;
	}

	public void setIsParameterSelected(Boolean parameterSelected) {
		isParameterSelected = parameterSelected;
	}

	public Boolean getIsParameterMandatory() {
		return isParameterMandatory;
	}

	public void setIsParameterMandatory(Boolean parameterMandatory) {
		isParameterMandatory = parameterMandatory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MatchDisplayObject [isMatched=" + isMatched + ", value=" + value + ", fpValue=" + fpValue
				+ ", isParameterSelected=" + isParameterSelected + ", isParameterMandatory=" + isParameterMandatory
				+ ", type=" + type + "]";
	}
}
