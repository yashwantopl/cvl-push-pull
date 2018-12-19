package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.ArrayList;
import java.util.Collections;
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
		if(!CommonUtils.isObjectNullOrEmpty(orgId)) {
			List<Object []> lst = offlineProcessedAppRepository.getInEligibleRecordList(orgId);
			if(!CommonUtils.isObjectListNull(lst)) {
				List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
				OfflineProcessedApplicationRequest request = null;
				Object locationCode = null;
				if(lst.size() > 0) {
					locationCode = getLocationCode(userId);
				}
				for(Object[] obj : lst) {
					request = new OfflineProcessedApplicationRequest();
					request.setApplicationId(CommonUtils.convertLong(obj[0]));
					request.setUserId(CommonUtils.convertLong(obj[1]));
					request.setLoanAmount(CommonUtils.convertDouble(obj[2]));
					request.setIsCampaignUser(!CommonUtils.isObjectNullOrEmpty(obj[3])?((String)obj[3]).toString() : "Market Place");
					request.setBranchName(CommonUtils.convertString(obj[4]));
					request.setOrganisationName(CommonUtils.convertString(obj[5]));
					request.setIsSanctioned(CommonUtils.convertBoolean(obj[6]));
					request.setIsDisbursed(CommonUtils.convertBoolean(obj[7]));
					request.setBranchId(CommonUtils.convertLong(obj[8]));
					request.setPan(CommonUtils.convertString(obj[9]));
					request.setGstin(CommonUtils.convertString(obj[10]));
					request.setBranchCode(CommonUtils.convertString(obj[11]));
					request.setBranchAddress(CommonUtils.convertString(obj[12]));
					request.setLocationData(locationCode);
					applicationRequests.add(request);
				}
				return applicationRequests;
			}
		}
		return Collections.emptyList();
	}
	
	/**
	 * author Harshit
	 * Rejection List By Organization Id
	 */
	@Override
	public List<OfflineProcessedApplicationRequest> getRejectProposalList(Long orgId, Long userId) {
		if(!CommonUtils.isObjectNullOrEmpty(orgId)) {
			List<Object []> lst = offlineProcessedAppRepository.getRejectProposalsList(orgId);
			if(!CommonUtils.isObjectListNull(lst)) {
				List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
				OfflineProcessedApplicationRequest request = null;
				Object locationCode = null;
				if(lst.size() > 0) {
					locationCode = getLocationCode(userId);
				}
				for(Object[] obj : lst) {
					request = new OfflineProcessedApplicationRequest();
					request.setApplicationId(CommonUtils.convertLong(obj[0]));
					request.setUserId(CommonUtils.convertLong(obj[1]));
					request.setLoanAmount(CommonUtils.convertDouble(obj[2]));
					request.setIsCampaignUser(!CommonUtils.isObjectNullOrEmpty(obj[3])?((String)obj[3]).toString() : "Market Place");
					request.setBranchName(CommonUtils.convertString(obj[4]));
					request.setOrganisationName(CommonUtils.convertString(obj[5]));
					request.setIsSanctioned(CommonUtils.convertBoolean(obj[6]));
					request.setIsDisbursed(CommonUtils.convertBoolean(obj[7]));
					request.setBranchId(CommonUtils.convertLong(obj[8]));
					request.setPan(CommonUtils.convertString(obj[9]));
					request.setGstin(CommonUtils.convertString(obj[10]));
					request.setBranchCode(CommonUtils.convertString(obj[11]));
					request.setBranchAddress(CommonUtils.convertString(obj[12]));
					request.setReason(CommonUtils.convertString(obj[13]));
					request.setModifiedDate(CommonUtils.convertDate(obj[14]));
					request.setLocationData(locationCode);
					applicationRequests.add(request);
				}
				return applicationRequests;
			}
		}
		return Collections.emptyList();
	}

	@Override
	public List<OfflineProcessedApplicationRequest> getSanctionedApplicationList(Long orgId, Long userId) {
		if(!CommonUtils.isObjectNullOrEmpty(orgId)) {
			List<Object []> lst = offlineProcessedAppRepository.getSanctionedApplicationList(orgId);
			if(!CommonUtils.isObjectListNull(lst)) {
				List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
				OfflineProcessedApplicationRequest request = null;
				Object locationCode = null;
				if(lst.size() > 0) {
					locationCode = getLocationCode(userId);
				}
				for(Object[] obj : lst) {
					request = new OfflineProcessedApplicationRequest();
					request.setSanctionedAmount(CommonUtils.convertDouble(obj[0]));
					request.setSanctionDate(CommonUtils.convertDate(obj[1]));
					request.setRemark(CommonUtils.convertString(obj[2]));
					request.setRoi(CommonUtils.convertDouble(obj[3]));
					request.setTenure(CommonUtils.convertDouble(obj[4]));
					request.setProcessingFee(CommonUtils.convertDouble(obj[5]));
					request.setApplicationId(CommonUtils.convertLong(obj[6]));
					request.setIsPartiallyDisbursedOffline(CommonUtils.convertBoolean(obj[7]));
					request.setOrganisationName(CommonUtils.convertString(obj[8]));
					request.setBranchId(CommonUtils.convertLong(obj[9]));
					try {
						Long value = CommonUtils.convertLong(obj[9]);
						if(!CommonUtils.isObjectNullOrEmpty(value)) {
							UserResponse resp = usersClient.getBranchNameById(value);
							if(!CommonUtils.isObjectNullOrEmpty(resp.getData())) {
								String branchName = resp.getData().toString();
								request.setBranchName(branchName);
							}
						}
					} catch (Exception e) {
						logger.info("Error while getting Branch name");
						e.printStackTrace();
					}
					request.setLocationData(locationCode);
					applicationRequests.add(request);
				}
				return applicationRequests;
			}
		}
		return Collections.emptyList();
	}

	@Override
	public List<OfflineProcessedApplicationRequest> getDisbursedApplicationList(Long orgId, Long userId) {
		if(!CommonUtils.isObjectNullOrEmpty(orgId)) {
			List<Object []> lst = offlineProcessedAppRepository.getDisbursedApplicationList(orgId);
			if(!CommonUtils.isObjectListNull(lst)) {
				List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
				OfflineProcessedApplicationRequest request = null;
				Object locationCode = null;
				if(lst.size() > 0) {
					locationCode = getLocationCode(userId);
				}
				for(Object[] obj : lst) {
					request = new OfflineProcessedApplicationRequest();
					request.setOrganisationName(CommonUtils.convertString(obj[3]));
					request.setApplicationId(CommonUtils.convertLong(obj[4]));
					request.setBranchId(CommonUtils.convertLong(obj[5]));
					try {
						Long branchId = CommonUtils.convertLong(obj[6]);
						if(!CommonUtils.isObjectNullOrEmpty(branchId)) {
							UserResponse resp = usersClient.getBranchNameById(branchId);
							if(!CommonUtils.isObjectNullOrEmpty(resp.getData())) {
								String branchName = resp.getData().toString();
								request.setBranchName(branchName);
							}
						}
					} catch (Exception e) {
						logger.info("Error while getting Branch name");
						e.printStackTrace();
					}
					request.setLocationData(locationCode);
					applicationRequests.add(request);
				}
				return applicationRequests;
			}
		}
		return Collections.emptyList();
	}
	
	private Object getLocationCode(Long userId) {
		if(CommonUtils.isObjectNullOrEmpty(userId)) {
			return null;
		}
		UsersRequest req = new UsersRequest();
		req.setId(userId);
		try {
			UserResponse resp = usersClient.getBranchDetailsBYUserId(req);
			if(!CommonUtils.isObjectNullOrEmpty(resp) && !CommonUtils.isObjectNullOrEmpty(resp.getData())) {
				return resp.getData();
			}
		} catch (Exception e) {
			logger.info("Error while getting Branch and location details from User Id");
			e.printStackTrace();
		}
		return null;
	}

}
