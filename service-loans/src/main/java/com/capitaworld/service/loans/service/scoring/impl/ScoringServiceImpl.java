package com.capitaworld.service.loans.service.scoring.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.HomeLoanModelRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoreParameterRequestLoans;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;
import com.capitaworld.service.loans.repository.common.LoanRepository;
import com.capitaworld.service.loans.repository.fundseeker.ScoringRequestDetailRepository;
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
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantIncomeRepository;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanModelService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.utils.scoreexcel.ScoreExcelFileGenerator;
import com.capitaworld.service.loans.utils.scoreexcel.ScoreExcelReader;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.BankList;
import com.capitaworld.service.oneform.enums.EmploymentWithPL;
import com.capitaworld.service.oneform.enums.EmploymentWithPLScoring;
import com.capitaworld.service.oneform.enums.scoring.EnvironmentCategory;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.model.IndustryResponse;
import com.capitaworld.service.rating.model.IrrRequest;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.FundSeekerInputRequest;
import com.capitaworld.service.scoring.model.GenericCheckerReqRes;
import com.capitaworld.service.scoring.model.ModelParameterResponse;
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
import com.google.gson.Gson;

@Service
@Transactional
public class ScoringServiceImpl implements ScoringService {


    private final Logger logger = LoggerFactory.getLogger(ScoringServiceImpl.class);

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
    private EligibilityClient eligibilityClient;
    
    @Autowired
	private HomeLoanModelService homeLoanModelService;
    
    @Autowired
    private LoanRepository loanRepository;

    private static final String ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING = "Error while getting retail applicant detail for personal loan scoring : ";
    private static final String ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_HOME_LOAN_SCORING = "Error while getting retail applicant detail for Home loan scoring : ";
    private static final String ERROR_WHILE_GETTING_FIELD_LIST = "error while getting field list : ";
    private static final String ERROR_WHILE_CALLING_SCORING = "error while calling scoring : ";

    private static final String SAVING_SCORING_REQUEST_DATA_FOR = "Saving Scoring Request Data for  =====> ";
    private static final String SCORE_IS_SUCCESSFULLY_CALCULATED = "score is successfully calculated";
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

                                    if(EmploymentWithPL.CENTRAL_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();

                                    }
                                    else if(EmploymentWithPL.STATE_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.PSU.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.CORPORATE.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        if(true == salaryWithBank)
                                            employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                        else
                                            employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.EDUCATIONAL_INSTITUTE.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        employmentWithPlValue= EmploymentWithPLScoring.EDUCATIONAL_INSTITUTE.getId().longValue();
                                    }
                                    else if(EmploymentWithPL.OTHERS.getId() == retailApplicantDetail.getEmploymentWith())
                                    {
                                        employmentWithPlValue= EmploymentWithPLScoring.OTHERS.getId().longValue();
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

        ScoringResponse scoringResponseMain = null;

        List<ScoringRequest> scoringRequestList=new ArrayList<ScoringRequest>();

        ScoreParameterRetailRequest scoreParameterRetailRequest = null;
        for(ScoringRequestLoans scoringRequestLoans:scoringRequestLoansList)
        {

            Long scoreModelId = scoringRequestLoans.getScoringModelId();
            Long applicationId = scoringRequestLoans.getApplicationId();
            Long fpProductId = scoringRequestLoans.getFpProductId();


            ///////// Start Getting Old Request ///////


            /*List<ScoringRequestDetail> scoringRequestDetailList = scoringRequestDetailRepository.getScoringRequestDetailByApplicationIdAndIsActive(applicationId);

            ScoringRequestDetail scoringRequestDetailSaved = new ScoringRequestDetail();

            if (scoringRequestDetailList.size() > 0) {
                logger.info("Getting Old Scoring request Data for  =====> " + applicationId);
                scoringRequestDetailSaved = scoringRequestDetailList.get(0);
                Gson gson = new Gson();
                scoreParameterRetailRequest = gson.fromJson(scoringRequestDetailSaved.getRequest(), ScoreParameterRetailRequest.class);
            }*/

            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.RETAIL_PERSONAL_LOAN);
            scoringRequest.setEmi(scoringRequestLoans.getEmi());

            if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
            } else {
                scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
            }

            ///////// End  Getting Old Request ///////

