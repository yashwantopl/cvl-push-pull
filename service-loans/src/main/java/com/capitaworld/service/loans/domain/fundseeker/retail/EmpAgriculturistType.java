package com.capitaworld.service.loans.domain.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fs_emp_agriculturist_type")
public class EmpAgriculturistType implements Serializable {
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

    @Column(name="total_land_owned_and_in_possesion")
    private Integer totalLandOwnedAndInPossesion;

    @Column(name="presently_irrigated")
    private Integer presentlyIrrigated;

    @Column(name="seasonal_irrigated")
    private Integer seasonalIrrigated;

    @Column(name="rain_fed")
    private Integer rainFed;

    @Column(name="un_attended")
    private Integer unAttended;

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

    public Integer getTotalLandOwnedAndInPossesion() {
        return totalLandOwnedAndInPossesion;
    }

    public void setTotalLandOwnedAndInPossesion(Integer totalLandOwnedAndInPossesion) {
        this.totalLandOwnedAndInPossesion = totalLandOwnedAndInPossesion;
    }

    public Integer getPresentlyIrrigated() {
        return presentlyIrrigated;
    }

    public void setPresentlyIrrigated(Integer presentlyIrrigated) {
        this.presentlyIrrigated = presentlyIrrigated;
    }

    public Integer getSeasonalIrrigated() {
        return seasonalIrrigated;
    }

    public void setSeasonalIrrigated(Integer seasonalIrrigated) {
        this.seasonalIrrigated = seasonalIrrigated;
    }

    public Integer getRainFed() {
        return rainFed;
    }

    public void setRainFed(Integer rainFed) {
        this.rainFed = rainFed;
    }

    public Integer getUnAttended() {
        return unAttended;
    }

    public void setUnAttended(Integer unAttended) {
        this.unAttended = unAttended;
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
