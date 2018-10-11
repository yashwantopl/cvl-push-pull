package com.capitaworld.service.loans.repository.sanction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;

/**
 * @author Ankit
 *
 */
public interface LoanSanctionRepository extends JpaRepository<LoanSanctionDomain, Long> {
															  
	@Query("SELECT lsd FROM LoanSanctionDomain lsd where lsd.applicationId =:applicationId AND lsd.isActive = true")
	public LoanSanctionDomain  findByAppliationId(@Param("applicationId") Long applicationId);
	
	public LoanSanctionDomain  findByBankSanctionPrimaryKeyAndIsActiveAndApplicationId(Long id , Boolean isActive,Long applicationId);
	
	@Query(value="SELECT sanction_date FROM sanction_detail WHERE application_id =:applicationId AND is_active = TRUE ORDER BY id DESC", nativeQuery = true)
	public List<Object[]> findSanctionDateByApplicationId(@Param("applicationId") Long applicationId);
	
}
