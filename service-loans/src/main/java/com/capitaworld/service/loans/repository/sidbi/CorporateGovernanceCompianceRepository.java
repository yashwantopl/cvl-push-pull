/**
 * 
 */
package com.capitaworld.service.loans.repository.sidbi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.sidbi.CorporateGovernanceCompliance;


/**
 * @author mohammad.maaz
 *
 */
public interface CorporateGovernanceCompianceRepository extends JpaRepository<CorporateGovernanceCompliance,Long> {

	@Query(value="SELECT * FROM `loan_application`.`fs_sidbi_corporate_governance_compliance` WHERE application_id =:applicationId ORDER BY created_date AND id DESC LIMIT 5",nativeQuery=true)
	List<CorporateGovernanceCompliance>findFirst5ByApplicationIdAndOrderByCreatedDateDesc(@Param("applicationId") Long applicationId);
	CorporateGovernanceCompliance findByApplicationIdAndCorporateGovernanceId(Long applicationId,Integer corporateGovernanceId);
}