            if (CommonUtils.isObjectNullOrEmpty(scoreParameterRetailRequest)) {
                scoreParameterRetailRequest= new ScoreParameterRetailRequest();
                logger.info("----------------------------START RETAIL PL ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);

                // GET SCORE RETAIL PERSONAL LOAN PARAMETERS

                RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);

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

                                Double cibil_score = null;
                                try {

                                    scoreParameterRetailRequest.setCibilScore_p(false);
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

                                        if(EmploymentWithPL.CENTRAL_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();

                                        }
                                        else if(EmploymentWithPL.STATE_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.PSU.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.PSU_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.CORPORATE.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            if(true == salaryWithBank)
                                                employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue= EmploymentWithPLScoring.CORPORATE_SALARY_ACCOUNT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.EDUCATIONAL_INSTITUTE.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            employmentWithPlValue= EmploymentWithPLScoring.EDUCATIONAL_INSTITUTE.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.OTHERS.getId() == retailApplicantDetail.getEmploymentWith())
                                        {
                                            employmentWithPlValue= EmploymentWithPLScoring.OTHERS.getId().longValue();
                                        }
                                        else if(EmploymentWithPL.QUASI_GOVERNMENT.getId() == retailApplicantDetail.getEmploymentWith()){
                                            if(true==salaryWithBank)
                                                employmentWithPlValue = EmploymentWithPLScoring.QUASI_GOVERNMENT_WITH_BANK.getId().longValue();
                                            else
                                                employmentWithPlValue = EmploymentWithPLScoring.QUASI_GOVERNMENT_NOT_WITH_BANK.getId().longValue();
                                        }
                                        logger.info("==============employmentWithPlValue: ============ "+employmentWithPlValue + " " +retailApplicantDetail.getEmploymentWith() );
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
                                    Double monthlyIncome = 0d;
                                    EligibilityResponse eligibilityResponse = eligibilityClient.getMonthlyIncome(applicationId);
                                    if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse)
                                            && !com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse.getData())){
                                        List monthlyIncomeList = (List) eligibilityResponse.getData();
                                        if(!com.capitaworld.service.matchengine.utils.CommonUtils.isListNullOrEmpty(monthlyIncomeList)){
                                            monthlyIncome = Double.valueOf(monthlyIncomeList.get(0).toString());
                                        }
                                    }

                                    if (!CommonUtils.isObjectNullOrEmpty(monthlyIncome)) {
                                        scoreParameterRetailRequest.setNetAnnualIncome(monthlyIncome * 12);
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

                                    try {
                                        Double netMonthlyIncome = 0d;
                                        //Double emi = scoringRequestLoans.getEmi();
                                        EligibilityResponse eligibilityResponse = eligibilityClient.getMonthlyIncome(applicationId);
                                        if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse)
                                                && !com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse.getData())){
                                            List monthlyIncomeList = (List) eligibilityResponse.getData();
                                            if(!com.capitaworld.service.matchengine.utils.CommonUtils.isListNullOrEmpty(monthlyIncomeList)){
                                                netMonthlyIncome = Double.valueOf(monthlyIncomeList.get(0).toString());
                                            }
                                        }

                                        if (!CommonUtils.isObjectNullOrEmpty(netMonthlyIncome) && !CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getEmi())) {
                                            scoreParameterRetailRequest.setEmiNmi_p(true);
                                            scoreParameterRetailRequest.setNmi(netMonthlyIncome);
                                            //scoreParameterRetailRequest.setEmi(emi);
                                        } else {
                                            scoreParameterRetailRequest.setEmiNmi_p(false);
                                            logger.error("Monthly income from Eligibility:: " + netMonthlyIncome);
                                        }
                                    } catch (Exception e) {
                                        logger.error("error while getting EMI_NMI_PL parameter : ",e);
                                        scoreParameterRetailRequest.setNetAnnualIncome_p(false);
                                    }
                                break;
                            }
                            case ScoreParameter.Retail.NO_OF_YEAR_CURRENT_LOCATION_PL:
                                try {
                                    Integer year = retailApplicantDetail.getResidenceSinceYear();
                                    Integer month = retailApplicantDetail.getResidenceSinceMonth();
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
                                    String s = "01/" + month + "/" + year;
                                    scoreParameterRetailRequest.setNoOfYearCurrentLocation(Math.ceil(CommonUtils.getAgeFromBirthDate(simpleDateFormat.parse(s)).doubleValue()));
                                    scoreParameterRetailRequest.setIsNoOfYearCurrentLocation_p(true);
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
                                    EligibilityResponse eligibilityResponse = eligibilityClient.getMonthlyIncome(applicationId);
                                    if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse) && !com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse.getData())){
                                        List monthlyIncomeList = (List) eligibilityResponse.getData();
                                        if(!com.capitaworld.service.matchengine.utils.CommonUtils.isListNullOrEmpty(monthlyIncomeList)){
                                            monthlyIncome = Double.valueOf(monthlyIncomeList.get(0).toString());
                                        }
                                    }

                                    if (!CommonUtils.isObjectNullOrEmpty(monthlyIncome)) {
                                        scoreParameterRetailRequest.setLoanToIncomeRatio_p(true);
                                        scoreParameterRetailRequest.setNetAnnualIncome(monthlyIncome * 12);
                                        scoreParameterRetailRequest.setLoanAmtProposed(retailApplicantDetail.getLoanAmountRequired());
                                    } else {
                                        scoreParameterRetailRequest.setLoanToIncomeRatio_p(false);
                                    }
                                } catch (Exception e) {
                                    logger.error("error while getting LOAN_TO_INCOME_RATIO_PL parameter : ",e);
                                    scoreParameterRetailRequest.setLoanToIncomeRatio_p(false);
                                }
                                break;
                            }
                            default:
                                break;

                        }
                    }

                    Double grossAnnualIncome =0d;
                    try {
                        EligibilityResponse eligibilityResponse = eligibilityClient.getMonthlyIncome(applicationId);
                        if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse) && !com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse.getData())){
                            List monthlyIncomeList = (List) eligibilityResponse.getData();
                            if(!com.capitaworld.service.matchengine.utils.CommonUtils.isListNullOrEmpty(monthlyIncomeList)){
                                grossAnnualIncome = Double.valueOf(monthlyIncomeList.get(8).toString());
                                scoreParameterRetailRequest.setGrossAnnualIncome(grossAnnualIncome*12);
                            }
                        }
                    } catch (EligibilityExceptions eligibilityExceptions) {
                        logger.error("error while getting GROSS ANNUAL INCOME FROM ELIGIBILITY  : ",eligibilityExceptions);
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
            scoringResponseMain = scoringClient.calculateScoreList(scoringRequestList);

            logger.info(SCORE_IS_SUCCESSFULLY_CALCULATED);
            LoansResponse loansResponse = new LoansResponse(SCORE_IS_SUCCESSFULLY_CALCULATED, HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_CALLING_SCORING,e);
            LoansResponse loansResponse = new LoansResponse(ERROR_WHILE_CALLING_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }
    
    
    private void setLoanPurposeModelFields(ScoreParameterRetailRequest parameterRetailRequest,HomeLoanModelRequest homeLoanModelRequest) {
	    if(homeLoanModelRequest != null) {
	    	parameterRetailRequest.setIsPurReadyBuiltHouse(homeLoanModelRequest.getIsPurReadyBuiltHouse());
	    	parameterRetailRequest.setIsPurReadyBuiltIndependentHouse(homeLoanModelRequest.getIsPurReadyBuiltIndependentHouse());
	    	parameterRetailRequest.setIsPurResidetialFlat(homeLoanModelRequest.getIsPurResidetialFlat());
	    	parameterRetailRequest.setIsPurResidetialFlatAllotee(homeLoanModelRequest.getIsPurResidetialFlatAllotee());
	    	parameterRetailRequest.setIsPurResidetialSite(homeLoanModelRequest.getIsPurResidetialSite());
	    	parameterRetailRequest.setIsConstruResidetialBuid(homeLoanModelRequest.getIsConstruResidetialBuid());
	    	parameterRetailRequest.setIsConstruExpaResBuild(homeLoanModelRequest.getIsConstruExpaResBuild());
	    	parameterRetailRequest.setIsConstruPurResSite(homeLoanModelRequest.getIsConstruPurResSite());
	    	parameterRetailRequest.setIsRepPurReadyBuiltIndependant(homeLoanModelRequest.getIsRepPurReadyBuiltIndependant());
	    	parameterRetailRequest.setIsRepRenImpFlatHouse(homeLoanModelRequest.getIsRepRenImpFlatHouse());
	    	parameterRetailRequest.setIsOthRefExcessMarginPaid(homeLoanModelRequest.getIsOthRefExcessMarginPaid());
	    	parameterRetailRequest.setIsOthLoanReimbursementFlat(homeLoanModelRequest.getIsOthLoanReimbursementFlat());
	    }
    }
    
    @Override
    public ResponseEntity<LoansResponse> calculateRetailHomeLoanScoringList(List<ScoringRequestLoans> scoringRequestLoansList) {

        RetailApplicantDetail retailApplicantDetail = null;
        Long applicationId = null;
        Long orgId = null;
        List<Long> coAppIds = null;
        List<Long> coAppITRUploadedIds = null;
        Double netMonthlyIncome = 0.0d;
        Double grossAnnualIncome = 0.0d;
        PrimaryHomeLoanDetail primaryHomLoanDetail = null;
        Data bankStatementData = null;
        Double totalEMI = 0.0d;
        CibilScoreLogRequest cibilResponse = null;
        CibilResponse cibilResponseDpd = null;

        if(!CommonUtils.isListNullOrEmpty(scoringRequestLoansList)) {
        	applicationId = scoringRequestLoansList.get(0).getApplicationId();
        	retailApplicantDetail = retailApplicantDetailRepository.findByApplicationId(applicationId);
        	if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                return new ResponseEntity<>(new LoansResponse(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_HOME_LOAN_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        	
        	primaryHomLoanDetail = primaryHomeLoanDetailRepository.getByApplication(applicationId);
        	if (CommonUtils.isObjectNullOrEmpty(primaryHomLoanDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                return new ResponseEntity<>(new LoansResponse("Primary Detail Must Not be null While Calculating Home Loan Scoring", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        	EligibilityResponse eligibilityResponse = null;
			try {
				eligibilityResponse = eligibilityClient.getMonthlyIncome(applicationId);
			} catch (EligibilityExceptions e) {
				logger.error("Error while Getting MonthlyIncome Details == >{}",e);
			}
            if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse)
                    && !com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse.getData())){
                List incomeList = (List) eligibilityResponse.getData();
                if(!com.capitaworld.service.matchengine.utils.CommonUtils.isListNullOrEmpty(incomeList)){
                    netMonthlyIncome = Double.valueOf(incomeList.get(0).toString());
                    grossAnnualIncome = Double.valueOf(incomeList.get(8).toString());
                }
                logger.info("Net Monthly Income For ApplicationId======{}======>{}",applicationId,netMonthlyIncome);
                logger.info("Gross Annual Income For ApplicationId======{}======>{}",applicationId,grossAnnualIncome);
            }
            try {
                 ReportRequest reportRequest = new ReportRequest();
                 reportRequest.setApplicationId(applicationId);
                 AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);
                 bankStatementData = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) analyzerResponse.getData(),Data.class);
            }catch(Exception e) {
            	logger.error("Error while getting Bank Statement Details");
            }
            
            totalEMI = financialArrangementDetailsService.getTotalEmiByApplicationIdSoftPing(applicationId);
            
            CibilRequest cibilRequest = new CibilRequest();
            cibilRequest.setPan(retailApplicantDetail.getPan());
            cibilRequest.setApplicationId(applicationId);
            try {
            	cibilResponse = cibilClient.getCibilScoreByPanCard(cibilRequest);
                cibilResponseDpd = cibilClient.getDPDLastXMonth(applicationId,retailApplicantDetail.getPan());	
            }catch(Exception e) {
            	logger.error("Error From CIBIL==>{}",e);
            	
            }
            
            
        }
        List<ScoringRequest> scoringRequestList=new ArrayList<>(scoringRequestLoansList.size());
        ScoreParameterRetailRequest scoreParameterRetailRequest = null;
//        HLEligibilityRequest hlEligibilityRequest = null;
        HomeLoanModelRequest homeLoanModelRequest = null;
        for(ScoringRequestLoans scoringRequestLoans : scoringRequestLoansList)
        {
            Long scoreModelId = scoringRequestLoans.getScoringModelId();
            Long fpProductId = scoringRequestLoans.getFpProductId();
            homeLoanModelRequest = homeLoanModelService.get(scoringRequestLoans.getLoanPurposeModelId(), null, null);
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

            ///////// End  Getting Old Request ///////

            if (CommonUtils.isObjectNullOrEmpty(scoreParameterRetailRequest)) {
                scoreParameterRetailRequest= new ScoreParameterRetailRequest();
                setLoanPurposeModelFields(scoreParameterRetailRequest, homeLoanModelRequest);
                scoringRequest.setLoanPurposeModelId(homeLoanModelRequest.getId());
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

                        
                        scoreParameterRetailRequest.setLoanAmtProposed(scoringRequestLoans.getElAmountOnAverageScoring());
                        switch (modelParameterResponse.getName()) {
                        case ScoreParameter.Retail.HomeLoan.AGE:
                        	   try {
                                   if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
                                	   long yearsDiff = ChronoUnit.YEARS.between(retailApplicantDetail.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),retailApplicantDetail.getCreatedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                                	   logger.info("");
                                       scoreParameterRetailRequest.setAge((double)yearsDiff);
                                       scoreParameterRetailRequest.setAge_p(true);
                                   }
                               } catch (Exception e) {
                                   logger.error("error while getting AGE_HL parameter : ",e);
                               }
                        	break;
            			case ScoreParameter.Retail.HomeLoan.TOTAL_JOB_EXP:
            				try {
                                Double totalExperience = 0.0;
                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceYear())) {
                                	totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceYear());
                                	logger.info("totalExperience Year {}===>{}",retailApplicantDetail.getTotalExperienceYear());
                                	
                                }

                                if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getTotalExperienceMonth())) {
                                	totalExperience += Double.valueOf(retailApplicantDetail.getTotalExperienceMonth()) / 12;
                                	logger.info("totalExperience Month {}===>{}",retailApplicantDetail.getTotalExperienceMonth());
                                }
                                
                                logger.info("totalExperience {}===>{}",totalExperience);

                                scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                scoreParameterRetailRequest.setWorkingExperience_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting TOTAL_JOB_EXP parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.CURRENT_JOB_EXP:
            				try {
            				 Double currentExperience = 0.0;
                             if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getCurrentJobYear())) {
                            	 currentExperience += Double.valueOf(retailApplicantDetail.getCurrentJobYear());
                            	 logger.info("currentExperience Year {}===>{}",currentExperience);
                             }
                             if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getCurrentJobMonth())) {
                                 currentExperience += (retailApplicantDetail.getCurrentJobMonth() / 12);
                            	 logger.info("currentExperience Month {}===>{}",(retailApplicantDetail.getCurrentJobMonth() / 12));	 
                             }
                             logger.info("currentExperience {}===>{}",currentExperience);
                             scoreParameterRetailRequest.setWorkingExperienceCurrent(currentExperience);
                             scoreParameterRetailRequest.setIsWorkingExperienceCurrent_p(true);
                         } catch (Exception e) {
                             logger.error("error while getting CURRENT_JOB_EXP parameter : {}",e);
                         }
            				break;
            			case ScoreParameter.Retail.HomeLoan.TOTAL_BUSI_PROFE_EXP:
            				if(retailApplicantDetail.getBusinessStartDate() != null) {
            					logger.info("retailApplicantDetail.getBusinessStartDate() For HL==== > {}",retailApplicantDetail.getBusinessStartDate());
            					long diffYears = ChronoUnit.YEARS.between(retailApplicantDetail.getBusinessStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            					logger.info("Total Business Experiance For HL==== > {}",diffYears);
            					scoreParameterRetailRequest.setTotalBusProfExperiance((int)diffYears);
            					scoreParameterRetailRequest.setIsTotalBusProfExperiance_p(true);
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.RESIDENCE_TYPE:
            				scoreParameterRetailRequest.setIsResidenceType_p(retailApplicantDetail.getResidenceType() != null);
            				scoreParameterRetailRequest.setResidenceType(retailApplicantDetail.getResidenceType());
            				break;
            			case ScoreParameter.Retail.HomeLoan.NO_YEARS_STAY_CURR_LOC:
            				try {
            					if(retailApplicantDetail.getResidenceSinceYear() != null && retailApplicantDetail.getResidenceSinceMonth() != null) {
            						Integer year = retailApplicantDetail.getResidenceSinceYear();
    	                            Integer month = retailApplicantDetail.getResidenceSinceMonth();
    	                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
    	                            String s = "01/" + month + "/" + year;
    	                            logger.info("Starting Date of Staying in Current Location For HL==== > {}",s);
    	                            double ceil = Math.ceil(CommonUtils.getAgeFromBirthDate(simpleDateFormat.parse(s)).doubleValue());
    	                            logger.info("No Of Years Staying in Current Location For HL==== > {}",ceil);
    	                            scoreParameterRetailRequest.setNoOfYearCurrentLocation(ceil);
    	                            scoreParameterRetailRequest.setIsNoOfYearCurrentLocation_p(true);            						
            					}
            				} catch (Exception e) {
	                            logger.error("error while getting NO_YEARS_STAY_CURR_LOC parameter : ", e);
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.BUREAU_SCORE:
            				Double cibilScore = null;
                            try {
                                logger.info("Cibil Score Response For HL==== > {}",cibilResponse.getScore());
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse.getScore())) {
                                    cibilScore = Double.parseDouble(cibilResponse.getScore());
                                    scoreParameterRetailRequest.setCibilScore(cibilScore);
                                    scoreParameterRetailRequest.setCibilScore_p(true);
                                } 
                            } catch (Exception e) {
                                logger.error("error while getting BUREAU_SCORE parameter from CIBIL client : ",e);
                                scoreParameterRetailRequest.setCibilScore_p(false);
                            }
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
            				scoreParameterRetailRequest.setIsEmployementJobCat_p(retailApplicantDetail.getEmploymentStatus() != null);
            				scoreParameterRetailRequest.setEmploymentTypeCatJob((retailApplicantDetail.getEmploymentStatus() != null  ? retailApplicantDetail.getEmploymentStatus() : null));
            				break;
            			case ScoreParameter.Retail.HomeLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED:
            		        scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(retailApplicantDetail.getEmploymentSubStatus() != null);
            		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus((retailApplicantDetail.getEmploymentSubStatus() != null  ? retailApplicantDetail.getEmploymentSubStatus().longValue() : null));
            				break;
            			case ScoreParameter.Retail.HomeLoan.CURRENT_EMPLOYMENT_STATUS:
            				scoreParameterRetailRequest.setIsCurrentEmploymentStatus_p(retailApplicantDetail.getCurrentEmploymentStatus() != null);
            				scoreParameterRetailRequest.setCurrentEmploymentStatus((retailApplicantDetail.getCurrentEmploymentStatus() != null  ? retailApplicantDetail.getCurrentEmploymentStatus().longValue() : null));
            				break;
            			case ScoreParameter.Retail.HomeLoan.MIN_BANKING_RELATIONSHIP:
            				scoreParameterRetailRequest.setIsMinBankingRelationship_p(minBankRelationshipInMonths != null);
            				scoreParameterRetailRequest.setMinBankingRelationship(minBankRelationshipInMonths);
            				break;
            			case ScoreParameter.Retail.HomeLoan.SPOUSE_EMPLOYEMENT:
            				try {
                                Long spouseEmployment = 3l; // No Spouse
                                if(retailApplicantDetail.getSpouseEmployment() != null) {
                                	spouseEmployment = retailApplicantDetail.getSpouseEmployment().longValue();                                	
                                }
                                scoreParameterRetailRequest.setSpouseEmploymentDetails(spouseEmployment);
                                scoreParameterRetailRequest.setSpouseEmploymentDetails_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting SPOUSE_EMPLOYEMENT parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.NO_OF_DEPENDANTS:
            				try {
                                scoreParameterRetailRequest.setNumberOfDependents((retailApplicantDetail.getNoOfDependent() != null ? retailApplicantDetail.getNoOfDependent() : null));
                                scoreParameterRetailRequest.setNumberOfDependents_p(retailApplicantDetail.getNoOfDependent() != null);
                            } catch (Exception e) {
                                logger.error("error while getting NO_OF_DEPENDANTS parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.NO_OF_APPLICANTS:
            				coAppIds = coApplicantDetailRepository.getCoAppIds(applicationId);
            	        	if(!CommonUtils.isListNullOrEmpty(coAppIds)) {
            	        		coAppITRUploadedIds = coApplicantDetailRepository.getCoAppIdsOfCoApplicantUploadedITR(applicationId);
            	        	}
            	        	
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
            				try {
            					if (scoringRequestLoans.getIsSetGrossNetIncome() != null && scoringRequestLoans.getIsSetGrossNetIncome()) {
            						if (scoringRequestLoans.getIncomeType() == null || scoringRequestLoans.getIncomeType() == 2) { // Net Monthly Income
            							scoreParameterRetailRequest.setNetAnnualIncome(netMonthlyIncome * 12);
            							scoreParameterRetailRequest.setNetAnnualIncome_p(true);
            							scoreParameterRetailRequest.setGrossAnnualIncome(null);
            						} else if (scoringRequestLoans.getIncomeType() == 1) { // Gross Monthly Income
            							scoreParameterRetailRequest.setGrossAnnualIncome(grossAnnualIncome);
            							scoreParameterRetailRequest.setNetAnnualIncome(null);
            							scoreParameterRetailRequest.setNetAnnualIncome_p(false);
            						}
            					}else {
									logger.warn("Gross Or Net Income is Not Set By Lender ANNUAL_INCOME==== > {}",scoringRequestLoans.getIsSetGrossNetIncome());
								}
                            } catch (Exception e) {
                                logger.error("error while getting ANNUAL_INCOME parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVAILABLE_INCOME:
            				try {
								if(scoringRequestLoans.getElAmountBasedOnIncome() != null) {
										scoreParameterRetailRequest.setAvailableIncome(scoringRequestLoans.getElAmountBasedOnIncome());
										scoreParameterRetailRequest.setIsAvailableIncome_p(true);
								}else {
									logger.warn("Eligible Loan Amount Based on Income is not Set in AVAILABLE_INCOME ==== > {}",scoringRequestLoans.getElAmountBasedOnIncome());
								}
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
            			case ScoreParameter.Retail.HomeLoan.TOIR:
            				try {
            					if (scoringRequestLoans.getIsSetGrossNetIncome() != null && scoringRequestLoans.getIsSetGrossNetIncome()) {
            						if (scoringRequestLoans.getIncomeType() == null || scoringRequestLoans.getIncomeType() == 2) { // Net Monthly Income
            							scoreParameterRetailRequest.setToir(scoringRequestLoans.getEmi() / netMonthlyIncome); 
            						} else if (scoringRequestLoans.getIncomeType() == 1) { // Gross Monthly Income
            							scoreParameterRetailRequest.setToir(scoringRequestLoans.getEmi() / grossAnnualIncome);
            						}
            						scoreParameterRetailRequest.setIsToir_p(true);
            					}else {
									logger.warn("Gross Or Net Income is Not Set By Lender TOIR==== > {}",scoringRequestLoans.getIsSetGrossNetIncome());
								}
                            } catch (Exception e) {
                                logger.error("error while getting TOIR parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.ADDI_INCOME_SPOUSE:
	            				if(retailApplicantDetail.getAnnualIncomeOfSpouse() != null) {
	            					scoreParameterRetailRequest.setSpouseIncome(retailApplicantDetail.getAnnualIncomeOfSpouse());
	            					scoreParameterRetailRequest.setIsSpouseIncome_p(true);
	            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.MON_INCOME_DEPENDANT:
            				if (scoringRequestLoans.getIsSetGrossNetIncome() != null && scoringRequestLoans.getIsSetGrossNetIncome()) {
        						if (scoringRequestLoans.getIncomeType() == null || scoringRequestLoans.getIncomeType() == 2) { // Net Monthly Income
        							if (!CommonUtils.isObjectNullOrEmpty(netMonthlyIncome) && retailApplicantDetail.getNoOfDependent() != null && retailApplicantDetail.getNoOfDependent() != 0) {
                                        scoreParameterRetailRequest.setMonIncomePerDep(netMonthlyIncome / (retailApplicantDetail.getNoOfDependent() + 1)); //1 is Applicant him/his self
                                    }else {
                                    	scoreParameterRetailRequest.setMonIncomePerDep(netMonthlyIncome / 1); //1 is Applicant him/his self
                                    }        							 
        						} else if (scoringRequestLoans.getIncomeType() == 1) { // Gross Monthly Income
        							if (!CommonUtils.isObjectNullOrEmpty(netMonthlyIncome) && retailApplicantDetail.getNoOfDependent() != null && retailApplicantDetail.getNoOfDependent() != 0) {
                                        scoreParameterRetailRequest.setMonIncomePerDep(grossAnnualIncome / (retailApplicantDetail.getNoOfDependent() + 1)); //1 is Applicant him/his self
                                    }else {
                                    	scoreParameterRetailRequest.setMonIncomePerDep(grossAnnualIncome / 1); //1 is Applicant him/his self
                                    }
        						}
        						scoreParameterRetailRequest.setIsMonIncomePerDep_p(true);
        					}else {
								logger.warn("MON_INCOME_DEPENDANT is Not Set By Lender TOIR==== > {}",scoringRequestLoans.getIsSetGrossNetIncome());
							}
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVG_INCREASE_INCOME_REPORT_3_YEARS:
            				List<Double> incomeOfItrOf3Years = loanRepository.getIncomeOfItrOf3Years(applicationId);
            				logger.info("Income List From ITR for HL == >{}",incomeOfItrOf3Years);
            				if(!CommonUtils.isListNullOrEmpty(incomeOfItrOf3Years)) {
            					if(incomeOfItrOf3Years.size() == 3) { //as if now considering 3 Years Compulsory
            						Double itrLastToLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 1);
            						if(itrLastToLastToLastYearIncome == null ) {
            							itrLastToLastToLastYearIncome = 0.0d;
            						}
                					Double itrLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 2);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 0.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 3);
                					
                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 0.0;
                					}
            						Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
            						logger.info("Final Income After Calculation for HL == >{}",finalIncome);
            						scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
            						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            					}else if(incomeOfItrOf3Years.size() == 2) { //as if now considering 2 Years Compulsory
            						Double itrLastToLastToLastYearIncome = 0.0d;
                					Double itrLastToLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 1);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 0.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 2);
                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 0.0;
                					}
            						Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
            						logger.info("Final Income After Calculation for HL == >{}",finalIncome);
            						scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
            						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            					}else if(incomeOfItrOf3Years.size() == 1) { //as if now considering 1 Years Compulsory
            						Double itrLastToLastToLastYearIncome = 0.0d;
                					Double itrLastToLastYearIncome = 0.0d;
                					Double itrLastYearIncome = incomeOfItrOf3Years.get(incomeOfItrOf3Years.size() - 1);
                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 0.0;
                					}
            						Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
            						logger.info("Final Income After Calculation for HL == >{}",finalIncome);
            						scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
            						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            					}
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.REPAYMENT_PERIOD:
	            				if(retailApplicantDetail.getRepayment() != null) {
	            					scoreParameterRetailRequest.setRepaymentPeriod(retailApplicantDetail.getRepayment());
	            					scoreParameterRetailRequest.setIsRepaymentPeriod_p(true);
	            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.AGE_PROPERTY:
	            				if(primaryHomLoanDetail.getOldPropYear() != null) {
	            					scoreParameterRetailRequest.setAgeOfProperty(primaryHomLoanDetail.getOldPropYear());
	            					scoreParameterRetailRequest.setIsAgeOfProperty_p(true);
	            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVG_DEPOS_LAST_6_MONTH:
            				if(bankStatementData != null && bankStatementData.getSummaryInfo() != null && bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails() != null  && bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit() != null) {
            					Double value =  Double.valueOf(bankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit()) / 6;
            					logger.info("AVG_DEPOS_LAST_6_MONTH value===>{}",value);
            					scoreParameterRetailRequest.setAvgOfTotalCheDepsitLast6Month(value);
	                            scoreParameterRetailRequest.setIsAvgOfTotalCheDepsitLast6Month_p(true);
       					 	}
            				
            				break;
            			case ScoreParameter.Retail.HomeLoan.CHECQUE_BOUNSE_LAST_1_MONTH:
            				 try {
            					 if(bankStatementData != null && bankStatementData.getCheckBounceForLast1Month() != null) {
            						 scoreParameterRetailRequest.setChequeBouncelast1Month(bankStatementData.getCheckBounceForLast1Month().doubleValue());
                                     scoreParameterRetailRequest.setIsChequeBounceLast1Month_p(true);            						 
            					 }
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 1 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.HomeLoan.CHECQUE_BOUNSE_LAST_6_MONTH:
            				 try {
                               if(bankStatementData != null && bankStatementData.getCheckBounceForLast6Month() != null) {
                            	   scoreParameterRetailRequest.setChequeBounce(bankStatementData.getCheckBounceForLast6Month().doubleValue());
                                   scoreParameterRetailRequest.setChequeBounce_p(true);  
                               }
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 6 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.HomeLoan.DPD:
            				try {
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isListNullOrEmpty(cibilResponseDpd.getListData())) {
                                    List<Integer> listDPD = (List<Integer>) cibilResponseDpd.getListData();
                                    Integer maxDPD = Collections.max(listDPD);
                                    logger.info("Max DPD===>{}",maxDPD);
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
            			case ScoreParameter.Retail.HomeLoan.EMI_NMI_RATIO:
            				try {
									if(scoringRequestLoans.getElAmountOnAverageScoring() != null) {
										Double monthlyRate = scoringRequestLoans.getRoi() / 100 / 12;
										Double pmtCalculation = (monthlyRate) / (1 - Math.pow(1 + monthlyRate, - (scoringRequestLoans.getEligibleTenure() * 12))) * scoringRequestLoans.getElAmountOnAverageScoring();
										scoreParameterRetailRequest.setEmi(pmtCalculation);
										if (scoringRequestLoans.getIsSetGrossNetIncome() != null && scoringRequestLoans.getIsSetGrossNetIncome()) {
		            						if (scoringRequestLoans.getIncomeType() == null || scoringRequestLoans.getIncomeType() == 2) { // Net Monthly Income
		            							//As of now Not considering Co-Applicant
												scoreParameterRetailRequest.setEmiNmiRatio(netMonthlyIncome * 12);
		            						} else if (scoringRequestLoans.getIncomeType() == 1) { // Gross Monthly Income
		            							scoreParameterRetailRequest.setEmiNmiRatio(grossAnnualIncome);
		            						}
		            						scoreParameterRetailRequest.setEmiNmi_p(true);
		            					}else {
											logger.warn("Gross Or Net Income is Not Set By Lender EMI_NMI_RATIO==== > {}",scoringRequestLoans.getIsSetGrossNetIncome());
										}
									}else {
										logger.warn("Eligible Loan Amount Based on Income is not Set in EMI_NMI_RATIO==== > {}",scoringRequestLoans.getElAmountOnAverageScoring());
									}
							} catch (Exception e1) {
								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
							}
            				break;
            			case ScoreParameter.Retail.HomeLoan.APPLICANT_NW_TO_LOAN_AMOUNT:
            				if(retailApplicantDetail.getNetworth() != null) {
                				try {
									if(scoringRequestLoans.getElAmountOnAverageScoring() != null) {
										scoreParameterRetailRequest.setIsNetWorth_p(true);
										scoreParameterRetailRequest.setNetWorth(retailApplicantDetail.getNetworth() / scoringRequestLoans.getElAmountOnAverageScoring());
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
	public ResponseEntity<LoansResponse> calculateRetailHomeLoanScoringListForCoApplicant(List<ScoringRequestLoans> scoringRequestLoansList) {
    	CoApplicantDetail coApplicantDetail = null;
        Long applicationId = null;
        Long coApplicantId = null;
        Long orgId = null;
        Double netMonthlyIncome = 0.0d;
        Double grossAnnualIncome = 0.0d;
        PrimaryHomeLoanDetail primaryHomLoanDetail = null;
        Data coApplicantBankStatementData = null;
        Double totalEMI = 0.0;
        CibilScoreLogRequest cibilResponse = null;
        CibilResponse cibilResponseDpdCoApp = null;
        if(!CommonUtils.isListNullOrEmpty(scoringRequestLoansList)) {
        	applicationId = scoringRequestLoansList.get(0).getApplicationId();
        	coApplicantId = scoringRequestLoansList.get(0).getCoApplicantId();
        	coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(coApplicantId, true);
        	if (CommonUtils.isObjectNullOrEmpty(coApplicantDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                return new ResponseEntity<>(new LoansResponse(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_HOME_LOAN_SCORING, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        	
        	primaryHomLoanDetail = primaryHomeLoanDetailRepository.getByApplication(applicationId);
        	if (CommonUtils.isObjectNullOrEmpty(primaryHomLoanDetail)) {
                logger.error(ERROR_WHILE_GETTING_RETAIL_APPLICANT_DETAIL_FOR_PERSONAL_LOAN_SCORING);
                return new ResponseEntity<>(new LoansResponse("Primary Detail Must Not be null While Calculating Home Loan Scoring", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        	CibilRequest cibilRequest = new CibilRequest();
            cibilRequest.setPan(coApplicantDetail.getPan());
            cibilRequest.setApplicationId(applicationId);
            try {
            	cibilResponse = cibilClient.getCibilScoreByPanCard(cibilRequest);
                cibilResponseDpdCoApp = cibilClient.getDPDLastXMonth(applicationId,coApplicantDetail.getPan());            	
            }catch(Exception e) {
            	logger.error("Error in Getting CIBIL infor like DPD and Score == >{}",e);
            }
        	
        	EligibilityResponse eligibilityResponse = null;
			try {
				eligibilityResponse = eligibilityClient.getMonthlyIncome(applicationId);
			} catch (EligibilityExceptions e) {
				logger.error("Error while Getting MonthlyIncome Details == >{}",e);
			}
            if (!com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse)
                    && !com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(eligibilityResponse.getData())){
                List incomeList = (List) eligibilityResponse.getData();
                if(!com.capitaworld.service.matchengine.utils.CommonUtils.isListNullOrEmpty(incomeList)){
                    netMonthlyIncome = Double.valueOf(incomeList.get(0).toString());
                    grossAnnualIncome = Double.valueOf(incomeList.get(8).toString());
                }
                logger.info("Net Monthly Income For ApplicationId======{}======>{}",applicationId,netMonthlyIncome);
                logger.info("Gross Annual Income For ApplicationId======{}======>{}",applicationId,grossAnnualIncome);
            }
            try {
                 ReportRequest reportRequest = new ReportRequest();
                 reportRequest.setApplicationId(applicationId);
                 reportRequest.setCoApplicantId(coApplicantId);
                 AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportByDirector(reportRequest);
                                                                                                   
            }catch(Exception e) {
            	logger.error("Error while getting Bank Statement Details");
            }
            totalEMI = financialArrangementDetailsService.getTotalEmiByApplicationIdSoftPing(coApplicantId,applicationId);
        }
        List<ScoringRequest> scoringRequestList=new ArrayList<>(scoringRequestLoansList.size());
        ScoreParameterRetailRequest scoreParameterRetailRequest = null;
//        HLEligibilityRequest hlEligibilityRequest = null;
        HomeLoanModelRequest homeLoanModelRequest = null;
        for(ScoringRequestLoans scoringRequestLoans : scoringRequestLoansList)
        {
            Long scoreModelId = scoringRequestLoans.getScoringModelId();
            Long fpProductId = scoringRequestLoans.getFpProductId();
            homeLoanModelRequest = homeLoanModelService.get(scoringRequestLoans.getLoanPurposeModelId(), null, null);
//            Integer minBankRelationshipInMonths = null;
//            orgId = scoringRequestLoans.getOrgId();
//            if(orgId != null) {
//            	BankList bankEnum = BankList.fromOrgId(orgId.toString());
//            	if(bankEnum != null) {
//            		minBankRelationshipInMonths = bankingRelationlRepository.getMinRelationshipInMonthByApplicationAndOrgName(applicationId, bankEnum.getName());	
//            	}
//            	logger.info("Min Banking Relationship in Month === >{}",minBankRelationshipInMonths);
//            }
            

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

            ///////// End  Getting Old Request ///////

            if (CommonUtils.isObjectNullOrEmpty(scoreParameterRetailRequest)) {
                scoreParameterRetailRequest= new ScoreParameterRetailRequest();
                setLoanPurposeModelFields(scoreParameterRetailRequest, homeLoanModelRequest);
                scoringRequest.setLoanPurposeModelId(homeLoanModelRequest.getId());
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
                        
                        scoreParameterRetailRequest.setLoanAmtProposed(scoringRequestLoans.getElAmountOnAverageScoring());
                        switch (modelParameterResponse.getName()) {
                        case ScoreParameter.Retail.HomeLoan.AGE:
                        	   try {
                                   if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate())) {
                                	   long yearsDiff = ChronoUnit.YEARS.between(coApplicantDetail.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),coApplicantDetail.getCreatedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                                	   logger.info("");
                                       scoreParameterRetailRequest.setAge((double)yearsDiff);
                                       scoreParameterRetailRequest.setAge_p(true);
                                   }
                               } catch (Exception e) {
                                   logger.error("error while getting AGE_HL parameter : ",e);
                               }
                        	break;
            			case ScoreParameter.Retail.HomeLoan.TOTAL_JOB_EXP:
            				try {
                                Double totalExperience = 0.0;
                                if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceYear())) {
                                	totalExperience += Double.valueOf(coApplicantDetail.getTotalExperienceYear());                                	
                                	logger.info("totalExperience Year {}===>{}",coApplicantDetail.getTotalExperienceYear());
                                }
                                

                                if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getTotalExperienceMonth())) {
                                	totalExperience += Double.valueOf(coApplicantDetail.getTotalExperienceMonth()) / 12;
                                	logger.info("totalExperience Year Month ===>{}",coApplicantDetail.getTotalExperienceMonth());
                                }
                                    
                                logger.info("totalExperience ===>{}",totalExperience);

                                scoreParameterRetailRequest.setWorkingExperience(totalExperience);
                                scoreParameterRetailRequest.setWorkingExperience_p(true);
                            } catch (Exception e) {
                                logger.error("error while getting TOTAL_JOB_EXP parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.CURRENT_JOB_EXP:
            				try {
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
                         } catch (Exception e) {
                             logger.error("error while getting CURRENT_JOB_EXP parameter : {}",e);
                         }
            				break;
            			case ScoreParameter.Retail.HomeLoan.TOTAL_BUSI_PROFE_EXP:
            				if(coApplicantDetail.getBusinessStartDate() != null) {
            					long diffYears = ChronoUnit.YEARS.between(coApplicantDetail.getBusinessStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            					logger.info("Total Business Experiance For HL CoApplicant==== > {}",diffYears);
            					scoreParameterRetailRequest.setTotalBusProfExperiance((int)diffYears);
            					scoreParameterRetailRequest.setIsTotalBusProfExperiance_p(true);
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.RESIDENCE_TYPE:
            				scoreParameterRetailRequest.setIsResidenceType_p(coApplicantDetail.getResidenceType() != null);
            				scoreParameterRetailRequest.setResidenceType(coApplicantDetail.getResidenceType());
            				break;
            			case ScoreParameter.Retail.HomeLoan.NO_YEARS_STAY_CURR_LOC:
            				try {
            					if(coApplicantDetail.getResidenceSinceYear() != null && coApplicantDetail.getResidenceSinceMonth() != null) {
            						Integer year = coApplicantDetail.getResidenceSinceYear();
    	                            Integer month = coApplicantDetail.getResidenceSinceMonth();
    	                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
    	                            String s = "01/" + month + "/" + year;
    	                            logger.info("Starting Date of Staying in Current Location For HL CoApplicant==== > {}",s);
    	                            double ceil = Math.ceil(CommonUtils.getAgeFromBirthDate(simpleDateFormat.parse(s)).doubleValue());
    	                            logger.info("No Of Years Staying in Current Location For HL==== > {}",ceil);
    	                            scoreParameterRetailRequest.setNoOfYearCurrentLocation(ceil);
    	                            scoreParameterRetailRequest.setIsNoOfYearCurrentLocation_p(true);            						
            					}
            				} catch (Exception e) {
	                            logger.error("error while getting NO_YEARS_STAY_CURR_LOC parameter : ", e);
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.BUREAU_SCORE:
            				Double cibilScore = null;
                            try {
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isObjectNullOrEmpty(cibilResponse.getScore())) {
                                	logger.info("Cibil Score Response For HL==== > {}",cibilResponse.getScore());
                                    cibilScore = Double.parseDouble(cibilResponse.getScore());
                                    scoreParameterRetailRequest.setCibilScore(cibilScore);
                                    scoreParameterRetailRequest.setCibilScore_p(true);
                                } 
                            } catch (Exception e) {
                                logger.error("error while getting BUREAU_SCORE parameter from CIBIL client : ",e);
                                scoreParameterRetailRequest.setCibilScore_p(false);
                            }
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
            				scoreParameterRetailRequest.setIsEmployementJobCat_p(coApplicantDetail.getEmploymentStatus() != null);
            				scoreParameterRetailRequest.setEmploymentTypeCatJob((coApplicantDetail.getEmploymentStatus() != null  ? coApplicantDetail.getEmploymentStatus() : null));
            				break;
            			case ScoreParameter.Retail.HomeLoan.EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED:
            		        scoreParameterRetailRequest.setIsEmployementTypeSelfEmpBus_p(coApplicantDetail.getEmploymentSubStatus() != null);
            		        scoreParameterRetailRequest.setEmploymentTypeSelfEmpBus((coApplicantDetail.getEmploymentSubStatus() != null  ? coApplicantDetail.getEmploymentSubStatus().longValue() : null));
            				break;
            			case ScoreParameter.Retail.HomeLoan.CURRENT_EMPLOYMENT_STATUS:
            				scoreParameterRetailRequest.setIsCurrentEmploymentStatus_p(coApplicantDetail.getCurrentEmploymentStatus() != null);
            				scoreParameterRetailRequest.setCurrentEmploymentStatus((coApplicantDetail.getCurrentEmploymentStatus() != null  ? coApplicantDetail.getCurrentEmploymentStatus().longValue() : null));
            				break;
            			case ScoreParameter.Retail.HomeLoan.MIN_BANKING_RELATIONSHIP:
            				//Not Available in Sheet Document
//            				scoreParameterRetailRequest.setIsMinBankingRelationship_p(minBankRelationshipInMonths != null);
//            				scoreParameterRetailRequest.setMinBankingRelationship(minBankRelationshipInMonths);
            				break;
            			case ScoreParameter.Retail.HomeLoan.NO_OF_DEPENDANTS:
            				try {
                                scoreParameterRetailRequest.setNumberOfDependents((coApplicantDetail.getNoDependent() != null ? coApplicantDetail.getNoDependent() : null));
                                scoreParameterRetailRequest.setNumberOfDependents_p(coApplicantDetail.getNoDependent() != null);
                            } catch (Exception e) {
                                logger.error("error while getting NO_OF_DEPENDANTS parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.ANNUAL_INCOME:
            				try {
            					if (scoringRequestLoans.getIsSetGrossNetIncome() != null && scoringRequestLoans.getIsSetGrossNetIncome()) {
            						if (scoringRequestLoans.getIncomeType() == null || scoringRequestLoans.getIncomeType() == 2) { // Net Monthly Income
            							scoreParameterRetailRequest.setNetAnnualIncome(netMonthlyIncome * 12);
            							scoreParameterRetailRequest.setNetAnnualIncome_p(true);
            							scoreParameterRetailRequest.setGrossAnnualIncome(null);
            						} else if (scoringRequestLoans.getIncomeType() == 1) { // Gross Monthly Income
            							scoreParameterRetailRequest.setGrossAnnualIncome(grossAnnualIncome * 12);
            							scoreParameterRetailRequest.setNetAnnualIncome(null);
            							scoreParameterRetailRequest.setNetAnnualIncome_p(false);
            						}
            					}else {
									logger.warn("Gross Or Net Income is Not Set By Lender ANNUAL_INCOME==== > {}",scoringRequestLoans.getIsSetGrossNetIncome());
								}
                            } catch (Exception e) {
                                logger.error("error while getting ANNUAL_INCOME parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVAILABLE_INCOME:
            				try {
								if(scoringRequestLoans.getElAmountBasedOnIncome() != null) {
										scoreParameterRetailRequest.setAvailableIncome(scoringRequestLoans.getElAmountBasedOnIncome());
										scoreParameterRetailRequest.setIsAvailableIncome_p(true);
								}else {
									logger.warn("Eligible Loan Amount Based on Income is not Set in AVAILABLE_INCOME TENURE==== > {}",scoringRequestLoans.getElAmountBasedOnIncome());
								}
							} catch (Exception e1) {
								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
							}
            				break;
            			case ScoreParameter.Retail.HomeLoan.TOIR:
            				try {
            					if (scoringRequestLoans.getIsSetGrossNetIncome() != null && scoringRequestLoans.getIsSetGrossNetIncome()) {
            						if (scoringRequestLoans.getIncomeType() == null || scoringRequestLoans.getIncomeType() == 2) { // Net Monthly Income
            							scoreParameterRetailRequest.setToir(scoringRequestLoans.getEmi() / netMonthlyIncome); 
            						} else if (scoringRequestLoans.getIncomeType() == 1) { // Gross Monthly Income
            							scoreParameterRetailRequest.setToir(scoringRequestLoans.getEmi() / grossAnnualIncome);
            						}
            						scoreParameterRetailRequest.setIsToir_p(true);
            					}else {
									logger.warn("Gross Or Net Income is Not Set By Lender TOIR==== > {}",scoringRequestLoans.getIsSetGrossNetIncome());
								}
                            } catch (Exception e) {
                                logger.error("error while getting TOIR parameter : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.ADDI_INCOME_SPOUSE:
            				//Not Available in Sheet Document
//	            				if(coApplicantDetail.get != null) {
//	            					scoreParameterRetailRequest.setSpouseIncome(coApplicantDetail.getAnnualIncomeOfSpouse());
//	            					scoreParameterRetailRequest.setIsSpouseIncome_p(true);
//	            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.MON_INCOME_DEPENDANT:
            				if (scoringRequestLoans.getIsSetGrossNetIncome() != null && scoringRequestLoans.getIsSetGrossNetIncome()) {
        						if (scoringRequestLoans.getIncomeType() == null || scoringRequestLoans.getIncomeType() == 2) { // Net Monthly Income
        							if (!CommonUtils.isObjectNullOrEmpty(netMonthlyIncome) && coApplicantDetail.getNoDependent() != null && coApplicantDetail.getNoDependent() != 0) {
                                        scoreParameterRetailRequest.setMonIncomePerDep(netMonthlyIncome / (coApplicantDetail.getNoDependent() + 1)); //1 is Applicant him/his self
                                    }else {
                                    	scoreParameterRetailRequest.setMonIncomePerDep(netMonthlyIncome / 1); //1 is Applicant him/his self
                                    }        							 
        						} else if (scoringRequestLoans.getIncomeType() == 1) { // Gross Monthly Income
        							if (!CommonUtils.isObjectNullOrEmpty(netMonthlyIncome) && coApplicantDetail.getNoDependent() != null && coApplicantDetail.getNoDependent() != 0) {
                                        scoreParameterRetailRequest.setMonIncomePerDep(grossAnnualIncome / (coApplicantDetail.getNoDependent() + 1)); //1 is Applicant him/his self
                                    }else {
                                    	scoreParameterRetailRequest.setMonIncomePerDep(grossAnnualIncome / 1); //1 is Applicant him/his self
                                    }
        						}
        						scoreParameterRetailRequest.setIsMonIncomePerDep_p(true);
        					}else {
								logger.warn("MON_INCOME_DEPENDANT is Not Set By Lender TOIR==== > {}",scoringRequestLoans.getIsSetGrossNetIncome());
							}
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVG_INCREASE_INCOME_REPORT_3_YEARS:
            				List<Double> incomeOfItrOf3YearsCoApplicant = loanRepository.getIncomeOfItrOf3YearsOfCoApplicant(coApplicantId);
            				logger.info("Income List From ITR for HL For CoApplicant == >{}",incomeOfItrOf3YearsCoApplicant);
            				if(!CommonUtils.isListNullOrEmpty(incomeOfItrOf3YearsCoApplicant)) {
            					if(incomeOfItrOf3YearsCoApplicant.size() == 3) { //as if now considering 3 Years Compulsory
            						Double itrLastToLastToLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 1);
            						if(itrLastToLastToLastYearIncome == null ) {
            							itrLastToLastToLastYearIncome = 0.0d;
            						}
                					Double itrLastToLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 2);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 0.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 3);
                					
                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 0.0;
                					}
            						Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
            						logger.info("Final Income After Calculation for HL == >{}",finalIncome);
            						scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
            						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            					}else if(incomeOfItrOf3YearsCoApplicant.size() == 2) { //as if now considering 2 Years Compulsory
            						Double itrLastToLastToLastYearIncome = 0.0d;
                					Double itrLastToLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 1);
                					if(itrLastToLastYearIncome == null) {
                						itrLastToLastYearIncome = 0.0d;
                					}
                					Double itrLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 2);
                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 0.0;
                					}
            						Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
            						logger.info("Final Income After Calculation for HL == >{}",finalIncome);
            						scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
            						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            					}else if(incomeOfItrOf3YearsCoApplicant.size() == 1) { //as if now considering 1 Years Compulsory
            						Double itrLastToLastToLastYearIncome = 0.0d;
                					Double itrLastToLastYearIncome = 0.0d;
                					Double itrLastYearIncome = incomeOfItrOf3YearsCoApplicant.get(incomeOfItrOf3YearsCoApplicant.size() - 1);
                					if(itrLastYearIncome == null) {
                						itrLastYearIncome = 0.0;
                					}
            						Double finalIncome =  ((((itrLastYearIncome - itrLastToLastYearIncome) / itrLastToLastYearIncome) * 100) +  (((itrLastToLastYearIncome - itrLastToLastToLastYearIncome) / itrLastToLastToLastYearIncome ) * 100)) / 2 ;
            						logger.info("Final Income After Calculation for HL == >{}",finalIncome);
            						scoreParameterRetailRequest.setIncomeFromItr(finalIncome);
            						scoreParameterRetailRequest.setIsIncomeFromItr_p(true);
            					}
            				}
            				break;
            			case ScoreParameter.Retail.HomeLoan.AVG_DEPOS_LAST_6_MONTH:
            				if(coApplicantBankStatementData != null && coApplicantBankStatementData.getSummaryInfo() != null && coApplicantBankStatementData.getSummaryInfo().getSummaryInfoAverageDetails() != null  && coApplicantBankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit() != null) {
	                             scoreParameterRetailRequest.setAvgOfTotalCheDepsitLast6Month(Double.valueOf(coApplicantBankStatementData.getSummaryInfo().getSummaryInfoAverageDetails().getTotalChqDeposit()));
	                             scoreParameterRetailRequest.setIsAvgOfTotalCheDepsitLast6Month_p(true);
       					 	}
            				
            				break;
            			case ScoreParameter.Retail.HomeLoan.CHECQUE_BOUNSE_LAST_1_MONTH:
            				 try {
            					 if(coApplicantBankStatementData != null && coApplicantBankStatementData.getCheckBounceForLast1Month() != null) {
            						 scoreParameterRetailRequest.setChequeBouncelast1Month(coApplicantBankStatementData.getCheckBounceForLast1Month().doubleValue());
                                     scoreParameterRetailRequest.setIsChequeBounceLast1Month_p(true);            						 
            					 }
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 1 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.HomeLoan.CHECQUE_BOUNSE_LAST_6_MONTH:
            				 try {
                               if(coApplicantBankStatementData != null && coApplicantBankStatementData.getCheckBounceForLast6Month() != null) {
                            	   scoreParameterRetailRequest.setChequeBounce(coApplicantBankStatementData.getCheckBounceForLast6Month().doubleValue());
                                   scoreParameterRetailRequest.setChequeBounce_p(true);  
                               }
            				 }catch(Exception e) {
            					 logger.error("Error while Getting Cheque Bounse of Last 6 Month");
            				 }
            				break;
            			case ScoreParameter.Retail.HomeLoan.DPD:
            				try {
                                if (!CommonUtils.isObjectNullOrEmpty(cibilResponseDpdCoApp) && !CommonUtils.isListNullOrEmpty(cibilResponseDpdCoApp.getListData())) {
                                    List<Integer> listDPD = (List<Integer>) cibilResponseDpdCoApp.getListData();
                                    Integer maxDPD = Collections.max(listDPD);
                                    logger.info("Max DPD Of CoApplicant===>{}",maxDPD);
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
                                logger.error("error while getting DPD parameter from CIBIL client : ",e);
                            }
            				break;
            			case ScoreParameter.Retail.HomeLoan.APPLICANT_NW_TO_LOAN_AMOUNT:
            				if(coApplicantDetail.getNetworth() != null) {
                				try {
									if(scoringRequestLoans.getElAmountOnAverageScoring() != null) {
										scoreParameterRetailRequest.setIsNetWorth_p(true);
										scoreParameterRetailRequest.setNetWorth((coApplicantDetail.getNetworth() / scoringRequestLoans.getElAmountOnAverageScoring()) * 100);
									}else {
										logger.warn("Eligible Loan Amount Based on Income is not Set in APPLICANT_NW_TO_LOAN_AMOUNT==== > {}",scoringRequestLoans.getElAmountOnAverageScoring());
									}
    							} catch (Exception e1) {
    								logger.error("Error while getting Eligibility Based On Income == >{}",e1);
    							}
            				}
            				break;
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
                List<Double> al = new ArrayList<Double>();
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

                                scoringParameterRequest.setDebt(debt);
                                scoringParameterRequest.setEquity(equity);
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

                                scoringParameterRequest.setTol(tol);
                                scoringParameterRequest.setTnw(tnw);
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

                                scoringParameterRequest.setAvgCurrentRatio(currentRatio);
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


                                scoringParameterRequest.setDebtorsDays(debtorsDays);
                                scoringParameterRequest.setAvgInventory(averageInventory);
                                scoringParameterRequest.setCogs(cogs);
                                scoringParameterRequest.setCreditorsDays(creditorsDays);
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
                                scoringParameterRequest.setTotalAsset(totalAsset);

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

    @Override
    public ResponseEntity<LoansResponse> calculateExistingBusinessScoringList(List<ScoringRequestLoans> scoringRequestLoansList) {

        ScoringResponse scoringResponseMain = null;

        List<ScoringRequest> scoringRequestList=new ArrayList<ScoringRequest>();

        ScoringParameterRequest scoringParameterRequest = null;

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

                                    scoringParameterRequest.setDebt(debt);
                                    scoringParameterRequest.setEquity(equity);
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

                                    scoringParameterRequest.setTol(tol);
                                    scoringParameterRequest.setTnw(tnw);
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

                                    scoringParameterRequest.setAvgCurrentRatio(currentRatio);
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


                                    scoringParameterRequest.setDebtorsDays(debtorsDays);
                                    scoringParameterRequest.setAvgInventory(averageInventory);
                                    scoringParameterRequest.setCogs(cogs);
                                    scoringParameterRequest.setCreditorsDays(creditorsDays);
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
                                    scoringParameterRequest.setTotalAsset(totalAsset);

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

                                    scoringParameterRequest.setLimitsInAccount(financialArrangementDetailsRepository.getExistingLimits(applicationId , loanTypeList ));

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

                                    scoringParameterRequest.setEbitda(operatingStatementDetailsTY.getOpProfitBeforeIntrest() + operatingStatementDetailsTY.getDepreciation());

                                    Double totalExistingLoanObligation=0.0;

                                    Double individualLoanObligation=financialArrangementDetailsRepository.getTotalEmiByApplicationId(applicationId);
                                    Double commercialLoanObligation=financialArrangementDetailsService.getTotalEmiOfAllDirByApplicationId(applicationId);
                                    if(!CommonUtils.isObjectNullOrEmpty(individualLoanObligation))
                                        totalExistingLoanObligation+=(individualLoanObligation*12);

                                    if(!CommonUtils.isObjectNullOrEmpty(commercialLoanObligation))
                                        totalExistingLoanObligation+=(commercialLoanObligation*12);

                                    scoringParameterRequest.setExistingLoanObligation(totalExistingLoanObligation);

                                    if(primaryCorporateDetail.getPurposeOfLoanId() == 1)
                                        scoringParameterRequest.setLoanType(2);
                                    else
                                        scoringParameterRequest.setLoanType(1);

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
                                    scoringParameterRequest.setExportSales(exportSales);
                                    scoringParameterRequest.setDomesticSales(domesticSales);
                                    scoringParameterRequest.setPastYearTurnover(domesticSales + exportSales);
                                } catch (Exception e) {
                                    logger.error("error while getting PAST_YEAR_TURNOVER parameter : ",e);
                                    scoringParameterRequest.setPastYearTurnover_p(false);
                                }
                                break;
                            }
                            case ScoreParameter.DEBT_EBITDA: {
                                try {

                                        //debt
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
                                }catch (Exception e){
                                    logger.error("error while getting DEBT_EBITDA parameter : ",e);
                                    scoringParameterRequest.setDebtEBITDA_p(false);
                                }
                                break;
                            }

                            case ScoreParameter.TURNOVER_ATNW: {
                                try {
                                        scoringParameterRequest.setLiabilitiesOrdinaryShareCapital(liabilitiesDetailsTY.getOrdinarySharesCapital());
                                        scoringParameterRequest.setLiabilitiesGeneralReserve(liabilitiesDetailsTY.getGeneralReserve());
                                        scoringParameterRequest.setDeficitInProfitANDLossAccount(liabilitiesDetailsTY.getSurplusOrDeficit());
                                        scoringParameterRequest.setLiabilitiesUnsecuredLoansFromPpromoters(liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromPromoters());
                                        scoringParameterRequest.setLiabilitiesUnsecuredLoansFromOthers(liabilitiesDetailsTY.getOtherNclUnsecuredLoansFromOther());
                                        scoringParameterRequest.setAssetsInvestmentsInSubsidiaryCosaffiliates(assetsDetailsTY.getInvestmentsInSubsidiary());
                                        scoringParameterRequest.setDomesticSales(operatingStatementDetailsTY.getDomesticSales());
                                        scoringParameterRequest.setExportSales(operatingStatementDetailsTY.getExportSales());
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

                            default: break;
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
}
