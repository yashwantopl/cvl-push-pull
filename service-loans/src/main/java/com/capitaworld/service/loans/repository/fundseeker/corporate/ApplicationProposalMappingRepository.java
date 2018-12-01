package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationProposalMappingRepository extends JpaRepository<ApplicationProposalMapping, Long> {

    @Query("select count(proposalId) from ApplicationProposalMapping apm where apm.proposalId =:proposalId and apm.isFinalLocked=1 and apm.isActive = true")
    Long checkFinalDetailIsLocked(@Param("proposalId") Long proposalId);

    @Query("select count(proposalId) from ApplicationProposalMapping apm where apm.proposalId =:proposalId and apm.isPrimaryLocked=1 and apm.isActive = true")
    Long checkPrimaryDetailIsLocked(@Param("proposalId") Long proposalId);
    
    @Query("from ApplicationProposalMapping lm where lm.applicationId =:applicationId and lm.orgId =:orgId and lm.isActive = true")
	public ApplicationProposalMapping getByApplicationIdAndOrgId(@Param("applicationId") Long applicationId, @Param("orgId") Long orgId);

    @Modifying
    @Query("update ApplicationProposalMapping apm set apm.isFinalUploadFilled =:isFinalUploadFilled,apm.modifiedDate = NOW() where apm.proposalId=:proposalId AND apm.applicationId =:applicationId AND apm.isActive = true")
    public int setIsFinalUploadMandatoryFilled(@Param("proposalId") Long proposalId,
                                               @Param("applicationId") Long applicationId,
                                               @Param("isFinalUploadFilled") Boolean isFinalUploadFilled);

    @Modifying
    @Query("update ApplicationProposalMapping apm set apm.finalFilledCount =:finalFilledCount,apm.modifiedDate = NOW() where apm.proposalId=:proposalId AND apm.applicationId =:applicationId and apm.isActive = true")
    public int setFinalFilledCount(@Param("proposalId") Long proposalId,
                                   @Param("applicationId") Long applicationId,
                                   @Param("finalFilledCount") String finalFilledCount);

    @Modifying
    @Query("update ApplicationProposalMapping apm set apm.isFinalDprUploadFilled =:isFinalDprUploadFilled,apm.modifiedDate = NOW() where apm.proposalId=:proposalId and apm.id =:id and apm.isActive = true")
    public int setIsFinalDprMandatoryFilled(@Param("proposalId") Long proposalId,
                                            @Param("id") Long id,
                                            @Param("isFinalDprUploadFilled") Boolean isFinalDprUploadFilled);


    @Query("select tenure from ApplicationProposalMapping where proposalId =:proposalId")
    public Double getTenure(@Param("proposalId") Long proposalId);

    @Query("from ApplicationProposalMapping apm where apm.proposalId=:proposalId and apm.applicationId=:applicationId and apm.isActive = true order by apm.proposalId")
    public ApplicationProposalMapping getByProposalIdAndApplicationId(@Param("proposalId") Long proposalId, @Param("applicationId") Long applicationId);
}
