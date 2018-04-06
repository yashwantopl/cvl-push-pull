package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import org.springframework.http.ResponseEntity;

public interface FundSeekerInputRequestService {

    public ResponseEntity<LoansResponse> saveOrUpdate(FundSeekerInputRequestResponse fundSeekerInputRequest);

    public ResponseEntity<LoansResponse> get(FundSeekerInputRequestResponse fundSeekerInputRequest);
}
