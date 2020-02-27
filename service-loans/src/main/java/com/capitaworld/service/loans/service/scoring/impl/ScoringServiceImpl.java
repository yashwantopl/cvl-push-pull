package com.capitaworld.service.loans.service.scoring.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.capitaworld.cibil.api.model.CibilRequest;
import com.capitaworld.cibil.api.model.CibilResponse;
import com.capitaworld.cibil.api.model.CibilScoreLogRequest;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.itr.api.model.ITRBasicDetailsResponse;
import com.capitaworld.itr.api.model.ITRConnectionResponse;
import com.capitaworld.itr.client.ITRClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
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
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetailMudraLoan;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoreParameterRequestLoans;
import com.capitaworld.service.loans.model.score.ScoringCibilRequest;
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
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailMudraLoanRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.BankingRelationlRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.BankBureauResponseService;
import com.capitaworld.service.loans.service.fundprovider.FSParameterMappingService;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.utils.scoreexcel.ScoreExcelFileGenerator;
import com.capitaworld.service.loans.utils.scoreexcel.ScoreExcelReader;
import com.capitaworld.service.matchengine.model.BankBureauRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.BankList;
import com.capitaworld.service.oneform.enums.FSParameterMst;
import com.capitaworld.service.oneform.exceptions.OneFormException;
import com.capitaworld.service.oneform.model.IrrBySectorAndSubSector;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.exception.RatingException;
import com.capitaworld.service.rating.model.IndustryResponse;
import com.capitaworld.service.rating.model.IrrRequest;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.FundSeekerInputRequest;
import com.capitaworld.service.scoring.model.GenericCheckerReqRes;
import com.capitaworld.service.scoring.model.ModelParameterResponse;
import com.capitaworld.service.scoring.model.ScoringParameterRequest;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;
import com.capitaworld.service.scoring.utils.ScoreParameter;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
@Transactional
@SuppressWarnings({"unchecked","unused"})
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
    private CorporateDirectorIncomeDetailsRepository corporateDirectorIncomeDetailsRepository;

    @Autowired
    private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;

    
    @Autowired
    private ITRClient itrClient;

    @Autowired
    private RetailApplicantDetailRepository retailApplicantDetailRepository;

    @Autowired
    private RatingClient ratingClient;

    @Autowired
    private OneFormClient oneFormClient;

    @Autowired
    private ScoringRequestDetailRepository scoringRequestDetailRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BankBureauResponseService bankBureauResponseService;
    
    @Autowired
    private FSParameterMappingService fsParameterMappingService;
    
    @Autowired
    private PrimaryCorporateDetailMudraLoanRepository primaryCorporateDetailMudraLoanRepository;
    
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
        return null;
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
    
	private void saveBureauScoringResponse(Map<String,Object> map,Long applicationId,Long fpProductId) {
    	BankBureauRequest bankBureauRequest = null;
//    	Map<String,Map<String,Object>>
    	for(Entry<String, Object> scoringSet : map.entrySet()) {
    		for(Entry<String, Map<String, Object>> fieldSet : ((Map<String,Map<String,Object>>)scoringSet.getValue()).entrySet()) {
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
    public ResponseEntity<LoansResponse> calculateMudraScoringList(List<ScoringRequestLoans> scoringRequestLoansList) {

        ScoringResponse scoringResponseMain = null;

        List<ScoringRequest> scoringRequestList=new ArrayList<ScoringRequest>();

        ScoringParameterRequest scoringParameterRequest = null;
        boolean isCibilCheck = false;
        boolean result = false;
		Boolean isBureauExistingLoansDisplayActive = false;
		Long applicationId = null;
        try {                                            
        	if(!scoringRequestLoansList.isEmpty()) {
        		logger.info("Enter in calculateExistingBusinessScoringList for check If Cibil API check or not");
        		applicationId = scoringRequestLoansList.get(0).getApplicationId();
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
        CibilResponse cibilResponseNillDPD = null;
		try {
			cibilResponseNillDPD = cibilClient.getDPDYears(applicationId);
		} catch (Exception e) {
			logger.error("Error while getting DPD years from CIBIL = >",e);
		}
     			
		Integer mainDirectorCibil = 0;
		DirectorBackgroundDetail mainDirectorBackgroundDetail = directorBackgroundDetailsRepository.findFirstByApplicationIdIdAndIsMainDirectorIsTrueAndIsActiveIsTrueOrderByIdDesc(applicationId);
		if(!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail)){
			try {
	             CibilResponse cibilResponse = cibilClient.getAllDirectorScore(applicationId);
	//             loanRepository.getAllDirectorAverageBureauScore(applicationId);
	             if(!CommonUtils.isObjectNullOrEmpty(cibilResponse) && !CommonUtils.isListNullOrEmpty(cibilResponse.getListData())){
	 				List<Map<String, Object>> cibilDirectorsResponseList = (List<Map<String, Object>>) cibilResponse.getListData();
	 				List<Integer> minBureauScoreList = new ArrayList<>(cibilDirectorsResponseList.size());
	 				for (int i = 0; i < cibilDirectorsResponseList.size(); i++) {
	 					CibilScoreLogRequest cibilScoreLogRequest = MultipleJSONObjectHelper.getObjectFromMap(cibilDirectorsResponseList.get(i),CibilScoreLogRequest.class);
	 					if(CommonUtils.isObjectNullOrEmpty(cibilScoreLogRequest) || CommonUtils.isObjectNullOrEmpty(cibilScoreLogRequest.getActualScore())){
	 						logger.warn("Score Not Found For ApplicationId  = >{}",applicationId);
	 						continue;
	 					}
	 					if(mainDirectorBackgroundDetail.getPanNo().equalsIgnoreCase(cibilScoreLogRequest.getPan())){
	 						if("000-1".equals(cibilScoreLogRequest.getActualScore())){
		 						cibilScoreLogRequest.setActualScore("-1"); // to Resolve Casting issue of 000-1 to -1
		 					}
		 					minBureauScoreList.add(Integer.parseInt(cibilScoreLogRequest.getActualScore()));	 						
	 					}
	 				}
	 				//Check Logic of Min Bureau Score
	 				if(!CommonUtils.isListNullOrEmpty(minBureauScoreList)){
	 					mainDirectorCibil = Collections.min(minBureauScoreList);
	 	 				logger.info("Minimum Bureau score For ApplicationId  = >{} and Score ===>{}",applicationId,mainDirectorCibil);
	 				}else{
	 					logger.warn("Score Not Found to find minimum For ApplicationId  = >{}",applicationId);
	 				}
	 			}
			} catch (Exception e) {
				logger.error("Error while getting Score from CIBIL = >",e);
			}
	}
		CibilResponse cibilResponseDPD = null;
		int maxDpd = 0;
		try {
			cibilResponseDPD = cibilClient.getDPDLastXMonth(applicationId);
			 if(!CommonUtils.isObjectNullOrEmpty(cibilResponseDPD) && !CommonUtils.isObjectNullOrEmpty(cibilResponseDPD.getListData())){
                 List<?> cibilDirectorsResponseList = cibilResponseDPD.getListData();
                 int commercialVal = 0;
                 for (int j = 0; j < cibilDirectorsResponseList.size(); j++) {
                     String cibilResponseObj = cibilDirectorsResponseList.get(j).toString();
                     if(cibilResponseObj.contains("|")){
                         String[] cibilDpdVal = cibilResponseObj.split(Pattern.quote("|"));
                         if(!CommonUtils.isObjectNullOrEmpty(cibilDpdVal[1]))
                             commercialVal = Integer.parseInt(cibilDpdVal[1]);
                     }else {
                         commercialVal = Integer.parseInt(cibilDirectorsResponseList.get(j).toString());
                     }
                     logger.info("commercialVal1::::::::::::::::::::::::::::::::::::::::::::::::::::::::{}",commercialVal);
                     if(maxDpd <= commercialVal){
                         maxDpd = commercialVal;
                     }
                     logger.info("maxDpd:::::::::::::::::::::::::::::::::::::::::::::::::::::::: {}",maxDpd);
                 }
             }
		} catch (Exception e) {
			logger.error("Error while getting Score from CIBIL = >",e);
		}
		
		AnalyzerResponse analyzerResponse = null;
		Double noOfChequeBounce1Month = 0.0;
		Double noOfChequeBounce6Month = 0.0;
		Double totalCredit = 0.0;
		Boolean isNoBankStatement = loanRepository.isNoBankStatement(applicationId);
        logger.info("isNoBankStatement ==>{}==>for ApplicationId===>{}",applicationId,isNoBankStatement);
        String noBankStatementBankName = null;
        if(isNoBankStatement){
        	String ifscCode = loanRepository.getIFSCByApplicationId(applicationId);
        	logger.info("Ifsc Code ==>{}==>for ApplicationId===>{}",applicationId,ifscCode);
        	if(!CommonUtils.isObjectNullOrEmpty(ifscCode)){
        		noBankStatementBankName = loanRepository.getBankNameByIFSC(ifscCode.substring(0,4));
        		logger.info("Bank Name ==>{}==>for ApplicationId===>{}",applicationId,noBankStatementBankName);
        	}
        	
        }
        if(isNoBankStatement){
        	noOfChequeBounce1Month = -1d;
    		noOfChequeBounce6Month = -1d;
        }else{
        	try {
    			ReportRequest reportRequest = new ReportRequest();
                reportRequest.setApplicationId(applicationId);
                reportRequest.setDirectorId(null);
    			analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
                if(!CommonUtils.isObjectNullOrEmpty(analyzerResponse) && !CommonUtils.isObjectNullOrEmpty(analyzerResponse.getData())){
                	int noOfMonths = 0;
                	Double totalCreditLast6Month = 0.0;
                	for(Object object : (List<?>)analyzerResponse.getData()) {
    					try {
    						Data data = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) object, Data.class);
    						if(!CommonUtils.isObjectNullOrEmpty(data)){
    	                		if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast1Month())) {
    	                            noOfChequeBounce1Month = noOfChequeBounce1Month + data.getCheckBounceForLast1Month().doubleValue();
    	                        }
    	                        if (!CommonUtils.isObjectNullOrEmpty(data.getCheckBounceForLast6Month())) {
    	                             noOfChequeBounce6Month = noOfChequeBounce6Month + data.getCheckBounceForLast6Month().doubleValue();
    	                        }                		
    	                        if(!CommonUtils.isObjectNullOrEmpty(data.getSummaryInfo().getSummaryInfoTotalDetails().getTotalCredit())){
    	                        	totalCreditLast6Month = totalCreditLast6Month + Double.parseDouble(data.getSummaryInfo().getSummaryInfoTotalDetails().getTotalCredit());
    	                        }
    	                        if(!CommonUtils.isListNullOrEmpty(data.getMonthlyDetailList().getMonthlyDetails())){
                                    noOfMonths = noOfMonths + data.getMonthlyDetailList().getMonthlyDetails().size();
                                }
    	                	}
    					}catch(Exception e) {
    						logger.error("Error While Casting Analyser object=====>{}====>{}",e);
    					}
    				}
                	logger.info("totalCreditLast6Month ==>{} = noOfMonths ==>{} for ApplicationId = >{}",totalCreditLast6Month,noOfMonths,applicationId);
                	if(totalCreditLast6Month > 0){
                		totalCredit = totalCreditLast6Month / noOfMonths;
                	}
                }
    		} catch (Exception e) {
    			logger.error("Error while getting Bank Statement Response = >",e);
    		}
        }
		
		
		ITRBasicDetailsResponse itrClientResponse = null;
		try {
			ITRBasicDetailsResponse arg0 = new ITRBasicDetailsResponse();
        	arg0.setApplicationId(applicationId);
        	itrClientResponse = itrClient.getAppOrCoAppBasicDetails(arg0);
		} catch (Exception e) {
			logger.error("Error while getting Bank Statement Response = >",e);
		}
		
		List<Integer> paraGovScheme = fsParameterMappingService.getParameters(applicationId, FSParameterMst.GOV_SCHEMES.getId() );
		List<Integer> parametersGovAuthorities = fsParameterMappingService.getParameters(applicationId, FSParameterMst.GOV_AUTHORITIES.getId());
//		List<BankingRelation> br = bankingRelationlRepository.listBankRelationAppId(applicationId);

		// start Get GST Parameter

        String gstNumber = corporateApplicantDetailRepository.getGstInByApplicationId(applicationId);
        Double loanAmount = primaryCorporateDetailRepository.getLoanAmountByApplication(applicationId);

        CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.getCorporateApplicantDetailByApplicationId(applicationId);
        IndustryResponse industryResponse =null;
        Integer businessTypeId= null;
        logger.info("corporateApplicantDetail.getKeyVerticalSubsector()=> {}", corporateApplicantDetail.getKeyVerticalSubsector());
        if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSector()) && !CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSubsector())) {
        	
        	IrrBySectorAndSubSector irr = new IrrBySectorAndSubSector();
			irr.setSectorId(corporateApplicantDetail.getKeyVerticalSector());
			irr.setSubSectorId(corporateApplicantDetail.getKeyVerticalSubsector());

			// IrrBySectorAndSubSector res
			// =(IrrBySectorAndSubSector)oneFormClient.getIrrBySectorAndSubSector(irr).getData();
			IrrBySectorAndSubSector res = null;
			try {
				res = (IrrBySectorAndSubSector) MultipleJSONObjectHelper.getObjectFromMap(
						(Map<String, Object>) oneFormClient.getIrrBySectorAndSubSector(irr).getData(),
						IrrBySectorAndSubSector.class);
				if(!CommonUtils.isObjectNullOrEmpty(res)){
				    IrrRequest irrIndustryRequest = new IrrRequest();
					irrIndustryRequest.setIrrIndustryId( res.getIrr());
					try {
						irrIndustryRequest = ratingClient.getIrrIndustry(irrIndustryRequest);
					} catch (RatingException e) {
						// TODO Auto-generated catch block
						logger.error("Error while Getting IRR Industry by IRR Client Details = >{}",e);
					}
					industryResponse = irrIndustryRequest.getIndustryResponse();
					businessTypeId = industryResponse.getBusinessTypeId();
					logger.info("::::::industryResponse.getBusinessTypeId()::::::"+industryResponse.getBusinessTypeId());
				}
			} catch (IOException | OneFormException e1) {
				// TODO Auto-generated catch block
				logger.error("Error while Getting IRR Details = >{}",e1);
			}
        }
        
        Double yearsInBusiness = null;
        if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)){
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
        }
        
        GstResponse gstResponse = null;
        GstCalculation gstCalculation = null;

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

        // end Get GST Parameter
        
        // Get Director Background detail
        Double age = 0.0d;
        if(!CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail) && !CommonUtils.isObjectNullOrEmpty(mainDirectorBackgroundDetail.getDob()) ){
        	age = Math.ceil(CommonUtils.getAgeFromBirthDate(mainDirectorBackgroundDetail.getDob()).doubleValue());
        }

        // get Primary Corporate Detail

        PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);

        // Primary Corporate details for Mudra loans
        
        PrimaryCorporateDetailMudraLoan corporateDetailMudraLoan  = primaryCorporateDetailMudraLoanRepository.findFirstByApplicationIdAndApplicationProposalMappingProposalIdIsNullOrderByIdDesc(applicationId);
        // GET SCORE CORPORATE LOAN PARAMETERS
        
        int currentYear = getFinYear(applicationId);
        if (CommonUtils.isObjectNullOrEmpty(currentYear)) {
            logger.error("error while getting current year from itr");
            LoansResponse loansResponse = new LoansResponse("error while getting current year from itr.", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
		
     // CMA
        OperatingStatementDetails operatingStatementDetailsFY = null;
        OperatingStatementDetails operatingStatementDetailsSY = null;
        OperatingStatementDetails operatingStatementDetailsTY = null;


        LiabilitiesDetails liabilitiesDetailsFY = null;
        LiabilitiesDetails liabilitiesDetailsSY = null;
        LiabilitiesDetails liabilitiesDetailsTY = null;

        AssetsDetails assetsDetailsFY = null;
        AssetsDetails assetsDetailsSY = null;
        AssetsDetails assetsDetailsTY = null;

            operatingStatementDetailsTY = operatingStatementDetailsRepository.findByLoanApplicationMasterIdAndYearAndApplicationProposalMappingIsNullAndIsActiveIsTrue(applicationId, currentYear - 1 + "");
            operatingStatementDetailsSY = operatingStatementDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 2 + "");
            operatingStatementDetailsFY = operatingStatementDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 3 + "");

            liabilitiesDetailsTY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
            liabilitiesDetailsSY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 2 + "");
            liabilitiesDetailsFY = liabilitiesDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 3 + "");

            assetsDetailsTY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 1 + "");
            assetsDetailsSY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 2 + "");
            assetsDetailsFY = assetsDetailsRepository.getByApplicationIdAndYearAndProposalIdNULL(applicationId, currentYear - 3 + "");
        
        for(ScoringRequestLoans scoringRequestLoans:scoringRequestLoansList)
        {
        	Integer minBankRelationshipInMonths = null;
        	if(scoringRequestLoans.getOrgId() != null) {
              	BankList bankEnum = BankList.fromOrgId(scoringRequestLoans.getOrgId().toString());
            	if(isNoBankStatement) {
            		if(!CommonUtils.isObjectNullOrEmpty(bankEnum) && !CommonUtils.isObjectNullOrEmpty(bankEnum.getName())) {
            			logger.info("Enum Bank Name = >{} and DB Bank Name = >{}",bankEnum.getName(),noBankStatementBankName);
            			if(bankEnum.getName().equalsIgnoreCase(noBankStatementBankName)){
            				minBankRelationshipInMonths = loanRepository.getMinRelationshipInMonthByApplicationId(applicationId);
            			}else{
            				minBankRelationshipInMonths = -1;
            			}
            			logger.info("Min Banking Relationship in Month when no Bank statement === >{}",minBankRelationshipInMonths);
            		}
            	}else {
            		if(bankEnum != null) {
                  		logger.info("Bank Name====>{}==>Application Id===>{}===> Fp Product Id===>{}",bankEnum.getName(),applicationId,scoringRequestLoans.getFpProductId());
                  		minBankRelationshipInMonths = bankingRelationlRepository.getMinRelationshipInMonthByApplicationAndOrgName(applicationId, bankEnum.getName());
                  		if(CommonUtils.isObjectNullOrEmpty(minBankRelationshipInMonths)){
                    		minBankRelationshipInMonths = -1;
                    	}
                  	}
                  	logger.info("Min Banking Relationship in Month when Upload Bank statement === >{}",minBankRelationshipInMonths);
            	}
              }
        	
        	if(CommonUtils.isObjectNullOrEmpty(minBankRelationshipInMonths)){
        		minBankRelationshipInMonths = 0;
        	}
            Long scoreModelId = scoringRequestLoans.getScoringModelId();
            Long fpProductId = scoringRequestLoans.getFpProductId();

            ScoringRequest scoringRequest = new ScoringRequest();
            scoringRequest.setScoringModelId(scoreModelId);
            scoringRequest.setFpProductId(fpProductId);
            scoringRequest.setApplicationId(applicationId);
            scoringRequest.setUserId(scoringRequestLoans.getUserId());
            scoringRequest.setBusinessTypeId(ScoreParameter.BusinessType.MUDRA_LOAN);
            scoringRequest.setEligibleLoanAmountCircular(scoringRequestLoans.getEligibleLoanAmountCircular());
            scoringRequest.setMap(scoringRequestLoans.getMapList());
			

                if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFinancialTypeIdProduct())) {
                    scoringRequest.setFinancialTypeId(ScoreParameter.FinancialType.THREE_YEAR_ITR);
                } else {
                    scoringRequest.setFinancialTypeId(scoringRequestLoans.getFinancialTypeIdProduct());
                }


                logger.info("Financial Type Id ::::::::::::::::================>" + scoringRequest.getFinancialTypeId());

                scoringParameterRequest=new ScoringParameterRequest();
                scoringParameterRequest.setDpd(maxDpd);
                scoringParameterRequest.setPaymentRecordsWithLenders_p(true);

                logger.info("Scoring Data Fetched First Time  =====> " + applicationId);

                logger.info("----------------------------START EXISTING LOAN ------------------------------");

                logger.info(MSG_APPLICATION_ID + applicationId + MSG_FP_PRODUCT_ID + fpProductId + MSG_SCORING_MODEL_ID + scoreModelId);
                
                if (!CommonUtils.isObjectNullOrEmpty(scoreModelId)) {
                    // GET ALL FIELDS FOR CALCULATE SCORE BY MODEL ID
                    ScoringResponse scoringResponse = null;
                    try {
                        scoringResponse = scoringClient.listFieldByBusinessTypeId(scoringRequest);
                    } catch (Exception e) {
                        logger.error(ERROR_WHILE_GETTING_FIELD_LIST,e);
                    }
                    if(CommonUtils.isObjectNullOrEmpty(scoringResponse) || CommonUtils.isListNullOrEmpty(scoringResponse.getDataList())){
                    	logger.warn("No Scoring Response Found for ApplicationId = >{}",applicationId);
                    	continue;
                    }

                    List<Map<String, Object>> dataList = (List<Map<String, Object>>) scoringResponse.getDataList();

                    //List<FundSeekerInputRequest> fundSeekerInputRequestList = new ArrayList<>(dataList.size());

                    logger.info("dataList=====================================>>>>>>>>>>>>>>>>>>>>>>" + dataList.size());

                    for (int i = 0; i < dataList.size(); i++){

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

                            case ScoreParameter.MudraLoan.CUSTOMER_ASSOCIATE_CONCERN_ML: {
                            	if(!isCibilCheck) {
                            		Double customer_ass_concern_year = null;
                                    try {
                                        if (!CommonUtils.isObjectNullOrEmpty(cibilResponseNillDPD) && !CommonUtils.isObjectNullOrEmpty(cibilResponseNillDPD.getData())) {
                                            customer_ass_concern_year = Double.parseDouble(cibilResponseNillDPD.getData().toString());

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
                            case ScoreParameter.MudraLoan.CIBIL_TRANSUNION_SCORE_ML: {
                            	if(!isCibilCheck) {
                                    try {
                                        if (!CommonUtils.isObjectNullOrEmpty(mainDirectorCibil)) {
                                            scoringParameterRequest.setCibilTransuniunScore(mainDirectorCibil.doubleValue());
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
                            
                            case ScoreParameter.MudraLoan.AGE_ML: {
                                try {
                                    scoringParameterRequest.setAge(age);
                                    scoringParameterRequest.setAge_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting AGE parameter : ",e);
                                    scoringParameterRequest.setAge_p(false);
                                }
                                break;
                            }
                            
                            case ScoreParameter.MudraLoan.OWNING_HOUSE_ML: {

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
                            
                            case ScoreParameter.MudraLoan.ACADEMIC_QUALIFICATION_ML: {

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
                            
                            case ScoreParameter.MudraLoan.EXPERIENCE_IN_THE_LINE_OF_TRADE_ML: {

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

                            case ScoreParameter.MudraLoan.ASSESSED_FOR_INCOME_TAX_ML: {

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
                            case ScoreParameter.MudraLoan.PAYMENT_RECORDS_WITH_LENDERS_ML: {
                            	if(!isCibilCheck) {
                            		  try {
                                    	  scoringParameterRequest.setDpd(maxDpd);
                                          scoringParameterRequest.setPaymentRecordsWithLenders_p(true);
                                      } catch (Exception e) {
                                          logger.error("error while getting PAYMENT_RECORDS_WITH_LENDERS parameter : ", e);
                                          scoringParameterRequest.setPaymentRecordsWithLenders_p(false);
                                      }
                            	}
                                break;
                            }
                            
                            case ScoreParameter.MudraLoan.ID_PROOF_ML: {

                            	if(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getIdProof()!=null) {
                            	scoringParameterRequest.setIdProof(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getIdProof());
                            	scoringParameterRequest.setIdProof_p(true);
                            	}
                            	else {
									scoringParameterRequest.setIdProof_p(false);
								}
                            	break;
                            }
                            case ScoreParameter.MudraLoan.NUMBER_OF_DEPENDENTS_ML: {
                            	if(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getDependent()!=null) {
                            		scoringParameterRequest.setNoOfDependents(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getDependent());
                            		scoringParameterRequest.setNoOfDependents_p(true);
                            	}
                            	else {
                            		scoringParameterRequest.setNoOfDependents_p(false);
                            	}
                            	break;
                            }
                            case ScoreParameter.MudraLoan.RESIDING_AT_THE_SAME_ADDRESS_ML: {
                            	logger.info("RESIDING_AT_THE_SAME_ADDRESS_ML Table data ::" +mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAddressYears());
                            	if(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAddressYears()!=null) {
                            		scoringParameterRequest.setAddressYear(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getAddressYears());
                            		scoringParameterRequest.setAddressYear_p(true);
                            	}
                            	else {

                            		scoringParameterRequest.setAddressYear(2);
                            		scoringParameterRequest.setAddressYear_p(true);
                            	}
                            	logger.info("RESIDING_AT_THE_SAME_ADDRESS_ML :: "+scoringParameterRequest.getAddressYear());
                            	logger.info("RESIDING_AT_THE_SAME_ADDRESS_ML :: "+scoringParameterRequest.getAddressYear_p());
                            	break;
                            }
                            case ScoreParameter.MudraLoan.CERTIFICATION_ML: {
                            		if(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getCertificationCourse()!=null) {
                            			scoringParameterRequest.setCertification_p(true);
                            			scoringParameterRequest.setCertification(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getCertificationCourse());
                            		}
                            		else {
                            			scoringParameterRequest.setCertification_p(false);
                            		}
                            	
                            	break;
                            }
                            case ScoreParameter.MudraLoan.MAIN_DIRECTOR_CATEGORY_ML: {
                            	logger.info("corporateApplicantDetail.getCastCategory():::::::"+ corporateApplicantDetail.getCastCategory());
                            	if(corporateApplicantDetail.getCastCategory()!=null) {
                            		scoringParameterRequest.setCastCategory(corporateApplicantDetail.getCastCategory());
                            		scoringParameterRequest.setCastCategory_p(true);
                            	}
                            	else {
                            		scoringParameterRequest.setCastCategory_p(false);
                            	}
                            	
                            	break;
                            }
                            case ScoreParameter.MudraLoan.COVERED_UNDER_DIFF_SCHEMES_ML: {
                            	if(paraGovScheme!=null && !paraGovScheme.isEmpty()) {
                            		scoringParameterRequest.setParameters(paraGovScheme);
                            		scoringParameterRequest.setParameters_p(true);
                            	}
                            	else {
                            		scoringParameterRequest.setParameters_p(false);
                            	}
                            	
                            	break;
                            }
                            case ScoreParameter.MudraLoan.OTHER_SOURCE_OF_INCOME_ML: {
                            	if(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOtherIncomeSource()!=null) {
                            		scoringParameterRequest.setOtherSourceOfIncome(mainDirectorBackgroundDetail.getDirectorPersonalDetail().getOtherIncomeSource());
                            		scoringParameterRequest.setOtherSourceOfIncome_p(true);
                            	}
                            	else {
                            		scoringParameterRequest.setOtherSourceOfIncome_p(false);
                            	}
                            	
                            	break;
                            }
                            
                            case ScoreParameter.MudraLoan.TOL_TNW_ML: {

                                try {
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
                            
                            case ScoreParameter.MudraLoan.TENURE_ML: {
                            	logger.info("scoringRequestLoans.getTenureFS() :: "+ scoringRequestLoans.getTenureFS());
                            	if(scoringRequestLoans.getTenureFS() != null) {
                            		scoringParameterRequest.setTenure(scoringRequestLoans.getTenureFS());
                            		scoringParameterRequest.setTenure_p(true);
                            	}
                            	else {
                            		scoringParameterRequest.setTenure_p(false);
                            	}
                            	break;
                            }
                            
                            case ScoreParameter.MudraLoan.PAST_YEAR_TURNOVER_ML: {

                                try {
                                	logger.info("operatingStatementDetailsTY.getNetSales() :: {}",operatingStatementDetailsTY.getNetSales());
                                    Double domesticSales = operatingStatementDetailsTY.getDomesticSales();
                                    Double exportSales = operatingStatementDetailsTY.getExportSales();
                                    scoringParameterRequest.setPastYearTurnover_p(true);
                                    scoringParameterRequest.setExportSalesTY(exportSales);
                                    scoringParameterRequest.setDomesticSalesTY(domesticSales);
                                    scoringParameterRequest.setPastYearTurnover(domesticSales + exportSales);
                                    scoringParameterRequest.setSales(operatingStatementDetailsTY.getNetSales());
                                    scoringParameterRequest.setSales_p(true);
                                } catch (Exception e) {
                                    logger.error("error while getting PAST_YEAR_TURNOVER parameter : ",e);
                                    scoringParameterRequest.setPastYearTurnover_p(false);
                                    scoringParameterRequest.setSales_p(false);
                                }
                                break;
                            }
                            
                            case ScoreParameter.MudraLoan.PAT_NET_SALES_RATIO_ML: {
                                try {

                                    Object[] itrResponse = moveAheadFromItr(applicationId);
                                    Integer itrType = CommonUtils.isObjectNullOrEmpty(itrResponse[1]) ? null : Integer.parseInt(itrResponse[1].toString());

                                    if(itrType !=null) {
                                    	logger.info("operatingStatementDetailsTY.getNetSales() :: {}" ,operatingStatementDetailsTY.getNetSales());
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
                            
                            case ScoreParameter.MudraLoan.NO_OF_CUSTOMER_ML: {
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
                            
                            case ScoreParameter.MudraLoan.FACTORY_PREMISES_ML: {
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
                            case ScoreParameter.MudraLoan.YEARS_IN_BUSINESS_ML: {
                                try {
                                	if(!CommonUtils.isObjectNullOrEmpty(yearsInBusiness)){
                                		scoringParameterRequest.setYearsInBusiness(yearsInBusiness);
                                        scoringParameterRequest.setYearsInBusiness_p(true);                                		
                                	}else{
                                		scoringParameterRequest.setYearsInBusiness_p(false);
                                	}
                                } catch (Exception e) {
                                    logger.error("error while getting YEARS_IN_BUSINESS parameter : ",e);
                                    scoringParameterRequest.setYearsInBusiness_p(false);
                                }
                                break;
                            }
                            
                            case ScoreParameter.MudraLoan.NO_OF_CHEQUES_BOUNCED_ML: {
                                try{
                                	scoringParameterRequest.setNoOfChequesBouncedLastMonth(noOfChequeBounce1Month);
                                    scoringParameterRequest.setChequesBouncedLastMonth_p(true);
                                }catch (Exception e){
                                    logger.error("error while getting NO_OF_CHEQUES_BOUNCED parameter : ",e);
                                    scoringParameterRequest.setChequesBouncedLastMonth_p(false);
                                }
                                break;
                            }

                            case ScoreParameter.MudraLoan.NO_OF_CHEQUES_BOUNCED_LAST_SIX_MONTH_ML: {
                                try{
                                	scoringParameterRequest.setNoOfChequesBouncedLastSixMonth(noOfChequeBounce6Month);
                                    scoringParameterRequest.setChequesBouncedLastSixMonth_p(true);
                                }catch (Exception e){
                                    logger.error("error while getting NO_OF_CHEQUES_BOUNCED_LAST_SIX_MONTH parameter : ",e);
                                    scoringParameterRequest.setChequesBouncedLastSixMonth_p(false);
                                }
                                break;
                            }
                            
                            case ScoreParameter.MudraLoan.UDYOG_AADHAR_ML: {
                            	if(corporateApplicantDetail.getAadhar()!=null) {
                            	scoringParameterRequest.setAadhar(1L);
                            	scoringParameterRequest.setAadhar_p(true);
                            	}	
                            	else {
                            		scoringParameterRequest.setAadhar(2L);
                            		scoringParameterRequest.setAadhar_p(true);
                            	}
                            	
                                break;
                            }
                            case ScoreParameter.MudraLoan.ITR_RETURN_FILED_ML: {
                            	scoringParameterRequest.setIsItReturnFiled_p(true);
                            	if(!CommonUtils.isObjectNullOrEmpty(itrClientResponse)){
                            		if(CommonUtils.isObjectNullOrEmpty(itrClientResponse.getIsFilledManual())){
                            			itrClientResponse.setIsFilledManual(false); // Assuming that 
                            		}
                            		
                            		if(!itrClientResponse.getIsFilledManual()) {
                            			scoringParameterRequest.setItReturnFiledId(1); // Value is YES                        			
                                	} else {
                                		// Manual Filed
                                		if(isNoBankStatement){
                                			scoringParameterRequest.setItReturnFiledId(2); // Value is NO	
                                		}else{
                                			// In Completed code. We will change when GST Client is Ready to return sales of last 6 month.
                                			scoringParameterRequest.setItReturnFiledId(3);
                                		}
                                	}
                            	}
                            	
                                 break;
                             }
                            
                            case ScoreParameter.MudraLoan.TYPE_OF_ACTIVITY_ML: {
        						if (!CommonUtils.isObjectNullOrEmpty(industryResponse)) {
        							scoringParameterRequest.setTypeOfActivity_p(true);
        							scoringParameterRequest.setTypeOfActivity(businessTypeId);
        						}
        						else {
            						scoringParameterRequest.setTypeOfActivity_p(false);
            					}

                                break;
                            }
                            case ScoreParameter.MudraLoan.RELATIONSHIP_WITH_BANK_ML: {
                            	try {
                            		logger.info("Relationship With Bank :: "+ minBankRelationshipInMonths);
                            		scoringParameterRequest.setBankRelation_p(true);
                            		scoringParameterRequest.setBankRelation(minBankRelationshipInMonths.longValue());
                            	}
                            	catch (Exception e) {
                            		e.printStackTrace();
                            		logger.info("in Caatch");
                            		scoringParameterRequest.setBankRelation_p(false);
								}
                            	logger.info("Relationship With Bank :: "+scoringParameterRequest.getBankRelation_p());
                            	logger.info("Relationship With Bank Data :: "+scoringParameterRequest.getBankRelation());
                                 break;
                             }
                            case ScoreParameter.MudraLoan.MARKETING_ARRANGEMENT_FOR_FINISHED_GOODS_ML: {
                            	if(!CommonUtils.isObjectNullOrEmpty(corporateDetailMudraLoan) && !CommonUtils.isObjectNullOrEmpty(corporateDetailMudraLoan.getMrktArragementFinishedGoods()) ) {
                            		scoringParameterRequest.setMarketingArrangmentForFinishedGoods(corporateDetailMudraLoan.getMrktArragementFinishedGoods());
                            		scoringParameterRequest.setMarketingArrangmentForFinishedGoods_p(true);
                            	}
                            	else {
                            		scoringParameterRequest.setMarketingArrangmentForFinishedGoods_p(false);
                            	}
                            	
                                 break;
                             }
                            case ScoreParameter.MudraLoan.REGISTRATION_WITH_GOVERNMENT_AUTHORITIES_ML: {
                            	if(parametersGovAuthorities!=null && !parametersGovAuthorities.isEmpty()) {
                            		scoringParameterRequest.setRegistrationWithGovernmentAuthorities(parametersGovAuthorities);
                            		scoringParameterRequest.setRegistrationWithGovernmentAuthorities_p(true);
                            	}
                            	else {
                            		scoringParameterRequest.setRegistrationWithGovernmentAuthorities_p(false);
                            	}
                                 break;
                             }
                            case ScoreParameter.MudraLoan.BUSINESS_PROSPECTS_OF_THE_ACTIVITY_TO_BE_UNDERTAKEN_ML: {
                            	if(corporateApplicantDetail.getBusinessProspects()!=null) {
                            		scoringParameterRequest.setBusinessProspects(corporateApplicantDetail.getBusinessProspects());
                            		scoringParameterRequest.setBusinessProspects_p(true);
                            	}
                            	else {
                            		scoringParameterRequest.setBusinessProspects_p(false);
                            	}
                                 break;
                             }
                            case ScoreParameter.MudraLoan.ACCESS_TO_INPUTS_ML: {
                            	
                            	logger.info("corporateApplicantDetail.getAccessInput() : " +corporateApplicantDetail.getAccessInput());
                            	if(corporateApplicantDetail.getAccessInput()!=null) {
                            		scoringParameterRequest.setAccessInputs(corporateApplicantDetail.getAccessInput());
                            		scoringParameterRequest.setAccessInputs_p(true);
                            	}
                            	else {
                            		scoringParameterRequest.setAccessInputs_p(false);
                            	}
                            	logger.info("AccessInputs :" + scoringParameterRequest.getAccessInputs());
                                 break;
                             }
                            
                            case ScoreParameter.MudraLoan.CREDIT_SUMMATION_ML:{
                            	if(!isNoBankStatement){
                            		Double projctedSales = null;
                            		if(!CommonUtils.isObjectNullOrEmpty(gstCalculation)){
                            			if(!CommonUtils.isObjectNullOrEmpty(gstCalculation.getHistoricalSales())) {
                                            projctedSales = gstCalculation.getHistoricalSales()/12;
                                        }else{
                                            projctedSales = gstCalculation.getProjectedSales()/12;
                                        }
                                        logger.info("projctedSales = >{} for ApplicationId = >{}",projctedSales,applicationId);
                                        logger.info("totalCredit = >{} for ApplicationId = >{}",totalCredit,applicationId);
                                        if (!(CommonUtils.isObjectNullOrEmpty(projctedSales) || projctedSales == 0.0)) {
                                        	scoringParameterRequest.setCreditSummation((totalCredit / (projctedSales / 12)) * 100);
        								} else {
        									scoringParameterRequest.setCreditSummation(0.0);
        								}                            			
                            		}else{
                            			scoringParameterRequest.setCreditSummation_p(false);
                            		}
                            	}else{
                            		scoringParameterRequest.setCreditSummation(-1d);
                            	}
                                scoringParameterRequest.setCreditSummation_p(true);
                            }
                            default:
                            	break;
                        }

                        //fundSeekerInputRequestList.add(fundSeekerInputRequest);
                    }

//                    logger.info(MSG_SCORE_PARAMETER + scoringParameterRequest.toString());

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
	public List<GenericCheckerReqRes> sendToCheckerEBLR(List<GenericCheckerReqRes> genericCheckerReqResList,
			Long userId) throws ScoringException {
		return scoringClient.sendToCheckerEBLR(genericCheckerReqResList, userId);
	}

	@Override
	public List<GenericCheckerReqRes> sendToChecker(List<GenericCheckerReqRes> genericCheckerReqResList, Long userId) throws ScoringException {
		   return scoringClient.sendToChecker(genericCheckerReqResList, userId);
	}
}
