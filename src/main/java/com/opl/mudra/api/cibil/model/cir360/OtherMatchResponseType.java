package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OtherMatchResponseType extends MatchResponseType {
	
	private String seq;
	
	@JsonProperty("Type")
	private List<String> type;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public List<String> getType() {
		if(type==null){
			type = new ArrayList<String>(); 
		}
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}
	
	
	
}
