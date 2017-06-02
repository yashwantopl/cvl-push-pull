package com.capitaworld.service.loans.service.common.impl;

import com.capitaworld.service.loans.repository.FundProviderSequenceRepository;
import com.capitaworld.service.loans.service.common.FundProviderSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dhaval on 02-Jun-17.
 */
@Service
@Transactional
public class FundProviderSequenceServiceImpl implements FundProviderSequenceService {

    @Autowired
    FundProviderSequenceRepository fundProviderSequenceRepository;

    @Override
    public String getFundProviderSequenceNumber(int productId) {
        Long sequenceNumber;
        switch (productId){
            case 1://WORKING CAPITAL
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-WC-"+sequenceNumber;
            case 2://WORKING CAPITAL
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-TL-"+sequenceNumber;
            case 3://HOME LOAN
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-HL-"+sequenceNumber;
            case 7://PERSONAL LOAN
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-PL-"+sequenceNumber;
            case 12://CAR_LOAN
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-CL-"+sequenceNumber;
            case 13://LOAN_AGAINST_PROPERTY
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-LAP-"+sequenceNumber;
            case 14://LOAN_AGAINST_SHARES_AND_SECUIRITIES
                sequenceNumber = fundProviderSequenceRepository.getFundProviderSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                fundProviderSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-LAS-"+sequenceNumber;
            default:
                return null;
        }
    }
}
