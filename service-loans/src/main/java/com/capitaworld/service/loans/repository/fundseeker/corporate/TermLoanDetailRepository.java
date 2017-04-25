package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.TermLoanDetail;

public interface TermLoanDetailRepository extends JpaRepository<TermLoanDetail, Long> {

	@Modifying
	@Query("update TermLoanDetail tl set tl.name =:name,tl.modifiedBy =:modifiedBy,tl.categoryCode =:categoryCode,tl.currencyId =:currencyId,tl.denominationId =:denominationId,tl.amount =:amount,tl.tenure =:tenure,tl.projectBrief =:projectBrief,tl.totalCostOfEstimate =:totalCostOfEstimate,tl.totalMeansOfFinance =:totalMeansOfFinance,tl.creditRatingId =:creditRatingId,tl.modifiedDate = NOW() where tl.applicationId.id =:applicationId and tl.isActive = true")
	public int updateTermLoan(@Param("name") String name, @Param("modifiedBy") Long modifiedBy,
			@Param("categoryCode") String categoryCode, @Param("currencyId") Integer currencyId,
			@Param("denominationId") Integer denominationId, @Param("amount") Double amount, @Param("tenure") int tenure,
			@Param("projectBrief") String projectBrief, @Param("totalCostOfEstimate") Double totalCostOfEstimate,
			@Param("totalMeansOfFinance") Double totalMeansOfFinance,
			@Param("applicationId") Long applicationId,
			@Param("creditRatingId") Long creditRatingId);

}
