package com.opl.mudra.api.oneform.model;

public class PsuResponse {
    private Long id;
    private String name;
    private String typeOfEntity;
    private String standaloneCurrentYear;
    private String standalonePreviousYear;
    private String consolidatedCurrentYear;
    private String consolidatedPreviousYear;
    private Boolean isActive;

    public PsuResponse() {
    }

    public PsuResponse(Long id,String name) {
        this.name = name;
        this.id = id;
    }

    public PsuResponse(Long id, String name, String typeOfEntity, String standaloneCurrentYear, String standalonePreviousYear, String consolidatedCurrentYear, String consolidatedPreviousYear, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.typeOfEntity = typeOfEntity;
        this.standaloneCurrentYear = standaloneCurrentYear;
        this.standalonePreviousYear = standalonePreviousYear;
        this.consolidatedCurrentYear = consolidatedCurrentYear;
        this.consolidatedPreviousYear = consolidatedPreviousYear;
        this.isActive = isActive;
    }


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

    public String getTypeOfEntity() {
        return typeOfEntity;
    }

    public void setTypeOfEntity(String typeOfEntity) {
        this.typeOfEntity = typeOfEntity;
    }

    public String getStandaloneCurrentYear() {
        return standaloneCurrentYear;
    }

    public void setStandaloneCurrentYear(String standaloneCurrentYear) {
        this.standaloneCurrentYear = standaloneCurrentYear;
    }

    public String getStandalonePreviousYear() {
        return standalonePreviousYear;
    }

    public void setStandalonePreviousYear(String standalonePreviousYear) {
        this.standalonePreviousYear = standalonePreviousYear;
    }

    public String getConsolidatedCurrentYear() {
        return consolidatedCurrentYear;
    }

    public void setConsolidatedCurrentYear(String consolidatedCurrentYear) {
        this.consolidatedCurrentYear = consolidatedCurrentYear;
    }

    public String getConsolidatedPreviousYear() {
        return consolidatedPreviousYear;
    }

    public void setConsolidatedPreviousYear(String consolidatedPreviousYear) {
        this.consolidatedPreviousYear = consolidatedPreviousYear;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
