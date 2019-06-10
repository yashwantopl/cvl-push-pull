package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.BankingRelation;

/**
 * @author jitesh
 *
 */
public interface BankingRelationlRepository extends JpaRepository<BankingRelation, Long> {

	@Query("select o from BankingRelation o where o.applicationId = :id and o.isActive = true and o.coApplicantId IS NULL")
	public List<BankingRelation> listBankRelationAppId(@Param("id")Long id);

    @Query("select o from BankingRelation o where o.applicationId = :id and o.isActive = true and o.coApplicantId =:coAppId")
    public List<BankingRelation> listBankRelationAppId(@Param("id")Long id,@Param("coAppId") Long coApplicantId);
	@Modifying
	@Query("update BankingRelation pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);
	
	@Query("select (((YEAR(NOW()) - MIN(o.sinceYear)) * 12) + o.sinceMonth)  from BankingRelation o where o.applicationId = :id and o.bank =:bankName and o.isActive = true and o.coApplicantId IS NULL")
	public Integer getMinRelationshipInMonthByApplicationAndOrgName(@Param("id")Long id,@Param("bankName")String bankName);
	
	@Query("select (((YEAR(NOW()) - MIN(o.sinceYear)) * 12) + o.sinceMonth)  from BankingRelation o where o.applicationId = :id and o.bank =:bankName and o.isActive = true and o.coApplicantId =:coAppId")
	public Integer getMinRelationshipInMonthByApplicationAndOrgNameAndCoApplicantId(@Param("id")Long id,@Param("bankName")String bankName,@Param("coAppId")Long coAppId);

}
