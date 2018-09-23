package com.capitaworld.service.loans.service.common.impl;

import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;
import com.capitaworld.service.loans.model.InEligibleProposalDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.IneligibleProposalDetailsRepository;
import com.capitaworld.service.loans.service.common.IneligibleProposalDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by KushalCW on 22-09-2018.
 */

@Service
@Transactional
public class IneligibleProposalDetailsServiceImpl implements IneligibleProposalDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(IneligibleProposalDetailsServiceImpl.class);

    @Autowired
    private IneligibleProposalDetailsRepository ineligibleProposalDetailsRepository;

    @Override
    public Boolean save(InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest) {
        try {
            IneligibleProposalDetails ineligibleProposalDetails = new IneligibleProposalDetails();
            BeanUtils.copyProperties(inEligibleProposalDetailsRequest,ineligibleProposalDetails);
            ineligibleProposalDetailsRepository.save(ineligibleProposalDetails);
            return true;
        }catch (Exception e){
            logger.error("error while saving in eligible proposal");
            e.printStackTrace();
        }
        return false;
    }
}
