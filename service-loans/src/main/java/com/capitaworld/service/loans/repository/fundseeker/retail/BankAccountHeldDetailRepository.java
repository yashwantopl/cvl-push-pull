package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	public List<BankAccountHeldDetail> listBankAccountHeldFromCoAppId(@Param("id") Long id);

	@Query("select o from BankAccountHeldDetail o where o.guarantorDetailId.id = :id and o.isActive = true")
	public List<BankAccountHeldDetail> listBankAccountHeldFromGarrId(@Param("id")Long id);

	@Modifying
	@Query("update BankAccountHeldDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicantId.id =:applicantId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId, @Param("applicantId") Long applicantId);
}
