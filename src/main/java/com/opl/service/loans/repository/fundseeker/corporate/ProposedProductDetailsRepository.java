package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.corporate.ProposedProductDetail;

/**
 * @author Sanket
 *
 */
public interface ProposedProductDetailsRepository extends JpaRepository<ProposedProductDetail, Long> {
	
	@Query("from ProposedProductDetail  a where a.applicationId.id=:id AND  a.applicationId.userId =:userId and a.isActive=true")
	public List<ProposedProductDetail> listProposedProductFromAppId(@Param("id") Long id, @Param("userId") Long userId);
	
    @Modifying 
	@Query("update ProposedProductDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);

    public List<ProposedProductDetail> findByApplicationIdIdAndIsActive(Long applicationId, Boolean isActive);
    
    public ProposedProductDetail findByIdAndIsActive(Long id, Boolean isActive);

	@Query("select o from ProposedProductDetail o where o.proposalId.proposalId =:proposalId and o.isActive = true")
	public List<ProposedProductDetail> findByProposalId(@Param("proposalId") Long proposalId);
}
