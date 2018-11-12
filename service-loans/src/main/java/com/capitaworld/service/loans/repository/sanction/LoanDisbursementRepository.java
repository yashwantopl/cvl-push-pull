package com.capitaworld.service.loans.repository.sanction;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.sanction.LoanDisbursementDomain;

/**
 * @author Ankit
 *
 */
public interface LoanDisbursementRepository extends JpaRepository<LoanDisbursementDomain, Long> {

	@Query("SELECT sum(ldd.disbursedAmount) FROM  LoanDisbursementDomain ldd WHERE ldd.applicationId =:applicationId and ldd.isActive =true")
	public Double getTotalDisbursedAmount(@Param("applicationId") Long applicationId );

	@Query("select obj from LoanDisbursementDomain obj where obj.applicationId=:applicationId and obj.isActive =true")
	public List<LoanDisbursementDomain> getDisbursedListByApplicationId(@Param("applicationId") Long applicationId);
	
	public LoanDisbursementDomain findByBankDisbursementPrimaryKeyAndApplicationIdAndIsActive(Long id , Long applicationId ,  Boolean IsActive);
	
	@Query(value="SELECT disbursement_date FROM disbursement_detail WHERE application_id =:applicationId AND is_active = TRUE ORDER BY id DESC", nativeQuery = true)
	public List<Date[]> findDisbursementDateByApplicationId(@Param("applicationId") Long applicationId);
	
	@Query("SELECT count(ld)  FROM LoanDisbursementDomain ld WHERE ld.orgId =:orgId and ld.applicationId =:applicationId  and ld.isDisbursedFrom = 2")
    public Long getApplicationIdCountByOrgId(@Param("applicationId") Long applicationId,@Param("orgId") Long orgId);
	
	@Query(value="SELECT SUM(disbursed_amount) AS disbursed_amount, MAX(DATE_FORMAT(disbursement_date, '%d/%m/%Y')) AS disbursement_date FROM loan_application.disbursement_detail WHERE application_id =:applicationId AND is_active = TRUE", nativeQuery = true)
	public List<Object[]> getDisbursmentData(@Param("applicationId") Long applicationId );
}
