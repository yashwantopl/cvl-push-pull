package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OverseasNetworkMappingDetail;
import com.capitaworld.service.loans.model.FinalTermLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinalTermLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OverseasNetworkRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinalTermLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalTermLoanServiceImpl implements FinalTermLoanService {

	private static final Logger logger = LoggerFactory.getLogger(FinalTermLoanServiceImpl.class.getName());

	@Autowired
	private FinalTermLoanDetailRepository termLoanDetailRepository;

	@Autowired
	private OverseasNetworkRepository networkRepository;

	@Override
	public boolean saveOrUpdate(FinalTermLoanRequest termLoanRequest) throws Exception {
		try {
			FinalTermLoanDetail termLoanDetail = null;
			if (termLoanRequest.getId() != null && termLoanRequest.getApplicationId() != null) {
				termLoanDetail = termLoanDetailRepository.getByApplicationIDAndID(termLoanRequest.getApplicationId(),
						termLoanRequest.getId());
				if (termLoanDetail == null) {
					throw new NullPointerException("FinalTermLoanDetail not exist in DB with ID=>"
							+ termLoanRequest.getId() + " applicationId==>" + termLoanRequest.getApplicationId());
				}
				// Inactive Previous Mapping
				networkRepository.inActiveMappingByApplicationId(termLoanRequest.getApplicationId());
				termLoanDetail.setModifiedBy(termLoanRequest.getUserId());
				termLoanDetail.setModifiedDate(new Date());
			} else {
				termLoanDetail = new FinalTermLoanDetail();
				termLoanDetail.setCreatedBy(termLoanRequest.getUserId());
				termLoanDetail.setCreatedDate(new Date());
				termLoanDetail.setIsActive(true);
				termLoanDetail.setApplicationId(new LoanApplicationMaster(termLoanRequest.getApplicationId()));
			}
			BeanUtils.copyProperties(termLoanRequest, termLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			termLoanDetail = termLoanDetailRepository.save(termLoanDetail);

			// saving Data
			saveOverseasNetworkMapping(termLoanRequest.getApplicationId(), termLoanRequest.getUserId(),
					termLoanRequest.getOverseasNetworkIds());
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Final Term Loan Details:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public FinalTermLoanRequest get(Long id, Long applicationId) throws Exception {
		try {

			FinalTermLoanDetail loanDetail = termLoanDetailRepository.getByApplicationIDAndID(applicationId, id);
			if (loanDetail == null) {
				throw new NullPointerException(
						"FinalTermLoanDetail not exist in DB with ID=>" + id + " applicationId==>" + applicationId);
			}
			FinalTermLoanRequest termLoanRequest = new FinalTermLoanRequest();
			BeanUtils.copyProperties(loanDetail, termLoanRequest);
			return termLoanRequest;
		} catch (Exception e) {
			logger.error("Error while getting Final Term Loan Details:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	private void saveOverseasNetworkMapping(Long applicationId, Long userId, List<Integer> overseasNetworkIds) {
		for (Integer networkId : overseasNetworkIds) {
			OverseasNetworkMappingDetail mappingDetail = new OverseasNetworkMappingDetail();
			mappingDetail.setApplicationId(applicationId);
			mappingDetail.setOverseasNetworkId(networkId);
			mappingDetail.setActive(true);
			mappingDetail.setCreatedDate(new Date());
			mappingDetail.setCreatedBy(userId);
			networkRepository.save(mappingDetail);
		}
	}
}
