package com.capitaworld.service.loans.service.fundprovider.impl;

import com.capitaworld.service.loans.domain.fundprovider.FpNpMapping;
import com.capitaworld.service.loans.model.FpNpMappingRequest;
import com.capitaworld.service.loans.repository.fundprovider.FpNpMappingRepository;
import com.capitaworld.service.loans.service.fundprovider.FpNpMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
