package com.capitaworld.service.loans.model;

public class CoApplicantRequest {

	private Long id;

	private String name;

	private Integer relationShipWith;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRelationShipWith() {
		return relationShipWith;
	}

	public void setRelationShipWith(Integer relationShipWith) {
		this.relationShipWith = relationShipWith;
	}

}
