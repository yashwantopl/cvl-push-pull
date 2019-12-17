package com.capitaworld.service.loans.service.scoring.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.api.eligibility.exceptions.EligibilityExceptions;
import com.capitaworld.api.eligibility.model.CoApplicantEligibilityRequest;
import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.api.eligibility.utility.EligibilityUtils;
import com.capitaworld.cibil.api.model.CibilRequest;
import com.capitaworld.cibil.api.model.CibilResponse;
import com.capitaworld.cibil.api.model.CibilScoreLogRequest;
import com.capitaworld.cibil.api.utility.CibilUtils;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.itr.api.model.ITRBasicDetailsResponse;
import com.capitaworld.itr.api.model.ITRConnectionResponse;
import com.capitaworld.itr.client.ITRClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.exceptions.AnalyzerException;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.MonthlyDetail;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.analyzer.model.common.Xn;
import com.capitaworld.service.gst.GstCalculation;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.ScoringRequestDetail;
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiIncomeDetails;
import com.capitaworld.service.loans.domain.fundseeker.retail.BankingRelation;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryAutoLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoreParameterRequestLoans;
import com.capitaworld.service.loans.model.score.ScoringCibilRequest;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;
import com.capitaworld.service.loans.repository.CspCodeRepository;
import com.capitaworld.service.loans.repository.common.LoanRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundseeker.ScoringRequestDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiApplicationDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiExpenseExpectedIncomeDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiIncomeDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateDirectorIncomeDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.BankingRelationlRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CreditCardsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryAutoLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantIncomeRepository;
import com.capitaworld.service.loans.service.common.BankBureauResponseService;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanModelService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.utils.scoreexcel.ScoreExcelFileGenerator;
import com.capitaworld.service.loans.utils.scoreexcel.ScoreExcelReader;
import com.capitaworld.service.matchengine.model.BankBureauRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.AnnualIncomeRural;
import com.capitaworld.service.oneform.enums.AutoLoanPurposeType;
import com.capitaworld.service.oneform.enums.BankList;
import com.capitaworld.service.oneform.enums.EmploymentWithPL;
import com.capitaworld.service.oneform.enums.EmploymentWithPLScoring;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.OccupationHL;
import com.capitaworld.service.oneform.enums.OccupationNatureNTB;
import com.capitaworld.service.oneform.enums.ResidenceStatusRetailMst;
import com.capitaworld.service.oneform.enums.VehicleType;
import com.capitaworld.service.oneform.enums.YesNo;
import com.capitaworld.service.oneform.enums.scoring.EnvironmentCategory;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.model.IndustryResponse;
import com.capitaworld.service.rating.model.IrrRequest;
import com.capitaworld.service.scoring.MCLRReqRes;
import com.capitaworld.service.scoring.REPOReqRes;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.FundSeekerInputRequest;
import com.capitaworld.service.scoring.model.GenericCheckerReqRes;
import com.capitaworld.service.scoring.model.ModelParameterResponse;
import com.capitaworld.service.scoring.model.ScoreParameterMFIRequest;
import com.capitaworld.service.scoring.model.ScoreParameterRetailRequest;
import com.capitaworld.service.scoring.model.ScoringParameterRequest;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;
import com.capitaworld.service.scoring.utils.ScoreParameter;
import com.capitaworld.service.thirdparty.model.CGTMSEDataResponse;
import com.capitaworld.service.thirdpaty.client.ThirdPartyClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Transactional
public class ScoringServiceImpl implements ScoringService {


    private final Logger logger = LoggerFactory.getLogger(ScoringServiceImpl.class);
    public static final String CIBIL_SCORE_VERSION_2 = "CibilScoreVersion2";

    @Autowired
    private OperatingStatementDetailsRepository operatingStatementDetailsRepository;

    @Autowired
    private LiabilitiesDetailsRepository liabilitiesDetailsRepository;

    @Autowired
    private AssetsDetailsRepository assetsDetailsRepository;

    @Autowired
    private BankingRelationlRepository bankingRelationlRepository;

    @Autowired
    private DirectorBackgroundDetailsRepository directorBackgroundDetailsRepository;

    @Autowired
    private ScoringClient scoringClient;

    @Autowired
    private GstClient gstClient;

    @Autowired
    private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

    @Autowired
    private PrimaryCorporateDetailRepository primaryCorporateDetailRepository;

    @Autowired
    private AnalyzerClient analyzerClient;

    @Autowired
    private CIBILClient cibilClient;

    @Autowired
    private Environment environment;

    @Autowired
    private UsersClient usersClient;

    @Autowired
    private ThirdPartyClient thirdPartyClient;

    @Autowired
    private CorporateDirectorIncomeDetailsRepository corporateDirectorIncomeDetailsRepository;

    @Autowired
    private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;

    
    @Autowired
    private CreditCardsDetailRepository creditCardsDetailRepository;
    
    @Autowired
    private FinancialArrangementDetailsService financialArrangementDetailsService;

    @Autowired
    private ITRClient itrClient;

    @Autowired
    private RetailApplicantDetailRepository retailApplicantDetailRepository;

    @Autowired
    private CoApplicantDetailRepository coApplicantDetailRepository;

    @Autowired
    private RetailApplicantIncomeRepository retailApplicantIncomeRepository;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Autowired
    private RatingClient ratingClient;

    @Autowired
    private OneFormClient oneFormClient;

    @Autowired
    private ScoringRequestDetailRepository scoringRequestDetailRepository;

    @Autowired
    private PrimaryHomeLoanDetailRepository primaryHomeLoanDetailRepository;

    @Autowired
    private PrimaryAutoLoanDetailRepository primaryAutoLoanDetailRepository;

    @Autowired
    private EligibilityClient eligibilityClient;

    @Autowired
	private HomeLoanModelService homeLoanModelService;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ProductMasterRepository productMasterRepository;

    @Autowired
    private MfiApplicationDetailsRepository mfiApplicationDetailsRepository;
    @Autowired
    private MfiExpenseExpectedIncomeDetailRepository expectedIncomeDetailRepository;

    @Autowired
    private MfiIncomeDetailsRepository mfiIncomeDetailsRepository;
    
    @Autowired
    private BankBureauResponseService bankBureauResponseService; 
    
    @Autowired
    private CspCodeRepository cspCodeRepository;
    
    private static final String ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING = "Error while getting retail applicant detail for personal loan scoring : ";
    private static final String ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_HOME_LOAN_SCORING = "Error while getting retail applicant detail for Home loan scoring : ";
    private static final String ERROR_WHILE_GETTING_FIELD_LIST = "error while getting field list : ";
    private static final String ERROR_WHILE_CALLING_SCORING = "error while calling scoring : ";

    private static final String SAVING_SCORING_REQUEST_DATA_FOR = "Saving Scoring Request Data for  =====> ";
    private static final String SCORE_IS_SUCCESSFULLY_CALCULATED = "score is successfully calculated=====>{}";
    private static final String MSG_APPLICATION_ID = " APPLICATION ID   :: ";
    private static final String MSG_FP_PRODUCT_ID = " FP PRODUCT ID    :: ";
    private static final String MSG_SCORING_MODEL_ID = " SCORING MODEL ID :: ";
    private static final String MSG_SCORE_PARAMETER = "SCORE PARAMETER ::::::::::";
    private static final String ORG_ID_IS_NULL_OR_EMPTY  = "org id is null or empty : ";


    @Override
    public ResponseEntity<LoansResponse> calculateScoring(ScoringRequestLoans scoringRequestLoans) {

        PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(scoringRequestLoans.getApplicationId());

        /*if(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)){
            RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(scoringRequestLoans.getApplicationId());
        }*/
        RetailApplicantDetail retailApplicantDetail = null;
        if (CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail) || CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getBusinessTypeId())) {
            retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(scoringRequestLoans.getApplicationId());
            if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
                logger.warn("Business type id is null or empty");
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Business type id is null or empty.", HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.OK);
            }
        }
        if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
            return calculateRetailPersonalLoanScoring(scoringRequestLoans);
        }
        Long businessTypeId = primaryCorporateDetail.getBusinessTypeId().longValue();

        if (ScoreParameter.BusinessType.EXISTING_BUSINESS == businessTypeId) {
            return calculateExistingBusinessScoring(scoringRequestLoans);
        } else if (ScoreParameter.BusinessType.NTB == businessTypeId) {
            return calculateNTBScoring(scoringRequestLoans, primaryCorporateDetail);
        } else if (ScoreParameter.BusinessType.RETAIL_PERSONAL_LOAN == businessTypeId) {
            return calculateRetailPersonalLoanScoring(scoringRequestLoans);
        }

        return null;
    }

    private ResponseEntity<LoansResponse> calculateRetailPersonalLoanScoring(ScoringRequestLoans scoringRequestLoans) {

        ScoreParameterRetailRequest scoreParameterRetailRequest = new ScoreParameterRetailRequest();

        Long scoreModelId = scoringRequestLoans.getScoringModelId();
        Long applicationId = scoringRequestLoans.getApplicationId();
        Long fpProductId = scoringRequestLoans.getFpProductId();


        ///////// Start Getting Old Request ///////


        List<ScoringRequestDetail> scoringRequestDetailList = scoringRequestDetailRepository.getScoringRequestDetailByApplicationIdAndIsActive(applicationId);

        ScoringRequestDetail scoringRequestDetailSaved;

        if (scoringRequestDetailList.size() > 0) {
            logger.info("Getting Old Scoring request Data for  =====> " + applicationId);
            scoringRequestDetailSaved = scoringRequestDetailList.get(0);
            Gson gson = new Gson();
            scoreParameterRetailRequest = gson.fromJson(scoringRequestDetailSaved.getRequest(), ScoreParameterRetailRequest.class);
        }


        ScoringResponse scoringResponseMain = null;

        ScoringRequest scoringRequest = new ScoringRequest();
        scoringRequest.setScoringModelId(scoreModelId);
        scoringRequest.setFpProductId(fpProductId);
        scoringRequest.setApplicationId(applicationId);
        scoringRequest.setUserId(scoringRequestLoans.getUserId());
        scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.RETAIL_PERSONAL_LOAN);

        if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
            scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
        } else {
            scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
        }

        ///////// End  Getting Old Request ///////

        if (!(scoringRequestDetailList.size() > 0)) {
            logger.info("----------------------------START RETAIL PL ------------------------------");
            logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

            // GET SCORE RETAIL PERSONAL LOAN PARAMETERS

            RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);

            if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }


            if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                ScoringResponse scoringResponse = null;
                try {
                    scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                } catch (Exception e) {
                    logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                }

                List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();


                for (int i = 0; i < dataList.size(); i++) {

                    ModelParameterResponse modelParameterResponse = null;
                    try {
                        modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                ModelParameterResponse.class);
                    } catch (IOException e) {
                        logger.error(CommonUtils.EXCEPTION,e);
                    }

                    FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                    fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                    fundSeekerInputRequest.setName(modelParameterResponse.getName());

                    switch (modelParameterResponse.getName()) {

                        case ScoreParameter.Retail.WORKING_EXPERIENCE_PL: {

                            try {
                                Double totalExperience = 0.0;
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceYear()))
                                    totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceYear());

                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceMonth()))
                                    totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceMonth() / 12);

                                scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                scoreParameterRetailRequest.setWorkingExperience_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting WORKING_EXPERIENCE_PL parameter : ",e);
                                scoreParameterRetailRequest.setWorkingExperience_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.CIBIL_SCORE_PL: {

                            Double cibil_score = null;
                            try {

                                CibilRequest cibilRequest = new CibilRequest();
                                cibilRequest.setPan(retailApplicantDetail.getPan());
                                cibilRequest.setApplicationId(applicationId);

                                CibilScoreLogRequest cibilResponse = cibilClient.getCibilScoreByPanCard(cibilRequest);
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse.getScore())) {
                                    cibil_score = Double.parseDouble(cibilResponse.getScore());
                                    scoreParameterRetailRequest.setCibilScore(cibil_score);
                                    scoreParameterRetailRequest.setCibilScore_p(true);
                                } else {
                                    scoreParameterRetailRequest.setCibilScore_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting CIBIL_SCORE_PL parameter from CIBIL client : ",e);
                                scoreParameterRetailRequest.setCibilScore_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.AGE_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
                                    scoreParameterRetailRequest.setAge(Math.ceil(CommonUtils.getAgeFromBirthDate(retailApplicantDetail.getBirthDate()).doubleValue()));
                                    scoreParameterRetailRequest.setAge_p(true);
                                } else {
                                    scoreParameterRetailRequest.setAge_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting AGE_PL parameter : ",e);
                                scoreParameterRetailRequest.setAge_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.EDUCATION_QUALI_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEducationQualification())) {
                                    scoreParameterRetailRequest.setEducationQualification(retailApplicantDetail.getEducationQualification().longValue());
                                    scoreParameterRetailRequest.setEducationQualifaction_p(true);
                                } else {
                                    scoreParameterRetailRequest.setEducationQualifaction_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting EDUCATION_QUALI_PL parameter : ",e);
                                scoreParameterRetailRequest.setEducationQualifaction_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.EMPLOYEMENT_TYPE_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEmploymentStatus())) {
                                    scoreParameterRetailRequest.setEmploymentType(retailApplicantDetail.getEmploymentStatus().longValue());
                                    scoreParameterRetailRequest.setEmployementType_p(true);
                                } else {
                                    scoreParameterRetailRequest.setEmployementType_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting EMPLOYEMENT_TYPE_PL parameter : ",e);
                                scoreParameterRetailRequest.setEmployementType_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.HOUSE_OWNERSHIP_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getResidenceType())) {
                                    scoreParameterRetailRequest.setHouseOwnership(retailApplicantDetail.getResidenceType().longValue());
                                    scoreParameterRetailRequest.setHouseOwnership_p(true);
                                } else {
                                    scoreParameterRetailRequest.setHouseOwnership_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting HOUSE_OWNERSHIP_PL parameter : ",e);
                                scoreParameterRetailRequest.setHouseOwnership_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.MARITAL_STATUS_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getStatusId())) {
                                    scoreParameterRetailRequest.setMaritalStatus(retailApplicantDetail.getStatusId().longValue());
                                    scoreParameterRetailRequest.setMaritalStatus_p(true);
                                } else {
                                    scoreParameterRetailRequest.setMaritalStatus_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting MARITAL_STATUS_PL parameter : ",e);
                                scoreParameterRetailRequest.setMaritalStatus_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.CATEGORY_INFO_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEmploymentWith())) {

                                    Boolean salaryWithBank=isSalaryAccountWithBank(applicationId);
                                    Long employmentWithPlValue=null;

                                    if(EmploymentWithPL.CENTRAL_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith())  //1
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();

                                    }
                                    else if(EmploymentWithPL.STATE_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith()) //2
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.PSU.getId() == retailApplicantDetail.getEmploymentWith())  //3
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.CORPORATE.getId() == retailApplicantDetail.getEmploymentWith()
                                    		|| EmploymentWithPL.SMALL_SECTOR_PVT_LTD_COMPANIES.getId() == retailApplicantDetail.getEmploymentWith()) //4
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.EDUCATIONAL_INSTITUTE.getId() == retailApplicantDetail.getEmploymentWith()) //5
                                    {
                                        employmentWithPlValue= EmploymentWithPLScoring.EDUCATIONAL_INSTITUTE.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.OTHERS.getId() == retailApplicantDetail.getEmploymentWith()
                                    		|| EmploymentWithPL.SMALL_SECTOR_PARTNERSHIP.getId() == retailApplicantDetail.getEmploymentWith()
                                    		|| EmploymentWithPL.SMALL_SECTOR_PROPRIETORSHIP.getId() == retailApplicantDetail.getEmploymentWith()
                                    		|| EmploymentWithPL.UNORGANISED_SECTOR.getId() == retailApplicantDetail.getEmploymentWith()) //6
                                    {
                                        employmentWithPlValue= EmploymentWithPLScoring.OTHERS.getId().longValue();
                                    }else if(EmploymentWithPL.QUASI_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith()) {  //7
                                    	if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.QUASI_GOVERNMENT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.QUASI_GOVERNMENT_NOT_WITH_BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.BANK.getId() == retailApplicantDetail.getEmploymentWith()) //8
                                    {
                                        employmentWithPlValue= EmploymentWithPLScoring.BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.INSURANCE_COMPANY.getId() == retailApplicantDetail.getEmploymentWith()) //9
                                    {
                                        employmentWithPlValue= EmploymentWithPLScoring.INSURANCE_COMPANY.getId().longValue();
                                    }
                                    scoreParameterRetailRequest.setCategoryInfo(employmentWithPlValue);
                                    scoreParameterRetailRequest.setCategoryInfo_p(true);
                                } else {
                                    scoreParameterRetailRequest.setCategoryInfo_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting CATEGORY_INFO_PL parameter : ",e);
                                scoreParameterRetailRequest.setCategoryInfo_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.FIXED_OBLI_INFO_RATIO_PL: {

                            Double totalEMI = financialArrangementDetailsRepository.getTotalEmiByApplicationId(applicationId);
                            if (CommonUtils.isObjectNullOrEmpty(totalEMI)) {
                                totalEMI = 0.0;
                            }
                            Double totalIncomeLastYear = 0.0;
                            try {

                                Integer maxYear=retailApplicantIncomeRepository.getMaxYearByApplicationId(applicationId);
                                totalIncomeLastYear = retailApplicantIncomeRepository.getTotalIncomeByApplicationIdAndYear(applicationId,maxYear);

                                if (CommonUtils.isObjectNullOrEmpty(totalIncomeLastYear)) {
                                    totalIncomeLastYear = 0.0;
                                }

                            } catch (Exception e) {
                                logger.error("error while getting total income from retail applicant income detail : ",e);
                            }

                            scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
                            scoreParameterRetailRequest.setLastYearTotalIncomeFromITR(totalIncomeLastYear);
                            scoreParameterRetailRequest.setFixedObligationRatio_p(true);
                            break;
                        }
                        case ScoreParameter.Retail.CHEQUE_BOUNCE_PAST_SIX_MONTH_PL: {

                            try {
                                Double noOfChequeBounce = null;
                                ReportRequest reportRequest = new ReportRequest();
                                reportRequest.setApplicationId(applicationId);

                                AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);

                                Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                        Data.class);
                                if (!CommonUtils.isObjectNullOrEmpty(data) && !CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month())) {
                                    {
                                        if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month().doubleValue())) {
                                            noOfChequeBounce = data.getCheckBounceForLast6Month().doubleValue();
                                        } else {
                                            noOfChequeBounce = 0.0;
                                        }

                                    }
                                } else {
                                    noOfChequeBounce = 0.0;
                                }

                                scoreParameterRetailRequest.setChequeBounce(noOfChequeBounce);
                                scoreParameterRetailRequest.setChequeBounce_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting CHEQUE_BOUNCE_PAST_SIX_MONTH_PL parameter : ",e);
                                scoreParameterRetailRequest.setChequeBounce_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.Retail.DAY_PAST_DUE_PL: {
                            try {

                                CibilResponse cibilResponse = cibilClient.getDPDLastXMonth(applicationId,retailApplicantDetail.getPan());
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isListNullOrEmpty(cibilResponse.getListData())) {
                                    List<Integer> listDPD = (List<Integer>) cibilResponse.getListData();

                                    Integer maxDPD = Collections.max(listDPD);
                                    if (!CommonUtils.isObjectNullOrEmpty(maxDPD)) {
                                        scoreParameterRetailRequest.setDpd(maxDPD.doubleValue());
                                    } else {
                                        scoreParameterRetailRequest.setDpd(0.0);
                                    }
                                } else {
                                    scoreParameterRetailRequest.setDpd(0.0);
                                }
                                scoreParameterRetailRequest.setDPD_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting DAY_PAST_DUE_PL parameter from CIBIL client : ",e);
                                scoreParameterRetailRequest.setDPD_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.NET_ANNUAL_INCOME_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
                                    scoreParameterRetailRequest.setNetAnnualIncome(retailApplicantDetail.getMonthlyIncome() * 12);
                                    scoreParameterRetailRequest.setNetAnnualIncome_p(true);
                                } else {
                                    scoreParameterRetailRequest.setNetAnnualIncome_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting NET_ANNUAL_INCOME_PL parameter : ",e);
                                scoreParameterRetailRequest.setNetAnnualIncome_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.Retail.EMI_NMI_PL: {
                        /*scoreParameterRetailRequest.setEminmi();
                        scoreParameterRetailRequest.setEmiNmi_p();*/

                            break;
                        }
                        default:
                            break;

                    }
                }

                logger.info(MSG_SCORE_PARAMETER + scoreParameterRetailRequest.toString());

                logger.info("----------------------------END-------------------------------------------");

                Gson g = new Gson();
                ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                try {
                    scoringRequestDetail.setApplicationId(applicationId);
                    scoringRequestDetail.setRequest(g.toJson(scoreParameterRetailRequest));
                    scoringRequestDetail.setCreatedDate(new Date());
                    scoringRequestDetail.setIsActive(true);
                    scoringRequestDetailRepository.save(scoringRequestDetail);

                    logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                } catch (Exception e) {
                    logger.error(CommonUtils.EXCEPTION,e);
                }
            }
        }

        scoringRequest.setScoreParameterRetailRequest(scoreParameterRetailRequest);

        try {
            scoringResponseMain = scoringClient.calculateScore(scoringRequest);

            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

    public ResponseEntity<LoansResponse> calculateRetailPersonalLoanScoringList(List<ScoringRequestLoans> scoringRequestLoansList) {

        Boolean isItrMannualFilled = false;
        Integer minBankRelationshipInMonths = null;
        List<Double> incomeOfItrOf3Years = null;
        Double netMonthlyIncome = 0.0d;
        Double grossMonthlyIncome = 0.0d;
        Double totalEMI = 0.0d;
        List<Data> bankStatementDatas = null;

        List<ScoringRequest> scoringRequestList=new ArrayList<ScoringRequest>();
        //Data bankStatementData;
        ScoreParameterRetailRequest scoreParameterRetailRequest = null;

        // CALL ELIGIBILITY CLIENT FOR GROSS AND NET MONTHLY INCOME PURPOSE
        EligibilityResponse eligibilityResponse = null;
        EligibililityRequest eligibililityRequest = new EligibililityRequest();
		eligibililityRequest.setApplicationId(scoringRequestLoansList.get(0).getApplicationId());
		eligibililityRequest.setIsIncomeCalculate(false);
		try {
			eligibilityResponse = eligibilityClient.getMonthlyIncome(eligibililityRequest);
		} catch (EligibilityExceptions e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Long applicationIdTmp = null;
        applicationIdTmp = scoringRequestLoansList.get(0).getApplicationId();
        if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse)
                && !com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse.getData())){
            List incomeList = (List) eligibilityResponse.getData();
            if(!com.capitaworld.service.matchengine.utils.CommonUtils.isListNullOrEmpty(incomeList)){
                netMonthlyIncome = Double.valueOf(incomeList.get(0).toString());
                grossMonthlyIncome = Double.valueOf(incomeList.get(8).toString());
            }

            if(netMonthlyIncome <= 0 || grossMonthlyIncome <= 0) {
                return new ResponseEntity<>(new LoansResponse("NMI or GMI is Zero ", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
            logger.info("Net Monthly Income For ApplicationId======{}======>{}",applicationIdTmp,netMonthlyIncome);
            logger.info("Gross Annual Income For ApplicationId======{}======>{}",applicationIdTmp,grossMonthlyIncome);
        }

        try {
            ReportRequest reportRequest = new ReportRequest();
            reportRequest.setApplicationId(applicationIdTmp);
            AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
            if(analyzerResponse == null) {
                return new ResponseEntity<>(new LoansResponse("Analyser Response Found null For Scoring Calculation PL For the ApplicationId===>" + applicationIdTmp, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
            bankStatementDatas = new ArrayList<>(5);
            for(Object object : (List)analyzerResponse.getData()) {
                Data dataBs = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) object, Data.class);
                if(dataBs != null) {
                    if(!EligibilityUtils.isObjectNullOrEmpty(dataBs.getCoAppId())) {
                        continue;
                    }
                    bankStatementDatas.add(dataBs);
                }
            }
        }catch(Exception e) {
            logger.error("Error while getting Bank Statement Details===>{}",e);
            return new ResponseEntity<>(new LoansResponse("Error while Getting Bank Statemtnt Report for ApplicationID====>" + applicationIdTmp + " and Message====>" + e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
        }
        
        for(ScoringRequestLoans scoringRequestLoans:scoringRequestLoansList)
        {
            Long scoreModelId = scoringRequestLoans.getScoringModelId();
            Long applicationId = scoringRequestLoans.getApplicationId();
            Long fpProductId = scoringRequestLoans.getFpProductId();
            Double eligibleTenure = scoringRequestLoans.getEligibleTenure();
            Long orgId = null;
            isItrMannualFilled = loanRepository.isITRUploaded(applicationId);
            Double eligibleLoanAmountCircular = scoringRequestLoans.getElAmountOnAverageScoring();
            incomeOfItrOf3Years = loanRepository.getIncomeOfItrOf3Years(applicationId);
            totalEMI = financialArrangementDetailsService.getTotalEmiByApplicationIdSoftPing(applicationId);
            orgId = scoringRequestLoans.getOrgId();
            if(orgId != null) {
                BankList bankEnum = BankList.fromOrgId(orgId.toString());
                if(bankEnum != null) {
                    logger.info("Bank Name====>{}==>Application Id===>{}===> Fp Product Id===>{}",bankEnum.getName(),applicationId,fpProductId);
                    minBankRelationshipInMonths = bankingRelationlRepository.getMinRelationshipInMonthByApplicationAndOrgName(applicationId, bankEnum.getName());
                }
                logger.info("Min Banking Relationship in Month === >{}",minBankRelationshipInMonths);
            }
/*
            try {
                ReportRequest reportRequest = new ReportRequest();
                reportRequest.setApplicationId(applicationId);
                AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);
                if(analyzerResponse == null) {
                    return new ResponseEntity<>(new LoansResponse("Analyser Response Found null For Scoring Calculation HL For the ApplicationId===>" + applicationId, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
                }
                bankStatementData = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),Data.class);
                if(bankStatementData == null) {
                    return new ResponseEntity<>(new LoansResponse("Bank Statement Report Found Null For the ApplicationId HL===>" + applicationId, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
                }
            }catch(Exception e) {
                logger.error("Error while getting Bank Statement Details===>{}",e);
                return new ResponseEntity<>(new LoansResponse("Error while Getting Bank Statemtnt Report for ApplicationID====>" + applicationId + " and Message====>" + e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
*/

//            productMasterRepository.findOne(fpProductId);

            ///////// Start Getting Individual Product Request ///////

            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.RETAIL_PERSONAL_LOAN);
            scoringRequest.setEmi(scoringRequestLoans.getEmi());
            scoringRequest.setEligibleLoanAmountCircular(eligibleLoanAmountCircular);
            scoringRequest.setEligibleTenure(eligibleTenure);
            scoringRequest.setFoir(scoringRequestLoans.getFoir());
            scoringRequest.setMinBankRelationshipInMonths(minBankRelationshipInMonths);
            if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
            } else {
                scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
            }


            ///////// End Getting Individual Product Request ///////

            RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);

            /*ScoringRequestLoans requestLoans = new ScoringRequestLoans();
            requestLoans.setApplicationId(applicationId);
            requestLoans.setFpProductId(fpProductId);*/
            Integer consessionBureauVersion = null;
             Object[] bureauVersionIdById = loanRepository.getBureauVersionIdById(scoreModelId);
             if(!CommonUtils.isObjectNullOrEmpty(bureauVersionIdById)) {
            	 if(!CommonUtils.isObjectNullOrEmpty(bureauVersionIdById[0])) {
            		 consessionBureauVersion = Integer.valueOf(bureauVersionIdById[0].toString());            		 
            	 }else {
            		 consessionBureauVersion = 1;
            	 }
            	 scoringRequestLoans.setBureauVersion(consessionBureauVersion);
             }
              
            Object [] concessionResp = getRetailConcessionDetails(scoringRequestLoans, null, null, null,retailApplicantDetail, null);
            logger.info("==========getRetailConcessionDetailS PERSONAL LOAN ========>>>>>"+concessionResp);

           Boolean  isBorrowersHavingAccounts	  =	(Boolean)concessionResp[0];
           Boolean  isBorrowersAvailingLoans          =	(Boolean)concessionResp[1];
           Boolean  isBorrowersHavingSalaryAccounts   = (Boolean)concessionResp[2];
           Boolean  isBorrowersAvailingCreaditCards   = (Boolean)concessionResp[3];

           // is Fully Check Off And Partially Check Off
           Boolean isCheckOffDirectPayEmi             =	(Boolean)concessionResp[4];
           Boolean  isCheckOffAgreetoPayOutstanding   = (Boolean)concessionResp[5];
           Boolean  isCheckOffShiftSalAcc             =	(Boolean)concessionResp[6];
           Boolean  isCheckOffPayOutstndAmount        =	(Boolean)concessionResp[7];
           Boolean isCheckOffNotChangeSalAcc          =	(Boolean)concessionResp[8];

           // Cibil BAsed Concession
           Double cibilActualScore                   =	(Double)concessionResp[9];
           Boolean isCreaditHisotryGreaterSixMonths   =	(Boolean)concessionResp[10];
           Boolean isCreaditHisotryLessThenSixMonths = (Boolean)concessionResp[11];
           Boolean isNoCreaditHistory                =	(Boolean)concessionResp[12];

          // partially and fully check off related----->
			scoringRequest.setIsBorrowersHavingAccounts(isBorrowersHavingAccounts);
          scoringRequest.setIsBorrowersAvailingLoans(isBorrowersAvailingLoans);
          scoringRequest.setIsBorrowersHavingSalaryAccounts(isBorrowersHavingSalaryAccounts);
          scoringRequest.setIsBorrowersAvailingCreaditCards(isBorrowersAvailingCreaditCards);

          scoringRequest.setIsCheckOffDirectPayEmi(isCheckOffDirectPayEmi);
          scoringRequest.setIsCheckOffAgreetoPayOutstanding(isCheckOffAgreetoPayOutstanding);
          scoringRequest.setIsCheckOffShiftSalAcc(isCheckOffShiftSalAcc);
          scoringRequest.setIsCheckOffPayOutstndAmount(isCheckOffPayOutstndAmount);
          scoringRequest.setIsCheckOffNotChangeSalAcc(isCheckOffNotChangeSalAcc);
     	// ENDS HERE CHECK OFF LOGIC HERE

		//  Cibil Based Object related----->
          scoringRequest.setCibilActualScore(cibilActualScore);
          scoringRequest.setIsCreaditHisotryGreaterSixMonths(isCreaditHisotryGreaterSixMonths);
          scoringRequest.setIsCreaditHisotryLessThenSixMonths(isCreaditHisotryLessThenSixMonths);
          scoringRequest.setIsNoCreaditHistory(isNoCreaditHistory);

          // SCORING BASED ON CONCESSION RATE OF INTEREST
            if (CommonUtils.isObjectNullOrEmpty(scoreParameterRetailRequest)) {
                scoreParameterRetailRequest= new ScoreParameterRetailRequest();
                logger.info("----------------------------START RETAIL PL ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

                // GET SCORE RETAIL PERSONAL LOAN PARAMETERS



                if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
                    logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                    LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                    //return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
                    break;
                }


                if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                    // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                    ScoringResponse scoringResponse = null;
                    try {
                        scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                    } catch (Exception e) {
                        logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                    }

                    List<Map<String, Object>> dataList = new ArrayList<>();
                    if (scoringResponse != null && scoringResponse.getDataList() != null) {
                        dataList = (List<Map<String, Object>>) scoringResponse.getDataList();
                    }
                    scoreParameterRetailRequest.setNmi(netMonthlyIncome);
                    scoreParameterRetailRequest.setGmi(grossMonthlyIncome);
                    for (int i = 0; i < dataList.size(); i++) {

                        ModelParameterResponse modelParameterResponse = null;
                        try {
                            modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                    ModelParameterResponse.class);
                            if(modelParameterResponse == null){
                                continue;
                            }
                        } catch (IOException | NullPointerException e) {
                            logger.error(CommonUtils.EXCEPTION,e);
                            continue;
                        }

                        FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                        fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                        fundSeekerInputRequest.setName(modelParameterResponse.getName());

                        switch (modelParameterResponse.getName()) {

                            case ScoreParameter.Retail.WORKING_EXPERIENCE_PL: {

                                try {
                                    Double totalExperience = 0.0;
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceYear()))
                                        totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceYear());

                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceMonth()))
                                        totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceMonth() / 12);

                                    scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                    scoreParameterRetailRequest.setWorkingExperience_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting WORKING_EXPERIENCE_PL parameter : ",e);
                                    scoreParameterRetailRequest.setWorkingExperience_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.CIBIL_SCORE_PL: {
                                try {
                                	List<CibilScoreLogRequest> cibilResponse = cibilClient.getSoftpingScores(applicationIdTmp, retailApplicantDetail.getPan());
                                	scoreParameterRetailRequest.setCibilActualScore(filterBureauScoreByVersion(1, cibilResponse));
                    				scoreParameterRetailRequest.setCibilActualScoreVersion2(filterBureauScoreByVersion(2, cibilResponse));
                    				scoreParameterRetailRequest.setCibilScore_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting CIBIL_SCORE_PL parameter from CIBIL client : ",e);                                  
                                }
                                break;
                            }
                            case ScoreParameter.Retail.AGE_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
                                        scoreParameterRetailRequest.setAge(Math.ceil(CommonUtils.getAgeFromBirthDate(retailApplicantDetail.getBirthDate()).doubleValue()));
                                        scoreParameterRetailRequest.setAge_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setAge_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting AGE_PL parameter : ",e);
                                    scoreParameterRetailRequest.setAge_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.EDUCATION_QUALI_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEducationQualification())) {
                                        scoreParameterRetailRequest.setEducationQualification(retailApplicantDetail.getEducationQualification().longValue());
                                        scoreParameterRetailRequest.setEducationQualifaction_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setEducationQualifaction_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting EDUCATION_QUALI_PL parameter : ",e);
                                    scoreParameterRetailRequest.setEducationQualifaction_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.EMPLOYEMENT_TYPE_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEmploymentStatus())) {
                                        scoreParameterRetailRequest.setEmploymentType(retailApplicantDetail.getEmploymentStatus().longValue());
                                        scoreParameterRetailRequest.setEmployementType_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setEmployementType_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting EMPLOYEMENT_TYPE_PL parameter : ",e);
                                    scoreParameterRetailRequest.setEmployementType_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.HOUSE_OWNERSHIP_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getResidenceType())) {
                                        scoreParameterRetailRequest.setHouseOwnership(retailApplicantDetail.getResidenceType().longValue());
                                        scoreParameterRetailRequest.setHouseOwnership_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setHouseOwnership_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting HOUSE_OWNERSHIP_PL parameter : ",e);
                                    scoreParameterRetailRequest.setHouseOwnership_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.MARITAL_STATUS_PL: {

                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getStatusId())) {
                                        scoreParameterRetailRequest.setMaritalStatus(retailApplicantDetail.getStatusId().longValue());
                                        scoreParameterRetailRequest.setMaritalStatus_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setMaritalStatus_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting MARITAL_STATUS_PL parameter : ",e);
                                    scoreParameterRetailRequest.setMaritalStatus_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.CATEGORY_INFO_PL: {

                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getEmploymentWith())) {

                                    Boolean salaryWithBank=isSalaryAccountWithBank(applicationId);
                                    Long employmentWithPlValue=null;

                                        if(EmploymentWithPL.CENTRAL_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith())  //1
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();

                                        }
                                        else if(EmploymentWithPL.STATE_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith()) //2
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.PSU.getId() == retailApplicantDetail.getEmploymentWith())  //3
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.CORPORATE.getId() == retailApplicantDetail.getEmploymentWith()
                                        		|| EmploymentWithPL.SMALL_SECTOR_PVT_LTD_COMPANIES.getId() == retailApplicantDetail.getEmploymentWith()) //4
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.EDUCATIONAL_INSTITUTE.getId() == retailApplicantDetail.getEmploymentWith()) //5
                                        {
                                            employmentWithPlValue= EmploymentWithPLScoring.EDUCATIONAL_INSTITUTE.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.OTHERS.getId() == retailApplicantDetail.getEmploymentWith()
                                        		|| EmploymentWithPL.SMALL_SECTOR_PARTNERSHIP.getId() == retailApplicantDetail.getEmploymentWith()
                                        		|| EmploymentWithPL.SMALL_SECTOR_PROPRIETORSHIP.getId() == retailApplicantDetail.getEmploymentWith()
                                        		|| EmploymentWithPL.UNORGANISED_SECTOR.getId() == retailApplicantDetail.getEmploymentWith()) //6
                                        {
                                            employmentWithPlValue= EmploymentWithPLScoring.OTHERS.getId().longValue();
                                        }else if(EmploymentWithPL.QUASI_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith()) {  //7
                                        	if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.QUASI_GOVERNMENT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.QUASI_GOVERNMENT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.BANK.getId() == retailApplicantDetail.getEmploymentWith()) //8
                                        {
                                            employmentWithPlValue= EmploymentWithPLScoring.BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.INSURANCE_COMPANY.getId() == retailApplicantDetail.getEmploymentWith()) //9
                                        {
                                            employmentWithPlValue= EmploymentWithPLScoring.INSURANCE_COMPANY.getId().longValue();
                                        }
                                        scoreParameterRetailRequest.setCategoryInfo(employmentWithPlValue);
                                        scoreParameterRetailRequest.setCategoryInfo_p(true);
                                    } else {
                                        scoreParameterRetailRequest.setCategoryInfo_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting CATEGORY_INFO_PL parameter : ",e);
                                    scoreParameterRetailRequest.setCategoryInfo_p(false);
                                }                          

                            break;
                        }
                            case ScoreParameter.Retail.FIXED_OBLI_INFO_RATIO_PL: {

                                totalEMI = financialArrangementDetailsRepository.getTotalEmiByApplicationId(applicationId);
                                if (CommonUtils.isObjectNullOrEmpty(totalEMI)) {
                                    totalEMI = 0.0;
                                }
                                Double totalIncomeLastYear = 0.0;
                                try {
                                    Integer maxYear=retailApplicantIncomeRepository.getMaxYearByApplicationId(applicationId);
                                    totalIncomeLastYear = retailApplicantIncomeRepository.getTotalIncomeByApplicationIdAndYear(applicationId,maxYear);

                                    if (CommonUtils.isObjectNullOrEmpty(totalIncomeLastYear)) {
                                        totalIncomeLastYear = 0.0;
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting total income from retail applicant income detail : ",e);
                                }

                                scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
                                scoreParameterRetailRequest.setLastYearTotalIncomeFromITR(totalIncomeLastYear);
                                scoreParameterRetailRequest.setFixedObligationRatio_p(true);
                                break;
                            }
                            case ScoreParameter.Retail.CHEQUE_BOUNCE_PAST_SIX_MONTH_PL: {

                                try {
                                    Double noOfChequeBounce = null;
                                    ReportRequest reportRequest = new ReportRequest();
                                    reportRequest.setApplicationId(applicationId);

                                    AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);

                                    Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                            Data.class);
                                    if (!CommonUtils.isObjectNullOrEmpty(data) && !CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month())) {
                                        {
                                            if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month().doubleValue())) {
                                                noOfChequeBounce = data.getCheckBounceForLast6Month().doubleValue();
                                            } else {
                                                noOfChequeBounce = 0.0;
                                            }

                                        }
                                    } else {
                                        noOfChequeBounce = 0.0;
                                    }

                                    scoreParameterRetailRequest.setChequeBounce(noOfChequeBounce);
                                    scoreParameterRetailRequest.setChequeBounce_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting CHEQUE_BOUNCE_PAST_SIX_MONTH_PL parameter : ",e);
                                    scoreParameterRetailRequest.setChequeBounce_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.Retail.DAY_PAST_DUE_PL: {
                                try {

                                    CibilResponse cibilResponse = cibilClient.getDPDLastXMonth(applicationId,retailApplicantDetail.getPan());
                                    if (!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isListNullOrEmpty(cibilResponse.getListData())) {
                                        List<Integer> listDPD = (List<Integer>) cibilResponse.getListData();

                                        Integer maxDPD = Collections.max(listDPD);
                                        if (!CommonUtils.isObjectNullOrEmpty(maxDPD)) {
                                            scoreParameterRetailRequest.setDpd(maxDPD.doubleValue());
                                        } else {
                                            scoreParameterRetailRequest.setDpd(0.0);
                                        }
                                    } else {
                                        scoreParameterRetailRequest.setDpd(0.0);
                                    }
                                    scoreParameterRetailRequest.setDPD_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting DAY_PAST_DUE_PL parameter from CIBIL client : ",e);
                                    scoreParameterRetailRequest.setDPD_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.NET_ANNUAL_INCOME_PL: {
                                scoreParameterRetailRequest.setNetAnnualIncome_p(true);
                                try {
                                    if (CommonUtils.isObjectNullOrEmpty(grossMonthlyIncome)) {
                                        scoreParameterRetailRequest.setNetAnnualIncome_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting NET_ANNUAL_INCOME_PL parameter : ", e);
                                    scoreParameterRetailRequest.setNetAnnualIncome_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.Retail.EMI_NMI_PL: {

                                scoreParameterRetailRequest.setEmiNmi_p(true);
                                    try {
                                         scoreParameterRetailRequest.setEmiNmi_p(true);
                                         scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
                                            //scoreParameterRetailRequest.setEmi(emi);
                                    } catch (Exception e) {
                                        logger.error("error while getting EMI_NMI_PL parameter : ",e);
                                        scoreParameterRetailRequest.setNetAnnualIncome_p(false);
                                    }
                                break;
                            }
                            case ScoreParameter.Retail.NO_OF_YEAR_CURRENT_LOCATION_PL:
                                try {
                                    if(retailApplicantDetail.getResidenceSinceYear() != null && retailApplicantDetail.getResidenceSinceMonth() != null) {
                                        Integer year = retailApplicantDetail.getResidenceSinceYear();
                                        Integer month = retailApplicantDetail.getResidenceSinceMonth();
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
                                        String s = "01/" + month + "/" + year;
                                        logger.info("Starting Date of Staying in Current Location For PL==== > {}",s);
                                        Integer[] exactAgeFromDate = CommonUtils.getExactAgeFromDate(simpleDateFormat.parse(s));
                                        Double noStayLoc = (((double) exactAgeFromDate[0]) + ((double)exactAgeFromDate[1] / 12));
                                        logger.info("No Of Years Staying in Current Location For PL==== > {}",noStayLoc);
                                        scoreParameterRetailRequest.setNoOfYearCurrentLocation(noStayLoc);
                                        scoreParameterRetailRequest.setIsNoOfYearCurrentLocation_p(true);
                                    }else{
                                        logger.error("NO_OF_YEAR_CURRENT_LOCAITON_PL parameter {},{}: ", retailApplicantDetail.getResidenceSinceYear(),retailApplicantDetail.getResidenceSinceMonth());
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting NO_OF_YEAR_CURRENT_LOCAITON_PL parameter : ", e);
                                    scoreParameterRetailRequest.setIsNoOfYearCurrentLocation_p(false);
                                }
                                break;
                            case ScoreParameter.Retail.SPOUSE_EMPLOYMENT_DETAILS_PL:
                                try {

                                    Long spouseEmployment =null;

                                    if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getSpouseEmployment()))
                                        spouseEmployment = retailApplicantDetail.getSpouseEmployment().longValue();

                                    if(CommonUtils.isObjectNullOrEmpty(spouseEmployment))
                                    {
                                        spouseEmployment=3l; // No Spouse
                                    }
                                    scoreParameterRetailRequest.setSpouseEmploymentDetails(spouseEmployment);
                                    scoreParameterRetailRequest.setSpouseEmploymentDetails_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting SPOUSE_EMPLOYMENT_DETAILS_PL parameter : ",e);
                                    scoreParameterRetailRequest.setSpouseEmploymentDetails_p(false);
                                }
                                break;
                            case ScoreParameter.Retail.NUMBER_OF_DEPENDENTS_PL:
                                try {
                                    Integer noOfDependent = retailApplicantDetail.getNoOfDependent();
                                    scoreParameterRetailRequest.setNumberOfDependents(noOfDependent);
                                    scoreParameterRetailRequest.setNumberOfDependents_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting NUMBER_OF_DEPENDENTS_PL parameter : ",e);
                                    scoreParameterRetailRequest.setNumberOfDependents_p(false);
                                }
                                break;
                            case ScoreParameter.Retail.DESIGNATION_PL:
                                try {
                                    Long designation = retailApplicantDetail.getDesignation().longValue();
                                    scoreParameterRetailRequest.setDesignation(designation);
                                    scoreParameterRetailRequest.setDesignation_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting DESIGNATION_PL parameter : ",e);
                                    scoreParameterRetailRequest.setDesignation_p(false);
                                }
                                break;
                            case ScoreParameter.Retail.LOAN_TO_INCOME_RATIO_PL: {

                                try {
                                    Double monthlyIncome = 0d;
                                    if (!CommonUtils.isObjectNullOrEmpty(netMonthlyIncome)) {
                                        scoreParameterRetailRequest.setLoanToIncomeRatio_p(true);
                                        scoreParameterRetailRequest.setLoanAmtProposed(retailApplicantDetail.getLoanAmountRequired());
                                        scoreParameterRetailRequest.setNetAnnualIncome(netMonthlyIncome * 12);
                                    } else {
                                        scoreParameterRetailRequest.setLoanToIncomeRatio_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting LOAN_TO_INCOME_RATIO_PL parameter : ",e);
                                    scoreParameterRetailRequest.setLoanToIncomeRatio_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.Retail.NET_WROTH_TO_LOAN_AMOUNT_PL:
                                try {
                                    Double netwroth = retailApplicantDetail.getNetworth();
                                    scoreParameterRetailRequest.setNetWorth(netwroth);
                                    scoreParameterRetailRequest.setEligibleLoanAmountCircular(eligibleLoanAmountCircular);
                                    scoreParameterRetailRequest.setIsNetWrothToLoanAmount_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting NET_WROTH_TO_LOAN_AMOUNT_PL parameter : ",e);
                                    scoreParameterRetailRequest.setIsNetWrothToLoanAmount_p(false);
                                }
                                break;
                            case ScoreParameter.Retail.AVG_EOD_BAL_TO_TOTAL_DEPOSITE_PL:
                                try {
                                    Double totalEODBalAvg=0.0;
                                    Double deposite = 0.0;
                                    boolean isAvgEod = false;

                                    for(Data bankStatementData : bankStatementDatas) {
                                        if(bankStatementData.getSummaryInfo() != null) {
                                            if(!CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg()) && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalCredit())) {
                                                totalEODBalAvg = totalEODBalAvg + Double.parseDouble(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg());
                                                deposite = deposite + Double.parseDouble(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalCredit());
                                                isAvgEod = true;
                                            }
                                        }
                                    }
                                     /*   if(bankStatementData != null &&
                                                bankStatementData.getSummaryInfo() != null &&
                                                bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg() != null &&
                                                bankStatementData.getSummaryInfo().getSummaryInfoTotalDetails().getTotalCredit() != null) {
                                            totalEODBalAvg = Double.valueOf(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg());
                                            deposite = Double.valueOf(bankStatementData.getSummaryInfo().getSummaryInfoTotalDetails().getTotalCredit());
                                            logger.info("value===>{},{}",totalEODBalAvg,deposite);
                                        }else{
                                            isAvgEod =false;
                                            logger.info("error while gettig AVG_EOD_BAL_TO_TOTAL_DEPOSITE_PL parameter value ===>{},{}",totalEODBalAvg,deposite);
                                    }*/

                                    if(isAvgEod){
                                        scoreParameterRetailRequest.setIsAvgEODBalToTotalDeposite_p(true);
                                        scoreParameterRetailRequest.setAvgEODBal(totalEODBalAvg);
                                        scoreParameterRetailRequest.setDeposite(deposite);
                                    }else{
                                        scoreParameterRetailRequest.setIsAvgEODBalToTotalDeposite_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting DESIGNATION_PL parameter : ",e);
                                    scoreParameterRetailRequest.setIsAvgEODBalToTotalDeposite_p(false);
                                }
                                break;
                            case ScoreParameter.Retail.TENURE_OF_THE_LOAN_PL:
                                try {
                                    scoreParameterRetailRequest.setTenureOfTheLoan(eligibleTenure);
                                    scoreParameterRetailRequest.setTenureOfTheLoan_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting TENURE_OF_THE_LOAN_PL parameter : ",e);
                                    scoreParameterRetailRequest.setTenureOfTheLoan_p(false);
                                }
                                break;
                            case ScoreParameter.Retail.ANNUAL_INCOME_PL:
                                scoreParameterRetailRequest.setAnnualIncome_p(true);
                                break;
                            case ScoreParameter.Retail.INCOME_PROOF_PL:
                                if(isItrMannualFilled == null || !isItrMannualFilled) {
                                    scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.IT_RETURN_AND_BANK_STATEMENT);
                                }else {
                                    scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.BANK_STATEMENT);
                                }
                                scoreParameterRetailRequest.setIncomeProof_p(true);
                                break;
                            case ScoreParameter.Retail.MON_INCOME_DEPENDANT_PL:
                                scoreParameterRetailRequest.setNoOfDependants(retailApplicantDetail.getNoOfDependent());
                                scoreParameterRetailRequest.setIsMonIncomePerDep_p(true);
                                break;
                            case ScoreParameter.Retail.AVG_INCREASE_INCOME_REPORT_3_YEARS_PL:
                                logger.info("Income List From ITR for PL == >{}==>ApplicationId==>{}",incomeOfItrOf3Years,applicationId);
                                if(!CommonUtils.isListNullOrEmpty(incomeOfItrOf3Years)) {
                                    if(incomeOfItrOf3Years.size() == 3) { //as if now considering 3 Years Compulsory
                                        Double itrLastToLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 1);
                                        if(itrLastToLastToLastYearIncome == null ) {
                                            itrLastToLastToLastYearIncome = 1.0d;
                                        }
                                        Double itrLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 2);
                                        if(itrLastToLastYearIncome == null) {
                                            itrLastToLastYearIncome = 1.0d;
                                        }
                                        Double itrLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 3);

                                        if(itrLastYearIncome == null) {
                                            itrLastYearIncome = 0.0;
                                        }
                                        Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
                                        logger.info("Final Income After Calculation for PL == >{}==>ApplicationId==>{}",finalIncome,applicationId);
                                        if(Double.isFinite(finalIncome)) {
                                            scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
                                            scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
                                        }
                                    }else if(incomeOfItrOf3Years.size() == 2) { //as if now considering 2 Years Compulsory
                                        Double itrLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 1);
                                        if(itrLastToLastYearIncome == null) {
                                            itrLastToLastYearIncome = 1.0d;
                                        }
                                        Double itrLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 2);
                                        if(itrLastYearIncome == null) {
                                            itrLastYearIncome = 1.0;
                                        }
                                        Double finalIncome =  (((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100);
                                        logger.info("Final Income After Calculation for PL == >{}==>ApplicationId==>{}",finalIncome,applicationId);
                                        if(Double.isFinite(finalIncome)) {
                                            scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
                                            scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
                                        }

                                    }else if(incomeOfItrOf3Years.size() == 1) { //as if now considering 1 Years Compulsory
                                        logger.info("Final Income After Calculation for PL as Only one year ITR Found == >{}==>ApplicationId==>{}",0.0d,applicationId);
                                        scoreParameterRetailRequest.setIncomeFromItr(0.0d);
                                        scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
                                    }
                                }
                                break;
                            case ScoreParameter.Retail.AVAILABLE_INCOME_PL:
                                try {
                                    logger.info("netMonthlyIncome===>{}===grossAnnualIncome===>{}== For ApplicationId ==>{}===>FpProductId===>{}",netMonthlyIncome,grossMonthlyIncome,applicationId,fpProductId);
                                    scoreParameterRetailRequest.setFoir(scoringRequest.getFoir());
                                    scoreParameterRetailRequest.setIsAvailableIncome_p(true);

                                } catch (Exception e1) {
                                    logger.error("Error while getting Eligibility Based On Income == >{}",e1);
                                }
                                break;
                            case ScoreParameter.Retail.AVG_DEPOS_LAST_6_MONTH_PL:
                                Double value = 0.0d;
                                for(Data bankStatementData : bankStatementDatas) {
                                    if(bankStatementData != null && bankStatementData.getSummaryInfo() != null && bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails() != null  && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit())) {
                                        value = value + Double.valueOf(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit()); // / 6
                                    }
                                }
                                scoreParameterRetailRequest.setAvgOfTotalCheDepsitLast6Month(value);
                                scoreParameterRetailRequest.setIsAvgOfTotalCheDepsitLast6Month_p(true);
                                break;
                            case ScoreParameter.Retail.CHEQUE_BOUNCE_LAST_1_MONTH_PL:
                                try {

                                    Double chequelast6Month = 0.0d;
                                    scoreParameterRetailRequest.setChequeBouncelast1Month(0.0d);
                                    for(Data bankStatementData : bankStatementDatas) {
                                        if(!CommonUtils.isObjectNullOrEmpty(bankStatementData.getCheckBounceForLast6Month())) {
                                            chequelast6Month = chequelast6Month + bankStatementData.getCheckBounceForLast6Month().doubleValue();
                                        }
                                    }
                                    scoreParameterRetailRequest.setChequeBouncelast1Month(chequelast6Month);
                                    scoreParameterRetailRequest.setIsChequeBounceLast1Month_p(true);
                                }catch(Exception e) {
                                    logger.error("Error while Getting Cheque Bounse of Last 6 Month");
                                }
                                break;
                            case ScoreParameter.Retail.ADDI_INCOME_SPOUSE_PL:
                                //Not Available in Sheet Document
                                // Unmarried or married assign zero income in case income not available
                                scoreParameterRetailRequest.setSpouseIncome(retailApplicantDetail.getAnnualIncomeOfSpouse() != null ? retailApplicantDetail.getAnnualIncomeOfSpouse() : 0);
                                scoreParameterRetailRequest.setIsSpouseIncome_p(true);
                                break;
                            case ScoreParameter.Retail.EMI_NMI_RATIO_PL:
                                //Already Set NMI and GMI and EMI Above Before Switch Starts

                                if (!CommonUtils.isObjectNullOrEmpty(netMonthlyIncome) && !CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getEmi())) {
                                    scoreParameterRetailRequest.setEmiNmiRatio_p(true);
                                    scoreParameterRetailRequest.setEmi(scoringRequestLoans.getEmi());
                                    scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
                                } else {
                                    scoreParameterRetailRequest.setEmiNmi_p(false);
                                    logger.error("Monthly income from Eligibility:: " + netMonthlyIncome);
                                }

                            case ScoreParameter.Retail.CURRENT_JOB_EXP_PL:
                                try {
                                    if(OccupationNatureNTB.SALARIED.getId().equals(retailApplicantDetail.getEmploymentType())){
                                        Double currentExperience = 0.0;
                                        if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getCurrentJobYear())){
                                            currentExperience += Double.valueOf(retailApplicantDetail.getCurrentJobYear());
                                            logger.info("CURRENT_JOB_EXP Year {}===>{}",retailApplicantDetail.getCurrentJobYear());
                                        }

                                        if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getCurrentJobMonth())) {
                                            currentExperience += (retailApplicantDetail.getCurrentJobMonth() / 12);
                                            logger.info("CURRENT_JOB_EXP Month {}===>{}",retailApplicantDetail.getCurrentJobMonth());
                                        }
                                        scoreParameterRetailRequest.setWorkingExperienceCurrent(currentExperience);
                                        scoreParameterRetailRequest.setIsWorkingExperienceCurrent_p(true);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting CURRENT_JOB_EXP parameter : {}",e);
                                }
                                break;
                            case ScoreParameter.Retail.CURRENT_EMPLOYMENT_STATUS_PL:
                                scoreParameterRetailRequest.setIsCurrentEmploymentStatus_p(retailApplicantDetail.getEmploymentStatus() != null);
                                scoreParameterRetailRequest.setCurrentEmploymentStatus((retailApplicantDetail.getEmploymentStatus() != null  ? retailApplicantDetail.getEmploymentStatus().longValue() : null));
                                break;
                            case ScoreParameter.Retail.MIN_BANKING_RELATIONSHIP_PL:
                                scoreParameterRetailRequest.setIsMinBankingRelationship_p(true);
                                scoreParameterRetailRequest.setMinBankingRelationship(minBankRelationshipInMonths == null ? 0 : minBankRelationshipInMonths);
                                break;
                            case ScoreParameter.Retail.RESIDENCE_TYPE_PL:
                            	if(retailApplicantDetail.getResidenceType() != null) {
                					if(ResidenceStatusRetailMst.OWNED.getId().equals(retailApplicantDetail.getResidenceType())) {
                						if(retailApplicantDetail.getIsOwnedProp() != null && retailApplicantDetail.getIsOwnedProp()) {
                							scoreParameterRetailRequest.setResidenceType(8); //Owned (Encumbered) : No Need to Add in ENUM. This is Only For Scoring
                						}else {
                							scoreParameterRetailRequest.setResidenceType(ResidenceStatusRetailMst.OWNED.getId());
                						}
                					}else {
                						scoreParameterRetailRequest.setResidenceType(retailApplicantDetail.getResidenceType());
                					}
                					scoreParameterRetailRequest.setIsResidenceType_p(true);
                				}
                				break;
                            case ScoreParameter.Retail.REPAYMENT_MODE_PL:
                                scoreParameterRetailRequest.setRepaymentMode(retailApplicantDetail.getRepaymentMode());
                                scoreParameterRetailRequest.setRepaymentMode_p(retailApplicantDetail.getRepaymentMode() != null);
                                break;
                            default:
                                break;

                        }
                    }

                    Double grossAnnualIncome =0d;
                    /*EligibililityRequest eligibililityRequest = new EligibililityRequest();
					eligibililityRequest.setApplicationId(applicationId);
					eligibililityRequest.setIsIncomeCalculate(false);
					EligibilityResponse eligibilityResponse = eligibilityClient.getMonthlyIncome(eligibililityRequest);*/
					if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse) && !com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse.getData())){
					    List monthlyIncomeList = (List) eligibilityResponse.getData();
					    if(!com.capitaworld.service.matchengine.utils.CommonUtils.isListNullOrEmpty(monthlyIncomeList)){
					        grossAnnualIncome = Double.valueOf(monthlyIncomeList.get(8).toString());
					        scoreParameterRetailRequest.setGrossAnnualIncome(grossAnnualIncome*12);
					    }
					}

                    logger.info(MSG_SCORE_PARAMETER + scoreParameterRetailRequest.toString());

                    logger.info("----------------------------END-------------------------------------------");

                    Gson g = new Gson();
                    ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                    try {
                        scoringRequestDetail.setApplicationId(applicationId);
                        scoringRequestDetail.setRequest(g.toJson(scoreParameterRetailRequest));
                        scoringRequestDetail.setCreatedDate(new Date());
                        scoringRequestDetail.setIsActive(true);
                        scoringRequestDetailRepository.save(scoringRequestDetail);

                        logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                    } catch (Exception e) {
                        logger.error(CommonUtils.EXCEPTION,e);
                    }
                }
            }

            scoringRequest.setScoreParameterRetailRequest(scoreParameterRetailRequest);
            scoringRequestList.add(scoringRequest);
        }

        try {
            scoringClient.calculateScoreList(scoringRequestList);
            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

    @Override
    public Object[] getRetailConcessionDetails(ScoringRequestLoans scoringRequestLoans,List<String> bankStringsList,List<BankingRelation> bankingRelationList,List<FinancialArrangementsDetail> financialArrangementsDetailList,RetailApplicantDetail retailApplicantDetail,List<CibilScoreLogRequest> cibilResponse1) {
    	logger.info("Getting Retail Concession Details===={}========{}==>>>>"+scoringRequestLoans.getApplicationId()+""
    			+ "fpProductId===={}=====>"+scoringRequestLoans.getFpProductId());

    		Long applicationId= scoringRequestLoans.getApplicationId();
    		Long fpProductId = 	scoringRequestLoans.getFpProductId();

    		Object[] retailConcessionObj = new Object[15];
 			ProductMaster productMaster = productMasterRepository.findOne(fpProductId);

    		// start getting relation with bank and loan detail for concession in roi
                Boolean isBorrowersHavingAccounts=false;
                Boolean isBorrowersAvailingLoans=false;
                Boolean isBorrowersHavingSalaryAccounts=false;
                Boolean isBorrowersAvailingCreaditCards=false;

     		   // LOGIC FOR CHECK OFF RELATED ISSUE
                Boolean isCheckOffDirectPayEmi = false;
                Boolean isCheckOffAgreetoPayOutstanding =false;
                Boolean isCheckOffShiftSalAcc = false;
                Boolean isCheckOffPayOutstndAmount = false;
                Boolean isCheckOffNotChangeSalAcc=false;
                // ENDS HERE CHECK OFF

        		// CIBIL BASED CONCESSION RATE  OF INTEREST
        		Boolean isCreaditHisotryGreaterSixMonths = false;
        		Boolean isCreaditHisotryLessThenSixMonths= false;
        		Boolean isNoCreaditHistory = false;        		
        		// ENDS HERE
        		Boolean isWomenApplicant = false;

        		if(retailApplicantDetail == null) {
        			retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);
        		}

            	if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {

            		if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getIsCheckOffDirectPayEmi())){
            			isCheckOffDirectPayEmi  =  retailApplicantDetail.getIsCheckOffDirectPayEmi();
            				isCheckOffDirectPayEmi = true;
            		}

            		if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getIsCheckOffAgreeToPayOutstanding())){
            			isCheckOffAgreetoPayOutstanding = retailApplicantDetail.getIsCheckOffAgreeToPayOutstanding();
            				isCheckOffAgreetoPayOutstanding = true;
            		}

            		if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getIsCheckOffShiftSalAcc())){
            			isCheckOffShiftSalAcc = retailApplicantDetail.getIsCheckOffShiftSalAcc();
            				isCheckOffShiftSalAcc = true;
            		}

            		if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getIsCheckOffPayOutstndAmount())){
            			isCheckOffPayOutstndAmount = retailApplicantDetail.getIsCheckOffPayOutstndAmount();
            				isCheckOffPayOutstndAmount = true;
            		}
            		if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getIsCheckOffNotChangeSalAcc())){
            			isCheckOffNotChangeSalAcc = retailApplicantDetail.getIsCheckOffNotChangeSalAcc();
            				isCheckOffNotChangeSalAcc = true;
            	   }

            		if(Gender.FEMALE.getId().equals(retailApplicantDetail.getGenderId())){
            			isWomenApplicant = true;
            		}

            	}
    			// ENDS HERE =========================================================>

                 Double cibilActualScore = null;
                 try {
                 	if(cibilResponse1 == null) {
                 		cibilResponse1 = cibilClient.getSoftpingScores(applicationId, retailApplicantDetail.getPan());
                 		if(CommonUtils.isListNullOrEmpty(cibilResponse1)) {
        					logger.error("Cibil Score Not found for ApplicationId == >{}",applicationId);        					
        				}
                 	}

                 	if (!CommonUtils.isListNullOrEmpty(cibilResponse1)) {
                 		cibilActualScore = filterBureauScoreByVersion(scoringRequestLoans.getBureauVersion(), cibilResponse1);
    					logger.info("CIBIL ACTUAL SOCRE ------------------>" + "applicationId=====>" + applicationId + "----"+ cibilActualScore);
    					if(cibilActualScore != null) {
                     		if(cibilActualScore >= 300 && cibilActualScore <=900) {
                     		 	isCreaditHisotryGreaterSixMonths = true;
                     	 	}

    	                 	if(cibilActualScore>= 0 && cibilActualScore <= 5){
    	                  		isCreaditHisotryLessThenSixMonths = true;
    	                  	}
    	
    	                 	if(cibilActualScore.equals(-1d)){
    	                 		isNoCreaditHistory = true;
    	                 	}
                     	}
                 	}

                 	
                 	 
                 }catch (Exception e) {
                     logger.error("EXCEPTION IS GETTING WHILE GETTING CIBIL SCORE========={}");
         		}


                // check isBorrowersHavingAccounts and isBorrowersHavingSalaryAccounts

                 if(bankingRelationList == null) {
            		  bankingRelationList = bankingRelationlRepository.listBankRelationAppId(applicationId);
                 }

                if(!CommonUtils.isObjectNullOrEmpty(bankingRelationList))
                {
                    for(BankingRelation bankingRelation:bankingRelationList)
                    {
                        BankList fsOrgObj=null;
                        try {
                            fsOrgObj = BankList.fromName(bankingRelation.getBank());
                        }
                        catch (Exception e)
                        {
                            logger.error("Other Bank Selected By User For Account");
                        }

                        if(!CommonUtils.isObjectNullOrEmpty(productMaster.getUserOrgId()) && !CommonUtils.isObjectNullOrEmpty(fsOrgObj) && !CommonUtils.isObjectNullOrEmpty(fsOrgObj.getOrgId()))
                        {
                            if(productMaster.getUserOrgId().toString().equals(fsOrgObj.getOrgId()))
                            {
                                isBorrowersHavingAccounts=true;

                                //  get Salary Account detail

                                try {

                                    ReportRequest reportRequest = new ReportRequest();
                                    reportRequest.setApplicationId(applicationId);

                                    if(bankStringsList == null) {
                                    AnalyzerResponse analyzerResponse = analyzerClient.getSalaryDetailsFromReport(reportRequest);
                                    	bankStringsList = (List<String> )analyzerResponse.getData();
                                    }

                                    if(!CommonUtils.isObjectNullOrEmpty(bankStringsList))
                                    {
                                        for (String bankName:bankStringsList)
                                        {
                                            BankList fsOrgObjInner=null;
                                            try {
                                                fsOrgObjInner = BankList.fromName(bankName);
                                            }
                                            catch (Exception e)
                                            {
                                                logger.error("Other Bank Selected By User For Salary Account");
                                            }

                                            if(!CommonUtils.isObjectNullOrEmpty(productMaster.getUserOrgId()) && !CommonUtils.isObjectNullOrEmpty(fsOrgObjInner) && !CommonUtils.isObjectNullOrEmpty(fsOrgObjInner.getOrgId()))
                                            {
                                                if(productMaster.getUserOrgId().toString().equals(fsOrgObjInner.getOrgId()))
                                                {
                                                    isBorrowersHavingSalaryAccounts=true;
                                                }
                                            }
                                        }
                                    }

                                } catch (Exception e) {
                                    logger.error("error while getting Salary Account Detail: ",e);
                                }

                            }
                        }
                    }
                }

                // check isBorrowersAvailingLoans and isBorrowersAvailingCreaditCards
                if(financialArrangementsDetailList == null ) {
                	financialArrangementsDetailList = financialArrangementDetailsRepository.listSecurityCorporateDetailByAppId(applicationId);
                }
                if(!CommonUtils.isObjectNullOrEmpty(financialArrangementsDetailList))
                {
                    for(FinancialArrangementsDetail financialArrangementsDetail:financialArrangementsDetailList)
                    {
                        BankList fsOrgObj=null;
                        try {
                            fsOrgObj = BankList.fromName(financialArrangementsDetail.getFinancialInstitutionName());
                        }
                        catch (Exception e)
                        {
                            logger.error("Other Bank Selected By User For Loan Account");
                        }

                        if(!CommonUtils.isObjectNullOrEmpty(productMaster.getUserOrgId()) && !CommonUtils.isObjectNullOrEmpty(fsOrgObj) && !CommonUtils.isObjectNullOrEmpty(fsOrgObj.getOrgId()))
                        {
                            if(productMaster.getUserOrgId().toString().equals(fsOrgObj.getOrgId()))
                            {
                                //  get Credit Card Account detail
                                if(financialArrangementsDetail.getLoanType().toString().equals(CommonUtils.CREDIT_CARD))
                                {
                                    isBorrowersAvailingCreaditCards=true;
                                }
                                else // get Loan Account Detail
                                {
                                    isBorrowersAvailingLoans=true;
                                }

                            }
                        }
                    }
                }
               // IS BASED ON CONCESSION RELATED
                retailConcessionObj[0] =  isBorrowersHavingAccounts;
                retailConcessionObj[1] =  isBorrowersAvailingLoans ;
                retailConcessionObj[2] =  isBorrowersHavingSalaryAccounts;
                retailConcessionObj[3] =  isBorrowersAvailingCreaditCards;

                // IS BASED ON PARTIALLY CHECK OFF AND FULLY CHECK OFF RELATED
                retailConcessionObj[4] =  isCheckOffDirectPayEmi;
                retailConcessionObj[5] =  isCheckOffAgreetoPayOutstanding;
                retailConcessionObj[6] =  isCheckOffShiftSalAcc;
                retailConcessionObj[7] =  isCheckOffPayOutstndAmount;
                retailConcessionObj[8] =  isCheckOffNotChangeSalAcc;

                // CIBIL BASED CONCESSION
                retailConcessionObj[9]= cibilActualScore;
                retailConcessionObj[10] =  isCreaditHisotryGreaterSixMonths;
                retailConcessionObj[11] = isCreaditHisotryLessThenSixMonths;
                retailConcessionObj[12] = isNoCreaditHistory;
                retailConcessionObj[13] = isWomenApplicant;

                return retailConcessionObj;

    }

    private Double filterBureauScoreByVersion(Integer version,List<CibilScoreLogRequest> logRequests) {
		List<CibilScoreLogRequest> filtered = null;
		if(version == null || version == 1) {
			filtered = logRequests.stream().filter(score -> !CIBIL_SCORE_VERSION_2.equalsIgnoreCase(score.getScoreName())).collect(Collectors.toList());	
		}else {
			filtered = logRequests.stream().filter(score -> CIBIL_SCORE_VERSION_2.equalsIgnoreCase(score.getScoreName())).collect(Collectors.toList());
		}
		if(CommonUtils.isListNullOrEmpty(filtered)) {
			logger.info("Actual Score Found Null For Version ===>{}",version);
			return null;
		}
		CibilScoreLogRequest cibilScoreLogRequest = filtered.get(0);
		if(CommonUtils.isObjectNullOrEmpty(cibilScoreLogRequest.getActualScore())) {
			logger.info("Actual Score Found Null For ApplicationId = >{}",cibilScoreLogRequest.getApplicantId());
			return null;
		}
		if(cibilScoreLogRequest.getActualScore().equals("000-1")){
			return -1d;
		}else
		{
			return Double.parseDouble(cibilScoreLogRequest.getActualScore());
		}
		
	}
    
    @SuppressWarnings("unchecked")
	@Override
    public ResponseEntity<LoansResponse> calculateRetailHomeLoanScoringList(List<ScoringRequestLoans> scoringRequestLoansList) {

        RetailApplicantDetail retailApplicantDetail = null;
        Boolean isItrMannualFilled = false;
        Long applicationId = null;
        Long orgId = null;
        List<Long> coAppIds = null;
        List<Long> coAppITRUploadedIds = null;
        Double netMonthlyIncome = 0.0d;
        Double grossMonthlyIncome = 0.0d;
        PrimaryHomeLoanDetail primaryHomLoanDetail = null;
        List<Data> bankStatementDatas = null;
        List<Integer> dpds = Collections.emptyList();
        Double totalEMI = 0.0d;
        List<CibilScoreLogRequest> cibilResponse = null;
        List<BankingRelation> bankingRelationList = null;
        List<String> bankStringsList = null;
        List<FinancialArrangementsDetail> financialArrangementsDetailList = null;
       // Boolean isWomenApplicant = false;
        List<Double> incomeOfItrOf3Years = null;

        if(!CommonUtils.isListNullOrEmpty(scoringRequestLoansList)) {
        	applicationId = scoringRequestLoansList.get(0).getApplicationId();
        	retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);
        	if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                return new ResponseEntity<>(new LoansResponse(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_HOME_LOAN_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        	logger.info("retailApplicantDetail.getEmploymentType()=======>{}",retailApplicantDetail.getEmploymentType());
        //	isWomenApplicant = Gender.FEMALE.getId().equals(retailApplicantDetail.getGenderId());
        	primaryHomLoanDetail = primaryHomeLoanDetailRepository.getByApplication(applicationId);
        	if (CommonUtils.isObjectNullOrEmpty(primaryHomLoanDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                return new ResponseEntity<>(new LoansResponse("Primary Detail Must Not be null While Calculating Home Loan Scoring", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }

        	EligibilityResponse eligibilityResponse = null;
			try {
				EligibililityRequest eligibililityRequest = new EligibililityRequest();
				eligibililityRequest.setApplicationId(applicationId);
				eligibililityRequest.setIsIncomeCalculate(false);
				eligibilityResponse = eligibilityClient.getMonthlyIncome(eligibililityRequest);
				if(eligibilityResponse == null) {
						return new ResponseEntity<>(new LoansResponse("Eligibility Response Found NULL : ", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			} catch (EligibilityExceptions e) {
				logger.error("Error while Getting MonthlyIncome Details == >{}",e);
				return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong while getting Calculated NMI and GMI for Scoring : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
			}
            if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse)
                    && !com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse.getData())){
                List incomeList = (List) eligibilityResponse.getData();
                if(!com.capitaworld.service.matchengine.utils.CommonUtils.isListNullOrEmpty(incomeList)){
                    netMonthlyIncome = Double.valueOf(incomeList.get(0).toString());
                    grossMonthlyIncome = Double.valueOf(incomeList.get(8).toString());
                }

                if(netMonthlyIncome <= 0 || grossMonthlyIncome <= 0) {
                	return new ResponseEntity<>(new LoansResponse("NMI or GMI is Zero ", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
                }
                logger.info("Net Monthly Income For ApplicationId======{}======>{}",applicationId,netMonthlyIncome);
                logger.info("Gross Annual Income For ApplicationId======{}======>{}",applicationId,grossMonthlyIncome);
            }
            try {
                 ReportRequest reportRequest = new ReportRequest();
                 reportRequest.setApplicationId(applicationId);
                 AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
                 if(analyzerResponse == null) {
                	 return new ResponseEntity<>(new LoansResponse("Analyser Response Found null For Scoring Calculation HL For the ApplicationId===>" + applicationId, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
                 }
                 bankStatementDatas = new ArrayList<>(5);
                 for(Object object : (List)analyzerResponse.getData()) {
                	 Data dataBs = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) object, Data.class);
 					if(dataBs != null) {
 						if(!EligibilityUtils.isObjectNullOrEmpty(dataBs.getCoAppId())) {
 							continue;
 						}
 						bankStatementDatas.add(dataBs);
 					}
                 }
            }catch(Exception e) {
            	logger.error("Error while getting Bank Statement Details===>{}",e);
            	return new ResponseEntity<>(new LoansResponse("Error while Getting Bank Statemtnt Report for ApplicationID====>" + applicationId + " and Message====>" + e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }

            totalEMI = financialArrangementDetailsService.getTotalEmiByApplicationIdSoftPing(applicationId);

            try {
            	cibilResponse = cibilClient.getSoftpingScores(applicationId, retailApplicantDetail.getPan());
            	if(cibilResponse == null) {
            		return new ResponseEntity<>(new LoansResponse("CIBIL Score Reponse Found NULL for ApplicationID====>" + applicationId, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            	}

            	CibilResponse cibilResponseDpd = cibilClient.getDPDLastXMonth(applicationId,retailApplicantDetail.getPan());
                if(cibilResponseDpd == null) {
            		return new ResponseEntity<>(new LoansResponse("CIBIL DPD Reponse Found NULL for ApplicationID====>" + applicationId, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            	}
                if(!CommonUtils.isObjectNullOrEmpty(cibilResponseDpd) && !CommonUtils.isObjectListNull(cibilResponseDpd.getListData())){
    				List cibilResponseList = cibilResponseDpd.getListData();
    				dpds = new ArrayList<>(cibilResponseList.size());
    				for (int i = 0; i < cibilResponseList.size(); i++) {
    					String cibilResponseObj = cibilResponseList.get(i).toString();
    					if(cibilResponseObj.contains("|")){
    						String[] cibilDpdVal = cibilResponseObj.split("\\|");
    						if(!CommonUtils.isObjectNullOrEmpty(cibilDpdVal[1])) {
    							dpds.add(Integer.parseInt(cibilDpdVal[1]));
    						}
    					}else {
    						dpds.add(Integer.parseInt(cibilResponseList.get(i).toString()));
    					}
    				}
    			}

            }catch(Exception e) {
            	return new ResponseEntity<>(new LoansResponse("Error while Getting DPD or CIBIL Score for ApplicationID====>" + applicationId + " and Message====>" + e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);

            }

            //Getting is Itr Mannual Filed
            isItrMannualFilled = loanRepository.isITRUploaded(applicationId);

            //Checking Flags of Bank Account Related
            bankingRelationList = bankingRelationlRepository.listBankRelationAppId(applicationId);

            //Getting Banks List
            ReportRequest reportRequest = new ReportRequest();
            reportRequest.setApplicationId(applicationId);
            AnalyzerResponse analyzerResponse = null;
			try {
				analyzerResponse = analyzerClient.getSalaryDetailsFromReport(reportRequest);
				bankStringsList = (List<String> )analyzerResponse.getData();
			} catch (AnalyzerException e) {
				logger.error("Error while Getting bankList from Analyzer ===> {}",e);
				return new ResponseEntity<>(new LoansResponse("Error while Getting BankList From Analyser for ApplicationID====>" + applicationId + " and Message====>" + e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
			}

			//Getting All Loans
			financialArrangementsDetailList = financialArrangementDetailsRepository.listSecurityCorporateDetailByAppId(applicationId);
			incomeOfItrOf3Years = loanRepository.getIncomeOfItrOf3Years(applicationId);
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationId);
        	if(!CommonUtils.isListNullOrEmpty(coAppIds)) {
        		coAppITRUploadedIds = coApplicantDetailRepository.getCoAppIdsOfCoApplicantUploadedITR(applicationId,true);
        	}
        }
        List<ScoringRequest> scoringRequestList=new ArrayList<>(scoringRequestLoansList.size());
        ScoreParameterRetailRequest scoreParameterRetailRequest = null;
        for(ScoringRequestLoans scoringRequestLoans : scoringRequestLoansList)
        {
            Long scoreModelId = scoringRequestLoans.getScoringModelId();
            Long fpProductId = scoringRequestLoans.getFpProductId();
//            homeLoanModelRequest = homeLoanModelService.get(scoringRequestLoans.getLoanPurposeModelId(), null, null);
            Integer minBankRelationshipInMonths = null;
            orgId = scoringRequestLoans.getOrgId();
            if(orgId != null) {
            	BankList bankEnum = BankList.fromOrgId(orgId.toString());
            	if(bankEnum != null) {
            		logger.info("Bank Name====>{}==>Application Id===>{}===> Fp Product Id===>{}",bankEnum.getName(),applicationId,fpProductId);
            		minBankRelationshipInMonths = bankingRelationlRepository.getMinRelationshipInMonthByApplicationAndOrgName(applicationId, bankEnum.getName());
            	}
            	logger.info("Min Banking Relationship in Month === >{}",minBankRelationshipInMonths);
            }
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.RETAIL_HOME_LOAN);
            scoringRequest.setEmi(scoringRequestLoans.getEmi());

            if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
            } else {
                scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
            }

            // STARTS HERE CONCESSION BASED ON RATE OF INTEREST:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            /*
            ScoringRequestLoans requestLoans = new ScoringRequestLoans();
            requestLoans.setApplicationId(applicationId);
            requestLoans.setFpProductId(fpProductId);*/
            Integer consessionBureauVersion = null;
            Object[] bureauVersionIdById = loanRepository.getBureauVersionIdById(scoreModelId);
            if(!CommonUtils.isObjectNullOrEmpty(bureauVersionIdById)) {
           	 if(!CommonUtils.isObjectNullOrEmpty(bureauVersionIdById[0])) {
           		 consessionBureauVersion = Integer.valueOf(bureauVersionIdById[0].toString());            		 
           	 }else {
           		 consessionBureauVersion = 1;
           	 }
           	 scoringRequestLoans.setBureauVersion(consessionBureauVersion);
            }
            Object [] concessionResp = getRetailConcessionDetails(scoringRequestLoans, bankStringsList, bankingRelationList, financialArrangementsDetailList,retailApplicantDetail, cibilResponse);
            logger.info("==========getRetailConcessionDetails========>>>>>"+concessionResp);

           Boolean  isBorrowersHavingAccounts	  =	(Boolean)concessionResp[0];
           Boolean  isBorrowersAvailingLoans          =	(Boolean)concessionResp[1];
           Boolean  isBorrowersHavingSalaryAccounts   = (Boolean)concessionResp[2];
           Boolean  isBorrowersAvailingCreaditCards   = (Boolean)concessionResp[3];

           // is Fully Check Off And Partially Check Off
           Boolean isCheckOffDirectPayEmi             =	(Boolean)concessionResp[4];
           Boolean  isCheckOffAgreetoPayOutstanding   = (Boolean)concessionResp[5];
           Boolean  isCheckOffShiftSalAcc             =	(Boolean)concessionResp[6];
           Boolean  isCheckOffPayOutstndAmount        =	(Boolean)concessionResp[7];
           Boolean isCheckOffNotChangeSalAcc          =	(Boolean)concessionResp[8];

           // Cibil BAsed Concession
           Double cibilActualScore                   =	(Double)concessionResp[9];
           Boolean isCreaditHisotryGreaterSixMonths   =	(Boolean)concessionResp[10];
           Boolean isCreaditHisotryLessThenSixMonths = (Boolean)concessionResp[11];
           Boolean isNoCreaditHistory                =	(Boolean)concessionResp[12];
           Boolean isWomenApplicant                =	(Boolean)concessionResp[13];


          // partially and fully check off related----->
           scoringRequest.setIsBorrowersHavingAccounts(isBorrowersHavingAccounts);
          scoringRequest.setIsBorrowersAvailingLoans(isBorrowersAvailingLoans);
          scoringRequest.setIsBorrowersHavingSalaryAccounts(isBorrowersHavingSalaryAccounts);
          scoringRequest.setIsBorrowersAvailingCreaditCards(isBorrowersAvailingCreaditCards);

          scoringRequest.setIsCheckOffDirectPayEmi(isCheckOffDirectPayEmi);
          scoringRequest.setIsCheckOffAgreetoPayOutstanding(isCheckOffAgreetoPayOutstanding);
          scoringRequest.setIsCheckOffShiftSalAcc(isCheckOffShiftSalAcc);
          scoringRequest.setIsCheckOffPayOutstndAmount(isCheckOffPayOutstndAmount);
          scoringRequest.setIsCheckOffNotChangeSalAcc(isCheckOffNotChangeSalAcc);
     	// ENDS HERE CHECK OFF LOGIC HERE

		//  Cibil Based Object related----->
          scoringRequest.setCibilActualScore(cibilActualScore);
          scoringRequest.setIsCreaditHisotryGreaterSixMonths(isCreaditHisotryGreaterSixMonths);
          scoringRequest.setIsCreaditHisotryLessThenSixMonths(isCreaditHisotryLessThenSixMonths);
          scoringRequest.setIsNoCreaditHistory(isNoCreaditHistory);

          scoringRequest.setIsWomenApplicant(isWomenApplicant); // Women For HL

          // ENDS HERE CONCESSION BASED ON RATE OF INTEREST:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            ///////// End  Getting Old Request ///////
                scoreParameterRetailRequest =  new ScoreParameterRetailRequest();
                logger.info("scoringRequestLoans.getFoir()=>{}==For ApplicationId====>{}==>For FpProductId===>{}",scoringRequestLoans.getFoir(),applicationId,fpProductId);
                scoreParameterRetailRequest.setFoir(scoringRequestLoans.getFoir());
                scoringRequest.setLoanPurposeModelId(scoringRequestLoans.getLoanPurposeModelId());
                logger.info("----------------------------START RETAIL HL ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

                // GET SCORE RETAIL PERSONAL LOAN PARAMETERS


                if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                    // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                    ScoringResponse scoringResponse = null;
                    try {
                        scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                    } catch (Exception e) {
                        logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                    }

                    List<Map<String, Object>> dataList = new ArrayList<>();
                    if (scoringResponse != null && scoringResponse.getDataList() != null) {
                        dataList = (List<Map<String, Object>>) scoringResponse.getDataList();
                    }

                    for (int i = 0; i < dataList.size(); i++) {

                        ModelParameterResponse modelParameterResponse = null;
                        try {
                            modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                    ModelParameterResponse.class);
                            if(modelParameterResponse == null){
                                continue;
                            }
                        } catch (IOException | NullPointerException e) {
                            logger.error(CommonUtils.EXCEPTION,e);
                            continue;
                        }

                        FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                        fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                        fundSeekerInputRequest.setName(modelParameterResponse.getName());


//                        scoreParameterRetailRequest.setLoanAmtProposed(scoringRequestLoans.getElAmountOnAverageScoring());
                        scoreParameterRetailRequest.setNmi(netMonthlyIncome);
						scoreParameterRetailRequest.setGmi(grossMonthlyIncome);
						scoreParameterRetailRequest.setEmi(scoringRequestLoans.getEmi());
						scoreParameterRetailRequest.setElAmountOnAverageScoring(scoringRequestLoans.getElAmountOnAverageScoring());
                        switch (modelParameterResponse.getName()) {
                        case ScoreParameter.Retail.HomeLoan.AGE:
                        	   try {
                                   if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
                                	   Integer exactAge [] = CommonUtils.getExactAgeFromDate(retailApplicantDetail.getBirthDate());
                                	   logger.info("Age With years and Month == {}==={}", exactAge[0],exactAge[1]);
                                	   Double age = (((double) exactAge[0]) + ((double)exactAge[1] / 12.0d));
                                	   logger.info("Age With Point == {}",age);
                                       scoreParameterRetailRequest.setAge(age);
                                       scoreParameterRetailRequest.setAge_p(true);
                                   }
                               } catch (Exception e) {
                                   logger.error("error while getting AGE_HL parameter : ",e);
                               }
                        	break;
            			case ScoreParameter.Retail.HomeLoan.TOTAL_WORK_EXP:
            				try {
            					Double totalExperience = 0.0;
            					if(retailApplicantDetail.getEmploymentType() != null) {
            						if(!OccupationNatureNTB.SALARIED.getId().equals(retailApplicantDetail.getEmploymentType())){
            							if(retailApplicantDetail.getBusinessStartDate() != null) {
                        					logger.info("retailApplicantDetail.getBusinessStartDate() For HL====ApplicationId===>{}=====>{}",retailApplicantDetail.getBusinessStartDate(),applicationId);
                        					Integer[] busiFromDate = CommonUtils.getExactAgeFromDate(retailApplicantDetail.getBusinessStartDate());
                        					logger.info("Year For HL====ApplicationId===>{}=====>{}",busiFromDate[0],applicationId);
                        					logger.info("Month For HL====ApplicationId===>{}=====>{}",busiFromDate[1],applicationId);
                        					totalExperience = (((double) busiFromDate[0]) + ((double)busiFromDate[1] / 12.0d));
                        					logger.info("Total Business Experiance For HL==== > {}",totalExperience);
                                            scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                            scoreParameterRetailRequest.setWorkingExperience_p(true);
                        				}
                					}else {
                						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceYear())) {
                                        	totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceYear());
                                        	logger.info("totalExperience Year {}===>{}",retailApplicantDetail.getTotalExperienceYear());
                                        }
                                        if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceMonth())) {
                                        	totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceMonth()) / 12.0d;
                                        	logger.info("totalExperience Month {}===>{}",retailApplicantDetail.getTotalExperienceMonth());
                                        }
                                        logger.info("totalExperience {}===>{}",totalExperience);
                                        scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                        scoreParameterRetailRequest.setWorkingExperience_p(true);
                					}
            					}
                            } catch (Exception e) {
                                logger.error("error while getting TOTAL_JOB_EXP parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.CURRENT_JOB_EXP:
            				try {
            					if(OccupationNatureNTB.SALARIED.getId().equals(retailApplicantDetail.getEmploymentType())){
            						Double currentExperience = 0.0;
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getCurrentJobYear())){
                                   	 currentExperience += Double.valueOf(retailApplicantDetail.getCurrentJobYear());
                                   	 logger.info("CURRENT_JOB_EXP Year {}===>{}",retailApplicantDetail.getCurrentJobYear());
                                    }

                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getCurrentJobMonth())) {
                                   	 currentExperience += (retailApplicantDetail.getCurrentJobMonth() / 12);
                                   	 logger.info("CURRENT_JOB_EXP Month {}===>{}",retailApplicantDetail.getCurrentJobMonth());
                                    }
                                    scoreParameterRetailRequest.setWorkingExperienceCurrent(currentExperience);
                                    scoreParameterRetailRequest.setIsWorkingExperienceCurrent_p(true);
            					}
                         } catch (Exception e) {
                             logger.error("error while getting CURRENT_JOB_EXP parameter : {}",e);
                         }
            				break;
            			case ScoreParameter.Retail.HomeLoan.RESIDENCE_TYPE:
            				if(retailApplicantDetail.getResidenceType() != null) {
            					if(ResidenceStatusRetailMst.OWNED.getId().equals(retailApplicantDetail.getResidenceType())) {
            						if(retailApplicantDetail.getIsOwnedProp() != null && retailApplicantDetail.getIsOwnedProp()) {
            							scoreParameterRetailRequest.setResidenceType(8); //Owned (Encumbered) : No Need to Add in ENUM. This is Only For Scoring
            						}else {
            								scoreParameterRetailRequest.setResidenceType(ResidenceStatusRetailMst.OWNED.getId());
            						}
            					}else{
            							scoreParameterRetailRequest.setResidenceType(retailApplicantDetail.getResidenceType());
            					}
            					scoreParameterRetailRequest.setIsResidenceType_p(true);
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.NO_YEARS_STAY_CURR_LOC:
            				try {
            					if(retailApplicantDetail.getResidenceSinceYear() != null && retailApplicantDetail.getResidenceSinceMonth() != null) {
            						Integer year = retailApplicantDetail.getResidenceSinceYear();
    	                            Integer month = retailApplicantDetail.getResidenceSinceMonth();
    	                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	                            String s = null;
    	                            if(month < 10) {
    	                            	s = "01/0" + month + "/" + year;
    	                            }else {
    	                            	s = "01/" + month + "/" + year;
    	                            }
    	                            logger.info("Starting Date of Staying in Current Location For HL==== > {}",s);
    	                            Integer[] exactAgeFromDate = CommonUtils.getExactAgeFromDate(simpleDateFormat.parse(s));
    	                            Double noStayLoc = (((double) exactAgeFromDate[0]) + ((double)exactAgeFromDate[1] / 12.0d));
    	                            logger.info("No Of Years Staying in Current Location For HL==== > {}",noStayLoc);
    	                            scoreParameterRetailRequest.setNoOfYearCurrentLocation(noStayLoc);
    	                            scoreParameterRetailRequest.setIsNoOfYearCurrentLocation_p(true);
            					}
            				} catch (Exception e) {
	                            logger.error("error while getting NO_YEARS_STAY_CURR_LOC parameter : ", e);
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.BUREAU_SCORE:
            				scoreParameterRetailRequest.setCibilActualScore(filterBureauScoreByVersion(1, cibilResponse));
            				scoreParameterRetailRequest.setCibilActualScoreVersion2(filterBureauScoreByVersion(2, cibilResponse));
            				scoreParameterRetailRequest.setCibilScore_p(true);
            				break;
            			case ScoreParameter.Retail.HomeLoan.MARITAL_STATUS:
            				try {
                                scoreParameterRetailRequest.setMaritalStatus((retailApplicantDetail.getStatusId() != null ? retailApplicantDetail.getStatusId().longValue() : null));
                                scoreParameterRetailRequest.setMaritalStatus_p(retailApplicantDetail.getStatusId() != null);
                            } catch (Exception e) {
                                logger.error("error while getting MARITAL_STATUS parameter : ",e);
                            }
            				break;

            			case ScoreParameter.Retail.HomeLoan.EMPLOYMENT_TYPE:
            				try {
            					scoreParameterRetailRequest.setEmployementType_p(retailApplicantDetail.getEmploymentType() != null);
                				scoreParameterRetailRequest.setEmploymentType((retailApplicantDetail.getEmploymentType() != null  ? retailApplicantDetail.getEmploymentType().longValue() : null));
                            } catch (Exception e) {
                                logger.error("error while getting MARITAL_STATUS parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.EMPLOYMENT_CATEG_JOB:
            				if(retailApplicantDetail.getEmploymentType() != null && OccupationNatureNTB.SALARIED.getId().equals(retailApplicantDetail.getEmploymentType())) {
            					scoreParameterRetailRequest.setIsEmployementJobCat_p(retailApplicantDetail.getEmploymentWith() != null);
                				scoreParameterRetailRequest.setEmploymentTypeCatJob(retailApplicantDetail.getEmploymentWith());
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED:
            				if(retailApplicantDetail.getEmploymentType() != null && !OccupationNatureNTB.SALARIED.getId().equals(retailApplicantDetail.getEmploymentType())) {
            					if(OccupationNatureNTB.AGRICULTURIST.getId().equals(retailApplicantDetail.getEmploymentType())
            							|| OccupationNatureNTB.PENSIONER.getId().equals(retailApplicantDetail.getEmploymentType())
            							|| OccupationNatureNTB.OTHERS.getId().equals(retailApplicantDetail.getEmploymentType())) {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(true);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus(OccupationHL.AGRICULTURIST_PENSIONER_OTHERS.getId().longValue());
            					}else if(OccupationNatureNTB.SELF_EMPLOYED_NON_PROFESSIONAL.getId().equals(retailApplicantDetail.getEmploymentType())) {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(true);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus(OccupationHL.BUSINESSMAN.getId().longValue());
            					}else {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(retailApplicantDetail.getEmploymentWith() != null);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus((retailApplicantDetail.getEmploymentWith() != null  ? retailApplicantDetail.getEmploymentWith().longValue() : null));
            					}
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.CURRENT_EMPLOYMENT_STATUS:
            				scoreParameterRetailRequest.setIsCurrentEmploymentStatus_p(retailApplicantDetail.getEmploymentStatus() != null);
            				scoreParameterRetailRequest.setCurrentEmploymentStatus((retailApplicantDetail.getEmploymentStatus() != null  ? retailApplicantDetail.getEmploymentStatus().longValue() : null));
            				break;
            			case ScoreParameter.Retail.HomeLoan.MIN_BANKING_RELATIONSHIP:
            				scoreParameterRetailRequest.setIsMinBankingRelationship_p(true);
            				scoreParameterRetailRequest.setMinBankingRelationship(minBankRelationshipInMonths == null ? 0 : minBankRelationshipInMonths);
            				break;
            			case ScoreParameter.Retail.HomeLoan.SPOUSE_EMPLOYEMENT:
            				try {
            					if(retailApplicantDetail.getSpouseEmployment() != null) {
            						scoreParameterRetailRequest.setSpouseEmploymentDetails(retailApplicantDetail.getSpouseEmployment().longValue());
            					}else {
            						scoreParameterRetailRequest.setSpouseEmploymentDetails(3l);
            					}
            					scoreParameterRetailRequest.setSpouseEmploymentDetails_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting SPOUSE_EMPLOYEMENT parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.NO_OF_DEPENDANTS:
            				try {
                                scoreParameterRetailRequest.setNumberOfDependents(retailApplicantDetail.getNoOfDependent());
                                scoreParameterRetailRequest.setNumberOfDependents_p(retailApplicantDetail.getNoOfDependent() != null);
                            } catch (Exception e) {
                                logger.error("error while getting NO_OF_DEPENDANTS parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.DESIGNATION:
            				try {
                                scoreParameterRetailRequest.setDesignation((retailApplicantDetail.getDesignation() != null ? retailApplicantDetail.getDesignation().longValue() : null));
                                scoreParameterRetailRequest.setDesignation_p(retailApplicantDetail.getDesignation() != null);
                            } catch (Exception e) {
                                logger.error("error while getting DESIGNATION parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.EDUCATION_QUALIFICATION:
            				try {
                                scoreParameterRetailRequest.setEducationQualification((retailApplicantDetail.getEducationQualification() != null ? retailApplicantDetail.getEducationQualification().longValue() : null));
                                scoreParameterRetailRequest.setEducationQualifaction_p(retailApplicantDetail.getEducationQualification() != null);
                            } catch (Exception e) {
                                logger.error("error while getting EDUCATION_QUALIFICATION parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.NO_OF_APPLICANTS:
            				if(CommonUtils.isListNullOrEmpty(coAppIds)) {
            					scoreParameterRetailRequest.setNoOfApplicantsType(ScoreParameter.NoOfApplicants.SINGLE);
            				}else if(CommonUtils.isListNullOrEmpty(coAppITRUploadedIds)) {
            					scoreParameterRetailRequest.setNoOfApplicantsType(ScoreParameter.NoOfApplicants.JOINT);
            				}else if(!CommonUtils.isListNullOrEmpty(coAppITRUploadedIds)) {
            					scoreParameterRetailRequest.setNoOfApplicantsType(ScoreParameter.NoOfApplicants.JOINT_WHERE_CO_APPLICANT_IS_EARNING);
            				}
            				scoreParameterRetailRequest.setIsNoOfApplicantsType_p(true);
            				break;
            			case ScoreParameter.Retail.HomeLoan.ANNUAL_INCOME:
            				//Already Set NMI and GMI and EMI Above Before Switch Starts
            				break;
            			case ScoreParameter.Retail.HomeLoan.EMI_NMI_RATIO:
            				//Already Set NMI and GMI and EMI Above Before Switch Starts
            				scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
            				break;
            			case ScoreParameter.Retail.HomeLoan.EMI_NMI:
            				scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
            				//Already Set NMI and GMI Above Before Switch Starts
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVAILABLE_INCOME:
            				try {
            					logger.info("netMonthlyIncome===>{}===grossAnnualIncome===>{}== For ApplicationId ==>{}===>FpProductId===>{}",netMonthlyIncome,grossMonthlyIncome,applicationId,fpProductId);
								scoreParameterRetailRequest.setFoir(scoringRequestLoans.getFoir());
								scoreParameterRetailRequest.setIsAvailableIncome_p(true);

							} catch (Exception e1) {
								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
							}
            				break;
            			case ScoreParameter.Retail.HomeLoan.TENURE:
            				if(scoringRequestLoans.getEligibleTenure() != null) {
								scoreParameterRetailRequest.setEligibleTenure(scoringRequestLoans.getEligibleTenure());
								scoreParameterRetailRequest.setIsEligibleTenure_p(true);
							}else {
								logger.warn("Eligible Tenure is not Set in AVAILABLE_INCOME TENURE==== > {}",scoringRequestLoans.getEligibleTenure());
							}
            				break;
            			case ScoreParameter.Retail.HomeLoan.ADDI_INCOME_SPOUSE:
	            				if(retailApplicantDetail.getAnnualIncomeOfSpouse() != null) {
	            					scoreParameterRetailRequest.setSpouseIncome(retailApplicantDetail.getAnnualIncomeOfSpouse());
	            					scoreParameterRetailRequest.setIsSpouseIncome_p(true);
	            				}else {
	            					scoreParameterRetailRequest.setSpouseIncome(0.0d);
	            					scoreParameterRetailRequest.setIsSpouseIncome_p(true);
	            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.MON_INCOME_DEPENDANT:
            				scoreParameterRetailRequest.setNoOfDependants(retailApplicantDetail.getNoOfDependent());
            				scoreParameterRetailRequest.setIsMonIncomePerDep_p(true);
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVG_INCREASE_INCOME_REPORT_3_YEARS:
            				logger.info("Income List From ITR for HL == >{}==>ApplicationId==>{}",incomeOfItrOf3Years,applicationId);
            				if(!CommonUtils.isListNullOrEmpty(incomeOfItrOf3Years)) {
            					if(incomeOfItrOf3Years.size() == 3) { //as if now considering 3 Years Compulsory
            						Double itrLastToLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 1);
            						if(itrLastToLastToLastYearIncome == null ) {
            							itrLastToLastToLastYearIncome = 1.0d;
            						}
                					Double itrLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 2);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 1.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 3);

                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 0.0;
                					}
            						Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
            						logger.info("Final Income After Calculation for HL == >{}==>ApplicationId==>{}",finalIncome,applicationId);
            						if(Double.isFinite(finalIncome)) {
            							scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
                						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            						}
            					}else if(incomeOfItrOf3Years.size() == 2) { //as if now considering 2 Years Compulsory
                					Double itrLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 1);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 1.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 2);
                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 1.0;
                					}
            						Double finalIncome =  (((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100);
            						logger.info("Final Income After Calculation for HL == >{}==>ApplicationId==>{}",finalIncome,applicationId);
            						if(Double.isFinite(finalIncome)) {
            							scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
                						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            						}

            					}else if(incomeOfItrOf3Years.size() == 1) { //as if now considering 1 Years Compulsory
            						logger.info("Final Income After Calculation for HL as Only one year ITR Found == >{}==>ApplicationId==>{}",0.0d,applicationId);
            						scoreParameterRetailRequest.setIncomeFromItr(0.0d);
            						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            					}
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.REPAYMENT_PERIOD:
            				scoreParameterRetailRequest.setRepaymentPeriod(retailApplicantDetail.getRepaymentMode());
        					scoreParameterRetailRequest.setIsRepaymentPeriod_p(retailApplicantDetail.getRepaymentMode() != null);
            				break;
            			case ScoreParameter.Retail.HomeLoan.AGE_PROPERTY:
	            				if(primaryHomLoanDetail.getOldPropYear() != null) {
	            					scoreParameterRetailRequest.setAgeOfProperty(primaryHomLoanDetail.getOldPropYear());
	            					scoreParameterRetailRequest.setIsAgeOfProperty_p(true);
	            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVG_DEPOS_LAST_6_MONTH:
            				Double value = 0.0d;
            				for(Data bankStatementData : bankStatementDatas) {
            					if(bankStatementData != null && bankStatementData.getSummaryInfo() != null && bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails() != null  && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit())) {
                					value = value + Double.valueOf(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit()); // / 6
           					 	}
            				}
            				logger.info("AVG_DEPOS_LAST_6_MONTH value===>{}",value);
            				scoreParameterRetailRequest.setAvgOfTotalCheDepsitLast6Month(value);
   					 		scoreParameterRetailRequest.setIsAvgOfTotalCheDepsitLast6Month_p(true);
            				break;
            			case ScoreParameter.Retail.HomeLoan.CHECQUE_BOUNSE_LAST_1_MONTH:
            				 try {
            					 Double chequelast1Month = 0.0d;
            					 for(Data bankStatementData : bankStatementDatas) {
            						 if(bankStatementData.getCheckBounceForLast1Month() != null) {
            							 chequelast1Month = chequelast1Month + bankStatementData.getCheckBounceForLast1Month().doubleValue();
                					 }
            					 }
            					 scoreParameterRetailRequest.setChequeBouncelast1Month(chequelast1Month);
            					 scoreParameterRetailRequest.setIsChequeBounceLast1Month_p(true);
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 1 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.HomeLoan.CHECQUE_BOUNSE_LAST_6_MONTH:
            				 try {
            					 Double chequelast6Month = 0.0d;
            					 for(Data bankStatementData : bankStatementDatas) {
            						 if(!CommonUtils.isObjectNullOrEmpty(bankStatementData.getCheckBounceForLast6Month())) {
            							 chequelast6Month = chequelast6Month + bankStatementData.getCheckBounceForLast6Month().doubleValue();
                                     }
            					 }
            				   scoreParameterRetailRequest.setChequeBounce(chequelast6Month);
                               scoreParameterRetailRequest.setChequeBounce_p(true);
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 6 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.HomeLoan.DPD:
            				try {
            					Integer maxDPD = 0;
            					if(!CommonUtils.isListNullOrEmpty(dpds)) {
            						maxDPD = Collections.max(dpds);
            					}
                                logger.info("Max DPD===>{}",maxDPD);
                                if (!CommonUtils.isObjectNullOrEmpty(maxDPD)) {
                                    scoreParameterRetailRequest.setDpd(maxDPD.doubleValue());
                                } else {
                                    scoreParameterRetailRequest.setDpd(0.0);
                                }
                                scoreParameterRetailRequest.setDPD_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting DPD parameter from CIBIL client : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.LTV:
            				if(primaryHomLoanDetail.getMarketValProp() != null) {
                				try {
									if(scoringRequestLoans.getElAmountOnAverageScoring() != null) {
										scoreParameterRetailRequest.setLtv(scoringRequestLoans.getElAmountOnAverageScoring());
										scoreParameterRetailRequest.setMarketValueOfProp(primaryHomLoanDetail.getMarketValProp());
										scoreParameterRetailRequest.setIsLTV_p(true);
									}else {
										logger.warn("Eligible Loan Amount Based on Income is not Set in LTV==== > {}",scoringRequestLoans.getElAmountOnAverageScoring());
									}
    							} catch (Exception e1) {
    								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
    							}
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.APPLICANT_NW_TO_LOAN_AMOUNT:
            				if(retailApplicantDetail.getNetworth() != null) {
                				try {
									if(scoringRequestLoans.getElAmountOnAverageScoring() != null) {
										scoreParameterRetailRequest.setIsNetWorth_p(true);
										scoreParameterRetailRequest.setNetWorth((retailApplicantDetail.getNetworth() / scoringRequestLoans.getElAmountOnAverageScoring()) * 100);
									}else {
										logger.warn("Eligible Loan Amount Based on Income is not Set in APPLICANT_NW_TO_LOAN_AMOUNT==== > {}",scoringRequestLoans.getElAmountOnAverageScoring());
									}
    							} catch (Exception e1) {
    								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
    							}
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.LOAN_PURPOSE:
            				if(retailApplicantDetail.getLoanPurpose() != null) {
            					scoreParameterRetailRequest.setIsLoanPurpose_p(true);
            					scoreParameterRetailRequest.setLoanPurpose(retailApplicantDetail.getLoanPurpose());
            					scoreParameterRetailRequest.setLoanPurposeQueType(retailApplicantDetail.getLoanPurposeQueType());
            					scoreParameterRetailRequest.setLoanPurposeQueValue(retailApplicantDetail.getLoanPurposeQueValue());
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.INCOME_PROOF:
	            				if(isItrMannualFilled == null || !isItrMannualFilled) {
	            					scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.IT_RETURN_AND_BANK_STATEMENT);
	            				}else {
	            					scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.BANK_STATEMENT);
	            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVG_EOD_BALANCE:
            				Double totalEODBalAvg = 0.0d;
            				Double totalCredit = 0.0d;
            				scoreParameterRetailRequest.setAvgEodBalToToalDep(0.0d);
            				for(Data bankStatementData : bankStatementDatas) {
            					if(bankStatementData.getSummaryInfo() != null) {
                					if(!CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg()) && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalCredit())) {
                						totalEODBalAvg = totalEODBalAvg + Double.parseDouble(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg());
                						totalCredit = totalCredit + Double.parseDouble(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalCredit());
                					}
                				}
            				}
            				if(totalEODBalAvg > 0 && totalCredit > 0) {
            					scoreParameterRetailRequest.setAvgEodBalToToalDep((totalEODBalAvg / totalCredit));            					
            				}
    						scoreParameterRetailRequest.setIsAvgEodBalToToalDep_p(true);
            				break;
            			case ScoreParameter.Retail.HomeLoan.LOAN_TO_INCOME_RATIO:
        					scoreParameterRetailRequest.setLoanToIncomeRatio_p(true);
            				break;
            			case ScoreParameter.Retail.HomeLoan.INCOME_TO_INSTALLMENT_RATIO:
        					scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
            				break;
                            default:
                                break;

                        }
                    }
//                    logger.info(MSG_SCORE_PARAMETER + scoreParameterRetailRequest.toString());
                    logger.info("----------------------------END-------------------------------------------");

                    Gson g = new GsonBuilder().serializeSpecialFloatingPointValues().create();
                    ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                    try {
                        scoringRequestDetail.setApplicationId(applicationId);
                        scoringRequestDetail.setRequest(g.toJson(scoreParameterRetailRequest));
                        scoringRequestDetail.setCreatedDate(new Date());
                        scoringRequestDetail.setIsActive(true);
                        scoringRequestDetailRepository.save(scoringRequestDetail);

                        logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                    } catch (Exception e) {
                        logger.error(CommonUtils.EXCEPTION,e);
                    }
                }
            scoringRequest.setScoreParameterRetailRequest(scoreParameterRetailRequest);
            scoringRequestList.add(scoringRequest);
        }

        try {
            ScoringResponse calculateScoreList = scoringClient.calculateScoreList(scoringRequestList);
            logger.info("Scoring Response For HOME Loan============>{}",calculateScoreList);
            logger.info("Scoring Response Status For HOME Loan ============>{}",calculateScoreList != null ? calculateScoreList.getStatus() : calculateScoreList);
            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED,applicationId);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }





    @Override
	public ResponseEntity<LoansResponse> calculateRetailHomeLoanScoringListForCoApplicant(List<ScoringRequestLoans> scoringRequestLoansList) {
    	CoApplicantDetail coApplicantDetail = null;
        Long orgId = null;
        Long applicationId = null;
        Long coApplicantId = null;
        ScoringRequestLoans scoringRequestLoansReq = null;
        Double netMonthlyIncome = 0.0d;
        Double grossMonthlyIncome = 0.0d;
        List<Data> coApplicantBankStatementDatas = Collections.emptyList();
        Double totalEMI = 0.0;
        List<CibilScoreLogRequest> cibilResponse = null;
        List<Integer> dpds = Collections.emptyList();
        Boolean itrSkippedForCoApp = null;
        Boolean itrMannualForCoApp = null;
        List<Double> incomeOfItrOf3YearsCoApplicant = null;
//        Double loanAmount = 0.0d;
        if(!CommonUtils.isListNullOrEmpty(scoringRequestLoansList)) {
        	scoringRequestLoansReq = scoringRequestLoansList.get(0);
        	applicationId = scoringRequestLoansReq.getApplicationId();
        	coApplicantId = scoringRequestLoansReq.getCoApplicantId();
        	logger.info("Calculating Scoring For CoApplicant and ApplicationId============{}==================>{}",coApplicantId,applicationId);
        	coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(coApplicantId, true);
        	if (CommonUtils.isObjectNullOrEmpty(coApplicantDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                return new ResponseEntity<>(new LoansResponse(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_HOME_LOAN_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        	logger.info("coApplicantDetail.getEmploymentType()===============>{}",coApplicantDetail.getEmploymentType());

        	CibilRequest cibilRequest = new CibilRequest();
            cibilRequest.setPan(coApplicantDetail.getPan());
            cibilRequest.setApplicationId(scoringRequestLoansReq.getApplicationId());
            try {
            	cibilResponse = cibilClient.getSoftpingScores(scoringRequestLoansReq.getApplicationId(), coApplicantDetail.getPan());
            	CibilResponse cibilResponseDpdCoApp = cibilClient.getDPDLastXMonth(applicationId,coApplicantDetail.getPan());
            	 if(!CommonUtils.isObjectNullOrEmpty(cibilResponseDpdCoApp) && !CommonUtils.isObjectListNull(cibilResponseDpdCoApp.getListData())){
     				List cibilResponseList = cibilResponseDpdCoApp.getListData();
     				dpds = new ArrayList<>(cibilResponseList.size());
     				for (int i = 0; i < cibilResponseList.size(); i++) {
     					String cibilResponseObj = cibilResponseList.get(i).toString();
     					if(cibilResponseObj.contains("|")){
     						String[] cibilDpdVal = cibilResponseObj.split("\\|");
     						if(!CommonUtils.isObjectNullOrEmpty(cibilDpdVal[1])) {
     							dpds.add(Integer.parseInt(cibilDpdVal[1]));
     						}
     					}else {
     						dpds.add(Integer.parseInt(cibilResponseList.get(i).toString()));
     					}
     				}
     			}
            }catch(Exception e) {
            	logger.error("Error in Getting CIBIL infor like DPD and Score == >{}",e);
            }

            List<CoApplicantEligibilityRequest> monthlyIncomeForCoApplicant = null;
            CoApplicantEligibilityRequest incomeFromEligibility = null;
			try {
				List<CoApplicantEligibilityRequest> coAppElRequest = new ArrayList<>(1);
				CoApplicantEligibilityRequest applicantEligibilityRequest = new CoApplicantEligibilityRequest();
				applicantEligibilityRequest.setId(scoringRequestLoansReq.getCoApplicantId());
				applicantEligibilityRequest.setIsConsiderIncome(scoringRequestLoansReq.getIsConsiderCoAppIncome());
				coAppElRequest.add(applicantEligibilityRequest);
				monthlyIncomeForCoApplicant = eligibilityClient.getMonthlyIncomeForCoApplicant(coAppElRequest, applicationId);
			} catch (EligibilityExceptions e) {
				logger.error("Error while Getting MonthlyIncome Details == >{}",e);
			}
			if(!CommonUtils.isListNullOrEmpty(monthlyIncomeForCoApplicant)) {
				try {
					incomeFromEligibility = monthlyIncomeForCoApplicant.get(0);
					logger.info("incomeFromEligibility===============>{}",incomeFromEligibility);
				}catch(Exception e) {
					logger.error("Error while Casting Object of CoApplicant Income====>{}",e);
				}
				if(!CommonUtils.isObjectNullOrEmpty(incomeFromEligibility)) {
					netMonthlyIncome = Double.valueOf(incomeFromEligibility.getNetMonthlyIncome());
                    grossMonthlyIncome = Double.valueOf(incomeFromEligibility.getGrossMonthlyIncome());
                    logger.info("Net Monthly Income For ApplicationId and CoApplicant Id======{}======>{}====>{}",applicationId,netMonthlyIncome,coApplicantId);
                    logger.info("Gross Annual Income For ApplicationId and CoApplicant Id======{}======>{}====>{}",applicationId,grossMonthlyIncome,coApplicantId);
				}
			}else {
				logger.info("Something is NULL From EligibilityResponse for CoApplicant===============>{}",monthlyIncomeForCoApplicant);
			}
            try {
                 ReportRequest reportRequest = new ReportRequest();
                 reportRequest.setApplicationId(applicationId);
                 reportRequest.setCoApplicantId(coApplicantId);
                 AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
                 if(analyzerResponse != null && analyzerResponse.getData() != null) {
                	 coApplicantBankStatementDatas = new ArrayList<>(5);
                     for(Object object : (List)analyzerResponse.getData()) {
                    	 Data dataBs = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) object, Data.class);
     					if(dataBs != null) {
     						if(EligibilityUtils.isObjectNullOrEmpty(dataBs.getCoAppId())) {
     							continue;
     						}
     						coApplicantBankStatementDatas.add(dataBs);
     					}
                     }
                 }


            }catch(Exception e) {
            	logger.error("Error while getting Bank Statement Details");
            }
            totalEMI = financialArrangementDetailsService.getTotalEmiByApplicationIdSoftPing(coApplicantId,applicationId);

            //ITR and bank Statement Checking
            itrSkippedForCoApp = loanRepository.isITRSkippedForCoApp(applicationId, coApplicantId);
            itrMannualForCoApp = loanRepository.isITRMannualForCoApp(applicationId, coApplicantId);
            incomeOfItrOf3YearsCoApplicant = loanRepository.getIncomeOfItrOf3YearsOfCoApplicant(coApplicantId);
        }
        List<ScoringRequest> scoringRequestList=new ArrayList<>(scoringRequestLoansList.size());
        Integer minBankRelationshipInMonths = null;
        for(ScoringRequestLoans scoringRequestLoans : scoringRequestLoansList)
        {
        	ScoreParameterRetailRequest scoreParameterRetailRequest = null;
            Long scoreModelId = scoringRequestLoans.getScoringModelCoAppId();
            if(scoreModelId == null) {
            	scoreModelId = scoringRequestLoans.getScoringModelId();
            }
            logger.info("Scoring model Id For CoApp===>{}",scoreModelId);
            Long fpProductId = scoringRequestLoans.getFpProductId();
            logger.info("Fp Product Id For CoApp===>{}",fpProductId);
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.RETAIL_HOME_LOAN);
            scoringRequest.setEmi(scoringRequestLoans.getEmi());

            if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
            } else {
                scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
            }

            orgId = scoringRequestLoans.getOrgId();
            if(orgId != null) {
            	BankList bankEnum = BankList.fromOrgId(orgId.toString());
            	if(bankEnum != null) {
            		logger.info("Bank Name====>{}==>Application Id===>{}===> Fp Product Id===>{}",bankEnum.getName(),applicationId,fpProductId);
            		minBankRelationshipInMonths = bankingRelationlRepository.getMinRelationshipInMonthByApplicationAndOrgNameAndCoApplicantId(applicationId, bankEnum.getName(),coApplicantId);
            	}
            	logger.info("Min Banking Relationship in Month CoApplicant === >{}",minBankRelationshipInMonths);
            }

            ///////// End  Getting Old Request ///////

            if (CommonUtils.isObjectNullOrEmpty(scoreParameterRetailRequest)) {
                scoreParameterRetailRequest= new ScoreParameterRetailRequest();
                scoringRequest.setLoanPurposeModelId(scoringRequestLoans.getLoanPurposeModelId());
                scoreParameterRetailRequest.setFoir(scoringRequestLoans.getFoir());
                scoreParameterRetailRequest.setNmi(netMonthlyIncome);
				scoreParameterRetailRequest.setGmi(grossMonthlyIncome);
				scoreParameterRetailRequest.setEmi(scoringRequestLoans.getEmi());
				scoreParameterRetailRequest.setElAmountOnAverageScoring(scoringRequestLoans.getElAmountOnAverageScoring());
				scoreParameterRetailRequest.setIsConsiderCoAppIncome(scoringRequestLoans.getIsConsiderCoAppIncome());
				logger.info("Is Income Consider For CoApplicant============>{}=======>{}",scoringRequestLoans.getIsConsiderCoAppIncome(), coApplicantId);
				logger.info("Result of Average Eligibility Call For CoApplicant===============>{}======>{}========>{}========================{}",scoringRequestLoans.getElAmountOnAverageScoring(),applicationId,fpProductId,coApplicantId);
				logger.info("FOIR For CoApplicant===============>{}======>{}========>{}========================{}",scoringRequestLoans.getFoir(),applicationId,fpProductId,applicationId,fpProductId,coApplicantId);
                logger.info("----------------------------START RETAIL HL ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

                // GET SCORE RETAIL PERSONAL LOAN PARAMETERS
                if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                    // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                	List<ModelParameterResponse> listFieldByBusinessTypeIdForCoApplicant = Collections.emptyList();
                    try {
                    	listFieldByBusinessTypeIdForCoApplicant = scoringClient.listFieldByBusinessTypeIdForCoApplicant(scoringRequest);
                    } catch (Exception e) {
                        logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                    }

                    for (ModelParameterResponse modelParameterResponse : listFieldByBusinessTypeIdForCoApplicant) {
                        FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                        fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                        fundSeekerInputRequest.setName(modelParameterResponse.getName());
                        logger.info("Parameter For CoApplicant==>{}",modelParameterResponse.getName());

//                        scoreParameterRetailRequest.setLoanAmtProposed(scoringRequestLoans.getElAmountOnAverageScoring());
                        switch (modelParameterResponse.getName()) {
                        case ScoreParameter.Retail.HomeLoan.AGE:
                        	   try {
                                   if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate())) {
                                	   Integer exactAge [] = CommonUtils.getExactAgeFromDate(coApplicantDetail.getBirthDate());
                                	   Double age = (((double) exactAge[0]) + ((double)exactAge[1] / 12.0d));
                                	   logger.info("Age With Point == {}",age);
                                       scoreParameterRetailRequest.setAge(age);
                                       scoreParameterRetailRequest.setAge_p(true);
                                   }
                               } catch (Exception e) {
                                   logger.error("error while getting AGE_HL parameter : ",e);
                               }
                        	break;
            			case ScoreParameter.Retail.HomeLoan.TOTAL_WORK_EXP:
            				try {
            					Double totalExperience = 0.0;
            					if(coApplicantDetail.getEmploymentType() != null) {
            						scoreParameterRetailRequest.setWorkingExperience_p(true);
            						if(!OccupationNatureNTB.SALARIED.getId().equals(coApplicantDetail.getEmploymentType())){
            							if(coApplicantDetail.getBusinessStartDate() != null) {
                        					logger.info("coApplicantDetail.getBusinessStartDate() For HL==== > {}",coApplicantDetail.getBusinessStartDate());
                        					Integer[] diifFromDate = CommonUtils.getExactAgeFromDate(coApplicantDetail.getBusinessStartDate());
                        					logger.info("Year For HL CoApplicant====ApplicationId===>{}=====>{}",diifFromDate[0],applicationId);
                        					logger.info("Month For HL CoApplicant====ApplicationId===>{}=====>{}",diifFromDate[1],applicationId);
                        					totalExperience = (((double) diifFromDate[0]) + ((double)diifFromDate[1] / 12.0d));
                        					logger.info("Total Business Experiance For HL==== > {}",totalExperience);
                                            scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                            scoreParameterRetailRequest.setWorkingExperience_p(true);
                        				}
                					}else {
                						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceYear())) {
                                        	totalExperience += Double.valueOf(coApplicantDetail.getTotalExperienceYear());
                                        	logger.info("totalExperience Year {}===>{}",coApplicantDetail.getTotalExperienceYear());
                                        }
                                        if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceMonth())) {
                                        	totalExperience += Double.valueOf(coApplicantDetail.getTotalExperienceMonth()) / 12.0d;
                                        	logger.info("totalExperience Month {}===>{}",coApplicantDetail.getTotalExperienceMonth());
                                        }
                                        logger.info("totalExperience {}===>{}",totalExperience);
                                        scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                					}
            					}
                            } catch (Exception e) {
                                logger.error("error while getting TOTAL_JOB_EXP parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.CURRENT_JOB_EXP:
            				try {
            					if(OccupationNatureNTB.SALARIED.getId().equals(coApplicantDetail.getEmploymentType())){
            						Double currentExperience = 0.0;
                                    if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobYear())){
                                   	 currentExperience += Double.valueOf(coApplicantDetail.getCurrentJobYear());
                                   	 logger.info("CURRENT_JOB_EXP Year {}===>{}",coApplicantDetail.getCurrentJobYear());
                                    }

                                    if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobMonth())) {
                                   	 currentExperience += (coApplicantDetail.getCurrentJobMonth() / 12);
                                   	 logger.info("CURRENT_JOB_EXP Month {}===>{}",coApplicantDetail.getCurrentJobMonth());
                                    }

                                    scoreParameterRetailRequest.setWorkingExperienceCurrent(currentExperience);
                                    scoreParameterRetailRequest.setIsWorkingExperienceCurrent_p(true);
            					}

                         } catch (Exception e) {
                             logger.error("error while getting CURRENT_JOB_EXP parameter : {}",e);
                         }
            				break;
            			case ScoreParameter.Retail.HomeLoan.RESIDENCE_TYPE:
            				if(coApplicantDetail.getResidenceType() != null) {
            					if(ResidenceStatusRetailMst.OWNED.getId().equals(coApplicantDetail.getResidenceType())) {
            						if(coApplicantDetail.getIsOwnedProp() != null && coApplicantDetail.getIsOwnedProp()) {
            							scoreParameterRetailRequest.setResidenceType(8); //Owned (Encumbered) : No Need to Add in ENUM. This is Only For Scoring
            						}else {
            								scoreParameterRetailRequest.setResidenceType(ResidenceStatusRetailMst.OWNED.getId());
            						}
            					}else{
            							scoreParameterRetailRequest.setResidenceType(coApplicantDetail.getResidenceType());
            					}
            					scoreParameterRetailRequest.setIsResidenceType_p(true);
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.NO_YEARS_STAY_CURR_LOC:
            				try {
            					if(coApplicantDetail.getResidenceSinceYear() != null && coApplicantDetail.getResidenceSinceMonth() != null) {
            						Integer year = coApplicantDetail.getResidenceSinceYear();
    	                            Integer month = coApplicantDetail.getResidenceSinceMonth();
    	                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	                            String s = null;
    	                            if(month < 10) {
    	                            	s = "01/0" + month + "/" + year;
    	                            }else {
    	                            	s = "01/" + month + "/" + year;
    	                            }
    	                            logger.info("Starting Date of Staying in Current Location For HL CoApplicant==== > {}",s);
    	                            Integer[] exactAgeFromDate = CommonUtils.getExactAgeFromDate(simpleDateFormat.parse(s));
    	                            Double noStayLoc = (((double) exactAgeFromDate[0]) + ((double)exactAgeFromDate[1] / 12.0d));
    	                            logger.info("No Of Years Staying in Current Location For HL==== > {}",noStayLoc);
    	                            scoreParameterRetailRequest.setNoOfYearCurrentLocation(noStayLoc);
    	                            scoreParameterRetailRequest.setIsNoOfYearCurrentLocation_p(true);
            					}
            				} catch (Exception e) {
	                            logger.error("error while getting NO_YEARS_STAY_CURR_LOC parameter : ", e);
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.BUREAU_SCORE:
            				scoreParameterRetailRequest.setCibilActualScore(filterBureauScoreByVersion(1, cibilResponse));
            				scoreParameterRetailRequest.setCibilActualScoreVersion2(filterBureauScoreByVersion(2, cibilResponse));
            				scoreParameterRetailRequest.setCibilScore_p(true);
            				break;
            			case ScoreParameter.Retail.HomeLoan.MARITAL_STATUS:
            				try {
                                scoreParameterRetailRequest.setMaritalStatus((coApplicantDetail.getStatusId() != null ? coApplicantDetail.getStatusId().longValue() : null));
                                scoreParameterRetailRequest.setMaritalStatus_p(coApplicantDetail.getStatusId() != null);
                            } catch (Exception e) {
                                logger.error("error while getting MARITAL_STATUS parameter : ",e);
                            }
            				break;

            			case ScoreParameter.Retail.HomeLoan.EMPLOYMENT_TYPE:
            				try {
            					scoreParameterRetailRequest.setEmployementType_p(coApplicantDetail.getEmploymentType() != null);
                				scoreParameterRetailRequest.setEmploymentType((coApplicantDetail.getEmploymentType() != null  ? coApplicantDetail.getEmploymentType().longValue() : null));
                            } catch (Exception e) {
                                logger.error("error while getting MARITAL_STATUS parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.EMPLOYMENT_CATEG_JOB:
            				if(coApplicantDetail.getEmploymentType() != null && OccupationNatureNTB.SALARIED.getId().equals(coApplicantDetail.getEmploymentType())) {
            					scoreParameterRetailRequest.setIsEmployementJobCat_p(coApplicantDetail.getEmploymentWith() != null);
                				scoreParameterRetailRequest.setEmploymentTypeCatJob(coApplicantDetail.getEmploymentWith());
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED:
            				if(coApplicantDetail.getEmploymentType() != null && !OccupationNatureNTB.SALARIED.getId().equals(coApplicantDetail.getEmploymentType())) {
            					if(OccupationNatureNTB.AGRICULTURIST.getId().equals(coApplicantDetail.getEmploymentType())
            							|| OccupationNatureNTB.PENSIONER.getId().equals(coApplicantDetail.getEmploymentType())
            							|| OccupationNatureNTB.OTHERS.getId().equals(coApplicantDetail.getEmploymentType())) {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(true);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus(OccupationHL.AGRICULTURIST_PENSIONER_OTHERS.getId().longValue());
            					}else if(OccupationNatureNTB.SELF_EMPLOYED_NON_PROFESSIONAL.getId().equals(coApplicantDetail.getEmploymentType())) {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(true);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus(OccupationHL.BUSINESSMAN.getId().longValue());
            					}else {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(coApplicantDetail.getEmploymentWith() != null);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus((coApplicantDetail.getEmploymentWith() != null  ? coApplicantDetail.getEmploymentWith().longValue() : null));
            					}
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.CURRENT_EMPLOYMENT_STATUS:
            				scoreParameterRetailRequest.setIsCurrentEmploymentStatus_p(coApplicantDetail.getEmploymentStatus() != null);
            				scoreParameterRetailRequest.setCurrentEmploymentStatus((coApplicantDetail.getEmploymentStatus() != null  ? coApplicantDetail.getEmploymentStatus().longValue() : null));
            				break;
            			case ScoreParameter.Retail.HomeLoan.MIN_BANKING_RELATIONSHIP:
            				//Not Available in Sheet Document
            				scoreParameterRetailRequest.setIsMinBankingRelationship_p(true);
            				scoreParameterRetailRequest.setMinBankingRelationship(minBankRelationshipInMonths == null ? 0 : minBankRelationshipInMonths);
            				break;
            			case ScoreParameter.Retail.HomeLoan.SPOUSE_EMPLOYEMENT:
            				try {
            					if(coApplicantDetail.getSpouseEmployment() != null) {
            						scoreParameterRetailRequest.setSpouseEmploymentDetails(coApplicantDetail.getSpouseEmployment().longValue());
            					}else {
            						scoreParameterRetailRequest.setSpouseEmploymentDetails(3l);
            					}
            					scoreParameterRetailRequest.setSpouseEmploymentDetails_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting SPOUSE_EMPLOYEMENT parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.NO_OF_DEPENDANTS:
            				try {
                                scoreParameterRetailRequest.setNumberOfDependents(coApplicantDetail.getNoDependent());
                                scoreParameterRetailRequest.setNumberOfDependents_p(coApplicantDetail.getNoDependent() != null);
                            } catch (Exception e) {
                                logger.error("error while getting NO_OF_DEPENDANTS parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.DESIGNATION:
            				try {
                                scoreParameterRetailRequest.setDesignation((coApplicantDetail.getDesignation() != null ? coApplicantDetail.getDesignation().longValue() : null));
                                scoreParameterRetailRequest.setDesignation_p(coApplicantDetail.getDesignation() != null);
                            } catch (Exception e) {
                                logger.error("error while getting DESIGNATION parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.EDUCATION_QUALIFICATION:
            				try {
                                scoreParameterRetailRequest.setEducationQualification((coApplicantDetail.getEducationQualification() != null ? coApplicantDetail.getEducationQualification().longValue() : null));
                                scoreParameterRetailRequest.setEducationQualifaction_p(coApplicantDetail.getEducationQualification() != null);
                            } catch (Exception e) {
                                logger.error("error while getting EDUCATION_QUALIFICATION parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.ANNUAL_INCOME:
            				//Already Set NMI and GMI Above Before Switch Starts
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVAILABLE_INCOME:
            				try {
            					logger.info("netMonthlyIncome===>{}===grossAnnualIncome===>{}== For ApplicationId ==>{}===>FpProductId===>{}",netMonthlyIncome,grossMonthlyIncome,applicationId,fpProductId);
            					logger.info("Foir For AVAILABLE_INCOME for CoApplicant============>{}",scoringRequestLoans.getFoir());
            					scoreParameterRetailRequest.setFoir(scoringRequestLoans.getFoir());
								scoreParameterRetailRequest.setIsAvailableIncome_p(true);
							} catch (Exception e1) {
								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
							}
            				break;
            			case ScoreParameter.Retail.HomeLoan.ADDI_INCOME_SPOUSE:
            				//Not Available in Sheet Document
	            				if(coApplicantDetail.getAnnualIncomeOfSpouse() != null) {
	            					scoreParameterRetailRequest.setSpouseIncome(coApplicantDetail.getAnnualIncomeOfSpouse());
	            					scoreParameterRetailRequest.setIsSpouseIncome_p(true);
	            				}else {
	            					scoreParameterRetailRequest.setSpouseIncome(0.0d);
	            					scoreParameterRetailRequest.setIsSpouseIncome_p(true);
	            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.MON_INCOME_DEPENDANT:
            				scoreParameterRetailRequest.setNoOfDependants(coApplicantDetail.getNoDependent());
            				scoreParameterRetailRequest.setIsMonIncomePerDep_p(true);
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVG_INCREASE_INCOME_REPORT_3_YEARS:
            				logger.info("Income List From ITR for HL For CoApplicant == >{}",incomeOfItrOf3YearsCoApplicant);
            				if(!CommonUtils.isListNullOrEmpty(incomeOfItrOf3YearsCoApplicant)) {
            					if(incomeOfItrOf3YearsCoApplicant.size() == 3) { //as if now considering 3 Years Compulsory
            						Double itrLastToLastToLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 1);
            						if(itrLastToLastToLastYearIncome == null ) {
            							itrLastToLastToLastYearIncome = 1.0d;
            						}
                					Double itrLastToLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 2);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 1.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 3);

                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 0.0;
                					}
            						Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
            						logger.info("Final Income After Calculation for HL == >{}",finalIncome);
            						if(Double.isFinite(finalIncome)) {
            							scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
                						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            						}
            					}else if(incomeOfItrOf3YearsCoApplicant.size() == 2) { //as if now considering 2 Years Compulsory
            						Double itrLastToLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 1);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 1.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 2);
                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 1.0;
                					}
            						Double finalIncome =  (((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100);
            						logger.info("Final Income After Calculation for HL == >{}==>ApplicationId==>{}",finalIncome,applicationId);
            						if(Double.isFinite(finalIncome)) {
            							scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
                						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            						}
            					}else if(incomeOfItrOf3YearsCoApplicant.size() == 1) { //as if now considering 1 Years Compulsory
            						logger.info("Final Income After Calculation for HL CoApplicant == >{} ==> For coApplicantId===>{}",0.0d,coApplicantId);
        							scoreParameterRetailRequest.setIncomeFromItr(0.0d);
            						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            					}
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVG_DEPOS_LAST_6_MONTH:
            				Double value = 0.0d;
            				for(Data bankStatementData : coApplicantBankStatementDatas) {
            					if(bankStatementData != null && bankStatementData.getSummaryInfo() != null && bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails() != null  && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit())) {
                					value = value + Double.valueOf(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit()); // / 6
           					 	}
            				}
            				logger.info("AVG_DEPOS_LAST_6_MONTH value===>{}",value);
            				scoreParameterRetailRequest.setAvgOfTotalCheDepsitLast6Month(value);
   					 		scoreParameterRetailRequest.setIsAvgOfTotalCheDepsitLast6Month_p(true);
            				break;
            			case ScoreParameter.Retail.HomeLoan.CHECQUE_BOUNSE_LAST_1_MONTH:
            				 try {
            					 Double chequelast1Month = 0.0d;
            					 for(Data bankStatementData : coApplicantBankStatementDatas) {
            						 if(bankStatementData.getCheckBounceForLast1Month() != null) {
            							 chequelast1Month = chequelast1Month + bankStatementData.getCheckBounceForLast1Month().doubleValue();
                					 }
            					 }
            					 scoreParameterRetailRequest.setChequeBouncelast1Month(chequelast1Month);
            					 scoreParameterRetailRequest.setIsChequeBounceLast1Month_p(true);
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 1 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.HomeLoan.CHECQUE_BOUNSE_LAST_6_MONTH:
            				 try {
            					 Double chequelast6Month = 0.0d;
            					 for(Data bankStatementData : coApplicantBankStatementDatas) {
            						 if(!CommonUtils.isObjectNullOrEmpty(bankStatementData.getCheckBounceForLast6Month())) {
            							 chequelast6Month = chequelast6Month + bankStatementData.getCheckBounceForLast6Month().doubleValue();
                                     }
            					 }
            				   scoreParameterRetailRequest.setChequeBounce(chequelast6Month);
                               scoreParameterRetailRequest.setChequeBounce_p(true);
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 6 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.HomeLoan.DPD:
            				try {
            					Integer maxDPD = 0;
            					if(!CommonUtils.isListNullOrEmpty(dpds)) {
            						maxDPD = Collections.max(dpds);
            					}
                                logger.info("Max DPD Of CoApplicant===>{}",maxDPD);
                                if (!CommonUtils.isObjectNullOrEmpty(maxDPD)) {
                                    scoreParameterRetailRequest.setDpd(maxDPD.doubleValue());
                                } else {
                                    scoreParameterRetailRequest.setDpd(0.0);
                                }

                                scoreParameterRetailRequest.setDPD_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting DPD parameter from CIBIL client : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.APPLICANT_NW_TO_LOAN_AMOUNT:
            				if(coApplicantDetail.getNetworth() != null) {
                				try {
									if(scoringRequestLoans.getElAmountOnAverageScoring() != null) {
										scoreParameterRetailRequest.setIsNetWorth_p(true);
										scoreParameterRetailRequest.setNetWorth((coApplicantDetail.getNetworth() / scoringRequestLoans.getElAmountOnAverageScoring()) * 100);
										logger.warn("Eligible Loan Amount Based on Income is Set in APPLICANT_NW_TO_LOAN_AMOUNT and Networth and Result==== > {}===>{}==>{}",scoringRequestLoans.getElAmountOnAverageScoring(),coApplicantDetail.getNetworth(),scoreParameterRetailRequest.getNetWorth());
									}else {
										logger.warn("Eligible Loan Amount Based on Income is not Set in APPLICANT_NW_TO_LOAN_AMOUNT==== > {}",scoringRequestLoans.getElAmountOnAverageScoring());
									}
    							} catch (Exception e1) {
    								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
    							}
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.INCOME_PROOF:
            				if(itrSkippedForCoApp != null && itrSkippedForCoApp) {
            					scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.NOT_AVAILABLE);
            				}else if (itrMannualForCoApp != null && itrMannualForCoApp){
            					scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.BANK_STATEMENT);
            				} else {
            					scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.IT_RETURN_AND_BANK_STATEMENT);
            				}
        				break;
        			case ScoreParameter.Retail.HomeLoan.AVG_EOD_BALANCE:
        				Double totalEODBalAvg = 0.0d;
        				Double totalCredit = 0.0d;
        				for(Data bankStatementData : coApplicantBankStatementDatas) {
        					if(bankStatementData.getSummaryInfo() != null) {
            					if(!CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg()) && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalCredit())) {
            						totalEODBalAvg = totalEODBalAvg + Double.parseDouble(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg());
            						totalCredit = totalCredit + Double.parseDouble(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalCredit());
            					}
            				}
        				}
        				scoreParameterRetailRequest.setAvgEodBalToToalDep((totalEODBalAvg / totalCredit));
						scoreParameterRetailRequest.setIsAvgEodBalToToalDep_p(true);
        				break;
        			case ScoreParameter.Retail.HomeLoan.LOAN_TO_INCOME_RATIO:
    					scoreParameterRetailRequest.setLoanToIncomeRatio_p(true);
        				break;
        			case ScoreParameter.Retail.HomeLoan.INCOME_TO_INSTALLMENT_RATIO:
    					scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
        				break;
        			case ScoreParameter.Retail.HomeLoan.EMI_NMI:
        				scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
        				//Already Set NMI and GMI Above Before Switch Starts
        				break;
                        default:
                         break;

                        }
                    }
//                    logger.info(MSG_SCORE_PARAMETER + scoreParameterRetailRequest.toString());

                    logger.info("----------------------------END-------------------------------------------");

                    Gson g = new Gson();
                    ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                    try {
                        scoringRequestDetail.setApplicationId(applicationId);
                        scoringRequestDetail.setRequest(g.toJson(scoreParameterRetailRequest));
                        scoringRequestDetail.setCreatedDate(new Date());
                        scoringRequestDetail.setCoAppId(scoringRequestLoans.getCoApplicantId());
                        scoringRequestDetail.setIsActive(true);
                        scoringRequestDetailRepository.save(scoringRequestDetail);

                        logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                    } catch (Exception e) {
                        logger.error(CommonUtils.EXCEPTION,e);
                    }
                }
            }
            scoringRequest.setScoreParameterRetailRequest(scoreParameterRetailRequest);
            scoringRequest.setCoAppId(coApplicantId);
            scoringRequestList.add(scoringRequest);
        }

        try {
            ScoringResponse calculateScoreList = scoringClient.calculateScoreList(scoringRequestList);
            logger.info("Scoring Response For HOME Loan for CoAPp============>{}",calculateScoreList);
            logger.info("Scoring Response Status For HOME Loan for CoAPp============>{}",calculateScoreList != null ? calculateScoreList.getStatus() : calculateScoreList);
            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
	}


    @Override
    public ResponseEntity<LoansResponse> calculateRetailAutoLoanScoringListForCoApplicant(List<ScoringRequestLoans> scoringRequestLoansList){

    	CoApplicantDetail coApplicantDetail = null;
        Long orgId = null;
        Long applicationId = null;
        Long coApplicantId = null;
        ScoringRequestLoans scoringRequestLoansReq = null;
        Double netMonthlyIncome = 0.0d;
        Double grossMonthlyIncome = 0.0d;
        List<Data> coApplicantBankStatementDatas = Collections.emptyList();
        Double totalEMI = 0.0;
        List<Integer> dpds = Collections.emptyList();
        List<CibilScoreLogRequest> cibilResponse = null;
        Boolean itrSkippedForCoApp = null;
        Boolean itrMannualForCoApp = null;
        List<Double> incomeOfItrOf3YearsCoApplicant = null;
//        Double loanAmount = 0.0d;
        if(!CommonUtils.isListNullOrEmpty(scoringRequestLoansList)) {
        	scoringRequestLoansReq = scoringRequestLoansList.get(0);
        	applicationId = scoringRequestLoansReq.getApplicationId();
        	coApplicantId = scoringRequestLoansReq.getCoApplicantId();
        	logger.info("Calculating Scoring For CoApplicant and ApplicationId============{}==================>{}",coApplicantId,applicationId);
        	coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(coApplicantId, true);
        	if (CommonUtils.isObjectNullOrEmpty(coApplicantDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                return new ResponseEntity<>(new LoansResponse(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_HOME_LOAN_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        	logger.info("coApplicantDetail.getEmploymentType()===============>{}",coApplicantDetail.getEmploymentType());

        	CibilRequest cibilRequest = new CibilRequest();
            cibilRequest.setPan(coApplicantDetail.getPan());
            cibilRequest.setApplicationId(scoringRequestLoansReq.getApplicationId());
            try {
            	cibilResponse = cibilClient.getSoftpingScores(scoringRequestLoansReq.getApplicationId(), coApplicantDetail.getPan());
            	CibilResponse cibilResponseDpdCoApp = cibilClient.getDPDLastXMonth(applicationId,coApplicantDetail.getPan());
            	if(!CommonUtils.isObjectNullOrEmpty(cibilResponseDpdCoApp) && !CommonUtils.isObjectListNull(cibilResponseDpdCoApp.getListData())){
    				List cibilResponseList = cibilResponseDpdCoApp.getListData();
    				dpds = new ArrayList<>(cibilResponseList.size());
    				for (int i = 0; i < cibilResponseList.size(); i++) {
    					String cibilResponseObj = cibilResponseList.get(i).toString();
    					if(cibilResponseObj.contains("|")){
    						String[] cibilDpdVal = cibilResponseObj.split("\\|");
    						if(!CommonUtils.isObjectNullOrEmpty(cibilDpdVal[1])) {
    							dpds.add(Integer.parseInt(cibilDpdVal[1]));
    						}
    					}else {
    						dpds.add(Integer.parseInt(cibilResponseList.get(i).toString()));
    					}
    				}
    			}
            }catch(Exception e) {
            	logger.error("Error in Getting CIBIL infor like DPD and Score == >{}",e);
            }

            List<CoApplicantEligibilityRequest> monthlyIncomeForCoApplicant = null;
            CoApplicantEligibilityRequest incomeFromEligibility = null;
			try {
				List<CoApplicantEligibilityRequest> coAppElRequest = new ArrayList<>(1);
				CoApplicantEligibilityRequest applicantEligibilityRequest = new CoApplicantEligibilityRequest();
				applicantEligibilityRequest.setId(scoringRequestLoansReq.getCoApplicantId());
				applicantEligibilityRequest.setIsConsiderIncome(scoringRequestLoansReq.getIsConsiderCoAppIncome());
				coAppElRequest.add(applicantEligibilityRequest);
				monthlyIncomeForCoApplicant = eligibilityClient.getMonthlyIncomeForCoApplicant(coAppElRequest, applicationId);
			} catch (EligibilityExceptions e) {
				logger.error("Error while Getting MonthlyIncome Details == >{}",e);
			}
			if(!CommonUtils.isListNullOrEmpty(monthlyIncomeForCoApplicant)) {
				try {
					incomeFromEligibility = monthlyIncomeForCoApplicant.get(0);
					logger.info("incomeFromEligibility===============>{}",incomeFromEligibility);
				}catch(Exception e) {
					logger.error("Error while Casting Object of CoApplicant Income====>{}",e);
				}
				if(!CommonUtils.isObjectNullOrEmpty(incomeFromEligibility)) {
					netMonthlyIncome = Double.valueOf(incomeFromEligibility.getNetMonthlyIncome());
                    grossMonthlyIncome = Double.valueOf(incomeFromEligibility.getGrossMonthlyIncome());
                    logger.info("Net Monthly Income For ApplicationId and CoApplicant Id======{}======>{}====>{}",applicationId,netMonthlyIncome,coApplicantId);
                    logger.info("Gross Annual Income For ApplicationId and CoApplicant Id======{}======>{}====>{}",applicationId,grossMonthlyIncome,coApplicantId);
				}
			}else {
				logger.info("Something is NULL From EligibilityResponse for CoApplicant===============>{}",monthlyIncomeForCoApplicant);
			}
            try {
                 ReportRequest reportRequest = new ReportRequest();
                 reportRequest.setApplicationId(applicationId);
                 reportRequest.setCoApplicantId(coApplicantId);
                 AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
                 if(analyzerResponse != null && analyzerResponse.getData() != null) {
                	 coApplicantBankStatementDatas = new ArrayList<>(5);
                     for(Object object : (List)analyzerResponse.getData()) {
                    	 Data dataBs = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) object, Data.class);
     					if(dataBs != null) {
     						if(EligibilityUtils.isObjectNullOrEmpty(dataBs.getCoAppId())) {
     							continue;
     						}
     						coApplicantBankStatementDatas.add(dataBs);
     					}
                     }
                 }
            }catch(Exception e) {
            	logger.error("Error while getting Bank Statement Details");
            }
            totalEMI = financialArrangementDetailsService.getTotalEmiByApplicationIdSoftPing(coApplicantId,applicationId);

            //ITR and bank Statement Checking
            itrSkippedForCoApp = loanRepository.isITRSkippedForCoApp(applicationId, coApplicantId);
            itrMannualForCoApp = loanRepository.isITRMannualForCoApp(applicationId, coApplicantId);
            incomeOfItrOf3YearsCoApplicant = loanRepository.getIncomeOfItrOf3YearsOfCoApplicant(coApplicantId);
        }
        List<ScoringRequest> scoringRequestList=new ArrayList<>(scoringRequestLoansList.size());
        Integer minBankRelationshipInMonths = null;
        for(ScoringRequestLoans scoringRequestLoans : scoringRequestLoansList)
        {
        	ScoreParameterRetailRequest scoreParameterRetailRequest = null;
            Long scoreModelId = scoringRequestLoans.getScoringModelCoAppId();
            if(scoreModelId == null) {
            	scoreModelId = scoringRequestLoans.getScoringModelId();
            }
            logger.info("Scoring model Id For CoApp===>{}",scoreModelId);
            Long fpProductId = scoringRequestLoans.getFpProductId();
            logger.info("Fp Product Id For CoApp===>{}",fpProductId);
            Integer personalBankingId = null;
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.RETAIL_AUTO_LOAN);
            scoringRequest.setEmi(scoringRequestLoans.getEmi());

            if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
            } else {
                scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
            }

            orgId = scoringRequestLoans.getOrgId();
            if(orgId != null) {
            	BankList bankEnum = BankList.fromOrgId(orgId.toString());
            	try {
	            	if(bankEnum != null) {
	            		logger.info("Bank Name====>{}==>Application Id===>{}===> Fp Product Id===>{}",bankEnum.getName(),applicationId,fpProductId);
	            		minBankRelationshipInMonths = bankingRelationlRepository.getMinRelationshipInMonthByApplicationAndOrgNameAndCoApplicantId(applicationId, bankEnum.getName(),coApplicantId);
	            	}
            	}catch(Exception e) {
            		logger.error("Error while Getting banking Relationship = >{}",e);
            	}
            	logger.info("Min Banking Relationship in Month CoApplicant === >{}",minBankRelationshipInMonths);
            	
            	// Step 1 :- (First scenario CHECK Borrower has any outstanding Loan with DPDs with SBI===============>)
            	try {
            		if(bankEnum != null) {
        				List<Long> ids = financialArrangementDetailsRepository.checkExistingLoanWithBankForCoApp(applicationId,(bankEnum.getName() != null ? bankEnum.getName().toLowerCase() : null),coApplicantId);
        				if(!CommonUtils.isListNullOrEmpty(ids)) {
        					Long cnt = financialArrangementDetailsRepository.checkDpdsWithBankByIds(ids);
        					if(cnt <= 0) {
        						personalBankingId = 2; //Credit Relation With satisfactory Performance(Standard In Our Books For the 12 Months) 
        					}
        				}
                	}       		
            	}catch(Exception e) {
            		logger.error("Error while Getting Personal Banking Relationship = >{}",e);
            	}
            	
            	
            	// Step 2 :- Corporate Salary Package(CSP Customer)
            	if(personalBankingId == null) {
            		for(Data bankStatementData : coApplicantBankStatementDatas) {
            			if(bankStatementData != null && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo()) && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getAccType())) {
            				String code = bankStatementData.getSummaryInfo().getAccType().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            				logger.info("After Replaced CoApp= >{}",code);
            					Long codeExists = cspCodeRepository.isCodeExists(code, orgId);
            					if(codeExists > 0) {
            						logger.info("CSP Code Found For OrgId==>{}==>{}",orgId,code);
            						personalBankingId = 3; //Corporate Salary Package(CSP Customer)
            						break;
            					}else {
            						logger.info("CSP Code not Found For OrgId==>{}",orgId);
            					}
            					
            			 }
            		}                		
            	}
            	
            	
            	// Step :3 (Deposit (SB/CA/TDR) relationship for at least 6 months) 				
            	if(personalBankingId == null) {
            		Double depositeSBCATDRAmount = 0.0d;
                	if(bankEnum != null) {
                		for(Data bankStatementData : coApplicantBankStatementDatas) {
                			if(bankStatementData != null && bankStatementData.getSummaryInfo() != null && bankStatementData.getSummaryInfo().getSummaryInfoTotalDetails() != null && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoTotalDetails().getTotalCredit()) && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getCustomerInfo()) && bankEnum.getName().equalsIgnoreCase(bankStatementData.getEnumBank())) {
                				depositeSBCATDRAmount = depositeSBCATDRAmount + Double.valueOf(bankStatementData.getSummaryInfo().getSummaryInfoTotalDetails().getTotalCredit()); 
                						logger.info("Total Deposite Amount here CoApp====={}====={}==>",depositeSBCATDRAmount,applicationId);
                			 	}
                		}                		
                	}
                	if(depositeSBCATDRAmount > 0.0d) {
                		personalBankingId = 4; //Deposite(SB/CA/TDR) Relationship For at Least 6 months
                	}
            	}
            	
            	if(personalBankingId == null) {
            		personalBankingId = 5; //New Customer
            	}
            }

            ///////// End  Getting Old Request ///////

            if (CommonUtils.isObjectNullOrEmpty(scoreParameterRetailRequest)) {
                scoreParameterRetailRequest= new ScoreParameterRetailRequest();
                scoringRequest.setLoanPurposeModelId(scoringRequestLoans.getLoanPurposeModelId());
                scoreParameterRetailRequest.setFoir(scoringRequestLoans.getFoir());
                scoreParameterRetailRequest.setNmi(netMonthlyIncome);
				scoreParameterRetailRequest.setGmi(grossMonthlyIncome);
				scoreParameterRetailRequest.setEmi(scoringRequestLoans.getEmi());
				scoreParameterRetailRequest.setElAmountOnAverageScoring(scoringRequestLoans.getElAmountOnAverageScoring());
				scoreParameterRetailRequest.setIsConsiderCoAppIncome(scoringRequestLoans.getIsConsiderCoAppIncome());
				scoreParameterRetailRequest.setPersonalBankingRelationShip(personalBankingId);
				logger.info("Is Income Consider For CoApplicant============>{}=======>{}",scoringRequestLoans.getIsConsiderCoAppIncome(), coApplicantId);
				logger.info("Result of Average Eligibility Call For CoApplicant===============>{}======>{}========>{}========================{}",scoringRequestLoans.getElAmountOnAverageScoring(),applicationId,fpProductId,coApplicantId);
				logger.info("FOIR For CoApplicant===============>{}======>{}========>{}========================{}",scoringRequestLoans.getFoir(),applicationId,fpProductId,applicationId,fpProductId,coApplicantId);
                logger.info("----------------------------START RETAIL HL ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

                // GET SCORE RETAIL PERSONAL LOAN PARAMETERS
                if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                    // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                	List<ModelParameterResponse> listFieldByBusinessTypeIdForCoApplicant = Collections.emptyList();
                    try {
                    	listFieldByBusinessTypeIdForCoApplicant = scoringClient.listFieldByBusinessTypeIdForCoApplicant(scoringRequest);
                    } catch (Exception e) {
                        logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                    }

                    for (ModelParameterResponse modelParameterResponse : listFieldByBusinessTypeIdForCoApplicant) {
                        FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                        fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                        fundSeekerInputRequest.setName(modelParameterResponse.getName());
                        logger.info("Parameter For CoApplicant==>{}",modelParameterResponse.getName());

//                        scoreParameterRetailRequest.setLoanAmtProposed(scoringRequestLoans.getElAmountOnAverageScoring());
                        switch (modelParameterResponse.getName()) {
                        case ScoreParameter.Retail.AutoLoan.AGE:
                        	   try {
                                   if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate())) {
                                	   Integer exactAge [] = CommonUtils.getExactAgeFromDate(coApplicantDetail.getBirthDate());
                                	   Double age = (((double) exactAge[0]) + ((double)exactAge[1] / 12.0d));
                                	   logger.info("Age With Point == {}",age);
                                       scoreParameterRetailRequest.setAge(age);
                                       scoreParameterRetailRequest.setAge_p(true);
                                   }
                               } catch (Exception e) {
                                   logger.error("error while getting AGE_HL parameter : ",e);
                               }
                        	break;
            			case ScoreParameter.Retail.AutoLoan.TOTAL_WORK_EXP:
            				try {
            					Double totalExperience = 0.0;
            					if(coApplicantDetail.getEmploymentType() != null) {
            						scoreParameterRetailRequest.setWorkingExperience_p(true);
            						if(!OccupationNatureNTB.SALARIED.getId().equals(coApplicantDetail.getEmploymentType())){
            							if(coApplicantDetail.getBusinessStartDate() != null) {
                        					logger.info("coApplicantDetail.getBusinessStartDate() For HL==== > {}",coApplicantDetail.getBusinessStartDate());
                        					Integer[] diifFromDate = CommonUtils.getExactAgeFromDate(coApplicantDetail.getBusinessStartDate());
                        					logger.info("Year For HL CoApplicant====ApplicationId===>{}=====>{}",diifFromDate[0],applicationId);
                        					logger.info("Month For HL CoApplicant====ApplicationId===>{}=====>{}",diifFromDate[1],applicationId);
                        					totalExperience = (((double) diifFromDate[0]) + ((double)diifFromDate[1] / 12.0d));
                        					logger.info("Total Business Experiance For HL==== > {}",totalExperience);
                                            scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                            scoreParameterRetailRequest.setWorkingExperience_p(true);
                        				}
                					}else {
                						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceYear())) {
                                        	totalExperience += Double.valueOf(coApplicantDetail.getTotalExperienceYear());
                                        	logger.info("totalExperience Year {}===>{}",coApplicantDetail.getTotalExperienceYear());
                                        }
                                        if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceMonth())) {
                                        	totalExperience += Double.valueOf(coApplicantDetail.getTotalExperienceMonth()) / 12.0d;
                                        	logger.info("totalExperience Month {}===>{}",coApplicantDetail.getTotalExperienceMonth());
                                        }
                                        logger.info("totalExperience {}===>{}",totalExperience);
                                        scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                					}
            					}
                            } catch (Exception e) {
                                logger.error("error while getting TOTAL_JOB_EXP parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.CURRENT_JOB_EXP:
            				try {
            					if(OccupationNatureNTB.SALARIED.getId().equals(coApplicantDetail.getEmploymentType())){
            						Double currentExperience = 0.0;
                                    if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobYear())){
                                   	 currentExperience += Double.valueOf(coApplicantDetail.getCurrentJobYear());
                                   	 logger.info("CURRENT_JOB_EXP Year {}===>{}",coApplicantDetail.getCurrentJobYear());
                                    }

                                    if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getCurrentJobMonth())) {
                                   	 currentExperience += (coApplicantDetail.getCurrentJobMonth() / 12);
                                   	 logger.info("CURRENT_JOB_EXP Month {}===>{}",coApplicantDetail.getCurrentJobMonth());
                                    }

                                    scoreParameterRetailRequest.setWorkingExperienceCurrent(currentExperience);
                                    scoreParameterRetailRequest.setIsWorkingExperienceCurrent_p(true);
            					}

                         } catch (Exception e) {
                             logger.error("error while getting CURRENT_JOB_EXP parameter : {}",e);
                         }
            				break;
            			case ScoreParameter.Retail.AutoLoan.RESIDENCE_TYPE:
            				if(coApplicantDetail.getResidenceType() != null) {
            					if(ResidenceStatusRetailMst.OWNED.getId().equals(coApplicantDetail.getResidenceType())) {
            						if(coApplicantDetail.getIsOwnedProp() != null && coApplicantDetail.getIsOwnedProp()) {
            							scoreParameterRetailRequest.setResidenceType(8); //Owned (Encumbered) : No Need to Add in ENUM. This is Only For Scoring
            						}else {
            								scoreParameterRetailRequest.setResidenceType(ResidenceStatusRetailMst.OWNED.getId());
            						}
            					}else{
            							scoreParameterRetailRequest.setResidenceType(coApplicantDetail.getResidenceType());
            					}
            					scoreParameterRetailRequest.setIsResidenceType_p(true);
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.NO_YEARS_STAY_CURR_LOC:
            				try {
            					if(coApplicantDetail.getResidenceSinceYear() != null && coApplicantDetail.getResidenceSinceMonth() != null) {
            						Integer year = coApplicantDetail.getResidenceSinceYear();
    	                            Integer month = coApplicantDetail.getResidenceSinceMonth();
    	                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	                            String s = null;
    	                            if(month < 10) {
    	                            	s = "01/0" + month + "/" + year;
    	                            }else {
    	                            	s = "01/" + month + "/" + year;
    	                            }
    	                            logger.info("Starting Date of Staying in Current Location For HL CoApplicant==== > {}",s);
    	                            Integer[] exactAgeFromDate = CommonUtils.getExactAgeFromDate(simpleDateFormat.parse(s));
    	                            Double noStayLoc = (((double) exactAgeFromDate[0]) + ((double)exactAgeFromDate[1] / 12.0d));
    	                            logger.info("No Of Years Staying in Current Location For HL==== > {}",noStayLoc);
    	                            scoreParameterRetailRequest.setNoOfYearCurrentLocation(noStayLoc);
    	                            scoreParameterRetailRequest.setIsNoOfYearCurrentLocation_p(true);
            					}
            				} catch (Exception e) {
	                            logger.error("error while getting NO_YEARS_STAY_CURR_LOC parameter : ", e);
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.BUREAU_SCORE:
            				scoreParameterRetailRequest.setCibilActualScore(filterBureauScoreByVersion(1, cibilResponse));
            				scoreParameterRetailRequest.setCibilActualScoreVersion2(filterBureauScoreByVersion(2, cibilResponse));
            				scoreParameterRetailRequest.setCibilScore_p(true);
            				break;
            			case ScoreParameter.Retail.AutoLoan.MARITAL_STATUS:
            				try {
                                scoreParameterRetailRequest.setMaritalStatus((coApplicantDetail.getStatusId() != null ? coApplicantDetail.getStatusId().longValue() : null));
                                scoreParameterRetailRequest.setMaritalStatus_p(coApplicantDetail.getStatusId() != null);
                            } catch (Exception e) {
                                logger.error("error while getting MARITAL_STATUS parameter : ",e);
                            }
            				break;

            			case ScoreParameter.Retail.AutoLoan.EMPLOYMENT_TYPE:
            				try {
            					scoreParameterRetailRequest.setEmployementType_p(coApplicantDetail.getEmploymentType() != null);
                				scoreParameterRetailRequest.setEmploymentType((coApplicantDetail.getEmploymentType() != null  ? coApplicantDetail.getEmploymentType().longValue() : null));
                            } catch (Exception e) {
                                logger.error("error while getting MARITAL_STATUS parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.EMPLOYMENT_CATEG_JOB:
            				if(coApplicantDetail.getEmploymentType() != null && OccupationNatureNTB.SALARIED.getId().equals(coApplicantDetail.getEmploymentType())) {
            					scoreParameterRetailRequest.setIsEmployementJobCat_p(coApplicantDetail.getEmploymentWith() != null);
                				scoreParameterRetailRequest.setEmploymentTypeCatJob(coApplicantDetail.getEmploymentWith());
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED:
            				if(coApplicantDetail.getEmploymentType() != null && !OccupationNatureNTB.SALARIED.getId().equals(coApplicantDetail.getEmploymentType())) {
            					if(OccupationNatureNTB.AGRICULTURIST.getId().equals(coApplicantDetail.getEmploymentType())
            							|| OccupationNatureNTB.PENSIONER.getId().equals(coApplicantDetail.getEmploymentType())
            							|| OccupationNatureNTB.OTHERS.getId().equals(coApplicantDetail.getEmploymentType())) {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(true);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus(OccupationHL.AGRICULTURIST_PENSIONER_OTHERS.getId().longValue());
            					}else if(OccupationNatureNTB.SELF_EMPLOYED_NON_PROFESSIONAL.getId().equals(coApplicantDetail.getEmploymentType())) {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(true);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus(OccupationHL.BUSINESSMAN.getId().longValue());
            					}else {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(coApplicantDetail.getEmploymentWith() != null);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus((coApplicantDetail.getEmploymentWith() != null  ? coApplicantDetail.getEmploymentWith().longValue() : null));
            					}
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.CURRENT_EMPLOYMENT_STATUS:
            				scoreParameterRetailRequest.setIsCurrentEmploymentStatus_p(coApplicantDetail.getEmploymentStatus() != null);
            				scoreParameterRetailRequest.setCurrentEmploymentStatus((coApplicantDetail.getEmploymentStatus() != null  ? coApplicantDetail.getEmploymentStatus().longValue() : null));
            				break;
            			case ScoreParameter.Retail.AutoLoan.MIN_BANKING_RELATIONSHIP:
            				//Not Available in Sheet Document
            				scoreParameterRetailRequest.setIsMinBankingRelationship_p(true);
            				scoreParameterRetailRequest.setMinBankingRelationship(minBankRelationshipInMonths == null ? 0 : minBankRelationshipInMonths);
            				break;
            			case ScoreParameter.Retail.AutoLoan.SPOUSE_EMPLOYEMENT:
            				try {
            					if(coApplicantDetail.getSpouseEmployment() != null) {
            						scoreParameterRetailRequest.setSpouseEmploymentDetails(coApplicantDetail.getSpouseEmployment().longValue());
            					}else {
            						scoreParameterRetailRequest.setSpouseEmploymentDetails(3l);
            					}
            					scoreParameterRetailRequest.setSpouseEmploymentDetails_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting SPOUSE_EMPLOYEMENT parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.NO_OF_DEPENDANTS:
            				try {
                                scoreParameterRetailRequest.setNumberOfDependents(coApplicantDetail.getNoDependent());
                                scoreParameterRetailRequest.setNumberOfDependents_p(coApplicantDetail.getNoDependent() != null);
                            } catch (Exception e) {
                                logger.error("error while getting NO_OF_DEPENDANTS parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.DESIGNATION:
            				try {
                                scoreParameterRetailRequest.setDesignation((coApplicantDetail.getDesignation() != null ? coApplicantDetail.getDesignation().longValue() : null));
                                scoreParameterRetailRequest.setDesignation_p(coApplicantDetail.getDesignation() != null);
                            } catch (Exception e) {
                                logger.error("error while getting DESIGNATION parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.EDUCATION_QUALIFICATION:
            				try {
                                scoreParameterRetailRequest.setEducationQualification((coApplicantDetail.getEducationQualification() != null ? coApplicantDetail.getEducationQualification().longValue() : null));
                                scoreParameterRetailRequest.setEducationQualifaction_p(coApplicantDetail.getEducationQualification() != null);
                            } catch (Exception e) {
                                logger.error("error while getting EDUCATION_QUALIFICATION parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.ANNUAL_INCOME:
            				//Already Set NMI and GMI Above Before Switch Starts
            				break;
            			case ScoreParameter.Retail.AutoLoan.AVAILABLE_INCOME:
            				try {
            					logger.info("netMonthlyIncome===>{}===grossAnnualIncome===>{}== For ApplicationId ==>{}===>FpProductId===>{}",netMonthlyIncome,grossMonthlyIncome,applicationId,fpProductId);
            					logger.info("Foir For AVAILABLE_INCOME for CoApplicant============>{}",scoringRequestLoans.getFoir());
            					scoreParameterRetailRequest.setFoir(scoringRequestLoans.getFoir());
								scoreParameterRetailRequest.setIsAvailableIncome_p(true);
							} catch (Exception e1) {
								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
							}
            				break;
            			case ScoreParameter.Retail.AutoLoan.ADDI_INCOME_SPOUSE:
            				//Not Available in Sheet Document
	            				if(coApplicantDetail.getAnnualIncomeOfSpouse() != null) {
	            					scoreParameterRetailRequest.setSpouseIncome(coApplicantDetail.getAnnualIncomeOfSpouse());
	            					scoreParameterRetailRequest.setIsSpouseIncome_p(true);
	            				}else {
	            					scoreParameterRetailRequest.setSpouseIncome(0.0d);
	            					scoreParameterRetailRequest.setIsSpouseIncome_p(true);
	            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.MON_INCOME_DEPENDANT:
            				scoreParameterRetailRequest.setNoOfDependants(coApplicantDetail.getNoDependent());
            				scoreParameterRetailRequest.setIsMonIncomePerDep_p(true);
            				break;
            			case ScoreParameter.Retail.AutoLoan.AVG_INCREASE_INCOME_REPORT_3_YEARS:
            				logger.info("Income List From ITR for HL For CoApplicant == >{}",incomeOfItrOf3YearsCoApplicant);
            				if(!CommonUtils.isListNullOrEmpty(incomeOfItrOf3YearsCoApplicant)) {
            					if(incomeOfItrOf3YearsCoApplicant.size() == 3) { //as if now considering 3 Years Compulsory
            						Double itrLastToLastToLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 1);
            						if(itrLastToLastToLastYearIncome == null ) {
            							itrLastToLastToLastYearIncome = 1.0d;
            						}
                					Double itrLastToLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 2);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 1.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 3);

                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 0.0;
                					}
            						Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
            						logger.info("Final Income After Calculation for HL == >{}",finalIncome);
            						if(Double.isFinite(finalIncome)) {
            							scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
                						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            						}
            					}else if(incomeOfItrOf3YearsCoApplicant.size() == 2) { //as if now considering 2 Years Compulsory
            						Double itrLastToLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 1);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 1.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 2);
                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 1.0;
                					}
            						Double finalIncome =  (((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100);
            						logger.info("Final Income After Calculation for HL == >{}==>ApplicationId==>{}",finalIncome,applicationId);
            						if(Double.isFinite(finalIncome)) {
            							scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
                						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            						}
            					}else if(incomeOfItrOf3YearsCoApplicant.size() == 1) { //as if now considering 1 Years Compulsory
            						logger.info("Final Income After Calculation for HL CoApplicant == >{} ==> For coApplicantId===>{}",0.0d,coApplicantId);
        							scoreParameterRetailRequest.setIncomeFromItr(0.0d);
            						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            					}
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.AVG_DEPOS_LAST_6_MONTH:
            				Double value = 0.0d;
            				for(Data bankStatementData : coApplicantBankStatementDatas) {
            					if(bankStatementData != null && bankStatementData.getSummaryInfo() != null && bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails() != null  && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit())) {
                					value = value + Double.valueOf(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit()); // / 6
                					logger.info("AVG_DEPOS_LAST_6_MONTH value===>{}",value);
           					 	}
            				}
            				scoreParameterRetailRequest.setAvgOfTotalCheDepsitLast6Month(value);
   					 		scoreParameterRetailRequest.setIsAvgOfTotalCheDepsitLast6Month_p(true);
            				break;
            			case ScoreParameter.Retail.AutoLoan.CHECQUE_BOUNSE_LAST_1_MONTH:
            				 try {
            					 Double chequeBounselast1Month = 0.0d;
            					 for(Data bankStatementData : coApplicantBankStatementDatas) {
            						 if(bankStatementData != null && bankStatementData.getCheckBounceForLast1Month() != null) {
                						 chequeBounselast1Month = chequeBounselast1Month + bankStatementData.getCheckBounceForLast1Month().doubleValue();
                					 }
            					 }
            					 scoreParameterRetailRequest.setChequeBouncelast1Month(chequeBounselast1Month);
            					 scoreParameterRetailRequest.setIsChequeBounceLast1Month_p(true);
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 1 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.AutoLoan.CHECQUE_BOUNSE_LAST_6_MONTH:
            				 try {
            					 Double chequeBounselast6Month = 0.0d;
            					 for(Data bankStatementData : coApplicantBankStatementDatas) {
            						 if(bankStatementData != null && bankStatementData.getCheckBounceForLast6Month() != null) {
            							 chequeBounselast6Month = chequeBounselast6Month + bankStatementData.getCheckBounceForLast6Month().doubleValue();
                					 }
            					 }
            				   scoreParameterRetailRequest.setChequeBounce(chequeBounselast6Month);
                               scoreParameterRetailRequest.setChequeBounce_p(true);
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 6 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.AutoLoan.DPD:
            				try {
            					Integer maxDPD = 0;
            					if(!CommonUtils.isListNullOrEmpty(dpds)) {
            						maxDPD = Collections.max(dpds);
            					}
                                logger.info("Max DPD Of CoApplicant===>{}",maxDPD);
                                if (!CommonUtils.isObjectNullOrEmpty(maxDPD)) {
                                    scoreParameterRetailRequest.setDpd(maxDPD.doubleValue());
                                } else {
                                    scoreParameterRetailRequest.setDpd(0.0);
                                }
                                scoreParameterRetailRequest.setDPD_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting DPD parameter from CIBIL client : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.APPLICANT_NW_TO_LOAN_AMOUNT:
            				if(coApplicantDetail.getNetworth() != null) {
                				try {
									if(scoringRequestLoans.getElAmountOnAverageScoring() != null) {
										scoreParameterRetailRequest.setIsNetWorth_p(true);
										scoreParameterRetailRequest.setNetWorth((coApplicantDetail.getNetworth() / scoringRequestLoans.getElAmountOnAverageScoring()) * 100);
										logger.warn("Eligible Loan Amount Based on Income is Set in APPLICANT_NW_TO_LOAN_AMOUNT and Networth and Result==== > {}===>{}==>{}",scoringRequestLoans.getElAmountOnAverageScoring(),coApplicantDetail.getNetworth(),scoreParameterRetailRequest.getNetWorth());
									}else {
										logger.warn("Eligible Loan Amount Based on Income is not Set in APPLICANT_NW_TO_LOAN_AMOUNT==== > {}",scoringRequestLoans.getElAmountOnAverageScoring());
									}
    							} catch (Exception e1) {
    								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
    							}
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.INCOME_PROOF:
            				if(itrSkippedForCoApp != null && itrSkippedForCoApp) {
            					scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.NOT_AVAILABLE);
            				}else if (itrMannualForCoApp != null && itrMannualForCoApp){
            					scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.BANK_STATEMENT);
            				} else {
            					scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.IT_RETURN_AND_BANK_STATEMENT);
            				}
        				break;
        			case ScoreParameter.Retail.AutoLoan.AVG_EOD_BALANCE:
        				Double totalEODBalAvg = 0.0d;
        				Double totalCredit = 0.0d;
        				for(Data bankStatementData : coApplicantBankStatementDatas) {
        					if(bankStatementData.getSummaryInfo() != null) {
            					if(!CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg()) && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalCredit())) {
            						totalEODBalAvg = totalEODBalAvg + Double.parseDouble(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg());
            						totalCredit = totalCredit + Double.parseDouble(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalCredit());
            					}
            				}
        				}
        				scoreParameterRetailRequest.setAvgEodBalToToalDep((totalEODBalAvg / totalCredit));
						scoreParameterRetailRequest.setIsAvgEodBalToToalDep_p(true);
        				break;
        			case ScoreParameter.Retail.AutoLoan.LOAN_TO_INCOME_RATIO:
    					scoreParameterRetailRequest.setLoanToIncomeRatio_p(true);
        				break;
        			case ScoreParameter.Retail.AutoLoan.INCOME_TO_INSTALLMENT_RATIO:
    					scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
        				break;
        			case ScoreParameter.Retail.AutoLoan.EMI_NMI:
        				scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
        				//Already Set NMI and GMI Above Before Switch Starts
        				break;
        			case ScoreParameter.Retail.AutoLoan.PERSONAL_RELATIONSHIP_WITH_BANK: //
        				scoreParameterRetailRequest.setIsPersonalRelationShipWithBank_p(true);
        				 break;
//        			case ScoreParameter.Retail.AutoLoan.IS_ADHAAR_CARD:
//        				scoreParameterRetailRequest.setIsAdhaarCard_p(true);
//        				scoreParameterRetailRequest.setAdhaarCardValue(YesNo.NO.getId()); // Default No
//        				if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getIsUserHaveAadhar())) {
//        					if(coApplicantDetail.getIsUserHaveAadhar()) {
//        						scoreParameterRetailRequest.setAdhaarCardValue(YesNo.YES.getId()); // Default Yes	
//        					}
//        				}
//        				break;
                        default:
                         break;

                        }
                    }
//                    logger.info(MSG_SCORE_PARAMETER + scoreParameterRetailRequest.toString());

                    logger.info("----------------------------END-------------------------------------------");

                    Gson g = new Gson();
                    ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                    try {
                        scoringRequestDetail.setApplicationId(applicationId);
                        scoringRequestDetail.setRequest(g.toJson(scoreParameterRetailRequest));
                        scoringRequestDetail.setCreatedDate(new Date());
                        scoringRequestDetail.setCoAppId(scoringRequestLoans.getCoApplicantId());
                        scoringRequestDetail.setIsActive(true);
                        scoringRequestDetailRepository.save(scoringRequestDetail);

                        logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                    } catch (Exception e) {
                        logger.error(CommonUtils.EXCEPTION,e);
                    }
                }
            }
            scoringRequest.setScoreParameterRetailRequest(scoreParameterRetailRequest);
            scoringRequest.setCoAppId(coApplicantId);
            scoringRequestList.add(scoringRequest);
        }

        try {
            ScoringResponse calculateScoreList = scoringClient.calculateScoreList(scoringRequestList);
            logger.info("Scoring Response For HOME Loan for CoAPp============>{}",calculateScoreList);
            logger.info("Scoring Response Status For HOME Loan for CoAPp============>{}",calculateScoreList != null ? calculateScoreList.getStatus() : calculateScoreList);
            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }

    }
    private Boolean isSalaryAccountWithBank(Long applicationId) {

        Boolean salaryWithBank=false;

        AnalyzerResponse analyzerResponse=null;
        Data data=null;

        try {
            ReportRequest reportRequest = new ReportRequest();
            reportRequest.setApplicationId(applicationId);
            analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);
            if (!CommonUtils.isObjectNullOrEmpty(analyzerResponse)) {
                     data = MultipleJSONObjectHelper
                        .getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(), Data.class);
            }
        } catch (Exception e) {
            logger.error("Exception while getting perfios data======={}", e.getMessage());
        }


        //Check BankStatement Last 6 Month Transaction
        try {
            if (data != null) {
                List<Xn> xns = data.getXns().getXn();
                for (Xn xn : xns) {
                    if (xn.getCategory().equalsIgnoreCase("Salary")) {
                        salaryWithBank=true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.info("EXCEPTION IS GETTING WHILE GETTING BANK STATEMENT DATA------------>>>>>>" + e.getMessage());
        }
        return salaryWithBank;
    }

    @Override
    public ResponseEntity<LoansResponse> calculateExistingBusinessScoring(ScoringRequestLoans scoringRequestLoans) {

        ScoringParameterRequest scoringParameterRequest = new ScoringParameterRequest();

        Long scoreModelId = scoringRequestLoans.getScoringModelId();
        Long applicationId = scoringRequestLoans.getApplicationId();
        Long fpProductId = scoringRequestLoans.getFpProductId();

        ///////// Get Financial Type Id from ITR////////

        Integer financialTypeId = 3;

        ITRConnectionResponse itrConnectionResponse = null;
        try {
            itrConnectionResponse = itrClient.getIsUploadAndYearDetails(applicationId);

            if (!CommonUtils.isObjectNullOrEmpty(itrConnectionResponse) && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse.getData())) {
                Map<String, Object> map = (Map<String, Object>) itrConnectionResponse.getData();
                ITRBasicDetailsResponse res = MultipleJSONObjectHelper.getObjectFromMap(map, ITRBasicDetailsResponse.class);
                if (!CommonUtils.isObjectNullOrEmpty(res) && !CommonUtils.isObjectNullOrEmpty(res.getItrFinancialType())) {
                    financialTypeId = Integer.valueOf(res.getItrFinancialType());
                }
            }
        } catch (IOException e) {
            logger.error("error while getting Financial Type Id from itr response : ",e);
        }

        /////////


        List<ScoringRequestDetail> scoringRequestDetailList = scoringRequestDetailRepository.getScoringRequestDetailByApplicationIdAndIsActive(applicationId);


        ScoringRequestDetail scoringRequestDetailSaved;

        if (scoringRequestDetailList.size() > 0) {
            logger.info("Getting Old Scoring request Data for  =====> " + applicationId);
            scoringRequestDetailSaved = scoringRequestDetailList.get(0);
            Gson gson = new Gson();
            scoringParameterRequest = gson.fromJson(scoringRequestDetailSaved.getRequest(), ScoringParameterRequest.class);
        }


        ScoringResponse scoringResponseMain = null;

        ScoringRequest scoringRequest = new ScoringRequest();
        scoringRequest.setScoringModelId(scoreModelId);
        scoringRequest.setFpProductId(fpProductId);
        scoringRequest.setApplicationId(applicationId);
        scoringRequest.setUserId(scoringRequestLoans.getUserId());
        scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.EXISTING_BUSINESS);

        if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
            scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
        } else {
            scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
        }

        logger.info("Financial Type Id ::::::::::::::::================>" + scoringRequest.getFinancialTypeId());
        if (!(scoringRequestDetailList.size() > 0)) {

            logger.info("Scoring Data Fetched First Time  =====> " + applicationId);

            logger.info("----------------------------START EXISTING LOAN ------------------------------");

            logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

            // start Get GST Parameter

            String gstNumber = corporateApplicantDetailRepository.getGstInByApplicationId(applicationId);
            Double loanAmount = primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);

            CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);


            GstResponse gstResponse = null;
            GstResponse gstResponseScoring = null;
            GstCalculation gstCalculation = new GstCalculation();

            try {
                GSTR1Request gstr1Request = new GSTR1Request();
                gstr1Request.setGstin(gstNumber);
                gstr1Request.setApplicationId(applicationId);
                gstResponse = gstClient.getCalculations(gstr1Request);

                if (!CommonUtils.isObjectNullOrEmpty(gstResponse) && !CommonUtils.isObjectNullOrEmpty(gstResponse.getData())) {
                    gstCalculation = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) gstResponse.getData(),
                            GstCalculation.class);
                }

            } catch (Exception e) {
                logger.error("error while getting GST parameter : ",e);
            }


            // get GST Data for Sales Show A Rising Trend

            try {
                GSTR1Request gstr1Request = new GSTR1Request();
                gstr1Request.setGstin(gstNumber);
                gstr1Request.setApplicationId(applicationId);
                gstResponseScoring = gstClient.getCalculationForScoring(gstr1Request);
            } catch (Exception e) {
                logger.error("error while getting GST parameter for GST Sales Show A Rising Trend : ",e);
            }

            // end Get GST Parameter

            int currentYear = getFinYear(applicationId);
            if (CommonUtils.isObjectNullOrEmpty(currentYear)) {
                logger.error("error while getting current year from itr");
                LoansResponse loansResponse = new LoansResponse("error while getting current year from itr.", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }

            // CMA
            OperatingStatementDetails operatingStatementDetailsFY = new OperatingStatementDetails();
            OperatingStatementDetails operatingStatementDetailsSY = new OperatingStatementDetails();
            OperatingStatementDetails operatingStatementDetailsTY = new OperatingStatementDetails();


            LiabilitiesDetails liabilitiesDetailsFY = new LiabilitiesDetails();
            LiabilitiesDetails liabilitiesDetailsSY = new LiabilitiesDetails();
            LiabilitiesDetails liabilitiesDetailsTY = new LiabilitiesDetails();

            AssetsDetails assetsDetailsFY = new AssetsDetails();
            AssetsDetails assetsDetailsSY = new AssetsDetails();
            AssetsDetails assetsDetailsTY = new AssetsDetails();

            if (ScoreParameter.FinancialTypeForITR.THREE_YEAR_ITR == financialTypeId) {
                operatingStatementDetailsTY = operatingStatementDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                operatingStatementDetailsSY = operatingStatementDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 2 + "");
                operatingStatementDetailsFY = operatingStatementDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 3 + "");

                liabilitiesDetailsTY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                liabilitiesDetailsSY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 2 + "");
                liabilitiesDetailsFY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 3 + "");

                assetsDetailsTY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                assetsDetailsSY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 2 + "");
                assetsDetailsFY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 3 + "");
            } else if (ScoreParameter.FinancialTypeForITR.ONE_YEAR_ITR == financialTypeId) {
                operatingStatementDetailsTY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear - 1 + "");
                liabilitiesDetailsTY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                assetsDetailsTY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear - 1 + "");
            } else if (ScoreParameter.FinancialTypeForITR.PRESUMPTIVE == financialTypeId) {
                operatingStatementDetailsTY = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, currentYear - 1 + "");
                liabilitiesDetailsTY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                assetsDetailsTY = assetsDetailsRepository.getAssetsDetails(applicationId, currentYear - 1 + "");
            }

            ///////////////

            // Get Director Background detail

            DirectorBackgroundDetail mainDirectorBackgroundDetail = directorBackgroundDetailsRepository.getMainDirectorByApplicationId(applicationId);

            // get Primary Corporate Detail

            PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);

            // GET SCORE CORPORATE LOAN PARAMETERS


            if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                ScoringResponse scoringResponse = null;
                try {
                    scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                } catch (Exception e) {
                    logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                }

                List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();

                //List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

                logger.info("dataList=====================================>>>>>>>>>>>>>>>>>>>>>>" + dataList.size());

                for (int i=0;i<dataList.size();i++){

                    ModelParameterResponse modelParameterResponse = null;
                    try {
                        modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                ModelParameterResponse.class);
                    } catch (IOException e) {
                        logger.error(CommonUtils.EXCEPTION,e);
                    }

                /*FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                fundSeekerInputRequest.setName(modelParameterResponse.getName());*/

                    switch (modelParameterResponse.getName()) {

                        case ScoreParameter.COMBINED_NETWORTH: {
                            try {
                                Double networthSum = directorBackgroundDetailsRepository.getSumOfDirectorsNetworth(applicationId);
                                if (CommonUtils.isObjectNullOrEmpty(networthSum))
                                    networthSum = 0.0;

                                Double termLoansTy = liabilitiesDetailsTY.getTermLoans();
                                if (CommonUtils.isObjectNullOrEmpty(termLoansTy))
                                    termLoansTy = 0.0;

                                scoringParameterRequest.setNetworthSum(networthSum);
                                scoringParameterRequest.setTermLoanTy(termLoansTy);
                                scoringParameterRequest.setLoanAmount(loanAmount);
                                scoringParameterRequest.setCombinedNetworth_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting COMBINED_NETWORTH parameter : ",e);
                                scoringParameterRequest.setCombinedNetworth_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.CUSTOMER_ASSOCIATE_CONCERN: {
                            Double customer_ass_concern_year = null;
                            try {

                                CibilResponse cibilResponse = cibilClient.getDPDYears(applicationId);
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isObjectNullOrEmpty(cibilResponse.getData())) {
                                    customer_ass_concern_year = (Double) cibilResponse.getData();

                                    scoringParameterRequest.setCustomerAssociateConcern(customer_ass_concern_year);
                                    scoringParameterRequest.setCustomerAsscociateConcern_p(true);
                                } else {
                                    scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                                }

                            } catch (Exception e) {
                                logger.error("error while getting CUSTOMER_ASSOCIATE_CONCERN parameter from CIBIL client : ",e);
                                scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                            }
                            break;

                        }
                        case ScoreParameter.CIBIL_TRANSUNION_SCORE: {
                            Double cibil_score_avg_promotor = null;
                            try {

                                CibilRequest cibilRequest = new CibilRequest();
                                cibilRequest.setApplicationId(applicationId);

                                CibilResponse cibilResponse = cibilClient.getCibilScore(cibilRequest);
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse.getData())) {
                                    cibil_score_avg_promotor = (Double) cibilResponse.getData();
                                    scoringParameterRequest.setCibilTransuniunScore(cibil_score_avg_promotor);
                                    scoringParameterRequest.setCibilTransunionScore_p(true);
                                } else {
                                    scoringParameterRequest.setCibilTransunionScore_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting CIBIL_TRANSUNION_SCORE parameter from CIBIL client : ",e);
                                scoringParameterRequest.setCibilTransunionScore_p(false);
                            }

                            break;
                        }

                        case ScoreParameter.EXPERIENCE_IN_THE_BUSINESS: {
                            Double directorExperience = directorBackgroundDetailsRepository.getMaxOfDirectorsExperience(applicationId);

                            if (!CommonUtils.isObjectNullOrEmpty(directorExperience)) {
                                scoringParameterRequest.setExperienceInTheBusiness(directorExperience);
                                scoringParameterRequest.setExperienceInTheBusiness_p(true);
                            } else {
                                scoringParameterRequest.setExperienceInTheBusiness_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.DEBT_EQUITY_RATIO: {

                            try {
                            /*Double debt = liabilitiesDetailsTY.getSubTotalA() +
                                    liabilitiesDetailsTY.getShortTermBorrowingFromOthers() +
                                    liabilitiesDetailsTY.getTotalTermLiabilities() -
                                    liabilitiesDetailsTY.getPreferencesShares() +
                                    liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther() +
                                    liabilitiesDetailsTY.getOtherNclOthers() +
                                    liabilitiesDetailsTY.getMinorityInterest() +
                                    liabilitiesDetailsTY.getDeferredTaxLiability();*/

                                // 27-9-2018 9:19 PM Rahul Khudai Removed iabilitiesDetailsTY.getSubTotalA()
                                // + liabilitiesDetailsTY.getShortTermBorrowingFromOthers()  from Debt calculation

                                Double debt = liabilitiesDetailsTY.getTotalTermLiabilities() -
                                        liabilitiesDetailsTY.getPreferencesShares() +
                                        liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther() +
                                        liabilitiesDetailsTY.getOtherNclOthers() +
                                        liabilitiesDetailsTY.getMinorityInterest() +
                                        liabilitiesDetailsTY.getDeferredTaxLiability();


                                if (CommonUtils.isObjectNullOrEmpty(debt))
                                    debt = 0.0;


                                Double equity = liabilitiesDetailsTY.getPreferencesShares() +
                                        liabilitiesDetailsTY.getNetWorth() -
                                        liabilitiesDetailsTY.getMinorityInterest() -
                                        liabilitiesDetailsTY.getDeferredTaxLiability();
                                if (CommonUtils.isObjectNullOrEmpty(debt))
                                    equity = 0.0;

                                scoringParameterRequest.setDebtTY(debt);
                                scoringParameterRequest.setEquityTY(equity);
                                scoringParameterRequest.setDebtEquityRatio_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting DEBT_EQUITY_RATIO parameter : ",e);
                                scoringParameterRequest.setDebtEquityRatio_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.TOL_TNW: {

                            try {
                                Double tol = liabilitiesDetailsTY.getTotalOutsideLiabilities();
                                if (CommonUtils.isObjectNullOrEmpty(tol))
                                    tol = 0.0;

                                Double tnw = assetsDetailsTY.getTangibleNetWorth();
                                if (CommonUtils.isObjectNullOrEmpty(tnw))
                                    tnw = 0.0;

                                scoringParameterRequest.setTolTY(tol);
                                scoringParameterRequest.setTnwTY(tnw);
                                scoringParameterRequest.setTolTnw_p(true);
                                scoringParameterRequest.setLoanAmount(loanAmount);

                            } catch (Exception e) {
                                logger.error("error while getting TOL_TNW parameter : ",e);
                                scoringParameterRequest.setTolTnw_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.AVERAGE_CURRENT_RATIO: {
                            try {

                                Double currentRatio = (assetsDetailsTY.getCurrentRatio() + assetsDetailsSY.getCurrentRatio()) / 2;
                                if (CommonUtils.isObjectNullOrEmpty(currentRatio))
                                    currentRatio = 0.0;

                                scoringParameterRequest.setAvgCurrentRatioTY(currentRatio);
                                scoringParameterRequest.setAvgCurrentRatio_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_CURRENT_RATIO parameter : ",e);
                                scoringParameterRequest.setAvgCurrentRatio_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.WORKING_CAPITAL_CYCLE: {

                            try {
                                Double debtorsDays = null;
                                if ((operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome()) != 0.0) {
                                    debtorsDays = ((assetsDetailsTY.getReceivableOtherThanDefferred() + assetsDetailsTY.getExportReceivables()) / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                                }
                                if (CommonUtils.isObjectNullOrEmpty(debtorsDays))
                                    debtorsDays = 0.0;


                                /////////////

                                Double averageInventory = (operatingStatementDetailsTY.getAddOperatingStockFg() + operatingStatementDetailsTY.getDeductClStockFg()) / 2;
                                if (CommonUtils.isObjectNullOrEmpty(averageInventory))
                                    averageInventory = 0.0;

                                Double cogs = operatingStatementDetailsTY.getRawMaterials() + operatingStatementDetailsTY.getAddOperatingStockFg() - operatingStatementDetailsTY.getDeductClStockFg();
                                if (CommonUtils.isObjectNullOrEmpty(cogs))
                                    cogs = 0.0;


                                /////////////

                                Double creditorsDays = null;
                                if ((operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome()) != 0) {
                                    creditorsDays = (liabilitiesDetailsTY.getSundryCreditors() / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                                }
                                if (CommonUtils.isObjectNullOrEmpty(creditorsDays))
                                    creditorsDays = 0.0;


                                scoringParameterRequest.setDebtorsDaysTY(debtorsDays);
                                scoringParameterRequest.setAvgInventoryTY(averageInventory);
                                scoringParameterRequest.setCogsTY(cogs);
                                scoringParameterRequest.setCreditorsDaysTY(creditorsDays);
                                scoringParameterRequest.setWorkingCapitalCycle_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting WORKING_CAPITAL_CYCLE parameter : ",e);
                                scoringParameterRequest.setWorkingCapitalCycle_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.AVERAGE_ANNUAL_GROWTH_GROSS_CASH: {
                            try {
                                Double netProfitOrLossTY = operatingStatementDetailsTY.getNetProfitOrLoss();
                                if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossTY))
                                    netProfitOrLossTY = 0.0;
                                Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                                if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                                    depreciationTy = 0.0;
                                Double interestTy = operatingStatementDetailsTY.getInterest();
                                if (CommonUtils.isObjectNullOrEmpty(interestTy))
                                    interestTy = 0.0;

                                Double netProfitOrLossSY = operatingStatementDetailsSY.getNetProfitOrLoss();
                                if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossSY))
                                    netProfitOrLossSY = 0.0;
                                Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                                if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                                    depreciationSy = 0.0;
                                Double interestSy = operatingStatementDetailsSY.getInterest();
                                if (CommonUtils.isObjectNullOrEmpty(interestSy))
                                    interestSy = 0.0;

                                Double netProfitOrLossFY = operatingStatementDetailsFY.getNetProfitOrLoss();
                                if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossFY))
                                    netProfitOrLossFY = 0.0;
                                Double depreciationFy = operatingStatementDetailsFY.getDepreciation();
                                if (CommonUtils.isObjectNullOrEmpty(depreciationFy))
                                    depreciationFy = 0.0;
                                Double interestFy = operatingStatementDetailsFY.getInterest();
                                if (CommonUtils.isObjectNullOrEmpty(interestFy))
                                    interestFy = 0.0;

                                scoringParameterRequest.setNetProfitOrLossFY(netProfitOrLossFY);
                                scoringParameterRequest.setNetProfitOrLossSY(netProfitOrLossSY);
                                scoringParameterRequest.setNetProfitOrLossTY(netProfitOrLossTY);

                                scoringParameterRequest.setDepriciationFy(depreciationFy);
                                scoringParameterRequest.setDepriciationSy(depreciationSy);
                                scoringParameterRequest.setDepriciationTy(depreciationTy);

                                scoringParameterRequest.setInterestFy(interestFy);
                                scoringParameterRequest.setInterestSy(interestSy);
                                scoringParameterRequest.setInterestTy(interestTy);

                                scoringParameterRequest.setAvgAnnualGrowthGrossCash_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_ANNUAL_GROWTH_GROSS_CASH parameter : ",e);
                                scoringParameterRequest.setAvgAnnualGrowthGrossCash_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.AVERAGE_ANNUAL_GROWTH_NET_SALE: {

                            try {
                                Double domesticSalesTy = operatingStatementDetailsTY.getDomesticSales();
                                if (CommonUtils.isObjectNullOrEmpty(domesticSalesTy))
                                    domesticSalesTy = 0.0;
                                Double exportSalesTy = operatingStatementDetailsTY.getExportSales();
                                if (CommonUtils.isObjectNullOrEmpty(exportSalesTy))
                                    exportSalesTy = 0.0;

                                Double domesticSalesSy = operatingStatementDetailsSY.getDomesticSales();
                                if (CommonUtils.isObjectNullOrEmpty(domesticSalesSy))
                                    domesticSalesSy = 0.0;

                                Double exportSalesSy = operatingStatementDetailsSY.getExportSales();
                                if (CommonUtils.isObjectNullOrEmpty(exportSalesSy))
                                    exportSalesSy = 0.0;


                                Double domesticSalesFy = operatingStatementDetailsFY.getDomesticSales();
                                if (CommonUtils.isObjectNullOrEmpty(domesticSalesFy))
                                    domesticSalesFy = 0.0;

                                Double exportSalesFy = operatingStatementDetailsFY.getExportSales();
                                if (CommonUtils.isObjectNullOrEmpty(exportSalesFy))
                                    exportSalesFy = 0.0;

                                Double totalSale_FY = 0.0;
                                if (domesticSalesFy + exportSalesFy == 0.0) {
                                    totalSale_FY = 1.0;
                                } else {
                                    totalSale_FY = domesticSalesFy + exportSalesFy;
                                }

                                Double totalSale_SY = 0.0;
                                if (domesticSalesSy + exportSalesSy == 0.0) {
                                    totalSale_SY = 1.0;
                                } else {
                                    totalSale_SY = domesticSalesSy + exportSalesSy;
                                }

                                Double totalSale_TY = 0.0;
                                if (domesticSalesTy + exportSalesTy == 0.0) {
                                    totalSale_TY = 1.0;
                                } else {
                                    totalSale_TY = domesticSalesTy + exportSalesTy;
                                }

                                scoringParameterRequest.setTotalSaleFy(totalSale_FY);
                                scoringParameterRequest.setTotalSaleSy(totalSale_SY);
                                scoringParameterRequest.setTotalSaleTy(totalSale_TY);
                                scoringParameterRequest.setAvgAnnualGrowthNetSale_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_ANNUAL_GROWTH_NET_SALE parameter : ",e);
                                scoringParameterRequest.setAvgAnnualGrowthNetSale_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.AVERAGE_EBIDTA: {

                            try {
                                Double profitBeforeTaxOrLossTy = operatingStatementDetailsTY.getProfitBeforeTaxOrLoss();
                                if (CommonUtils.isObjectNullOrEmpty(profitBeforeTaxOrLossTy))
                                    profitBeforeTaxOrLossTy = 0.0;


                                Double interestTy = operatingStatementDetailsTY.getInterest();
                                if (CommonUtils.isObjectNullOrEmpty(interestTy))
                                    interestTy = 0.0;


                                Double profitBeforeTaxOrLossSy = operatingStatementDetailsSY.getProfitBeforeTaxOrLoss();
                                if (CommonUtils.isObjectNullOrEmpty(profitBeforeTaxOrLossSy))
                                    profitBeforeTaxOrLossSy = 0.0;


                                Double interestSy = operatingStatementDetailsSY.getInterest();
                                if (CommonUtils.isObjectNullOrEmpty(interestSy))
                                    interestSy = 0.0;


                                Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                                if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                                    depreciationTy = 0.0;


                                Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                                if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                                    depreciationSy = 0.0;


                                Double termLoansTy = liabilitiesDetailsTY.getTermLoans();
                                if (CommonUtils.isObjectNullOrEmpty(termLoansTy))
                                    termLoansTy = 0.0;


                                scoringParameterRequest.setProfitBeforeTaxOrLossTy(profitBeforeTaxOrLossTy);
                                scoringParameterRequest.setProfitBeforeTaxOrLossSy(profitBeforeTaxOrLossSy);
                                scoringParameterRequest.setInterestTy(interestTy);
                                scoringParameterRequest.setInterestSy(interestSy);
                                scoringParameterRequest.setDepriciationTy(depreciationTy);
                                scoringParameterRequest.setDepriciationSy(depreciationSy);
                                scoringParameterRequest.setTermLoanTy(termLoansTy);
                                scoringParameterRequest.setLoanAmount(loanAmount);

                                scoringParameterRequest.setAvgEBIDTA_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_EBIDTA parameter : ",e);
                                scoringParameterRequest.setAvgEBIDTA_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS: {

                            try {

                                Double netProfitOrLossTY = operatingStatementDetailsTY.getNetProfitOrLoss();
                                if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossTY))
                                    netProfitOrLossTY = 0.0;

                                Double netProfitOrLossSY = operatingStatementDetailsSY.getNetProfitOrLoss();
                                if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossSY))
                                    netProfitOrLossSY = 0.0;

                                Double interestTy = operatingStatementDetailsTY.getInterest();
                                if (CommonUtils.isObjectNullOrEmpty(interestTy))
                                    interestTy = 0.0;

                                Double interestSy = operatingStatementDetailsSY.getInterest();
                                if (CommonUtils.isObjectNullOrEmpty(interestSy))
                                    interestSy = 0.0;

                                Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                                if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                                    depreciationTy = 0.0;

                                Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                                if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                                    depreciationSy = 0.0;

                                Double totalAsset = assetsDetailsTY.getTotalAssets();
                                if (CommonUtils.isObjectNullOrEmpty(totalAsset))
                                    totalAsset = 0.0;

                                scoringParameterRequest.setNetProfitOrLossSY(netProfitOrLossSY);
                                scoringParameterRequest.setNetProfitOrLossTY(netProfitOrLossTY);
                                scoringParameterRequest.setInterestSy(interestSy);
                                scoringParameterRequest.setInterestTy(interestTy);
                                scoringParameterRequest.setDepriciationSy(depreciationSy);
                                scoringParameterRequest.setDepriciationTy(depreciationTy);
                                scoringParameterRequest.setTotalAssetTy(totalAsset);

                                scoringParameterRequest.setAvgAnnualGrossCashAccuruals_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS parameter : ",e);
                                scoringParameterRequest.setAvgAnnualGrossCashAccuruals_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.AVERAGE_INTEREST_COV_RATIO: {
                            try {
                                Double opProfitBeforeIntrestTy = operatingStatementDetailsTY.getOpProfitBeforeIntrest();
                                if (CommonUtils.isObjectNullOrEmpty(opProfitBeforeIntrestTy))
                                    opProfitBeforeIntrestTy = 0.0;


                                Double opProfitBeforeIntrestSy = operatingStatementDetailsSY.getOpProfitBeforeIntrest();
                                if (CommonUtils.isObjectNullOrEmpty(opProfitBeforeIntrestSy))
                                    opProfitBeforeIntrestSy = 0.0;

                                Double interestTy = operatingStatementDetailsTY.getInterest();
                                if (CommonUtils.isObjectNullOrEmpty(interestTy))
                                    interestTy = 0.0;

                                Double interestSy = operatingStatementDetailsSY.getInterest();
                                if (CommonUtils.isObjectNullOrEmpty(interestSy))
                                    interestSy = 0.0;

                                scoringParameterRequest.setOpProfitBeforeInterestTy(opProfitBeforeIntrestTy);
                                scoringParameterRequest.setOpProfitBeforeInterestSy(opProfitBeforeIntrestSy);
                                scoringParameterRequest.setInterestTy(interestTy);
                                scoringParameterRequest.setInterestSy(interestSy);

                                scoringParameterRequest.setAvgInterestCovRatio_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting AVERAGE_INTEREST_COV_RATIO parameter : ",e);
                                scoringParameterRequest.setAvgInterestCovRatio_p(false);
                            }

                            break;
                        }
                        case ScoreParameter.NO_OF_CUSTOMER: {
                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getNoOfCustomer())) {
                                    scoringParameterRequest.setNoOfCustomenr(gstCalculation.getNoOfCustomer());
                                    scoringParameterRequest.setNoOfCustomer_p(true);
                                } else {
                                    scoringParameterRequest.setNoOfCustomer_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting NO_OF_CUSTOMER parameter : ",e);
                                scoringParameterRequest.setNoOfCustomer_p(false);
                                /*map.put("NO_OF_CUSTOMER",null);*/
                            }
                            break;
                        }
                        case ScoreParameter.CONCENTRATION_CUSTOMER: {
                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getConcentration())) {
                                    scoringParameterRequest.setConcentrationCustomer(gstCalculation.getConcentration());
                                    scoringParameterRequest.setConcentrationCustomer_p(true);
                                } else {
                                    scoringParameterRequest.setConcentrationCustomer_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting CONCENTRATION_CUSTOMER parameter : ",e);
                                scoringParameterRequest.setConcentrationCustomer_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.CREDIT_SUMMATION: {

                            Double totalCredit = null;
                            Double projctedSales = null;
                            Integer noOfMonths = 1;

                            // start get total credit from Analyser
                            ReportRequest reportRequest = new ReportRequest();
                            reportRequest.setApplicationId(applicationId);
                            try {
                                AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);
                                Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                        Data.class);

                                if(!CommonUtils.isListNullOrEmpty(data.getMonthlyDetailList().getMonthlyDetails())){
                                    noOfMonths = data.getMonthlyDetailList().getMonthlyDetails().size();
                                }

                                if (!CommonUtils.isObjectNullOrEmpty(analyzerResponse.getData())) {
                                    {
                                        if (!CommonUtils.isObjectNullOrEmpty(data.getTotalCredit())) {
                                            totalCredit = data.getTotalCredit();
                                        } else {
                                            totalCredit = 0.0;
                                        }

                                    }

                                }
                            } catch (Exception e) {
                                totalCredit = 0.0;
                                logger.error("error while calling analyzer client : ",e);
                            }

                            totalCredit=totalCredit/noOfMonths;

                            // end get total credit from Analyser

                            // start get projected sales from GST client

                            if(!CommonUtils.isObjectNullOrEmpty(gstCalculation.getHistoricalSales())) {
                                projctedSales = gstCalculation.getHistoricalSales()/12;
                            }
                            else
                            {
                                projctedSales = gstCalculation.getProjectedSales()/12;
                            }

                            // end get projected sales from GST client


                            scoringParameterRequest.setTotalCredit(totalCredit);
                            scoringParameterRequest.setProjectedSale(projctedSales);
                            scoringParameterRequest.setCreditSummation_p(true);

                            break;
                        }
                        case ScoreParameter.AGE: {
                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDob())) {
                                    scoringParameterRequest.setAge(Math.ceil(CommonUtils.getAgeFromBirthDate(mainDirectorBackgroundDetail.getDob()).doubleValue()));
                                    scoringParameterRequest.setAge_p(true);
                                } else {
                                    scoringParameterRequest.setAge_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting AGE parameter",e);
                                scoringParameterRequest.setAge_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.NO_OF_CHILDREN: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getNoOfChildren())) {
                                    scoringParameterRequest.setNoOfChildren(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getNoOfChildren().doubleValue());
                                    scoringParameterRequest.setNoOfChildren_p(true);
                                } else {
                                    scoringParameterRequest.setNoOfChildren_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting NO_OF_CHILDREN parameter",e);
                                scoringParameterRequest.setNoOfChildren_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.OWNING_HOUSE: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOwningHouse())) {
                                    scoringParameterRequest.setOwningHouse(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOwningHouse().longValue());
                                    scoringParameterRequest.setOwningHouse_p(true);
                                } else {
                                    scoringParameterRequest.setOwningHouse_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting OWNING_HOUSE parameter : ",e);
                                scoringParameterRequest.setOwningHouse_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.ACADEMIC_QUALIFICATION: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getEducationalStatus())) {
                                    scoringParameterRequest.setAcadamicQualification(mainDirectorBackgroundDetail.getEducationalStatus().longValue());
                                    scoringParameterRequest.setAcadamicQualification_p(true);
                                } else {
                                    scoringParameterRequest.setAcadamicQualification_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting ACADEMIC_QUALIFICATION parameter : ",e);
                                scoringParameterRequest.setAcadamicQualification_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.EXPERIENCE_IN_THE_LINE_OF_TRADE: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getTotalExperience())) {
                                    scoringParameterRequest.setExperienceInLineOfBusiness(mainDirectorBackgroundDetail.getTotalExperience());
                                    scoringParameterRequest.setExpLineOfTrade_p(true);
                                } else {
                                    scoringParameterRequest.setExpLineOfTrade_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting EXPERIENCE_IN_THE_LINE_OF_TRADE parameter : ",e);
                                scoringParameterRequest.setExpLineOfTrade_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.SPOUSE_DETAILS: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getSpouseDetail())) {
                                    scoringParameterRequest.setSpouceDetails(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getSpouseDetail().longValue());
                                    scoringParameterRequest.setSpouseDetails_p(true);
                                } else {
                                    scoringParameterRequest.setSpouseDetails_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting SPOUSE_DETAILS parameter : ",e);
                                scoringParameterRequest.setSpouseDetails_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.ASSESSED_FOR_INCOME_TAX: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAssessedForIt())) {
                                    scoringParameterRequest.setAssessedFOrIT(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAssessedForIt().longValue());
                                    scoringParameterRequest.setAssessedForIncomeTax_p(true);
                                } else {
                                    scoringParameterRequest.setAssessedForIncomeTax_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting ASSESSED_FOR_INCOME_TAX parameter : ",e);
                                scoringParameterRequest.setAssessedForIncomeTax_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.HAVE_LIFE_INSURANCE_POLICY: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getHaveLiPolicy())) {
                                    scoringParameterRequest.setHaveLIPolicy(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getHaveLiPolicy().longValue());
                                    scoringParameterRequest.setHaveLifeIncPolicy_p(true);
                                } else {
                                    scoringParameterRequest.setHaveLifeIncPolicy_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting HAVE_LIFE_INSURANCE_POLICY parameter : ",e);
                                scoringParameterRequest.setHaveLifeIncPolicy_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.YEARS_IN_BUSINESS: {
                            try {

                            	Double yearsInBusiness = null;
                            	Integer yearsInBetween = corporateApplicantDetail.getBusinessSinceYear();
                            	Integer monthsDiff = null;
                            	if(yearsInBetween == null) {
                            		java.util.Calendar todayDate = java.util.Calendar.getInstance();
                                    todayDate.setTime(new Date());

                                    yearsInBetween = todayDate.get(java.util.Calendar.YEAR) - corporateApplicantDetail.getEstablishmentYear();

                                    monthsDiff = todayDate.get(java.util.Calendar.MONTH) - corporateApplicantDetail.getEstablishmentMonth();

                                    yearsInBusiness = (((double)yearsInBetween * 12 + (double)monthsDiff) / 12);
                            	}else {
                            		monthsDiff = corporateApplicantDetail.getBusinessSinceMonth();
                            		if(monthsDiff > 6)
                            			yearsInBusiness = (double)yearsInBetween + 1;
                            		else
                            			yearsInBusiness = (double)yearsInBetween;
                            	}

                                scoringParameterRequest.setYearsInBusiness(yearsInBusiness);
                                scoringParameterRequest.setYearsInBusiness_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting YEARS_IN_BUSINESS parameter : ",e);
                                scoringParameterRequest.setYearsInBusiness_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.REPAYMENT_PERIOD: {

                            try {

                                // get repayment period from one form // remaining
                                scoringParameterRequest.setRepaymentPeriod(5.0);
                                scoringParameterRequest.setRepaymentPeriod_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting REPAYMENT_PERIOD parameter : ",e);
                                scoringParameterRequest.setRepaymentPeriod_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.CONTINUOUS_NET_PROFIT: {

                            try {

                                Double netProfitOrLossFY = operatingStatementDetailsFY.getProfitBeforeTaxOrLoss();
                                Double netProfitOrLossSY = operatingStatementDetailsSY.getProfitBeforeTaxOrLoss();
                                Double netProfitOrLossTY = operatingStatementDetailsTY.getProfitBeforeTaxOrLoss();

                                scoringParameterRequest.setContinuousNetProfitOrLossFY(netProfitOrLossFY);
                                scoringParameterRequest.setContinuousNetProfitOrLossSY(netProfitOrLossSY);
                                scoringParameterRequest.setContinuousNetProfitOrLossTY(netProfitOrLossTY);

                                scoringParameterRequest.setContinousNetProfit_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting CONTINUOUS_NET_PROFIT parameter : ",e);
                                scoringParameterRequest.setContinousNetProfit_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.QUALITY_OF_RECEIVABLES: {

                            try {

                                Double totalSaleTY = operatingStatementDetailsTY.getDomesticSales() + operatingStatementDetailsTY.getExportSales();
                                Double grossSaleTy = operatingStatementDetailsTY.getTotalGrossSales();

                                scoringParameterRequest.setTotalSaleTy(totalSaleTY);
                                scoringParameterRequest.setGrossSaleTy(grossSaleTy);

                                scoringParameterRequest.setQualityOfReceivable_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting QUALITY_OF_RECEIVABLES parameter : ",e);
                                scoringParameterRequest.setQualityOfReceivable_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.QUALITY_OF_FINISHED_GOODS_INVENTORY: {

                            try {

                                Double totalCostSaleTy = operatingStatementDetailsTY.getTotalCostSales();
                                Double finishedGoodTy = assetsDetailsTY.getFinishedGoods();

                                scoringParameterRequest.setTotalCostSaleTy(totalCostSaleTy);
                                scoringParameterRequest.setFinishedGoodTy(finishedGoodTy);

                                scoringParameterRequest.setQualityOfFinishedGood_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting QUALITY_OF_FINISHED_GOODS_INVENTORY parameter : ",e);
                                scoringParameterRequest.setQualityOfFinishedGood_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.KNOW_HOW: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getKnowHow())) {
                                    scoringParameterRequest.setKnowHow(primaryCorporateDetail.getKnowHow().longValue());
                                    scoringParameterRequest.setKnowHow_p(true);
                                } else {
                                    scoringParameterRequest.setKnowHow_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting KNOW_HOW parameter : ",e);
                                scoringParameterRequest.setKnowHow_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.LINE_OF_ACTIVITY: {
                            scoringParameterRequest.setLineOfActivity(1l);
                            scoringParameterRequest.setLineOfActivity_p(true);

                            break;
                        }
                        case ScoreParameter.COMPETITION: {

                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCompetition())) {
                                    scoringParameterRequest.setCompetition(primaryCorporateDetail.getCompetition().longValue());
                                    scoringParameterRequest.setCompetition_p(true);
                                } else {
                                    scoringParameterRequest.setCompetition_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting COMPETITION parameter : ",e);
                                scoringParameterRequest.setCompetition_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.FACTORY_PREMISES: {
                            try {

                                if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getFactoryPremise())) {
                                    scoringParameterRequest.setFactoryPremises(primaryCorporateDetail.getFactoryPremise().longValue());
                                    scoringParameterRequest.setFactoryPremises_p(true);
                                } else {
                                    scoringParameterRequest.setFactoryPremises_p(false);
                                }
                            } catch (Exception e) {
                                logger.error("error while getting FACTORY_PREMISES parameter : ",e);
                                scoringParameterRequest.setFactoryPremises_p(false);
                            }
                            break;
                        }
                        case ScoreParameter.SALES_SHOW_A_RISING_TREND: {

                            try {

                                // getting Gst Current Year Sale from GST Client

                                if (!CommonUtils.isObjectNullOrEmpty(gstResponseScoring) && !CommonUtils.isObjectNullOrEmpty(gstResponseScoring.getData())) {
                                    scoringParameterRequest.setGstSaleCurrentYear((Double) gstResponseScoring.getData());
                                } else {
                                    scoringParameterRequest.setGstSaleCurrentYear(0.0);
                                    logger.error("Error while getting Gst data for Scoring from GST client");
                                }

                                scoringParameterRequest.setNetSaleFy(operatingStatementDetailsFY.getNetSales());
                                scoringParameterRequest.setNetSaleSy(operatingStatementDetailsSY.getNetSales());
                                scoringParameterRequest.setNetSaleTy(operatingStatementDetailsTY.getNetSales());

                                scoringParameterRequest.setSalesShowArisingTrend_p(true);

                            } catch (Exception e) {
                                logger.error("error while getting SALES_SHOW_A_RISING_TREND parameter : ",e);
                                scoringParameterRequest.setSalesShowArisingTrend_p(false);
                            }
                            break;
                        }
                        default : break;
                    }
                    //fundSeekerInputRequestList.add(fundSeekerInputRequest);
                }

                logger.info(MSG_SCORE_PARAMETER + scoringParameterRequest.toString());

                logger.info("----------------------------END-----------------------------------------------");
            }
            Gson g = new Gson();
            ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

            try {
                scoringRequestDetail.setApplicationId(applicationId);
                scoringRequestDetail.setRequest(g.toJson(scoringParameterRequest));
                scoringRequestDetail.setCreatedDate(new Date());
                scoringRequestDetail.setIsActive(true);
                scoringRequestDetailRepository.save(scoringRequestDetail);

                logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
            } catch (Exception e) {
                logger.error(CommonUtils.EXCEPTION,e);
            }
        }

        scoringRequest.setScoringParameterRequest(scoringParameterRequest);

        try {
            scoringResponseMain = scoringClient.calculateScore(scoringRequest);
        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }

        if (scoringResponseMain.getStatus() == HttpStatus.OK.value()) {
            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        } else {
            logger.error(ERROR_WHILE_CALLING_SCORING);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }
    
    @SuppressWarnings("unchecked")
	private void setBureauScore(List<ScoringRequestLoans> scorReqLoansList, Long orgId) throws Exception {
    	logger.info("Enter setBureauScore --------------------------------->");
    	//put SET
    	Set<Long> scoreModelIdList = new HashSet<Long>(); 
    	Long applicationId = null;
        for(ScoringRequestLoans scrReq : scorReqLoansList) {
        	applicationId = scrReq.getApplicationId();
        	logger.info("scrReq.getScoringModelId()------------------------------>" + scrReq.getScoringModelId());
        	scoreModelIdList.add(scrReq.getScoringModelId());
        }
        logger.info("Enter setBureauScore applicationId --------------------------------->" + applicationId);
        if(scoreModelIdList.isEmpty()) {
        	throw new Exception("Need to atlease one score model id to process check scoring.");
        }
        try {
        	List<Long> fieldMasterIdList = new ArrayList<Long>();
        	fieldMasterIdList.add(2l);
        	fieldMasterIdList.add(3l);
        	fieldMasterIdList.add(160l);
        	fieldMasterIdList.add(210l);
        	fieldMasterIdList.add(69l);
        	fieldMasterIdList.add(66l);
        	String value = loanRepository.getScoringMinAndMaxRangeValue(scoreModelIdList.stream().collect(Collectors.toList()), fieldMasterIdList);
        	if(value == null)
        		return;
        		//throw new Exception("Score model range is not found from database");
        	
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("applicationId", applicationId);
            List<ScoringCibilRequest> minAndMaxRanges = Arrays.asList(new ObjectMapper().readValue(value, ScoringCibilRequest[].class));
            
            for(Long modelId : scoreModelIdList) {
            	Map<String, Object> filedMap = new HashMap<String, Object>();
            	for(Long fieldMasterId : fieldMasterIdList) {
            		List<ScoringCibilRequest> filterList = minAndMaxRanges.stream().filter(a -> modelId.equals(a.getScoreModelId()) && fieldMasterId.equals(a.getFieldMasterId())).collect(Collectors.toList());
            		List<Map<String, Object>> subMapList = new ArrayList<Map<String,Object>>();
            		for(ScoringCibilRequest req : filterList) {
            			Map<String, Object> subMap = new HashMap<String, Object>();
                       	subMap.put("min", req.getMinRange());
                       	subMap.put("max", req.getMaxRange());
                       	subMap.put("score", req.getScore());
                       	subMap.put("description", req.getDescription());
                       	subMapList.add(subMap);
            		}
            		filedMap.put(fieldMasterId.toString(), subMapList);
            	}
            	map.put(modelId.toString(), filedMap);
            }
            logger.info("PREPARE MAP FOR CIBIL API CALL -----> " + MultipleJSONObjectHelper.getStringfromObject(map));
            
            CibilRequest cibilRequest = new CibilRequest();
            cibilRequest.setApplicantId(applicationId);
            cibilRequest.setDataInput(map);
            cibilRequest.setOrgId(orgId);
            CibilResponse response = cibilClient.getScoringResult(cibilRequest);
            if(response != null && response.getData() != null) {
            	Map<String,Object> mapRes = (Map<String,Object>) response.getData();
            	try {
            			saveBureauScoringResponse(mapRes, applicationId, null);            			
            	}catch(Exception e) {
            		logger.error("Error while saving Bureau Response ====>{}",e);
            	}
            	for(ScoringRequestLoans scrReq : scorReqLoansList) {
                	scrReq.setMapList((Map<String,Object>)mapRes.get(scrReq.getScoringModelId().toString()));
                }
            } else {
            	throw new Exception("Response from cibil integration is null or empty while set bureau score in calculate scoring " + applicationId);	
            }
		} catch (Exception e) {
			logger.error("Exception while Set Bureau Score from cibil integration ",e);
			throw new Exception("Application hash encountered error while set Bureau Score from cibil integraion ",e);
		}
        
    }
    @SuppressWarnings("unchecked")
	private void saveBureauScoringResponse(Map<String,Object> map,Long applicationId,Long fpProductId) {
    	BankBureauRequest bankBureauRequest = null;
//    	Map<String,Map<String,Object>>
    	for(Entry<String, Object> scoringSet : map.entrySet()) {
    		for(Entry<String, Map<String, Object>> fieldSet : ((Map<String,Map<String,Object>>)scoringSet).entrySet()) {
        		bankBureauRequest = new BankBureauRequest();
        		bankBureauRequest.setApplicationId(applicationId);
        		bankBureauRequest.setFpProductId(fpProductId);
        		bankBureauRequest.setType(com.capitaworld.service.matchengine.utils.CommonUtils.BankBureauResponseType.SCORING.getId());
        		bankBureauRequest.setFieldMasterId(Long.valueOf(fieldSet.getKey()));
        		bankBureauRequest.setScoringModelId(Long.valueOf(scoringSet.getKey()));
        		if(!CommonUtils.isObjectNullOrEmpty(fieldSet.getValue())) {
        			Map<String, Object> dataMap = fieldSet.getValue();
        			if(!CommonUtils.isObjectNullOrEmpty(dataMap.get("score"))) {
        				bankBureauRequest.setScore(Double.valueOf(dataMap.get("score").toString()));		
        			}
        			if(!CommonUtils.isObjectNullOrEmpty(dataMap.get("description"))) {
        				bankBureauRequest.setDescription(dataMap.get("description").toString());	
        			}
        			
        			if(!CommonUtils.isObjectNullOrEmpty(dataMap.get("totalEmiOfCompany"))) {
        				bankBureauRequest.setTotalComEmi(Double.valueOf(dataMap.get("totalEmiOfCompany").toString()));
        			}

					if(!CommonUtils.isObjectNullOrEmpty(dataMap.get("totalEmiOfDirector"))) {
						bankBureauRequest.setTotalDirEmi(Double.valueOf(dataMap.get("totalEmiOfDirector").toString()));
					}
					
					if(!CommonUtils.isObjectNullOrEmpty(dataMap.get("totalExistingLimit"))) {
						bankBureauRequest.setExistingLoanAmount(Double.valueOf(dataMap.get("totalExistingLimit").toString()));
					}
        		}
        		bankBureauResponseService.inActiveAndsaveScoring(bankBureauRequest);
        	}    		
    	}
    	
    }

    private ScoringCibilRequest filterScore(Map<String,Object> map, Long scoringModelId,Long fieldMasterId) {
		Object fieldMap = map.entrySet().stream().filter(x -> x.getKey().equalsIgnoreCase(fieldMasterId.toString())).map(x -> x.getValue()).findFirst().orElse(null);
		if(fieldMap == null) {
			logger.warn("No Object Found for Field master id == >{}-===Score ====>{}",fieldMasterId);			
		}
		logger.warn("Filtered Map ====>{} ===> by Field Master Id ====>{}",fieldMap,fieldMasterId);
		ScoringCibilRequest response = null;
		if(fieldMap instanceof ScoringCibilRequest) {
			response = (ScoringCibilRequest)fieldMap;
		}else if (fieldMap instanceof Map) {
			try {
				response = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>)fieldMap,ScoringCibilRequest.class );
			}catch(Exception e) {
				logger.error("Error while converting Map to Object to Scoring response from Bureau Integration Server",e);
			}
		}
		logger.info("Scoring CIbil Response == >{}",response);
		return response;
	}
    
    @Override
    public ResponseEntity<LoansResponse> calculateExistingBusinessScoringList(List<ScoringRequestLoans> scoringRequestLoansList) {

        ScoringResponse scoringResponseMain = null;

        List<ScoringRequest> scoringRequestList=new ArrayList<ScoringRequest>();

        ScoringParameterRequest scoringParameterRequest = null;
        boolean isCibilCheck = false;
        boolean result = false;
		Boolean isBureauExistingLoansDisplayActive = false;
        try {                                            
        	if(!scoringRequestLoansList.isEmpty()) {
        		logger.info("Enter in calculateExistingBusinessScoringList for check If Cibil API check or not");
        		Long applicationId = scoringRequestLoansList.get(0).getApplicationId();
        		//GET CAMPAIGN BANK ID FROM APPLICATION ID
        		Long orgId = loanRepository.getCampaignOrgIdByApplicationId(applicationId);
        		if(orgId == null)
        			orgId = 10l;
        		Object [] bankBureauFlags = loanRepository.getBankBureauFlags(orgId);
        		if(bankBureauFlags != null) {
        			result = (!CommonUtils.isObjectNullOrEmpty(bankBureauFlags[0]) && Boolean.valueOf(bankBureauFlags[0].toString()));
        			isBureauExistingLoansDisplayActive = (!CommonUtils.isObjectNullOrEmpty(bankBureauFlags[4]) && Boolean.valueOf(bankBureauFlags[4].toString()));
    			}
        		String checkAPI = loanRepository.getCommonPropertiesValue("CIBIL_BUREAU_API_START");
        		logger.info("Found Result For CIBIL API ----->" + result + " For Org ID ----" + orgId + "  And check API --- >" + checkAPI);
        		if(result && "true".equals(checkAPI)) {
        			isCibilCheck = true;
        			setBureauScore(scoringRequestLoansList,orgId);	
        		}
        	}
		} catch (Exception e) {
			logger.error("Exeption while set Bureau score " + e.getMessage());
            return new ResponseEntity<LoansResponse>(new LoansResponse("Application has encountered error while check CIBIL bureau score.", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
        

        for(ScoringRequestLoans scoringRequestLoans:scoringRequestLoansList)
        {
            Long scoreModelId = scoringRequestLoans.getScoringModelId();
            Long applicationId = scoringRequestLoans.getApplicationId();
            Long fpProductId = scoringRequestLoans.getFpProductId();

            ///////// Get Financial Type Id from ITR////////

            //Integer financialTypeId = 3;

          /*  List<ScoringRequestDetail> scoringRequestDetailList = scoringRequestDetailRepository.getScoringRequestDetailByApplicationIdAndIsActive(applicationId);


            ScoringRequestDetail scoringRequestDetailSaved = new ScoringRequestDetail();

            if (scoringRequestDetailList.size() > 0) {
                logger.info("Getting Old Scoring request Data for  =====> " + applicationId);
                scoringRequestDetailSaved = scoringRequestDetailList.get(0);
                Gson gson = new Gson();
                scoringParameterRequest = gson.fromJson(scoringRequestDetailSaved.getRequest(), ScoringParameterRequest.class);
            }*/


            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.EXISTING_BUSINESS);
            scoringRequest.setEligibleLoanAmountCircular(scoringRequestLoans.getEligibleLoanAmountCircular());
            scoringRequest.setMap(scoringRequestLoans.getMapList());



            if (CommonUtils.isObjectNullOrEmpty(scoringParameterRequest)) {


               /* ITRConnectionResponse itrConnectionResponse = null;
                try {
                    itrConnectionResponse = itrClient.getIsUploadAndYearDetails(applicationId);

                    if (!CommonUtils.isObjectNullOrEmpty(itrConnectionResponse) && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse.getData())) {
                        Map<String, Object> map = (Map<String, Object>) itrConnectionResponse.getData();
                        ITRBasicDetailsResponse res = MultipleJSONObjectHelper.getObjectFromMap(map, ITRBasicDetailsResponse.class);
                        if (!CommonUtils.isObjectNullOrEmpty(res) && !CommonUtils.isObjectNullOrEmpty(res.getItrFinancialType())) {
                            financialTypeId = Integer.valueOf(res.getItrFinancialType());
                        }
                    }
                } catch (IOException e) {
                    logger.error("error while getting Financial Type Id from itr response : ",e);
                }*/

                if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                    scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
                } else {
                    scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
                }


                logger.info("Financial Type Id ::::::::::::::::================>" + scoringRequest.getFinancialTypeId());

                scoringParameterRequest=new ScoringParameterRequest();

                logger.info("Scoring Data Fetched First Time  =====> " + applicationId);

                logger.info("----------------------------START EXISTING LOAN ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

                // start Get GST Parameter

                String gstNumber = corporateApplicantDetailRepository.getGstInByApplicationId(applicationId);
                Double loanAmount = primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);

                CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);


                GstResponse gstResponse = null;
                GstResponse gstResponseScoring = null;
                GstCalculation gstCalculation = new GstCalculation();

                try {
                    GSTR1Request gstr1Request = new GSTR1Request();
                    gstr1Request.setGstin(gstNumber);
                    gstr1Request.setApplicationId(applicationId);
                    gstResponse = gstClient.getCalculations(gstr1Request);

                    if (!CommonUtils.isObjectNullOrEmpty(gstResponse) && !CommonUtils.isObjectNullOrEmpty(gstResponse.getData())) {
                        gstCalculation = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) gstResponse.getData(),
                                GstCalculation.class);
                    }

                } catch (Exception e) {
                    logger.error("error while getting GST parameter : ",e);
                }


                // get GST Data for Sales Show A Rising Trend

                try {
                    GSTR1Request gstr1Request = new GSTR1Request();
                    gstr1Request.setGstin(gstNumber);
                    gstr1Request.setApplicationId(applicationId);
                    gstResponseScoring = gstClient.getCalculationForScoring(gstr1Request);
                } catch (Exception e) {
                    logger.error("error while getting GST parameter for GST Sales Show A Rising Trend : ",e);
                }

                // end Get GST Parameter

                int currentYear = getFinYear(applicationId);
                if (CommonUtils.isObjectNullOrEmpty(currentYear)) {
                    logger.error("error while getting current year from itr");
                    LoansResponse loansResponse = new LoansResponse("error while getting current year from itr.", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
                }

                // CMA
                OperatingStatementDetails operatingStatementDetailsFY = new OperatingStatementDetails();
                OperatingStatementDetails operatingStatementDetailsSY = new OperatingStatementDetails();
                OperatingStatementDetails operatingStatementDetailsTY = new OperatingStatementDetails();


                LiabilitiesDetails liabilitiesDetailsFY = new LiabilitiesDetails();
                LiabilitiesDetails liabilitiesDetailsSY = new LiabilitiesDetails();
                LiabilitiesDetails liabilitiesDetailsTY = new LiabilitiesDetails();

                AssetsDetails assetsDetailsFY = new AssetsDetails();
                AssetsDetails assetsDetailsSY = new AssetsDetails();
                AssetsDetails assetsDetailsTY = new AssetsDetails();

                if (ScoreParameter.FinancialTypeForITR.THREE_YEAR_ITR == scoringRequest.getFinancialTypeId()) {
                    operatingStatementDetailsTY = operatingStatementDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                    operatingStatementDetailsSY = operatingStatementDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 2 + "");
                    operatingStatementDetailsFY = operatingStatementDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 3 + "");

                    liabilitiesDetailsTY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                    liabilitiesDetailsSY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 2 + "");
                    liabilitiesDetailsFY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 3 + "");

                    assetsDetailsTY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                    assetsDetailsSY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 2 + "");
                    assetsDetailsFY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 3 + "");
                } else if (ScoreParameter.FinancialTypeForITR.ONE_YEAR_ITR == scoringRequest.getFinancialTypeId()) {
                    operatingStatementDetailsTY = operatingStatementDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                    liabilitiesDetailsTY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                    assetsDetailsTY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                } else if (ScoreParameter.FinancialTypeForITR.PRESUMPTIVE == scoringRequest.getFinancialTypeId()) {
                    operatingStatementDetailsTY = operatingStatementDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                    liabilitiesDetailsTY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                    assetsDetailsTY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
                }

                ///////////////

                // Get Director Background detail

                DirectorBackgroundDetail mainDirectorBackgroundDetail = directorBackgroundDetailsRepository.getMainDirectorByApplicationId(applicationId);

                // get Primary Corporate Detail

                PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);

                // GET SCORE CORPORATE LOAN PARAMETERS


                if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                    // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                    ScoringResponse scoringResponse = null;
                    try {
                        scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                    } catch (Exception e) {
                        logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                    }

                    List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();

                    //List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

                    logger.info("dataList=====================================>>>>>>>>>>>>>>>>>>>>>>" + dataList.size());

                    for (int i=0;i<dataList.size();i++){

                        ModelParameterResponse modelParameterResponse = null;
                        try {
                            modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                    ModelParameterResponse.class);
                        } catch (IOException e) {
                            logger.error(CommonUtils.EXCEPTION,e);
                        }

                /*FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                fundSeekerInputRequest.setName(modelParameterResponse.getName());*/

                        switch (modelParameterResponse.getName()) {

                            case ScoreParameter.COMBINED_NETWORTH: {
                                try {
                                    Double networthSum = directorBackgroundDetailsRepository.getSumOfDirectorsNetworth(applicationId);
                                    if (CommonUtils.isObjectNullOrEmpty(networthSum))
                                        networthSum = 0.0;

                                    Double termLoansTy = liabilitiesDetailsTY.getTermLoans();
                                    if (CommonUtils.isObjectNullOrEmpty(termLoansTy))
                                        termLoansTy = 0.0;

                                    scoringParameterRequest.setNetworthSum(networthSum);
                                    scoringParameterRequest.setTermLoanTy(termLoansTy);
                                    scoringParameterRequest.setLoanAmount(loanAmount);
                                    scoringParameterRequest.setCombinedNetworth_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting COMBINED_NETWORTH parameter : ",e);
                                    scoringParameterRequest.setCombinedNetworth_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.CUSTOMER_ASSOCIATE_CONCERN: {
                            	if(!isCibilCheck) {
                            		Double customer_ass_concern_year = null;
                                    try {

                                        CibilResponse cibilResponse = cibilClient.getDPDYears(applicationId);
                                        if (!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isObjectNullOrEmpty(cibilResponse.getData())) {
                                            customer_ass_concern_year = (Double) cibilResponse.getData();

                                            scoringParameterRequest.setCustomerAssociateConcern(customer_ass_concern_year);
                                            scoringParameterRequest.setCustomerAsscociateConcern_p(true);
                                        } else {
                                            scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                                        }

                                    } catch (Exception e) {
                                        logger.error("error while getting CUSTOMER_ASSOCIATE_CONCERN parameter from CIBIL client : ",e);
                                        scoringParameterRequest.setCustomerAsscociateConcern_p(false);
                                    }	
                            	}
                                break;
                            }
                            case ScoreParameter.CIBIL_TRANSUNION_SCORE: {
                            	if(!isCibilCheck) {
                            		Double cibil_score_avg_promotor = null;
                                    try {

                                        CibilRequest cibilRequest = new CibilRequest();
                                        cibilRequest.setApplicationId(applicationId);

                                        CibilResponse cibilResponse = cibilClient.getCibilScore(cibilRequest);
                                        if (!CommonUtils.isObjectNullOrEmpty(cibilResponse.getData())) {
                                            cibil_score_avg_promotor = (Double) cibilResponse.getData();
                                            scoringParameterRequest.setCibilTransuniunScore(cibil_score_avg_promotor);
                                            scoringParameterRequest.setCibilTransunionScore_p(true);
                                        } else {
                                            scoringParameterRequest.setCibilTransunionScore_p(false);
                                        }
                                    } catch (Exception e) {
                                        logger.error("error while getting CIBIL_TRANSUNION_SCORE parameter from CIBIL client : ",e);
                                        scoringParameterRequest.setCibilTransunionScore_p(false);
                                    }	
                            	}
                                break;
                            }

                            case ScoreParameter.EXPERIENCE_IN_THE_BUSINESS: {
                                Double directorExperience = directorBackgroundDetailsRepository.getMaxOfDirectorsExperience(applicationId);

                                if (!CommonUtils.isObjectNullOrEmpty(directorExperience)) {
                                    scoringParameterRequest.setExperienceInTheBusiness(directorExperience);
                                    scoringParameterRequest.setExperienceInTheBusiness_p(true);
                                } else {
                                    scoringParameterRequest.setExperienceInTheBusiness_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.DEBT_EQUITY_RATIO: {

                                try {
                            /*Double debt = liabilitiesDetailsTY.getSubTotalA() +
                                    liabilitiesDetailsTY.getShortTermBorrowingFromOthers() +
                                    liabilitiesDetailsTY.getTotalTermLiabilities() -
                                    liabilitiesDetailsTY.getPreferencesShares() +
                                    liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther() +
                                    liabilitiesDetailsTY.getOtherNclOthers() +
                                    liabilitiesDetailsTY.getMinorityInterest() +
                                    liabilitiesDetailsTY.getDeferredTaxLiability();*/

                                    // 27-9-2018 9:19 PM Rahul Khudai Removed iabilitiesDetailsTY.getSubTotalA()
                                    // + liabilitiesDetailsTY.getShortTermBorrowingFromOthers()  from Debt calculation

                                    // Before central bank changes
                                    /*Double debt = liabilitiesDetailsTY.getTotalTermLiabilities() -
                                            liabilitiesDetailsTY.getPreferencesShares() +
                                            liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther() +
                                            liabilitiesDetailsTY.getOtherNclOthers() +
                                            liabilitiesDetailsTY.getMinorityInterest() +
                                            liabilitiesDetailsTY.getDeferredTaxLiability();


                                    if (CommonUtils.isObjectNullOrEmpty(debt))
                                        debt = 0.0;


                                    Double equity = liabilitiesDetailsTY.getPreferencesShares() +
                                            liabilitiesDetailsTY.getNetWorth() -
                                            liabilitiesDetailsTY.getMinorityInterest() -
                                            liabilitiesDetailsTY.getDeferredTaxLiability();
                                    if (CommonUtils.isObjectNullOrEmpty(debt))
                                        equity = 0.0;*/

                                    // After central bank changes
                                    Double[] fyDebtAndEquityValues = getDebtAndEquityValue(liabilitiesDetailsFY);
                                    Double[] syDebtAndEquityValues = getDebtAndEquityValue(liabilitiesDetailsSY);
                                    Double[] tyDebtAndEquityValues = getDebtAndEquityValue(liabilitiesDetailsTY);

                                    scoringParameterRequest.setDebtFY(fyDebtAndEquityValues[0]);
                                    scoringParameterRequest.setDebtSY(syDebtAndEquityValues[0]);
                                    scoringParameterRequest.setDebtTY(tyDebtAndEquityValues[0]);

                                    scoringParameterRequest.setEquityFY(fyDebtAndEquityValues[1]);
                                    scoringParameterRequest.setEquitySY(syDebtAndEquityValues[1]);
                                    scoringParameterRequest.setEquityTY(tyDebtAndEquityValues[1]);

                                    scoringParameterRequest.setDebtEquityRatio_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting DEBT_EQUITY_RATIO parameter : ",e);
                                    scoringParameterRequest.setDebtEquityRatio_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.TOL_TNW: {

                                try {

                                    //Before central bank changes
                                    /*Double tol = liabilitiesDetailsTY.getTotalOutsideLiabilities();
                                    if (CommonUtils.isObjectNullOrEmpty(tol))
                                        tol = 0.0;

                                    Double tnw = assetsDetailsTY.getTangibleNetWorth();
                                    if (CommonUtils.isObjectNullOrEmpty(tnw))
                                        tnw = 0.0;*/

                                    //After central bank changes
                                    Double[] fyTolTnwValues =getTolTnwValues(liabilitiesDetailsFY,assetsDetailsFY);
                                    Double[] syTolTnwValues =getTolTnwValues(liabilitiesDetailsSY,assetsDetailsSY);
                                    Double[] tyTolTnwValues =getTolTnwValues(liabilitiesDetailsTY,assetsDetailsTY);

                                    scoringParameterRequest.setTolFY(fyTolTnwValues[0]);
                                    scoringParameterRequest.setTolSY(syTolTnwValues[0]);
                                    scoringParameterRequest.setTolTY(tyTolTnwValues[0]);

                                    scoringParameterRequest.setTnwFY(fyTolTnwValues[1]);
                                    scoringParameterRequest.setTnwSY(syTolTnwValues[1]);
                                    scoringParameterRequest.setTnwTY(tyTolTnwValues[1]);

                                    scoringParameterRequest.setTolTnw_p(true);
                                    scoringParameterRequest.setLoanAmount(loanAmount);

                                } catch (Exception e) {
                                    logger.error("error while getting TOL_TNW parameter : ",e);
                                    scoringParameterRequest.setTolTnw_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.AVERAGE_CURRENT_RATIO: {
                                try {

                                    // Before central bank changes
                                    /*Double currentRatio = (assetsDetailsTY.getCurrentRatio() + assetsDetailsSY.getCurrentRatio()) / 2;
                                    if (CommonUtils.isObjectNullOrEmpty(currentRatio))
                                        currentRatio = 0.0;*/

                                    // After central bank changes/
                                    Double currentRatioFY = (assetsDetailsFY.getCurrentRatio()) ;
                                    Double currentRatioSY = (assetsDetailsSY.getCurrentRatio()) ;
                                    Double currentRatioTY = (assetsDetailsTY.getCurrentRatio()) ;

                                    if (CommonUtils.isObjectNullOrEmpty(currentRatioFY))
                                        currentRatioFY = 0.0;
                                    if (CommonUtils.isObjectNullOrEmpty(currentRatioSY))
                                        currentRatioSY = 0.0;
                                    if (CommonUtils.isObjectNullOrEmpty(currentRatioTY))
                                        currentRatioTY = 0.0;

                                    scoringParameterRequest.setAvgCurrentRatioFY(currentRatioFY);
                                    scoringParameterRequest.setAvgCurrentRatioSY(currentRatioSY);
                                    scoringParameterRequest.setAvgCurrentRatioTY(currentRatioTY);
                                    scoringParameterRequest.setAvgCurrentRatio_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_CURRENT_RATIO parameter : ",e);
                                    scoringParameterRequest.setAvgCurrentRatio_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.WORKING_CAPITAL_CYCLE: {

                                try {

                                    /*
                                    *  Double debtorsDays = null;
                                    if ((operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome()) != 0.0) {
                                        debtorsDays = ((assetsDetailsTY.getReceivableOtherThanDefferred() + assetsDetailsTY.getExportReceivables()) / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                                    }
                                    if (CommonUtils.isObjectNullOrEmpty(debtorsDays))
                                        debtorsDays = 0.0;


                                    /////////////

                                    Double averageInventory = (operatingStatementDetailsTY.getAddOperatingStockFg() + operatingStatementDetailsTY.getDeductClStockFg()) / 2;
                                    if (CommonUtils.isObjectNullOrEmpty(averageInventory))
                                        averageInventory = 0.0;

                                    Double cogs = operatingStatementDetailsTY.getRawMaterials() + operatingStatementDetailsTY.getAddOperatingStockFg() - operatingStatementDetailsTY.getDeductClStockFg();
                                    if (CommonUtils.isObjectNullOrEmpty(cogs))
                                        cogs = 0.0;


                                    /////////////

                                    Double creditorsDays = null;
                                    if ((operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome()) != 0) {
                                        creditorsDays = (liabilitiesDetailsTY.getSundryCreditors() / (operatingStatementDetailsTY.getTotalGrossSales() - operatingStatementDetailsTY.getAddOtherRevenueIncome())) * 365;
                                    }
                                    if (CommonUtils.isObjectNullOrEmpty(creditorsDays))
                                        creditorsDays = 0.0;


                                    scoringParameterRequest.setDebtorsDays(debtorsDays);
                                    scoringParameterRequest.setAvgInventory(averageInventory);
                                    scoringParameterRequest.setCogs(cogs);
                                    scoringParameterRequest.setCreditorsDays(creditorsDays);
                                    * */

                                    Double[]  fyDebtorsCreditorsCogsAvgInvValues = getDebtorsCreditorsCogsAvgInvValues(operatingStatementDetailsFY,assetsDetailsFY,liabilitiesDetailsFY);
                                    Double[]  syDebtorsCreditorsCogsAvgInvValues = getDebtorsCreditorsCogsAvgInvValues(operatingStatementDetailsSY,assetsDetailsSY,liabilitiesDetailsSY);
                                    Double[]  tyDebtorsCreditorsCogsAvgInvValues = getDebtorsCreditorsCogsAvgInvValues(operatingStatementDetailsTY,assetsDetailsTY,liabilitiesDetailsTY);

                                    scoringParameterRequest.setDebtorsDaysFY(fyDebtorsCreditorsCogsAvgInvValues[0]);
                                    scoringParameterRequest.setAvgInventoryFY(fyDebtorsCreditorsCogsAvgInvValues[1]);
                                    scoringParameterRequest.setCogsFY(fyDebtorsCreditorsCogsAvgInvValues[2]);
                                    scoringParameterRequest.setCreditorsDaysFY(fyDebtorsCreditorsCogsAvgInvValues[3]);

                                    scoringParameterRequest.setDebtorsDaysSY(syDebtorsCreditorsCogsAvgInvValues[0]);
                                    scoringParameterRequest.setAvgInventorySY(syDebtorsCreditorsCogsAvgInvValues[1]);
                                    scoringParameterRequest.setCogsSY(syDebtorsCreditorsCogsAvgInvValues[2]);
                                    scoringParameterRequest.setCreditorsDaysSY(syDebtorsCreditorsCogsAvgInvValues[3]);

                                    scoringParameterRequest.setDebtorsDaysTY(tyDebtorsCreditorsCogsAvgInvValues[0]);
                                    scoringParameterRequest.setAvgInventoryTY(tyDebtorsCreditorsCogsAvgInvValues[1]);
                                    scoringParameterRequest.setCogsTY(tyDebtorsCreditorsCogsAvgInvValues[2]);
                                    scoringParameterRequest.setCreditorsDaysTY(tyDebtorsCreditorsCogsAvgInvValues[3]);

                                    scoringParameterRequest.setWorkingCapitalCycle_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting WORKING_CAPITAL_CYCLE parameter : ",e);
                                    scoringParameterRequest.setWorkingCapitalCycle_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.AVERAGE_ANNUAL_GROWTH_GROSS_CASH: {
                                try {
                                    Double netProfitOrLossTY = operatingStatementDetailsTY.getNetProfitOrLoss();
                                    if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossTY))
                                        netProfitOrLossTY = 0.0;
                                    Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                                    if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                                        depreciationTy = 0.0;
                                    Double interestTy = operatingStatementDetailsTY.getInterest();
                                    if (CommonUtils.isObjectNullOrEmpty(interestTy))
                                        interestTy = 0.0;

                                    Double netProfitOrLossSY = operatingStatementDetailsSY.getNetProfitOrLoss();
                                    if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossSY))
                                        netProfitOrLossSY = 0.0;
                                    Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                                    if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                                        depreciationSy = 0.0;
                                    Double interestSy = operatingStatementDetailsSY.getInterest();
                                    if (CommonUtils.isObjectNullOrEmpty(interestSy))
                                        interestSy = 0.0;

                                    Double netProfitOrLossFY = operatingStatementDetailsFY.getNetProfitOrLoss();
                                    if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossFY))
                                        netProfitOrLossFY = 0.0;
                                    Double depreciationFy = operatingStatementDetailsFY.getDepreciation();
                                    if (CommonUtils.isObjectNullOrEmpty(depreciationFy))
                                        depreciationFy = 0.0;
                                    Double interestFy = operatingStatementDetailsFY.getInterest();
                                    if (CommonUtils.isObjectNullOrEmpty(interestFy))
                                        interestFy = 0.0;

                                    scoringParameterRequest.setNetProfitOrLossFY(netProfitOrLossFY);
                                    scoringParameterRequest.setNetProfitOrLossSY(netProfitOrLossSY);
                                    scoringParameterRequest.setNetProfitOrLossTY(netProfitOrLossTY);

                                    scoringParameterRequest.setDepriciationFy(depreciationFy);
                                    scoringParameterRequest.setDepriciationSy(depreciationSy);
                                    scoringParameterRequest.setDepriciationTy(depreciationTy);

                                    scoringParameterRequest.setInterestFy(interestFy);
                                    scoringParameterRequest.setInterestSy(interestSy);
                                    scoringParameterRequest.setInterestTy(interestTy);

                                    scoringParameterRequest.setAvgAnnualGrowthGrossCash_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_ANNUAL_GROWTH_GROSS_CASH parameter : ",e);
                                    scoringParameterRequest.setAvgAnnualGrowthGrossCash_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.AVERAGE_ANNUAL_GROWTH_NET_SALE: {

                                try {
                                    Double domesticSalesTy = operatingStatementDetailsTY.getDomesticSales();
                                    if (CommonUtils.isObjectNullOrEmpty(domesticSalesTy))
                                        domesticSalesTy = 0.0;
                                    Double exportSalesTy = operatingStatementDetailsTY.getExportSales();
                                    if (CommonUtils.isObjectNullOrEmpty(exportSalesTy))
                                        exportSalesTy = 0.0;

                                    Double domesticSalesSy = operatingStatementDetailsSY.getDomesticSales();
                                    if (CommonUtils.isObjectNullOrEmpty(domesticSalesSy))
                                        domesticSalesSy = 0.0;

                                    Double exportSalesSy = operatingStatementDetailsSY.getExportSales();
                                    if (CommonUtils.isObjectNullOrEmpty(exportSalesSy))
                                        exportSalesSy = 0.0;


                                    Double domesticSalesFy = operatingStatementDetailsFY.getDomesticSales();
                                    if (CommonUtils.isObjectNullOrEmpty(domesticSalesFy))
                                        domesticSalesFy = 0.0;

                                    Double exportSalesFy = operatingStatementDetailsFY.getExportSales();
                                    if (CommonUtils.isObjectNullOrEmpty(exportSalesFy))
                                        exportSalesFy = 0.0;

                                    Double totalSale_FY = 0.0;
                                    if (domesticSalesFy + exportSalesFy == 0.0) {
                                        totalSale_FY = 1.0;
                                    } else {
                                        totalSale_FY = domesticSalesFy + exportSalesFy;
                                    }

                                    Double totalSale_SY = 0.0;
                                    if (domesticSalesSy + exportSalesSy == 0.0) {
                                        totalSale_SY = 1.0;
                                    } else {
                                        totalSale_SY = domesticSalesSy + exportSalesSy;
                                    }

                                    Double totalSale_TY = 0.0;
                                    if (domesticSalesTy + exportSalesTy == 0.0) {
                                        totalSale_TY = 1.0;
                                    } else {
                                        totalSale_TY = domesticSalesTy + exportSalesTy;
                                    }

                                    scoringParameterRequest.setTotalSaleFy(totalSale_FY);
                                    scoringParameterRequest.setTotalSaleSy(totalSale_SY);
                                    scoringParameterRequest.setTotalSaleTy(totalSale_TY);
                                    scoringParameterRequest.setAvgAnnualGrowthNetSale_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_ANNUAL_GROWTH_NET_SALE parameter : ",e);
                                    scoringParameterRequest.setAvgAnnualGrowthNetSale_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.AVERAGE_EBIDTA: {

                                try {

                                    // Before central bank
                                    /*Double profitBeforeTaxOrLossTy = operatingStatementDetailsTY.getProfitBeforeTaxOrLoss();
                                    if (CommonUtils.isObjectNullOrEmpty(profitBeforeTaxOrLossTy))
                                        profitBeforeTaxOrLossTy = 0.0;


                                    Double interestTy = operatingStatementDetailsTY.getInterest();
                                    if (CommonUtils.isObjectNullOrEmpty(interestTy))
                                        interestTy = 0.0;


                                    Double profitBeforeTaxOrLossSy = operatingStatementDetailsSY.getProfitBeforeTaxOrLoss();
                                    if (CommonUtils.isObjectNullOrEmpty(profitBeforeTaxOrLossSy))
                                        profitBeforeTaxOrLossSy = 0.0;


                                    Double interestSy = operatingStatementDetailsSY.getInterest();
                                    if (CommonUtils.isObjectNullOrEmpty(interestSy))
                                        interestSy = 0.0;


                                    Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                                    if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                                        depreciationTy = 0.0;


                                    Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                                    if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                                        depreciationSy = 0.0;


                                    Double termLoansTy = liabilitiesDetailsTY.getTermLoans();
                                    if (CommonUtils.isObjectNullOrEmpty(termLoansTy))
                                        termLoansTy = 0.0;*/

                                    // After central bank
                                    Double[] fyAvgEBIDTAValue = getAvgEBIDTAValue(operatingStatementDetailsFY,liabilitiesDetailsFY);
                                    Double[] syAvgEBIDTAValue = getAvgEBIDTAValue(operatingStatementDetailsSY,liabilitiesDetailsSY);
                                    Double[] tyAvgEBIDTAValue = getAvgEBIDTAValue(operatingStatementDetailsTY,liabilitiesDetailsTY);

                                    scoringParameterRequest.setProfitBeforeTaxOrLossFy(fyAvgEBIDTAValue[0]);
                                    scoringParameterRequest.setProfitBeforeTaxOrLossSy(syAvgEBIDTAValue[0]);
                                    scoringParameterRequest.setProfitBeforeTaxOrLossTy(tyAvgEBIDTAValue[0]);

                                    scoringParameterRequest.setInterestFy(fyAvgEBIDTAValue[1]);
                                    scoringParameterRequest.setInterestSy(syAvgEBIDTAValue[1]);
                                    scoringParameterRequest.setInterestTy(tyAvgEBIDTAValue[1]);

                                    scoringParameterRequest.setDepriciationFy(fyAvgEBIDTAValue[2]);
                                    scoringParameterRequest.setDepriciationTy(syAvgEBIDTAValue[2]);
                                    scoringParameterRequest.setDepriciationSy(tyAvgEBIDTAValue[2]);

                                    scoringParameterRequest.setTermLoanFy(fyAvgEBIDTAValue[3]);
                                    scoringParameterRequest.setTermLoanSy(syAvgEBIDTAValue[3]);
                                    scoringParameterRequest.setTermLoanTy(tyAvgEBIDTAValue[3]);

                                    scoringParameterRequest.setLoanAmount(loanAmount);

                                    scoringParameterRequest.setAvgEBIDTA_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_EBIDTA parameter : ",e);
                                    scoringParameterRequest.setAvgEBIDTA_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS: {

                                try {

                                    // Before central bank changes
                                    /*Double netProfitOrLossTY = operatingStatementDetailsTY.getNetProfitOrLoss();
                                    if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossTY))
                                        netProfitOrLossTY = 0.0;

                                    Double netProfitOrLossSY = operatingStatementDetailsSY.getNetProfitOrLoss();
                                    if (CommonUtils.isObjectNullOrEmpty(netProfitOrLossSY))
                                        netProfitOrLossSY = 0.0;

                                    Double interestTy = operatingStatementDetailsTY.getInterest();
                                    if (CommonUtils.isObjectNullOrEmpty(interestTy))
                                        interestTy = 0.0;

                                    Double interestSy = operatingStatementDetailsSY.getInterest();
                                    if (CommonUtils.isObjectNullOrEmpty(interestSy))
                                        interestSy = 0.0;

                                    Double depreciationTy = operatingStatementDetailsTY.getDepreciation();
                                    if (CommonUtils.isObjectNullOrEmpty(depreciationTy))
                                        depreciationTy = 0.0;

                                    Double depreciationSy = operatingStatementDetailsSY.getDepreciation();
                                    if (CommonUtils.isObjectNullOrEmpty(depreciationSy))
                                        depreciationSy = 0.0;

                                    Double totalAsset = assetsDetailsTY.getTotalAssets();
                                    if (CommonUtils.isObjectNullOrEmpty(totalAsset))
                                        totalAsset = 0.0;*/

                                    // After central bank changes
                                    Double[] avgAnnualGrossCaseAccrualsValueFY = getAvgAnnualGrossCaseAccrualsValue(operatingStatementDetailsFY,assetsDetailsFY);
                                    Double[] avgAnnualGrossCaseAccrualsValueSY = getAvgAnnualGrossCaseAccrualsValue(operatingStatementDetailsSY,assetsDetailsSY);
                                    Double[] avgAnnualGrossCaseAccrualsValueTY = getAvgAnnualGrossCaseAccrualsValue(operatingStatementDetailsTY,assetsDetailsTY);

                                    scoringParameterRequest.setNetProfitOrLossFY(avgAnnualGrossCaseAccrualsValueFY[0]);
                                    scoringParameterRequest.setNetProfitOrLossSY(avgAnnualGrossCaseAccrualsValueSY[0]);
                                    scoringParameterRequest.setNetProfitOrLossTY(avgAnnualGrossCaseAccrualsValueTY[0]);

                                    scoringParameterRequest.setInterestFy(avgAnnualGrossCaseAccrualsValueFY[1]);
                                    scoringParameterRequest.setInterestSy(avgAnnualGrossCaseAccrualsValueSY[1]);
                                    scoringParameterRequest.setInterestTy(avgAnnualGrossCaseAccrualsValueTY[1]);

                                    scoringParameterRequest.setDepriciationFy(avgAnnualGrossCaseAccrualsValueFY[2]);
                                    scoringParameterRequest.setDepriciationSy(avgAnnualGrossCaseAccrualsValueSY[2]);
                                    scoringParameterRequest.setDepriciationTy(avgAnnualGrossCaseAccrualsValueTY[2]);

                                    scoringParameterRequest.setTotalAssetFy(avgAnnualGrossCaseAccrualsValueFY[3]);
                                    scoringParameterRequest.setTotalAssetSy(avgAnnualGrossCaseAccrualsValueSY[3]);
                                    scoringParameterRequest.setTotalAssetTy(avgAnnualGrossCaseAccrualsValueTY[3]);

                                    scoringParameterRequest.setAvgAnnualGrossCashAccuruals_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS parameter : ",e);
                                    scoringParameterRequest.setAvgAnnualGrossCashAccuruals_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.AVERAGE_INTEREST_COV_RATIO: {
                                try {
                                    Double opProfitBeforeIntrestTy = operatingStatementDetailsTY.getOpProfitBeforeIntrest();
                                    if (CommonUtils.isObjectNullOrEmpty(opProfitBeforeIntrestTy))
                                        opProfitBeforeIntrestTy = 0.0;


                                    Double opProfitBeforeIntrestSy = operatingStatementDetailsSY.getOpProfitBeforeIntrest();
                                    if (CommonUtils.isObjectNullOrEmpty(opProfitBeforeIntrestSy))
                                        opProfitBeforeIntrestSy = 0.0;

                                    Double opProfitBeforeIntrestFy = operatingStatementDetailsFY.getOpProfitBeforeIntrest();
                                    if (CommonUtils.isObjectNullOrEmpty(opProfitBeforeIntrestFy))
                                        opProfitBeforeIntrestFy = 0.0;


                                    Double interestTy = operatingStatementDetailsTY.getInterest();
                                    if (CommonUtils.isObjectNullOrEmpty(interestTy))
                                        interestTy = 0.0;

                                    Double interestSy = operatingStatementDetailsSY.getInterest();
                                    if (CommonUtils.isObjectNullOrEmpty(interestSy))
                                        interestSy = 0.0;

                                    Double interestFy = operatingStatementDetailsFY.getInterest();
                                    if (CommonUtils.isObjectNullOrEmpty(interestFy))
                                        interestFy = 0.0;

                                    scoringParameterRequest.setOpProfitBeforeInterestFy(opProfitBeforeIntrestFy);
                                    scoringParameterRequest.setOpProfitBeforeInterestTy(opProfitBeforeIntrestTy);
                                    scoringParameterRequest.setOpProfitBeforeInterestSy(opProfitBeforeIntrestSy);
                                    scoringParameterRequest.setInterestFy(interestFy);
                                    scoringParameterRequest.setInterestTy(interestTy);
                                    scoringParameterRequest.setInterestSy(interestSy);

                                    scoringParameterRequest.setAvgInterestCovRatio_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting AVERAGE_INTEREST_COV_RATIO parameter : ",e);
                                    scoringParameterRequest.setAvgInterestCovRatio_p(false);
                                }

                                break;
                            }
                            case ScoreParameter.NO_OF_CUSTOMER: {
                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getNoOfCustomer())) {
                                        scoringParameterRequest.setNoOfCustomenr(gstCalculation.getNoOfCustomer());
                                        scoringParameterRequest.setNoOfCustomer_p(true);
                                    } else {
                                        scoringParameterRequest.setNoOfCustomer_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting NO_OF_CUSTOMER parameter : ",e);
                                    scoringParameterRequest.setNoOfCustomer_p(false);
                                    /*map.put("NO_OF_CUSTOMER",null);*/
                                }
                                break;
                            }
                            case ScoreParameter.CONCENTRATION_CUSTOMER: {
                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(gstCalculation) && !CommonUtils.isObjectNullOrEmpty(gstCalculation.getConcentration())) {
                                        scoringParameterRequest.setConcentrationCustomer(gstCalculation.getConcentration());
                                        scoringParameterRequest.setConcentrationCustomer_p(true);
                                    } else {
                                        scoringParameterRequest.setConcentrationCustomer_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting CONCENTRATION_CUSTOMER parameter : ",e);
                                    scoringParameterRequest.setConcentrationCustomer_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.CREDIT_SUMMATION: {

                                Double totalCredit = null;
                                Double projctedSales = null;
                                Integer noOfMonths = 1;
                                // start get total credit from Analyser
                                ReportRequest reportRequest = new ReportRequest();
                                reportRequest.setApplicationId(applicationId);
                                try {
                                    AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);
                                    Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                            Data.class);


                                    if(!CommonUtils.isListNullOrEmpty(data.getMonthlyDetailList().getMonthlyDetails())){
                                        noOfMonths = data.getMonthlyDetailList().getMonthlyDetails().size();
                                    }
                                    if (!CommonUtils.isObjectNullOrEmpty(analyzerResponse.getData())) {
                                        {
                                            if (!CommonUtils.isObjectNullOrEmpty(data.getTotalCredit())) {
                                                totalCredit = data.getTotalCredit();
                                            } else {
                                                totalCredit = 0.0;
                                            }

                                        }

                                    }
                                } catch (Exception e) {
                                    totalCredit = 0.0;
                                    logger.error("error while calling analyzer client : ",e);
                                }

                                // get get total credit from Analyser

                                // start get projected sales from GST client

                                if(!CommonUtils.isObjectNullOrEmpty(gstCalculation.getHistoricalSales())) {
                                    projctedSales = gstCalculation.getHistoricalSales();
                                }
                                else
                                {
                                    projctedSales = gstCalculation.getProjectedSales();
                                }

                                // end get projected sales from GST client
                                scoringParameterRequest.setNoOfMonths(noOfMonths);
                                scoringParameterRequest.setTotalCredit(totalCredit);
                                scoringParameterRequest.setProjectedSale(projctedSales);
                                scoringParameterRequest.setCreditSummation_p(true);

                                break;
                            }
                            case ScoreParameter.AGE: {
                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDob())) {
                                        scoringParameterRequest.setAge(Math.ceil(CommonUtils.getAgeFromBirthDate(mainDirectorBackgroundDetail.getDob()).doubleValue()));
                                        scoringParameterRequest.setAge_p(true);
                                    } else {
                                        scoringParameterRequest.setAge_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting AGE parameter : ",e);
                                    scoringParameterRequest.setAge_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.NO_OF_CHILDREN: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getNoOfChildren())) {
                                        scoringParameterRequest.setNoOfChildren(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getNoOfChildren().doubleValue());
                                        scoringParameterRequest.setNoOfChildren_p(true);
                                    } else {
                                        scoringParameterRequest.setNoOfChildren_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting NO_OF_CHILDREN parameter : ",e);
                                    scoringParameterRequest.setNoOfChildren_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.OWNING_HOUSE: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOwningHouse())) {
                                        scoringParameterRequest.setOwningHouse(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOwningHouse().longValue());
                                        scoringParameterRequest.setOwningHouse_p(true);
                                    } else {
                                        scoringParameterRequest.setOwningHouse_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting OWNING_HOUSE parameter : ",e);
                                    scoringParameterRequest.setOwningHouse_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.ACADEMIC_QUALIFICATION: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getEducationalStatus())) {
                                        scoringParameterRequest.setAcadamicQualification(mainDirectorBackgroundDetail.getEducationalStatus().longValue());
                                        scoringParameterRequest.setAcadamicQualification_p(true);
                                    } else {
                                        scoringParameterRequest.setAcadamicQualification_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting ACADEMIC_QUALIFICATION parameter : ",e);
                                    scoringParameterRequest.setAcadamicQualification_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.EXPERIENCE_IN_THE_LINE_OF_TRADE: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getTotalExperience())) {
                                        scoringParameterRequest.setExperienceInLineOfBusiness(mainDirectorBackgroundDetail.getTotalExperience());
                                        scoringParameterRequest.setExpLineOfTrade_p(true);
                                    } else {
                                        scoringParameterRequest.setExpLineOfTrade_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting EXPERIENCE_IN_THE_LINE_OF_TRADE parameter : ",e);
                                    scoringParameterRequest.setExpLineOfTrade_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.SPOUSE_DETAILS: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getSpouseDetail())) {
                                        scoringParameterRequest.setSpouceDetails(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getSpouseDetail().longValue());
                                        scoringParameterRequest.setSpouseDetails_p(true);
                                    } else {
                                        scoringParameterRequest.setSpouseDetails_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting SPOUSE_DETAILS parameter : ",e);
                                    scoringParameterRequest.setSpouseDetails_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.ASSESSED_FOR_INCOME_TAX: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAssessedForIt())) {
                                        scoringParameterRequest.setAssessedFOrIT(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAssessedForIt().longValue());
                                        scoringParameterRequest.setAssessedForIncomeTax_p(true);
                                    } else {
                                        scoringParameterRequest.setAssessedForIncomeTax_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting ASSESSED_FOR_INCOME_TAX parameter : ",e);
                                    scoringParameterRequest.setAssessedForIncomeTax_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.HAVE_LIFE_INSURANCE_POLICY: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getHaveLiPolicy())) {
                                        scoringParameterRequest.setHaveLIPolicy(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getHaveLiPolicy().longValue());
                                        scoringParameterRequest.setHaveLifeIncPolicy_p(true);
                                    } else {
                                        scoringParameterRequest.setHaveLifeIncPolicy_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting HAVE_LIFE_INSURANCE_POLICY parameter : ",e);
                                    scoringParameterRequest.setHaveLifeIncPolicy_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.YEARS_IN_BUSINESS: {
                                try {
                                	Double yearsInBusiness = null;
                                	Integer yearsInBetween = corporateApplicantDetail.getBusinessSinceYear();
                                	Integer monthsDiff = null;
                                	if(yearsInBetween == null) {
                                		java.util.Calendar todayDate = java.util.Calendar.getInstance();
                                        todayDate.setTime(new Date());

                                        yearsInBetween = todayDate.get(java.util.Calendar.YEAR) - corporateApplicantDetail.getEstablishmentYear();

                                        monthsDiff = todayDate.get(java.util.Calendar.MONTH) - corporateApplicantDetail.getEstablishmentMonth();

                                        yearsInBusiness = (((double)yearsInBetween * 12 + (double)monthsDiff) / 12);
                                	}else {
                                		monthsDiff = corporateApplicantDetail.getBusinessSinceMonth();
                                		if(monthsDiff > 6)
                                			yearsInBusiness = (double)yearsInBetween + 1;
                                		else
                                			yearsInBusiness = (double)yearsInBetween;
                                	}

                                    scoringParameterRequest.setYearsInBusiness(yearsInBusiness);
                                    scoringParameterRequest.setYearsInBusiness_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting YEARS_IN_BUSINESS parameter : ",e);
                                    scoringParameterRequest.setYearsInBusiness_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.REPAYMENT_PERIOD: {

                                try {

                                    // get repayment period from one form // remaining
                                    scoringParameterRequest.setRepaymentPeriod(5.0);
                                    scoringParameterRequest.setRepaymentPeriod_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting REPAYMENT_PERIOD parameter : ",e);
                                    scoringParameterRequest.setRepaymentPeriod_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.CONTINUOUS_NET_PROFIT: {

                                try {

                                    Double netProfitOrLossFY = operatingStatementDetailsFY.getProfitBeforeTaxOrLoss();
                                    Double netProfitOrLossSY = operatingStatementDetailsSY.getProfitBeforeTaxOrLoss();
                                    Double netProfitOrLossTY = operatingStatementDetailsTY.getProfitBeforeTaxOrLoss();

                                    scoringParameterRequest.setContinuousNetProfitOrLossFY(netProfitOrLossFY);
                                    scoringParameterRequest.setContinuousNetProfitOrLossSY(netProfitOrLossSY);
                                    scoringParameterRequest.setContinuousNetProfitOrLossTY(netProfitOrLossTY);

                                    scoringParameterRequest.setContinousNetProfit_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting CONTINUOUS_NET_PROFIT parameter : ",e);
                                    scoringParameterRequest.setContinousNetProfit_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.QUALITY_OF_RECEIVABLES: {

                                try {

                                    Double totalSaleTY = operatingStatementDetailsTY.getDomesticSales() + operatingStatementDetailsTY.getExportSales();
                                    Double grossSaleTy = operatingStatementDetailsTY.getTotalGrossSales();

                                    scoringParameterRequest.setTotalSaleTy(totalSaleTY);
                                    scoringParameterRequest.setGrossSaleTy(grossSaleTy);

                                    scoringParameterRequest.setQualityOfReceivable_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting QUALITY_OF_RECEIVABLES parameter : ",e);
                                    scoringParameterRequest.setQualityOfReceivable_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.QUALITY_OF_FINISHED_GOODS_INVENTORY: {

                                try {

                                    Double totalCostSaleTy = operatingStatementDetailsTY.getTotalCostSales();
                                    Double finishedGoodTy = assetsDetailsTY.getFinishedGoods();

                                    scoringParameterRequest.setTotalCostSaleTy(totalCostSaleTy);
                                    scoringParameterRequest.setFinishedGoodTy(finishedGoodTy);

                                    scoringParameterRequest.setQualityOfFinishedGood_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting QUALITY_OF_FINISHED_GOODS_INVENTORY parameter : ",e);
                                    scoringParameterRequest.setQualityOfFinishedGood_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.KNOW_HOW: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getKnowHow())) {
                                        scoringParameterRequest.setKnowHow(primaryCorporateDetail.getKnowHow().longValue());
                                        scoringParameterRequest.setKnowHow_p(true);
                                    } else {
                                        scoringParameterRequest.setKnowHow_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting KNOW_HOW parameter : ",e);
                                    scoringParameterRequest.setKnowHow_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.LINE_OF_ACTIVITY: {
                                scoringParameterRequest.setLineOfActivity(1l);
                                scoringParameterRequest.setLineOfActivity_p(true);

                                break;
                            }
                            case ScoreParameter.COMPETITION: {

                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCompetition())) {
                                        scoringParameterRequest.setCompetition(primaryCorporateDetail.getCompetition().longValue());
                                        scoringParameterRequest.setCompetition_p(true);
                                    } else {
                                        scoringParameterRequest.setCompetition_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting COMPETITION parameter : ",e);
                                    scoringParameterRequest.setCompetition_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.FACTORY_PREMISES: {
                                try {

                                    if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getFactoryPremise())) {
                                        scoringParameterRequest.setFactoryPremises(primaryCorporateDetail.getFactoryPremise().longValue());
                                        scoringParameterRequest.setFactoryPremises_p(true);
                                    } else {
                                        scoringParameterRequest.setFactoryPremises_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting FACTORY_PREMISES parameter : ",e);
                                    scoringParameterRequest.setFactoryPremises_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.SALES_SHOW_A_RISING_TREND: {

                                try {

                                    // getting Gst Current Year Sale from GST Client

                                    if (!CommonUtils.isObjectNullOrEmpty(gstResponseScoring) && !CommonUtils.isObjectNullOrEmpty(gstResponseScoring.getData())) {
                                        scoringParameterRequest.setGstSaleCurrentYear((Double) gstResponseScoring.getData());
                                    } else {
                                        scoringParameterRequest.setGstSaleCurrentYear(0.0);
                                        logger.error("Error while getting Gst data for Scoring from GST client");
                                    }

                                    scoringParameterRequest.setNetSaleFy(operatingStatementDetailsFY.getNetSales());
                                    scoringParameterRequest.setNetSaleSy(operatingStatementDetailsSY.getNetSales());
                                    scoringParameterRequest.setNetSaleTy(operatingStatementDetailsTY.getNetSales());

                                    scoringParameterRequest.setSalesShowArisingTrend_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting SALES_SHOW_A_RISING_TREND parameter : ",e);
                                    scoringParameterRequest.setSalesShowArisingTrend_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.UTILISATION_PERCENTAGE: {

                                try {
                                    Integer noOfMonths = 1;

                                    ReportRequest reportRequest = new ReportRequest();
                                    reportRequest.setApplicationId(applicationId);
                                    AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);
                                    Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                            Data.class);

                                    if (!CommonUtils.isObjectNullOrEmpty(data)) {
                                        {

                                           /* if(!CommonUtils.isListNullOrEmpty(data.getMonthlyDetailList().getMonthlyDetails())){
                                                noOfMonths = data.getMonthlyDetailList().getMonthlyDetails().size();
                                            }

                                            if(!CommonUtils.isObjectNullOrEmpty(data.getSummaryInfo().getSummaryInfoTotalDetails().getBalAvg()))
                                            {
                                                scoringParameterRequest.setAverageDailyBalance(Double.parseDouble(data.getSummaryInfo().getSummaryInfoTotalDetails().getBalAvg()) / noOfMonths);
                                            }*/

                                            Double avrgBalance=0.0;
                                            if(!CommonUtils.isListNullOrEmpty(data.getMonthlyDetailList().getMonthlyDetails())){
                                                noOfMonths = data.getMonthlyDetailList().getMonthlyDetails().size();
                                                for (MonthlyDetail monthlyObj:data.getMonthlyDetailList().getMonthlyDetails()) {
                                                    if(!CommonUtils.isObjectNullOrEmpty(monthlyObj.getBalAvg())){
                                                        avrgBalance+=Math.abs(Double.valueOf(monthlyObj.getBalAvg()));
                                                    }
                                                }
                                            }
                                            scoringParameterRequest.setAverageDailyBalance(avrgBalance/noOfMonths);


                                        }
                                    }

                                    List<String> loanTypeList=new ArrayList<String>();
                                    loanTypeList.add(CibilUtils.CreditTypeEnum.CASH_CREDIT.getValue());
                                    loanTypeList.add(CibilUtils.CreditTypeEnum.OVERDRAFT.getValue());
                                    Double existingLimits = financialArrangementDetailsRepository.getExistingLimits(applicationId , loanTypeList );
                                    if(isCibilCheck && !isBureauExistingLoansDisplayActive) {
                                    	ScoringCibilRequest scoringCibilRequest = filterScore(scoringRequest.getMap(), null, modelParameterResponse.getFieldMasterId());
                                    	if(!CommonUtils.isObjectNullOrEmpty(scoringCibilRequest)) {
                                    		logger.info("Total Bureau Existing Limit ===>{} ===>{}",applicationId,scoringCibilRequest.getTotalExistingLimit());
                                    		if(!CommonUtils.isObjectNullOrEmpty(scoringCibilRequest.getTotalExistingLimit())) {
                                    			if(!CommonUtils.isObjectNullOrEmpty(existingLimits)) {
                                    				existingLimits = existingLimits + scoringCibilRequest.getTotalExistingLimit();                                    				
                                    			}else {
                                    				existingLimits = scoringCibilRequest.getTotalExistingLimit();
                                    			}
                                    		}
                                    	}
                                    }
                                    logger.info("existingLimits For UTILISATION_PERCENTAGE ApplicationId ==>{}",applicationId,existingLimits);
                                    scoringParameterRequest.setLimitsInAccount(existingLimits);

                                    scoringParameterRequest.setUtilisationPercentage_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting UTILISATION_PERCENTAGE parameter : ",e);
                                    scoringParameterRequest.setUtilisationPercentage_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.TURN_OVER_TO_LIMIT_RATIO: {

                                try {

                                    scoringParameterRequest.setTurnOver(operatingStatementDetailsTY.getDomesticSales() + operatingStatementDetailsTY.getExportSales());

                                    scoringParameterRequest.setTurnOverToLimitRatio_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting TURN_OVER_TO_LIMIT_RATIO parameter : ",e);
                                    scoringParameterRequest.setTurnOverToLimitRatio_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.COLLATERAL_COVERAGE: {

                                try {

                                    if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCollateralSecurityAmount()))
                                        scoringParameterRequest.setAmountOfCollateral(primaryCorporateDetail.getCollateralSecurityAmount());
                                    else
                                        scoringParameterRequest.setAmountOfCollateral(0.0);


                                    scoringParameterRequest.setCollateralCoverage_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting COLLATERAL_COVERAGE parameter : ",e);
                                    scoringParameterRequest.setCollateralCoverage_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.DEBT_SERVICE_COVERAGE_RATIO: {

                                try {

                                    scoringParameterRequest.setEbitdaFY(operatingStatementDetailsFY.getOpProfitBeforeIntrest() + operatingStatementDetailsFY.getDepreciation());
                                    scoringParameterRequest.setEbitdaSY(operatingStatementDetailsSY.getOpProfitBeforeIntrest() + operatingStatementDetailsSY.getDepreciation());
                                    scoringParameterRequest.setEbitdaTY(operatingStatementDetailsTY.getOpProfitBeforeIntrest() + operatingStatementDetailsTY.getDepreciation());

                                    Double totalExistingLoanObligation=0.0;

                                    Double individualLoanObligation = financialArrangementDetailsRepository.getTotalEmiByApplicationId(applicationId);
                                    Double commercialLoanObligation = financialArrangementDetailsService.getTotalEmiOfAllDirByApplicationId(applicationId);
                                    if(isCibilCheck && !isBureauExistingLoansDisplayActive) {
                                    	ScoringCibilRequest scoringCibilRequest = filterScore(scoringRequest.getMap(), null, modelParameterResponse.getFieldMasterId());
                                    	if(!CommonUtils.isObjectNullOrEmpty(scoringCibilRequest)) {
                                    		if(!CommonUtils.isObjectNullOrEmpty(scoringCibilRequest.getTotalEmiOfCompany())) {
                                    			if(!CommonUtils.isObjectNullOrEmpty(individualLoanObligation)) {
                                    				individualLoanObligation = individualLoanObligation + scoringCibilRequest.getTotalEmiOfCompany();                                    				
                                    			}else {
                                    				individualLoanObligation = scoringCibilRequest.getTotalEmiOfCompany();
                                    			}
                                    		}if(!CommonUtils.isObjectNullOrEmpty(scoringCibilRequest.getTotalEmiOfDirector())) {
                                    			if(!CommonUtils.isObjectNullOrEmpty(commercialLoanObligation)) {
                                    				commercialLoanObligation = commercialLoanObligation + scoringCibilRequest.getTotalEmiOfDirector();	
                                    			}else {
                                    				commercialLoanObligation = scoringCibilRequest.getTotalEmiOfDirector();
                                    			}
                                    			                                    			
                                    		}
                                    	}
                                    }
                                    if(!CommonUtils.isObjectNullOrEmpty(individualLoanObligation)){
                                    	totalExistingLoanObligation+=(individualLoanObligation*12);
                                    }

                                    if(!CommonUtils.isObjectNullOrEmpty(commercialLoanObligation)) {
                                    	totalExistingLoanObligation+=(commercialLoanObligation*12);
                                    }
                                    logger.info("totalExistingLoanObligation For DEBT_SERVICE_COVERAGE_RATIO ApplicationId ==>{}",applicationId,totalExistingLoanObligation);

                                    scoringParameterRequest.setExistingLoanObligation(totalExistingLoanObligation);

                                    if(primaryCorporateDetail.getPurposeOfLoanId() == 1) {
                                    	scoringParameterRequest.setLoanType(2);
                                    }else {
                                    	scoringParameterRequest.setLoanType(1);
                                    }
                                        

                                    scoringParameterRequest.setDebtServiceCoverageRatio_p(true);

                                } catch (Exception e) {
                                    logger.error("error while getting DEBT_SERVICE_COVERAGE_RATIO parameter : ",e);
                                    scoringParameterRequest.setDebtServiceCoverageRatio_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.PAST_YEAR_TURNOVER: {

                                try {
                                    Double domesticSales = operatingStatementDetailsTY.getDomesticSales();
                                    Double exportSales = operatingStatementDetailsTY.getExportSales();
                                    scoringParameterRequest.setPastYearTurnover_p(true);
                                    scoringParameterRequest.setExportSalesTY(exportSales);
                                    scoringParameterRequest.setDomesticSalesTY(domesticSales);
                                    scoringParameterRequest.setPastYearTurnover(domesticSales + exportSales);
                                } catch (Exception e) {
                                    logger.error("error while getting PAST_YEAR_TURNOVER parameter : ",e);
                                    scoringParameterRequest.setPastYearTurnover_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.DEBT_EBITDA: {
                                try {

                                    //Before Central Bank changes
                                    /*
                                    *   //debt
                                        scoringParameterRequest.setTotalTermLiabilities(liabilitiesDetailsTY.getTotalTermLiabilities());
                                        scoringParameterRequest.setPreferenceShares(liabilitiesDetailsTY.getPreferencesShares());
                                        scoringParameterRequest.setUnsecuredLoansFromOthers(liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther());
                                        scoringParameterRequest.setOthers(liabilitiesDetailsTY.getOthers());
                                        scoringParameterRequest.setMinorityInterest(liabilitiesDetailsTY.getMinorityInterest());
                                        scoringParameterRequest.setDeferredTaxLiability(liabilitiesDetailsTY.getDeferredTaxLiability());
                                        scoringParameterRequest.setDeferredTaxAssets(assetsDetailsTY.getDeferredTaxAssets());

                                        //EBITA
                                        scoringParameterRequest.setProfitBeforeInterest(operatingStatementDetailsTY.getOpProfitBeforeIntrest());
                                        scoringParameterRequest.setDepreciation(operatingStatementDetailsTY.getDepreciation());
                                        scoringParameterRequest.setDebtEBITDA_p(true);

                                    * */

                                    //After Central Bank changes
                                    //debt FY
                                    Double[] fyDebtEbitdaValues = getDebtEbitdaValues(liabilitiesDetailsFY,assetsDetailsFY,operatingStatementDetailsFY);
                                    Double[] syDebtEbitdaValues = getDebtEbitdaValues(liabilitiesDetailsSY,assetsDetailsSY,operatingStatementDetailsSY);
                                    Double[] tyDebtEbitdaValues = getDebtEbitdaValues(liabilitiesDetailsTY,assetsDetailsTY,operatingStatementDetailsTY);

                                    scoringParameterRequest.setTotalTermLiabilitiesFY(fyDebtEbitdaValues[0]);
                                    scoringParameterRequest.setPreferenceSharesFY(fyDebtEbitdaValues[1]);
                                    scoringParameterRequest.setOthersFY(fyDebtEbitdaValues[2]);
                                    scoringParameterRequest.setMinorityInterestFY(fyDebtEbitdaValues[3]);
                                    scoringParameterRequest.setDeferredTaxLiabilityFY(fyDebtEbitdaValues[4]);
                                    scoringParameterRequest.setDeferredTaxAssetsFY(fyDebtEbitdaValues[5]);
                                    scoringParameterRequest.setUnsecuredLoansFromOthersFY(fyDebtEbitdaValues[6]);

                                    //EBITA FY
                                    scoringParameterRequest.setProfitBeforeInterestFY(fyDebtEbitdaValues[7]);
                                    scoringParameterRequest.setDepreciationFY(fyDebtEbitdaValues[8]);

                                    //debt SY
                                    scoringParameterRequest.setTotalTermLiabilitiesSY(syDebtEbitdaValues[0]);
                                    scoringParameterRequest.setPreferenceSharesSY(syDebtEbitdaValues[1]);
                                    scoringParameterRequest.setOthersSY(syDebtEbitdaValues[2]);
                                    scoringParameterRequest.setMinorityInterestSY(syDebtEbitdaValues[3]);
                                    scoringParameterRequest.setDeferredTaxLiabilitySY(syDebtEbitdaValues[4]);
                                    scoringParameterRequest.setDeferredTaxAssetsSY(syDebtEbitdaValues[5]);
                                    scoringParameterRequest.setUnsecuredLoansFromOthersSY(syDebtEbitdaValues[6]);

                                    //EBITA SY
                                    scoringParameterRequest.setProfitBeforeInterestSY(syDebtEbitdaValues[7]);
                                    scoringParameterRequest.setDepreciationSY(syDebtEbitdaValues[8]);

                                    //debt TY
                                    scoringParameterRequest.setTotalTermLiabilitiesTY(tyDebtEbitdaValues[0]);
                                    scoringParameterRequest.setPreferenceSharesTY(tyDebtEbitdaValues[1]);
                                    scoringParameterRequest.setOthersTY(tyDebtEbitdaValues[2]);
                                    scoringParameterRequest.setMinorityInterestTY(tyDebtEbitdaValues[3]);
                                    scoringParameterRequest.setDeferredTaxLiabilityTY(tyDebtEbitdaValues[4]);
                                    scoringParameterRequest.setDeferredTaxAssetsTY(tyDebtEbitdaValues[5]);
                                    scoringParameterRequest.setUnsecuredLoansFromOthersTY(tyDebtEbitdaValues[6]);

                                    //EBITA TY
                                    scoringParameterRequest.setProfitBeforeInterestTY(tyDebtEbitdaValues[7]);
                                    scoringParameterRequest.setDepreciationTY(tyDebtEbitdaValues[8]);

                                    scoringParameterRequest.setDebtEBITDA_p(true);
                                }catch (Exception e){
                                    logger.error("error while getting DEBT_EBITDA parameter : ",e);
                                    scoringParameterRequest.setDebtEBITDA_p(false);
                                }
                                break;
                            }

                            case ScoreParameter.TURNOVER_ATNW: {
                                try {

                                    /*    scoringParameterRequest.setLiabilitiesOrdinaryShareCapital(liabilitiesDetailsTY.getOrdinarySharesCapital());
                                        scoringParameterRequest.setLiabilitiesGeneralReserve(liabilitiesDetailsTY.getGeneralReserve());
                                        scoringParameterRequest.setDeficitInProfitANDLossAccount(liabilitiesDetailsTY.getSurplusOrDeficit());
                                        scoringParameterRequest.setLiabilitiesUnsecuredLoansFromPpromoters(liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromPromoters());
                                        scoringParameterRequest.setLiabilitiesUnsecuredLoansFromOthers(liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther());
                                        scoringParameterRequest.setAssetsInvestmentsInSubsidiaryCosaffiliates(assetsDetailsTY.getInvestmentsInSubsidiary());
                                        scoringParameterRequest.setDomesticSales(operatingStatementDetailsTY.getDomesticSales());
                                        scoringParameterRequest.setExportSales(operatingStatementDetailsTY.getExportSales());*/

                                    Double[] fyTurnOverATNWValue = getTurnOverATNWValue(operatingStatementDetailsFY, liabilitiesDetailsFY, assetsDetailsFY);
                                    Double[] syTurnOverATNWValue = getTurnOverATNWValue(operatingStatementDetailsSY, liabilitiesDetailsSY, assetsDetailsSY);
                                    Double[] tyTurnOverATNWValue = getTurnOverATNWValue(operatingStatementDetailsTY, liabilitiesDetailsTY, assetsDetailsTY);


                                    //FY
                                    scoringParameterRequest.setLiabilitiesOrdinaryShareCapitalFY(fyTurnOverATNWValue[0]);
                                    scoringParameterRequest.setLiabilitiesGeneralReserveFY(fyTurnOverATNWValue[1]);
                                    scoringParameterRequest.setDeficitInProfitANDLossAccountFY(fyTurnOverATNWValue[2]);
                                    scoringParameterRequest.setLiabilitiesUnsecuredLoansFromPpromotersFY(fyTurnOverATNWValue[3]);
                                    scoringParameterRequest.setLiabilitiesUnsecuredLoansFromOthersFY(fyTurnOverATNWValue[4]);
                                    scoringParameterRequest.setAssetsInvestmentsInSubsidiaryCosaffiliatesFY(fyTurnOverATNWValue[5]);
                                    scoringParameterRequest.setDomesticSalesFY(fyTurnOverATNWValue[6]);
                                    scoringParameterRequest.setExportSalesFY(fyTurnOverATNWValue[7]);

                                    //SY
                                    scoringParameterRequest.setLiabilitiesOrdinaryShareCapitalSY(syTurnOverATNWValue[0]);
                                    scoringParameterRequest.setLiabilitiesGeneralReserveSY(syTurnOverATNWValue[1]);
                                    scoringParameterRequest.setDeficitInProfitANDLossAccountSY(syTurnOverATNWValue[2]);
                                    scoringParameterRequest.setLiabilitiesUnsecuredLoansFromPpromotersSY(syTurnOverATNWValue[3]);
                                    scoringParameterRequest.setLiabilitiesUnsecuredLoansFromOthersSY(syTurnOverATNWValue[4]);
                                    scoringParameterRequest.setAssetsInvestmentsInSubsidiaryCosaffiliatesSY(syTurnOverATNWValue[5]);
                                    scoringParameterRequest.setDomesticSalesSY(syTurnOverATNWValue[6]);
                                    scoringParameterRequest.setExportSalesSY(syTurnOverATNWValue[7]);

                                    //TY
                                    scoringParameterRequest.setLiabilitiesOrdinaryShareCapitalTY(tyTurnOverATNWValue[0]);
                                    scoringParameterRequest.setLiabilitiesGeneralReserveTY(tyTurnOverATNWValue[1]);
                                    scoringParameterRequest.setDeficitInProfitANDLossAccountTY(tyTurnOverATNWValue[2]);
                                    scoringParameterRequest.setLiabilitiesUnsecuredLoansFromPpromotersTY(tyTurnOverATNWValue[3]);
                                    scoringParameterRequest.setLiabilitiesUnsecuredLoansFromOthersTY(tyTurnOverATNWValue[4]);
                                    scoringParameterRequest.setAssetsInvestmentsInSubsidiaryCosaffiliatesTY(tyTurnOverATNWValue[5]);
                                    scoringParameterRequest.setDomesticSalesTY(tyTurnOverATNWValue[6]);
                                    scoringParameterRequest.setExportSalesTY(tyTurnOverATNWValue[7]);

                                    scoringParameterRequest.setTurnoverATNW_p(true);

                                }catch (Exception e){
                                    logger.error("error while getting TURNOVER_ATNW parameter : ",e);
                                    scoringParameterRequest.setTurnoverATNW_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.NO_OF_CHEQUES_BOUNCED: {
                                try{
                                    Double noOfChequeBounce = 0.0;
                                    ReportRequest reportRequest = new ReportRequest();
                                    reportRequest.setApplicationId(applicationId);
                                    reportRequest.setDirectorId(null);

                                    AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);

                                    Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                            Data.class);
                                    if (!CommonUtils.isObjectNullOrEmpty(data) && !CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast1Month())) {
                                        {
                                            if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast1Month().doubleValue())) {
                                                noOfChequeBounce = data.getCheckBounceForLast1Month().doubleValue();
                                            } else {
                                                noOfChequeBounce = 0.0;
                                            }
                                        }
                                    } else {
                                        noOfChequeBounce = 0.0;
                                    }
                                    scoringParameterRequest.setNoOfChequesBouncedLastMonth(noOfChequeBounce);
                                    scoringParameterRequest.setChequesBouncedLastMonth_p(true);
                                }catch (Exception e){
                                    logger.error("error while getting NO_OF_CHEQUES_BOUNCED parameter : ",e);
                                    scoringParameterRequest.setChequesBouncedLastMonth_p(false);
                                }
                                break;
                            }

                            case ScoreParameter.NO_OF_CHEQUES_BOUNCED_LAST_SIX_MONTH: {
                                try{
                                    Double noOfChequeBounce = 0.0;
                                    ReportRequest reportRequest = new ReportRequest();
                                    reportRequest.setApplicationId(applicationId);
                                    reportRequest.setDirectorId(null);

                                    AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);

                                    Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                            Data.class);
                                    if (!CommonUtils.isObjectNullOrEmpty(data) && !CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month())) {
                                        {
                                            if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month().doubleValue())) {
                                                noOfChequeBounce = data.getCheckBounceForLast6Month().doubleValue();
                                            } else {
                                                noOfChequeBounce = 0.0;
                                            }

                                        }
                                    } else {
                                        noOfChequeBounce = 0.0;
                                    }
                                    scoringParameterRequest.setNoOfChequesBouncedLastSixMonth(noOfChequeBounce);
                                    scoringParameterRequest.setChequesBouncedLastSixMonth_p(true);
                                }catch (Exception e){
                                    logger.error("error while getting NO_OF_CHEQUES_BOUNCED_LAST_SIX_MONTH parameter : ",e);
                                    scoringParameterRequest.setChequesBouncedLastSixMonth_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.PAT_NET_SALES_RATIO: {
                                try {

                                    Object[] itrResponse = moveAheadFromItr(applicationId);
                                    Integer itrType = CommonUtils.isObjectNullOrEmpty(itrResponse[1]) ? null : Integer.parseInt(itrResponse[1].toString());

                                    if(itrType !=null) {
                                        scoringParameterRequest.setNetSaleTy(getOrDefauls(operatingStatementDetailsTY.getNetSales()));
                                        scoringParameterRequest.setNetSaleSy(getOrDefauls(operatingStatementDetailsSY.getNetSales()));
                                        scoringParameterRequest.setNetSaleFy(getOrDefauls(operatingStatementDetailsFY.getNetSales()));

                                        scoringParameterRequest.setNetProfitOrLossFY(getOrDefauls(operatingStatementDetailsFY.getNetProfitOrLoss()));
                                        scoringParameterRequest.setNetProfitOrLossSY(getOrDefauls(operatingStatementDetailsSY.getNetProfitOrLoss()));
                                        scoringParameterRequest.setNetProfitOrLossTY(getOrDefauls(operatingStatementDetailsTY.getNetProfitOrLoss()));

                                        scoringParameterRequest.setOtherRevenueIncomeFY(getOrDefauls(operatingStatementDetailsFY.getAddOtherRevenueIncome()));
                                        scoringParameterRequest.setOtherRevenueIncomeSY(getOrDefauls(operatingStatementDetailsSY.getAddOtherRevenueIncome()));
                                        scoringParameterRequest.setOtherRevenueIncomeTY(getOrDefauls(operatingStatementDetailsTY.getAddOtherRevenueIncome()));

                                        scoringParameterRequest.setItyYearType(itrType);
                                        scoringParameterRequest.setPatNetSalesRatio_p(true);
                                    }else {
                                        logger.error("error while getting PAT_NET_SALES_RATIO parameter :- Not able to find itr type.");
                                        scoringParameterRequest.setPatNetSalesRatio_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting PAT_NET_SALES_RATIO parameter : ", e);
                                    scoringParameterRequest.setPatNetSalesRatio_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.STATUTORY_COMPLIANCE: {
                                try {
                                    ITRConnectionResponse itrBasicDetailsResponse = itrClient.getITRBasicDetails(applicationId);
                                    boolean isITRAvailable  = false;
                                    if(!CommonUtils.isObjectNullOrEmpty(itrBasicDetailsResponse)){
                                        isITRAvailable = true;
                                    }
                                    boolean isGstAvailable = ((!CommonUtils.isObjectNullOrEmpty(gstResponse)) && (!CommonUtils.isObjectNullOrEmpty(gstResponse.getData())));
                                    Integer id = 0;
                                    if(isGstAvailable && isGstAvailable){
                                        id = 3;
                                    }else if(isITRAvailable){
                                        id = 1;
                                    }else if(isGstAvailable){
                                        id = 2;
                                    }
                                    scoringParameterRequest.setStatutoryComplianceType(id);
                                    scoringParameterRequest.setStatutoryCompliance_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting STATUTORY_COMPLIANCE parameter : ", e);
                                    scoringParameterRequest.setStatutoryCompliance_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.PAYMENT_RECORDS_WITH_LENDERS: {
                            	if(!isCibilCheck) {
                            		  try {
                                          CibilResponse cibilResponse = cibilClient.getDPDLastXMonth(applicationId);
                                          if(!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isObjectNullOrEmpty(cibilResponse.getListData())){
                                              List cibilDirectorsResponseList = cibilResponse.getListData();
                                              int commercialVal = 0;
                                              int maxDpd = 0;
                                              for (int j = 0; j < cibilDirectorsResponseList.size(); j++) {
                                                  String cibilResponseObj = cibilDirectorsResponseList.get(j).toString();
                                                  if(cibilResponseObj.contains("|")){
                                                      String[] cibilDpdVal = cibilResponseObj.split(Pattern.quote("|"));
                                                      if(!CommonUtils.isObjectNullOrEmpty(cibilDpdVal[1]))
                                                          commercialVal = Integer.parseInt(cibilDpdVal[1]);
                                                  }else {
                                                      commercialVal = Integer.parseInt(cibilDirectorsResponseList.get(i).toString());
                                                  }
                                                  logger.info("commercialVal1::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+commercialVal);
                                                  if(maxDpd <= commercialVal){
                                                      maxDpd = commercialVal;
                                                  }
                                                  logger.info("maxDpd::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+maxDpd);
                                                  scoringParameterRequest.setDpd(maxDpd);
                                                  scoringParameterRequest.setPaymentRecordsWithLenders_p(true);
                                              }
                                          }else {
                                              logger.error("error while getting PAYMENT_RECORDS_WITH_LENDERS parameter :- Unable to fetch DPD details");
                                              scoringParameterRequest.setPaymentRecordsWithLenders_p(false);
                                          }
                                      } catch (Exception e) {
                                          logger.error("error while getting PAYMENT_RECORDS_WITH_LENDERS parameter : ", e);
                                          scoringParameterRequest.setPaymentRecordsWithLenders_p(false);
                                      }
                            	}
                                break;
                            }
                            case ScoreParameter.CMR_SCORE_MSME_RANKING: {  // CMR RATING FETCH FROM COMMERCIAL BUREAU
                            	if(!isCibilCheck) {
                            		try {
                                    	String cmrScore = cibilClient.getCMRScore(applicationId);
                                    	 	logger.info("{CMR_SCORE_MSME_RANKING}====={cmrScore}===={}=====>",cmrScore,"==={applicationId}===>"+applicationId);
                                    	 	
                                    		if(!CommonUtils.isObjectNullOrEmpty(cmrScore) && (!cmrScore.equals("NA"))){
                                    			// String cmrValue = cmrScore.substring(4,6);
                                    			String [] cmrValue = cmrScore.trim().split("-");
        	                            			if(!CommonUtils.isObjectNullOrEmpty(cmrValue) && !CommonUtils.isObjectNullOrEmpty(cmrValue[1]))
        	                            			{
        	                            					scoringParameterRequest.setCmrScoreMsmeRanking(Double.valueOf(cmrValue[1]));
        	                            			 }
        	                            			else
        	                            			{
        	                            				scoringParameterRequest.setCmrScoreMsmeRanking(0.0);
        	                            			}
        	                            			scoringParameterRequest.setCmrScoreMsmeRanking_p(true);
                                           }else{
                                        	   scoringParameterRequest.setCmrScoreMsmeRanking_p(true);
                                        	   scoringParameterRequest.setCmrScoreMsmeRanking(0.0);
                                           }
        								} catch (Exception e) {
        									logger.error("Exception is getting while Get CMR Score CIBI:---->",e);
        									e.printStackTrace();
        								}
                            	}
                            	
                                break;
                            }
                            case ScoreParameter.ISO_CERTIFICATION: {
                            	// One form ISO CERTIFIED
                            	Boolean isoCertifiedResp = primaryCorporateDetail.getIsIsoCertified();
                            	logger.info("ENTER HERE (ISO_CERTIFICATION)::::::::::{ISO_CERTIFICATION}======{}===>>>",isoCertifiedResp);
                            	if(!CommonUtils.isObjectNullOrEmpty(isoCertifiedResp)){
                            		
                            		scoringParameterRequest.setIsoCertification_p(true);		
                            		scoringParameterRequest.setIsoCertificationVal(isoCertifiedResp);
                            	}else{
                            		scoringParameterRequest.setIsoCertification_p(true);
                            		scoringParameterRequest.setIsoCertificationVal(false);
                            		
                            	}
                                break;
                            }
                            case ScoreParameter.TOTAL_NO_OF_INWARD_CHEQUE_BOUNCES_LAST_SIX_MONTHS: {
                            	logger.info("TOTAL_NO_OF_INWARD_CHEQUE_BOUNCES_LAST_SIX_MONTHS::::::::::");
                            	
                            	try{
                            		Double totalNoOfInwardChequeBouncesLatSixMonths = 0.0;
                                    Double noOfChequeBounceLast6MonthsCount = 0.0;
                                    Double noOfChequeIssuelastSixMonthsCount = 0.0d;
                                    
                                 // NO OF CHEQUE BOUNCES   && NO OF CHEQUE ISSUE IN LAST 6 MONTHS
                                    ReportRequest reportRequest = new ReportRequest();
                                    reportRequest.setApplicationId(applicationId);
                                    AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
                                    if(!CommonUtils.isObjectNullOrEmpty(analyzerResponse) && !CommonUtils.isObjectNullOrEmpty(analyzerResponse.getData())){
                                    	
                                    	for(Object object : (List)analyzerResponse.getData()) {
                                    		try {
                        						Data dataBs = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) object, Data.class);
                        						
                        					    noOfChequeBounceLast6MonthsCount = + Double.valueOf(dataBs.getSummaryInfo().getSummaryInfoTotalDetails().getInwBounces());
                        						noOfChequeIssuelastSixMonthsCount = + Double.valueOf(dataBs.getSummaryInfo().getSummaryInfoTotalDetails().getChqIssues());
                        						
                        						
                        						if(noOfChequeIssuelastSixMonthsCount!=0.0){
                        							totalNoOfInwardChequeBouncesLatSixMonths  = (noOfChequeBounceLast6MonthsCount / noOfChequeIssuelastSixMonthsCount) * 100;
                        						}else{
                        							totalNoOfInwardChequeBouncesLatSixMonths = 0.0;
                        						}
                       							scoringParameterRequest.setTotalNoOfChequeBounceLastSixMonths_p(true);
                       							scoringParameterRequest.setTotalNoOfInwardChequeBouncesLatSixMonths(totalNoOfInwardChequeBouncesLatSixMonths);
                       							logger.info("{noOfChequeBounceLast6MonthsCount}::::::::::======{1}======{}===>>>"+noOfChequeBounceLast6MonthsCount);
                       							logger.info("{noOfChequeIssuelastSixMonthsCount}::::::::::======{2}======{}===>>>"+noOfChequeIssuelastSixMonthsCount);
                       							logger.info("{totalNoOfInwardChequeBouncesLatSixMonths}::::::::::======{3}======{}===>>>"+totalNoOfInwardChequeBouncesLatSixMonths);
                        					}catch(Exception e) {
                        						logger.error("EXCEPTION IS GETTING WHILE CALCULATE CHEQUE BOUNCES / ISSUE LOGIC=====>{}====>{}",noOfChequeIssuelastSixMonthsCount,noOfChequeBounceLast6MonthsCount,e);
                        						scoringParameterRequest.setChequesBouncedLastSixMonth_p(false);
                        					}
                        				}
                                    }
                                   
                                }catch (Exception e){
                                    logger.error("error while getting NO_OF_CHEQUES_BOUNCED_LAST_SIX_MONTH parameter : ",e);
                                    scoringParameterRequest.setChequesBouncedLastSixMonth_p(false);
                                }
                                
                                break;
                            }

                            default:
                            	break;
                        }

                        //fundSeekerInputRequestList.add(fundSeekerInputRequest);
                    }

                    logger.info(MSG_SCORE_PARAMETER + scoringParameterRequest.toString());

                    logger.info("----------------------------END-----------------------------------------------");
                }
                Gson g = new Gson();
                ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                try {
                    scoringRequestDetail.setApplicationId(applicationId);
                    scoringRequestDetail.setRequest(g.toJson(scoringParameterRequest));
                    scoringRequestDetail.setCreatedDate(new Date());
                    scoringRequestDetail.setIsActive(true);
                    scoringRequestDetailRepository.save(scoringRequestDetail);

                    logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                } catch (Exception e) {
                    logger.error(CommonUtils.EXCEPTION,e);
                }
            }

            scoringRequest.setScoringParameterRequest(scoringParameterRequest);
            scoringRequestList.add(scoringRequest);
        }

        try {
            scoringResponseMain = scoringClient.calculateScoreList(scoringRequestList);
        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }

        if (scoringResponseMain.getStatus() == HttpStatus.OK.value()) {
            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        } else {
            logger.error(ERROR_WHILE_CALLING_SCORING);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

    private Object[] moveAheadFromItr(Long applicationId){
        Object[] itrResponseObj =new Object[2];
        Boolean isMovieAhead = false;
        Integer itrType = null;
        ITRConnectionResponse itrConnectionResponse = null;
        try {
            itrConnectionResponse = itrClient.isMoveAheadForMatches(applicationId);
        }catch (Exception e){
            logger.error("error while calling itr client for moveAheadFromItr()");
            logger.error(CommonUtils.EXCEPTION+e.getMessage(), e);
        }
        try {
            if(itrConnectionResponse != null && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse) && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse.getData())){
                Map<String,Object> map = (Map<String,Object>)itrConnectionResponse.getData();
                ITRBasicDetailsResponse res = MultipleJSONObjectHelper.getObjectFromMap(map, ITRBasicDetailsResponse.class);
                if(!CommonUtils.isObjectNullOrEmpty(res)){
                    isMovieAhead = res.getIsMoveAhead();
                    itrType = res.getItrFinancialType();
                }
            }
        } catch (IOException e) {
            logger.error("error while getting move ahead from itr response");
            logger.error(CommonUtils.EXCEPTION+e.getMessage(), e);
        }
        itrResponseObj[0] = isMovieAhead;
        itrResponseObj[1] = itrType;
        return itrResponseObj;
    }

    private Double[] getDebtAndEquityValue(LiabilitiesDetails liabilitiesDetails){

        Double debt = liabilitiesDetails.getTotalTermLiabilities() -
                liabilitiesDetails.getPreferencesShares() +
                liabilitiesDetails.getOtherNclUnsecuredLoansFromOther() +
                liabilitiesDetails.getOtherNclOthers() +
                liabilitiesDetails.getMinorityInterest() +
                liabilitiesDetails.getDeferredTaxLiability();

        Double equity = liabilitiesDetails.getPreferencesShares() +
                liabilitiesDetails.getNetWorth() -
                liabilitiesDetails.getMinorityInterest() -
                liabilitiesDetails.getDeferredTaxLiability();


        if (CommonUtils.isObjectNullOrEmpty(debt))
            debt = 0.0;

        if (CommonUtils.isObjectNullOrEmpty(equity))
            equity = 0.0;

        return new Double[]{debt,equity};

    }

    private Double[] getTolTnwValues(LiabilitiesDetails liabilitiesDetails,AssetsDetails assetsDetails){
        Double tol = liabilitiesDetails.getTotalOutsideLiabilities();
        if (CommonUtils.isObjectNullOrEmpty(tol))
            tol = 0.0;

        Double tnw = assetsDetails.getTangibleNetWorth();
        if (CommonUtils.isObjectNullOrEmpty(tnw))
            tnw = 0.0;

        return new Double[]{tol,tnw};
    }

    private Double[] getDebtorsCreditorsCogsAvgInvValues(OperatingStatementDetails operatingStatementDetails,AssetsDetails assetsDetails,LiabilitiesDetails liabilitiesDetails){

        Double debtorsDays = null;
        if ((operatingStatementDetails.getTotalGrossSales() - operatingStatementDetails.getAddOtherRevenueIncome()) != 0.0) {
            debtorsDays = ((assetsDetails.getReceivableOtherThanDefferred() + assetsDetails.getExportReceivables()) / (operatingStatementDetails.getTotalGrossSales() - operatingStatementDetails.getAddOtherRevenueIncome())) * 365;
        }
        if (CommonUtils.isObjectNullOrEmpty(debtorsDays))
            debtorsDays = 0.0;


        Double averageInventory = (operatingStatementDetails.getAddOperatingStockFg() + operatingStatementDetails.getDeductClStockFg()) / 2;
        if (CommonUtils.isObjectNullOrEmpty(averageInventory))
            averageInventory = 0.0;

        Double cogs = operatingStatementDetails.getRawMaterials() + operatingStatementDetails.getAddOperatingStockFg() - operatingStatementDetails.getDeductClStockFg();
        if (CommonUtils.isObjectNullOrEmpty(cogs))
            cogs = 0.0;

        Double creditorsDays = null;
        if ((operatingStatementDetails.getTotalGrossSales() - operatingStatementDetails.getAddOtherRevenueIncome()) != 0) {
            creditorsDays = (liabilitiesDetails.getSundryCreditors() / (operatingStatementDetails.getTotalGrossSales() - operatingStatementDetails.getAddOtherRevenueIncome())) * 365;
        }
        if (CommonUtils.isObjectNullOrEmpty(creditorsDays))
            creditorsDays = 0.0;


        return new Double[]{debtorsDays,averageInventory,cogs,creditorsDays};
    }

    private Double[] getDebtEbitdaValues(LiabilitiesDetails liabilitiesDetails,AssetsDetails assetsDetails,OperatingStatementDetails operatingStatementDetails){

        Double totalTermLiabilities = liabilitiesDetails.getTotalTermLiabilities();
        Double preferenceShares = liabilitiesDetails.getPreferencesShares();
        Double others = liabilitiesDetails.getOthers();
        Double minorityInterest = liabilitiesDetails.getMinorityInterest();
        Double deferredTaxLiability = liabilitiesDetails.getDeferredTaxLiability();
        Double deferredTaxAsserts = assetsDetails.getDeferredTaxAssets();
        Double otherNclUnsecuredLoansFromOther = liabilitiesDetails.getOtherNclUnsecuredLoansFromOther();
        Double opProfitBeforeIntrest = operatingStatementDetails.getOpProfitBeforeIntrest();
        Double depreciation = operatingStatementDetails.getDepreciation();

        return  new Double[]{totalTermLiabilities,preferenceShares,others,minorityInterest,deferredTaxLiability,deferredTaxAsserts,otherNclUnsecuredLoansFromOther,opProfitBeforeIntrest,depreciation};
    }

    private Double[] getAvgAnnualGrossCaseAccrualsValue(OperatingStatementDetails operatingStatementDetails,AssetsDetails assetsDetails){

        Double netProfitOrLoss = operatingStatementDetails.getNetProfitOrLoss();
        if (CommonUtils.isObjectNullOrEmpty(netProfitOrLoss))
            netProfitOrLoss = 0.0;

        Double interest = operatingStatementDetails.getInterest();
        if (CommonUtils.isObjectNullOrEmpty(interest))
            interest = 0.0;

        Double depreciation = operatingStatementDetails.getDepreciation();
        if (CommonUtils.isObjectNullOrEmpty(depreciation))
            depreciation = 0.0;

        Double totalAsset = assetsDetails.getTotalAssets();
        if (CommonUtils.isObjectNullOrEmpty(totalAsset))
            totalAsset = 0.0;

        return  new Double[]{netProfitOrLoss,interest,depreciation,totalAsset};
    }

    private Double[] getAvgEBIDTAValue(OperatingStatementDetails operatingStatementDetails,LiabilitiesDetails liabilitiesDetails){
        Double profitBeforeTaxOrLoss = operatingStatementDetails.getProfitBeforeTaxOrLoss();
        if (CommonUtils.isObjectNullOrEmpty(profitBeforeTaxOrLoss))
            profitBeforeTaxOrLoss = 0.0;


        Double interest = operatingStatementDetails.getInterest();
        if (CommonUtils.isObjectNullOrEmpty(interest))
            interest = 0.0;

        Double depreciation = operatingStatementDetails.getDepreciation();
        if (CommonUtils.isObjectNullOrEmpty(depreciation))
            depreciation = 0.0;

        Double termLoans = liabilitiesDetails.getTermLoans();
        if (CommonUtils.isObjectNullOrEmpty(termLoans))
            termLoans = 0.0;

        return new Double[]{profitBeforeTaxOrLoss,interest,depreciation,termLoans};
    }

    private Double[] getTurnOverATNWValue(OperatingStatementDetails operatingStatementDetails,LiabilitiesDetails liabilitiesDetails,AssetsDetails assetsDetails){

        Double ordinarySharesCapital = getOrDefauls(liabilitiesDetails.getOrdinarySharesCapital());
        Double generalReserve = getOrDefauls(liabilitiesDetails.getGeneralReserve());
        Double surplusOrDeficit = getOrDefauls(liabilitiesDetails.getSurplusOrDeficit());
        Double nclUnsercuredLoansFromPromotors = getOrDefauls(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters());
        Double nlcUnsercuredLoansFromOthers =  getOrDefauls(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther());
        Double investmentsInSubSidiary = getOrDefauls(assetsDetails.getInvestmentsInSubsidiary());
        Double domestivSales = getOrDefauls(operatingStatementDetails.getDomesticSales());
        Double exportSales = getOrDefauls(operatingStatementDetails.getExportSales());

        return new Double[]{ordinarySharesCapital,generalReserve,surplusOrDeficit,nclUnsercuredLoansFromPromotors,nlcUnsercuredLoansFromOthers,investmentsInSubSidiary,domestivSales,exportSales};
    }

    private Double getOrDefauls(Double obj){
        return  CommonUtils.isObjectNullOrEmpty(obj)==true?0.0:obj;
    }


    @Override
    public ResponseEntity<LoansResponse> calculateNTBScoring(ScoringRequestLoans scoringRequestLoans, PrimaryCorporateDetail primaryCorporateDetail) {


        CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findByApplicationIdIdAndIsActive(primaryCorporateDetail.getApplicationId().getId(), true);

        // Fetch Data for Calculate Director Score

        List<DirectorBackgroundDetail> directorBackgroundDetailsList = directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(scoringRequestLoans.getApplicationId());

        logger.info("directorBackgroundDetailsList.size()==========>>" + directorBackgroundDetailsList.size());

        if (directorBackgroundDetailsList.size() > 0) {
            for (DirectorBackgroundDetail directorBackgroundDetail : directorBackgroundDetailsList) {
                Boolean flag = calculateDirectorScore(scoringRequestLoans, directorBackgroundDetail, primaryCorporateDetail);
            }
        }

        // Fetch Data for Calculate Company Score

        com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest scoreParameterNTBRequest = new com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest();

        Long scoreModelId = scoringRequestLoans.getScoringModelId();
        Long applicationId = scoringRequestLoans.getApplicationId();
        Long fpProductId = scoringRequestLoans.getFpProductId();

        logger.info("----------------------------START NTB LOAN------------------------------");

        logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

        ScoringResponse scoringResponseMain = null;

        // GET SCORE NTB LOAN PARAMETERS


        if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.NTB);

            // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
            ScoringResponse scoringResponse = null;
            try {
                scoringResponse = scoringClient.listField(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
            }

            List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();

            List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

            for (int i = 0; i < dataList.size(); i++) {

                ModelParameterResponse modelParameterResponse = null;
                try {
                    modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                            ModelParameterResponse.class);
                } catch (IOException e) {
                    logger.error(CommonUtils.EXCEPTION,e);
                }

                FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                fundSeekerInputRequest.setName(modelParameterResponse.getName());

                switch (modelParameterResponse.getName()) {

                    case ScoreParameter.NTB.WORKING_EXPERIENCE: {
                        scoreParameterNTBRequest.setIsWorkingExperience(true);
                        break;
                    }
                    case ScoreParameter.NTB.IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS: {
                        scoreParameterNTBRequest.setIsFamilyMemberInLineOfBusiness(true);
                        break;
                    }
                    case ScoreParameter.NTB.CIBIL_TRANSUNION_SCORE: {
                        scoreParameterNTBRequest.setIsCibilTransunionScore(true);
                        break;
                    }
                    case ScoreParameter.NTB.AGE_OF_PROMOTOR: {
                        scoreParameterNTBRequest.setIsAgeOfPromotor(true);
                        break;
                    }
                    case ScoreParameter.NTB.EDUCATION_QUALIFICATION: {
                        scoreParameterNTBRequest.setIsEducationQualification(true);
                        break;
                    }
                    case ScoreParameter.NTB.EMPLOYMENT_TYPE: {
                        scoreParameterNTBRequest.setIsEmploymentType(true);
                        break;
                    }
                    case ScoreParameter.NTB.HOUSE_OWNERSHIP: {
                        scoreParameterNTBRequest.setIsHouseOwnership(true);
                        break;
                    }
                    case ScoreParameter.NTB.MARITIAL_STATUS: {
                        scoreParameterNTBRequest.setIsMaritialStatus(true);
                        break;
                    }
                    case ScoreParameter.NTB.ITR_SALARY_INCOME: {
                        scoreParameterNTBRequest.setIsItrSalaryIncome(true);
                        break;
                    }
                    case ScoreParameter.NTB.FIXED_OBLIGATION_RATIO: {
                        scoreParameterNTBRequest.setIsFixedObligationRatio(true);
                        break;
                    }
                    case ScoreParameter.NTB.CHEQUE_BOUNCES: {
                        scoreParameterNTBRequest.setIsChequeBounces(true);
                        break;
                    }
                    case ScoreParameter.NTB.DPD: {
                        scoreParameterNTBRequest.setIsDPD(true);
                        break;
                    }
                    case ScoreParameter.NTB.CONSTITUTION_OF_BORROWER: {
                        try {
                            Long proposedConstitutionOfUnit = Long.parseLong(primaryCorporateDetail.getProposedConstitutionOfUnit().toString());

                            if (!CommonUtils.isObjectNullOrEmpty(proposedConstitutionOfUnit)) {
                                scoreParameterNTBRequest.setConstitutionOfBorrowe(proposedConstitutionOfUnit);
                                scoreParameterNTBRequest.setIsConstitutionOfBorrower(true);
                            } else {
                                scoreParameterNTBRequest.setIsConstitutionOfBorrower(false);
                            }
                        } catch (Exception e) {
                            logger.error("error while getting CONSTITUTION_OF_BORROWER parameter : ",e);
                            scoreParameterNTBRequest.setIsConstitutionOfBorrower(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.ASSET_COVERAGE_RATIO: {
                        try {

                            Double collatralValue = 0.0;
                            CGTMSEDataResponse cgtmseDataResponse = thirdPartyClient.getCalulation(applicationId);
                            if (!CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse)
                                    && !CommonUtils.isObjectNullOrEmpty(cgtmseDataResponse.getColleteralCoverage())) {
                                collatralValue = cgtmseDataResponse.getColleteralCoverage();
                            }

                            scoreParameterNTBRequest.setColatralValue(collatralValue);
                            scoreParameterNTBRequest.setIsAssetCoverageRatio(true);
                        } catch (Exception e) {
                            logger.error("error while getting ASSET_COVERAGE_RATIO parameter : ",e);
                            scoreParameterNTBRequest.setIsAssetCoverageRatio(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.UNIT_FACTORY_PREMISES: {
                        try {
                            Long unitFactoryPremises = primaryCorporateDetail.getProposedDetailsOfUnit().longValue();
                            scoreParameterNTBRequest.setUnitFactoryPremisesDetails(unitFactoryPremises);
                            scoreParameterNTBRequest.setIsUnitFactoryPremises(true);
                        } catch (Exception e) {
                            logger.error("error while getting UNIT_FACTORY_PREMISES parameter : ",e);
                            scoreParameterNTBRequest.setIsUnitFactoryPremises(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.BALANCE_GESTATION_PERIOD: {
                        try {

                            Integer years = 0;
                            java.util.Calendar proposedDate = java.util.Calendar.getInstance();
                            proposedDate.setTime(primaryCorporateDetail.getProposedOperationDate());

                            java.util.Calendar appCreatedDate = java.util.Calendar.getInstance();
                            appCreatedDate.setTime(primaryCorporateDetail.getCreatedDate());

                            Integer yearsInBetween = proposedDate.get(java.util.Calendar.YEAR) - appCreatedDate.get(java.util.Calendar.YEAR);
                            Integer monthsDiff = 1;

                            monthsDiff = monthsDiff + proposedDate.get(java.util.Calendar.MONTH) - appCreatedDate.get(java.util.Calendar.MONTH);

                            Integer months = yearsInBetween * 12 + monthsDiff;

                            scoreParameterNTBRequest.setBalanceGestationPeriod(months.doubleValue());

                            if (months.doubleValue() >= 0)
                                scoreParameterNTBRequest.setIsBalanceGestationPeriod(true);
                            else
                                scoreParameterNTBRequest.setIsBalanceGestationPeriod(false);

                        } catch (Exception e) {
                            logger.error("error while getting BALANCE_GESTATION_PERIOD parameter : ",e);
                            scoreParameterNTBRequest.setIsBalanceGestationPeriod(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.ENVIRONMENT_CATEGORY: {

                        // start get business type id from irr

                        Integer businessTypeId = null; // get from irr-cw industry mapping

                        IrrRequest irrIndustryRequest = new IrrRequest();

                        Long irrId = null;
                        try {
                            irrId = loanApplicationService.getIrrByApplicationId(applicationId);

                        } catch (Exception e) {
                            logger.error("error while getting irr id from one form : ",e);
                        }

                        // start getting irr industry and business type
                        try {

                            irrIndustryRequest.setIrrIndustryId(irrId);
                            irrIndustryRequest = ratingClient.getIrrIndustry(irrIndustryRequest);
                            IndustryResponse industryResponse = irrIndustryRequest.getIndustryResponse();
                            if (CommonUtils.isObjectNullOrEmpty(industryResponse)) {
                                logger.info("Error while getting irr id from rating");
                            }

                            businessTypeId = industryResponse.getBusinessTypeId();
                        } catch (Exception e) {
                            logger.error("error while getting irr industry detail from rating : ",e);
                        }

                        // end get business type id from irr

                        try {

                            logger.info("businessTypeId:===============:::::::::::::==========" + businessTypeId);
                            if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) && com.capitaworld.service.rating.utils.CommonUtils.BusinessType.MANUFACTURING == businessTypeId) {
                                Long envCategoryId = corporateApplicantDetail.getEnvironmentalImpactId();

                                logger.info("envCategoryId:===============:::::::::::::==========" + envCategoryId);
                                if (!CommonUtils.isObjectNullOrEmpty(envCategoryId)) {
                                    try {

                                        OneFormResponse oneFormResponse = oneFormClient.getEnvironmentCategoryIdById(envCategoryId);

                                        Integer environmentCategoryId = (Integer) oneFormResponse.getData();
                                        scoreParameterNTBRequest.setEnvironmentCategory(environmentCategoryId.longValue());
                                        logger.info("environmentCategoryId from one form client:===============:::::::::::::==========" + environmentCategoryId);
                                        scoreParameterNTBRequest.setIsEnvironmentCategory(true);
                                    } catch (Exception e) {
                                        logger.error("Error while calling one form client : ",e);
                                    }
                                } else {
                                    scoreParameterNTBRequest.setIsEnvironmentCategory(false);
                                }

                            } else if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) && com.capitaworld.service.rating.utils.CommonUtils.BusinessType.SERVICE == businessTypeId) {
                                scoreParameterNTBRequest.setEnvironmentCategory(EnvironmentCategory.SERVICE.getId().longValue());
                                scoreParameterNTBRequest.setIsEnvironmentCategory(true);
                            } else if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) && com.capitaworld.service.rating.utils.CommonUtils.BusinessType.TRADING == businessTypeId) {
                                scoreParameterNTBRequest.setEnvironmentCategory(EnvironmentCategory.TRADING.getId().longValue());
                                scoreParameterNTBRequest.setIsEnvironmentCategory(true);
                            } else {
                                scoreParameterNTBRequest.setIsEnvironmentCategory(false);
                            }
                        } catch (Exception e) {
                            logger.error("error while getting ENVIRONMENT_CATEGORY parameter : ",e);
                            scoreParameterNTBRequest.setIsEnvironmentCategory(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.CNW: {
                        try {
                            Double networth = directorBackgroundDetailsRepository.getSumOfDirectorsNetworth(applicationId);
                            if (CommonUtils.isObjectNullOrEmpty(networth)) {
                                networth = 0.0;
                            }
                            scoreParameterNTBRequest.setNetworth(networth);

                            Double loanAmount = primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);

                            if (!CommonUtils.isObjectNullOrEmpty(loanAmount)) {
                                scoreParameterNTBRequest.setLoanAmount(loanAmount);
                                scoreParameterNTBRequest.setIsCNW(true);
                            } else {
                                scoreParameterNTBRequest.setIsCNW(false);
                            }

                        } catch (Exception e) {
                            logger.error("error while getting CNW parameter : ",e);
                            scoreParameterNTBRequest.setIsCNW(false);
                        }
                        break;
                    }
                        default : break;
                }
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            logger.info(MSG_SCORE_PARAMETER + scoreParameterNTBRequest.toString());

            logger.info("----------------------------END-----------------------------------------");

            scoringRequest.setDataList(fundSeekerInputRequestList);
            scoringRequest.setScoreParameterNTBRequest(scoreParameterNTBRequest);

            try {
                scoringResponseMain = scoringClient.calculateScore(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_CALLING_SCORING,e);
                LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }

            if (scoringResponseMain.getStatus() == HttpStatus.OK.value()) {
                logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
                LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            } else {
                logger.error(ERROR_WHILE_CALLING_SCORING);
                LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }
        }

        LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
    }

    public Boolean calculateDirectorScore(ScoringRequestLoans scoringRequestLoans, DirectorBackgroundDetail directorBackgroundDetail, PrimaryCorporateDetail primaryCorporateDetail) {


        // Fetch Data for Calculate Director Score

        com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest scoreParameterNTBRequest = new com.capitaworld.service.scoring.model.scoringmodel.ScoreParameterNTBRequest();

        Long scoreModelId = scoringRequestLoans.getScoringModelId();
        Long applicationId = scoringRequestLoans.getApplicationId();
        Long fpProductId = scoringRequestLoans.getFpProductId();

        logger.info("----------------------------START NTB DIRECTOR------------------------------");

        logger.info("DIRECTOR ID :: " + directorBackgroundDetail.getId() + MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId );

        ScoringResponse scoringResponseMain = null;

        // GET SCORE NTB LOAN PARAMETERS


        if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.NTB);
            scoringRequest.setDirectorId(directorBackgroundDetail.getId());

            // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
            ScoringResponse scoringResponse = null;
            try {
                scoringResponse = scoringClient.listField(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
            }

            List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();

            List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

            for (int i = 0; i < dataList.size(); i++) {

                ModelParameterResponse modelParameterResponse = null;
                try {
                    modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                            ModelParameterResponse.class);
                } catch (IOException e) {
                    logger.error(CommonUtils.EXCEPTION,e);
                }

                FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                fundSeekerInputRequest.setName(modelParameterResponse.getName());

                switch (modelParameterResponse.getName()) {

                    case ScoreParameter.NTB.WORKING_EXPERIENCE: {

                        try {
                            Double totalExperience = directorBackgroundDetail.getTotalExperience();
                            if (CommonUtils.isObjectNullOrEmpty(totalExperience)) {
                                totalExperience = 0.0;
                            }
                            scoreParameterNTBRequest.setTotalworkingExperience(totalExperience);
                            scoreParameterNTBRequest.setIsWorkingExperience(true);
                        } catch (Exception e) {
                            logger.error("error while getting WORKING_EXPERIENCE parameter : ",e);
                            scoreParameterNTBRequest.setIsWorkingExperience(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS: {
                        try {
                            Boolean isFamilyMemberInBusiness = directorBackgroundDetail.getFamilyMemberInBusiness();
                            if (CommonUtils.isObjectNullOrEmpty(isFamilyMemberInBusiness) || isFamilyMemberInBusiness == false) {
                                scoreParameterNTBRequest.setFamilyMemberInLineOfBusiness(2l);
                            } else {
                                scoreParameterNTBRequest.setFamilyMemberInLineOfBusiness(1l);
                            }
                            scoreParameterNTBRequest.setIsFamilyMemberInLineOfBusiness(true);
                        } catch (Exception e) {
                            logger.error("error while getting IS_FAMILY_MEMBER_IN_LINE_OF_BUSINESS parameter : ",e);
                            scoreParameterNTBRequest.setIsFamilyMemberInLineOfBusiness(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.CIBIL_TRANSUNION_SCORE: {
                        try {
                            CibilRequest cibilRequest = new CibilRequest();
                            cibilRequest.setApplicationId(applicationId);
                            cibilRequest.setPan(directorBackgroundDetail.getPanNo());

                            CibilScoreLogRequest cibilScoreLogRequest = cibilClient.getCibilScoreByPanCard(cibilRequest);
                            if (!CommonUtils.isObjectNullOrEmpty(cibilScoreLogRequest) && !CommonUtils.isObjectNullOrEmpty(cibilScoreLogRequest.getScore())) {
                                Double cibilScore = Double.parseDouble(cibilScoreLogRequest.getScore());
                                scoreParameterNTBRequest.setCibilTransunionScore(cibilScore);
                                scoreParameterNTBRequest.setIsCibilTransunionScore(true);
                            } else {
                                scoreParameterNTBRequest.setIsCibilTransunionScore(false);
                            }
                        } catch (Exception e) {
                            logger.error("error while getting CIBIL_TRANSUNION_SCORE parameter : ",e);
                            scoreParameterNTBRequest.setIsCibilTransunionScore(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.AGE_OF_PROMOTOR: {
                        try {

                            if (!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail.getDob())) {
                                scoreParameterNTBRequest.setAgeOfPromotor(Math.ceil(CommonUtils.getAgeFromBirthDate(directorBackgroundDetail.getDob()).doubleValue()));
                                scoreParameterNTBRequest.setIsAgeOfPromotor(true);
                            } else {
                                scoreParameterNTBRequest.setIsAgeOfPromotor(false);
                            }
                        } catch (Exception e) {
                            logger.error("error while getting AGE_OF_PROMOTOR parameter : ",e);
                            scoreParameterNTBRequest.setIsAgeOfPromotor(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.EDUCATION_QUALIFICATION: {
                        try {
                            Long qualificationId = directorBackgroundDetail.getQualificationId().longValue();
                            scoreParameterNTBRequest.setEducationQualification(qualificationId);
                            scoreParameterNTBRequest.setIsEducationQualification(true);
                        } catch (Exception e) {
                            logger.error("error while getting EDUCATION_QUALIFICATION parameter : ",e);
                            scoreParameterNTBRequest.setIsEducationQualification(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.EMPLOYMENT_TYPE: {
                        try {
                            Long empType = directorBackgroundDetail.getEmploymentDetail().getEmploymentStatus();

                            if (!CommonUtils.isObjectNullOrEmpty(empType)) {
                                scoreParameterNTBRequest.setEmployeeType(empType);
                                scoreParameterNTBRequest.setIsEmploymentType(true);
                            } else {
                                scoreParameterNTBRequest.setIsEmploymentType(false);
                            }

                        } catch (Exception e) {
                            logger.error("error while getting EMPLOYMENT_TYPE parameter : ",e);
                            scoreParameterNTBRequest.setIsEmploymentType(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.HOUSE_OWNERSHIP: {
                        try {

                            Long residentType = directorBackgroundDetail.getResidenceType().longValue();
                            scoreParameterNTBRequest.setHouseOwnerShip(residentType);
                            scoreParameterNTBRequest.setIsHouseOwnership(true);
                        } catch (Exception e) {
                            logger.error("error while getting HOUSE_OWNERSHIP parameter : ",e);
                            scoreParameterNTBRequest.setIsHouseOwnership(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.MARITIAL_STATUS: {
                        try {

                            Long maritialStatus = directorBackgroundDetail.getMaritalStatus().longValue();
                            scoreParameterNTBRequest.setMaritialStatus(maritialStatus);
                            scoreParameterNTBRequest.setIsMaritialStatus(true);
                        } catch (Exception e) {
                            logger.error("error while getting MARITIAL_STATUS parameter : ",e);
                            scoreParameterNTBRequest.setIsMaritialStatus(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.ITR_SALARY_INCOME: {
                        try {
                            logger.info("Application id ===========>" + applicationId);
                            logger.info("directorBackgroundDetail id ===========>" + directorBackgroundDetail.getId());
                            Double avgSalary = corporateDirectorIncomeDetailsRepository.getTotalSalaryByApplicationIdAndDirectorId(applicationId, directorBackgroundDetail.getId());
                            if (avgSalary != 0) {
                                avgSalary = avgSalary / 3;
                            }

                            Double promotorContribution = primaryCorporateDetail.getPromoterContribution();

                            if (CommonUtils.isObjectNullOrEmpty(promotorContribution)) {
                                promotorContribution = 0.0;
                            }

                            scoreParameterNTBRequest.setItrSalaryIncomeAvg(avgSalary);
                            scoreParameterNTBRequest.setItrPromotorContribution(promotorContribution);
                            scoreParameterNTBRequest.setIsItrSalaryIncome(true);
                        } catch (Exception e) {
                            logger.error("error while getting ITR_SALARY_INCOME parameter : ",e);
                            scoreParameterNTBRequest.setIsItrSalaryIncome(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.FIXED_OBLIGATION_RATIO: {
                        try {

                            Double totalIncome = corporateDirectorIncomeDetailsRepository.getTotalIncomeByApplicationIdAndDirectorId(applicationId, directorBackgroundDetail.getId());
                            Double totalEMI = financialArrangementDetailsRepository.getTotalEmiByApplicationIdAndDirectorId(applicationId, directorBackgroundDetail.getId());

                            if (CommonUtils.isObjectNullOrEmpty(totalIncome)) {
                                totalIncome = 0.0;
                            }

                            if (CommonUtils.isObjectNullOrEmpty(totalEMI)) {
                                totalEMI = 0.0;
                            }

                            scoreParameterNTBRequest.setItrSalaryIncome(totalIncome);
                            scoreParameterNTBRequest.setTotalEmiPaid(totalEMI);
                            scoreParameterNTBRequest.setIsFixedObligationRatio(true);
                        } catch (Exception e) {
                            logger.error("error while getting FIXED_OBLIGATION_RATIO parameter : ",e);
                            scoreParameterNTBRequest.setIsFixedObligationRatio(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.CHEQUE_BOUNCES: {
                        try {
                            Double noOfChequeBounce = null;
                            ReportRequest reportRequest = new ReportRequest();
                            reportRequest.setApplicationId(applicationId);
                            reportRequest.setDirectorId(directorBackgroundDetail.getId());

                            AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);

                            Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),
                                    Data.class);
                            if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month())) {
                                {
                                    if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month().doubleValue())) {
                                        noOfChequeBounce = data.getCheckBounceForLast6Month().doubleValue();
                                    } else {
                                        noOfChequeBounce = 0.0;
                                    }

                                }
                            } else {
                                noOfChequeBounce = 0.0;
                            }

                            scoreParameterNTBRequest.setChequeBouncesPastSixMonths(noOfChequeBounce);
                            scoreParameterNTBRequest.setIsChequeBounces(true);
                        } catch (Exception e) {
                            logger.error("error while getting CHEQUE_BOUNCES parameter : ",e);
                            scoreParameterNTBRequest.setIsChequeBounces(false);
                        }
                        break;
                    }
                    case ScoreParameter.NTB.DPD: {
                        try {

                            //remaining
                            scoreParameterNTBRequest.setIsDPD(false);
                        } catch (Exception e) {
                            logger.error("error while getting DPD parameter : ",e);
                            scoreParameterNTBRequest.setIsDPD(false);
                        }
                        break;
                    }
                        default : break;
                }
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            logger.info(MSG_SCORE_PARAMETER + scoreParameterNTBRequest.toString());

            logger.info("----------------------------END---------------------------------------------");

            scoringRequest.setDataList(fundSeekerInputRequestList);
            scoringRequest.setScoreParameterNTBRequest(scoreParameterNTBRequest);

            try {
                scoringResponseMain = scoringClient.calculateScore(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_CALLING_SCORING,e);
                return false;
            }

            if (scoringResponseMain.getStatus() == HttpStatus.OK.value()) {
                logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
                return true;
            } else {
                logger.error(ERROR_WHILE_CALLING_SCORING);
                return false;
            }
        }

        return null;
    }


    @Override
    public ResponseEntity<LoansResponse> calculateScoringTest(ScoringRequestLoans scoringRequestLoans) {

        ScoringParameterRequest scoringParameterRequest = new ScoringParameterRequest();

        logger.info("SCORE PARAMETER BEFORE::::::::::" + scoringRequestLoans.getScoreParameterRequestLoans().toString());

        BeanUtils.copyProperties(scoringRequestLoans.getScoreParameterRequestLoans(), scoringParameterRequest);

        Long scoreModelId = scoringRequestLoans.getScoringModelId();
        Long applicationId = scoringRequestLoans.getApplicationId();

        logger.info("----------------------------START------------------------------");

        logger.info(MSG_SCORING_MODEL_ID + scoreModelId);

        ScoringResponse scoringResponseMain = null;

        ///////////////

        // GET SCORE CORPORATE LOAN PARAMETERS


        if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setApplicationId(applicationId);

            // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
            ScoringResponse scoringResponse = null;
            try {
                scoringResponse = scoringClient.listField(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
            }

            List<Map<String, Object>> dataList = new ArrayList<>();
            if (scoringResponse != null && scoringResponse.getDataList() != null ) {
                dataList = (List<Map<String, Object>>) scoringResponse.getDataList();
            }

            List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

            for (int i = 0; i < dataList.size(); i++) {

                ModelParameterResponse modelParameterResponse = null;
                try {
                    modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                            ModelParameterResponse.class);
                } catch (IOException | NullPointerException e) {
                    logger.error(CommonUtils.EXCEPTION,e);
                }

                FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                if (modelParameterResponse != null) {
                    fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                    fundSeekerInputRequest.setName(modelParameterResponse.getName());
                }
                fundSeekerInputRequestList.add(fundSeekerInputRequest);
            }

            logger.info(MSG_SCORE_PARAMETER + scoringParameterRequest.toString());

            logger.info("----------------------------END--------------------------------");

            scoringRequest.setDataList(fundSeekerInputRequestList);
            scoringRequest.setScoringParameterRequest(scoringParameterRequest);
            scoringRequest.setTestingApiCall(true);

            try {
                scoringResponseMain = scoringClient.calculateScore(scoringRequest);
            } catch (Exception e) {
                logger.error(ERROR_WHILE_CALLING_SCORING,e);
                LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }

            if (scoringResponseMain.getStatus() == HttpStatus.OK.value()) {
                logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
                LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
                loansResponse.setData(scoringResponseMain.getDataObject());
                loansResponse.setListData(scoringResponseMain.getDataList());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            } else {
                logger.error(ERROR_WHILE_CALLING_SCORING);
                LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }
        }

        LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
    }

    @SuppressWarnings("resource")
    @Override
    public Workbook readScoringExcel(MultipartFile multipartFile) throws IllegalStateException, InvalidFormatException, IOException, LoansException {
        logger.info("-----------------------------Enter in readScoringExcel()-----------------------------------> MultiPartfile " + multipartFile);
        InputStream file;
        Workbook workbook = null;
        Sheet scoreSheet;
        List<ScoreParameterRequestLoans> scoreParameterRequestLoansList = null;
        try {
            file = new ByteArrayInputStream(multipartFile.getBytes());
            workbook = new XSSFWorkbook(file);
            scoreSheet = workbook.getSheetAt(0);
            scoreParameterRequestLoansList = ScoreExcelReader.extractCellFromSheet(scoreSheet);

            // ScoringRequestLoans List
            List<LoansResponse> loansResponseList = new ArrayList<LoansResponse>();
            ScoringRequestLoans scoringRequestLoans = null;
            logger.info("calculating scorring()----------------------------------->");
            for (ScoreParameterRequestLoans scoreParameterRequestLoans : scoreParameterRequestLoansList) {
                scoringRequestLoans = new ScoringRequestLoans();
                scoringRequestLoans.setScoreParameterRequestLoans(scoreParameterRequestLoans);
                scoringRequestLoans.setApplicationId(scoreParameterRequestLoans.getTestId().longValue());
                scoringRequestLoans.setScoringModelId(1l);

                loansResponseList.add(calculateScoringTest(scoringRequestLoans).getBody());

            }
            logger.info("calculating scorring() list size-----------------------> " + loansResponseList.size());
            workbook = generateScoringExcel(loansResponseList);
            logger.info("------------------------Exit from readScoringExcel() ---------------name of sheet in workook -----------------------> " + workbook.getSheetName(0));

        } catch (NullPointerException | IOException e) {
            logger.error("----------------Error/Exception while calculating scorring()------------------------------> " + e.getMessage());
            throw new LoansException(e);
        }
        return workbook;
    }

    @Override
    public Workbook generateScoringExcel(List<LoansResponse> loansResponseList) throws LoansException {
        logger.info("----------------Enter in  generateScoringExcel() ------------------------------>");
        return new ScoreExcelFileGenerator().scoreResultExcel(loansResponseList, environment);

    }

    @Override
    public ScoringModelReqRes getScoringModelTempList(ScoringModelReqRes scoringModelReqRes) {
        try {
            UserResponse userResponse = usersClient.getOrgIdFromUserId(scoringModelReqRes.getUserId());

            if (!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
                scoringModelReqRes.setOrgId(Long.parseLong(userResponse.getData().toString()));
            } else {
                logger.debug(ORG_ID_IS_NULL_OR_EMPTY + "In getScoringModelTempList");
                return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            logger.error(ORG_ID_IS_NULL_OR_EMPTY,e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

        try {

            return scoringClient.getScoringModelTempList(scoringModelReqRes);
        } catch (Exception e) {
            logger.error("error while geting score model list from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

    }

    @Override
    public ScoringModelReqRes saveScoringModelTemp(ScoringModelReqRes scoringModelReqRes) {

        try {

            UserResponse userResponse = usersClient.getOrgIdFromUserId(scoringModelReqRes.getUserId());

            if (!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
                scoringModelReqRes.getScoringModelResponse().setOrgId(Long.parseLong(userResponse.getData().toString()));
            } else {
                logger.error(ORG_ID_IS_NULL_OR_EMPTY + " In saveScoringModelTemp ");
                return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            logger.error(ORG_ID_IS_NULL_OR_EMPTY,e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

        try {

            return scoringClient.saveScoringModelTemp(scoringModelReqRes);
        } catch (Exception e) {
            logger.error("error while saving score model from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringModelReqRes getScoringModelTempDetail(ScoringModelReqRes scoringModelReqRes) {
        try {
            try {
                return scoringClient.getScoringModelTempDetail(scoringModelReqRes);
            } catch (Exception e) {
                logger.error("error while accessing fp product id for scoring : ",e);
                return new ScoringModelReqRes("Error while accessing fp product id for scoring", HttpStatus.BAD_REQUEST.value());
            }

        } catch (Exception e) {
            logger.error("error while getting score model detail from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringModelReqRes saveScoringModelTempDetail(ScoringModelReqRes scoringModelReqRes) {
        try {

            return scoringClient.saveScoringModelTempDetail(scoringModelReqRes);
        } catch (Exception e) {
            logger.error("error while saving score model detail from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public List<GenericCheckerReqRes> sendToChecker(List<GenericCheckerReqRes> genericCheckerReqResList, Long userId) throws ScoringException {
        return scoringClient.sendToChecker(genericCheckerReqResList, userId);
    }



    @Override
    public ScoringModelReqRes getScoringModelMasterList(ScoringModelReqRes scoringModelReqRes) {
        try {
            UserResponse userResponse = usersClient.getOrgIdFromUserId(scoringModelReqRes.getUserId());

            if (!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
                scoringModelReqRes.setOrgId(Long.parseLong(userResponse.getData().toString()));
            } else {
                logger.error(ORG_ID_IS_NULL_OR_EMPTY + " In getScoringModelMasterList ");
                return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            logger.error(ORG_ID_IS_NULL_OR_EMPTY,e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

        try {

            return scoringClient.getScoringModelMasterList(scoringModelReqRes);
        } catch (Exception e) {
            logger.error("error while geting score model list from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

    }

    @Override
    public ScoringModelReqRes getScoringModelMasterDetail(ScoringModelReqRes scoringModelReqRes) {
        try {
            try {
                return scoringClient.getScoringModelMasterDetail(scoringModelReqRes);
            } catch (Exception e) {
                logger.error("error while accessing fp product id for scoring : ",e);
                return new ScoringModelReqRes("Error while accessing fp product id for scoring", HttpStatus.BAD_REQUEST.value());
            }

        } catch (Exception e) {
            logger.error("error while getting score model detail from scoring : ",e);
            return new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }


    public Integer getFinYear(Long applicationId) {
        Integer year = 0;
        ITRConnectionResponse itrConnectionResponse = null;
        try {
            itrConnectionResponse = itrClient.getIsUploadAndYearDetails(applicationId);
        } catch (Exception e) {
            logger.error("error while calling itr client for getIsUploadAndYearDetails()",e);
        }
        try {
            if (itrConnectionResponse != null && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse) && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse.getData())) {
                Map<String, Object> map = (Map<String, Object>) itrConnectionResponse.getData();
                ITRBasicDetailsResponse res = MultipleJSONObjectHelper.getObjectFromMap(map, ITRBasicDetailsResponse.class);
                if (!CommonUtils.isObjectNullOrEmpty(res)) {
                    year = Integer.valueOf(res.getYear());
                }
            }
        } catch (IOException | NullPointerException e) {
            logger.error("error while getting year from itr response : ",e);
        }
        return year + 1;
    }

    @Override
    public List<GenericCheckerReqRes> sendToCheckerMCLR(List<GenericCheckerReqRes> genericCheckerReqResList, Long userId) throws ScoringException {
        return scoringClient.sendToCheckerMCLR(genericCheckerReqResList, userId);
    }

    @Override
    public List<GenericCheckerReqRes> sendToCheckerREPO(List<GenericCheckerReqRes> genericCheckerReqResList, Long userId) throws ScoringException {
        return scoringClient.sendToCheckerREPO(genericCheckerReqResList, userId);
    }

    @Override
    public ScoringResponse createJob(MCLRReqRes mclrReqRes) {
        try {
            return scoringClient.createJob(mclrReqRes);
        } catch (Exception e) {
            logger.error("error while creating job for MCLR: ", e);
            return new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringResponse createJobForREPO(REPOReqRes repoReqRes) {
        try {
            return scoringClient.createJobForRepo(repoReqRes);
        } catch (Exception e) {
            logger.error("error while creating job for MCLR: ", e);
            return new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringResponse getMCLRForChecker(MCLRReqRes mclrReqRes) {
        try {
            return scoringClient.getMCLRForChecker(mclrReqRes);
        } catch (Exception e) {
            logger.error("error while getting MCLR history detail : ", e);
            return new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringResponse getREPOForChecker(REPOReqRes repoReqRes) {
        try {
            return scoringClient.getREPOForChecker(repoReqRes);
        } catch (Exception e) {
            logger.error("error while getting MCLR history detail : ", e);
            return new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringResponse getMCLRHistoryDetail(MCLRReqRes mclrReqRes) {
        try {
            return scoringClient.getMCLRHistory(mclrReqRes);
        } catch (Exception e) {
            logger.error("error while getting MCLR history detail : ", e);
            return new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringResponse getREPOHistoryDetail(REPOReqRes repoReqRes) {
        try {
            return scoringClient.getREPOHistory(repoReqRes);
        } catch (Exception e) {
            logger.error("error while getting MCLR history detail : ", e);
            return new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringResponse getLatestMCLRDetails(MCLRReqRes mclrReqRes) {
        try {
            return scoringClient.getLatestMCLRDetails(mclrReqRes);
        } catch (Exception e) {
            logger.error("error while getting MCLR history detail : ", e);
            return new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringResponse saveMCLRDetails(MCLRReqRes mclrReqRes) {
        try {
            return scoringClient.saveMCLR(mclrReqRes);
        } catch (Exception e) {
            logger.error("error while saving MCLR details : ", e);
            return new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringResponse saveREPODetails(REPOReqRes repoReqRes) {
        try {
            return scoringClient.saveREPO(repoReqRes);
        } catch (Exception e) {
            logger.error("error while saving MCLR details : ", e);
            return new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public ScoringResponse getEffectiveMCLRDetails(MCLRReqRes mclrReqRes) {
        try {
            return scoringClient.getEffectiveMCLR(mclrReqRes);
        } catch (Exception e) {
            logger.error("error while getting effective MCLR details : ", e);
            return new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
        }

    }

    @Override
    public ResponseEntity<LoansResponse> calculateMFILoanScoringList(List<ScoringRequestLoans> scoringRequestLoansList) {

        Long applicationId = null;
        ScoringRequestLoans scoringRequestLoansReq = null;
        List<ScoringRequest> scoringRequestList = new ArrayList<>(scoringRequestLoansList.size());

        MFIApplicantDetail mfiApplicantDetail = null;
        MfiIncomeDetails mfiIncomeDetails=null;
        if (!CommonUtils.isListNullOrEmpty(scoringRequestLoansList)) {
            applicationId = scoringRequestLoansList.get(0).getApplicationId();
            mfiApplicantDetail = mfiApplicationDetailsRepository.findByAppIdAndType(applicationId, 1);
             mfiIncomeDetails=mfiIncomeDetailsRepository.findIncomeDetailsByAppIdAndType(applicationId,1);
        }
        for (ScoringRequestLoans scoringRequestLoans : scoringRequestLoansList) {
            ScoreParameterMFIRequest scoreParameterMFIRequest = null;
            Long scoreModelId = scoringRequestLoans.getScoringModelCoAppId();
            if (scoreModelId == null) {
                scoreModelId = scoringRequestLoans.getScoringModelId();
            }
            logger.info("Scoring model Id For App===>{}", scoreModelId);
            Long fpProductId = scoringRequestLoans.getFpProductId();
            logger.info("Fp Product Id For App===>{}", fpProductId);
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.MFI_LOAN);
            scoringRequest.setEmi(scoringRequestLoans.getEmi());

            if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
            } else {
                scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
            }

            ///////// End  Getting Old Request ///////

            if (CommonUtils.isObjectNullOrEmpty(scoreParameterMFIRequest)) {
                scoreParameterMFIRequest = new ScoreParameterMFIRequest();
                scoreParameterMFIRequest.setMfiMakerRecommendedTenure(mfiApplicantDetail.getTenureRecomandation());
                scoreParameterMFIRequest.setLoanAmountRecommanded(mfiApplicantDetail.getLoanAmountRecomandation());
                scoreParameterMFIRequest.setCibilScore(0.0);
                scoringRequest.setLoanPurposeModelId(scoringRequestLoans.getLoanPurposeModelId());

                logger.info("----------------------------START MFI  ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

                // GET SCORE RETAIL PERSONAL LOAN PARAMETERS
                if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                    // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                    ScoringResponse scoringResponse = null;
                    try {
                        scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                    } catch (Exception e) {
                        logger.error(ERROR_WHILE_GETTING_FIELD_LIST, e);
                    }

                    List<Map<String, Object>> dataList = new ArrayList<>();
                    if (scoringResponse != null && scoringResponse.getDataList() != null) {
                        dataList = (List<Map<String, Object>>) scoringResponse.getDataList();
                    }
                    for (int i = 0; i < dataList.size(); i++) {

                        ModelParameterResponse modelParameterResponse = null;
                        try {
                            modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                    ModelParameterResponse.class);
                            if (modelParameterResponse == null) {
                                continue;
                            }
                        } catch (IOException | NullPointerException e) {
                            logger.error(CommonUtils.EXCEPTION, e);
                            continue;
                        }

                        FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                        fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                        fundSeekerInputRequest.setName(modelParameterResponse.getName());

                        switch (modelParameterResponse.getName()) {
                            case ScoreParameter.MFI.AGE_OF_BORROWER_MFI:
                                try {
                                    if (!CommonUtils.isObjectNullOrEmpty(mfiApplicantDetail.getBirthDate())) {
                                        Integer exactAge[] = CommonUtils.getExactAgeFromDate(mfiApplicantDetail.getBirthDate());
                                        Double age = (((double) exactAge[0]) + ((double) exactAge[1] / 12.0d));
                                        logger.info("Age With Point == {}", age);
                                        scoreParameterMFIRequest.setAgeOfBorrower(age.longValue());
                                        scoreParameterMFIRequest.setAgeOfBorrower_p(true);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting AGE_OF_BORROWER_MFI parameter : ", e);
                                }
                                break;
                            case ScoreParameter.MFI.EXPERIENCE_IN_THE_BUSINESS_WORKING_MFI:
                                try {
                                    if (mfiApplicantDetail.getExpInSameLine() != null) {
                                        scoreParameterMFIRequest.setWorkingExperience_p(true);
                                        scoreParameterMFIRequest.setWorkingExperience(Double.valueOf(mfiApplicantDetail.getExpInSameLine()));
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting EXPERIENCE_IN_THE_BUSINESS_WORKING_MFI parameter : ", e);
                                }
                                break;
                            case ScoreParameter.MFI.ACADEMIC_QUALIFICATION_MFI:
                                try {
                                    scoreParameterMFIRequest.setAcademicQualification_p(mfiApplicantDetail.getEducationQualification() != null);
                                    scoreParameterMFIRequest.setAcademicQualification((mfiApplicantDetail.getEducationQualification() != null ? mfiApplicantDetail.getEducationQualification().longValue() : null));
                                } catch (Exception e) {
                                    logger.error("error while getting MARITAL_STATUS parameter : ", e);
                                }
                                break;
                            case ScoreParameter.MFI.PURPOSE_OF_LOAN_MFI:
                                if (mfiApplicantDetail.getPurposeOfLoan() != null) {
                                    scoreParameterMFIRequest.setLoanPurpose_p(mfiApplicantDetail.getPurposeOfLoan() != null);
                                    scoreParameterMFIRequest.setLoanPurpose(mfiApplicantDetail.getPurposeOfLoan());
                                }
                                break;
                            case ScoreParameter.MFI.DEPENDENTS_IN_THE_FAMILY_MFI:
                                try {
                                    scoreParameterMFIRequest.setDependents(mfiApplicantDetail.getNoDependent());
                                    scoreParameterMFIRequest.setDependents_p(mfiApplicantDetail.getNoDependent() != null);
                                } catch (Exception e) {
                                    logger.error("error while getting DEPENDENTS_IN_THE_FAMILY_MFI parameter : ", e);
                                }
                                break;
                            case ScoreParameter.MFI.OWNERSHIP_OF_HOUSE_MFI:
                                try {
                                    scoreParameterMFIRequest.setOwnershipOfHouse((mfiApplicantDetail.getHouseType() != null ? mfiApplicantDetail.getHouseType().longValue() : null));
                                    scoreParameterMFIRequest.setOwnerShipOfHouse_p(mfiApplicantDetail.getHouseType() != null);
                                } catch (Exception e) {
                                    logger.error("error while getting OWNERSHIP_OF_HOUSE_MFI parameter : ", e);
                                }
                                break;
                            case ScoreParameter.MFI.ANNUAL_INCOME_AS_APPLICABLE_MFI:
                                try {
//                                    AreaTypeMfi areaType = AreaTypeMfi.fromId(mfiApplicantDetail.getAreaType());
                                    Double annualIncome = (mfiIncomeDetails.getMonthlyIncomeChecker() * 12);
                                    AnnualIncomeRural annualIncomeRural = AnnualIncomeRural.getRangeByValue(annualIncome, mfiApplicantDetail.getAreaType());
                                    if (!CommonUtils.isObjectNullOrEmpty(annualIncomeRural)) {
                                        scoreParameterMFIRequest.setAnnualIncome(annualIncomeRural.getId().longValue());
                                        scoreParameterMFIRequest.setAnnualIncome_p(true);
                                    } else {
                                        scoreParameterMFIRequest.setAnnualIncome_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting ANNUAL_INCOME_AS_APPLICABLE_MFI parameter : ", e);
                                    scoreParameterMFIRequest.setAnnualIncome_p(false);
                                }
                                break;
                            default:
                                break;
                        }
                    }

                    logger.info("----------------------------END-------------------------------------------");

                    Gson g = new Gson();
                    ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                    try {
                        scoringRequestDetail.setApplicationId(applicationId);
                        scoringRequestDetail.setRequest(g.toJson(scoreParameterMFIRequest));
                        scoringRequestDetail.setCreatedDate(new Date());
                        scoringRequestDetail.setCoAppId(scoringRequestLoans.getCoApplicantId());
                        scoringRequestDetail.setIsActive(true);
                        scoringRequestDetailRepository.save(scoringRequestDetail);

                        logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                    } catch (Exception e) {
                        logger.error(CommonUtils.EXCEPTION, e);
                    }
                }
            }
            scoringRequest.setScoreParameterMFIRequest(scoreParameterMFIRequest);
            scoringRequestList.add(scoringRequest);
        }

        try {
            ScoringResponse calculateScoreList = scoringClient.calculateScoreList(scoringRequestList);
            logger.info("Scoring Response For MFI Loan for App============>{}", calculateScoreList);
            logger.info("Scoring Response Status For MFI Loan for App============>{}", calculateScoreList != null ? calculateScoreList.getStatus() : calculateScoreList);
            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING, e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

	@Override
	public ResponseEntity<LoansResponse> calculateRetailAutoLoanScoringList(List<ScoringRequestLoans> scoringRequestLoansList) {
        RetailApplicantDetail retailApplicantDetail = null;
        Boolean isItrMannualFilled = false;
        Long applicationId = null;
        Long orgId = null;
        List<Long> coAppIds = null;
        List<Long> coAppITRUploadedIds = null;
        Double netMonthlyIncome = 0.0d;
        Double grossMonthlyIncome = 0.0d;
        PrimaryAutoLoanDetail primaryAutoLoanDetail = null;
        List<Data> bankStatementDatas = null;
        Double totalEMI = 0.0d;
        List<CibilScoreLogRequest> cibilResponse = null;
        List<BankingRelation> bankingRelationList = null;
        List<String> bankStringsList = null;
        List<FinancialArrangementsDetail> financialArrangementsDetailList = null;
        List<Integer> dpds = Collections.emptyList();
       // Boolean isWomenApplicant = false;
        List<Double> incomeOfItrOf3Years = null;

        if(!CommonUtils.isListNullOrEmpty(scoringRequestLoansList)) {
        	applicationId = scoringRequestLoansList.get(0).getApplicationId();
        	retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);
        	if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                return new ResponseEntity<>(new LoansResponse(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_HOME_LOAN_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        	logger.info("retailApplicantDetail.getEmploymentType()=======>{}",retailApplicantDetail.getEmploymentType());
        //	isWomenApplicant = Gender.FEMALE.getId().equals(retailApplicantDetail.getGenderId());
        	primaryAutoLoanDetail = primaryAutoLoanDetailRepository.findById(applicationId);
        	if (CommonUtils.isObjectNullOrEmpty(primaryAutoLoanDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                return new ResponseEntity<>(new LoansResponse("Primary Auto Loan Detail Must Not be null While Calculating Auto Loan Scoring", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }

        	EligibilityResponse eligibilityResponse = null;
			try {
				EligibililityRequest eligibililityRequest = new EligibililityRequest();
				eligibililityRequest.setApplicationId(applicationId);
				eligibililityRequest.setIsIncomeCalculate(false);
				eligibilityResponse = eligibilityClient.getMonthlyIncome(eligibililityRequest);
				if(eligibilityResponse == null) {
						return new ResponseEntity<>(new LoansResponse("Eligibility Response Found NULL : ", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			} catch (EligibilityExceptions e) {
				logger.error("Error while Getting MonthlyIncome Details == >{}",e);
				return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong while getting Calculated NMI and GMI for Scoring : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
			}
            if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse)
                    && !com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse.getData())){
                List incomeList = (List) eligibilityResponse.getData();
                if(!com.capitaworld.service.matchengine.utils.CommonUtils.isListNullOrEmpty(incomeList)){
                    netMonthlyIncome = Double.valueOf(incomeList.get(0).toString());
                    grossMonthlyIncome = Double.valueOf(incomeList.get(8).toString());
                }

                if(netMonthlyIncome <= 0 || grossMonthlyIncome <= 0) {
                	return new ResponseEntity<>(new LoansResponse("NMI or GMI is Zero ", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
                }
                logger.info("Net Monthly Income For ApplicationId======{}======>{}",applicationId,netMonthlyIncome);
                logger.info("Gross Annual Income For ApplicationId======{}======>{}",applicationId,grossMonthlyIncome);
            }
            try {
                 ReportRequest reportRequest = new ReportRequest();
                 reportRequest.setApplicationId(applicationId);
                 AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
                 if(analyzerResponse == null) {
                	 return new ResponseEntity<>(new LoansResponse("Analyser Response Found null For Scoring Calculation AL For the ApplicationId===>" + applicationId, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
                 }
                 bankStatementDatas = new ArrayList<>(5);
                 for(Object object : (List)analyzerResponse.getData()) {
                	 Data dataBs = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) object, Data.class);
 					if(dataBs != null) {
 						if(!EligibilityUtils.isObjectNullOrEmpty(dataBs.getCoAppId())) {
 							continue;
 						}
 						bankStatementDatas.add(dataBs);
 					}
                 }
            }catch(Exception e) {
            	logger.error("Error while getting Bank Statement Details===>{}",e);
            	return new ResponseEntity<>(new LoansResponse("Error while Getting Bank Statemtnt Report for ApplicationID====>" + applicationId + " and Message====>" + e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }

            totalEMI = financialArrangementDetailsService.getTotalEmiByApplicationIdSoftPing(applicationId);

            try {
            	cibilResponse = cibilClient.getSoftpingScores(applicationId, retailApplicantDetail.getPan());
            	if(cibilResponse == null) {
            		return new ResponseEntity<>(new LoansResponse("CIBIL Score Reponse Found NULL for ApplicationID====>" + applicationId, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            	}

            	CibilResponse cibilResponseDpd = cibilClient.getDPDLastXMonth(applicationId,retailApplicantDetail.getPan());
                if(cibilResponseDpd == null) {
            		return new ResponseEntity<>(new LoansResponse("CIBIL DPD Reponse Found NULL for ApplicationID====>" + applicationId, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            	}
                if(!CommonUtils.isObjectNullOrEmpty(cibilResponseDpd) && !CommonUtils.isObjectListNull(cibilResponseDpd.getListData())){
    				List cibilResponseList = cibilResponseDpd.getListData();
    				dpds = new ArrayList<>(cibilResponseList.size());
    				for (int i = 0; i < cibilResponseList.size(); i++) {
    					String cibilResponseObj = cibilResponseList.get(i).toString();
    					if(cibilResponseObj.contains("|")){
    						String[] cibilDpdVal = cibilResponseObj.split("\\|");
    						if(!CommonUtils.isObjectNullOrEmpty(cibilDpdVal[1])) {
    							dpds.add(Integer.parseInt(cibilDpdVal[1]));
    						}
    					}else {
    						dpds.add(Integer.parseInt(cibilResponseObj));
    					}
    				}
    			}

            }catch(Exception e) {
            	return new ResponseEntity<>(new LoansResponse("Error while Getting DPD or CIBIL Score for ApplicationID====>" + applicationId + " and Message====>" + e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);

            }

            //Getting is Itr Mannual Filed
            isItrMannualFilled = loanRepository.isITRUploaded(applicationId);

            //Checking Flags of Bank Account Related
            bankingRelationList = bankingRelationlRepository.listBankRelationAppId(applicationId);

            //Getting Banks List
            ReportRequest reportRequest = new ReportRequest();
            reportRequest.setApplicationId(applicationId);
            AnalyzerResponse analyzerResponse = null;
			try {
				analyzerResponse = analyzerClient.getSalaryDetailsFromReport(reportRequest);
				bankStringsList = (List<String> )analyzerResponse.getData();
			} catch (AnalyzerException e) {
				logger.error("Error while Getting bankList from Analyzer ===> {}",e);
				return new ResponseEntity<>(new LoansResponse("Error while Getting BankList From Analyser for ApplicationID====>" + applicationId + " and Message====>" + e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
			}

			//Getting All Loans
			financialArrangementsDetailList = financialArrangementDetailsRepository.listSecurityCorporateDetailByAppId(applicationId);
			incomeOfItrOf3Years = loanRepository.getIncomeOfItrOf3Years(applicationId);
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationId);
        	if(!CommonUtils.isListNullOrEmpty(coAppIds)) {
        		coAppITRUploadedIds = coApplicantDetailRepository.getCoAppIdsOfCoApplicantUploadedITR(applicationId,true);
        	}
        }        	
        	
         // IF LOAN IS ALREADY EXIST WITH SBI THEN CHECK DPDs ALL OUTSTANDING LOAN            
        /*if(count > 0) {
        	List<CreditCardsDetail> dpdDetailsList = creditCardsDetailRepository.listCreditCardsFromAppId(applicationId);
        	List<CreditCardsDetail> dpdList = new ArrayList<>(dpdDetailsList.size());
        	
        	// for(CreditCardsDetail response : dpdDetailsList) {
        	for (int i = 0; i < dpdDetailsList.size(); i++) {
        			String dpdResponse =dpdDetailsList.get(i).getDpdDetails() ;	
        				logger.info("dpdResponse Here ======{}====={}===>>>"+dpdResponse);
        				if(dpdResponse.contains("|")){
     						String[] DpdValResp = dpdResponse.split("\\|");
     						if(!CommonUtils.isObjectNullOrEmpty(DpdValResp[1])) {
     								dpds.add(Integer.parseInt(DpdValResp[1]));
     				}
        		}
        	}
        	
        } 	*/
        // Step 2 :--- Check whether Borrower is uploading SBI Bank Statement With Discuss Balu Bhai
        			//	(PENDING)
        	
        				
        
        List<ScoringRequest> scoringRequestList=new ArrayList<>(scoringRequestLoansList.size());
        ScoreParameterRetailRequest scoreParameterRetailRequest = null;
        for(ScoringRequestLoans scoringRequestLoans : scoringRequestLoansList)
        {
            Long scoreModelId = scoringRequestLoans.getScoringModelId();
            Long fpProductId = scoringRequestLoans.getFpProductId();
            Integer personalBankingId = null;
//            homeLoanModelRequest = homeLoanModelService.get(scoringRequestLoans.getLoanPurposeModelId(), null, null);
            Integer minBankRelationshipInMonths = null;
            orgId = scoringRequestLoans.getOrgId();
            if(orgId != null) {
            	BankList bankEnum = BankList.fromOrgId(orgId.toString());
            	try {
            		if(bankEnum != null) {
                		logger.info("Bank Name====>{}==>Application Id===>{}===> Fp Product Id===>{}",bankEnum.getName(),applicationId,fpProductId);
                		minBankRelationshipInMonths = bankingRelationlRepository.getMinRelationshipInMonthByApplicationAndOrgName(applicationId, bankEnum.getName());
                	}            		
            	}catch(Exception e) {
            		logger.error("Error while Getting Minimum Banking Relationship = >{}",e);
            	}
            	// Step 1 :- (First scenario CHECK Borrower has any outstanding Loan with DPDs with SBI===============>)
            	try {
            		if(bankEnum != null) {
        				List<Long> ids = financialArrangementDetailsRepository.checkExistingLoanWithBank(applicationId,(bankEnum.getName() != null ? bankEnum.getName().toLowerCase() : null));
        				if(!CommonUtils.isListNullOrEmpty(ids)) {
        					Long cnt = financialArrangementDetailsRepository.checkDpdsWithBankByIds(ids);
        					if(cnt <= 0) {
        						personalBankingId = 2; //Credit Relation With satisfactory Performance(Standard In Our Books For the 12 Months) 
        					}
        				}
                	}       		
            	}catch(Exception e) {
            		logger.error("Error while Getting Personal Banking Relationship = >{}",e);
            	}
            	
            	
            	// Step 2 :- Corporate Salary Package(CSP Customer)
            	if(personalBankingId == null) {
            		for(Data bankStatementData : bankStatementDatas) {
            			if(bankStatementData != null && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo()) && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getAccType())) {
            				String code = bankStatementData.getSummaryInfo().getAccType().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            				logger.info("After Replaced = >{}",code);
            				Long codeExists = cspCodeRepository.isCodeExists(code, orgId);
            					if(codeExists > 0) {
            						logger.info("CSP Code Found For OrgId==>{}==>{}",orgId,code);
            						personalBankingId = 3; //Corporate Salary Package(CSP Customer)
            						break;
            					}else {
            						logger.info("CSP Code not Found For OrgId==>{}",orgId);
            					}
            					
            			 }
            		}                		
            	}
            	
            	
            	
              // Step :3 (Deposit (SB/CA/TDR) relationship for at least 6 months)
            	if(personalBankingId == null) {
            		Double depositeSBCATDRAmount = 0.0d;
                	if(bankEnum != null) {
                		for(Data bankStatementData : bankStatementDatas) {
                			if(bankStatementData != null && bankStatementData.getSummaryInfo() != null && bankStatementData.getSummaryInfo().getSummaryInfoTotalDetails() != null && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoTotalDetails().getTotalCredit()) && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getCustomerInfo()) && bankEnum.getName().equalsIgnoreCase(bankStatementData.getEnumBank())) {
                				depositeSBCATDRAmount = depositeSBCATDRAmount + Double.valueOf(bankStatementData.getSummaryInfo().getSummaryInfoTotalDetails().getTotalCredit()); 
                						logger.info("Total Deposite Amount here ====={}====={}==>",depositeSBCATDRAmount,applicationId);
                			 	}
                		}                		
                	}
                	if(depositeSBCATDRAmount > 0.0d) {
                		personalBankingId = 4; //Deposite(SB/CA/TDR) Relationship For at Least 6 months
                	}
            	}
            	
            	if(personalBankingId == null) {
            		personalBankingId = 5; //New Customer
            	}
            	
            	logger.info("Min Banking Relationship in Month === >{}",minBankRelationshipInMonths);
            	
            	// CHECK CUSTOMER IS ALREADY OUTSTANDING LOAN WITH SBI BANK
            //	bankingRelationlRepository. CHECK CUSTOMER IS (UPLOADED STATEMENT WITH SBI BANK) 
            }
            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.RETAIL_AUTO_LOAN);
            scoringRequest.setEmi(scoringRequestLoans.getEmi());

            if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
            } else {
                scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
            }

            // STARTS HERE CONCESSION BASED ON RATE OF INTEREST:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            /*
            ScoringRequestLoans requestLoans = new ScoringRequestLoans();
            requestLoans.setApplicationId(applicationId);
            requestLoans.setFpProductId(fpProductId);*/
            Integer consessionBureauVersion = null;
            Object[] bureauVersionIdById = loanRepository.getBureauVersionIdById(scoreModelId);
            if(!CommonUtils.isObjectNullOrEmpty(bureauVersionIdById)) {
           	 if(!CommonUtils.isObjectNullOrEmpty(bureauVersionIdById[0])) {
           		 consessionBureauVersion = Integer.valueOf(bureauVersionIdById[0].toString());            		 
           	 }else {
           		 consessionBureauVersion = 1;
           	 }
           	 scoringRequestLoans.setBureauVersion(consessionBureauVersion);
            }
            Object [] concessionResp = getRetailConcessionDetails(scoringRequestLoans, bankStringsList, bankingRelationList, financialArrangementsDetailList,retailApplicantDetail, cibilResponse);
            logger.info("==========getRetailConcessionDetails========>>>>>"+concessionResp);

           Boolean  isBorrowersHavingAccounts	  =	(Boolean)concessionResp[0];
           Boolean  isBorrowersAvailingLoans          =	(Boolean)concessionResp[1];
           Boolean  isBorrowersHavingSalaryAccounts   = (Boolean)concessionResp[2];
           Boolean  isBorrowersAvailingCreaditCards   = (Boolean)concessionResp[3];

           // is Fully Check Off And Partially Check Off
           Boolean isCheckOffDirectPayEmi             =	(Boolean)concessionResp[4];
           Boolean  isCheckOffAgreetoPayOutstanding   = (Boolean)concessionResp[5];
           Boolean  isCheckOffShiftSalAcc             =	(Boolean)concessionResp[6];
           Boolean  isCheckOffPayOutstndAmount        =	(Boolean)concessionResp[7];
           Boolean isCheckOffNotChangeSalAcc          =	(Boolean)concessionResp[8];

           // Cibil BAsed Concession
           Double cibilActualScore                   =	(Double)concessionResp[9];
           Boolean isCreaditHisotryGreaterSixMonths   =	(Boolean)concessionResp[10];
           Boolean isCreaditHisotryLessThenSixMonths = (Boolean)concessionResp[11];
           Boolean isNoCreaditHistory                =	(Boolean)concessionResp[12];
           Boolean isWomenApplicant                =	(Boolean)concessionResp[13];


          // partially and fully check off related----->
           scoringRequest.setIsBorrowersHavingAccounts(isBorrowersHavingAccounts);
          scoringRequest.setIsBorrowersAvailingLoans(isBorrowersAvailingLoans);
          scoringRequest.setIsBorrowersHavingSalaryAccounts(isBorrowersHavingSalaryAccounts);
          scoringRequest.setIsBorrowersAvailingCreaditCards(isBorrowersAvailingCreaditCards);

          scoringRequest.setIsCheckOffDirectPayEmi(isCheckOffDirectPayEmi);
          scoringRequest.setIsCheckOffAgreetoPayOutstanding(isCheckOffAgreetoPayOutstanding);
          scoringRequest.setIsCheckOffShiftSalAcc(isCheckOffShiftSalAcc);
          scoringRequest.setIsCheckOffPayOutstndAmount(isCheckOffPayOutstndAmount);
          scoringRequest.setIsCheckOffNotChangeSalAcc(isCheckOffNotChangeSalAcc);
     	// ENDS HERE CHECK OFF LOGIC HERE

		//  Cibil Based Object related----->
          scoringRequest.setCibilActualScore(cibilActualScore);
          scoringRequest.setIsCreaditHisotryGreaterSixMonths(isCreaditHisotryGreaterSixMonths);
          scoringRequest.setIsCreaditHisotryLessThenSixMonths(isCreaditHisotryLessThenSixMonths);
          scoringRequest.setIsNoCreaditHistory(isNoCreaditHistory);

          scoringRequest.setIsWomenApplicant(isWomenApplicant); // Women For HL

          // ENDS HERE CONCESSION BASED ON RATE OF INTEREST:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
            ///////// End  Getting Old Request ///////
                scoreParameterRetailRequest =  new ScoreParameterRetailRequest();
                scoreParameterRetailRequest.setPersonalBankingRelationShip(personalBankingId);
                scoreParameterRetailRequest.setExShowRoomPrice(primaryAutoLoanDetail.getVehicleExShowRoomPrice() != null ? primaryAutoLoanDetail.getVehicleExShowRoomPrice().doubleValue() : 0.0d);
                scoreParameterRetailRequest.setOnRoadPrice(primaryAutoLoanDetail.getVehicleOnRoadPrice() != null ? primaryAutoLoanDetail.getVehicleOnRoadPrice().doubleValue() : 0.0d);
                scoreParameterRetailRequest.setAgreedIDV(primaryAutoLoanDetail.getVehicleAgreedPurchasePrice() != null ? primaryAutoLoanDetail.getVehicleAgreedPurchasePrice().doubleValue() : 0.0d);
                if(AutoLoanPurposeType.NEW_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose()) || AutoLoanPurposeType.NEW_TWO_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
                	scoreParameterRetailRequest.setVechileType(VehicleType.NEW.getId());
                	scoreParameterRetailRequest.setVechileAge(-1.0d);
                }else if(AutoLoanPurposeType.SECOND_HAND_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose()) || AutoLoanPurposeType.SECOND_HAND_TWO_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
                	scoreParameterRetailRequest.setVechileType(VehicleType.SECOND_HAND.getId());
                	scoreParameterRetailRequest.setVechileAge(primaryAutoLoanDetail.getVehicleAge() != null ? primaryAutoLoanDetail.getVehicleAge().doubleValue() : null);
                }
                logger.info("scoringRequestLoans.getFoir()=>{}==For ApplicationId====>{}==>For FpProductId===>{}",scoringRequestLoans.getFoir(),applicationId,fpProductId);
                scoreParameterRetailRequest.setFoir(scoringRequestLoans.getFoir());
                scoringRequest.setLoanPurposeModelId(scoringRequestLoans.getLoanPurposeModelId());
                logger.info("----------------------------START RETAIL HL ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

                // GET SCORE RETAIL PERSONAL LOAN PARAMETERS


                if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                    // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                    ScoringResponse scoringResponse = null;
                    try {
                        scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                    } catch (Exception e) {
                        logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                    }

                    List<Map<String, Object>> dataList = new ArrayList<>();
                    if (scoringResponse != null && scoringResponse.getDataList() != null) {
                        dataList = (List<Map<String, Object>>) scoringResponse.getDataList();
                    }

                    for (int i = 0; i < dataList.size(); i++) {

                        ModelParameterResponse modelParameterResponse = null;
                        try {
                            modelParameterResponse = MultipleJSONObjectHelper.getObjectFromMap(dataList.get(i),
                                    ModelParameterResponse.class);
                            if(modelParameterResponse == null){
                                continue;
                            }
                        } catch (IOException | NullPointerException e) {
                            logger.error(CommonUtils.EXCEPTION,e);
                            continue;
                        }

                        FundSeekerInputRequest fundSeekerInputRequest = new FundSeekerInputRequest();
                        fundSeekerInputRequest.setFieldId(modelParameterResponse.getFieldMasterId());
                        fundSeekerInputRequest.setName(modelParameterResponse.getName());


//                        scoreParameterRetailRequest.setLoanAmtProposed(scoringRequestLoans.getElAmountOnAverageScoring());
                        scoreParameterRetailRequest.setNmi(netMonthlyIncome);
						scoreParameterRetailRequest.setGmi(grossMonthlyIncome);
						scoreParameterRetailRequest.setEmi(scoringRequestLoans.getEmi());
						scoreParameterRetailRequest.setElAmountOnAverageScoring(scoringRequestLoans.getElAmountOnAverageScoring());
                        switch (modelParameterResponse.getName()) {
                        case ScoreParameter.Retail.AutoLoan.AGE:
                        	   try {
                                   if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
                                	   Integer exactAge [] = CommonUtils.getExactAgeFromDate(retailApplicantDetail.getBirthDate());
                                	   logger.info("Age With years and Month == {}==={}", exactAge[0],exactAge[1]);
                                	   Double age = (((double) exactAge[0]) + ((double)exactAge[1] / 12.0d));
                                	   logger.info("Age With Point == {}",age);
                                       scoreParameterRetailRequest.setAge(age);
                                       scoreParameterRetailRequest.setAge_p(true);
                                   }
                               } catch (Exception e) {
                                   logger.error("error while getting AGE_HL parameter : ",e);
                               }
                        	break;
            			case ScoreParameter.Retail.AutoLoan.TOTAL_WORK_EXP:
            				try {
            					Double totalExperience = 0.0;
            					if(retailApplicantDetail.getEmploymentType() != null) {
            						if(!OccupationNatureNTB.SALARIED.getId().equals(retailApplicantDetail.getEmploymentType())){
            							if(retailApplicantDetail.getBusinessStartDate() != null) {
                        					logger.info("retailApplicantDetail.getBusinessStartDate() For HL====ApplicationId===>{}=====>{}",retailApplicantDetail.getBusinessStartDate(),applicationId);
                        					Integer[] busiFromDate = CommonUtils.getExactAgeFromDate(retailApplicantDetail.getBusinessStartDate());
                        					logger.info("Year For AL====ApplicationId===>{}=====>{}",busiFromDate[0],applicationId);
                        					logger.info("Month For AL====ApplicationId===>{}=====>{}",busiFromDate[1],applicationId);
                        					totalExperience = (((double) busiFromDate[0]) + ((double)busiFromDate[1] / 12.0d));
                        					logger.info("Total Business Experiance For AL==== > {}",totalExperience);
                                            scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                            scoreParameterRetailRequest.setWorkingExperience_p(true);
                        				}
                					}else {
                						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceYear())) {
                                        	totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceYear());
                                        	logger.info("totalExperience Year {}===>{}",retailApplicantDetail.getTotalExperienceYear());
                                        }
                                        if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceMonth())) {
                                        	totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceMonth()) / 12.0d;
                                        	logger.info("totalExperience Month {}===>{}",retailApplicantDetail.getTotalExperienceMonth());
                                        }
                                        logger.info("totalExperience {}===>{}",totalExperience);
                                        scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                        scoreParameterRetailRequest.setWorkingExperience_p(true);
                					}
            					}
                            } catch (Exception e) {
                                logger.error("error while getting TOTAL_JOB_EXP parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.CURRENT_JOB_EXP:
            				try {
            					if(OccupationNatureNTB.SALARIED.getId().equals(retailApplicantDetail.getEmploymentType())){
            						Double currentExperience = 0.0;
                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getCurrentJobYear())){
                                   	 currentExperience += Double.valueOf(retailApplicantDetail.getCurrentJobYear());
                                   	 logger.info("CURRENT_JOB_EXP Year {}===>{}",retailApplicantDetail.getCurrentJobYear());
                                    }

                                    if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getCurrentJobMonth())) {
                                   	 currentExperience += (retailApplicantDetail.getCurrentJobMonth() / 12);
                                   	 logger.info("CURRENT_JOB_EXP Month {}===>{}",retailApplicantDetail.getCurrentJobMonth());
                                    }
                                    scoreParameterRetailRequest.setWorkingExperienceCurrent(currentExperience);
                                    scoreParameterRetailRequest.setIsWorkingExperienceCurrent_p(true);
            					}
                         } catch (Exception e) {
                             logger.error("error while getting CURRENT_JOB_EXP parameter : {}",e);
                         }
            				break;
            			case ScoreParameter.Retail.AutoLoan.RESIDENCE_TYPE:
            				if(retailApplicantDetail.getResidenceType() != null) {
            					if(ResidenceStatusRetailMst.OWNED.getId().equals(retailApplicantDetail.getResidenceType())) {
            						if(retailApplicantDetail.getIsOwnedProp() != null && retailApplicantDetail.getIsOwnedProp()) {
            							scoreParameterRetailRequest.setResidenceType(8); //Owned (Encumbered) : No Need to Add in ENUM. This is Only For Scoring
            						}else {
            								scoreParameterRetailRequest.setResidenceType(ResidenceStatusRetailMst.OWNED.getId());
            						}
            					}else{
            							scoreParameterRetailRequest.setResidenceType(retailApplicantDetail.getResidenceType());
            					}
            					scoreParameterRetailRequest.setIsResidenceType_p(true);
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.NO_YEARS_STAY_CURR_LOC:
            				try {
            					if(retailApplicantDetail.getResidenceSinceYear() != null && retailApplicantDetail.getResidenceSinceMonth() != null) {
            						Integer year = retailApplicantDetail.getResidenceSinceYear();
    	                            Integer month = retailApplicantDetail.getResidenceSinceMonth();
    	                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	                            String s = null;
    	                            if(month < 10) {
    	                            	s = "01/0" + month + "/" + year;
    	                            }else {
    	                            	s = "01/" + month + "/" + year;
    	                            }
    	                            logger.info("Starting Date of Staying in Current Location For HL==== > {}",s);
    	                            Integer[] exactAgeFromDate = CommonUtils.getExactAgeFromDate(simpleDateFormat.parse(s));
    	                            Double noStayLoc = (((double) exactAgeFromDate[0]) + ((double)exactAgeFromDate[1] / 12.0d));
    	                            logger.info("No Of Years Staying in Current Location For HL==== > {}",noStayLoc);
    	                            scoreParameterRetailRequest.setNoOfYearCurrentLocation(noStayLoc);
    	                            scoreParameterRetailRequest.setIsNoOfYearCurrentLocation_p(true);
            					}
            				} catch (Exception e) {
	                            logger.error("error while getting NO_YEARS_STAY_CURR_LOC parameter : ", e);
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.BUREAU_SCORE:
            				scoreParameterRetailRequest.setCibilActualScore(filterBureauScoreByVersion(1, cibilResponse));
            				scoreParameterRetailRequest.setCibilActualScoreVersion2(filterBureauScoreByVersion(2, cibilResponse));
            				scoreParameterRetailRequest.setCibilScore_p(true);
            				break;
            			case ScoreParameter.Retail.AutoLoan.MARITAL_STATUS:
            				try {
                                scoreParameterRetailRequest.setMaritalStatus((retailApplicantDetail.getStatusId() != null ? retailApplicantDetail.getStatusId().longValue() : null));
                                scoreParameterRetailRequest.setMaritalStatus_p(retailApplicantDetail.getStatusId() != null);
                            } catch (Exception e) {
                                logger.error("error while getting MARITAL_STATUS parameter : ",e);
                            }
            				break;

            			case ScoreParameter.Retail.AutoLoan.EMPLOYMENT_TYPE:
            				try {
            					scoreParameterRetailRequest.setEmployementType_p(retailApplicantDetail.getEmploymentType() != null);
                				scoreParameterRetailRequest.setEmploymentType((retailApplicantDetail.getEmploymentType() != null  ? retailApplicantDetail.getEmploymentType().longValue() : null));
                            } catch (Exception e) {
                                logger.error("error while getting MARITAL_STATUS parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.EMPLOYMENT_CATEG_JOB:
            				if(retailApplicantDetail.getEmploymentType() != null && OccupationNatureNTB.SALARIED.getId().equals(retailApplicantDetail.getEmploymentType())) {
            					scoreParameterRetailRequest.setIsEmployementJobCat_p(retailApplicantDetail.getEmploymentWith() != null);
                				scoreParameterRetailRequest.setEmploymentTypeCatJob(retailApplicantDetail.getEmploymentWith());
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED:
            				if(retailApplicantDetail.getEmploymentType() != null && !OccupationNatureNTB.SALARIED.getId().equals(retailApplicantDetail.getEmploymentType())) {
            					if(OccupationNatureNTB.AGRICULTURIST.getId().equals(retailApplicantDetail.getEmploymentType())
            							|| OccupationNatureNTB.PENSIONER.getId().equals(retailApplicantDetail.getEmploymentType())
            							|| OccupationNatureNTB.OTHERS.getId().equals(retailApplicantDetail.getEmploymentType())) {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(true);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus(OccupationHL.AGRICULTURIST_PENSIONER_OTHERS.getId().longValue());
            					}else if(OccupationNatureNTB.SELF_EMPLOYED_NON_PROFESSIONAL.getId().equals(retailApplicantDetail.getEmploymentType())) {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(true);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus(OccupationHL.BUSINESSMAN.getId().longValue());
            					}else {
            						scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(retailApplicantDetail.getEmploymentWith() != null);
                    		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus((retailApplicantDetail.getEmploymentWith() != null  ? retailApplicantDetail.getEmploymentWith().longValue() : null));
            					}
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.CURRENT_EMPLOYMENT_STATUS:
            				scoreParameterRetailRequest.setIsCurrentEmploymentStatus_p(retailApplicantDetail.getEmploymentStatus() != null);
            				scoreParameterRetailRequest.setCurrentEmploymentStatus((retailApplicantDetail.getEmploymentStatus() != null  ? retailApplicantDetail.getEmploymentStatus().longValue() : null));
            				break;
            			case ScoreParameter.Retail.AutoLoan.MIN_BANKING_RELATIONSHIP:
            				scoreParameterRetailRequest.setIsMinBankingRelationship_p(true);
            				scoreParameterRetailRequest.setMinBankingRelationship(minBankRelationshipInMonths == null ? 0 : minBankRelationshipInMonths);
            				break;
            			case ScoreParameter.Retail.AutoLoan.SPOUSE_EMPLOYEMENT:
            				try {
            					if(retailApplicantDetail.getSpouseEmployment() != null) {
            						scoreParameterRetailRequest.setSpouseEmploymentDetails(retailApplicantDetail.getSpouseEmployment().longValue());
            					}else {
            						scoreParameterRetailRequest.setSpouseEmploymentDetails(3l);
            					}
            					scoreParameterRetailRequest.setSpouseEmploymentDetails_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting SPOUSE_EMPLOYEMENT parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.NO_OF_DEPENDANTS:
            				try {
                                scoreParameterRetailRequest.setNumberOfDependents(retailApplicantDetail.getNoOfDependent());
                                scoreParameterRetailRequest.setNumberOfDependents_p(retailApplicantDetail.getNoOfDependent() != null);
                            } catch (Exception e) {
                                logger.error("error while getting NO_OF_DEPENDANTS parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.DESIGNATION:
            				try {
                                scoreParameterRetailRequest.setDesignation((retailApplicantDetail.getDesignation() != null ? retailApplicantDetail.getDesignation().longValue() : null));
                                scoreParameterRetailRequest.setDesignation_p(retailApplicantDetail.getDesignation() != null);
                            } catch (Exception e) {
                                logger.error("error while getting DESIGNATION parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.EDUCATION_QUALIFICATION:
            				try {
                                scoreParameterRetailRequest.setEducationQualification((retailApplicantDetail.getEducationQualification() != null ? retailApplicantDetail.getEducationQualification().longValue() : null));
                                scoreParameterRetailRequest.setEducationQualifaction_p(retailApplicantDetail.getEducationQualification() != null);
                            } catch (Exception e) {
                                logger.error("error while getting EDUCATION_QUALIFICATION parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.NO_OF_APPLICANTS:
            				if(CommonUtils.isListNullOrEmpty(coAppIds)) {
            					scoreParameterRetailRequest.setNoOfApplicantsType(ScoreParameter.NoOfApplicants.SINGLE);
            				}else if(CommonUtils.isListNullOrEmpty(coAppITRUploadedIds)) {
            					scoreParameterRetailRequest.setNoOfApplicantsType(ScoreParameter.NoOfApplicants.JOINT);
            				}else if(!CommonUtils.isListNullOrEmpty(coAppITRUploadedIds)) {
            					scoreParameterRetailRequest.setNoOfApplicantsType(ScoreParameter.NoOfApplicants.JOINT_WHERE_CO_APPLICANT_IS_EARNING);
            				}
            				scoreParameterRetailRequest.setIsNoOfApplicantsType_p(true);
            				break;
            			case ScoreParameter.Retail.AutoLoan.ANNUAL_INCOME:
            				//Already Set NMI and GMI and EMI Above Before Switch Starts
            				break;
            			case ScoreParameter.Retail.AutoLoan.EMI_NMI_RATIO:
            				//Already Set NMI and GMI and EMI Above Before Switch Starts
            				scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
            				break;
            			case ScoreParameter.Retail.AutoLoan.EMI_NMI:
            				scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
            				//Already Set NMI and GMI Above Before Switch Starts
            				break;
            			case ScoreParameter.Retail.AutoLoan.AVAILABLE_INCOME:
            				try {
            					logger.info("netMonthlyIncome===>{}===grossAnnualIncome===>{}== For ApplicationId ==>{}===>FpProductId===>{}",netMonthlyIncome,grossMonthlyIncome,applicationId,fpProductId);
								scoreParameterRetailRequest.setFoir(scoringRequestLoans.getFoir());
								scoreParameterRetailRequest.setIsAvailableIncome_p(true);

							} catch (Exception e1) {
								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
							}
            				break;
            			case ScoreParameter.Retail.AutoLoan.TENURE:
            				if(scoringRequestLoans.getEligibleTenure() != null) {
								scoreParameterRetailRequest.setEligibleTenure(scoringRequestLoans.getEligibleTenure());
								scoreParameterRetailRequest.setIsEligibleTenure_p(true);
							}else {
								logger.warn("Eligible Tenure is not Set in AVAILABLE_INCOME TENURE==== > {}",scoringRequestLoans.getEligibleTenure());
							}
            				break;
            			case ScoreParameter.Retail.AutoLoan.ADDI_INCOME_SPOUSE:
	            				if(retailApplicantDetail.getAnnualIncomeOfSpouse() != null) {
	            					scoreParameterRetailRequest.setSpouseIncome(retailApplicantDetail.getAnnualIncomeOfSpouse());
	            					scoreParameterRetailRequest.setIsSpouseIncome_p(true);
	            				}else {
	            					scoreParameterRetailRequest.setSpouseIncome(0.0d);
	            					scoreParameterRetailRequest.setIsSpouseIncome_p(true);
	            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.MON_INCOME_DEPENDANT:
            				scoreParameterRetailRequest.setNoOfDependants(retailApplicantDetail.getNoOfDependent());
            				scoreParameterRetailRequest.setIsMonIncomePerDep_p(true);
            				break;
            			case ScoreParameter.Retail.AutoLoan.AVG_INCREASE_INCOME_REPORT_3_YEARS:
            				logger.info("Income List From ITR for AL == >{}==>ApplicationId==>{}",incomeOfItrOf3Years,applicationId);
            				if(!CommonUtils.isListNullOrEmpty(incomeOfItrOf3Years)) {
            					if(incomeOfItrOf3Years.size() == 3) { //as if now considering 3 Years Compulsory
            						Double itrLastToLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 1);
            						if(itrLastToLastToLastYearIncome == null ) {
            							itrLastToLastToLastYearIncome = 1.0d;
            						}
                					Double itrLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 2);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 1.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 3);

                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 0.0;
                					}
            						Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
            						logger.info("Final Income After Calculation for AL == >{}==>ApplicationId==>{}",finalIncome,applicationId);
            						if(Double.isFinite(finalIncome)) {
            							scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
                						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            						}
            					}else if(incomeOfItrOf3Years.size() == 2) { //as if now considering 2 Years Compulsory
                					Double itrLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 1);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 1.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 2);
                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 1.0;
                					}
            						Double finalIncome =  (((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100);
            						logger.info("Final Income After Calculation for AL == >{}==>ApplicationId==>{}",finalIncome,applicationId);
            						if(Double.isFinite(finalIncome)) {
            							scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
                						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            						}

            					}else if(incomeOfItrOf3Years.size() == 1) { //as if now considering 1 Years Compulsory
            						logger.info("Final Income After Calculation for AL as Only one year ITR Found == >{}==>ApplicationId==>{}",0.0d,applicationId);
            						scoreParameterRetailRequest.setIncomeFromItr(0.0d);
            						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            					}
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.REPAYMENT_PERIOD:
            				scoreParameterRetailRequest.setRepaymentPeriod(retailApplicantDetail.getRepaymentMode());
        					scoreParameterRetailRequest.setIsRepaymentPeriod_p(retailApplicantDetail.getRepaymentMode() != null);
            				break;
            			case ScoreParameter.Retail.AutoLoan.AGE_OF_VEHICLE:
            					//Value is already set above
            					scoreParameterRetailRequest.setIsVehicleAge_p(true);
	        				break;
            			case ScoreParameter.Retail.AutoLoan.AVG_DEPOS_LAST_6_MONTH:
            				Double value = 0.0d;
            				for(Data bankStatementData : bankStatementDatas) {
            					if(bankStatementData != null && bankStatementData.getSummaryInfo() != null && bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails() != null  && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit())) {
                					value = value + Double.valueOf(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit()); // / 6
                					logger.info("AVG_DEPOS_LAST_6_MONTH value===>{}",value);
           					 	}
            				}
            				scoreParameterRetailRequest.setAvgOfTotalCheDepsitLast6Month(value);
   					 		scoreParameterRetailRequest.setIsAvgOfTotalCheDepsitLast6Month_p(true);
            				break;
            			case ScoreParameter.Retail.AutoLoan.CHECQUE_BOUNSE_LAST_1_MONTH:
            				 try {
            					 Double chequeBounselast1Month = 0.0d;
            					 for(Data bankStatementData : bankStatementDatas) {
            						 if(bankStatementData != null && bankStatementData.getCheckBounceForLast1Month() != null) {
                						 chequeBounselast1Month = chequeBounselast1Month + bankStatementData.getCheckBounceForLast1Month().doubleValue();
                					 }
            					 }
            					 scoreParameterRetailRequest.setChequeBouncelast1Month(chequeBounselast1Month);
            					 scoreParameterRetailRequest.setIsChequeBounceLast1Month_p(true);
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 1 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.AutoLoan.CHECQUE_BOUNSE_LAST_6_MONTH:
            				 try {
            					 Double chequeBounselast6Month = 0.0d;
            					 for(Data bankStatementData : bankStatementDatas) {
            						 if(bankStatementData != null && bankStatementData.getCheckBounceForLast6Month() != null) {
            							 chequeBounselast6Month = chequeBounselast6Month + bankStatementData.getCheckBounceForLast6Month().doubleValue();
                					 }
            					 }
            				   scoreParameterRetailRequest.setChequeBounce(chequeBounselast6Month);
                               scoreParameterRetailRequest.setChequeBounce_p(true);
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 6 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.AutoLoan.DPD:
            				try {
            					Integer maxDPD = 0;
            					if(!CommonUtils.isListNullOrEmpty(dpds)) {
            						maxDPD = Collections.max(dpds);
            					}
                                logger.info("Max DPD===>{}",maxDPD);
                                if (!CommonUtils.isObjectNullOrEmpty(maxDPD)) {
                                    scoreParameterRetailRequest.setDpd(maxDPD.doubleValue());
                                } else {
                                    scoreParameterRetailRequest.setDpd(0.0);
                                }
                                scoreParameterRetailRequest.setDPD_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting DPD parameter from CIBIL client : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.LTV:
            				if(AutoLoanPurposeType.NEW_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose()) || AutoLoanPurposeType.NEW_TWO_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
            					if(primaryAutoLoanDetail.getVehicleOnRoadPrice() != null) {
                    				try {
    									if(scoringRequestLoans.getElAmountOnAverageScoring() != null) {
    										scoreParameterRetailRequest.setLtv(scoringRequestLoans.getElAmountOnAverageScoring());
    										scoreParameterRetailRequest.setOnRoadPrice(primaryAutoLoanDetail.getVehicleOnRoadPrice().doubleValue());
    										scoreParameterRetailRequest.setIsLTV_p(true);
    									}else {
    										logger.warn("Eligible Loan Amount Based on Income is not Set in LTV==== > {}",scoringRequestLoans.getElAmountOnAverageScoring());
    									}
        							} catch (Exception e1) {
        								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
        							}
                				}
                            }else if(AutoLoanPurposeType.SECOND_HAND_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose()) || AutoLoanPurposeType.SECOND_HAND_TWO_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
                            	if(primaryAutoLoanDetail.getVehicleAgreedPurchasePrice() != null) {
                    				try {
    									if(scoringRequestLoans.getElAmountOnAverageScoring() != null) {
    										scoreParameterRetailRequest.setLtv(scoringRequestLoans.getElAmountOnAverageScoring());
    										scoreParameterRetailRequest.setOnRoadPrice(primaryAutoLoanDetail.getVehicleAgreedPurchasePrice().doubleValue());
    										scoreParameterRetailRequest.setIsLTV_p(true);
    									}else {
    										logger.warn("Eligible Loan Amount Based on Income is not Set in LTV==== > {}",scoringRequestLoans.getElAmountOnAverageScoring());
    									}
        							} catch (Exception e1) {
        								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
        							}
                				}
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.APPLICANT_NW_TO_LOAN_AMOUNT:
            				if(retailApplicantDetail.getNetworth() != null) {
                				try {
									if(scoringRequestLoans.getElAmountOnAverageScoring() != null) {
										scoreParameterRetailRequest.setIsNetWorth_p(true);
										scoreParameterRetailRequest.setNetWorth((retailApplicantDetail.getNetworth() / scoringRequestLoans.getElAmountOnAverageScoring()) * 100);
									}else {
										logger.warn("Eligible Loan Amount Based on Income is not Set in APPLICANT_NW_TO_LOAN_AMOUNT==== > {}",scoringRequestLoans.getElAmountOnAverageScoring());
									}
    							} catch (Exception e1) {
    								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
    							}
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.LOAN_PURPOSE:
            				scoreParameterRetailRequest.setLoanPurpose(retailApplicantDetail.getLoanPurpose());
            				if(AutoLoanPurposeType.SECOND_HAND_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose()) || AutoLoanPurposeType.SECOND_HAND_TWO_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
            					if(AutoLoanPurposeType.SECOND_HAND_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
                					scoreParameterRetailRequest.setLoanPurposeQueType(8);// Static as we do not asking to borrower for Detailed Purpose
            					}else {
                					scoreParameterRetailRequest.setLoanPurposeQueType(9);
            					}
            					scoreParameterRetailRequest.setIsLoanPurpose_p(true);
            					scoreParameterRetailRequest.setLoanPurposeQueValue(retailApplicantDetail.getLoanPurposeQueValue());
            				}else {
            					scoreParameterRetailRequest.setIsLoanPurpose_p(retailApplicantDetail.getLoanPurposeQueType() != null);
            					scoreParameterRetailRequest.setLoanPurposeQueType(retailApplicantDetail.getLoanPurposeQueType());
            					scoreParameterRetailRequest.setLoanPurposeQueValue(retailApplicantDetail.getLoanPurposeQueValue());            					
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.INCOME_PROOF:
	            				if(isItrMannualFilled == null || !isItrMannualFilled) {
	            					scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.IT_RETURN_AND_BANK_STATEMENT);
	            				}else {
	            					scoreParameterRetailRequest.setIncomeProofId(ScoreParameter.IncomeProof.BANK_STATEMENT);
	            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.AVG_EOD_BALANCE:
            				Double totalEODBalAvg = 0.0d;
            				Double totalCredit = 0.0d;
            				for(Data bankStatementData : bankStatementDatas) {
            					if(bankStatementData.getSummaryInfo() != null) {
                					if(!CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg()) && !CommonUtils.isObjectNullOrEmpty(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalCredit())) {
                						totalEODBalAvg = totalEODBalAvg + Double.parseDouble(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getBalAvg());
                						totalCredit = totalCredit + Double.parseDouble(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalCredit());
                					}
                				}
            				}
            				scoreParameterRetailRequest.setAvgEodBalToToalDep((totalEODBalAvg / totalCredit));
    						scoreParameterRetailRequest.setIsAvgEodBalToToalDep_p(true);
            				break;
            			case ScoreParameter.Retail.AutoLoan.LOAN_TO_INCOME_RATIO:
        					scoreParameterRetailRequest.setLoanToIncomeRatio_p(true);
            				break;
            			case ScoreParameter.Retail.AutoLoan.INCOME_TO_INSTALLMENT_RATIO:
        					scoreParameterRetailRequest.setEmiAmountFromCIBIL(totalEMI);
            				break;
            			case ScoreParameter.Retail.AutoLoan.BORROWER_MARGIN:
            				if(AutoLoanPurposeType.NEW_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose()) || AutoLoanPurposeType.NEW_TWO_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
            					if(retailApplicantDetail.getBorrowerContribution() != null && primaryAutoLoanDetail.getVehicleExShowRoomPrice() != null) {
                					scoreParameterRetailRequest.setBorrowerMargin(retailApplicantDetail.getBorrowerContribution().doubleValue() / primaryAutoLoanDetail.getVehicleExShowRoomPrice().doubleValue() * 100);
                					scoreParameterRetailRequest.setIsBorrowerMargin_p(true);
                				}
                            }else if(AutoLoanPurposeType.SECOND_HAND_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose()) || AutoLoanPurposeType.SECOND_HAND_TWO_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
                            	if(retailApplicantDetail.getBorrowerContribution() != null && primaryAutoLoanDetail.getVehicleAgreedPurchasePrice() != null) {
                					scoreParameterRetailRequest.setBorrowerMargin(retailApplicantDetail.getBorrowerContribution().doubleValue() / primaryAutoLoanDetail.getVehicleAgreedPurchasePrice().doubleValue() * 100);
                					scoreParameterRetailRequest.setIsBorrowerMargin_p(true);
                				}
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.SECURITY_COVERAGE:
            				if(AutoLoanPurposeType.NEW_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose()) || AutoLoanPurposeType.NEW_TWO_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
            					if(primaryAutoLoanDetail.getVehicleOnRoadPrice() != null) {
                					scoreParameterRetailRequest.setSecurityCoverage((primaryAutoLoanDetail.getVehicleOnRoadPrice() / scoringRequestLoans.getElAmountOnAverageScoring()) * 100);
                    				scoreParameterRetailRequest.setIsSecurityCoverage_p(true);
                				}            					
                            }else if(AutoLoanPurposeType.SECOND_HAND_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose()) || AutoLoanPurposeType.SECOND_HAND_TWO_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
                            	if(primaryAutoLoanDetail.getVehicleAgreedPurchasePrice() != null) {
                					scoreParameterRetailRequest.setSecurityCoverage((primaryAutoLoanDetail.getVehicleAgreedPurchasePrice() / scoringRequestLoans.getElAmountOnAverageScoring()) * 100);
                    				scoreParameterRetailRequest.setIsSecurityCoverage_p(true);
                				}
                            }
            				break;
            			case ScoreParameter.Retail.AutoLoan.CAR_SEGMENT:
            				if(AutoLoanPurposeType.NEW_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose()) || AutoLoanPurposeType.NEW_TWO_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
            					scoreParameterRetailRequest.setCarSegment(retailApplicantDetail.getLoanPurposeQueType());
                				scoreParameterRetailRequest.setIsCarSegment_p(retailApplicantDetail.getLoanPurposeQueType() != null);            					
            				}else {
            					if(AutoLoanPurposeType.SECOND_HAND_FOUR_WHEELER_LOAN.getId().equals(retailApplicantDetail.getLoanPurpose())) {
            						scoreParameterRetailRequest.setCarSegment(9); //Static Because No Enum is Avaiable
            					}else {
            						scoreParameterRetailRequest.setCarSegment(8); //Static Because No Enum is Avaiable           						
            					}
                				scoreParameterRetailRequest.setIsCarSegment_p(true);
            				}
            				break;
            			case ScoreParameter.Retail.AutoLoan.TAKE_HOME_PAY:
            				scoreParameterRetailRequest.setTakeHomePay(scoringRequestLoans.getNetTakeHomepay());
            				scoreParameterRetailRequest.setIsTakeHomePay_p(scoringRequestLoans.getNetTakeHomepay() != null);
            				break;
            			case ScoreParameter.Retail.AutoLoan.PERSONAL_RELATIONSHIP_WITH_BANK: //
            				scoreParameterRetailRequest.setIsPersonalRelationShipWithBank_p(true);
            				break;	
            			case ScoreParameter.Retail.AutoLoan.IS_ADHAAR_CARD:
            				scoreParameterRetailRequest.setIsAdhaarCard_p(true);
            				scoreParameterRetailRequest.setAdhaarCardValue(YesNo.NO.getId()); // Default No
            				if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getIsUserHaveAadhar()) && retailApplicantDetail.getIsUserHaveAadhar()) {
            					scoreParameterRetailRequest.setAdhaarCardValue(YesNo.YES.getId()); // Default Yes	
            				}
            				break;	
                            default:
                                break;

                        }
                    }
//                    logger.info(MSG_SCORE_PARAMETER + scoreParameterRetailRequest.toString());
                    logger.info("----------------------------END-------------------------------------------");

                    Gson g = new GsonBuilder().serializeSpecialFloatingPointValues().create();
                    ScoringRequestDetail scoringRequestDetail = new ScoringRequestDetail();

                    try {
                        scoringRequestDetail.setApplicationId(applicationId);
                        scoringRequestDetail.setRequest(g.toJson(scoreParameterRetailRequest));
                        scoringRequestDetail.setCreatedDate(new Date());
                        scoringRequestDetail.setIsActive(true);
                        scoringRequestDetailRepository.save(scoringRequestDetail);

                        logger.info(SAVING_SCORING_REQUEST_DATA_FOR + applicationId);
                    } catch (Exception e) {
                        logger.error(CommonUtils.EXCEPTION,e);
                    }
                }
            scoringRequest.setScoreParameterRetailRequest(scoreParameterRetailRequest);
            scoringRequestList.add(scoringRequest);
        }

        try {
            ScoringResponse calculateScoreList = scoringClient.calculateScoreList(scoringRequestList);
            logger.info("Scoring Response For HOME Loan============>{}",calculateScoreList);
            logger.info("Scoring Response Status For HOME Loan ============>{}",calculateScoreList != null ? calculateScoreList.getStatus() : calculateScoreList);
            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED,applicationId);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
	}
}
