package com.capitaworld.service.loans.service.fundseeker.microfinance.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.api.eligibility.model.MFIRequest;
import com.capitaworld.api.workflow.model.WorkflowJobsTrackerRequest;
import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.cibil.api.exception.CibilException;
import com.capitaworld.cibil.api.model.CibilResponse;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationStatusMaster;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIConversation;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiAssetsLiabilityDetails;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiBankDetails;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiExpenseExpectedIncomeDetails;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiFinancialArrangementsDetail;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiIncomeDetails;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiPpiScoringMaster;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoanDisbursementRequest;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ProposalRequestResponce;
import com.capitaworld.service.loans.model.mfi.MFIFinancialArrangementRequest;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.FlagCheckMFI;
import com.capitaworld.service.loans.model.micro_finance.MFIConversationReq;
import com.capitaworld.service.loans.model.micro_finance.MFIConversationRes;
import com.capitaworld.service.loans.model.micro_finance.MfiApplicantDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiAssetsDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiBankDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeAndExpenditureReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanRecomandationReq;
import com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq;
import com.capitaworld.service.loans.model.teaser.primaryview.MFITeaserViewResponse;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MFIConversationRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiApplicationDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiAssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiBankDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiExpenseExpectedIncomeDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiFinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiIncomeDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiPpiScoringRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.common.ApplicationSequenceService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.microfinance.MfiApplicationService;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;
import com.capitaworld.service.loans.service.sanction.LoanSanctionService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.EncryptionUtils;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.oneform.enums.AccountTypeMfi;
import com.capitaworld.service.oneform.enums.AddressProofType;
import com.capitaworld.service.oneform.enums.AreaTypeMfi;
import com.capitaworld.service.oneform.enums.BankListMfi;
import com.capitaworld.service.oneform.enums.BusinessInBriefMstMFI;
import com.capitaworld.service.oneform.enums.BusinessTypeMfi;
import com.capitaworld.service.oneform.enums.CastCategory;
import com.capitaworld.service.oneform.enums.ClientTypeMfi;
import com.capitaworld.service.oneform.enums.CompetitionMfi;
import com.capitaworld.service.oneform.enums.FrequencyPaymentMstMFI;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.HeadFamilyEduMfi;
import com.capitaworld.service.oneform.enums.HouseTypeMfi;
import com.capitaworld.service.oneform.enums.MaritalStatusMst;
import com.capitaworld.service.oneform.enums.OwnershipOfHouse;
import com.capitaworld.service.oneform.enums.ParticularsMfi;
import com.capitaworld.service.oneform.enums.PpiPersonDetailMFI;
import com.capitaworld.service.oneform.enums.PurposeOfLoanMFI;
import com.capitaworld.service.oneform.enums.RelationMstMFI;
import com.capitaworld.service.oneform.enums.ReligionRetailMst;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.ProposalScoreDetailResponse;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.scoring.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.scoring.utils.ScoreParameter.MFI;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.FundProviderDetailsRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class MfiApplicationServiceImpl implements MfiApplicationService {

	private static final Logger logger = LoggerFactory.getLogger(MfiApplicationServiceImpl.class.getName());
	private static final Integer ASSETS = 1;
	private static final Integer LIABILITY = 2;
	private static final Integer MAKERNAME = 0;
	private static final Integer PINAME = 1;
	private static final Integer PIADDRESS = 2;

	@Autowired
	private MfiApplicationDetailsRepository detailsRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private LoanApplicationService applicationService;

	@Autowired
	private MfiBankDetailsRepository bankDetailsRepository;

	@Autowired
	private MfiIncomeDetailsRepository MfiIncomeDetailsRepository;

	@Autowired
	private MfiAssetsDetailsRepository MfiAssetsDetailsRepository;

	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;

	@Autowired
	private MfiExpenseExpectedIncomeDetailRepository expectedIncomeDetailRepository;

	@Autowired
	private WorkflowClient workflowClient;

	@Autowired
	private DMSClient dmsClient;

	@Autowired
	private CIBILClient cibilClient;

	@Autowired
	private MFIConversationRepository mfiConversationRepository;

	@Autowired
	private MfiFinancialArrangementDetailsRepository mfiFinancialRepository;

	@Autowired
	private ApplicationSequenceService applicationSequenceService;

	@Autowired
	private LoanSanctionService loanSanctionService;

	@Autowired
	private LoanDisbursementService loanDisbursementService;

	@Autowired
	private MfiPpiScoringRepository mfiPpiScoringRepository;

	@Value("${dmsURL}")
	String dmsUrl;
	
	@Value("${cw.mfi.consolidated.xml.location}")
	String consolidateUrl;

	@Autowired
	private EligibilityClient eligibilityClient;

	@Autowired
	private MatchEngineClient matchEngineClient;

	@Autowired
	private ScoringClient scoringClient;

	@Autowired
	private UsersClient usersClient;

	/**
	 * Save basic profile details with images
	 *
	 * @param uploadingFile
	 * @param aadharDetailsReq
	 * @return
	 */
	@Override
	public AadharDetailsReq saveOrUpdateAadharDetails(MultipartFile uploadingFile, MultipartFile[] addressProofFiles,
			AadharDetailsReq aadharDetailsReq) {
		MFIApplicantDetail mfiApplicationDetail;
		// server side validation added
		String serverSideValidation = serverSideValidation(CommonUtils.BASIC_DETAILS, aadharDetailsReq);
		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
			AadharDetailsReq detailsReq = new AadharDetailsReq();
			detailsReq.setMessage(serverSideValidation);
			return detailsReq;
		}
		if (aadharDetailsReq.getId() == null) {
			// create Application And generate ApplicationId
			Long applicationId;
			if (aadharDetailsReq.getApplicationId() == null) {
				applicationId = applicationService.createMfiLoan(aadharDetailsReq.getUserId(), true,
						aadharDetailsReq.getBusinessTypeId(), aadharDetailsReq.getOrgId());
			} else {
				applicationId = aadharDetailsReq.getApplicationId();
			}
			if (applicationId != null) {
				mfiApplicationDetail = new MFIApplicantDetail();
//                String addressProofNo = aadharDetailsReq.getAddressProofNo();
				BeanUtils.copyProperties(aadharDetailsReq, mfiApplicationDetail);
//                String encryptionWithKey = new EncryptionUtils().encryptionWithKey(addressProofNo);
				mfiApplicationDetail.setAddressProofNo(aadharDetailsReq.getAddressProofNo());
				mfiApplicationDetail.setAddressProofType(aadharDetailsReq.getAddressProfType());
				mfiApplicationDetail.setApplicationId(new LoanApplicationMaster(applicationId));
				mfiApplicationDetail.setStatus(CommonUtils.PENDING);
				mfiApplicationDetail.setIsActive(true);
				mfiApplicationDetail.setCreatedBy(aadharDetailsReq.getUserId());
				mfiApplicationDetail.setCreatedDate(new Date());
				mfiApplicationDetail.setType(aadharDetailsReq.getType());

				// image upload to DMS S3 server recent Image
				String profileImgToDms = uploadImageForMfi(uploadingFile, applicationId, 593);
				mfiApplicationDetail.setProfileImg(profileImgToDms); // save path for recent Image

				// image upload to DMS S3 server Address proof Image
				String addressProofImgToDms = "";
				int count = 0;
				for (MultipartFile addressProofFile : addressProofFiles) { // multiple files for address proof
					String imageForMfi = uploadImageForMfi(addressProofFile, applicationId, 593);
					if (!CommonUtils.isObjectNullOrEmpty(imageForMfi)) {
						addressProofImgToDms = (count == 0 ? "" : (addressProofImgToDms + ",")) + imageForMfi;
					}
					count++;
				}

				mfiApplicationDetail.setAddressProofImg(addressProofImgToDms); // save path for Addressproof Image

				detailsRepository.save(mfiApplicationDetail);
				aadharDetailsReq.setId(mfiApplicationDetail.getId());
				aadharDetailsReq.setApplicationId(mfiApplicationDetail.getApplicationId().getId());
			}
		} else {

			mfiApplicationDetail = detailsRepository.findOne(aadharDetailsReq.getId());
			BeanUtils.copyProperties(aadharDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setApplicationId(new LoanApplicationMaster(aadharDetailsReq.getApplicationId()));
			if (uploadingFile != null) {
				// image upload to DMS S3 server recent Image
				String profileImgToDms = uploadImageForMfi(uploadingFile, aadharDetailsReq.getApplicationId(), 593);
				mfiApplicationDetail.setProfileImg(profileImgToDms); // save path for recent Image
			}
			mfiApplicationDetail.setStatus(CommonUtils.PENDING);
			mfiApplicationDetail.setModifiedBy(aadharDetailsReq.getUserId());
			mfiApplicationDetail.setModifiedDate(new Date());
			detailsRepository.save(mfiApplicationDetail);
		}
		AadharDetailsReq detailsReq = new AadharDetailsReq();
		detailsReq.setId(aadharDetailsReq.getId());
		detailsReq.setApplicationId(aadharDetailsReq.getApplicationId());
		detailsReq.setMessage("Successfully Saved");
		return detailsReq;

	}

	/**
	 *
	 * @param multipartFiles
	 * @param aadharDetailsReq
	 * @return
	 */
	@Override
	public boolean saveConsentFormImage(MultipartFile[] multipartFiles, AadharDetailsReq aadharDetailsReq) {

		MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(aadharDetailsReq.getId());
		String consentImgToDms = "";
		int count = 0;
		for (MultipartFile uploadingFile : multipartFiles) {
			String imageForMfi = uploadImageForMfi(uploadingFile, aadharDetailsReq.getApplicationId(), 593);
			if (!CommonUtils.isObjectNullOrEmpty(imageForMfi)) {
				consentImgToDms = (count == 0 ? "" : (consentImgToDms + ",")) + imageForMfi;
			}
			count++;
		}

		mfiApplicationDetail.setConsentFormImg(consentImgToDms);
		detailsRepository.save(mfiApplicationDetail);

		return true;
	}

	/**
	 * Upload Image For Profile/Bank passbook/Address proof
	 *
	 * @param multipartFile
	 * @param userId
	 * @return
	 */
	private String uploadImageForMfi(MultipartFile multipartFile, Long userId, Integer productDocMappingId) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("applicationId", userId);
		jsonObj.put("productDocumentMappingId", productDocMappingId);// this is productmappingid 593 for save in amazon
																		// s3
		jsonObj.put("userType", DocumentAlias.UERT_TYPE_APPLICANT);
		jsonObj.put("originalFileName", multipartFile.getOriginalFilename());
		try {
			DocumentResponse documentResponse = dmsClient.uploadFile(jsonObj.toString(), multipartFile);
			logger.info("response {}", documentResponse.getStatus());
			StorageDetailsResponse response = null;
			Map<String, Object> list = (Map<String, Object>) documentResponse.getData();
			if (!CommonUtils.isObjectListNull(list)) {
				try {
					response = MultipleJSONObjectHelper.getObjectFromMap(list, StorageDetailsResponse.class);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("IO exception while upload file on DMS");
				}
			}

			if (response != null) {
				logger.debug("uploadImageForMfi() :: response is not null");
				if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath())) {
					return response.getId().toString();
				} else {
					logger.debug("uploadImageForMfi() :: error while upload Files response not 200");
					return null;
				}
			} else {
				logger.debug("uploadImageForMfi() :: response is null");
				return null;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error("Document exception while upload file on DMS");
			return null;
		}
	}

	@Override
	public AadharDetailsReq getAadharDetailsByAppId(Long applicationId, Integer type) {
		List<AadharDetailsReq> detailsReq = detailsRepository.findAadharDetailsByAppId(applicationId, type);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public Object saveOrUpdatePersonalDetails(PersonalDetailsReq personalDetailsReq) {
		String serverSideValidation = serverSideValidation(CommonUtils.PERSONAL_DETAILS, personalDetailsReq);

		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
			return serverSideValidation;
		}
		MFIApplicantDetail mfiApplicationDetail;
		if (null != personalDetailsReq.getId()) {
			mfiApplicationDetail = detailsRepository.findOne(personalDetailsReq.getId());
			BeanUtils.copyProperties(personalDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsPersonalDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
			return true;
		}
		return false;

	}

	@Override
	public PersonalDetailsReq getPersonalDetailsAppId(Long applicationId, Integer type) {
		List<PersonalDetailsReq> detailsReq = detailsRepository.findPersonalDetailsByAppId(applicationId, type);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public Object saveOrUpdateProjectDetails(ProjectDetailsReq projectDetailsReq) {
		MFIApplicantDetail mfiApplicationDetail;
		String serverSideValidation = serverSideValidation(CommonUtils.PROJECT_DETAILS, projectDetailsReq);

		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
			return serverSideValidation;
		}
		if (null != projectDetailsReq.getId()) {
			mfiApplicationDetail = detailsRepository.findOne(projectDetailsReq.getId());
			projectDetailsReq.setTotalCostEquipment(
					projectDetailsReq.getCostOfEquipment() + projectDetailsReq.getWorkingCapOfEquipment());
			projectDetailsReq.setTotalMeanFinance(
					projectDetailsReq.getPromoterContribution() + projectDetailsReq.getLoanRequiredFromSidbi());
			BeanUtils.copyProperties(projectDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsProjectDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
			MfiExpenseExpectedIncomeDetails byApplicationIdAndType = expectedIncomeDetailRepository
					.findByApplicationIdAndType(projectDetailsReq.getApplicationId(), 1);
			if (!CommonUtils.isObjectNullOrEmpty(byApplicationIdAndType)) {
				Double cashFlow = projectDetailsReq.getMonthlyIncome() + byApplicationIdAndType.getNetSaving();
				expectedIncomeDetailRepository.updateBusinessBrief(projectDetailsReq.getBusinessInBrief(),
						projectDetailsReq.getMonthlyCashflow(), projectDetailsReq.getMonthlyExpenditure(),
						projectDetailsReq.getMonthlyIncome(), cashFlow, projectDetailsReq.getApplicationId(), 1);
				expectedIncomeDetailRepository.updateBusinessBrief(projectDetailsReq.getBusinessInBrief(),
						projectDetailsReq.getMonthlyCashflow(), projectDetailsReq.getMonthlyExpenditure(),
						projectDetailsReq.getMonthlyIncome(), cashFlow, projectDetailsReq.getApplicationId(), 2);
			} else {
				saveProjectWithCopy(projectDetailsReq, 1);
				saveProjectWithCopy(projectDetailsReq, 2);
			}

			return true;
		}
		return false;
	}

	@Override
	public Object saveOrUpdateBankDetails(MultipartFile uploadingFile, MfiBankDetailsReq bankDetailsReq) {
		// for server side validation
		String serverSideValidation = serverSideValidation(CommonUtils.BANK_DETAILS, bankDetailsReq);
		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
			// return if required fields empty
			return serverSideValidation;
		}

		if (bankDetailsReq.getApplicationId() != null) {
			// save bank details in bank details table
			MfiBankDetails mfiBankDetails = new MfiBankDetails();
			BeanUtils.copyProperties(bankDetailsReq, mfiBankDetails);
			BankListMfi bankListMfi = BankListMfi.fromValue(bankDetailsReq.getBankName());
			mfiBankDetails.setBankId(Long.valueOf(bankListMfi.getId()));
			String bankPassbookToDms = uploadImageForMfi(uploadingFile, bankDetailsReq.getApplicationId(), 593);
			mfiBankDetails.setPassbookImg(bankPassbookToDms);
			bankDetailsRepository.save(mfiBankDetails);
			detailsRepository.updateBankFilledFlag(bankDetailsReq.getApplicationId());
			return true;
		}
		return false;
	}

	@Override
	public MfiBankDetailsReq fetchBankDetail(Long applicationId) {
		MfiBankDetailsReq mfiBankDetailsReq = new MfiBankDetailsReq();
		MfiBankDetails byApplicationId = bankDetailsRepository.findByApplicationId(applicationId);
		BeanUtils.copyProperties(byApplicationId, mfiBankDetailsReq);
		return mfiBankDetailsReq;
	}

	@Override
	public MfiApplicantDetailsReq getApplicantDetails(Long applicationId, Integer type) {

		MFIApplicantDetail mfiApplicantDetail = detailsRepository.findByApplicationIdAndAndTypeIsActive(applicationId,
				type);

		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);

		MfiApplicantDetailsReq detailsReq = new MfiApplicantDetailsReq();
		BeanUtils.copyProperties(mfiApplicantDetail, detailsReq);
		detailsReq.setStatus(loanApplicationMaster.getApplicationStatusMaster().getId().intValue()); // for current
		// status value
		detailsReq.setRepaymentTrack(mfiApplicantDetail.getRepaymentTrack());
		// for bank details
		MfiBankDetails byApplicationId = bankDetailsRepository.findByApplicationId(applicationId);
		if (byApplicationId != null) {
			BeanUtils.copyProperties(byApplicationId, detailsReq);
			detailsReq.setAcHolderName(byApplicationId.getAccountHolderName());
		}
		// for assets and liability
		detailsReq.setAssetsDetails(MfiAssetsDetailsRepository.findAssetsDetailsByAppId(applicationId));
		detailsReq.setLiabilityDetails(MfiAssetsDetailsRepository.findLiabilityDetailsByAppId(applicationId));
		// for Income
		List<MfiIncomeDetailsReq> incomeDetails = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId, 1);
		detailsReq.setIncomeDetailsReqList(incomeDetails);

