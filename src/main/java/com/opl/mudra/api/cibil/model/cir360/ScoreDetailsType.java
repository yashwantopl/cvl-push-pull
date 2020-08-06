package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ScoreDetailsType {

	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("Version")
	private String version;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Value")
	private String value;
	
	@JsonProperty("ScoringElements")
	private List<ScoringElementsType> scoringElements;
	
	@JsonProperty("Error")
	private ErrorDetails errorDetails;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<ScoringElementsType> getScoringElements() {
		return scoringElements;
	}

	public void setScoringElements(List<ScoringElementsType> scoringElements) {
		this.scoringElements = scoringElements;
	}

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}
}
