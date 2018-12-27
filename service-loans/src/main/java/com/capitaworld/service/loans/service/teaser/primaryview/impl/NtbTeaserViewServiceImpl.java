/**
 * 
 */
package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.cibil.api.model.CibilResponse;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.fraudanalytics.client.FraudAnalyticsClient;
import com.capitaworld.service.fraudanalytics.model.AnalyticsResponse;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FinanceMeansDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.PincodeDataResponse;
import com.capitaworld.service.loans.model.TotalCostOfProjectResponse;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.NtbPrimaryViewResponse;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateDirectorIncomeService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExistingProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinanceMeansDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.GuarantorsCorporateDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.NTBService;
import com.capitaworld.service.loans.service.fundseeker.corporate.SecurityCorporateDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.TotalCostOfProjectService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.service.teaser.primaryview.NtbTeaserViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.FinanceCategory;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.enums.Particular;
import com.capitaworld.service.oneform.enums.ProposedConstitutionOfUnitNTB;
import com.capitaworld.service.oneform.enums.ProposedDetailOfUnitNTB;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.oneform.model.SectorIndustryModel;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.thirdparty.model.CGTMSEDataResponse;
import com.capitaworld.service.thirdpaty.client.ThirdPartyClient;
import com.capitaworld.service.users.client.UsersClient;

/**
 * @author nilay
 *
 */
@Service
@Transactional
public class NtbTeaserViewServiceImpl implements NtbTeaserViewService {

	private static final Logger logger = LoggerFactory.getLogger(CorporatePrimaryViewServiceImpl.class);

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateRepository;

	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;

	@Autowired
	private DirectorBackgroundDetailsService directorBackgroundDetailsService;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private DMSClient dmsClient;

	@Autowired
	private MatchEngineClient matchEngineClient;

	@Autowired
	private UsersClient usersClient;

	@Autowired
	private IrrService irrService;

	@Autowired
	private ScoringClient scoringClient;

	@Autowired
	private AnalyzerClient analyzerClient;

	@Autowired
	private EligibilityClient eligibilityClient;

	@Autowired
	private ThirdPartyClient thirdPartyClient;

	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;

	@Autowired
	private CorporateDirectorIncomeService corporateDirectorIncomeService;

	@Autowired
	private ExistingProductDetailsService existingProductDetailsService;

	@Autowired
	private NTBService ntbService;

	@Autowired
	private DirectorBackgroundDetailsRepository dirBackgroundDetailsRepository;

	@Autowired
	private CIBILClient cibilClient;

	@Autowired
	private DirectorBackgroundDetailsRepository dirBackgroundDetails;

	@Autowired
	private FraudAnalyticsClient fraudAnalyticsClient;

	@Autowired
	private TotalCostOfProjectService costOfProjectService;

	@Autowired
	private FinanceMeansDetailsService financeMeansDetailsService;

	@Autowired
	private SecurityCorporateDetailsService securityCorporateDetailsService;

	@Autowired
	private GuarantorsCorporateDetailService guarantorsCorporateDetailService;

	@Autowired
	private AssociatedConcernDetailService associatedConcernDetailService;
	
	@Autowired
	private CorporateFinalInfoService corporateFinalInfoService;
	
	@Autowired
	private PincodeDateService pincodeDateService;

