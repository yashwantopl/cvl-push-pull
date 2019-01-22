package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.cibil.api.utility.CibilUtils;
import com.capitaworld.connect.api.ConnectAuditErrorCode;
import com.capitaworld.connect.api.ConnectLogAuditRequest;
import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.api.ConnectStage;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.fraudanalytics.client.FraudAnalyticsClient;
import com.capitaworld.service.fraudanalytics.model.AnalyticsRequest;
import com.capitaworld.service.fraudanalytics.model.AnalyticsResponse;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorPersonalDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.DirectorPersonalDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.NTBRequest;
import com.capitaworld.service.loans.model.common.HunterRequestDataResponse;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssociatedConcernDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorPersonalDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FundSeekerInputRequestService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.enums.Constitution;

@Service
@Transactional
public class FundSeekerInputRequestServiceImpl implements FundSeekerInputRequestService {

	private static final Logger logger = LoggerFactory.getLogger(FundSeekerInputRequestServiceImpl.class);

	private static final String DATA_NOT_FOUND_FOR_GIVEN_APPLICATION_ID_MSG = "Data not found for given application id";
	private static final String DIRECTOR_DETAIL_SUCCESSFULLY_SAVED_MSG = "director detail successfully saved";
	private static final String CORPORATE_APPLICANT_DETAIL_IS_NULL_CREATED_NEW_OBJECT_MSG = "corporateApplicantDetail is null created new object";
	private static final String ERROR_WHILE_GETTING_GST_RECEIPT_FROM_S3_MSG = "Error while Getting GST Receipt from S3 : {}";
	private static final String NO_GST_RECEIPT_FOUND_FOR_APPLICATION_ID_MSG = "No GST Receipt Found for Application Id ==>{}";
	private static final String SOMETHING_GOES_WRONG_WHILE_DELETING_GST_RECEIPT_RESPONSE_FOUND_NULL_MSG = "Something goes wrong while Deleting GST Receipt for ApplicationId as Response Found Null==>{}";
	private static final String SUCCESSFULLY_DELETING_GST_RECEIPT_MSG = "Successfully Deleting GST Receipt For Application Id ==>{}";
	private static final String SOMETHING_GOES_WRONG_WHILE_DELETING_GST_RECEIPT_FOR_APPLICATION_ID_MSG = "Something goes wrong while Deleting GST Receipt for ApplicationId===>{}";
	private static final String ERROR_WHILE_DELETING_EXISTING_DOCUMENTS_OF_GST_AND_ITR_MSG = "Error while Deleting Existing Documents of GST and ITR : {}";
	private static final String ABOUT_US = "aboutUs";
	private static final String SAME_AS = "sameAs";
	private static final String CREDIT_RATING_ID = "creditRatingId";
	private static final String NOT_APPLICABLE = "notApplicable";
	private static final String SECOND_ADDRESS = "secondAddress";
	private static final String CONSTITUTION_ID = "constitutionId";
	private static final String CONT_LIABILITY_FY_AMT = "contLiabilityFyAmt";
	private static final String CONT_LIABILITY_SY_AMT = "contLiabilitySyAmt";
	private static final String CONT_LIABILITY_TY_AMT = "contLiabilityTyAmt";
	private static final String CONT_LIABILITY_YEAR = "contLiabilityYear";

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;
  
	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateDetailRepository;
	
	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;
	
	@Autowired
	private FinancialArrangementDetailsRepository financialArrangementDetailsRepository; 

	@Autowired
	private DirectorBackgroundDetailsRepository directorBackgroundDetailsRepository;
	
	@Autowired
	private DirectorBackgroundDetailsService directorBackgroundDetailsService; 
	
	@Autowired
	private AssociatedConcernDetailService associatedConcernDetailService; 
	
	@Autowired
	private AssociatedConcernDetailRepository associatedConcernDetailRepository; 

	@Autowired
	private ConnectClient connectClient;

	@Autowired
	private AnalyzerClient analyzerClient;
	
	@Autowired
	private GstClient gstClient; 
	
	@Autowired
	private DMSClient dMSClient; 

	@Autowired
	private CorporateApplicantService corporateApplicantService;

	@Autowired
	private LoanApplicationService loanApplicationService;

	@Autowired
	private FraudAnalyticsClient fraudAnalyticsClient;
	
	@Autowired
	private IndustrySectorRepository industrySectorRepository;
	    
	@Autowired
	private SubSectorRepository subSectorRepository; 
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private DirectorPersonalDetailRepository directorPersonalDetailRepository;
	
	@Override
	public boolean saveOrUpdate(FundSeekerInputRequestResponse fundSeekerInputRequest) throws LoansException {
		try {
			logger.info("getting corporateApplicantDetail from applicationId::"
					+ fundSeekerInputRequest.getApplicationId());
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
			if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
				logger.info(CORPORATE_APPLICANT_DETAIL_IS_NULL_CREATED_NEW_OBJECT_MSG);
				corporateApplicantDetail = new CorporateApplicantDetail();
				BeanUtils.copyProperties(fundSeekerInputRequest, corporateApplicantDetail, SECOND_ADDRESS, SAME_AS,"organisationName",CONSTITUTION_ID,
						CREDIT_RATING_ID, CONT_LIABILITY_FY_AMT, CONT_LIABILITY_SY_AMT, CONT_LIABILITY_TY_AMT,
						CONT_LIABILITY_YEAR, NOT_APPLICABLE, ABOUT_US, "id", CommonUtils.IS_ACTIVE);
				corporateApplicantDetail
						.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
				corporateApplicantDetail.setCreatedBy(fundSeekerInputRequest.getUserId());
				corporateApplicantDetail.setCreatedDate(new Date());
				corporateApplicantDetail.setIsActive(true);
			} else {
				BeanUtils.copyProperties(fundSeekerInputRequest, corporateApplicantDetail, SECOND_ADDRESS, SAME_AS,"organisationName",CONSTITUTION_ID,
						CREDIT_RATING_ID, CONT_LIABILITY_FY_AMT, CONT_LIABILITY_SY_AMT, CONT_LIABILITY_TY_AMT,
						CONT_LIABILITY_YEAR, NOT_APPLICABLE, ABOUT_US, "id");
				corporateApplicantDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
				corporateApplicantDetail.setModifiedDate(new Date());
			}

			corporateApplicantDetailRepository.save(corporateApplicantDetail);

			// ----INDUSTRY SECTOR SUBSECTOR SAVE START
			// industry data save
			corporateApplicantService.saveIndustry(corporateApplicantDetail.getApplicationId().getId(),
					fundSeekerInputRequest.getIndustrylist());
			// Sector data save
			corporateApplicantService.saveSector(corporateApplicantDetail.getApplicationId().getId(),
					fundSeekerInputRequest.getSectorlist());
			// sub sector save
			corporateApplicantService.saveSubSector(corporateApplicantDetail.getApplicationId().getId(),
					fundSeekerInputRequest.getSubsectors());
			// ----INDUSTRY SECTOR SUBSECTOR SAVE END

			logger.info(
					"getting primaryCorporateDetail from applicationId::" + fundSeekerInputRequest.getApplicationId());
			PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository
					.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
			
			Double requiredLoanAmount = fundSeekerInputRequest.getLoanAmount();
			if (CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)) {
				logger.info("primaryCorporateDetail is null created new object");
				primaryCorporateDetail = new PrimaryCorporateDetail();
			}
			BeanUtils.copyProperties(fundSeekerInputRequest, primaryCorporateDetail);
			