//            List<MfiIncomeDetailsReq> incomeDetailsEditable = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId, 2);
//            detailsReq.setIncomeDetailsTypeTwoList(incomeDetailsEditable);

		// FOR MFI MAKER MfiIncomeAndExpenditureReq
		MfiExpenseExpectedIncomeDetails mfiIncomeAndExpendMFIMaker = expectedIncomeDetailRepository
				.findByApplicationIdAndType(applicationId, 1);
		detailsReq.setNetSaving(mfiIncomeAndExpendMFIMaker.getNetSaving());

		MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq = detailsRepository
				.findIncomeAndExpenditureDetailsByAppId(applicationId, 1);
		BeanUtils.copyProperties(mfiIncomeAndExpendMFIMaker, mfiIncomeAndExpenditureReq);

		detailsReq.setMfiIncomeAndExpenditureReqMFIMaker(mfiIncomeAndExpenditureReq);

		// FOR MFI CHECKER MfiIncomeAndExpenditureReq
		MfiExpenseExpectedIncomeDetails mfiIncomeAndExpendMFIChecker = expectedIncomeDetailRepository
				.findByApplicationIdAndType(applicationId, 2);
		MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq2 = new MfiIncomeAndExpenditureReq();
		BeanUtils.copyProperties(mfiIncomeAndExpendMFIChecker, mfiIncomeAndExpenditureReq2);
		detailsReq.setMfiIncomeAndExpenditureReqMFIChecker(mfiIncomeAndExpenditureReq2);
		List<MFIApplicantDetail> byCoApplicationIdAndAndTypeIsActive = detailsRepository
				.findByCoApplicationIdAndAndTypeIsActive(applicationId, 2);
		List<AadharDetailsReq> aadharDetailsReqs = new ArrayList<>();
		if (!CommonUtils.isListNullOrEmpty(byCoApplicationIdAndAndTypeIsActive)) {
			for (MFIApplicantDetail coApplicantDetail : byCoApplicationIdAndAndTypeIsActive) {
				AadharDetailsReq aadharDetailsReq = new AadharDetailsReq();
				BeanUtils.copyProperties(coApplicantDetail, aadharDetailsReq);
				aadharDetailsReqs.add(aadharDetailsReq);
			}
			detailsReq.setCoApplicantDetails(aadharDetailsReqs);
		} else {
			detailsReq.setCoApplicantDetails(Collections.EMPTY_LIST);
		}
		List<MFIFinancialArrangementRequest> financialArrangementRequests = mfiFinancialRepository
				.getFinancialDetailsByApplicationId(applicationId);
		detailsReq.setFinancialArrangementDetails(financialArrangementRequests);

		try {
			LoanSanctionRequest loanSanctionRequest = loanSanctionService.getSanctionDetail(applicationId);
			List<LoanDisbursementRequest> disbursementList = loanDisbursementService.getDisbursedList(applicationId);
			detailsReq.setSanctionDetail(loanSanctionRequest);
			detailsReq.setDisbursementDetails(disbursementList);

		} catch (LoansException e) {
			logger.error("Exception : " + e.getMessage());
		}

		List<MfiPpiScoringMaster> mfiPpiScoringMasters = mfiPpiScoringRepository.findAll();
		if (mfiPpiScoringMasters != null && !mfiPpiScoringMasters.isEmpty()) {
			detailsReq.setPpiNoFamilyMemberScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.MEMBERS_FAMILY.getId(), mfiApplicantDetail.getPpiNoFamilyMember()));
			detailsReq.setPpiAcadamicHeadFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.ACADAMIC_STANDARD.getId(), mfiApplicantDetail.getPpiAcadamicHeadFamily()));
			detailsReq.setPpiStoveInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_GAS_BURNER.getId(), mfiApplicantDetail.getPpiStoveInFamily()));
			detailsReq.setPpiPressureCookerInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_PRESSURE_COOCKER.getId(), mfiApplicantDetail.getPpiPressureCookerInFamily()));
			detailsReq.setPpiTvInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_TALIVISION.getId(), mfiApplicantDetail.getPpiTvInFamily()));
			detailsReq.setPpiFanInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_FAN.getId(), mfiApplicantDetail.getPpiFanInFamily()));
			detailsReq.setPpiVehicleInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_VEHICLE.getId(), mfiApplicantDetail.getPpiVehicleInFamily()));
			detailsReq.setPpiDressingTableInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_ALMIRAH.getId(), mfiApplicantDetail.getPpiDressingTableInFamily()));
			detailsReq.setPpiOtherTableInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_CHAIR.getId(), mfiApplicantDetail.getPpiOtherTableInFamily()));
			detailsReq.setPpiRafrigeratorInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_REFRIGERATOR.getId(), mfiApplicantDetail.getPpiRafrigeratorInFamily()));
		}
		
		
		if(!CommonUtils.isObjectNullOrEmpty(mfiApplicantDetail.getDpnDoc()) 
				&& !CommonUtils.isObjectNullOrEmpty(mfiApplicantDetail.getLoiDoc()) 
				&& !CommonUtils.isObjectNullOrEmpty(mfiApplicantDetail.getLohDoc()) 
				&& !CommonUtils.isObjectNullOrEmpty(mfiApplicantDetail.getAgreementDoc())) 
		{
			
			
			try {
				DocumentRequest documentRequest = new DocumentRequest();
				List<Long> productDocMappingIds = new ArrayList<>();
				productDocMappingIds.add(597l);
				productDocMappingIds.add(598l);
				productDocMappingIds.add(599l);
				productDocMappingIds.add(600l);
				
				System.out.println("productDocMappingIds: "+productDocMappingIds);
				
				documentRequest.setProMapIds(productDocMappingIds);
				documentRequest.setApplicationId(applicationId);
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				
				DocumentResponse documentResponse = dmsClient.listProDocByMultiProMapId(documentRequest);
				System.out.println("documentResponse: "+documentResponse);
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse response = null;
						for(Map<String, Object> objs : list) {
							response = MultipleJSONObjectHelper.getObjectFromMap(objs, StorageDetailsResponse.class);
							if(!CommonUtils.isObjectNullOrEmpty(response)) {
								System.out.println("getOriginalFileName : "+response.getOriginalFileName());
								
								switch (response.getProductMappingId().intValue()) {
									case 597:
										detailsReq.setDpnDocFileName(response.getOriginalFileName());
										break;
									case 598:
										detailsReq.setLoiDocFileName(response.getOriginalFileName());
										break;
									case 599:
										detailsReq.setLohDocFileName(response.getOriginalFileName());
										break;
									case 600:
										detailsReq.setAgreementDocFileName(response.getOriginalFileName());
										break;
									default:
										break;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("Document detail Exception : "+e.getMessage());
			}
			
		}
		
		

		return detailsReq;

	}

	@Override
	public ProjectDetailsReq getProjectDetailsAppId(Long applicationId, Integer type) {
		List<ProjectDetailsReq> detailsReq = detailsRepository.findProjectDetailsByAppId(applicationId, type);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public Object saveOrUpdateIncomeExpenditureDetails(MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq) {
		Double totalIncome = 0.0;
		// check server side validation
		String serverSideValidation = serverSideValidation(CommonUtils.INCOME_EXPENDITURE, mfiIncomeAndExpenditureReq);

		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
			// return if required fields not available
			return serverSideValidation;
		}
		if (null != mfiIncomeAndExpenditureReq.getId()) {
			// save data in expense and expected income details for agent type 1 for agent
			// details
			if (!CommonUtils.isListNullOrEmpty(mfiIncomeAndExpenditureReq.getIncomeDetailsReqList())) { // save income
				// details
				MfiIncomeDetailsRepository.inActiveMappingByAppId(mfiIncomeAndExpenditureReq.getApplicationId());
				// for MFI Agent data from users
				for (MfiIncomeDetailsReq mfiIncomeDetailsReq : mfiIncomeAndExpenditureReq.getIncomeDetailsReqList()) {
					MfiIncomeDetails mfiIncomeDetails = new MfiIncomeDetails();
					BeanUtils.copyProperties(mfiIncomeDetailsReq, mfiIncomeDetails);
					totalIncome = totalIncome + mfiIncomeDetails.getMonthlyIncome();
					mfiIncomeDetails.setMonthlyIncomeChecker(mfiIncomeDetails.getMonthlyIncome());
					mfiIncomeDetails.setType(1);
					mfiIncomeDetails.setIsActive(true);
					MfiIncomeDetailsRepository.save(mfiIncomeDetails);
				}
			}

			// inactive all previous data
			expectedIncomeDetailRepository.inactiveExpene(mfiIncomeAndExpenditureReq.getApplicationId());

			// save data in expense and expected income details for agent type 1 for agent
			// and 2 for checker details
			boolean withCopy = saveIncomeAndExpenditureWithCopy(mfiIncomeAndExpenditureReq, totalIncome, 1);
			if (withCopy) {
				saveIncomeAndExpenditureWithCopy(mfiIncomeAndExpenditureReq, totalIncome, 2);
			}

			// save PPI and is income filled true
			MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(mfiIncomeAndExpenditureReq.getId());
			Integer type = mfiApplicationDetail.getType();
			BeanUtils.copyProperties(mfiIncomeAndExpenditureReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsIncomeDetailsFilled(true);
			mfiApplicationDetail.setType(type);
			detailsRepository.save(mfiApplicationDetail);
			return true;
		}
		return false;

	}

	/**
	 * this method for copy code of maker and agent
	 *
	 * @param mfiIncomeAndExpenditureReq
	 * @param totalIncome
	 * @param type
	 * @return
	 */
	private boolean saveIncomeAndExpenditureWithCopy(MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq,
			Double totalIncome, Integer type) {
		Double totalExpense = 0.0;
		MfiExpenseExpectedIncomeDetails expectedIncomeDetails = new MfiExpenseExpectedIncomeDetails();
		expectedIncomeDetails.setApplicationId(mfiIncomeAndExpenditureReq.getApplicationId());
		BeanUtils.copyProperties(mfiIncomeAndExpenditureReq, expectedIncomeDetails);
		// below code for calculate total expense
		totalExpense = expectedIncomeDetails.getEducationExpense() + expectedIncomeDetails.getMedicalExpense()
				+ expectedIncomeDetails.getHouseHoldExpense() + expectedIncomeDetails.getFoodExpense()
				+ expectedIncomeDetails.getOtherExpense() + expectedIncomeDetails.getClothesExpense();
		// Existing Expenses from Business/Family
		expectedIncomeDetails.setTotalExpense(CommonUtils.isObjectNullOrEmpty(totalExpense) ? 0.0 : totalExpense);
		// Income from Occupation/Business is Monthly
		expectedIncomeDetails
				.setTotalMonthlyIncomeForFamily(CommonUtils.isObjectNullOrEmpty(totalIncome) ? 0.0 : totalIncome);
		// Net Savings
		expectedIncomeDetails.setNetSaving(
				expectedIncomeDetails.getTotalMonthlyIncomeForFamily() - expectedIncomeDetails.getTotalExpense());
		// Expected Increase in Income out of Loan ---- Monthly Income Column use
		// Total Cash Flow
		expectedIncomeDetails.setCashFlow(0.0 + expectedIncomeDetails.getNetSaving());
		expectedIncomeDetails.setIsActive(true);
		expectedIncomeDetails.setType(type);
		expectedIncomeDetailRepository.save(expectedIncomeDetails);
		return true;
	}

	/**
	 * For project Business Details Copy
	 *
	 * @param projectDetailsReq
	 * @param type
	 * @return
	 */
	private boolean saveProjectWithCopy(ProjectDetailsReq projectDetailsReq, Integer type) {
		MfiExpenseExpectedIncomeDetails expectedIncomeDetails = new MfiExpenseExpectedIncomeDetails();
		expectedIncomeDetails.setApplicationId(projectDetailsReq.getApplicationId());
		BeanUtils.copyProperties(projectDetailsReq, expectedIncomeDetails);
		expectedIncomeDetails.setCashFlow(expectedIncomeDetails.getMonthlyIncome() + 0.0);
		expectedIncomeDetails.setType(type);
		expectedIncomeDetails.setIsActive(true);
		expectedIncomeDetailRepository.save(expectedIncomeDetails);
		return true;
	}

	@Override
	public MfiIncomeAndExpenditureReq getIncomeExpenditureDetailsAppId(Long applicationId) {
		MfiIncomeAndExpenditureReq detailsReq = detailsRepository.findIncomeAndExpenditureDetailsByAppId(applicationId,
				1);
		List<MfiIncomeDetailsReq> incomeDetails = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId, 1);
		detailsReq.setIncomeDetailsReqList(
				!CommonUtils.isListNullOrEmpty(incomeDetails) ? incomeDetails : Collections.emptyList());
		return detailsReq;
	}

	/**
	 * @param applicationId
	 * @param type          1 for Applicant 2 for co-applicant
	 * @return
	 */
	@Override
	public FlagCheckMFI findAllFlag(Long applicationId, Integer type) {
		return detailsRepository.getFlagDetailByApplicationId(applicationId, type);

	}

	/**
	 * Assets and Liability details save
	 *
	 * @param mfiAssetsDetailsReq
	 * @return
	 */
	@Override
	public LoansResponse saveOrUpdateAssetsLiabilityDetails(MfiAssetsDetailsReq mfiAssetsDetailsReq) {
		MfiAssetsLiabilityDetails mfiAssetsLiabilityDetails = null;
		if (!CommonUtils.isListNullOrEmpty(mfiAssetsDetailsReq.getAssetsDetails())
				|| !CommonUtils.isListNullOrEmpty(mfiAssetsDetailsReq.getLiabilityDetails())) { // to save assets
			// details
			if (!CommonUtils.isListNullOrEmpty(mfiAssetsDetailsReq.getAssetsDetails())) { // to save assets details
				for (MfiAssetsDetailsReq mfiassetsDetailsReq : mfiAssetsDetailsReq.getAssetsDetails()) {
					mfiAssetsLiabilityDetails = new MfiAssetsLiabilityDetails();
					BeanUtils.copyProperties(mfiassetsDetailsReq, mfiAssetsLiabilityDetails);
					ParticularsMfi particularsMfi = ParticularsMfi
							.fromId(mfiassetsDetailsReq.getParticulars().toString());
					mfiAssetsLiabilityDetails.setAssetsLiabilityType(particularsMfi.getType());
					mfiAssetsLiabilityDetails.setApplicationId(mfiassetsDetailsReq.getApplicationId());
					mfiAssetsLiabilityDetails.setType(ASSETS);
					MfiAssetsDetailsRepository.save(mfiAssetsLiabilityDetails);
				}
			}
			if (!CommonUtils.isListNullOrEmpty(mfiAssetsDetailsReq.getLiabilityDetails())) { // to save liability
				// details
				for (MfiAssetsDetailsReq mfiDetailsReq : mfiAssetsDetailsReq.getLiabilityDetails()) {
					mfiAssetsLiabilityDetails = new MfiAssetsLiabilityDetails();
					BeanUtils.copyProperties(mfiDetailsReq, mfiAssetsLiabilityDetails);
					mfiAssetsLiabilityDetails.setApplicationId(mfiDetailsReq.getApplicationId());
					mfiAssetsLiabilityDetails.setType(LIABILITY);
					MfiAssetsDetailsRepository.save(mfiAssetsLiabilityDetails);
				}
			}
			// set flag filled assets
			MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(mfiAssetsDetailsReq.getId());
			mfiApplicationDetail.setIsAssetsDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
			// send response
			LoansResponse loansResponse = new LoansResponse();
			loansResponse.setMessage("Successfully Saved.");
			loansResponse.setStatus(HttpStatus.OK.value());
			return loansResponse;
		}
		return null;
	}

	@Override
	public MfiAssetsDetailsReq getAssetsLiabilityDetailsAppId(Long applicationId) {
		MfiAssetsDetailsReq mfiAssetsDetailsReq = new MfiAssetsDetailsReq();
		mfiAssetsDetailsReq.setAssetsDetails(MfiAssetsDetailsRepository.findAssetsDetailsByAppId(applicationId));
		mfiAssetsDetailsReq.setLiabilityDetails(MfiAssetsDetailsRepository.findLiabilityDetailsByAppId(applicationId));
		return mfiAssetsDetailsReq;
	}

	@Override
	public Object saveOrUpdateLoanAssessmentDetails(MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq) {
		String serverSideValidation = serverSideValidation(CommonUtils.LOAN_ASSESMENT, mfiLoanAssessmentDetailsReq);
		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
			return serverSideValidation;
		}
		if (null != mfiLoanAssessmentDetailsReq.getId()) {
			MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(mfiLoanAssessmentDetailsReq.getId());
			Integer purposeOfLoan = mfiApplicationDetail.getPurposeOfLoan();
			BeanUtils.copyProperties(mfiLoanAssessmentDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setPurposeOfLoan(purposeOfLoan);
			mfiApplicationDetail.setIsLoanassessmentDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
			return true;
		}
		return false;

	}

	@Override
	public Object saveOrUpdateLoanRecommandationDetails(MfiLoanRecomandationReq loanRecomandationReq) {
		LoansResponse loansResponse = new LoansResponse();
		// server side validations
		String serverSideValidation = serverSideValidation(CommonUtils.LOAN_RECOMANDATION, loanRecomandationReq);
		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
			loansResponse.setData(serverSideValidation);
			loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return loansResponse;
		}
		if (null != loanRecomandationReq.getId()) {

			MFIApplicantDetail mfiApplicationDetail = detailsRepository
					.findByAppIdAndType(loanRecomandationReq.getApplicationId(), 1);
			
			MFIApplicantDetail mfiApplicationDetail1 = detailsRepository
					.findByAppIdAndType(loanRecomandationReq.getApplicationId(), 2);

			// for status change to 10 display in Checker this code for submit application
			// or add in consent form
			LoanApplicationMaster corporateLoan = loanApplicationRepository
					.getById(loanRecomandationReq.getApplicationId());
			corporateLoan
					.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.MFI_PENDING));
			CommonUtils.LoanType type = CommonUtils.LoanType.getType(17);
			corporateLoan.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue()));
			loanApplicationRepository.save(corporateLoan);

			// create Workflow JobId and step actions
			WorkflowRequest request = new WorkflowRequest();
			if (!CommonUtils.isObjectNullOrEmpty(mfiApplicationDetail.getJobId())) {
				request.setJobId(mfiApplicationDetail.getJobId());
			}
			request.setApplicationId(loanRecomandationReq.getApplicationId());
			request.setUserId(loanRecomandationReq.getUserId());// user id as per agent logged in id
			request.setRoleIds(Arrays.asList(17l)); // role statically 17 added for mfi checker
			Object activeButtons = getActiveButtons(request); // job created or get workflow steps
			WorkflowJobsTrackerRequest objectFromMap = (WorkflowJobsTrackerRequest) activeButtons;

			// for save Loan recomandation
			BeanUtils.copyProperties(loanRecomandationReq, mfiApplicationDetail);
			mfiApplicationDetail.setJobId(objectFromMap.getJob().getId());
			detailsRepository.save(mfiApplicationDetail);

			// response back to User JobId and Steps return
			// step actions return with encryption
			String stringfromObject = null, encryption = null;
			try {
				stringfromObject = com.capitaworld.service.loans.utils.MultipleJSONObjectHelper
						.getStringfromObject(objectFromMap.getStep().getStepActions());
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("Error while convert into string");
			}
			if (!CommonUtils.isObjectNullOrEmpty(stringfromObject)) {
				encryption = new EncryptionUtils().encryptionWithKey(stringfromObject);
			}
			loansResponse.setData(encryption);
			loansResponse.setId(objectFromMap.getJob().getId()); // jobId for submit current step and action
			loansResponse.setMessage("Successfully Saved.");
			loansResponse.setStatus(HttpStatus.OK.value());
			
			
			if (!CommonUtils.isObjectNullOrEmpty(mfiApplicationDetail)) {
				Map<String, Object> consolidateDetails = getConsolidateInfo(mfiApplicationDetail);
				if (null != (Boolean) consolidateDetails.get("isConsolidated")) {
					mfiApplicationDetail.setIsConsolidated((Boolean) consolidateDetails.get("isConsolidated"));
				}
				if (null != (String) consolidateDetails.get("consolidateName")) {
					mfiApplicationDetail.setConsolidatedName((String) consolidateDetails.get("consolidateName"));
				}
				detailsRepository.save(mfiApplicationDetail);
			}
			if (!CommonUtils.isObjectNullOrEmpty(mfiApplicationDetail1)) {
				Map<String, Object> consolidateDetails1 = getConsolidateInfo(mfiApplicationDetail1);
				if (null != (Boolean) consolidateDetails1.get("isConsolidated")) {
					mfiApplicationDetail1.setIsConsolidated((Boolean) consolidateDetails1.get("isConsolidated"));
				}
				if (null != (String) consolidateDetails1.get("consolidateName")) {
					mfiApplicationDetail1.setConsolidatedName((String) consolidateDetails1.get("consolidateName"));
				}
				detailsRepository.save(mfiApplicationDetail1);
			}
