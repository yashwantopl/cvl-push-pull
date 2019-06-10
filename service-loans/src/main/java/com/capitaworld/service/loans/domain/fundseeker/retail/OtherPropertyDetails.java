package com.capitaworld.service.loans.domain.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fs_other_property_details")
public class OtherPropertyDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private LoanApplicationMaster applicationId;

    @OneToOne
    @JoinColumn(name = "proposal_mapping_id")
    private ApplicationProposalMapping proposalId;

    @Column(name="type_of_repair_renovation")
    private Integer typeOfRepairRenovation;

    @Column(name="total_cost_of_land")
    private Integer totalCostOfLand;

    @Column(name="total_cost_of_construction")
    private Integer totalCostOfConstruction;

    @Column(name="total_cost_of_renovation")
    private Integer totalCostOfRenovation;

    @Column(name="time_for_completion_construction")
    private Integer timeForCompletionConstruction;

    @Column(name="time_for_completion_renovation")
    private Integer timeForCompletionRenovation;

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

    public Integer getTotalCostOfLand() {
        return totalCostOfLand;
    }

    public void setTotalCostOfLand(Integer totalCostOfLand) {
        this.totalCostOfLand = totalCostOfLand;
    }

    public Integer getTotalCostOfConstruction() {
        return totalCostOfConstruction;
    }

    public void setTotalCostOfConstruction(Integer totalCostOfConstruction) {
        this.totalCostOfConstruction = totalCostOfConstruction;
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

    public Integer getTypeOfRepairRenovation() {
        return typeOfRepairRenovation;
    }

    public void setTypeOfRepairRenovation(Integer typeOfRepairRenovation) {
        this.typeOfRepairRenovation = typeOfRepairRenovation;
    }

    public Integer getTotalCostOfRenovation() {
        return totalCostOfRenovation;
    }

    public void setTotalCostOfRenovation(Integer totalCostOfRenovation) {
        this.totalCostOfRenovation = totalCostOfRenovation;
    }

    public Integer getTimeForCompletionConstruction() {
        return timeForCompletionConstruction;
    }

    public void setTimeForCompletionConstruction(Integer timeForCompletionConstruction) {
        this.timeForCompletionConstruction = timeForCompletionConstruction;
    }

    public Integer getTimeForCompletionRenovation() {
        return timeForCompletionRenovation;
    }

    public void setTimeForCompletionRenovation(Integer timeForCompletionRenovation) {
        this.timeForCompletionRenovation = timeForCompletionRenovation;
    }
}
