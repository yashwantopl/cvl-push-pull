package com.capitaworld.service.loans.model.corporate;


public class SubSectorMappingRequest {
	private Long  id;

	//private Long sectorId;

	private Long subSectorId;

	
	public SubSectorMappingRequest() {
		super();
	}

	public SubSectorMappingRequest(Long id, Long subSectorId) {
		super();
		this.id = id;
		this.subSectorId = subSectorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public Long getSectorId() {
		return sectorId;
	}

	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}
*/
	public Long getSubSectorId() {
		return subSectorId;
	}

	public void setSubSectorId(Long subSectorId) {
		this.subSectorId = subSectorId;
	}
	
	
	
}
