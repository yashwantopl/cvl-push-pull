package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "scoreType", "version", "attributeList", })
public class ScoreAttributes {

	@JsonProperty("scoreType")
	private String scoreType;
	@JsonProperty("version")
	private String version;
	@JsonProperty("attributeList")
	private List<AttributeList> attributeList;

	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<AttributeList> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<AttributeList> attributeList) {
		this.attributeList = attributeList;
	}

}
