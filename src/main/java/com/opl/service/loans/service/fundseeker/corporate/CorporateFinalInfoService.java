package com.opl.service.loans.service.fundseeker.corporate;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.CorporateFinalInfoRequest;

public interface CorporateFinalInfoService {

    public boolean saveOrUpdate(CorporateFinalInfoRequest corporateFinalInfoRequest, Long userId) throws LoansException;

    public CorporateFinalInfoRequest get(Long userId, Long applicationId) throws LoansException;

    public CorporateFinalInfoRequest getByProposalId(Long userId, Long proposalId) throws Exception;

    public CorporateFinalInfoRequest getNTBDetails(Long userId, Long applicationId) throws LoansException;

    public boolean saveOrUpdateNTBDetails(CorporateFinalInfoRequest corporateFinalInfoRequest, Long userId) throws LoansException;
}
