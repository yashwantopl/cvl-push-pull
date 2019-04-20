package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;

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
}
