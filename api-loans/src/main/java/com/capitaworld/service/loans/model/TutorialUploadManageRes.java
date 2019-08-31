package com.capitaworld.service.loans.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jaimin.darji
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TutorialUploadManageRes implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nameTutorial;

    private String title;

    private String urlTutorial;

    private String description;

    private Integer type;

    private Boolean isActive;

    private Long createdBy;

    private Date createdDate;
    
    private Long viewCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTutorial() {
        return nameTutorial;
    }

    public void setNameTutorial(String nameTutorial) {
        this.nameTutorial = nameTutorial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlTutorial() {
        return urlTutorial;
    }

    public void setUrlTutorial(String urlTutorial) {
        this.urlTutorial = urlTutorial;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
    
    
}
