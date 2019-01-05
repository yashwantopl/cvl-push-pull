package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;

public interface CorporateFinalInfoService {

    public boolean saveOrUpdate(CorporateFinalInfoRequest corporateFinalInfoRequest, Long userId) throws LoansException;

    public CorporateFinalInfoRequest get(Long userId, Long applicationId) throws LoansException;

    public CorporateFinalInfoRequest getNTBDetails(Long userId, Long applicationId) throws LoansException;
    public boolean saveOrUpdateNTBDetails(CorporateFinalInfoRequest corporateFinalInfoRequest, Long userId) throws LoansException;
}
