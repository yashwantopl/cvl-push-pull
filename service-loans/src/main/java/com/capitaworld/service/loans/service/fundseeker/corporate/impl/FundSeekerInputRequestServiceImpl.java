package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaTransactionManager;
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
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.common.HunterRequestDataResponse;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FundSeekerInputRequestService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FundSeekerInputRequestServiceImpl implements FundSeekerInputRequestService {


    private static final Logger logger = LoggerFactory.getLogger(FundSeekerInputRequestServiceImpl.class);

    @Autowired
    private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

    @Autowired
    private PrimaryCorporateDetailRepository primaryCorporateDetailRepository;

    @Autowired
    private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;

    @Autowired
    private DirectorBackgroundDetailsRepository directorBackgroundDetailsRepository;
    
    @Autowired
    private ConnectClient connectClient;

    @Autowired
    private CorporateApplicantService corporateApplicantService;

    @Autowired
    private JpaTransactionManager jpaTransactionManager;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    
    @Autowired
    private LoanApplicationService loanApplicationService;
    
    @Autowired
    private FraudAnalyticsClient fraudAnalyticsClient;

    @Override
    public boolean saveOrUpdate(FundSeekerInputRequestResponse fundSeekerInputRequest) throws Exception {
        try {
            logger.info("getting corporateApplicantDetail from applicationId::"+fundSeekerInputRequest.getApplicationId());
            CorporateApplicantDetail corporateApplicantDetail=corporateApplicantDetailRepository.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
            if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
                logger.info("corporateApplicantDetail is null created new object");
                corporateApplicantDetail=new CorporateApplicantDetail();
                BeanUtils.copyProperties(fundSeekerInputRequest,corporateApplicantDetail,"secondAddress","sameAs","creditRatingId",
        				"contLiabilityFyAmt","contLiabilitySyAmt" ,"contLiabilityTyAmt" ," contLiabilityYear","notApplicable","aboutUs","id","isActive");
                corporateApplicantDetail.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
                corporateApplicantDetail.setCreatedBy(fundSeekerInputRequest.getUserId());
                corporateApplicantDetail.setCreatedDate(new Date());
                corporateApplicantDetail.setIsActive(true);
            } else {
            	BeanUtils.copyProperties(fundSeekerInputRequest,corporateApplicantDetail,"secondAddress","sameAs","creditRatingId",
        				"contLiabilityFyAmt","contLiabilitySyAmt" ,"contLiabilityTyAmt" ," contLiabilityYear","notApplicable","aboutUs","id");
            	corporateApplicantDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
                corporateApplicantDetail.setModifiedDate(new Date());
            }

            corporateApplicantDetailRepository.save(corporateApplicantDetail);

            //----INDUSTRY SECTOR SUBSECTOR SAVE START
            // industry data save
            corporateApplicantService.saveIndustry(corporateApplicantDetail.getApplicationId().getId(), fundSeekerInputRequest.getIndustrylist());
            // Sector data save
            corporateApplicantService.saveSector(corporateApplicantDetail.getApplicationId().getId(), fundSeekerInputRequest.getSectorlist());
            // sub sector save
            corporateApplicantService.saveSubSector(corporateApplicantDetail.getApplicationId().getId(), fundSeekerInputRequest.getSubsectors());
            //----INDUSTRY SECTOR SUBSECTOR SAVE END

            logger.info("getting primaryCorporateDetail from applicationId::"+fundSeekerInputRequest.getApplicationId());
            PrimaryCorporateDetail primaryCorporateDetail=primaryCorporateDetailRepository.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
            if(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail))
            {
                logger.info("primaryCorporateDetail is null created new object");
                primaryCorporateDetail=new PrimaryCorporateDetail();
            }
            BeanUtils.copyProperties(fundSeekerInputRequest,primaryCorporateDetail);
            primaryCorporateDetail.setAmount(fundSeekerInputRequest.getLoanAmount());

            primaryCorporateDetail.setIsApplicantDetailsFilled(true);
            primaryCorporateDetail.setIsApplicantPrimaryFilled(true);
            primaryCorporateDetail.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
            primaryCorporateDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
            primaryCorporateDetail.setModifiedDate(new Date());

            primaryCorporateDetailRepository.saveAndFlush(primaryCorporateDetail);

            List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList=fundSeekerInputRequest.getFinancialArrangementsDetailRequestsList();
            for(FinancialArrangementsDetailRequest reqObj : financialArrangementsDetailRequestsList) {
                FinancialArrangementsDetail saveFinObj = null;
                if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
                    saveFinObj = financialArrangementDetailsRepository.findByIdAndIsActive(reqObj.getId(), true);
                }
                if(CommonUtils.isObjectNullOrEmpty(saveFinObj)){
                    saveFinObj = new FinancialArrangementsDetail();
                    BeanUtils.copyProperties(reqObj, saveFinObj,"id","createdBy","createdDate","modifiedBy","modifiedDate","isActive");

                    saveFinObj.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
                    saveFinObj.setCreatedBy(fundSeekerInputRequest.getUserId());
                    saveFinObj.setCreatedDate(new Date());
                    saveFinObj.setIsActive(true);
                } else {
                    BeanUtils.copyProperties(reqObj, saveFinObj,"id","createdBy","createdDate","modifiedBy","modifiedDate");
                    saveFinObj.setModifiedBy(fundSeekerInputRequest.getUserId());
                    saveFinObj.setModifiedDate(new Date());
                }
                financialArrangementDetailsRepository.save(saveFinObj);
            }

           return true;

        } catch (Exception e) {
            logger.info("Throw Exception while save and update Fundseeker input request !!");
            e.printStackTrace();
            throw new Exception();
        }
    }



    @Override
    public ResponseEntity<LoansResponse> saveOrUpdateDirectorDetail(FundSeekerInputRequestResponse fundSeekerInputRequest) {
        try {
            //==== Applicant Address
        	
        	logger.info("Enter in save directors details ---------------------------------------->" + fundSeekerInputRequest.getApplicationId());
            CorporateApplicantDetail corporateApplicantDetail=corporateApplicantDetailRepository.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
            if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
                logger.info("corporateApplicantDetail is null created new object");
                corporateApplicantDetail=new CorporateApplicantDetail();
                BeanUtils.copyProperties(fundSeekerInputRequest,corporateApplicantDetail,"aadhar","secondAddress","sameAs","creditRatingId",
                        "contLiabilityFyAmt","contLiabilitySyAmt" ,"contLiabilityTyAmt" ," contLiabilityYear","notApplicable","aboutUs","id","isActive");
                corporateApplicantDetail.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
                corporateApplicantDetail.setCreatedBy(fundSeekerInputRequest.getUserId());
                corporateApplicantDetail.setCreatedDate(new Date());
                corporateApplicantDetail.setIsActive(true);
            } else {
            	logger.info("constitution id  ------------------------------------------>" + corporateApplicantDetail.getConstitutionId());
            	CorporateApplicantDetail copyObj = corporateApplicantDetail;
                BeanUtils.copyProperties(fundSeekerInputRequest,corporateApplicantDetail,"aadhar","secondAddress","sameAs","creditRatingId",
                        "contLiabilityFyAmt","contLiabilitySyAmt" ,"contLiabilityTyAmt" ," contLiabilityYear","notApplicable","aboutUs","id","constitutionId");
                logger.info("Before save constitution id ---------------> " + fundSeekerInputRequest.getKeyVericalFunding() + "---------------in DB------------->" + copyObj.getConstitutionId());
                corporateApplicantDetail.setKeyVericalFunding(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getKeyVericalFunding()) ? fundSeekerInputRequest.getKeyVericalFunding() : copyObj.getKeyVericalFunding());
                corporateApplicantDetail.setKeyVerticalSector(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getKeyVerticalSector()) ? fundSeekerInputRequest.getKeyVerticalSector() : copyObj.getKeyVerticalSector());
                corporateApplicantDetail.setKeyVerticalSubsector(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getKeyVerticalSubsector()) ? fundSeekerInputRequest.getKeyVerticalSubsector() : copyObj.getKeyVerticalSubsector());
                corporateApplicantDetail.setOrganisationName(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getOrganisationName()) ? fundSeekerInputRequest.getOrganisationName() : copyObj.getOrganisationName());
                corporateApplicantDetail.setAadhar(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getAadhar()) ? fundSeekerInputRequest.getAadhar() : copyObj.getAadhar());
                corporateApplicantDetail.setMsmeRegistrationNumber(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getMsmeRegistrationNumber()) ? fundSeekerInputRequest.getMsmeRegistrationNumber() : copyObj.getMsmeRegistrationNumber());
                corporateApplicantDetail.setConstitutionId(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequest.getConstitutionId()) ? fundSeekerInputRequest.getConstitutionId() : copyObj.getConstitutionId());
                
                corporateApplicantDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
                corporateApplicantDetail.setModifiedDate(new Date());
            }
            copyAddressFromRequestToDomain(fundSeekerInputRequest, corporateApplicantDetail);
            logger.info("Just Before Save ------------------------------------->" + corporateApplicantDetail.getConstitutionId());
            corporateApplicantDetailRepository.save(corporateApplicantDetail);
            //==== Director details
            List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList=fundSeekerInputRequest.getDirectorBackgroundDetailRequestsList();

            try {
                for(DirectorBackgroundDetailRequest reqObj : directorBackgroundDetailRequestList) {
                    DirectorBackgroundDetail saveDirObj = null;
                    if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
                        saveDirObj = directorBackgroundDetailsRepository.findByIdAndIsActive(reqObj.getId(), true);
                        logger.info("Old Object Retrived For Director saveDirObj.getId()==========================>{}",saveDirObj.getId());
                        BeanUtils.copyProperties(reqObj, saveDirObj,"id","createdBy","createdDate","modifiedBy","modifiedDate");
                        saveDirObj.setModifiedBy(fundSeekerInputRequest.getUserId());
                        saveDirObj.setModifiedDate(new Date());
                    }
                    else
                    {
                        logger.info("New Object Created for Director");
                        saveDirObj = new DirectorBackgroundDetail();
                        BeanUtils.copyProperties(reqObj, saveDirObj,"id","createdBy","createdDate","modifiedBy","modifiedDate","isActive");
                        saveDirObj.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
                        saveDirObj.setCreatedBy(fundSeekerInputRequest.getUserId());
                        saveDirObj.setCreatedDate(new Date());
                        saveDirObj.setIsActive(true);
                    }

                    directorBackgroundDetailsRepository.save(saveDirObj);
                }
            }catch (Exception e){
                logger.info("Directors ===============> Throw Exception While Save Director Background Details -------->");
                e.printStackTrace();
            }

            LoansResponse res=new LoansResponse("director detail successfully saved",HttpStatus.OK.value());
            res.setFlag(true);
            logger.error("director detail successfully saved");
            return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);

        }
        catch (Exception e)
        {
            LoansResponse res=new LoansResponse("error while saving director detail",HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.error("error while saving director detail");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<LoansResponse> get(FundSeekerInputRequestResponse fsInputReq) {

        FundSeekerInputRequestResponse fsInputRes = new FundSeekerInputRequestResponse();
        fsInputRes.setApplicationId(fsInputReq.getApplicationId());


        try {
            CorporateApplicantDetail corpApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(fsInputReq.getApplicationId());
            if(CommonUtils.isObjectNullOrEmpty(corpApplicantDetail)) {
            	logger.info("Data not found for given applicationid");
            	fsInputRes.setFinancialArrangementsDetailRequestsList(Collections.EMPTY_LIST);
                return new ResponseEntity<LoansResponse>(new LoansResponse("Data not found for given applicationid",HttpStatus.BAD_REQUEST.value(),fsInputRes),HttpStatus.OK);
            }
            
            BeanUtils.copyProperties(corpApplicantDetail,fsInputRes);

            PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(fsInputReq.getApplicationId());
            if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)) {
            	BeanUtils.copyProperties(primaryCorporateDetail,fsInputRes);
            }
            
            List<FinancialArrangementsDetail> finArngDetailList = financialArrangementDetailsRepository.listSecurityCorporateDetailByAppId(fsInputReq.getApplicationId());
            
            List<FinancialArrangementsDetailRequest> finArrngDetailResList = new ArrayList<FinancialArrangementsDetailRequest>(finArngDetailList.size());

            FinancialArrangementsDetailRequest finArrngDetailReq = null;
            for(FinancialArrangementsDetail finArrngDetail : finArngDetailList) {
                finArrngDetailReq = new FinancialArrangementsDetailRequest();
                BeanUtils.copyProperties(finArrngDetail,finArrngDetailReq);
                finArrngDetailResList.add(finArrngDetailReq);
            }
            fsInputRes.setFinancialArrangementsDetailRequestsList(finArrngDetailResList);

            return new ResponseEntity<LoansResponse>(new LoansResponse("One form data successfully fetched",HttpStatus.OK.value(),fsInputRes),HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while fetching one form data");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(new LoansResponse("Error while fetching one form input data",HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<LoansResponse> getDirectorDetail(FundSeekerInputRequestResponse fundSeekerInputRequest) {

        FundSeekerInputRequestResponse fundSeekerInputResponse=new FundSeekerInputRequestResponse();
        fundSeekerInputResponse.setApplicationId(fundSeekerInputRequest.getApplicationId());
        try {
            //=== Applicant Address
            CorporateApplicantDetail corporateApplicantDetail=corporateApplicantDetailRepository.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
            if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail))
            {
            	fundSeekerInputResponse.setDirectorBackgroundDetailRequestsList(Collections.emptyList());
                logger.info("Data not found for given applicationid");
                return new ResponseEntity<LoansResponse>(new LoansResponse("Data not found for given applicationid",HttpStatus.BAD_REQUEST.value(),fundSeekerInputResponse),HttpStatus.OK);
            }
            
            BeanUtils.copyProperties(corporateApplicantDetail,fundSeekerInputResponse);
            copyAddressFromDomainToRequest(corporateApplicantDetail, fundSeekerInputResponse);
            //=== Director
            List<DirectorBackgroundDetail> directorBackgroundDetailList = directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(fundSeekerInputRequest.getApplicationId());
            
            List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList= new ArrayList<DirectorBackgroundDetailRequest>(directorBackgroundDetailList.size());
            
            DirectorBackgroundDetailRequest directorBackgroundDetailRequest =null; 
            for(DirectorBackgroundDetail directorBackgroundDetail : directorBackgroundDetailList) {
            	
                directorBackgroundDetailRequest = new DirectorBackgroundDetailRequest();
                BeanUtils.copyProperties(directorBackgroundDetail,directorBackgroundDetailRequest);
                directorBackgroundDetailRequestList.add(directorBackgroundDetailRequest);
            }
            fundSeekerInputResponse.setDirectorBackgroundDetailRequestsList(directorBackgroundDetailRequestList);

            logger.info("director detail successfully fetched");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Director detail successfully fetched",HttpStatus.OK.value(),fundSeekerInputResponse),HttpStatus.OK);

        }
        catch (Exception e) {
            logger.error("error while fetching director detail");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(new LoansResponse("Error while fetching director detail",HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }


	@Override
	public LoansResponse callMatchEngineClient(Long applicationId,Long userId,Integer businessTypeId) {
        ConnectResponse postOneForm;
		try {
			postOneForm = connectClient.postOneForm(applicationId,userId,businessTypeId);
			if(postOneForm != null) {
				logger.info("postOneForm=======================>Client Connect Response=============>{}",postOneForm.toString());
				if(!postOneForm.getProceed().booleanValue()) {
					return new LoansResponse("Not Eligibile from Matchengine",HttpStatus.BAD_REQUEST.value());
				}else {
					return new LoansResponse("Successfully Matched",HttpStatus.OK.value());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("Error while Calling Matchengine after Oneform Submit=============");
		}
		return new LoansResponse("Something went wrong while Checking your Eligibility",HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

    private static void copyAddressFromRequestToDomain(FundSeekerInputRequestResponse from, CorporateApplicantDetail to) {
        // Setting Regsiterd Address
        if (from.getFirstAddress() != null) {
            to.setRegisteredPremiseNumber(from.getFirstAddress().getPremiseNumber());
            to.setRegisteredLandMark(from.getFirstAddress().getLandMark());
            to.setRegisteredStreetName(from.getFirstAddress().getStreetName());
            to.setRegisteredPincode(from.getFirstAddress().getPincode());
            to.setRegisteredCityId(from.getFirstAddress().getCityId());
            to.setRegisteredStateId(from.getFirstAddress().getStateId());
            to.setRegisteredCountryId(from.getFirstAddress().getCountryId());
        }

		/*// Setting Administrative Address
		if (from.getSameAs() != null && from.getSameAs().booleanValue()) {
			if (from.getFirstAddress() != null) {
				to.setAdministrativePremiseNumber(from.getFirstAddress().getPremiseNumber());
				to.setAdministrativeLandMark(from.getFirstAddress().getLandMark());
				to.setAdministrativeStreetName(from.getFirstAddress().getStreetName());
				to.setAdministrativePincode(from.getFirstAddress().getPincode());
				to.setAdministrativeCityId(from.getFirstAddress().getCityId());
				to.setAdministrativeStateId(from.getFirstAddress().getStateId());
				to.setAdministrativeCountryId(from.getFirstAddress().getCountryId());
			}
		} else {
			if (from.getSecondAddress() != null) {
				to.setAdministrativePremiseNumber(from.getSecondAddress().getPremiseNumber());
				to.setAdministrativeLandMark(from.getSecondAddress().getLandMark());
				to.setAdministrativeStreetName(from.getSecondAddress().getStreetName());
				to.setAdministrativePincode(from.getSecondAddress().getPincode());
				to.setAdministrativeCityId(from.getSecondAddress().getCityId());
				to.setAdministrativeStateId(from.getSecondAddress().getStateId());
				to.setAdministrativeCountryId(from.getSecondAddress().getCountryId());
			}
		}*/
    }

    private static void copyAddressFromDomainToRequest(CorporateApplicantDetail from, FundSeekerInputRequestResponse to) {
        // Setting Regsiterd Address
        Address address = new Address();

        address.setPremiseNumber(from.getRegisteredPremiseNumber());
        address.setLandMark(from.getRegisteredLandMark());
        address.setStreetName(from.getRegisteredStreetName());
        address.setPincode(from.getRegisteredPincode());
        address.setCityId(from.getRegisteredCityId());
        address.setStateId(from.getRegisteredStateId());
        address.setCountryId(from.getRegisteredCountryId());
        to.setFirstAddress(address);
		/*if (from.getSameAs() != null && from.getSameAs()) {
			to.setSecondAddress(address);
		} else {
			address = new Address();
			address.setPremiseNumber(from.getAdministrativePremiseNumber());
			address.setLandMark(from.getAdministrativeLandMark());
			address.setStreetName(from.getAdministrativeStreetName());
			address.setPincode(from.getAdministrativePincode());
			address.setCityId(from.getAdministrativeCityId());
			address.setStateId(from.getAdministrativeStateId());
			address.setCountryId(from.getAdministrativeCountryId());
			to.setSecondAddress(address);

		}*/

        // Setting Administrative Address
    }



	/* (non-Javadoc)
	 * @see com.capitaworld.service.loans.service.fundseeker.corporate.FundSeekerInputRequestService#invokeFraudAnalytics(com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse)
	 */
	@Override
	public Boolean invokeFraudAnalytics(FundSeekerInputRequestResponse fundSeekerInputRequestResponse) throws Exception {
		
		try {
		HunterRequestDataResponse hunterRequestDataResponse = loanApplicationService.getDataForHunter(fundSeekerInputRequestResponse.getApplicationId());
		AnalyticsRequest request = new AnalyticsRequest();
		request.setApplicationId(fundSeekerInputRequestResponse.getApplicationId());
		request.setUserId(fundSeekerInputRequestResponse.getUserId());
		request.setData(hunterRequestDataResponse);
		
		AnalyticsResponse response = fraudAnalyticsClient.callHunterIIAPI(request);
		Boolean resp = false;
		if(response!=null) {
			resp = Boolean.valueOf(response.getData().toString());
		}
		return resp;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}



}
