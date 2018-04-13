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
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
 import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
 import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
 import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
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
    private JpaTransactionManager jpaTransactionManager;

    @Override
    public ResponseEntity<LoansResponse> saveOrUpdate(FundSeekerInputRequestResponse fundSeekerInputRequest) {
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

            logger.info("getting primaryCorporateDetail from applicationId::"+fundSeekerInputRequest.getApplicationId());
            PrimaryCorporateDetail primaryCorporateDetail=primaryCorporateDetailRepository.findOneByApplicationIdId(fundSeekerInputRequest.getApplicationId());
            if(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail))
            {
                logger.info("primaryCorporateDetail is null created new object");
                primaryCorporateDetail=new PrimaryCorporateDetail();
            }
            BeanUtils.copyProperties(fundSeekerInputRequest,primaryCorporateDetail);
            primaryCorporateDetail.setAmount(fundSeekerInputRequest.getLoanAmount());

            primaryCorporateDetail.setApplicationId(new LoanApplicationMaster(fundSeekerInputRequest.getApplicationId()));
            primaryCorporateDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
            primaryCorporateDetail.setModifiedDate(new Date());

            primaryCorporateDetailRepository.saveAndFlush(primaryCorporateDetail);

            List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList=fundSeekerInputRequest.getFinancialArrangementsDetailRequestsList();

            for(FinancialArrangementsDetailRequest financialArrangementsDetailRequest:financialArrangementsDetailRequestsList)
            {
                FinancialArrangementsDetail financialArrangementsDetail=financialArrangementDetailsRepository.findOne(financialArrangementsDetailRequest.getId());
                BeanUtils.copyProperties(financialArrangementsDetailRequest,financialArrangementsDetail);

                financialArrangementsDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
                financialArrangementsDetail.setModifiedDate(new Date());

                financialArrangementDetailsRepository.save(financialArrangementsDetail);
            }


            List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList=fundSeekerInputRequest.getDirectorBackgroundDetailRequestsList();

            for(DirectorBackgroundDetailRequest directorBackgroundDetailRequest:directorBackgroundDetailRequestList)
            {
                DirectorBackgroundDetail directorBackgroundDetail= directorBackgroundDetailsRepository.findOne(directorBackgroundDetailRequest.getId());
                BeanUtils.copyProperties(directorBackgroundDetailRequest,directorBackgroundDetail);

                directorBackgroundDetail.setModifiedBy(fundSeekerInputRequest.getUserId());
                directorBackgroundDetail.setModifiedDate(new Date());

                directorBackgroundDetailsRepository.save(directorBackgroundDetail);
            }

            // save and commit transaction

            EntityManagerFactory emf = jpaTransactionManager.getEntityManagerFactory();
            EntityManager entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(emf);
//            EntityTransaction entityTransaction= entityManager.getTransaction();
//            entityTransaction.commit();
            entityManager.flush();
            entityManager.clear();



            ConnectResponse postOneForm  = connectClient.postOneForm(fundSeekerInputRequest.getApplicationId(), fundSeekerInputRequest.getUserId());
            if(postOneForm != null) {
    			logger.info("postOneForm=======================>Client Connect Response=============>{}",postOneForm.toString());
    			if(!postOneForm.getProceed().booleanValue()) {
    				return new ResponseEntity<LoansResponse>(new LoansResponse("Not Eligibile from Matchengine",HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
    			}
    		}
            LoansResponse res=new LoansResponse("data successfully saved",HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setFlag(true);
            logger.error("data successfully saved");
            return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);

        }
        catch (Exception e)
        {

            LoansResponse res=new LoansResponse("error while saving fund seeker input data",HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.error("error while saving fund seeker input data");
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


            List<DirectorBackgroundDetail> directorBackgroundDetailList=directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(fundSeekerInputRequest.getApplicationId());

            for(DirectorBackgroundDetail directorBackgroundDetail:directorBackgroundDetailList)
            {
                DirectorBackgroundDetailRequest directorBackgroundDetailRequest=new DirectorBackgroundDetailRequest();
                BeanUtils.copyProperties(directorBackgroundDetail,directorBackgroundDetailRequest);
                directorBackgroundDetailRequestList.add(directorBackgroundDetailRequest);
            }

            fundSeekerInputResponse.setDirectorBackgroundDetailRequestsList(directorBackgroundDetailRequestList);

            LoansResponse res=new LoansResponse("data successfully fetched",HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setData(fundSeekerInputResponse);
            logger.info("data successfully fetched");
            return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);

        }
        catch (Exception e)
        {
            LoansResponse res=new LoansResponse("error while fetching fund seeker input data",HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.error("error while fetching fund seeker input data");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
        }
    }
}