//			for (int i = 1; i <= 2; i++) {
//				MFIApplicantDetail mfiApplicationDetail11 = detailsRepository
//						.findByAppIdAndType(loanRecomandationReq.getApplicationId(), i);
//				if (!CommonUtils.isObjectNullOrEmpty(mfiApplicationDetail11)) {
//					Map<String, Object> consolidateDetails1 = getConsolidateInfo(mfiApplicationDetail11);
//					mfiApplicationDetail11.setIsConsolidated((Boolean) consolidateDetails1.get("isConsolidated"));
//					mfiApplicationDetail11.setConsolidatedName((String) consolidateDetails1.get("consolidateName"));
//					detailsRepository.save(mfiApplicationDetail11);
//				}
//			}
			
			return loansResponse;
		}
		return false;

	}

	@Override
	public MfiLoanAssessmentDetailsReq getLoanAssessmentDetailsAppId(Long applicationId, Integer type) {
		List<MfiLoanAssessmentDetailsReq> detailsReq = detailsRepository.findLoanAssessmentDetailsByAppId(applicationId,
				type);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public MfiLoanAssessmentDetailsReq getCashFlowAssesmentByAppId(Long applicationId, Integer type) {
		List<MfiLoanAssessmentDetailsReq> cashFlowAssessment = expectedIncomeDetailRepository
				.findCashFlowAssessment(applicationId, type);
		return !CommonUtils.isListNullOrEmpty(cashFlowAssessment) ? cashFlowAssessment.get(0) : null;
	}

	private String serverSideValidation(Integer type, Object validationJson) {

		if (type == CommonUtils.BASIC_DETAILS) {
			AadharDetailsReq aadharDetailsReq = (AadharDetailsReq) validationJson;
			if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getFirstName())
					|| CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getLastName())) {
				return "First Name or last name is empty";
			} else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getGenderId())) {
				return "Gender is empty";
			} else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getMaritalStatusId())) {
				return "Marital status is empty";
			} else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getBirthDate())) {
				return "Date of birth is empty";
			} else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getAddressProfType())) {
				return "Address proof type is empty";
			} else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getAddressProofNo())) {
				return "Address proof number is empty";
			} else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getAddressPincode())) {
				return "pincode is empty";
			}
		} else if (type == CommonUtils.PERSONAL_DETAILS) {
			PersonalDetailsReq personalDetailsReq = (PersonalDetailsReq) validationJson;
			if (CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getFatherName())
					|| CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNoDependent())) {
				return "Some required fields in Family Details are Missing Personal Detail section";
			} else if (CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getEducationQualification())
					|| CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getAcademicReligion())
					|| CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getAcademicCaste())) {
				return "Some required fields in Acadamic and other details are missing Personal Detail section";
			} else if (CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getAreaType())
					|| CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getBusinessType())
					|| CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getHouseOwnership())
					|| CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNameOfFirm())
					|| CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getBusinessPremises())
					|| CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getExpInSameLine())) {
				return "Some required fields in Business and Other details are missing Personal Detail section";
			}
		} else if (type == CommonUtils.BANK_DETAILS) {
			MfiBankDetailsReq mfiBankDetailsReq = (MfiBankDetailsReq) validationJson;
			if (CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getAccountNo())
					|| CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getAccountType())
					|| CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getBranchName())
					|| CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getIfscCode())) {
				return "Some required fields in Bank details are missing";
			}
		} else if (type == CommonUtils.INCOME_EXPENDITURE) {
			MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq = (MfiIncomeAndExpenditureReq) validationJson;
			if (CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getOtherExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getMedicalExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getEducationExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getFoodExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getHouseHoldExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getClothesExpense())) {
				return "Some required fields in family monthly Expenses are missing in Income and Expenditure section";

			} else if (CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getPpiNoFamilyMember())
					|| CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getPpiAcadamicHeadFamily())) {
				return "Some required fields in Progress out of poverty index are missing in Income and Expenditure section";
			}
		} else if (type == CommonUtils.PROJECT_DETAILS) {
			ProjectDetailsReq projectDetailsReq = (ProjectDetailsReq) validationJson;
			if (CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getLoanType())
					|| CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getLoanAmountRequired())
					|| CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getPurposeOfLoan())) {
				return "Some required fields in Loan Applied are missing In project detail section";
			} else if (CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getRepaymentFrequency())) {
				return "Some required fields in Repayment & insurence are missing In project detail section";
			} else if (CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getCostOfEquipment())
					|| CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getWorkingCapOfEquipment())) {
				return "Some required fields in cost of finance are missing In project detail section";
			} else if (CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getPromoterContribution())
					|| CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getLoanRequiredFromSidbi())) {
				return "Some required fields in mean of finance are missing In project detail section";
			} else if (CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getBusinessInBrief())
					|| CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getMonthlyCashflow())
					|| CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getMonthlyExpenditure())
					|| CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getMonthlyIncome())) {
				return "Some required fields in expected increase income are missing in Income and Expenditure section";
			}
		} else if (type == CommonUtils.LOAN_RECOMANDATION) {
			MfiLoanRecomandationReq assessmentDetailsReq = (MfiLoanRecomandationReq) validationJson;
			if (CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getLoanAmountRecomandation())
					|| CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getTenureRecomandation())
					|| CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getInstallmentRecomandation())) {
				return "Some required fields in mean of missing in Loan Reccommendation detail section";
			}
		}
		return null;
	}

	public boolean checkIsSaveorNot(Integer type, MfiApplicantDetailsReq mfiApplicantDetailsReq) {
		if (type == CommonUtils.BASIC_DETAILS) {
			if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getFirstName())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getLastName())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getGenderId())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getMaritalStatusId())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getBirthDate())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getAddressProfType())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getAddressProofNo())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getAddressPincode())) {
				return false;
			}
		} else if (type == CommonUtils.PERSONAL_DETAILS) {
			if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getFatherName())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getNoDependent())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getEducationQualification())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getAcademicReligion())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getAcademicCaste())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getLandHolding())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getAreaType())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getBusinessType())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getHouseOwnership())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getNameOfFirm())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getBusinessPremises())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getExpInSameLine())) {
				return false;
			}
		} else if (type == CommonUtils.BANK_DETAILS) {
			if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getBankId())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getAccountNo())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getAccountType())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getBranchName())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getIfscCode())) {
				return false;
			}
		} else if (type == CommonUtils.INCOME_EXPENDITURE) {
			if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getOtherExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getMedicalExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getEducationExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getFoodExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getHouseHoldExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getClothesExpense())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getPpiNoFamilyMember())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getPpiAcadamicHeadFamily())) {
				return false;
			}
		} else if (type == CommonUtils.PROJECT_DETAILS) {
			if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getLoanType())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getLoanAmountRequired())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getPurposeOfLoan())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getRepaymentFrequency())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getCostOfEquipment())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getWorkingCapOfEquipment())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getPromoterContribution())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getLoanRequiredFromSidbi())) {
				return false;
			} else if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getBusinessInBrief())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getMonthlyCashflow())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getMonthlyExpenditure())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getMonthlyIncome())) {
				return false;
			}
		} else if (type == CommonUtils.LOAN_ASSESMENT) {
			if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getClientType())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getRepaymentTrack())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getCreaditWorthiness())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getCompetition())) {
				return false;
			}
		} else if (type == CommonUtils.LOAN_RECOMANDATION) {
			if (CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getLoanAmountRecomandation())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getTenureRecomandation())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getMoratoriumRecomandation())
					|| CommonUtils.isObjectNullOrEmpty(mfiApplicantDetailsReq.getInstallmentRecomandation())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ProposalRequestResponce getProposalDetails(ProposalRequestResponce proposalRequestResponce) {

		ProposalRequestResponce proposalRequestResponceNew = new ProposalRequestResponce();
		// get Proposal Details

		ProposalDetails proposalDetails = proposalDetailsRepository
				.getByApplicationIdAndFPProductId(proposalRequestResponce.getApplicationId());
		if (!CommonUtils.isObjectNullOrEmpty(proposalDetails)) {
			proposalRequestResponceNew.setFpProductId(proposalDetails.getFpProductId());
			proposalRequestResponceNew.setProposalStatusId(proposalDetails.getProposalStatusId().getId());
			proposalRequestResponceNew.setProposalMappingId(proposalDetails.getId());
			proposalRequestResponceNew.setApplicationId(proposalDetails.getApplicationId());
		}

		return proposalRequestResponceNew;

	}

	@Override
	public AadharDetailsReq getApplicationsByStatus(Long orgId, Long userId, Integer status) {
		if (status == 1) {
			return detailsRepository.getPendingApplications(userId, orgId);
		} else {
			return detailsRepository.getApprovedApplications(userId, orgId);
		}
	}

	@Override
	public Boolean saveOrUpdateApplicantDetail(MfiApplicantDetailsReq mfiApplicantDetailsReq) {
		Boolean result = false;
		try {

			if (mfiApplicantDetailsReq != null) {

				if (mfiApplicantDetailsReq.getIncomeDetailsReqList() != null) {
					List<MfiIncomeDetails> mfiIncomeDetails = new ArrayList<>();
					for (MfiIncomeDetailsReq mfiIncomeDetailsReq : mfiApplicantDetailsReq.getIncomeDetailsReqList()) {
						MfiIncomeDetails mfiIncomeDetail = MfiIncomeDetailsRepository
								.findOne(mfiIncomeDetailsReq.getId());
//						BeanUtils.copyProperties(mfiIncomeDetailsReq, mfiIncomeDetail);
//						mfiIncomeDetail.setIsActive(true);
//						mfiIncomeDetail.setType(2);
						if (mfiIncomeDetail != null) {
							mfiIncomeDetail.setMonthlyIncomeChecker(mfiIncomeDetailsReq.getMonthlyIncomeChecker());
							mfiIncomeDetails.add(mfiIncomeDetail);
						}
					}
					MfiIncomeDetailsRepository.save(mfiIncomeDetails);
				}

				MfiIncomeAndExpenditureReq mfiIncomeAndExpendMFIChecker = mfiApplicantDetailsReq
						.getMfiIncomeAndExpenditureReqMFIChecker();
				if (mfiIncomeAndExpendMFIChecker != null) {
					MfiExpenseExpectedIncomeDetails mfiExpenseExpectedIncomeDetails = expectedIncomeDetailRepository
							.findOne(mfiIncomeAndExpendMFIChecker.getId());
					mfiExpenseExpectedIncomeDetails
							.setHouseHoldExpense(mfiIncomeAndExpendMFIChecker.getHouseHoldExpense());
					mfiExpenseExpectedIncomeDetails
							.setEducationExpense(mfiIncomeAndExpendMFIChecker.getEducationExpense());
					mfiExpenseExpectedIncomeDetails.setMedicalExpense(mfiIncomeAndExpendMFIChecker.getMedicalExpense());
					mfiExpenseExpectedIncomeDetails.setFoodExpense(mfiIncomeAndExpendMFIChecker.getFoodExpense());
					mfiExpenseExpectedIncomeDetails.setClothesExpense(mfiIncomeAndExpendMFIChecker.getClothesExpense());
					mfiExpenseExpectedIncomeDetails.setOtherExpense(mfiIncomeAndExpendMFIChecker.getOtherExpense());
					mfiExpenseExpectedIncomeDetails
							.setMonthlyCashflow(mfiIncomeAndExpendMFIChecker.getMonthlyCashflow());
					mfiExpenseExpectedIncomeDetails
							.setMonthlyExpenditure(mfiIncomeAndExpendMFIChecker.getMonthlyExpenditure());
					mfiExpenseExpectedIncomeDetails.setMonthlyIncome(mfiIncomeAndExpendMFIChecker.getMonthlyIncome());

					expectedIncomeDetailRepository.save(mfiExpenseExpectedIncomeDetails);
				}

				MFIApplicantDetail mfiApplicantDetail = detailsRepository.findByApplicationIdAndAndTypeIsActive(
						mfiApplicantDetailsReq.getApplicationId(), mfiApplicantDetailsReq.getType());
				if (mfiApplicantDetail != null) {
					mfiApplicantDetail.setLoanAmountBankMaker(mfiApplicantDetailsReq.getLoanAmountBankMaker());
					mfiApplicantDetail.setLoanAmountMFIChecker(mfiApplicantDetailsReq.getLoanAmountMFIChecker());
					detailsRepository.save(mfiApplicantDetail);
				}

				result = true;

			}

		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("Exception : " + e.getMessage());
		}
		return result;
	}

	@Override
	public LoansResponse callBureauGetFinancialDetails(Long applicationId, Long applicantId, Long userId,
			Integer type) {
		LoansResponse loansResponse = new LoansResponse();
		String bureauCall = null, encryption = null;
		if (type == 1) {
			List<MFIFinancialArrangementRequest> arrangementRequests = getFinancialDetailsAppId(applicationId, userId);
			try {
				bureauCall = com.capitaworld.service.loans.utils.MultipleJSONObjectHelper
						.getStringfromObject(arrangementRequests);
				if (!CommonUtils.isObjectNullOrEmpty(bureauCall)) {
					encryption = new EncryptionUtils().encryptionWithKey(bureauCall);
					loansResponse.setMessage("Successfully Fetch Existing Loan details.");
					loansResponse.setData(encryption);
					loansResponse.setStatus(HttpStatus.OK.value());
					return loansResponse;
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("Error while convert into string Call Bureau");
			}
			loansResponse.setMessage("Something went wrong");
			loansResponse.setData(null);
			loansResponse.setStatus(HttpStatus.OK.value());
			return loansResponse;

		}
		CibilResponse cibilReportMfi = null;

		/*try {
			cibilReportMfi = cibilClient.getCibilReportMfi(applicationId, userId);
			if (cibilReportMfi.getStatus() == 200) {
				CibilResponse data = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) cibilReportMfi.getData(), CibilResponse.class);
				if (data.getStatus() != 200) {
					loansResponse.setMessage(data.getMessage());
					loansResponse.setStatus(data.getStatus());
					loansResponse.setData(cibilReportMfi.getData());
					return loansResponse;
				}

				List<MFIFinancialArrangementRequest> financialDetailsAppId = getFinancialDetailsAppId(applicationId,
						userId);
				bureauCall = com.capitaworld.service.loans.utils.MultipleJSONObjectHelper
						.getStringfromObject(financialDetailsAppId);
				if (!CommonUtils.isObjectNullOrEmpty(bureauCall)) {
					encryption = new EncryptionUtils().encryptionWithKey(bureauCall);
					loansResponse.setMessage("Successfully Fetch Existing Loan details and bureau report.");
					loansResponse.setStatus(HttpStatus.OK.value());
					loansResponse.setData(encryption);
					return loansResponse;
				}
			}
		} catch (CibilException e) {
			e.printStackTrace();
			logger.info("CibilException error while getReport");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("IOException for generate report");
		}*/
		loansResponse.setMessage("Something went wrong while call cibil report");
		loansResponse.setData(null);
		loansResponse.setStatus(HttpStatus.OK.value());
		return loansResponse;
	}

	public Object getActiveButtons(WorkflowRequest workflowRequest) {

		Long jobId = workflowRequest.getJobId();
		if (CommonUtils.isObjectNullOrEmpty(jobId)) {
			workflowRequest.setUserId(workflowRequest.getUserId());
			workflowRequest.setApplicationId(workflowRequest.getApplicationId());
			workflowRequest.setWorkflowId(WorkflowUtils.Workflow.MFI_PROCESS);
			workflowRequest.setActionId(WorkflowUtils.Action.PICKED_BY_MFI_MAKER);
			workflowRequest.setUserId(workflowRequest.getUserId());
			WorkflowResponse workflowResponse = workflowClient.createJob(workflowRequest);
			if (!com.capitaworld.service.scoring.utils.CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				jobId = Long.valueOf(workflowResponse.getData().toString());
			}
		}
		try {
			WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(jobId,
					workflowRequest.getRoleIds(), workflowRequest.getUserId());
			if (!com.capitaworld.service.scoring.utils.CommonUtils.isObjectNullOrEmpty(workflowResponse)
					&& !com.capitaworld.service.scoring.utils.CommonUtils
							.isObjectNullOrEmpty(workflowResponse.getData())) {

				WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
				if (!com.capitaworld.service.scoring.utils.CommonUtils
						.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep())
						&& !com.capitaworld.service.scoring.utils.CommonUtils
								.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
					return workflowJobsTrackerRequest;
				}

			}
		} catch (IOException e) {
			logger.error("Error While getting data from workflow {}", e);
		}
		return null;
	}

	@Override
	public boolean updateStaus(Long applicationId, Long status) {
		return loanApplicationRepository.updateStatus(applicationId, status) > 0;
	}

	@Override
	public Object getMfiConversation(MFIConversationReq mfiConversationReq) {

		List<MFIConversation> selfToSubMessageDetailsList = mfiConversationRepository.findAllMessageByFromIdAndToId(
				mfiConversationReq.getFromId(), mfiConversationReq.getToId(), mfiConversationReq.getApplicationId());
		List<MFIConversation> selfToSuperMessageDetailsList = mfiConversationRepository.findAllMessageByFromIdAndToId(
				mfiConversationReq.getFromId(), mfiConversationReq.getSuperToId(),
				mfiConversationReq.getApplicationId());

		List<MFIConversationReq> selfToSubMessageList = new ArrayList<>();
		List<MFIConversationReq> selfToSuperMessageList = new ArrayList<>();

		for (MFIConversation mfiConversation : selfToSubMessageDetailsList) {
			MFIConversationReq mfiConversationReq1 = new MFIConversationReq();
			BeanUtils.copyProperties(mfiConversation, mfiConversationReq1);
			selfToSubMessageList.add(mfiConversationReq1);
		}

		for (MFIConversation mfiConversation : selfToSuperMessageDetailsList) {
			MFIConversationReq mfiConversationReq1 = new MFIConversationReq();
			BeanUtils.copyProperties(mfiConversation, mfiConversationReq1);
			selfToSuperMessageList.add(mfiConversationReq1);
		}

		MFIConversationRes mfiConversationRes = new MFIConversationRes();
		mfiConversationRes.setSelfToSubConversation(selfToSubMessageList);
		mfiConversationRes.setSelfToSuperConversation(selfToSuperMessageList);

		return mfiConversationRes;
	}

	@Override
	public Object saveOrUpdateMfiConversation(MFIConversationReq mfiConversationReq) {

		MFIConversation mfiConversation = new MFIConversation();
		mfiConversationReq.setMessageDate(new Date());
		BeanUtils.copyProperties(mfiConversationReq, mfiConversation);
		mfiConversation = mfiConversationRepository.save(mfiConversation);
		return CommonUtils.isObjectNullOrEmpty(mfiConversation);
	}

	@Override
	public Boolean saveFinancialDetails(List<MFIFinancialArrangementRequest> financialDataList, Long applicationId,
			Long createdBy, Long applicantId) {
		mfiFinancialRepository.inActive(createdBy, applicationId, applicantId);
		for (MFIFinancialArrangementRequest req : financialDataList) {
			MfiFinancialArrangementsDetail arrangementsDetail = new MfiFinancialArrangementsDetail();
			BeanUtils.copyProperties(req, arrangementsDetail);
			arrangementsDetail.setApplicationId(new LoanApplicationMaster(applicationId));
			arrangementsDetail.setCreatedBy(createdBy);
			arrangementsDetail.setCreatedDate(new Date());
			arrangementsDetail.setIsActive(true);
			arrangementsDetail.setApplicantId(applicantId);
			mfiFinancialRepository.save(arrangementsDetail);
		}
		return true;
	}

	@Override
	public Boolean saveFinancialData(MFIFinancialArrangementRequest financialData, Long createdBy) {
		MfiFinancialArrangementsDetail arrangementsDetail = new MfiFinancialArrangementsDetail();
		BeanUtils.copyProperties(financialData, arrangementsDetail);
		arrangementsDetail.setApplicationId(new LoanApplicationMaster(financialData.getApplicationId()));
		arrangementsDetail.setCreatedBy(createdBy);
		arrangementsDetail.setCreatedDate(new Date());
		arrangementsDetail.setIsActive(true);
		arrangementsDetail.setIsManuallyAdded(true);
		arrangementsDetail.setApplicantId(createdBy);
		mfiFinancialRepository.save(arrangementsDetail);
		return true;
	}

	@Override
	public Boolean proceedFinancialFinalData(Long applicationId, Long createdBy, Integer creditWorthiness) {
		MFIApplicantDetail mfiApplicationDetail = detailsRepository.findByAppIdAndType(applicationId, 1);
		List<MFIFinancialArrangementRequest> financialDetailsAppId = getFinancialDetailsAppId(applicationId, createdBy);
		Double totalLoanAmount = 0.0, totalAssets = 0.0, allEmi = 0.0, expense = 0.0, netSaving = 0.0;
		// for calculate Amount and emi total
		for (MFIFinancialArrangementRequest arrangementRequest : financialDetailsAppId) {
			totalLoanAmount = totalLoanAmount + arrangementRequest.getAmount(); // sum of all Loan Amount
			allEmi = allEmi + arrangementRequest.getEmi(); // sum of all Emi
		}
		// for assets details calculations
		List<MfiAssetsDetailsReq> assetsDetailsByAppId = MfiAssetsDetailsRepository
				.findAssetsDetailsByAppId(applicationId);
		for (MfiAssetsDetailsReq assetsDetailsReq : assetsDetailsByAppId) {
			totalAssets = totalAssets + assetsDetailsReq.getAmount(); // sum of all assets
		}
		// calculate Ratio (assets - Loan amount / loan)
		Double ratio = (totalAssets - totalLoanAmount) / totalLoanAmount;
		mfiApplicationDetail.setLoanLiabilityRatio(ratio); // Liability Ratio
		mfiApplicationDetail.setCreaditWorthiness(creditWorthiness); // creditworthiness
		mfiApplicationDetail.setTotalEmi(allEmi);
		detailsRepository.save(mfiApplicationDetail);
		// get details from Expected increase in income
		MfiExpenseExpectedIncomeDetails expectedIncomeDetails = expectedIncomeDetailRepository
				.findByApplicationIdAndType(applicationId, 1);
		netSaving = expectedIncomeDetails.getTotalMonthlyIncomeForFamily()
				- (expectedIncomeDetails.getTotalExpense() + allEmi);
		// update net-saving
		expectedIncomeDetailRepository.updateNetSaving(netSaving, applicationId);
//		expectedIncomeDetailRepository.updateNetSaving(netSaving, applicationId,2);

		return true;
	}

	@Override
	public List<MFIFinancialArrangementRequest> getFinancialDetailsAppId(Long applicationId, Long applicantId) {
		try {
			return mfiFinancialRepository.getFinancialDetailsByAppId(applicationId, applicantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	public Object saveOrUpdateAllApplicantsDetails(MultipartFile uploadingFile, MultipartFile addressProof,
			MultipartFile consentformImg, MultipartFile aadharImg, MfiApplicantDetailsReq mfiApplicantDetailsReq) {

		Long applicationId = applicationService.createMfiLoan(mfiApplicantDetailsReq.getUserId(), true, 6,
				mfiApplicantDetailsReq.getOrgId());

		// SAVE PERSONAL,PROJECT,LOANASSESSMENT DETAILS
		MFIApplicantDetail mfiApplicationDetail;
		mfiApplicationDetail = new MFIApplicantDetail();
		BeanUtils.copyProperties(mfiApplicantDetailsReq, mfiApplicationDetail);
		mfiApplicationDetail.setApplicationId(new LoanApplicationMaster(applicationId));

		// image upload to DMS S3 server recent Image
		String profileImgToDms = uploadImageForMfi(uploadingFile, mfiApplicantDetailsReq.getUserId(), 593);
		mfiApplicationDetail.setProfileImg(profileImgToDms); // save path for recent Image

		// image upload to DMS S3 server for address proof
		String addressImgToDms = uploadImageForMfi(addressProof, mfiApplicantDetailsReq.getUserId(), 593);
		mfiApplicationDetail.setAddressProofImg(addressImgToDms);

		// image upload to DMS S3 server for consentform
		String consentImgToDms = uploadImageForMfi(consentformImg, mfiApplicantDetailsReq.getUserId(), 593);
		mfiApplicationDetail.setConsentFormImg(consentImgToDms);

		// image upload to DMS S3 server for aadhar Image
		String aadharImgToDms = uploadImageForMfi(aadharImg, mfiApplicantDetailsReq.getUserId(), 593);
		mfiApplicationDetail.setAadharImg(aadharImgToDms);
		boolean checkPersonalSaveornot = checkIsSaveorNot(CommonUtils.PERSONAL_DETAILS, mfiApplicantDetailsReq);
		if (checkPersonalSaveornot) {
			mfiApplicationDetail.setIsPersonalDetailsFilled(true);
		} else {
			mfiApplicationDetail.setIsPersonalDetailsFilled(false);
		}

		boolean checkProjectSaveornot = checkIsSaveorNot(CommonUtils.PROJECT_DETAILS, mfiApplicantDetailsReq);
		if (checkProjectSaveornot) {
			mfiApplicationDetail.setIsProjectDetailsFilled(true);
		} else {
			mfiApplicationDetail.setIsProjectDetailsFilled(false);
		}

		boolean checkLoanSaveornot = checkIsSaveorNot(CommonUtils.LOAN_ASSESMENT, mfiApplicantDetailsReq);
		if (checkLoanSaveornot) {
			mfiApplicationDetail.setIsLoanassessmentDetailsFilled(true);
		} else {
			mfiApplicationDetail.setIsLoanassessmentDetailsFilled(false);
		}

		mfiApplicationDetail.setIsActive(true);
		mfiApplicationDetail.setCreatedBy(mfiApplicantDetailsReq.getUserId());
		mfiApplicationDetail.setCreatedDate(new Date());
		mfiApplicationDetail.setStatus(CommonUtils.PENDING);
		detailsRepository.save(mfiApplicationDetail);

		// SAVE INCOME DETAILS LIST
		Double totalIncome = 0.0;
		if (!CommonUtils.isListNullOrEmpty(mfiApplicantDetailsReq.getIncomeDetailsReqList())) { // save income details
			MfiIncomeDetailsRepository.inActiveMappingByAppId(applicationId);
			// for MFI Agent data from users
			for (MfiIncomeDetailsReq mfiIncomeDetailsReq : mfiApplicantDetailsReq.getIncomeDetailsReqList()) {
				MfiIncomeDetails mfiIncomeDetails = new MfiIncomeDetails();
				BeanUtils.copyProperties(mfiIncomeDetailsReq, mfiIncomeDetails);
				totalIncome = totalIncome + mfiIncomeDetails.getMonthlyIncome();
				mfiIncomeDetails.setType(1);
				mfiIncomeDetails.setIsActive(true);
				mfiIncomeDetails.setApplicationId(applicationId);
				MfiIncomeDetailsRepository.save(mfiIncomeDetails);
			}
			// save PPI and is income filled true
			Integer type = mfiApplicationDetail.getType();
			mfiApplicationDetail.setIsIncomeDetailsFilled(true);
			mfiApplicationDetail.setType(type);
			detailsRepository.save(mfiApplicationDetail);
		}

		// SAVE ASSETS LIABILITY DETAILS
		MfiAssetsLiabilityDetails mfiAssetsLiabilityDetails = null;
		if (!CommonUtils.isListNullOrEmpty(mfiApplicantDetailsReq.getAssetsDetails())
				|| !CommonUtils.isListNullOrEmpty(mfiApplicantDetailsReq.getLiabilityDetails())) {
			if (!CommonUtils.isListNullOrEmpty(mfiApplicantDetailsReq.getAssetsDetails())) { // to save assets details
				for (MfiAssetsDetailsReq mfiassetsDetailsReq : mfiApplicantDetailsReq.getAssetsDetails()) {
					mfiAssetsLiabilityDetails = new MfiAssetsLiabilityDetails();
					BeanUtils.copyProperties(mfiassetsDetailsReq, mfiAssetsLiabilityDetails);
					mfiAssetsLiabilityDetails.setApplicationId(applicationId);
					mfiAssetsLiabilityDetails.setType(ASSETS);
					MfiAssetsDetailsRepository.save(mfiAssetsLiabilityDetails);
				}
			}
			if (!CommonUtils.isListNullOrEmpty(mfiApplicantDetailsReq.getLiabilityDetails())) { // to save liability
																								// details
				for (MfiAssetsDetailsReq mfiDetailsReq : mfiApplicantDetailsReq.getLiabilityDetails()) {
					mfiAssetsLiabilityDetails = new MfiAssetsLiabilityDetails();
					BeanUtils.copyProperties(mfiDetailsReq, mfiAssetsLiabilityDetails);
					mfiAssetsLiabilityDetails.setApplicationId(applicationId);
					mfiAssetsLiabilityDetails.setType(LIABILITY);
					MfiAssetsDetailsRepository.save(mfiAssetsLiabilityDetails);
				}
			}
			// set flag filled assets
			mfiApplicationDetail.setIsAssetsDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
			
			Map<String, Object> consolidateDetails = getConsolidateInfo(mfiApplicationDetail);
			if (null != (Boolean) consolidateDetails.get("isConsolidated")) {
				mfiApplicationDetail.setIsConsolidated((Boolean) consolidateDetails.get("isConsolidated"));
			}
			if (null != (String) consolidateDetails.get("consolidateName")) {
				mfiApplicationDetail.setConsolidatedName((String) consolidateDetails.get("consolidateName"));
			}
			detailsRepository.save(mfiApplicationDetail);
		}
		return true;
	}

	public Double getScoringOfPpiQuestion(List<MfiPpiScoringMaster> mfiPpiScoringMasters, Integer queId,
			Integer ansId) {
		Double result = null;
		if (mfiPpiScoringMasters != null && queId != null && ansId != null)
			for (MfiPpiScoringMaster mfiPpiScoringMaster : mfiPpiScoringMasters) {
				if (mfiPpiScoringMaster.getQueId().equals(queId) && mfiPpiScoringMaster.getAnsId().equals(ansId)) {
					return mfiPpiScoringMaster.getScoreValue();
				}
			}
		return result;
	}

	@Override
	public Map<String, Object> getReportDetails(Long applicationId) {
		Map<String, Object> map = new HashMap<String, Object>();

		MFITeaserViewResponse mfiTeaserViewResponse = new MFITeaserViewResponse();

		Integer bussnessTypeId = null;
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId); // FOR BUSSNESS
																										// TYPE ID
																										// RELATED
		Long productMappingId = proposalDetailsRepository.getFpProductIdByApplicationId(applicationId); // GETTING FP
																										// PRODUCT ID BY
																										// APPLICATION
																										// ID 1441l

		if (loanApplicationMaster != null) {
			bussnessTypeId = loanApplicationMaster.getBusinessTypeId();
		}

		/*
		 * scoringRequest.setApplicationId(applicationId);
		 * scoringRequest.setFpProductId(productMappingId);
		 */
		map.put("applicationId", applicationId);
		map.put("fpProductMappingId", productMappingId);

		ScoringRequest scoringRequest = new ScoringRequest();
		scoringRequest.setApplicationId(applicationId);
		scoringRequest.setFpProductId(productMappingId);

		try {
			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) scoringResponse.getDataObject(), ProposalScoreResponse.class);
			logger.info("SCORING RESPONSE HERE ======={}=====>", proposalScoreResponse);
			if (proposalScoreResponse != null) {
				map.put("scoringModelName",
						proposalScoreResponse.getScoringModelName() != null
								? proposalScoreResponse.getScoringModelName()
								: " - ");
				// map.put("dataList",
				// scoringResponse.getDataList()!=null?scoringResponse.getDataList():" - ");
				map.put("dataObject",
						scoringResponse.getDataObject() != null ? scoringResponse.getDataObject() : " - ");
				map.put("scoringResponseList",
						scoringResponse.getScoringResponseList() != null ? scoringResponse.getScoringResponseList()
								: " - ");

				/* ARUN */
				// Filter Parameters
				// Filter Parameters
				List<LinkedHashMap<String, Object>> mapList = (List<LinkedHashMap<String, Object>>) scoringResponse
						.getDataList();
				List<ProposalScoreDetailResponse> newMapList = new ArrayList<>(mapList.size());
				Map<String, Object> companyMap = new HashMap<>();
				List<Map<String, Object>> scoreResponse = new ArrayList<>(scoringResponse.getDataList().size());
				for (LinkedHashMap<String, Object> mp : mapList) {
					newMapList.add(MultipleJSONObjectHelper.getObjectFromMap(mp, ProposalScoreDetailResponse.class));
				}
				List<ProposalScoreDetailResponse> collect = newMapList.stream()
						.filter(m -> m.getParameterName().equalsIgnoreCase(MFI.AGE_OF_BORROWER_MFI))
						.collect(Collectors.toList());
				if (!CommonUtils.isListNullOrEmpty(collect)) {
					companyMap.put(MFI.AGE_OF_BORROWER_MFI, CommonUtils.printFields(collect.get(0), null));
				}
				collect = newMapList.stream()
						.filter(m -> m.getParameterName().equalsIgnoreCase(MFI.ACADEMIC_QUALIFICATION_MFI))
						.collect(Collectors.toList());
				if (!CommonUtils.isListNullOrEmpty(collect)) {
					companyMap.put(MFI.ACADEMIC_QUALIFICATION_MFI, CommonUtils.printFields(collect.get(0), null));
				}
				collect = newMapList.stream()
						.filter(m -> m.getParameterName().equalsIgnoreCase(MFI.ANNUAL_INCOME_AS_APPLICABLE_MFI))
						.collect(Collectors.toList());
				if (!CommonUtils.isListNullOrEmpty(collect)) {
					companyMap.put(MFI.ANNUAL_INCOME_AS_APPLICABLE_MFI, CommonUtils.printFields(collect.get(0), null));
				}
				collect = newMapList.stream()
						.filter(m -> m.getParameterName().equalsIgnoreCase(MFI.DEPENDENTS_IN_THE_FAMILY_MFI))
						.collect(Collectors.toList());
				if (!CommonUtils.isListNullOrEmpty(collect)) {
					companyMap.put(MFI.DEPENDENTS_IN_THE_FAMILY_MFI, CommonUtils.printFields(collect.get(0), null));
				}
				collect = newMapList.stream()
						.filter(m -> m.getParameterName().equalsIgnoreCase(MFI.EXPERIENCE_IN_THE_BUSINESS_WORKING_MFI))
						.collect(Collectors.toList());
				if (!CommonUtils.isListNullOrEmpty(collect)) {
					companyMap.put(MFI.EXPERIENCE_IN_THE_BUSINESS_WORKING_MFI,
							CommonUtils.printFields(collect.get(0), null));
				}
				collect = newMapList.stream()
						.filter(m -> m.getParameterName().equalsIgnoreCase(MFI.OWNERSHIP_OF_HOUSE_MFI))
						.collect(Collectors.toList());
				if (!CommonUtils.isListNullOrEmpty(collect)) {
					companyMap.put(MFI.OWNERSHIP_OF_HOUSE_MFI, CommonUtils.printFields(collect.get(0), null));
				}
				collect = newMapList.stream()
						.filter(m -> m.getParameterName().equalsIgnoreCase(MFI.PURPOSE_OF_LOAN_MFI))
						.collect(Collectors.toList());
				if (!CommonUtils.isListNullOrEmpty(collect)) {
					companyMap.put(MFI.PURPOSE_OF_LOAN_MFI, CommonUtils.printFields(collect.get(0), null));
				}
				scoreResponse.add(companyMap);
				map.put("scoringResp", scoreResponse);
			}
		} catch (Exception e) {
			logger.error("Error while getting scoring data : ", e);
		}

		/* ARUN */

		// mfiTeaserViewResponse.setScoringModelName(proposalScoreResponse.getScoringModelName()!=null?proposalScoreResponse.getScoringModelName():"
		// - ");
		// mfiTeaserViewResponse.setDataList(scoringResponse.getDataList()!=null?scoringResponse.getDataList():"
		// - ");
		// mfiTeaserViewResponse.setDataObject(scoringResponse.getDataObject()!=null?scoringResponse.getDataObject():"
		// - ");
		// mfiTeaserViewResponse.setScoringResponseList(scoringResponse.getScoringResponseList()!=null?scoringResponse.getScoringResponseList():"
		// - ")

		/*
		 * MFIRequest eligibilityReq = new MFIRequest(); //2. FOR ASSESSMENT LOAN
		 * DETAILS RELATED eligibilityReq.setApplicationId(applicationId);
		 * eligibilityReq.setFpProductMappingId(productMappingId);
		 * 
		 * try { EligibilityResponse eligibilityResp =
		 * eligibilityClient.getMfiLoanDetails(eligibilityReq);
		 * //mfiTeaserViewResponse.setEligibilityDataObject(eligibilityResp.getData()!=
		 * null?eligibilityResp.getData():null);
		 * map.put("eligibilityDataObject",CommonUtils.convertToDoubleForXml(
		 * MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,
		 * Object>)eligibilityResp.getData(), PersonalEligibilityRequest.class), new
		 * HashMap<>()));
		 * logger.info("ELIGIBILITY RESPONSE HERE ======={}=====>",eligibilityResp); }
		 * catch (Exception e1) { logger.error(CommonUtils.EXCEPTION,e1); }
		 */

		MFIRequest eligibilityReq = new MFIRequest(); // 2. FOR ASSESSMENT LOAN DETAILS RELATED
		eligibilityReq.setApplicationId(applicationId);
		eligibilityReq.setFpProductMappingId(productMappingId);

		try {
			EligibilityResponse eligibilityResp = eligibilityClient.getMfiLoanDetails(eligibilityReq);
			// mfiTeaserViewResponse.setEligibilityDataObject(eligibilityResp.getData()!=null?eligibilityResp.getData():null);
			map.put("eligibilityDataObject", eligibilityResp.getData() != null ? eligibilityResp.getData() : null);
			logger.info("ELIGIBILITY RESPONSE HERE ======={}=====>", eligibilityResp);
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION, e1);
		}

		// ENDS HERE ASSESSMENT AND SCORING RELATED CODDE HERE
		// ======================================================================================
		// }

		try {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setApplicationId(applicationId);
			matchRequest.setProductId(productMappingId);
			matchRequest.setBusinessTypeId(bussnessTypeId);
			MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfMFI(matchRequest);
			logger.info("matchesResponse" + matchResponse);
			map.put("matchesResponse",
					!CommonUtils.isListNullOrEmpty(matchResponse.getMatchDisplayObjectList())
							? CommonUtils.printFields(matchResponse.getMatchDisplayObjectList(), null)
							: " ");
		} catch (Exception e) {
			logger.error("Error while getting matches data : ", e);
		}

		return map;
	}

	@Override
	public Map<String, Object> getApplicantDetails1(Long applicationId, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();

		MFIApplicantDetail mfiApplicantDetail = detailsRepository.findByApplicationIdAndAndTypeIsActive(applicationId,
				type);

		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);

		MfiApplicantDetailsReq detailsReq = new MfiApplicantDetailsReq();
		BeanUtils.copyProperties(mfiApplicantDetail, detailsReq);
		
		
		if (loanApplicationMaster.getFpMakerId() != null) {
			// get fp maker name from fp_maker_id
			detailsReq.setMakerName(getFpMakerName(loanApplicationMaster.getFpMakerId(), MAKERNAME));
			detailsReq.setPiName(getFpMakerName(loanApplicationMaster.getFpMakerId(), PINAME));
			detailsReq.setPiAddress(getFpMakerName(loanApplicationMaster.getFpMakerId(), PIADDRESS));
			detailsReq.setLoginUser(getFpMakerName(loanApplicationMaster.getFpMakerId(), MAKERNAME));
		}
		BusinessInBriefMstMFI purposeOfLoanMFI = BusinessInBriefMstMFI.getById(mfiApplicantDetail.getPurposeOfLoan());
		detailsReq.setLoanPurposeStr(CommonUtils.isObjectNullOrEmpty(purposeOfLoanMFI.getValue()) ? purposeOfLoanMFI.getValue().toString() : "-");
		detailsReq.setCurrDateStr(CommonUtils.getCurrentDate("dd-MM-yyyy"));

		/* ENUM CONVERSION */
		detailsReq.setMaritalStatus(mfiApplicantDetail.getMaritalStatusId() !=null ? MaritalStatusMst.getById(mfiApplicantDetail.getMaritalStatusId()).getValue() : "");
		detailsReq.setGender( mfiApplicantDetail.getGenderId() !=null ? Gender.getById(mfiApplicantDetail.getGenderId()).getValue() : "");
		detailsReq.setEduQualification( mfiApplicantDetail.getEducationQualification()!= null ? StringEscapeUtils
				.escapeXml(HeadFamilyEduMfi.getById(mfiApplicantDetail.getEducationQualification()).getValue()) : "");
		detailsReq.setRelationWithNominee(mfiApplicantDetail.getRelationWithNomineeId()!= null ?
				RelationMstMFI.getById(mfiApplicantDetail.getRelationWithNomineeId()).getValue() : "");
		detailsReq.setHouseType1( mfiApplicantDetail.getHouseType() != null ? HouseTypeMfi.getById(mfiApplicantDetail.getHouseType()).getValue() : "");
		detailsReq.setRepayFreq( mfiApplicantDetail.getRepaymentFrequency()!= null ? FrequencyPaymentMstMFI.getById(mfiApplicantDetail.getRepaymentFrequency()).getValue() : "");
		detailsReq.setAcademicReli( mfiApplicantDetail.getAcademicReligion() != null ? ReligionRetailMst.getById(mfiApplicantDetail.getAcademicReligion()).getValue() : "");
		detailsReq.setAcademicCast( mfiApplicantDetail.getAcademicCaste()!= null ? CastCategory.getById(mfiApplicantDetail.getAcademicCaste()).getValue() : "");
		detailsReq.setHouseOwnerShip(mfiApplicantDetail.getHouseOwnership() !=null ? OwnershipOfHouse.getById(mfiApplicantDetail.getHouseOwnership()).getValue() : "");
		detailsReq.setAreaType1( mfiApplicantDetail.getAreaType() != null ? AreaTypeMfi.getById(mfiApplicantDetail.getAreaType()).getValue() : "");
		detailsReq.setBusinessPremises1(mfiApplicantDetail.getBusinessPremises() != null ? OwnershipOfHouse.getById(mfiApplicantDetail.getBusinessPremises()).getValue() : "");
		detailsReq.setAddressProofType1( mfiApplicantDetail.getAddressProofType() != null ? AddressProofType.getById(mfiApplicantDetail.getAddressProofType()).getValue() : "");
		detailsReq.setBirthDate( mfiApplicantDetail.getBirthDate() != null ? mfiApplicantDetail.getBirthDate() : null);
		detailsReq.setBusinessType1( mfiApplicantDetail.getBusinessType() != null ? BusinessTypeMfi.getById(mfiApplicantDetail.getBusinessType()).getValue() : "");
		System.out.println("mfiApplicantDetail.getLoanType()------::" + mfiApplicantDetail.getLoanType());
		//detailsReq.setLoanTypeString(BusinessInBriefMstMFI.getById(mfiApplicantDetail.getLoanType()).getValue());
		//detailsReq.setPurposeOfLoanString(PurposeOfLoanMFI.getById(mfiApplicantDetail.getPurposeOfLoan()).getValue());
		detailsReq.setProfileImg(mfiApplicantDetail.getProfileImg() != null ? mfiApplicantDetail.getProfileImg() : "No Image");
		// detailsReq.setConsentFormImg(mfiApplicantDetail.getConsentFormImg());
		System.out.println("Image Here ===============>>>>>>>>>>>" + mfiApplicantDetail.getProfileImg());

		/* ENUM CONVERSION */

		detailsReq.setStatus( loanApplicationMaster.getApplicationStatusMaster().getId() != null ? loanApplicationMaster.getApplicationStatusMaster().getId().intValue() : 0); // for current
		
		//FOR CONSENT IMAGE
