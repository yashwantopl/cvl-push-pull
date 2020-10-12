package com.opl.service.loans.service.fundseeker.corporate.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.common.CommonUtils.PaymentTypeMaster;
import com.opl.mudra.api.common.MultipleJSONObjectHelper;
import com.opl.mudra.api.connect.ConnectResponse;
import com.opl.mudra.api.notification.exception.NotificationException;
import com.opl.mudra.api.notification.model.ContentAttachment;
import com.opl.mudra.api.notification.utils.NotificationAlias;
import com.opl.mudra.api.notification.utils.NotificationConstants;
import com.opl.mudra.api.notification.utils.NotificationMasterAlias;
import com.opl.mudra.api.payment.enums.BanksEnum;
import com.opl.mudra.api.payment.enums.ReportsEnum;
import com.opl.mudra.api.payment.exception.GatewayException;
import com.opl.mudra.api.payment.model.GatewayRequest;
import com.opl.mudra.api.payment.model.GatewayResponse;
import com.opl.mudra.api.reports.ReportRequest;
import com.opl.mudra.api.reports.utils.JasperReportEnum;
import com.opl.mudra.api.user.model.BranchBasicDetailsRequest;
import com.opl.mudra.api.user.model.UserOrganisationRequest;
import com.opl.mudra.api.user.model.UserResponse;
import com.opl.mudra.api.user.model.UsersRequest;
import com.opl.mudra.client.connect.ConnectClient;
import com.opl.mudra.client.notification.NotificationClient;
import com.opl.mudra.client.payment.GatewayClient;
import com.opl.mudra.client.reports.ReportsClient;
import com.opl.mudra.client.users.UsersClient;
import com.opl.service.loans.config.AsyncComponent;
import com.opl.service.loans.config.MailComponent;
import com.opl.service.loans.domain.fundprovider.ProposalDetails;
import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.opl.service.loans.repository.common.CommonRepository;
import com.opl.service.loans.repository.fundprovider.ProductMasterRepository;
import com.opl.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.opl.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.service.ProposalService;
import com.opl.service.loans.service.fundseeker.corporate.CamReportPdfDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.PaymentServiceLoans;


@Service
public class PaymentServiceImplLoans implements PaymentServiceLoans{
	
	private static final String INVOICE_DATA = "invoiceData";

	private static final String SKIP_PAYMENT = "skipPayment";

	private static final String SKIP_TYPE = "skipType";

	private static final String PAYMENT_TYPE_ID = "paymentTypeId";
	
	private static final String REPORT_URL = "/adv/reports/getReport";

	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImplLoans.class.getName());
	
	@Autowired
	LoanApplicationRepository loanApplicationMasterRepository;
	
	@Autowired
	ProposalDetailsRepository proposalDetailsRepository;
	
	@Autowired
	ConnectClient connectClient;
	
	@Autowired
	CommonRepository commonRepository;
	
	@Autowired
	ApplicationProposalMappingRepository applicationProposalMappingRepository;
	
	@Autowired
	CorporateApplicantDetailRepository corporateApplicantDetailRepository;
	
	@Autowired
	ProductMasterRepository fpProductMasterRep;
	
	@Autowired
	UsersClient userClient;
	
	@Autowired
	AsyncComponent asyncComp;
	
	@Autowired
	GatewayClient gatewayClient;
	
	@Autowired
	NotificationClient notificationClient;
	
	@Autowired
	ProposalService proposalService;
	
	@Value("${capitaworld.sidbi.mail.to.bcc.inprinciple}")
	private String[] bcc;
	
	@Autowired
	ReportsClient reportsClient;
	
	@Autowired
	MailComponent mailComponent;
	
	@Autowired
	CamReportPdfDetailsService camReportPdfDetailsService;
	
	@Value("${capitaworld.support.email}")
	private String supportEmail;
	
	@Value("${capitaworld.bccforcam}")
	private String[] bccForCam;

	@Value("${capitaworld.sidbi.mail.to.supplier}")
	private String supplierEmail;
	
	SimpleDateFormat sdfForInPrinciple = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public Map<String, Object> savePaymentDetail(GatewayRequest gatewayRequest) throws GatewayException {
		logger.info("============ Enter in savePaymentDetail() =============== applicationId ==>{} userId ==>{}",gatewayRequest.getApplicationId(),  gatewayRequest.getUserId());
		Map<String, Object> map = null;
		LoanApplicationMaster loanApplicationMaster = loanApplicationMasterRepository.findByIdAndIsActive(gatewayRequest.getApplicationId() , true );
		if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster)) {
			logger.info("Loan Master Detail is null or Empty of applicationid--------------------->{}" , gatewayRequest.getApplicationId());
			return null;
		}
		
		if(gatewayRequest.getBusinessTypeId() == null) {
			gatewayRequest.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
		}
		
		if(gatewayRequest != null && gatewayRequest.getBusinessTypeId() != null) {
			Integer businessTypeId = gatewayRequest.getBusinessTypeId();
			logger.info("Starting BusinessTypeId==>{}  of ApplicationId==>{} in savePaymentDetail..." , businessTypeId , gatewayRequest.getApplicationId());
		}
		
		if(gatewayRequest.getUserId() == null) {
			gatewayRequest.setUserId(loanApplicationMaster.getUserId());
		}
		
		try {
			ProposalDetails proposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(gatewayRequest.getApplicationId(), false);
			if(proposalDetails != null) {
				
				Long loanType = applicationProposalMappingRepository.getProductIdByProposalId(proposalDetails.getId());
				logger.info("Set LoanTypeId==> [{}]  ApplicationId==> [{}] with BusinessTypeId==> [{}]", loanType , gatewayRequest.getApplicationId() , gatewayRequest.getBusinessTypeId());
				gatewayRequest.setLoanTypeId(loanType != null ? loanType.intValue() : null);
				
				logger.info("Proposal Details for checking branchId==>{} and productId==>{}" ,proposalDetails.getBranchId() ,proposalDetails.getFpProductId());
				UserResponse branch = userClient.getBranchDataBasedOnOrgAndBranchId(proposalDetails.getUserOrgId(), proposalDetails.getBranchId());
				BranchBasicDetailsRequest branchMaster =null;
				for (Map<String, Object> obj :(List<Map<String, Object>>) branch.getListData()) {
					if(obj.get("isActive") != null && Boolean.valueOf(obj.get("isActive").toString())) {
					branchMaster = MultipleJSONObjectHelper.getObjectFromMap(obj,BranchBasicDetailsRequest.class);
					}
				}
				
				if(branchMaster != null && branchMaster.getId() != null) {
					logger.info("branchId is Active....BranchId===>{}",branchMaster.getId());
					List<Object[]> fpDetailList = new ArrayList<Object[]>();
					
					fpDetailList = fpProductMasterRep.getFpDetailAndisActive(proposalDetails.getFpProductId(), true );
					if(fpDetailList == null || fpDetailList.isEmpty()) {
						map = new HashMap<String, Object>();
						logger.info("Product is inActive...productId==>{}",proposalDetails.getFpProductId());	
						try {
//							if(loanApplicationMaster.getBusinessTypeId() != null) {
//								ConnectResponse connectResponse = connectClient.postPaymentToMatches(gatewayRequest.getApplicationId(), gatewayRequest.getUserId(), loanApplicationMaster.getBusinessTypeId());
//								if(connectResponse != null) {
									logger.info("User revert back to Matches Page while product is inActive of ApplicationId==>{}",gatewayRequest.getApplicationId());
									
									Integer[] status = new Integer[2];
									status[0] = proposalDetailsRepository.deleteByProposalId(proposalDetails.getId());
									try {
										status[1] = applicationProposalMappingRepository.deleteByProposalId(proposalDetails.getId());
									}catch (Exception e) {
										logger.error("Error/Exception occured while deleting ApplicationProposalMapping detail of proposalId ==>{} Error==>{}",proposalDetails.getId(),e);
									}
									
									logger.info("Delete ProposalDetails==>{} And ApplicationProposalMappingDetails==>{} of proposalId==>{}" , status[0] , status[1] , proposalDetails.getId());
									
									map.put(CommonUtils.PRODUCT_INACTIVE, "It seems your selected product is withdrawn by bank, please select product again");

//								}else {
//									logger.error("Error/Exception occured while reverting back to matches stage of applicationId==>{}",gatewayRequest.getApplicationId());
//									map.put(CommonUtils.PRODUCT_INACTIVE, "Please try Again");
//								}
								
//							}else {
								try {
									commonRepository.setFailureReasonToPaymentsTable("In LoanApplicationMaster Detail , BusinessTypeId is null while checking branch and product inactive", gatewayRequest.getApplicationId(), true);
								}catch (Exception e) {
									logger.error("Error/Exception while updating failure reason of LoanApplicationMaster Detail , BusinessTypeId null while checking branch and product inactive of applicationId==>{} ...Error==>{}",gatewayRequest.getApplicationId(),e);
								}
//							}
						}catch (Exception e) {
							logger.error("Error/Exception occured while changing stage to matches while product was InActive....Error==>{}",e);
							map.put(CommonUtils.PRODUCT_INACTIVE, "Please try Again");
							return map;
						}
						
					return map;
					}else {
						logger.info("fp productid is active....fpProductId==>{}",proposalDetails.getFpProductId());
					}
				}else {
					map = new HashMap<String, Object>();
					map.put("applicationId", gatewayRequest.getApplicationId());
					map.put("fpProductId", proposalDetails.getFpProductId());
					map.put("userId",  gatewayRequest.getUserId());
					map.put("elAmount", proposalDetails.getElAmount());
					map.put("elTenure", proposalDetails.getElTenure());
					map.put("elRoi", proposalDetails.getElRoi());
					map.put("emi", proposalDetails.getEmi());
					map.put("processingFee", proposalDetails.getProcessingFee());
					map.put("npOrgId", proposalDetails.getUserOrgId());
					map.put("orgId", proposalDetails.getUserOrgId());
					map.put("proposalId", proposalDetails.getId());
					map.put(CommonUtils.BRANCH_INACTIVE,"It seems your selected bank branch is not in operation, please select other nearby branch");
					return map;
				}
			}else{
				logger.error("Proposal Detail is already active or null while checking branchid and productId active of ApplicationId==>{}" ,gatewayRequest.getApplicationId());
			}
			
			gatewayRequest.setStatus(CommonUtils.PaymentStatus.PENDING);
			
			/** Check SameProductAndPan SkipType */ 
			if(proposalDetails != null && proposalDetails.getFpProductId() != null) {
				Boolean skipType = checkingPanAndLoanTypeForSkipPayment(gatewayRequest.getApplicationId(), proposalDetails.getFpProductId());
				if(skipType != null && skipType) {
					gatewayRequest.setSameProductIdSamePan(true);
				}
			}
			
			//Checking user that he is from any SkipType or not 
//			gatewayOnlineService.checkTypeOfSkipPayment(gatewayRequest);
//			if(gatewayRequest != null && gatewayRequest.getSkipPayment() != null && gatewayRequest.getSkipPayment()) {
//				logger.info("Change the Flow from MarketPlace to SkipPayment of ApplicationId==>{} By SkipType ==>{}", gatewayRequest.getApplicationId() ,gatewayRequest.getSkipType());
//				map = new HashMap<String, Object>();
//				gatewayRequest.setPurposeCode(null);
//				gatewayRequest.setProductInfo(gatewayRequest.getSkipType());
//				gatewayOnlineService.skipPaymentForWhiteLabel(gatewayRequest);
//				map.put(CommonUtils.PaymentStatus.BYPASS,"Congratulations!! Payment is not require for your proposal, please click on 'OK' and continue your journey. ");
//				return map;
//			}
			
			Object[] onlinePaymentDetailStatus = null;
			Object[] paymentIdAndFailurReason = commonRepository.findPaymentIdAndFailureResonByApplicationIdAndIsActive(gatewayRequest.getApplicationId(), true);
			
			/**
			 * onlinePaymentTransactionsAuditLog[0] = paymentId
			 * onlinePaymentTransactionsAuditLog[1] = FailurReason
			 * onlinePaymentTransactionsAuditLog[2] = createdDate
			 */
			
			
			if (paymentIdAndFailurReason != null && paymentIdAndFailurReason.length > 2) {
				onlinePaymentDetailStatus = commonRepository.findOnlinePaymentDetailByPaymentIAndIsActive(Long.valueOf(paymentIdAndFailurReason[0].toString()), true);
				/**
				 * onlinePaymentDetailStatus[0] = id
				 * onlinePaymentDetailStatus[1] = providerId
				 * onlinePaymentDetailStatus[2] = status
				 */
				if (onlinePaymentDetailStatus != null && onlinePaymentDetailStatus.length > 2  && CommonUtils.PaymentStatus.PENDING.equals(onlinePaymentDetailStatus[2].toString())
						|| CommonUtils.PaymentStatus.FAILED.equals(onlinePaymentDetailStatus[2].toString())) {
					
					//Get The Status of Previous Payment and updating its Status
					if(CommonUtils.GatewayProvider.AGGREPAY_PROVIDER.equals(Long.valueOf(onlinePaymentDetailStatus[1].toString())) 
							&& (CommonUtils.PaymentStatus.PENDING.equals(onlinePaymentDetailStatus[2].toString()) || (paymentIdAndFailurReason[1].toString().contains("1030")))) {
						
						String status = gatewayClient.callStatusApiAndUpdatePayment(gatewayRequest.getApplicationId()).toString();
						if(CommonUtils.PaymentStatus.SUCCESS.equals(status)) {
							map = new HashMap<String, Object>();
							map.put(CommonUtils.IS_PAYMENT_DONE, "Payment Already Succeeded");
							return map;	
						}else {
							Long delayTime = commonRepository.getDelayTimeFromPaymentGateway();

							logger.info("Fetching Delay Time to Hold The User On Payment page for AggrePay is==>{} minutes",delayTime);
							if(delayTime != null) {
								logger.info("Getting failure reason to check Transaction Incomplete status 1030 to check delay time of applicationId==>{} with Failure Reason==>{}"
									,gatewayRequest.getApplicationId() , paymentIdAndFailurReason != null ? paymentIdAndFailurReason[1].toString() : "AuditLog is null");
								if(paymentIdAndFailurReason[1].toString().contains("1030")) {
									SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss"); 
									Date newDate = new Date();
									Date oldDate = format.parse(paymentIdAndFailurReason[2].toString());
									
									long difference = newDate.getTime() - oldDate.getTime();
									long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
									long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);
									logger.info("Difference Between Old Transaction to New in minutes==>{} Or seconds is==>{}",minutes , seconds);
									
									if(delayTime >= minutes) {
										map = new HashMap<String, Object>();
										long diffSec = (delayTime*60)-seconds;
										long min = (diffSec % 3600)/60;
								        long sec = (diffSec % 3600)%60;
								      
										map.put(CommonUtils.DELAY_STATUS, "Please wait for " + 
												(min != 0 ? min + " minutes " : "") + (sec != 0 ? sec + " seconds" : "")
												+ " while we Process your Previous Payment");
										return map;	
									}
								}
							}
						}
					}
					
					logger.info("Inside make existing Online payment record with status Pending/Failed as inactive for applicationId:-{}", gatewayRequest.getApplicationId());
					
					int rowUpdate =  commonRepository.inActivateOnlinePaymentDetail(Long.valueOf(onlinePaymentDetailStatus[0].toString()), true );
					logger.info("-------------------------inActive updated in payment status  -------------- updated row=>{}" , rowUpdate);
					rowUpdate = commonRepository.inActivatePaymentsTable(Long.valueOf(paymentIdAndFailurReason[0].toString()), true);
					logger.info("-------------------------inActive updated in transaction audit log  -------------- updated row=>{} ", rowUpdate);
					
				}else if(CommonUtils.PaymentStatus.SUCCESS.equals(onlinePaymentDetailStatus[2].toString())) {
					logger.error("-------Payment is already updated as Success of ApplicationId----------{}",gatewayRequest.getApplicationId());
					try {
						commonRepository.setFailureReasonToPaymentsTable("Payment is already updated as Success from savePaymentDetail()", gatewayRequest.getApplicationId(), true);
					}catch (Exception e) {
						logger.error("Error/Exception while updating failure reason of payment status already success of applicationId==> Error==>{}",gatewayRequest.getApplicationId() ,e);
					}
					map = new HashMap<String, Object>();
					
					Boolean callConnect= false;
					
                    if(gatewayRequest.getBusinessTypeId() == null) {
                    	proposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(gatewayRequest.getApplicationId(), true );
                    	ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(proposalDetails.getId(), true);
                    	if(applicationProposalMapping != null) {
                    		gatewayRequest.setBusinessTypeId(applicationProposalMapping.getBusinessTypeId());
                    	}
                    }
                    
                    /** updating stage to inprinciple if payment is success */
//                    ConnectResponse connectResponse = connectClient.postPayment(gatewayRequest.getApplicationId(), gatewayRequest.getUserId() , gatewayRequest.getBusinessTypeId());
                    
//                    if (!CommonUtils.isObjectNullOrEmpty(connectResponse) && connectResponse.getProceed() != null && connectResponse.getProceed()){
//        				logger.info("connectResponse.getProceed()==============>>>{}" , connectResponse.getProceed());
//    					logger.info("loanApplicationMaster.getCompanyCinNumber()==============> {}", loanApplicationMaster.getCompanyCinNumber());
//    					if (loanApplicationMaster.getCompanyCinNumber() != null) {					
//    						asyncComp.callMCAForData(loanApplicationMaster.getCompanyCinNumber(),gatewayRequest.getApplicationId() , loanApplicationMaster.getUserId());
//    					}
//        				
//    					callConnect = true;
//        			} else {
//        				commonRepository.setFailureReasonToPaymentsTable("Issue in updating stage by connectPostPayment", gatewayRequest.getApplicationId(), true);
//        				logger.error("Connector Response null or empty");
//        			}
					logger.info("-------------------------call post payment and mca from connect client from savePaymentDetail-------------applicationId==>{}  status ==>{}",gatewayRequest.getApplicationId(),callConnect);
					
					map.put("isPaymentDone", "Payment Already Succeeded");
					return map;	
				}
			}
			
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findFirstByApplicationIdIdAndIsActiveOrderByIdAsc(gatewayRequest.getApplicationId() , true );
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
				
				if(gatewayRequest.getNameOfEntity() == null) {
					gatewayRequest.setNameOfEntity(corporateApplicantDetail.getOrganisationName());
				}
				
				String cityName = "NA";
				String stateName = "NA";
				String countryName = "NA";
				Long stateId = corporateApplicantDetail.getRegisteredStateId() != null ? corporateApplicantDetail.getRegisteredStateId().longValue() : null;
				Object[] obj = commonRepository.findCityStateCountryById(corporateApplicantDetail.getRegisteredCityId(), stateId, corporateApplicantDetail.getRegisteredCountryId());
				if(obj !=null ) {
					cityName = obj[0] != null ? String.valueOf(obj[0]) : cityName;
					stateName = obj[1] != null ? String.valueOf(obj[1]) : stateName;
					countryName = obj[2] != null ? String.valueOf(obj[2]) : countryName;
				}
				gatewayRequest.setCityName(cityName);
				gatewayRequest.setStateName(stateName);
				gatewayRequest.setCountryName(countryName);
				gatewayRequest.setPinCode(corporateApplicantDetail.getRegisteredPincode() != null ? corporateApplicantDetail.getRegisteredPincode().toString() : "NA");
				gatewayRequest.setGstIn(corporateApplicantDetail.getGstIn());
			}
			
			/*
			 * if (CommonUtils.PaymentMode.ONLINE.equalsIgnoreCase(gatewayRequest.
			 * getTypeOfPayment()) && gatewayRequest.getPurposeCode().equals("NHBS_FEES")) {
			 * 
			 * logger.info("Start when Payment Mode in ONLINE() in NHBS");
			 * gatewayRequest.setAmount(commonPropertiesRepository.getNhbsAmount() != null ?
			 * Double.valueOf(commonPropertiesRepository.getNhbsAmount()) :
			 * Double.valueOf(9999));
			 * 
			 * }else
			 */
