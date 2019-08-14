package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiFinancialArrangementsDetail;
import com.capitaworld.service.loans.model.mfi.MFIFinancialArrangementRequest;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq;

/**
 * @author Sanket
 *
 */
public interface MfiFinancialArrangementDetailsRepository extends JpaRepository<MfiFinancialArrangementsDetail, Long> {
	
	@Modifying
	@Query("update MfiFinancialArrangementsDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:createdBy where pm.applicationId.id =:applicationId and pm.isActive = true and pm.applicantId =:applicantId")
	public int inActive(@Param("createdBy") Long createdBy, @Param("applicationId") Long applicationId, @Param("applicantId") Long applicantId);

	
	@Query("select new com.capitaworld.service.loans.model.mfi.MFIFinancialArrangementRequest(fn.applicationId.id,fn.financialInstitutionName,fn.otherInstitutionName,fn.amount,fn.loanDate,fn.reportedDate,fn.loanType,fn.emi,fn.createdBy,fn.createdDate,fn.modifiedBy,fn.modifiedDate,fn.outstandingAmount,fn.isManuallyAdded,fn.bureauOutstandingAmount,fn.bankerOutstandingAmount,fn.bureauOrCalculatedEmi,fn.isBureauEmi,fn.isActive,fn.provider,fn.applicantId) from MfiFinancialArrangementsDetail fn where fn.applicationId.id= :appId and fn.applicantId =:applicantId and fn.isActive=true")
    public List<MFIFinancialArrangementRequest> getFinancialDetailsByAppId(@Param("appId") Long appId,@Param("applicantId") Long applicantId);
	
}
