package com.opl.service.loans.service.fundprovider.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opl.mudra.api.loans.model.FpNpMappingRequest;
import com.opl.service.loans.domain.fundprovider.FpNpMapping;
import com.opl.service.loans.repository.fundprovider.FpNpMappingRepository;
import com.opl.service.loans.service.fundprovider.FpNpMappingService;

@Service
@Transactional
public class FpNpMappingServiceImpl implements FpNpMappingService {

    private static final Logger logger = LoggerFactory.getLogger(FpNpMappingServiceImpl.class);

    @Autowired
    private FpNpMappingRepository fpNpMappingRepository;

    @Override
    public Boolean fpNpBoLevelMapping(FpNpMappingRequest fpNpMappingRequest) {
        logger.info("entry in fpNpBoLevelMapping()");
        FpNpMapping fpNpMapping = new FpNpMapping();
        BeanUtils.copyProperties(fpNpMappingRequest,fpNpMapping);
        fpNpMapping.setCreatedBy(fpNpMapping.getFpProductId());
        fpNpMapping.setCreatedDate(new Date());
        fpNpMapping.setIsActive(true);
        fpNpMappingRepository.save(fpNpMapping);
        logger.info("exit from fpNpBoLevelMapping()");
        return true;
    }
}