			if(fundSeekerInputRequest.getEnhancementAmount() != null) {
				requiredLoanAmount = requiredLoanAmount + fundSeekerInputRequest.getEnhancementAmount();
			}
			
			primaryCorporateDetail.setLoanAmount(requiredLoanAmount);
			primaryCorporateDetail.setEnhancementAmount(fundSeekerInputRequest.getEnhancementAmount());
			primaryCorporateDetail.setIsApplicantDetailsFilled(true);
			primaryCorporateDetail.setIsApplicantPrimaryFilled(true);
			primaryCorporateDetail.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
			logger.info("Save in LoanAppMaster with BusinessType ==>"+fundSeekerInputRequest.getBusinessTypeId());
			primaryCorporateDetail.setBusinessTypeId(fundSeekerInputRequest.getBusinessTypeId());
			primaryCorporateDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
			primaryCorporateDetail.setModifiedDate(new Date());
			primaryCorporateDetail.setIsActive(true);
			primaryCorporateDetailRepository.saveAndFlush(primaryCorporateDetail);

			List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList = fundSeekerInputRequest
					.getFinancialArrangementsDetailRequestsList();
			if(!CommonUtils.isListNullOrEmpty(financialArrangementsDetailRequestsList)) {
				Boolean saveOrUpdate = financialArrangementDetailsService.saveOrUpdate(financialArrangementsDetailRequestsList, fundSeekerInputRequest.getApplicationId(), fundSeekerInputRequest.getUserId());
				logger.info("Update Result in Loans Details==>{}",saveOrUpdate);
			}
			return true;
		} catch (Exception e) {
			logger.error("Throw Exception while save and update Fundseeker input request !!",e);
			throw new LoansException(e);
		}
	}

	@Override
	public ResponseEntity<LoansResponse> saveOrUpdateDirectorDetail(FundSeekerInputRequestResponse fundSeekerInputRequest) {
		String msg = null;
		try {
			// ==== Applicant Address

			logger.info("Enter in save directors details ---------------------------------------->"
					+ fundSeekerInputRequest.getApplicationId());
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
			if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
				logger.info(CORPORATE_APPLICANT_DETAIL_IS_NULL_CREATED_NEW_OBJECT_MSG);
				corporateApplicantDetail = new CorporateApplicantDetail();
				BeanUtils.copyProperties(fundSeekerInputRequest, corporateApplicantDetail, "aadhar", SECOND_ADDRESS,
						SAME_AS, CREDIT_RATING_ID, CONT_LIABILITY_FY_AMT, CONT_LIABILITY_SY_AMT, CONT_LIABILITY_TY_AMT,
						CONT_LIABILITY_YEAR, NOT_APPLICABLE, ABOUT_US, "id", CommonUtils.IS_ACTIVE);
				corporateApplicantDetail
						.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
				corporateApplicantDetail.setCreatedBy(fundSeekerInputRequest.getUserId());
				corporateApplicantDetail.setCreatedDate(new Date());
				corporateApplicantDetail.setIsActive(true);
			} else {
				logger.info("constitution id  ------------------------------------------>"
						+ corporateApplicantDetail.getConstitutionId());
				CorporateApplicantDetail copyObj = corporateApplicantDetail;
				BeanUtils.copyProperties(fundSeekerInputRequest, corporateApplicantDetail, "aadhar", SECOND_ADDRESS,
						SAME_AS, CREDIT_RATING_ID, CONT_LIABILITY_FY_AMT, CONT_LIABILITY_SY_AMT, CONT_LIABILITY_TY_AMT,
						CONT_LIABILITY_YEAR, NOT_APPLICABLE, ABOUT_US, "id", CONSTITUTION_ID);
				logger.info(
						"Before save constitution id ---------------> " + fundSeekerInputRequest.getKeyVericalFunding()
								+ "---------------in DB------------->" + copyObj.getConstitutionId());
				corporateApplicantDetail.setKeyVericalFunding(
						!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getKeyVericalFunding())
								? fundSeekerInputRequest.getKeyVericalFunding()
								: copyObj.getKeyVericalFunding());
				corporateApplicantDetail.setKeyVerticalSector(
						!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getKeyVerticalSector())
								? fundSeekerInputRequest.getKeyVerticalSector()
								: copyObj.getKeyVerticalSector());
				corporateApplicantDetail.setKeyVerticalSubsector(
						!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getKeyVerticalSubsector())
								? fundSeekerInputRequest.getKeyVerticalSubsector()
								: copyObj.getKeyVerticalSubsector());
				corporateApplicantDetail.setOrganisationName(
						!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getOrganisationName())
								? fundSeekerInputRequest.getOrganisationName()
								: copyObj.getOrganisationName());
				corporateApplicantDetail.setAadhar(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getAadhar())
						? fundSeekerInputRequest.getAadhar()
						: copyObj.getAadhar());
				corporateApplicantDetail.setMsmeRegistrationNumber(
						!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getMsmeRegistrationNumber())
								? fundSeekerInputRequest.getMsmeRegistrationNumber()
								: copyObj.getMsmeRegistrationNumber());
				corporateApplicantDetail
						.setConstitutionId(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getConstitutionId())
								? fundSeekerInputRequest.getConstitutionId()
								: copyObj.getConstitutionId());

				corporateApplicantDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
				corporateApplicantDetail.setModifiedDate(new Date());
			}
			
			corporateApplicantDetail.setBusinessSinceYear(fundSeekerInputRequest.getSinceYear());
			corporateApplicantDetail.setBusinessSinceMonth(fundSeekerInputRequest.getSinceMonth());
			copyAddressFromRequestToDomain(fundSeekerInputRequest, corporateApplicantDetail);

			logger.info("Just Before Save ------------------------------------->" + corporateApplicantDetail.getConstitutionId());
			corporateApplicantDetailRepository.save(corporateApplicantDetail);
			// ==== Director details
			List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = fundSeekerInputRequest.getDirectorBackgroundDetailRequestsList();

			try {
				for (DirectorBackgroundDetailRequest reqObj : directorBackgroundDetailRequestList) {
					DirectorBackgroundDetail saveDirObj = null;
					if (!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
						saveDirObj = directorBackgroundDetailsRepository.findByIdAndIsActive(reqObj.getId(), true);
						logger.info("Old Object Retrived For Director saveDirObj.getId()==========================>{}",
								saveDirObj.getId());
						BeanUtils.copyProperties(reqObj, saveDirObj, "id", "createdBy", "createdDate", "modifiedBy",
								"modifiedDate");
						saveDirObj.setModifiedBy(fundSeekerInputRequest.getUserId());
						saveDirObj.setModifiedDate(new Date());
					} else {
						logger.info("New Object Created for Director");
						saveDirObj = new DirectorBackgroundDetail();
						BeanUtils.copyProperties(reqObj, saveDirObj, "id", "createdBy", "createdDate", "modifiedBy",
								"modifiedDate", CommonUtils.IS_ACTIVE);
						saveDirObj
								.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
						saveDirObj.setCreatedBy(fundSeekerInputRequest.getUserId());
						saveDirObj.setCreatedDate(new Date());
						saveDirObj.setIsActive(true);
					}
					if(!CommonUtils.isObjectNullOrEmpty(reqObj.getIsMainDirector()) && (reqObj.getIsMainDirector())){
						DirectorPersonalDetailRequest directorPersonalDetailRequest = reqObj.getDirectorPersonalDetailRequest();
						DirectorPersonalDetail directorPersonalDetail = null;
						if(directorPersonalDetailRequest.getId() != null){
							directorPersonalDetail = directorPersonalDetailRepository.findOne(directorPersonalDetailRequest.getId());
						}else{
							directorPersonalDetail = new DirectorPersonalDetail();
							directorPersonalDetail.setCreatedBy(fundSeekerInputRequest.getUserId());
							directorPersonalDetail.setCreatedDate(new Date());
						}
						BeanUtils.copyProperties(directorPersonalDetailRequest,directorPersonalDetail);
						directorPersonalDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
						directorPersonalDetail.setModifiedDate(new Date());
						DirectorPersonalDetail directorPersonalDetailTemp=directorPersonalDetailRepository.save(directorPersonalDetail);
						logger.info("employment detail saved successfully");
						saveDirObj.setDirectorPersonalDetail(directorPersonalDetailTemp);
					}else{
						saveDirObj.setDirectorPersonalDetail(null);
					}
					directorBackgroundDetailsRepository.save(saveDirObj);
				}
			} catch (Exception e) {
				logger.error("Directors ===============> Throw Exception While Save Director Background Details -------->",e);
			}

			LoansResponse res = new LoansResponse(DIRECTOR_DETAIL_SUCCESSFULLY_SAVED_MSG, HttpStatus.OK.value());
			res.setFlag(true);
			logger.info(DIRECTOR_DETAIL_SUCCESSFULLY_SAVED_MSG);
			msg = DIRECTOR_DETAIL_SUCCESSFULLY_SAVED_MSG;
			return new ResponseEntity<LoansResponse>(res, HttpStatus.OK);

		} catch (Exception e) {
			LoansResponse res = new LoansResponse("error while saving director detail",
					HttpStatus.INTERNAL_SERVER_ERROR.value());
			msg="";
			logger.error("error while saving director detail",e);

			return new ResponseEntity<LoansResponse>(res, HttpStatus.OK);
		}finally {
			try {
				connectClient.saveAuditLog(new ConnectLogAuditRequest(fundSeekerInputRequest.getApplicationId(), ConnectStage.DIRECTOR_BACKGROUND.getId(),fundSeekerInputRequest.getUserId(),msg, ConnectAuditErrorCode.DIRECTOR_SUBMIT.toString(),CommonUtils.BusinessType.EXISTING_BUSINESS.getId()));
			} catch (Exception e){
				logger.error(CommonUtils.EXCEPTION,e);
			}
		}
	}

	@Override
	public ResponseEntity<LoansResponse> get(FundSeekerInputRequestResponse fsInputReq) {

		FundSeekerInputRequestResponse fsInputRes = new FundSeekerInputRequestResponse();
		fsInputRes.setApplicationId(fsInputReq.getApplicationId());

		try {
			CorporateApplicantDetail corpApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(fsInputReq.getApplicationId());
			if (CommonUtils.isObjectNullOrEmpty(corpApplicantDetail)) {
				logger.info(DATA_NOT_FOUND_FOR_GIVEN_APPLICATION_ID_MSG);
				fsInputRes.setFinancialArrangementsDetailRequestsList(Collections.emptyList());
				return new ResponseEntity<LoansResponse>(new LoansResponse(DATA_NOT_FOUND_FOR_GIVEN_APPLICATION_ID_MSG,
						HttpStatus.BAD_REQUEST.value(), fsInputRes), HttpStatus.OK);
			}

			BeanUtils.copyProperties(corpApplicantDetail, fsInputRes);

			PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository
					.findOneByApplicationIdId(fsInputReq.getApplicationId());
			if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)) {
				BeanUtils.copyProperties(primaryCorporateDetail, fsInputRes);
			}
			fsInputRes.setFinancialArrangementsDetailRequestsList(financialArrangementDetailsService.getFinancialArrangementDetailsList(fsInputReq.getApplicationId(), fsInputReq.getUserId()));
			
			List<Long> industryList = industrySectorRepository.getIndustryByApplicationId(fsInputReq.getApplicationId());
			logger.info("TOTAL INDUSTRY FOUND ------------>" + industryList.size() + "------------By APP Id -----------> " + fsInputReq.getApplicationId());
			fsInputRes.setIndustrylist(industryList);
            
			List<Long> sectorList = industrySectorRepository.getSectorByApplicationId(fsInputReq.getApplicationId());
			logger.info("TOTAL SECTOR FOUND ------------->" + sectorList.size() + "-----------By APP Id -----------> " + fsInputReq.getApplicationId());
			fsInputRes.setSectorlist(sectorList);
            
            List<Long> subSectorList = subSectorRepository.getSubSectorByApplicationId(fsInputReq.getApplicationId());
			logger.info("TOTAL SUB SECTOR FOUND ------------->" + subSectorList.size() + "----------By APP Id ---------> " + fsInputReq.getApplicationId());
			fsInputRes.setSubsectors(subSectorList);

			return new ResponseEntity<LoansResponse>(
					new LoansResponse("One form data successfully fetched", HttpStatus.OK.value(), fsInputRes),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while fetching one form data : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Error while fetching one form input data",
					HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<LoansResponse> getDirectorDetail(FundSeekerInputRequestResponse fundSeekerInputRequest) {

		FundSeekerInputRequestResponse fundSeekerInputResponse = new FundSeekerInputRequestResponse();
		fundSeekerInputResponse.setApplicationId(fundSeekerInputRequest.getApplicationId());
		try {
			// === Applicant Address
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
			if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
				fundSeekerInputResponse.setDirectorBackgroundDetailRequestsList(Collections.emptyList());
				logger.info(DATA_NOT_FOUND_FOR_GIVEN_APPLICATION_ID_MSG);
				return new ResponseEntity<LoansResponse>(new LoansResponse(DATA_NOT_FOUND_FOR_GIVEN_APPLICATION_ID_MSG,
						HttpStatus.BAD_REQUEST.value(), fundSeekerInputResponse), HttpStatus.OK);
			}

			BeanUtils.copyProperties(corporateApplicantDetail, fundSeekerInputResponse);
			copyAddressFromDomainToRequest(corporateApplicantDetail, fundSeekerInputResponse);
			if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getConstitutionId()) && corporateApplicantDetail.getConstitutionId()==7){
				ReportRequest reportRequest = new ReportRequest();
				reportRequest.setApplicationId(fundSeekerInputRequest.getApplicationId());
				try {
					String orgName = analyzerClient.getOrgNameByAppId(reportRequest);
					fundSeekerInputResponse.setOrganisationName(orgName);
					logger.info("Fetched Organisation Name from Bank Statement ==>"+orgName);
				} catch (Exception e) {
					logger.error("Error while getting perfios data : ",e);
				}
			}
			// === Director
			List<DirectorBackgroundDetail> directorBackgroundDetailList = directorBackgroundDetailsRepository
					.listPromotorBackgroundFromAppId(fundSeekerInputRequest.getApplicationId());

			List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = new ArrayList<DirectorBackgroundDetailRequest>(
					directorBackgroundDetailList.size());

			Date dobOfProprietor = null;
			DirectorBackgroundDetailRequest directorBackgroundDetailRequest = null;
			for (DirectorBackgroundDetail directorBackgroundDetail : directorBackgroundDetailList) {

				directorBackgroundDetailRequest = new DirectorBackgroundDetailRequest();
				BeanUtils.copyProperties(directorBackgroundDetail, directorBackgroundDetailRequest);
				if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail.getIsMainDirector()) && (directorBackgroundDetail.getIsMainDirector()) && !CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail.getDirectorPersonalDetail())){
					DirectorPersonalDetailRequest directorPersonalDetailRequest = new DirectorPersonalDetailRequest();
					BeanUtils.copyProperties(directorBackgroundDetail.getDirectorPersonalDetail(), directorPersonalDetailRequest);
					directorBackgroundDetailRequest.setDirectorPersonalDetailRequest(directorPersonalDetailRequest);
					dobOfProprietor = directorBackgroundDetail.getDob();
				}
				directorBackgroundDetailRequestList.add(directorBackgroundDetailRequest);
			}
			fundSeekerInputResponse.setDirectorBackgroundDetailRequestsList(directorBackgroundDetailRequestList);
			
			try {
				LocalDate start = null;
				if(corporateApplicantDetail.getConstitutionId() == 7) {
					start = dobOfProprietor.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				}else {
					start = LocalDate.of(corporateApplicantDetail.getEstablishmentYear(), corporateApplicantDetail.getEstablishmentMonth(), 01);
				}
				LocalDate now = LocalDate.now();
				Period diff = Period.between(start, now);
				Integer diffYear = diff.getYears();
				/*if(diff.getMonths() > 6) {
					diffYear = diffYear + 1;
				}*/
				fundSeekerInputResponse.setEstablishmentYear(diffYear);

			}catch (Exception e) {
				logger.error("error while find diff of establishment year : ",e);
			}
			
			
			logger.info("director detail successfully fetched");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Director detail successfully fetched",
					HttpStatus.OK.value(), fundSeekerInputResponse), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("error while fetching director detail : ",e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Error while fetching director detail", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@Override
	public LoansResponse callMatchEngineClient(Long applicationId, Long userId, Integer businessTypeId) {
		ConnectResponse postOneForm;
		try {
			postOneForm = connectClient.postOneForm(applicationId, userId, businessTypeId);
			if (postOneForm != null) {
				logger.info("postOneForm=======================>Client Connect Response=============>{}",
						postOneForm.toString());
				if(!postOneForm.getProceed().booleanValue() && postOneForm.getStatus() == 4){
					return new LoansResponse("Your request could not be processed now, please try again after sometime.", HttpStatus.METHOD_FAILURE.value());
				}else if (!postOneForm.getProceed().booleanValue() && postOneForm.getStatus() == 6) {
					return new LoansResponse("Not Eligibile from Matchengine", HttpStatus.BAD_REQUEST.value());
				}else if (!postOneForm.getProceed().booleanValue() && postOneForm.getStatus() == 500) {
					return new LoansResponse("Your request could not be refined now, please try again after sometime!", HttpStatus.INTERNAL_SERVER_ERROR.value());
				} else {
					return new LoansResponse("Successfully Matched", HttpStatus.OK.value());
				}
			}
		} catch (Exception e) {
			logger.error("Error while Calling Matchengine after Oneform Submit=============",e);
		}
		return new LoansResponse("Something went wrong while Checking your Eligibility",
				HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	private static void copyAddressFromRequestToDomain(FundSeekerInputRequestResponse from,
			CorporateApplicantDetail to) {
		// Setting Regsiterd Address
		if (from.getFirstAddress() != null) {
			to.setRegisteredPremiseNumber(from.getFirstAddress().getPremiseNumber());
			to.setRegisteredLandMark(from.getFirstAddress().getLandMark());
			to.setRegisteredStreetName(from.getFirstAddress().getStreetName());
			to.setRegisteredPincode(from.getFirstAddress().getPincode());
			to.setRegisteredCityId(from.getFirstAddress().getCityId());
			to.setRegisteredStateId(from.getFirstAddress().getStateId());
			to.setRegisteredCountryId(from.getFirstAddress().getCountryId());
			to.setRegisteredDistMappingId(from.getFirstAddress().getDistrictMappingId());
		}
	}

	private static void copyAddressFromDomainToRequest(CorporateApplicantDetail from,
			FundSeekerInputRequestResponse to) {
		// Setting Regsiterd Address
		Address address = new Address();

		address.setPremiseNumber(from.getRegisteredPremiseNumber());
		address.setLandMark(from.getRegisteredLandMark());
		address.setStreetName(from.getRegisteredStreetName());
		address.setPincode(from.getRegisteredPincode());
		address.setCityId(from.getRegisteredCityId());
		address.setStateId(from.getRegisteredStateId());
		address.setCountryId(from.getRegisteredCountryId());
		address.setDistrictMappingId(from.getRegisteredDistMappingId());
		to.setFirstAddress(address);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capitaworld.service.loans.service.fundseeker.corporate.
	 * FundSeekerInputRequestService#invokeFraudAnalytics(com.capitaworld.service.
	 * loans.model.corporate.FundSeekerInputRequestResponse)
	 */
	@Override
	public LoansResponse invokeFraudAnalytics(FundSeekerInputRequestResponse fundSeekerInputRequestResponse)
			throws LoansException {

		try {
			logger.info("Start invokeFraudAnalytics()");
			LoansResponse res = new LoansResponse();
			if ("Y".equals(String.valueOf(environment.getRequiredProperty("cw.call.service_fraudanalytics")))) {
				Boolean isNTB = false;
				HunterRequestDataResponse hunterRequestDataResponse = null;
				if (fundSeekerInputRequestResponse.getBusinessTypeId() != null
						&& fundSeekerInputRequestResponse.getBusinessTypeId() == 2) {// FOR NTB ONLY
					isNTB = true;
					hunterRequestDataResponse = loanApplicationService
							.getDataForHunterForNTB(fundSeekerInputRequestResponse.getApplicationId());
				} else {
					hunterRequestDataResponse = loanApplicationService
							.getDataForHunter(fundSeekerInputRequestResponse.getApplicationId());
				}

				AnalyticsRequest request = new AnalyticsRequest();
				request.setApplicationId(fundSeekerInputRequestResponse.getApplicationId());
				request.setUserId(fundSeekerInputRequestResponse.getUserId());
				request.setData(hunterRequestDataResponse);
				request.setIsNtb(isNTB);
				res.setMessage(CommonUtils.ONE_FORM_SAVED_SUCCESSFULLY);
				res.setStatus(HttpStatus.OK.value());

				try {
					AnalyticsResponse response = fraudAnalyticsClient.callHunterIIAPI(request);
				}
				catch (Exception e) {
					logger.error("End invokeFraudAnalytics() with Error : "+e.getMessage());
					return new LoansResponse(CommonUtils.ONE_FORM_SAVED_SUCCESSFULLY, HttpStatus.OK.value());
				}
			/*	if (response != null && response.getData() != null) {
					Boolean resp = Boolean.valueOf(response.getData().toString());
					res.setData(resp);
					if (resp == false) {
						res.setStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value());
						res.setMessage(CommonUtils.HUNTER_INELIGIBLE_MESSAGE);
					}
				} */

				logger.info("End invokeFraudAnalytics() with resp : " + res.getData());
				return new LoansResponse(CommonUtils.ONE_FORM_SAVED_SUCCESSFULLY, HttpStatus.OK.value());
			} else {
				logger.info("End invokeFraudAnalytics() Skiping Fraud Analytics call");
				logger.info("FUNDSEEKER INPUT SAVED SUCCESSFULLY");
				return new LoansResponse(CommonUtils.ONE_FORM_SAVED_SUCCESSFULLY, HttpStatus.OK.value());

			}
		} catch (Exception e) {
			logger.error("End invokeFraudAnalytics() Error in Fraud Analytics call",e);
			return new LoansResponse(CommonUtils.ONE_FORM_SAVED_SUCCESSFULLY, HttpStatus.OK.value());
		}
	}

	@Override
	public LoansResponse postDirectorBackground(NTBRequest ntbRequest) {
		logger.info("Start postDirectorBackground()");
		try {
			ConnectResponse connectResponse = connectClient.postDirectorBackground(ntbRequest.getApplicationId(),
					ntbRequest.getUserId(), ntbRequest.getBusineeTypeId(), ntbRequest.getDirectorId());
			if (connectResponse == null) {
				return new LoansResponse(
						"Something goes wrong with the internal server. Please try again after sometime.",
						HttpStatus.BAD_REQUEST.value());
			}
			logger.info("End postDirectorBackground()");
			if (!connectResponse.getProceed().booleanValue()) {
				return new LoansResponse(connectResponse.getMessage(), HttpStatus.BAD_REQUEST.value());
			} else {
				return new LoansResponse("Success", HttpStatus.OK.value());
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return null;
		}
	}
	
	@Override
	public LoansResponse saveOrUpdateForOnePagerEligibility(FundSeekerInputRequestResponse fundSeekerInputRequest){
		String msg = "Oneform Uniform Detail Successfully Saved";
		try{
			
		logger.info("Enter in saveOrUpdateForOnePagerEligibility ---------------------------------------->"
				+ fundSeekerInputRequest.getApplicationId());
		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
		if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
			logger.info(CORPORATE_APPLICANT_DETAIL_IS_NULL_CREATED_NEW_OBJECT_MSG);
			return new LoansResponse(CommonUtils.GENERIC_ERROR_MSG,HttpStatus.BAD_REQUEST.value());
		}
//		if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getIsGstCompleted()) || !corporateApplicantDetail.getIsGstCompleted()){
//    		return new LoansResponse(CommonUtils.GST_VALIDATION_ERROR_MSG,HttpStatus.BAD_REQUEST.value());	
//    	}
//    	if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getIsItrCompleted()) || !corporateApplicantDetail.getIsItrCompleted()){
//    		new LoansResponse(CommonUtils.ITR_VALIDATION_ERROR_MSG,HttpStatus.BAD_REQUEST.value());	
//    	}
		
		
		logger.info("constitution id  ------------------------------------------>"+ corporateApplicantDetail.getConstitutionId());
		corporateApplicantDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
		corporateApplicantDetail.setModifiedDate(new Date());
		
		if(Constitution.SOLE_PROPRIETORSHIP.getId().equals(fundSeekerInputRequest.getConstitutionId())){
			corporateApplicantDetail.setOrganisationName(fundSeekerInputRequest.getOrganisationName());
		}

		copyAddressFromRequestToDomain(fundSeekerInputRequest, corporateApplicantDetail);

		logger.info("Just Before Save ------------------------------------->" + corporateApplicantDetail.getConstitutionId());
		corporateApplicantDetailRepository.save(corporateApplicantDetail);
		
		logger.info("getting primaryCorporateDetail from applicationId::" + fundSeekerInputRequest.getApplicationId());
		PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
		if (CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)) {
			logger.info("primaryCorporateDetail is null created new object");
			throw new LoansException("PrimaryCorporateDetail Must not be Null for Application Id = >" + fundSeekerInputRequest.getApplicationId());
		}
		primaryCorporateDetail.setAmount(fundSeekerInputRequest.getLoanAmount());
		primaryCorporateDetail.setLoanAmount(fundSeekerInputRequest.getLoanAmount());
		primaryCorporateDetail.setTurnOverCurrFinYearTillMonth(fundSeekerInputRequest.getTurnOverCurrFinYearTillMonth());
		primaryCorporateDetail.setProjectedTurnOverCurrFinYear(fundSeekerInputRequest.getProjectedTurnOverCurrFinYear());
		primaryCorporateDetail.setProjectedProfitCurrFinYear(fundSeekerInputRequest.getProjectedProfitCurrFinYear());
		primaryCorporateDetail.setIsApplicantDetailsFilled(true);
		primaryCorporateDetail.setIsApplicantPrimaryFilled(true);
		primaryCorporateDetail.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
		logger.info("Save in LoanAppMaster with BusinessType ==>"+fundSeekerInputRequest.getBusinessTypeId());
		primaryCorporateDetail.setBusinessTypeId(fundSeekerInputRequest.getBusinessTypeId());
		primaryCorporateDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
		primaryCorporateDetail.setModifiedDate(new Date());
		primaryCorporateDetail.setIsActive(true);
		primaryCorporateDetailRepository.saveAndFlush(primaryCorporateDetail);
		// ==== Director details
		List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = fundSeekerInputRequest.getDirectorBackgroundDetailRequestsList();
		try {
			directorBackgroundDetailsService.inactive(fundSeekerInputRequest.getApplicationId(), fundSeekerInputRequest.getUserId());
			for (DirectorBackgroundDetailRequest reqObj : directorBackgroundDetailRequestList) {
				directorBackgroundDetailsService.saveDirectorInfo(reqObj, fundSeekerInputRequest.getApplicationId(), fundSeekerInputRequest.getUserId());
			}
		} catch (Exception e) {
			logger.error("Oneform Uniform Product ===============> Throw Exception While Save Oneform Uniform Product -------->{}",e);
		}
		financialArrangementDetailsService.saveOrUpdateManuallyAddedLoans(fundSeekerInputRequest.getFinancialArrangementsDetailRequestsList(), fundSeekerInputRequest.getApplicationId(), fundSeekerInputRequest.getUserId());
		associatedConcernDetailService.saveOrUpdate(fundSeekerInputRequest.getAssociatedConcernDetailRequestsList(), fundSeekerInputRequest.getApplicationId(), fundSeekerInputRequest.getUserId());
		LoansResponse res = new LoansResponse(msg, HttpStatus.OK.value());
		res.setFlag(true);
		res.setData(getDataForOnePagerOneForm(fundSeekerInputRequest.getApplicationId()));
		logger.info(msg);
		return res;
	} catch (Exception e) {
		LoansResponse res = new LoansResponse(CommonUtils.GENERIC_ERROR_MSG,HttpStatus.INTERNAL_SERVER_ERROR.value());
		logger.error(msg,e);
		return res;
	}finally {
		try {
			connectClient.saveAuditLog(new ConnectLogAuditRequest(fundSeekerInputRequest.getApplicationId(), ConnectStage.ONEPAGER_ONEFORM.getId(),fundSeekerInputRequest.getUserId(),msg, ConnectAuditErrorCode.ONFORM_SUBMIT.toString(),CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId()));
		} catch (Exception e){
			logger.error(CommonUtils.EXCEPTION,e);
		}
	}
}
	
	@Override
	public LoansResponse getDataForOnePagerOneForm(Long applicationId) {

		FundSeekerInputRequestResponse fundSeekerInputResponse = new FundSeekerInputRequestResponse();
		fundSeekerInputResponse.setApplicationId(applicationId);
		try {
			// === Applicant Address
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
			if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
				fundSeekerInputResponse.setDirectorBackgroundDetailRequestsList(Collections.emptyList());
				fundSeekerInputResponse.setFinancialArrangementsDetailRequestsList(Collections.emptyList());
				logger.info(DATA_NOT_FOUND_FOR_GIVEN_APPLICATION_ID_MSG);
				return new LoansResponse("Data not found for given applicationid",HttpStatus.BAD_REQUEST.value(), fundSeekerInputResponse);
			}

			BeanUtils.copyProperties(corporateApplicantDetail, fundSeekerInputResponse);
			copyAddressFromDomainToRequest(corporateApplicantDetail, fundSeekerInputResponse);
			fundSeekerInputResponse.setPan(corporateApplicantDetail.getPanNo());
			fundSeekerInputResponse.setDirectorBackgroundDetailRequestsList(directorBackgroundDetailsService.getDirectorBackgroundDetailList(applicationId, null));
			fundSeekerInputResponse.setFinancialArrangementsDetailRequestsList(financialArrangementDetailsService.getManuallyAddedFinancialArrangementDetailsList(applicationId));
			fundSeekerInputResponse.setAssociatedConcernDetailRequestsList(associatedConcernDetailService.getAssociatedConcernsDetailList(applicationId, null));
			
			//Getting Financial Information from PrimaryCorporateDetails

			PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);
			if (CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)) {
				fundSeekerInputResponse.setDirectorBackgroundDetailRequestsList(Collections.emptyList());
				logger.info("Data not found for given applicationid from Primary Corporate Details");
				return new LoansResponse("Data not found for given applicationid",HttpStatus.BAD_REQUEST.value(), fundSeekerInputResponse);
			}
			
			fundSeekerInputResponse.setTurnOverPrevFinYear(primaryCorporateDetail.getTurnOverPrevFinYear());
			fundSeekerInputResponse.setProfitCurrFinYear(primaryCorporateDetail.getProfitCurrFinYear());
			fundSeekerInputResponse.setProjectedProfitCurrFinYear(primaryCorporateDetail.getProjectedProfitCurrFinYear());
			fundSeekerInputResponse.setTurnOverCurrFinYearTillMonth(primaryCorporateDetail.getTurnOverCurrFinYearTillMonth());
			fundSeekerInputResponse.setProjectedTurnOverCurrFinYear(primaryCorporateDetail.getProjectedTurnOverCurrFinYear());
			if(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getLoanAmount())){
				fundSeekerInputResponse.setLoanAmount(primaryCorporateDetail.getAmount());
			}else{
				fundSeekerInputResponse.setLoanAmount(primaryCorporateDetail.getLoanAmount());				
			}
			
			LoansResponse loansResponse = new LoansResponse("Data Found",HttpStatus.OK.value(), fundSeekerInputResponse);
			//Getting Uploaded Documents of GST
			DocumentRequest documentRequest = new DocumentRequest();
			documentRequest.setApplicationId(applicationId);
			documentRequest.setProductDocumentMappingId(DocumentAlias.GST_RECEIPT);
			documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
			try{
					DocumentResponse listProductDocument = dMSClient.listProductDocument(documentRequest);
					if(!CommonUtils.isObjectNullOrEmpty(listProductDocument)){
						loansResponse.setListData(listProductDocument.getDataList());					
					}else{
						logger.warn(NO_GST_RECEIPT_FOUND_FOR_APPLICATION_ID_MSG,applicationId);
					}
			}catch(DocumentException documentException){
				logger.error(ERROR_WHILE_GETTING_GST_RECEIPT_FROM_S3_MSG,documentException);
			}
			
			
			//Getting Uploaded Documents of ITR
			try{
				documentRequest.setProductDocumentMappingId(DocumentAlias.CORPORATE_ITR_XML);
				DocumentResponse listProductDocument = dMSClient.listProductDocument(documentRequest);
				if(!CommonUtils.isObjectNullOrEmpty(listProductDocument)){
					loansResponse.getListData().addAll(listProductDocument.getDataList());					
				}else{
					logger.warn(NO_GST_RECEIPT_FOUND_FOR_APPLICATION_ID_MSG,applicationId);
				}
			}catch(DocumentException documentException){
				logger.error(ERROR_WHILE_GETTING_GST_RECEIPT_FROM_S3_MSG,documentException);
			}
			logger.info("Oneform Uniform Prodcut Details Successfully Fetched");
			return loansResponse;

		} catch (Exception e) {
			String msg = "Error while fetching Details for Uniform OneForm"; 
			logger.error(msg + " : "  ,e);
			return new LoansResponse(msg, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public LoansResponse verifyGST(String gstin,Long applicationId,Long userId,MultipartFile[] uploadedFiles) {
		//Uploading GST Receipt
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setApplicationId(applicationId);
		documentRequest.setProductDocumentMappingId(DocumentAlias.GST_RECEIPT);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		try{
			DocumentResponse documentResponse = dMSClient.deleteProductDocumentFromApplicationId(MultipleJSONObjectHelper.getStringfromObject(documentRequest));
			if (CibilUtils.isObjectListNull(documentResponse)) {
				logger.warn(SOMETHING_GOES_WRONG_WHILE_DELETING_GST_RECEIPT_RESPONSE_FOUND_NULL_MSG,applicationId);
			} else if (documentResponse.getStatus() == HttpStatus.OK.value()) {
				logger.info(SUCCESSFULLY_DELETING_GST_RECEIPT_MSG,applicationId);
			} else {
				logger.warn(SOMETHING_GOES_WRONG_WHILE_DELETING_GST_RECEIPT_FOR_APPLICATION_ID_MSG,applicationId);
			}
		}catch(Exception e){
			logger.error(ERROR_WHILE_DELETING_EXISTING_DOCUMENTS_OF_GST_AND_ITR_MSG, e);
		}
		
		
		boolean isDocumentUploaded = false;
		try{
			for (MultipartFile uploadedFile : uploadedFiles) {
				if(CommonUtils.isObjectNullOrEmpty(uploadedFile)) {
					continue;
				}
				documentRequest.setOriginalFileName(uploadedFile.getOriginalFilename());
				DocumentResponse uploadFile = dMSClient.uploadFile(MultipleJSONObjectHelper.getStringfromObject(documentRequest), uploadedFile);
				if (CibilUtils.isObjectListNull(uploadFile)) {
					logger.warn("Something goes wrong while uploading GST Receipt for APplicationId as Response Found Null===>{}",applicationId);
				} else if (uploadFile.getStatus() == HttpStatus.OK.value()) {
					logger.info("Successfully Uploaded GST Receipt For Application Id==>{}",applicationId);
					isDocumentUploaded = true;
				} else {
					logger.warn("Something goes wrong while uploading GST Receipt for APplicationId===>{}",applicationId);
				}
			}
		}catch(Exception e){
			logger.error("Error while Upllading GST Reveipt to S3 : {}",e);
		}
		
		try {
			GSTR1Request request = new GSTR1Request();
			request.setApplicationId(applicationId);
			request.setGstin(gstin);
			request.setUserId(userId);
			GstResponse response = gstClient.getGSTSearchData(request);
			if(response == null){
				return null;
			}
			int updateGSTFlag = 0;
			if(response.getStatus() == HttpStatus.OK.value() && "200".equalsIgnoreCase(response.getStatusCd())){
				updateGSTFlag = corporateApplicantDetailRepository.updateGSTFlag(applicationId, gstin, true);
				logger.info("GST Updated Count of TRUE WIth GST Status================>{}=====>{}====>{}",updateGSTFlag,response.getStatus(),response.getStatusCd());
			}else{
				updateGSTFlag = corporateApplicantDetailRepository.updateGSTFlag(applicationId, gstin, false);
				logger.info("GST Updated Count of FALSE WIth GST Status================>{}=====>{}====>{}",updateGSTFlag,response.getStatus(),response.getStatusCd());
			}
			
			//Getting Address Details from GST
			CorporateApplicantDetail corpApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(corpApplicantDetail) && !CommonUtils.isObjectNullOrEmpty(response.getData())) {
				LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>)response.getData();
				data.put("premiseNumber", corpApplicantDetail.getRegisteredPremiseNumber());
				data.put("landMark", corpApplicantDetail.getRegisteredLandMark());
				data.put("streetName", corpApplicantDetail.getRegisteredStreetName());
				data.put("pincode", corpApplicantDetail.getRegisteredPincode());
				data.put("cityId", corpApplicantDetail.getRegisteredCityId());
				data.put("stateId", corpApplicantDetail.getRegisteredStateId());
				data.put("sountryId", corpApplicantDetail.getRegisteredCountryId());
				data.put("districtMappingId", corpApplicantDetail.getRegisteredDistMappingId());
				response.setData(data);
			}
			//Getting Uploaded GST Receipt
//			BeanUtils.copyProperties(corpApplicantDetail, fsInputRes);
			LoansResponse loansResponse = new LoansResponse("Done",HttpStatus.OK.value(),response);
			try{
				logger.info("Uploaded Document Result for Application Id===>{}",isDocumentUploaded);
				
				if(isDocumentUploaded){
					DocumentResponse listProductDocument = dMSClient.listProductDocument(documentRequest);
					if(!CommonUtils.isObjectNullOrEmpty(listProductDocument)){
						loansResponse.setListData(listProductDocument.getDataList());					
					}else{
						logger.warn(NO_GST_RECEIPT_FOUND_FOR_APPLICATION_ID_MSG,applicationId);
					}
				}	
			}catch(DocumentException documentException){
				logger.error(ERROR_WHILE_GETTING_GST_RECEIPT_FROM_S3_MSG,documentException);
			}
			
			return loansResponse;
		} catch (Exception e) {
			logger.error("error while Verifying GST Number : {}",e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public LoansResponse updateFlag(Long applicationId,Boolean flag,Integer flagType) {
		logger.warn("flagType=================>{}",flagType);
		DocumentRequest documentRequest = new DocumentRequest();
		int count = 0;
		if(flagType == CommonUtils.APIFlags.ITR.getId()){
			count = corporateApplicantDetailRepository.updateITRFlag(applicationId, flag);
			documentRequest.setProductDocumentMappingId(DocumentAlias.CORPORATE_ITR_XML);
			logger.info("ITR Flag Change Count==>{}",count);			
		}else if(flagType == CommonUtils.APIFlags.GST.getId()){
			count = corporateApplicantDetailRepository.updateGSTFlagWithoutGstin(applicationId, flag);
			documentRequest.setProductDocumentMappingId(DocumentAlias.GST_RECEIPT);
			logger.info("ITR Flag Change Count==>{}",count);
		}else{
			logger.warn("Invalid API Flag so Returning Default False");
			return new LoansResponse("Invalid API Type",HttpStatus.BAD_REQUEST.value());
		}
		LoansResponse loansResponse = new LoansResponse("Success",HttpStatus.OK.value());
		if(count > 0){
			documentRequest.setApplicationId(applicationId);
			documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
			try{
				DocumentResponse listProductDocument = dMSClient.listProductDocument(documentRequest);
				if(!CommonUtils.isObjectNullOrEmpty(listProductDocument)){
					loansResponse.setListData(listProductDocument.getDataList());					
				}else{
					logger.warn(NO_GST_RECEIPT_FOUND_FOR_APPLICATION_ID_MSG,applicationId);
				}	
			}catch(DocumentException exception){
				logger.error("error Updating Flags and Getting Document : {}",exception);
			}
		}
		PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);
		if (CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)) {
			logger.info("Data not found for given applicationid from Primary Corporate Details");
			return loansResponse;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("turnOverPrevFinYear", primaryCorporateDetail.getTurnOverPrevFinYear());
		jsonObject.put("profitCurrFinYear", primaryCorporateDetail.getProfitCurrFinYear());
		jsonObject.put("projectedProfitCurrFinYear", primaryCorporateDetail.getProjectedProfitCurrFinYear());
		jsonObject.put("turnOverCurrFinYearTillMonth", primaryCorporateDetail.getTurnOverCurrFinYearTillMonth());
		jsonObject.put("projectedTurnOverCurrFinYear", primaryCorporateDetail.getProjectedTurnOverCurrFinYear());
		try {
			jsonObject.put("direcorsList",directorBackgroundDetailsService.getDirectorBackgroundDetailList(applicationId, null));
		} catch (Exception e) {
			logger.error("Error while Getting Directors List After Updating Flags ======>{}",e);
		}
		loansResponse.setData(jsonObject);
		return loansResponse;
	}	
	
	@SuppressWarnings("unchecked")
	public LoansResponse deleteDocument(Long applicationId,List<Long> docIds,Long mappingId){
		
		for(Long id : docIds){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			try{
				DocumentResponse inactiveFile = dMSClient.deleteProductDocument(jsonObject.toJSONString());
				if(!CommonUtils.isObjectNullOrEmpty(inactiveFile)){
					logger.warn("Delete/Inactive Document status is ===>{}======>for ApplicationId==={} for Doc Id=====>{}",inactiveFile.getStatus(),applicationId,id);
				}else{
					logger.warn("Something goes wrong while deleting/inactivating Document. The status is NULL for ApplicationId==={} for Doc Id=====>{}",applicationId,id);
				}	
			}catch(DocumentException exception){
				logger.error("error while Deleting/Inactivating Document for Document Id : {} : and Exceptions are : {}",id,exception);
			}			
		}
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setApplicationId(applicationId);
		documentRequest.setProductDocumentMappingId(mappingId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);

		LoansResponse loansResponse = new LoansResponse("Process Done.",HttpStatus.OK.value());
		try{
			DocumentResponse listProductDocument = dMSClient.listProductDocument(documentRequest);
			if(!CommonUtils.isObjectNullOrEmpty(listProductDocument)){
				loansResponse.setListData(listProductDocument.getDataList());					
			}else{
				logger.warn(NO_GST_RECEIPT_FOUND_FOR_APPLICATION_ID_MSG,applicationId);
			}	
		}catch(DocumentException exception){
			logger.error("error Updating Flags and Getting Document : {}",exception);
		}
		return loansResponse;
	}
	
	@Override
	public LoansResponse resetUniformApplication(ConnectResponse connectResponse){
		financialArrangementDetailsRepository.inActiveManuallyAddedLoans(connectResponse.getUserId(), connectResponse.getApplicationId());
		associatedConcernDetailRepository.inActive(connectResponse.getUserId(), connectResponse.getApplicationId());
		directorBackgroundDetailsRepository.inActive(connectResponse.getUserId(), connectResponse.getApplicationId());
		primaryCorporateDetailRepository.updatedFinancialFieldsForUniformProduct(connectResponse.getApplicationId(), null, null,null,null);
		corporateApplicantDetailRepository.updateGSTFlag(connectResponse.getApplicationId(), connectResponse.getGstin(), false);
		corporateApplicantDetailRepository.updateITRFlag(connectResponse.getApplicationId(), false);
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setApplicationId(connectResponse.getApplicationId());
		documentRequest.setProductDocumentMappingId(DocumentAlias.GST_RECEIPT);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		try{
			DocumentResponse documentResponse = dMSClient.deleteProductDocumentFromApplicationId(MultipleJSONObjectHelper.getStringfromObject(documentRequest));
			if (CibilUtils.isObjectListNull(documentResponse)) {
				logger.warn(SOMETHING_GOES_WRONG_WHILE_DELETING_GST_RECEIPT_RESPONSE_FOUND_NULL_MSG,connectResponse.getApplicationId());
			} else if (documentResponse.getStatus() == HttpStatus.OK.value()) {
				logger.info(SUCCESSFULLY_DELETING_GST_RECEIPT_MSG,connectResponse.getApplicationId());
			} else {
				logger.warn(SOMETHING_GOES_WRONG_WHILE_DELETING_GST_RECEIPT_FOR_APPLICATION_ID_MSG,connectResponse.getApplicationId());
			}
		}catch(Exception e){
			logger.error(ERROR_WHILE_DELETING_EXISTING_DOCUMENTS_OF_GST_AND_ITR_MSG, e);
		}
		
		try{
			documentRequest.setProductDocumentMappingId(DocumentAlias.CORPORATE_ITR_XML);
			DocumentResponse documentResponse = dMSClient.deleteProductDocumentFromApplicationId(MultipleJSONObjectHelper.getStringfromObject(documentRequest));
			if (CibilUtils.isObjectListNull(documentResponse)) {
				logger.warn(SOMETHING_GOES_WRONG_WHILE_DELETING_GST_RECEIPT_RESPONSE_FOUND_NULL_MSG,connectResponse.getApplicationId());
			} else if (documentResponse.getStatus() == HttpStatus.OK.value()) {
				logger.info(SUCCESSFULLY_DELETING_GST_RECEIPT_MSG,connectResponse.getApplicationId());
			} else {
				logger.warn(SOMETHING_GOES_WRONG_WHILE_DELETING_GST_RECEIPT_FOR_APPLICATION_ID_MSG,connectResponse.getApplicationId());
			}
		}catch(Exception e){
			logger.error(ERROR_WHILE_DELETING_EXISTING_DOCUMENTS_OF_GST_AND_ITR_MSG, e);
		}
		return new LoansResponse("Successfully Reset the Form.", HttpStatus.OK.value(), getDataForOnePagerOneForm(connectResponse.getApplicationId()));
	}

}