package com.opl.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.corporate.CorporateMcqDetail;

public interface CorporateMcqDetailRepository extends JpaRepository<CorporateMcqDetail, Long> {

    @Query("from CorporateMcqDetail pd where pd.applicationId.id =:applicationId and pd.applicationId.userId =:userId")
    public CorporateMcqDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId, @Param("userId") Long id);
    
    @Query("from CorporateMcqDetail pd where pd.applicationId.id =:applicationId")
    public CorporateMcqDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId);

    /*multiple bank*/
    @Query("from CorporateMcqDetail pd where pd.applicationProposalMapping.proposalId =:proposalId")
    public CorporateMcqDetail getByProposalId(@Param("proposalId") Long proposalId);

    /*multiple bank*/
    @Query("from CorporateMcqDetail pd where pd.applicationProposalMapping.proposalId =:proposalId")
    public CorporateMcqDetail getByProposalIdAndUserId(@Param("proposalId") Long proposalId);
}
