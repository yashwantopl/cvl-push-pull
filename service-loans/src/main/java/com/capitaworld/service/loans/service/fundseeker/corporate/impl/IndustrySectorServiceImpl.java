package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.controller.fundseeker.corporate.MonthlyTurnoverDetailController;
import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.IndustrySectorService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class IndustrySectorServiceImpl implements IndustrySectorService {

	private static final Logger logger = LoggerFactory.getLogger(IndustrySectorServiceImpl.class);

	@Autowired
	private IndustrySectorRepository industrySectorRepository;

	@Override
	public void saveOrUpdate(IndustrySectorDetail industrySectorDetail) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Long> getIndustryByApplicantId(Long applicantId) throws Exception {
		// TODO Auto-generated method stub
		try {
			return industrySectorRepository.getIndustryByApplicationId(applicantId);
		} catch (Exception e) {
			logger.error("Error while getIndustryByApplicantId");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);

		}

	}

	@Override
	public List<Long> getSectorByApplicantId(Long applicantId) throws Exception {
		// TODO Auto-generated method stub
		try {
			return industrySectorRepository.getSectorByApplicationId(applicantId);
		} catch (Exception e) {
			logger.error("Error while getIndustryByApplicantId");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);

		}

	}

}
