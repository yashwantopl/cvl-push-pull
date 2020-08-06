package com.opl.mudra.api.analyzer.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StringList {
private List<String> passList;

public List<String> getPassList() {
	return passList;
}

public void setPassList(List<String> passList) {
	this.passList = passList;
}




}
