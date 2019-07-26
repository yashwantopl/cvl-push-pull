package com.capitaworld.service.loans.service.common.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.common.OfflineAppConfig;
import com.capitaworld.service.loans.model.common.OfflineAppConfigRequest;
import com.capitaworld.service.loans.repository.common.OfflineAppConfigRepository;
import com.capitaworld.service.loans.service.common.OfflineAppConfigService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class OfflineAppConfigServiceImpl implements OfflineAppConfigService{
	private static final Logger logger = LoggerFactory.getLogger(OfflineAppConfigServiceImpl.class);

	@Autowired
	private OfflineAppConfigRepository offlineAppConfigRepository; 
	
	@Override
	public OfflineAppConfigRequest save(OfflineAppConfigRequest appConfigRequest,Long userId) {
		OfflineAppConfig offlineAppConfig = offlineAppConfigRepository.findFirstByOrgIdAndBusinessTypeIdAndIsActiveOrderByIdDesc(appConfigRequest.getOrgId(), appConfigRequest.getBusinessTypeId(), true);
		if(offlineAppConfig == null) {
			offlineAppConfig = new OfflineAppConfig();
			BeanUtils.copyProperties(appConfigRequest, offlineAppConfig,CommonUtils.IgnorableCopy.getAuditFieldsWithId());
			offlineAppConfig.setCreatedBy(userId);
			offlineAppConfig.setCreatedDate(new Date());
			offlineAppConfig.setIsActive(true);
		}else {
			BeanUtils.copyProperties(appConfigRequest, offlineAppConfig,CommonUtils.IgnorableCopy.getAuditFields());
			offlineAppConfig.setModifiedDate(new Date());
		}
		return prepareRequestFromDomain(offlineAppConfigRepository.save(offlineAppConfig),null);
	}

	@Override
	public OfflineAppConfigRequest get(OfflineAppConfigRequest appConfigRequest) {
		OfflineAppConfig offlineAppConfig = offlineAppConfigRepository.findFirstByOrgIdAndBusinessTypeIdAndIsActiveOrderByIdDesc(appConfigRequest.getOrgId(), appConfigRequest.getBusinessTypeId(), true);
		if(offlineAppConfig == null) {
			return null;
		}
		return prepareRequestFromDomain(offlineAppConfig, null);
	}
	
	/**
	 * 
	 * @param offlineAppConfig
	 * @param appConfigRequest Pass Null if U want to create new
	 * @return
	 */
	private OfflineAppConfigRequest prepareRequestFromDomain(OfflineAppConfig offlineAppConfig,OfflineAppConfigRequest appConfigRequest) {
		if(appConfigRequest == null) {
			appConfigRequest = new OfflineAppConfigRequest();			
		}
		BeanUtils.copyProperties(offlineAppConfig, appConfigRequest);
		return appConfigRequest;
	}
	
	
}
