package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.opl.service.loans.domain.fundseeker.corporate.AssetsDetails;

public interface AssetsDetailsRepository extends JpaRepository<AssetsDetails, Long> {
	
	@Modifying
	@Transactional
	@Query("update AssetsDetails a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveAssetsDetails(@Param("sId") Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update AssetsDetails a set a.isActive = false where a.loanApplicationMaster.id = :applicationId and a.isActive = true")
	public void inActiveAssetsDetailsByAppId(@Param("applicationId") Long applicationId);
	
	@Modifying
	@Transactional
	@Query("update AssetsDetails a set a.isActive = false where a.loanApplicationMaster.id = :applicationId and a.isActive = true and a.applicationProposalMapping.proposalId IS NULL")
	public void inActiveByAppId(@Param("applicationId") Long applicationId);
	
	@Query("from AssetsDetails a where a.applicationProposalMapping.proposalId = :proposalId and a.year = :yr and a.isActive = true")
	public AssetsDetails getAssetsDetails(@Param("proposalId") Long proposalId, @Param("yr") String year);
	
	@Query("select o from AssetsDetails o where o.loanApplicationMaster.id = :applicationId and o.year = :yr and o.isActive = true")
	public AssetsDetails getAssestDetailsByApplicationId(@Param("applicationId") Long applicationId,@Param("yr") String year);

	@Query("from AssetsDetails a where a.applicationProposalMapping.proposalId = :proposalId and a.year = :yr and a.isActive = true")
	public AssetsDetails getAssetsDetailByProposal(@Param("proposalId") Long proposalId, @Param("yr") String year);

	//@Query("select o from AssetsDetails o where o.loanApplicationMaster.id = :applicationId and o.isActive = true")
    @Query(value=" SELECT * FROM ( SELECT * FROM fs_corporate_cma_assets_details o WHERE o.application_id = :applicationId AND o.proposal_mapping_id IS NULL AND o.financial_yearly_statement = 'Audited'  AND o.is_active = TRUE ORDER BY o.year DESC LIMIT 3 ) AS t ORDER BY t.year " , nativeQuery = true )
	public List<AssetsDetails> getByApplicationId(@Param("applicationId") Long applicationId);
    
    @Query(value=" SELECT * FROM fs_corporate_cma_assets_details o WHERE o.application_id = :applicationId AND o.proposal_mapping_id IS NULL AND o.financial_yearly_statement = 'Audited'  AND o.is_active = TRUE ORDER BY o.year LIMIT 3 " , nativeQuery = true )
    public List<AssetsDetails> getAssetsDetailsByApplicationId(@Param("applicationId") Long applicationId);
    
    //@Query(value=" SELECT * FROM fs_corporate_cma_assets_details o WHERE o.application_id = :applicationId AND o.proposal_mapping_id IS NULL AND o.financial_yearly_statement = 'Audited'  AND o.is_active = TRUE ORDER BY o.year LIMIT 3 " , nativeQuery = true )
    @Query("select o from AssetsDetails o where o.loanApplicationMaster.id = :applicationId and o.isActive = true and o.applicationProposalMapping.proposalId = NULL order by o.year ")
    public List<AssetsDetails> getAssetsByApplicationId(@Param("applicationId") Long applicationId);
    
    @Query(value=" SELECT * FROM ( SELECT * FROM fs_corporate_cma_assets_details o WHERE o.application_id = :applicationId AND o.proposal_mapping_id = :proposalId AND o.financial_yearly_statement = 'Audited'  AND o.is_active = TRUE ORDER BY o.year DESC LIMIT 3 ) AS t ORDER BY t.year " , nativeQuery = true )
	public List<AssetsDetails> getByApplicationIdAndProposalIdForPushAPI(@Param("applicationId") Long applicationId,@Param("proposalId") Long proposalId);

	@Query("select o from AssetsDetails o where o.loanApplicationMaster.id = :applicationId and o.applicationProposalMapping.proposalId = NULL and o.isActive = true")
	public List<AssetsDetails> getByApplicationIdAndProposalIdNULL(@Param("applicationId") Long applicationId);

	@Query("select o from AssetsDetails o where o.loanApplicationMaster.id = :applicationId and o.year = :yr and o.applicationProposalMapping.proposalId = NULL and o.isActive = true")
	public AssetsDetails getByApplicationIdAndYearAndProposalIdNULL(@Param("applicationId") Long applicationId, @Param("yr") String year);

	@Query("select o from AssetsDetails o where o.loanApplicationMaster.id =:applicationId and o.applicationProposalMapping.proposalId = :proposalId and o.isActive = true")
	public List<AssetsDetails> getByApplicationIdAndProposalId(@Param("applicationId") Long applicationId,@Param("proposalId") Long proposalId);

	public AssetsDetails findByIdAndIsActive(Long id, Boolean isActive);
	
	@Query("select o from AssetsDetails o where o.loanApplicationMaster.id = :applicationId and o.isActive = true and o.year IN :yearList and o.financialYearlyStatement =:financialYearlyStatement ORDER By o.year ASC ")
	public List<AssetsDetails> getAssetsDetailsByApplicationId(@Param("applicationId") Long applicationId, @Param("yearList") List<String> yearList, @Param("financialYearlyStatement") String financialYearlyStatement);
	
