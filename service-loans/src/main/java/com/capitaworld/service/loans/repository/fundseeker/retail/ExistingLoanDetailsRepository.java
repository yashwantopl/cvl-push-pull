package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.ExistingLoanDetail;

/**
 * @author Sanket
 *
 */
public interface ExistingLoanDetailsRepository extends JpaRepository<ExistingLoanDetail, Long> {

	@Query("select o from ExistingLoanDetail o where o.applicantId.id = :id and o.isActive = true")
	public List<ExistingLoanDetail> listExistingLoanFromAppId(@Param("id") Long id);

	@Query("select o from ExistingLoanDetail o where o.coApplicantDetailId.id = :id and o.isActive = true")
	public List<ExistingLoanDetail> listExistingLoanFromCoAppId(@Param("id")Long applicationId);

	@Query("select o from ExistingLoanDetail o where o.guarantorDetailId.id = :id and o.isActive = true")
	public List<ExistingLoanDetail> listExistingLoanFromGarrId(@Param("id")Long applicationId);

}
