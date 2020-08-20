package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.DirectorBackgroundDetailRequest;
import com.opl.mudra.api.loans.model.FinancialArrangementsDetailRequest;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.NTBRequest;
import com.opl.mudra.api.loans.model.corporate.FundSeekerInputRequestResponse;

public interface NTBService {

    public DirectorBackgroundDetailRequest getOneformDetailByDirectorId(Long directorId)  throws LoansException;

    public Boolean saveOneformDetailForDirector(DirectorBackgroundDetailRequest directorBackgroundDetailRequest, Long userId) throws LoansException;

    public List<FinancialArrangementsDetailRequest> getFinancialDetails(Long applicationId, Long directorId) throws LoansException;

    public Boolean saveFinancialDetails(List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList, Long applicationId, Long userId, Long directorId) throws LoansException;

    public FundSeekerInputRequestResponse getOthersDetail(Long applicationId) throws LoansException;

    public Boolean saveOthersDetail(FundSeekerInputRequestResponse fundSeekerInputRequest, Long applicationId, Long userId) throws LoansException;

    /**
     * Use to call after Director Details saved.
     * @param ntbRequest
     * @return
     */
    public LoansResponse postDirectorBackground(NTBRequest ntbRequest);
    public LoansResponse postDirectorsChangeStage(NTBRequest ntbRequest);
    public LoansResponse postOthersChangeStage(NTBRequest ntbRequest);

	public LoansResponse invokeFraudAnalytics(FundSeekerInputRequestResponse fundSeekerInputRequestResponse) throws LoansException;
}
