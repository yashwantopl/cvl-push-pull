package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.opl.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;

public interface LiabilitiesDetailsRepository  extends JpaRepository<LiabilitiesDetails	, Long>{
	
	@Modifying
	@Transactional
	@Query("update LiabilitiesDetails l set l.isActive = false where l.storageDetailsId= :sId")
	public void inActiveAssetsDetails(@Param("sId") Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update LiabilitiesDetails l set l.isActive = false where l.fsLoanApplicationMaster.id = :applicationId and l.isActive = true")
	public void inActiveAssetsDetailsByAppId(@Param("applicationId") Long applicationId);
	
	@Modifying
	@Transactional
	@Query("update LiabilitiesDetails l set l.isActive = false where l.fsLoanApplicationMaster.id = :applicationId and l.isActive = true and l.applicationProposalMapping.proposalId IS NULL")
	public void inActiveByAppId(@Param("applicationId") Long applicationId);
	
	@Query("from LiabilitiesDetails l where l.fsLoanApplicationMaster.id = :appId and l.year = :yr and l.isActive = true")
	public LiabilitiesDetails getLiabilitiesDetails(@Param("appId") Long applicationId, @Param("yr") String year);

	@Query("from LiabilitiesDetails l where l.fsLoanApplicationMaster.id = :appId and l.year = :yr and l.isActive = true and l.applicationProposalMapping.proposalId IS NULL")
	public LiabilitiesDetails getLiabilitiesDetailsByAppIdAndFinYear(@Param("appId") Long applicationId, @Param("yr") String year);
	
	@Query("from LiabilitiesDetails l where l.applicationProposalMapping.proposalId= :proposalId and l.year = :yr and l.isActive = true")
	public LiabilitiesDetails getLiabilitiesDetailByProposal(@Param("proposalId") Long proposalId, @Param("yr") String year);

	//@Query("select o from LiabilitiesDetails o where o.fsLoanApplicationMaster.id = :applicationId and o.isActive = true")
	@Query(value=" SELECT * FROM ( SELECT * FROM fs_corporate_cma_liabilities_details o WHERE o.application_id = :applicationId AND o.proposal_mapping_id IS NULL AND o.financial_yearly_statement = 'Audited'  AND o.is_active = TRUE ORDER BY o.year DESC LIMIT 3 ) AS t ORDER BY t.year " , nativeQuery = true )
	public List<LiabilitiesDetails> getByApplicationId(@Param("applicationId") Long applicationId);
	
	@Query(value="SELECT * FROM fs_corporate_cma_liabilities_details o WHERE o.application_id = :applicationId AND o.proposal_mapping_id IS NULL AND o.financial_yearly_statement = 'Audited'  AND o.is_active = TRUE ORDER BY o.year LIMIT 3" , nativeQuery = true )
	public List<LiabilitiesDetails> getLiabilitiesByApplicationId(@Param("applicationId") Long applicationId);
	
//	@Query(value="SELECT * FROM fs_corporate_cma_liabilities_details o WHERE o.application_id = :applicationId AND o.proposal_mapping_id IS NULL AND o.financial_yearly_statement = 'Audited'  AND o.is_active = TRUE ORDER BY o.year LIMIT 3" , nativeQuery = true )
	@Query("select o from LiabilitiesDetails o where o.fsLoanApplicationMaster.id = :applicationId and o.isActive = true and o.applicationProposalMapping.proposalId = NULL ORDER BY o.year")
	public List<LiabilitiesDetails> getLiabilitiesDetailsByApplicationId(@Param("applicationId") Long applicationId);
	
	@Query(value=" SELECT * FROM ( SELECT * FROM fs_corporate_cma_liabilities_details o WHERE o.application_id = :applicationId AND o.proposal_mapping_id =:proposalId AND o.financial_yearly_statement = 'Audited'  AND o.is_active = TRUE ORDER BY o.year DESC LIMIT 3 ) AS t ORDER BY t.year " , nativeQuery = true )
	public List<LiabilitiesDetails> getByApplicationIdAndProposalIdForPushAPI(@Param("applicationId") Long applicationId,@Param("proposalId") Long proposalId);

	@Query("select o from LiabilitiesDetails o where o.fsLoanApplicationMaster.id = :applicationId and o.applicationProposalMapping.proposalId = NULL and o.isActive = true")
	public List<LiabilitiesDetails> getByApplicationIdAndProposalIdNULL(@Param("applicationId") Long applicationId);

	@Query("select o from LiabilitiesDetails o where o.fsLoanApplicationMaster.id = :applicationId and o.year = :yr and o.applicationProposalMapping.proposalId = NULL and o.isActive = true")
	public LiabilitiesDetails getByApplicationIdAndYearAndProposalIdNULL(@Param("applicationId") Long applicationId,@Param("yr") String year);

	@Query("select o from LiabilitiesDetails o where o.fsLoanApplicationMaster.id =:applicationId and o.applicationProposalMapping.proposalId =:proposalId and o.isActive = true")
	public List<LiabilitiesDetails> getByApplicationIdAndProposalId(@Param("applicationId") Long applicationId,@Param("proposalId") Long proposalId);

	public LiabilitiesDetails findByIdAndIsActive(Long id, Boolean isActive);
	
	@Query("select o from LiabilitiesDetails o where o.fsLoanApplicationMaster.id = :applicationId and o.isActive = true and o.year IN :yearList and o.financialYearlyStatement =:financialYearlyStatement ORDER BY o.year ASC")
	public List<LiabilitiesDetails> getLiabilitiesDetailsByApplicationId(@Param("applicationId") Long applicationId,@Param("yearList") List<String> yearList, @Param("financialYearlyStatement") String financialYearlyStatement);
	
	@Query("select a.sundryCreditors , a.advancePaymentsFromCustomers , a.subTotalA , a.totalCurrentLiabilities,a.totalOutsideLiabilities from LiabilitiesDetails a where a.fsLoanApplicationMaster.id =:applicationId  AND year = (SELECT  max(a.year) FROM LiabilitiesDetails a WHERE a.fsLoanApplicationMaster.id =:applicationId AND a.applicationProposalMapping.proposalId = NULL AND a.isActive=true AND a.financialYearlyStatement =:financialYearlyStatement)")
	public List<Object[]> getCMADetail(@Param("applicationId") Long applicationId, @Param("financialYearlyStatement") String financialYearlyStatement);
	
	// FOR DATA API RELATED------>
	@Query("select a from LiabilitiesDetails a where a.fsLoanApplicationMaster.id =:applicationId  AND year = (SELECT  max(a.year) FROM LiabilitiesDetails a WHERE a.fsLoanApplicationMaster.id =:applicationId AND a.applicationProposalMapping.proposalId = NULL AND a.isActive=true AND a.financialYearlyStatement =:financialYearlyStatement)")
	public List<LiabilitiesDetails> getCMADetailForAPI(@Param("applicationId") Long applicationId, @Param("financialYearlyStatement") String financialYearlyStatement);
	
	// FOR DATA API RELATED------>
	@Query("select a from LiabilitiesDetails a where a.fsLoanApplicationMaster.id =:applicationId  AND year = (SELECT  max(a.year-1) FROM LiabilitiesDetails a WHERE a.fsLoanApplicationMaster.id =:applicationId AND a.applicationProposalMapping.proposalId = NULL AND a.isActive=true AND a.financialYearlyStatement =:financialYearlyStatement)")
	public List<LiabilitiesDetails> getCMADetailForAPIMinAndMaxYear(@Param("applicationId") Long applicationId, @Param("financialYearlyStatement") String financialYearlyStatement);
	

	public LiabilitiesDetails findByFsLoanApplicationMasterIdAndYearAndFinancialYearlyStatementAndIsActive(Long applicationId , String year , String financialYearlyStatement , Boolean isActive);

	@Modifying
	@Transactional
	@Query("update LiabilitiesDetails o set o.isActive = false where o.fsLoanApplicationMaster.id = :applicationId and o.financialYearlyStatement IN ('Estimated', 'Projected' ) and o.isActive = true")
	public int inActiveByAppIdAndFinancialYearlyStatementAndIsActive(@Param("applicationId") Long applicationId);
	
	@Modifying
	@Transactional
	@Query("update LiabilitiesDetails o set o.isActive = false where o.fsLoanApplicationMaster.id = :applicationId and o.financialYearlyStatement IN ('Estimated', 'Projected' ) and o.applicationProposalMapping.proposalId =:proposalId and o.isActive = true")
	public int inActiveByAppIdAndProposalIdAndFinancialYearlyStatementAndIsActive(@Param("applicationId") Long applicationId , @Param("proposalId") Long  proposalId);
	
	public List<LiabilitiesDetails> findByFsLoanApplicationMasterIdAndYearAndIsActive(Long applicationId , String year , Boolean isActive);
	
	/**
	 * get total_liability 
	 * @param applicationId
	 * @param key
	 * @return String 
	 * @author rohit.chaudhary
	 */
	@Query(value = "SELECT CAST(JSON_OBJECT('year', JSON_OBJECTAGG(year, total_liability)) AS CHAR) AS year FROM `fs_corporate_cma_liabilities_details` WHERE application_id=:applicationId and is_active = true AND proposal_mapping_id IS NULL" , nativeQuery=true)
	public String getTotalLiability(@Param("applicationId") Long applicationId);

	
	/**
	 * get creditors 
	 * @param applicationId
	 * @param key
	 * @return String 
	 * @author rohit.chaudhary
	 */
	@Query(value = "SELECT CAST(JSON_OBJECT('year', JSON_OBJECTAGG(year, creditors)) AS CHAR) AS year FROM `fs_corporate_cma_liabilities_details` WHERE application_id=:applicationId and is_active = true AND proposal_mapping_id IS NULL" , nativeQuery=true)
	public String getCreditors(@Param("applicationId") Long applicationId);
	
}
