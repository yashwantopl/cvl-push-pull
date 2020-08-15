package com.opl.service.loans.service.fundseeker.corporate;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.CorporateMcqRequest;

public interface CorporateMcqService {

    public boolean saveOrUpdate(CorporateMcqRequest termLoanRequest, Long userId) throws LoansException;

    public boolean skipMcq(CorporateMcqRequest termLoanRequest, Long userId) throws LoansException;

    //public CorporateMcqRequest get(Long userId, Long applicationId) throws LoansException;

    public CorporateMcqRequest get(Long proposalId) throws Exception;
}
