package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.CreditCardsDetail;

/**
 * @author Sanket
 *
 */
public interface CreditCardsDetailRepository extends JpaRepository<CreditCardsDetail, Long> {

	@Query("select o from CreditCardsDetail o where o.applicantionId.id = :id and isActive = true")
	public List<CreditCardsDetail> listCreditCardsFromAppId(@Param("id")Long id);

	@Query("select o from CreditCardsDetail o where o.coApplicantDetailId.id = :id and isActive = true")
	public List<CreditCardsDetail> listCreditCardsFromCoAppId(@Param("id")Long id);

	@Query("select o from CreditCardsDetail o where o.guarantorDetailId.id = :id and isActive = true")
	public List<CreditCardsDetail> listCreditCardsFromGarrId(@Param("id")Long id);

}
