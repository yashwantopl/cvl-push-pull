package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.NTBRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;

import java.util.List;

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
