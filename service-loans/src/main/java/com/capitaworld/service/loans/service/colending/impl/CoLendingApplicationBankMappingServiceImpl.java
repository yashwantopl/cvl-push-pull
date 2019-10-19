package com.capitaworld.service.loans.service.colending.impl;

import com.capitaworld.service.loans.controller.colending.CoLendingApplicationBankMappingController;
import com.capitaworld.service.loans.domain.colending.CoLendingApplicationBankMapping;
import com.capitaworld.service.loans.model.colending.CoLendingApplicationBankMappingRequest;
import com.capitaworld.service.loans.repository.colending.CoLendingApplicationBankMappingRepository;
import com.capitaworld.service.loans.service.colending.CoLendingApplicationBankMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by dhaval.panchal on 27-Aug-19.
 */
@Service
@Transactional
public class CoLendingApplicationBankMappingServiceImpl implements CoLendingApplicationBankMappingService{

    private static final Logger logger = LoggerFactory.getLogger(CoLendingApplicationBankMappingServiceImpl.class.getName());

    @Autowired
    private CoLendingApplicationBankMappingRepository coLendingApplicationBankMappingRepository;

    @Override
    public boolean save(CoLendingApplicationBankMappingRequest coLendingApplicationBankMappingRequest) {
        try {
            for (Integer bankOrgId: coLendingApplicationBankMappingRequest.getBankOrgIdList()) {
                CoLendingApplicationBankMapping domain = new CoLendingApplicationBankMapping();
                domain.setApplicationId(coLendingApplicationBankMappingRequest.getApplicationId());
                domain.setBankOrgId(bankOrgId);
                domain.setActive(true);
                domain.setCreatedDate(new Date());
                coLendingApplicationBankMappingRepository.save(domain);
            }
            return true;
        } catch (Exception e) {
            logger.error("Error While save object {}",e);
            return false;
        }
    }

    @Override
    public CoLendingApplicationBankMappingRequest get(CoLendingApplicationBankMappingRequest coLendingApplicationBankMappingRequest){
        try {
            coLendingApplicationBankMappingRequest.setBankOrgIdList(coLendingApplicationBankMappingRepository.findListByApplicationIdAndIsActive(coLendingApplicationBankMappingRequest.getApplicationId(),true));
            return coLendingApplicationBankMappingRequest;
        }catch (Exception e){
            logger.error("Error While getting Bank mapping object {}",e);
        }
        return null;
    }
}
