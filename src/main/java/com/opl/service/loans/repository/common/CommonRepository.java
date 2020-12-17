package com.opl.service.loans.repository.common;

import java.math.BigInteger;
import java.util.List;


public interface CommonRepository {

	public Object[] getUserCampainCodeByApplicationId(Long applicationId);
	
	public Object[] getEmailDataByApplicationId(Long applicationId);
	
	public String getCoApplicatantNameFromITR(Long coAppId);

	public List<Object[]>  getBranchUserDetailsBasedOnRoleId(Long orgId,Integer roleId);
	
	public Object[] getFpFullName(Long userId); 
	
	public Object getMakerDate(Long applicationId);
	
	public Integer getViewedTeaser(String emailId);
	
	public String getEmailIdFromUsers(Long userId);
	
	public Boolean checkUserWithBusinessTypeId(Long userId , Integer businessTypeId);
	
	public Object[] getEmailIdAndMobileForNBFCUser(Long userId);
	
	public String getNoteForHLCam(Long applicationId);
	
	public Object[] getInEligibleByApplicationId(Long applicationId);
	
	public List<Object[]> getBankDetails(Long applicationId, Long orgId);

	public Object[] getUserDetailsByApplicationId(Long applicationId) throws Exception;
	
	public List<String> getUserDetailsByUserOrgIdAndUserRoleIdAndBranchId(Long orgId ,Long roleId ,Long branchId);
	
	public Object getIsNBFCUser(Long applicationId);
	
	public Object[] fetchALDetailsOfManufacturerAssetsSupplier(Long manufacturerId , Long assetModelId, Integer supplierId) ;
	
	public BigInteger checkApplicationDisbursed(String pan);
	
	//Payment Common Properties
	
	public String getSidbiAmount();
	
	public String getGatewayProvider();
	
	public Object[] getLastCheckerNameByBranchId(Long branchId) throws Exception;
	
	public String getStateByStateCode(Long id);
	
	public Long getCountOfJobId(Long jobId , Long stepId , Long actionId);
	
	public Boolean checkUserForMudraLoanByUserId(Long userId);
	
	/** New payment Queries*/
	public Long getLoanType(Long applicationId) ;
	public Long getDelayTimeFromPaymentGateway();
	Object[] findCityStateCountryById(Long cityId, Long stateId, Integer countryId);
	public Integer setFailureReasonToPaymentsTable(String failureReason,Long applicationId,Boolean isActive) ;
	public Object[] findPaymentIdAndFailureResonByApplicationIdAndIsActive(Long applicationId,Boolean isActive);
	public Object[] findOnlinePaymentDetailByPaymentIAndIsActive(Long paymentId,Boolean isActive);
	public Integer inActivateOnlinePaymentDetail(Long opid,Boolean isActive);
	public Integer inActivatePaymentsTable(Long pid,Boolean isActive);
	public Integer getCountofOnlineNotSancAndDis(String  panNumber,Integer productId);
	public Object getSbiSpecific(Long applicationId);
	public Object getSidbiSpecific(Long applicationId);
	public Integer getNBFCUser(Long applicationId);
	public String getPaymentStatus(Long applicationId);
	public Integer updateConnectWithProposalId(Long applicationId,Long proposalId,Integer stageId,Integer previousStageId,Boolean nbfcFlow);
	public Object getInprincipleDate(Long applicationId);
	public List<Object[]> getUserListByUserOrgIdAndRoleIdAndBranchId(Long branchId,Long roleId,Long userOrgId);
	
	public void insertDateForProductEdit(Long fpUserId ,Long fpProductId,Integer type,Integer tabId);
	public void updateExisitngProductEditDownloadDate(Long fpUserId ,Long fpProductId,Integer type,Integer tabId);
	public Boolean checkExistingProductEditDownloadDate(Long fpUserId ,Long fpProductId,Integer type,Integer tabId);
	public String getLastDownloadDateForProductEdit(Long fpUserId ,Long fpProductId,Integer type,Integer tabId);

	public String getCamVersionForBSStandalone(String type);
	
	public String getGSTInFromConnectWithApplicationIdAndProposalId(Long applicationId);
	public List<Object[]> getStateAndCityNameById(Long stateId, Long cityId);
	
}
