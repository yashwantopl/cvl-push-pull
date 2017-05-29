package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryCarLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLasLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryTermLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryWorkingCapitalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryCarLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryLapLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryLasLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryPersonalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationServiceImpl.class.getName());

	@Autowired
	private Environment environment;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private PrimaryWorkingCapitalLoanDetailRepository primaryWorkingCapitalLoanDetailRepository;

	@Autowired
	private PrimaryTermLoanDetailRepository primaryTermLoanDetailRepository;

	@Autowired
	private PrimaryLasLoanDetailRepository primaryLasLoanDetailRepository;

	@Autowired
	private PrimaryLapLoanDetailRepository primaryLapLoanDetailRepository;

	@Autowired
	private PrimaryHomeLoanDetailRepository primaryHomeLoanDetailRepository;

	@Autowired
	private PrimaryPersonalLoanDetailRepository primaryPersonalLoanDetailRepository;

	@Autowired
	private PrimaryCarLoanDetailRepository primaryCarLoanDetailRepository;

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Override
	public boolean saveOrUpdate(FrameRequest commonRequest, Long userId) throws Exception {
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
				logger.info("userId==>" + (CommonUtils.isObjectNullOrEmpty(commonRequest.getClientId()) ? userId
						: commonRequest.getClientId()));
				BeanUtils.copyProperties(loanApplicationRequest, applicationMaster);
				applicationMaster.setUserId((CommonUtils.isObjectNullOrEmpty(commonRequest.getClientId()) ? userId
						: commonRequest.getClientId()));
				applicationMaster.setCreatedBy(userId);
				applicationMaster.setCreatedDate(new Date());
				applicationMaster.setModifiedBy(userId);
				applicationMaster.setModifiedDate(new Date());
				// For Demo now we have put it static,
				applicationMaster.setIsApplicantPrimaryFilled(true);
				// later on we will validate and change it.
				applicationMaster = loanApplicationRepository.save(applicationMaster);
				lockPrimary(applicationMaster.getId(), (CommonUtils.isObjectNullOrEmpty(commonRequest.getClientId())
						? userId : commonRequest.getClientId()), applicationMaster.getProductId());
				logger.info("applicationMaster==>" + applicationMaster.toString());
			}
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public LoanApplicationRequest get(Long id, Long userId) throws Exception {
		try {
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(id, userId);
			if (applicationMaster == null) {
				throw new NullPointerException("Invalid Loan Application ID==>" + id + " of User ID==>" + userId);
			}
			BeanUtils.copyProperties(applicationMaster, applicationRequest);
			applicationRequest.setHasAlreadyApplied(hasAlreadyApplied(userId, applicationMaster.getId(), applicationMaster.getProductId()));
			int userMainType = CommonUtils.getUserMainType(applicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				applicationRequest.setLoanTypeMain(CommonUtils.CORPORATE);
			} else {
				applicationRequest.setLoanTypeMain(CommonUtils.RETAIL);
			}
			applicationRequest.setLoanTypeSub(CommonUtils.getCorporateLoanType(applicationMaster.getProductId()));
			if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getCurrencyId())) {
				Currency currency = Currency.getById(applicationMaster.getCurrencyId());
				applicationRequest.setCurrencyValue(currency.getValue());
			}
			return applicationRequest;
		} catch (Exception e) {
			logger.error("Error while getting Individual Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public boolean inActive(Long id, Long userId) throws Exception {
		loanApplicationRepository.inActive(id, userId);
		return true;
	}

	@Override
	public List<LoanApplicationRequest> getList(Long userId) throws Exception {
		try {
			List<LoanApplicationMaster> results = loanApplicationRepository.getUserLoans(userId);
			List<LoanApplicationRequest> requests = new ArrayList<>(results.size());
			for (LoanApplicationMaster master : results) {
				LoanApplicationRequest request = new LoanApplicationRequest();
				BeanUtils.copyProperties(master, request);
				request.setHasAlreadyApplied(hasAlreadyApplied(userId, master.getId(), master.getProductId()));
				int userMainType = CommonUtils.getUserMainType(master.getProductId());
				if (userMainType == CommonUtils.UserMainType.CORPORATE) {
					request.setLoanTypeMain(CommonUtils.CORPORATE);
				} else {
					request.setLoanTypeMain(CommonUtils.RETAIL);
				}
				request.setLoanTypeSub(CommonUtils.getCorporateLoanType(master.getProductId()));
				if (!CommonUtils.isObjectNullOrEmpty(master.getCurrencyId())) {
					request.setCurrencyValue(CommonDocumentUtils.getCurrency(master.getCurrencyId()));
				}
				requests.add(request);
			}
			return requests;
		} catch (Exception e) {
			logger.error("Error while Getting Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<LoanApplicationDetailsForSp> getLoanDetailsByUserIdList(Long userId) {
		return loanApplicationRepository.getListByUserId(userId);
	}

	@Override
	public boolean lockPrimary(Long applicationId, Long userId, Integer productId) throws Exception {
		try {
			LoanApplicationMaster applicationMaster = null;
			LoanType type = CommonUtils.LoanType.getType(productId);
			if (type == null) {
				throw new Exception("Invalid Product Id==>" + productId);
			}
			switch (type) {
			case WORKING_CAPITAL:
				applicationMaster = primaryWorkingCapitalLoanDetailRepository.getByApplicationAndUserId(applicationId,
						userId);
				break;
			case TERM_LOAN:
				applicationMaster = primaryTermLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
				break;
			case LAS_LOAN:
				applicationMaster = primaryLasLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
				break;
			case LAP_LOAN:
				applicationMaster = primaryLapLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
				break;
			case PERSONAL_LOAN:
				applicationMaster = primaryPersonalLoanDetailRepository.getByApplicationAndUserId(applicationId,
						userId);
				break;
			case HOME_LOAN:
				applicationMaster = primaryHomeLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
				break;
			case CAR_LOAN:
				applicationMaster = primaryCarLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
				break;
			default:
				throw new Exception("Invalid Product Id==>" + productId);
			}

			if (applicationMaster == null) {
				throw new Exception(
						"LoanapplicationMaster object Must not be null while locking the Profile And Primary Details==>"
								+ applicationMaster);
			}

			applicationMaster.setIsPrimaryLocked(true);
			loanApplicationRepository.save(applicationMaster);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Locking Profile and Primary Information");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public boolean lockFinal(Long applicationId, Long userId, Integer productId) throws Exception {
		try {
			LoanApplicationMaster applicationMaster = null;
			LoanType type = CommonUtils.LoanType.getType(productId);
			if (type == null) {
				throw new Exception("Invalid Product Id==>" + productId);
			}
			switch (type) {
			case WORKING_CAPITAL:
				applicationMaster = primaryWorkingCapitalLoanDetailRepository.getByApplicationAndUserId(applicationId,
						userId);
				break;
			case TERM_LOAN:
				applicationMaster = primaryTermLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
				break;
			case LAS_LOAN:
				applicationMaster = primaryLasLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
				break;
			case LAP_LOAN:
				applicationMaster = primaryLapLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
				break;
			case PERSONAL_LOAN:
				applicationMaster = primaryPersonalLoanDetailRepository.getByApplicationAndUserId(applicationId,
						userId);
				break;
			case HOME_LOAN:
				applicationMaster = primaryHomeLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
				break;
			case CAR_LOAN:
				applicationMaster = primaryCarLoanDetailRepository.getByApplicationAndUserId(applicationId, userId);
				break;
			default:
				throw new Exception("Invalid Product Id==>" + productId);
			}

			if (applicationMaster == null) {
				throw new Exception(
						"LoanapplicationMaster object Must not be null while locking the Profile And Primary Details==>"
								+ applicationMaster);
			}

			applicationMaster.setIsFinalLocked(true);
			loanApplicationRepository.save(applicationMaster);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Locking Final Information");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
			
		} 
	}

	@Override
	public UserResponse setLastAccessApplication(Long applicationId, Long userId) throws Exception {
		try {
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setLastAccessApplicantId(applicationId);
			usersRequest.setId(userId);
			UsersClient client = new UsersClient(environment.getRequiredProperty(CommonUtils.USER_CLIENT_URL));
			return client.setLastAccessApplicant(usersRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);

		}

	}

	private boolean hasAlreadyApplied(Long userId, Long applicationId, Integer productId) {
		if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(productId)) {
			return (corporateApplicantDetailRepository.hasAlreadyApplied(userId, applicationId) > 0 ? true : false);
		} else {
			return (retailApplicantDetailRepository.hasAlreadyApplied(userId, applicationId) > 0 ? true : false);
		}
	}

	@Override
	public Integer getProductIdByApplicationId(Long applicationId, Long userId) throws Exception {
		try {
			return loanApplicationRepository.getProductIdByApplicationId(applicationId, userId);
		} catch (Exception e) {
			logger.error("Error while getting Product Id by Application Id");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	@Override
	public Object[] getApplicationDetailsById(Long applicationId)
	{
	return loanApplicationRepository.getUserDetailsByApplicationId(applicationId);
	}

}
