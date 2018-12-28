package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.ApplicationProposalMappingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplicationProposalMappingServiceImpl implements ApplicationProposalMappingService {

	@Autowired
	ApplicationProposalMappingRepository repository;
	
	Logger logger =LoggerFactory.getLogger(ApplicationProposalMappingServiceImpl.class);

	@Override
    public Boolean saveOrUpdate() {
        return null;
    }

	@Override
	public ApplicationProposalMapping getByApplicationId(Long applicationId) {
		return repository.getByApplicationId(applicationId);
	}
}
