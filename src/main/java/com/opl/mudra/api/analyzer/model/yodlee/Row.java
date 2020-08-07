package com.opl.mudra.api.analyzer.model.yodlee;

import java.util.List;

public class Row {
	
	private Integer id;
	private String label;
	private String form;
	private String fieldRowChoice;
	private List<Field> field = null;
	private String fields;

	public List<Field> getField() {
		return field;
	}

	public void setField(List<Field> field) {
		this.field = field;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getFieldRowChoice() {
		return fieldRowChoice;
	}

	public void setFieldRowChoice(String fieldRowChoice) {
		this.fieldRowChoice = fieldRowChoice;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}


	
	
	
	
}
