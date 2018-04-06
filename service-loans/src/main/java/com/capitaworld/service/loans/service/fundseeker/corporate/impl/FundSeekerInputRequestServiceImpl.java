package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FundSeekerInputRequestService;
import org.springframework.beans.factory.annotation.Autowired;

public class FundSeekerInputRequestServiceImpl implements FundSeekerInputRequestService {

    @Autowired
    private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

    @Override
    public Boolean saveOrUpdate(FundSeekerInputRequest fundSeekerInputRequest) {
        return null;
    }
}
