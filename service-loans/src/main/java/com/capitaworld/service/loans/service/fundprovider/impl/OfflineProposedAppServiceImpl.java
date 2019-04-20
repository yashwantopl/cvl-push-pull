package com.capitaworld.service.loans.service.fundprovider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private static final String MARKET_PLACE = "Market Place";
	private static final String BANK_SPECIFIC  = "Bank Specific";

	@Autowired
	private OfflineProcessedAppRepository offlineProcessedAppRepository;
	
	@Autowired
	private UsersClient usersClient; 
	
	/**
	 * FETCH ALL PENDING OFFLINE PROPOSALS BASED ON USERID
	 * RETURN DATA BASED ON ROLE ID
	 */
	@Override
	public String getApplicationList(Long userId) {
		return offlineProcessedAppRepository.getInEligibleRecordList(userId);
//		if(lst.isEmpty()) {
//			return Collections.emptyList();
//		}
//		List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
//		OfflineProcessedApplicationRequest request = null;
//		for(Object[] obj : lst) {
//			request = new OfflineProcessedApplicationRequest();
//			request.setApplicationId(CommonUtils.convertLong(obj[0]));
//			request.setOrganisationName(CommonUtils.convertString(obj[1]));
//			request.setPan(CommonUtils.convertString(obj[2]));
//			request.setGstin(CommonUtils.convertString(obj[3]));
//			request.setUserId(CommonUtils.convertLong(obj[4]));
//			request.setLoanAmount(CommonUtils.convertDouble(obj[5]));
//			request.setBranchName(CommonUtils.convertString(obj[6]));
//			request.setBranchCode(CommonUtils.convertString(obj[7]));
//			request.setBranchAddress(CommonUtils.convertString(obj[8]));
//			request.setCampaignCode(CommonUtils.convertString(obj[9]));
//			Integer campId = CommonUtils.convertInteger(obj[10]);
//			if(campId == 0) {
//				request.setIsCampaignUser(MARKET_PLACE);
//			} else {
//				request.setIsCampaignUser(BANK_SPECIFIC);
//			}
//			request.setBranchId(CommonUtils.convertLong(obj[11]));
//			request.setMobile(CommonUtils.convertString(obj[12]));
//			request.setEmail(CommonUtils.convertString(obj[13]));
//			request.setUserOrgId(CommonUtils.convertLong(obj[14]));
//			request.setProposalId(CommonUtils.convertLong(obj[15]));
//			applicationRequests.add(request);
//		}
//		return applicationRequests;
	}

	/**
	 * author Harshit
	 * Rejection List By Organization Id
	 */
	@Override
	public String getRejectProposalList(Long userId) {
		return offlineProcessedAppRepository.getRejectProposalsList(userId);
//		if(lst.isEmpty()) {
//			return Collections.emptyList();
//		}
//		List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
//		OfflineProcessedApplicationRequest request = null;
//		for(Object[] obj : lst) {
//			request = new OfflineProcessedApplicationRequest();
//			request.setApplicationId(CommonUtils.convertLong(obj[0]));
//			request.setOrganisationName(CommonUtils.convertString(obj[1]));
//			request.setPan(CommonUtils.convertString(obj[2]));
//			request.setGstin(CommonUtils.convertString(obj[3]));
//			request.setUserId(CommonUtils.convertLong(obj[4]));
//			request.setLoanAmount(CommonUtils.convertDouble(obj[5]));
//			request.setBranchName(CommonUtils.convertString(obj[6]));
//			request.setBranchCode(CommonUtils.convertString(obj[7]));
//			request.setBranchAddress(CommonUtils.convertString(obj[8]));
//			request.setReason(CommonUtils.convertString(obj[9]));
//			request.setModifiedDate(CommonUtils.convertDate(obj[10]));
//			request.setCampaignCode(CommonUtils.convertString(obj[11]));
//			Integer campId = CommonUtils.convertInteger(obj[12]);
//			if(campId == 0) {
//				request.setIsCampaignUser(MARKET_PLACE);
//			} else {
//				request.setIsCampaignUser(BANK_SPECIFIC);
//			}
//			request.setBranchId(CommonUtils.convertLong(obj[13]));
//			request.setMobile(CommonUtils.convertString(obj[14]));
//			request.setEmail(CommonUtils.convertString(obj[15]));
//			request.setProposalId(CommonUtils.convertLong(obj[16]));
//			applicationRequests.add(request);
//		}
//		return applicationRequests;
	}

	@Override
	public String getOtherProposalList(Long userId) {
		return offlineProcessedAppRepository.getOtherProposalsList(userId);
//		if(lst.isEmpty()) {
//			return Collections.emptyList();
//		}
//		List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
//		OfflineProcessedApplicationRequest request = null;
//		for(Object[] obj : lst) {
//			request = new OfflineProcessedApplicationRequest();
//			request.setApplicationId(CommonUtils.convertLong(obj[0]));
//			request.setOrganisationName(CommonUtils.convertString(obj[1]));
//			request.setPan(CommonUtils.convertString(obj[2]));
//			request.setGstin(CommonUtils.convertString(obj[3]));
//			request.setUserId(CommonUtils.convertLong(obj[4]));
//			request.setLoanAmount(CommonUtils.convertDouble(obj[5]));
//			request.setBranchName(CommonUtils.convertString(obj[6]));
//			request.setBranchCode(CommonUtils.convertString(obj[7]));
//			request.setBranchAddress(CommonUtils.convertString(obj[8]));
//			request.setModifiedDate(CommonUtils.convertDate(obj[9]));
//			request.setCampaignCode(CommonUtils.convertString(obj[10]));
//			Integer campId = CommonUtils.convertInteger(obj[11]);
//			if(campId == 0) {
//				request.setIsCampaignUser(MARKET_PLACE);
//			} else {
//				request.setIsCampaignUser(BANK_SPECIFIC);
//			}
//			request.setBranchId(CommonUtils.convertLong(obj[12]));
//			request.setStatus(CommonUtils.convertInteger(obj[13]));
//			request.setMobile(CommonUtils.convertString(obj[14]));
//			request.setEmail(CommonUtils.convertString(obj[15]));
//			applicationRequests.add(request);
//		}
//		return applicationRequests;
	}

	@Override
	public String getSanctionedApplicationList(Long userId) {
		return offlineProcessedAppRepository.getSanctionedApplicationList(userId);
//		if(lst.isEmpty()) {
//			return Collections.emptyList();
//		}
//		List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
//		OfflineProcessedApplicationRequest request = null;
//		for(Object[] obj : lst) {
//			request = new OfflineProcessedApplicationRequest();
//			request.setApplicationId(CommonUtils.convertLong(obj[0]));
//			request.setOrganisationName(CommonUtils.convertString(obj[1]));
//			request.setSanctionDate(CommonUtils.convertDate(obj[2]));
//			request.setSanctionedAmount(CommonUtils.convertDouble(obj[3]));
//			request.setTenure(CommonUtils.convertDouble(obj[4]));
//			request.setRoi(CommonUtils.convertDouble(obj[5]));
//			request.setProcessingFee(CommonUtils.convertDouble(obj[6]));
//			request.setRemark(CommonUtils.convertString(obj[7]));
//			request.setBranchName(CommonUtils.convertString(obj[8]));
//			request.setBranchCode(CommonUtils.convertString(obj[9]));
//			request.setBranchId(CommonUtils.convertLong(obj[10]));
//			request.setMobile(CommonUtils.convertString(obj[11]));
//			request.setEmail(CommonUtils.convertString(obj[12]));
//			applicationRequests.add(request);
//		}
//		return applicationRequests;
	}

	@Override
	public String getDisbursedApplicationList(Long userId) {
		return offlineProcessedAppRepository.getDisbursedApplicationList(userId);
//		if(lst.isEmpty()) {
//			return Collections.emptyList();
//		}
//		List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
//		OfflineProcessedApplicationRequest request = null;
//		for(Object[] obj : lst) {
//			request = new OfflineProcessedApplicationRequest();
//			request.setApplicationId(CommonUtils.convertLong(obj[0]));
//			request.setOrganisationName(CommonUtils.convertString(obj[1]));
//			request.setBranchName(CommonUtils.convertString(obj[2]));
//			request.setBranchCode(CommonUtils.convertString(obj[3]));
//			request.setBranchId(CommonUtils.convertLong(obj[4]));
//			request.setMobile(CommonUtils.convertString(obj[5]));
//			request.setEmail(CommonUtils.convertString(obj[6]));
//			applicationRequests.add(request);
//		}
//		return applicationRequests;
	}
	
	/**
	 * NOT USED AFTER CHANGE ALL QUERY BY HARSHIT
	 * @param userId
	 * @return
	 */
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
			logger.error("Error while getting Branch and location details from User Id : ",e);
		}
		return null;
	}

	@Override
	public String getUniformApplicationList(Long userId) {
		return offlineProcessedAppRepository.getUniformApplications(userId);
//		if(lst.isEmpty()) {
//			return Collections.emptyList();
//		}
//		List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
//		OfflineProcessedApplicationRequest request = null;
//		for(Object[] obj : lst) {
//			request = new OfflineProcessedApplicationRequest();
//			request.setApplicationId(CommonUtils.convertLong(obj[0]));
//			request.setOrganisationName(CommonUtils.convertString(obj[1]));
//			request.setPan(CommonUtils.convertString(obj[2]));
//			request.setGstin(CommonUtils.convertString(obj[3]));
//			request.setUserId(CommonUtils.convertLong(obj[4]));
//			request.setLoanAmount(CommonUtils.convertDouble(obj[5]));
//			request.setBranchName(CommonUtils.convertString(obj[6]));
//			request.setBranchCode(CommonUtils.convertString(obj[7]));
//			request.setBranchAddress(CommonUtils.convertString(obj[8]));
//			request.setCampaignCode(CommonUtils.convertString(obj[9]));
//			Integer campId = CommonUtils.convertInteger(obj[10]);
//			if(campId == 0) {
//				request.setIsCampaignUser(MARKET_PLACE);
//			} else {
//				request.setIsCampaignUser(BANK_SPECIFIC);
//			}
//			request.setBranchId(CommonUtils.convertLong(obj[11]));
//			request.setRoi(CommonUtils.convertDouble(obj[12]));
//			request.setProcessingFee(CommonUtils.convertDouble(obj[13]));
//			request.setFpProductId(CommonUtils.convertLong(obj[14]));
//			request.setMobile(CommonUtils.convertString(obj[15]));
//			request.setEmail(CommonUtils.convertString(obj[16]));
//			applicationRequests.add(request);
//		}
//		return applicationRequests;
	}

	@Override
	public String getUniformSanctionedApplicationList(Long userId) {
		return offlineProcessedAppRepository.getUniformSanctionedApplicationList(userId);
//		if(lst.isEmpty()) {
//			return Collections.emptyList();
//		}
//		List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
//		OfflineProcessedApplicationRequest request = null;
//		for(Object[] obj : lst) {
//			request = new OfflineProcessedApplicationRequest();
//			request.setApplicationId(CommonUtils.convertLong(obj[0]));
//			request.setOrganisationName(CommonUtils.convertString(obj[1]));
//			request.setSanctionDate(CommonUtils.convertDate(obj[2]));
//			request.setSanctionedAmount(CommonUtils.convertDouble(obj[3]));
//			request.setTenure(CommonUtils.convertDouble(obj[4]));
//			request.setRoi(CommonUtils.convertDouble(obj[5]));
//			request.setProcessingFee(CommonUtils.convertDouble(obj[6]));
//			request.setRemark(CommonUtils.convertString(obj[7]));
//			request.setBranchName(CommonUtils.convertString(obj[8]));
//			request.setBranchCode(CommonUtils.convertString(obj[9]));
//			request.setBranchId(CommonUtils.convertLong(obj[10]));
//			request.setFpProductId(CommonUtils.convertLong(obj[11]));
//			request.setProposalId(CommonUtils.convertLong(obj[12]));
//			request.setMobile(CommonUtils.convertString(obj[13]));
//			request.setEmail(CommonUtils.convertString(obj[14]));
//			applicationRequests.add(request);
//		}
//		return applicationRequests;
	}

	@Override
	public String getUniformDisbursedApplicationList(Long userId) {
		return offlineProcessedAppRepository.getUniformDisbursedApplicationList(userId);
//		if(lst.isEmpty()) {
//			return Collections.emptyList();
//		}
//		List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
//		OfflineProcessedApplicationRequest request = null;
//		for(Object[] obj : lst) {
//			request = new OfflineProcessedApplicationRequest();
//			request.setApplicationId(CommonUtils.convertLong(obj[0]));
//			request.setOrganisationName(CommonUtils.convertString(obj[1]));
//			request.setBranchName(CommonUtils.convertString(obj[2]));
//			request.setBranchCode(CommonUtils.convertString(obj[3]));
//			request.setBranchId(CommonUtils.convertLong(obj[4]));
//			request.setRoi(CommonUtils.convertDouble(obj[5]));
//			request.setProcessingFee(CommonUtils.convertDouble(obj[6]));
//			request.setFpProductId(CommonUtils.convertLong(obj[7]));
//			request.setDisbursedAmount(CommonUtils.convertDouble(obj[8]));
//			request.setTransactionNo(CommonUtils.convertString(obj[9]));
//			request.setAccountNo(CommonUtils.convertString(obj[10]));
//			request.setDisbursedDate(CommonUtils.convertDate(obj[11]));
//			request.setPaymentMode(CommonUtils.convertInteger(obj[12]));
//			request.setProposalId(CommonUtils.convertLong(obj[13]));
//			request.setMobile(CommonUtils.convertString(obj[14]));
//			request.setEmail(CommonUtils.convertString(obj[15]));
//			applicationRequests.add(request);
//		}
//		return applicationRequests;
	}

	@Override
	public String getUniformRejectProposalList(Long userId) {
		return offlineProcessedAppRepository.getUniformRejectProposalsList(userId);
//		if(lst.isEmpty()) {
//			return Collections.emptyList();
//		}
//		List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
//		OfflineProcessedApplicationRequest request = null;
//		for(Object[] obj : lst) {
//			request = new OfflineProcessedApplicationRequest();
//			request.setApplicationId(CommonUtils.convertLong(obj[0]));
//			request.setOrganisationName(CommonUtils.convertString(obj[1]));
//			request.setPan(CommonUtils.convertString(obj[2]));
//			request.setGstin(CommonUtils.convertString(obj[3]));
//			request.setUserId(CommonUtils.convertLong(obj[4]));
//			request.setLoanAmount(CommonUtils.convertDouble(obj[5]));
//			request.setBranchName(CommonUtils.convertString(obj[6]));
//			request.setBranchCode(CommonUtils.convertString(obj[7]));
//			request.setBranchAddress(CommonUtils.convertString(obj[8]));
//			request.setReason(CommonUtils.convertString(obj[9]));
//			request.setModifiedDate(CommonUtils.convertDate(obj[10]));
//			request.setCampaignCode(CommonUtils.convertString(obj[11]));
//			Integer campId = CommonUtils.convertInteger(obj[12]);
//			if(campId == 0) {
//				request.setIsCampaignUser(MARKET_PLACE);
//			} else {
//				request.setIsCampaignUser(BANK_SPECIFIC);
//			}
//			request.setBranchId(CommonUtils.convertLong(obj[13]));
//			request.setRoi(CommonUtils.convertDouble(obj[14]));
//			request.setProcessingFee(CommonUtils.convertDouble(obj[15]));
//			request.setFpProductId(CommonUtils.convertLong(obj[16]));
//			request.setMobile(CommonUtils.convertString(obj[17]));
//			request.setEmail(CommonUtils.convertString(obj[18]));
//			request.setProposalId(CommonUtils.convertLong(obj[19]));
//			applicationRequests.add(request);
//		}
//		return applicationRequests;
	}

	@Override
	public String getUniformOtherProposalList(Long userId) {
		return  offlineProcessedAppRepository.getUniformOtherProposalsList(userId);
//		if(lst.isEmpty()) {
//			return Collections.emptyList();
//		}
//		List<OfflineProcessedApplicationRequest> applicationRequests = new ArrayList<OfflineProcessedApplicationRequest>(lst.size());
//		OfflineProcessedApplicationRequest request = null;
//		for(Object[] obj : lst) {
//			request = new OfflineProcessedApplicationRequest();
//			request.setApplicationId(CommonUtils.convertLong(obj[0]));
//			request.setOrganisationName(CommonUtils.convertString(obj[1]));
//			request.setPan(CommonUtils.convertString(obj[2]));
//			request.setGstin(CommonUtils.convertString(obj[3]));
//			request.setUserId(CommonUtils.convertLong(obj[4]));
//			request.setLoanAmount(CommonUtils.convertDouble(obj[5]));
//			request.setBranchName(CommonUtils.convertString(obj[6]));
//			request.setBranchCode(CommonUtils.convertString(obj[7]));
//			request.setBranchAddress(CommonUtils.convertString(obj[8]));
//			request.setModifiedDate(CommonUtils.convertDate(obj[9]));
//			request.setCampaignCode(CommonUtils.convertString(obj[10]));
//			Integer campId = CommonUtils.convertInteger(obj[11]);
//			if(campId == 0) {
//				request.setIsCampaignUser(MARKET_PLACE);
//			} else {
//				request.setIsCampaignUser(BANK_SPECIFIC);
//			}
//			request.setBranchId(CommonUtils.convertLong(obj[12]));
//			request.setStatus(CommonUtils.convertInteger(obj[13]));
//			request.setRoi(CommonUtils.convertDouble(obj[14]));
//			request.setProcessingFee(CommonUtils.convertDouble(obj[15]));
//			request.setFpProductId(CommonUtils.convertLong(obj[16]));
//			request.setMobile(CommonUtils.convertString(obj[17]));
//			request.setEmail(CommonUtils.convertString(obj[18]));
//			applicationRequests.add(request);
//		}
//		return applicationRequests;
	}

}
