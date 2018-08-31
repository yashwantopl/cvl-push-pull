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
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.NtbPrimaryViewResponse;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateDirectorIncomeService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.NTBService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.service.teaser.primaryview.NtbTeaserViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.LoanType;
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
	private NTBService ntbService;

	@Autowired
	private DirectorBackgroundDetailsRepository dirBackgroundDetailsRepository;

	@Autowired
	private CIBILClient cibilClient;

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
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
		if (userType != null) {
			if (!(CommonUtils.UserType.FUND_SEEKER == userType)) {// TEASER VIEW FROM FP SIDE
				try {
					MatchRequest matchRequest = new MatchRequest();
					matchRequest.setApplicationId(toApplicationId);
					matchRequest.setProductId(productMappingId);
					matchRequest.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
					MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfCorporate(matchRequest);
					ntbPrimaryViewRespone.setMatchesList(matchResponse.getMatchDisplayObjectList());
				} catch (Exception e) {
					logger.info("Error while getting matches data" + e);
					e.printStackTrace();
				}
			}
		}

		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.findOneByApplicationIdId(toApplicationId);
		// set value of org name to response
		if (corporateApplicantDetail != null) {
			ntbPrimaryViewRespone.setOrganisationName(corporateApplicantDetail.getOrganisationName());
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
				ntbPrimaryViewRespone.setBusinessAssetAmount(primaryCorporateDetail.getBusinessAssetAmount() != null
						? String.valueOf(primaryCorporateDetail.getBusinessAssetAmount())
						: null);
				ntbPrimaryViewRespone.setWcAmount(primaryCorporateDetail.getWcAmount() != null
						? String.valueOf(primaryCorporateDetail.getWcAmount())
						: null);
				ntbPrimaryViewRespone.setOtherAmt(primaryCorporateDetail.getOtherAmt() != null
						? String.valueOf(primaryCorporateDetail.getOtherAmt())
						: null);

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
							? DATE_FORMAT.format(primaryCorporateDetail.getModifiedDate())
							: null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// DIRECTOR BACKGROUND DETAILS

		try {
			List<Map<String, Object>> directorBackgroundDetails = corporateDirectorIncomeService
					.getDirectorBackGroundDetails(toApplicationId);
			ntbPrimaryViewRespone.setDirectorBackGroundDetails(directorBackgroundDetails);
			if (directorBackgroundDetails.size() == 1) {
				System.out.println("director list size====>>>>" + directorBackgroundDetails.size());
				ntbPrimaryViewRespone.setIsMultipleUser(false);
			} else {
				ntbPrimaryViewRespone.setIsMultipleUser(true);
			}
			/*
			 * for (int i = 0; i < directorBackgroundDetails.size(); i++) {
			 * DirectorBackgroundDetail dir = new DirectorBackgroundDetail();
			 * 
			 * //
			 * MultipleJSONObjectHelper.getObjectFromMap(directorBackgroundDetails.get(i),
			 * // DirectorBackgroundDetail.class);
			 * 
			 * BeanUtils.copyProperties(directorBackgroundDetails.get(i), dir); //
			 * dir.setGender(Integer.valueOf(String.valueOf(directorBackgroundDetails.get(i)
			 * .get("genderInt"))));
			 * dir.setId(Long.valueOf(String.valueOf(directorBackgroundDetails.get(i).get(
			 * "directorId")))); ntbPrimaryViewRespone.setIsMainDir(
			 * Boolean.valueOf(String.valueOf(directorBackgroundDetails.get(i).get(
			 * "isMainDirector")))); if (ntbPrimaryViewRespone.getIsMainDir() == true) {
			 * 
			 * ntbPrimaryViewRespone.setAppId(
			 * Long.valueOf(String.valueOf(directorBackgroundDetails.get(i).get(
			 * "applicationId"))));
			 * ntbPrimaryViewRespone.setDirPan(String.valueOf(directorBackgroundDetails.get(
			 * i).get("panNo")));
			 * 
			 * CibilRequest cibilRequest = new CibilRequest();
			 * cibilRequest.setApplicationId(ntbPrimaryViewRespone.getAppId());
			 * cibilRequest.setPan(ntbPrimaryViewRespone.getDirPan()); try {
			 * CibilScoreLogRequest cibilRes =
			 * cibilClient.getCibilScoreByPanCard(cibilRequest);
			 * ntbPrimaryViewRespone.setCibilOfMainDir(cibilRes); } catch (Exception e) {
			 * logger.info("Error While calling Cibil Score By PanCard");
			 * e.printStackTrace(); }
			 * 
			 * } }
			 */

		} catch (Exception e) {
			e.printStackTrace();
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
			logger.info("Error While calling Cibil Score By PanCard");
			e.printStackTrace();
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
			if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getProposedConstitutionOfUnit())) {
				ProposedConstitutionOfUnitNTB byIdProCons = ProposedConstitutionOfUnitNTB
						.getById(fundSeekerInputRequestResponse.getProposedConstitutionOfUnit());
				fundSeekerInputRequestResponse.setProposedConstitutionOfUnit(
						CommonUtils.isObjectNullOrEmpty(byIdProCons) ? Integer.valueOf(byIdProCons.getValue()) : null);
			}
			if (!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getProposedDetailsOfUnit())) {
				ProposedDetailOfUnitNTB byIdProConsDet = ProposedDetailOfUnitNTB
						.getById(fundSeekerInputRequestResponse.getProposedDetailsOfUnit());
				fundSeekerInputRequestResponse.setProposedDetailsOfUnit(
						CommonUtils.isObjectNullOrEmpty(byIdProConsDet) ? Integer.valueOf(byIdProConsDet.getValue())
								: null);
			}
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
					e.printStackTrace();
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
				e.printStackTrace();
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
				}
				ntbPrimaryViewRespone
						.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

			} catch (Exception e) {
				e.printStackTrace();
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

			e.printStackTrace();
			logger.info("Error while getting perfios data");

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

			e1.printStackTrace();
		}

		// Eligibility Data

		EligibililityRequest eligibilityReq = new EligibililityRequest();
		eligibilityReq.setApplicationId(toApplicationId);
		eligibilityReq.setFpProductMappingId(productMappingId);
		System.out.println(" for eligibility appid============>>" + toApplicationId);

		try {

			EligibilityResponse eligibilityResp = eligibilityClient.corporateLoanData(eligibilityReq);

			ntbPrimaryViewRespone.setEligibilityDataObject(eligibilityResp.getData());

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// CGTMSE
		try {
			boolean isMultipleUserForCgtmse = ntbPrimaryViewRespone.getIsMultipleUser();
			System.out.println("is multiple user...??" + isMultipleUserForCgtmse);

			CGTMSEDataResponse cgtmseDataResp = thirdPartyClient.getCalulation(toApplicationId);

			ntbPrimaryViewRespone.setCgtmseData(cgtmseDataResp);
		} catch (Exception e) {

			e.printStackTrace();
			logger.info("Error while getting CGTMSE data");
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
			e.printStackTrace();
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_BANK_STATEMENT);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			ntbPrimaryViewRespone.setBankStatement(documentResponse.getDataList());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.CORPORATE_ITR_PDF);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			ntbPrimaryViewRespone.setIrtPdfReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		if (isFinal) {

		}
		return ntbPrimaryViewRespone;
	}

	public String convertValue(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value) ? decim.format(value).toString() : "0";
	}
}
