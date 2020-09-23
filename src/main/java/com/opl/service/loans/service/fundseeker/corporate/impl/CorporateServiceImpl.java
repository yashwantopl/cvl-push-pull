package com.opl.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.opl.mudra.api.analyzer.model.common.AnalyzerResponse;
import com.opl.mudra.api.common.CommonResponse;
import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.common.MultipleJSONObjectHelper;
import com.opl.mudra.api.connect.ConnectResponse;
import com.opl.mudra.api.gst.model.GstResponse;
import com.opl.mudra.api.gst.model.yuva.request.GSTR1Request;
import com.opl.mudra.client.analyzer.AnalyzerClient;
import com.opl.mudra.client.connect.ConnectClient;
import com.opl.mudra.client.gst.GstClient;
import com.opl.mudra.client.itr.ITRClient;
import com.opl.profile.api.model.LoanMappingRequest;
import com.opl.profile.api.model.ProfileVerMapRequest;
import com.opl.profile.api.utils.ProfileUtils.ProfileModuleType;
import com.opl.profile.client.ProfileClient;
import com.opl.service.loans.domain.CommonAuditTable;
import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.opl.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.opl.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.opl.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.opl.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.opl.service.loans.repository.CommonAuditTableRepository;
import com.opl.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.opl.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.opl.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.opl.service.loans.service.fundseeker.corporate.CorporateService;

@Service
@Transactional
public class CorporateServiceImpl implements CorporateService {
	
	private static final Logger logger = LoggerFactory.getLogger(CorporateServiceImpl.class);
	
	public static final String ERROR_MSG = "The application has encountered an error, please try again after sometime!!!";
	
	public static final String BAD_REQUEST = "Invalid Request";
	
	
	public static final String MODULE_TYPE = "moduleType";
	public static final String MODULE_NAME = "moduleName";
	public static final String DETAIL_IMG_PATH = "detailImg";
	public static final String DETAIL_VALID_UP_TO = "detailvalidUpto";
	public static final String DESCRIPTION = "description";
	public static final String TITLE = "title";
	public static final String COMPLETED = "completed";
	public static final String ACTIVAE = "active";
	public static final String MANUAL_BS_DATA = "manualBSData";
	public static final String BS_MANUAL_FILL = "isManualUpload";

	@Autowired
	private CorporateApplicantDetailRepository corpApplicantDetailRepo;
	
	@Autowired
	private ApplicationProposalMappingRepository appProMappingRepo;
	
	@Autowired
	private LoanApplicationRepository loanAppRepo;
	
	@Autowired
	private OperatingStatementDetailsRepository osCMADetailsRepo;
	
	@Autowired
	private AssetsDetailsRepository assetCMADetailsRepo;
	
	@Autowired
	private LiabilitiesDetailsRepository liabilityCMADetailsRepo;
	
	@Autowired
	private ProfileClient profileClient;
	
	@Autowired
	private ITRClient itrClient;
	
	@Autowired
	private AnalyzerClient analyzerClient;
	
	@Autowired
	private GstClient gstClient;
	
	@Autowired
	private ConnectClient connectClient;
	
	@Autowired
	private DirectorBackgroundDetailsRepository backgroundDetailsRepository;
	
	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateDetailRepository;
	
	@Autowired
	private CommonAuditTableRepository auditTableRepository;
	
