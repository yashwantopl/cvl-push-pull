package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryTermLoanRequest;

public interface PrimaryCorporateService {

    public boolean saveOrUpdate(PrimaryCorporateRequest primaryCorporateRequest, Long userId) throws Exception;

    public PrimaryCorporateRequest get(Long applicationId, Long userId) throws Exception;
}
