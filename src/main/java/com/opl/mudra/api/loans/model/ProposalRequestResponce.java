package com.opl.mudra.api.loans.model;

public class ProposalRequestResponce {

    private Long applicationId;

    private Long proposalMappingId;

    private Long fpProductId;

    private Long proposalStatusId;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getProposalMappingId() {
        return proposalMappingId;
    }

    public void setProposalMappingId(Long proposalMappingId) {
        this.proposalMappingId = proposalMappingId;
    }

    public Long getFpProductId() {
        return fpProductId;
    }

    public void setFpProductId(Long fpProductId) {
        this.fpProductId = fpProductId;
    }

    public Long getProposalStatusId() {
        return proposalStatusId;
    }

    public void setProposalStatusId(Long proposalStatusId) {
        this.proposalStatusId = proposalStatusId;
    }
}