	/**
	 * CHECK ALL DATA ENTERY IN DATABASE IS CORRECT OR WRONG AND
	 * COPY APPLICANT DATA WITH NEW PROPOSAL ID FOR MULTIPLE BANK PURPOSE
	 */
	@Override
	public CommonResponse copyMsmeDataWithProposalId (Long applicationId, Long proposalId) {
		try {
			// ================= START CHECK BELOW DETAILS ARE NOT NULL ==============================
			LoanApplicationMaster loanAppMaster = loanAppRepo.findOne(applicationId);
			if(loanAppMaster ==  null) {
				logger.error("END LoanApplicationMaster Is null Or Empty with ApplicationId ========>" + applicationId);
				return new CommonResponse(BAD_REQUEST + ", ApplicationId Is Invalid!!", HttpStatus.BAD_REQUEST.value(), Boolean.FALSE);
			}
			
			CorporateApplicantDetail corpAppPropDetails = corpApplicantDetailRepo.getByAppIdAndProposalId(applicationId, proposalId);
			if(corpAppPropDetails !=  null) {
				logger.error("END Already Entry Exist in CorporateApplicantDetails with ApplicationId ========>" + applicationId + " === ProposalId ====" + proposalId);
				return new CommonResponse("Already Saved Data With ProposalId!!", HttpStatus.OK.value(), Boolean.TRUE);
			}
			
			List<OperatingStatementDetails> osListWithProposalId = osCMADetailsRepo.getByApplicationIdAndProposalId(applicationId, proposalId);
			if(osListWithProposalId.size() > 0) {
				logger.error("END Already Entry Exist in OperatingStatementDetails with ApplicationId ========>" + applicationId + " === ProposalId ====" + proposalId);
				return new CommonResponse(BAD_REQUEST + ", Operating Statement Details Is Already Saved With ProposalId!!", HttpStatus.BAD_REQUEST.value(), Boolean.FALSE);
			}
			
			List<AssetsDetails> assetListWithProposalId = assetCMADetailsRepo.getByApplicationIdAndProposalId(applicationId, proposalId);
			if(assetListWithProposalId.size() > 0) {
				logger.error("END Already Entry Exist in AssetsDetails with ApplicationId ========>" + applicationId + " === ProposalId ====" + proposalId);
				return new CommonResponse(BAD_REQUEST + ", Assets Details Is Already Saved With ProposalId!!", HttpStatus.BAD_REQUEST.value(), Boolean.FALSE);
			}
			
			List<LiabilitiesDetails> liabilityListWithProposalId = liabilityCMADetailsRepo.getByApplicationIdAndProposalId(applicationId, proposalId);
			if(liabilityListWithProposalId.size() > 0) {
				logger.error("END Already Entry Exist in LiabilitiesDetails with ApplicationId ========>" + applicationId + " === ProposalId ====" + proposalId);
				return new CommonResponse(BAD_REQUEST + ", Liability Details Is Already Saved With ProposalId!!", HttpStatus.BAD_REQUEST.value(), Boolean.FALSE);
			}
			
			List<OperatingStatementDetails> osList = osCMADetailsRepo.getByApplicationIdAndProposalIdNULL(applicationId);
			if(osList.size() == 0) {
				logger.error("END OperatingStatementDetails Is null Or Empty with ApplicationId ========>" + applicationId);
				return new CommonResponse(BAD_REQUEST + ", Operating Statement Details Is Null Or Empty!!", HttpStatus.BAD_REQUEST.value(), Boolean.FALSE);
			}
			
			List<AssetsDetails> assetList = assetCMADetailsRepo.getByApplicationIdAndProposalIdNULL(applicationId);
			if(assetList ==  null) {
				logger.error("END AssetsDetails Is null Or Empty with ApplicationId ========>" + applicationId);
				return new CommonResponse(BAD_REQUEST + ", Asset Details Is Null Or Empty!!", HttpStatus.BAD_REQUEST.value(), Boolean.FALSE);
			}
			
			List<LiabilitiesDetails> liabilityList = liabilityCMADetailsRepo.getByApplicationIdAndProposalIdNULL(applicationId);
			if(liabilityList ==  null) {
				logger.error("END LiabilitiesDetails Is null Or Empty with ApplicationId ========>" + applicationId);
				return new CommonResponse(BAD_REQUEST + ", Liability Details Is Null Or Empty!!", HttpStatus.BAD_REQUEST.value(), Boolean.FALSE);
			}
			// ================= END CHECK BELOW DETAILS WITH PROPOSAL ID ==============================
			
			
			// ==================== START COPY DATA WITH PROPOSAL ID ==========================================
			
			ApplicationProposalMapping appProposalMapping = appProMappingRepo.getByProposalIdAndApplicationId(proposalId, applicationId);
			if(appProposalMapping ==  null) {
				logger.error("END ApplicationProposalMapping Is Null Or Empty with ApplicationId ========>" + applicationId + " === ProposalId ====" + proposalId);
				return new CommonResponse(BAD_REQUEST + ", ApplicationId And ProposalId Mapping Is Invalid!!", HttpStatus.BAD_REQUEST.value(), Boolean.FALSE);
			}
			appProposalMapping.setPaymentStatus(loanAppMaster.getPaymentStatus());
			appProposalMapping.setModifiedDate(new Date());
			appProMappingRepo.save(appProposalMapping);
			
			CorporateApplicantDetail corpAppDetails = corpApplicantDetailRepo.getByApplicationIdAndIsAtive(applicationId);
			if(corpAppDetails ==  null) {
				logger.error("END CorporateApplicantDetail Is null Or Empty with ApplicationId ========>" + applicationId);
				return new CommonResponse(BAD_REQUEST + ", Corporate Applicant Details Is Null Or Empty!!", HttpStatus.BAD_REQUEST.value(), Boolean.FALSE);
			}
			
			CorporateApplicantDetail newCorpAppDetails = new CorporateApplicantDetail();
			BeanUtils.copyProperties(corpAppDetails, newCorpAppDetails);
			newCorpAppDetails.setId(null);
			newCorpAppDetails.setApplicationProposalMapping(appProposalMapping);
			newCorpAppDetails.setCreatedDate(new Date());
			newCorpAppDetails.setModifiedDate(new Date());
			corpApplicantDetailRepo.save(newCorpAppDetails);
			
			OperatingStatementDetails newOsDetails = null;
			for(OperatingStatementDetails osDetails : osList) {
				newOsDetails = new OperatingStatementDetails();
				BeanUtils.copyProperties(osDetails, newOsDetails);
				newOsDetails.setId(null);
				newOsDetails.setCreatedDate(new Date());
				newOsDetails.setModifiedDate(new Date());
				newOsDetails.setApplicationProposalMapping(appProposalMapping);
				osCMADetailsRepo.save(newOsDetails);
			}
			
			AssetsDetails newAssetDetails = null;
			for(AssetsDetails assetDetails : assetList) {
				newAssetDetails = new AssetsDetails();
				BeanUtils.copyProperties(assetDetails, newAssetDetails);
				newAssetDetails.setId(null);
				newAssetDetails.setCreatedDate(new Date());
				newAssetDetails.setModifiedDate(new Date());
				newAssetDetails.setApplicationProposalMapping(appProposalMapping);
				assetCMADetailsRepo.save(newAssetDetails);
			}
			
			LiabilitiesDetails newLiabilitiesDetails = null;
			for(LiabilitiesDetails liabilitiesDetails : liabilityList) {
				newLiabilitiesDetails = new LiabilitiesDetails();
				BeanUtils.copyProperties(liabilitiesDetails, newLiabilitiesDetails);
				newLiabilitiesDetails.setId(null);
				newLiabilitiesDetails.setCreatedDate(new Date());
				newLiabilitiesDetails.setModifiedDate(new Date());
				newLiabilitiesDetails.setApplicationProposalMapping(appProposalMapping);
				liabilityCMADetailsRepo.save(newLiabilitiesDetails);
			}
			// ==================== END COPY DATA WITH PROPOSAL ID ==========================================
			logger.info("END SuccessFully Update Copy Data With ProposalId ===>" + proposalId + " With ApplicationId ====>" + applicationId);
			return new CommonResponse("Successfully updated data!!", HttpStatus.OK.value(), Boolean.TRUE);
		} catch (Exception e) {
			logger.error("END Exception while Copy MSME Data With Proposal Id =========>" , e);
			return new CommonResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), Boolean.FALSE);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getLoanTabsData(Long profileId, Long applicationId,Long userId) throws Exception {
		com.opl.profile.api.model.CommonResponse commRes = profileClient.getProfileVersionDetails(profileId);
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		ProfileVerMapRequest profileRes = null;
		if(commRes == null || CommonUtils.isObjectNullOrEmpty(commRes.getData())) {
//			throw new Exception("Profile Data not Found !!");
			logger.warn("Profile Data not Found !!");
		} else {
			profileRes = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) commRes.getData(), ProfileVerMapRequest.class);
		}

		// SET GST DATA
		Map<String, Object> gstMap = new HashMap<String, Object>();
		gstMap.put(MODULE_TYPE, ProfileModuleType.GST);
		gstMap.put(MODULE_NAME, "Sales Details");
		gstMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/gst-icon-gray.svg");
		gstMap.put(COMPLETED, Boolean.FALSE);
		gstMap.put(ACTIVAE, Boolean.TRUE);
		
		if(!CommonUtils.isObjectNullOrEmpty(profileRes) && !CommonUtils.isObjectNullOrEmpty(profileRes.getGstId())) {
			gstMap.put(DETAIL_VALID_UP_TO, "Details Valid Up To 1 Month");
			gstMap.put(DESCRIPTION, profileRes.getGstOrgName());
			gstMap.put(TITLE, "Company Name");
			try {
				GSTR1Request gstReq = new GSTR1Request();
				gstReq.setProfileId(profileId);
				gstReq.setGstId(profileRes.getGstId());
				gstReq.setIsProceedCopyData(Boolean.FALSE);
				gstReq.setUserId(userId);
				GstResponse gstRes = gstClient.checkGstDataUpdatedOrNot(gstReq);
				if(gstRes != null && gstRes.getData() != null && gstRes.getStatus() == 200) {
					Map<String, Object> map = (Map<String, Object>) gstRes.getData();
					Boolean isDataUpdated = false;
					if(map.get("isDataUpdated") instanceof String) {					
						isDataUpdated = Boolean.parseBoolean((String)map.get("isDataUpdated"));
					}else {
						isDataUpdated = (Boolean)map.get("isDataUpdated");
					}
					if(isDataUpdated) {
						gstMap.put(COMPLETED, Boolean.TRUE);
						gstMap.put(DETAIL_VALID_UP_TO, (String) map.get("message"));
                        gstMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/gst-icon-blue.svg");
					} else {
						gstMap.put(DETAIL_VALID_UP_TO, "Update GST Details");
					}
				}else {
					logger.info("data not found from gst client :: " + profileId);
				}
			} catch (Exception e) {
				logger.error("Exception while Check GST data updated or Not " , e);
			}
		}
		
		mapList.add(gstMap);
		
		//SET ITR DATA
		Map<String, Object> itrMap = new HashMap<String, Object>();
		itrMap.put(MODULE_TYPE, ProfileModuleType.ITR);
		itrMap.put(MODULE_NAME, "Income Tax Return");
		itrMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/income-tax-icon-gray.svg");
		itrMap.put(COMPLETED, Boolean.FALSE);
		itrMap.put(ACTIVAE, Boolean.TRUE);
		if(!CommonUtils.isObjectNullOrEmpty(profileRes) && !CommonUtils.isObjectNullOrEmpty(profileRes.getItrId())) {
			itrMap.put(DESCRIPTION, profileRes.getItrYearRange());
			if (profileRes.getItrYearRange() != null) {
				Integer itrYearCount = 1;
				String[] split = profileRes.getItrYearRange().split("-");
				if (split.length > 1) {
					Integer firstYear = Integer.parseInt(split[0]);
					Integer secondYear = Integer.parseInt(split[1]);
					itrYearCount = ((secondYear - firstYear) == 1) ? 2 : 3;
				}
				itrMap.put(TITLE, "Last " + itrYearCount + " year/s of ITR");
			}
			try {
				CommonResponse itrRes = itrClient.checkIsITRUpdated(profileId, profileRes.getItrId());
				if (itrRes != null && itrRes.getStatus() == 200) {
					if (itrRes.getFlag()) {
						itrMap.put(COMPLETED, Boolean.TRUE);
						itrMap.put(DETAIL_VALID_UP_TO, itrRes.getMessage());
                        itrMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/income-tax-icon-blue.svg");
					} else {
						itrMap.put(DETAIL_VALID_UP_TO, "Update ITR Details");
					}
				}
			} catch (Exception e) {
				logger.error("Exception while Check ITR data updated or Not " , e);
			}
		}
		mapList.add(itrMap);
		
		// SET BANK STATEMENT DATA
		Map<String, Object> bsMap = new HashMap<String, Object>();
		bsMap.put(MODULE_TYPE, ProfileModuleType.BS);
		bsMap.put(MODULE_NAME, "Bank Statement");
		bsMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/bankStatement-icon-gray.svg");
		bsMap.put(COMPLETED, Boolean.FALSE);
		bsMap.put(ACTIVAE, Boolean.TRUE);
		if(!CommonUtils.isObjectNullOrEmpty(profileRes) && !CommonUtils.isObjectNullOrEmpty(profileRes.getBsId())) {
			bsMap.put(DESCRIPTION, profileRes.getTotalBankStatement());
			bsMap.put(TITLE, "Total Bank Account Added");
			try {
				/*
				if(profileRes.getNoBankStatementDetail() != null) {
					bsMap.put(COMPLETED, Boolean.TRUE);
					bsMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/bankStatement-icon-blue.svg");
					bsMap.put(TITLE, "Bank Account Verified");
					String[] manualBsData = profileRes.getNoBankStatementDetail() != null && profileRes.getNoBankStatementDetail().contains("|") ? StringUtils.split(profileRes.getNoBankStatementDetail(), "|") : null;
					bsMap.put(DESCRIPTION, manualBsData != null ? manualBsData[0] : null);
				}else {*/
					AnalyzerResponse analyRes = analyzerClient.isBankStatementIsUpdated(profileId, profileRes.getBsId());
					if (analyRes != null) {
//						Boolean isUpdated = false;
//						if(analyRes.getData() instanceof String) {					
//							isUpdated = Boolean.parseBoolean((String)analyRes.getData());
//						}else {
//							isUpdated = (Boolean)analyRes.getData();
//						}
//						if (isUpdated != null && isUpdated) {
//							bsMap.put(COMPLETED, Boolean.TRUE);
//                            bsMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/bankStatement-icon-blue.svg");
//						}
//						bsMap.put(DETAIL_VALID_UP_TO, "Details Valid Up To "+ analyRes.getMessage());
						
						Boolean isUpdated = Boolean.parseBoolean(analyRes.getData() != null ? String.valueOf(analyRes.getData()) : null);
						if (isUpdated != null && isUpdated) {
							bsMap.put(COMPLETED, Boolean.TRUE);
							bsMap.put(DETAIL_VALID_UP_TO, "Data Valid Up To " + analyRes.getMessage());
							bsMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/bankStatement-icon-blue.svg");
							bsMap.put(MANUAL_BS_DATA, profileRes.getNoBankStatementDetail());
							bsMap.put(BS_MANUAL_FILL,analyRes.getIsManualUpload());
							
							if(analyRes.getIsManualUpload() != null && analyRes.getIsManualUpload()) {
								bsMap.put(TITLE, "Bank Account Verified");
							}
						}else{
							bsMap.put(DETAIL_VALID_UP_TO, "Update Bank Statement Details");
						}
					}
//				}
			} catch (Exception e) {
				logger.error("Exception while Check Bank Statement data updated or Not ", e);
			}
		}
		mapList.add(bsMap);
		if(applicationId != null) {
            // SET KEY PERSON DETAILS
            Map<String, Object> keyPerMap = new HashMap<String, Object>();
            keyPerMap.put(MODULE_TYPE, 4);
            keyPerMap.put(MODULE_NAME, "Key Person Details");
            keyPerMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/key-person-gray.svg");
            keyPerMap.put(COMPLETED, Boolean.FALSE);
            keyPerMap.put(ACTIVAE, ((boolean) itrMap.get(COMPLETED) && (boolean) gstMap.get(COMPLETED)&& (boolean) bsMap.get(COMPLETED)));
            DirectorBackgroundDetail dirBackGroundDetails = backgroundDetailsRepository.getByAppIdAndIsMainDirector(applicationId);
            if (dirBackGroundDetails != null) {
            	keyPerMap.put(COMPLETED, Boolean.TRUE);
                keyPerMap.put(DESCRIPTION, dirBackGroundDetails.getFirstName() + " " + dirBackGroundDetails.getMiddleName() + " " + dirBackGroundDetails.getLastName());
                keyPerMap.put(TITLE, "Main Partner");
                keyPerMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/key-person-blue.svg");
            }
            mapList.add(keyPerMap);
            
            // SET ASSOCIATE CONCERN DETAILS
            Map<String, Object> acMap = new HashMap<String, Object>();
            acMap.put(MODULE_TYPE, 6);
            acMap.put(MODULE_NAME, "Associate Concern Details");
            acMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/bankStatement-icon.svg");
            acMap.put(COMPLETED, Boolean.FALSE);
            acMap.put(ACTIVAE, ((boolean) itrMap.get(COMPLETED) && (boolean) gstMap.get(COMPLETED)&& (boolean) bsMap.get(COMPLETED) && (boolean) keyPerMap.get(COMPLETED)));
            mapList.add(acMap);

            // SET ONEFORM DETAILS
            Map<String, Object> oneFormMap = new HashMap<String, Object>();
            oneFormMap.put(MODULE_TYPE, 5);
            oneFormMap.put(MODULE_NAME, "One Form");
            oneFormMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/oneform-gray.svg");
            oneFormMap.put(COMPLETED, Boolean.FALSE);
			oneFormMap.put(ACTIVAE, acMap.get(COMPLETED));
            Double loanAmount = primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);
            if (loanAmount != null) {
                keyPerMap.put(DESCRIPTION, loanAmount);
                keyPerMap.put(TITLE, "Loan Required");
                keyPerMap.put(DETAIL_IMG_PATH, "assets/images/Provide-data/oneform-blue.svg");
            }
            mapList.add(oneFormMap);
        }
		return mapList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CommonResponse isProfileUpdated(Long profileId, Long applicationId, boolean isLoanSaved, Long userId) {
		try {
			
			ProfileVerMapRequest profileVerMapReq = new ProfileVerMapRequest();
			profileVerMapReq.setProfileId(profileId);
			
			boolean isUpdated = Boolean.FALSE;
			// GST DETAILS
			GSTR1Request gstReq = new GSTR1Request();
			gstReq.setProfileId(profileId);
			gstReq.setUserId(userId);
			gstReq.setIsProceedCopyData(Boolean.TRUE);
			GstResponse gstRes = gstClient.checkGstDataUpdatedOrNot(gstReq);
			if(gstRes != null && gstRes.getData() != null) {
				Map<String, Object> map = (Map<String, Object>) gstRes.getData();
				Boolean isDataUpdated = false;
				if(map.get("isDataUpdated") instanceof String) {					
					isDataUpdated = Boolean.parseBoolean((String)map.get("isDataUpdated"));
				}else {
					isDataUpdated = (Boolean)map.get("isDataUpdated");
				}
				if(isDataUpdated != null && isDataUpdated) {
					isUpdated = Boolean.TRUE;
				}
				Long gstMasterId = Long.parseLong(map.get("masterId").toString());
				profileVerMapReq.setGstId(gstMasterId);
				String companyName = (String) map.get("orgName");
				profileVerMapReq.setGstOrgName(companyName);
			}
			
			CommonResponse itrRes = itrClient.checkIsITRUpdated(profileId, null);
			if (itrRes != null && itrRes.getStatus() == 200) {
				Boolean isITRUpdated =  itrRes.getFlag();
				if(isITRUpdated != null && isITRUpdated) {
					isUpdated = Boolean.TRUE;
				}
				Map<String, Object> map = (Map<String, Object>) itrRes.getData();
				Long itrMasterId = Long.parseLong(map.get("itrMasterId").toString());
				profileVerMapReq.setItrId(itrMasterId);
				String yearRange = (String) map.get("yearRange");
				profileVerMapReq.setItrYearRange(yearRange);
				Integer lastYear = Integer.parseInt(map.get("lastYear").toString());
				profileVerMapReq.setItrLastYear(lastYear);
			}
			
			
			AnalyzerResponse analyRes = analyzerClient.isBankStatementIsUpdated(profileId, null);
			if (analyRes != null) {
				Boolean isBsUpdated  = false;
				if(analyRes.getData() instanceof String) {
					isBsUpdated = Boolean.parseBoolean((String)analyRes.getData());
				}else {
					isBsUpdated = (Boolean) analyRes.getData();
				}
				if(isBsUpdated != null && isBsUpdated) {
					isUpdated = Boolean.TRUE;
				}
				Integer totalBankState = analyRes.getTotalBankStatement();
				profileVerMapReq.setTotalBankStatement(totalBankState);
				Long analyMasterId = analyRes.getId();
				profileVerMapReq.setBsId(analyMasterId);
			}
			
			if(isLoanSaved) {
				LoanMappingRequest loanMappingReq = new LoanMappingRequest();
				loanMappingReq.setApplicationId(applicationId);
				loanMappingReq.setProfileId(profileId);
				
				CommonResponse commRes = connectClient.getStage(applicationId);
				if(commRes != null && commRes.getStatus() == 200) {
					ConnectResponse conRes = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) commRes.getData(), ConnectResponse.class);
					loanMappingReq.setBusinessTypeId(conRes.getBusinessTypeId().longValue());
					loanMappingReq.setConnectId(conRes.getId());
					loanMappingReq.setProposalId(conRes.getProposalId());
					profileVerMapReq.setUserId(conRes.getUserId());
				}
				profileVerMapReq.setLoanMapReq(loanMappingReq);
			}
			
			com.opl.profile.api.model.CommonResponse proComRes = profileClient.updateProfileVerMapAndLoanMap(profileVerMapReq);
			if(proComRes != null && proComRes.getFlag()) {
				logger.info("Successfully Updated Data");
				return new CommonResponse("Successfully Checked !!", proComRes.getData(), HttpStatus.OK.value(), isUpdated);
			} else {
				auditTableRepository.save(new CommonAuditTable(applicationId, profileId, CorporateServiceImpl.class.getName(), "isProfileUpdated", "Profile Version Details Not Saved " + proComRes.getMessage()));
				return new CommonResponse("Not updated profile version details !!", HttpStatus.OK.value(), Boolean.FALSE);				
			}
		} catch (Exception e) {
			logger.error("Exception while check Is Profile is Update or not =====>" , e);
			auditTableRepository.save(new CommonAuditTable(applicationId, profileId, CorporateServiceImpl.class.getName(), "isProfileUpdated", "Exception :-" + e.getMessage()));
			return new CommonResponse("Not updated profile version details !!", HttpStatus.INTERNAL_SERVER_ERROR.value(), Boolean.FALSE);
		}
		
	}
	

}
