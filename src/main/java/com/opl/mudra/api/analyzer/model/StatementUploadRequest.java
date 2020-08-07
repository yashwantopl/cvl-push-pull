package com.opl.mudra.api.analyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.analyzer.model.common.Statement;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatementUploadRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Statement> statements;

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
	
}
