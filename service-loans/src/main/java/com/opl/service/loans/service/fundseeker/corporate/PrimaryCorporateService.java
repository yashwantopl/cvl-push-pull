package com.opl.service.loans.service.fundseeker.corporate;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.PrimaryCorporateRequest;

public interface PrimaryCorporateService {

    public boolean saveOrUpdate(PrimaryCorporateRequest primaryCorporateRequest, Long userId) throws LoansException;

    public PrimaryCorporateRequest get(Long applicationId, Long userId) throws LoansException;

    /**
     * Getting PrimaryCorproateRequest by Application Id
     * @param applicationId
     * @return
     */
    public PrimaryCorporateRequest get(Long applicationId);

    public boolean saveOrUpdateSpecificData(PrimaryCorporateRequest primaryCorporateRequest, Long userId) throws LoansException;

    public boolean saveSwitchExistingLoan(PrimaryCorporateRequest primaryCorporateRequest) throws LoansException;

}
