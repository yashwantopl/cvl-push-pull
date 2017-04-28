package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class LoanApplicationServiceImpl implements LoanApplicationService {

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public boolean saveOrUpdate(FrameRequest commonRequest) {
		try {
			for (Map<String, Object> obj : commonRequest.getDataList()) {
				LoanApplicationRequest loanApplicationRequest = (LoanApplicationRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, LoanApplicationRequest.class);
				if (loanApplicationRequest.getLoanType() == CommonUtils.LoanType.CORPORATE) {
					CorporateApplicantDetail applicantDetail = new CorporateApplicantDetail();
					BeanUtils.copyProperties(loanApplicationRequest, applicantDetail);
					loanApplicationRepository.save(applicantDetail);

				} else if (loanApplicationRequest.getLoanType() == CommonUtils.LoanType.RETAIL) {

				}
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
