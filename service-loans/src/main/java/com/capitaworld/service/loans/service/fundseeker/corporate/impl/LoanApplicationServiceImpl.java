package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLasLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class LoanApplicationServiceImpl implements LoanApplicationService {

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public boolean saveOrUpdate(FrameRequest commonRequest) {
		try {
			LoanApplicationMaster applicationMaster = null;
			for (Map<String, Object> obj : commonRequest.getDataList()) {
				LoanApplicationRequest loanApplicationRequest = (LoanApplicationRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, LoanApplicationRequest.class);
				LoanType type = CommonUtils.LoanType.getType(loanApplicationRequest.getProductId());
				if (type == null) {
					continue;
				}

				switch (type) {
				case WORKING_CAPITAL:
					applicationMaster = new PrimaryWorkingCapitalLoanDetail();
					break;
				case TERM_LOAN:
					applicationMaster = new PrimaryTermLoanDetail();
					break;
				case LAS_LOAN:
					applicationMaster = new PrimaryLasLoanDetail();
					break;
				case LAP_LOAN:
					applicationMaster = new PrimaryLapLoanDetail();
					break;
				case PERSONAL_LOAN:
					applicationMaster = new PrimaryPersonalLoanDetail();
					break;
				default:
					continue;
				}

				BeanUtils.copyProperties(loanApplicationRequest, applicationMaster);
				applicationMaster.setCreatedBy(loanApplicationRequest.getUserId());
				applicationMaster.setCreatedDate(new Date());
				applicationMaster.setIsActive(true);
				loanApplicationRepository.save(applicationMaster);
			}
			return true;
		}

		catch (Exception e) {
			// logger.info("Exception in save achievementDetail :-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public LoanApplicationRequest get(Long id) {
		LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
		BeanUtils.copyProperties(loanApplicationRepository.findOne(id), applicationRequest);
		return applicationRequest;
	}

	@Override
	public boolean inActive(Long id) {
		loanApplicationRepository.inActive(id);
		return true;
	}

	@Override
	public List<LoanApplicationRequest> getList(Long userId) {
		List<LoanApplicationMaster> results = loanApplicationRepository.getUserLoans(userId);
		List<LoanApplicationRequest> requests = new ArrayList<>(results.size());
		for (LoanApplicationMaster master : results) {
			LoanApplicationRequest request = new LoanApplicationRequest();
			BeanUtils.copyProperties(master, request);
			requests.add(request);
		}
		return requests;
	}

}