//	    List<Resource> urlList = new ArrayList<Resource>();
		List<String> byteList = new ArrayList<String>();
		String[] ids = (String[]) mfiApplicantDetail.getConsentFormImg().split(",");
		for (int i = 0; i < ids.length; i++) {
			try {
				ByteArrayResource resp = (ByteArrayResource) dmsClient.productDownloadDocument(Long.valueOf(ids[i]));
				byte[] bytes = resp.getByteArray(); // Files.readAllBytes(Paths.get(resp.getFile().getAbsolutePath()));
				String encoded = Base64.getEncoder().encodeToString(bytes).toString();
				byteList.add(encoded);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//	    detailsReq.setListOfImages(urlList);

		detailsReq.setByteList(byteList);
		//FOR CONSENT IMAGE
		
		/* FOR ADDRESS PROOF */
		List<String> byteListAddProof = new ArrayList<String>();
		String[] idsAddProof = (String[]) mfiApplicantDetail.getAddressProofImg().split(",");
		for (int i = 0; i < idsAddProof.length; i++) {
			try {
				ByteArrayResource resp = (ByteArrayResource) dmsClient.productDownloadDocument(Long.valueOf(idsAddProof[i]));
				byte[] bytes = resp.getByteArray(); // Files.readAllBytes(Paths.get(resp.getFile().getAbsolutePath()));
				String encoded = Base64.getEncoder().encodeToString(bytes).toString();
				byteListAddProof.add(encoded);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
		detailsReq.setByteListAddProof(byteListAddProof);
		/* FOR ADDRESS PROOF */
		

		
		
		
		// status value
		detailsReq.setRepaymentTrack(mfiApplicantDetail.getRepaymentTrack()!=null ? mfiApplicantDetail.getRepaymentTrack() : 0);
		
		
		// for bank details
		MfiBankDetails byApplicationId = bankDetailsRepository.findByApplicationId(applicationId);
		if (byApplicationId != null) {
			BeanUtils.copyProperties(byApplicationId, detailsReq);
			detailsReq.setBankName(BankListMfi.fromId(byApplicationId.getBankId().toString()).toString());
			detailsReq.setAcHolderName(byApplicationId.getAccountHolderName());
			detailsReq.setAccountType1(AccountTypeMfi.getById(byApplicationId.getAccountType()).toString());
			
			
			/* FOR PASSBOOK PHOTO */
			List<String> byteListPassBook = new ArrayList<String>();
			String[] idsPassBook = (String[]) byApplicationId.getPassbookImg().split(",");
			for (int i = 0; i < idsPassBook.length; i++) {
				try {
					ByteArrayResource resp = (ByteArrayResource) dmsClient.productDownloadDocument(Long.valueOf(idsPassBook[i]));
					byte[] bytes = resp.getByteArray(); // Files.readAllBytes(Paths.get(resp.getFile().getAbsolutePath()));
					String encoded = Base64.getEncoder().encodeToString(bytes).toString();
					byteListPassBook.add(encoded);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			detailsReq.setByteListPassImg(byteListPassBook);

			/* FOR PASSBOOK PHOTO */
			
			
			

		}
		// for assets and liability
		detailsReq.setAssetsDetails(MfiAssetsDetailsRepository.findAssetsDetailsByAppId(applicationId));
		detailsReq.setLiabilityDetails(MfiAssetsDetailsRepository.findLiabilityDetailsByAppId(applicationId));
		// for Income
		List<MfiIncomeDetailsReq> incomeDetails = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId, 1);
		detailsReq.setIncomeDetailsReqList(incomeDetails);

		Double totalIncomeChecker = 0d;

		for (int i = 0; i < incomeDetails.size(); i++) {
			totalIncomeChecker += incomeDetails.get(i).getMonthlyIncomeChecker();
		}

		detailsReq.setTotalIncomeChecker(totalIncomeChecker);

//	            List<MfiIncomeDetailsReq> incomeDetailsEditable = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId, 2);
//	            detailsReq.setIncomeDetailsTypeTwoList(incomeDetailsEditable);

		// FOR MFI MAKER MfiIncomeAndExpenditureReq
		MfiExpenseExpectedIncomeDetails mfiIncomeAndExpendMFIMaker = expectedIncomeDetailRepository
				.findByApplicationIdAndType(applicationId, 1);
		detailsReq.setNetSaving( mfiIncomeAndExpendMFIMaker.getNetSaving() != null ? mfiIncomeAndExpendMFIMaker.getNetSaving() : 0 );
		detailsReq.setMfiMakerTotalExpense((mfiIncomeAndExpendMFIMaker.getHouseHoldExpense() !=null ? mfiIncomeAndExpendMFIMaker.getHouseHoldExpense() : 0) 
				+ (mfiIncomeAndExpendMFIMaker.getEducationExpense() !=null ? mfiIncomeAndExpendMFIMaker.getEducationExpense() : 0)
				+ (mfiIncomeAndExpendMFIMaker.getMedicalExpense() != null ? mfiIncomeAndExpendMFIMaker.getMedicalExpense() : 0)
				+ (mfiIncomeAndExpendMFIMaker.getFoodExpense() != null ? mfiIncomeAndExpendMFIMaker.getFoodExpense() : 0) 
				+ (mfiIncomeAndExpendMFIMaker.getClothesExpense() !=null ? mfiIncomeAndExpendMFIMaker.getClothesExpense() : 0) 
				+ (mfiIncomeAndExpendMFIMaker.getOtherExpense() != null ? mfiIncomeAndExpendMFIMaker.getOtherExpense() : 0));

		MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq = detailsRepository
				.findIncomeAndExpenditureDetailsByAppId(applicationId, 1);
		BeanUtils.copyProperties(mfiIncomeAndExpendMFIMaker, mfiIncomeAndExpenditureReq);

		detailsReq.setMfiIncomeAndExpenditureReqMFIMaker(mfiIncomeAndExpenditureReq);

		// FOR MFI CHECKER MfiIncomeAndExpenditureReq
		MfiExpenseExpectedIncomeDetails mfiIncomeAndExpendMFIChecker = expectedIncomeDetailRepository
				.findByApplicationIdAndType(applicationId, 2);
		MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq2 = new MfiIncomeAndExpenditureReq();
		BeanUtils.copyProperties(mfiIncomeAndExpendMFIChecker, mfiIncomeAndExpenditureReq2);
		detailsReq.setMfiIncomeAndExpenditureReqMFIChecker(mfiIncomeAndExpenditureReq2);
		detailsReq.setMfiCheckerTotalExpense((mfiIncomeAndExpendMFIChecker.getHouseHoldExpense() != null ?mfiIncomeAndExpendMFIChecker.getHouseHoldExpense() : 0)
				+ (mfiIncomeAndExpendMFIChecker.getEducationExpense() != null ? mfiIncomeAndExpendMFIChecker.getEducationExpense() : 0) 
				+ (mfiIncomeAndExpendMFIChecker.getMedicalExpense()!=null ? mfiIncomeAndExpendMFIChecker.getMedicalExpense() : 0)
				+ (mfiIncomeAndExpendMFIChecker.getFoodExpense()!=null ? mfiIncomeAndExpendMFIChecker.getFoodExpense() : 0) 
				+ (mfiIncomeAndExpendMFIChecker.getClothesExpense()!=null ? mfiIncomeAndExpendMFIChecker.getClothesExpense() : 0)
				+ (mfiIncomeAndExpendMFIChecker.getOtherExpense() !=null ? mfiIncomeAndExpendMFIChecker.getOtherExpense() : 0));
		if(!CommonUtils.isObjectNullOrEmpty(detailsReq.getMfiCheckerTotalExpense()) && !CommonUtils.isObjectNullOrEmpty(detailsReq.getTotalEmi()))
		{
			detailsReq.setNetSavingChecker(
					totalIncomeChecker - detailsReq.getMfiCheckerTotalExpense() - detailsReq.getTotalEmi());
		}

		detailsReq.setIncreasedIncomeChecker(mfiIncomeAndExpendMFIChecker.getMonthlyIncome() !=null ? mfiIncomeAndExpendMFIChecker.getMonthlyIncome() : 0);
		detailsReq.setTotalCashFlow(CommonUtils.isObjectNullOrEmpty(detailsReq.getNetSavingChecker() != null ? detailsReq.getNetSavingChecker() : 0 + detailsReq.getIncreasedIncomeChecker()) ? detailsReq.getNetSavingChecker() + detailsReq.getIncreasedIncomeChecker() : 0);
		
		List<MFIApplicantDetail> byCoApplicationIdAndAndTypeIsActive = detailsRepository
				.findByCoApplicationIdAndAndTypeIsActive(applicationId, 2);
		List<AadharDetailsReq> aadharDetailsReqs = new ArrayList<>();
		if (!CommonUtils.isListNullOrEmpty(byCoApplicationIdAndAndTypeIsActive)) {
			for (MFIApplicantDetail coApplicantDetail : byCoApplicationIdAndAndTypeIsActive) {
				AadharDetailsReq aadharDetailsReq = new AadharDetailsReq();				
				BeanUtils.copyProperties(coApplicantDetail, aadharDetailsReq);
				aadharDetailsReq.setGender(aadharDetailsReq.getGenderId() != null ? Gender.getById(aadharDetailsReq.getGenderId()).getValue(): "");
				aadharDetailsReq.setMaritalStatus(aadharDetailsReq.getMaritalStatusId() != null ? MaritalStatusMst.getById(aadharDetailsReq.getMaritalStatusId()).getValue() : "");
				aadharDetailsReq.setAddressProof(aadharDetailsReq.getAddressProofType() != null ? AddressProofType.getById(aadharDetailsReq.getAddressProofType()).getValue() : "");
				/* FOR CO_APP IMAGE */
				List<String> byteListCoAppImg = new ArrayList<String>();
				String[] idsCoAppAddProof = (String[]) aadharDetailsReq.getAddressProofImg().split(",");
				for (int i = 0; i < idsAddProof.length; i++) {
					try {
						ByteArrayResource resp = (ByteArrayResource) dmsClient.productDownloadDocument(Long.valueOf(idsCoAppAddProof[i]));
						byte[] bytes = resp.getByteArray(); // Files.readAllBytes(Paths.get(resp.getFile().getAbsolutePath()));
						String encoded = Base64.getEncoder().encodeToString(bytes).toString();
						byteListCoAppImg.add(encoded);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}			
				//aadharDetailsReq.setCoAppAddProof(byteListCoAppImg);
				detailsReq.setCoAppAddProof(byteListCoAppImg);
				/* FOR CO_APP IMAGE */
				
				aadharDetailsReqs.add(aadharDetailsReq);
			}
			
			detailsReq.setCoApplicantDetails(!CommonUtils.isListNullOrEmpty(aadharDetailsReqs) ? aadharDetailsReqs : null);
		} else {
			detailsReq.setCoApplicantDetails(null);
		}

		List<MFIFinancialArrangementRequest> financialArrangementRequests = mfiFinancialRepository
				.getFinancialDetailsByApplicationId(applicationId);
		detailsReq.setFinancialArrangementDetails(financialArrangementRequests);

		try {
			LoanSanctionRequest loanSanctionRequest = loanSanctionService.getSanctionDetail(applicationId);
			List<LoanDisbursementRequest> disbursementList = loanDisbursementService.getDisbursedList(applicationId);
			detailsReq.setSanctionDetail(loanSanctionRequest);
			detailsReq.setDisbursementDetails(disbursementList);

		} catch (LoansException e) {
			logger.error("Exception : " + e.getMessage());
		}

		List<MfiPpiScoringMaster> mfiPpiScoringMasters = mfiPpiScoringRepository.findAll();
		if (mfiPpiScoringMasters != null && !mfiPpiScoringMasters.isEmpty()) {
			detailsReq.setPpiNoFamilyMemberScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.MEMBERS_FAMILY.getId(), mfiApplicantDetail.getPpiNoFamilyMember()));
			detailsReq.setPpiAcadamicHeadFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.ACADAMIC_STANDARD.getId(), mfiApplicantDetail.getPpiAcadamicHeadFamily()));
			detailsReq.setPpiStoveInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_GAS_BURNER.getId(), mfiApplicantDetail.getPpiStoveInFamily()));
			detailsReq.setPpiPressureCookerInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_PRESSURE_COOCKER.getId(), mfiApplicantDetail.getPpiPressureCookerInFamily()));
			detailsReq.setPpiTvInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_TALIVISION.getId(), mfiApplicantDetail.getPpiTvInFamily()));
			detailsReq.setPpiFanInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_FAN.getId(), mfiApplicantDetail.getPpiFanInFamily()));
			detailsReq.setPpiVehicleInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_VEHICLE.getId(), mfiApplicantDetail.getPpiVehicleInFamily()));
			detailsReq.setPpiDressingTableInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_ALMIRAH.getId(), mfiApplicantDetail.getPpiDressingTableInFamily()));
			detailsReq.setPpiOtherTableInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_CHAIR.getId(), mfiApplicantDetail.getPpiOtherTableInFamily()));
			detailsReq.setPpiRafrigeratorInFamilyScore(getScoringOfPpiQuestion(mfiPpiScoringMasters,
					PpiPersonDetailMFI.IS_REFRIGERATOR.getId(), mfiApplicantDetail.getPpiRafrigeratorInFamily()));

			/* ARUn ENUMS */
			detailsReq.setPpiAcadamicHeadFamily1(mfiApplicantDetail.getPpiAcadamicHeadFamily() != null  ? StringEscapeUtils
					.escapeXml(HeadFamilyEduMfi.getById(mfiApplicantDetail.getPpiAcadamicHeadFamily()).getValue()) :  "");
			detailsReq.setClientType1( mfiApplicantDetail.getClientType() !=null ? ClientTypeMfi.getById(mfiApplicantDetail.getClientType()).getValue() : "");
			detailsReq.setRepayTrack(mfiApplicantDetail.getRepaymentTrack() != null
					? FrequencyPaymentMstMFI.getById(mfiApplicantDetail.getRepaymentTrack()).getValue()
					: "0");
			detailsReq.setCompetition1( mfiApplicantDetail.getCompetition()!=null ?  CompetitionMfi.getById(mfiApplicantDetail.getCompetition()).getValue() : "");
		}
		map.put("applicantDataObject", detailsReq);

		return map;
	}

	public String getCurrLoginUser(Long userId) {

		if (!CommonUtils.isObjectNullOrEmpty(userId)) {
			try {
				UserResponse userResponseForName = usersClient.getUserBasicDetails(userId);
				UserResponse uResponse = MultipleJSONObjectHelper
						.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(), UserResponse.class);
				return uResponse.getData().toString();

			} catch (Exception e) {
				logger.error("Exception : " + e.getMessage());
			}
		}
		return "-";
	}

	public String getFpMakerName(Long fpMakerId, Integer flag) { // pi reprentative name
		if (!CommonUtils.isObjectNullOrEmpty(fpMakerId)) {
			UsersRequest usersRequestForMaker = new UsersRequest();
			usersRequestForMaker.setId(fpMakerId);
			try {
				UserResponse userResponseForName = usersClient.getFPDetails(usersRequestForMaker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				if (flag == 0) {// pi repre name
					return (fundProviderDetailsRequest.getFirstName() == null ? "N/A"
							: fundProviderDetailsRequest.getFirstName()) + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
				} else if (flag == 1) {// pi name
					return (fundProviderDetailsRequest.getOrganizationName() == null ? "N/A"
							: fundProviderDetailsRequest.getOrganizationName());
				} else if (flag == 2) { // pi address
					return (fundProviderDetailsRequest.getStreetAddress() == null ? "_"
							: fundProviderDetailsRequest.getStreetAddress())
							+ " "
							+ (fundProviderDetailsRequest.getAddress() == null ? "_"
									: fundProviderDetailsRequest.getAddress())
							+ " " + (fundProviderDetailsRequest.getLandmark() == null ? "_"
									: fundProviderDetailsRequest.getLandmark());
				}
			} catch (Exception e) {
				logger.error("Exception : " + e.getMessage());
			}
		}
		return "-";
	}

	@Override
	public boolean uploadDocuments(MultipartFile[] uploadingFiles, MfiApplicantDetailsReq mfiApplicantDetailsReq) {
//		MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(mfiApplicantDetailsReq.getId());
		MFIApplicantDetail mfiApplicationDetail = detailsRepository.findByApplicationIdAndAndTypeIsActive(
				mfiApplicantDetailsReq.getApplicationId(), mfiApplicantDetailsReq.getType());

		if (!CommonUtils.isObjectNullOrEmpty(mfiApplicationDetail)) {
			int count = 0;
			for (MultipartFile uploadingFile : uploadingFiles) {
				String imageForMfi = uploadImageForMfi(uploadingFile, mfiApplicantDetailsReq.getApplicationId(),
						597 + count);
				if (!CommonUtils.isObjectNullOrEmpty(imageForMfi)) {
					switch (count) {
					case 0:
						mfiApplicationDetail.setDpnDoc(imageForMfi);
						break;
					case 1:
						mfiApplicationDetail.setLoiDoc(imageForMfi);
						break;
					case 2:
						mfiApplicationDetail.setLohDoc(imageForMfi);
						break;
					case 3:
						mfiApplicationDetail.setAgreementDoc(imageForMfi);
						break;
					default:
						break;
					}
				}
				count++;
			}
			detailsRepository.save(mfiApplicationDetail);
			return true;
		}
		return false;
	}

	public Map<String, Object> getConsolidateInfo(MFIApplicantDetail mfiApplicationDetail) {
		Map<String, Object> map = new HashMap<String, Object>();
		Boolean isconsolidated = false;
		String consolidatename = "";
		try {
			String firstName = mfiApplicationDetail.getFirstName().trim();
			String lastName = mfiApplicationDetail.getLastName().trim();
			String fullName = firstName.concat(lastName).trim();

			File fXmlFile = new File(consolidateUrl);
			DocumentBuilderFactory dbFactory = null;
			DocumentBuilder dBuilder = null;
			Document doc = null;
			try {
				dbFactory = DocumentBuilderFactory.newInstance();
				dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
				dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.parse(fXmlFile);
			} catch (Exception e) {
				logger.error("Error while getting consolidate document");
			}

			// Start Compare with FIRST_NAME / SECOND_NAME / THIRD_NAME / FOURTH_NAME

			NodeList nodeList = doc.getElementsByTagName("INDIVIDUAL");
			for (int individual = 0; individual < nodeList.getLength(); individual++) {

				Node iNode = nodeList.item(individual);
				Element iElement = (Element) iNode;

//				System.out.println("INDIVIDUAL Node Name :" + iNode.getNodeName());
				String firstNameXML = checkStringNulld(iElement.getElementsByTagName("FIRST_NAME"));
				String secondNameXML = checkStringNulld(iElement.getElementsByTagName("SECOND_NAME"));
				String thirdNameXML = checkStringNulld(iElement.getElementsByTagName("THIRD_NAME"));
				String fourthNameXML = checkStringNulld(iElement.getElementsByTagName("FOURTH_NAME"));

				String compareStr = firstNameXML.concat(secondNameXML).concat(thirdNameXML).concat(fourthNameXML);
				if (fullName.equalsIgnoreCase(compareStr)) {
					isconsolidated = true;
					consolidatename = firstNameXML + " " + secondNameXML + " " + thirdNameXML + " " + fourthNameXML;
					logger.info("MATCH FOUND======={}=====>", consolidatename);
					break;
				}
			}

			// Start Compare with ALIAS_NAME
			if (!isconsolidated) {
				NodeList aliasNodeList = doc.getElementsByTagName("INDIVIDUAL_ALIAS");
				for (int alias = 0; alias < aliasNodeList.getLength(); alias++) {
					Node aliasNode = aliasNodeList.item(alias);
					Element aliasElement = (Element) aliasNode;
//					logger.info("ALIAS Node Name======={}=====>",aliasElement.getNodeName());
					if ((firstName + " " + lastName)
							.equalsIgnoreCase(checkStringNulld(aliasElement.getElementsByTagName("ALIAS_NAME")))) {
						logger.info("MATCH FOUND IN ALIAS======={}=====>",
								checkStringNulld(aliasElement.getElementsByTagName("ALIAS_NAME")));
						isconsolidated = true;
						consolidatename = checkStringNulld(aliasElement.getElementsByTagName("ALIAS_NAME"));
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error while comparing name with consolidate list");
		}
		map.put("isConsolidated", isconsolidated);
		map.put("consolidateName", consolidatename);
		return map;
	}

	public static String checkStringNulld(NodeList nodeList) {
		try {
			if (!com.capitaworld.service.loans.utils.CommonUtils.isObjectNullOrEmpty(nodeList)
					&& nodeList.getLength() > 0) {
				return nodeList.item(0).getTextContent().trim();
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}
		return "";
	}
}
