package com.capitaworld.service.loans.service.common.impl;

import com.capitaworld.service.loans.repository.ApplicationSequenceRepository;
import com.capitaworld.service.loans.service.common.ApplicationSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dhaval on 02-Jun-17.
 */
@Service
@Transactional
public class ApplicationSequenceServiceImpl implements ApplicationSequenceService {

    @Autowired
    private ApplicationSequenceRepository applicationSequenceRepository;

    @Override
    public String getApplicationSequenceNumber(int productId) {
        Long sequenceNumber;
        switch (productId){
            case 1://WORKING CAPITAL
                sequenceNumber = applicationSequenceRepository.getApplicationSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                applicationSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-AWC-"+sequenceNumber;
            case 2://WORKING CAPITAL
                sequenceNumber = applicationSequenceRepository.getApplicationSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                applicationSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-ATL-"+sequenceNumber;
            case 3://HOME LOAN
                sequenceNumber = applicationSequenceRepository.getApplicationSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                applicationSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-AHL-"+sequenceNumber;
            case 7://PERSONAL LOAN
                sequenceNumber = applicationSequenceRepository.getApplicationSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                applicationSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-APL-"+sequenceNumber;
            case 12://CAR_LOAN
                sequenceNumber = applicationSequenceRepository.getApplicationSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                applicationSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-ACL-"+sequenceNumber;
            case 13://LOAN_AGAINST_PROPERTY
                sequenceNumber = applicationSequenceRepository.getApplicationSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                applicationSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-ALAP-"+sequenceNumber;
            case 14://LOAN_AGAINST_SHARES_AND_SECUIRITIES
                sequenceNumber = applicationSequenceRepository.getApplicationSequenceNumber(Long.valueOf(productId));
                sequenceNumber+=1;
                applicationSequenceRepository.updateSequenceNumber(sequenceNumber, Long.valueOf(productId));
                return "CW-ALAS-"+sequenceNumber;
            default:
                return null;
        }
    }
}
