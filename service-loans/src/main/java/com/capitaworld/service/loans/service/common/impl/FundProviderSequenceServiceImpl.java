package com.capitaworld.service.loans.service.common.impl;

import com.capitaworld.service.loans.repository.FundProviderSequenceRepository;
import com.capitaworld.service.loans.service.common.FundProviderSequenceService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dhaval on 02-Jun-17.
 */
@Service
@Transactional
public class FundProviderSequenceServiceImpl implements FundProviderSequenceService {

	private static final Logger logger = LoggerFactory.getLogger(FundProviderSequenceServiceImpl.class);

	private static final String GET_FUND_PROVIDER_SEQUENCE_NUMBER = "getFundProviderSequenceNumber";
	
    @Autowired
    FundProviderSequenceRepository fundProviderSequenceRepository;

    @Override
    public String getFundProviderSequenceNumber(int productId) {
    	CommonDocumentUtils.startHook(logger, GET_FUND_PROVIDER_SEQUENCE_NUMBER);
        Long sequenceNumber;
        switch (productId){
            case 1://WORKING CAPITAL
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                CommonDocumentUtils.endHook(logger, GET_FUND_PROVIDER_SEQUENCE_NUMBER);
                return "CW-WC-"+sequenceNumber;
            case 2://WORKING CAPITAL
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                CommonDocumentUtils.endHook(logger, GET_FUND_PROVIDER_SEQUENCE_NUMBER);
                return "CW-TL-"+sequenceNumber;
            case 3://HOME LOAN
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                CommonDocumentUtils.endHook(logger, GET_FUND_PROVIDER_SEQUENCE_NUMBER);
                return "CW-HL-"+sequenceNumber;
            case 7://PERSONAL LOAN
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                CommonDocumentUtils.endHook(logger, GET_FUND_PROVIDER_SEQUENCE_NUMBER);
                return "CW-PL-"+sequenceNumber;
            case 12://CAR_LOAN
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                CommonDocumentUtils.endHook(logger, GET_FUND_PROVIDER_SEQUENCE_NUMBER);
                return "CW-CL-"+sequenceNumber;
            case 13://LOAN_AGAINST_PROPERTY
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                CommonDocumentUtils.endHook(logger, GET_FUND_PROVIDER_SEQUENCE_NUMBER);
                return "CW-LAP-"+sequenceNumber;
            case 14://LOAN_AGAINST_SHARES_AND_SECUIRITIES
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                CommonDocumentUtils.endHook(logger, GET_FUND_PROVIDER_SEQUENCE_NUMBER);
                return "CW-LAS-"+sequenceNumber;
            case 15://Unsecure loan
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                CommonDocumentUtils.endHook(logger, GET_FUND_PROVIDER_SEQUENCE_NUMBER);
                return "CW-USL-"+sequenceNumber;
            case 16://WCTL loan
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                CommonDocumentUtils.endHook(logger, GET_FUND_PROVIDER_SEQUENCE_NUMBER);
                return "CW-WCTL-"+sequenceNumber;
                
            default:
            	logger.error("Product Id Found NUll while getting Unique Sequence Number. ");
                return null;
        }
    }
}