//			if (CommonUtils.PaymentMode.ONLINE.equalsIgnoreCase(gatewayRequest.getTypeOfPayment()) && gatewayRequest.getPurposeCode().equals("SIDBI_FEES")){
				
				logger.info("Start when Payment Mode in ONLINE() in SIDBI");
				String sidbiAmount = commonRepository.getSidbiAmount();
				gatewayRequest.setAmount(sidbiAmount != null ? Double.valueOf(sidbiAmount) : Double.valueOf(1180));
				gatewayRequest.setPurposeCode("SIDBI_FEES");
//			}	
			
			try {
//				User user = userDetailRepository.findByIdAndIsActive(userId, true);
				UsersRequest req= MultipleJSONObjectHelper.getObjectFromMap((Map)  userClient.getEmailMobile(gatewayRequest.getUserId()).getData(), UsersRequest.class);
				
				if(req !=null ) {
					gatewayRequest.setEmail(req.getEmail());
					gatewayRequest.setPhone(req.getMobile());
				}
			}catch (Exception e) {
				logger.error("Error/Exception while getting the email and phone no MSG =>{} ",e);
			}
			
			loanApplicationMaster.setTypeOfPayment(gatewayRequest.getTypeOfPayment() != null ? gatewayRequest.getTypeOfPayment():"ONLINE");
			loanApplicationMaster.setPaymentAmount(gatewayRequest.getAmount());
			loanApplicationMasterRepository.save(loanApplicationMaster);
			
			/** creating txnId with pending status*/
			try{
//				map = gatewayOnlineService.savePaymentDetailWithPendingStatus(gatewayRequest);
				GatewayResponse resp = gatewayClient.savePaymentDetailWithPendingStatus(gatewayRequest);
				map = (Map) resp.getData();
				
				logger.info("End savePaymentDetail when Payment Mode in ONLINE() in NHBS or SIDBI");
			} catch (Exception e) {
				logger.error("Error while Saving Pending Status to Payment Module when Payment Mode is ONLINE ...Error==>{}",e);
				throw new GatewayException(CommonUtils.SOMETHING_WENT_WRONG);
			}

		} catch (Exception e) {
			logger.error("Error while Saving payment information in Loan ...Error==>{}",e);
			throw new GatewayException(CommonUtils.SOMETHING_WENT_WRONG);
		}
		return map ;
	}
	

	@Override
	public Boolean checkingPanAndLoanTypeForSkipPayment(Long applicationId , Long fpProductId) {
		logger.info("Inside Checking condition to check Pan And LoanType For SkipPayment of AppId==>{} with fpProductId==>{}",applicationId , fpProductId);
		Boolean skipPaymentForPan = false;
		ProposalDetails proposalDetails = null;
		Integer productId = null;
		
		if(fpProductId != null) {
			try {
				productId = fpProductMasterRep.getProductIdByFpProductId(fpProductId);
				logger.info("-------------Get productId from fp_product_master----------productId==>{}",productId );
			}catch(Exception e) {
				logger.error("Error/Exception while getting fp details directly by fpProductId ...Error==>{}",e);
			}
		}else {
			logger.info("fpProductId is null from frontend");
			proposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(applicationId, false);
			if(proposalDetails != null) {
				try {
					productId = fpProductMasterRep.getProductIdByFpProductId(proposalDetails.getFpProductId());
					logger.info("-------------Get productId from fp_product_master----------productId==>{}",productId );
				}catch(Exception e) {
					logger.error("Error/Exception while getting fp details ...Error==>{}",e);
				}
			}	
		}

		Integer count = 0;
		String newPanNo = corporateApplicantDetailRepository.getPanNoByApplicationId(applicationId);
		if(newPanNo != null && productId != null) {
			try {
				logger.info("Checking Pan Condition For Skip Payment of PanNo.==>{}",newPanNo);
				count = commonRepository.getCountofOnlineNotSancAndDis(newPanNo, productId);
			
				logger.info("Count of Online...Not Sanctioned and Distbursed-----------In Online==>{}",count);
				if(count > 0) {
					skipPaymentForPan = true;
				}
			}catch (Exception e) {
				logger.error("Error/Exception while running query of Not Sanctioned And Disbursed in Online...Error==>{}",e);
			}
		}else {
			logger.error("PanNo. is null or Cant be uncompressed of applicationId==>{}",applicationId);
		}
		return skipPaymentForPan;
	}
	
	@Override
	public Map<String, Object> checkTypeOfSkipPayment(GatewayRequest gatewayRequest) {
		Map<String, Object> response=new HashMap<>();
		if(gatewayRequest != null) {
			if(CommonUtils.isObjectNullOrEmpty(gatewayRequest.getLoanTypeId())) {
				gatewayRequest.setLoanTypeId(applicationProposalMappingRepository.getProductIdByApplicationId(gatewayRequest.getApplicationId()));
			}
			
			String skipPayment = null;
			response.put(SKIP_PAYMENT, false);
			response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.ONLINE_PAYMENT.getId());
			
			logger.info("Checking Under SkipType Method with BusinessTypeId==>{} and loanTypeId==>{} for ApplicationId==>{}",gatewayRequest.getBusinessTypeId() , gatewayRequest.getLoanTypeId() ,gatewayRequest.getApplicationId());
			//set SkipType for Uniform Product
			if(gatewayRequest.getBusinessTypeId() != null && CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId().equals(gatewayRequest.getBusinessTypeId())) {
				response.put(SKIP_PAYMENT, true);
				response.put(SKIP_TYPE, CommonUtils.SkipType.ONE_PAGER_ELIGIBILITY);
				response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.ONE_PAGER_SKIP_PAYMENT.getId());
				return response;
			}
			//set SkipType for Mudra Loan
			else if(gatewayRequest.getBusinessTypeId() != null && CommonUtils.BusinessType.MUDRA_LOAN.getId().equals(gatewayRequest.getBusinessTypeId())) {
				response.put(SKIP_PAYMENT, true);
				response.put(SKIP_TYPE, CommonUtils.SkipType.MUDRA_LOAN);
				response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.MUDRA_LOAN_SKIP_PAYMENT.getId());
				/** put this for future reference */
//				skipPayment = loanApplicationMasterRepository.isFromBankSpecificOrMarketPalce(gatewayRequest.getApplicationId(), true);
//				if(skipPayment != null) {
//					gatewayRequest.setAdditionalSkipType(CommonUtils.SkipType.MUDRA_LOAN_BANK_SPECIFIC);
//				}
				return response;
			}
			//set SkipType for DFS
			else if(gatewayRequest.getBusinessTypeId() != null && CommonUtils.BusinessType.DFS_LOAN.getId().equals(gatewayRequest.getBusinessTypeId())) {
				response.put(SKIP_PAYMENT, true);
				response.put(SKIP_TYPE, CommonUtils.SkipType.DFS_LOAN);
				response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.DFS_LOAN_SKIP_PAYMENT.getId());
				return response;
			}
			//set SkipType For VFS
			else if(gatewayRequest.getBusinessTypeId() != null && CommonUtils.BusinessType.VFS_LOAN.getId().equals(gatewayRequest.getBusinessTypeId())) {
				response.put(SKIP_PAYMENT, true);
				response.put(SKIP_TYPE, CommonUtils.SkipType.VFS_LOAN);
				response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.VFS_LOAN_SKIP_PAYMENT.getId());
				return response;
			}
			//set SkipType for ODOP
			else if(gatewayRequest.getBusinessTypeId() != null && CommonUtils.BusinessType.ODOP_LOAN.getId().equals(gatewayRequest.getBusinessTypeId())) {
				response.put(SKIP_PAYMENT, true);
				response.put(SKIP_TYPE, CommonUtils.SkipType.ODOP_LOAN);
				response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.ODOP_LOAN_SKIP_PAYMENT.getId());
				return response;
			}
			//set SkipType for Personal Loan
			else if(gatewayRequest.getLoanTypeId() != null && CommonUtils.LoanType.PERSONAL_LOAN.getValue() == gatewayRequest.getLoanTypeId()) {
				response.put(SKIP_PAYMENT, true);
				response.put(SKIP_TYPE, CommonUtils.SkipType.PERSONAL_LOAN);
				response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.PERSONAL_LOAN_SKIP_PAYMENT.getId());
				return response;
			}
			//set SkipType for Home Loan
			else if(gatewayRequest.getLoanTypeId() != null && CommonUtils.LoanType.HOME_LOAN.getValue() == gatewayRequest.getLoanTypeId()) {
				response.put(SKIP_PAYMENT, true);
				response.put(SKIP_TYPE, CommonUtils.SkipType.HOME_LOAN);
				response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.HOME_LOAN_SKIP_PAYMENT.getId());
				return response;
			}
			//set SkipType for Auto Loan
			else if(gatewayRequest.getLoanTypeId() != null && CommonUtils.LoanType.AUTO_LOAN.getValue() == gatewayRequest.getLoanTypeId()) {
				response.put(SKIP_PAYMENT, true);
				response.put(SKIP_TYPE, CommonUtils.SkipType.AUTO_LOAN);
				response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.AUTO_LOAN_SKIP_PAYMENT.getId());
				return response;
			}
			//set Skiptype for SameProductPan  need to check
