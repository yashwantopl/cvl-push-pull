package com.opl.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.retail.BankingRelation;

/**
 * @author jitesh
 *
 */
public interface BankingRelationlRepository extends JpaRepository<BankingRelation, Long> {

	@Query("select o from BankingRelation o where o.applicationId = :id and o.isActive = true and o.coApplicantId IS NULL")
	public List<BankingRelation> listBankRelationAppId(@Param("id")Long id);

    @Query("select o from BankingRelation o where o.applicationId = :id and o.isActive = true and o.coApplicantId =:coAppId")
    public List<BankingRelation> listBankRelationAppId(@Param("id")Long id,@Param("coAppId") Long coApplicantId);
    
    public BankingRelation findFirstByApplicationIdAndIsActiveTrueAndAccountNoOrderByIdDesc(Long applicationId, String accountNumber);
    
	@Modifying
	@Query("update BankingRelation pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);
	
	@Query(value = "SELECT TIMESTAMPDIFF(MONTH,STR_TO_DATE(CONCAT('01,',o.since_month,',',o.since_year),'%d,%m,%Y'),NOW()) AS res FROM fs_pl_banking_relation o WHERE o.application_id =:id  AND o.bank =:bankName AND o.is_active = TRUE AND o.co_applicant_id IS NULL ORDER BY res DESC limit 1",nativeQuery = true)
	public Integer getMinRelationshipInMonthByApplicationAndOrgName(@Param("id")Long id,@Param("bankName")String bankName);
	
	@Query(value = "SELECT TIMESTAMPDIFF(MONTH,STR_TO_DATE(CONCAT('01,',o.since_month,',',o.since_year),'%d,%m,%Y'),NOW()) AS res FROM fs_pl_banking_relation o WHERE o.application_id =:id  AND o.bank !=:bankName AND o.is_active = TRUE AND o.co_applicant_id IS NULL ORDER BY res DESC limit 1",nativeQuery = true)
	public Integer getMinRelationshipInMonthByApplicationAndNotOrgName(@Param("id")Long id,@Param("bankName")String bankName);
	
	@Query(value = "SELECT TIMESTAMPDIFF(MONTH,STR_TO_DATE(CONCAT('01,',o.since_month,',',o.since_year),'%d,%m,%Y'),NOW()) AS res FROM fs_pl_banking_relation o WHERE o.application_id =:id  AND o.bank =:bankName AND o.is_active = TRUE AND o.co_applicant_id =:coAppId ORDER BY res DESC limit 1",nativeQuery = true)
	public Integer getMinRelationshipInMonthByApplicationAndOrgNameAndCoApplicantId(@Param("id")Long id,@Param("bankName")String bankName,@Param("coAppId")Long coAppId);

}
