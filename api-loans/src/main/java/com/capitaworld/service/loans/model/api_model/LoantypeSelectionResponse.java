package com.capitaworld.service.loans.model.api_model;

public class LoantypeSelectionResponse {

    private Long id;
    private String type;
    private String description;
    private  int businessTypeId;
    private String imgPath;
    private Boolean isActive;
    
    public LoantypeSelectionResponse(){

    }

    public LoantypeSelectionResponse(Long id, String type, String description, int businessTypeId, String imgPath, Boolean isActive) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.businessTypeId = businessTypeId;
        this.imgPath = imgPath;
        this.isActive = isActive;
    }

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the businessTypeId
	 */
	public int getBusinessTypeId() {
		return businessTypeId;
	}

	/**
	 * @param businessTypeId the businessTypeId to set
	 */
	public void setBusinessTypeId(int businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	/**
	 * @return the imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath the imgPath to set
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoantypeSelectionResponse [id=" + id + ", type=" + type + ", description=" + description
				+ ", businessTypeId=" + businessTypeId + ", imgPath=" + imgPath + ", isActive=" + isActive + "]";
	}
    
}
