package com.opl.mudra.api.rating.model;

public class RatingResponse {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer status;

	private String message;

	private Object data;
	
	private Integer businessTypeId;
	
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public RatingResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingResponse(String message,Integer status) {
		super();
		this.status = status;
		this.message = message;
	}

	public RatingResponse(Object data, String message,Integer status) {
		// TODO Auto-generated constructor stub
		
		super();
		this.data = data;
		this.status = status;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RatingResponse{" +
				"id=" + id +
				", status=" + status +
				", message='" + message + '\'' +
				", data=" + data +
				", businessTypeId=" + businessTypeId +
				'}';
	}
}
