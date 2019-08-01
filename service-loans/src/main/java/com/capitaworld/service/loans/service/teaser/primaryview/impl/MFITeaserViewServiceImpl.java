package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.api.eligibility.model.MFIRequest;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.model.teaser.primaryview.MFITeaserViewResponse;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.teaser.primaryview.MFITeaserViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;

/**
 * @author rahul.meena
 *
 */
@Service
@Transactional
public class MFITeaserViewServiceImpl implements MFITeaserViewService {
	
	private static final Logger logger = LoggerFactory.getLogger(MFITeaserViewServiceImpl.class);
	
	@Autowired
	private ScoringClient scoringClient;

	@Autowired
	private EligibilityClient eligibilityClient;
	
	@Autowired
	private MatchEngineClient matchEngineClient;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private  ProposalDetailsRepository proposalDetailsRepository;
	
	@Override
	public MFITeaserViewResponse getPrimaryMFiDetails(Long applicationId,Integer mfiFpType) {
		logger.info("ENTER HERE getPrimaryMFiDetails======{}====={}>>{}" , applicationId,mfiFpType);
		
		MFITeaserViewResponse mfiTeaserViewResponse = new MFITeaserViewResponse();
		
		Integer bussnessTypeId =null;
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId); // FOR BUSSNESS TYPE ID RELATED
		Long productMappingId = proposalDetailsRepository.getFpProductIdByApplicationId(applicationId); // GETTING FP PRODUCT ID BY APPLICATION ID 1441l 
		
		if(loanApplicationMaster!=null){
			bussnessTypeId = loanApplicationMaster.getBusinessTypeId();
		}
		
		if(mfiFpType!= null && mfiFpType == CommonUtils.mfiDataDisplayType.MFI_SCORING_DISPLAY_TYPE){ //1. FOR SCORING RELATED
			
		ScoringRequest scoringRequest = new ScoringRequest();  
		scoringRequest.setApplicationId(applicationId);
		scoringRequest.setFpProductId(productMappingId);
		try {
			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) scoringResponse.getDataObject(), ProposalScoreResponse.class);
			logger.info("SCORING RESPONSE HERE ======={}=====>",proposalScoreResponse);
			if (proposalScoreResponse != null){
				mfiTeaserViewResponse.setScoringModelName(proposalScoreResponse.getScoringModelName()!=null?proposalScoreResponse.getScoringModelName():" - ");
				mfiTeaserViewResponse.setDataList(scoringResponse.getDataList()!=null?scoringResponse.getDataList():" - ");
				mfiTeaserViewResponse.setDataObject(scoringResponse.getDataObject()!=null?scoringResponse.getDataObject():" - ");
				mfiTeaserViewResponse.setScoringResponseList(scoringResponse.getScoringResponseList()!=null?scoringResponse.getScoringResponseList():" - ");
			}
		} catch (Exception e) {
			logger.error("EXCEPTION IS GETTING WHILE SCORING RESPONSE HERE ======={}======{}=====>",e);
		}
	}
		
		if(mfiFpType!= null && mfiFpType == CommonUtils.mfiDataDisplayType.MFI_ASSESSMENT_TYPE){ //1. FOR SCORING RELATED
		MFIRequest eligibilityReq = new MFIRequest(); 		//2.  FOR ASSESSMENT LOAN DETAILS RELATED
		eligibilityReq.setApplicationId(applicationId);
		eligibilityReq.setFpProductMappingId(productMappingId);

		try {
			EligibilityResponse eligibilityResp = eligibilityClient.getMfiLoanDetails(eligibilityReq);
			mfiTeaserViewResponse.setEligibilityDataObject(eligibilityResp.getData()!=null?eligibilityResp.getData():null);
			logger.info("ELIGIBILITY RESPONSE HERE ======={}=====>",eligibilityResp);
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
			}
		//ENDS HERE ASSESSMENT AND SCORING RELATED CODDE HERE ====================================================================================== 
		}
		
		if(mfiFpType!= null && mfiFpType == CommonUtils.mfiDataDisplayType.MFI_MATCHES_DISPLAY_TYPE){ //FOR MFI MATCHE ENGINE DATA
		try {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setApplicationId(applicationId);
			matchRequest.setProductId(productMappingId);
			matchRequest.setBusinessTypeId(bussnessTypeId);
			MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfMFI(matchRequest);
				mfiTeaserViewResponse.setMatchesList(matchResponse.getMatchDisplayObjectList());
				logger.info("mathes response here  ======={}=====>",matchResponse);
		} catch (Exception e) {
			logger.error("EXCEPTION IS GETTING WHILE GET MATCHES DATA====={}======={}" , e);
		}
	}	
		return mfiTeaserViewResponse;
	}

}
