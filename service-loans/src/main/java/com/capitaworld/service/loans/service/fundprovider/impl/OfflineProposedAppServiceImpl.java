package com.capitaworld.service.loans.service.fundprovider.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.model.OfflineProcessedApplicationRequest;
import com.capitaworld.service.loans.repository.OfflineProcessedAppRepository;
import com.capitaworld.service.loans.service.fundprovider.OfflineProcessedAppService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Transactional
@Service
public class OfflineProposedAppServiceImpl implements OfflineProcessedAppService{
	
	private static final Logger logger = LoggerFactory.getLogger(OfflineProposedAppServiceImpl.class);
	
	@Autowired
	private OfflineProcessedAppRepository offlineProcessedAppRepository;
	
	@Override
	public List<OfflineProcessedApplicationRequest> getApplicationList(Long orgId) {
		List<OfflineProcessedApplicationRequest> applicationRequests = Collections.emptyList();
		if(!CommonUtils.isObjectNullOrEmpty(orgId)) {
			List<Object []> lst = offlineProcessedAppRepository.getInEligibleRecordList(orgId);
			if(!CommonUtils.isObjectListNull(lst)) {
				applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
				for(Object[] obj : lst) {
					OfflineProcessedApplicationRequest request = new OfflineProcessedApplicationRequest();
					request.setApplicationId(((BigInteger)obj[0]).longValue());
					request.setUserId(((BigInteger)obj[1]).longValue());
					request.setLoanAmount(((BigDecimal)obj[2]).doubleValue());
					request.setIsCampaignUser(!CommonUtils.isObjectNullOrEmpty(obj[3])?((String)obj[3]).toString() : null);
					request.setBranchName(((String)obj[4]).toString());
					request.setOrganisationName(((String)obj[5]).toString());
					request.setIsSanctioned(!CommonUtils.isObjectNullOrEmpty(obj[6])?((Boolean)obj[6]).booleanValue():null);
					request.setIsDisbursed(!CommonUtils.isObjectNullOrEmpty(obj[7])?((Boolean)obj[7]).booleanValue():null);
					request.setBranchId(!CommonUtils.isObjectNullOrEmpty(obj[8])?((BigInteger)obj[8]).longValue():null);
					request.setPan(!CommonUtils.isObjectNullOrEmpty(obj[9])? ((String)obj[9]).toString() : null);
					request.setGstin(!CommonUtils.isObjectNullOrEmpty(obj[10])? ((String)obj[10]).toString() : null);
					applicationRequests.add(request);
				}
			}
		}
		return applicationRequests;
	}

	@Override
	public List<OfflineProcessedApplicationRequest> getSanctionedApplicationList(Long orgId) {
		List<OfflineProcessedApplicationRequest> applicationRequests = Collections.emptyList();
		if(!CommonUtils.isObjectNullOrEmpty(orgId)) {
			List<Object []> lst = offlineProcessedAppRepository.getSanctionedApplicationList(orgId);
			if(!CommonUtils.isObjectListNull(lst)) {
				applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
				for(Object[] obj : lst) {
					OfflineProcessedApplicationRequest request = new OfflineProcessedApplicationRequest();
					request.setSanctionedAmount(!CommonUtils.isObjectNullOrEmpty(obj[0])? ((Double)obj[0]).doubleValue() : null);
					request.setSanctionDate(!CommonUtils.isObjectNullOrEmpty(obj[1])? ((Date)obj[1]) : null);
					request.setRemark(!CommonUtils.isObjectNullOrEmpty(obj[2])? ((String)obj[2]).toString() : null);
					request.setRoi(!CommonUtils.isObjectNullOrEmpty(obj[3])?((Double)obj[3]).doubleValue() : null);
					request.setTenure(!CommonUtils.isObjectNullOrEmpty(obj[4])? ((Double)obj[4]).doubleValue() : null);
					request.setProcessingFee(!CommonUtils.isObjectNullOrEmpty(obj[5])? ((Double)obj[5]).doubleValue(): null);
					request.setApplicationId(((BigInteger)obj[6]).longValue());
					request.setIsPartiallyDisbursedOffline(!CommonUtils.isObjectNullOrEmpty(obj[7])?((Boolean)obj[7]).booleanValue():null);
					request.setOrganisationName(((String)obj[8]).toString());
					applicationRequests.add(request);
				}
			}
		}
		return applicationRequests;
	}

	@Override
	public List<OfflineProcessedApplicationRequest> getDisbursedApplicationList(Long orgId) {
		List<OfflineProcessedApplicationRequest> applicationRequests = Collections.emptyList();
		if(!CommonUtils.isObjectNullOrEmpty(orgId)) {
			List<Object []> lst = offlineProcessedAppRepository.getDisbursedApplicationList(orgId);
			System.out.println("lst"+lst);
			if(!CommonUtils.isObjectListNull(lst)) {
				applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
				for(Object[] obj : lst) {
					OfflineProcessedApplicationRequest request = new OfflineProcessedApplicationRequest();
					request.setOrganisationName(((String)obj[4]).toString());
					request.setApplicationId(((BigInteger)obj[5]).longValue());
					applicationRequests.add(request);
				}
			}
		}
		return applicationRequests;	
	}

}
