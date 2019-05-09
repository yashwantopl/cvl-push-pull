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

	@Query("select o from BankingRelation o where o.applicationId = :id and o.isActive = true")
	public List<BankingRelation> listBankRelationAppId(@Param("id")Long id);

	@Modifying
	@Query("update BankingRelation pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);
	
	@Query("select (YEAR(NOW()) - MIN(o.sinceYear)) * 12  from BankingRelation o where o.applicationId = :id and o.bank =:bankName and o.isActive = true")
	public Integer getMinRelationshipInMonthByApplicationAndOrgName(@Param("id")Long id,@Param("bankName")String bankName);

}
