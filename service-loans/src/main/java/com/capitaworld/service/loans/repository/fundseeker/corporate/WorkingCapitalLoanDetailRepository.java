package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.WorkingCapitalLoanDetail;

public interface WorkingCapitalLoanDetailRepository extends JpaRepository<WorkingCapitalLoanDetail, Long> {

	@Modifying
	@Query("update WorkingCapitalLoanDetail wc set wc.amount =:amount,wc.name =:name,wc.modifiedBy =:modifiedBy,wc.categoryCode =:categoryCode,wc.haveExistingLimit =:haveExistingLimit,wc.projectBrief =:projectBrief,wc.collateralSecurityAmtTotal =:collateralSecurityAmtTotal,wc.creditRatingId =:creditRatingId,wc.currencyId =:currencyId,wc.denominationId =:denominationId,wc.modifiedDate = NOW() where wc.applicationId.id =:applicationId and wc.isActive = true")
	public int updatePrimaryWorkingCapital(@Param("amount") Double amount,@Param("name") String name, @Param("modifiedBy") Long modifiedBy,
			@Param("categoryCode") String categoryCode, @Param("haveExistingLimit") Long haveExistingLimit,
			@Param("projectBrief") String projectBrief,
			@Param("collateralSecurityAmtTotal") Double collateralSecurityAmtTotal,
			@Param("currencyId") Integer currencyId, @Param("denominationId") Integer denominationId,
			@Param("applicationId") Long applicationId,
			@Param("creditRatingId") Long creditRatingId);

}
