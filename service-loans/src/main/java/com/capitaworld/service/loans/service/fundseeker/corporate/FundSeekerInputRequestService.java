package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import org.springframework.http.ResponseEntity;

public interface FundSeekerInputRequestService {

    public boolean saveOrUpdate(FundSeekerInputRequestResponse fundSeekerInputRequest) throws Exception;

    public ResponseEntity<LoansResponse> saveOrUpdateDirectorDetail(FundSeekerInputRequestResponse fundSeekerInputRequest);

    public ResponseEntity<LoansResponse> get(FundSeekerInputRequestResponse fundSeekerInputRequest);

    public ResponseEntity<LoansResponse> getDirectorDetail(FundSeekerInputRequestResponse fundSeekerInputRequest);
    
    public LoansResponse callMatchEngineClient(Long applicationId,Long userId,Integer businessTypeId);
}
