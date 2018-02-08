package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CreditRatingOrganizationDetail;

/**
 * @author Sanket
 *
 */
public interface CreditRatingOrganizationDetailsRepository extends JpaRepository<CreditRatingOrganizationDetail, Long> {

	@Query("select o from CreditRatingOrganizationDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and o.isActive = true")
	public List<CreditRatingOrganizationDetail> listCreditRatingOrganizationDetailsFromAppId(@Param("id")Long id, @Param("userId") Long userId);
	

	@Query("select o.creditRatingOptionId from CreditRatingOrganizationDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and o.isActive = true and o.creditRatingTermId!=2")
	public List<Integer> listLongCreditRatingOptionDetailsFromAppId(@Param("id")Long id, @Param("userId") Long userId);

	@Query("select o.creditRatingOptionId from CreditRatingOrganizationDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and o.isActive = true and o.creditRatingTermId=2")
	public List<Integer> listShortCreditRatingOptionDetailsFromAppId(@Param("id")Long id, @Param("userId") Long userId);
	
	@Modifying
	@Query("update CreditRatingOrganizationDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);

}
