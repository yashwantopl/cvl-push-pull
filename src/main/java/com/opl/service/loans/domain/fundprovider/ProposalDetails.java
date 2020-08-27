package com.opl.service.loans.domain.fundprovider;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * The persistent class for the proposal_details database table.
 *
 */
@Entity
@Table(name="proposal_details")
@NamedQuery(name="ProposalDetails.findAll", query="SELECT p FROM ProposalDetails p")
public class ProposalDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="fp_product_id")
    private Long fpProductId;

    @Column(name="application_id")
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name="proposal_status_id")
    private ProposalStatusMaster proposalStatusId;

    @Column(name="is_proposal_auto")
    private Boolean isProposalAuto;

    @Column(name="proposal_stage")
    private Long proposalStageId;

    @Column(name="initiated_by")
    private Long initiatedBy;

    @Column(name="last_action_performed_by")
    private Long lastActionPerformedBy;

    @Column(name="created_by")
    private Long createdBy;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="is_active")
    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pending_proposal_update_date")
    private Date pendingProposalUpdateDate;

    @Column(name="user_score")
    private Double userScore;

    @Column(name="user_org_id")
    private Long userOrgId;

    @Column(name="branch_id")
    private Long branchId;

    @Column(name="existing_loan_amount")
    private Double existingLoanAmount;

    @Column(name="additional_loan_amount")
    private Double additionalLoanAmount;

    @Column(name="el_amount")
    private Double elAmount;

    @Column(name="el_tenure")
    private Double elTenure;

    @Column(name="el_roi")
    private Double elRoi;

    @Column(name="assign_by")
    private Long assignBy;

    @Column(name="assign_branch_to")
    private Long assignBranchTo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "assign_date")
    private Date assignDate;

    @Column(name="emi")
    private Double emi;

    private Integer version;
    
    @Column(name="processing_fee")
    private Double processingFee;

	@Column(name="reason")
	private String reason;

    @Column(name="nbfc_flow")
    private Integer nbfcFlow;
    
    @Column(name="min_pf")
    private Double minPf;
	
	@Column(name="max_pf")
    private Double maxPf;
	
	@Column(name="payment_type_id")
	private Long paymentTypeId;

    @Column(name="gstin")
    private String gstin;

    @Column(name = "business_type_id")
    private Integer businessTypeId;

    @Column(name = "is_offline")
    private Boolean isOffline;

    @Column(name = "addi_fields")
    private String addiFields;

    @Column(name="reopen_reason")
    private String reopenReason;

    @Column(name="is_sanctioned")
    private Boolean isSanctioned;

    @Column(name="is_Disbursed")
    private Boolean isDisbursed;

    @Column(name="consession_roi")
    private Double consessionRoi;

    @Column(name="concession_based_on_type")
    private String concessionBasedOnType;

    @Column(name="spread_roi")  // SPREAD RATE OF INTEREST
    private Double spreadRoi;

    @Column(name="mclr_roi") // MCLR RATE OF INTEREST
    private Double mclrRoi;

    @Column(name="scoring_model_based_on")
    private Integer scoringModelBasedOn;

    @Column(name = "pf_concession")
    private Double pfConcession;

    @Column(name = "pf_concession_text")
    private String pfConcessionText;

    @Column(name = "connect_flow_type_id")
    private Integer connectFlowTypeId;

    public Double getEmi() {
        return emi;
    }

    public void setEmi(Double emi) {
        this.emi = emi;
    }

    public Double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Double processingFee) {
        this.processingFee = processingFee;
    }

    public Double getElAmount() {
        return elAmount;
    }


    public void setElAmount(Double elAmount) {
        this.elAmount = elAmount;
    }


    public Double getElTenure() {
        return elTenure;
    }


    public void setElTenure(Double elTenure) {
        this.elTenure = elTenure;
    }


    public Double getElRoi() {
        return elRoi;
    }


    public void setElRoi(Double elRoi) {
        this.elRoi = elRoi;
    }


    public Long getId() {
        return id;
    }


    public Long getProposalStageId() {
        return proposalStageId;
    }

    public void setProposalStageId(Long proposalStageId) {
        this.proposalStageId = proposalStageId;
    }

    public Long getInitiatedBy() {
        return initiatedBy;
    }


    public void setInitiatedBy(Long initiatedBy) {
        this.initiatedBy = initiatedBy;
    }


    public Long getLastActionPerformedBy() {
        return lastActionPerformedBy;
    }


    public void setLastActionPerformedBy(Long lastActionPerformedBy) {
        this.lastActionPerformedBy = lastActionPerformedBy;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFpProductId() {
        return fpProductId;
    }

    public void setFpProductId(Long fpProductId) {
        this.fpProductId = fpProductId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public ProposalStatusMaster getProposalStatusId() {
        return proposalStatusId;
    }

    public void setProposalStatusId(ProposalStatusMaster proposalStatusId) {
        this.proposalStatusId = proposalStatusId;
    }

    public Boolean getIsProposalAuto() {
        return isProposalAuto;
    }

    public void setIsProposalAuto(Boolean isProposalAuto) {
        this.isProposalAuto = isProposalAuto;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    public Date getPendingProposalUpdateDate() {
        return pendingProposalUpdateDate;
    }


    public void setPendingProposalUpdateDate(Date pendingProposalUpdateDate) {
        this.pendingProposalUpdateDate = pendingProposalUpdateDate;
    }


    public Long getUserOrgId() {
        return userOrgId;
    }


    public void setUserOrgId(Long userOrgId) {
        this.userOrgId = userOrgId;
    }


    public Double getUserScore() {
        return userScore;
    }


    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }


    public Long getAssignBy() {
        return assignBy;
    }


    public void setAssignBy(Long assignBy) {
        this.assignBy = assignBy;
    }


    public Long getAssignBranchTo() {
        return assignBranchTo;
    }


    public void setAssignBranchTo(Long assignBranchTo) {
        this.assignBranchTo = assignBranchTo;
    }


    public Date getAssignDate() {
        return assignDate;
    }


    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

    public Integer getNbfcFlow() {
        return nbfcFlow;
    }

    public void setNbfcFlow(Integer nbfcFlow) {
        this.nbfcFlow = nbfcFlow;
    }

    public Double getExistingLoanAmount() {
        return existingLoanAmount;
    }

    public void setExistingLoanAmount(Double existingLoanAmount) {
        this.existingLoanAmount = existingLoanAmount;
    }

    public Double getAdditionalLoanAmount() {
        return additionalLoanAmount;
    }

    public void setAdditionalLoanAmount(Double additionalLoanAmount) {
        this.additionalLoanAmount = additionalLoanAmount;
    }

	public Double getMinPf() {
		return minPf;
	}

	public void setMinPf(Double minPf) {
		this.minPf = minPf;
	}

	public Double getMaxPf() {
		return maxPf;
	}

	public void setMaxPf(Double maxPf) {
		this.maxPf = maxPf;
	}

	public Boolean getIsOffline() {
		return isOffline;
	}

	public void setIsOffline(Boolean isOffline) {
		this.isOffline = isOffline;
	}

	public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public String getAddiFields() {
		return addiFields;
	}

	public void setAddiFields(String addiFields) {
		this.addiFields = addiFields;
	}

	public String getReopenReason() {
		return reopenReason;
	}

	public void setReopenReason(String reopenReason) {
		this.reopenReason = reopenReason;
	}

	public Boolean getIsSanctioned() {
		return isSanctioned;
	}

	public void setIsSanctioned(Boolean isSanctioned) {
		this.isSanctioned = isSanctioned;
	}

	public Boolean getIsDisbursed() {
		return isDisbursed;
	}

	public void setIsDisbursed(Boolean isDisbursed) {
		this.isDisbursed = isDisbursed;
	}

	public Double getConsessionRoi() {
		return consessionRoi;
	}

	public void setConsessionRoi(Double consessionRoi) {
		this.consessionRoi = consessionRoi;
	}

	public String getConcessionBasedOnType() {
		return concessionBasedOnType;
	}

	public void setConcessionBasedOnType(String concessionBasedOnType) {
		this.concessionBasedOnType = concessionBasedOnType;
	}

	public Double getSpreadRoi() {
		return spreadRoi;
	}

	public void setSpreadRoi(Double spreadRoi) {
		this.spreadRoi = spreadRoi;
	}

	public Double getMclrRoi() {
		return mclrRoi;
	}

	public void setMclrRoi(Double mclrRoi) {
		this.mclrRoi = mclrRoi;
	}

	public Integer getScoringModelBasedOn() {
		return scoringModelBasedOn;
	}

	public void setScoringModelBasedOn(Integer scoringModelBasedOn) {
		this.scoringModelBasedOn = scoringModelBasedOn;
	}

	public Double getPfConcession() {
		return pfConcession;
	}

	public void setPfConcession(Double pfConcession) {
		this.pfConcession = pfConcession;
	}

	public String getPfConcessionText() {
		return pfConcessionText;
	}

	public void setPfConcessionText(String pfConcessionText) {
		this.pfConcessionText = pfConcessionText;
	}

	public Integer getConnectFlowTypeId() {
		return connectFlowTypeId;
	}

	public void setConnectFlowTypeId(Integer connectFlowTypeId) {
		this.connectFlowTypeId = connectFlowTypeId;
	}
	

}