package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.corporate.CorporateMcqRequest;

public interface CorporateMcqService {

    public boolean saveOrUpdate(CorporateMcqRequest termLoanRequest, Long userId) throws LoansException;

    public boolean skipMcq(CorporateMcqRequest termLoanRequest, Long userId) throws LoansException;

    //public CorporateMcqRequest get(Long userId, Long applicationId) throws LoansException;

    public CorporateMcqRequest get(Long proposalId) throws Exception;
}
