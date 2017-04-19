package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LiabilitiesDetailsService;

@Service
public class LiabilitiesDetailsServiceImpl implements LiabilitiesDetailsService {

	@Autowired
	LiabilitiesDetailsRepository liabilitiesDetailsRepository;
	
	@Override
	public void saveOrUpdate(LiabilitiesDetails liabilitiesDetails) {
		// TODO Auto-generated method stub
		liabilitiesDetailsRepository.save(liabilitiesDetails);
	}

}
