package com.capitaworld.service.loans.service.scoring;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ScoringResponseLoans;
import org.springframework.http.ResponseEntity;

public interface ScoringService {

    public ResponseEntity<LoansResponse> calculateScoring(Long applicationId);
}
