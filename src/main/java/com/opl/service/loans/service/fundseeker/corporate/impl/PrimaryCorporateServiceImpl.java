package com.opl.service.loans.service.fundseeker.corporate.impl;

import java.util.Date;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.PrimaryCorporateRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.opl.service.loans.repository.fundseeker.IneligibleProposalDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.opl.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.opl.service.loans.service.fundseeker.corporate.PrimaryCorporateService;

@Service
@Transactional
public class PrimaryCorporateServiceImpl implements PrimaryCorporateService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryCorporateServiceImpl.class.getName());

	private static final String ERROR_WHILE_PRIMARY_CORPORATE_DETAILS_MSG = "Error while Primary Corporate Details:-";
	private static final String PRIMARY_CORPORATE_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG = "PrimaryCorporateDetail not exist in DB with ID=>";

	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateRepository;

	@Autowired
	private LoanApplicationService loanApplicationService;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private IneligibleProposalDetailsRepository ineligibleProposalDetailsRepository;

	@Override
	public boolean saveOrUpdate(PrimaryCorporateRequest primaryCorporateRequest, Long userId) throws LoansException {

		try {
			PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository.getByApplicationAndUserId(
					primaryCorporateRequest.getId(),
					(CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getClientId()) ? userId
							: primaryCorporateRequest.getClientId()));
			if (primaryCorporateDetail == null) {
				throw new NullPointerException(PRIMARY_CORPORATE_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG
						+ primaryCorporateRequest.getId() + " and UserId==>" + userId);
			}
			BeanUtils.copyProperties(primaryCorporateRequest, primaryCorporateDetail,
					CommonUtils.IgnorableCopy.getCORPORATE());
			primaryCorporateDetail.setModifiedBy(userId);
			primaryCorporateDetail.setModifiedDate(new Date());
			primaryCorporateRepository.save(primaryCorporateDetail);
			return true;
		} catch (Exception e) {
			logger.error(ERROR_WHILE_PRIMARY_CORPORATE_DETAILS_MSG, e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public PrimaryCorporateRequest get(Long applicationId, Long userId) throws LoansException {
		try {
			if(CommonUtils.isObjectNullOrEmpty(userId)){
				LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getById(applicationId);
				if(loanApplicationMaster == null){
					throw new NullPointerException(
							PRIMARY_CORPORATE_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG + applicationId + " and UserId==>" + userId);
				}
				userId = loanApplicationMaster.getUserId();
			}
			PrimaryCorporateDetail loanDetail = primaryCorporateRepository.getByApplicationAndUserId(applicationId,
					userId);
			if (loanDetail == null) {
				throw new NullPointerException(
						PRIMARY_CORPORATE_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG + applicationId + " and UserId==>" + userId);
			}
			PrimaryCorporateRequest primaryCorporateRequest = new PrimaryCorporateRequest();
			BeanUtils.copyProperties(loanDetail, primaryCorporateRequest);
			JSONObject result = loanApplicationService.getCurrencyAndDenomination(applicationId, userId);
			String data = result.get("currency").toString();
			data = data.concat(" In " + result.get("denomination").toString());
			primaryCorporateRequest.setCurrencyValue(data);
			return primaryCorporateRequest;
		} catch (Exception e) {
			logger.error(ERROR_WHILE_PRIMARY_CORPORATE_DETAILS_MSG, e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public PrimaryCorporateRequest get(Long applicationId) {
		try {
			PrimaryCorporateDetail loanDetail = primaryCorporateRepository.findByApplicationIdId(applicationId);
			if (loanDetail == null) {
				throw new NullPointerException(PRIMARY_CORPORATE_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG + applicationId);
			}
			PrimaryCorporateRequest primaryCorporateRequest = new PrimaryCorporateRequest();
			BeanUtils.copyProperties(loanDetail, primaryCorporateRequest);
			return primaryCorporateRequest;
		} catch (Exception e) {
			logger.error(ERROR_WHILE_PRIMARY_CORPORATE_DETAILS_MSG, e);
		}
		return null;
	}

	@Override
	public boolean saveOrUpdateSpecificData(PrimaryCorporateRequest primaryCorporateRequest, Long userId) throws LoansException {

		try {
			PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository.getByApplicationAndUserId(
					primaryCorporateRequest.getId(),
					(CommonUtils.isObjectNullOrEmpty(primaryCorporateRequest.getClientId()) ? userId
							: primaryCorporateRequest.getClientId()));
			if (primaryCorporateDetail == null) {
				throw new NullPointerException(PRIMARY_CORPORATE_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG
						+ primaryCorporateRequest.getId() + " and UserId==>" + userId);
			}

			primaryCorporateDetail.setPurposeOfLoanId(primaryCorporateRequest.getPurposeOfLoanId());
			primaryCorporateDetail.setModifiedBy(userId);
			primaryCorporateDetail.setModifiedDate(new Date());
			primaryCorporateRepository.save(primaryCorporateDetail);
			return true;
		} catch (Exception e) {
			logger.error(ERROR_WHILE_PRIMARY_CORPORATE_DETAILS_MSG, e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	

	@Override
	public boolean saveSwitchExistingLoan(PrimaryCorporateRequest primaryCorporateRequest) throws LoansException {
		try {
			PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository.findByApplicationIdId(primaryCorporateRequest.getId());
			if (primaryCorporateDetail == null) {
				throw new NullPointerException(PRIMARY_CORPORATE_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG
						+ primaryCorporateRequest.getId());
			}
			primaryCorporateDetail.setIsAllowSwitchExistingLender(true);
			primaryCorporateDetail.setModifiedDate(new Date());
			primaryCorporateRepository.save(primaryCorporateDetail);

			ineligibleProposalDetailsRepository.updateInEligibleDataBasedonApplicationId(primaryCorporateRequest.getId(),false);
			return true;
		} catch (Exception e) {
			logger.error(ERROR_WHILE_PRIMARY_CORPORATE_DETAILS_MSG, e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
}