//			else if(gatewayRequest.getSameProductIdSamePan() != null && gatewayRequest.getSameProductIdSamePan()){
//				gatewayRequest.setSkipPayment(true);
//				gatewayRequest.setSkipType(CommonUtils.SkipType.SAME_PAN_NO_AND_SAME_PRODUCT_ID);
//			}
			//set skipType for BankSpecific or SbiSpecific or SidbiSpecific
			else {
				/** banks specific check */
				skipPayment = loanApplicationMasterRepository.isFromBankSpecificOrMarketPalce(gatewayRequest.getApplicationId(), true);
				if(skipPayment != null) {
					response.put(SKIP_PAYMENT, true);
					response.put(SKIP_TYPE, CommonUtils.SkipType.BANK_SPECIFIC);
					response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.BANK_SPECIFIC_SKIP_PAYMENT.getId());
					
					if(gatewayRequest.getSpecificSkipType() != null && CommonUtils.SkipType.SBI_SPECIFIC.equals(gatewayRequest.getSpecificSkipType())) {
						response.put(SKIP_TYPE, CommonUtils.SkipType.SBI_SPECIFIC);
						response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.SBI_SPECIFIC_SKIP_PAYMENT.getId());
						return response;
						
					}else if(gatewayRequest.getSpecificSkipType() != null && CommonUtils.SkipType.SIDBI_SPECIFIC.equals(gatewayRequest.getSpecificSkipType())) {
						response.put(SKIP_TYPE, CommonUtils.SkipType.SIDBI_SPECIFIC);
						response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.SIDBI_SPECIFIC_SKIP_PAYMENT.getId());
						return response;
						
					}else {
						Integer sbiSpecific = ((BigInteger)commonRepository.getSbiSpecific(gatewayRequest.getApplicationId())).intValue();
						if(sbiSpecific > 0) {
							response.put(SKIP_TYPE, CommonUtils.SkipType.SBI_SPECIFIC);
							response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.SBI_SPECIFIC_SKIP_PAYMENT.getId());
							return response;
						}
						
						Integer sidbiSpecific = ((BigInteger)commonRepository.getSidbiSpecific(gatewayRequest.getApplicationId())).intValue();
						if(sidbiSpecific > 0) {
							response.put(SKIP_TYPE, CommonUtils.SkipType.SIDBI_SPECIFIC);
							response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.SIDBI_SPECIFIC_SKIP_PAYMENT.getId());
							return response;
						}
					}
					
				} else {
				
					/** Market place and check same pan and product*/
					Boolean checkPanAndSameProduct = checkingPanAndLoanTypeForSkipPayment(gatewayRequest.getApplicationId(), null);
					if(checkPanAndSameProduct) {
						response.put("additionalSkipType", CommonUtils.SkipType.SAME_PAN_NO_AND_SAME_PRODUCT_ID);
						response.put(SKIP_PAYMENT, true);
						response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.SAME_PAN_NO_AND_SAME_PRODUCT_ID_SKIP_PAYMENT.getId());
						return response;
					}
					
					/** Add here MULTIBANK condition*/
					// check if single payment done or not
					String paymentStatus = commonRepository.getPaymentStatus(gatewayRequest.getApplicationId()) ;
					Integer count = proposalDetailsRepository.getCountOfProposalDetailsByApplicationId(gatewayRequest.getApplicationId());
					if(count > 0 ) {
						response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.MULTIBANK_SKIP_PAYMENT.getId());
						response.put("additionalSkipType", CommonUtils.SkipType.MULTIBANK);
						response.put(SKIP_PAYMENT, true);
						return response;
					}else { 
						if(CommonUtils.PaymentStatus.SUCCESS.equals(paymentStatus)){
							/** check here is payment already success or not for same applicationId*/
							response.put(PAYMENT_TYPE_ID, CommonUtils.PaymentTypeMaster.ONLINE_PAYMENT.getId());
							response.put(SKIP_PAYMENT, true);
							return response;
						}
					}
				}
				
			}
			logger.info("CheckType of Skip Payment==>{} with Additional Skip Type==>{}", gatewayRequest.getSkipType() ,gatewayRequest.getAdditionalSkipType()); 
		}
		return response;
	}


	/**
	 *  Use for Al , HL , PL
	 *  In MSME  use for Bank specific , Same product and pan , Mudra loan only.
	 *  
	 *  if skip payment is false then check if payment is done or not
	 */
	@Override
	public String skipPayment(GatewayRequest gatewayRequest) {
		String status = "";
		Integer businessTypeId = null;
		
		LoanApplicationMaster loanApplicationMaster=loanApplicationMasterRepository.findByIdAndIsActive(gatewayRequest.getApplicationId(), true);
		
		if(loanApplicationMaster != null && loanApplicationMaster.getBusinessTypeId() != null) {
			gatewayRequest.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
			businessTypeId = loanApplicationMaster.getBusinessTypeId();
		}
		
		Long loanType = commonRepository.getLoanType(gatewayRequest.getApplicationId());
		
		if(loanType  != null) {
			logger.info("Set LoanTypeId==>{} from Connect of ApplicationId==>{} with BusinessTypeId==>{}", loanType , gatewayRequest.getApplicationId() , gatewayRequest.getBusinessTypeId());
			gatewayRequest.setLoanTypeId(loanType != null ? loanType.intValue() : null);
		}else {
			gatewayRequest.setLoanTypeId(applicationProposalMappingRepository.getProductIdByApplicationId(gatewayRequest.getApplicationId(), loanApplicationMaster.getUserId()));
			logger.info("Set LoanTypeId==>{} from Connect of ApplicationId==>{} with BusinessTypeId==>{}", loanType , gatewayRequest.getApplicationId() , gatewayRequest.getBusinessTypeId());
		}
		
//		if(gatewayRequest != null && gatewayRequest.getBusinessTypeId() != null) {
//			businessTypeId = gatewayRequest.getBusinessTypeId();
//		}

		if(gatewayRequest.getUserId() == null && loanApplicationMaster != null && loanApplicationMaster.getUserId() != null) {
			gatewayRequest.setUserId(loanApplicationMaster.getUserId());
		}
		
		logger.info("Checking user for skip payment of White Label of ApplicationId==>{} and BusinessTypeId==>{} with LoanTypeId==>{}" , gatewayRequest.getApplicationId() , businessTypeId , gatewayRequest.getLoanTypeId());
			
		String paymentStatus = CommonUtils.PaymentStatus.PENDING;
		
		if(!gatewayRequest.getSkipPayment() && gatewayRequest.getPaymentTypeId() == CommonUtils.PaymentTypeMaster.ONLINE_PAYMENT.getId()) { 
			paymentStatus = commonRepository.getPaymentStatus(gatewayRequest.getApplicationId()) ;
		}
		
		// put here condition if skip payment false and payment type id is online payment
		if(gatewayRequest.getSkipPayment()) {
			paymentStatus = CommonUtils.PaymentStatus.SUCCESS ;
		}
		
		
//		if (/* gatewayRequest.getSkipPayment() == null || */ !(gatewayRequest.getSkipPayment()) && (gatewayRequest.getSameProductIdSamePan() != null && !gatewayRequest.getSameProductIdSamePan())) {
//			OnlinePaymentTransactionsAuditLog onlinePaymentTransactionsAuditLog=onlinePaymentTransactionAuditLogRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(gatewayRequest.getApplicationId(), true);
//			if(onlinePaymentTransactionsAuditLog != null) {
//				OnlinePaymentDetailStatus onlinePaymentDetailStatus= onlinePaymentDetailStatusRepository.findFirstByPaymentIdIdAndIsActiveOrderByIdDesc(onlinePaymentTransactionsAuditLog.getId(), true);
//				if(onlinePaymentDetailStatus != null && (CommonUtils.PaymentStatus.SUCCESS.equals(onlinePaymentDetailStatus.getStatus())
//						|| CommonUtils.PaymentStatus.BYPASS.equals(onlinePaymentDetailStatus.getStatus()))){
//					paymentStatus = CommonUtils.PaymentStatus.SUCCESS;
//					checkTypeOfSkipPayment(gatewayRequest);
//					gatewayRequest.setAdditionalSkipType(CommonUtils.SkipType.MULTIBANK);
//					try {
//						logger.info("Set Multibank Status of OnlinePaymentId==>{} and AppId==>{}" , onlinePaymentDetailStatus.getId() , gatewayRequest.getApplicationId());
//						String previousResponse = onlinePaymentDetailStatus != null ? onlinePaymentDetailStatus.getResponseParam() : null;
//						onlinePaymentDetailStatusRepository.setMultibankStatus(true, onlinePaymentTransactionsAuditLog.getId() , 
//								!CommonUtils.isObjectNullOrEmpty(previousResponse) ? previousResponse + " ,[Forwarded as MultiBank At DateTime==>" + sdf.format(new Date()) + "]" : "[Forwarded as MultiBank At DateTime==>" + sdf.format(new Date()) + "]");
//					}catch (Exception e) {
//						logger.error("Error/Exception while set multibank Status in Online Payment Table...Error==>{}" , e);
//					}
//				}
//			}
//		} 
		// =================================================================================    
			
 		Map<String, Object> mailParameter = null ;
		int rowUpdate=0;
			
		if(paymentStatus != null && (CommonUtils.PaymentStatus.SUCCESS.equals(paymentStatus))) {
			logger.info("Inside Skip Payment of applicationId==>{} with SkipType==>{}", gatewayRequest.getApplicationId(), gatewayRequest.getSkipType());
			gatewayRequest.setStatus(paymentStatus);
			String cases = null;
//			gatewayRequest.setStatus(CommonUtils.PaymentStatus.BYPASS);
			if(gatewayRequest.getPaymentTypeId() == CommonUtils.PaymentTypeMaster.MULTIBANK_SKIP_PAYMENT.getId()) {
				cases = CommonUtils.SkipType.MULTIBANK;
			}else {
				cases = gatewayRequest.getSkipType();
			}
			
			logger.info("----------FS Comes from {}----------of ApplicationId==>{} with Business Type Id==>{}",cases ,gatewayRequest.getApplicationId(),businessTypeId);
				
			status = CommonUtils.PaymentStatus.SUCCESS;
				
			//update status in loan application master
//			rowUpdate = loanApplicationMasterRepository.updatePaymentStatus(gatewayRequest.getStatus(), gatewayRequest.getApplicationId(), true) ;
//			logger.info("------------update succesfully payment status in loan master ----------------  on applicationId =>{} updated row => {}" , gatewayRequest.getApplicationId() , rowUpdate);	
//			
//			if(CommonUtils.PaymentStatus.SUCCESS.equals(paymentStatus)){
//				logger.info("Payment Status was Successfully forwarded as multibank of ApplicationId==>{} ", gatewayRequest.getApplicationId());
//			}else {
//				savePaymentDetailWithPendingStatus(gatewayRequest);
//			}
			
//			if(CommonUtils.SkipType.NBFC.equals(gatewayRequest.getSkipType())){
//				nbfcFlow(gatewayRequest);
//			}
//			else {
			
				ProposalDetails proposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(gatewayRequest.getApplicationId(), false);
				if(proposalDetails != null ) {
					
					//active proposal details - 1
					rowUpdate = activeProposalDetail(gatewayRequest.getApplicationId(), proposalDetails.getFpProductId(),proposalDetails.getId(),gatewayRequest.getBusinessTypeId(),null,gatewayRequest.getPaymentTypeId());
					logger.info("Set Proposal Detail Active Status==>{} of ApplicationId==>{} and fpProductId==>{} ",rowUpdate ,gatewayRequest.getApplicationId(), proposalDetails.getFpProductId());
				
				}else {
					proposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(gatewayRequest.getApplicationId(), true);
				}
				
				if(proposalDetails != null ) {
					
					// think this step -- check if its inprinciple sent or not? // need to discuss
					
					ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(proposalDetails.getId(), true);
		            
					if(gatewayRequest.getLoanTypeId() == null && applicationProposalMapping != null) {
						gatewayRequest.setLoanTypeId(applicationProposalMapping.getProductId());
					}
					
					
					try {
//						Boolean connectPost = false;
//						connectPost = connectPostPayment(gatewayRequest); //
						logger.info("loanApplicationMaster.getCompanyCinNumber()==============> {}", loanApplicationMaster.getCompanyCinNumber());
						if (loanApplicationMaster.getCompanyCinNumber() != null) {					
							asyncComp.callMCAForData(loanApplicationMaster.getCompanyCinNumber(),gatewayRequest.getApplicationId() , loanApplicationMaster.getUserId());
						}
//						logger.info("Call Connect Post Payment in case of Skip Payment of applicationId==>{} Status ==>{}",gatewayRequest.getApplicationId() ,connectPost);
					}catch (Exception e) {
						logger.error("Error/Exception while changing stage on call post payment of application id==>{} ...Error=>{}",gatewayRequest.getApplicationId(),e);
					}
					
					//get Inprinciple Details - 2
					try {
						mailParameter = getInPrincipleDetail(gatewayRequest.getApplicationId() , proposalDetails.getId(),gatewayRequest.getSkipType());	
					} catch (Exception e) {
						logger.error("Error/Exception while getting inprinciple Details in skip Payment==>{} Error==>{}",gatewayRequest.getApplicationId(),e);
					}
					
					try {
						sendInprincipleLetterAndSmsToFS(mailParameter, gatewayRequest, proposalDetails.getId());
					} catch (NotificationException e1) {
						logger.error("Error/Exception while Sending Inprinciple And Send sms And System Notification in skip Payment for ApplicationId==>{} Error==>{}",gatewayRequest.getApplicationId(),e1);
					}
					
					
					
					//send mail to fp
					try {
						sendmailToAllFundProvider(mailParameter,applicationProposalMapping, gatewayRequest);
						logger.info("Sended skip payment Mail to fp of inprinciple for ApplicationId==>{}",gatewayRequest.getApplicationId());
					}catch (Exception e) {
						logger.error("--------------Error/Exception in sending skip payment mail to fund provider-----------Error==>{}",e);
					}
					
					

				}else {
					logger.error("--------------------Proposal detail is null in skipPaymentForWhiteLabel---------------");
				}
					
//			}
		} else {
			// need to discuss this
			status=CommonUtils.PaymentStatus.FAILED;
			
//			try {
//				Boolean connectMatches=false;
//				if(!gatewayRequest.getSkipPayment() && gatewayRequest.getPaymentTypeId() == CommonUtils.PaymentTypeMaster.ONLINE_PAYMENT.getId()) {
//					connectMatches = connectPostMatches(gatewayRequest);
//				}
				
//				logger.info("Call Connect Post Payment in case of Skip Payment of applicationId==>{} Status ==>{}",gatewayRequest.getApplicationId() ,connectMatches);
//			}catch (Exception e) {
//				logger.error("Error/Exception while changing stage on call post matches of application id==>{}  ...Error==>{}",gatewayRequest.getApplicationId() ,e);
//			}
			
			logger.error("User is not from Any of the Skip Type of ApplicationId==>{}.. So he is Considered as Market Place User and forwarded to Payment Page",gatewayRequest.getApplicationId());
		}
		return status;
	}
	
	// activate proposal mapping
	public Integer activeProposalDetail(Long applicationId, Long fpProductId,Long proposalId,Integer businessTypeId,Boolean nbfcFlow,Integer paymentTypeId){
		int rowUpdate=0;
		try {
			rowUpdate = proposalDetailsRepository.activeProposalDetailStatusByAppIdAndFpProductId(true,applicationId,fpProductId,paymentTypeId.longValue());
			logger.info("------------set proposal detail status as active -------------  on applicationId =>{} and fpProductId==>{} updated row ===>{}" , applicationId ,fpProductId , rowUpdate);
			// need to understand this
//			updateConnectWithProposalIdOnProposalActive(applicationId,proposalId,businessTypeId,nbfcFlow);
		}catch (Exception e) {
			logger.error("Error/Exception while set proposal status as active of applicationId ==>{} and fpProductId==>{} ...Error==>{}" ,applicationId ,fpProductId , e);
		}
		return rowUpdate;
	}

	// not needed
	public Boolean connectPostPayment(GatewayRequest gatewayRequest) throws GatewayException {
		try {
			Integer businessTypeId = gatewayRequest.getBusinessTypeId();
			 if(businessTypeId != null && (CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId().equals(businessTypeId) || CommonUtils.BusinessType.RETAIL_AUTO_LOAN.getId().equals(businessTypeId))
					 || CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId().equals(businessTypeId)) {
 				businessTypeId = CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId();
 			}
			
			ConnectResponse connectResponse = null;
			
//			if(CommonUtils.BusinessType.MUDRA_LOAN.getId().equals(businessTypeId) || CommonUtils.BusinessType.DFS_LOAN.getId().equals(businessTypeId) || CommonUtils.BusinessType.ODOP_LOAN.getId().equals(businessTypeId) || CommonUtils.BusinessType.VFS_LOAN.getId().equals(businessTypeId)) {
//				connectResponse = restCallsManagementService.connectPostPaymentWithRestApi(gatewayRequest , businessTypeId);
//			}else {
//				connectResponse = connectClient.postPayment(gatewayRequest.getApplicationId(), gatewayRequest.getUserId() , businessTypeId);
//			}
			
			if (!CommonUtils.isObjectNullOrEmpty(connectResponse)){
				logger.info("connectResponse.getProceed()==============>>>{}" , connectResponse.getProceed());
				
				LoanApplicationMaster loanApplicationMaster=loanApplicationMasterRepository.findByIdAndIsActive(gatewayRequest.getApplicationId(), true);
				if (connectResponse.getProceed() != null && connectResponse.getProceed()){
					logger.info("loanApplicationMaster.getCompanyCinNumber()==============> {}", loanApplicationMaster.getCompanyCinNumber());
					if (loanApplicationMaster.getCompanyCinNumber() != null) {					
						asyncComp.callMCAForData(loanApplicationMaster.getCompanyCinNumber(),gatewayRequest.getApplicationId() , loanApplicationMaster.getUserId());
					}
				}
				
              return true;
			} else {
				logger.error("Issue in updating stage by connectPostPayment for applicationId : {} ",gatewayRequest.getApplicationId());
			}
		}catch (Exception e) {
			logger.error(" connect service not available Error MSG==>{} ",e);
		}
		return false;
	}
	
	// not needed
	public Boolean connectPostMatches(GatewayRequest gatewayRequest) throws GatewayException {
		try {
			Integer businessTypeId = gatewayRequest.getBusinessTypeId();
			 if(businessTypeId != null && (CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId().equals(businessTypeId) || CommonUtils.BusinessType.RETAIL_AUTO_LOAN.getId().equals(businessTypeId))
					 || CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId().equals(businessTypeId)) {
 				businessTypeId = CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId();
 			}
			
			ConnectResponse connectResponse = null;
			
//			if(CommonUtils.BusinessType.MUDRA_LOAN.getId().equals(businessTypeId) || CommonUtils.BusinessType.DFS_LOAN.getId().equals(businessTypeId) || CommonUtils.BusinessType.ODOP_LOAN.getId().equals(businessTypeId) || CommonUtils.BusinessType.VFS_LOAN.getId().equals(businessTypeId)) {
//				connectResponse = restCallsManagementService.connectPostMatchesWithRestApi(gatewayRequest , businessTypeId);
//			}else {
//				connectResponse = connectClient.postMatches(gatewayRequest.getApplicationId(), gatewayRequest.getUserId() , businessTypeId);
//			}

			if (!CommonUtils.isObjectListNull(connectResponse)) {
				if (connectResponse.getProceed() != null && connectResponse.getProceed()) {
					logger.info("Proceed to Payment Page of ApplicationId==>{} with ConnectResponse==>{}",gatewayRequest.getApplicationId() ,connectResponse);
					return true;
				}
				return true;
			} else {
//				onlinePaymentTransactionAuditLogRepository.setFailureReason("Issue in updating stage by connectPostMatches", gatewayRequest.getApplicationId(), true);
				logger.error("Connector Response null or empty");
				return false;
			}
		}catch (Exception e) {
			logger.error(" connect service not available Error MSG==> {}",e);
			return false;
		}
	}
