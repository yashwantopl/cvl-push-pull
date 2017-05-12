package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryCarLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLasLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.CommonResponse;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationServiceImpl.class.getName());

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Override
	public boolean saveOrUpdate(FrameRequest commonRequest) throws Exception {
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
				case HOME_LOAN:
					applicationMaster = new PrimaryHomeLoanDetail();
					break;
				case CAR_LOAN:
					applicationMaster = new PrimaryCarLoanDetail();
					break;

				default:
					continue;
				}

				BeanUtils.copyProperties(loanApplicationRequest, applicationMaster);
				applicationMaster.setCreatedBy(commonRequest.getUserId());
				applicationMaster.setCreatedDate(new Date());
				applicationMaster.setModifiedBy(commonRequest.getUserId());
				applicationMaster.setModifiedDate(new Date());
				applicationMaster.setIsActive(true);
				loanApplicationRepository.save(applicationMaster);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Error while Saving Loan Details:-");
			e.printStackTrace();
			throw new Exception("Something went Wrong !");
		}
	}

	@Override
	public LoanApplicationRequest get(Long id, Long userId) throws Exception {
		LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
		LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(id, userId);
		if (applicationMaster == null) {
			throw new NullPointerException("Invalid Loan Application ID==>" + id + " of User ID==>" + userId);
		}
		BeanUtils.copyProperties(applicationMaster, applicationRequest);
		return applicationRequest;
	}

	@Override
	public boolean inActive(Long id, Long userId) throws Exception {
		loanApplicationRepository.inActive(id, userId);
		return true;
	}

	@Override
	public List<LoanApplicationRequest> getList(Long userId) throws Exception {
		List<LoanApplicationMaster> results = loanApplicationRepository.getUserLoans(userId);
		List<LoanApplicationRequest> requests = new ArrayList<>(results.size());
		for (LoanApplicationMaster master : results) {
			LoanApplicationRequest request = new LoanApplicationRequest();
			BeanUtils.copyProperties(master, request);
			requests.add(request);
		}
		return requests;
	}

	@Override
	public List<LoansResponse> getLoanDetailsByUserIdList(List<Long> userList) {
		List<LoansResponse> loansResponses = new ArrayList<LoansResponse>();
		for (Long id : userList) {
			LoansResponse loansResponse = new LoansResponse();
			loansResponse.setId(id);
			loansResponse.setListData(loanApplicationRepository.getListByUserId(id));
			loansResponses.add(loansResponse);
		}
		return loansResponses;
	}

	@Override
	public String getApplicationType(Long applicationId) {
		// TODO Auto-generated method stub
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if (applicationMaster != null) {
			if (applicationMaster.getProductId() == 1 || applicationMaster.getProductId() == 2) {
				return CommonUtils.CORPORATE;
			} else if (applicationMaster.getProductId() == 3 || applicationMaster.getProductId() == 12
					|| applicationMaster.getProductId() == 7 || applicationMaster.getProductId() == 13
					|| applicationMaster.getProductId() == 14) {
				return CommonUtils.RETAIL;
			}
		}
		return null;
	}

	@Override
	public String getUserNameByApplicationId(Long applicationId,Long userId) {
		// TODO Auto-generated method stub
		String userType = getApplicationType(applicationId);
		String userName;
		if (userType == null)
			return null;

		if (userType.equals(CommonUtils.CORPORATE)) {
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.getByApplicationAndUserId(userId, applicationId);
			if (corporateApplicantDetail != null) {
				userName = corporateApplicantDetail.getOrganisationName();
				if (userName != null || userName.length() != 0) {
					return userName;
				}
			}
		} else if (userType.equals(CommonUtils.RETAIL)) {
			RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.getByApplicationAndUserId(userId, applicationId);
			if (retailApplicantDetail != null) {
				userName = retailApplicantDetail.getFirstName() + retailApplicantDetail.getLastName();
				if (userName != null || userName.length() != 0) {
					return userName;
				}
			}
		} else {
			return null;
		}
		return null;
	}

}
