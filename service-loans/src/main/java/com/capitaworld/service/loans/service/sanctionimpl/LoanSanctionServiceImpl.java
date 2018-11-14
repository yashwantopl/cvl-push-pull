package com.capitaworld.service.loans.service.sanctionimpl;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.config.AuditComponentBankToCW;
import com.capitaworld.service.loans.config.FPAsyncComponent;
import com.capitaworld.service.loans.domain.BankCWAuditTrailDomain;
import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.sanction.LoanSanctionAndDisbursedRequest;
import com.capitaworld.service.loans.repository.OfflineProcessedAppRepository;
import com.capitaworld.service.loans.repository.banktocw.BankToCWAuditTrailRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;
import com.capitaworld.service.loans.service.sanction.LoanSanctionService;
import com.capitaworld.service.loans.utils.CommonUtility;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserOrganisationRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.utils.OrganisationConfiguration;
import com.capitaworld.sidbi.integration.client.SidbiIntegrationClient;
import com.capitaworld.sidbi.integration.model.GenerateTokenRequest;
import com.capitaworld.sidbi.integration.util.AESEncryptionUtility;
import com.capitaworld.sidbi.integration.util.AESEncryptionUtilitySBI;
/**
 * @author Ankit
 *
 */

@Service
@Transactional
public class LoanSanctionServiceImpl implements LoanSanctionService {
	private static final Logger logger = LoggerFactory.getLogger(LoanSanctionServiceImpl.class);

	@Autowired
	private LoanSanctionRepository loanSanctionRepository;
	
	@Autowired 
	private ProposalDetailsRepository proposalDetailsRepository;
	
	@Autowired
	private UsersClient userClient;
	
	@Value("${capitaworld.sidbi.integration.is_production}")
	private String isProduction; 
	
	@Value("${capitaworld.sidbi.integration.reverse_api_timeout}")
	private String reverseAPITimeOut; 
	
	@Autowired
	private SidbiIntegrationClient sidbiIntegrationClient;
	
	@Autowired
	private AuditComponentBankToCW auditComponentBankToCW;
	
	@Autowired
	private LoanDisbursementService loanDisbursementService;

	@Autowired
	private FPAsyncComponent fpAsyncComponent;
	
	@Autowired
	private BankToCWAuditTrailRepository bankToCWAuditTrailRepository;
	
	@Autowired
	private OfflineProcessedAppRepository offlineProcessedAppRepository;