	@Query("select a.receivableOtherThanDefferred, a.exportReceivables, a.inventory ,a.advanceToSupplierRawMaterials , a.grossBlock , a.totalCurrentAssets,a.tangibleNetWorth from AssetsDetails a where a.loanApplicationMaster.id =:applicationId  AND year = (SELECT  max(a.year) FROM AssetsDetails a WHERE a.loanApplicationMaster.id =:applicationId AND a.applicationProposalMapping.proposalId = NULL AND  a.isActive=true AND a.financialYearlyStatement =:financialYearlyStatement)")
	public List<Object[]> getCMADetail(@Param("applicationId") Long applicationId,@Param("financialYearlyStatement") String financialYearlyStatement);
	
	@Query("select a from AssetsDetails a where a.loanApplicationMaster.id =:applicationId  AND year = (SELECT  max(a.year) FROM AssetsDetails a WHERE a.loanApplicationMaster.id =:applicationId AND a.applicationProposalMapping.proposalId = NULL AND  a.isActive=true AND a.financialYearlyStatement =:financialYearlyStatement)")
	public List<AssetsDetails> getCMADetailAPI(@Param("applicationId") Long applicationId,@Param("financialYearlyStatement") String financialYearlyStatement);
	
	
	@Query("select a from AssetsDetails a where a.loanApplicationMaster.id =:applicationId  AND year = (SELECT  max(a.year-1) FROM AssetsDetails a WHERE a.loanApplicationMaster.id =:applicationId AND a.applicationProposalMapping.proposalId = NULL AND  a.isActive=true AND a.financialYearlyStatement =:financialYearlyStatement)")
	public List<AssetsDetails> getCMADetailAPIMinAndMaxYear(@Param("applicationId") Long applicationId,@Param("financialYearlyStatement") String financialYearlyStatement);

	public AssetsDetails findByLoanApplicationMasterIdAndYearAndFinancialYearlyStatementAndIsActive(Long applicationId , String year , String financialYearlyStatement , Boolean isActive);

	@Modifying
	@Transactional
	@Query("update AssetsDetails o set o.isActive = false where o.loanApplicationMaster.id = :applicationId and o.financialYearlyStatement IN ('Estimated', 'Projected' ) and o.isActive = true")
	public int inActiveByAppIdAndFinancialYearlyStatementAndIsActive(@Param("applicationId") Long applicationId);
	
	@Modifying
	@Transactional
	@Query("update AssetsDetails o set o.isActive = false where o.loanApplicationMaster.id = :applicationId and o.financialYearlyStatement IN ('Estimated', 'Projected' ) and o.applicationProposalMapping.proposalId =:proposalId and o.isActive = true")
	public int inActiveByAppIdAndProposalIdAndFinancialYearlyStatementAndIsActive(@Param("applicationId") Long applicationId  , @Param("proposalId") Long proposalId);
	
	public List<AssetsDetails> findByLoanApplicationMasterIdAndYearAndIsActive(Long applicationId , String year , Boolean isActive);
	
	/**
	 * get total_assets 
	 * @param applicationId
	 * @param key
	 * @return String 
	 * @author rohit.chaudhary
	 */
	@Query(value="SELECT CAST(JSON_OBJECT('year', JSON_OBJECTAGG(YEAR, total_assets)) AS CHAR) AS YEAR FROM `loan_application`.`fs_corporate_cma_assets_details` WHERE application_id=:applicationId and is_active = true AND proposal_mapping_id IS NULL", nativeQuery=true)
	public String getTotalAssets(@Param("applicationId") Long applicationId );
	
	/**
	 * get Inventory 
	 * @param applicationId
	 * @param key
	 * @return String 
	 * @author rohit.chaudhary
	 */
	@Query(value="SELECT CAST(JSON_OBJECT('year', JSON_OBJECTAGG(YEAR, inventory)) AS CHAR) AS YEAR FROM `loan_application`.`fs_corporate_cma_assets_details` WHERE application_id=:applicationId and is_active = true AND proposal_mapping_id IS NULL", nativeQuery=true)
	public String getInventory(@Param("applicationId") Long applicationId );
	
	/**
	 * get debtors 
	 * @param applicationId
	 * @param key
	 * @return String 
	 * @author rohit.chaudhary
	 */
	@Query(value="SELECT CAST(JSON_OBJECT('year', JSON_OBJECTAGG(YEAR, debtors)) AS CHAR) AS YEAR FROM `loan_application`.`fs_corporate_cma_assets_details` WHERE application_id=:applicationId and is_active = true AND proposal_mapping_id IS NULL", nativeQuery=true)
	public String getDebtors(@Param("applicationId") Long applicationId );
	
	/**
	 * get investmentInPlantMachinery 
	 * @param applicationId
	 * @param key
	 * @return String 
	 * @author rohit.chaudhary
	 */
	@Query(value="SELECT CAST(JSON_OBJECT('year', JSON_OBJECTAGG(year, investment_in_plant_machinery)) AS CHAR) AS YEAR FROM `loan_application`.`fs_corporate_cma_assets_details` WHERE application_id=:applicationId and is_active = true AND proposal_mapping_id IS NULL", nativeQuery=true)
	public String getInvestmentInPlantMachinery(@Param("applicationId") Long applicationId );

	
	
	/**
	 * get tangible_net_worth 
	 * @param applicationId
	 * @param key
	 * @return String 
	 * @author rohit.chaudhary
	 */
	@Query(value = "SELECT CAST(JSON_OBJECT('year', JSON_OBJECTAGG(year, tangible_net_worth)) AS CHAR) AS year FROM `loan_application`.`fs_corporate_cma_assets_details` WHERE application_id=:applicationId and is_active = true AND proposal_mapping_id IS NULL" , nativeQuery=true)
	public String getTangibleNetWorth(@Param("applicationId") Long applicationId);
	
	
	
}
