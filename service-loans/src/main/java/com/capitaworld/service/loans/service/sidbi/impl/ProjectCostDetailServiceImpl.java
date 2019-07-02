package com.capitaworld.service.loans.service.sidbi.impl;

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

import com.capitaworld.service.loans.domain.sidbi.ProjectCostDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.sidbi.ProjectCostDetailRepository;
import com.capitaworld.service.loans.service.sidbi.ProjectCostDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.enums.Denomination;

@Service
@Transactional
public class ProjectCostDetailServiceImpl implements ProjectCostDetailService{

	private static final Logger logger = LoggerFactory.getLogger(ProjectCostDetailServiceImpl.class.getName());
	@Autowired
	private ProjectCostDetailRepository projectCostDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			
			projectCostDetailRepository.inActive(frameRequest.getUserId(), frameRequest.getApplicationId());
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				TotalCostOfProjectRequest totalCostOfProjectRequest = (TotalCostOfProjectRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, TotalCostOfProjectRequest.class);
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
}