	DecimalFormat decim = new DecimalFormat("#,###.00");

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.capitaworld.service.loans.service.teaser.primaryview.NtbTeaserViewService
	 * #getNtbTeaserViewDetails(java.lang.Long, java.lang.Integer, java.lang.Long,
	 * java.lang.Boolean)
	 */
	@Override
	public NtbPrimaryViewResponse getNtbTeaserViewDetails(Long toApplicationId, Integer userType, Long userId,
			Long productMappingId, Boolean isFinal) {

		NtbPrimaryViewResponse ntbPrimaryViewRespone = new NtbPrimaryViewResponse();
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(toApplicationId);
		ntbPrimaryViewRespone.setProductId(loanApplicationMaster.getProductId());
		// ntbPrimaryViewRespone.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());

		/* ========= Matches Data ========== */
		if (userType != null && !(CommonUtils.UserType.FUND_SEEKER == userType) ) {
			// TEASER VIEW FROM FP SIDE
				try {
					MatchRequest matchRequest = new MatchRequest();
					matchRequest.setApplicationId(toApplicationId);
					matchRequest.setProductId(productMappingId);
					matchRequest.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
					MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfCorporate(matchRequest);
					ntbPrimaryViewRespone.setMatchesList(matchResponse.getMatchDisplayObjectList());
				} catch (Exception e) {
					logger.error("Error while getting matches data" + e);
				}
		}

		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.findOneByApplicationIdId(toApplicationId);
		// set value of org name to response
		if (corporateApplicantDetail != null) {
			ntbPrimaryViewRespone.setOrganisationName(corporateApplicantDetail.getOrganisationName());
			ntbPrimaryViewRespone.setNameOfEntity(corporateApplicantDetail.getOrganisationName());
			ntbPrimaryViewRespone.setConsitution(corporateApplicantDetail.getConstitutionId() != null
					? Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue().toString()
					: "-");
			ntbPrimaryViewRespone.setPan(corporateApplicantDetail.getPanNo());
			ntbPrimaryViewRespone.setEstablishDate(corporateApplicantDetail.getEstablishmentDate());

		}

		// Primary Details of applicant
		try {
			PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository
					.findOneByApplicationIdId(toApplicationId);
			if (primaryCorporateDetail != null) {

				if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCurrencyId())
						&& !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getDenominationId())) {
					ntbPrimaryViewRespone.setCurrencyDenomination(
							Currency.getById(primaryCorporateDetail.getCurrencyId()).getValue() + " in "
									+ Denomination.getById(primaryCorporateDetail.getDenominationId()).getValue());
				}
				ntbPrimaryViewRespone.setLoanType(primaryCorporateDetail.getProductId() != null
						? LoanType.getById(primaryCorporateDetail.getProductId()).getValue()
						: null);
				ntbPrimaryViewRespone.setLoanAmount(
						primaryCorporateDetail.getAmount() != null ? String.valueOf(primaryCorporateDetail.getAmount())
								: null);

				ntbPrimaryViewRespone.setPurposeOfLoan(
						CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getPurposeOfLoanId()) ? null
								: PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).getValue()
										.toString());

				ntbPrimaryViewRespone
						.setHaveCollateralSecurity(primaryCorporateDetail.getHaveCollateralSecurity() != null
								? String.valueOf(primaryCorporateDetail.getHaveCollateralSecurity())
								: null);
				ntbPrimaryViewRespone
						.setCollateralSecurityAmount(primaryCorporateDetail.getCollateralSecurityAmount() != null
								? String.valueOf(primaryCorporateDetail.getCollateralSecurityAmount())
								: null);

				ntbPrimaryViewRespone.setPromotersContribution(primaryCorporateDetail.getPromoterContribution());
				ntbPrimaryViewRespone.setPromotersContributionPer(primaryCorporateDetail.getTotalAmtPercentage() != null
						? " (" + convertValue(primaryCorporateDetail.getTotalAmtPercentage()) + "%)"
						: null);

				ntbPrimaryViewRespone.setNpOrgId(loanApplicationMaster.getNpOrgId());
				if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getModifiedDate()))
					ntbPrimaryViewRespone.setDateOfProposal(primaryCorporateDetail.getModifiedDate() != null
							? CommonUtils.DATE_FORMAT.format(primaryCorporateDetail.getModifiedDate())
							: null);
			}

		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		// DIRECTOR BACKGROUND DETAILS

		try {
			List<Map<String, Object>> directorBackgroundDetails = corporateDirectorIncomeService
					.getDirectorBackGroundDetails(toApplicationId);
			ntbPrimaryViewRespone.setDirectorBackGroundDetails(directorBackgroundDetails);
			if (directorBackgroundDetails.size() == 1) {
				logger.info("director list size====>>>>" + directorBackgroundDetails.size());
				ntbPrimaryViewRespone.setIsMultipleUser(false);
			} else {
				ntbPrimaryViewRespone.setIsMultipleUser(true);
			}

			/*
			 * for (int i = 0; i < directorBackgroundDetails.size(); i++) {
			 * DirectorBackgroundDetail dir = new DirectorBackgroundDetail();
			 * 
			 * // DirectorBackgroundDetail.class);
			 * 
			 * BeanUtils.copyProperties(directorBackgroundDetails.get(i), dir);
			 * dir.setPincode(String.valueOf(directorBackgroundDetails.get(i).get("pincode")
			 * ));
			 * 
			 * try {
			 * if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetails.get(i).get(
			 * "districtMappingId"))) { PincodeDataResponse
			 * pinRes=(pincodeDateService.getById(Long.valueOf(String.valueOf(
			 * directorBackgroundDetails.get(i).get("districtMappingId")))));
			 * ntbPrimaryViewRespone.setPindata(pinRes);
			 * 
			 * } } catch (Exception e) { logger.error(CommonUtils.EXCEPTION,e); }
			 * 
			 * 
			 * }
			 */

		} catch (Exception e) {
			logger.error("Problem to get Data of Director's Background=========> {}", e);
		}

		// Cibil Client Call
		/*
		 * CibilRequest cibilRequest = new CibilRequest();
		 * cibilRequest.setApplicationId(toApplicationId);
		 * cibilRequest.setPan(ntbPrimaryViewRespone.getDirPan());
		 */
		try {
			CibilResponse cibilRes = cibilClient.getDirectorAverageScore(toApplicationId);
			ntbPrimaryViewRespone.setCibilOfMainDir(cibilRes);
		} catch (Exception e) {
			logger.error("Error While calling Cibil Score By PanCard : ",e);
		}

		// get Director Income Details

		try {
			List<CorporateDirectorIncomeRequest> directorIncomeDetails = corporateDirectorIncomeService
					.getDirectorIncomeDetails(toApplicationId);
			ntbPrimaryViewRespone.setDirectorIncomeDetails(directorIncomeDetails);

		} catch (Exception e) {
			logger.error("Problem to get Director's Income Details===========> {}", e);
		}

		// get Other Details

		try {

			FundSeekerInputRequestResponse fundSeekerInputRequestResponse = ntbService.getOthersDetail(toApplicationId);
			/*
			 * if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.
			 * getProposedConstitutionOfUnit())) { ProposedConstitutionOfUnitNTB byIdProCons
			 * = ProposedConstitutionOfUnitNTB
			 * .getById(fundSeekerInputRequestResponse.getProposedConstitutionOfUnit());
			 * ntbPrimaryViewRespone.setProposedConstitutionOfUnit(
			 * CommonUtils.isObjectNullOrEmpty(byIdProCons) ?
			 * Integer.valueOf(byIdProCons.getValue()) : null); }
			 */
			/*
			 * if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.
			 * getProposedDetailsOfUnit())) { ProposedDetailOfUnitNTB byIdProConsDet =
			 * ProposedDetailOfUnitNTB
			 * .getById(fundSeekerInputRequestResponse.getProposedDetailsOfUnit());
			 * ntbPrimaryViewRespone.setProposedDetailOfUnitFact(
			 * !CommonUtils.isObjectNullOrEmpty(byIdProConsDet) ?
			 * String.valueOf(byIdProConsDet.getValue().toString()) : null); }
			 */

			ntbPrimaryViewRespone
					.setProposedDetailOfUnitFact(fundSeekerInputRequestResponse.getProposedDetailsOfUnit() != null
							? ProposedDetailOfUnitNTB.getById(fundSeekerInputRequestResponse.getProposedDetailsOfUnit())
									.getValue().toString()
							: '-');
			List<Long> keyVerticalFundingId = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getKeyVericalFunding()))
				keyVerticalFundingId.add(fundSeekerInputRequestResponse.getKeyVericalFunding());
			if (!CommonUtils.isListNullOrEmpty(keyVerticalFundingId)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getIndustryById(keyVerticalFundingId);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						ntbPrimaryViewRespone.setKeyVericalFunding(masterResponse.getValue());
					} else {
						ntbPrimaryViewRespone.setKeyVericalFunding("NA");
					}

				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}

			// key vertical sector
			List<Long> keyVerticalSectorId = new ArrayList<>();
			// getting sector id from mapping
			if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getKeyVerticalSector()))
				keyVerticalSectorId.add(fundSeekerInputRequestResponse.getKeyVerticalSector());

			try {
				OneFormResponse formResponse = oneFormClient
						.getIndustrySecByMappingId(fundSeekerInputRequestResponse.getKeyVerticalSector());
				SectorIndustryModel sectorIndustryModel = MultipleJSONObjectHelper
						.getObjectFromMap((Map) formResponse.getData(), SectorIndustryModel.class);

				// get key vertical sector value
				OneFormResponse oneFormResponse = oneFormClient
						.getSectorById(Arrays.asList(sectorIndustryModel.getSectorId()));
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					ntbPrimaryViewRespone.setKeyVericalSector(masterResponse.getValue());
				} else {
					ntbPrimaryViewRespone.setKeyVericalSector("NA");
				}
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}

			// key vertical Subsector
			try {
				if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getKeyVerticalSubsector())) {
					OneFormResponse oneFormResponse = oneFormClient
							.getSubSecNameByMappingId(fundSeekerInputRequestResponse.getKeyVerticalSubsector());
					ntbPrimaryViewRespone.setKeyVericalSubsector((String) oneFormResponse.getData());
				}
			} catch (Exception e) {

				logger.warn("error while getting key vertical sub-sector");
			}

			ntbPrimaryViewRespone.setOtherDetails(fundSeekerInputRequestResponse);

		} catch (Exception e) {
			logger.error("Problem to get Other Details===========> {}", e);
		}

		// GET EXISTING FINANCIAL DETAILS
		List<Long> dirIdList = dirBackgroundDetailsRepository.getDirectorIdFromApplicationId(toApplicationId);
		for (Long dirId : dirIdList) {
			try {
				List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService
						.getFinancialArrangementDetailsListDirId(dirId, toApplicationId);
				List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList = new ArrayList<>(
						financialArrangementsDetailRequestList.size());
				String directorName = dirBackgroundDetailsRepository.getDirectorNamefromDirectorId(dirId);
				FinancialArrangementsDetailResponse financialArrangementsDetailResponse = null;
				for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
					financialArrangementsDetailResponse = new FinancialArrangementsDetailResponse();
					// financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
					financialArrangementsDetailResponse
							.setOutstandingAmount(financialArrangementsDetailRequest.getOutstandingAmount());
					financialArrangementsDetailResponse
							.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
					financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
					// financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
					financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
					financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
					financialArrangementsDetailResponse.setFinancialInstitutionName(
							financialArrangementsDetailRequest.getFinancialInstitutionName());
					// financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
					// financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
					financialArrangementsDetailResponse.setDirectorName(directorName);
					financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
					ntbPrimaryViewRespone
							.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);
				}

			} catch (Exception e) {
				logger.error("Problem to get Data of Financial Arrangements Details {}", e);
			}
		}

		// BANK STATEMENT
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(toApplicationId);
		reportRequest.setUserId(userId);
		List<Data> datas = new ArrayList<>();
		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);

			List<HashMap<String, Object>> hashMaps = (List<HashMap<String, Object>>) analyzerResponse.getData();

			if (!CommonUtils.isListNullOrEmpty(hashMaps)) {

				for (HashMap<String, Object> hashMap : hashMaps) {

					Data data = MultipleJSONObjectHelper.getObjectFromMap(hashMap, Data.class);
					datas.add(data);
				}
			}
			ntbPrimaryViewRespone.setBankData(datas);

		} catch (Exception e) {
			logger.error("Error while getting perfios data : ",e);
		}

		// SCORING DATA
		ScoringRequest scoringRequest = new ScoringRequest();
		scoringRequest.setApplicationId(toApplicationId);
		scoringRequest.setFpProductId(productMappingId);

		try {

			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) scoringResponse.getDataObject(), ProposalScoreResponse.class);

			ntbPrimaryViewRespone.setDataList(scoringResponse.getDataList());
			ntbPrimaryViewRespone.setDataObject(scoringResponse.getDataObject());
			ntbPrimaryViewRespone.setScoringResponseList(scoringResponse.getScoringResponseList());

		} catch (ScoringException | IOException e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}

		// Eligibility Data

		EligibililityRequest eligibilityReq = new EligibililityRequest();
		eligibilityReq.setApplicationId(toApplicationId);
		eligibilityReq.setFpProductMappingId(productMappingId);
		logger.info(" for eligibility appid============>>" + toApplicationId);

		try {

			EligibilityResponse eligibilityResp = eligibilityClient.corporateLoanData(eligibilityReq);

			ntbPrimaryViewRespone.setEligibilityDataObject(eligibilityResp.getData());

		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}

		// CGTMSE
		try {
			boolean isMultipleUserForCgtmse = ntbPrimaryViewRespone.getIsMultipleUser();
			logger.info("is multiple user...??" + isMultipleUserForCgtmse);

			CGTMSEDataResponse cgtmseDataResp = thirdPartyClient.getCalulation(toApplicationId);

			ntbPrimaryViewRespone.setCgtmseData(cgtmseDataResp);
		} catch (Exception e) {
			logger.error("Error while getting CGTMSE data : ",e);
		}

		// Fraud Detection Data

		try {
			AnalyticsResponse hunterResp = fraudAnalyticsClient.getRuleAnalysisData(toApplicationId);

			if (!CommonUtils.isObjectListNull(hunterResp, hunterResp.getData())) {

				ntbPrimaryViewRespone.setFraudDetectionData(hunterResp);

			}
		} catch (Exception e1) {
			logger.error("------:::::...Error while fetching Fraud Detection Details...For..::::::-----", toApplicationId + CommonUtils.EXCEPTION + e1);
		}

		// GET DOCUMENTS
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setApplicationId(toApplicationId);

		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE);

		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			ntbPrimaryViewRespone.setProfilePic(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_BANK_STATEMENT);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			ntbPrimaryViewRespone.setBankStatement(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		List<Long> dirIdListReq = dirBackgroundDetailsRepository.getDirectorIdFromApplicationId(toApplicationId);
		DocumentRequest documentRequestItr = new DocumentRequest();
		documentRequestItr.setApplicationId(toApplicationId);
		documentRequestItr.setUserType(DocumentAlias.UERT_TYPE_DIRECTOR);

		List<Object> itrPdfList = new ArrayList<>();
		List<Object> itrXml = new ArrayList<>();

		for (int i = 0; i < dirIdListReq.size(); i++) {
			documentRequestItr.setDirectorId(dirIdListReq.get(i));
			documentRequestItr.setProductDocumentMappingId(DocumentAlias.CORPORATE_ITR_PDF);
			try {
				DocumentResponse documentResponseitr = dmsClient.listProductDocument(documentRequestItr);
				itrPdfList.add(documentResponseitr.getDataList());

			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}

			documentRequestItr.setProductDocumentMappingId(DocumentAlias.CORPORATE_ITR_XML);
			try {
				DocumentResponse documentResponseitrXml = dmsClient.listProductDocument(documentRequestItr);
				itrXml.add(documentResponseitrXml.getDataList());

			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}

		}
		ntbPrimaryViewRespone.setIrtPdfReport(itrPdfList);
		ntbPrimaryViewRespone.setIrtXMLReport(itrXml);

		if (isFinal) {
			
			/*// address
			
			CorporateFinalInfoRequest corporateFinalInfoRequest;
			try {
				corporateFinalInfoRequest = corporateFinalInfoService.get(userId,toApplicationId);
				//ADMIN OFFICE ADDRESS
				try {
					if(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId() != null) {
						
						PincodeDataResponse pindata=pincodeDateService.getById(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId());
						ntbPrimaryViewRespone.setAdminAddDist(pindata.getDistrictName());
						ntbPrimaryViewRespone.setAdminAddTaluko(pindata.getTaluka());
						pindata.getTaluka();
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
				if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress())){
					
					ntbPrimaryViewRespone.setAdminAdd( (corporateFinalInfoRequest.getSecondAddress().getPremiseNumber()!=null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber())) :"") + (corporateFinalInfoRequest.getSecondAddress().getStreetName() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getSecondAddress().getStreetName())) : "") + (corporateFinalInfoRequest.getSecondAddress().getLandMark() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getSecondAddress().getLandMark())) : "")+ (ntbPrimaryViewRespone.getAdminAddDist() != null ?(CommonUtils.commaReplace(ntbPrimaryViewRespone.getAdminAddDist())) :"")+ (ntbPrimaryViewRespone.getAdminAddTaluko() != null ? (CommonUtils.commaReplace(ntbPrimaryViewRespone.getAdminAddTaluko())) : "") + (corporateFinalInfoRequest.getSecondAddress().getPincode() != null ? (corporateFinalInfoRequest.getSecondAddress().getPincode()) : ""));
				}
			}
			catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
				}	
			
			//address
			
			
			try {
				corporateFinalInfoRequest = corporateFinalInfoService.get(userId,toApplicationId);
				//Reg OFFICE ADDRESS
				try {
					if(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId() != null) {
						
						PincodeDataResponse pindata=pincodeDateService.getById(corporateFinalInfoRequest.getFirstAddress().getDistrictMappingId());
						ntbPrimaryViewRespone.setRegAddDist(pindata.getDistrictName());
						ntbPrimaryViewRespone.setRegAddTaluko(pindata.getTaluka());
						pindata.getTaluka();
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
				if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress())){
					
					ntbPrimaryViewRespone.setRegAdd( (corporateFinalInfoRequest.getFirstAddress().getPremiseNumber()!=null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber())) :"") + (corporateFinalInfoRequest.getFirstAddress().getStreetName() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getFirstAddress().getStreetName())) : "") + (corporateFinalInfoRequest.getFirstAddress().getLandMark() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getFirstAddress().getLandMark())) : "")+ (ntbPrimaryViewRespone.getRegAddDist() != null ?(CommonUtils.commaReplace(ntbPrimaryViewRespone.getRegAddDist())) :"")+ (ntbPrimaryViewRespone.getRegAddTaluko() != null ? (CommonUtils.commaReplace(ntbPrimaryViewRespone.getRegAddTaluko())) : "") + (corporateFinalInfoRequest.getFirstAddress().getPincode() != null ? (corporateFinalInfoRequest.getFirstAddress().getPincode()) : ""));
				}
			}
			catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
				}	*/
			

			// EXISTING PRODUCT DETAILS
			try {
				ntbPrimaryViewRespone.setExistingProductDetailRequestList(
						existingProductDetailsService.getExistingProductDetailList(toApplicationId, userId));
			} catch (Exception e) {
				logger.error("Problem to get Data of Existing Product {}", e);
			}

			// cost of project
			try {
				List<TotalCostOfProjectRequest> costOfProjectsList = costOfProjectService
						.getCostOfProjectDetailList(toApplicationId, userId);
				List<TotalCostOfProjectResponse> costOfProjectResponses = new ArrayList<TotalCostOfProjectResponse>();
				for (TotalCostOfProjectRequest costOfProjectRequest : costOfProjectsList) {
					TotalCostOfProjectResponse costOfProjectResponse = new TotalCostOfProjectResponse();
					BeanUtils.copyProperties(costOfProjectRequest, costOfProjectResponse);
					if (costOfProjectRequest.getParticularsId() != null)
						costOfProjectResponse.setParticulars(
								Particular.getById(Integer.parseInt(costOfProjectRequest.getParticularsId().toString()))
										.getValue());
					costOfProjectResponses.add(costOfProjectResponse);
				}
				ntbPrimaryViewRespone.setTotalCostOfProjectResponseList(costOfProjectResponses);
			} catch (Exception e1) {
				logger.error("Problem to get Data of Total cost of project{}", e1);
			}

			// Means of finance

			try {
				List<FinanceMeansDetailRequest> financeMeansDetailRequestsList = financeMeansDetailsService
						.getMeansOfFinanceList(toApplicationId, userId);
				List<FinanceMeansDetailResponse> financeMeansDetailResponsesList = new ArrayList<FinanceMeansDetailResponse>();
				for (FinanceMeansDetailRequest financeMeansDetailRequest : financeMeansDetailRequestsList) {
					FinanceMeansDetailResponse detailResponse = new FinanceMeansDetailResponse();
					BeanUtils.copyProperties(financeMeansDetailRequest, detailResponse);

					if (financeMeansDetailRequest.getFinanceMeansCategoryId() != null)
						detailResponse.setFinanceMeansCategory(FinanceCategory
								.getById(Integer
										.parseInt(financeMeansDetailRequest.getFinanceMeansCategoryId().toString()))
								.getValue());
					financeMeansDetailResponsesList.add(detailResponse);
				}
				ntbPrimaryViewRespone.setFinanceMeansDetailResponseList(financeMeansDetailResponsesList);
			} catch (Exception e1) {
				logger.error("Problem to get Data of Finance Means Details {}", e1);
			}

			// Security

			try {
				ntbPrimaryViewRespone.setSecurityCorporateDetailRequestList(
						securityCorporateDetailsService.getsecurityCorporateDetailsList(toApplicationId, userId));
			} catch (Exception e) {
				logger.error("Problem to get Data of Security Details {}", e);
			}

			// get data of Details of Guarantors
			try {
				ntbPrimaryViewRespone.setGuarantorsCorporateDetailRequestList(
						guarantorsCorporateDetailService.getGuarantorsCorporateDetailList(toApplicationId, userId));
			} catch (Exception e) {
				logger.error("Problem to get Data of Details of Guarantor {}", e);
			}

			// get data of Associated Concern
			try {
				ntbPrimaryViewRespone.setAssociatedConcernDetailRequests(
						associatedConcernDetailService.getAssociatedConcernsDetailList(toApplicationId, userId));
			} catch (Exception e) {
				logger.error("Problem to get Data of Associated Concerns {}", e);
			}

			// final uploads Documents

			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_LAST_IT_RETURN);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setItr(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_BANK_STATEMENT);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setBankStatementFinalView(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_SANCTION_LETTER_COPY);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setSanctionLetter(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}

			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_NET_WORTH_STATEMENT_OF_DIRECTORS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setNetWorthStatements(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}

			documentRequest.setProductDocumentMappingId(DocumentAlias.TL_MOM_AOA);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setMomAndAoa(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}

			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_COPY_OF_PAN_CARD);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setCopyOfPanCard(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_DIRECTOR_ADDRESS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setResidenceAddOfDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_PHOTO_OF_DIRECTORS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setPhotosOfDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}

			documentRequest.setProductDocumentMappingId((long) DocumentAlias.TL_CMA);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setCmaList(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_AADHAR_CARD);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setAadhar(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_BROCHURE_OF_PROPOSED_ACTIVITY);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				ntbPrimaryViewRespone.setBrochure(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}

		}
		return ntbPrimaryViewRespone;
	}

	public String convertValue(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value) ? decim.format(value).toString() : "0";
	}
}
