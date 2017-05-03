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
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OverseasNetworkMappingDetail;
import com.capitaworld.service.loans.model.FinalWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinalWorkingCapitalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OverseasNetworkRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinalWorkingCapitalLoanService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalWorkingCapitalLoanServiceImpl implements FinalWorkingCapitalLoanService {

	private static final Logger logger = LoggerFactory.getLogger(FinalWorkingCapitalLoanServiceImpl.class.getName());

	@Autowired
	private FinalWorkingCapitalLoanDetailRepository finalWCRepository;

	@Autowired
	private OverseasNetworkRepository networkRepository;

	@Override
	public boolean saveOrUpdate(FinalWorkingCapitalLoanRequest capitalLoanRequest) throws Exception {
		try {
			FinalWorkingCapitalLoanDetail capitalLoanDetail = null;
			if (capitalLoanRequest.getId() != null && capitalLoanRequest.getApplicationId() != null) {
				capitalLoanDetail = finalWCRepository.getByApplicationIDAndID(capitalLoanRequest.getApplicationId(),
						capitalLoanRequest.getId());
				if (capitalLoanDetail == null) {

					throw new NullPointerException("FinalWorkingCapitalLoanDetail not exist in DB with ID=>"
							+ capitalLoanRequest.getId() + " applicationId==>" + capitalLoanRequest.getApplicationId());

				}
				// Inactive Previous Mapping
				networkRepository.inActiveMappingByApplicationId(capitalLoanRequest.getApplicationId());
				capitalLoanDetail.setModifiedBy(capitalLoanRequest.getUserId());
				capitalLoanDetail.setModifiedDate(new Date());
			} else {
				capitalLoanDetail = new FinalWorkingCapitalLoanDetail();
				capitalLoanDetail.setCreatedBy(capitalLoanRequest.getUserId());
				capitalLoanDetail.setCreatedDate(new Date());
				capitalLoanDetail.setIsActive(true);
				capitalLoanDetail.setApplicationId(new LoanApplicationMaster(capitalLoanRequest.getApplicationId()));
			}
			BeanUtils.copyProperties(capitalLoanRequest, capitalLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			capitalLoanDetail = finalWCRepository.save(capitalLoanDetail);

			// saving Data
			saveOverseasNetworkMapping(capitalLoanRequest.getApplicationId(), capitalLoanRequest.getUserId(),
					capitalLoanRequest.getOverseasNetworkIds());
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Final Working Capital Details:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public FinalWorkingCapitalLoanRequest get(Long id, Long applicationId) throws Exception {
		try {
			FinalWorkingCapitalLoanDetail loanDetails = finalWCRepository.getByApplicationIDAndID(applicationId, id);
			if (loanDetails == null) {
				throw new NullPointerException("FinalWorkingCapitalLoanDetail not exist in DB with ID=>" + id
						+ " applicationId==>" + applicationId);
			}
			FinalWorkingCapitalLoanRequest capitalLoanRequest = new FinalWorkingCapitalLoanRequest();
			BeanUtils.copyProperties(loanDetails, capitalLoanRequest);
			return capitalLoanRequest;
		} catch (Exception e) {
			logger.error("Error while getting Final Working Capital Details:-");
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
