package com.opl.service.loans.service.sidbi.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.common.MultipleJSONObjectHelper;
import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.corporate.TotalCostOfProjectRequest;
import com.opl.mudra.api.oneform.enums.Denomination;
import com.opl.mudra.api.sidbi.enums.SidbiCurrencyRate;
import com.opl.service.loans.domain.sidbi.ProjectCostDetail;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.sidbi.ProjectCostDetailRepository;
import com.opl.service.loans.service.sidbi.ProjectCostDetailService;
import com.opl.service.loans.service.sidbi.SidbiSpecificService;

@Service
@Transactional
public class ProjectCostDetailServiceImpl implements ProjectCostDetailService {

	private static final Logger logger = LoggerFactory.getLogger(ProjectCostDetailServiceImpl.class.getName());
	@Autowired
	private ProjectCostDetailRepository projectCostDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
    SidbiSpecificService sidbiSpecificService;
	
	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			
			projectCostDetailRepository.inActive(frameRequest.getUserId(), frameRequest.getApplicationId());
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				TotalCostOfProjectRequest totalCostOfProjectRequest = (TotalCostOfProjectRequest) MultipleJSONObjectHelper.getObjectFromMap(obj, TotalCostOfProjectRequest.class);
				
				convertAbsoluteValues(totalCostOfProjectRequest, frameRequest.getApplicationId(), frameRequest.getUserId());
				
				ProjectCostDetail totalCostOfProject = null;
//				if (totalCostOfProjectRequest.getId() != null) {
//					totalCostOfProject = projectCostDetailRepository.findOne(totalCostOfProjectRequest.getId());
//				} else {
//					totalCostOfProject = new ProjectCostDetail();
//					totalCostOfProject.setCreatedBy(frameRequest.getUserId());
//					totalCostOfProject.setCreatedDate(new Date());
//				}
				
				totalCostOfProject = new ProjectCostDetail();
				totalCostOfProject.setCreatedBy(frameRequest.getUserId());
				totalCostOfProject.setCreatedDate(new Date());
				
				BeanUtils.copyProperties(totalCostOfProjectRequest, totalCostOfProject);
				totalCostOfProject.setApplicationId(frameRequest.getApplicationId());
				totalCostOfProject.setModifiedBy(frameRequest.getUserId());
				totalCostOfProject.setModifiedDate(new Date());
				projectCostDetailRepository.save(totalCostOfProject);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception in save totalCostOfProject :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}


	@Override
	public List<TotalCostOfProjectRequest> getCostOfProjectDetailList(Long applicationId, Long userId)
			throws LoansException {
		try {
			List<ProjectCostDetail> totalCostOfProjectRequest = projectCostDetailRepository
					.listCostOfProjectFromAppId(applicationId, userId);
			List<TotalCostOfProjectRequest> totalCostOfProjectRequests = new ArrayList<TotalCostOfProjectRequest>(
					totalCostOfProjectRequest.size());

			for (ProjectCostDetail detail : totalCostOfProjectRequest) {
				TotalCostOfProjectRequest totalCostofProjectRequest = new TotalCostOfProjectRequest();				
				BeanUtils.copyProperties(detail, totalCostofProjectRequest);
//				this.convertValuesIn(totalCostofProjectRequest, applicationId, userId);
				totalCostOfProjectRequests.add(totalCostofProjectRequest);
			}
			return totalCostOfProjectRequests;
		} catch (Exception e) {
			logger.error("Exception getting TotalCostOfProjects  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	
	@Override
	public Double getCostOfProject(Long applicationId, Long userId) {
			List<ProjectCostDetail> totalCostOfProjectRequest = projectCostDetailRepository
					.listCostOfProjectFromAppId(applicationId, userId);
			Double totalCost=(double) 0;
			for(ProjectCostDetail totalCostOfProject : totalCostOfProjectRequest)
			{
				totalCost+=totalCostOfProject.getTotalCost();
			}
			
			Integer denominationId = loanApplicationRepository.getDenominationId(applicationId, userId);
			totalCost=totalCost*Denomination.getById(denominationId).getDigit();
			return totalCost;
	}
	
	private void convertAbsoluteValues(TotalCostOfProjectRequest totalCostOfProject,Long applicationId, Long userId) throws LoansException {
		SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);
		
		totalCostOfProject.setAlreadyIncurred(CommonUtils.convertTwoDecimalAbsoluteValues(totalCostOfProject.getAlreadyIncurred(), sidbiCurrencyRateObj.getRate()));
		totalCostOfProject.setToBeIncurred(CommonUtils.convertTwoDecimalAbsoluteValues(totalCostOfProject.getToBeIncurred(), sidbiCurrencyRateObj.getRate()));
		totalCostOfProject.setTotalCost(CommonUtils.convertTwoDecimalAbsoluteValues(totalCostOfProject.getTotalCost(), sidbiCurrencyRateObj.getRate()));
	}
	
	private void convertValuesIn(TotalCostOfProjectRequest totalCostOfProject,Long applicationId, Long userId) throws LoansException {
		SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);
		
		totalCostOfProject.setAlreadyIncurred(CommonUtils.convertTwoDecimalValuesIn(totalCostOfProject.getAlreadyIncurred(), sidbiCurrencyRateObj.getRate()));
		totalCostOfProject.setToBeIncurred(CommonUtils.convertTwoDecimalValuesIn(totalCostOfProject.getToBeIncurred(), sidbiCurrencyRateObj.getRate()));
		totalCostOfProject.setTotalCost(CommonUtils.convertTwoDecimalValuesIn(totalCostOfProject.getTotalCost(), sidbiCurrencyRateObj.getRate()));
		
	}
}
