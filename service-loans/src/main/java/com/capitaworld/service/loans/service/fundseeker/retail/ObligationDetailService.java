package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.ObligationDetailRequest;

import java.util.List;

/**
 * Created by ravina.panchal on 04-10-2018.
 */
public interface ObligationDetailService {


    public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

    public List<ObligationDetailRequest> getObligationDetailList(Long id, int applicationType) throws LoansException;
    
    public List<ObligationDetailRequest> getObligationDetailsFromProposalId(Long proposalId, int applicationType) throws LoansException;

}
