package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.corporate.CorporateMcqRequest;

public interface CorporateMcqService {

    public boolean saveOrUpdate(CorporateMcqRequest termLoanRequest, Long userId) throws Exception;

    public CorporateMcqRequest get(Long userId, Long applicationId) throws Exception;
}
