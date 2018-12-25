package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.fraudanalytics.client.FraudAnalyticsClient;
import com.capitaworld.service.fraudanalytics.model.AnalyticsRequest;
import com.capitaworld.service.fraudanalytics.model.AnalyticsResponse;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.EmploymentDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.EmploymentDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.NTBRequest;
import com.capitaworld.service.loans.model.common.HunterRequestDataResponse;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.EmploymentDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.corporate.NTBService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class NTBServiceImpl implements NTBService {

    private static final Logger logger = LoggerFactory.getLogger(NTBServiceImpl.class);

    private static final String EXCEPTION_IN_GET_ONE_FORM_DETAIL_BY_DIRECTOR_ID_MSG = "Exception in getOneformDetailByDirectorId  :-";
    private static final String SOMETHING_GOES_WRONG_PLEASE_TRY_AGAIN_AFTER_SOMETIME_MSG = "Something goes wrong with the internal server. Please try again after sometime.";

    @Autowired
    private DirectorBackgroundDetailsRepository directorBackgroundDetailsRepository;

    @Autowired
    private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;

     @Autowired
     private EmploymentDetailRepository employmentDetailRepository;

     @Autowired
     private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

     @Autowired
     private PrimaryCorporateDetailRepository primaryCorporateDetailRepository;

     @Autowired
     private CorporateApplicantService corporateApplicantService;

    @Autowired
    private ConnectClient connectClient;

    @Autowired
    private IndustrySectorRepository industrySectorRepository;

    @Autowired
    private SubSectorRepository subSectorRepository;
    
    @Autowired
    private LoanApplicationService loanApplicationService;
    
    @Autowired
    private FraudAnalyticsClient fraudAnalyticsClient;
    
    @Autowired
    private Environment environment;

    @Override
    public DirectorBackgroundDetailRequest getOneformDetailByDirectorId(Long directorId) throws Exception {
        logger.info("Enter getOneformDetailByDirectorId() with directorID : " + directorId);

        try {
            DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetailsRepository.findByIdAndIsActive(directorId, true);
            if(CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail)) {
                logger.warn("Director Background Details not found For Id ==>{}",directorId);
                return null;
            }
            DirectorBackgroundDetailRequest directorBackgroundDetailRequest = new DirectorBackgroundDetailRequest();
//            if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail.getEmploymentDetailRequest())){
//                directorBackgroundDetail.setEmploymentDetailRequest(employmentDetailRepository.findOne(directorBackgroundDetail.getEmploymentDetailRequest().getId()));
//            }
            if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail.getEmploymentDetail())){
                EmploymentDetailRequest employmentDetailRequest = new EmploymentDetailRequest();
                BeanUtils.copyProperties(directorBackgroundDetail.getEmploymentDetail(),employmentDetailRequest);
                directorBackgroundDetailRequest.setEmploymentDetailRequest(employmentDetailRequest);
            }
            BeanUtils.copyProperties(directorBackgroundDetail, directorBackgroundDetailRequest);
            logger.info("directors Data fetched successfully :: " + directorBackgroundDetailRequest.toString());
            logger.info("Exit getOneformDetailByDirectorId()" );
            return directorBackgroundDetailRequest;

        } catch (Exception e) {
            logger.error(EXCEPTION_IN_GET_ONE_FORM_DETAIL_BY_DIRECTOR_ID_MSG,e);
            return null;
        }
    }

    @Override
    public Boolean saveOneformDetailForDirector(DirectorBackgroundDetailRequest directorBackgroundDetailRequest, Long userId) throws Exception {
         logger.info("Enter saveOneformDetailForDirector() with data : " + directorBackgroundDetailRequest.toString());
        try {
            EmploymentDetailRequest employmentDetailRequest =directorBackgroundDetailRequest.getEmploymentDetailRequest();
            EmploymentDetail employmentDetail = null;
            if (employmentDetailRequest.getId() != null) {
                employmentDetail = employmentDetailRepository.findOne(employmentDetailRequest.getId());
            } else {
                employmentDetail = new EmploymentDetail();
                employmentDetail.setCreatedBy(userId);
                employmentDetail.setCreatedDate(new Date());
            }
            BeanUtils.copyProperties(employmentDetailRequest,employmentDetail);
            employmentDetail.setModifiedBy(userId);
            employmentDetail.setModifiedDate(new Date());
            EmploymentDetail employmentDetailTemp=employmentDetailRepository.save(employmentDetail);
            logger.info("employment detail saved successfully");

            DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetailsRepository.findByIdAndIsActive(directorBackgroundDetailRequest.getId(), true);
            BeanUtils.copyProperties(directorBackgroundDetailRequest, directorBackgroundDetail, "isItrCompleted", "isCibilCompleted", "isBankStatementCompleted", "isOneFormCompleted");

            directorBackgroundDetail.setEmploymentDetail(employmentDetailTemp);
            directorBackgroundDetail.setTotalExperience(employmentDetailRequest.getTotalExperience().doubleValue());
            directorBackgroundDetail.setModifiedBy(userId);
            directorBackgroundDetail.setModifiedDate(new Date());
            directorBackgroundDetailsRepository.save(directorBackgroundDetail);
            logger.info("director detail saved successfully");
            return true;

        } catch (Exception e) {
            logger.error(EXCEPTION_IN_GET_ONE_FORM_DETAIL_BY_DIRECTOR_ID_MSG,e);
            return false;
        }
    }



    @Override
    public List<FinancialArrangementsDetailRequest> getFinancialDetails(Long applicationId, Long directorId) throws Exception {
        logger.info("Entry in getFinancialDetails() for applicationId : " + applicationId + " directorId : " + directorId);
        try{
            List<FinancialArrangementsDetail> finArngDetailList = financialArrangementDetailsRepository.findByDirectorBackgroundDetailIdAndApplicationIdIdAndIsActive(directorId, applicationId, true);
            List<FinancialArrangementsDetailRequest> finArrngDetailResList = new ArrayList<FinancialArrangementsDetailRequest>(finArngDetailList.size());

            FinancialArrangementsDetailRequest finArrngDetailReq = null;
            for(FinancialArrangementsDetail finArrngDetail : finArngDetailList) {
                finArrngDetailReq = new FinancialArrangementsDetailRequest();
                BeanUtils.copyProperties(finArrngDetail,finArrngDetailReq);
                finArrngDetailResList.add(finArrngDetailReq);
            }
            logger.info("Successfully get data for getFinancialDetails()" + finArrngDetailResList.toString());
            return finArrngDetailResList;
        }catch (Exception e){
            logger.error("Exception  in getFinancialDetails  :-",e);
            return null;
        }

    }

    @Override
    public Boolean saveFinancialDetails(List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList, Long applicationId, Long userId, Long directorId) throws Exception {
        logger.info("Entry in saveFinancialDetails() for applicationId : " + applicationId + " userId:" + userId);
        try {
            if (!CommonUtils.isListNullOrEmpty(financialArrangementsDetailRequestList)) {
                for (FinancialArrangementsDetailRequest reqObj : financialArrangementsDetailRequestList) {
                    FinancialArrangementsDetail saveFinObj = null;
                    if (!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
                        saveFinObj = financialArrangementDetailsRepository.findByIdAndIsActive(reqObj.getId(), true);
                    }
                    if (CommonUtils.isObjectNullOrEmpty(saveFinObj)) {
                        saveFinObj = new FinancialArrangementsDetail();
                        BeanUtils.copyProperties(reqObj, saveFinObj, "id", "createdBy", "createdDate", "modifiedBy", "modifiedDate", "isActive");

                        saveFinObj.setDirectorBackgroundDetail(directorBackgroundDetailsRepository.findOne(directorId));
                        saveFinObj.setApplicationId(new LoanApplicationMaster(applicationId));
                        saveFinObj.setCreatedBy(userId);
                        saveFinObj.setCreatedDate(new Date());
                        saveFinObj.setIsActive(true);
                    } else {
                        BeanUtils.copyProperties(reqObj, saveFinObj, "id", "createdBy", "createdDate", "modifiedBy", "modifiedDate");
                        saveFinObj.setModifiedBy(userId);
                        saveFinObj.setModifiedDate(new Date());
                    }
                    financialArrangementDetailsRepository.save(saveFinObj);
                }
                logger.info("successfully saved data for saveFinancialDetails() applicationId :" + applicationId+ " userId:" + userId);
            }
            DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetailsRepository.findByIdAndIsActive(directorId, true);
            directorBackgroundDetail.setOneFormCompleted(true);
            directorBackgroundDetailsRepository.save(directorBackgroundDetail);
            logger.info("director oneform_completed flag saved successfully");

            return true;
        }catch (Exception e){
            logger.error(EXCEPTION_IN_GET_ONE_FORM_DETAIL_BY_DIRECTOR_ID_MSG,e);
            return false;
        }
    }

    @Override
    public FundSeekerInputRequestResponse getOthersDetail(Long applicationId) throws Exception {
        try{
            FundSeekerInputRequestResponse fundSeekerInputRequestResponse = new FundSeekerInputRequestResponse();
            CorporateApplicantDetail corpApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
            if(!CommonUtils.isObjectNullOrEmpty(corpApplicantDetail)) {
                BeanUtils.copyProperties(corpApplicantDetail,fundSeekerInputRequestResponse);
            }
            PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);
            if(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)) {
                logger.info("Data not found for given applicationid");
                return null;
            }
            fundSeekerInputRequestResponse.setLoanAmt(CommonUtils.convertValue(primaryCorporateDetail.getLoanAmount()));
            fundSeekerInputRequestResponse.setCollateralSecurityAmountStr(CommonUtils.convertValue(primaryCorporateDetail.getCollateralSecurityAmount()));
            fundSeekerInputRequestResponse.setCostOfProjectStr(CommonUtils.convertValue(primaryCorporateDetail.getCostOfProject()));
            BeanUtils.copyProperties(primaryCorporateDetail,fundSeekerInputRequestResponse);

            //---industry
            List<Long> industryList = industrySectorRepository.getIndustryByApplicationId(applicationId);
            logger.info("TOTAL INDUSTRY FOUND ------------->" + industryList.size() + "------------By APP Id -----------> " + applicationId);
            fundSeekerInputRequestResponse.setIndustrylist(industryList);

            List<Long> sectorList = industrySectorRepository.getSectorByApplicationId(applicationId);
            logger.info("TOTAL SECTOR FOUND ------------->" + sectorList.size() + "------------By APP Id -----------> " + applicationId);
            fundSeekerInputRequestResponse.setSectorlist(sectorList);

            List<Long> subSectorList = subSectorRepository.getSubSectorByApplicationId(applicationId);
            logger.info("TOTAL SUB SECTOR FOUND ------------->" + subSectorList.size() + "fundSeekerInputRequestResponse " + applicationId);
            fundSeekerInputRequestResponse.setSubsectors(subSectorList);

            logger.info("Data found for given applicationid ==>"+applicationId + " response Data {}===>"+fundSeekerInputRequestResponse.toString());
            return fundSeekerInputRequestResponse;
        }catch (Exception e){
            logger.error("Exception  in getOthersDetail  :-",e);
            return null;
        }
    }

    @Override
    public Boolean saveOthersDetail(FundSeekerInputRequestResponse fundSeekerInputRequestResponse, Long applicationId, Long userId) throws Exception {

        try{
            logger.info("getting corporateApplicantDetail from applicationId::"+applicationId);
            CorporateApplicantDetail corporateApplicantDetail=corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
            if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
                logger.info("corporateApplicantDetail is null created new object");
                corporateApplicantDetail=new CorporateApplicantDetail();
                BeanUtils.copyProperties(fundSeekerInputRequestResponse,corporateApplicantDetail,"firstAddress","organisationName","aadhar","msmeRegistrationNumber","secondAddress","sameAs","creditRatingId",
                        "contLiabilityFyAmt","contLiabilitySyAmt" ,"contLiabilityTyAmt" ," contLiabilityYear","notApplicable","aboutUs","constitutionId","id","isActive");
                corporateApplicantDetail.setApplicationId(new LoanApplicationMaster(applicationId));
                corporateApplicantDetail.setCreatedBy(userId);
                corporateApplicantDetail.setCreatedDate(new Date());
                corporateApplicantDetail.setIsActive(true);
            } else {
                BeanUtils.copyProperties(fundSeekerInputRequestResponse,corporateApplicantDetail,"firstAddress","organisationName","aadhar","msmeRegistrationNumber","secondAddress","sameAs","creditRatingId",
                        "contLiabilityFyAmt","contLiabilitySyAmt" ,"contLiabilityTyAmt" ," contLiabilityYear","notApplicable","aboutUs","constitutionId","id");
                corporateApplicantDetail.setModifiedBy(userId);
                corporateApplicantDetail.setModifiedDate(new Date());
            }

            corporateApplicantDetailRepository.save(corporateApplicantDetail);

            //----INDUSTRY SECTOR SUBSECTOR SAVE START
            // industry data save
            corporateApplicantService.saveIndustry(corporateApplicantDetail.getApplicationId().getId(), fundSeekerInputRequestResponse.getIndustrylist());
            // Sector data save
            corporateApplicantService.saveSector(corporateApplicantDetail.getApplicationId().getId(), fundSeekerInputRequestResponse.getSectorlist());
            // sub sector save
            corporateApplicantService.saveSubSector(corporateApplicantDetail.getApplicationId().getId(), fundSeekerInputRequestResponse.getSubsectors());
            //----INDUSTRY SECTOR SUBSECTOR SAVE END

            logger.info("getting primaryCorporateDetail from applicationId::"+applicationId);
            PrimaryCorporateDetail primaryCorporateDetail=primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);
            if(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail))
            {
                logger.info("primaryCorporateDetail is null created new object");
                primaryCorporateDetail=new PrimaryCorporateDetail();
            }
            BeanUtils.copyProperties(fundSeekerInputRequestResponse,primaryCorporateDetail);
            primaryCorporateDetail.setAmount(fundSeekerInputRequestResponse.getLoanAmount());
            primaryCorporateDetail.setUserId(userId);
            primaryCorporateDetail.setIsApplicantDetailsFilled(true);
            primaryCorporateDetail.setIsApplicantPrimaryFilled(true);
            primaryCorporateDetail.setApplicationId(new LoanApplicationMaster(applicationId));
            primaryCorporateDetail.setModifiedBy(userId);
            primaryCorporateDetail.setModifiedDate(new Date());
            primaryCorporateDetail.setIsActive(true);
            primaryCorporateDetailRepository.saveAndFlush(primaryCorporateDetail);

            // =========================== Director details save=======================================================================
            List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = fundSeekerInputRequestResponse.getDirectorBackgroundDetailRequestsList();

            try {
                for (DirectorBackgroundDetailRequest reqObj : directorBackgroundDetailRequestList) {
                    DirectorBackgroundDetail saveDirObj = null;
                    if (!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
                        saveDirObj = directorBackgroundDetailsRepository.findByIdAndIsActive(reqObj.getId(), true);
                        logger.info("Old Object Retrived For Director saveDirObj.getId()==========================>{}",saveDirObj.getId());
                        BeanUtils.copyProperties(reqObj, saveDirObj, "id", "createdBy", "createdDate", "modifiedBy","modifiedDate");
                        saveDirObj.setModifiedBy(userId);
                        saveDirObj.setModifiedDate(new Date());
                    }
                    directorBackgroundDetailsRepository.save(saveDirObj);
                }
            } catch (Exception e) {
                logger.error("Directors ===============> Throw Exception While Save Director Details -------->",e);
            }
            logger.info("director detail saved successfully");
            return true;

            //=========================================================================================================================================
            /*DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetailsRepository.findByIdAndIsActive(fundSeekerInputRequestResponse.getDirectorBackgroundDetailRequestsList().get(0).getId(), true);
            directorBackgroundDetail.setIsMainDirector(true);
            directorBackgroundDetail.setModifiedBy(userId);
            directorBackgroundDetail.setModifiedDate(new Date());
            directorBackgroundDetailsRepository.save(directorBackgroundDetail);*/

        }catch (Exception e){
            logger.error("Throw Exception while save and update Others Detail !!",e);
            throw new Exception();
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
                        SOMETHING_GOES_WRONG_PLEASE_TRY_AGAIN_AFTER_SOMETIME_MSG,
                        HttpStatus.BAD_REQUEST.value());
            }
            logger.info("End postDirectorBackground()");
            if (!connectResponse.getProceed().booleanValue()) {
                return new LoansResponse(connectResponse.getMessage(), HttpStatus.BAD_REQUEST.value());
            } else {
                DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetailsRepository.findByIdAndIsActive(ntbRequest.getDirectorId(), true);
                DirectorBackgroundDetailRequest directorBackgroundDetailRequest = new DirectorBackgroundDetailRequest();
                if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail.getEmploymentDetail())){
                    EmploymentDetailRequest employmentDetailRequest = new EmploymentDetailRequest();
                    BeanUtils.copyProperties(directorBackgroundDetail.getEmploymentDetail(), employmentDetailRequest);
                    directorBackgroundDetailRequest.setEmploymentDetailRequest(employmentDetailRequest);
                }
                BeanUtils.copyProperties(directorBackgroundDetail, directorBackgroundDetailRequest);
                return new LoansResponse("Success", HttpStatus.OK.value(), directorBackgroundDetailRequest);
            }
        } catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
            return null;
        }
    }

    @Override
    public LoansResponse postDirectorsChangeStage(NTBRequest ntbRequest) {
        logger.info("Start postDirectorsChangeStage()");
        try {
            ConnectResponse connectResponse = connectClient.postDirBackChangeStageNTB(ntbRequest.getApplicationId(),
                    ntbRequest.getUserId(), ntbRequest.getBusineeTypeId());
            if (connectResponse == null) {
                return new LoansResponse(
                        SOMETHING_GOES_WRONG_PLEASE_TRY_AGAIN_AFTER_SOMETIME_MSG,
                        HttpStatus.BAD_REQUEST.value());
            }
            logger.info("End postDirectorsChangeStage()");
            if (!connectResponse.getProceed().booleanValue()) {
                return new LoansResponse(connectResponse.getMessage(), HttpStatus.BAD_REQUEST.value(),false);
            } else {
                return new LoansResponse("Success data updated", HttpStatus.OK.value(),true);
            }
        } catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
            return new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public LoansResponse postOthersChangeStage(NTBRequest ntbRequest) {
        try {
            ConnectResponse connectResponse = connectClient.postNTBOneFormOtherDetails(ntbRequest.getApplicationId(), ntbRequest.getUserId(), ntbRequest.getBusineeTypeId());
            if (CommonUtils.isObjectNullOrEmpty(connectResponse)) {
                return new LoansResponse( SOMETHING_GOES_WRONG_PLEASE_TRY_AGAIN_AFTER_SOMETIME_MSG, HttpStatus.BAD_REQUEST.value());
            }
            if (!connectResponse.getProceed().booleanValue()) {
                return new LoansResponse(connectResponse.getMessage(), HttpStatus.BAD_REQUEST.value(),false);
            } else {
                return new LoansResponse("Success data updated", HttpStatus.OK.value(),true);
            }
        } catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
            return new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
    
    @Override
	public LoansResponse invokeFraudAnalytics(FundSeekerInputRequestResponse fundSeekerInputRequestResponse)
			throws Exception {
		
		try {
			logger.info("Start invokeFraudAnalytics()");
			LoansResponse res = new LoansResponse();
			if("Y".equals(String.valueOf(environment.getRequiredProperty("cw.call.service_fraudanalytics")))) {
				Boolean isNTB = false;
				HunterRequestDataResponse hunterRequestDataResponse = null;
				if(fundSeekerInputRequestResponse.getBusinessTypeId()!=null && fundSeekerInputRequestResponse.getBusinessTypeId() == 2) {// FOR NTB ONLY
					isNTB = true;
					hunterRequestDataResponse = loanApplicationService
							.getDataForHunterForNTB(fundSeekerInputRequestResponse.getApplicationId());
				}
				else {
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
			AnalyticsResponse response = fraudAnalyticsClient.callHunterIIAPI(request);
			if (response != null) {
				
				Boolean resp = false;
				if(response.getData()!=null) {
					resp = Boolean.valueOf(response.getData().toString());
				}
				res.setData(resp);
				if(resp) {
					res.setStatus(HttpStatus.OK.value());
					res.setMessage(CommonUtils.ONE_FORM_SAVED_SUCCESSFULLY);
				}
				else {
					res.setStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value());
				res.setMessage(CommonUtils.HUNTER_INELIGIBLE_MESSAGE);
				}
			}
			
			logger.info("End invokeFraudAnalytics() with resp : "+res.getData());
			return res;
			}
			else {
				logger.info("End invokeFraudAnalytics() Skiping Fraud Analytics call");
				   logger.info("Successfully Saved");
	                return new LoansResponse(CommonUtils.ONE_FORM_SAVED_SUCCESSFULLY, HttpStatus.OK.value());
	                      
			}
		} catch (Exception e) {
			//throw new Exception();
			logger.error("End invokeFraudAnalytics() ERROR IN FRAUD ANALYTICS CALL : ",e);
			 return new LoansResponse("Successfully Saved", HttpStatus.OK.value());
		}
	}

}
