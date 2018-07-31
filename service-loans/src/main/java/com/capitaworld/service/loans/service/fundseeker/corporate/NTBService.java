package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.NTBRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface NTBService {

    public DirectorBackgroundDetailRequest getOneformDetailByDirectorId(Long directorId)  throws Exception;

    public Boolean saveOneformDetailForDirector(DirectorBackgroundDetailRequest directorBackgroundDetailRequest, Long userId) throws Exception;

    public List<FinancialArrangementsDetailRequest> getFinancialDetails(Long applicationId, Long directorId) throws Exception;

    public Boolean saveFinancialDetails(List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList, Long applicationId, Long userId, Long directorId) throws Exception;

    public FundSeekerInputRequestResponse getOthersDetail(Long applicationId) throws Exception;

    public Boolean saveOthersDetail(FundSeekerInputRequestResponse fundSeekerInputRequest, Long applicationId, Long userId) throws Exception;

    /**
     * Use to call after Director Details saved.
     * @param ntbRequest
     * @return
     */
    public LoansResponse postDirectorBackground(NTBRequest ntbRequest);
    public LoansResponse postDirectorsChangeStage(NTBRequest ntbRequest);
    public LoansResponse postOthersChangeStage(NTBRequest ntbRequest);
}
