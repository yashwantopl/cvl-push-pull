package com.capitaworld.service.loans.domain.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fs_purchase_property_details")
public class PurchasePropertyDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "application_id")
    private LoanApplicationMaster applicationId;

    @OneToOne
    @JoinColumn(name = "proposal_mapping_id")
    private ApplicationProposalMapping proposalId;

    @Column(name="city")
    private Integer city;

    @Column(name="state")
    private Integer state;

    @Column(name="built_up_area")
    private Integer buildUpArea;

    @Column(name="super_built_up_area")
    private Integer superBuildUpArea;

    @Column(name="carpet_area")
    private Integer carpetArea;

    @Column(name="total_price_of_property")
    private Integer totalPriceOfProperty;

    @Column(name="property_name")
    private String propertyName;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="is_active")
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanApplicationMaster getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(LoanApplicationMaster applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getBuildUpArea() {
        return buildUpArea;
    }

    public void setBuildUpArea(Integer buildUpArea) {
        this.buildUpArea = buildUpArea;
    }

    public Integer getSuperBuildUpArea() {
        return superBuildUpArea;
    }

    public void setSuperBuildUpArea(Integer superBuildUpArea) {
        this.superBuildUpArea = superBuildUpArea;
    }

    public Integer getCarpetArea() {
        return carpetArea;
    }

    public void setCarpetArea(Integer carpetArea) {
        this.carpetArea = carpetArea;
    }

    public Integer getTotalPriceOfProperty() {
        return totalPriceOfProperty;
    }

    public void setTotalPriceOfProperty(Integer totalPriceOfProperty) {
        this.totalPriceOfProperty = totalPriceOfProperty;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public ApplicationProposalMapping getProposalId() {
        return proposalId;
    }

    public void setProposalId(ApplicationProposalMapping proposalId) {
        this.proposalId = proposalId;
    }
}
