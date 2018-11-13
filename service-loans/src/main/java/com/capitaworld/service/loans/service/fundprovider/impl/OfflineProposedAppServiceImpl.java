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
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Transactional
@Service
public class OfflineProposedAppServiceImpl implements OfflineProcessedAppService{
	
	private static final Logger logger = LoggerFactory.getLogger(OfflineProposedAppServiceImpl.class);
	
	@Autowired
	private OfflineProcessedAppRepository offlineProcessedAppRepository;
	
	@Autowired
	private UsersClient usersClient; 
	
	@Override
	public List<OfflineProcessedApplicationRequest> getApplicationList(Long orgId, Long userId) {
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
					request.setIsCampaignUser(!CommonUtils.isObjectNullOrEmpty(obj[3])?((String)obj[3]).toString() : "Market Place");
					request.setBranchName(((String)obj[4]).toString());
					request.setOrganisationName(((String)obj[5]).toString());
					request.setIsSanctioned(!CommonUtils.isObjectNullOrEmpty(obj[6])?((Boolean)obj[6]).booleanValue():null);
					request.setIsDisbursed(!CommonUtils.isObjectNullOrEmpty(obj[7])?((Boolean)obj[7]).booleanValue():null);
					request.setBranchId(!CommonUtils.isObjectNullOrEmpty(obj[8])?((BigInteger)obj[8]).longValue():null);
					request.setPan(!CommonUtils.isObjectNullOrEmpty(obj[9])? ((String)obj[9]).toString() : null);
					request.setGstin(!CommonUtils.isObjectNullOrEmpty(obj[10])? ((String)obj[10]).toString() : null);
					request.setBranchCode(!CommonUtils.isObjectNullOrEmpty(obj[11])? ((String)obj[11]).toString() : null);
					request.setBranchAddress(!CommonUtils.isObjectNullOrEmpty(obj[12])? ((String)obj[12]).toString() : null);
					request.setLocationData(!CommonUtils.isObjectNullOrEmpty(getLocationCode(userId)) ? getLocationCode(userId) : " ");
					applicationRequests.add(request);
				}
			}
		}
		return applicationRequests;
	}

	@Override
	public List<OfflineProcessedApplicationRequest> getSanctionedApplicationList(Long orgId, Long userId) {
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
					request.setBranchId(!CommonUtils.isObjectNullOrEmpty(obj[9])?((BigInteger)obj[9]).longValue():null);
					try {
						if(!CommonUtils.isObjectNullOrEmpty(obj[9])) {
							UserResponse resp = usersClient.getBranchNameById(((BigInteger)obj[9]).longValue());
							if(!CommonUtils.isObjectNullOrEmpty(resp.getData())) {
								String branchName = resp.getData().toString();
								request.setBranchName(branchName);
							}
						}
					} catch (Exception e) {
						logger.info("Error while getting Branch name");
						e.printStackTrace();
					}
					request.setLocationData(!CommonUtils.isObjectNullOrEmpty(getLocationCode(userId)) ? getLocationCode(userId) : " ");
					applicationRequests.add(request);
				}
			}
		}
		return applicationRequests;
	}

	@Override
	public List<OfflineProcessedApplicationRequest> getDisbursedApplicationList(Long orgId, Long userId) {
		List<OfflineProcessedApplicationRequest> applicationRequests = Collections.emptyList();
		if(!CommonUtils.isObjectNullOrEmpty(orgId)) {
			List<Object []> lst = offlineProcessedAppRepository.getDisbursedApplicationList(orgId);
			if(!CommonUtils.isObjectListNull(lst)) {
				applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
				for(Object[] obj : lst) {
					OfflineProcessedApplicationRequest request = new OfflineProcessedApplicationRequest();
					request.setOrganisationName(((String)obj[3]).toString());
					request.setApplicationId(((BigInteger)obj[4]).longValue());
					request.setBranchId(!CommonUtils.isObjectNullOrEmpty(obj[6])?((BigInteger)obj[6]).longValue():null);
					try {
						if(!CommonUtils.isObjectNullOrEmpty(obj[6])) {
							UserResponse resp = usersClient.getBranchNameById(((BigInteger)obj[6]).longValue());
							if(!CommonUtils.isObjectNullOrEmpty(resp.getData())) {
								String branchName = resp.getData().toString();
								request.setBranchName(branchName);
							}
						}
					} catch (Exception e) {
						logger.info("Error while getting Branch name");
						e.printStackTrace();
					}
					request.setLocationData(!CommonUtils.isObjectNullOrEmpty(getLocationCode(userId)) ? getLocationCode(userId) : " ");
					applicationRequests.add(request);
				}
			}
		}
		return applicationRequests;	
	}
	
	private Object getLocationCode(Long userId) {
		UsersRequest req = new UsersRequest();
		req.setId(userId);
		try {
			UserResponse resp = usersClient.getBranchDetailsBYUserId(req);
			logger.info("resp===>"+resp.getData());
			if(!CommonUtils.isObjectNullOrEmpty(resp.getData())) {
				return resp.getData();
			}
		} catch (Exception e) {
			logger.info("Error while getting Branch and location details from User Id");
			e.printStackTrace();
		}
		return null;
	}

}
