package com.opl.service.loans.service.common.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.model.common.OfflineAppConfigRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.service.loans.domain.common.OfflineAppConfig;
import com.opl.service.loans.domain.common.OfflineAppConfigAudit;
import com.opl.service.loans.repository.common.OfflineAppConfigAuditRepository;
import com.opl.service.loans.repository.common.OfflineAppConfigRepository;
import com.opl.service.loans.service.common.OfflineAppConfigService;

@Service
@Transactional
public class OfflineAppConfigServiceImpl implements OfflineAppConfigService{
	private static final Logger logger = LoggerFactory.getLogger(OfflineAppConfigServiceImpl.class);

	@Autowired
	private OfflineAppConfigRepository offlineAppConfigRepository;
	
	@Autowired
	private OfflineAppConfigAuditRepository offlineAppConfigAuditRepository;
	
	@Override
	public OfflineAppConfigRequest save(OfflineAppConfigRequest appConfigRequest,Long userId) {
		OfflineAppConfig offlineAppConfig = offlineAppConfigRepository.findFirstByOrgIdAndBusinessTypeIdAndIsActiveOrderByIdDesc(appConfigRequest.getOrgId(), appConfigRequest.getBusinessTypeId(), true);
		if(offlineAppConfig == null) {
			offlineAppConfig = new OfflineAppConfig();
			BeanUtils.copyProperties(appConfigRequest, offlineAppConfig,CommonUtils.IgnorableCopy.getAuditFields());
			offlineAppConfig.setCreatedBy(userId);
			offlineAppConfig.setCreatedDate(new Date());
			offlineAppConfig.setIsActive(true);
		}else {
			BeanUtils.copyProperties(appConfigRequest, offlineAppConfig,CommonUtils.IgnorableCopy.getAuditFieldsWithId());
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
	
	
	@Override
	public List<OfflineAppConfigRequest> hisotry(OfflineAppConfigRequest appConfigRequest) {
		List<OfflineAppConfigAudit> audits = offlineAppConfigAuditRepository.findByOrgIdAndBusinessTypeIdAndIsActiveOrderByIdDesc(appConfigRequest.getOrgId(), appConfigRequest.getBusinessTypeId(), true);
		if(CommonUtils.isListNullOrEmpty(audits)) {
			return Collections.emptyList();
		}
		List<OfflineAppConfigRequest> appConfigRequests = new ArrayList<OfflineAppConfigRequest>(audits.size());
		for(OfflineAppConfigAudit audit :  audits) {
			appConfigRequests.add(prepareRequestFromDomainAudit(audit, null));
		}
		return appConfigRequests;
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
	/**
	 * Prepare Request Form Audit Domain
	 * @param offlineAppConfig
	 * @param appConfigRequest
	 * @return
	 */
	
	private OfflineAppConfigRequest prepareRequestFromDomainAudit(OfflineAppConfigAudit  offlineAppConfig,OfflineAppConfigRequest appConfigRequest) {
		if(appConfigRequest == null) {
			appConfigRequest = new OfflineAppConfigRequest();			
		}
		BeanUtils.copyProperties(offlineAppConfig, appConfigRequest);
		appConfigRequest.setToDate(offlineAppConfig.getCreatedDate());
		return appConfigRequest;
	}
	
	
}