	@Override
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws Exception {
		try {
		logger.info("Enter in saveLoanSanctionDetail() ----------------------->  LoanSanctionRequest==> "+ loanSanctionRequest);
		
		LoanSanctionDomain loanSanctionDomainOld =loanSanctionRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld) ) {
			loanSanctionDomainOld = new LoanSanctionDomain();
			loanSanctionDomainOld.setOrgId(!CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getOrgId()) ? loanSanctionRequest.getOrgId() : null);
			if(loanSanctionRequest.getIsIneligibleProposal() != null && loanSanctionRequest.getIsIneligibleProposal() == true) {
				loanSanctionDomainOld.setIsSanctionedFrom(loanSanctionRequest.getIsSanctionedFrom());
				IneligibleProposalDetails ineligibleProposalDetails = (IneligibleProposalDetails) offlineProcessedAppRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
				ineligibleProposalDetails.setIsSanctioned(true);
			}else if(CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getIsIneligibleProposal()) || loanSanctionRequest.getIsIneligibleProposal() == false) {
				loanSanctionDomainOld.setIsSanctionedFrom(CommonUtils.sanctionedFrom.ELIGIBLE_USERS);
			}
			BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld,"id");
			loanSanctionDomainOld.setCreatedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setCreatedDate(new Date());
			loanSanctionDomainOld.setIsActive(true);
		}else{
			//BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld,"id");
			if(loanSanctionRequest.getIsIneligibleProposal() != null && loanSanctionRequest.getIsIneligibleProposal()) {
				loanSanctionDomainOld.setIsSanctionedFrom(loanSanctionRequest.getIsSanctionedFrom());
				IneligibleProposalDetails ineligibleProposalDetails = (IneligibleProposalDetails) offlineProcessedAppRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
				ineligibleProposalDetails.setIsSanctioned(true);
			}else {
				loanSanctionDomainOld.setIsSanctionedFrom(CommonUtils.sanctionedFrom.ELIGIBLE_USERS);
			}
			
			loanSanctionDomainOld.setSanctionAmount(loanSanctionRequest.getSanctionAmount());
			loanSanctionDomainOld.setSanctionDate(new Date());
			loanSanctionDomainOld.setTenure(loanSanctionRequest.getTenure());
			loanSanctionDomainOld.setRoi(loanSanctionRequest.getRoi());
			loanSanctionDomainOld.setProcessingFee(loanSanctionRequest.getProcessingFee());
			loanSanctionDomainOld.setRemark(loanSanctionRequest.getRemark());
			loanSanctionDomainOld.setModifiedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setModifiedDate(new Date());
		}
		//==================Sending Mail notification to Maker=============================
		
		fpAsyncComponent.sendEmailToFSWhenCheckerSanctionLoan(loanSanctionDomainOld);
		fpAsyncComponent.sendEmailToMakerHOBOWhenCheckerSanctionLoan(loanSanctionDomainOld);
		
		//=================================================================================
		//logger.info("Exit saveLoanSanctionDetail() -----------------------> LoanSanctionDomain "+ loanSanctionDomainOld);
		return loanSanctionRepository.save(loanSanctionDomainOld) != null;
		}catch (Exception e) {
			logger.info("Error/Exception in saveLoanSanctionDetail() -----------------------> Message "+e.getMessage());
			e.printStackTrace();
			throw e;
		}

	}
	
	@Override
	public String sanctionRequestValidation( Long applicationId,Long orgId) throws Exception {
		logger.info("Enter in requestValidation() ----------------------->  applicationId==> "+ applicationId);
	        try {        	
		 
	        	Long recCount = proposalDetailsRepository.getApplicationIdCountByOrgId(applicationId ,orgId);
	        	if(recCount != null && recCount  > 0) {
	        		return  "SUCCESS";
	        	}else {
	        		return "Invalid ApplicationId ";
	        	}		 
	        }catch (Exception e) {
	        	logger.info("Error/Exception in requestValidation() ----------------------->  Message "+ e.getMessage());
	        	throw e;
			}
	}

	@Override
	public Boolean saveSanctionDetailFromPopup(LoanSanctionRequest loanSanctionRequest) throws Exception {

		logger.info("Enter in saveSanctionDetailFromPopup() ----------------------------- sanctionRequest Data : "+ loanSanctionRequest.toString());
		try {


			logger.info("going to fetch username/password");
			UserOrganisationRequest userOrganisationRequest = userClient.getByOrgId(loanSanctionRequest.getOrgId());
			if(CommonUtils.isObjectListNull( userOrganisationRequest, userOrganisationRequest.getUsername(),  userOrganisationRequest.getPassword() )){
				logger.warn("username/password found null ");
				return false;
			}

			loanSanctionRequest.setUserName(userOrganisationRequest.getUsername());
			loanSanctionRequest.setPassword(userOrganisationRequest.getPassword());
			loanSanctionRequest.setSanctionDate(new Date());

			return saveLoanSanctionDetail(loanSanctionRequest);

		}catch (Exception e) {
			logger.info("Error/Exception in saveSanctionDetailFromPopup() ----------------------->  Message "+ e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean saveSanctionAndDisbursementDetailsFromBank() throws Exception {

		logger.info("================= Enter in saveSanctionAndDisbursementDetailsFromBank() ============================== ");
		try {
			String failureReason = null ; 
			//Getting userOrgDetail List
			UserResponse userResponse = userClient.getOrgList();
			if(!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
				List<Map<String ,  Object>> userOrganisationRequestList = (List<Map<String, Object>>) userResponse.getData();
				if(!CommonUtils.isListNullOrEmpty(userOrganisationRequestList)){
					UserOrganisationRequest	userOrganisationRequest =null;
					for(Map<String , Object> map : userOrganisationRequestList) {
						userOrganisationRequest = (UserOrganisationRequest)	MultipleJSONObjectHelper.getObjectFromMap(map, UserOrganisationRequest.class);
						if(CommonUtils.isObjectNullOrEmpty(userOrganisationRequest.getIsReverseApiActivated()) || !userOrganisationRequest.getIsReverseApiActivated().booleanValue()) {
							logger.info("Organization ID is Not Activated==========>{}=====Name============>{}",userOrganisationRequest.getUserOrgId(),userOrganisationRequest.getOrganisationName());
							continue;
						}
						
						if(!CommonUtils.isObjectNullOrEmpty(isProduction)){
							if(CommonUtils.isObjectNullOrEmpty(userOrganisationRequest.getProductionUrl())) {
								logger.info("Production URL is NULL for Bank==========>{}",userOrganisationRequest.getOrganisationName());
								continue;
							}
							sidbiIntegrationClient.setIntegrationBaseUrl(userOrganisationRequest.getProductionUrl());
						}else {
							if(CommonUtils.isObjectNullOrEmpty(userOrganisationRequest.getUatUrl())) {
								logger.info("UAT URL is NULL for Bank==========>{}",userOrganisationRequest.getOrganisationName());
								continue;
							}
							sidbiIntegrationClient.setIntegrationBaseUrl(userOrganisationRequest.getUatUrl());//
						}
						logger.warn("Getting token from SidbiIntegrationClient --------------" +userOrganisationRequest);
						GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest();
						generateTokenRequest.setApplicationId(1l);
						generateTokenRequest.setPassword(userOrganisationRequest.getPassword());
						
						if(userOrganisationRequest.getUserOrgId() == 17l || userOrganisationRequest.getUserOrgId() == 16l) {
							String reqTok = "bobc:bob12345";
							String requestDataEnc = Base64.getEncoder().encodeToString(reqTok.getBytes());
							generateTokenRequest.setBankToken(requestDataEnc);
						}
						String token=null;
						
						try {
							
							// for syndicate Bank only check orgid
							OrganisationConfiguration organisationConfiguration = MultipleJSONObjectHelper.getObjectFromString(userOrganisationRequest.getConfig(), OrganisationConfiguration.class);
							if(!CommonUtils.isObjectNullOrEmpty(organisationConfiguration) && organisationConfiguration.getIsSSL()){
								System.setProperty("javax.net.ssl.keyStore",  organisationConfiguration.getKeyStore());                                    
								System.setProperty("javax.net.ssl.keyStorePassword", organisationConfiguration.getKeyStorePassword());              
								System.setProperty("javax.net.ssl.keyStoreType",  organisationConfiguration.getKeyStoreType());            
							}
							token = sidbiIntegrationClient.getToken(generateTokenRequest,generateTokenRequest.getBankToken() , userOrganisationRequest.getCodeLanguage());
							generateTokenRequest.setToken(token);
							//Getting sanction and disbursement Details from Bank 
							String encryptedString = sidbiIntegrationClient.getSanctionAndDisbursmentDetailList(token, generateTokenRequest.getBankToken() , userOrganisationRequest.getCodeLanguage());
							if(!CommonUtils.isObjectNullOrEmpty(encryptedString)) {
								Object res = sanctionAndDisbursementValidation(encryptedString , userOrganisationRequest.getCodeLanguage());
								try {
									
									sidbiIntegrationClient.setTokenAsExpired(generateTokenRequest, generateTokenRequest.getBankToken(), userOrganisationRequest.getCodeLanguage());

								} catch (Exception e) {
									logger.info("---------- Error/Exception while expirim token ------------ " +e.getMessage());
								}
								if(res instanceof List){
									List<LoanSanctionAndDisbursedRequest> list = (List<LoanSanctionAndDisbursedRequest> )res;
									List<com.capitaworld.sidbi.integration.model.sanction.LoanSanctionAndDisbursedRequest> list1   = null ;
									try {
										if(!CommonUtils.isObjectListNull(list)&& !CommonUtils.isObjectNullOrEmpty(list.get(0))) {
											generateTokenRequest.setApplicationId(list.get(0).getApplicationId());
											token = sidbiIntegrationClient.getToken(generateTokenRequest,generateTokenRequest.getBankToken() , userOrganisationRequest.getCodeLanguage());
											logger.info("********************************* " + list.size() +" ***********************************");
											String json = MultipleJSONObjectHelper.getStringfromObject(list);
											list1 = MultipleJSONObjectHelper.getListOfObjects(json, null, com.capitaworld.sidbi.integration.model.sanction.LoanSanctionAndDisbursedRequest.class);
										}
									}catch (Exception e) {
										logger.info("------------------ Error/Exception while getting appication from getSanctionAndDisbursmentDetailList ------------ MSG =>" + e.getMessage());	
									}
									if(sidbiIntegrationClient.updateSavedSanctionAndDisbursmentDetailList(list1 , token, generateTokenRequest.getBankToken() , userOrganisationRequest.getCodeLanguage())) {
										try {
											//wait for 20 Second
											logger.info("*******Sucessgfully updated sanction and disbursement details in sidbi integration********** ");
											TimeUnit.SECONDS.sleep(20);
											logger.info("*******Going to Call another Bank Reverse API.********** ");
										}catch (Exception e) {
											logger.info("Error/Exception in for 20 second wait() ----------------------->  Message "+ e.getMessage());
											e.printStackTrace();
										}
									}
								}else {
									logger.info("*******Unable to store sanction or disbursement detail   **********  reasion is =={}", (res != null ? res.toString() : res));
									 failureReason = "*******Unable to store sanction or disbursement detail   **********  reasion is =={}" + res != null ? res.toString() :  res+"" ;
								}
							}else {
								logger.info("*******Null in getting sanction or disbursement detail  from  bank side  **********  reasion is ==> ", encryptedString +" org id ==> " + userOrganisationRequest.getUserOrgId() );
								 failureReason ="*******Null in getting sanction or disbursement detail  from  bank side  **********  org id ==> " + userOrganisationRequest.getUserOrgId();
								
							}
							auditComponentBankToCW.saveBankToCWReqRes(encryptedString, null, null, null, failureReason, userOrganisationRequest.getUserOrgId(), null);
						}catch(Exception e) {
							e.printStackTrace();
							logger.error("Error while Calling get token API");
						}
					}
				}
			}
			logger.info("going to fetch username/password");
			return false;
		}catch (Exception e) {
			logger.info("Error/Exception in saveSanctionAndDisbursementDetailsFromBank() ----------------------->  Message "+ e.getMessage());
			e.printStackTrace();
			return false;
		}

	}
	
	@SuppressWarnings({ "unchecked", "null" })
	public Object sanctionAndDisbursementValidation(String encryptedString , Integer codeLonguage) {
		
		LoansResponse loansResponse = null;
		String sanctionReason = null;
		String disbursementReason = null;
		Long orgId = null;
		String decrypt = null;
		List<LoanSanctionAndDisbursedRequest> loanSanctionAndDisbursedRequestList = null;
		Long applicationId =null;
		Boolean isSanctionSuccess = false; 
		try {
			logger.info("=============================Entry saveLoanSanctionDisbursementDetailFromBank(){} ============================= ");
			
			if (encryptedString != null) {
				// Decryption of request
				try {
					if(codeLonguage == com.capitaworld.sidbi.integration.util.CommonUtils.CodeLangType.JAVA) {
						decrypt = AESEncryptionUtilitySBI.decrypt(encryptedString);
					}else if(codeLonguage == com.capitaworld.sidbi.integration.util.CommonUtils.CodeLangType.DOT_NET ) {
						decrypt = AESEncryptionUtility.decrypt(encryptedString);
					}
					loanSanctionAndDisbursedRequestList = MultipleJSONObjectHelper.getListOfObjects(decrypt,  null ,LoanSanctionAndDisbursedRequest.class);
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(
							"Error while Converting Encrypted Object to LoanSanctionAndDisbursedRequest  saveLoanSanctionDisbursementDetailFromBank(){} -------------------------> ",
							e.getMessage());
					loansResponse = new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value(),
							HttpStatus.OK);
					loansResponse.setData(false);
					if (CommonUtils.isObjectNullOrEmpty(decrypt)) {
						sanctionReason = "ERROR WHILE DECRYPT ENCRYPTED OBJECT   ====> Msg ===> ";
					} else {
						sanctionReason = "error while converting decrypt string to profileReqRes ====> Msg ===> ";
					}
					sanctionReason += e.getMessage();
					return sanctionReason;
				}
				int rowUpdated = 0 ;
				String jsonString = null ;
				//checking validation 
				for(LoanSanctionAndDisbursedRequest loanSanctionAndDisbursedRequest : loanSanctionAndDisbursedRequestList) {
					BankCWAuditTrailDomain bankCWAuditTrailDomain = null; 
					if (!CommonUtils.isObjectListNull(loanSanctionAndDisbursedRequest,
						loanSanctionAndDisbursedRequest.getApplicationId(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getAccountNo(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getApplicationId(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getBranch(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getRoi(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getSanctionAmount(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getSanctionDate(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getTenure(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getUserName(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getPassword()/*,
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getReferenceNo(),
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getActionBy()*/)) {
//						if(!CommonUtils.isObjectNullOrEmpty(loanSanctionAndDisbursedRequest)){
							applicationId = loanSanctionAndDisbursedRequest.getApplicationId();
							//	checking validation for right organization
							orgId = auditComponentBankToCW.getOrgIdByCredential(loanSanctionAndDisbursedRequest.getUserName(),loanSanctionAndDisbursedRequest.getPassword());
							
							if (!CommonUtils.isObjectNullOrEmpty(orgId)) {
								//		saving sanction with validation
								sanctionReason = sanctionRequestValidation(loanSanctionAndDisbursedRequest.getApplicationId(), orgId);
								
								if("SUCCESS".equalsIgnoreCase(sanctionReason) ) {
									bankCWAuditTrailDomain = bankToCWAuditTrailRepository.findByApplicationIdAndOrgIdAndApiTypeAndBankPrimaryKeyAndIsActive(loanSanctionAndDisbursedRequest.getApplicationId(), orgId, CommonUtility.ApiType.REVERSE_SANCTION , loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getId() , true);
									if(CommonUtils.isObjectNullOrEmpty(bankCWAuditTrailDomain)) {
										isSanctionSuccess = saveLoanSanctionDetailById(loanSanctionAndDisbursedRequest.getLoanSanctionRequest());
										rowUpdated =  proposalDetailsRepository.updateSanctionStatus(13l, loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getApplicationId());
										
										loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.SUCCESS);	
										logger.info("------------------------- saving sanction detail of reverse api--------------- isSuccess ==> " + isSanctionSuccess +" ----------- updating the proposal detail table row " + rowUpdated);
									}else {
										loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.ALREADY_DONE_SANCTION);
										logger.info("------------------------- already save sanction detail of reverse api---------------");
									}
									loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setIsSaved(isSanctionSuccess);
									loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setReason(sanctionReason);
								
									//	saving disbursement with validation
									try {
									
										jsonString = MultipleJSONObjectHelper.getStringfromObject(loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList());
									
									} catch (Exception e) {
										logger.error("--------------Error/Eception while converting object to String  ------------ MSG => "+ e.getMessage());
									}
									if(! CommonUtils.isListNullOrEmpty(loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList())) {
										loanSanctionAndDisbursedRequest.setLoanDisbursementRequestsList( loanDisbursementService.bankRequestValidationAndSave(loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getId() , loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList(), orgId , CommonUtility.ApiType.REVERSE_DISBURSEMENT)) ;
										if(!CommonUtils.isListNullOrEmpty(loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList())){
											disbursementReason = "SUCCESS";
											auditComponentBankToCW.saveBankToCWReqRes(jsonString, loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getApplicationId() , CommonUtility.ApiType.REVERSE_DISBURSEMENT , null, disbursementReason , orgId, null);
										}
									}else {
										disbursementReason = "No Disbursement has done for that aplicaiotnId "+ loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getApplicationId() +" from bank Side and this sanction detail loanSanctionDetail => " + loanSanctionAndDisbursedRequest.getLoanSanctionRequest().toString() ;
										auditComponentBankToCW.saveBankToCWReqRes(jsonString, loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getApplicationId() , CommonUtility.ApiType.REVERSE_DISBURSEMENT , null, disbursementReason , orgId, null);
									} 
								
									
								}else {
									loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setIsSaved(false);
									loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.INVALID_APPLICATION_ID);
									loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setReason(sanctionReason);
									logger.info("------------------------- unable to save reverse sanction details reason ==> "+ sanctionReason);
								}
								
							} else {
								sanctionReason = "Invalid Credentials";
								logger.info(	"Invalid Credentials while saveLoanSanctionDisbursementDetailFromBank() ----------------> orgId "+ orgId + " reason  " + sanctionReason);
								loansResponse = new LoansResponse(sanctionReason, HttpStatus.UNAUTHORIZED.value());
								loansResponse.setData(false);
								loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setIsSaved(false);
								loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.INVALID_CREDENTIAL);
								loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setReason(sanctionReason);
								logger.info("================== Exit saveLoanDisbursementDetail() =================");
								//return sanctionReason;
							}	
//						} else {
//							logger.info("Null in LoanSanctionAndDisbursedRequest while saveLoanSanctionDisbursementDetailFromBank() ----------------> LoanDisbursementRequest"+ loanSanctionAndDisbursedRequest);
//							loansResponse = new LoansResponse("Mandatory Fields Must Not be Null",HttpStatus.BAD_REQUEST.value(), HttpStatus.OK);
//							loansResponse.setData(false);
//							sanctionReason = "Mandatory Fields Must Not be Null while saveLoanSanctionDisbursementDetailFromBank() ===> LoanDisbursementRequest ====> "+ loanSanctionAndDisbursedRequest;
//							return sanctionReason;
//						}
					}else {
						
						logger.info("SOME of Values from  LoanSanctionAndDisbursedRequest while saveLoanSanctionDisbursementDetailFromBank() ----------------> LoanDisbursementRequest"+ loanSanctionAndDisbursedRequest.getLoanSanctionRequest());
						loansResponse = new LoansResponse("Mandatory Fields Must Not be Null",HttpStatus.BAD_REQUEST.value(), HttpStatus.OK);
						loansResponse.setData(false);
						sanctionReason = "Mandatory Fields Must Not be Null while saveLoanSanctionDisbursementDetailFromBank() ===> LoanDisbursementRequest ====> "+ loanSanctionAndDisbursedRequest.getLoanSanctionRequest();
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.MANDAROTY_FIELD_MUST_NOT_BE_NULL);
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setReason(sanctionReason);
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setIsSaved(false);
						//return sanctionReason;
					}
					
					//saving req in bank to  cw-audit table
					/*if(!CommonUtils.isObjectNullOrEmpty(loanSanctionAndDisbursedRequest.getLoanSanctionRequest()) && isSanctionSuccess && CommonUtils.isObjectNullOrEmpty(bankCWAuditTrailDomain)) {
						String 
						auditComponentBankToCW.saveBankToCWReqRes(jsonString , 	applicationId,CommonUtility.ApiType.REVERSE_SANCTION, loansResponse, sanctionReason, orgId  , loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getId());
					}*/
					jsonString = MultipleJSONObjectHelper.getStringfromObject(loanSanctionAndDisbursedRequest.getLoanSanctionRequest());
					auditComponentBankToCW.saveBankToCWReqRes(jsonString , 	applicationId,CommonUtility.ApiType.REVERSE_SANCTION, loansResponse, sanctionReason, orgId  , loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getId());
					
				}	
				logger.info("Msg while saveLoanSanctionDisbursementDetailFromBank() ----------------> Sanction Reason => "+ sanctionReason +"  Disbursement reason =>" +  disbursementReason) ;
					
				if( "SUCCESS".equalsIgnoreCase(sanctionReason) && "SUCCESS".equalsIgnoreCase(disbursementReason)){
					sanctionReason = null;
					loansResponse = new LoansResponse("Information Successfully Stored ",HttpStatus.OK.value());
					loansResponse.setData(CommonUtility.ApiType.REVERSE_SANCTION_AND_DISBURSEMENT);
					logger.info("------------------- reverse sanction and disbursement  is saved--------------");
					//return loanSanctionAndDisbursedRequestList;
				}else if( "SUCCESS".equalsIgnoreCase(sanctionReason) ){
					loansResponse = new LoansResponse("Information Successfully Stored ",HttpStatus.OK.value());
					loansResponse.setData(CommonUtility.ApiType.REVERSE_SANCTION);
					logger.info("-------------------only  reverse sanction is saved--------------");
					//return loanSanctionAndDisbursedRequestList;
				}else if("SUCCESS".equalsIgnoreCase(disbursementReason) || "First Disbursement".equalsIgnoreCase(disbursementReason)){
					loansResponse = new LoansResponse("Information Successfully Stored ",HttpStatus.OK.value());
					loansResponse.setData(CommonUtility.ApiType.REVERSE_DISBURSEMENT);
					logger.info("-------------------onluy  reverse disbursement is saved--------------");
					//return loanSanctionAndDisbursedRequestList;
				} else {
					logger.info("Failure msg while save LoanSanctionAndDisbursedRequest in saveLoanSanctionDisbursementDetailFromBank() to CW ----------------> msg "+ sanctionReason);
					loansResponse = new LoansResponse(sanctionReason.split("[\\{}]")[0],HttpStatus.BAD_REQUEST.value());
					loansResponse.setData(false);
					logger.info("Exit saveLoanDisbursementDetail() ----------------> Sanction Reason => "+ sanctionReason +"  Disbursement reason =>" +  disbursementReason);
					//return loanSanctionAndDisbursedRequestList;
				}
				return loanSanctionAndDisbursedRequestList;
			} else {
				logger.info("Null in encryptedString while saveLoanSanctionDisbursementDetailFromBank() ----------------> encryptedString "+ encryptedString);
				loansResponse = new LoansResponse("Mandatory Fields Must Not be Null", HttpStatus.BAD_REQUEST.value(),HttpStatus.OK);
				loansResponse.setData(false);
				sanctionReason = "Null in encryptedString while saveLoanSanctionDisbursementDetailFromBank() encryptedString ====>"+ encryptedString;
				
				return sanctionReason;  
			}
			
		} catch (Exception e) {
			logger.error("Error while saveLoanSanctionDisbursementDetailFromBank()----------------------> ", e);
			e.printStackTrace();
			loansResponse = new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK);
			loansResponse.setData(false);
			sanctionReason = "Error while save LoanSanctionAndDisbursedRequest in  saveLoanSanctionDisbursementDetailFromBank() ===> Msg "+ e.getMessage();
			return  sanctionReason;
		} finally {
			logger.info("Saving Request to DB ===> ");
			auditComponentBankToCW.saveBankToCWReqRes(decrypt != null ? decrypt : encryptedString, 	null ,CommonUtility.ApiType.REVERSE_SANCTION_AND_DISBURSEMENT, loansResponse, " ** Whole Request with reason ** sanctionReason => "+sanctionReason +" disbursementReason =>" + disbursementReason, orgId , null);
		}
	}

	@Override
	public Boolean saveLoanSanctionDetailById(LoanSanctionRequest loanSanctionRequest) throws Exception {
		try {
		logger.info("Enter in saveLoanSanctionDetail() ----------------------->  LoanSanctionRequest==> "+ loanSanctionRequest);
		                                                                       
		LoanSanctionDomain loanSanctionDomainOld =loanSanctionRepository.findByBankSanctionPrimaryKeyAndIsActiveAndApplicationId(loanSanctionRequest.getId(), true,loanSanctionRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld) ) {
			loanSanctionDomainOld = new LoanSanctionDomain();
			loanSanctionDomainOld.setCreatedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setCreatedDate(new Date());
			loanSanctionDomainOld.setIsActive(true);
			BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld, "id" , "createdBy" , "createdDate" , "isActive" , "modifiedBy" , "modifiedDate");
			loanSanctionDomainOld.setBankSanctionPrimaryKey(loanSanctionRequest.getId());
			loanSanctionDomainOld.setIsSanctionedFrom(CommonUtils.sanctionedFrom.FROM_API);
			logger.info("Exit saveLoanSanctionDetail() -----------------------> LoanSanctionDomain "+ loanSanctionDomainOld);
			return loanSanctionRepository.save(loanSanctionDomainOld) != null;
		}/*else{
			loanSanctionDomainOld.setModifiedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setModifiedDate(new Date());
		}
		BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld, "id" , "createdBy" , "createdDate" , "isActive" , "modifiedBy" , "modifiedDate");
		loanSanctionDomainOld.setBankSanctionPrimaryKey(loanSanctionRequest.getId());
		logger.info("Exit saveLoanSanctionDetail() -----------------------> LoanSanctionDomain "+ loanSanctionDomainOld);
		return loanSanctionRepository.save(loanSanctionDomainOld) != null;*/
		return true;
		}catch (Exception e) {
			logger.info("Error/Exception in saveLoanSanctionDetail() -----------------------> Message "+e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public String getToken(String url , GenerateTokenRequest generateTokenRequest , Integer langCode  ) throws Exception {
		sidbiIntegrationClient.setIntegrationBaseUrl(url);
		String reqTok = "bobc:bob12345";
		String requestDataEnc = Base64.getEncoder().encodeToString(reqTok.getBytes());
		generateTokenRequest.setBankToken(requestDataEnc);
		
		return sidbiIntegrationClient.getToken(generateTokenRequest, generateTokenRequest.getBankToken(), langCode); 
	}

}
