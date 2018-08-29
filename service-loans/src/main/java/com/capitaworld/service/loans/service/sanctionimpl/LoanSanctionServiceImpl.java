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
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.sanction.LoanSanctionAndDisbursedRequest;
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
import com.capitaworld.sidbi.integration.SidbiIntegerationResponse;
import com.capitaworld.sidbi.integration.client.SidbiIntegrationClient;
import com.capitaworld.sidbi.integration.model.GenerateTokenRequest;
import com.capitaworld.sidbi.integration.util.AESEncryptionUtility;
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

	@Override
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws Exception {
		try {
		logger.info("Enter in saveLoanSanctionDetail() ----------------------->  LoanSanctionRequest==> "+ loanSanctionRequest);
		
		LoanSanctionDomain loanSanctionDomainOld =loanSanctionRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld) ) {
			loanSanctionDomainOld = new LoanSanctionDomain();
			BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld,"id");
			loanSanctionDomainOld.setCreatedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setCreatedDate(new Date());
			loanSanctionDomainOld.setIsActive(true);
		}else{
			BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld,"id");
			loanSanctionDomainOld.setModifiedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setModifiedDate(new Date());
			
			//==================Sending Mail notification to Maker=============================
			
			fpAsyncComponent.sendEmailToMakerHOBOWhenCheckerSanctionLoan(loanSanctionDomainOld);
			
			//=================================================================================
			
		}
		logger.info("Exit saveLoanSanctionDetail() -----------------------> LoanSanctionDomain "+ loanSanctionDomainOld);
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
						
						if(CommonUtils.isObjectNullOrEmpty(isProduction)){
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
							/*token = sidbiIntegrationClient.getToken(generateTokenRequest,generateTokenRequest.getBankToken());
							generateTokenRequest.setToken(token);
							//Getting sanction and disbursement Details from Bank 
							SidbiIntegerationResponse sidbiIntegerationResponse = sidbiIntegrationClient.getSanctionAndDisbursmentDetailList(token, generateTokenRequest.getBankToken());
							if(!CommonUtils.isObjectNullOrEmpty(sidbiIntegerationResponse)) {
								if(!CommonUtils.isObjectNullOrEmpty(sidbiIntegerationResponse.getData())) {
									Object res = sanctionAndDisbursementValidation((String)sidbiIntegerationResponse.getData()); 
									if(res instanceof List){
										List<com.capitaworld.sidbi.integration.model.sanction.LoanSanctionAndDisbursedRequest> list = (List<com.capitaworld.sidbi.integration.model.sanction.LoanSanctionAndDisbursedRequest> )res;
										logger.info("********************************* " + list.size() +" ***********************************");
										if(sidbiIntegrationClient.updateSavedSanctionAndDisbursmentDetailList(list , generateTokenRequest.getToken(), generateTokenRequest.getBankToken())) {
											try {
												//wait foo 15 minute
												logger.info("*******Sucessgfully updated sanction and disbursement details in sidbi integration********** ");
												TimeUnit.MINUTES.sleep(1);
												logger.info("*******Going to Call another Bank Reverse API.********** ");
											}catch (Exception e) {
												logger.info("Error/Exception in for 15 min wait() ----------------------->  Message "+ e.getMessage());
												e.printStackTrace();
											}
										}
										*//*}*//*
									}else {
										logger.info("*******Unable to store sanction or disbursement detail   **********  reasion is =={}", (res != null ? res.toString() : res));
									}
								}
							}*/
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
	
	@SuppressWarnings("unchecked")
	public Object sanctionAndDisbursementValidation(String encryptedString) {
		
		LoansResponse loansResponse = null;
		String sanctionReason = null;
		String disbursementReason = null;
		Long orgId = null;
		String decrypt = null;
		List<LoanSanctionAndDisbursedRequest> loanSanctionAndDisbursedRequestList = null;
		GenerateTokenRequest generateTokenRequest = null;
		String tokenString = null;
		Long applicationId =null;
		try {
			logger.info("=============================Entry saveLoanSanctionDisbursementDetailFromBank(){} ============================= ");
			
			if (encryptedString != null) {
				// Decryption of request
				try {
					decrypt = AESEncryptionUtility.decrypt(encryptedString);
					
					loanSanctionAndDisbursedRequestList = MultipleJSONObjectHelper.getListOfObjects(decrypt,  null ,LoanSanctionAndDisbursedRequest.class);
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.info(
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
				//checking validation 
				for(LoanSanctionAndDisbursedRequest loanSanctionAndDisbursedRequest : loanSanctionAndDisbursedRequestList) {
			
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
								if ("SUCCESS".equalsIgnoreCase(sanctionReason) && saveLoanSanctionDetailById(loanSanctionAndDisbursedRequest.getLoanSanctionRequest())) {
									loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setIsSaved(true);
									if(! CommonUtils.isListNullOrEmpty(loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList())) {
										//	saving disbursement with validation
										disbursementReason = loanDisbursementService.bankRequestValidationAndSave(loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getId() , loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList(), orgId , CommonUtility.ApiType.SANCTION_AND_DISBURSEMENT);
									}
								}
							} else {
								sanctionReason = "Invalid Credentials";
								logger.info(	"Invalid Credentials while saveLoanSanctionDisbursementDetailFromBank() ----------------> orgId "+ orgId + " reason  " + sanctionReason);
								loansResponse = new LoansResponse(sanctionReason, HttpStatus.UNAUTHORIZED.value());
								loansResponse.setData(false);
								logger.info("================== Exit saveLoanDisbursementDetail() =================");
								return sanctionReason;
							}	
//						} else {
//							logger.info("Null in LoanSanctionAndDisbursedRequest while saveLoanSanctionDisbursementDetailFromBank() ----------------> LoanDisbursementRequest"+ loanSanctionAndDisbursedRequest);
//							loansResponse = new LoansResponse("Mandatory Fields Must Not be Null",HttpStatus.BAD_REQUEST.value(), HttpStatus.OK);
//							loansResponse.setData(false);
//							sanctionReason = "Mandatory Fields Must Not be Null while saveLoanSanctionDisbursementDetailFromBank() ===> LoanDisbursementRequest ====> "+ loanSanctionAndDisbursedRequest;
//							return sanctionReason;
//						}
					}else {
						logger.info("SOME of Values from  LoanSanctionAndDisbursedRequest while saveLoanSanctionDisbursementDetailFromBank() ----------------> LoanDisbursementRequest"+ loanSanctionAndDisbursedRequest);
						loansResponse = new LoansResponse("Mandatory Fields Must Not be Null",HttpStatus.BAD_REQUEST.value(), HttpStatus.OK);
						loansResponse.setData(false);
						sanctionReason = "Mandatory Fields Must Not be Null while saveLoanSanctionDisbursementDetailFromBank() ===> LoanDisbursementRequest ====> "+ loanSanctionAndDisbursedRequest;
						return sanctionReason;
					}
				}	
				if ("SUCCESS".equalsIgnoreCase(sanctionReason) || "First Disbursement".equalsIgnoreCase(sanctionReason)) {
					logger.info("Success msg while saveLoanSanctionDisbursementDetailFromBank() ----------------> msg "+ sanctionReason);
					sanctionReason = null;
					loansResponse = new LoansResponse("Information Successfully Stored ",HttpStatus.OK.value());
					if("SUCCESS".equalsIgnoreCase(disbursementReason) || "First Disbursement".equalsIgnoreCase(disbursementReason)){
						loansResponse.setData(CommonUtility.ApiType.SANCTION_AND_DISBURSEMENT);
					}else {
						loansResponse.setData(CommonUtility.ApiType.SANCTION);
					}
					logger.info("Exit saveLoanSanctionDisbursementDetailFromBank() ---------------->  msg ==>"+ "Information Successfully Stored ");
					/*return disbursementReason != null ? CommonUtility.ApiType.SANCTION_AND_DISBURSEMENT.toString() : CommonUtility.ApiType.SANCTION.toString() ;*/
					return loanSanctionAndDisbursedRequestList;
				} else {
					logger.info("Failure msg while save LoanSanctionAndDisbursedRequest in saveLoanSanctionDisbursementDetailFromBank() to CW ----------------> msg "+ sanctionReason);
					loansResponse = new LoansResponse(sanctionReason.split("[\\{}]")[0],HttpStatus.BAD_REQUEST.value());
					loansResponse.setData(false);
					logger.info("Exit saveLoanDisbursementDetail() ----------------> msg ==>" + sanctionReason);
					return sanctionReason;
				}
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
			generateTokenRequest = new GenerateTokenRequest();
			generateTokenRequest.setToken(tokenString);
			auditComponentBankToCW.saveBankToCWReqRes(decrypt != null ? decrypt : encryptedString, 	applicationId,CommonUtility.ApiType.SANCTION_AND_DISBURSEMENT, loansResponse, sanctionReason, orgId);
		}
	}

	@Override
	public Boolean saveLoanSanctionDetailById(LoanSanctionRequest loanSanctionRequest) throws Exception {
		try {
		logger.info("Enter in saveLoanSanctionDetail() ----------------------->  LoanSanctionRequest==> "+ loanSanctionRequest);
		                                                                       
		LoanSanctionDomain loanSanctionDomainOld =loanSanctionRepository.findByBankSanctionPrimaryKeyAndIsActive(loanSanctionRequest.getId(), true);
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld) ) {
			loanSanctionDomainOld = new LoanSanctionDomain();
			loanSanctionDomainOld.setCreatedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setCreatedDate(new Date());
			loanSanctionDomainOld.setIsActive(true);
			BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld, "id" , "createdBy" , "createdDate" , "isActive" , "modifiedBy" , "modifiedDate");
			loanSanctionDomainOld.setBankSanctionPrimaryKey(loanSanctionRequest.getId());
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
}
