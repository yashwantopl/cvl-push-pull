package com.capitaworld.service.loans.service.sidbi.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.domain.sidbi.MeansOfFinanceDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.repository.sidbi.MeansOfFinanceDetailRepository;
import com.capitaworld.service.loans.service.sidbi.MeansOfFinanceDetailService;
import com.capitaworld.service.loans.service.sidbi.SidbiSpecificService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.enums.AccountingSystems;
import com.capitaworld.service.oneform.enums.sidbi.MeansOfFinanceParticulars;
import com.capitaworld.service.oneform.model.MasterResponse;

@Service
@Transactional
public class MeansOfFinanceDetailServiceImpl implements MeansOfFinanceDetailService{
	
	private static final Logger logger = LoggerFactory.getLogger(MeansOfFinanceDetailServiceImpl.class.getName());
	
	@Autowired
	MeansOfFinanceDetailRepository meansOfFinanceDetailRepository;
	
	@Autowired
	PrimaryCorporateDetailRepository primaryCorporateDetailRepository;
	
	@Autowired
	SidbiSpecificService sidbiSpecificService;
	

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				TotalCostOfProjectRequest financeMeansRequest = (TotalCostOfProjectRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, TotalCostOfProjectRequest.class);
				MeansOfFinanceDetail financeMeansDetail = null;
				if (financeMeansRequest.getId() != null) {
					financeMeansDetail = meansOfFinanceDetailRepository.findOne(financeMeansRequest.getId());
				} else {
					financeMeansDetail = new MeansOfFinanceDetail();
					financeMeansDetail.setCreatedBy(frameRequest.getUserId());
					financeMeansDetail.setCreatedDate(new Date());
				}

				BeanUtils.copyProperties(financeMeansRequest, financeMeansDetail);
				financeMeansDetail.setApplicationId(frameRequest.getApplicationId());
				financeMeansDetail.setModifiedBy(frameRequest.getUserId());
				financeMeansDetail.setModifiedDate(new Date());
				meansOfFinanceDetailRepository.save(financeMeansDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception in save totalCostOfProject :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}


	@Override
	public List<TotalCostOfProjectRequest> getMeansOfFinanceList(Long applicationId, Long userId) throws LoansException {
		try {
			List<MeansOfFinanceDetail> financeMeansDetails = meansOfFinanceDetailRepository
					.listFinanceMeansFromAppId(applicationId, userId);
			List<TotalCostOfProjectRequest> financeMeansRequests = new ArrayList<>();
			
			if(financeMeansDetails == null || financeMeansDetails.isEmpty()) {
				
				PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findByApplicationIdId(applicationId);
				
				for (MeansOfFinanceParticulars systems : MeansOfFinanceParticulars.getAll()) {
					TotalCostOfProjectRequest financeMeansDetailRequest = new TotalCostOfProjectRequest();
					financeMeansDetailRequest.setParticularName(systems.getDescription());
					financeMeansDetailRequest.setParticularsId(systems.getId());
					
					if(systems.getId().equals(1)) {
						Double promoterContribution = primaryCorporateDetail != null ? primaryCorporateDetail.getPromoterContribution() : 0;
						financeMeansDetailRequest.setToBeIncurred(promoterContribution);
					}else if(systems.getId().equals(4)) {
						Integer loanType = primaryCorporateDetail != null ? primaryCorporateDetail.getPurposeOfLoanId() : 0;
						if(loanType == 1) {
							Double loanAmt = sidbiSpecificService.getLoanAmountByApplicationId(applicationId);
							financeMeansDetailRequest.setToBeIncurred(loanAmt);
						}
					}
					financeMeansRequests.add(financeMeansDetailRequest);
                }
			}else {
				for (MeansOfFinanceDetail detail : financeMeansDetails) {
					TotalCostOfProjectRequest financeMeansDetailRequest = new TotalCostOfProjectRequest();
					BeanUtils.copyProperties(detail, financeMeansDetailRequest);
					financeMeansRequests.add(financeMeansDetailRequest);
				}
			}
			
			return financeMeansRequests;
		} catch (Exception e) {
			logger.error("Exception getting financeMeansDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