// TODO need to discuss --Akash and kushal 
	public Boolean updateConnectWithProposalIdOnProposalActive(Long applicationId,Long proposalId,Integer businessTypeId,Boolean nbfcFlow){
		try {
			if(businessTypeId != null && (CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId().equals(businessTypeId) || CommonUtils.BusinessType.RETAIL_AUTO_LOAN.getId().equals(businessTypeId))
					 || CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId().equals(businessTypeId)) {
				businessTypeId = CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId();
			}
			
			if(!CommonUtils.isObjectListNull(businessTypeId)){
				Integer stageId = 0;
				Integer previousStageId = 0;
				if(CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(businessTypeId)){
					stageId = 4;
					previousStageId = 6;
				}else if(CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId().equals(businessTypeId)){
					stageId = 207;
					previousStageId = 209;
				}else if(CommonUtils.BusinessType.MUDRA_LOAN.getId().equals(businessTypeId)) {
					stageId = 1005;
					previousStageId = 1007;
				}else if(CommonUtils.BusinessType.ODOP_LOAN.getId().equals(businessTypeId)) {
					stageId = 2204;
					previousStageId = 2206;
				}else if(CommonUtils.BusinessType.DFS_LOAN.getId().equals(businessTypeId)) {
					stageId = 3005;
					previousStageId = 3007;
				}
				return commonRepository.updateConnectWithProposalId(applicationId,proposalId,stageId,previousStageId,nbfcFlow) > 0;
			}
		}catch(Exception e){
			logger.error("Error while updating connect table with proposal_id  --> updatedConnectWithProposalIdOnProposalActive() ...with Error==>{}" ,e);
		}
		return false;
	}
	
	
	@Override
	public Map<String , Object> getInPrincipleDetail(Long applicationId , Long proposalId ,String skipType){
		Map<String , Object > inPrincipleDetailMap = null ; 
		List<String> errorMessage=new ArrayList<>();
		try {
			logger.info("---------------------getInPrincipleDetail for ApplicationId==>{}",applicationId);
			LoanApplicationMaster loanApplicationMaster = loanApplicationMasterRepository.findByIdAndIsActive(applicationId, true );
			if(loanApplicationMaster != null ) {
				GatewayRequest gatewayRequest = new GatewayRequest();
				gatewayRequest.setApplicationId(applicationId);
				gatewayRequest.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
				gatewayRequest.setUserId(loanApplicationMaster.getUserId());
				gatewayRequest.setSkipType(skipType);
				gatewayRequest.setLoanTypeId(loanApplicationMaster.getProductId());
				
				if(gatewayRequest.getLoanTypeId() == null) {
					Long loanType = commonRepository.getLoanType(gatewayRequest.getApplicationId());
					if(loanType == null) {
						gatewayRequest.setLoanTypeId(applicationProposalMappingRepository.getProductIdByApplicationId(gatewayRequest.getApplicationId(), loanApplicationMaster.getUserId()));
					}else {
						gatewayRequest.setLoanTypeId(loanType != null && gatewayRequest.getLoanTypeId() == null ? loanType.intValue() : null);
					}
				}
				
				logger.info("In GetInprinciple set loanType==>{} of ApplicationId==>{} with BusinessTypeId==>{}", gatewayRequest.getLoanTypeId() , gatewayRequest.getApplicationId() , gatewayRequest.getBusinessTypeId());
				
				inPrincipleDetailMap = new HashMap<String ,Object>() ;
				ProposalDetails proposalDetails = null;
				if(proposalId != null) {
					proposalDetails = proposalDetailsRepository.findByProposalIdAndApplicationIdAndIsActiveTrue(proposalId, applicationId);
				}else {
					proposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(applicationId, true );
				}
				
				if(proposalDetails !=null ) {
					inPrincipleDetailMap.put("proposalId", proposalDetails.getId());
					Integer productId = 0;
					String fpName = null ;
					String fpProductName = null;
					Long orgId = null ;
					
					try {
						List<Object[]> fpDetailList = fpProductMasterRep.getFpDetailAndisActive(proposalDetails.getFpProductId(), true);
	
						//------------------ set fp Detail ----------------------
						if(fpDetailList !=null && !fpDetailList.isEmpty() && fpDetailList.get(0) !=null ) {
							fpName = String.valueOf(fpDetailList.get(0)[0]);
							fpProductName = String.valueOf(fpDetailList.get(0)[1]);
						
							//set product id from master 
							productId = Integer.valueOf(String.valueOf(fpDetailList.get(0)[3]));
							orgId = Long.valueOf(String.valueOf(fpDetailList.get(0)[2]));
							logger.info("-------------Get Fp Product Details Successfully----------FpName==>{} fpProductName==>{} productId==>{} OrgId==>{}",fpName ,fpProductName ,productId ,orgId);
						}else {
							errorMessage.add("FpProductId is null or Inactive.");
						}
					}catch(IndexOutOfBoundsException e) {
						logger.error("Error/Exception while getting fp details ...Error==>{}",e);
						errorMessage.add("Exception in getting FpProductId");
					}
					
					if(gatewayRequest.getSkipType() == null) {
						checkTypeOfSkipPayment(gatewayRequest);
						if(gatewayRequest.getSkipType() != null) {
							inPrincipleDetailMap.put(CommonUtils.SKIP_TYPE, gatewayRequest.getSkipType() != null ? gatewayRequest.getSkipType().trim() : null);
						}
					}
					
					inPrincipleDetailMap.put(CommonUtils.USER_ID, loanApplicationMaster.getUserId());
					
					try {
						String logoUrl = null;
						if(CommonUtils.SkipType.ODOP_LOAN.equals(gatewayRequest.getSkipType())){
							logoUrl = BanksEnum.getBankUrl(10L);
						}else {
							logoUrl = notificationClient.getLogoUrl();
						}
						inPrincipleDetailMap.put("emailLogoUrl", logoUrl);
					} catch (NotificationException e2) {
						logger.info("Exception in getting host image:{}",e2);
					}
					
					if(orgId == null && proposalDetails.getUserOrgId() != null ) {
						orgId = proposalDetails.getUserOrgId() ;  
						logger.info("----------------OrgId got from ProposalDetails-----------------OrgId==>{}",orgId);
					}
					
					ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(proposalDetails.getId(), true);
					if(applicationProposalMapping == null) {
						errorMessage.add("Actual ProposalId ==>"+ proposalDetails.getId() +" is Different or null in ApplicationProposalMapping.");
					}
					
					inPrincipleDetailMap.put("fp_name", fpName);
					inPrincipleDetailMap.put("fp_product_name", fpProductName != null ? fpProductName:" ");
					inPrincipleDetailMap.put(CommonUtils.USER_ID , applicationProposalMapping.getUserId());
					
					inPrincipleDetailMap.put(CommonUtils.APPLICATION_IID, applicationProposalMapping.getApplicationCode()!=null?applicationProposalMapping.getApplicationCode():"-");
					inPrincipleDetailMap.put("app_code", applicationProposalMapping.getApplicationCode()!=null?applicationProposalMapping.getApplicationCode():"-");
					inPrincipleDetailMap.put("noCode", false);
					
					if(gatewayRequest.getAdditionalSkipType() != null && CommonUtils.SkipType.MULTIBANK.equals(gatewayRequest.getAdditionalSkipType())) {
						logger.info("-------------------Current Date---------------------");
						inPrincipleDetailMap.put("date",sdfForInPrinciple.format(new Date()));
					}else {
						Object getDate=commonRepository.getInprincipleDate(applicationId);
						if(getDate != null) {
							logger.info("-------------------InPrinciple Date---------------------");
							inPrincipleDetailMap.put("date",sdfForInPrinciple.format(getDate));
						}else {
							logger.info("-------------------Current Date---------------------");
							inPrincipleDetailMap.put("date",sdfForInPrinciple.format(new Date()));
						}
					}
					
					//----------set countOfInprinciple-----for note in inprinciple
					Boolean countofInprinciple = false;
					if(!CommonUtils.SkipType.BANK_SPECIFIC.equals(gatewayRequest.getSkipType()) && !CommonUtils.SkipType.SBI_SPECIFIC.equals(gatewayRequest.getSkipType()) 
							&& !CommonUtils.SkipType.SIDBI_SPECIFIC.equals(gatewayRequest.getSkipType()) && gatewayRequest.getAdditionalSkipType() == null) {
						Integer getCountOfInprinciple = proposalDetailsRepository.getCountOfProposalDetailsByApplicationId(applicationId);
						if(getCountOfInprinciple < 2) {
							countofInprinciple = true;
						}
					}
					inPrincipleDetailMap.put("countofInprinciple",countofInprinciple);
					
					//--------------- set FS email and phone no
					String fsEmail = "NA" ; 
					String mobileNo = "NA" ;
					try {
						//userId may be taken from paymentReq
						UsersRequest user = MultipleJSONObjectHelper.getObjectFromMap((Map)  userClient.getEmailMobile(gatewayRequest.getUserId()).getData(), UsersRequest.class);
						if(user !=null ) {
							fsEmail = user.getEmail();
							mobileNo = user.getMobile();		
						}
					} catch (Exception e) {
						logger.error("Error/Exception while getting the email and phone no Error MSG => {}",e);
						errorMessage.add("Exception in getting FS email and mobile");
					}
					
					inPrincipleDetailMap.put(CommonUtils.MOBILE_NO , mobileNo );
					inPrincipleDetailMap.put(CommonUtils.EMAIL , fsEmail );
					//======================= START set all parameter for in principle detail =============================
					
					//------------------set product detail -------------------
					String loanType = null;
					String processingFees = null;
	
	                if(applicationProposalMapping.getProductId() != null && productId == null) {
	                    productId = applicationProposalMapping.getProductId();  
	                }
	                
	                inPrincipleDetailMap.put(CommonUtils.LOAN_TYPE_ID, productId);
					
	                Integer businessTypeId = gatewayRequest.getBusinessTypeId();
	                if(businessTypeId != null && (CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId().equals(businessTypeId) || CommonUtils.BusinessType.RETAIL_AUTO_LOAN.getId().equals(businessTypeId))) {
	    				businessTypeId = CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId();
	    			}
	                
					//*******************get FS name for HL,PL,Existing,NTB *************
					Map<String , Object> fsNameAndAddress = getFSNameAndAddressOnBusinessTypeId(applicationId , businessTypeId , productId );
					inPrincipleDetailMap.put(CommonUtils.FS_NAME, fsNameAndAddress.get(CommonUtils.FS_NAME) != null ? fsNameAndAddress.get(CommonUtils.FS_NAME):"NA");
					inPrincipleDetailMap.put(CommonUtils.FS_ADDRESS, fsNameAndAddress.get(CommonUtils.FS_ADDRESS) != null ? fsNameAndAddress.get(CommonUtils.FS_ADDRESS):"-");
					if(CommonUtils.SkipType.HOME_LOAN.equals(gatewayRequest.getSkipType())) {
						inPrincipleDetailMap.put(CommonUtils.LOAN_PURPOSE,fsNameAndAddress.get(CommonUtils.LOAN_PURPOSE));
						inPrincipleDetailMap.put(CommonUtils.EMPLOYMENT_TYPE,fsNameAndAddress.get(CommonUtils.EMPLOYMENT_TYPE));
					}
					
					Long tenure = null;
					if(!CommonUtils.isObjectNullOrEmpty(proposalDetails) && !CommonUtils.isObjectNullOrEmpty(proposalDetails.getElTenure())){
						tenure = Math.round(proposalDetails.getElTenure());	
					}else{
						tenure = null;
					}
					
					String url=null;
					if(orgId != null ) {
						//get Bank URL
						url = BanksEnum.getBankUrl(orgId);
						
						if (productId == CommonUtils.LoanType.WORKING_CAPITAL.getValue()) {  //1
							loanType = "Working Capital";
							inPrincipleDetailMap.put(CommonUtils.TENURE,"Renewable Annually");
							if(BanksEnum.SBI_BANK.getOrgId().equals(orgId)) {
								processingFees = "@0.35% of loan amount plus applicable GST";
							} else {
								processingFees = proposalDetails.getProcessingFee() + " %";
							}
						} else if (productId == CommonUtils.LoanType.WCTL_LOAN.getValue()) { //16
							loanType = "Working Capital Term Loan";
							inPrincipleDetailMap.put(CommonUtils.TENURE,tenure != null ? tenure +CommonUtils.YEARS:"NA");
							processingFees = proposalDetails.getProcessingFee() + " %";
	//					} else if (productId == CommonUtils.LoanType.PERSONAL_LOAN.getValue()) { //7
	//						loanType = "Personal Loan";
	//						inPrincipleDetailMap.put(CommonUtils.TENURE,tenure != null ? tenure +CommonUtils.YEARS:"NA");
	//						processingFees = proposalDetails.getProcessingFee() + " %";
	//						inPrincipleDetailMap.put(CommonUtils.MINPF, proposalDetails.getMinPf() != null ? CommonUtils.convertValueWithoutDecimal(proposalDetails.getMinPf()) : null);
	//						inPrincipleDetailMap.put(CommonUtils.MAXPF, proposalDetails.getMaxPf() != null ? CommonUtils.convertValueWithoutDecimal(proposalDetails.getMaxPf()) : null);
	//					} else if (productId == CommonUtils.LoanType.HOME_LOAN.getValue()) { //3
	//						loanType = "Home Loan";
	//						inPrincipleDetailMap.put(CommonUtils.TENURE,tenure != null ? tenure +CommonUtils.YEARS:"NA");
	//						processingFees = proposalDetails.getProcessingFee() + " %";
	//						inPrincipleDetailMap.put(CommonUtils.MINPF, proposalDetails.getMinPf() != null ? CommonUtils.convertValueWithoutDecimal(proposalDetails.getMinPf()) : null);
	//						inPrincipleDetailMap.put(CommonUtils.MAXPF, proposalDetails.getMaxPf() != null ? CommonUtils.convertValueWithoutDecimal(proposalDetails.getMaxPf()) : null);
	//					} else if (productId == CommonUtils.LoanType.AUTO_LOAN.getValue()) { //12
	//						loanType = "Auto Loan";
	//						inPrincipleDetailMap.put(CommonUtils.TENURE,tenure != null ? tenure +CommonUtils.YEARS:"NA");
	//						processingFees = proposalDetails.getProcessingFee() + " %";
	//						inPrincipleDetailMap.put(CommonUtils.MINPF, proposalDetails.getMinPf() != null ? CommonUtils.convertValueWithoutDecimal(proposalDetails.getMinPf()) : null);
	//						inPrincipleDetailMap.put(CommonUtils.MAXPF, proposalDetails.getMaxPf() != null ? CommonUtils.convertValueWithoutDecimal(proposalDetails.getMaxPf()) : null);
						} else {
							loanType = "Term Loan";
							inPrincipleDetailMap.put(CommonUtils.TENURE,tenure != null ? tenure +CommonUtils.YEARS:"NA");
							if (16 == orgId) {
								processingFees = "Upfront fee as applicable";
							} else {
								processingFees = proposalDetails.getProcessingFee() + " %";
							}
							inPrincipleDetailMap.put(CommonUtils.BUSINESS_TYPE_ID, 1);
						}
						
						if(gatewayRequest.getSkipType() != null && CommonUtils.SkipType.MUDRA_LOAN.equals(gatewayRequest.getSkipType())) {
							inPrincipleDetailMap.put(CommonUtils.MINPF, proposalDetails.getMinPf() != null ? CommonUtils.convertValueWithoutDecimal(proposalDetails.getMinPf()) : null);
							inPrincipleDetailMap.put(CommonUtils.MAXPF, proposalDetails.getMaxPf() != null ? CommonUtils.convertValueWithoutDecimal(proposalDetails.getMaxPf()) : null);
						}
					}
					
					logger.info("OrgId==>{} Bank Url==>{} loanType{} tenure==>{} processingFees==>{}", orgId, url, loanType ,tenure ,processingFees);
					inPrincipleDetailMap.put(CommonUtils.BANK_URL, url);
					inPrincipleDetailMap.put(CommonUtils.LOAN_TYPE, loanType);
					
					inPrincipleDetailMap.put("processing_fees", !"null %".equals(processingFees)  ? processingFees : "NA");
		
					//---------------set Org Name ---------- 
					Object userData = userClient.getOrgNameByOrgId(orgId).getData();
					if(!CommonUtils.isObjectNullOrEmpty(userData)) {
						UserOrganisationRequest orgName = MultipleJSONObjectHelper.getObjectFromMap((Map) userData, UserOrganisationRequest.class);
						
						inPrincipleDetailMap.put(CommonUtils.ORGANISATION_NAME, orgName.getOrganisationName() != null ? orgName.getOrganisationName() : "bank");
						inPrincipleDetailMap.put(CommonUtils.ORG_NAME, orgName.getOrganisationName() != null ? orgName.getOrganisationName() : "bank");
						logger.info("OrgName==={} of application Id===>{}",orgName.getOrganisationName() ,applicationId);
					}else {
						logger.error("------------------------------> Exception in getting bank name");
					}
					
					//------------------START set branch detail --------------
					logger.info("get branch detail for Inprinciple of ApplicationId==>{} With BranchId==>{} and OrgId==>{}",applicationId , proposalDetails.getBranchId() , orgId);
					
					BranchBasicDetailsRequest branchMaster = null; 
					List<?> data = userClient.getBranchDataBasedOnOrgAndBranchId(orgId, proposalDetails.getBranchId()).getListData();
					if(!data.isEmpty()) {
						Object brObj = data.stream().findFirst().orElse(null);
						
						if(brObj != null) {
							branchMaster =	MultipleJSONObjectHelper.getObjectFromMap((Map) brObj, BranchBasicDetailsRequest.class);
//							prepare for cc email 
							String[] ccEmail = new String[5];
							ccEmail[0] = !CommonUtils.isObjectNullOrEmpty(branchMaster.getContactPersonEmail()) ? branchMaster.getContactPersonEmail().replace("\\s+", "").trim() : "";
							if(16 == orgId && branchMaster.getSmecEmail()!=null) {
								ccEmail[1] = branchMaster.getSmecEmail().replace("\\s+", "").trim();
							}
							
							inPrincipleDetailMap.put(CommonUtils.CC_EMAIL_LIST, ccEmail);
						}
					}
					
							
					
//					if(branchList != null && !branchList.isEmpty()) {
//					  branchMaster = branchList.get(0);
//					}
					 
					String branchName = "-" ;
					String branchCode = "-" ;
					String ifscCode ="-" ;
					String branchAddress = "-";
					String branchContactPersonNumber = "-" ;
					if(branchMaster !=null ) {
						String stateName = " ";
						String cityName = " ";
						String pinCode = " " ; 
				
						branchName = branchMaster.getName() != null ? branchMaster.getName():branchName;
						branchCode = branchMaster.getCode() != null ? branchMaster.getCode():branchCode;
						ifscCode = branchMaster.getIfscCode() != null ? branchMaster.getIfscCode():ifscCode;  
						try {
							if(branchMaster.getCountryId() != null && branchMaster.getStateId() != null && branchMaster.getCityId() != null) {
								Object [] obj = commonRepository.findCityStateCountryById(branchMaster.getCityId().longValue(), branchMaster.getStateId().longValue() , branchMaster.getCountryId()); 
								if(obj !=null ) {
									cityName = obj[0] != null ? String.valueOf(obj[0]) : "";
									stateName = obj[1] != null ? String.valueOf(obj[1]) : "";
								}
							}
						}catch (Exception e) {
							logger.error("Error/Exception in branch master Detail...Error==>{}",e);	
						}
						if( branchMaster.getLocationId() != null  ) { 
							pinCode = String.valueOf(branchMaster.getPincode());
						}
						
						branchAddress=branchMaster.getPremisesNo()!=null ? branchMaster.getPremisesNo():"";		
						branchAddress+=branchMaster.getStreetName()!=null ?branchMaster.getStreetName():"";
						branchAddress+=branchMaster.getLandMark()!=null ? branchMaster.getLandMark():"";
						branchAddress+=" "+cityName+" "+stateName+" "+pinCode;
						 
						branchContactPersonNumber = branchMaster.getContactPersonNumber()!=null ? branchMaster.getContactPersonNumber() :"-" ; 
						
					}
//					else {
	//					try {
	//						onlinePaymentTransactionAuditLogRepository.setFailureReason("BranchId is null or Inactive", applicationId, true);
	//					}catch (Exception e) {
	//						logger.error("Error/Exception while updating failure Reason of BranchId inactive ...Error==>{}",e);
	//					}
//					}
					
					inPrincipleDetailMap.put(CommonUtils.BRANCH_ID,proposalDetails.getBranchId() != null ? proposalDetails.getBranchId() :"-");
					inPrincipleDetailMap.put("branch_name",branchName);
					inPrincipleDetailMap.put("branch_code", branchCode);
					inPrincipleDetailMap.put("ifsc_code", ifscCode);
					inPrincipleDetailMap.put("branch_address", branchAddress != null ? branchAddress:"-");
					inPrincipleDetailMap.put("branch_contact", branchContactPersonNumber);
					//------------------END  --------------
					
					//-------------- START loan detail ------------------
					Long loanAmount = null;
					Long emi = null;
					
					if(!CommonUtils.isObjectNullOrEmpty(proposalDetails) && !CommonUtils.isObjectNullOrEmpty(proposalDetails.getElAmount())){
						loanAmount = Math.round(proposalDetails.getElAmount());	
					}else{
						logger.error("Error/Exception while fetching loan amount in InprincipleDetail of ProposalId==>{}",proposalDetails.getId());
//						errorMessage.add("Error/Exception while fetching loan amount in InprincipleDetail of ProposalId"+ proposalDetails.getId());
					}
					
					if(!CommonUtils.isObjectNullOrEmpty(proposalDetails) && !CommonUtils.isObjectNullOrEmpty(proposalDetails.getEmi())){
						emi = Math.round(proposalDetails.getEmi());	
					}
					else{
						emi = null;
					}
						
					//renewal status
					inPrincipleDetailMap.put(CommonUtils.WC_RENEWABLE , loanApplicationMaster.getWcRenewalStatus() !=null && loanApplicationMaster.getWcRenewalStatus() == 2 ? CommonUtils.TRUE : CommonUtils.FALSE);
					
					//bank specific 
					inPrincipleDetailMap.put(CommonUtils.IS_CENTRAL, !CommonUtils.isObjectNullOrEmpty(orgId) && BanksEnum.CENTRAL_BANK.getOrgId().equals(orgId) ? CommonUtils.TRUE : CommonUtils.FALSE);
					inPrincipleDetailMap.put(CommonUtils.IS_KOTAK, !CommonUtils.isObjectNullOrEmpty(orgId) && BanksEnum.KOTAK_BANK.getOrgId().equals(orgId) ? CommonUtils.TRUE : CommonUtils.FALSE);
					inPrincipleDetailMap.put(CommonUtils.IS_ANDHRA, !CommonUtils.isObjectNullOrEmpty(orgId) && BanksEnum.ANDHRA_BANK.getOrgId().equals(orgId) ? CommonUtils.TRUE : CommonUtils.FALSE);
					inPrincipleDetailMap.put(CommonUtils.IS_ICICI, !CommonUtils.isObjectNullOrEmpty(orgId) && BanksEnum.ICICI_BANK.getOrgId().equals(orgId) ? CommonUtils.TRUE : CommonUtils.FALSE);
					
					//canara bank ..Should be in Boolean value
					inPrincipleDetailMap.put(CommonUtils.IS_CANARA, !CommonUtils.isObjectNullOrEmpty(orgId) && BanksEnum.CANARA_BANK.getOrgId().equals(orgId) ? true : false);
					
					//obc bank ..Should be in Boolean value
					inPrincipleDetailMap.put(CommonUtils.IS_OBC, !CommonUtils.isObjectNullOrEmpty(orgId) && BanksEnum.ORIENTAL_BANK_OF_COMMERCE.getOrgId().equals(orgId) ? true : false);
					
					//indusInd bank ..Should be in Boolean value
					inPrincipleDetailMap.put(CommonUtils.IS_INDUSIND, !CommonUtils.isObjectNullOrEmpty(orgId) && BanksEnum.INDUSIND_BANK.getOrgId().equals(orgId) ? true : false);
					
					//SBI Bank
					if(!CommonUtils.isObjectNullOrEmpty(orgId) && BanksEnum.SBI_BANK.getOrgId().equals(orgId)) {
						logger.info("Inside the SBI specific SMECC code ======orgId================{}",orgId);
						inPrincipleDetailMap.put(CommonUtils.IS_SBI, CommonUtils.TRUE);
						inPrincipleDetailMap.put("smec_email", branchMaster != null && branchMaster.getSmecEmail() != null ? branchMaster.getSmecEmail().replaceAll("\\s+","").trim() : "NA");
	                   	inPrincipleDetailMap.put("smec_name", branchMaster != null && branchMaster.getSmecName()!= null ? branchMaster.getSmecName() :"NA");
	                   	inPrincipleDetailMap.put("smec_code", branchMaster != null && branchMaster.getSmecCode() != null ? branchMaster.getSmecCode() :"NA");
	                   	inPrincipleDetailMap.put("smec_mobile", branchMaster != null && branchMaster.getSmecMobile() != null ? branchMaster.getSmecMobile() :"NA");
					}else {
						inPrincipleDetailMap.put(CommonUtils.IS_SBI, CommonUtils.FALSE);
					}
									
					//uniform product
					inPrincipleDetailMap.put("uni_product", loanApplicationMaster.getBusinessTypeId() != null && CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId().equals(gatewayRequest.getBusinessTypeId()) ? CommonUtils.TRUE : CommonUtils.FALSE);
								
					//Existing and Additional Loan Amount
					Double existingLoanAmount = proposalDetails.getExistingLoanAmount() != null ? proposalDetails.getExistingLoanAmount() : 0;
					Double additionalLoanAmount = proposalDetails.getAdditionalLoanAmount() != null ? proposalDetails.getAdditionalLoanAmount(): 0;
	
					logger.info("Getting Existing LoanAmount==>{} and Additional LoanAmount==>{} from proposalDetails...of ApplicationId==>{}  and proposalId==>{}",
							existingLoanAmount , additionalLoanAmount , applicationId , proposalDetails.getId());
					if(existingLoanAmount != 0 && additionalLoanAmount != 0) {
						loanAmount = ((Double) (existingLoanAmount + additionalLoanAmount)).longValue();
					}
					
					inPrincipleDetailMap.put(CommonUtils.EXISTING_LOAN_AMOUNT, existingLoanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(existingLoanAmount.doubleValue()) : 0);
					
					if(existingLoanAmount != 0)
						inPrincipleDetailMap.put(CommonUtils.ADDITIONAL_LOAN_AMOUNT, additionalLoanAmount  != 0 ? CommonUtils.convertValueWithoutDecimal(additionalLoanAmount.doubleValue()) : 0);
					else
						inPrincipleDetailMap.put(CommonUtils.ADDITIONAL_LOAN_AMOUNT, additionalLoanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(additionalLoanAmount.doubleValue()) : CommonUtils.convertValueWithoutDecimal(loanAmount.doubleValue()));
					
					inPrincipleDetailMap.put(CommonUtils.AMOUNT, loanAmount != null ? CommonUtils.convertValueWithoutDecimal(loanAmount.doubleValue()) : "NA");
					
					if(gatewayRequest.getSkipType() != null && CommonUtils.SkipType.MUDRA_LOAN.equals(gatewayRequest.getSkipType()) && loanAmount != null){
						if(loanAmount <= 50000) {
							inPrincipleDetailMap.put("category", "Shishu");
						}else if(loanAmount > 50000 && loanAmount <= 500000) {
							inPrincipleDetailMap.put("category", "Kishor");
						}else if(loanAmount > 500000 && loanAmount <= 1000000) {
							inPrincipleDetailMap.put("category", "Tarun");
						}else {
							inPrincipleDetailMap.put("category", "-");
						}
					}
					
					
					if(loanApplicationMaster.getDenominationId() != null ) {
						inPrincipleDetailMap.put("denominationFS", " ");
					}
					
					inPrincipleDetailMap.put(CommonUtils.RATE_INTEREST, proposalDetails.getElRoi() != null? proposalDetails.getElRoi() + " %":"NA");
					inPrincipleDetailMap.put(CommonUtils.EMI_AMOUNT, emi != null ? CommonUtils.convertValueWithoutDecimal(emi.doubleValue()): "NA");
					inPrincipleDetailMap.put(CommonUtils.ORG_ID, orgId!=null ? orgId : null);
					inPrincipleDetailMap.put("fp_product_id", proposalDetails.getFpProductId()!=null?proposalDetails.getFpProductId():"NA");
					inPrincipleDetailMap.put(CommonUtils.BRANCH_ID, proposalDetails.getBranchId()!=null?proposalDetails.getBranchId():"NA");
					logger.info("InPrinciple Detail ===>{}",inPrincipleDetailMap);
					
					String dayDiff = proposalService.getDayDiffrenceForInprinciple(gatewayRequest.getLoanTypeId());
					
					inPrincipleDetailMap.put("dayDiff", dayDiff != null ? dayDiff : "");
			
				} else {
					logger.error("Error/Exception in getting Proposal Details");
					errorMessage.add("proposal not found for applicationId "+applicationId);
				}
				//-------------------- END------------------
			}else {
				logger.error("------------- Entry Not Found in Loan Application Master with ----------- applicationId ==>{}", applicationId);
				errorMessage.add("Application not found by application Id "+applicationId);
			}
		}catch (Exception e) {
			logger.error("Exception in getting inprinciple details for application Id [{}] and exception is {}", applicationId,e);
		}
	
		if(!errorMessage.isEmpty()) {
			inPrincipleDetailMap.put("Error", errorMessage);
		}
		return inPrincipleDetailMap; 	
	}

	public Map<String , Object> getFSNameAndAddressOnBusinessTypeId(Long applicationId , Integer businessTypeId , Integer productId ) {
		logger.info("-----------get FSName And FSAddress On BusinessTypeId==>{} and ProductId==>{}--------------------ApplicationId==>{}",businessTypeId , productId ,applicationId);
		Map< String ,Object > map= new HashMap<String, Object>();
		map.put(CommonUtils.FS_NAME,"NA");
		String userAddress = "-";
		try {
				if (productId != null && CommonUtils.getUserMainType(productId) == CommonUtils.UserMainType.CORPORATE) {
					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findFirstByApplicationIdIdAndIsActiveOrderByIdAsc(applicationId , true);
					if(corporateApplicantDetail != null) {
						if(corporateApplicantDetail.getOrganisationName() != null) {
							map.put(CommonUtils.FS_NAME, corporateApplicantDetail.getOrganisationName());
						}else {
							map.put(CommonUtils.FS_NAME, "NA");
						}
						userAddress = corporateApplicantDetail.getRegisteredPremiseNumber() != null ? corporateApplicantDetail.getRegisteredPremiseNumber():"";		
						userAddress += corporateApplicantDetail.getRegisteredStreetName() != null ? corporateApplicantDetail.getRegisteredStreetName():"";
						userAddress += corporateApplicantDetail.getRegisteredLandMark() != null ? corporateApplicantDetail.getRegisteredLandMark():"";
						
						Object[] cityStateCountry = commonRepository.findCityStateCountryById(corporateApplicantDetail.getRegisteredCityId(), corporateApplicantDetail.getRegisteredStateId().longValue(), corporateApplicantDetail.getRegisteredCountryId());
						if(cityStateCountry != null) {
							String cityName = cityStateCountry[0] != null ? String.valueOf(cityStateCountry[0]) : "";
							String stateName = cityStateCountry[1] != 	null ? String.valueOf(cityStateCountry[1]) : "";
							String pinCode = corporateApplicantDetail.getRegisteredPincode() != null ? corporateApplicantDetail.getRegisteredPincode().toString() : "";
							userAddress += " "+cityName+" "+stateName+" "+pinCode;
						}
					}	
					map.put(CommonUtils.FS_ADDRESS , userAddress);	
					logger.info("FsName==>{} FSAddress==>{}", map.get(CommonUtils.FS_NAME), userAddress);
					return map;
				}
		}
		catch (Exception e) {
			logger.error("Error/Exception while getting fs_Name of ApplicationId===>{}",applicationId+ "Error: ",e);
		}
		
		map.put(CommonUtils.FS_ADDRESS,userAddress);
		return map;
	}
	

	
	// send in principle letter 
	public Boolean sendInprincipleLetterAndSmsToFS(Map<String, Object> mailParameters , GatewayRequest gatewayRequest , Long proposalId) throws NotificationException {
		logger.info("----------Send Mail Sms Sys To Fs-----------ApplicationId==>{}",gatewayRequest.getApplicationId());
		boolean mailStatus = false;
		if (gatewayRequest.getPaymentStatus() != null && CommonUtils.PaymentStatus.SUCCESS.equals(gatewayRequest.getPaymentStatus()) || CommonUtils.PaymentStatus.BYPASS.equals(gatewayRequest.getPaymentStatus())){
			
			Long domainId = null;
			String to = null;
			
			Long applicationId = gatewayRequest.getApplicationId();
			if(mailParameters == null ) {
				mailParameters = getInPrincipleDetail(applicationId ,proposalId ,null);
			}
			
			if(mailParameters != null && !mailParameters.isEmpty()) {
			
				if(!mailParameters.isEmpty() && mailParameters.get(CommonUtils.SKIP_TYPE) != null && gatewayRequest.getSkipType() == null) {
					gatewayRequest.setSkipType(mailParameters.get(CommonUtils.SKIP_TYPE).toString());
				}
				
				mailParameters.put(CommonUtils.BANKER_NAME, mailParameters.get(CommonUtils.ORGANISATION_NAME));
				mailParameters.put(CommonUtils.PRODUCT_TYPE, mailParameters.get(CommonUtils.LOAN_TYPE));
				
				//set domainId for notification url call
				domainId = NotificationConstants.NotificationProperty.DomainValue.MSME.getId();
				
				Long orgId = null;
				Integer loanTypeId = null;
				
				if(mailParameters != null && mailParameters.get(CommonUtils.ORG_ID) != null) {
					orgId = Long.valueOf(String.valueOf(mailParameters.get(CommonUtils.ORG_ID)));
				}
				if(mailParameters != null && mailParameters.get(CommonUtils.LOAN_TYPE_ID) != null) {
					loanTypeId = Integer.valueOf(String.valueOf(mailParameters.get(CommonUtils.LOAN_TYPE_ID)));
				}
				
				
				if(mailParameters.containsKey(CommonUtils.EMAIL)) {
					
					String[] ccForMail = new String[5];
					if(mailParameters.containsKey(CommonUtils.CC_EMAIL_LIST)) {
						ccForMail  = (String[]) mailParameters.get(CommonUtils.CC_EMAIL_LIST);
					}
					
					to = mailParameters.get(CommonUtils.EMAIL).toString();
					
					/** send inprinciple letter in msme  
					 * Not send on sidbi specific and sbi specific
					 *  */
					
					if(CommonUtils.SkipType.MUDRA_LOAN.equals(gatewayRequest.getSkipType())){
						List<ContentAttachment> contentAttachmentList =  new ArrayList<ContentAttachment>();
						ContentAttachment contentAttachment = generateIndicativeDocument(mailParameters, gatewayRequest);
						if(contentAttachment != null) {
							logger.info("Attachment for Mudra Loan IndicativeDocumentList of ContentAttachment of ApplicationId==>{}" ,gatewayRequest.getApplicationId());
							contentAttachmentList.add(contentAttachment);
						}
						
						contentAttachment = generateInprincipleLetterPdf(mailParameters, gatewayRequest);
						if(contentAttachment != null) {
							logger.info("Attachment for Mudra Loan Inprincple Pdf of ContentAttachment of ApplicationId==>{}" ,gatewayRequest.getApplicationId());
							//fileName = mailParameters.get(CommonUtils.APPLICATION_IID)+"_MLInpLet_"+sdfForUploadFile.format(date)+".pdf";
							//uploadFileOnDms(gatewayRequest.getApplicationId(), contentAttachment.getContentInByte(), fileName, INPRINCIPLE_DMS_MAPPING);
							contentAttachmentList.add(contentAttachment);
						}
					
						/** In principle send to fund seeker with Attachment*/
						mailStatus = mailComponent.sendMailWithAttachment(to ,ccForMail ,mailParameters ,contentAttachmentList ,gatewayRequest.getUserId() ,
								NotificationAlias.ML_COMMON_INPRINCIPLE_LETTER ,null ,bcc ,domainId , orgId , loanTypeId ,NotificationMasterAlias.ML_IN_PRINCIPLE_LETTER.getMasterId());
						if(mailStatus) {
							logger.info("===============================================>EMAIL SENT");								
						}else {
							logger.info("Error in  sending inprinciple letter of ApplicationId ==> [{}]",gatewayRequest.getApplicationId());
						}
					}
				}
				
			}else {
				logger.error("Error/Exception while fetching InPrinciple Detail...So not sending Inprinciple Sms And System notification with applicationId==>{} and proposalId==>{}",gatewayRequest.getApplicationId() , proposalId);
			}
			
		}
		/** after every thing this will be use */
//		else {
//			String orgName="-";
//			String loanType="-";
//			
//			Long orgId = null;
//			Integer loanTypeId = null;
//			Integer productId = null;
//			
//			domainId = NotificationConstants.NotificationProperty.DomainValue.MSME.getId();
//			
//			ProposalDetails proposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(applicationId, false );
//			if(proposalDetails != null) {
//				orgId = proposalDetails.getUserOrgId();
//			    orgName = userOrganisationMasterRepository.getUserOrganizationNameByOrgId(proposalDetails.getUserOrgId());
//			    
//			    ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(proposalDetails.getId(), true);
//			    productId = applicationProposalMapping != null && applicationProposalMapping.getProductId() != null ? applicationProposalMapping.getProductId() : null;
//			}
//			
//			LoanApplicationMaster loanApplicationMaster = loanApplicationMasterRepository.findByIdAndIsActive(applicationId, true );
//			if(loanApplicationMaster != null) {
//				loanType = loanApplicationMaster.getProductId() != null ? LoanType.getType(loanApplicationMaster.getProductId()).getType() : "-";
//			}
//			
//			String userName = "NA";
//			if(applicationId != null && productId != null) {
//				Map<String , Object> fsNameAndAddress = getFSNameAndAddressOnBusinessTypeId(applicationId , null , productId );
//				userName = fsNameAndAddress.get(CommonUtils.FS_NAME) != null ? fsNameAndAddress.get(CommonUtils.FS_NAME).toString() : "NA";
//			}
//			
//			mailParameters = new HashMap<String, Object>();
//			
//			mailParameters.put(CommonUtils.BANKER_NAME, orgName);
//			mailParameters.put(CommonUtils.PRODUCT_TYPE, loanType);
//			mailParameters.put("login_url", "www.bitly.com");
//			mailParameters.put(CommonUtils.FS_NAME, userName);
//			
//			if(user.getEmail() != null ) {
//				to= user.getEmail() ;
//				emailSubject =  "Payment Failure - For Quick Business Loan Approval";
//				 fpAsyncComponent.createNotificationForEmailForFundSeeker(to, String.valueOf(userId), mailParameters, NotificationAlias.EMAIL_FS_PAYS_CANCEL_PAYMENT, emailSubject,null ,domainId , orgId ,loanTypeId ,NotificationMasterAlias.EMAIL_FS_PAYS_CANCEL_PAYMENT.getMasterId());
//			}
//				
//			if(user.getMobile() != null ) {
//				to= user.getMobile();
//				mailParameters.put("url", "www.bitly.com");
//				 fpAsyncComponent.sendSMSNotification(to , String.valueOf(userId ) , mailParameters, NotificationAlias.SMS_FS_CANCELS_PAYMENT ,domainId , orgId ,loanTypeId ,NotificationMasterAlias.SMS_FS_CANCELS_PAYMENT.getMasterId());
//			}
//				
//		}	
		return mailStatus ;
	}
	
	public ContentAttachment generateIndicativeDocument(Map<String, Object> map , GatewayRequest gatewayRequest) {
		byte[] contentAttachmentForInductiveDocument = callReportsToFetchDocuments(ReportsEnum.INDICATIVE_DOCUMENT_FOR_ML.getId(), null , CommonUtils.SkipType.MUDRA_LOAN);
		try {
			logger.info("Indicative Document Attachment pdf for Mail of ApplicationId==>{}",gatewayRequest.getApplicationId());
			if(contentAttachmentForInductiveDocument != null) {
				ContentAttachment contentAttachment = new ContentAttachment();
				contentAttachment.setFileName("IndicativeDocumentList.pdf");
				contentAttachment.setContentInByte(contentAttachmentForInductiveDocument);
				return contentAttachment;
			}
		}catch (Exception e) {
			logger.error("Error / Exception Indicative Document attachment failed due to some error check reports log {}",e);
		}
		return null;
	}
	
	public ContentAttachment generateInprincipleLetterPdf(Map<String, Object> map , GatewayRequest gatewayRequest) {
		byte[] contentAttachmentForInductiveDocument = callReportsToFetchDocuments(ReportsEnum.INPRINCIPLE_LETTER_ML.getId(), map ,CommonUtils.SkipType.MUDRA_LOAN);
		try {
			logger.info("Inprinciple Letter in pdf for Mail of ApplicationId==>{}",gatewayRequest.getApplicationId());
			if(contentAttachmentForInductiveDocument != null) {
				ContentAttachment contentAttachment = new ContentAttachment();
				contentAttachment.setFileName("InprincipleLetter.pdf");
				contentAttachment.setContentInByte(contentAttachmentForInductiveDocument);
				return contentAttachment;
			}
		} catch (Exception e) {
			logger.error("Error / Exception Inprinciple Letter attachment failed due to some error check reports log {}",e);
		}
		return null;
	}
	
	public byte[] callReportsToFetchDocuments(Integer templateId , Map<String, Object> parameter , String skipType) {
		Map<String , Object> reportParameter = new HashMap<String , Object>();
		reportParameter.put("templateId", templateId);
		if(parameter != null && !parameter.isEmpty()) {
			List<Map<String, Object>> bankArr= new ArrayList<Map<String,Object>>();
			bankArr.add(parameter);
			reportParameter.put("data", bankArr);
		}
		
		String url = com.opl.commons.lib.common.CommonUtils.getLocalIpAddress(com.opl.commons.lib.common.CommonUtils.UrlType.reports).concat(REPORT_URL);
		
		logger.info("Call Reports For {}  and Fetching report with URL==>{} and templateId==>{}", skipType , url , templateId);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("req_auth", "true");
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			HttpEntity<Map<String , Object>> entity = new HttpEntity<Map<String , Object>>(reportParameter, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class).getBody();
		} catch (Exception e) {
			logger.error("Throw Exception while calling {} reports for get Report with templateId-->{} with Error==>{}",skipType ,templateId , e);
		}
		return null;
	}
	
	public Boolean sendmailToAllFundProvider(Map<String, Object> map ,ApplicationProposalMapping applicationProposalMapping , GatewayRequest gatewayRequest) throws NotificationException {
		
		if(map == null ) {
			try {
				map = getInPrincipleDetail(gatewayRequest.getApplicationId() ,null ,null);
			}catch (Exception e) {
				logger.error("Error/Exception while getting inprinciple detail --------------applicationId==>{} ...Error=> {}", gatewayRequest.getApplicationId() ,e);
			}
		}
		
		if(gatewayRequest.getSkipType() == null && !CommonUtils.isObjectNullOrEmpty(map) && !CommonUtils.isObjectNullOrEmpty(map.get(CommonUtils.SKIP_TYPE))) {
			gatewayRequest.setSkipType(map.get(CommonUtils.SKIP_TYPE).toString());
		}
		
		Long orgId = null;
		Long branchId = null;
		Integer loanTypeId = null;
		
		ProposalDetails proposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(gatewayRequest.getApplicationId(), true );
		if(proposalDetails != null) {
			orgId = proposalDetails.getUserOrgId();
			branchId = proposalDetails.getBranchId();
		}
		
		if(applicationProposalMapping == null ) {
            applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(proposalDetails.getId(), true);
        }

		map.put(CommonUtils.FS_NAME,map.get(CommonUtils.FS_NAME) != null ? map.get(CommonUtils.FS_NAME) : "NA" ) ;
		map.put("support_email", supportEmail);
		map.put("product_type", map.get(CommonUtils.LOAN_TYPE)) ;
		map.put("interest_rate", map.get(CommonUtils.RATE_INTEREST));
		map.put(CommonUtils.EMI_AMOUNT ,map.get(CommonUtils.EMI_AMOUNT));
		map.put("application_id",map.get(CommonUtils.APPLICATION_IID));
		map.put(CommonUtils.MOBILE_NO,map.get(CommonUtils.MOBILE_NO) != null ? map.get(CommonUtils.MOBILE_NO):"-");
		map.put("address",map.get(CommonUtils.FS_ADDRESS) != null ? map.get(CommonUtils.FS_ADDRESS):"-");
		map.put("loan_amount", applicationProposalMapping.getLoanAmount() != null ? applicationProposalMapping.getLoanAmount() :"NA");
		loanTypeId = map.get(CommonUtils.LOAN_TYPE_ID) != null ? Integer.valueOf(map.get(CommonUtils.LOAN_TYPE_ID).toString()) : null;
		
		//set domainId for notification url call
		Long domainId =  NotificationConstants.NotificationProperty.DomainValue.MSME.getId();
		
		// start attach CAM to Mail
		List<ContentAttachment> listOfContentAttachments = new ArrayList<ContentAttachment>();
		ContentAttachment contentAttachment = new ContentAttachment();
		Long fpProductId = (Long)map.get("fp_product_id");
		if(fpProductId != null && applicationProposalMapping != null && applicationProposalMapping.getProposalId() != null) {
			try {
				logger.info("Fetching Cam Report Data from Loans...applicationID==>{}  fpProductId==>{}  proposalID==>{}",gatewayRequest.getApplicationId(),fpProductId, applicationProposalMapping.getProposalId());
				
				byte[]  camReport = camReportPdfDetailsService.getCamReportPrimaryDetailsByteArray(gatewayRequest.getApplicationId(), fpProductId, applicationProposalMapping.getProposalId(), false,loanTypeId);
				
				if(camReport != null && camReport.length > 0) {
					contentAttachment.setFileName("CAM.pdf");
					contentAttachment.setContentInByte(camReport);
					listOfContentAttachments.add(contentAttachment);
					logger.info("Attaching Cam Report For Maker Checker BO HO of applicationID==>{}",gatewayRequest.getApplicationId());
				}else {
					logger.error("Error/Exception while fetching Cam Report Data from LoansClient of applicationId==>{}",gatewayRequest.getApplicationId());
				}
			} catch (Exception e){
				logger.error("Error while attaching cam report : ...Error==>{} ",e);
			}
		}else {
			logger.error("FpProductId or ProposalId is null of applicationId====>{}",gatewayRequest.getApplicationId());
		}
		
		//start attach Application Form to Mail
		contentAttachment = new ContentAttachment();
		if(fpProductId != null && applicationProposalMapping != null && applicationProposalMapping.getProposalId() != null && applicationProposalMapping.getProductId() != null) {
			try {
				byte[] applicationReportData = null;
				
				logger.info("Fetching Application Form Report Data from Loans...applicationID==>{}  fpProductId==>{}  proposalId==>{}  with LoanTypeId==>{}",gatewayRequest.getApplicationId(),fpProductId, applicationProposalMapping.getProposalId() ,applicationProposalMapping.getProductId());
				if(!CommonUtils.SkipType.DFS_LOAN.equals(gatewayRequest.getSkipType())) {
					applicationReportData = camReportPdfDetailsService.getApplicationForm(gatewayRequest.getApplicationId(), fpProductId, applicationProposalMapping.getProposalId(), loanTypeId);
				}
				
				if(applicationReportData != null && applicationReportData.length > 0) {
					contentAttachment.setFileName("ApplicationForm.pdf");
					contentAttachment.setContentInByte(applicationReportData);
					listOfContentAttachments.add(contentAttachment);
				}else {
					logger.error("Error/Exception while fetching Application Form Report with ApplicationId==>{} fpProductId==>{}  proposalId==>{}  LoanTypeId==>{} and OrgId==>{}" , gatewayRequest.getApplicationId() ,fpProductId, applicationProposalMapping.getProposalId() ,applicationProposalMapping.getProductId() , orgId);
				}
			} catch (Exception e){
				logger.error("Error while attaching Application Report : ...Error==>{} ",e);
			}
		}
		
		Integer businessTypeId = null;
		String to = null;
		ArrayList<String> cc = new ArrayList<String>();
		String [] bccCam = null;
		String subject = null ;
		String fullName = "Sir/Madam"; 
//		if(CommonUtils.SkipType.PERSONAL_LOAN.equals(gatewayRequest.getSkipType())) {
//			businessTypeId = CommonUtils.BusinessType.RETAIL_LOAN.getId();
//			subject = "Personal Loan Intimation : New In-Principle Approved Proposal";
//		}else if(CommonUtils.SkipType.HOME_LOAN.equals(gatewayRequest.getSkipType())){
//			businessTypeId = CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId();
//			subject = "Home Loan Intimation : New In-Principle Approved Proposal";
//		}else if(CommonUtils.SkipType.AUTO_LOAN.equals(gatewayRequest.getSkipType())){
//			businessTypeId = CommonUtils.BusinessType.RETAIL_AUTO_LOAN.getId();
//			subject = "Auto Loan Intimation : New In-Principle Approved Proposal";
//		}else if(CommonUtils.SkipType.MUDRA_LOAN.equals(gatewayRequest.getSkipType())){
//			businessTypeId = CommonUtils.BusinessType.MUDRA_LOAN.getId();
//			subject = "MUDRA Loan - Intimation : New In-Principle Approved Proposal";
//		}else if(CommonUtils.SkipType.DFS_LOAN.equals(gatewayRequest.getSkipType())){
//			businessTypeId = CommonUtils.BusinessType.DFS_LOAN.getId();
//			subject = "DFS - Intimation : New In-Principle Approved Proposal";
//		}else if(CommonUtils.SkipType.ODOP_LOAN.equals(gatewayRequest.getSkipType())){
//			businessTypeId = CommonUtils.BusinessType.ODOP_LOAN.getId();
//			subject = "ODOP - Intimation : New In-Principle Approved Proposal";
//			loanTypeId = CommonUtils.LoanType.ODOP_LOAN.getValue();
//		}else {
			businessTypeId = CommonUtils.BusinessType.EXISTING_BUSINESS.getId();
			subject = "MSME Intimation : New In-Principle Approved Proposal";
//		}
		map.put("maker_name", fullName);
		
		List<Long> roleTypeList = new ArrayList<Long>();
		
		if(CommonUtils.SkipType.MUDRA_LOAN.equals(gatewayRequest.getSkipType())) {
			roleTypeList.add(CommonUtils.UsersRoles.FP_MAKER);
		}else {
			roleTypeList.add(CommonUtils.UsersRoles.FP_MAKER);
			roleTypeList.add(CommonUtils.UsersRoles.FP_CHECKER);
		}
		
		for (Long roleTypeId : roleTypeList) {
			try {
				logger.info("Fetching Details of User Role Type by UserRoleId==>{}" ,roleTypeId);
				
				List<Object[]> usersList = null;
				
				if(orgId != null && branchId != null) {
					usersList = commonRepository.getUserListByUserOrgIdAndRoleIdAndBranchId(branchId, roleTypeId, orgId);
				}else {
					logger.error("Error/Exception while fetching users for Maker And Checker...OrgId==>{}  BranchId==>{}", orgId, branchId);
				}
								
				if(usersList != null && !usersList.isEmpty()) {
					for (Object[] user : usersList) {
						// user[0] = userId
						// user[1] = email
						// user[2] = mobile
						
						if(user != null && user.length > 2) {
							Boolean checkUserWithSpecificBusinessType = commonRepository.checkUserWithBusinessTypeId(Long.valueOf(user[0].toString()), businessTypeId);
							
							if(checkUserWithSpecificBusinessType != null && checkUserWithSpecificBusinessType) {
								
								//send mail to fp
								if(!CommonUtils.SkipType.SBI_SPECIFIC.equals(gatewayRequest.getSkipType()) && !CommonUtils.SkipType.SIDBI_SPECIFIC.equals(gatewayRequest.getSkipType())) {
									if(user[1] !=null ) {
										if(to == null) {
											to = user[1].toString();
											bccCam = bccForCam;
										}else {
											cc.add(user[1].toString());
										}
									}
								}else {
									if(to == null) {
										to = user[1].toString();
										bccCam = bccForCam;
									}else {
										cc.add(user[1].toString());
									}
								}
								
								if(!CommonUtils.SkipType.SBI_SPECIFIC.equals(gatewayRequest.getSkipType()) && !CommonUtils.SkipType.SIDBI_SPECIFIC.equals(gatewayRequest.getSkipType())) {
									String toMobile = null;
									//send sms to fp
									if(user[2] != null ) {
										toMobile = user[2].toString();
										map.put("url", "www.psbloansin59minutes.com");
										mailComponent.sendSMSNotification(toMobile ,user[0].toString() ,map ,NotificationAlias.SMS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS ,domainId , orgId ,loanTypeId , NotificationMasterAlias.SMS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS.getMasterId());
									}
									
									//send sys to fp
									if(user[0] != null ) {
										String toSys = null;
										toSys = user[0].toString();
										if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping)  && !applicationProposalMapping.getBusinessTypeId().equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
											mailComponent.sendSYSNotification(toSys ,gatewayRequest.getApplicationId(), toSys ,map ,NotificationAlias.SYS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS ,toSys ,domainId, orgId , loanTypeId ,NotificationMasterAlias.SYS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS.getMasterId());
										}
									}	
								}
							}
						}
					}
				}else {
					logger.error("Error/Exception Or May be not found in database while fetching Details of User Role Type by UserRoleId==>{} of ApplicationId==>{}" , roleTypeId , gatewayRequest.getApplicationId() );
				}
			}catch (Exception e) {
				logger.error("Error/Exception while send mail to FP ------------------ applicationId ==>{} ...Error==>{}",gatewayRequest.getApplicationId(), e);
			}
		}
		
		String[] ccCam = cc != null && !cc.isEmpty() ? cc.toArray(new String[cc.size()]) : null;
		
		try {
			logger.info("Send Intimation Mail To==>{} with CC==>{}  and Bcc==>{}" ,to , ccCam != null && ccCam.length > 0 ? Arrays.asList(ccCam) : null , bccCam != null && bccCam.length > 0 ? Arrays.asList(bccCam) : null);
			
			if(to != null) {
				if(!CommonUtils.SkipType.SBI_SPECIFIC.equals(gatewayRequest.getSkipType()) && !CommonUtils.SkipType.SIDBI_SPECIFIC.equals(gatewayRequest.getSkipType())) {
					
					mailComponent.sendMailWithAttachment(to, ccCam, map, listOfContentAttachments, gatewayRequest.getUserId(), NotificationAlias.EMAIL_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS, subject, 
							bccCam, domainId, orgId, loanTypeId, NotificationMasterAlias.EMAIL_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS.getMasterId());
					
				}else {
					
					mailComponent.sendMailWithAttachment(to, ccCam, map, listOfContentAttachments, gatewayRequest.getUserId(), NotificationAlias.EMAIL_OF_THANKYOU_BANKSPECIFIC_FP, subject, 
							bccCam, domainId, orgId, loanTypeId, NotificationMasterAlias.EMAIL_OF_THANKYOU_BANKSPECIFIC_FP.getMasterId());
				}
			}else {
				logger.error("No User Found For Send Intimation Mail of ApplicationId==>{} with SkipType==>{}" , gatewayRequest.getApplicationId() , gatewayRequest.getSkipType());
			}
		} catch (Exception e) {
			logger.error("Error/Exception while sending Intimation Mail of ApplicationId==>{} with error==>{}" ,gatewayRequest.getApplicationId() ,e);
		}
		
		return true;
	}


	@Override
	public Map<String, Object> updatePaymentAfterSuccess(GatewayRequest gatewayRequest) {
//		LoanApplicationRequest loanApplicationRequest = new LoanApplicationRequest();
		
		logger.info("start updateLoanApplicationMasterPaymentStatus()");
		LoanApplicationMaster loanApplicationMaster = loanApplicationMasterRepository.findByIdAndIsActive(gatewayRequest.getApplicationId(), true );
		
		//set payment status in loan master
		if (loanApplicationMaster == null  ) {
			throw new NullPointerException("Invalid Loan Application ID==>" + gatewayRequest.getApplicationId());
		}
		
		gatewayRequest.setUserId(loanApplicationMaster.getUserId());
			
		Boolean updatePayment = false;
		Map<String, Object> map = new HashMap<>();
		try {
			
			String paymentStatus = commonRepository.getPaymentStatus(gatewayRequest.getApplicationId());
			
			gatewayRequest.setPaymentStatus(paymentStatus);
			if(paymentStatus != null && paymentStatus.equals(CommonUtils.SUCCESS)) {
				updatePayment = true;
			}else {
				map.put("PaymentError", "Payment is not success");
				return map;
			}
			logger.info("Update payment Status===>{}", updatePayment);
		} catch (Exception e) {
			logger.error("THROW EXCEPTION WHILE UPDATE PAYMENT DETAIL ON GATEWAY  ...Error==>{}",e);
			map.put("Error", e.getMessage());
			return map;
		}
		
		// check payment
		if(gatewayRequest.getPaymentTypeId() == null || gatewayRequest.getSkipPayment() == null ) {
			Map<String, Object> checkTypeOfSkipPayment = checkTypeOfSkipPayment(gatewayRequest);
			gatewayRequest.setPaymentTypeId( (Integer) checkTypeOfSkipPayment.get(PAYMENT_TYPE_ID));
			if(checkTypeOfSkipPayment.containsKey(SKIP_TYPE))
				gatewayRequest.setSkipType(checkTypeOfSkipPayment.get(SKIP_TYPE).toString());
			gatewayRequest.setSkipPayment((Boolean) checkTypeOfSkipPayment.get(SKIP_PAYMENT) );
		}
						
		int rowUpdate = 0 ;
//		if ("SIDBI_FEES".equals(gatewayRequest.getPurposeCode())) {
			
			try {
				rowUpdate = loanApplicationMasterRepository.updatePaymentStatus(gatewayRequest.getStatus(), gatewayRequest.getApplicationId(), true) ;
				logger.info("------------update succesfully payment status in loan master ----------------  on applicationId =>{} updated row => {}" , gatewayRequest.getApplicationId() , rowUpdate);
			}catch (Exception e) {
				logger.error("Error/Exception while updating status in LoanApplicationMaster Table of ApplicationId==>{}" , gatewayRequest.getApplicationId());
			}

			if (CommonUtils.PaymentStatus.SUCCESS.equals(gatewayRequest.getPaymentStatus()) && updatePayment) {
		               
				ProposalDetails proposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(gatewayRequest.getApplicationId(), false );
				if(proposalDetails != null ) {
									
				    //active proposal details
					rowUpdate = activeProposalDetail(gatewayRequest.getApplicationId(), proposalDetails.getFpProductId(),proposalDetails.getId(),gatewayRequest.getBusinessTypeId(),null,gatewayRequest.getPaymentTypeId());
					if(!CommonUtils.isObjectNullOrEmpty(rowUpdate) && rowUpdate == 1) {
						logger.info("Set Proposal Detail Active Status==>{}",rowUpdate);
					}else{
						logger.error("---------------Error in activing proposal detail of applicationId==>{}---------------->",gatewayRequest.getApplicationId());
						map.put("Error", "Error in updating proposal");
						map.put("paymentStatus", gatewayRequest.getPaymentStatus());
						return map;
					}
					
				}else {
					logger.error("---------------Proposal detail is already active or null of applicationId==>{}---------------->",gatewayRequest.getApplicationId());
					map.put("Error", "Proposal detail already active");
					map.put("paymentStatus", gatewayRequest.getPaymentStatus());
					return map;
				}
					
				ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(proposalDetails.getId(), true);
							
				//get Inprinciple Detail
				try {
					map = getInPrincipleDetail(gatewayRequest.getApplicationId() ,null ,null);
				}catch (Exception e) {
					logger.error("Error/Exception while gettting inprinciple detail --------------applicationId==>{} ...Error=>{} ",gatewayRequest.getApplicationId() ,e);
				}
				
				if(gatewayRequest.getPaymentTypeId().equals(CommonUtils.PaymentTypeMaster.ONLINE_PAYMENT.getId())) {
					Map<String, Object> invoiceDetailByApplicationId;
					// invoice
					try {
						invoiceDetailByApplicationId = gatewayClient.getInvoiceDetailByApplicationId(gatewayRequest.getApplicationId());
						if(invoiceDetailByApplicationId != null && !invoiceDetailByApplicationId.isEmpty()) {
							map.put(INVOICE_DATA, invoiceDetailByApplicationId);
						}
					} catch (GatewayException e1) {
						logger.error("Error/Exception while getting invoice and send invoice to FS of applicationId==>{} ...Error==>{}",gatewayRequest.getApplicationId() ,e1);
					}
				}
							
				//send invoice and inprinciple to fs
				Boolean inpStatus = false;
				try {
					logger.info("Calling invoice for applicationId :[{}]",gatewayRequest.getApplicationId());
					inpStatus = sendInprincipleLetterAndSmsToFS(map, gatewayRequest, null);
					logger.info("Inprinciple Email Send status [{}] for applicationId : [{}]",inpStatus,gatewayRequest.getApplicationId());
				}
				catch (Exception e) {
					logger.error("Error/Exception while getting invoice and send invoice to FS of applicationId==>{} ...Error==>{}",gatewayRequest.getApplicationId() ,e);
				}
				
				/** send mail to fp with cam */
				try {
					inpStatus = sendmailToAllFundProvider(map, applicationProposalMapping , gatewayRequest);
					logger.info("CAM Email Send status [{}] for applicationId : [{}]",inpStatus,gatewayRequest.getApplicationId());
				}catch (Exception e) {
					logger.error("--------------Error/Exception in sending mail to fund provider-----------{}",e);
				}						
					
				// =======================================================================================================================================
//				if (map != null) {
//					loanApplicationRequest.setTypeOfLoan(String.valueOf(map.get(CommonUtils.LOAN_TYPE)));
//					loanApplicationRequest.setOnlinePaymentSuccess(updatePayment);
//					loanApplicationRequest.setNameOfEntity(gatewayRequest.getNameOfEntity());
//					loanApplicationRequest.setEmail(gatewayRequest.getEmailAddress());
//					loanApplicationRequest.setMobile(gatewayRequest.getMobileNumber());
//					loanApplicationRequest.setFundProvider(String.valueOf(map.get("organisationName")));
//				    loanApplicationRequest.setAddress(String.valueOf(map.get("address")));
//				}
				
//					callPushPullAPI(proposalDetails.getId() , proposalDetails.getApplicationId(), proposalDetails.getFpProductId(), proposalDetails.getUserOrgId(), gatewayRequest.getBusinessTypeId()); 		
					
				
				logger.info("End of Congratulations");
				
			}else {
				logger.error("--------------Status is not Success---------------");
			}
			map.put("paymentStatus", gatewayRequest.getPaymentStatus());
			//set data in loans
//			loanApplicationRequest.setProfilePrimaryLocked(loanApplicationMaster.getIsPrimaryLocked());
//			loanApplicationRequest.setFinalLocked(loanApplicationMaster.getIsFinalLocked());
//			Map<String ,Object> mapForFsName = gatewayOnlineService.getFSNameAndAddressOnBusinessTypeId(gatewayRequest.getApplicationId() , loanApplicationMaster.getBusinessTypeId(), loanApplicationMaster.getProductId());
//			if(mapForFsName != null && mapForFsName.get(CommonUtils.FS_NAME) != null) {
//				loanApplicationRequest.setUserName(mapForFsName.get(CommonUtils.FS_NAME).toString());
//			}
				
//		}else {
//		  logger.info("-------------------Purpose Code is not Sidbi fees-----------------------");
//		}
			
//		if(updatePayment) {
//			loanApplicationRequest.setPaymentStatus(CommonUtils.PaymentStatus.SUCCESS);
//		}else {
//			loanApplicationRequest.setPaymentStatus(CommonUtils.PaymentStatus.FAILED);
//		}
		
//		if (CommonUtils.isObjectNullOrEmpty(loanApplicationRequest)) {
//			logger.error("Invalid Application Id in Updating Payment Status====>{}",gatewayRequest.getApplicationId());
//			return null;
//		}
		
		logger.info("End updateLoanApplicationMasterPaymentStatus() successfully");
		return map;
		
	}
	@Override
	public Map<String, Object> sendAllMailToFSAndFP(GatewayRequest gatewayRequest,Long proposalId,Boolean forFs,Boolean forFp) {
		logger.info("start updateLoanApplicationMasterPaymentStatus()");
		LoanApplicationMaster loanApplicationMaster = loanApplicationMasterRepository.findByIdAndIsActive(gatewayRequest.getApplicationId(), true );
		
		//set payment status in loan master
		if (loanApplicationMaster == null  ) {
			throw new NullPointerException("Invalid Loan Application ID==>" + gatewayRequest.getApplicationId());
		}
		
		gatewayRequest.setUserId(loanApplicationMaster.getUserId());
		
		Boolean updatePayment = false;
		Map<String, Object> map = new HashMap<>();
		try {
			
			String paymentStatus = commonRepository.getPaymentStatus(gatewayRequest.getApplicationId());
			
			gatewayRequest.setPaymentStatus(paymentStatus);
			if(paymentStatus.equals(CommonUtils.SUCCESS)) {
				updatePayment = true;
			}
			logger.info("Update payment Status===>{}", updatePayment);
		} catch (Exception e) {
			logger.error("THROW EXCEPTION WHILE UPDATE PAYMENT DETAIL ON GATEWAY  ...Error==>{}",e);
			map.put("Error", e.getMessage());
			return map;
		}
		
		// check payment
		if(gatewayRequest.getPaymentTypeId() == null || gatewayRequest.getSkipPayment() == null ) {
			Map<String, Object> checkTypeOfSkipPayment = checkTypeOfSkipPayment(gatewayRequest);
			gatewayRequest.setPaymentTypeId( (Integer) checkTypeOfSkipPayment.get(PAYMENT_TYPE_ID));
			if(checkTypeOfSkipPayment.containsKey(SKIP_TYPE)) { 
				gatewayRequest.setSkipType(checkTypeOfSkipPayment.get(SKIP_TYPE).toString());
			}
			gatewayRequest.setSkipPayment((Boolean) checkTypeOfSkipPayment.get(SKIP_PAYMENT) );
		}
		
		if (CommonUtils.PaymentStatus.SUCCESS.equals(gatewayRequest.getPaymentStatus()) && updatePayment) {
			
			ProposalDetails proposalDetails = null;
			if(proposalId == null) {
			proposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(gatewayRequest.getApplicationId(), true );
			}else {
				proposalDetails = proposalDetailsRepository.findByProposalIdAndApplicationIdAndIsActiveTrue(proposalId, gatewayRequest.getApplicationId());	
			}
			
			
			ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(proposalDetails.getId(), true);
			
			//get Inprinciple Detail
			try {
				map = getInPrincipleDetail(gatewayRequest.getApplicationId() ,null ,null);
			}catch (Exception e) {
				logger.error("Error/Exception while gettting inprinciple detail --------------applicationId==>{} ...Error=>{} ",gatewayRequest.getApplicationId() ,e);
			}
			
			if(gatewayRequest.getPaymentTypeId().equals(CommonUtils.PaymentTypeMaster.ONLINE_PAYMENT.getId())) {
				Map<String, Object> invoiceDetailByApplicationId;
				// invoice
				try {
					invoiceDetailByApplicationId = gatewayClient.getInvoiceDetailByApplicationId(gatewayRequest.getApplicationId());
					if(invoiceDetailByApplicationId != null && !invoiceDetailByApplicationId.isEmpty()) {
						map.put(INVOICE_DATA, invoiceDetailByApplicationId);
					}
				} catch (GatewayException e1) {
					logger.error("Error/Exception while getting invoice and send invoice to FS of applicationId==>{} ...Error==>{}",gatewayRequest.getApplicationId() ,e1);
				}
			}
			
			Boolean inpStatus = false;
			if(forFs == null || forFs) {
				//send invoice and inprinciple to fs
				try {
					logger.info("Calling invoice for applicationId :[{}]",gatewayRequest.getApplicationId());
					inpStatus = sendInprincipleLetterAndSmsToFS(map, gatewayRequest, null);
					logger.info("Inprinciple Email Send status [{}] for applicationId : [{}]",inpStatus,gatewayRequest.getApplicationId());
				}
				catch (Exception e) {
					logger.error("Error/Exception while getting invoice and send invoice to FS of applicationId==>{} ...Error==>{}",gatewayRequest.getApplicationId() ,e);
				}
				
			}
			if(forFp == null || forFp) {			
				/** send mail to fp with cam */
				try {
					inpStatus = sendmailToAllFundProvider(map, applicationProposalMapping , gatewayRequest);
					logger.info("CAM Email Send status [{}] for applicationId : [{}]",inpStatus,gatewayRequest.getApplicationId());
				}catch (Exception e) {
					logger.error("--------------Error/Exception in sending mail to fund provider-----------{}",e);
				}						
				
			}
			// =======================================================================================================================================
			
//					callPushPullAPI(proposalDetails.getId() , proposalDetails.getApplicationId(), proposalDetails.getFpProductId(), proposalDetails.getUserOrgId(), gatewayRequest.getBusinessTypeId()); 		
			
			
			logger.info("End of Congratulations");
			
		}else {
			logger.error("--------------Status is not Success---------------");
			map.put("paymentError", "payment status not Success");
		}
		
		logger.info("End sendInprinciple() successfully");
		return map;
		
	}
	
}
