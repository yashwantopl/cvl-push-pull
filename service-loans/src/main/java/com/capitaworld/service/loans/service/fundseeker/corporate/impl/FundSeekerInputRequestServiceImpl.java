package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.client.ConnectClient;
 import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
 import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
 import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
 import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
 import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
 import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
 import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
 import com.capitaworld.service.loans.model.LoansResponse;
 import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FundSeekerInputRequestService;
import com.capitaworld.service.loans.utils.CommonUtils;

import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.BeanUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;

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

    @Override
    public boolean saveOrUpdate(FundSeekerInputRequestResponse fundSeekerInputRequest) throws Exception {
        try {
            logger.info("getting corporateApplicantDetail from applicationId::"+fundSeekerInputRequest.getApplicationId());
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
            	BeanUtils.copyProperties(fundSeekerInputRequest,corporateApplicantDetail,"aadhar","secondAddress","sameAs","creditRatingId",
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

            List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList=fundSeekerInputRequest.getDirectorBackgroundDetailRequestsList();

            try {
                for(DirectorBackgroundDetailRequest reqObj : directorBackgroundDetailRequestList) {
                    DirectorBackgroundDetail saveDirObj = null;
                    if(!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
                        saveDirObj = directorBackgroundDetailsRepository.findByIdAndIsActive(reqObj.getId(), true);
                    }
                    if(CommonUtils.isObjectNullOrEmpty(saveDirObj)){
                        saveDirObj = new DirectorBackgroundDetail();
                        BeanUtils.copyProperties(reqObj, saveDirObj,"id","createdBy","createdDate","modifiedBy","modifiedDate","isActive");

                        saveDirObj.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
                        saveDirObj.setCreatedBy(fundSeekerInputRequest.getUserId());
                        saveDirObj.setCreatedDate(new Date());
                        saveDirObj.setIsActive(true);
                    } else {
                        BeanUtils.copyProperties(reqObj, saveDirObj,"id","createdBy","createdDate","modifiedBy","modifiedDate");
                        saveDirObj.setModifiedBy(fundSeekerInputRequest.getUserId());
                        saveDirObj.setModifiedDate(new Date());
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
    public ResponseEntity<LoansResponse> get(FundSeekerInputRequestResponse fundSeekerInputRequest) {

        FundSeekerInputRequestResponse fundSeekerInputResponse=new FundSeekerInputRequestResponse();

        fundSeekerInputResponse.setApplicationId(fundSeekerInputRequest.getApplicationId());

        List<FinancialArrangementsDetailRequest> financialArrangementsDetailResponseList = new ArrayList<FinancialArrangementsDetailRequest>();
        List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList= new ArrayList<DirectorBackgroundDetailRequest>();

        try {
            CorporateApplicantDetail corporateApplicantDetail=corporateApplicantDetailRepository.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
            if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail))
            {
                corporateApplicantDetail=new CorporateApplicantDetail();
            }
            BeanUtils.copyProperties(corporateApplicantDetail,fundSeekerInputResponse);

            PrimaryCorporateDetail primaryCorporateDetail=primaryCorporateDetailRepository.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
            if(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail))
            {
                primaryCorporateDetail=new PrimaryCorporateDetail();
            }
            BeanUtils.copyProperties(primaryCorporateDetail,fundSeekerInputResponse);


            List<FinancialArrangementsDetail> financialArrangementsDetailList=financialArrangementDetailsRepository.listSecurityCorporateDetailByAppId(fundSeekerInputRequest.getApplicationId());

            for(FinancialArrangementsDetail financialArrangementsDetail:financialArrangementsDetailList)
            {
                FinancialArrangementsDetailRequest financialArrangementsDetailRequest=new FinancialArrangementsDetailRequest();
                BeanUtils.copyProperties(financialArrangementsDetail,financialArrangementsDetailRequest);
                financialArrangementsDetailResponseList.add(financialArrangementsDetailRequest);
            }

            fundSeekerInputResponse.setFinancialArrangementsDetailRequestsList(financialArrangementsDetailResponseList);


           /* List<DirectorBackgroundDetail> directorBackgroundDetailList=directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(fundSeekerInputRequest.getApplicationId());

            for(DirectorBackgroundDetail directorBackgroundDetail:directorBackgroundDetailList)
            {
                DirectorBackgroundDetailRequest directorBackgroundDetailRequest=new DirectorBackgroundDetailRequest();
                BeanUtils.copyProperties(directorBackgroundDetail,directorBackgroundDetailRequest);
                directorBackgroundDetailRequestList.add(directorBackgroundDetailRequest);
            }

            fundSeekerInputResponse.setDirectorBackgroundDetailRequestsList(directorBackgroundDetailRequestList);*/

            LoansResponse res=new LoansResponse("one form data successfully fetched",HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setData(fundSeekerInputResponse);
            logger.info("one form data successfully fetched");
            return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);

        }
        catch (Exception e)
        {
            LoansResponse res=new LoansResponse("error while fetching one form input data",HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.error("error while fetching one form data");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
        }
    }


    @Override
    public ResponseEntity<LoansResponse> getDirectorDetail(FundSeekerInputRequestResponse fundSeekerInputRequest) {

        FundSeekerInputRequestResponse fundSeekerInputResponse=new FundSeekerInputRequestResponse();

        fundSeekerInputResponse.setApplicationId(fundSeekerInputRequest.getApplicationId());

        List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList= new ArrayList<DirectorBackgroundDetailRequest>();

        try {

            List<DirectorBackgroundDetail> directorBackgroundDetailList=directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(fundSeekerInputRequest.getApplicationId());

            for(DirectorBackgroundDetail directorBackgroundDetail:directorBackgroundDetailList)
            {
                DirectorBackgroundDetailRequest directorBackgroundDetailRequest=new DirectorBackgroundDetailRequest();
                BeanUtils.copyProperties(directorBackgroundDetail,directorBackgroundDetailRequest);
                directorBackgroundDetailRequestList.add(directorBackgroundDetailRequest);
            }

            fundSeekerInputResponse.setDirectorBackgroundDetailRequestsList(directorBackgroundDetailRequestList);

            LoansResponse res=new LoansResponse("director detail successfully fetched",HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setData(fundSeekerInputResponse);
            logger.info("director detail successfully fetched");
            return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);

        }
        catch (Exception e)
        {
            LoansResponse res=new LoansResponse("error while fetching director detail",HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.error("error while fetching director detail");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
        }
    }


	@Override
	public LoansResponse callMatchEngineClient(Long applicationId,Long userId) {
        ConnectResponse postOneForm;
		try {
			postOneForm = connectClient.postOneForm(applicationId,userId);
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
    
    
}
