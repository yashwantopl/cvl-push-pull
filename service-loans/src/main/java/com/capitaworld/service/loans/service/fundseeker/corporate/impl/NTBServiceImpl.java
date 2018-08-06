package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.dms.util.CommonUtil;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.*;
import com.capitaworld.service.loans.model.*;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.model.corporate.PrimaryCorporateRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.NTBService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NTBServiceImpl implements NTBService {

    private static final Logger logger = LoggerFactory.getLogger(NTBServiceImpl.class);

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
            logger.info("Exception  in getOneformDetailByDirectorId  :-");
            e.printStackTrace();
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
            logger.info("Exception  in getOneformDetailByDirectorId  :-");
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public List<FinancialArrangementsDetailRequest> getFinancialDetails(Long applicationId, Long directorId) throws Exception {
        logger.info("Entry in getFinancialDetails() for applicationId : " + applicationId + " directorId : " + directorId);
        try{
            List<FinancialArrangementsDetail> finArngDetailList = financialArrangementDetailsRepository.listFinancialListForPartner(directorId, applicationId, true);
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
            logger.info("Exception  in getFinancialDetails  :-");
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Boolean saveFinancialDetails(List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList, Long applicationId, Long userId, Long directorId) throws Exception {
        logger.info("Entry in saveFinancialDetails() for applicationId : " + applicationId + " userId:" + userId);
        try {
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

            DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetailsRepository.findByIdAndIsActive(directorId, true);
            directorBackgroundDetail.setOneFormCompleted(true);
            directorBackgroundDetailsRepository.save(directorBackgroundDetail);
            logger.info("director oneform_completed flag saved successfully");

            return true;
        }catch (Exception e){
            logger.info("Exception  in getOneformDetailByDirectorId  :-");
            e.printStackTrace();
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
            BeanUtils.copyProperties(primaryCorporateDetail,fundSeekerInputRequestResponse);

            return fundSeekerInputRequestResponse;
        }catch (Exception e){
            logger.info("Exception  in getOthersDetail  :-");
            e.printStackTrace();
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

            primaryCorporateDetailRepository.saveAndFlush(primaryCorporateDetail);


            DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetailsRepository.findByIdAndIsActive(fundSeekerInputRequestResponse.getDirectorBackgroundDetailRequestsList().get(0).getId(), true);
            directorBackgroundDetail.setIsMainDirector(true);
            directorBackgroundDetail.setModifiedBy(userId);
            directorBackgroundDetail.setModifiedDate(new Date());
            directorBackgroundDetailsRepository.save(directorBackgroundDetail);
            logger.info("director detail saved successfully");
            return true;

        }catch (Exception e){
            logger.info("Throw Exception while save and update Others Detail !!");
            e.printStackTrace();
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
                        "Something goes wrong with the internal server. Please try again after sometime.",
                        HttpStatus.BAD_REQUEST.value());
            }
            logger.info("End postDirectorBackground()");
            if (!connectResponse.getProceed().booleanValue()) {
                return new LoansResponse(connectResponse.getMessage(), HttpStatus.BAD_REQUEST.value());
            } else {
                DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetailsRepository.findByIdAndIsActive(ntbRequest.getDirectorId(), true);
                DirectorBackgroundDetailRequest directorBackgroundDetailRequest = new DirectorBackgroundDetailRequest();
                BeanUtils.copyProperties(directorBackgroundDetail, directorBackgroundDetailRequest);
                return new LoansResponse("Success", HttpStatus.OK.value(), directorBackgroundDetailRequest);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
                        "Something goes wrong with the internal server. Please try again after sometime.",
                        HttpStatus.BAD_REQUEST.value());
            }
            logger.info("End postDirectorsChangeStage()");
            if (!connectResponse.getProceed().booleanValue()) {
                return new LoansResponse(connectResponse.getMessage(), HttpStatus.BAD_REQUEST.value(),false);
            } else {
                return new LoansResponse("Success data updated", HttpStatus.OK.value(),true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public LoansResponse postOthersChangeStage(NTBRequest ntbRequest) {
        try {
            ConnectResponse connectResponse = connectClient.postNTBOneFormOtherDetails(ntbRequest.getApplicationId(), ntbRequest.getUserId(), ntbRequest.getBusineeTypeId());
            if (CommonUtils.isObjectNullOrEmpty(connectResponse)) {
                return new LoansResponse( "Something goes wrong with the internal server. Please try again after sometime.", HttpStatus.BAD_REQUEST.value());
            }
            if (!connectResponse.getProceed().booleanValue()) {
                return new LoansResponse(connectResponse.getMessage(), HttpStatus.BAD_REQUEST.value(),false);
            } else {
                return new LoansResponse("Success data updated", HttpStatus.OK.value(),true);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
