package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.BankAccountHeldDetail;

/**
 * @author Sanket
 *
 */
public interface BankAccountHeldDetailRepository extends JpaRepository<BankAccountHeldDetail, Long> {

	@Query("select o from BankAccountHeldDetail o where o.applicantId.id = :id and o.isActive = true")
	public List<BankAccountHeldDetail> listBankAccountHeldFromAppId(@Param("id")Long id);

	@Query("select o from BankAccountHeldDetail o where o.coApplicantDetailId.id = :id and o.isActive = true")
	public List<BankAccountHeldDetail> listBankAccountHeldFromCoAppId(Long id);

	@Query("select o from BankAccountHeldDetail o where o.guarantorDetailId.id = :id and o.isActive = true")
	public List<BankAccountHeldDetail> listBankAccountHeldFromGarrId(Long id);

}
