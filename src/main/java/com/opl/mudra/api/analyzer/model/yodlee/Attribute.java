package com.opl.mudra.api.analyzer.model.yodlee;

import java.util.List;

public class Attribute {
	private String name;
	private List<String> container = null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getContainer() {
		return container;
	}
	public void setContainer(List<String> container) {
		this.container = container;
	}
	public Attribute(String name, List<String> container) {
		super();
		this.name = name;
		this.container = container;
	}
	public Attribute() {
		super();
	}
	
	
	
	
}
