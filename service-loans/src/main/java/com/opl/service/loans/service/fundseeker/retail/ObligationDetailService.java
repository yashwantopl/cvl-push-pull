package com.opl.service.loans.service.fundseeker.retail;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.ObligationDetailRequest;

/**
 * Created by ravina.panchal on 04-10-2018.
 */
public interface ObligationDetailService {


    public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

    public List<ObligationDetailRequest> getObligationDetailList(Long id, int applicationType) throws LoansException;
    
    public List<ObligationDetailRequest> getObligationDetailsFromProposalId(Long proposalId, int applicationType) throws LoansException;

}
