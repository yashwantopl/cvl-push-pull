package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.ApplicationProposalMappingService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;

import com.capitaworld.service.loans.utils.CommonUtils;
import org.json.simple.JSONObject;
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
	public ApplicationProposalMapping getApplicationProposalMappingByProposalId(Long proposalId) {
		return repository.findByProposalIdAndIsActive(proposalId,true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getCurrencyAndDenomination(Long applicationId, Long proposalId, Long userId) throws LoansException {
		try {
			Integer currencyId = repository.getCurrencyId(applicationId, proposalId, userId);
			Integer denominationId = repository.getDenominationId(applicationId, proposalId, userId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("currency", CommonDocumentUtils.getCurrency(currencyId));
			jsonObject.put("denomination", CommonDocumentUtils.getDenomination(denominationId));
			return jsonObject;
		} catch (Exception e) {
			logger.error("Error while getting Currency and Denomination Value : ", e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public JSONObject getCurrencyAndDenomination(Long proposalId) throws LoansException {
		try {
			Integer currencyId = repository.getCurrencyId(proposalId);
			Integer denominationId = repository.getDenominationId(proposalId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("currency", CommonDocumentUtils.getCurrency(currencyId));
			jsonObject.put("denomination", CommonDocumentUtils.getDenomination(denominationId));
			return jsonObject;
		} catch (Exception e) {
			logger.error("Error while getting Currency and Denomination Value : ", e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}
}
