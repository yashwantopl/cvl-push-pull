/**
 * 
 */
package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.NtbPrimaryViewResponse;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
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
import com.capitaworld.service.oneform.enums.ProposedConstitutionOfUnitNTB;
import com.capitaworld.service.oneform.enums.ProposedDetailOfUnitNTB;
import com.capitaworld.service.oneform.enums.ResidenceStatusRetailMst;
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
public class NtbTeaserViewServiceImpl implements NtbTeaserViewService{

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
		
	    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	    DecimalFormat decim = new DecimalFormat("#,###.00");
		/* (non-Javadoc)
		 * @see com.capitaworld.service.loans.service.teaser.primaryview.NtbTeaserViewService#getNtbTeaserViewDetails(java.lang.Long, java.lang.Integer, java.lang.Long, java.lang.Boolean)
		 */
		@Override
		public NtbPrimaryViewResponse getNtbTeaserViewDetails(Long toApplicationId, Integer userType, Long userId, Long productMappingId, Boolean isFinal) {

			  NtbPrimaryViewResponse ntbPrimaryViewRespone = new NtbPrimaryViewResponse();
		      LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(toApplicationId);
		      ntbPrimaryViewRespone.setProductId(loanApplicationMaster.getProductId());
//		      ntbPrimaryViewRespone.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
		      

		        /*========= Matches Data ==========*/
		        if (userType != null) {
		            if (!(CommonUtils.UserType.FUND_SEEKER == userType)) {//TEASER VIEW FROM FP SIDE 
		            try {
		                    MatchRequest matchRequest = new MatchRequest();
		                    matchRequest.setApplicationId(toApplicationId);
		                    matchRequest.setProductId(productMappingId);
		                    matchRequest.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
		                    MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfCorporate(matchRequest);
		                    ntbPrimaryViewRespone.setMatchesList(matchResponse.getMatchDisplayObjectList());
		            } catch (Exception e) {
		            	logger.info("Error while getting matches data"+e);
		                    e.printStackTrace();
		             }
		           }
		       }
		        //DIRECTOR BACKGROUND DETAILS
		        
				try {
					List<Map<String, Object>> directorBackgroundDetails = corporateDirectorIncomeService
							.getDirectorBackGroundDetails(toApplicationId);
					ntbPrimaryViewRespone.setDirectorBackGroundDetails(directorBackgroundDetails);

				} catch (Exception e) {
					logger.error("Problem to get Data of Director's Background=========> {}", e);
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
					if(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getProposedConstitutionOfUnit())) {
						ProposedConstitutionOfUnitNTB byIdProCons = ProposedConstitutionOfUnitNTB.getById(fundSeekerInputRequestResponse.getProposedConstitutionOfUnit());
						fundSeekerInputRequestResponse.setProposedConstitutionOfUnit(CommonUtils.isObjectNullOrEmpty(byIdProCons) ? Integer.valueOf(byIdProCons.getValue()) : null);
					}
					if(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getProposedDetailsOfUnit())) {
						ProposedDetailOfUnitNTB byIdProConsDet = ProposedDetailOfUnitNTB.getById(fundSeekerInputRequestResponse.getProposedDetailsOfUnit());
						fundSeekerInputRequestResponse.setProposedDetailsOfUnit(CommonUtils.isObjectNullOrEmpty(byIdProConsDet) ? Integer.valueOf(byIdProConsDet.getValue()) : null);
					}
					ntbPrimaryViewRespone.setOtherDetails(fundSeekerInputRequestResponse);

				} catch (Exception e) {
					logger.error("Problem to get Other Details===========> {}", e);
				}

		

		        
	           /* try {
	                List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = directorBackgroundDetailsService.getDirectorBackgroundDetailList(toApplicationId, userId);
	                List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponseList = new ArrayList<>();
	                for (DirectorBackgroundDetailRequest directorBackgroundDetailRequest : directorBackgroundDetailRequestList) {
	                    DirectorBackgroundDetailResponse directorBackgroundDetailResponse = new DirectorBackgroundDetailResponse();
	                    directorBackgroundDetailResponse.setAddress(directorBackgroundDetailRequest.getAddress());
	                    directorBackgroundDetailResponse.setDirectorsName((directorBackgroundDetailRequest.getSalutationId() != null ? Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue() : null )+ " " + directorBackgroundDetailRequest.getDirectorsName());
	                    directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo().toUpperCase());
	                    directorBackgroundDetailResponse.setAadhar(directorBackgroundDetailRequest.getAadhar());
	                    String directorName = "";
	                    if (directorBackgroundDetailRequest.getSalutationId() != null){
	                        directorName = Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue();
	                    }
	                    directorName += " "+directorBackgroundDetailRequest.getDirectorsName();
	                    directorBackgroundDetailResponse.setDirectorsName(directorName);
	                    directorBackgroundDetailResponse.setQualification(directorBackgroundDetailRequest.getQualificationId()!= null ? EducationQualificationNTB.getById(directorBackgroundDetailRequest.getQualificationId()).getValue() : null);
	                    directorBackgroundDetailResponse.setMaritalStatus(directorBackgroundDetailRequest.getMaritalStatus()!= null ? MaritalStatus.getById(directorBackgroundDetailRequest.getMaritalStatus()).getValue() : null);
	                    directorBackgroundDetailResponse.setNoOfDependent(directorBackgroundDetailRequest.getNoOfDependent().toString());
	                  //directorBackgroundDetailResponse.setResidenceType(directorBackgroundDetailRequest.getResidenceType());
	                  //directorBackgroundDetailResponse.setResidenceSince(directorBackgroundDetailRequest.getResidenceSinceYear()!= null ? reside);
	                    directorBackgroundDetailResponse.setTotalExperience(directorBackgroundDetailRequest.getTotalExperience().toString()+" Years");
	                    directorBackgroundDetailResponse.setNetworth(directorBackgroundDetailRequest.getNetworth().toString());
	                    directorBackgroundDetailResponse.setDesignation(directorBackgroundDetailRequest.getDesignation());
	                    directorBackgroundDetailResponse.setAppointmentDate(directorBackgroundDetailRequest.getAppointmentDate());
	                    directorBackgroundDetailResponse.setDin(directorBackgroundDetailRequest.getDin());
	                    directorBackgroundDetailResponse.setMobile(directorBackgroundDetailRequest.getMobile());
	                    directorBackgroundDetailResponse.setDob(directorBackgroundDetailRequest.getDob());
	                    directorBackgroundDetailResponse.setPincode(directorBackgroundDetailRequest.getPincode());
	                    directorBackgroundDetailResponse.setStateCode(directorBackgroundDetailRequest.getStateCode());
	                    directorBackgroundDetailResponse.setCity(directorBackgroundDetailRequest.getCity());
	                    directorBackgroundDetailResponse.setGender((directorBackgroundDetailRequest.getGender() != null ? Gender.getById(directorBackgroundDetailRequest.getGender()).getValue() : " " ));
	                    directorBackgroundDetailResponse.setRelationshipType((directorBackgroundDetailRequest.getRelationshipType() != null ? DirectorRelationshipType.getById(directorBackgroundDetailRequest.getRelationshipType()).getValue() : " " ));
	                    directorBackgroundDetailResponseList.add(directorBackgroundDetailResponse);
	                }
	                ntbPrimaryViewRespone.setDirectorBackgroundDetailResponses(directorBackgroundDetailResponseList);
	            } catch (Exception e) {
	                logger.error("Problem to get Data of Director's Background {}", e);
	            }*/
	            
	            // get value of Financial Arrangements and set in response
	            try {
	                List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService
	                        .getFinancialArrangementDetailsList(toApplicationId, userId);
	                List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList = new ArrayList<>();
	                for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
	                    FinancialArrangementsDetailResponse financialArrangementsDetailResponse = new FinancialArrangementsDetailResponse();
	                    //financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
	                    financialArrangementsDetailResponse.setOutstandingAmount(financialArrangementsDetailRequest.getOutstandingAmount());
	                    financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
	                    financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
	                    //financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
	                    financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
	                    financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
	                    financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
	                    //financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
	                    //financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
	                    financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
	                }
	                ntbPrimaryViewRespone.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

	            } catch (Exception e) {
	                logger.error("Problem to get Data of Financial Arrangements Details {}", e);
	            }

	            //BANK STATEMENT
				ReportRequest reportRequest = new ReportRequest();
				reportRequest.setApplicationId(toApplicationId);
				reportRequest.setUserId(userId);
				List<Data> datas=new ArrayList<>();
				try {
					AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
					List<HashMap<String, Object>> hashMaps=(List<HashMap<String, Object>>) analyzerResponse.getData();
					if(!CommonUtils.isListNullOrEmpty(hashMaps))
					{
					for(HashMap<String,Object> hashMap:hashMaps)
					{
						Data data = MultipleJSONObjectHelper.getObjectFromMap(hashMap, Data.class);
						datas.add(data);
					}
					}
					ntbPrimaryViewRespone.setBankData(datas);
//					Data data = MultipleJSONObjectHelper.getObjectFromMap((HashMap<String, Object>) analyzerResponse.getData(), Data.class);
//					ntbPrimaryViewRespone.setMonthlyDetailList(data.getMonthlyDetailList());
//					ntbPrimaryViewRespone.setTop5FundReceivedList(data.getTop5FundReceivedList());
//					ntbPrimaryViewRespone.setTop5FundTransferedList(data.getTop5FundTransferedList());
				}catch (Exception e) {
					e.printStackTrace();
					logger.info("Error while getting perfios data");
				}
				
				//SCORING DATA
				ScoringRequest scoringRequest = new ScoringRequest();
				scoringRequest.setApplicationId(toApplicationId);
				scoringRequest.setFpProductId(productMappingId);
				try {
					ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
					ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)scoringResponse.getDataObject(),ProposalScoreResponse.class);
					ntbPrimaryViewRespone.setDataList(scoringResponse.getDataList());
					ntbPrimaryViewRespone.setManagementRiskScore(proposalScoreResponse.getManagementRiskScore());
					ntbPrimaryViewRespone.setFinancialRiskScore(proposalScoreResponse.getFinancialRiskScore());
					ntbPrimaryViewRespone.setBuisnessRiskScore(proposalScoreResponse.getBusinessRiskScore());
					ntbPrimaryViewRespone.setManagementRiskScoreWeight(proposalScoreResponse.getManagementRiskWeight());
					ntbPrimaryViewRespone.setFinancialRiskScoreWeight(proposalScoreResponse.getFinancialRiskWeight());
					ntbPrimaryViewRespone.setBuisnessRiskScoreWeight(proposalScoreResponse.getBusinessRiskWeight());
					ntbPrimaryViewRespone.setScoreInterpretation(proposalScoreResponse.getInterpretation());
				} catch (ScoringException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//Eligibility Data
				TermLoanParameter termLoanParameter = termLoanParameterRepository.getById(productMappingId);
//				Long assessmentId = termLoanParameter.getAssessmentMethodId().longValue();
//				if(!CommonUtils.isObjectNullOrEmpty(assessmentId)) {
//					ntbPrimaryViewRespone.setAssesmentId(assessmentId);
//				}
				EligibililityRequest eligibilityReq=new EligibililityRequest();
				eligibilityReq.setApplicationId(toApplicationId);
				eligibilityReq.setFpProductMappingId(productMappingId);
				System.out.println(" for eligibility appid============>>"+toApplicationId);
				
				try {
					
					EligibilityResponse eligibilityResp= eligibilityClient.corporateLoanData(eligibilityReq);
//					CLEligibilityRequest cLEligibilityRequest= MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>), CLEligibilityRequest.class);
					ntbPrimaryViewRespone.setEligibilityDataObject(eligibilityResp.getData());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//CGTMSE
				try {
					CGTMSEDataResponse cgtmseDataResp = thirdPartyClient.getCalulation(toApplicationId);
					ntbPrimaryViewRespone.setCgtmseData(cgtmseDataResp);
				}catch (Exception e) {
					e.printStackTrace();
					logger.info("Error while getting CGTMSE data");
				}
				

	            //GET DOCUMENTS
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
	            
	            if(isFinal) {
	            	
	            }
			return ntbPrimaryViewRespone;
		}
	
	    

}
