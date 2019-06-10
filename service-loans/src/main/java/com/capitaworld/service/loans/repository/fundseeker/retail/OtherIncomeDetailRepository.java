package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.OtherIncomeDetail;

/**
 * @author Sanket
 *
 */
public interface OtherIncomeDetailRepository extends JpaRepository<OtherIncomeDetail, Long> {

	@Query("select o from OtherIncomeDetail o where o.applicationId.id = :id and o.isActive = true")
	public List<OtherIncomeDetail> listOtherIncomeFromAppId(@Param("id")Long id);

	@Query("select o from OtherIncomeDetail o where o.applicationId.id = :id and o.proposalId.proposalId=:proposalId and o.coApplicantDetailId.id = null and o.isActive = true")
	public List<OtherIncomeDetail> listOtherIncomeFromAppId(@Param("id")Long id,@Param("proposalId")Long proposalId);

	@Query("select o from OtherIncomeDetail o where o.coApplicantDetailId.id = :id and o.isActive = true")
	public List<OtherIncomeDetail> listOtherIncomeFromCoAppId(@Param("id")Long id);

	@Query("select o from OtherIncomeDetail o where o.proposalId.proposalId=:proposalId and o.coApplicantDetailId.id =:coAppId and o.isActive = true")
	public List<OtherIncomeDetail> listOtherIncomeFromProposalIdAndCoAppId(@Param("proposalId")Long propsoalId,@Param("coAppId")Long coAppId);

	@Query("select o from OtherIncomeDetail o where o.guarantorDetailId.id = :id and o.isActive = true")
	public List<OtherIncomeDetail> listOtherIncomeFromGarrId(@Param("id")Long id);

}
