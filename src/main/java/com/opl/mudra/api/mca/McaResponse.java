package com.opl.mudra.api.mca;

import java.util.Collections;
import java.util.List;

/**
 * @author Sanket
 *
 */

public class McaResponse {
	
	private Long id;

	private Integer status;

	private String message;

	private Object data;
	
	private List<?> listData = Collections.emptyList();
	
	private List<McaFinancialCalculationResponse> financialCalculationData;
	
	public McaResponse(String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
	}
	public McaResponse(String message, Integer status,Object data) {
		super();
		this.message = message;
		this.status = status;
		this.data=data;
	}
	
	public McaResponse(Object data, Integer status) {
		super();
		this.data = data;
		this.status = status;
	}
	

	public McaResponse() {
		super();
	}



	public Long getId() {
		return id;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public List<?> getListData() {
		return listData;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setListData(List<?> listData) {
		this.listData = listData;
	}

	public List<McaFinancialCalculationResponse> getFinancialCalculationData() {
		return financialCalculationData;
	}

	public void setFinancialCalculationData(List<McaFinancialCalculationResponse> financialCalculationData) {
		this.financialCalculationData = financialCalculationData;
	}

	

}
