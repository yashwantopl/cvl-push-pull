package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;

public interface CorporateFinalInfoService {

    public boolean saveOrUpdate(CorporateFinalInfoRequest corporateFinalInfoRequest, Long userId) throws Exception;

    public CorporateFinalInfoRequest get(Long userId, Long applicationId) throws Exception;

    public CorporateFinalInfoRequest getNTBDetails(Long userId, Long applicationId) throws Exception;
    public boolean saveOrUpdateNTBDetails(CorporateFinalInfoRequest corporateFinalInfoRequest, Long userId) throws Exception;
}
